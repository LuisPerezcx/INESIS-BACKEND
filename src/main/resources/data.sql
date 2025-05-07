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

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '101', 5, 1
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '101' AND id_cat_carrera = 5 AND id_cat_semestre = 1);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '201', 5, 2
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '201' AND id_cat_carrera = 5 AND id_cat_semestre = 2);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '301', 5, 3
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '301' AND id_cat_carrera = 5 AND id_cat_semestre = 3);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '401', 5, 4
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '401' AND id_cat_carrera = 5 AND id_cat_semestre = 4);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '501', 5, 5
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '501' AND id_cat_carrera = 5 AND id_cat_semestre = 5);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '601', 5, 6
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '601' AND id_cat_carrera = 5 AND id_cat_semestre = 6);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '701', 5, 7
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '701' AND id_cat_carrera = 5 AND id_cat_semestre = 7);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '801', 5, 8
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '801' AND id_cat_carrera = 5 AND id_cat_semestre = 8);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '901', 5, 9
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '901' AND id_cat_carrera = 5 AND id_cat_semestre = 9);

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1001', 5, 10
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1001' AND id_cat_carrera = 5 AND id_cat_semestre = 10);

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

#------------------- REGISTROS DE CATGRUPO -------------------
-- Inicio registros
