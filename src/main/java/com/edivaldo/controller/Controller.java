package com.edivaldo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edivaldo.model.HashTagOdt;
import com.edivaldo.model.Postagem;
import com.edivaldo.model.ResponseDefault;
import com.edivaldo.model.UserEntity;
import com.edivaldo.service.TweetsServicesImp;

@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	private TweetsServicesImp TweetsServicesImp;
	
	@CrossOrigin
	@GetMapping("/userComMaiSeguidores")
	public ResponseEntity<ResponseDefault> userComMaiSeguidores() {
		List<UserEntity> usersBySeguidoresLimit5 = TweetsServicesImp.getUsersBySeguidoresLimit5();
		
		ResponseDefault idiomaResponse = new ResponseDefault();
		idiomaResponse.setApiVersion("1.0");
		idiomaResponse.setObj(usersBySeguidoresLimit5);
		return idiomaResponse != null ? ResponseEntity.ok(idiomaResponse) : ResponseEntity.notFound().build();
	}

	@CrossOrigin
	@GetMapping("/postagensPorHora")
	public ResponseEntity<ResponseDefault> postagensPorHora() {
		List<Postagem> listaPostagens = TweetsServicesImp.getTweets();
		ResponseDefault idiomaResponse = new ResponseDefault();
		idiomaResponse.setApiVersion("1.0");
		idiomaResponse.setObj(listaPostagens);
		return idiomaResponse != null ? ResponseEntity.ok(idiomaResponse) : ResponseEntity.notFound().build();
	}
	

	
	@CrossOrigin
	@GetMapping("/hashTagPorIdioma")
	public ResponseEntity<ResponseDefault> hashTagPorIdioma() {
		List<HashTagOdt> hashTagPorIdimoPais = TweetsServicesImp.hashTagPorIdimoPais();

		ResponseDefault idiomaResponse = new ResponseDefault();
		idiomaResponse.setApiVersion("1.0");
		idiomaResponse.setObj(hashTagPorIdimoPais);
		return idiomaResponse != null ? ResponseEntity.ok(idiomaResponse) : ResponseEntity.notFound().build();
	}

}