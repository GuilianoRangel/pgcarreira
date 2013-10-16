-- --Imports no database de segurança
-- 
-- --Insert na tabela ambientes de sistema
-- INSERT INTO environment (name) VALUES ('WEB');
-- INSERT INTO environment (name) VALUES ('Desktop');
-- INSERT INTO environment (name) VALUES ('WebService');
-- INSERT INTO environment (name) VALUES ('Mobile');
-- 
-- --Insert na tabela unidades
-- INSERT INTO unit (name, visible) VALUES ('Sem Unidade', 'False');
-- 

--insert do PGC
-- cpfs de exemplo
--"01525541145"
--"03303766169"
--"03604957109"

do $$
declare 
	group_egresso text;
	pk_group int;
	pk_academico int;
	pk_user_group int;
	pk_allocation_user int;
begin
group_egresso := 'Egressos';
pk_academico :=87;
pk_allocation_user :=(select pk from allocation_user where user_pk=pk_academico);
pk_group := (select pk from group_user where name=group_egresso);
pk_user_group :=(select pk from user_group_user where group_pk=pk_group and user_pk = pk_allocation_user);




RAISE INFO '%', pk_group;

delete from permission_group where group_pk=pk_user_group;
delete from user_group_user where group_pk = pk_group and user_pk=pk_allocation_user;
delete from group_user where name=group_egresso;


insert into group_user(name,status) values(group_egresso,1);
pk_group := (select pk from group_user where name=group_egresso);

insert into user_group_user(group_pk,user_pk) values(pk_group,pk_allocation_user);
pk_user_group :=(select pk from user_group_user where group_pk=pk_group and user_pk = pk_allocation_user);



 insert into permission_group(action_pk,group_pk) 
 select pk,pk_user_group 
 from action where id in('AC_PROCURAR_ACADEMICO','AC_EDITAR_ACADEMICO');

end $$;


