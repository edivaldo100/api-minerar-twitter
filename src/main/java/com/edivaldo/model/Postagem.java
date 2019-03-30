package com.edivaldo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
public class Postagem implements Serializable {
	
	private static final long serialVersionUID = -1995008769486167671L;

	private int totalPostagens;
	
	private PostaguePorDia postaguePorDia;
}
