package com.fjcasasaltas.services.model;

import java.io.Serializable;

public class Contador implements Serializable{
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_CONT = "contador";

	private int id;
	private int contador;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	
		
}
