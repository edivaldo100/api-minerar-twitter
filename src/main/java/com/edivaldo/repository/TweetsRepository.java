package com.edivaldo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edivaldo.model.TweetsEntity;
public interface TweetsRepository extends JpaRepository<TweetsEntity, Long> {
	Optional<TweetsEntity> findById(Long id);
	
    @Query(value = "select * from tweets order by DATE_FORMAT(data_criacao, \"%Y %m %d %k %i %s\") asc;", nativeQuery = true)
    List<TweetsEntity> findAllByData();

}
