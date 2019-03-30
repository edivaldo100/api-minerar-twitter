package com.edivaldo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edivaldo.model.HashTagEntity;
import com.edivaldo.model.Postagem;
import com.edivaldo.model.PostaguePorDia;
import com.edivaldo.model.TweetsEntity;
import com.edivaldo.model.UserEntity;
import com.edivaldo.service.TweetsServicesImp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

	@Autowired
	private TweetsServicesImp tweetsServicesImp;

	@Test
	public void hashTagPorIdimoPaisTest() {
		try {

			Iterable<TweetsEntity> tweetsIterable = tweetsServicesImp.findAllByData();

			Iterable<HashTagEntity> findAllHashTag = tweetsServicesImp.findAllHashTag();

			minerarDados(findAllHashTag, tweetsIterable);

			System.out.println("FIM---------------------->");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void minerarDados(Iterable<HashTagEntity> findAllHashTag, Iterable<TweetsEntity> tweetsIterable) {
		try {
			int cont = 0;
			for (HashTagEntity hashIterable : findAllHashTag) {
				List<TweetsEntity> pegaTotalByHashTag = pegaTotalByHashTag(hashIterable.getTexto(), tweetsIterable);
				System.out.println(
						"=============================" + hashIterable.getTexto() + "============================");
				tratarDados(pegaTotalByHashTag);
				// HashMap<Integer, List<String>> newmap = tratarDados(mapIdiomaAndCount);

				/*
				 * System.out.println("TEMOS UM TOTAL PARA "+hashIterable.getTexto());
				 * 
				 * for (Map.Entry<Integer, List<String>> entrada : newmap.entrySet()) {
				 * 
				 * Integer key = entrada.getKey(); List<String> value = entrada.getValue();
				 * 
				 * //System.out.println(key+ " com idioma:  "+value.);
				 * 
				 * }
				 */

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * public void minerarDados(Iterable<HashTagEntity> findAllHashTag,
	 * Iterable<TweetsEntity> tweetsIterable) { try { int cont = 0; for
	 * (HashTagEntity hashIterable : findAllHashTag) { HashMap<Integer,
	 * List<String>> mapIdiomaAndCount = pegaTotal(hashIterable.getTexto(),
	 * tweetsIterable); HashMap<Integer, List<String>> newmap =
	 * tratarDados(mapIdiomaAndCount);
	 * 
	 * System.out.println("TEMOS UM TOTAL PARA "+hashIterable.getTexto());
	 * 
	 * for (Map.Entry<Integer, List<String>> entrada : newmap.entrySet()) {
	 * 
	 * Integer key = entrada.getKey(); List<String> value = entrada.getValue();
	 * 
	 * //System.out.println(key+ " com idioma:  "+value.);
	 * 
	 * } System.out.println(
	 * "========================================================="); } } catch
	 * (Exception e) { e.printStackTrace(); } }
	 * 
	 * public HashMap<Integer, List<String>> tratarDados(HashMap<Integer,
	 * List<String>> map) { //int cont = 0; //List<String> listaIdiomas = new
	 * List<String>(); HashMap<Integer, List<String>> newMap = new HashMap<>(); try
	 * { for (Map.Entry<Integer, List<String>> entrada : map.entrySet()) {
	 * 
	 * Integer key = entrada.getKey(); List<String> value = entrada.getValue();
	 * List<String> removeDuplicados = removeDuplicados(value); newMap.put(key,
	 * removeDuplicados); } } catch (Exception e) { e.printStackTrace(); } return
	 * newMap; }
	 *
	 */

	public HashMap<String, Integer> tratarDados(List<TweetsEntity> lista) {
		// int cont = 0;
		List<String> listaIdiomasDestaHashTag = new ArrayList<String>();
		HashMap<String, Integer> newMap = new HashMap<>();
		try {
			for (TweetsEntity tweet : lista) {
				if (tweet.getUser().getIdioma() != null && !tweet.getUser().getIdioma().isEmpty()) {
					listaIdiomasDestaHashTag.add(new String(tweet.getUser().getIdioma()));
				}
			}

			List<String> removeDuplicados = removeDuplicados(listaIdiomasDestaHashTag);

			for (String stringIdioma : removeDuplicados) {
				int contador = 0;
				for (TweetsEntity tweet : lista) {
					String idioma = new String(tweet.getUser().getIdioma());
					if (stringIdioma.equals(idioma)) {
						contador++;
					}
				}
				newMap.put(stringIdioma, contador);
				System.out.println("O total de tweets para o Idioma: " + stringIdioma + " é " + contador);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newMap;
	}

	public List<String> removeDuplicados(List<String> lista) {
		try {
			return lista.stream().distinct().collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TweetsEntity> pegaTotalByHashTag(String hash, Iterable<TweetsEntity> tweetsIterable) {

		List<TweetsEntity> newList = new ArrayList<TweetsEntity>();
		try {
			for (TweetsEntity tweetsEntity : tweetsIterable) {
				if (hash.equals(tweetsEntity.getHashTag().getTexto())) {
					newList.add(tweetsEntity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newList;
	}
	/*
	 * public HashMap<Integer, List<String>> pegaTotal(String hash,
	 * Iterable<TweetsEntity> tweetsIterable) { int cont = 0; List<String>
	 * listaIdiomas = new ArrayList<String>(); HashMap<Integer, List<String>>
	 * mapIdiomaAndCount = new HashMap<>(); try { for (TweetsEntity hashIterable :
	 * tweetsIterable) { if(hash.equals(hashIterable.getHashTag().getTexto())) {
	 * listaIdiomas.add(new String(hashIterable.getUser().getIdioma())); cont ++; }
	 * mapIdiomaAndCount.put(cont, listaIdiomas); } } catch (Exception e) {
	 * e.printStackTrace(); } return mapIdiomaAndCount; }
	 */

	@Test
	public void getUsers() {
		System.out.println(" ");
		System.out.println(" ");
		try {
			List<UserEntity> usersList = tweetsServicesImp.getUsersBySeguidoresLimit5();
			int cont = 0;
			for (UserEntity user : usersList) {
				Long seguidores = user.getSeguidores();
				Long id = user.getId();
				String name = new String(user.getName());

				System.out.println(id + " - " + name + " - seguidores: " + seguidores);

				cont++;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		System.out.println(" ");
		System.out.println(" ");
	}
	
	
	@Test
	public void hashTagPorDataHora() {
		try {

			 List<Postagem> tweets = tweetsServicesImp.getTweets();
			
			for (Postagem postagem : tweets) {
				
				int totalPostagens = postagem.getTotalPostagens();
				
				PostaguePorDia postaguePorDia = postagem.getPostaguePorDia();
				List<String> dataHora = postaguePorDia.getDataHora();
				String dia = postaguePorDia.getDia();
				System.out.println("==================================Total : "+totalPostagens);
				System.out.println("===do dia : "+dia);
				for (String dataHoraList : dataHora) {
					System.out.println("  "+dataHoraList);
				}
				
			}
			//Iterable<HashTagEntity> findAllHashTag = tweetsServicesImp.findAllHashTag();

			//minerarDados(findAllHashTag, tweetsIterable);

			System.out.println("FIM---------------------->");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void getTweets() {

		try {
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("APP - TESTE INIT");
			// Iterable<TweetsEntity> tweetsList = tweetsRepository.getTweets();
			Iterable<TweetsEntity> tweetsList = tweetsServicesImp.findAllByData();
			int cont = 0;
			for (TweetsEntity teets : tweetsList) {
				Date dataCriacao = teets.getDataCriacao();
				String dataCriacaoString = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(dataCriacao);
				//System.out.println(new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(dataCriacao));

				// LocalDateTime convertToLocalDateViaInstant =
				// convertToLocalDateViaInstant(dataCriacao);
				//byte[] hashTagText = teets.getHashTagText();
				//String texto = new String(hashTagText);

				//texto = texto.substring(0, 20);// limitando texto ha 20 caracteres
				/// System.out.println(cont+" - "+dataCriacaoString+" id: "+teets.getId()+"
				/// "+texto);

				// dataCriacao.getHours();
				// dataCriacao.getMinutes();
				cont++;
			}
			System.out.println("TOTAL DE Tweets É " + cont);
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");

			System.out.println("------------SEPARAR------------");
			separarPorDataHora(tweetsList);

			// Iterable<UserEntity> usersList = tweetsRepository.getUsers();

			/*
			 * int cont2 = 0; for(UserEntity userEntity: usersList) {
			 * System.out.println(cont2+" - id: "+userEntity.getId()+" seguidores: "
			 * +userEntity.getSeguidores()+" "+new String(userEntity.getName())); cont2++; }
			 * System.out.println("TOTAL DE USER É "+cont2);
			 */
		} catch (Exception e) {

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
		/// Collections.sort(tweetsList);
		int novo = 0;
		for (TweetsEntity tweet : tweetsList) {
			Date dataCriacao = tweet.getDataCriacao();
			int day = dataCriacao.getDate();

			if (day == ultimoDay) {
				print(tweet);
				// System.out.println("add.. array temp");
				// arrayListTemp.add(tweet);
			} else {
				Date dataCriacao2 = tweet.getDataCriacao();
				String dataCriacaoString2 = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(dataCriacao2);
				System.out.println("==AMOSTRA DO DIA ===: " + day);
				print(tweet);
				/*
				 * if(ultimoDay == 0) { arrayListTemp.add(tweet); }else {
				 * System.out.println("ADD. no listade lista");
				 * 
				 * listaDeLista.add(arrayListTemp); System.out.println("limpa array temp");
				 * arrayListTemp.clear(); System.out.println("add.. array temp");
				 * arrayListTemp.add(tweet); }
				 */
				ultimoDay = day;
			}
		}
		System.out.println("===================== FINAL=======================");

		/*
		 * System.out.println("=====================PRINT FINAL======================="
		 * ); listaDeLista.forEach(listaDaLista ->{ //ArrayList<TweetsEntity>
		 * System.out.println("========LISTA");
		 * 
		 * listaDaLista.forEach(tweetTemp ->{ Collections.sort(listaTemp, new
		 * Comparator<SeuObjeto>() { public int compare(SeuObjeto o1, SeuObjeto o2) { if
		 * (o1.getDate() == null || o2.getDate() == null) return 0; return
		 * o1.getDate().compareTo(o2.getDate()); } });
		 * 
		 * 
		 * print(tweetTemp); });
		 * 
		 * 
		 * });
		 */

	}

	public void print(TweetsEntity tweet) {
		Date dataCriacao = tweet.getDataCriacao();
		String dataCriacaoString = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(dataCriacao);
		byte[] hashTagText = tweet.getHashTagText();
		//String texto = new String(hashTagText);

		//texto = texto.substring(0, 20);// limitando texto ha 20 caracteres
		System.out.println(" - " + dataCriacaoString + " id: " + tweet.getId() + " " );
	}

}
