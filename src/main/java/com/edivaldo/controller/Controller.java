package com.edivaldo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.edivaldo.model.HashTagOdt;
import com.edivaldo.model.Postagem;
import com.edivaldo.model.UserEntity;
import com.edivaldo.service.TweetsServicesImp;

@RestController
@RequestMapping("/")
public class Controller {

	@Autowired
	private TweetsServicesImp TweetsServicesImp;

	@RequestMapping(value = "/tweets", method = RequestMethod.GET)
	public String getTweets() {
		// tweetsRepository.getTweets();
		return "OK";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUser() {
		// tweetsRepository.getUser("eu");
		return "OK";
	}


	@GetMapping("/userComMaiSeguidores")
	public ResponseEntity<List<UserEntity>> userComMaiSeguidores() {
		List<UserEntity> usersBySeguidoresLimit5 = TweetsServicesImp.getUsersBySeguidoresLimit5();
		return usersBySeguidoresLimit5 != null ? ResponseEntity.ok(usersBySeguidoresLimit5)
				: ResponseEntity.notFound().build();
	}
	
	@GetMapping("/postagensPorHora")
	public ResponseEntity<List<Postagem>> postagensPorHora() {
		List<Postagem> listaPostagens = TweetsServicesImp.getTweets();
		return listaPostagens != null ? ResponseEntity.ok(listaPostagens)
				: ResponseEntity.notFound().build();
	}
	@GetMapping("/hashTagPorIdioma")
	public ResponseEntity<List<HashTagOdt>> hashTagPorIdioma() {
		List<HashTagOdt> hashTagPorIdimoPais = TweetsServicesImp.hashTagPorIdimoPais();
		return hashTagPorIdimoPais != null ? ResponseEntity.ok(hashTagPorIdimoPais)
				: ResponseEntity.notFound().build();
	}
	
	
	
}