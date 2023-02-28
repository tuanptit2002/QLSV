package Tuhoc.example.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Tuhoc.example.demo.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
	@Query("SELECT s FROM Student s join s.user u where s.studentCode like :p")
	Page<Student>searchByStudentCode(@Param("p") String it ,Pageable pageable);
}
