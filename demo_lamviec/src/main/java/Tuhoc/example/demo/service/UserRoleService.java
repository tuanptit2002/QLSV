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
import Tuhoc.example.demo.DTO.UserRoleDTO;
import Tuhoc.example.demo.entity.User;
import Tuhoc.example.demo.entity.UserRole;
import Tuhoc.example.demo.repo.UserRepo;
import Tuhoc.example.demo.repo.UserRoleRepo;

@Service
public class UserRoleService {
	@Autowired
	UserRoleRepo userRoleRepo;
	@Autowired
	UserRepo userRepo;
	@Transient
	public void create(UserRoleDTO userRoleDTO) {
		UserRole userRole = new UserRole();
		userRole.setRole(userRoleDTO.getRole());
		User user = userRepo.findById(userRoleDTO.getUserid()).orElseThrow(NoResultException::new);
			userRole.setUser(user);
			userRoleRepo.save(userRole);
	}
	@Transient
	public void update(UserRoleDTO userRoleDTO) {
		UserRole userRole = userRoleRepo.findById(userRoleDTO.getId()).orElseThrow(NoResultException::new);
		userRole.setRole(userRoleDTO.getRole());
		User user = userRepo.findById(userRoleDTO.getUserid()).orElseThrow(NoResultException::new);
		userRole.setUser(user);
		userRoleRepo.save(userRole);
	}
	@Transient
	public void delete(int id) {
		userRoleRepo.deleteById(id);
	}
	@Transient
	public void deleteByUserId(int userid) {
		userRoleRepo.deleteById(userid);
	}
	public PageDTO<UserRoleDTO> searchByRole(String s,int page ,int size){
		Pageable pageble = PageRequest.of(page, size);
		Page<UserRole> pageUR = userRoleRepo.searchByRole(s, pageble);
		PageDTO<UserRoleDTO> pageURD = new PageDTO<>();
			pageURD.setTotalElements(pageUR.getTotalPages());
			pageURD.setTotalElements(pageUR.getTotalElements());
			List<UserRoleDTO> list = new ArrayList<>();
			for(UserRole userRole  : pageUR.getContent()) {
				list.add(new ModelMapper().map(userRole, UserRoleDTO.class));
			}
			pageURD.setContents(list);
			return pageURD;
	}
}
