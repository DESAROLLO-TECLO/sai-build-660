package mx.com.teclo.saicdmx.persistencia.vo.certificados;

import mx.com.teclo.saicdmx.util.enumerados.CodigoPerfilesEnum;

public class ConsultaUsersVO {

	private String emp_id;
	private String emp_placa;
	private String emp_rfc;
	private String emp_certificado;
	private String emp_nombre;
	private String emp_ape_paterno;
	private String emp_ape_materno;
	private String perfil_web;
	private String perfil_hh;
	private String reg_nombre;
	private String upc_nombre;
	private String estatus;
	private String caja_id;
	private String caja_cod;
	private String autorizada_p_cobro;
	private String tiene_operaciones;
	private String dep_id;
	private String dep_nombre;
	private String derecho_cobro;
	private String perfil_id;
	private String emp_tipo_id;
	private String emp_tipo_nombre;
	private String areaOperativa;
	private String cd_perfil;
	private String cd_aplicacion;

	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_placa() {
		return emp_placa;
	}
	public void setEmp_placa(String emp_placa) {
		this.emp_placa = emp_placa;
	}
	public String getEmp_rfc() {
		return emp_rfc;
	}
	public void setEmp_rfc(String emp_rfc) {
		this.emp_rfc = emp_rfc;
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
	public String getPerfil_web() {
		return perfil_web;
	}
	public void setPerfil_web(String perfil_web) {
		this.perfil_web = perfil_web;
	}
	public String getPerfil_hh() {
		return perfil_hh;
	}
	public void setPerfil_hh(String perfil_hh) {
		this.perfil_hh = perfil_hh;
	}
	public String getReg_nombre() {
		return reg_nombre;
	}
	public void setReg_nombre(String reg_nombre) {
		this.reg_nombre = reg_nombre;
	}
	public String getUpc_nombre() {
		return upc_nombre;
	}
	public void setUpc_nombre(String upc_nombre) {
		this.upc_nombre = upc_nombre;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getCaja_id() {
		return caja_id;
	}
	public void setCaja_id(String caja_id) {
		this.caja_id = caja_id;
	}
	public String getCaja_cod() {
		return caja_cod;
	}
	public void setCaja_cod(String caja_cod) {
		this.caja_cod = caja_cod;
	}
	public String getAutorizada_p_cobro() {
		return autorizada_p_cobro;
	}
	public void setAutorizada_p_cobro(String autorizada_p_cobro) {
		this.autorizada_p_cobro = autorizada_p_cobro;
	}
	public String getTiene_operaciones() {
		return tiene_operaciones;
	}
	public void setTiene_operaciones(String tiene_operaciones) {
		this.tiene_operaciones = tiene_operaciones;
	}
	public String getDep_id() {
		return dep_id;
	}
	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
	}
	public String getDep_nombre() {
		return dep_nombre;
	}
	public void setDep_nombre(String dep_nombre) {
		this.dep_nombre = dep_nombre;
	}
	public String getDerecho_cobro() {
		return derecho_cobro;
	}
	public void setDerecho_cobro(String derecho_cobro) {
		this.derecho_cobro = derecho_cobro;
	}
	public String getPerfil_id() {
		return perfil_id;
	}
	public void setPerfil_id(String perfil_id) {
		this.perfil_id = perfil_id;
	}
	public String getEmp_tipo_id() {
		return emp_tipo_id;
	}
	public void setEmp_tipo_id(String emp_tipo_id) {
		this.emp_tipo_id = emp_tipo_id;
	}
	public String getEmp_tipo_nombre() {
		return emp_tipo_nombre;
	}
	public void setEmp_tipo_nombre(String emp_tipo_nombre) {
		this.emp_tipo_nombre = emp_tipo_nombre;
	}
	public String getAreaOperativa() {
		return areaOperativa;
	}
	public void setAreaOperativa(String areaOperativa) {
		this.areaOperativa = areaOperativa;
	}
	public String getEmp_certificado() {
		return emp_certificado;
	}
	public void setEmp_certificado(String emp_certificado) {
		this.emp_certificado = emp_certificado;
	}
	public String getCd_perfil() {
		return cd_perfil;
	}
	public void setCd_perfil(String cd_perfil) {
		this.cd_perfil = cd_perfil;
	}
	public String getCd_aplicacion() {
		return cd_aplicacion;
	}
	public void setCd_aplicacion(String cd_aplicacion) {
		this.cd_aplicacion = cd_aplicacion;
	}
	public Boolean getIsCajeroOrHH() {
		if(cd_perfil== null){
			return false;
		}
		return cd_perfil.equals(CodigoPerfilesEnum.CAJERO.getCodigo()) 
				|| cd_perfil.equals(CodigoPerfilesEnum.HANDHELD.getCodigo());
		
		//return isCajeroOrHH;
	}
	
	
	
	
	
	

}
