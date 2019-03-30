package com.edivaldo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = -1995008769486167671L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private Long idTwitter;

	private byte[] name;
	private Long seguidores;
	private String idioma;
	private String pais;
	private String timeZone;

}
