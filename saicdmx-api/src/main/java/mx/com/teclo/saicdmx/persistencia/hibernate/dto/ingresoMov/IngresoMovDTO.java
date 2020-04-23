package mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresoMov;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INGRESOS_MOV_HIST")
public class IngresoMovDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6981099208203593354L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_MOV_HIST", nullable = false)
	private Long idMovHist;
	
	@Column(name = "DEP_ID", nullable = false)
	private Long depId;
	
	@Column(name = "INGR_RESGUARDO", nullable = false)
	private String  ingrResguardo;
	
	@Column(name = "CAUSA_ID")
	private Long causaId;
	
	@Column(name = "VTIPO_COD")
	private String vTipoCod;
	
	@Column(name = "T_INGR_ID")
	private Long tIngrId;
	
	@Column(name = "INGR_ASN")
	private String ingrAsn;
	
	@Column(name = "INGR_NUM_CTRL")
	private String ingrNumCtrl;
	
	@Column(name = "INFRAC_NUM")
	private String infracNum;
	
	@Column(name = "SELLADO")
	private String sellado;
	
	@Column(name = "CAJUELA")
	private String cajuela;
	
	@Column(name = "INGR_FECHA")
	private Date ingrFecha;
	
	@Column(name = "INGR_SALIDA")
	private Date ingrSalida;
	
	@Column(name = "INGR_SERIE")
	private String ingrSerie;
	
	@Column(name = "INGR_STATUS")
	private String ingrStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	@Column(name = "VEH_TIPO")
	private String vehtipo;
	
	@Column(name = "INGR_OBSERVA")
	private String ingrObserva;
	
	@Column(name = "TIPO_GRUA")
	private Long tipoGrua;
	
	@Column(name = "PROGRAMA")
	private Long programa;
	
	@Column(name = "TIPO_ENTRADA")
	private Long tipoEntrada;
	
	@Column(name = "GRUA_COD")
	private String gruaCod;
	
	@Column(name = "INFRAC_DOCTO")
	private String infracDocto;
	
	@Column(name = "INFRAC_CAPTURA")
	private String infracCaptura;
	
	@Column(name = "ESTATUS_CANCELACION")
	private String estatusCancelacion;
	
	@Column(name = "AUTORIZACION_CANCELACION")
	private String autorizacionCancelacion;
	
	@Column(name = "FECHA_CANCELACION")
	private Date fechaCancelacion;
	
	@Column(name = "USUARIO_CANCELA")
	private Long usuarioCancela;
	
	@Column(name = "USUARIO_AUTORIZA")
	private Long usuarioAutoriza;

	public Long getIdMovHist() {
		return idMovHist;
	}

	public void setIdMovHist(Long idMovHist) {
		this.idMovHist = idMovHist;
	}

	public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public String getIngrResguardo() {
		return ingrResguardo;
	}

	public void setIngrResguardo(String ingrResguardo) {
		this.ingrResguardo = ingrResguardo;
	}

	public Long getCausaId() {
		return causaId;
	}

	public void setCausaId(Long causaId) {
		this.causaId = causaId;
	}

	public String getvTipoCod() {
		return vTipoCod;
	}

	public void setvTipoCod(String vTipoCod) {
		this.vTipoCod = vTipoCod;
	}

	public Long gettIngrId() {
		return tIngrId;
	}

	public void settIngrId(Long tIngrId) {
		this.tIngrId = tIngrId;
	}

	public String getIngrAsn() {
		return ingrAsn;
	}

	public void setIngrAsn(String ingrAsn) {
		this.ingrAsn = ingrAsn;
	}

	public String getIngrNumCtrl() {
		return ingrNumCtrl;
	}

	public void setIngrNumCtrl(String ingrNumCtrl) {
		this.ingrNumCtrl = ingrNumCtrl;
	}

	public String getInfracNum() {
		return infracNum;
	}

	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	public String getSellado() {
		return sellado;
	}

	public void setSellado(String sellado) {
		this.sellado = sellado;
	}

	public String getCajuela() {
		return cajuela;
	}

	public void setCajuela(String cajuela) {
		this.cajuela = cajuela;
	}

	public Date getIngrFecha() {
		return ingrFecha;
	}

	public void setIngrFecha(Date ingrFecha) {
		this.ingrFecha = ingrFecha;
	}

	public Date getIngrSalida() {
		return ingrSalida;
	}

	public void setIngrSalida(Date ingrSalida) {
		this.ingrSalida = ingrSalida;
	}

	public String getIngrSerie() {
		return ingrSerie;
	}

	public void setIngrSerie(String ingrSerie) {
		this.ingrSerie = ingrSerie;
	}

	public String getIngrStatus() {
		return ingrStatus;
	}

	public void setIngrStatus(String ingrStatus) {
		this.ingrStatus = ingrStatus;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public String getVehtipo() {
		return vehtipo;
	}

	public void setVehtipo(String vehtipo) {
		this.vehtipo = vehtipo;
	}

	public String getIngrObserva() {
		return ingrObserva;
	}

	public void setIngrObserva(String ingrObserva) {
		this.ingrObserva = ingrObserva;
	}

	public Long getTipoGrua() {
		return tipoGrua;
	}

	public void setTipoGrua(Long tipoGrua) {
		this.tipoGrua = tipoGrua;
	}

	public Long getPrograma() {
		return programa;
	}

	public void setPrograma(Long programa) {
		this.programa = programa;
	}

	public Long getTipoEntrada() {
		return tipoEntrada;
	}

	public void setTipoEntrada(Long tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}

	public String getGruaCod() {
		return gruaCod;
	}

	public void setGruaCod(String gruaCod) {
		this.gruaCod = gruaCod;
	}

	public String getInfracDocto() {
		return infracDocto;
	}

	public void setInfracDocto(String infracDocto) {
		this.infracDocto = infracDocto;
	}

	public String getInfracCaptura() {
		return infracCaptura;
	}

	public void setInfracCaptura(String infracCaptura) {
		this.infracCaptura = infracCaptura;
	}

	public String getEstatusCancelacion() {
		return estatusCancelacion;
	}

	public void setEstatusCancelacion(String estatusCancelacion) {
		this.estatusCancelacion = estatusCancelacion;
	}

	public String getAutorizacionCancelacion() {
		return autorizacionCancelacion;
	}

	public void setAutorizacionCancelacion(String autorizacionCancelacion) {
		this.autorizacionCancelacion = autorizacionCancelacion;
	}

	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public Long getUsuarioCancela() {
		return usuarioCancela;
	}

	public void setUsuarioCancela(Long usuarioCancela) {
		this.usuarioCancela = usuarioCancela;
	}

	public Long getUsuarioAutoriza() {
		return usuarioAutoriza;
	}

	public void setUsuarioAutoriza(Long usuarioAutoriza) {
		this.usuarioAutoriza = usuarioAutoriza;
	}
	
	
	
	
}
