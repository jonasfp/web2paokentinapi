CREATE DATABASE web2padaria;
USE web2padaria;

CREATE TABLE paes (
  codigo int(8) NOT NULL AUTO_INCREMENT,
  nome varchar(200) NOT NULL,
  `desc` varchar(200) NOT NULL,
  minutos int(8) NOT NULL,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB;

CREATE TABLE fornadas (
  codigo int(8) NOT NULL AUTO_INCREMENT,
  prontoEm datetime NOT NULL,
  paoCodigo int(8) NOT NULL,
  PRIMARY KEY (codigo),
  KEY fkFornada (paoCodigo),
  CONSTRAINT fkFornada FOREIGN KEY (paoCodigo) REFERENCES paes (codigo) ON DELETE CASCADE
) ENGINE=InnoDB;