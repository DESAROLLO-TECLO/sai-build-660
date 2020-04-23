package mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito;

import java.io.Serializable;

public class OficialIngresoVO implements Serializable{

	private static final long serialVersionUID = 3555308119480100363L;
	
	private int emp_id;
	private String emp_placa;
	private String nombre_comp;
	private String emp_nombre;
	private String emp_ape_paterno;
	private String emp_ape_materno;
	private int agrp_id;
	private String agrp_nombre;
	private int sec_id;
	private String sec_nombre;
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_placa() {
		return emp_placa;
	}
	public void setEmp_placa(String emp_placa) {
		this.emp_placa = emp_placa;
	}

	
	
	public String getNombre_comp() {
		return nombre_comp;
	}
	public void setNombre_comp(String nombre_comp) {
		this.nombre_comp = nombre_comp;
	}
	public String getEmp_nombre() {
		return emp_nombre;
	}
	public void setEmp_nombre(String emp_nombre) {
		this.emp_nombre = emp_nombre;
	}
	public String getEmp_ape_paterno() {
		return emp_ape_paterno;
	}
	public void setEmp_ape_paterno(String emp_ape_paterno) {
		this.emp_ape_paterno = emp_ape_paterno;
	}
	public String getEmp_ape_materno() {
		return emp_ape_materno;
	}
	public void setEmp_ape_materno(String emp_ape_materno) {
		this.emp_ape_materno = emp_ape_materno;
	}
	public int getAgrp_id() {
		return agrp_id;
	}
	public void setAgrp_id(int agrp_id) {
		this.agrp_id = agrp_id;
	}
	public String getAgrp_nombre() {
		return agrp_nombre;
	}
	public void setAgrp_nombre(String agrp_nombre) {
		this.agrp_nombre = agrp_nombre;
	}
	public int getSec_id() {
		return sec_id;
	}
	public void setSec_id(int sec_id) {
		this.sec_id = sec_id;
	}
	public String getSec_nombre() {
		return sec_nombre;
	}
	public void setSec_nombre(String sec_nombre) {
		this.sec_nombre = sec_nombre;
	}
	
}
