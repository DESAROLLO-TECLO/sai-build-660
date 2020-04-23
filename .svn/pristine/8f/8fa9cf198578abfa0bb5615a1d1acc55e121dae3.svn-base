package mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PARTE_INFORMATIVO_C_DOCS")
public class ParteInformativoCDocsDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_PI", unique = true, nullable = false)
	private Long idPi;
	
	@Column(name = "NO_CONSECUTIVO", length=15)
	private String noConsecutivo;
	
	@Column(name = "FECHA")
	private Date fecha;
	
	@Column(name = "OFICIAL_ID")
	private Long oficialId;
	
	@Column(name = "OFICIAL_AOPER", length=50)
	private String oficialAoper;
	
	@Column(name = "DOC_IFE", length=2)
	private String docIfe;
	
	@Column(name = "DOC_TARJ_CIRC", length=2)
	private String docTarjCirc;
	
	@Column(name = "DOC_LICENCIA", length=2)
	private String docLicencia;
	
	@Column(name = "DOC_VERIFIC", length=2)
	private String docVerific;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name = "DOC_IFE_NOMBRE", length=100)
	private String docIfeNombre;
	
	@Column(name = "DOC_TARJ_CIRC_Nombre", length=100)
	private String docTarjCircNombre;
	
	@Column(name = "DOC_LICENCIA_NOMBRE", length=100)
	private String docLicenciaNombre;
	
	@Column(name = "DOC_VERIFIC_NOMBRE", length=100)
	private String docVerificNombre;
	
	@Column(name = "OBSERVACION", length=100)
	private String observacion;
	
	@Column(name = "FECHA_ENTREGA")
	private Date fechaEntrega;
	
	@Column(name = "DOC_OTROS", length=1)
	private String docOtros;
	
	@Column(name = "DOC_OTROS_NOMBRE", length=100)
	private String docOtrosNombre;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "piId")
	private List<ParteInformativoCDocsInfracsDTO> infracciones;

	/**
	 * @return the idPi
	 */
	public Long getIdPi() {
		return idPi;
	}

	/**
	 * @param idPi the idPi to set
	 */
	public void setIdPi(Long idPi) {
		this.idPi = idPi;
	}

	/**
	 * @return the noConsecutivo
	 */
	public String getNoConsecutivo() {
		return noConsecutivo;
	}

	/**
	 * @param noConsecutivo the noConsecutivo to set
	 */
	public void setNoConsecutivo(String noConsecutivo) {
		this.noConsecutivo = noConsecutivo;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the oficialId
	 */
	public Long getOficialId() {
		return oficialId;
	}

	/**
	 * @param oficialId the oficialId to set
	 */
	public void setOficialId(Long oficialId) {
		this.oficialId = oficialId;
	}

	/**
	 * @return the oficialAoper
	 */
	public String getOficialAoper() {
		return oficialAoper;
	}

	/**
	 * @param oficialAoper the oficialAoper to set
	 */
	public void setOficialAoper(String oficialAoper) {
		this.oficialAoper = oficialAoper;
	}

	/**
	 * @return the docIfe
	 */
	public String getDocIfe() {
		return docIfe;
	}

	/**
	 * @param docIfe the docIfe to set
	 */
	public void setDocIfe(String docIfe) {
		this.docIfe = docIfe;
	}

	/**
	 * @return the docTarjCirc
	 */
	public String getDocTarjCirc() {
		return docTarjCirc;
	}

	/**
	 * @param docTarjCirc the docTarjCirc to set
	 */
	public void setDocTarjCirc(String docTarjCirc) {
		this.docTarjCirc = docTarjCirc;
	}

	/**
	 * @return the docLicencia
	 */
	public String getDocLicencia() {
		return docLicencia;
	}

	/**
	 * @param docLicencia the docLicencia to set
	 */
	public void setDocLicencia(String docLicencia) {
		this.docLicencia = docLicencia;
	}

	/**
	 * @return the docVerific
	 */
	public String getDocVerific() {
		return docVerific;
	}

	/**
	 * @param docVerific the docVerific to set
	 */
	public void setDocVerific(String docVerific) {
		this.docVerific = docVerific;
	}

	/**
	 * @return the creadoPor
	 */
	public Long getCreadoPor() {
		return creadoPor;
	}

	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the modificadoPor
	 */
	public Long getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor the modificadoPor to set
	 */
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the docIfeNombre
	 */
	public String getDocIfeNombre() {
		return docIfeNombre;
	}

	/**
	 * @param docIfeNombre the docIfeNombre to set
	 */
	public void setDocIfeNombre(String docIfeNombre) {
		this.docIfeNombre = docIfeNombre;
	}

	/**
	 * @return the docTarjCircNombre
	 */
	public String getDocTarjCircNombre() {
		return docTarjCircNombre;
	}

	/**
	 * @param docTarjCircNombre the docTarjCircNombre to set
	 */
	public void setDocTarjCircNombre(String docTarjCircNombre) {
		this.docTarjCircNombre = docTarjCircNombre;
	}

	/**
	 * @return the docLicenciaNombre
	 */
	public String getDocLicenciaNombre() {
		return docLicenciaNombre;
	}

	/**
	 * @param docLicenciaNombre the docLicenciaNombre to set
	 */
	public void setDocLicenciaNombre(String docLicenciaNombre) {
		this.docLicenciaNombre = docLicenciaNombre;
	}

	/**
	 * @return the docVerificNombre
	 */
	public String getDocVerificNombre() {
		return docVerificNombre;
	}

	/**
	 * @param docVerificNombre the docVerificNombre to set
	 */
	public void setDocVerificNombre(String docVerificNombre) {
		this.docVerificNombre = docVerificNombre;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the docOtros
	 */
	public String getDocOtros() {
		return docOtros;
	}

	/**
	 * @param docOtros the docOtros to set
	 */
	public void setDocOtros(String docOtros) {
		this.docOtros = docOtros;
	}

	/**
	 * @return the docOtrosNombre
	 */
	public String getDocOtrosNombre() {
		return docOtrosNombre;
	}

	/**
	 * @param docOtrosNombre the docOtrosNombre to set
	 */
	public void setDocOtrosNombre(String docOtrosNombre) {
		this.docOtrosNombre = docOtrosNombre;
	}

	/**
	 * @return the infracciones
	 */
	public List<ParteInformativoCDocsInfracsDTO> getInfracciones() {
		return infracciones;
	}

	/**
	 * @param infracciones the infracciones to set
	 */
	public void setInfracciones(List<ParteInformativoCDocsInfracsDTO> infracciones) {
		this.infracciones = infracciones;
	}
}
