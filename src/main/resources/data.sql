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


#------------------- REGISTROS DE USUARIO -------------------
SELECT 1;
INSERT INTO usuario (id_cat_rol, usuario, contrasenia, estatus)
SELECT 2, 'admin', '$2a$10$zEruov1alu6oS4DEHlkJM.W66f/YpSziRXekFqJ5H8IyCrYx7o9PK', 1



WHERE NOT EXISTS (
    SELECT 1 FROM usuario WHERE usuario = 'admin'
);

#------------------- REGISTROS DE CATCARRERA -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Licenciatura en Ciencias Ambientales'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Ingeniería Forestal'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Licenciatura en Informática'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Licenciatura en Biología'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Licenciatura en Administración Turística'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Maestría en Ciencias en Conservación de los Recursos Forestales'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Conservación de los Recursos Forestales');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Maestría en Ciencias en Gestión Ambiental'
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

#------------------- REGISTROS DE CATGRUPO -------------------
-- Inicio registros
SELECT 1;

-- Licenciatura en ciencias ambientales

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '101', 1, 1
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '101' AND id_cat_carrera = 1 AND id_cat_semestre = 1);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '201', 1, 2
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '201' AND id_cat_carrera = 1 AND id_cat_semestre = 2);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '301', 1, 3
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '301' AND id_cat_carrera = 1 AND id_cat_semestre = 3);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '401', 1, 4
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '401' AND id_cat_carrera = 1 AND id_cat_semestre = 4);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '501', 1, 5
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '501' AND id_cat_carrera = 1 AND id_cat_semestre = 5);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '601', 1, 6
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '601' AND id_cat_carrera = 1 AND id_cat_semestre = 6);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '701', 1, 7
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '701' AND id_cat_carrera = 1 AND id_cat_semestre = 7);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '801', 1, 8
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '801' AND id_cat_carrera = 1 AND id_cat_semestre = 8);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '901', 1, 9
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '901' AND id_cat_carrera = 1 AND id_cat_semestre = 9);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1001', 1, 10
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1001' AND id_cat_carrera = 1 AND id_cat_semestre = 10);

--Ingeniria Forestal 

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '102', 2, 1
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '102' AND id_cat_carrera = 2 AND id_cat_semestre = 1);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '202', 2, 2
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '202' AND id_cat_carrera = 2 AND id_cat_semestre = 2);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '302', 2, 3
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '302' AND id_cat_carrera = 2 AND id_cat_semestre = 3);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '402', 2, 4
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '402' AND id_cat_carrera = 2 AND id_cat_semestre = 4);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '502', 2, 5
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '502' AND id_cat_carrera = 2 AND id_cat_semestre = 5);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '602', 2, 6
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '602' AND id_cat_carrera = 2 AND id_cat_semestre = 6);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '702', 2, 7
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '702' AND id_cat_carrera = 2 AND id_cat_semestre = 7);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '802', 2, 8
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '802' AND id_cat_carrera = 2 AND id_cat_semestre = 8);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '902', 2, 9
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '902' AND id_cat_carrera = 2 AND id_cat_semestre = 9);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1002', 2, 10
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1002' AND id_cat_carrera = 2 AND id_cat_semestre = 10);

--Licenciatura en Informatica

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '103', 3, 1
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '103' AND id_cat_carrera = 3 AND id_cat_semestre = 1);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '203', 3, 2
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '203' AND id_cat_carrera = 3 AND id_cat_semestre = 2);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '303', 3, 3
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '303' AND id_cat_carrera = 3 AND id_cat_semestre = 3);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '403', 3, 4
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '403' AND id_cat_carrera = 3 AND id_cat_semestre = 4);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '503', 3, 5
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '503' AND id_cat_carrera = 3 AND id_cat_semestre = 5);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '603', 3, 6
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '603' AND id_cat_carrera = 3 AND id_cat_semestre = 6);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '703', 3, 7
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '703' AND id_cat_carrera = 3 AND id_cat_semestre = 7);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '803', 3, 8
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '803' AND id_cat_carrera = 3 AND id_cat_semestre = 8);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '903', 3, 9
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '903' AND id_cat_carrera = 3 AND id_cat_semestre = 9);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1003', 3, 10
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1003' AND id_cat_carrera = 3 AND id_cat_semestre = 10);

--Ingenieria en Desarrollo de software y sistemas inteligentes

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '103', 4, 1
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '103' AND id_cat_carrera = 4 AND id_cat_semestre = 1);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '203', 4, 2
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '203' AND id_cat_carrera = 4 AND id_cat_semestre = 2);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '303', 4, 3
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '303' AND id_cat_carrera = 4 AND id_cat_semestre = 3);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '403', 4, 4
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '403' AND id_cat_carrera = 4 AND id_cat_semestre = 4);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '503', 4, 5
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '503' AND id_cat_carrera = 4 AND id_cat_semestre = 5);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '603', 4, 6
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '603' AND id_cat_carrera = 4 AND id_cat_semestre = 6);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '703', 4, 7
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '703' AND id_cat_carrera = 4 AND id_cat_semestre = 7);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '803', 4, 8
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '803' AND id_cat_carrera = 4 AND id_cat_semestre = 8);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '903', 4, 9
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '903' AND id_cat_carrera = 4 AND id_cat_semestre = 9);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1003', 4, 10
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1003' AND id_cat_carrera = 4 AND id_cat_semestre = 10);

--Liceniatura en Biologia

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '104', 5, 1
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '104' AND id_cat_carrera = 5 AND id_cat_semestre = 1);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '204', 5, 2
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '204' AND id_cat_carrera = 5 AND id_cat_semestre = 2);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '304', 5, 3
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '304' AND id_cat_carrera = 5 AND id_cat_semestre = 3);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '404', 5, 4
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '404' AND id_cat_carrera = 5 AND id_cat_semestre = 4);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '504', 5, 5
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '504' AND id_cat_carrera = 5 AND id_cat_semestre = 5);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '604', 5, 6
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '604' AND id_cat_carrera = 5 AND id_cat_semestre = 6);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '704', 5, 7
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '704' AND id_cat_carrera = 5 AND id_cat_semestre = 7);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '804', 5, 8
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '804' AND id_cat_carrera = 5 AND id_cat_semestre = 8);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '904', 5, 9
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '904' AND id_cat_carrera = 5 AND id_cat_semestre = 9);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1004', 5, 10
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1004' AND id_cat_carrera = 5 AND id_cat_semestre = 10);

--Licenciatura en Administración Turistica

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '105', 6, 1
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '105' AND id_cat_carrera = 6 AND id_cat_semestre = 1);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '205', 6, 2
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '205' AND id_cat_carrera = 6 AND id_cat_semestre = 2);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '305', 6, 3
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '305' AND id_cat_carrera = 6 AND id_cat_semestre = 3);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '405', 6, 4
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '405' AND id_cat_carrera = 6 AND id_cat_semestre = 4);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '505', 6, 5
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '505' AND id_cat_carrera = 6 AND id_cat_semestre = 5);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '605', 6, 6
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '605' AND id_cat_carrera = 6 AND id_cat_semestre = 6);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '705', 6, 7
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '705' AND id_cat_carrera = 6 AND id_cat_semestre = 7);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '805', 6, 8
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '805' AND id_cat_carrera = 6 AND id_cat_semestre = 8);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '905', 6, 9
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '905' AND id_cat_carrera = 6 AND id_cat_semestre = 9);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1005', 6, 10
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1005' AND id_cat_carrera = 6 AND id_cat_semestre = 10);

--Maestria en ciencias de la conservacion

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '106', 7, 1
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '106' AND id_cat_carrera = 7 AND id_cat_semestre = 1);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '206', 7, 2
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '206' AND id_cat_carrera = 7 AND id_cat_semestre = 2);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '306', 7, 3
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '306' AND id_cat_carrera = 7 AND id_cat_semestre = 3);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '406', 7, 4
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '406' AND id_cat_carrera = 7 AND id_cat_semestre = 4);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '506', 7, 5
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '506' AND id_cat_carrera = 7 AND id_cat_semestre = 5);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '606', 7, 6
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '606' AND id_cat_carrera = 7 AND id_cat_semestre = 6);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '706', 7, 7
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '706' AND id_cat_carrera = 7 AND id_cat_semestre = 7);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '806', 7, 8
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '806' AND id_cat_carrera = 7 AND id_cat_semestre = 8);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '906', 7, 9
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '906' AND id_cat_carrera = 7 AND id_cat_semestre = 9);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1006', 7, 10
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1006' AND id_cat_carrera = 7 AND id_cat_semestre = 10);

--Maestria en ciencia de la gestión ambiental

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '107', 8, 1
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '107' AND id_cat_carrera = 8 AND id_cat_semestre = 1);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '207', 8, 2
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '207' AND id_cat_carrera = 8 AND id_cat_semestre = 2);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '307', 8, 3
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '307' AND id_cat_carrera = 8 AND id_cat_semestre = 3);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '407', 8, 4
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '407' AND id_cat_carrera = 8 AND id_cat_semestre = 4);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '507', 8, 5
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '507' AND id_cat_carrera = 8 AND id_cat_semestre = 5);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '607', 8, 6
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '607' AND id_cat_carrera = 8 AND id_cat_semestre = 6);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '707', 8, 7
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '707' AND id_cat_carrera = 8 AND id_cat_semestre = 7);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '807', 8, 8
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '807' AND id_cat_carrera = 8 AND id_cat_semestre = 8);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '907', 8, 9
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '907' AND id_cat_carrera = 8 AND id_cat_semestre = 9);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1007', 8, 10
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1007' AND id_cat_carrera = 8 AND id_cat_semestre = 10);

#------------------- REGISTROS DE CATSEXO -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_sexo (nombre_sexo)
SELECT 'Masculino'
WHERE NOT EXISTS (SELECT 1 FROM cat_sexo WHERE nombre_sexo = 'Masculino');

INSERT INTO cat_sexo (nombre_sexo)
SELECT 'Femenino'
WHERE NOT EXISTS (SELECT 1 FROM cat_sexo WHERE nombre_sexo = 'Femenino');


#------------------- REGISTROS DE ESTADO CIVIL -------------------
SELECT 1;

INSERT INTO cat_estado_civil (nombre_estado_civil)
SELECT 'Soltero'
WHERE NOT EXISTS (SELECT 1 FROM cat_estado_civil WHERE nombre_estado_civil = 'Soltero');

INSERT INTO cat_estado_civil (nombre_estado_civil)
SELECT 'Casado'
WHERE NOT EXISTS (SELECT 1 FROM cat_estado_civil WHERE nombre_estado_civil = 'Casado');

#-------------------- REGISTROS DE CATTIPOTRANSPORTE
SELECT 1;

INSERT INTO cat_tipo_transporte (nombre_tipo)
SELECT 'Particular'
WHERE NOT EXISTS (SELECT 1 FROM cat_tipo_transporte WHERE nombre_tipo = 'Particular');

INSERT INTO cat_tipo_transporte (nombre_tipo)
SELECT 'Público'
WHERE NOT EXISTS (SELECT 1 FROM cat_tipo_transporte WHERE nombre_tipo = 'Público');

INSERT INTO cat_tipo_transporte (nombre_tipo)
SELECT 'Privado'
WHERE NOT EXISTS (SELECT 1 FROM cat_tipo_transporte WHERE nombre_tipo = 'Privado');


#------------------- REGISTROS DE CATMEDIOSTRANSPORTE -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Automóvil propio'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Automóvil propio');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Bicicleta'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Bicicleta');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Automóvil de amigos'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Automóvil de amigos');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Motocicleta'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Motocicleta');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Caminando'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Caminando');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Automovil familiar'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Automovil familiar');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Mototaxi'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Mototaxi');

INSERT INTO cat_medios_transporte (nombre_medio)
SELECT 'Taxi'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_transporte WHERE nombre_medio = 'Taxi');

# ------------------------------------------- REGISTRO DE CAT_OCUPACION

SELECT 1;

INSERT INTO cat_ocupacion (nombre_ocupacion)
SELECT 'Comerciante'
WHERE NOT EXISTS (SELECT 1 FROM cat_ocupacion WHERE nombre_ocupacion = 'Comerciante');

INSERT INTO cat_ocupacion (nombre_ocupacion)
SELECT 'Jubilado o pensionado'
WHERE NOT EXISTS (SELECT 1 FROM cat_ocupacion WHERE nombre_ocupacion = 'Jubilado o pensionado');

INSERT INTO cat_ocupacion (nombre_ocupacion)
SELECT 'Empleado de gobierno'
WHERE NOT EXISTS (SELECT 1 FROM cat_ocupacion WHERE nombre_ocupacion = 'Empleado de gobierno');

INSERT INTO cat_ocupacion (nombre_ocupacion)
SELECT 'Labores del campo o de la pesca'
WHERE NOT EXISTS (SELECT 1 FROM cat_ocupacion WHERE nombre_ocupacion = 'Labores del campo o de la pesca');

INSERT INTO cat_ocupacion (nombre_ocupacion)
SELECT 'Empleado de empresa privada'
WHERE NOT EXISTS (SELECT 1 FROM cat_ocupacion WHERE nombre_ocupacion = 'Empleado de empresa privada');

INSERT INTO cat_ocupacion (nombre_ocupacion)
SELECT 'Empleado empresa ejidal o comunal'
WHERE NOT EXISTS (SELECT 1 FROM cat_ocupacion WHERE nombre_ocupacion = 'Empleado empresa ejidal o comunal');

INSERT INTO cat_ocupacion (nombre_ocupacion)
SELECT 'Tiene negocio propio'
WHERE NOT EXISTS (SELECT 1 FROM cat_ocupacion WHERE nombre_ocupacion = 'Tiene negocio propio');

INSERT INTO cat_ocupacion (nombre_ocupacion)
SELECT 'Otro'
WHERE NOT EXISTS (SELECT 1 FROM cat_ocupacion WHERE nombre_ocupacion = 'Otro');


/*REGISTRO DE CAT BIENES HOGAR*/
#------------------- REGISTROS DE CATBIENESHOGAR -------------------
SELECT 1;

INSERT INTO cat_bienes_hogar (nombre_bien)
SELECT 'Refrigerador'
WHERE NOT EXISTS (SELECT 1 FROM cat_bienes_hogar WHERE nombre_bien = 'Refrigerador');

INSERT INTO cat_bienes_hogar (nombre_bien)
SELECT 'Estufa de gas'
WHERE NOT EXISTS (SELECT 1 FROM cat_bienes_hogar WHERE nombre_bien = 'Estufa de gas');

INSERT INTO cat_bienes_hogar (nombre_bien)
SELECT 'Agua Caliente'
WHERE NOT EXISTS (SELECT 1 FROM cat_bienes_hogar WHERE nombre_bien = 'Agua Caliente');

INSERT INTO cat_bienes_hogar (nombre_bien)
SELECT 'Aire Acondicionado'
WHERE NOT EXISTS (SELECT 1 FROM cat_bienes_hogar WHERE nombre_bien = 'Aire Acondicionado');

INSERT INTO cat_bienes_hogar (nombre_bien)
SELECT 'Automovil particular'
WHERE NOT EXISTS (SELECT 1 FROM cat_bienes_hogar WHERE nombre_bien = 'Automovil particular');

INSERT INTO cat_bienes_hogar (nombre_bien)
SELECT 'Hornno de microondas'
WHERE NOT EXISTS (SELECT 1 FROM cat_bienes_hogar WHERE nombre_bien = 'Hornno de microondas');

INSERT INTO cat_bienes_hogar (nombre_bien)
SELECT 'Televisión'
WHERE NOT EXISTS (SELECT 1 FROM cat_bienes_hogar WHERE nombre_bien = 'Televisión');

INSERT INTO cat_bienes_hogar (nombre_bien)
SELECT 'Lavadora'
WHERE NOT EXISTS (SELECT 1 FROM cat_bienes_hogar WHERE nombre_bien = 'Lavadora');

INSERT INTO cat_bienes_hogar (nombre_bien)
SELECT 'Espacio privado de estudio'
WHERE NOT EXISTS (SELECT 1 FROM cat_bienes_hogar WHERE nombre_bien = 'Espacio privado de estudio');


#------------------- REGISTROS DE CATESCOLARIDAD -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_escolaridad (nombre_escolaridad)
SELECT 'Sin estudios'
WHERE NOT EXISTS (SELECT 1 FROM cat_escolaridad WHERE nombre_escolaridad = 'Sin estudios');

INSERT INTO cat_escolaridad (nombre_escolaridad)
SELECT 'Primaria'
WHERE NOT EXISTS (SELECT 1 FROM cat_escolaridad WHERE nombre_escolaridad = 'Primaria');

INSERT INTO cat_escolaridad (nombre_escolaridad)
SELECT 'Secundaria'
WHERE NOT EXISTS (SELECT 1 FROM cat_escolaridad WHERE nombre_escolaridad = 'Secundaria');

INSERT INTO cat_escolaridad (nombre_escolaridad)
SELECT 'Bachillerato'
WHERE NOT EXISTS (SELECT 1 FROM cat_escolaridad WHERE nombre_escolaridad = 'Bachillerato');

INSERT INTO cat_escolaridad (nombre_escolaridad)
SELECT 'Técnico'
WHERE NOT EXISTS (SELECT 1 FROM cat_escolaridad WHERE nombre_escolaridad = 'Técnico');

INSERT INTO cat_escolaridad (nombre_escolaridad)
SELECT 'Licenciatura'
WHERE NOT EXISTS (SELECT 1 FROM cat_escolaridad WHERE nombre_escolaridad = 'Licenciatura');

INSERT INTO cat_escolaridad (nombre_escolaridad)
SELECT 'Posgrado'
WHERE NOT EXISTS (SELECT 1 FROM cat_escolaridad WHERE nombre_escolaridad = 'Posgrado');


#------------------- REGISTROS DE CATINTERNET -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_internet (nombre_internet)
SELECT 'Sin acceso'
WHERE NOT EXISTS (SELECT 1 FROM cat_internet WHERE nombre_internet = 'Sin acceso');

INSERT INTO cat_internet (nombre_internet)
SELECT 'Datos móviles'
WHERE NOT EXISTS (SELECT 1 FROM cat_internet WHERE nombre_internet = 'Datos móviles');

INSERT INTO cat_internet (nombre_internet)
SELECT 'WiFi comunitario'
WHERE NOT EXISTS (SELECT 1 FROM cat_internet WHERE nombre_internet = 'WiFi comunitario');

INSERT INTO cat_internet (nombre_internet)
SELECT 'Red pública'
WHERE NOT EXISTS (SELECT 1 FROM cat_internet WHERE nombre_internet = 'Red pública');

INSERT INTO cat_internet (nombre_internet)
SELECT 'Internet en casa'
WHERE NOT EXISTS (SELECT 1 FROM cat_internet WHERE nombre_internet = 'Internet en casa');

INSERT INTO cat_internet (nombre_internet)
SELECT 'Otra'
WHERE NOT EXISTS (SELECT 1 FROM cat_internet WHERE nombre_internet = 'Otra');


#------------------- REGISTROS DE CAT_MATERIAL_VIVIENDA -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_material_vivienda (nombre_material)
SELECT 'Mamposteria'
WHERE NOT EXISTS (SELECT 1 FROM cat_material_vivienda WHERE nombre_material = 'Mamposteria');

INSERT INTO cat_material_vivienda (nombre_material)
SELECT 'Madera'
WHERE NOT EXISTS (SELECT 1 FROM cat_material_vivienda WHERE nombre_material = 'Madera');

INSERT INTO cat_material_vivienda (nombre_material)
SELECT 'Lámina'
WHERE NOT EXISTS (SELECT 1 FROM cat_material_vivienda WHERE nombre_material = 'Lámina');

INSERT INTO cat_material_vivienda (nombre_material)
SELECT 'Concreto'
WHERE NOT EXISTS (SELECT 1 FROM cat_material_vivienda WHERE nombre_material = 'Concreto');

INSERT INTO cat_material_vivienda (nombre_material)
SELECT 'Otro'
WHERE NOT EXISTS (SELECT 1 FROM cat_material_vivienda WHERE nombre_material = 'Otro');

#------------------- REGISTROS DE CAT_MEDIOS_ESTUDIO -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_medios_estudio (nombre_medios)
SELECT 'Impresora'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_estudio WHERE nombre_medios = 'Impresora');

INSERT INTO cat_medios_estudio (nombre_medios)
SELECT 'Librero'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_estudio WHERE nombre_medios = 'Librero');

INSERT INTO cat_medios_estudio (nombre_medios)
SELECT 'Escritorio / Mesa de trabajo'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_estudio WHERE nombre_medios = 'Escritorio / Mesa de trabajo');

INSERT INTO cat_medios_estudio (nombre_medios)
SELECT 'Calculadora'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_estudio WHERE nombre_medios = 'Calculadora');

INSERT INTO cat_medios_estudio (nombre_medios)
SELECT 'Libros especializados'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_estudio WHERE nombre_medios = 'Libros especializados');

INSERT INTO cat_medios_estudio (nombre_medios)
SELECT 'Computadora'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_estudio WHERE nombre_medios = 'Computadora');

INSERT INTO cat_medios_estudio (nombre_medios)
SELECT 'Diccionario'
WHERE NOT EXISTS (SELECT 1 FROM cat_medios_estudio WHERE nombre_medios = 'Diccionario');

#------------------- REGISTROS DE CAT_SITUACION_VIVIENDA -------------------
-- Inicio registros
SELECT 1;

INSERT INTO cat_situacion_vivienda (nombre_situacion)
SELECT 'Propia'
WHERE NOT EXISTS (SELECT 1 FROM cat_situacion_vivienda WHERE nombre_situacion = 'Propia');

INSERT INTO cat_situacion_vivienda (nombre_situacion)
SELECT 'Alquilada'
WHERE NOT EXISTS (SELECT 1 FROM cat_situacion_vivienda WHERE nombre_situacion = 'Alquilada');

INSERT INTO cat_situacion_vivienda (nombre_situacion)
SELECT 'Rento cuarto'
WHERE NOT EXISTS (SELECT 1 FROM cat_situacion_vivienda WHERE nombre_situacion = 'Rento cuarto');

INSERT INTO cat_situacion_vivienda (nombre_situacion)
SELECT 'Rento casa'
WHERE NOT EXISTS (SELECT 1 FROM cat_situacion_vivienda WHERE nombre_situacion = 'Rento casa');

INSERT INTO cat_situacion_vivienda (nombre_situacion)
SELECT 'Vivo con familiares'
WHERE NOT EXISTS (SELECT 1 FROM cat_situacion_vivienda WHERE nombre_situacion = 'Vivo con familiares');

INSERT INTO cat_situacion_vivienda (nombre_situacion)
SELECT 'Otro'
WHERE NOT EXISTS (SELECT 1 FROM cat_situacion_vivienda WHERE nombre_situacion = 'Otro');

#------------------- REGISTROS DE CAT_TIPO_VIVIENDA -------------------
-- Inicio de inserciones
SELECT 1;

INSERT INTO cat_tipo_vivienda (nombre_tipo)
SELECT 'Casa sola'
WHERE NOT EXISTS (SELECT 1 FROM cat_tipo_vivienda WHERE nombre_tipo = 'Casa sola');

INSERT INTO cat_tipo_vivienda (nombre_tipo)
SELECT 'Condominio'
WHERE NOT EXISTS (SELECT 1 FROM cat_tipo_vivienda WHERE nombre_tipo = 'Condominio');

INSERT INTO cat_tipo_vivienda (nombre_tipo)
SELECT 'Vecindad'
WHERE NOT EXISTS (SELECT 1 FROM cat_tipo_vivienda WHERE nombre_tipo = 'Vecindad');  

INSERT INTO cat_tipo_vivienda (nombre_tipo)
SELECT 'Departamento'
WHERE NOT EXISTS (SELECT 1 FROM cat_tipo_vivienda WHERE nombre_tipo = 'Departamento');


# ----------------------------------- REGISTROS DE CAT_TIPO_TRABAJO ----------------------------------------

SELECT 1;

INSERT INTO cat_tipo_trabajo (nombre_tipo)
SELECT 'Temporal'
WHERE NOT EXISTS (SELECT 1 FROM cat_tipo_trabajo WHERE nombre_tipo = 'Temporal');

INSERT INTO cat_tipo_trabajo (nombre_tipo)
SELECT 'Permanente'
WHERE NOT EXISTS (SELECT 1 FROM cat_tipo_trabajo WHERE nombre_tipo = 'Permanente');


# ----------------------------------- REGISTROS DE CAT_PARENTESCO ----------------------------------------

SELECT 1;

INSERT INTO cat_parentesco (nombre_parentesco)
SELECT 'Padre'
WHERE NOT EXISTS (SELECT 1 FROM cat_parentesco WHERE nombre_parentesco = 'Padre');

INSERT INTO cat_parentesco (nombre_parentesco)
SELECT 'Madre'
WHERE NOT EXISTS (SELECT 1 FROM cat_parentesco WHERE nombre_parentesco = 'Madre');

INSERT INTO cat_parentesco (nombre_parentesco)
SELECT 'Abuelo'
WHERE NOT EXISTS (SELECT 1 FROM cat_parentesco WHERE nombre_parentesco = 'Abuelo');

INSERT INTO cat_parentesco (nombre_parentesco)
SELECT 'Abuela'
WHERE NOT EXISTS (SELECT 1 FROM cat_parentesco WHERE nombre_parentesco = 'Abuela');

INSERT INTO cat_parentesco (nombre_parentesco)
SELECT 'Hermano/a'
    WHERE NOT EXISTS (SELECT 1 FROM cat_parentesco WHERE nombre_parentesco = 'Hermano/a');

INSERT INTO cat_parentesco (nombre_parentesco)
SELECT 'Hijo/a'
    WHERE NOT EXISTS (SELECT 1 FROM cat_parentesco WHERE nombre_parentesco = 'Hijo/a');

-- -------------------------------
-- REGISTROS PARA cat_servicio_otro
-- -------------------------------
SELECT 1;

INSERT INTO cat_servicio_otro (nombre_servicio)
SELECT 'Agua' WHERE NOT EXISTS (SELECT 1 FROM cat_servicio_otro WHERE nombre_servicio = 'Agua');

INSERT INTO cat_servicio_otro (nombre_servicio)
SELECT 'Luz' WHERE NOT EXISTS (SELECT 1 FROM cat_servicio_otro WHERE nombre_servicio = 'Luz');

INSERT INTO cat_servicio_otro (nombre_servicio)
SELECT 'Drenaje' WHERE NOT EXISTS (SELECT 1 FROM cat_servicio_otro WHERE nombre_servicio = 'Drenaje');

INSERT INTO cat_servicio_otro (nombre_servicio)
SELECT 'Telefono' WHERE NOT EXISTS (SELECT 1 FROM cat_servicio_otro WHERE nombre_servicio = 'Telefono');

INSERT INTO cat_servicio_otro (nombre_servicio)
SELECT 'Otro' WHERE NOT EXISTS (SELECT 1 FROM cat_servicio_otro WHERE nombre_servicio = 'Otro');

-- ------------------------------- insertar Regiones
INSERT INTO cat_region (nombre_region)
SELECT 'Cañada' WHERE NOT EXISTS (SELECT 1 FROM cat_region WHERE nombre_region='Cañada');
INSERT INTO cat_region (nombre_region)
SELECT 'Costa' WHERE NOT EXISTS (SELECT 1 FROM cat_region WHERE nombre_region='Costa');
INSERT INTO cat_region (nombre_region)
SELECT 'Istmo' WHERE NOT EXISTS (SELECT 1 FROM cat_region WHERE nombre_region='Istmo');
INSERT INTO cat_region (nombre_region)
SELECT 'Mixteca' WHERE NOT EXISTS (SELECT 1 FROM cat_region WHERE nombre_region='Mixteca');
INSERT INTO cat_region (nombre_region)
SELECT 'Papaloapan' WHERE NOT EXISTS (SELECT 1 FROM cat_region WHERE nombre_region='Papaloapan');
INSERT INTO cat_region (nombre_region)
SELECT 'Sierra Sur' WHERE NOT EXISTS (SELECT 1 FROM cat_region WHERE nombre_region='Sierra Sur');
INSERT INTO cat_region (nombre_region)
SELECT 'Sierra Norte' WHERE NOT EXISTS (SELECT 1 FROM cat_region WHERE nombre_region='Sierra Norte');
INSERT INTO cat_region (nombre_region)
SELECT 'Valles Centrales' WHERE NOT EXISTS (SELECT 1 FROM cat_region WHERE nombre_region='Valles Centrales');
INSERT INTO cat_region (nombre_region)
SELECT 'Otro' WHERE NOT EXISTS (SELECT 1 FROM cat_region WHERE nombre_region = 'Otro');

-- ---------------------------------------insertar distritos

-- Cañada (2 distritos: Teotitlán y Cuicatlán)
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Teotitlán', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Cañada')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Teotitlán');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Cuicatlán', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Cañada')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Cuicatlán');

-- Costa (3 distritos: Jamiltepec, Juquila, Pochutla)
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Jamiltepec', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Costa')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Jamiltepec');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Juquila', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Costa')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Juquila');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Pochutla', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Costa')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Pochutla');

-- Istmo (2 distritos: Juchitán y Tehuantepec)
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Juchitán', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Istmo')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Juchitán');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Tehuantepec', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Istmo')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Tehuantepec');

-- Mixteca (7 distritos: Silacayoapam, Huajuapan, Coixtlahuaca, Juxtlahuaca, Teposcolula, Nochixtlán, Tlaxiaco)
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Silacayoapam', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Mixteca')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Silacayoapam');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Huajuapan', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Mixteca')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Huajuapan');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Coixtlahuaca', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Mixteca')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Coixtlahuaca');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Juxtlahuaca', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Mixteca')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Juxtlahuaca');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Teposcolula', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Mixteca')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Teposcolula');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Nochixtlán', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Mixteca')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Nochixtlán');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Tlaxiaco', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Mixteca')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Tlaxiaco');

-- Papaloapan (2 distritos: Tuxtepec y Choapan)
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Tuxtepec', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Papaloapan')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Tuxtepec');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Choapan', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Papaloapan')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Choapan');

-- Sierra Sur (4 distritos: Putla, Sola de Vega, Miahuatlán, Yautepec)
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Putla', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Sierra Sur')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Putla');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Sola de Vega', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Sierra Sur')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Sola de Vega');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Miahuatlán', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Sierra Sur')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Miahuatlán');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Yautepec', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Sierra Sur')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Yautepec');

-- Sierra Norte (hoy Sierra de Juárez) (3 distritos: Ixtlán, Villa Alta, Mixe)
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Ixtlán', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Sierra Norte')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Ixtlán');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Villa Alta', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Sierra Norte')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Villa Alta');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Mixe', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Sierra Norte')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Mixe');

-- Valles Centrales (7 distritos: Centro, Etla, Zaachila, Zimatlán, Ejutla, Tlacolula, Ocotlán)
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Centro', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Valles Centrales')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Centro');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Etla', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Valles Centrales')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Etla');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Zaachila', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Valles Centrales')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Zaachila');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Zimatlán', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Valles Centrales')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Zimatlán');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Ejutla', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Valles Centrales')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Ejutla');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Tlacolula', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Valles Centrales')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Tlacolula');
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Ocotlán', (SELECT id_cat_region FROM cat_region WHERE nombre_region='Valles Centrales')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito='Ocotlán');

-- OTRO
INSERT INTO cat_distrito (nombre_distrito, id_cat_region)
SELECT 'Otro', (SELECT id_cat_region FROM cat_region WHERE nombre_region = 'Otro')
    WHERE NOT EXISTS (SELECT 1 FROM cat_distrito WHERE nombre_distrito = 'Otro');