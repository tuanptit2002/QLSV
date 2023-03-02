package Tuhoc.example.demo.Controller;

import java.io.File;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Tuhoc.example.demo.DTO.PageDTO;
import Tuhoc.example.demo.DTO.ResponseDTO;
import Tuhoc.example.demo.DTO.UserDTO;
import Tuhoc.example.demo.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/new")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseDTO<UserDTO> create(@ModelAttribute UserDTO userDTO) throws IllegalStateException ,IOException{
		if(userDTO.getFile() != null && userDTO.getFile().isEmpty()) {
			final String UPLOAD_FOLDER = "D:/file/user";
			String filename = userDTO.getFile().getOriginalFilename();
//			String extension = filename.substring(filename.lastIndexOf("."));
//			String newFilenameString = UUID.randomUUID().toString() + extension;
			File newFile = new File(UPLOAD_FOLDER + filename);
			userDTO.getFile().transferTo(newFile);
			userDTO.setAvatar(filename);
		}
		userService.create(userDTO);
		ResponseDTO<UserDTO> response = new ResponseDTO<>();
		response.setStatus(200);
		response.setData(userDTO);
		return null;
	}
	@PostMapping("/update")
	public ResponseDTO<Void> update(@ModelAttribute UserDTO userDTO)throws IllegalStateException,IOException{
		if(!userDTO.getFile().isEmpty()) {
			final String UPLOAD_FOLDER = "D:/file/"; // save file to D
			//Read file
			String fileName = userDTO.getFile().getOriginalFilename();
			//Save file
			File newFile = new File(UPLOAD_FOLDER + fileName);
			
			userDTO.getFile().transferTo(newFile);
			//Save to Database
			userDTO.setAvatar(fileName);
		}
		userService.update(userDTO);
		ResponseDTO<Void> response = new ResponseDTO<>();
		response.setStatus(200);
		return response;
	}
	@PostMapping("/update/password")
	public ResponseDTO<Void> updatePassword(@ModelAttribute UserDTO userDTO )throws IllegalStateException, IOException{
		userService.update(userDTO);
		ResponseDTO<Void> response = new ResponseDTO<>();
		response.setStatus(200);
		return response;
	}
	@DeleteMapping("/delete")
	public ResponseDTO<Void> delete(@RequestParam("id")int id){
		userService.delete(id);
		ResponseDTO<Void> response = new ResponseDTO<>();
		response.setStatus(200);
		return response;
	}

	@GetMapping("/get/{id}")
	public ResponseDTO<UserDTO> get(@PathVariable("id")int id){
		UserDTO userDTO = userService.getById(id);
		ResponseDTO<UserDTO> response = new ResponseDTO<>();
		response.setStatus(200);
		response.setData(userDTO);
		return response;
	}
	
	@GetMapping("/search")
	public ResponseDTO<PageDTO<UserDTO>>search(
			@RequestParam(name="name",required = false)String name,
			@RequestParam(name="page",required = false)Integer page,
			@RequestParam(name="size",required = false)Integer size
			){
		size = size == null ? 10 : size;
		page = page == null ? 0 : page;
		name = name == null ? "" :name;
		PageDTO<UserDTO> pageRS = null;
		pageRS	=	userService.searchByname(name, page, size);
		ResponseDTO<PageDTO<UserDTO>> rs = new ResponseDTO<PageDTO<UserDTO>>();
		rs.setStatus(200);
		rs.setData(pageRS);
		return rs;
}
}