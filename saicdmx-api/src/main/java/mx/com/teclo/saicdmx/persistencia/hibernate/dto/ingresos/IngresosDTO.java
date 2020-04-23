package mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.SalidasDTO;

@Entity
@Table(name="INGRESOS")
public class IngresosDTO implements Serializable {

	private static final long serialVersionUID = 217233178478708976L;
	
	@Id
	@Column(name = "INFRAC_NUM", unique = true, nullable =  false)
	@GeneratedValue(generator="gen")
	@GenericGenerator(name="gen", strategy="foreign", parameters={@Parameter(name="property", value="infraccion")})
	private String infracNum;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private InfraccionDTO infraccion;
	
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

	@Column(name = "ST_MOVIMIENTO")
	private String stMovimiento;
	
	@Column(name = "FH_MOVIMIENTO")
	private Date fhMovimiento;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "numinfraccion")
	private List<SalidasDTO> movimientos;

	public InfraccionDTO getInfraccion() {
		return infraccion;
	}

	public void setInfraccion(InfraccionDTO infraccion) {
		this.infraccion = infraccion;
	}
	
	public String getStMovimiento() {
		return stMovimiento;
	}

	public void setStMovimiento(String stMovimiento) {
		this.stMovimiento = stMovimiento;
	}

	public Date getFhMovimiento() {
		return fhMovimiento;
	}

	public void setFhMovimiento(Date fhMovimiento) {
		this.fhMovimiento = fhMovimiento;
	}
	
	public List<SalidasDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<SalidasDTO> movimientos) {
		this.movimientos = movimientos;
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
	 * @return the ingrResguardo
	 */
	public String getIngrResguardo() {
		return ingrResguardo;
	}

	/**
	 * @param ingrResguardo the ingrResguardo to set
	 */
	public void setIngrResguardo(String ingrResguardo) {
		this.ingrResguardo = ingrResguardo;
	}

	/**
	 * @return the causaId
	 */
	public Long getCausaId() {
		return causaId;
	}

	/**
	 * @param causaId the causaId to set
	 */
	public void setCausaId(Long causaId) {
		this.causaId = causaId;
	}

	/**
	 * @return the vTipoCod
	 */
	public String getvTipoCod() {
		return vTipoCod;
	}

	/**
	 * @param vTipoCod the vTipoCod to set
	 */
	public void setvTipoCod(String vTipoCod) {
		this.vTipoCod = vTipoCod;
	}

	/**
	 * @return the tIngrId
	 */
	public Long gettIngrId() {
		return tIngrId;
	}

	/**
	 * @param tIngrId the tIngrId to set
	 */
	public void settIngrId(Long tIngrId) {
		this.tIngrId = tIngrId;
	}

	/**
	 * @return the ingrAsn
	 */
	public String getIngrAsn() {
		return ingrAsn;
	}

	/**
	 * @param ingrAsn the ingrAsn to set
	 */
	public void setIngrAsn(String ingrAsn) {
		this.ingrAsn = ingrAsn;
	}

	/**
	 * @return the ingrNumCtrl
	 */
	public String getIngrNumCtrl() {
		return ingrNumCtrl;
	}

	/**
	 * @param ingrNumCtrl the ingrNumCtrl to set
	 */
	public void setIngrNumCtrl(String ingrNumCtrl) {
		this.ingrNumCtrl = ingrNumCtrl;
	}

	/**
	 * @return the infracNum
	 */
	public String getInfracNum() {
		return infracNum;
	}

	/**
	 * @param infracNum the infracNum to set
	 */
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	/**
	 * @return the sellado
	 */
	public String getSellado() {
		return sellado;
	}

	/**
	 * @param sellado the sellado to set
	 */
	public void setSellado(String sellado) {
		this.sellado = sellado;
	}

	/**
	 * @return the cajuela
	 */
	public String getCajuela() {
		return cajuela;
	}

	/**
	 * @param cajuela the cajuela to set
	 */
	public void setCajuela(String cajuela) {
		this.cajuela = cajuela;
	}

	/**
	 * @return the ingrFecha
	 */
	public Date getIngrFecha() {
		return ingrFecha;
	}

	/**
	 * @param ingrFecha the ingrFecha to set
	 */
	public void setIngrFecha(Date ingrFecha) {
		this.ingrFecha = ingrFecha;
	}

	/**
	 * @return the ingrSalida
	 */
	public Date getIngrSalida() {
		return ingrSalida;
	}

	/**
	 * @param ingrSalida the ingrSalida to set
	 */
	public void setIngrSalida(Date ingrSalida) {
		this.ingrSalida = ingrSalida;
	}

	/**
	 * @return the ingrSerie
	 */
	public String getIngrSerie() {
		return ingrSerie;
	}

	/**
	 * @param ingrSerie the ingrSerie to set
	 */
	public void setIngrSerie(String ingrSerie) {
		this.ingrSerie = ingrSerie;
	}

	/**
	 * @return the ingrStatus
	 */
	public String getIngrStatus() {
		return ingrStatus;
	}

	/**
	 * @param ingrStatus the ingrStatus to set
	 */
	public void setIngrStatus(String ingrStatus) {
		this.ingrStatus = ingrStatus;
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
	 * @return the ultimaModificacion
	 */
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	/**
	 * @param ultimaModificacion the ultimaModificacion to set
	 */
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	/**
	 * @return the vehtipo
	 */
	public String getVehtipo() {
		return vehtipo;
	}

	/**
	 * @param vehtipo the vehtipo to set
	 */
	public void setVehtipo(String vehtipo) {
		this.vehtipo = vehtipo;
	}

	/**
	 * @return the ingrObserva
	 */
	public String getIngrObserva() {
		return ingrObserva;
	}

	/**
	 * @param ingrObserva the ingrObserva to set
	 */
	public void setIngrObserva(String ingrObserva) {
		this.ingrObserva = ingrObserva;
	}

	/**
	 * @return the tipoGrua
	 */
	public Long getTipoGrua() {
		return tipoGrua;
	}

	/**
	 * @param tipoGrua the tipoGrua to set
	 */
	public void setTipoGrua(Long tipoGrua) {
		this.tipoGrua = tipoGrua;
	}

	/**
	 * @return the programa
	 */
	public Long getPrograma() {
		return programa;
	}

	/**
	 * @param programa the programa to set
	 */
	public void setPrograma(Long programa) {
		this.programa = programa;
	}

	/**
	 * @return the tipoEntrada
	 */
	public Long getTipoEntrada() {
		return tipoEntrada;
	}

	/**
	 * @param tipoEntrada the tipoEntrada to set
	 */
	public void setTipoEntrada(Long tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}

	/**
	 * @return the gruaCod
	 */
	public String getGruaCod() {
		return gruaCod;
	}

	/**
	 * @param gruaCod the gruaCod to set
	 */
	public void setGruaCod(String gruaCod) {
		this.gruaCod = gruaCod;
	}

	/**
	 * @return the infracDocto
	 */
	public String getInfracDocto() {
		return infracDocto;
	}

	/**
	 * @param infracDocto the infracDocto to set
	 */
	public void setInfracDocto(String infracDocto) {
		this.infracDocto = infracDocto;
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
	 * @return the estatusCancelacion
	 */
	public String getEstatusCancelacion() {
		return estatusCancelacion;
	}

	/**
	 * @param estatusCancelacion the estatusCancelacion to set
	 */
	public void setEstatusCancelacion(String estatusCancelacion) {
		this.estatusCancelacion = estatusCancelacion;
	}

	/**
	 * @return the autorizacionCancelacion
	 */
	public String getAutorizacionCancelacion() {
		return autorizacionCancelacion;
	}

	/**
	 * @param autorizacionCancelacion the autorizacionCancelacion to set
	 */
	public void setAutorizacionCancelacion(String autorizacionCancelacion) {
		this.autorizacionCancelacion = autorizacionCancelacion;
	}

	/**
	 * @return the fechaCancelacion
	 */
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	/**
	 * @param fechaCancelacion the fechaCancelacion to set
	 */
	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	/**
	 * @return the usuarioCancela
	 */
	public Long getUsuarioCancela() {
		return usuarioCancela;
	}

	/**
	 * @param usuarioCancela the usuarioCancela to set
	 */
	public void setUsuarioCancela(Long usuarioCancela) {
		this.usuarioCancela = usuarioCancela;
	}

	/**
	 * @return the usuarioAutoriza
	 */
	public Long getUsuarioAutoriza() {
		return usuarioAutoriza;
	}

	/**
	 * @param usuarioAutoriza the usuarioAutoriza to set
	 */
	public void setUsuarioAutoriza(Long usuarioAutoriza) {
		this.usuarioAutoriza = usuarioAutoriza;
	}
}
