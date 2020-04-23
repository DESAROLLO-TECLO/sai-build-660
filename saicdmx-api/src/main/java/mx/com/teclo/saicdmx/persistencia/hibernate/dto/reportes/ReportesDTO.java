package mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.AplicacionDTO;

@Entity
@Table(name = "TAQ004D_AR_REPORTES")
public class ReportesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5030485256231002698L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_REPORTE", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idReporte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_REPORTE", nullable = false)
	private TipoReportesDTO tipoReporte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_TITULO", nullable = false)
	private TipoTitulosDTO tipoTitulo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_APLICACION", nullable = false)
	private AplicacionDTO aplicacion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reporte")
	@OrderBy("nuOrden ASC")
	@Where(clause = "ST_ACTIVO = 1")
	private List<ParametrosDTO> parametros = new ArrayList<ParametrosDTO>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reporte")
	@Where(clause = "ST_ACTIVO = 1")
	private List<ReportesFormatosDTO> reporteFormato = new ArrayList<ReportesFormatosDTO>();

	@Column(name = "CD_REPORTE", nullable = false, length = 10)
	private String cdReporte;

	@Column(name = "NB_REPORTE", nullable = false, length = 100)
	private String nbReporte;

	@Column(name = "TX_REPORTE", nullable = false, length = 300)
	private String txReporte;

	@Column(name = "TX_URL", nullable = false, length = 200)
	private String url;

	@Column(name = "TX_URL_DINAMIC", nullable = false, length = 50)
	private String txUrlDinamic;

	@Column(name = "SCRIPT_SELECT", nullable = false)
	private String scriptSelect;

	@Column(name = "SCRIPT_FROM", nullable = false)
	private String scritFrom;

	@Column(name = "SCRIPT_WHERE", nullable = false)
	private String scriptWhere;

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

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public TipoReportesDTO getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(TipoReportesDTO tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public TipoTitulosDTO getTipoTitulo() {
		return tipoTitulo;
	}

	public void setTipoTitulo(TipoTitulosDTO tipoTitulo) {
		this.tipoTitulo = tipoTitulo;
	}

	public AplicacionDTO getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionDTO aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getCdReporte() {
		return cdReporte;
	}

	public void setCdReporte(String cdReporte) {
		this.cdReporte = cdReporte;
	}

	public String getNbReporte() {
		return nbReporte;
	}

	public void setNbReporte(String nbReporte) {
		this.nbReporte = nbReporte;
	}

	public String getTxReporte() {
		return txReporte;
	}

	public void setTxReporte(String txReporte) {
		this.txReporte = txReporte;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getScriptSelect() {
		return scriptSelect;
	}

	public void setScriptSelect(String scriptSelect) {
		this.scriptSelect = scriptSelect;
	}

	public String getScritFrom() {
		return scritFrom;
	}

	public void setScritFrom(String scritFrom) {
		this.scritFrom = scritFrom;
	}

	public String getScriptWhere() {
		return scriptWhere;
	}

	public void setScriptWhere(String scriptWhere) {
		this.scriptWhere = scriptWhere;
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

	public List<ParametrosDTO> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametrosDTO> parametros) {
		this.parametros = parametros;
	}

	public List<ReportesFormatosDTO> getReporteFormato() {
		return reporteFormato;
	}

	public void setReporteFormato(List<ReportesFormatosDTO> reporteFormato) {
		this.reporteFormato = reporteFormato;
	}

	public String getTxUrlDinamic() {
		return txUrlDinamic;
	}

	public void setTxUrlDinamic(String txUrlDinamic) {
		this.txUrlDinamic = txUrlDinamic;
	}

}
