package com.edivaldo;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edivaldo.model.TweetsEntity;
import com.edivaldo.model.UserEntity;
import com.edivaldo.service.TweetsServicesImp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {
	
	@Autowired
	private TweetsServicesImp tweetsServicesImp;
	
	
	@Test
	public void getUsers() {
		List<UserEntity> usersList = tweetsServicesImp.getUsersBySeguidoresLimit5();
		int cont = 0;
		for(UserEntity user: usersList) {
			Long seguidores = user.getSeguidores();
			Long id = user.getId();
			String name = new String(user.getName());
			
			System.out.println(id+" "+name+" "+seguidores);
			
			cont ++;
		}
		
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void getTweets() {
		
		try {
		System.out.println(" ");System.out.println(" ");System.out.println(" ");
		System.out.println("APP - TESTE INIT");
		//Iterable<TweetsEntity> tweetsList = tweetsRepository.getTweets();
		Iterable<TweetsEntity> tweetsList = tweetsServicesImp.findAllByData();
		int cont = 0;
		for(TweetsEntity teets: tweetsList) {
			Date dataCriacao = teets.getDataCriacao();
			String dataCriacaoString = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(dataCriacao);
			System.out.println(new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(dataCriacao));  

			 //LocalDateTime convertToLocalDateViaInstant = convertToLocalDateViaInstant(dataCriacao);
			 byte[] hashTagText = teets.getHashTagText();
			 String texto = new String(hashTagText);
			 
			texto = texto.substring(0, 20);//limitando texto ha 20 caracteres
			///System.out.println(cont+" - "+dataCriacaoString+" id: "+teets.getId()+" "+texto);
			
			//dataCriacao.getHours();
			//dataCriacao.getMinutes();
			cont++;
		}
		System.out.println("TOTAL DE Tweets É "+cont);
		System.out.println(" ");System.out.println(" ");System.out.println(" ");
		
		System.out.println("------------SEPARAR------------");
		separarPorDataHora(tweetsList);
		
		
		
		//Iterable<UserEntity> usersList = tweetsRepository.getUsers();
		
/*		int cont2 = 0;
		for(UserEntity userEntity: usersList) {
			System.out.println(cont2+" - id: "+userEntity.getId()+" seguidores: "+userEntity.getSeguidores()+" "+new String(userEntity.getName()));
			cont2++;
		}
		System.out.println("TOTAL DE USER É "+cont2);*/
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	@SuppressWarnings("deprecation")
	public void separarPorDataHora(Iterable<TweetsEntity> tweetsList) {
		int cont = 0;
		Date ultimaData = new Date();
		int ultimoDay = 0;
		
		ArrayList<ArrayList<TweetsEntity>> listaDeLista = new ArrayList<ArrayList<TweetsEntity>>();
		ArrayList<TweetsEntity> arrayListTemp = new ArrayList<TweetsEntity>();
		///Collections.sort(tweetsList);
		int novo = 0;
		for(TweetsEntity tweet: tweetsList) {
			Date dataCriacao = tweet.getDataCriacao();
			int day = dataCriacao.getDate();
			
			if(day == ultimoDay) {
				print(tweet);
				//System.out.println("add.. array temp");
				//arrayListTemp.add(tweet);
			}else {
				Date dataCriacao2 = tweet.getDataCriacao();
				String dataCriacaoString2 = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(dataCriacao2);
				System.out.println("==AMOSTRA DO DIA ===: "+day);
				print(tweet);
				/*if(ultimoDay == 0) {
					arrayListTemp.add(tweet);
				}else {
					System.out.println("ADD. no listade lista");
					
					listaDeLista.add(arrayListTemp);
					System.out.println("limpa array temp");
					arrayListTemp.clear();
					System.out.println("add.. array temp");
					arrayListTemp.add(tweet);
				}*/
				ultimoDay = day;
			}
		}
		System.out.println("===================== FINAL=======================");
		
		/*System.out.println("=====================PRINT FINAL=======================");
		listaDeLista.forEach(listaDaLista ->{
			//ArrayList<TweetsEntity>
			System.out.println("========LISTA");
		
			listaDaLista.forEach(tweetTemp ->{
				Collections.sort(listaTemp, new Comparator<SeuObjeto>() {
					  public int compare(SeuObjeto o1, SeuObjeto o2) {
					      if (o1.getDate() == null || o2.getDate() == null)
					        return 0;
					      return o1.getDate().compareTo(o2.getDate());
					  }
					});
				
				
				print(tweetTemp);
			});
			
			
		});*/
		
		
	}
	
	public void print(TweetsEntity tweet) {
		Date dataCriacao = tweet.getDataCriacao();
		String dataCriacaoString = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(dataCriacao);
		 byte[] hashTagText = tweet.getHashTagText();
		 String texto = new String(hashTagText);
		 
		texto = texto.substring(0, 20);//limitando texto ha 20 caracteres
		System.out.println(" - "+dataCriacaoString+" id: "+tweet.getId()+" "+texto);
	}

}
