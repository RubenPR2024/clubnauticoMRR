package com.app.clubnautico.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	private String fechaEntrada;
	@Column
	private String fechaSalida;

	
	@ManyToOne
	@JoinColumn(name = "barcoId")
	private BarcoModel barco;



	@ManyToOne
	@JoinColumn(name = "numPatron")
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



	public String getFechaEntrada() {
		return fechaEntrada;
	}



	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}



	public String getFechaSalida() {
		return fechaSalida;
	}



	public void setFechaSalida(String fechaSalida) {
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
