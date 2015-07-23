CREATE DATABASE mercado;

\connect mercado

CREATE TABLE categoria(
id integer NOT NULL,
descricao varchar(150) NOT NULL,
CONSTRAINT pk_categoria_id PRIMARY KEY (id)
);

CREATE TABLE unidade(
id integer NOT NULL,
sigla char(5) NOT NULL,
descricao varchar(150) NOT NULL,
CONSTRAINT pk_unidade_id PRIMARY KEY (id)
);


CREATE TABLE produto(
id bigint NOT NULL,
descricao varchar(150) NOT NULL,
valor numeric(8,2) NOT NULL,
unidade_id integer NOT NULL,
categoria_id integer NOT NULL,
estoque bigint NOT NULL,
estoque_minimo bigint NOT NULL,
CONSTRAINT pk_produto_id PRIMARY KEY (id),
CONSTRAINT fk_produto_cat FOREIGN KEY (categoria_id) REFERENCES categoria(id),
CONSTRAINT fk_produdo_un  FOREIGN KEY (unidade_id)   REFERENCES unidade(id)
);

CREATE TABLE pessoa(
id bigint NOT NULL,
nome varchar(150) NOT NULL,
email varchar(255),
cep integer,
endereco varchar(255),
cidade varchar(150),
uf char(2),
tipo char(1),
CONSTRAINT pk_pessoa_id PRIMARY KEY(id)
);

CREATE TABLE pessoa_fisica(
 id bigint NOT NULL,
 rg bigint,
 cpf bigint NOT NULL,
 CONSTRAINT pk_pessoa_f_id PRIMARY KEY(id),
 CONSTRAINT uq_cpf UNIQUE(cpf)
);

CREATE TABLE pessoa_juridica(
 id bigint NOT NULL,
 cnpj bigint NOT NULL,
 ie bigint,
 im bigint,
 nome_fantasia varchar(255),
 CONSTRAINT pk_pessoa_j_id PRIMARY KEY(id),
 CONSTRAINT uq_cnpj UNIQUE(cnpj)
);

CREATE TABLE funcionario(
 id bigint NOT NULL,
 matricula bigint NOT NULL,
 ctps bigint NOT NULL,
 CONSTRAINT pk_funcionario_id PRIMARY KEY(id),
 CONSTRAINT uq_matricula UNIQUE(matricula)
);

CREATE TABLE telefone(
id bigint NOT NULL,
ddd integer NOT NULL,
numero integer NOT NULL,
tipo varchar(50),
ramal integer,
pessoa_id bigint NOT NULL,
CONSTRAINT pk_telefone_id PRIMARY KEY(id),
CONSTRAINT fk_telefone_pessoa FOREIGN KEY(pessoa_id) REFERENCES pessoa(id)
);

CREATE TABLE entrada(
id bigint NOT NULL,
fornecedor_id bigint NOT NULL,
produto_id bigint NOT NULL,
quantidade bigint NOT NULL,
data timestamp NOT NULL,
CONSTRAINT pk_entrada_id PRIMARY KEY(id),
CONSTRAINT fk_entrada_forn FOREIGN KEY(fornecedor_id) REFERENCES pessoa(id),
CONSTRAINT fk_entrada_produto FOREIGN KEY(produto_id) REFERENCES produto(id)
);

CREATE TABLE pedido(
id bigint NOT NULL,
cliente_id bigint NOT NULL,
data timestamp NOT NULL,
valor numeric(8,2) NOT NULL,
funcionario_id bigint NOT NULL,
CONSTRAINT pk_pedido_id PRIMARY KEY(id),
CONSTRAINT fk_pedido_cliente FOREIGN KEY(cliente_id) REFERENCES pessoa(id),
CONSTRAINT fk_pedido_func FOREIGN KEY(funcionario_id) REFERENCES pessoa(id)
);

CREATE TABLE saida(
id bigint NOT NULL,
pedido_id bigint NOT NULL,
produto_id bigint NOT NULL,
quantidade int NOT NULL,
valor numeric(8,2) NOT NULL,
desconto numeric(8,2),
CONSTRAINT pk_saida_id PRIMARY KEY(id),
CONSTRAINT fk_saida_pedido FOREIGN KEY(pedido_id) REFERENCES pedido(id),
CONSTRAINT fk_saida_prod FOREIGN KEY(produto_id) REFERENCES produto(id)
);

CREATE SEQUENCE seq_categoria;
CREATE SEQUENCE seq_unidade;
CREATE SEQUENCE seq_entrada;
CREATE SEQUENCE seq_funcionario;
CREATE SEQUENCE seq_pedido;
CREATE SEQUENCE seq_pessoa;
CREATE SEQUENCE seq_produto;
CREATE SEQUENCE seq_saida;
CREATE SEQUENCE seq_telefone;





