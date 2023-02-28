package Tuhoc.example.demo.DTO;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class UserDTO {
	
	private Integer id;
	private String avatar;
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public List<UserRoleDTO> getUserRoleDTO() {
		return userRoleDTO;
	}
	public void setUserRoleDTO(List<UserRoleDTO> userRoleDTO) {
		this.userRoleDTO = userRoleDTO;
	}

	private String name;
	private String username;
	private String password;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthdate;

	private MultipartFile file;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	private List<UserRoleDTO> userRoleDTO;
	public List<UserRoleDTO> getUserRole() {
		return userRoleDTO;
	}
	public void setUserRole(List<UserRoleDTO> userRoleDTO) {
		this.userRoleDTO = userRoleDTO;
	}
	
	
}
