INSERT INTO rallydb.usuarios (alias, apellidos, email, f_nacimiento, nombre, num_telefono, password, rol)
SELECT 'admin1', 'Gonzalez Romero', 'admin1@gmail.com','1988/09/19', 'Alejo', '698547856', '$2a$10$3smbmFlYsjdViYGMn6kEZeQ.kTIVWJiKu1uY7.a5QTTE0.ylg2HDK', 'admin'
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE alias = 'admin1');
