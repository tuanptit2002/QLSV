package Tuhoc.example.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import Tuhoc.example.demo.DTO.PageDTO;
import Tuhoc.example.demo.DTO.ScoreDTO;
import Tuhoc.example.demo.service.ScoreService;

@RestController
@RequestMapping("/score")
public class ScoreController {
	@Autowired
	ScoreService scoreService;
	
	@PostMapping("/score")
	public void create(@ModelAttribute ScoreDTO scoreDTO)throws IllegalStateException,IOException {
		scoreService.create(scoreDTO);
	}
	@PostMapping("/update")
	public void update(@ModelAttribute ScoreDTO scoreDTO)throws IllegalStateException, IOException {
		scoreService.update(scoreDTO);
	}
	@DeleteMapping("/delete")
	public void delete(@PathVariable("id")int id) {
		scoreService.delete(id);
	}
	@GetMapping("/search")
	public PageDTO<ScoreDTO> search(@RequestBody double x ,Integer page,Integer size
			){
		
		PageDTO<ScoreDTO> pg = null;
		pg = scoreService.searchByScore(x, page, size);
		return pg;
	}
}
