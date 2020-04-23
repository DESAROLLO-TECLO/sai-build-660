package mx.com.teclo.saicdmx.persistencia.vo.parteinformativo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParteInformativoCDocsVO implements Serializable{
	private static final long serialVersionUID = -5141994717718559985L;
	
	private Long idPi;
	private String noConsecutivo;
	private String fecha;
	private String fechaEntrega;
	private Long oficialId;
	private String oficialAoper;
	private String oficialPlaca;
	private String oficialNombre;
	private String oficialSector;
	private String oficialAgrupamiento;
	
	private String docIfe;
	private String docTarjCirc;
	private String docLicencia;
	private String docVerific;
	private String docOtros;
	
	private String docIfeNombre;
	private String docTarjCircNombre;
	private String docLicenciaNombre;
	private String docVerificNombre;
	private String docOtrosNombre;
	
	private String observacion;
	private Long modificadoPor;
	
	private String p_resultado;
	private String p_mensaje;

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
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
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
	public String getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(String fechaEntrega) {
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
	 * @return the resultado
	 */
	public String getResultado() {
		return p_resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(String resultado) {
		this.p_resultado = resultado;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return p_mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.p_mensaje = mensaje;
	}

	/**
	 * @return the strFechaRecepcion
	 
	public String getStrFechaRecepcion() {
		return strFechaRecepcion;
	}*/

	/**
	 * @param strFechaRecepcion the strFechaRecepcion to set
	 
	public void setStrFechaRecepcion(String strFechaRecepcion) {
		this.strFechaRecepcion = strFechaRecepcion;
	}*/

	/**
	 * @return the strFechaEntrega
	 
	public String getStrFechaEntrega() {
		return strFechaEntrega;
	}*/

	/**
	 * @param strFechaEntrega the strFechaEntrega to set
	
	public void setStrFechaEntrega(String strFechaEntrega) {
		this.strFechaEntrega = strFechaEntrega;
	} */

	/**
	 * @return the oficialPlaca
	 */
	public String getOficialPlaca() {
		return oficialPlaca;
	}

	/**
	 * @param oficialPlaca the oficialPlaca to set
	 */
	public void setOficialPlaca(String oficialPlaca) {
		this.oficialPlaca = oficialPlaca;
	}

	/**
	 * @return the oficialNombre
	 */
	public String getOficialNombre() {
		return oficialNombre;
	}

	/**
	 * @param oficialNombre the oficialNombre to set
	 */
	public void setOficialNombre(String oficialNombre) {
		this.oficialNombre = oficialNombre;
	}

	/**
	 * @return the oficialSector
	 */
	public String getOficialSector() {
		return oficialSector;
	}

	/**
	 * @param oficialSector the oficialSector to set
	 */
	public void setOficialSector(String oficialSector) {
		this.oficialSector = oficialSector;
	}

	/**
	 * @return the oficialAgrupamiento
	 */
	public String getOficialAgrupamiento() {
		return oficialAgrupamiento;
	}

	/**
	 * @param oficialAgrupamiento the oficialAgrupamiento to set
	 */
	public void setOficialAgrupamiento(String oficialAgrupamiento) {
		this.oficialAgrupamiento = oficialAgrupamiento;
	}
}
