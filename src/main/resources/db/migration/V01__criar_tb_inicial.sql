CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_twitter` bigint(20) DEFAULT NULL,
  `name` tinyblob,
  `idioma` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `seguidores` bigint(20) DEFAULT NULL,
  `time_zone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=668 DEFAULT CHARSET=latin1;


CREATE TABLE hastag (
  hashtag_id bigint(20) NOT NULL AUTO_INCREMENT,
  texto varchar(255) DEFAULT NULL,
  PRIMARY KEY (hashtag_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO hastag (texto) values ('#openbanking');
<!--INSERT INTO hastag (texto) values ('#apifirst');
INSERT INTO hastag (texto) values ('#devops');
INSERT INTO hastag (texto) values ('#cloudfirst');
INSERT INTO hastag (texto) values ('#microservices');
INSERT INTO hastag (texto) values ('#apigateway');
INSERT INTO hastag (texto) values ('#oauth');
INSERT INTO hastag (texto) values ('#swagger');
INSERT INTO hastag (texto) values ('#raml');
INSERT INTO hastag (texto) values ('#openapis');-->


