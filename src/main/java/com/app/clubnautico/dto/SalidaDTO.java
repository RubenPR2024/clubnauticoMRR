package com.app.clubnautico.dto;

public class SalidaDTO {
	//Clase utilizada para transferir los datos entre la capa de presentación y la capa de negocio
	//Menores preocupaciones
	//Simplificación de operaciones
	
	private String destino;
	private String fechaEntrada;
	private String fechaSalida;
    private Long numPatron;
    
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
	public Long getNumPatron() {
		return numPatron;
	}
	public void setNumPatron(Long numPatron) {
		this.numPatron = numPatron;
	}



    
}
