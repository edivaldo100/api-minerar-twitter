package com.edivaldo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edivaldo.model.TweetsEntity;
import com.edivaldo.model.UserEntity;
import com.edivaldo.repository.HashTagRepository;
import com.edivaldo.repository.TweetsRepository;
import com.edivaldo.repository.UserRepository;



@Transactional
@Repository
public class TweetsServicesImp {

	@Autowired
	private TweetsRepository tweetsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HashTagRepository hashTagRepository;
	
	
	public List<TweetsEntity> findAllByData() {
		return tweetsRepository.findAllByData();
	}
	public Iterable<TweetsEntity> getTweets() {
		return tweetsRepository.findAll();
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
	
	public byte[] stringToByte(String string) {
		System.out.println("------------------------stringToByte-------------------------------------");
		System.out.println(string);
		System.out.println("------------------------stringToByte-------------------------------------");
		try {
			if(string == null || string.equals(null)) {
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
