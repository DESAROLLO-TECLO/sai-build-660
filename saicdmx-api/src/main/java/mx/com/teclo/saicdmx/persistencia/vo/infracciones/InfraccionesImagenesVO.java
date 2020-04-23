package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.util.Date;

public class InfraccionesImagenesVO{
	
	private String infraccNum;
	private String infraccNumCtrl;
	private Date fechaCreacion;
	private String nombreArchivo;
	private byte[] image;
	private Integer bitNumCiclo;
	private Integer bitCicloEstatus;
	
	public String getInfraccNum() {
		return infraccNum;
	}
	public void setInfraccNum(String infraccNum) {
		this.infraccNum = infraccNum;
	}
	public String getInfraccNumCtrl() {
		return infraccNumCtrl;
	}
	public void setInfraccNumCtrl(String infraccNumCtrl) {
		this.infraccNumCtrl = infraccNumCtrl;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	public Integer getBitNumCiclo() {
		return bitNumCiclo;
	}
	public void setBitNumCiclo(Integer bitNumCiclo) {
		this.bitNumCiclo = bitNumCiclo;
	}
	public Integer getBitCicloEstatus() {
		return bitCicloEstatus;
	}
	public void setBitCicloEstatus(Integer bitCicloEstatus) {
		this.bitCicloEstatus = bitCicloEstatus;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
