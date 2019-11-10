package logic.model;

import java.util.Date;

public class Atleta {
	
	public long id;
	public String dni;
	public String nombre;
	public String apellidos;
	public String email;
	public String sexo;
	public Date fechaNacimiento;
	public String genero;
	public String diaNacimiento;
	public String mesNacimiento;
	public String añoNacimiento;

	public String getDni() {
		return dni;
	
	}
	
	
	public long getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellidos;
	}

	public String getSexo() {
		return sexo;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}
}
