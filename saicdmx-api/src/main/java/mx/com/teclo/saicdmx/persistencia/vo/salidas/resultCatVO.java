package mx.com.teclo.saicdmx.persistencia.vo.salidas;

import java.util.Date;

public class resultCatVO {
	private Long idCat;
	private String nomTipoCat;
	private String descripcion;
	private String fchCompatacion;
	private String estatus;
	
	
	public Long getIdCat() {
		return idCat;
	}
	public void setIdCat(Long idCat) {
		this.idCat = idCat;
	}
	public String getNomTipoCat() {
		return nomTipoCat;
	}
	public void setNomTipoCat(String nomTipoCat) {
		this.nomTipoCat = nomTipoCat;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFchCompatacion() {
		return fchCompatacion;
	}
	public void setFchCompatacion(String fchCompatacion) {
		this.fchCompatacion = fchCompatacion;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	

}
