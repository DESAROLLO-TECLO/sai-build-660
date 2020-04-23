package mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_INFRACCIONES_VALIDA_IMAGEN")
public class VInfraccionesValidaImagenDTO implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@Column(name = "INFRAC_NUM")
	private String numeroInfraccion;
	@Column(name = "ESTATUS")
	private String estatus;
	@Column(name = "FECHA_MODIFICACION")
	private String fechaModificacion;
	@Column(name = "MODIFICADO_POR")
	private String modificadoPor;
	
	public String getNumeroInfraccion() {
		return numeroInfraccion;
	}
	public void setNumeroInfraccion(String numeroInfraccion) {
		this.numeroInfraccion = numeroInfraccion;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	@Override
	public String toString() {
		return "VInfraccionesValidaImagen [numeroInfraccion=" + numeroInfraccion + ", estatus=" + estatus
				+ ", fechaModificacion=" + fechaModificacion + ", modificadoPor=" + modificadoPor + "]";
	}
	

	
	
	
}
