package modelo;

import java.time.LocalDateTime;

public class Empleado {
	
	private int codEmpleado;
	private String nombre;
	private String apellidos;
	private int sueldo;
	private int codDepartamento;
	private int codCargo;
	private Integer codJefe;
	private boolean esJefe;
	private LocalDateTime fechaAlta;
	
	public Empleado() {
		
	}

	public int getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmpleado(int codEmpleado) {
		this.codEmpleado = codEmpleado;
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

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public int getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(int codDepartamento) {
		this.codDepartamento = codDepartamento;
	}

	public int getCodCargo() {
		return codCargo;
	}

	public void setCodCargo(int codCargo) {
		this.codCargo = codCargo;
	}

	public Integer getCodJefe() {
		return codJefe;
	}

	public void setCodJefe(Integer codJefe) {
		this.codJefe = codJefe;
	}

	public Boolean getEsJefe() {
		return esJefe;
	}

	public void setEsJefe(Boolean esJefe) {
		this.esJefe = esJefe;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
}