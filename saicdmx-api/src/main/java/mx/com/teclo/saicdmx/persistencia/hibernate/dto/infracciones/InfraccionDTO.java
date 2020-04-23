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

import org.codehaus.jackson.annotate.JsonIgnore;
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
@Table(name="INFRACCIONES")
public class InfraccionDTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8237858451424986526L;
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
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "SEC_ID")
	private SectorDTO sector;
	@Column(name = "UT_ID")
	private Long utId;
	@Column(name = "INFRAC_CON_PLACA")
	private String infraccConPlaca;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "INFRAC_PLACA_EDO",nullable = true)
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
	@Column(name = "MOTIVO_CAMBIO")
	private String motCambio;
//Se comenta por problemas con garantia Recepcion,Entrega y Consulta	
//	@Column(name = "LINEA_CAPT_VIGENCIA")
//	private Date lineaCapVig;
//	@Column(name = "INFRAC_I_CURP")
//	private String infracICurp;
//	@Column(name = "INFRAC_I_COD_POSTAL")
//	private String infracICP;
	@Column(name = "CON_DIRECCION")
	private String conDirecc;
	@Column(name = "FRENTE_AL_NUM")
	private String frenteAlNum;
	@Column(name = "OBSERVE_QUE")
	private String ObserveQue;
	
	@Formula("substr(INFRAC_NUM,1,2)")
	private String radarInfraccion;
	
	public String getRadarInfraccion() {
		return radarInfraccion;
	}
	public void setRadarInfraccion(String radarInfraccion) {
		this.radarInfraccion = radarInfraccion;
	}
	/**
	 * @return the infraccNum
	 */
	public String getInfraccNum() {
		return infraccNum;
	}
	/**
	 * @param infraccNum the infraccNum to set
	 */
	public void setInfraccNum(String infraccNum) {
		this.infraccNum = infraccNum;
	}
	/**
	 * @return the infraccAAA
	 */
	public Integer getInfraccAAA() {
		return infraccAAA;
	}
	/**
	 * @param infraccAAA the infraccAAA to set
	 */
	public void setInfraccAAA(Integer infraccAAA) {
		this.infraccAAA = infraccAAA;
	}
	/**
	 * @return the infraccAAAMMM
	 */
	public Integer getInfraccAAAMMM() {
		return infraccAAAMMM;
	}
	/**
	 * @param infraccAAAMMM the infraccAAAMMM to set
	 */
	public void setInfraccAAAMMM(Integer infraccAAAMMM) {
		this.infraccAAAMMM = infraccAAAMMM;
	}
	/**
	 * @return the infraccOrigen
	 */
	public String getInfraccOrigen() {
		return infraccOrigen;
	}
	/**
	 * @param infraccOrigen the infraccOrigen to set
	 */
	public void setInfraccOrigen(String infraccOrigen) {
		this.infraccOrigen = infraccOrigen;
	}
	/**
	 * @return the infraccFolio
	 */
	public Integer getInfraccFolio() {
		return infraccFolio;
	}
	/**
	 * @param infraccFolio the infraccFolio to set
	 */
	public void setInfraccFolio(Integer infraccFolio) {
		this.infraccFolio = infraccFolio;
	}
	/**
	 * @return the infraccImpresa
	 */
	public String getInfraccImpresa() {
		return infraccImpresa;
	}
	/**
	 * @param infraccImpresa the infraccImpresa to set
	 */
	public void setInfraccImpresa(String infraccImpresa) {
		this.infraccImpresa = infraccImpresa;
	}
	/**
	 * @return the infraccNumCtrl
	 */
	public String getInfraccNumCtrl() {
		return infraccNumCtrl;
	}
	/**
	 * @param infraccNumCtrl the infraccNumCtrl to set
	 */
	public void setInfraccNumCtrl(String infraccNumCtrl) {
		this.infraccNumCtrl = infraccNumCtrl;
	}
	/**
	 * @return the sector
	 */
	public SectorDTO getSector() {
		return sector;
	}
	/**
	 * @param sector the sector to set
	 */
	public void setSector(SectorDTO sector) {
		this.sector = sector;
	}
	/**
	 * @return the utId
	 */
	public Long getUtId() {
		return utId;
	}
	/**
	 * @param utId the utId to set
	 */
	public void setUtId(Long utId) {
		this.utId = utId;
	}
	/**
	 * @return the infraccConPlaca
	 */
	public String getInfraccConPlaca() {
		return infraccConPlaca;
	}
	/**
	 * @param infraccConPlaca the infraccConPlaca to set
	 */
	public void setInfraccConPlaca(String infraccConPlaca) {
		this.infraccConPlaca = infraccConPlaca;
	}
	/**
	 * @return the infraccPlacaEdo
	 */
	public EstadoDTO getInfraccPlacaEdo() {
		return infraccPlacaEdo;
	}
	/**
	 * @param infraccPlacaEdo the infraccPlacaEdo to set
	 */
	public void setInfraccPlacaEdo(EstadoDTO infraccPlacaEdo) {
		this.infraccPlacaEdo = infraccPlacaEdo;
	}
	/**
	 * @return the infraccPlaca
	 */
	public String getInfraccPlaca() {
		return infraccPlaca;
	}
	/**
	 * @param infraccPlaca the infraccPlaca to set
	 */
	public void setInfraccPlaca(String infraccPlaca) {
		this.infraccPlaca = infraccPlaca;
	}
	/**
	 * @return the infIPaterno
	 */
	public String getInfIPaterno() {
		return infIPaterno;
	}
	/**
	 * @param infIPaterno the infIPaterno to set
	 */
	public void setInfIPaterno(String infIPaterno) {
		this.infIPaterno = infIPaterno;
	}
	/**
	 * @return the infIMaterno
	 */
	public String getInfIMaterno() {
		return infIMaterno;
	}
	/**
	 * @param infIMaterno the infIMaterno to set
	 */
	public void setInfIMaterno(String infIMaterno) {
		this.infIMaterno = infIMaterno;
	}
	/**
	 * @return the infINombre
	 */
	public String getInfINombre() {
		return infINombre;
	}
	/**
	 * @param infINombre the infINombre to set
	 */
	public void setInfINombre(String infINombre) {
		this.infINombre = infINombre;
	}
	/**
	 * @return the infIRFC
	 */
	public String getInfIRFC() {
		return infIRFC;
	}
	/**
	 * @param infIRFC the infIRFC to set
	 */
	public void setInfIRFC(String infIRFC) {
		this.infIRFC = infIRFC;
	}
	/**
	 * @return the infICalle
	 */
	public String getInfICalle() {
		return infICalle;
	}
	/**
	 * @param infICalle the infICalle to set
	 */
	public void setInfICalle(String infICalle) {
		this.infICalle = infICalle;
	}
	/**
	 * @return the infINumExt
	 */
	public String getInfINumExt() {
		return infINumExt;
	}
	/**
	 * @param infINumExt the infINumExt to set
	 */
	public void setInfINumExt(String infINumExt) {
		this.infINumExt = infINumExt;
	}
	/**
	 * @return the infINumInt
	 */
	public String getInfINumInt() {
		return infINumInt;
	}
	/**
	 * @param infINumInt the infINumInt to set
	 */
	public void setInfINumInt(String infINumInt) {
		this.infINumInt = infINumInt;
	}
	/**
	 * @return the infIColonia
	 */
	public String getInfIColonia() {
		return infIColonia;
	}
	/**
	 * @param infIColonia the infIColonia to set
	 */
	public void setInfIColonia(String infIColonia) {
		this.infIColonia = infIColonia;
	}
	/**
	 * @return the infraccIDelEdo
	 */
	public DelegacionDTO getInfraccIDelEdo() {
		return infraccIDelEdo;
	}
	/**
	 * @param infraccIDelEdo the infraccIDelEdo to set
	 */
	public void setInfraccIDelEdo(DelegacionDTO infraccIDelEdo) {
		this.infraccIDelEdo = infraccIDelEdo;
	}
	/**
	 * @return the infraccLicencia
	 */
	public String getInfraccLicencia() {
		return infraccLicencia;
	}
	/**
	 * @param infraccLicencia the infraccLicencia to set
	 */
	public void setInfraccLicencia(String infraccLicencia) {
		this.infraccLicencia = infraccLicencia;
	}
	/**
	 * @return the tipoL
	 */
	public TipoLicenciaDTO getTipoL() {
		return tipoL;
	}
	/**
	 * @param tipoL the tipoL to set
	 */
	public void setTipoL(TipoLicenciaDTO tipoL) {
		this.tipoL = tipoL;
	}
	/**
	 * @return the infraccLServPub
	 */
	public String getInfraccLServPub() {
		return infraccLServPub;
	}
	/**
	 * @param infraccLServPub the infraccLServPub to set
	 */
	public void setInfraccLServPub(String infraccLServPub) {
		this.infraccLServPub = infraccLServPub;
	}
	/**
	 * @return the infraccLicEdo
	 */
	public EstadoDTO getInfraccLicEdo() {
		return infraccLicEdo;
	}
	/**
	 * @param infraccLicEdo the infraccLicEdo to set
	 */
	public void setInfraccLicEdo(EstadoDTO infraccLicEdo) {
		this.infraccLicEdo = infraccLicEdo;
	}
	/**
	 * @return the vModMar
	 */
	public VehiculoModeloDTO getvModMar() {
		return vModMar;
	}
	/**
	 * @param vModMar the vModMar to set
	 */
	public void setvModMar(VehiculoModeloDTO vModMar) {
		this.vModMar = vModMar;
	}
	/**
	 * @return the vColor
	 */
	public VehiculoColorDTO getvColor() {
		return vColor;
	}
	/**
	 * @param vColor the vColor to set
	 */
	public void setvColor(VehiculoColorDTO vColor) {
		this.vColor = vColor;
	}
	/**
	 * @return the vOrigen
	 */
	public String getvOrigen() {
		return vOrigen;
	}
	/**
	 * @param vOrigen the vOrigen to set
	 */
	public void setvOrigen(String vOrigen) {
		this.vOrigen = vOrigen;
	}
	/**
	 * @return the vTarCircul
	 */
	public String getvTarCircul() {
		return vTarCircul;
	}
	/**
	 * @param vTarCircul the vTarCircul to set
	 */
	public void setvTarCircul(String vTarCircul) {
		this.vTarCircul = vTarCircul;
	}
	/**
	 * @return the vTipo
	 */
	public VehiculoTipoDTO getvTipo() {
		return vTipo;
	}
	/**
	 * @param vTipo the vTipo to set
	 */
	public void setvTipo(VehiculoTipoDTO vTipo) {
		this.vTipo = vTipo;
	}
	/**
	 * @return the artMot
	 */
	public String getArtMot() {
		return artMot;
	}
	/**
	 * @param artMot the artMot to set
	 */
	public void setArtMot(String artMot) {
		this.artMot = artMot;
	}
	/**
	 * @return the infracMCalle
	 */
	public String getInfracMCalle() {
		return infracMCalle;
	}
	/**
	 * @param infracMCalle the infracMCalle to set
	 */
	public void setInfracMCalle(String infracMCalle) {
		this.infracMCalle = infracMCalle;
	}
	/**
	 * @return the infracMEntreCalle
	 */
	public String getInfracMEntreCalle() {
		return infracMEntreCalle;
	}
	/**
	 * @param infracMEntreCalle the infracMEntreCalle to set
	 */
	public void setInfracMEntreCalle(String infracMEntreCalle) {
		this.infracMEntreCalle = infracMEntreCalle;
	}
	/**
	 * @return the infracMYCalle
	 */
	public String getInfracMYCalle() {
		return infracMYCalle;
	}
	/**
	 * @param infracMYCalle the infracMYCalle to set
	 */
	public void setInfracMYCalle(String infracMYCalle) {
		this.infracMYCalle = infracMYCalle;
	}
	/**
	 * @return the infracMColonia
	 */
	public String getInfracMColonia() {
		return infracMColonia;
	}
	/**
	 * @param infracMColonia the infracMColonia to set
	 */
	public void setInfracMColonia(String infracMColonia) {
		this.infracMColonia = infracMColonia;
	}
	/**
	 * @return the infracMDelEdo
	 */
	public DelegacionDTO getInfracMDelEdo() {
		return infracMDelEdo;
	}
	/**
	 * @param infracMDelEdo the infracMDelEdo to set
	 */
	public void setInfracMDelEdo(DelegacionDTO infracMDelEdo) {
		this.infracMDelEdo = infracMDelEdo;
	}
	/**
	 * @return the infracMFechaHora
	 */
	public Timestamp getInfracMFechaHora() {
		return infracMFechaHora;
	}
	/**
	 * @param infracMFechaHora the infracMFechaHora to set
	 */
	public void setInfracMFechaHora(Timestamp infracMFechaHora) {
		this.infracMFechaHora = infracMFechaHora;
	}
	/**
	 * @return the articulo
	 */
	public ArticuloDTO getArticulo() {
		return articulo;
	}
	/**
	 * @param articulo the articulo to set
	 */
	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
	/**
	 * @return the infracLeyTrans
	 */
	public String getInfracLeyTrans() {
		return infracLeyTrans;
	}
	/**
	 * @param infracLeyTrans the infracLeyTrans to set
	 */
	public void setInfracLeyTrans(String infracLeyTrans) {
		this.infracLeyTrans = infracLeyTrans;
	}
	/**
	 * @return the infracArrastre
	 */
	public String getInfracArrastre() {
		return infracArrastre;
	}
	/**
	 * @param infracArrastre the infracArrastre to set
	 */
	public void setInfracArrastre(String infracArrastre) {
		this.infracArrastre = infracArrastre;
	}
	/**
	 * @return the infracNumArrastre
	 */
	public String getInfracNumArrastre() {
		return infracNumArrastre;
	}
	/**
	 * @param infracNumArrastre the infracNumArrastre to set
	 */
	public void setInfracNumArrastre(String infracNumArrastre) {
		this.infracNumArrastre = infracNumArrastre;
	}
	/**
	 * @return the infracTipoArrastre
	 */
	public String getInfracTipoArrastre() {
		return infracTipoArrastre;
	}
	/**
	 * @param infracTipoArrastre the infracTipoArrastre to set
	 */
	public void setInfracTipoArrastre(String infracTipoArrastre) {
		this.infracTipoArrastre = infracTipoArrastre;
	}
	/**
	 * @return the grua
	 */
	public GruaDTO getGrua() {
		return grua;
	}
	/**
	 * @param grua the grua to set
	 */
	public void setGrua(GruaDTO grua) {
		this.grua = grua;
	}
	/**
	 * @return the depId
	 */
	public Long getDepId() {
		return depId;
	}
	/**
	 * @param depId the depId to set
	 */
	public void setDepId(Long depId) {
		this.depId = depId;
	}
	/**
	 * @return the empId
	 */
	public Long getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	/**
	 * @return the empSector
	 */
	public Long getEmpSector() {
		return empSector;
	}
	/**
	 * @param empSector the empSector to set
	 */
	public void setEmpSector(Long empSector) {
		this.empSector = empSector;
	}
	/**
	 * @return the empAgrup
	 */
	public Long getEmpAgrup() {
		return empAgrup;
	}
	/**
	 * @param empAgrup the empAgrup to set
	 */
	public void setEmpAgrup(Long empAgrup) {
		this.empAgrup = empAgrup;
	}
	
	public ResponsableVehiculoDTO getResponsableVehiculo() 
	{
		return responsableVehiculo;
	}
	
	public void setResponsableVehiculo(ResponsableVehiculoDTO responsableVehiculo) 
	{
		this.responsableVehiculo = responsableVehiculo;
	}
	
	/**
	 * @return the evenId
	 */
	public Long getEvenId() {
		return evenId;
	}
	/**
	 * @param evenId the evenId to set
	 */
	public void setEvenId(Long evenId) {
		this.evenId = evenId;
	}
	/**
	 * @return the estatId
	 */
	public Long getEstatId() {
		return estatId;
	}
	/**
	 * @param estatId the estatId to set
	 */
	public void setEstatId(Long estatId) {
		this.estatId = estatId;
	}
	/**
	 * @return the infracPagada
	 */
	public String getInfracPagada() {
		return infracPagada;
	}
	/**
	 * @param infracPagada the infracPagada to set
	 */
	public void setInfracPagada(String infracPagada) {
		this.infracPagada = infracPagada;
	}
	/**
	 * @return the infracGPSLat
	 */
	public BigDecimal getInfracGPSLat() {
		return infracGPSLat;
	}
	/**
	 * @param infracGPSLat the infracGPSLat to set
	 */
	public void setInfracGPSLat(BigDecimal infracGPSLat) {
		this.infracGPSLat = infracGPSLat;
	}
	/**
	 * @return the infracGPSLon
	 */
	public BigDecimal getInfracGPSLon() {
		return infracGPSLon;
	}
	/**
	 * @param infracGPSLon the infracGPSLon to set
	 */
	public void setInfracGPSLon(BigDecimal infracGPSLon) {
		this.infracGPSLon = infracGPSLon;
	}
	/**
	 * @return the infracGCalle
	 */
	public String getInfracGCalle() {
		return infracGCalle;
	}
	/**
	 * @param infracGCalle the infracGCalle to set
	 */
	public void setInfracGCalle(String infracGCalle) {
		this.infracGCalle = infracGCalle;
	}
	/**
	 * @return the infracGEntreCalle
	 */
	public String getInfracGEntreCalle() {
		return infracGEntreCalle;
	}
	/**
	 * @param infracGEntreCalle the infracGEntreCalle to set
	 */
	public void setInfracGEntreCalle(String infracGEntreCalle) {
		this.infracGEntreCalle = infracGEntreCalle;
	}
	/**
	 * @return the infracGYCalle
	 */
	public String getInfracGYCalle() {
		return infracGYCalle;
	}
	/**
	 * @param infracGYCalle the infracGYCalle to set
	 */
	public void setInfracGYCalle(String infracGYCalle) {
		this.infracGYCalle = infracGYCalle;
	}
	/**
	 * @return the infraGColonia
	 */
	public String getInfraGColonia() {
		return infraGColonia;
	}
	/**
	 * @param infraGColonia the infraGColonia to set
	 */
	public void setInfraGColonia(String infraGColonia) {
		this.infraGColonia = infraGColonia;
	}
	/**
	 * @return the infraObs
	 */
	public String getInfraObs() {
		return infraObs;
	}
	/**
	 * @param infraObs the infraObs to set
	 */
	public void setInfraObs(String infraObs) {
		this.infraObs = infraObs;
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
	 * @return the modPor
	 */
	public Long getModPor() {
		return modPor;
	}
	/**
	 * @param modPor the modPor to set
	 */
	public void setModPor(Long modPor) {
		this.modPor = modPor;
	}
	/**
	 * @return the ultimaMod
	 */
	public Date getUltimaMod() {
		return ultimaMod;
	}
	/**
	 * @param ultimaMod the ultimaMod to set
	 */
	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}
	/**
	 * @return the autPor
	 */
	public Long getAutPor() {
		return autPor;
	}
	/**
	 * @param autPor the autPor to set
	 */
	public void setAutPor(Long autPor) {
		this.autPor = autPor;
	}
	/**
	 * @return the fechaAut
	 */
	public Date getFechaAut() {
		return fechaAut;
	}
	/**
	 * @param fechaAut the fechaAut to set
	 */
	public void setFechaAut(Date fechaAut) {
		this.fechaAut = fechaAut;
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
	 * @return the infracStatus
	 */
	public String getInfracStatus() {
		return infracStatus;
	}
	/**
	 * @param infracStatus the infracStatus to set
	 */
	public void setInfracStatus(String infracStatus) {
		this.infracStatus = infracStatus;
	}
	/**
	 * @return the statusProceso
	 */
	public String getStatusProceso() {
		return statusProceso;
	}
	/**
	 * @param statusProceso the statusProceso to set
	 */
	public void setStatusProceso(String statusProceso) {
		this.statusProceso = statusProceso;
	}
	/**
	 * @return the tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}
	/**
	 * @param tipoProceso the tipoProceso to set
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	/**
	 * @return the infracCaptura
	 */
	public String getInfracCaptura() {
		return infracCaptura;
	}
	/**
	 * @param infracCaptura the infracCaptura to set
	 */
	public void setInfracCaptura(String infracCaptura) {
		this.infracCaptura = infracCaptura;
	}
	/**
	 * @return the infracLC
	 */
	public String getInfracLC() {
		return infracLC;
	}
	/**
	 * @param infracLC the infracLC to set
	 */
	public void setInfracLC(String infracLC) {
		this.infracLC = infracLC;
	}
	/**
	 * @return the estImvId
	 */
	public Long getEstImvId() {
		return estImvId;
	}
	/**
	 * @param estImvId the estImvId to set
	 */
	public void setEstImvId(Long estImvId) {
		this.estImvId = estImvId;
	}
	/**
	 * @return the infracApoyoGrua
	 */
	public Long getInfracApoyoGrua() {
		return infracApoyoGrua;
	}
	/**
	 * @param infracApoyoGrua the infracApoyoGrua to set
	 */
	public void setInfracApoyoGrua(Long infracApoyoGrua) {
		this.infracApoyoGrua = infracApoyoGrua;
	}
	/**
	 * @return the estCancel
	 */
	public String getEstCancel() {
		return estCancel;
	}
	/**
	 * @param estCancel the estCancel to set
	 */
	public void setEstCancel(String estCancel) {
		this.estCancel = estCancel;
	}
	/**
	 * @return the autCancel
	 */
	public String getAutCancel() {
		return autCancel;
	}
	/**
	 * @param autCancel the autCancel to set
	 */
	public void setAutCancel(String autCancel) {
		this.autCancel = autCancel;
	}
	/**
	 * @return the fechaCancel
	 */
	public Date getFechaCancel() {
		return fechaCancel;
	}
	/**
	 * @param fechaCancel the fechaCancel to set
	 */
	public void setFechaCancel(Date fechaCancel) {
		this.fechaCancel = fechaCancel;
	}
	/**
	 * @return the usuarioCancel
	 */
	public Long getUsuarioCancel() {
		return usuarioCancel;
	}
	/**
	 * @param usuarioCancel the usuarioCancel to set
	 */
	public void setUsuarioCancel(Long usuarioCancel) {
		this.usuarioCancel = usuarioCancel;
	}
	/**
	 * @return the usuarioAut
	 */
	public Long getUsuarioAut() {
		return usuarioAut;
	}
	/**
	 * @param usuarioAut the usuarioAut to set
	 */
	public void setUsuarioAut(Long usuarioAut) {
		this.usuarioAut = usuarioAut;
	}
	/**
	 * @return the motCambio
	 */
	public String getMotCambio() {
		return motCambio;
	}
	/**
	 * @param motCambio the motCambio to set
	 */
	public void setMotCambio(String motCambio) {
		this.motCambio = motCambio;
	}
	/**
	 * @return the lineaCapVig
	 */
//	public Date getLineaCapVig() {
//		return lineaCapVig;
//	}
//	/**
//	 * @param lineaCapVig the lineaCapVig to set
//	 */
//	public void setLineaCapVig(Date lineaCapVig) {
//		this.lineaCapVig = lineaCapVig;
//	}
	/**
	 * @return the infracICurp
	 */
//	public String getInfracICurp() {
//		return infracICurp;
//	}
//	/**
//	 * @param infracICurp the infracICurp to set
//	 */
//	public void setInfracICurp(String infracICurp) {
//		this.infracICurp = infracICurp;
//	}
//	/**
//	 * @return the infracICP
//	 */
//	public String getInfracICP() {
//		return infracICP;
//	}
//	/**
//	 * @param infracICP the infracICP to set
//	 */
//	public void setInfracICP(String infracICP) {
//		this.infracICP = infracICP;
//	}
	/**
	 * @return the conDirecc
	 */
	public String getConDirecc() {
		return conDirecc;
	}
	/**
	 * @param conDirecc the conDirecc to set
	 */
	public void setConDirecc(String conDirecc) {
		this.conDirecc = conDirecc;
	}
	/**
	 * @return the frenteAlNum
	 */
	public String getFrenteAlNum() {
		return frenteAlNum;
	}
	/**
	 * @param frenteAlNum the frenteAlNum to set
	 */
	public void setFrenteAlNum(String frenteAlNum) {
		this.frenteAlNum = frenteAlNum;
	}
	/**
	 * @return the observeQue
	 */
	public String getObserveQue() {
		return ObserveQue;
	}
	/**
	 * @param observeQue the observeQue to set
	 */
	public void setObserveQue(String observeQue) {
		ObserveQue = observeQue;
	}
	/**
	 * @return the sancArt
	 */
	public Long getSancArt() {
		return sancArt;
	}
	/**
	 * @param sancArt the sancArt to set
	 */
	public void setSancArt(Long sancArt) {
		this.sancArt = sancArt;
	}
	@Override
	public String toString() {
		return "InfraccionDTO [infraccNum=" + infraccNum + ", infraccAAA=" + infraccAAA + ", infraccAAAMMM="
				+ infraccAAAMMM + ", infraccOrigen=" + infraccOrigen + ", infraccFolio=" + infraccFolio
				+ ", infraccImpresa=" + infraccImpresa + ", infraccNumCtrl=" + infraccNumCtrl + ", sector=" + sector
				+ ", utId=" + utId + ", infraccConPlaca=" + infraccConPlaca + ", infraccPlacaEdo=" + infraccPlacaEdo
				+ ", infraccPlaca=" + infraccPlaca + ", infIPaterno=" + infIPaterno + ", infIMaterno=" + infIMaterno
				+ ", infINombre=" + infINombre + ", infIRFC=" + infIRFC + ", infICalle=" + infICalle + ", infINumExt="
				+ infINumExt + ", infINumInt=" + infINumInt + ", infIColonia=" + infIColonia + ", infraccIDelEdo="
				+ infraccIDelEdo + ", infraccLicencia=" + infraccLicencia + ", tipoL=" + tipoL + ", infraccLServPub="
				+ infraccLServPub + ", infraccLicEdo=" + infraccLicEdo + ", vModMar=" + vModMar + ", vColor=" + vColor
				+ ", vOrigen=" + vOrigen + ", vTarCircul=" + vTarCircul + ", vTipo=" + vTipo + ", artMot=" + artMot
				+ ", infracMCalle=" + infracMCalle + ", infracMEntreCalle=" + infracMEntreCalle + ", infracMYCalle="
				+ infracMYCalle + ", infracMColonia=" + infracMColonia + ", infracMDelEdo=" + infracMDelEdo
				+ ", infracMFechaHora=" + infracMFechaHora + ", articulo=" + articulo + ", infracLeyTrans="
				+ infracLeyTrans + ", sancArt=" + sancArt + ", infracArrastre=" + infracArrastre
				+ ", infracNumArrastre=" + infracNumArrastre + ", infracTipoArrastre=" + infracTipoArrastre + ", grua="
				+ grua + ", depId=" + depId + ", empId=" + empId + ", empSector=" + empSector + ", empAgrup=" + empAgrup
				+ ", responsableVehiculo=" + responsableVehiculo + ", evenId=" + evenId + ", estatId=" + estatId
				+ ", infracPagada=" + infracPagada + ", infracGPSLat=" + infracGPSLat + ", infracGPSLon=" + infracGPSLon
				+ ", infracGCalle=" + infracGCalle + ", infracGEntreCalle=" + infracGEntreCalle + ", infracGYCalle="
				+ infracGYCalle + ", infraGColonia=" + infraGColonia + ", infraObs=" + infraObs + ", creadoPor="
				+ creadoPor + ", modPor=" + modPor + ", ultimaMod=" + ultimaMod + ", autPor=" + autPor + ", fechaAut="
				+ fechaAut + ", fechaCreacion=" + fechaCreacion + ", infracStatus=" + infracStatus + ", statusProceso="
				+ statusProceso + ", tipoProceso=" + tipoProceso + ", infracCaptura=" + infracCaptura + ", infracLC="
				+ infracLC + ", estImvId=" + estImvId + ", infracApoyoGrua=" + infracApoyoGrua + ", estCancel="
				+ estCancel + ", autCancel=" + autCancel + ", fechaCancel=" + fechaCancel + ", usuarioCancel="
				+ usuarioCancel + ", usuarioAut=" + usuarioAut + ", motCambio=" + motCambio + ""
				+ ", conDirecc=" + conDirecc + ", frenteAlNum=" + frenteAlNum
				+ ", ObserveQue=" + ObserveQue + "]";
	}
		
}
