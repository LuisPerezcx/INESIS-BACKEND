#------------------- REGISTROS DE CATROL -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_rol (nombre_rol)
SELECT 'Alumno'
WHERE NOT EXISTS (SELECT 1 FROM cat_rol WHERE nombre_rol = 'Alumno');

INSERT INTO cat_rol (nombre_rol)
SELECT 'Administrador'
WHERE NOT EXISTS (SELECT 1 FROM cat_rol WHERE nombre_rol = 'Administrador');

INSERT INTO cat_rol (nombre_rol)
SELECT 'Revisor'
WHERE NOT EXISTS (SELECT 1 FROM cat_rol WHERE nombre_rol = 'Revisor');

#------------------- REGISTROS DE CATCARRERA -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_carrera (nombre_carrera, codigo_carrera)
SELECT 'Licenciatura en Informática', '03'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática');

INSERT INTO cat_carrera (nombre_carrera, codigo_carrera)
SELECT 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes', '03'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes');

INSERT INTO cat_carrera (nombre_carrera, codigo_carrera)
SELECT 'Licenciatura en Administración Turística', '05'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística');

INSERT INTO cat_carrera (nombre_carrera, codigo_carrera)
SELECT 'Licenciatura en Biología', '06'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología');

INSERT INTO cat_carrera (nombre_carrera, codigo_carrera)
SELECT 'Licenciatura en Ciencias Ambientales', '07'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales');

INSERT INTO cat_carrera (nombre_carrera, codigo_carrera)
SELECT 'Ingeniería Forestal', '08'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal');

INSERT INTO cat_carrera (nombre_carrera, codigo_carrera)
SELECT 'Maestría en Ciencias en Conservación de los Recursos Forestales', '09'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Conservación de los Recursos Forestales');

INSERT INTO cat_carrera (nombre_carrera, codigo_carrera)
SELECT 'Maestría en Ciencias en Gestión Ambiental', '10'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Gestión Ambiental');

#------------------- REGISTROS DE CATSEMESTRE -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_semestre (nombre_semestre)
SELECT 'Primero'
WHERE NOT EXISTS (SELECT 1 FROM cat_semestre WHERE nombre_semestre = 'Primero');

INSERT INTO cat_semestre (nombre_semestre)
SELECT 'Segundo'
WHERE NOT EXISTS (SELECT 1 FROM cat_semestre WHERE nombre_semestre = 'Segundo');

INSERT INTO cat_semestre (nombre_semestre)
SELECT 'Tercero'
WHERE NOT EXISTS (SELECT 1 FROM cat_semestre WHERE nombre_semestre = 'Tercero');

INSERT INTO cat_semestre (nombre_semestre)
SELECT 'Cuarto'
WHERE NOT EXISTS (SELECT 1 FROM cat_semestre WHERE nombre_semestre = 'Cuarto');

INSERT INTO cat_semestre (nombre_semestre)
SELECT 'Quinto'
WHERE NOT EXISTS (SELECT 1 FROM cat_semestre WHERE nombre_semestre = 'Quinto');

INSERT INTO cat_semestre (nombre_semestre)
SELECT 'Sexto'
WHERE NOT EXISTS (SELECT 1 FROM cat_semestre WHERE nombre_semestre = 'Sexto');

INSERT INTO cat_semestre (nombre_semestre)
SELECT 'Séptimo'
WHERE NOT EXISTS (SELECT 1 FROM cat_semestre WHERE nombre_semestre = 'Séptimo');

INSERT INTO cat_semestre (nombre_semestre)
SELECT 'Octavo'
WHERE NOT EXISTS (SELECT 1 FROM cat_semestre WHERE nombre_semestre = 'Octavo');

INSERT INTO cat_semestre (nombre_semestre)
SELECT 'Noveno'
WHERE NOT EXISTS (SELECT 1 FROM cat_semestre WHERE nombre_semestre = 'Noveno');

INSERT INTO cat_semestre (nombre_semestre)
SELECT 'Décimo'
WHERE NOT EXISTS (SELECT 1 FROM cat_semestre WHERE nombre_semestre = 'Décimo');

#------------------- REGISTROS DE CATSEXO -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_sexo (nombre_sexo)
SELECT 'Masculino'
WHERE NOT EXISTS (SELECT 1 FROM cat_sexo WHERE nombre_sexo = 'Masculino');

INSERT INTO cat_sexo (nombre_sexo)
SELECT 'Femenino'
WHERE NOT EXISTS (SELECT 1 FROM cat_sexo WHERE nombre_sexo = 'Femenino');

INSERT INTO cat_sexo (nombre_sexo)
SELECT 'Otro'
WHERE NOT EXISTS (SELECT 1 FROM cat_sexo WHERE nombre_sexo = 'Otro');

#------------------- REGISTROS DE CATMEDIOSTRANSPORTE -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Automóvil de amigos'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Automóvil de amigos');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Bicicleta'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Bicicleta');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Mototaxi'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Mototaxi');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Caminando'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Caminando');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Automovil familiar'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Automovil familiar');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Colectivo'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Colectivo');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Taxi'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Taxi');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Microbús'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Microbús');
