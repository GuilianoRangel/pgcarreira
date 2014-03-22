----cria um grupo para egresso
--insert into "group"(name,status,system_pk)
--values('egresso',1,(select pk from system where name='pgc'));
--
--
--insert into permission_group(action_pk,group_pk)
--select 
--action_pk,
--(select pk from "group" where name='egresso') as pk_group
-- from scenario_action 
--where scenario_id in(
--'CADASTRO_ACADEMICO_SCENARIO',
--'CADASTRO_ACADEMICO_INFOPROFISSIONAL_SCENARIO');

insert into tabela_basica(tabela,descricao)
values('sexo','Masculino'),('sexo','Feminino');

insert into tabela_basica(tabela,descricao)
values('estado','GO'),('estado','TO'),('estado','DF');

insert into tabela_basica(tabela,descricao)
values('estado_civil','Solteiro'),('estado_civil','Casado');