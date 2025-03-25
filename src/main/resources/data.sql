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

