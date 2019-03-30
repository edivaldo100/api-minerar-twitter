
CREATE TABLE `tweets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` date DEFAULT NULL,
  `hash_tag_text` tinyblob,
  `hash_tag_hashtag_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgsj865ex34wqxvrwdfxbr25k5` (`hash_tag_hashtag_id`),
  KEY `FKsymxo1awyw5wc978n4fngkkrs` (`user_id`),
  CONSTRAINT `FKgsj865ex34wqxvrwdfxbr25k5` FOREIGN KEY (`hash_tag_hashtag_id`) REFERENCES `hastag` (`hashtag_id`),
  CONSTRAINT `FKsymxo1awyw5wc978n4fngkkrs` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=387 DEFAULT CHARSET=latin1;

CREATE TABLE tweets (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  hashtag_texto varchar(255) DEFAULT NULL,
  hashtag_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_HASHTAG (hashtag_id),
  CONSTRAINT FK_HASHTAG FOREIGN KEY (hashtag_id) REFERENCES hastag (hashtag_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO twitter.tweets(hashtag_texto, hashtag_id)VALUES('#FocaNoProjetoQueESucesso', 1);


CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  id_twitter bigint(20) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
