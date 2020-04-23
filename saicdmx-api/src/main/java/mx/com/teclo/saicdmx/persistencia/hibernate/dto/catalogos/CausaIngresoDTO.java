package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CAUSA_INGRESO")
public class CausaIngresoDTO implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -5246746059411045458L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CAUSA_ID", unique = true, nullable = false)
	private Long idCausaIngreso;
	@Column(name = "CAUSA_COD")
	private String codigoCausaIngreso;
	@Column(name = "CAUSA_NOMBRE")
	private String nombreCausaIngreso;
	@Column(name = "CAUSA_STATUS")
	private String statusCausaIngreso;
	/**
	 * @return the idCausaIngreso
	 */
	public Long getIdCausaIngreso() {
		return idCausaIngreso;
	}
	/**
	 * @param idCausaIngreso the idCausaIngreso to set
	 */
	public void setIdCausaIngreso(Long idCausaIngreso) {
		this.idCausaIngreso = idCausaIngreso;
	}
	/**
	 * @return the codigoCausaIngreso
	 */
	public String getCodigoCausaIngreso() {
		return codigoCausaIngreso;
	}
	/**
	 * @param codigoCausaIngreso the codigoCausaIngreso to set
	 */
	public void setCodigoCausaIngreso(String codigoCausaIngreso) {
		this.codigoCausaIngreso = codigoCausaIngreso;
	}
	/**
	 * @return the nombreCausaIngreso
	 */
	public String getNombreCausaIngreso() {
		return nombreCausaIngreso;
	}
	/**
	 * @param nombreCausaIngreso the nombreCausaIngreso to set
	 */
	public void setNombreCausaIngreso(String nombreCausaIngreso) {
		this.nombreCausaIngreso = nombreCausaIngreso;
	}
	/**
	 * @return the statusCausaIngreso
	 */
	public String getStatusCausaIngreso() {
		return statusCausaIngreso;
	}
	/**
	 * @param statusCausaIngreso the statusCausaIngreso to set
	 */
	public void setStatusCausaIngreso(String statusCausaIngreso) {
		this.statusCausaIngreso = statusCausaIngreso;
	}	
	  
	
}
