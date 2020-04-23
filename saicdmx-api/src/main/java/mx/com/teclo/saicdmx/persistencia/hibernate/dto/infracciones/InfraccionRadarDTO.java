package mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticuloDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DelegacionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EstadoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GruaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ResponsableVehiculoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoLicenciaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoColorDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoModeloDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoTipoDTO;

@Entity
@Table(name="INFRACCIONES_RADAR")
public class InfraccionRadarDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "INFRAC_NUM", unique = true, nullable = false)
	private String infraccNum;
	@Column(name = "INFRAC_AAA")
	private Integer infraccAAA;
	@Column(name = "INFRAC_AAA_MMM")
	private Integer infraccAAAMMM;
	@Column(name = "INFRAC_ORIGEN")
	private String infraccOrigen;
	@Column(name = "INFRAC_FOLIO")
	private Integer infraccFolio;
	@Column(name = "INFRAC_IMPRESA")
	private String infraccImpresa;
	@Column(name = "INFRAC_NUM_CTRL")
	private String infraccNumCtrl;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "SEC_ID")
	private SectorDTO sector;
	@Column(name = "UT_ID")
	private Long utId;
	@Column(name = "INFRAC_CON_PLACA")
	private String infraccConPlaca;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "INFRAC_PLACA_EDO")
	private EstadoDTO infraccPlacaEdo;
	@Column(name = "INFRAC_PLACA")
	private String infraccPlaca;
	@Column(name = "INFRAC_I_PATERNO")
	private String infIPaterno;
	@Column(name = "INFRAC_I_MATERNO")
	private String infIMaterno;
	@Column(name = "INFRAC_I_NOMBRE")
	private String infINombre;
	@Column(name = "INFRAC_I_RFC")
	private String infIRFC;
	@Column(name = "INFRAC_I_CALLE")
	private String infICalle;
	@Column(name = "INFRAC_I_NUM_EXT")
	private String infINumExt;
	@Column(name = "INFRAC_I_NUM_INT")
	private String infINumInt;
	@Column(name = "INFRAC_I_COLONIA")
	private String infIColonia;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(name="INFRAC_I_EDO_ID", referencedColumnName="EDO_ID"),
        @JoinColumn(name="INFRAC_I_DEL_ID", referencedColumnName="DEL_ID")
    })
	private DelegacionDTO infraccIDelEdo;
	@Column(name = "INFRAC_LICENCIA")
	private String infraccLicencia;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "TIPO_L_ID")
	private TipoLicenciaDTO tipoL;
	@Column(name = "INFRAC_L_SERV_PUBLICO")
	private String infraccLServPub;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "INFRAC_LIC_EDO")
	private EstadoDTO infraccLicEdo;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(name="VMAR_ID", referencedColumnName="VMAR_ID"),
        @JoinColumn(name="VMOD_ID", referencedColumnName="VMOD_ID")
    })
	private VehiculoModeloDTO vModMar;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "VCOLOR_ID")
	private VehiculoColorDTO vColor;
	@Column(name = "VORIGEN")
	private String vOrigen;
	@Column(name = "VTARJETA_CIRCULACION")
	private String vTarCircul;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "VTIPO_ID")
	private VehiculoTipoDTO vTipo;
	@Column(name = "ART_MOTIVACION")
	private String artMot;
	@Column(name = "INFRAC_M_EN_LA_CALLE")
	private String infracMCalle;
	@Column(name = "INFRAC_M_ENTRE_CALLE")
	private String infracMEntreCalle;
	@Column(name = "INFRAC_M_Y_LA_CALLE")
	private String infracMYCalle;
	@Column(name = "INFRAC_M_COLONIA")
	private String infracMColonia;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(name="INFRAC_M_EDO_ID", referencedColumnName="EDO_ID"),
        @JoinColumn(name="INFRAC_M_DEL_ID", referencedColumnName="DEL_ID")
    })
	private DelegacionDTO infracMDelEdo;
	@Column(name = "INFRAC_M_FECHA_HORA")
	private Timestamp infracMFechaHora;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ART_ID")
	private ArticuloDTO articulo;
	@Column(name = "INFRAC_LEY_TRANSPORTE")
	private String infracLeyTrans;
	@Column(name = "SANCION_ART_ID")
	private Long sancArt;
	@Column(name = "INFRAC_ARRASTRE")
	private String infracArrastre;
	@Column(name = "INFRAC_NUM_ARRASTRE")
	private String infracNumArrastre;
	@Column(name = "INFRAC_TIPO_ARRASTRE")
	private String infracTipoArrastre;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "GRUA_ID")
	private GruaDTO grua;
	@Column(name = "DEP_ID")
	private Long depId;
	@Column(name = "EMP_ID")
	private Long empId;
	@Column(name = "EMP_SECTOR")
	private Long empSector;
	@Column(name = "EMP_AGRUP")
	private Long empAgrup;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "R_VEH_ID")
	private ResponsableVehiculoDTO responsableVehiculo;
	@Column(name = "EVEN_ID")
	private Long evenId;
	@Column(name = "ESTAT_ID")
	private Long estatId;
	@Column(name = "INFRAC_PAGADA")
	private String infracPagada;
	@Column(name = "INFRAC_GPS_LAT")
	private BigDecimal infracGPSLat;
	@Column(name = "INFRAC_GPS_LON")
	private BigDecimal infracGPSLon;
	@Column(name = "INFRAC_G_EN_LA_CALLE")
	private String infracGCalle;
	@Column(name = "INFRAC_G_ENTRE_CALLE")
	private String infracGEntreCalle;
	@Column(name = "INFRAC_G_Y_LA_CALLE")
	private String infracGYCalle;
	@Column(name = "INFRAC_G_COLONIA")
	private String infraGColonia;
	@Column(name = "INFRAC_OBSERVACION")
	private String infraObs;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	@Column(name = "AUTORIZADO_POR")
	private Long autPor;
	@Column(name = "FECHA_AUTORIZACION")
	private Date fechaAut;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "INFRAC_STATUS")
	private String infracStatus;
	@Column(name = "STATUS_PROCESO")
	private String statusProceso;
	@Column(name = "TIPO_PROCESO")
	private String tipoProceso;
	@Column(name = "INFRAC_CAPTURA")
	private String infracCaptura;
	@Column(name = "INFRAC_LINEA_CAP")
	private String infracLC;
	@Column(name = "EST_IMV_ID")
	private Long estImvId;
	@Column(name = "INFRAC_APOYO_GRUA")
	private Long infracApoyoGrua;
	@Column(name = "ESTATUS_CANCELACION")
	private String estCancel;
	@Column(name = "AUTORIZACION_CANCELACION")
	private String autCancel;
	@Column(name = "FECHA_CANCELACION")
	private Date fechaCancel;
	@Column(name = "USUARIO_CANCELA")
	private Long usuarioCancel;
	@Column(name = "USUARIO_AUTORIZA")
	private Long usuarioAut;
	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	@Column(name = "INFRAC_LINEA_CAP_VIGENCIA")
	private Date lineaCapVig;
	@Column(name = "INFRAC_LINEA_CAP_MONTO")
	private Integer lineaCapMonto;
	@Column(name = "MOTIVO_CAMBIO")
	private String motCambio;
	@Column(name = "CON_DIRECCION")
	private String conDirecc;
	@Column(name = "FRENTE_AL_NUM")
	private String frenteAlNum;
	@Column(name = "OBSERVE_QUE")
	private String ObserveQue;
	@Column(name = "PAGO_TIPO")
	private String pagoTipo;
	
	@Formula("substr(INFRAC_NUM,1,2)")
	private String radarInfraccion;
	
	public String getRadarInfraccion() {
		return radarInfraccion;
	}
	public void setRadarInfraccion(String radarInfraccion) {
		this.radarInfraccion = radarInfraccion;
	}
	
	public String getInfraccNum() {
		return infraccNum;
	}
	public void setInfraccNum(String infraccNum) {
		this.infraccNum = infraccNum;
	}
	public Integer getInfraccAAA() {
		return infraccAAA;
	}
	public void setInfraccAAA(Integer infraccAAA) {
		this.infraccAAA = infraccAAA;
	}
	public Integer getInfraccAAAMMM() {
		return infraccAAAMMM;
	}
	public void setInfraccAAAMMM(Integer infraccAAAMMM) {
		this.infraccAAAMMM = infraccAAAMMM;
	}
	public String getInfraccOrigen() {
		return infraccOrigen;
	}
	public void setInfraccOrigen(String infraccOrigen) {
		this.infraccOrigen = infraccOrigen;
	}
	public Integer getInfraccFolio() {
		return infraccFolio;
	}
	public void setInfraccFolio(Integer infraccFolio) {
		this.infraccFolio = infraccFolio;
	}
	public String getInfraccImpresa() {
		return infraccImpresa;
	}
	public void setInfraccImpresa(String infraccImpresa) {
		this.infraccImpresa = infraccImpresa;
	}
	public String getInfraccNumCtrl() {
		return infraccNumCtrl;
	}
	public void setInfraccNumCtrl(String infraccNumCtrl) {
		this.infraccNumCtrl = infraccNumCtrl;
	}
	public SectorDTO getSector() {
		return sector;
	}
	public void setSector(SectorDTO sector) {
		this.sector = sector;
	}
	public Long getUtId() {
		return utId;
	}
	public void setUtId(Long utId) {
		this.utId = utId;
	}
	public String getInfraccConPlaca() {
		return infraccConPlaca;
	}
	public void setInfraccConPlaca(String infraccConPlaca) {
		this.infraccConPlaca = infraccConPlaca;
	}
	public EstadoDTO getInfraccPlacaEdo() {
		return infraccPlacaEdo;
	}
	public void setInfraccPlacaEdo(EstadoDTO infraccPlacaEdo) {
		this.infraccPlacaEdo = infraccPlacaEdo;
	}
	public String getInfraccPlaca() {
		return infraccPlaca;
	}
	public void setInfraccPlaca(String infraccPlaca) {
		this.infraccPlaca = infraccPlaca;
	}
	public String getInfIPaterno() {
		return infIPaterno;
	}
	public void setInfIPaterno(String infIPaterno) {
		this.infIPaterno = infIPaterno;
	}
	public String getInfIMaterno() {
		return infIMaterno;
	}
	public void setInfIMaterno(String infIMaterno) {
		this.infIMaterno = infIMaterno;
	}
	public String getInfINombre() {
		return infINombre;
	}
	public void setInfINombre(String infINombre) {
		this.infINombre = infINombre;
	}
	public String getInfIRFC() {
		return infIRFC;
	}
	public void setInfIRFC(String infIRFC) {
		this.infIRFC = infIRFC;
	}
	public String getInfICalle() {
		return infICalle;
	}
	public void setInfICalle(String infICalle) {
		this.infICalle = infICalle;
	}
	public String getInfINumExt() {
		return infINumExt;
	}
	public void setInfINumExt(String infINumExt) {
		this.infINumExt = infINumExt;
	}
	public String getInfINumInt() {
		return infINumInt;
	}
	public void setInfINumInt(String infINumInt) {
		this.infINumInt = infINumInt;
	}
	public String getInfIColonia() {
		return infIColonia;
	}
	public void setInfIColonia(String infIColonia) {
		this.infIColonia = infIColonia;
	}
	public DelegacionDTO getInfraccIDelEdo() {
		return infraccIDelEdo;
	}
	public void setInfraccIDelEdo(DelegacionDTO infraccIDelEdo) {
		this.infraccIDelEdo = infraccIDelEdo;
	}
	public String getInfraccLicencia() {
		return infraccLicencia;
	}
	public void setInfraccLicencia(String infraccLicencia) {
		this.infraccLicencia = infraccLicencia;
	}
	public TipoLicenciaDTO getTipoL() {
		return tipoL;
	}
	public void setTipoL(TipoLicenciaDTO tipoL) {
		this.tipoL = tipoL;
	}
	public String getInfraccLServPub() {
		return infraccLServPub;
	}
	public void setInfraccLServPub(String infraccLServPub) {
		this.infraccLServPub = infraccLServPub;
	}
	public EstadoDTO getInfraccLicEdo() {
		return infraccLicEdo;
	}
	public void setInfraccLicEdo(EstadoDTO infraccLicEdo) {
		this.infraccLicEdo = infraccLicEdo;
	}
	public VehiculoModeloDTO getvModMar() {
		return vModMar;
	}
	public void setvModMar(VehiculoModeloDTO vModMar) {
		this.vModMar = vModMar;
	}
	public VehiculoColorDTO getvColor() {
		return vColor;
	}
	public void setvColor(VehiculoColorDTO vColor) {
		this.vColor = vColor;
	}
	public String getvOrigen() {
		return vOrigen;
	}
	public void setvOrigen(String vOrigen) {
		this.vOrigen = vOrigen;
	}
	public String getvTarCircul() {
		return vTarCircul;
	}
	public void setvTarCircul(String vTarCircul) {
		this.vTarCircul = vTarCircul;
	}
	public VehiculoTipoDTO getvTipo() {
		return vTipo;
	}
	public void setvTipo(VehiculoTipoDTO vTipo) {
		this.vTipo = vTipo;
	}
	public String getArtMot() {
		return artMot;
	}
	public void setArtMot(String artMot) {
		this.artMot = artMot;
	}
	public String getInfracMCalle() {
		return infracMCalle;
	}
	public void setInfracMCalle(String infracMCalle) {
		this.infracMCalle = infracMCalle;
	}
	public String getInfracMEntreCalle() {
		return infracMEntreCalle;
	}
	public void setInfracMEntreCalle(String infracMEntreCalle) {
		this.infracMEntreCalle = infracMEntreCalle;
	}
	public String getInfracMYCalle() {
		return infracMYCalle;
	}
	public void setInfracMYCalle(String infracMYCalle) {
		this.infracMYCalle = infracMYCalle;
	}
	public String getInfracMColonia() {
		return infracMColonia;
	}
	public void setInfracMColonia(String infracMColonia) {
		this.infracMColonia = infracMColonia;
	}
	public DelegacionDTO getInfracMDelEdo() {
		return infracMDelEdo;
	}
	public void setInfracMDelEdo(DelegacionDTO infracMDelEdo) {
		this.infracMDelEdo = infracMDelEdo;
	}
	public Timestamp getInfracMFechaHora() {
		return infracMFechaHora;
	}
	public void setInfracMFechaHora(Timestamp infracMFechaHora) {
		this.infracMFechaHora = infracMFechaHora;
	}
	public ArticuloDTO getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
	public String getInfracLeyTrans() {
		return infracLeyTrans;
	}
	public void setInfracLeyTrans(String infracLeyTrans) {
		this.infracLeyTrans = infracLeyTrans;
	}
	public Long getSancArt() {
		return sancArt;
	}
	public void setSancArt(Long sancArt) {
		this.sancArt = sancArt;
	}
	public String getInfracArrastre() {
		return infracArrastre;
	}
	public void setInfracArrastre(String infracArrastre) {
		this.infracArrastre = infracArrastre;
	}
	public String getInfracNumArrastre() {
		return infracNumArrastre;
	}
	public void setInfracNumArrastre(String infracNumArrastre) {
		this.infracNumArrastre = infracNumArrastre;
	}
	public String getInfracTipoArrastre() {
		return infracTipoArrastre;
	}
	public void setInfracTipoArrastre(String infracTipoArrastre) {
		this.infracTipoArrastre = infracTipoArrastre;
	}
	public GruaDTO getGrua() {
		return grua;
	}
	public void setGrua(GruaDTO grua) {
		this.grua = grua;
	}
	public Long getDepId() {
		return depId;
	}
	public void setDepId(Long depId) {
		this.depId = depId;
	}
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public Long getEmpSector() {
		return empSector;
	}
	public void setEmpSector(Long empSector) {
		this.empSector = empSector;
	}
	public Long getEmpAgrup() {
		return empAgrup;
	}
	public void setEmpAgrup(Long empAgrup) {
		this.empAgrup = empAgrup;
	}
	public ResponsableVehiculoDTO getResponsableVehiculo() {
		return responsableVehiculo;
	}
	public void setResponsableVehiculo(ResponsableVehiculoDTO responsableVehiculo) {
		this.responsableVehiculo = responsableVehiculo;
	}
	public Long getEvenId() {
		return evenId;
	}
	public void setEvenId(Long evenId) {
		this.evenId = evenId;
	}
	public Long getEstatId() {
		return estatId;
	}
	public void setEstatId(Long estatId) {
		this.estatId = estatId;
	}
	public String getInfracPagada() {
		return infracPagada;
	}
	public void setInfracPagada(String infracPagada) {
		this.infracPagada = infracPagada;
	}
	public BigDecimal getInfracGPSLat() {
		return infracGPSLat;
	}
	public void setInfracGPSLat(BigDecimal infracGPSLat) {
		this.infracGPSLat = infracGPSLat;
	}
	public BigDecimal getInfracGPSLon() {
		return infracGPSLon;
	}
	public void setInfracGPSLon(BigDecimal infracGPSLon) {
		this.infracGPSLon = infracGPSLon;
	}
	public String getInfracGCalle() {
		return infracGCalle;
	}
	public void setInfracGCalle(String infracGCalle) {
		this.infracGCalle = infracGCalle;
	}
	public String getInfracGEntreCalle() {
		return infracGEntreCalle;
	}
	public void setInfracGEntreCalle(String infracGEntreCalle) {
		this.infracGEntreCalle = infracGEntreCalle;
	}
	public String getInfracGYCalle() {
		return infracGYCalle;
	}
	public void setInfracGYCalle(String infracGYCalle) {
		this.infracGYCalle = infracGYCalle;
	}
	public String getInfraGColonia() {
		return infraGColonia;
	}
	public void setInfraGColonia(String infraGColonia) {
		this.infraGColonia = infraGColonia;
	}
	public String getInfraObs() {
		return infraObs;
	}
	public void setInfraObs(String infraObs) {
		this.infraObs = infraObs;
	}
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Long getModPor() {
		return modPor;
	}
	public void setModPor(Long modPor) {
		this.modPor = modPor;
	}
	public Date getUltimaMod() {
		return ultimaMod;
	}
	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}
	public Long getAutPor() {
		return autPor;
	}
	public void setAutPor(Long autPor) {
		this.autPor = autPor;
	}
	public Date getFechaAut() {
		return fechaAut;
	}
	public void setFechaAut(Date fechaAut) {
		this.fechaAut = fechaAut;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getInfracStatus() {
		return infracStatus;
	}
	public void setInfracStatus(String infracStatus) {
		this.infracStatus = infracStatus;
	}
	public String getStatusProceso() {
		return statusProceso;
	}
	public void setStatusProceso(String statusProceso) {
		this.statusProceso = statusProceso;
	}
	public String getTipoProceso() {
		return tipoProceso;
	}
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	public String getInfracCaptura() {
		return infracCaptura;
	}
	public void setInfracCaptura(String infracCaptura) {
		this.infracCaptura = infracCaptura;
	}
	public String getInfracLC() {
		return infracLC;
	}
	public void setInfracLC(String infracLC) {
		this.infracLC = infracLC;
	}
	public Long getEstImvId() {
		return estImvId;
	}
	public void setEstImvId(Long estImvId) {
		this.estImvId = estImvId;
	}
	public Long getInfracApoyoGrua() {
		return infracApoyoGrua;
	}
	public void setInfracApoyoGrua(Long infracApoyoGrua) {
		this.infracApoyoGrua = infracApoyoGrua;
	}
	public String getEstCancel() {
		return estCancel;
	}
	public void setEstCancel(String estCancel) {
		this.estCancel = estCancel;
	}
	public String getAutCancel() {
		return autCancel;
	}
	public void setAutCancel(String autCancel) {
		this.autCancel = autCancel;
	}
	public Date getFechaCancel() {
		return fechaCancel;
	}
	public void setFechaCancel(Date fechaCancel) {
		this.fechaCancel = fechaCancel;
	}
	public Long getUsuarioCancel() {
		return usuarioCancel;
	}
	public void setUsuarioCancel(Long usuarioCancel) {
		this.usuarioCancel = usuarioCancel;
	}
	public Long getUsuarioAut() {
		return usuarioAut;
	}
	public void setUsuarioAut(Long usuarioAut) {
		this.usuarioAut = usuarioAut;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getLineaCapVig() {
		return lineaCapVig;
	}
	public void setLineaCapVig(Date lineaCapVig) {
		this.lineaCapVig = lineaCapVig;
	}
	public Integer getLineaCapMonto() {
		return lineaCapMonto;
	}
	public void setLineaCapMonto(Integer lineaCapMonto) {
		this.lineaCapMonto = lineaCapMonto;
	}
	public String getMotCambio() {
		return motCambio;
	}
	public void setMotCambio(String motCambio) {
		this.motCambio = motCambio;
	}
	public String getConDirecc() {
		return conDirecc;
	}
	public void setConDirecc(String conDirecc) {
		this.conDirecc = conDirecc;
	}
	public String getFrenteAlNum() {
		return frenteAlNum;
	}
	public void setFrenteAlNum(String frenteAlNum) {
		this.frenteAlNum = frenteAlNum;
	}
	public String getObserveQue() {
		return ObserveQue;
	}
	public void setObserveQue(String observeQue) {
		ObserveQue = observeQue;
	}
	public String getPagoTipo() {
		return pagoTipo;
	}
	public void setPagoTipo(String pagoTipo) {
		this.pagoTipo = pagoTipo;
	}
}
