package com.edivaldo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edivaldo.model.TweetsEntity;

public interface TweetsRepository extends JpaRepository<TweetsEntity, Long> {
	Optional<TweetsEntity> findById(Long id);

	//@Query(value = "select * from tweets order by DATE_FORMAT(data_criacao, \"%Y %m %d %k %i %s\") asc;", nativeQuery = true)
	
	@Query(value = "select * from tweets order by data_criacao desc;", nativeQuery = true)
	List<TweetsEntity> findAllByData();
	
	/*
	 * @Query(value = "select distinct h.texto, t.user_id,u.idioma, u.pais \r\n" +
	 * "from tweets t, hastag h, `user`u\r\n" + "where h.texto = ?1", nativeQuery =
	 * true) List<HashTweetUserDto> findByHashTag(String hash);
	 */
}
