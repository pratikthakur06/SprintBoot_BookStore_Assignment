package com.gl.SpringBootBookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.SpringBootBookStore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select User from User u where u.email = ?1 and u.password = ?2")
	public User validateCredentails(String email, String password);
	
	public User findByEmail(String email);
}
