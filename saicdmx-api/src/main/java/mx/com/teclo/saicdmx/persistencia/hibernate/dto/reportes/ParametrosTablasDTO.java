package mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAQ014D_AR_PARAMETROS_TABLAS")
public class ParametrosTablasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3482812046860478755L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PARAMETRO_TABLA", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idParametroTabla;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PARAMETRO", nullable = false)
	private ParametrosDTO parametro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TABLA", nullable = false)
	private TablasDTO tabla;

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

	public Long getIdParametroTabla() {
		return idParametroTabla;
	}

	public void setIdParametroTabla(Long idParametroTabla) {
		this.idParametroTabla = idParametroTabla;
	}

	public ParametrosDTO getParametro() {
		return parametro;
	}

	public void setParametro(ParametrosDTO parametro) {
		this.parametro = parametro;
	}

	public TablasDTO getTabla() {
		return tabla;
	}

	public void setTabla(TablasDTO tabla) {
		this.tabla = tabla;
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
