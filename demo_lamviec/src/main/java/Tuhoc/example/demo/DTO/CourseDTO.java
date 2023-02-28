package Tuhoc.example.demo.DTO;

import java.util.List;





public class CourseDTO {
	
	private Integer id;
	private String name;
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
	
	private List<StudentDTO> studentDTO;
	public List<StudentDTO> getStudentDTO() {
		return studentDTO;
	}
	public void setStudentDTO(List<StudentDTO> studentDTO) {
		this.studentDTO = studentDTO;
	}
	
	
}
