package mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FOTOMULTA_DETECCIONES")
public class FotomultaDeteccionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1499922522535822356L;

	@Id
	@Column(name = "FOTOMULTA_ID", unique = true, nullable = false)
	private Integer fotomultaId;
	
	@Column(name="LOTE_ID")
	private Integer loteId;
	
	@Column(name="VALIDO")
	private Integer valido;
	
	@Column(name="PROCESADO")
	private Integer procesado;
	
	@Column(name="PLACA")
	private String placa;

	@Column(name="FECHA")
	private String fecha;
	
	@Column(name="HORA")
	private String hora;
	
	@Column(name="TDSKUID")
	private String tdskuid;
	
	@Column(name="UT")
	private String ut;
	
	@Column(name="VELOCIDAD_DETECTADA")
	private String velocidadDetectada;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="APELLIDO_PATERNO")
	private String apellidoPaterno;
	
	@Column(name="APELLIDO_MATERNO")
	private String apellidoMaterno;
	
	@Column(name="CALLE")
	private String calle;
	
	@Column(name="NUM_EXTERIOR")
	private String numExterior;
	
	@Column(name="NUM_INTERIOR")
	private String numInterior;
	
	@Column(name="COLONIA")
	private String colonia;
	
	@Column(name="MUNICIPIO")
	private String municipio;
	
	@Column(name="CODIGO_POSTAL")
	private String codigoPostal;
	
	@Column(name="ENTIDAD_FEDERATIVA")
	private String entidadFederativa;
	
	@Column(name="MARCA")
	private String marca;
	
	@Column(name="SUBMARCA")
	private String submarca;
	
	@Column(name="MODELO")
	private String modelo;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	@Column(name="SERIE")
	private String serie;
	
	@Column(name="MOTOR")
	private String motor;
	
	@Column(name="ARTICULO_ID")
	private Integer articuloId;
	
	@Column(name="FECHA_CREACION ")
	private Date fechaCreacion;
	
	@Column(name="CREADO_POR")
	private Integer creadoPor;
	
	@Column(name="ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	@Column(name="MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name="CODIGO_ERROR")
	private Integer codigoError;

	@ManyToOne
    @JoinColumn(name="RADAR_TIPO_ID")
	private FotomultaTipoRadarDTO fotomultaTipoRadar;

	@Column(name="OFICIAL_PLACA")                
	private String oficialPlaca;
	
	@Column(name="OFICIAL_NOMBRE")
	private String oficialNombre;

	@Column(name="AUTORIZADA")
	private Integer autorizada;
	
	@Column(name="FECHA_VALIDACION")
	private Date fechaValidacion;
	
	@Column(name="MOTIVO_DESCARTE_ID")
	private Long motivoDescarte;
	
	@Column(name="MOTIVO_CANCELACION")           
	private String motivoCancelacion;
	
	@Column(name="FECHA_CANCELACION")      
	private Date fechaCancelacion;

	@Column(name="ORIGEN_PLACA") 
	private Integer origenPlaca;
	
	@ManyToOne
	@JoinColumn(name="MOTIVO_CANCELACION_ID")
	private FotomultaMotivoCancelacionDTO fotomultaMotivoCancelacionDTO;	
	
	@Column(name="DC_CANCELADO_POR")
	private Long canceladoPorDc;

	private String anio;
	
	private String mes;
	

	public FotomultaMotivoCancelacionDTO getFotomultaMotivoCancelacionDTO() {
		return fotomultaMotivoCancelacionDTO;
	}

	public void setFotomultaMotivoCancelacionDTO(FotomultaMotivoCancelacionDTO fotomultaMotivoCancelacionDTO) {
		this.fotomultaMotivoCancelacionDTO = fotomultaMotivoCancelacionDTO;
	}

	public Long getCanceladoPorDc() {
		return canceladoPorDc;
	}

	public void setCanceladoPorDc(Long canceladoPorDc) {
		this.canceladoPorDc = canceladoPorDc;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getFotomultaId() {
		return fotomultaId;
	}

	public void setFotomultaId(Integer fotomultaId) {
		this.fotomultaId = fotomultaId;
	}

	public Integer getLoteId() {
		return loteId;
	}

	public void setLoteId(Integer loteId) {
		this.loteId = loteId;
	}

	public Integer getValido() {
		return valido;
	}

	public void setValido(Integer valido) {
		this.valido = valido;
	}

	public Integer getProcesado() {
		return procesado;
	}

	public void setProcesado(Integer procesado) {
		this.procesado = procesado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTdskuid() {
		return tdskuid;
	}

	public void setTdskuid(String tdskuid) {
		this.tdskuid = tdskuid;
	}

	public String getUt() {
		return ut;
	}

	public void setUt(String ut) {
		this.ut = ut;
	}

	public String getVelocidadDetectada() {
		return velocidadDetectada;
	}

	public void setVelocidadDetectada(String velocidadDetectada) {
		this.velocidadDetectada = velocidadDetectada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumExterior() {
		return numExterior;
	}

	public void setNumExterior(String numExterior) {
		this.numExterior = numExterior;
	}

	public String getNumInterior() {
		return numInterior;
	}

	public void setNumInterior(String numInterior) {
		this.numInterior = numInterior;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getEntidadFederativa() {
		return entidadFederativa;
	}

	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getSubmarca() {
		return submarca;
	}

	public void setSubmarca(String submarca) {
		this.submarca = submarca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public Integer getArticuloId() {
		return articuloId;
	}

	public void setArticuloId(Integer articuloId) {
		this.articuloId = articuloId;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Integer creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Integer getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(Integer codigoError) {
		this.codigoError = codigoError;
	}

	public FotomultaTipoRadarDTO getFotomultaTipoRadar() {
		return fotomultaTipoRadar;
	}

	public void setFotomultaTipoRadar(FotomultaTipoRadarDTO fotomultaTipoRadar) {
		this.fotomultaTipoRadar = fotomultaTipoRadar;
	}

	public String getOficialPlaca() {
		return oficialPlaca;
	}

	public void setOficialPlaca(String oficialPlaca) {
		this.oficialPlaca = oficialPlaca;
	}

	public String getOficialNombre() {
		return oficialNombre;
	}

	public void setOficialNombre(String oficialNombre) {
		this.oficialNombre = oficialNombre;
	}

	public Integer getAutorizada() {
		return autorizada;
	}

	public void setAutorizada(Integer autorizada) {
		this.autorizada = autorizada;
	}

	public Date getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(Date fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	public Long getMotivoDescarte() {
		return motivoDescarte;
	}

	public void setMotivoDescarte(Long motivoDescarte) {
		this.motivoDescarte = motivoDescarte;
	}

	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public Integer getOrigenPlaca() {
		return origenPlaca;
	}

	public void setOrigenPlaca(Integer origenPlaca) {
		this.origenPlaca = origenPlaca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	
}
