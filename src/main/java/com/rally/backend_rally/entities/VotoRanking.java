package com.rally.backend_rally.entities;

import java.time.LocalDate;

public class VotoRanking  {
	private LocalDate fecha;
    private int votos;
	
	// Constructor
    public VotoRanking(LocalDate fecha, int votos) {
		super();
		this.fecha = fecha;
        this.votos = votos;
	}

	// Getter y setter
    public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}
}