package mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAQ041D_AR_RESTRICCIONES_QUERY")
public class RestriccionesQueryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7131988268055707119L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_RESTRICCION", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idRestriccion;

	@Column(name = "CD_RESTRICCION", nullable = false, length = 15)
	private String cdRestriccion;

	@Column(name = "TX_RESTRICCION", nullable = false, length = 100)
	private String txRestriccion;

	@Column(name = "TX_VALOR", nullable = false, length = 50)
	private String txValor;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	public Long getIdRestriccion() {
		return idRestriccion;
	}

	public void setIdRestriccion(Long idRestriccion) {
		this.idRestriccion = idRestriccion;
	}

	public String getCdRestriccion() {
		return cdRestriccion;
	}

	public void setCdRestriccion(String cdRestriccion) {
		this.cdRestriccion = cdRestriccion;
	}

	public String getTxRestriccion() {
		return txRestriccion;
	}

	public void setTxRestriccion(String txRestriccion) {
		this.txRestriccion = txRestriccion;
	}

	public String getTxValor() {
		return txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

}
