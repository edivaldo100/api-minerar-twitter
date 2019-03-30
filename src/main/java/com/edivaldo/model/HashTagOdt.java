package com.edivaldo.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class HashTagOdt implements Serializable {

	private static final long serialVersionUID = -1995008769486167671L;

	private String hashTag;

	private List<IdiomaOdt> idioma;

}
