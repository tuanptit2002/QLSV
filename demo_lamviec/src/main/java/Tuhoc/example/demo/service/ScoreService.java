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
import Tuhoc.example.demo.DTO.ScoreDTO;
import Tuhoc.example.demo.entity.Course;
import Tuhoc.example.demo.entity.Score;
import Tuhoc.example.demo.entity.Student;
import Tuhoc.example.demo.repo.CourseRepo;
import Tuhoc.example.demo.repo.ScoreRepo;
import Tuhoc.example.demo.repo.StudentRepo;

@Service
public class ScoreService {
	@Autowired
	ScoreRepo scoreRepo;
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	CourseRepo courseRepo;
	
	@Transient
	public void create(ScoreDTO scoreDTO) {
		Score score = new Score();
		score.setScore(scoreDTO.getScore());
		
		Student student = studentRepo.findById(scoreDTO.getId()).orElseThrow(NoResultException::new);
		Course course = courseRepo.findById(scoreDTO.getId()).orElseThrow(NoResultException::new);
		score.setStudent(student);
		score.setCourse(course);
		scoreRepo.save(score);
	}
	@Transient
	public void update(ScoreDTO scoreDTO) {
		Score score = scoreRepo.findById(scoreDTO.getId()).orElseThrow(NoResultException::new);
		score.setScore(scoreDTO.getScore());
		Student student = studentRepo.findById(scoreDTO.getId()).orElseThrow(NoResultException::new);
		Course course = courseRepo.findById(scoreDTO.getId()).orElseThrow(NoResultException::new);
		score.setCourse(course);
		score.setStudent(student);
		scoreRepo.save(score);
	}
	@Transient
	public void delete(int id) {
		scoreRepo.deleteById(id);
	}
	
	public PageDTO<ScoreDTO>searchByScore(double x,int page ,int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Score> pageScore = scoreRepo.searchByScore(x, pageable);
		PageDTO<ScoreDTO> pageDTO = new PageDTO<>();
		pageDTO.setTotalElements(pageScore.getTotalElements());
		pageDTO.setTotalPages(pageScore.getTotalPages());
		List<ScoreDTO> list = new ArrayList<>();
		for(Score score : pageScore.getContent()) {
			list.add(new ModelMapper().map(score, ScoreDTO.class));
		}
		pageDTO.setContents(list);
		return pageDTO;
	}
}
