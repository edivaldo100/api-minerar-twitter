package com.edivaldo.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ResponseDefault implements Serializable {

	private static final long serialVersionUID = -1995008769486167671L;

	private String apiVersion;

	private Object obj;

}
