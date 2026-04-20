package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.model.Usuario;
import com.UNSIJ.INESIS_BACKEND.repository.UsuarioRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IUsuarioService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioServiceJPA implements IUsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceJPA.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CatRolServiceJPA rolServiceJPA;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuarioModel) throws Exception {
        return usuarioRepository.save(usuarioModel);
    }

    @Override
    public Usuario create(Map<String, Object> params) throws Exception {
        Usuario usuarioModel = new Usuario();
        try {
            this.build(params, usuarioModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el usuario");
        }
        return this.save(usuarioModel);
    }

    @Override
    public Usuario update(Usuario usuarioModel, Map<String, Object> params) throws Exception {
        try {
            this.build(params, usuarioModel);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el usuario");
        }
        return this.save(usuarioModel);
    }

    @Override
    @Transactional
    public Usuario build(Map<String, Object> params, Usuario usuarioModel) {
        try {
            String usuario = JsonUtils.obtString(params, "usuario");
            if (usuario == null)
                throw new IllegalArgumentException("El campo usuario es obligatorio");
            usuarioModel.setUsuario(usuario);

            String contrasenia = JsonUtils.obtString(params, "contrasenia");

             if (contrasenia == null) throw new IllegalArgumentException("El campo contraseña es obligatorio");

            // Cifrar aquí
            usuarioModel.setContrasenia(passwordEncoder.encode(contrasenia));

            Boolean estatus = JsonUtils.obtBoolean(params, "estatus");
            if (estatus == null)
                throw new IllegalArgumentException("El campo estatus es obligatorio");
            usuarioModel.setEstatus(estatus);

            Map<String, Object> rolMap = (Map<String, Object>) params.get("rol");
            if (rolMap == null || rolMap.get("idCatRol") == null) {
                throw new IllegalArgumentException("El campo rol.idCatRol es obligatorio");
            }

            Long idRol = Long.parseLong(rolMap.get("idCatRol").toString());
            usuarioModel.setRol(rolServiceJPA.findById(idRol));

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el usuario");
        }
        return usuarioModel;
    }

    @Transactional
    public void actualizarPassword(Long idUsuario, String rawPassword) throws Exception {
        Usuario usuario = findById(idUsuario);
        String passwordCifrada = passwordEncoder.encode(rawPassword);
        usuario.setContrasenia(passwordCifrada);
        save(usuario);
    }

    @Override
    public Usuario updateInstance(Usuario usuarioInstance) throws Exception {
        Usuario usuarioBD = this.findById(usuarioInstance.getId());
        usuarioBD.setUsuario(usuarioInstance.getUsuario());
        usuarioBD.setContrasenia(usuarioInstance.getContrasenia());
        usuarioBD.setEstatus(usuarioInstance.getEstatus());
        return this.save(usuarioBD);
    }

    @Override
    public void deleteById(Long id) {
        Usuario usuarioModel = this.findById(id);
        if (usuarioModel != null) {
            usuarioRepository.deleteById(id);
        }
    }

    @Override
    public Usuario findByAlumnoId(Long idAlumno) {
        return usuarioRepository.findByAlumnoId(idAlumno)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró un usuario para el alumno con ID: " + idAlumno));
    }

    @Transactional
    public Usuario crearDesdeAlumno(Alumno alumno) throws Exception {
        Usuario usuario = new Usuario();
        try {
            usuario.setAlumno(alumno);
            usuario.setContrasenia(passwordEncoder.encode(alumno.getMatricula()));
            usuario.setRol(rolServiceJPA.findById(1L)); // Asignar rol de Alumno
            usuario.setEstatus(true); // Por defecto, el usuario está activo

            // Normalizar el nombre completo conservando separación entre nombres
            String nombreLimpio = limpiarYFormatear(alumno.getNombre());
            String[] nombreCompleto = nombreLimpio.split(" ");
            // Para el usuario usamos SOLO el primer nombre y el apellido (sin espacios)
            String apellidoPaternoLimpio = limpiarYFormatear(alumno.getApellidoPaterno()).replaceAll("\\s+", "");
            String primerNombre = nombreCompleto.length > 0 ? nombreCompleto[0] : "";
            String baseUsuario = primerNombre + "." + apellidoPaternoLimpio;
            String nombreUsuario = baseUsuario;
            int contador = 1;
            // Verificar si el usuario ya existe
            while (usuarioRepository.findByUsuario(nombreUsuario).isPresent()) {
                if (contador == 1 && nombreCompleto.length > 1) {
                    String segundoNombre = nombreCompleto[1].replaceAll("\\s+", "");
                    nombreUsuario = segundoNombre + "." + apellidoPaternoLimpio;
                } else if (contador == 2) {
                    String primerNombreSinEsp = primerNombre.replaceAll("\\s+", "");
                    String apellidoMaternoLimpio = limpiarYFormatear(alumno.getApellidoMaterno()).replaceAll("\\s+", "");
                    nombreUsuario = primerNombreSinEsp + "." + apellidoMaternoLimpio;
                } else {
                    nombreUsuario = baseUsuario + contador;
                }
                contador++;
            }
            usuario.setUsuario(nombreUsuario);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el usuario");
        }
        return this.save(usuario);
    }

    public String limpiarYFormatear(String texto) {
        if (texto == null)
            return "";
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        String textoSinAcentos = textoNormalizado.replaceAll("\\p{M}", "");
        // Mantener separación entre palabras pero consolidar espacios múltiples y trim
        return textoSinAcentos.toLowerCase().replaceAll("\\s+", " ").trim();
    }

    public Usuario findByRevisorId(Long idRevisor) {
        return usuarioRepository.findByRevisorId(idRevisor)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuario no encontrado para el revisor con ID: " + idRevisor));
    }

    public Usuario validarLogin(String usuario, String contrasenia) {
        // Intentar obtener el usuario junto con su alumno asociado
        Usuario user = usuarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Log para depuración: si el alumno está presente o no
        if (user.getAlumno() == null) {
            logger.debug("Usuario '{}' no tiene Alumno asociado (null) al validar login", usuario);
        } else {
            logger.debug("Usuario '{}' tiene Alumno asociado id={} al validar login", usuario, user.getAlumno().getId());
        }

        // No imprimir hashes en consola; usar logger.debug si realmente hace falta
        logger.debug("Validando contraseña para usuario={}", usuario);

        if (!passwordEncoder.matches(contrasenia, user.getContrasenia())) {
            logger.warn("Contraseña incorrecta para usuario={}", usuario);
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        return user;
    }

    public boolean verificarContrasena(String usuario, String contrasena) {
        Usuario user = usuarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        if (user.getContrasenia() == null) {
            throw new IllegalArgumentException("El usuario no tiene contraseña registrada");
        }
        return passwordEncoder.matches(contrasena, user.getContrasenia());
    }

    @Transactional
    public void cambiarContrasena(String usuario, String nuevaContrasena) {
        Usuario user = usuarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        // Cifrar la nueva contraseña antes de guardarla
        String nuevaContrasenaCifrada = passwordEncoder.encode(nuevaContrasena);
        user.setContrasenia(nuevaContrasenaCifrada);
        usuarioRepository.save(user);
    }

}
