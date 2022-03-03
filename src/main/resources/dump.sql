CREATE DATABASE IF NOT EXISTS seguradora;

USE seguradora;

CREATE TABLE t_cliente (
  id int AUTO_INCREMENT PRIMARY KEY,
  nome_completo varchar(255),
  cpf varchar(50),
  cidade varchar(50),
  uf varchar(50)
);

CREATE TABLE t_apolice (
  id int AUTO_INCREMENT PRIMARY KEY,
  numero_apolice int(10),
  dt_inicio_vigencia datetime,
  dt_fim_vigencia datetime,
  placa_veiculo varchar(10),
  valor double,
  fk_cliente int
);

INSERT INTO t_cliente VALUES
	(null, 'Kauê Batista', '49343192983', 'São Paulo', 'São Paulo');

INSERT INTO t_apolice VALUES
	(null, '1234567891', '2020-01-01 22:22:33', '2020-03-01 22:22:33', 'LQX2122', 20.0, 1);

ALTER TABLE t_apolice ADD CONSTRAINT id_fk_cliente
FOREIGN KEY(fk_cliente) REFERENCES t_cliente(id);