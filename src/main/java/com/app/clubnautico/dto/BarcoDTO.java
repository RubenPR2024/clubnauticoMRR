package com.app.clubnautico.dto;

import java.util.List;

public class BarcoDTO {
	//Clase utilizada para transferir los datos entre la capa de presentación y la capa de negocio
	//Menores preocupaciones
	//Simplificación de operaciones
	
	private String matricula;
    private String nombre;
    private Long nAmarre;
    private double cuota;
    private Long numSocio;
    private List<SalidaDTO> salida;
    
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
	public Long getNumSocio() {
		return numSocio;
	}
	public void setNumSocio(Long numSocio) {
		this.numSocio = numSocio;
	}
	public List<SalidaDTO> getSalida() {
		return salida;
	}
	public void setSalida(List<SalidaDTO> salida) {
		this.salida = salida;
	}
	
}
