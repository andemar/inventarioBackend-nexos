insert into cargo (id_cargo, titulo,uuid) values (100, 'Asesor de ventas','00000000-0000-0000-0001-000000000001');
insert into cargo (id_cargo, titulo,uuid) values (101, 'Administrador','00000000-0000-0000-0001-000000000002');
insert into cargo (id_cargo, titulo,uuid) values (102, 'Soporte','00000000-0000-0000-0001-000000000003');

insert into usuario (id_usuario, nombre, edad, fecha_ingreso, id_cargo, uuid) values (100, 'Usuario 1', 24, '2021-06-01 00:00:00.000000', 100, '00000000-0000-0000-0002-000000000001');

insert into mercancia (id_mercancia, cantidad, fecha_ingreso, nombre_producto, id_usuario, uuid) values (100, 10, '2021-06-01 00:00:00.000000', 'mercancia 1', 100, '00000000-0000-0000-0003-000000000001');