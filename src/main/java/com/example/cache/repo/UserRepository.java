package com.example.cache.repo;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cache.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Cacheable(value="users")
			//, key = "#p0")
	User getById(Long id);
   
	@Cacheable(value="users")
			//, key = "#p0")
	User findByLastName(String lastName);
	
	
	@Query("select pr from User pr where pr.firstName = :firstName and pr.lastName = :lastName")
	List<User> findByUser(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
