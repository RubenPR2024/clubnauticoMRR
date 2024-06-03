package com.app.clubnautico.models;

import javax.print.attribute.standard.DateTimeAtCompleted;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "salida")
public class SalidaModel {
	//Entidad JPA que representa la tabla Salida en la base de datos que contiene los atributos y relaciones de la entidad Salida
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String destino;
	@Column
	private DateTimeAtCompleted fechaEntrada;
	@Column
	private DateTimeAtCompleted fechaSalida;

	
	@ManyToOne
	private BarcoModel barco;



	@ManyToOne
	private UserModel usuario;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}



	public DateTimeAtCompleted getFechaEntrada() {
		return fechaEntrada;
	}



	public void setFechaEntrada(DateTimeAtCompleted fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}



	public DateTimeAtCompleted getFechaSalida() {
		return fechaSalida;
	}



	public void setFechaSalida(DateTimeAtCompleted fechaSalida) {
		this.fechaSalida = fechaSalida;
	}



	public BarcoModel getBarco() {
		return barco;
	}



	public void setBarco(BarcoModel barco) {
		this.barco = barco;
	}



	public UserModel getUsuario() {
		return usuario;
	}



	public void setUsuario(UserModel usuario) {
		this.usuario = usuario;
	}
	
	
}
