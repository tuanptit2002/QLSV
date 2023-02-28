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

import Tuhoc.example.demo.DTO.PageDTO;
import Tuhoc.example.demo.DTO.UserDTO;
import Tuhoc.example.demo.DTO.UserRoleDTO;
import Tuhoc.example.demo.entity.User;
import Tuhoc.example.demo.entity.UserRole;
import Tuhoc.example.demo.repo.UserRepo;
import Tuhoc.example.demo.repo.UserRoleRepo;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	UserRoleRepo userRoleRepo;
	
	@Transient
	public void create(UserDTO userDTO) {
		User user = new User();
		user.setBirthdate(userDTO.getBirthdate());
		user.setAvatar(userDTO.getAvatar());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		user.setFile(userDTO.getFile());
		userRepo.save(user);
		List<UserRoleDTO> useRoleDTO = userDTO.getUserRole();
		for(UserRoleDTO useRoles : useRoleDTO) {
			if(useRoles.getRole() != null) {
				UserRole userRole = new UserRole();
				userRole.setRole(useRoles.getRole());
				userRole.setUser(user);
				userRoleRepo.save(userRole);
			}
		}
	}
	@Transient
	public void update(UserDTO userDTO) {
		User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
		user.setBirthdate(userDTO.getBirthdate());
		user.setAvatar(userDTO.getAvatar());
		user.setFile(userDTO.getFile());
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getName());
		userRepo.save(user);
	}
	@Transient
	public void updatePassword(UserDTO userDTO) {
		User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
		user.setPassword(userDTO.getPassword());
		userRepo.save(user);
	}
	@Transient
	public void delete(int id) {
		userRepo.deleteById(id);
	}
	public UserDTO getById(int id) {
		User user = userRepo.findById(id).orElseThrow(NoResultException::new);
		UserDTO userDTO = new UserDTO();
		userDTO.setAvatar(user.getAvatar());
		userDTO.setBirthdate(user.getBirthdate());
		userDTO.setFile(user.getFile());
		userDTO.setName(user.getName());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}
	public PageDTO<UserDTO> searchByname(String s,int page,int size){
		Pageable pageable = PageRequest.of(page, size);
		
		Page<User> pageRS = userRepo.searchByName(s, pageable);
		
		PageDTO<UserDTO> pageDTO = new PageDTO<>();
		pageDTO.setTotalElements(pageRS.getTotalElements());
		pageDTO.setTotalPages(pageRS.getTotalPages());
		
		List<UserDTO> userDTO= new ArrayList<>();
		for(User user : pageRS.getContent()) {
			userDTO.add(new ModelMapper().map(user, UserDTO.class));

		}
		pageDTO.setContents(userDTO);
		return pageDTO;
	}
	
}
