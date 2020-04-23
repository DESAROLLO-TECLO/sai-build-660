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
@Table(name = "TAQ038C_AR_AGRUPACION_HOJAS")
public class AgrupacionHojasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8076992294093355765L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_TIPO_AGRUPACION", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idTipoAgrupacion;

	@Column(name = "CD_TIPO_AGRUPACION", nullable = false, length = 15)
	private String cdTipoAgrupacion;

	@Column(name = "TX_OBSERVACION", nullable = true, length = 100)
	private String txObservacion;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private char stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	public Long getIdTipoAgrupacion() {
		return idTipoAgrupacion;
	}

	public void setIdTipoAgrupacion(Long idTipoAgrupacion) {
		this.idTipoAgrupacion = idTipoAgrupacion;
	}

	public String getCdTipoAgrupacion() {
		return cdTipoAgrupacion;
	}

	public void setCdTipoAgrupacion(String cdTipoAgrupacion) {
		this.cdTipoAgrupacion = cdTipoAgrupacion;
	}

	public String getTxObservacion() {
		return txObservacion;
	}

	public void setTxObservacion(String txObservacion) {
		this.txObservacion = txObservacion;
	}

	public char getStActivo() {
		return stActivo;
	}

	public void setStActivo(char stActivo) {
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
