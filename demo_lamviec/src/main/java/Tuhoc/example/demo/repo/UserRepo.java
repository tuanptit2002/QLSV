package Tuhoc.example.demo.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Tuhoc.example.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.name LIKE :x ")
	Page<User> searchByName(@Param("x") String s, Pageable pageable);
	
}
