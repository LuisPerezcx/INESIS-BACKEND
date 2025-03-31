#------------------- REGISTROS DE CATCARRERA -------------------

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Ingeniería Forestal'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Licenciatura en Administración Turística'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Licenciatura en Biología'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Licenciatura en Ciencias Ambientales'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Maestría en Ciencias en Conservación de los Recursos Forestales'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Conservación de los Recursos Forestales');

INSERT INTO cat_carrera (nombre_carrera)
SELECT 'Maestría en Ciencias en Gestión Ambiental'
WHERE NOT EXISTS (SELECT 1 FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Gestión Ambiental');

#------------------- REGISTROS DE CATSEMESTRE -------------------

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

INSERT INTO cat_sexo (nombre_sexo)
SELECT 'Masculino'
WHERE NOT EXISTS (SELECT 1 FROM cat_sexo WHERE nombre_sexo = 'Masculino');

INSERT INTO cat_sexo (nombre_sexo)
SELECT 'Femenino'
WHERE NOT EXISTS (SELECT 1 FROM cat_sexo WHERE nombre_sexo = 'Femenino');

INSERT INTO cat_sexo (nombre_sexo)
SELECT 'Otro'
WHERE NOT EXISTS (SELECT 1 FROM cat_sexo WHERE nombre_sexo = 'Otro');

