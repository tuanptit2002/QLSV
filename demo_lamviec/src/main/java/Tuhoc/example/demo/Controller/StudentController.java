package Tuhoc.example.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Tuhoc.example.demo.DTO.PageDTO;
import Tuhoc.example.demo.DTO.ResponseDTO;
import Tuhoc.example.demo.DTO.StudentDTO;

import Tuhoc.example.demo.service.StudentService;


@RestController
@RequestMapping("/api/student")

public class StudentController {
	@Autowired(required = true)
	StudentService studentService;
	
	@PostMapping("/new")
	public ResponseDTO<StudentDTO> create(@ModelAttribute StudentDTO studentDTO)throws IllegalStateException, IOException{
		studentService.create(studentDTO);
		ResponseDTO<StudentDTO> rs = new ResponseDTO<>();
		rs.setData(studentDTO);
		rs.setStatus(200);
		return rs;
	}
	@PostMapping("update")
	public ResponseDTO<StudentDTO> update(@ModelAttribute StudentDTO studentDTO) throws IllegalStateException, IOException{
		studentService.update(studentDTO);
		ResponseDTO<StudentDTO> rs = new ResponseDTO<>();
		rs.setData(studentDTO);
		rs.setStatus(200);
		return rs;
	}
	@DeleteMapping("/delete")
	public ResponseDTO<Void> delete(@PathVariable("id")int id){
		studentService.delete(id);
		ResponseDTO<Void> rs = new ResponseDTO<>();
		rs.setStatus(200);
		return rs;
	}
	@GetMapping("/get/id")
	public ResponseDTO<StudentDTO> getByUserId(@PathVariable("id")int id){
		StudentDTO studentDTO = studentService.getStudentById(id);
		ResponseDTO<StudentDTO> rs = new ResponseDTO<>();
		rs.setData(studentDTO);
		rs.setStatus(200);
		return rs;
	}
	@GetMapping("/search")
	public ResponseDTO<PageDTO<StudentDTO>> search(
			@RequestParam(name="name",required = false)String name,
			@RequestParam(name="page",required = false)Integer page,
			@RequestParam(name="size",required = false)Integer size
			){
		size = size == null ? 10 : size;
		page = page == null ? 0 : page;
		name = name == null ? "" :name;
		PageDTO<StudentDTO> pageRS = null;
		pageRS = studentService.searchByStudentCode(name, page, size);
		ResponseDTO<PageDTO<StudentDTO>> rs = new ResponseDTO<>();
		rs.setData(pageRS);
		rs.setStatus(200);
		return rs;
	}
	
}
