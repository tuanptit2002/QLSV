package Tuhoc.example.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Tuhoc.example.demo.entity.Score;

public interface ScoreRepo extends JpaRepository<Score, Integer>{
	@Query("SELECT s FROM Score s where s.score = :p")
	Page<Score>searchByScore(@Param("p")double p,Pageable pageable);
}
