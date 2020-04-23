package mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INFRACCIONES_VALIDA_IMAGEN")
public class InfraccionesValidaImagenDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
    @Id
	@Column(name="INFRAC_NUM")
	private String infraccionNumero;
	@Column(name="ESTATUS")
	private String estatus;
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name="CREADO_POR")
	private long creadoPor;
	@Column(name="ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	@Column(name="MODIFICADO_POR")
    private long modificadoPor;
	
	public String getInfraccionNumero() {
		return infraccionNumero;
	}
	public void setInfraccionNumero(String infraccionNumero) {
		this.infraccionNumero = infraccionNumero;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(long creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
	public long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	@Override
	public String toString() {
		return "InfraccionesValidaImagenDTO [infraccionNumero=" + infraccionNumero + ", estatus=" + estatus
				+ ", fechaCreacion=" + fechaCreacion + ", creadoPor=" + creadoPor + ", ultimaModificacion="
				+ ultimaModificacion + ", modificadoPor=" + modificadoPor + "]";
	}
	
	
	
	

}
