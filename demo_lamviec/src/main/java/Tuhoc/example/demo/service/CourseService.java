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
import Tuhoc.example.demo.entity.Course;
import Tuhoc.example.demo.repo.CourseRepo;

@Service
public class CourseService {
	@Autowired
	CourseRepo courseRepo;
	@Transient
	public void create(CourseDTO courseDTO) {
		Course course = new Course();
		course.setName(courseDTO.getName());
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
