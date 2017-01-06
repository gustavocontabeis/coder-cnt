--v2

drop schema imobiliaria;
create schema imobiliaria;

select * from imobiliaria.perfil_acesso

select * 
from imobiliaria.usuario usu
left join imobiliaria.usuario_perfilxxx up on usu.id_usuario = up.id_usuario;



Backup
mysqldump -u root -p imobiliaria > ~/imobiliaria.sql

Restore
mysql -u root -p imobiliaria < ~/imobiliaria.sql






alter table agenda 
    drop 
    foreign key FK3f7olp41ob71nrbutl09256i4; 

alter table agenda 
    drop 
    foreign key FKotvtjj5wnjgyki7b1m066weea; 

alter table agenda_evento 
    drop 
    foreign key FKq98fewivl1y0fb9njyke65de; 

alter table agenda_evento 
    drop 
    foreign key FKsl3cfh9pg1nu4yab0l8aapaso; 

alter table agenda_evento 
    drop 
    foreign key agenda_evento_cliente_fk; 

alter table agenda_evento 
    drop 
    foreign key agenda_evento_corretor_fk; 

alter table agenda_evento 
    drop 
    foreign key agenda_evento_motivo_evento_fk; 

alter table agenda_eventos 
    drop 
    foreign key agenda_eventos_evento_fk; 

alter table agenda_eventos 
    drop 
    foreign key agenda_eventos_agenda_fk; 

alter table bairro 
    drop 
    foreign key FKgcj010s7l6nxwjvv11osltyq3; 

alter table bairro 
    drop 
    foreign key FKga6562l53caoyrncnmajmwsxy; 

alter table bairro 
    drop 
    foreign key bairro_municipio_fk; 

alter table categoria 
    drop 
    foreign key FKs1p2d2g8im738dpsklppwxm65; 

alter table categoria 
    drop 
    foreign key FK8oke3cj9fwof2wm7n97tn7ls2; 

alter table cliente 
    drop 
    foreign key FK9upifrrv32vqsb33c796npxqu; 

alter table cliente 
    drop 
    foreign key FKqw2xn8lawmgd385o0bydd3r0c; 

alter table cliente 
    drop 
    foreign key cliente_negocio_fk; 

alter table cliente 
    drop 
    foreign key cliente_perfil_imovel_fk; 

alter table cliente_corretores 
    drop 
    foreign key cliente_corretores_corretor_fk; 

alter table cliente_corretores 
    drop 
    foreign key ccliente_corretores_cliente_fk; 

alter table construtora 
    drop 
    foreign key FK4ml7gykekhs5j2i5bmmgcct8i; 

alter table construtora 
    drop 
    foreign key FKmvodlowtba3p3m6nc0jge029v; 

alter table corretor 
    drop 
    foreign key FKte0yrq285rarbt90oqc74hcwg; 

alter table corretor 
    drop 
    foreign key FK5rgh5kymptgdusx1viqcs4lv0; 

alter table corretor 
    drop 
    foreign key corretor_equipe_fk; 

alter table corretor 
    drop 
    foreign key corretor_usuario_fk; 

alter table dados_edificio 
    drop 
    foreign key FKmksdsfv5omi6ab9r05xcqmrjh; 

alter table dados_edificio 
    drop 
    foreign key FK9abyyxhlwa6qc76jylixgx6aw; 

alter table dados_edificio 
    drop 
    foreign key dados_edificio_construtora_fk; 

alter table equipe 
    drop 
    foreign key FKft4cxolnhj5ascirp1gki24u3; 

alter table equipe 
    drop 
    foreign key FKt380ph8ovlddvq24inok0825n; 

alter table equipe 
    drop 
    foreign key equipe_corretor_gerente_fk; 

alter table evento_imoveis 
    drop 
    foreign key evento_imoveis_imovel_fk; 

alter table evento_imoveis 
    drop 
    foreign key evento_imoveis_evento_fk; 

alter table historico 
    drop 
    foreign key FKpjs6ydm5f5twxq52w28jmcbvf; 

alter table historico 
    drop 
    foreign key FKd0f6nuho294u324hh8yl543gg; 

alter table historico 
    drop 
    foreign key historico_corretor_fk; 

alter table historico 
    drop 
    foreign key historico_imovel_fk; 

alter table historicos_clientes 
    drop 
    foreign key historicos_clientes_cliente_fk; 

alter table historicos_clientes 
    drop 
    foreign key historicos_clientes_historico_fk; 

alter table imagem 
    drop 
    foreign key FKo65jhv2o6me0t96ydjvxi6k5b; 

alter table imagem 
    drop 
    foreign key FK78wjcgmn23qn031nj3tnduwc8; 

alter table imovel 
    drop 
    foreign key FK4ctqqe0g451m50epakh7a9vt7; 

alter table imovel 
    drop 
    foreign key FKc11ycjuc2ksdscqkr5sjk1uur; 

alter table imovel 
    drop 
    foreign key imovel_categoria_fk; 

alter table imovel 
    drop 
    foreign key imovel_imovel_complemento_fk; 

alter table imovel 
    drop 
    foreign key imovel_logradouro_fk; 

alter table imovel 
    drop 
    foreign key imovel_pessoa_proprietario_fk; 

alter table imovel_caracteristicas 
    drop 
    foreign key FKkt0vd2t4k7iaetsg8opjiugth; 

alter table imovel_caracteristicas 
    drop 
    foreign key FKsn75w7u0j4ebjdylynxc7rcvd; 

alter table imovel_caracteristicas 
    drop 
    foreign key imovel_caracteristicas_imovel_fk; 

alter table imovel_complemento 
    drop 
    foreign key FKc7xhma16u3sn868m2mwl8antg; 

alter table imovel_complemento 
    drop 
    foreign key FKeg4x2kw6sdtusubsog946flk5; 

alter table imovel_complemento 
    drop 
    foreign key imovel_complemento_dadosedificio_fk; 

alter table imovel_imagem 
    drop 
    foreign key FKolucvbhap0np8cr3ag42w8vaa; 

alter table imovel_imagem 
    drop 
    foreign key FKkv8khtq5dcy44asg9nwfnv3xx; 

alter table imovel_imagem 
    drop 
    foreign key imovel_imagem_arquivo_fk; 

alter table imovel_imagem 
    drop 
    foreign key imovel_imagem_imovel_fk; 

alter table logradouro 
    drop 
    foreign key FK2ymieicpnr4iuqm3sn4v1711w; 

alter table logradouro 
    drop 
    foreign key FKkgv7hsbnev1x2wbt56f3wu5p0; 

alter table logradouro 
    drop 
    foreign key Logradouro_Bairro_fk; 

alter table motivo_evento 
    drop 
    foreign key FKrq8ryrmlpwfs1tq1ips6wk29w; 

alter table motivo_evento 
    drop 
    foreign key FKckccura9jq8fcktp9dli4mxkx; 

alter table municipio 
    drop 
    foreign key FKckuirducjx869ta37v7w1qda4; 

alter table municipio 
    drop 
    foreign key FKgglmocksm4wb4vpi2hkn763hb; 

alter table negocio 
    drop 
    foreign key FKjh0kw0ntf1dh6gy6j5x2rw8hr; 

alter table negocio 
    drop 
    foreign key FKbscp00x70ew2behd712xvvlpb; 

alter table negocio 
    drop 
    foreign key negocio_cliente_fk; 

alter table perfil_imovel 
    drop 
    foreign key FKgd2bcwvemfieuf731rx8ibsjo; 

alter table perfil_imovel 
    drop 
    foreign key FK35psrvn5wjy79rpmrbsbpawnr; 

alter table perfil_imovel 
    drop 
    foreign key perfil_imovel_categoria_fk; 

alter table perfil_imovel 
    drop 
    foreign key perfil_imovel_cliente_fk; 

alter table perfil_imovel 
    drop 
    foreign key perfil_imovel_logradouro_fk; 

alter table perfis_imovel_bairros 
    drop 
    foreign key perfis_imovel_bairro_bair_fk; 

alter table perfis_imovel_bairros 
    drop 
    foreign key perfis_imovel_bairro_perf_fk; 

alter table perfis_imovel_municipios 
    drop 
    foreign key perfis_imovel_municipios_mun_fk; 

alter table perfis_imovel_municipios 
    drop 
    foreign key perfis_imovel_municipios_perf_fk; 

alter table pessoa 
    drop 
    foreign key FKel7iwgdergp7bb9buke4bmyk5; 

alter table pessoa 
    drop 
    foreign key FKnhglapqmft0pnivihlgv1pdkb; 

alter table pessoa 
    drop 
    foreign key pessoa_logradouro_fk; 

alter table pessoa 
    drop 
    foreign key pessoa_redes_sociais_fk; 

alter table redes_sociais 
    drop 
    foreign key FKjciia4id7sne7ch90lpx1fn9k; 

alter table redes_sociais 
    drop 
    foreign key FKhgfy5g3q5sbenf51fts9hrwu7; 

alter table usuario 
    drop 
    foreign key usuario_pessoa_fk; 

alter table usuario_perfil 
    drop 
    foreign key usuario_perfil_acesso_perfil_fk; 

alter table usuario_perfil 
    drop 
    foreign key usuario_perfil_acesso_usuario_fk; 

alter table usuario_usuario_perfil 
    drop 
    foreign key FKsbsquvof189xcjm95u5bmp0k5; 

alter table usuario_usuario_perfil 
    drop 
    foreign key FKelkh2oh6r38078dg31cdl8d52; 

drop table if exists agenda; 

drop table if exists agenda_evento; 

drop table if exists agenda_eventos; 

drop table if exists bairro; 

drop table if exists categoria; 

drop table if exists cliente; 

drop table if exists cliente_corretores; 

drop table if exists construtora; 

drop table if exists corretor; 

drop table if exists dados_edificio; 

drop table if exists equipe; 

drop table if exists evento_imoveis; 

drop table if exists hibernate_sequence; 

drop table if exists historico; 

drop table if exists historicos_clientes; 

drop table if exists imagem; 

drop table if exists imovel; 

drop table if exists imovel_caracteristicas; 

drop table if exists imovel_complemento; 

drop table if exists imovel_imagem; 

drop table if exists logradouro; 

drop table if exists motivo_evento; 

drop table if exists municipio; 

drop table if exists negocio; 

drop table if exists perfil_acesso; 

drop table if exists perfil_imovel; 

drop table if exists perfis_imovel_bairros; 

drop table if exists perfis_imovel_municipios; 

drop table if exists pessoa; 

drop table if exists redes_sociais; 

drop table if exists usuario; 

drop table if exists usuario_perfil; 

drop table if exists usuario_usuario_perfil; 

create table agenda (
    id_agenda bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    primary key (id_agenda)
);

create table agenda_evento (
    id_agenda_evento bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    concluido bit not null,
    descricao longtext,
    dia_inteiro bit not null,
    fim datetime not null,
    inicio datetime not null,
    particular bit not null,
    titulo varchar(60) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_cliente bigint not null,
    id_corretor bigint not null,
    id_motivo_evento bigint,
    primary key (id_agenda_evento)
); 

create table agenda_eventos (
    id_agenda bigint not null,
    id_evento bigint not null
); 

create table bairro (
    id_bairro bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    nome varchar(20) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_municipio bigint not null,
    primary key (id_bairro)
); 

create table categoria (
    id_categoria bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    nome varchar(20) not null,
    tipo varchar(20) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    primary key (id_categoria)
); 

create table cliente (
    id_cliente bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    capital_disponivel decimal(10,2) not null,
    prazo_intencional_compra varchar(15) not null,
    status_cliente varchar(15) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_negocio bigint not null,
    id_perfil_imovel bigint,
    primary key (id_cliente)
); 

create table cliente_corretores (
    id_cliente bigint not null,
    id_corretor bigint not null
); 

create table construtora (
    id_construtora bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    nome varchar(60) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    primary key (id_construtora)
); 

create table corretor (
    id_corretor bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    celular varchar(14) not null,
    cpf varchar(14) not null,
    creci varchar(15) not null,
    dtCadastro date not null,
    dtDesligamento date,
    dtNascimento date not null,
    email varchar(60) not null,
    nome varchar(60) not null,
    orgao_emissor varchar(20) not null,
    rg varchar(15) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_equipe bigint,
    id_usuario bigint not null,
    primary key (id_corretor)
); 

create table dados_edificio (
    id_dados_edificio bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    adm_condominio varchar(255) not null,
    andar integer not null,
    box_nr integer not null,
    convencao bit not null,
    elevador integer not null,
    escriturada bit not null,
    m2_priv decimal(10,2) not null,
    m2_total decimal(10,2) not null,
    nome_edificio varchar(30),
    nr_chave_claviculario integer not null,
    nr_pavimento integer not null,
    observacoes longtext,
    peso_agenciamento decimal(10,2) not null,
    peso_placa decimal(10,2) not null,
    peso_placa_recolocacao decimal(10,0) not null,
    terreno bit not null,
    un_pavimento integer not null,
    valor_condominio decimal(10,2) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_construtora bigint not null,
    primary key (id_dados_edificio)
); 

create table equipe (
    id_equipe bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    desativada_em date,
    nome varchar(60) not null,
    objetivo_captações decimal(10,0),
    objetivo_vendas decimal(10,0),
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_corretor_gerente bigint not null,
    primary key (id_equipe)
); 

create table evento_imoveis (
    id_evento bigint not null,
    id_imovel bigint not null
); 

create table hibernate_sequence (
    next_val bigint
); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

insert into hibernate_sequence values ( 1 ); 

create table historico (
    id_historico bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    data datetime not null,
    descricao longtext not null,
    pendente bit not null,
    tipo varchar(15) not null,
    valor_proposta decimal(19,2),
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_corretor bigint not null,
    id_imovel bigint not null,
    primary key (id_historico)
); 

create table historicos_clientes (
    id_historico bigint not null,
    id_cliente bigint not null
); 

create table imagem (
    id_arquivo bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    contentType varchar(255),
    dados tinyblob not null,
    extencao varchar(255),
    height integer,
    nome varchar(255),
    tamanho bigint,
    width integer,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    primary key (id_arquivo)
); 

create table imovel (
    id_imovel bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    ano_construcao integer,
    area_privativa decimal(10,2) not null,
    area_total decimal(10,2) not null,
    banheiros integer not null,
    chaves varchar(15),
    com_proposta bit not null,
    destaque_web bit not null,
    dormitorios integer not null,
    dt_atualizacao date,
    dt_cadastro date,
    dt_entrega date,
    estado_do_imovel varchar(20) not null,
    exibir_corretor_no_site bit not null,
    exibir_descricao_no_site bit not null,
    exibir_no_site bit not null,
    garagem integer not null,
    hora_visita varchar(5),
    imediacoes varchar(30),
    iptu decimal(10,2) not null,
    lancamento bit not null,
    logradouro_complemento varchar(15),
    logradouro_numero varchar(10) not null,
    nao_colocar_placa bit not null,
    ocupacao varchar(15) not null,
    orientacaoSolar varchar(15) not null,
    outro bit not null,
    pendente bit not null,
    proposito varchar(15) not null,
    regul_financ bit not null,
    salas integer not null,
    situacao varchar(20) not null,
    status varchar(255) not null,
    suites integer not null,
    tem_placa bit not null,
    texto_anuncio varchar(255) not null,
    tipo_garagem varchar(15) not null,
    valor decimal(10,2) not null,
    varandas integer not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_categoria bigint not null,
    id_imovel_complemento bigint,
    id_logradouro bigint not null,
    id_proprietario bigint not null,
    primary key (id_imovel)
); 

create table imovel_caracteristicas (
    id_imovel_caracteristicas bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    descricao varchar(255) not null,
    nome varchar(30) not null,
    tipo varchar(30) not null,
    valor varchar(255) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_imovel bigint not null,
    primary key (id_imovel_caracteristicas)
); 

create table imovel_complemento (
    id_imovel_complemento bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    comum integer not null,
    direita integer not null,
    esquerda integer not null,
    frente integer not null,
    fundos integer not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_dados_edificio bigint,
    primary key (id_imovel_complemento)
); 

create table imovel_imagem (
    id_imovel_imagem bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    descricao varchar(120),
    tipo varchar(255),
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_arquivo bigint not null,
    id_imovel bigint not null,
    primary key (id_imovel_imagem)
); 

create table logradouro (
    id_logradouro bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    cep varchar(9),
    nome varchar(80) not null,
    tipo_logradouro varchar(15) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_bairro bigint not null,
    primary key (id_logradouro)
); 

create table motivo_evento (
    id_motivo_evento bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    motivo varchar(60) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    primary key (id_motivo_evento)
); 

create table municipio (
    id_municipio bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    nome varchar(40) not null,
    uf varchar(2) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    primary key (id_municipio)
); 

create table negocio (
    DTYPE varchar(31) not null,
    id_negocio bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    observacoes longtext not null,
    valor decimal(10,2) not null,
    meses_contrato integer,
    valor_aluguel decimal(10,2),
    data_futura date not null,
    investimento_disponivel decimal(10,2) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_cliente bigint,
    primary key (id_negocio)
); 

create table perfil_acesso (
    id_perfil bigint not null,
    descricao varchar(255) not null,
    nome varchar(15) not null,
    primary key (id_perfil)
); 

create table perfil_imovel (
    id_perfil_imovel bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    banheiros integer not null,
    dormitorios integer not null,
    endereco_complemento varchar(60) not null,
    esperar_imovel bit not null,
    garagem integer not null,
    status_perfil varchar(255) not null,
    suites integer not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_categoria bigint not null,
    id_cliente bigint,
    id_logradouro bigint not null,
    primary key (id_perfil_imovel)
); 

create table perfis_imovel_bairros (
    id_perfis_imovel bigint not null,
    id_bairro bigint not null
); 

create table perfis_imovel_municipios (
    id_perfis_imovel bigint not null,
    id_municipios bigint not null
); 

create table pessoa (
    id_pessoa bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    cpf varchar(14) not null,
    dt_nascimento date not null,
    email varchar(60) not null,
    endereco_bloco varchar(30),
    endereco_complemento varchar(10),
    endereco_numero varchar(10) not null,
    estado_civil varchar(30),
    nacionalidade varchar(30) not null,
    nome varchar(50) not null,
    orgao_emissor varchar(15) not null,
    profissao varchar(30),
    ramal varchar(10),
    regime_casamento varchar(30),
    rg varchar(15) not null,
    telefone_principal varchar(13) not null,
    telefone_secundario varchar(13),
    veiculo_de_captacao varchar(255) not null,
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    id_logradouro bigint not null,
    id_redes_sociais bigint,
    primary key (id_pessoa)
); 

create table redes_sociais (
    id_redes_sociais bigint not null,
    dt_alteracao datetime,
    dt_inclusao datetime not null,
    facebook varchar(100),
    google_plus varchar(100),
    instagram varchar(100),
    linked_in varchar(100),
    id_pessoa_alteracao bigint,
    id_pessoa_inclusao bigint not null,
    primary key (id_redes_sociais)
); 

create table usuario (
    id_usuario bigint not null,
    dt_senha date not null,
    dt_ultimo_acesso date,
    inativo bit not null,
    login varchar(50) not null,
    senha varchar(50) not null,
    id_pessoa bigint,
    primary key (id_usuario)
); 

create table usuario_perfil (
    id_usuario_perfil bigint not null,
    nome_perfil varchar(50) not null,
    nome_usuario varchar(50) not null,
    id_perfil_acesso bigint not null,
    id_usuario bigint not null,
    primary key (id_usuario_perfil)
); 

create table usuario_usuario_perfil (
    Usuario_id_usuario bigint not null,
    usuarioPerfis_id_usuario_perfil bigint not null
); create index logradouro_nome_index on logradouro (nome); 

alter table perfil_acesso 
    add constraint UK_phscd09njq650crx6yfjmfsrv unique (nome); create index pessoa_cpf_index on pessoa (cpf); create index pessoa_nome_index on pessoa (nome); 

alter table agenda 
    add constraint FK3f7olp41ob71nrbutl09256i4 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table agenda 
    add constraint FKotvtjj5wnjgyki7b1m066weea 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table agenda_evento 
    add constraint FKq98fewivl1y0fb9njyke65de 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table agenda_evento 
    add constraint FKsl3cfh9pg1nu4yab0l8aapaso 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table agenda_evento 
    add constraint agenda_evento_cliente_fk 
    foreign key (id_cliente) 
    references cliente (id_cliente); 

alter table agenda_evento 
    add constraint agenda_evento_corretor_fk 
    foreign key (id_corretor) 
    references corretor (id_corretor); 

alter table agenda_evento 
    add constraint agenda_evento_motivo_evento_fk 
    foreign key (id_motivo_evento) 
    references motivo_evento (id_motivo_evento); 

alter table agenda_eventos 
    add constraint agenda_eventos_evento_fk 
    foreign key (id_evento) 
    references agenda_evento (id_agenda_evento); 

alter table agenda_eventos 
    add constraint agenda_eventos_agenda_fk 
    foreign key (id_agenda) 
    references agenda (id_agenda); 

alter table bairro 
    add constraint FKgcj010s7l6nxwjvv11osltyq3 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table bairro 
    add constraint FKga6562l53caoyrncnmajmwsxy 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table bairro 
    add constraint bairro_municipio_fk 
    foreign key (id_municipio) 
    references municipio (id_municipio); 

alter table categoria 
    add constraint FKs1p2d2g8im738dpsklppwxm65 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table categoria 
    add constraint FK8oke3cj9fwof2wm7n97tn7ls2 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table cliente 
    add constraint FK9upifrrv32vqsb33c796npxqu 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table cliente 
    add constraint FKqw2xn8lawmgd385o0bydd3r0c 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table cliente 
    add constraint cliente_negocio_fk 
    foreign key (id_negocio) 
    references negocio (id_negocio); 

alter table cliente 
    add constraint cliente_perfil_imovel_fk 
    foreign key (id_perfil_imovel) 
    references perfil_imovel (id_perfil_imovel); 

alter table cliente_corretores 
    add constraint cliente_corretores_corretor_fk 
    foreign key (id_corretor) 
    references corretor (id_corretor); 

alter table cliente_corretores 
    add constraint ccliente_corretores_cliente_fk 
    foreign key (id_cliente) 
    references cliente (id_cliente); 

alter table construtora 
    add constraint FK4ml7gykekhs5j2i5bmmgcct8i 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table construtora 
    add constraint FKmvodlowtba3p3m6nc0jge029v 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table corretor 
    add constraint FKte0yrq285rarbt90oqc74hcwg 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table corretor 
    add constraint FK5rgh5kymptgdusx1viqcs4lv0 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table corretor 
    add constraint corretor_equipe_fk 
    foreign key (id_equipe) 
    references equipe (id_equipe); 

alter table corretor 
    add constraint corretor_usuario_fk 
    foreign key (id_usuario) 
    references usuario (id_usuario); 

alter table dados_edificio 
    add constraint FKmksdsfv5omi6ab9r05xcqmrjh 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table dados_edificio 
    add constraint FK9abyyxhlwa6qc76jylixgx6aw 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table dados_edificio 
    add constraint dados_edificio_construtora_fk 
    foreign key (id_construtora) 
    references construtora (id_construtora); 

alter table equipe 
    add constraint FKft4cxolnhj5ascirp1gki24u3 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table equipe 
    add constraint FKt380ph8ovlddvq24inok0825n 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table equipe 
    add constraint equipe_corretor_gerente_fk 
    foreign key (id_corretor_gerente) 
    references corretor (id_corretor);
    
alter table evento_imoveis 
    add constraint evento_imoveis_imovel_fk 
    foreign key (id_imovel) 
    references imovel (id_imovel); 

alter table evento_imoveis 
    add constraint evento_imoveis_evento_fk 
    foreign key (id_evento) 
    references agenda_evento (id_agenda_evento); 

alter table historico 
    add constraint FKpjs6ydm5f5twxq52w28jmcbvf 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table historico 
    add constraint FKd0f6nuho294u324hh8yl543gg 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table historico 
    add constraint historico_corretor_fk 
    foreign key (id_corretor) 
    references corretor (id_corretor); 

alter table historico 
    add constraint historico_imovel_fk 
    foreign key (id_imovel) 
    references imovel (id_imovel); 

alter table historicos_clientes 
    add constraint historicos_clientes_cliente_fk 
    foreign key (id_cliente) 
    references cliente (id_cliente); 

alter table historicos_clientes 
    add constraint historicos_clientes_historico_fk 
    foreign key (id_historico) 
    references historico (id_historico); 

alter table imagem 
    add constraint FKo65jhv2o6me0t96ydjvxi6k5b 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table imagem 
    add constraint FK78wjcgmn23qn031nj3tnduwc8 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table imovel 
    add constraint FK4ctqqe0g451m50epakh7a9vt7 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table imovel 
    add constraint FKc11ycjuc2ksdscqkr5sjk1uur 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table imovel 
    add constraint imovel_categoria_fk 
    foreign key (id_categoria) 
    references categoria (id_categoria); 

alter table imovel 
    add constraint imovel_imovel_complemento_fk 
    foreign key (id_imovel_complemento) 
    references imovel_complemento (id_imovel_complemento); 

alter table imovel 
    add constraint imovel_logradouro_fk 
    foreign key (id_logradouro) 
    references logradouro (id_logradouro); 

alter table imovel 
    add constraint imovel_pessoa_proprietario_fk 
    foreign key (id_proprietario) 
    references pessoa (id_pessoa); 

alter table imovel_caracteristicas 
    add constraint FKkt0vd2t4k7iaetsg8opjiugth 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table imovel_caracteristicas 
    add constraint FKsn75w7u0j4ebjdylynxc7rcvd 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table imovel_caracteristicas 
    add constraint imovel_caracteristicas_imovel_fk 
    foreign key (id_imovel) 
    references imovel (id_imovel); 

alter table imovel_complemento 
    add constraint FKc7xhma16u3sn868m2mwl8antg 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table imovel_complemento 
    add constraint FKeg4x2kw6sdtusubsog946flk5 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table imovel_complemento 
    add constraint imovel_complemento_dadosedificio_fk 
    foreign key (id_dados_edificio) 
    references dados_edificio (id_dados_edificio); 

alter table imovel_imagem 
    add constraint FKolucvbhap0np8cr3ag42w8vaa 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table imovel_imagem 
    add constraint FKkv8khtq5dcy44asg9nwfnv3xx 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table imovel_imagem 
    add constraint imovel_imagem_arquivo_fk 
    foreign key (id_arquivo) 
    references imagem (id_arquivo); 

alter table imovel_imagem 
    add constraint imovel_imagem_imovel_fk 
    foreign key (id_imovel) 
    references imovel (id_imovel); 

alter table logradouro 
    add constraint FK2ymieicpnr4iuqm3sn4v1711w 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table logradouro 
    add constraint FKkgv7hsbnev1x2wbt56f3wu5p0 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table logradouro 
    add constraint Logradouro_Bairro_fk 
    foreign key (id_bairro) 
    references bairro (id_bairro); 

alter table motivo_evento 
    add constraint FKrq8ryrmlpwfs1tq1ips6wk29w 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table motivo_evento 
    add constraint FKckccura9jq8fcktp9dli4mxkx 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table municipio 
    add constraint FKckuirducjx869ta37v7w1qda4 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table municipio 
    add constraint FKgglmocksm4wb4vpi2hkn763hb 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table negocio 
    add constraint FKjh0kw0ntf1dh6gy6j5x2rw8hr 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table negocio 
    add constraint FKbscp00x70ew2behd712xvvlpb 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table negocio 
    add constraint negocio_cliente_fk 
    foreign key (id_cliente) 
    references cliente (id_cliente); 

alter table perfil_imovel 
    add constraint FKgd2bcwvemfieuf731rx8ibsjo 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table perfil_imovel 
    add constraint FK35psrvn5wjy79rpmrbsbpawnr 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table perfil_imovel 
    add constraint perfil_imovel_categoria_fk 
    foreign key (id_categoria) 
    references categoria (id_categoria); 

alter table perfil_imovel 
    add constraint perfil_imovel_cliente_fk 
    foreign key (id_cliente) 
    references cliente (id_cliente); 

alter table perfil_imovel 
    add constraint perfil_imovel_logradouro_fk 
    foreign key (id_logradouro) 
    references logradouro (id_logradouro); 

alter table perfis_imovel_bairros 
    add constraint perfis_imovel_bairro_bair_fk 
    foreign key (id_bairro) 
    references bairro (id_bairro); 

alter table perfis_imovel_bairros 
    add constraint perfis_imovel_bairro_perf_fk 
    foreign key (id_perfis_imovel) 
    references perfil_imovel (id_perfil_imovel); 

alter table perfis_imovel_municipios 
    add constraint perfis_imovel_municipios_mun_fk 
    foreign key (id_municipios) 
    references municipio (id_municipio); 

alter table perfis_imovel_municipios 
    add constraint perfis_imovel_municipios_perf_fk 
    foreign key (id_perfis_imovel) 
    references perfil_imovel (id_perfil_imovel); 

alter table pessoa 
    add constraint FKel7iwgdergp7bb9buke4bmyk5 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table pessoa 
    add constraint FKnhglapqmft0pnivihlgv1pdkb 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table pessoa 
    add constraint pessoa_logradouro_fk 
    foreign key (id_logradouro) 
    references logradouro (id_logradouro); 

alter table pessoa 
    add constraint pessoa_redes_sociais_fk 
    foreign key (id_redes_sociais) 
    references redes_sociais (id_redes_sociais); 

alter table redes_sociais 
    add constraint FKjciia4id7sne7ch90lpx1fn9k 
    foreign key (id_pessoa_alteracao) 
    references usuario (id_usuario); 

alter table redes_sociais 
    add constraint FKhgfy5g3q5sbenf51fts9hrwu7 
    foreign key (id_pessoa_inclusao) 
    references usuario (id_usuario); 

alter table usuario 
    add constraint usuario_pessoa_fk 
    foreign key (id_pessoa) 
    references pessoa (id_pessoa); 

alter table usuario_perfil 
    add constraint usuario_perfil_acesso_perfil_fk 
    foreign key (id_perfil_acesso) 
    references perfil_acesso (id_perfil); 

alter table usuario_perfil 
    add constraint usuario_perfil_acesso_usuario_fk 
    foreign key (id_usuario) 
    references usuario (id_usuario); 

alter table usuario_usuario_perfil 
    add constraint FKsbsquvof189xcjm95u5bmp0k5 
    foreign key (usuarioPerfis_id_usuario_perfil) 
    references usuario_perfil (id_usuario_perfil); 

alter table usuario_usuario_perfil 
    add constraint FKelkh2oh6r38078dg31cdl8d52 
    foreign key (Usuario_id_usuario) 
    references usuario (id_usuario);