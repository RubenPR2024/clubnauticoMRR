package com.app.clubnautico.dto;

public class UsuarioDTO {
	//Clase utilizada para transferir los datos entre la capa de presentación y la capa de negocio
	//Menores preocupaciones
	//Simplificación de operaciones
	
    private String dni;
    private String nombre;
    private String numSocio;
    private String numPatron;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String direccion;
    private String email;
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
	public String getNumSocio() {
		return numSocio;
	}
	public void setNumSocio(String numSocio) {
		this.numSocio = numSocio;
	}
	public String getNumPatron() {
		return numPatron;
	}
	public void setNumPatron(String numPatron) {
		this.numPatron = numPatron;
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

   
}
