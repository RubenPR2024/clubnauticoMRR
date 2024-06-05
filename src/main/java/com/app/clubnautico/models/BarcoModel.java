package com.app.clubnautico.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "Barco")
public class BarcoModel {
	//Entidad JPA que representa la tabla Barco en la base de datos que contiene los atributos y relaciones de la entidad Barco
	
	
	//Atributos Tabla Barco
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBarco;
	@Column
	private String matricula;
	@Column
	private String nombre;
	@Column
	private Long nAmarre;
	@Column
	private double cuota;
	
	@ManyToOne
	@JoinColumn(name = "propietarioId")
	private UserModel usuario;


	// Relación n:1 con tabla salida
	@OneToMany(mappedBy = "barco", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
	private List<SalidaModel> salida;


	public Integer getIdBarco() {
		return idBarco;
	}


	public void setIdBarco(Integer idBarco) {
		this.idBarco = idBarco;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Long getnAmarre() {
		return nAmarre;
	}


	public void setnAmarre(Long nAmarre) {
		this.nAmarre = nAmarre;
	}


	public double getCuota() {
		return cuota;
	}


	public void setCuota(double cuota) {
		this.cuota = cuota;
	}


	public UserModel getUsuario() {
		return usuario;
	}


	public void setUsuario(UserModel usuario) {
		this.usuario = usuario;
	}


	public List<SalidaModel> getSalida() {
		return salida;
	}


	public void setSalida(List<SalidaModel> salida) {
		this.salida = salida;
	}
	
	


}
