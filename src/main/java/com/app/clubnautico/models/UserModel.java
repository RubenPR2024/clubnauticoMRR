package com.app.clubnautico.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "users")
public class UserModel {
	//Entidad JPA que representa la tabla Usuario en la base de datos que contiene los atributos y relaciones de la entidad Usuario
	
	
	//Atributos Tabla Usuario
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column
	private String dni;
	@Column
	private String nombre;
	@Column
	private String numsocio;
	@Column
	private String numpatron;
	@Column
	private String apellido1;
	@Column
	private String apellido2;
	@Column
	private String telefono;
	@Column
	private String direccion;
	@Column
	private String email;
	
	//Relacion 1:N con tabla barco (para identificar el dueño del barco según num_socio)
		@OneToMany(mappedBy = "usuario", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
//		@Pattern(regexep = "[a-Z]{3}[0-9]{3}")
		private List<BarcoModel> barco;
		
		//Relacion 1:N con tabla salida (para identificar el patrón necesario para sacar el barco)
		@OneToMany(mappedBy = "usuario", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
//		@Pattern(regexep = "[a-Z]{3}[0-9]{4}")
		private List<SalidaModel> salida;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDni() {
			return dni;
		}

		public void setDni(String dni) {
			this.dni = dni;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getNumsocio() {
			return numsocio;
		}

		public void setNum_socio(String numsocio) {
			this.numsocio = numsocio;
		}

		public String getNumpatron() {
			return numpatron;
		}

		public void setNumpatron(String numpatron) {
			this.numpatron = numpatron;
		}

		public String getApellido1() {
			return apellido1;
		}

		public void setApellido1(String apellido1) {
			this.apellido1 = apellido1;
		}

		public String getApellido2() {
			return apellido2;
		}

		public void setApellido2(String apellido2) {
			this.apellido2 = apellido2;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<BarcoModel> getBarco() {
			return barco;
		}

		public void setBarco(List<BarcoModel> barco) {
			this.barco = barco;
		}

		public List<SalidaModel> getSalida() {
			return salida;
		}

		public void setSalida(List<SalidaModel> salida) {
			this.salida = salida;
		}
		
		
}
