package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.util.Date;

public class IngresosImagenesVO{
	
	private String infraccNum;
	private String infraccNumCtrl;
	private Date fechaCreacion;
	private String nombreArchivo;
	private byte[] image;
	private Integer idMovimiento;
	private Integer idSalida;
	
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
	public Integer getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public Integer getIdSalida() {
		return idSalida;
	}
	public void setIdSalida(Integer idSalida) {
		this.idSalida = idSalida;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}