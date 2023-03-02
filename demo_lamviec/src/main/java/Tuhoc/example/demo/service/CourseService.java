package Tuhoc.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Transient;

import Tuhoc.example.demo.DTO.StudentDTO;
import Tuhoc.example.demo.entity.Student;
import Tuhoc.example.demo.repo.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Tuhoc.example.demo.DTO.CourseDTO;
import Tuhoc.example.demo.DTO.PageDTO;
import Tuhoc.example.demo.entity.Course;
import Tuhoc.example.demo.repo.CourseRepo;

@Service
public class CourseService {
	@Autowired
	CourseRepo courseRepo;
	@Autowired
	private StudentRepo studentRepo;

//	@Transient
	public void create(CourseDTO courseDTO) {
		Course course = new Course();
		course.setName(courseDTO.getName());
		// lambda expression, stream api: java 8
//		courseDTO.getStudents().forEach(student -> {
//			Student foundStudent = studentRepo.findById(student.getId()).orElseThrow(() -> new RuntimeException("Invalid Student Id: " + student.getId()));
//			course.getStudent().add(foundStudent);
//		});

		for (StudentDTO student : courseDTO.getStudents()) {
			Student foundStudent = studentRepo.findById(student.getId())
					.orElseThrow(() -> new RuntimeException("Student not found: " + student.getId()));
			course.getStudent().add(foundStudent);
		}

		// Session :
//		for (StudentDTO studentDTO : courseDTO.getStudents()) {
//			Student student = new Student();
//			student.setId(studentDTO.getId());
//			course.getStudent().add(student);
//		}

		// student chua ton tai va khi tao course muon tao luon ca student (keyword: cascading) => loi khi ma thuc hien persist xuong db
		// voi bang trung gian thi chi loi runtime khi thuc thi

		courseRepo.save(course);
	}
	@Transient
	public void update(CourseDTO courseDTO) {
		Course course = courseRepo.findById(courseDTO.getId()).orElseThrow(NoResultException::new);
		course.setName(courseDTO.getName());
		courseRepo.save(course);
	}
	@Transient
	public void delete(int id) {
		courseRepo.deleteById(id);
	}
	public PageDTO<CourseDTO>searchByName(String s,int page,int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Course> pageCourse = courseRepo.searchByName(s, pageable);
		PageDTO<CourseDTO> pageCourseDTO = new PageDTO<>();
		pageCourseDTO.setTotalElements(pageCourse.getTotalElements());
		pageCourseDTO.setTotalPages(pageCourse.getTotalPages());
		List<CourseDTO> list = new ArrayList<>();
		for(Course course : pageCourse.getContent()) {
			list.add(new ModelMapper().map(course, CourseDTO.class));
		}
		pageCourseDTO.setContents(list);
		return pageCourseDTO;
	}
	
}
