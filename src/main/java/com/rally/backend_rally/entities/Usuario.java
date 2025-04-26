package com.rally.backend_rally.entities;

import java.time.LocalDate;

import com.rally.backend_rally.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Etiqueta que nos indica que esto es una entidad JPA
@Table(name = "users") // Objeto que va a mapear una tabla users en la bbdd
public class Usuario {
    @Id // Etiqueta que indica que es una clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera automáticamente 
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String alias;
    
   	@Column(nullable = false)
    private String nombre;
    
   	@Column(nullable = false)
    private String apellidos;
    
    @Column(nullable = false)
    private LocalDate fNacimiento;
    
    @Column(nullable = false)
    private Integer numTelefono;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol; // admin, participante

    // Getters y Setters
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getNombre() {
			return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getEmail() {
		return email;
	}
	
	public LocalDate getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(LocalDate fNacimiento) {
		this.fNacimiento = fNacimiento;
	}
	
	public Integer getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(Integer numTelefono) {
		this.numTelefono = numTelefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
    
}
