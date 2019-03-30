package com.edivaldo.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edivaldo.model.HashTagEntity;
import com.edivaldo.model.HashTagOdt;
import com.edivaldo.model.IdiomaOdt;
import com.edivaldo.model.Postagem;
import com.edivaldo.model.PostaguePorDia;
import com.edivaldo.model.TweetsEntity;
import com.edivaldo.model.UserEntity;
import com.edivaldo.repository.HashTagRepository;
import com.edivaldo.repository.TweetsRepository;
import com.edivaldo.repository.UserRepository;

@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
@Repository
public class TweetsServicesImp {

	@Autowired
	private TweetsRepository tweetsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HashTagRepository hashTagRepository;

	public Iterable<HashTagEntity> findAllHashTag() {
		return hashTagRepository.findAll();
	}

	public List<TweetsEntity> findAllByData() {
		return tweetsRepository.findAllByData();
	}

	public UserEntity getUserByIdTwitter(Long idTwitter) {
		return userRepository.findByIdTwitter(idTwitter);
	}

	public Iterable<UserEntity> getUsers() {
		return userRepository.findAll();
	}

	public List<UserEntity> getUsersBySeguidoresLimit5() {
		return userRepository.getUsersBySeguidoresLimit5();
	}

	public List<HashTagOdt> hashTagPorIdimoPais() {
		try {

			Iterable<TweetsEntity> tweetsIterable = findAllByData();
			
			Iterable<HashTagEntity> findAllHashTag = findAllHashTag();
			List<HashTagOdt> HashTagOdtLista = new ArrayList<HashTagOdt>();
			HashMap<String, HashMap<String, Integer>> minerarDados = minerarDados(findAllHashTag, tweetsIterable);

			for (Map.Entry<String, HashMap<String, Integer>> entrada : minerarDados.entrySet()) {
				String key = entrada.getKey();
				HashMap<String, Integer> value = entrada.getValue();
				HashTagOdt hashTagOdt = new HashTagOdt();
				List<IdiomaOdt> idiomaList = new ArrayList<IdiomaOdt>();
				for (Map.Entry<String, Integer> valueMaps : value.entrySet()) {
					String key2 = valueMaps.getKey();
					Integer value2 = valueMaps.getValue();
					
					IdiomaOdt idiomaOdt = new IdiomaOdt();
					idiomaOdt.setIdioma(key2);
					idiomaOdt.setTotal(value2);
					idiomaList.add(idiomaOdt);
				}
				hashTagOdt.setHashTag(key);
				
				hashTagOdt.setIdioma(idiomaList);
				HashTagOdtLista.add(hashTagOdt);
			}

			System.out.println("FIM---------------------->");
			return HashTagOdtLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public HashMap<String, HashMap<String, Integer>> minerarDados(Iterable<HashTagEntity> findAllHashTag,
			Iterable<TweetsEntity> tweetsIterable) {
		try {
			HashMap<String, HashMap<String, Integer>> hashMap = new HashMap<String, HashMap<String, Integer>>();
			int cont = 0;
			for (HashTagEntity hashIterable : findAllHashTag) {
				List<TweetsEntity> pegaTotalByHashTag = pegaTotalByHashTag(hashIterable.getTexto(), tweetsIterable);
				System.out.println(
						"=============================" + hashIterable.getTexto() + "============================");
				HashMap<String, Integer> tratarDados = tratarDados(pegaTotalByHashTag);
				hashMap.put(hashIterable.getTexto(), tratarDados);
			}
			return hashMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

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

	public List<String> removeDataDuplicadas(Iterable<TweetsEntity> tweetsList) {
		List<String> listaDatas = new ArrayList<String>();
		try {
			for (TweetsEntity tweet : tweetsList) {
				Date dataCriacao = tweet.getDataCriacao();
				String data = new SimpleDateFormat("dd/MM/yyyy").format(dataCriacao);
				listaDatas.add(data);

			}

			return listaDatas.stream().distinct().collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDatas;
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

	public HashMap<String, ArrayList<TweetsEntity>> separarPorDia(Iterable<TweetsEntity> tweetsList) {
		try {
			HashMap<String, ArrayList<TweetsEntity>> listaDeLista = new HashMap<>();

			// ArrayList<String> listaDatas = new ArrayList<String>();
			// int total = 0;

			List<String> listaDeData = removeDataDuplicadas(tweetsList);

			for (String data : listaDeData) {

				ArrayList<TweetsEntity> arrayListTemp = new ArrayList<TweetsEntity>();
				for (TweetsEntity tweet : tweetsList) {

					Date dataCriacao = tweet.getDataCriacao();
					String dataTweet = new SimpleDateFormat("dd/MM/yyyy").format(dataCriacao);
					if (data.equals(dataTweet)) {
						arrayListTemp.add(tweet);
					}
				}
				listaDeLista.put(data, arrayListTemp);
				// arrayListTemp.clear();
			}
			return listaDeLista;
			/*
			 * for (TweetsEntity tweet : tweetsList) { Date dataCriacao =
			 * tweet.getDataCriacao(); int day = dataCriacao.getDate(); int month =
			 * dataCriacao.getMonth(); if (day == ultimoDay) { String dataReturn =
			 * print(tweet); listaDatas.add(dataReturn); } else { // Date dataCriacao2 =
			 * tweet.getDataCriacao(); // String dataCriacaoString2 = new
			 * SimpleDateFormat("hh:mm:ss // dd/MM/yyyy").format(dataCriacao2); //
			 * System.out.println("==AMOSTRA DO DIA ===: " + day); String dataReturn =
			 * print(tweet); postaguePorDia.setDia(day + "/" + month);
			 * listaDatas.add(dataReturn); ultimoDay = day; } total++; }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> returnListStringDatas(ArrayList<TweetsEntity> lista) {
		ArrayList arrayList = new ArrayList<String>();
		for (TweetsEntity listTweet : lista) {

			Date dataCriacao2 = listTweet.getDataCriacao();
			String dataCriacaoString2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(dataCriacao2);
			arrayList.add(dataCriacaoString2);
		}

		return arrayList;

	}

	public int totalLista(Iterable<TweetsEntity> lista) {
		int cont = 0;
		for (TweetsEntity listTweet : lista) {
			cont++;
		}
		return cont;
	}

	@SuppressWarnings("deprecation")
	public List<Postagem> getTweets() {

		try {
			ArrayList<Postagem> arrayListPostagem = new ArrayList<Postagem>();
			Postagem postagem = new Postagem();
			Iterable<TweetsEntity> tweetsList = findAllByData();

			HashMap<String, ArrayList<TweetsEntity>> separarPorDia = separarPorDia(tweetsList);

			for (Map.Entry<String, ArrayList<TweetsEntity>> entrada : separarPorDia.entrySet()) {
				PostaguePorDia postaguePorDia = new PostaguePorDia();
				String key = entrada.getKey();
				ArrayList<TweetsEntity> value = entrada.getValue();

				postaguePorDia.setDia(key);

				postaguePorDia.setDataHora(returnListStringDatas(value));

				postagem.setPostaguePorDia(postaguePorDia);

				arrayListPostagem.add(postagem);
			}

			postagem.setTotalPostagens(totalLista(tweetsList));
			return arrayListPostagem;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
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
			} else {
				Date dataCriacao2 = tweet.getDataCriacao();
				String dataCriacaoString2 = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(dataCriacao2);
				System.out.println("==AMOSTRA DO DIA ===: " + day);
				print(tweet);
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

	public String print(TweetsEntity tweet) {
		Date dataCriacao = tweet.getDataCriacao();
		String dataCriacaoString = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(dataCriacao);
		// byte[] hashTagText = tweet.getHashTagText();
		// String texto = new String(hashTagText);

		// texto = texto.substring(0, 20);// limitando texto ha 20 caracteres
		// System.out.println(" - " + dataCriacaoString + " id: " + tweet.getId() + " "
		// );
		return dataCriacaoString;
	}

	public byte[] stringToByte(String string) {
		System.out.println("------------------------stringToByte-------------------------------------");
		System.out.println(string);
		System.out.println("------------------------stringToByte-------------------------------------");
		try {
			if (string == null || string.equals(null)) {
				string = "vazio";
			}
			byte[] bytes = string.getBytes("UTF-8");
			return bytes;

		} catch (UnsupportedEncodingException e) {
			System.out.print("-------------------------------------------------------------");
			System.out.print("ERRO AO TENTAR CONVERTER A STING ABAIXO PARA ARRAY DE BYTE");
			System.out.print(string);
			System.out.print("-------------------------------------------------------------");
			e.printStackTrace();
		}
		return null;
	}
}
