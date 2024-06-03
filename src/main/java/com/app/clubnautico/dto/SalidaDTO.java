package com.app.clubnautico.dto;

import java.time.LocalDateTime;

public class SalidaDTO {
	//Clase utilizada para transferir los datos entre la capa de presentación y la capa de negocio
	//Menores preocupaciones
	//Simplificación de operaciones
	
	private String destino;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
    
}
