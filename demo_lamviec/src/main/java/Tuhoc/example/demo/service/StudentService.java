package Tuhoc.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Transient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Tuhoc.example.demo.DTO.CourseDTO;
import Tuhoc.example.demo.DTO.PageDTO;
import Tuhoc.example.demo.DTO.StudentDTO;
import Tuhoc.example.demo.entity.Course;
import Tuhoc.example.demo.entity.Student;
import Tuhoc.example.demo.entity.User;
import Tuhoc.example.demo.entity.UserRole;
import Tuhoc.example.demo.repo.CourseRepo;
import Tuhoc.example.demo.repo.StudentRepo;
import Tuhoc.example.demo.repo.UserRepo;
@Service
public class StudentService {
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	CourseRepo courseRepo;
	
	@Transient
	public void create(StudentDTO studentDTO) {
		User user = userRepo.findById(studentDTO.getUser().getId()).orElseThrow(NoResultException::new);
		for(UserRole userRole : user.getUserRole()) {
			if(userRole.getRole().equals("ROLE_STUDENT")) {
				Student student = new Student();
				student.setStudentCode(studentDTO.getStudentCode());
				student.setUser(user);
				List<Course> list = new ArrayList<>();
				for(CourseDTO courseDTO : studentDTO.getCourseDTO()) {
					Course course = courseRepo.findById(courseDTO.getId()).orElseThrow(NoResultException::new);
					list.add(course);
				}
				student.setCourse(list);
				studentRepo.save(student);
			}
		}
		
	}
	
	@Transient
	public void update(StudentDTO studentDTO) {
		Student student = studentRepo.findById(studentDTO.getId()).orElseThrow(NoResultException::new);
		student.setStudentCode(studentDTO.getStudentCode());
		
		List<Course>list = new ArrayList<>();
		for(CourseDTO courseDTO : studentDTO.getCourseDTO()) {
			Course course = courseRepo.findById(courseDTO.getId()).orElseThrow(NoResultException::new);
			list.add(course);
		}
		student.setCourse(list);
		
	}
	@Transient
	public void delete(int id) {
		studentRepo.deleteById(id);
	}
	
	public StudentDTO getStudentById(int id) {
		Student student = studentRepo.findById(id).orElseThrow(NoResultException::new);
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setStudentCode(student.getStudentCode());
		List<CourseDTO>list = new ArrayList<>();
		for(Course course  : student.getCourse()) {
			CourseDTO courseDTO = new ModelMapper().map(course, CourseDTO.class);
			list.add(courseDTO);
		}
		studentDTO.setCourseDTO(list);
		return studentDTO;
	}
	
	public PageDTO<StudentDTO> searchByStudentCode(String name, int page,int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Student> student = studentRepo.searchByStudentCode(name, pageable);
		PageDTO<StudentDTO> studentDTO = new PageDTO<>();
		studentDTO.setTotalElements(student.getTotalElements());
		studentDTO.setTotalPages(student.getTotalPages());
		List<StudentDTO> list = new ArrayList<>();
		for(Student stdents : student.getContent()) {
			
			list.add(new ModelMapper().map(stdents, StudentDTO.class));
		}
		studentDTO.setContents(list);
		return studentDTO;
	}
}
