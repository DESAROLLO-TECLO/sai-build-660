package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAI035D_PARAMETROS_PAGOS")
public class ParametrosPagosDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6169783930247890421L;
	@Id
	@Column(name="ID_PARAMETRO_PAGO")
	private Integer idParametroPago;
	@Column(name="ST_BLOQUEO_PAGO")
	private Integer stBloqueoPago;
	@Column(name="ST_ACTIVO")
	private Integer stActivo;
	@Column(name="ID_USR_CREACION")
	private Integer idUsrCreacion;
	@Column(name="FH_CREACION")
	private Date fechaCreacion;
	@Column(name="ID_USR_MODIFICA")
	private Integer idUsrModifica;
	@Column(name="FH_MODIFICACION")
	private Date fechaModificacion;
	
	public Integer getIdParametroPago() {
		return idParametroPago;
	}
	public void setIdParametroPago(Integer idParametroPago) {
		this.idParametroPago = idParametroPago;
	}
	public Integer getStBloqueoPago() {
		return stBloqueoPago;
	}
	public void setStBloqueoPago(Integer stBloqueoPago) {
		this.stBloqueoPago = stBloqueoPago;
	}
	public Integer getStActivo() {
		return stActivo;
	}
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
	public Integer getIdUsrCreacion() {
		return idUsrCreacion;
	}
	public void setIdUsrCreacion(Integer idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Integer getIdUsrModifica() {
		return idUsrModifica;
	}
	public void setIdUsrModifica(Integer idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	

}
