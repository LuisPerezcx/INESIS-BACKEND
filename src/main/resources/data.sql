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


#------------------- REGISTROS DE CATGRUPO -------------------
-- Inicio registros

SELECT 1;

--1 semestre
INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '101', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Primero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '101');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '102', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Primero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '102');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '103', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Primero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '103');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '103', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Primero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '103');


INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '104', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Primero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '104');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '107', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Primero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '107');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '106', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Conservación de los Recursos Forestales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Primero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '106');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '108', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Gestión Ambiental'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Primero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '108');

--2 Semestre

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '201', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Segundo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '201');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '202', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Segundo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '202');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '103', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Segundo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '103');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '103', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Segundo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '103');



INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '204', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Segundo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '204');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '207', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'segundo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '207');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '206', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Conservación de los Recursos Forestales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Segundo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '106');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '208', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Gestión Ambiental'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Segundo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '208');

--3 Semestre

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '301', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Tercero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '301');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '302', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Tercero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '302');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '303', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Tercero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '303');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '303', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Tercero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '303');


INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '304', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Tercero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '304');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '307', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Tercero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '307');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '306', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Conservación de los Recursos Forestales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Tercero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '306');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '308', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Gestión Ambiental'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Tercero')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '308');

--4 Semestre

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '401', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Cuarto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '401');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '402', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Cuarto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '402');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '403', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Cuarto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '403');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '403', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Cuarto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '403');


INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '404', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Cuarto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '404');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '407', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Cuarto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '407');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '406', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Conservación de los Recursos Forestales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Cuarto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '406');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '408', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Maestría en Ciencias en Gestión Ambiental'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Cuarto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '408');

--5 Semestre 

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '501', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Quinto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '501');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '502', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Quinto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '502');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '503', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Quinto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '503');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '503', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Quinto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '503');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '504', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Quinto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '504');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '507', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Quinto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '507');


---6 semestre 

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '601', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Sexto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '601');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '602', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Sexto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '602');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '603', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Sexto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '603');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '603', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Sexto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '603');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '604', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Sexto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '604');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '607', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Sexto')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '607');

--7 Semestre

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '701', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Séptimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '701');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '702', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Séptimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '702');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '703', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Séptimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '703');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '703', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Séptimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '703');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '704', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Séptimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '704');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '707', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Séptimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '707');

--8 Semestre 

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '801', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Octavo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '801');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '802', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Octavo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '802');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '803', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Octavo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '803');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '803', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Octavo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '803');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '804', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Octavo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '804');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '807', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Octavo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '807');


--9 Semestre 

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '901', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Noveno')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '901');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '902', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Noveno')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '902');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '903', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Noveno')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '903');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '903', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Noveno')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '903');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '904', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Noveno')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '904');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '907', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Noveno')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '907');


--10 Semestre

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1001', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Ciencias Ambientales'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Décimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1001');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1002', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería Forestal'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Décimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1002');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1003', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Informática'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Décimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1003');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1003', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Ingeniería en Desarrollo de Software y Sistemas Inteligentes'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Décimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1003');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1004', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Biología'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Décimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1004');

INSERT INTO cat_grupo (nombre_grupo, id_cat_carrera, id_cat_semestre)
SELECT '1007', 
       (SELECT id_cat_carrera FROM cat_carrera WHERE nombre_carrera = 'Licenciatura en Administración Turística'),
       (SELECT id_cat_semestre FROM cat_semestre WHERE nombre_semestre = 'Décimo')
WHERE NOT EXISTS (SELECT 1 FROM cat_grupo WHERE nombre_grupo = '1007');

