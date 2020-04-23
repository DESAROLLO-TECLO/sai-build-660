package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TmpParametrosKey implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "PARAMETRO")
	private String parametro;
	@Column(name = "PROCESO")
	private String proceso;
	@Column(name = "FECHA_PAR")
	private Date fechaParametro;
	
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public Date getFechaParametro() {
		return fechaParametro;
	}
	public void setFechaParametro(Date fechaParametro) {
		this.fechaParametro = fechaParametro;
	}
}
