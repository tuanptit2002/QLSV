package Tuhoc.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Tuhoc.example.demo.DTO.CourseDTO;
import Tuhoc.example.demo.DTO.PageDTO;

import Tuhoc.example.demo.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@PostMapping("/new")
	public void create(@ModelAttribute CourseDTO courseDTO) {
		courseService.create(courseDTO);
	}
	@PostMapping("/update")
	public void update(@ModelAttribute CourseDTO courseDTO) {
		courseService.update(courseDTO);
	}
	@DeleteMapping("/delete")
	public void delete(@PathVariable("x")int x) {
		courseService.delete(x);
	}
	@GetMapping("/search")
	public PageDTO<CourseDTO> search(
			@RequestParam(name="name",required = false)String name,
			@RequestParam(name="page",required = false)Integer page,
			@RequestParam(name="size",required = false)Integer size
			){
		size = size == null ? 10 : size;
		page = page == null ? 0 : page;
		name = name == null ? "" :name;
		PageDTO<CourseDTO> pageRS = courseService.searchByName(name, page, size);
		return pageRS;
}
}
