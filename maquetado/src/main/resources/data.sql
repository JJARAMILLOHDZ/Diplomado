USE Proyecto;

INSERT INTO `persona` (`nombre`, `apellidos`, `curp`, `rfc`) VALUES
('María', 'González Pérez', 'GOPM850101MDFLRN01', 'GOPM850101ABC'),
('Carlos', 'Martínez Sánchez', 'MASC900505HDFLRN02', 'MASC900505XYZ'),
('Ana', 'López Ramírez', 'LORA920202MDFLRN03', 'LORA920202DEF'),
('Pedro', 'Hernández García', 'HEGP870707HDFLRN04', 'HEGP870707GHI'),
('Laura', 'Díaz Fernández', 'DIFL950909MDFLRN05', 'DIFL950909JKL');

INSERT INTO rol (nombre) values('ADMINISTRADOR');
INSERT INTO rol (nombre) values('COLECCIONISTA');
INSERT INTO rol (nombre) values('VISITA');

INSERT INTO `Proyecto`.`usuario` (`nombre`, `password`, `correo`, `persona_id`) VALUES
('mgonzalez', 'password123', 'maria.gonzalez@email.com', 1),
('cmartinez', 'securepass', 'carlos.martinez@email.com', 2),
('alopez', 'ana1234', 'ana.lopez@email.com', 3),
('pherandez', 'pedro2023', 'pedro.hernandez@email.com', 4),
('ldiaz', 'lauraPass', 'laura.diaz@email.com', 5);

INSERT INTO `usuario_rol` (`status`, `usuario_id`, `rol_id`) VALUES
(1, 1, 1),
(1, 2, 1),
(1, 3, 2),
(1, 4, 2),
(1, 5, 3);