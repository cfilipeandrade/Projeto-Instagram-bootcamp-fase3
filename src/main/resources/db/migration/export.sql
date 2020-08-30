CREATE DATABASE instagram /*!40100 DEFAULT CHARACTER SET utf8 */;
USE instagram;
SET NAMES utf8 ;

CREATE TABLE conta (
  nomeUsuario varchar(50) NOT NULL,
  senha varchar(72) NOT NULL,
  email varchar(100) DEFAULT NULL,
  ativo tinyint(1) DEFAULT NULL,
  inativo tinyint(1) DEFAULT NULL,
  dataAtualizacao datetime DEFAULT NULL,
  dataCadastro datetime DEFAULT NULL,
  nome varchar(100) DEFAULT NULL,
  codigoVerif varchar(50) DEFAULT NULL,
  PRIMARY KEY (nomeUsuario)
);

CREATE TABLE fotoPerfil (
  idFotoPerfil int(11) NOT NULL AUTO_INCREMENT,
  nomeArquivo varchar(50) NOT NULL,
  nomeUsuario varchar(50) NOT NULL,
  datahora datetime DEFAULT NULL,
  PRIMARY KEY (idFotoPerfil),
  UNIQUE KEY nomeArquivo_UNIQUE (nomeArquivo),
  KEY idxFKNomeUsusario (nomeUsuario),
  CONSTRAINT fkFotoPerfilNomeUsusario FOREIGN KEY (nomeUsuario) REFERENCES conta (nomeUsuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE block (
  nomeUsuario1 varchar(50) NOT NULL,
  nomeUsuario2 varchar(50) NOT NULL,
  datahora datetime NOT NULL,
  PRIMARY KEY (nomeUsuario1,nomeUsuario2)
);

CREATE TABLE comentario (
  isComentario int(11) NOT NULL AUTO_INCREMENT,
  conteudo varchar(200) DEFAULT NULL,
  datahora datetime DEFAULT NULL,
  nomeUsuario varchar(50) DEFAULT NULL,
  idFoto int(11) DEFAULT NULL,
  PRIMARY KEY (idComentario),
  KEY idxFKNomeUsusario (nomeUsuario),
  KEY idxfkIdFoto (idFoto),
  CONSTRAINT fkComentarioIdFoto FOREIGN KEY (idFoto) REFERENCES foto (idFoto) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fkComentarioNomeUsuario FOREIGN KEY (nomeUsuario) REFERENCES conta (nomeUsuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE segue (
  idSegue int(11) NOT NULL AUTO_INCREMENT,
  nomeUsuario1 varchar(50) NOT NULL,
  nomeUsuario2 varchar(50) NOT NULL,
  dataHora datetime NOT NULL,
  naoQuandoPostar tinyint(1) DEFAULT '0',
  PRIMARY KEY (idSegue),
  UNIQUE KEY nomeUsuario1UNIQUE (nomeUsuario1, nomeUsuario2),
  KEY idxfkSegueNomeUsuario (nomeUsuario2),
  CONSTRAINT fkSegueNomeUsuario1 FOREIGN KEY (nomeUsuario1) REFERENCES conta (nomeUsuario) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fkSegueNomeUsuario2 FOREIGN KEY (nomeUsuario2) REFERENCES conta (nomeUsuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE like (
  idLike int(11) NOT NULL AUTO_INCREMENT,
  nomeUsuario varchar(50) NOT NULL,
  idFoto int(11) NOT NULL,
  datahora datetime DEFAULT NULL,
  PRIMARY KEY (idLike),
  UNIQUE KEY idFotoUNIQUE (idFoto, nomeUsuario),
  KEY fkLikeNomeUsuario (nomeUsuario) /*!80000 INVISIBLE */,
  CONSTRAINT fkLikeIdFoto FOREIGN KEY (idFoto) REFERENCES foto (idFoto) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fkLikeNomeUsuario FOREIGN KEY (nomeUsuario) REFERENCES conta (nomeUsuario) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE local (
  idLocal int(11) NOT NULL AUTO_INCREMENT,
  local varchar(200) NOT NULL,
  PRIMARY KEY (idLocal)
);

CREATE TABLE foto (
  idFoto int(11) NOT NULL AUTO_INCREMENT,
  titulo varchar(200) DEFAULT NULL,
  nomeArquivo varchar(50) NOT NULL,
  datahora datetime DEFAULT NULL,
  dataAtualizacao datetime DEFAULT NULL,
  idLocal int(11) DEFAULT NULL,
  modoCamera varchar(50) DEFAULT NULL,
  nomeUsuario varchar(50) DEFAULT NULL,
  PRIMARY KEY (idFoto),
  UNIQUE KEY nomeUsuarioUNIQUE (nomeArquivo),
  KEY idxfkIdLoca (idLocal),
  KEY idxfknomeUsuario (nomeUsuario),
  CONSTRAINT fkFotoIdLocal FOREIGN KEY (idLocal) REFERENCES local (idLocal) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fkFotoNomeUsuario FOREIGN KEY (nomeUsuario) REFERENCES conta (nomeUsuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE perfil (
  nomeUsuario varchar(50) NOT NULL,
  biografia varchar(100) DEFAULT '',
  telefone varchar(15) DEFAULT '',
  sexo varchar(10) DEFAULT 'm',
  website varchar(100) DEFAULT '',
  PRIMARY KEY (nomeUsuario),
  CONSTRAINT fkPerfilNomeUsuario FOREIGN KEY (nomeUsuario) REFERENCES conta (nomeUsuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE resposta (
  idResposta int(11) NOT NULL AUTO_INCREMENT,
  datahora datetime DEFAULT NULL,
  conteudo varchar(200) DEFAULT NULL,
  idComentario int(11) DEFAULT NULL,
  nomeUsuario varchar(50) DEFAULT NULL,
  PRIMARY KEY (idResposta),
  KEY idxNomeUsuario (nomeUsuario),
  KEY idxIdComentario (idComentario),
  CONSTRAINT fkRespostaIdComentario FOREIGN KEY (idComentario) REFERENCES comentario (idComentario) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fkRespostaNomeUsuario FOREIGN KEY (nomeUsuario) REFERENCES conta (nomeUsuario) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE configuracao (
  nomeUsuario varchar(50) NOT NULL,
  lingua varchar(10) DEFAULT 'ENG',
  contaPrivada tinyint(1) DEFAULT '0',
  PRIMARY KEY (nomeUsuario),
  KEY idxfkNomeUsuario (nomeUsuario),
  CONSTRAINT fkConfiguracaoNomeUsuario FOREIGN KEY (nomeUsuario) REFERENCES conta (nomeUsuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE story (
  idStory int(11) NOT NULL,
  datahora datetime DEFAULT NULL,
  nomeArquivo varchar(50) DEFAULT NULL,
  nomeUsuario varchar(50) DEFAULT NULL,
  PRIMARY KEY (idStory),
  KEY idxfkNomeUsuario (nomeUsuario),
  CONSTRAINT fkStoryNomeUsuario FOREIGN KEY (nomeUsuario) REFERENCES conta (nomeUsuario) ON DELETE CASCADE ON UPDATE CASCADE
);




