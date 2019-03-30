package com.edivaldo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.edivaldo.model.HashTagEntity;

public interface HashTagRepository extends CrudRepository<HashTagEntity, Long> {
	Optional<HashTagEntity> findById(Long id);
}
