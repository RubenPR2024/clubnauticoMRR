package com.app.clubnautico.dto;

import java.util.List;

public class UsuarioDTOconListas {
	private Long id;
    private String dni;
    private String nombre;
    private Long numSocio;
    private Long numPatron;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String direccion;
    private String email;
	private List<BarcoDTO> barcos;
    private List<SalidaDTO> salidas;
    
    
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

	public Long getNumSocio() {
		return numSocio;
	}
	public void setNumSocio(Long numSocio) {
		this.numSocio = numSocio;
	}
	public Long getNumPatron() {
		return numPatron;
	}
	public void setNumPatron(Long numPatron) {
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
    
    public List<BarcoDTO> getBarcos() {
		return barcos;
	}
	public void setBarcos(List<BarcoDTO> barcos) {
		this.barcos = barcos;
	}
	public List<SalidaDTO> getSalidas() {
		return salidas;
	}
	public void setSalidas(List<SalidaDTO> salidas) {
		this.salidas = salidas;
	}
}
