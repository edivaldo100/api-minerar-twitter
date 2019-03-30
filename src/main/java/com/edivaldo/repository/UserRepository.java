package com.edivaldo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edivaldo.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	Optional<UserEntity> findById(Long id);

	UserEntity findByName(String name);

	UserEntity findByIdTwitter(Long idTwitter);

	@Query(value = "select * from `user` order by seguidores desc limit 5;", nativeQuery = true)
	List<UserEntity> getUsersBySeguidoresLimit5();
}
