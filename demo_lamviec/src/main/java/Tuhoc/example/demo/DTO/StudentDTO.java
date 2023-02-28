package Tuhoc.example.demo.DTO;

import java.util.List;




public class StudentDTO {

	private Integer id;
	private String studentCode;

	private UserDTO user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	private List<CourseDTO> courseDTO;
	public List<CourseDTO> getCourseDTO() {
		return courseDTO;
	}
	public void setCourseDTO(List<CourseDTO> courseDTO) {
		this.courseDTO = courseDTO;
	}
}
