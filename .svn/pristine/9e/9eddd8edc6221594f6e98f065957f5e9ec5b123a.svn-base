package mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDTO;

@Entity
@Table(name = "GARANTIAS")
public class GarantiaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3276454526902434748L;
	@Id
	@SequenceGenerator(name = "seq_garantias", sequenceName = "SEQ_GARANTIAS", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_garantias")
	@Column(name = "GARANTIA_ID", nullable = false, unique = true)
	private Long garantiaId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "INFRAC_NUM")
	private InfraccionDTO infraccionDTO;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "GARANTIA_DOCUMENTO_ID")
	private GarantiaDocumentoDTO garantiaDocumentoDTO;
	@Column(name = "DOCUMENTO_FOLIO")
	private String documentoFolio;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "GARANTIA_PROCESO_ID")
	private GarantiaProcesoDTO garantiaProcesoDTO;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_ID")
	private EmpleadosDTO empleadosDTO;
	@Column(name = "RECIBIDA")
	private Boolean recibida;
	@Column(name = "ENTREGADA")
	private Boolean entregada;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "FECHA_CREACION", insertable = false, updatable = false)
	private Date fechaCreacion;
	@Column(name = "ULTIMA_MODIFICACION", insertable = false, updatable = true)
	private Date ultimaModificacion;
	@Column(name="PAGADA")
	private Boolean pagada;
	@Column(name = "TIPO_PAGO")
	private Integer tipoPago;
	@Column(name="ID_LOTE")
	private Long idLote;

	// @OneToMany(mappedBy = "garantia", cascade = CascadeType.ALL)
	// public Set<GarantiaEstatusProcesoDTO> garantiasEstatusProcesoDTO = new
	// HashSet<GarantiaEstatusProcesoDTO>();
	/**
	 * @return the garantiaId
	 */
	public Long getGarantiaId() {
		return garantiaId;
	}

	/**
	 * @param garantiaId
	 *            the garantiaId to set
	 */
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}

	/**
	 * @return the garantiaDocumentoDTO
	 */
	public GarantiaDocumentoDTO getGarantiaDocumentoDTO() {
		return garantiaDocumentoDTO;
	}

	/**
	 * @param garantiaDocumentoDTO
	 *            the garantiaDocumentoDTO to set
	 */
	public void setGarantiaDocumentoDTO(
			GarantiaDocumentoDTO garantiaDocumentoDTO) {
		this.garantiaDocumentoDTO = garantiaDocumentoDTO;
	}

	/**
	 * @return the documentoFolio
	 */
	public String getDocumentoFolio() {
		return documentoFolio;
	}

	/**
	 * @param documentoFolio
	 *            the documentoFolio to set
	 */
	public void setDocumentoFolio(String documentoFolio) {
		this.documentoFolio = documentoFolio;
	}

	/**
	 * @return the garantiaProcesoDTO
	 */
	public GarantiaProcesoDTO getGarantiaProcesoDTO() {
		return garantiaProcesoDTO;
	}

	/**
	 * @param garantiaProcesoDTO
	 *            the garantiaProcesoDTO to set
	 */
	public void setGarantiaProcesoDTO(GarantiaProcesoDTO garantiaProcesoDTO) {
		this.garantiaProcesoDTO = garantiaProcesoDTO;
	}

	/**
	 * @return the creadoPor
	 */
	public Long getCreadoPor() {
		return creadoPor;
	}

	/**
	 * @param creadoPor
	 *            the creadoPor to set
	 */
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * @return the modificadoPor
	 */
	public Long getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor
	 *            the modificadoPor to set
	 */
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	
	/**
	 * 
	 * @return
	 */
	public InfraccionDTO getInfraccionDTO() {
		return infraccionDTO;
	}
	
	/**
	 * 
	 * @param infraccionDTO
	 */
	public void setInfraccionDTO(InfraccionDTO infraccionDTO) {
		this.infraccionDTO = infraccionDTO;
	}

	/**
	 * @return the empleadoDTO
	 */
//	public EmpleadosDTO getEmpleadoDTO() {
//		return empleadosDTO;
//	}
//
//	/**
//	 * @param empleadoDTO the empleadoDTO to set
//	 */
//	public void setEmpleadoDTO(EmpleadosDTO empleadoDTO) {
//		this.empleadosDTO = empleadosDTO;
//	}

	
	
	/**
	 * @return the recibida
	 */
	public Boolean getRecibida() {
		return recibida;
	}

	public EmpleadosDTO getEmpleadosDTO() {
		return empleadosDTO;
	}

	public void setEmpleadosDTO(EmpleadosDTO empleadosDTO) {
		this.empleadosDTO = empleadosDTO;
	}

	/**
	 * @param recibida
	 *            the recibida to set
	 */
	public void setRecibida(Boolean recibida) {
		this.recibida = recibida;
	}

	/**
	 * @return the entregada
	 */
	public Boolean getEntregada() {
		return entregada;
	}

	/**
	 * @param entregada
	 *            the entregada to set
	 */
	public void setEntregada(Boolean entregada) {
		this.entregada = entregada;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the ultimaModificacion
	 */
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	/**
	 * @param ultimaModificacion
	 *            the ultimaModificacion to set
	 */
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
	
	/**
	 * 
	 * @return pagada Boolean
	 */
	public Boolean getPagada() {
		return pagada;
	}
	
	/**
	 * 
	 * @param pagada Boolean
	 */
	public void setPagada(Boolean pagada) {
		this.pagada = pagada;
	}

	/**
	 * @return the tipoPago
	 */
	public Integer getTipoPago() {
		return tipoPago;
	}

	/**
	 * @param tipoPago the tipoPago to set
	 */
	public void setTipoPago(Integer tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	public Long getIdLote(){
		return idLote;
	}
	
	public void setIdLote(Long idLote){
		this.idLote=idLote;
	}


}
