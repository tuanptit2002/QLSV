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

import Tuhoc.example.demo.DTO.UserRoleDTO;
import Tuhoc.example.demo.service.UserRoleService;

@RestController
@RequestMapping("/user/role")
public class UserRoleController {
	@Autowired
	UserRoleService userRoleService;
	
	@PostMapping("/new")
	public ResponseDTO<UserRoleDTO> create(@ModelAttribute UserRoleDTO userRoleDTO)throws IllegalStateException,IOException{
		 userRoleService.create(userRoleDTO);
		 ResponseDTO<UserRoleDTO> ps= new ResponseDTO<>();
		 ps.setData(userRoleDTO);
		 ps.setStatus(200);
		 return ps;
	}
	@PostMapping("/update")
	public ResponseDTO<UserRoleDTO> update(@ModelAttribute UserRoleDTO userRoleDTO)throws IllegalStateException,IOException{
		 userRoleService.update(userRoleDTO);
		 ResponseDTO<UserRoleDTO> ps= new ResponseDTO<>();
		 ps.setData(userRoleDTO);
		 ps.setStatus(200);
		 return ps;
	}
	@DeleteMapping("/delete")
	public ResponseDTO<Void> delete(@PathVariable("id")int id){
		 userRoleService.delete(id);
		 ResponseDTO<Void> ps= new ResponseDTO<>();
		
		 ps.setStatus(200);
		 return ps;
	}
	@DeleteMapping("/delete/user")
	public ResponseDTO<Void> deleteById(@PathVariable("id")int id){
		 userRoleService.deleteByUserId(id);
		 ResponseDTO<Void> ps= new ResponseDTO<>();
		 ps.setStatus(200);
		 return ps;
	}
	@GetMapping("/search")
	
	public ResponseDTO<PageDTO<UserRoleDTO>>search(
			@RequestParam(name="name",required = false)String name,
			@RequestParam(name="page",required = false)Integer page,
			@RequestParam(name="size",required = false)Integer size
			){
		size = size == null ? 10 : size;
		page = page == null ? 0 : page;
		name = name == null ? "" :name;
		PageDTO<UserRoleDTO> pageRS = null;
		pageRS	=	userRoleService.searchByRole(name, page, size);
		ResponseDTO<PageDTO<UserRoleDTO>> rs = new ResponseDTO<PageDTO<UserRoleDTO>>();
		rs.setStatus(200);
		rs.setData(pageRS);
		return rs;
}
}
