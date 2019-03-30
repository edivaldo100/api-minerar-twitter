package com.edivaldo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "tweets")
@Data
public class TweetsEntity implements Serializable {
	
	private static final long serialVersionUID = -1995008769486167671L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	private byte[] hashTagText;
	
	@OneToOne(cascade=CascadeType.ALL)
	private HashTagEntity hashTag;

	
	@Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
	
	@OneToOne(cascade=CascadeType.ALL)
	private UserEntity user;
	
}
