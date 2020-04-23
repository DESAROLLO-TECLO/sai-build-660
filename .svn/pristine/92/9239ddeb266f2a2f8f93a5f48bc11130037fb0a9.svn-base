package mx.com.teclo.saicdmx.persistencia.hibernate.dto.atencionCiudadana;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoTramiteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;

@Entity
@Table(name = "TAI045D_AC_TRAMITES")
public class AtencionCiudadanaTramitesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1499922522535822356L;

	@Id
	@Column(name = "ID_AC_TRAMITE", unique = true, nullable = false)
	private String idACTramite;
	@Column(name = "ID_TIPO_TRAMITE")
	private String tipoTramite;
	@Column(name = "FH_ALTA")
	private Date fhAlta;
	@Column(name = "NB_CIUDADANO")
	private String nbCiudadano;
	@Column(name = "NB_C_PATERNO")
	private String nbCPaterno;
	@Column(name = "NB_C_MATERNO")
	private String nbMaterno;
	@Column(name = "NU_C_TELEFONO")
	private String nuCTelefono;
	@Column(name = "TX_C_CORREO")
	private String txCCorreo;
	@Column(name = "TX_C_CALLE")
	private String txCCalle;
	@Column(name = "TX_C_COLONIA")
	private String txCColonia;
	@Column(name = "NU_C_INT")
	private String nuCInt;
	@Column(name = "NU_C_EXT")
	private String nuCExt;
	@Column(name = "ID_C_DELEGACION")
	private Long idCDelegacion;
	@Column(name = "NU_C_CP")
	private String nuCCP;
	@Column(name = "ID_C_EDO")
	private Long idCEDO;
	@Column(name = "CD_C_PLACA")
	private String cdCPlaca;
	@Column(name = "ID_MARCA")
	private Long idMarca;
	@Column(name = "ID_MODELO")
	private Long idModelo;
	@Column(name = "ID_COLOR")
	private Long idColor;
	@Column(name = "ID_TIPO_VEHICULO")
	private Long idTipoVehiculo;
	@Column(name = "TX_HECHOS")
	private String txHechos;
	@Column(name = "TX_CC")
	private String txCC;
	@Column(name = "ID_TIPO_DOC")
	private String idTipoDoc;
	/*@Column(name = "LB_EXPEDIENTE")
	private byte[] lbExpediente;*/
	@Column(name = "ST_EXPEDIENTE", updatable = false)
	private boolean stExpediente;
	@Column(name = "ST_ATENDIDO")
	private boolean stAtendido;
	@Column(name = "NB_MARCA_OTRO")
	private String nbMarcaOtro;
	@Column(name = "NB_MODELO_OTRO")
	private String nbModelOtro;
	@Column(name = "NB_DOC_OTRO")
	private String nbDocOtro;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_EMP", referencedColumnName="EMP_ID", insertable=false, updatable=false)
	private EmpleadosDTO empId;
	@Column(name = "ID_USR_CREACION", updatable = false)
	private Long creadoPor;
	@Column(name = "FH_CREACION", updatable = false)
	private Date fhCreacion;
	@Column(name = "ID_USR_MODIFICA")
	private Long modificadoPor;
	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;
	@Column(name = "NB_EMPRESA")
	private String nbEmpresa; 
	@Column(name="TX_MOTIVO_CAMBIO")
	private String motivoCambio;
	@Column(name = "CD_SOLICITANTE")
	private String cdTipoPersona;
	//nuevos
	@Column(name = "ID_MEDIO_SOLICITUD")
	private Integer idMedioSolicitud;
	@Column(name = "TX_INFRACCION")
	private String txInfraccion; 
	@Column(name="CD_NUEVO_ORIGEN_PLACA")
	private Integer cdNuevoOrigenPlaca;
	@Column(name = "ID_NUEVO_TIPO_PERSONA")
	private Integer idNuevoTipoPersona;
	@Column(name = "ID_NUEVO_EDO")
	private Integer idNuevoEdo;
	

	public String getIdACTramite() {
		return idACTramite;
	}

	public void setIdACTramite(String idACTramite) {
		this.idACTramite = idACTramite;
	}

	public Date getFhAlta() {
		return fhAlta;
	}

	public void setFhAlta(Date fhAlta) {
		this.fhAlta = fhAlta;
	}

	public String getNbCiudadano() {
		return nbCiudadano;
	}

	public void setNbCiudadano(String nbCiudadano) {
		this.nbCiudadano = nbCiudadano;
	}

	public String getNbCPaterno() {
		return nbCPaterno;
	}

	public void setNbCPaterno(String nbCPaterno) {
		this.nbCPaterno = nbCPaterno;
	}

	public String getNbMaterno() {
		return nbMaterno;
	}

	public void setNbMaterno(String nbMaterno) {
		this.nbMaterno = nbMaterno;
	}

	public String getTxCCorreo() {
		return txCCorreo;
	}

	public void setTxCCorreo(String txCCorreo) {
		this.txCCorreo = txCCorreo;
	}

	public String getTxCCalle() {
		return txCCalle;
	}

	public void setTxCCalle(String txCCalle) {
		this.txCCalle = txCCalle;
	}

	public String getTxCColonia() {
		return txCColonia;
	}

	public void setTxCColonia(String txCColonia) {
		this.txCColonia = txCColonia;
	}

	public String getTxHechos() {
		return txHechos;
	}

	public void setTxHechos(String txHechos) {
		this.txHechos = txHechos;
	}

	public String getTxCC() {
		return txCC;
	}

	public void setTxCC(String txCC) {
		this.txCC = txCC;
	}

	public String getIdTipoDoc() {
		return idTipoDoc;
	}

	public void setIdTipoDoc(String idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}

	/*public byte[] getLbExpediente() {
		return lbExpediente;
	}

	public void setLbExpediente(byte[] lbExpediente) {
		this.lbExpediente = lbExpediente;
	}*/

	public boolean isStAtendido() {
		return stAtendido;
	}

	public void setStAtendido(boolean stAtendido) {
		this.stAtendido = stAtendido;
	}

	public String getNbMarcaOtro() {
		return nbMarcaOtro;
	}

	public void setNbMarcaOtro(String nbMarcaOtro) {
		this.nbMarcaOtro = nbMarcaOtro;
	}

	public String getNbModelOtro() {
		return nbModelOtro;
	}

	public void setNbModelOtro(String nbModelOtro) {
		this.nbModelOtro = nbModelOtro;
	}

	public String getNbDocOtro() {
		return nbDocOtro;
	}

	public void setNbDocOtro(String nbDocOtro) {
		this.nbDocOtro = nbDocOtro;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Date getfhModificacion() {
		return fhModificacion;
	}

	public void setfhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	public boolean isStExpediente() {
		return stExpediente;
	}

	public void setStExpediente(boolean stExpediente) {
		this.stExpediente = stExpediente;
	}

	public EmpleadosDTO getEmpId() {
		return empId;
	}

	public void setEmpId(EmpleadosDTO empId) {
		this.empId = empId;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public String getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(String tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	public String getNuCInt() {
		return nuCInt;
	}

	public void setNuCInt(String nuCInt) {
		this.nuCInt = nuCInt;
	}

	public String getNuCExt() {
		return nuCExt;
	}

	public void setNuCExt(String nuCExt) {
		this.nuCExt = nuCExt;
	}

	public Long getIdCDelegacion() {
		return idCDelegacion;
	}

	public void setIdCDelegacion(Long idCDelegacion) {
		this.idCDelegacion = idCDelegacion;
	}

	public String getNuCCP() {
		return nuCCP;
	}

	public void setNuCCP(String nuCCP) {
		this.nuCCP = nuCCP;
	}

	public Long getIdCEDO() {
		return idCEDO;
	}

	public void setIdCEDO(Long idCEDO) {
		this.idCEDO = idCEDO;
	}

	public String getCdCPlaca() {
		return cdCPlaca;
	}

	public void setCdCPlaca(String cdCPlaca) {
		this.cdCPlaca = cdCPlaca;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public Long getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Long idModelo) {
		this.idModelo = idModelo;
	}

	public Long getIdColor() {
		return idColor;
	}

	public void setIdColor(Long idColor) {
		this.idColor = idColor;
	}

	public Long getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	public void setIdTipoVehiculo(Long idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	public String getNuCTelefono() {
		return nuCTelefono;
	}

	public void setNuCTelefono(String nuCTelefono) {
		this.nuCTelefono = nuCTelefono;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	public String getNbEmpresa() {
		return nbEmpresa;
	}

	public void setNbEmpresa(String nbEmpresa) {
		this.nbEmpresa = nbEmpresa;
	}

	public String getMotivoCambio() {
		return motivoCambio;
	}

	public void setMotivoCambio(String motivoCambio) {
		this.motivoCambio = motivoCambio;
	}

	public String getCdTipoPersona() {
		return cdTipoPersona;
	}

	public void setCdTipoPersona(String cdTipoPersona) {
		this.cdTipoPersona = cdTipoPersona;
	}

	public Integer getIdMedioSolicitud() {
		return idMedioSolicitud;
	}

	public void setIdMedioSolicitud(Integer idMedioSolicitud) {
		this.idMedioSolicitud = idMedioSolicitud;
	}

	public String getTxInfraccion() {
		return txInfraccion;
	}

	public void setTxInfraccion(String txInfraccion) {
		this.txInfraccion = txInfraccion;
	}

	public Integer getCdNuevoOrigenPlaca() {
		return cdNuevoOrigenPlaca;
	}

	public void setCdNuevoOrigenPlaca(Integer cdNuevoOrigenPlaca) {
		this.cdNuevoOrigenPlaca = cdNuevoOrigenPlaca;
	}

	public Integer getIdNuevoTipoPersona() {
		return idNuevoTipoPersona;
	}

	public void setIdNuevoTipoPersona(Integer idNuevoTipoPersona) {
		this.idNuevoTipoPersona = idNuevoTipoPersona;
	}

	public Integer getIdNuevoEdo() {
		return idNuevoEdo;
	}

	public void setIdNuevoEdo(Integer idNuevoEdo) {
		this.idNuevoEdo = idNuevoEdo;
	}
	
	
	
	
}
