package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_REMISION_REPORTE")
public class VRemisionReporteDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "INFRAC_NUM")
	private String numeroInfraccion;
	@Column(name = "INFRAC_SECTOR")
	private String sectorInfraccion;
	@Column(name = "INFRAC_UNITERR")
	private String uniterrInfraccion;
	@Column(name = "INFRAC_M_FECHA")
	private String fechaInfraccion;
	@Column(name = "INFRAC_M_HORA")
	private String horaInfraccion;
	@Column(name = "INFRAC_M_DIA")
	private String diaInfraccion;
	@Column(name = "INFRAC_M_MES")
	private String mesInfraccion;
	@Column(name = "INFRAC_M_ANIO")
	private String anioInfraccion;
	@Column(name = "INFRAC_M_EN_LA_CALLE")
	private String calleInfraccion;
	@Column(name = "INFRAC_M_ENTRE_CALLE")
	private String entreCalleInfraccion;
	@Column(name = "INFRAC_M_Y_LA_CALLE")
	private String yLaCalleInfraccion;
	@Column(name = "INFRAC_M_COLONIA")
	private String coloniaInfraccion;
	@Column(name = "INFRAC_M_DELEGACION")
	private String delagacionInfraccion;
	@Column(name = "INFRAC_NUM_ARRASTRE")
	private String numeroArrastre;
	@Column(name = "INFRAC_TIPO_ARRASTRE")
	private String tipoArrastre;
	@Column(name = "VEHICULO_PLACA")
	private String vehiculoPlaca;
	@Column(name = "VEHICULO_TIPO")
	private String vehiculoTipo;
	@Column(name = "VEHICULO_MARCA")
	private String vehiculoMarca;
	@Column(name = "VEHICULO_MODELO")
	private String vehiculoModelo;
	@Column(name = "VEHICULO_COLOR")
	private String vehiculoColor;
	@Column(name = "INFRAC_DEPOSITO")
	private String depositoInfraccion;
	@Column(name = "INFRACTOR_NOMBRE")
	private String nombreInfractor;
	@Column(name = "INFRACTOR_TIPO_LICENCIA")
	private String tipoLicenciaInfractor;
	@Column(name = "OFICIAL_NOMBRE")
	private String nombreOficial;
	@Column(name = "OFICIAL_PLACA")
	private String placaOficial;
	@Column(name = "INFRAC_ARTICULO")
	private String articuloInfraccion;
	@Column(name = "INFRAC_FRACCION")
	private String fraccionInfraccion;
	@Column(name = "INFRAC_PARRAFO")
	private String parrafoInfraccion;
	@Column(name = "INFRAC_INCISO")
	private String incisoInfraccion;
	@Column(name = "INFRAC_MOTIVACION")
	private String motivacionInfraccion;
	@Column(name = "INFRAC_DIAS")
	private String diasInfraccion;
	@Column(name = "INGRESO_NUMERO_RESGUARDO")
	private String numeroResguardoIngreso;
	@Column(name = "INGRESO_TIPO_ENTRADA")
	private String tipoEntradaIngreso;
	@Column(name = "INGRESO_SELLADO")
	private String ingresoSellado;
	@Column(name = "INGRESO_CAJUELA")
	private String ingresoCajuela;
	@Column(name = "INGRESO_GRUA_NUMERO_ECONOMICO")
	private String numeroEconomicoGrua;
	@Column(name = "INGRESO_GRUA_CONCECIONARIA")
	private String concecionariaGrua;
	@Column(name = "INGRESO_OBSERVACIONES")
	private String observaciones;
	@Column(name = "INGRESO_INVENTARIO")
	private String inventarioIngreso;
	@Column(name = "INGRESO_DOCUMENTO")
	private String documentoIngreso;
	
	public String getNumeroInfraccion() {
		return numeroInfraccion;
	}
	public void setNumeroInfraccion(String numeroInfraccion) {
		this.numeroInfraccion = numeroInfraccion;
	}
	public String getSectorInfraccion() {
		return sectorInfraccion;
	}
	public void setSectorInfraccion(String sectorInfraccion) {
		this.sectorInfraccion = sectorInfraccion;
	}
	public String getUniterrInfraccion() {
		return uniterrInfraccion;
	}
	public void setUniterrInfraccion(String uniterrInfraccion) {
		this.uniterrInfraccion = uniterrInfraccion;
	}
	public String getFechaInfraccion() {
		return fechaInfraccion;
	}
	public void setFechaInfraccion(String fechaInfraccion) {
		this.fechaInfraccion = fechaInfraccion;
	}
	public String getHoraInfraccion() {
		return horaInfraccion;
	}
	public void setHoraInfraccion(String horaInfraccion) {
		this.horaInfraccion = horaInfraccion;
	}
	public String getDiaInfraccion() {
		return diaInfraccion;
	}
	public void setDiaInfraccion(String diaInfraccion) {
		this.diaInfraccion = diaInfraccion;
	}
	public String getMesInfraccion() {
		return mesInfraccion;
	}
	public void setMesInfraccion(String mesInfraccion) {
		this.mesInfraccion = mesInfraccion;
	}
	public String getAnioInfraccion() {
		return anioInfraccion;
	}
	public void setAnioInfraccion(String anioInfraccion) {
		this.anioInfraccion = anioInfraccion;
	}
	public String getCalleInfraccion() {
		return calleInfraccion;
	}
	public void setCalleInfraccion(String calleInfraccion) {
		this.calleInfraccion = calleInfraccion;
	}
	public String getEntreCalleInfraccion() {
		return entreCalleInfraccion;
	}
	public void setEntreCalleInfraccion(String entreCalleInfraccion) {
		this.entreCalleInfraccion = entreCalleInfraccion;
	}
	public String getyLaCalleInfraccion() {
		return yLaCalleInfraccion;
	}
	public void setyLaCalleInfraccion(String yLaCalleInfraccion) {
		this.yLaCalleInfraccion = yLaCalleInfraccion;
	}
	public String getColoniaInfraccion() {
		return coloniaInfraccion;
	}
	public void setColoniaInfraccion(String coloniaInfraccion) {
		this.coloniaInfraccion = coloniaInfraccion;
	}
	public String getDelagacionInfraccion() {
		return delagacionInfraccion;
	}
	public void setDelagacionInfraccion(String delagacionInfraccion) {
		this.delagacionInfraccion = delagacionInfraccion;
	}
	public String getNumeroArrastre() {
		return numeroArrastre;
	}
	public void setNumeroArrastre(String numeroArrastre) {
		this.numeroArrastre = numeroArrastre;
	}
	public String getTipoArrastre() {
		return tipoArrastre;
	}
	public void setTipoArrastre(String tipoArrastre) {
		this.tipoArrastre = tipoArrastre;
	}
	public String getVehiculoPlaca() {
		return vehiculoPlaca;
	}
	public void setVehiculoPlaca(String vehiculoPlaca) {
		this.vehiculoPlaca = vehiculoPlaca;
	}
	public String getVehiculoTipo() {
		return vehiculoTipo;
	}
	public void setVehiculoTipo(String vehiculoTipo) {
		this.vehiculoTipo = vehiculoTipo;
	}
	public String getVehiculoMarca() {
		return vehiculoMarca;
	}
	public void setVehiculoMarca(String vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}
	public String getVehiculoModelo() {
		return vehiculoModelo;
	}
	public void setVehiculoModelo(String vehiculoModelo) {
		this.vehiculoModelo = vehiculoModelo;
	}
	public String getVehiculoColor() {
		return vehiculoColor;
	}
	public void setVehiculoColor(String vehiculoColor) {
		this.vehiculoColor = vehiculoColor;
	}
	public String getDepositoInfraccion() {
		return depositoInfraccion;
	}
	public void setDepositoInfraccion(String depositoInfraccion) {
		this.depositoInfraccion = depositoInfraccion;
	}
	public String getNombreInfractor() {
		return nombreInfractor;
	}
	public void setNombreInfractor(String nombreInfractor) {
		this.nombreInfractor = nombreInfractor;
	}
	public String getTipoLicenciaInfractor() {
		return tipoLicenciaInfractor;
	}
	public void setTipoLicenciaInfractor(String tipoLicenciaInfractor) {
		this.tipoLicenciaInfractor = tipoLicenciaInfractor;
	}
	public String getNombreOficial() {
		return nombreOficial;
	}
	public void setNombreOficial(String nombreOficial) {
		this.nombreOficial = nombreOficial;
	}
	public String getPlacaOficial() {
		return placaOficial;
	}
	public void setPlacaOficial(String placaOficial) {
		this.placaOficial = placaOficial;
	}
	public String getArticuloInfraccion() {
		return articuloInfraccion;
	}
	public void setArticuloInfraccion(String articuloInfraccion) {
		this.articuloInfraccion = articuloInfraccion;
	}
	public String getFraccionInfraccion() {
		return fraccionInfraccion;
	}
	public void setFraccionInfraccion(String fraccionInfraccion) {
		this.fraccionInfraccion = fraccionInfraccion;
	}
	public String getParrafoInfraccion() {
		return parrafoInfraccion;
	}
	public void setParrafoInfraccion(String parrafoInfraccion) {
		this.parrafoInfraccion = parrafoInfraccion;
	}
	public String getIncisoInfraccion() {
		return incisoInfraccion;
	}
	public void setIncisoInfraccion(String incisoInfraccion) {
		this.incisoInfraccion = incisoInfraccion;
	}
	public String getMotivacionInfraccion() {
		return motivacionInfraccion;
	}
	public void setMotivacionInfraccion(String motivacionInfraccion) {
		this.motivacionInfraccion = motivacionInfraccion;
	}
	public String getDiasInfraccion() {
		return diasInfraccion;
	}
	public void setDiasInfraccion(String diasInfraccion) {
		this.diasInfraccion = diasInfraccion;
	}
	public String getNumeroResguardoIngreso() {
		return numeroResguardoIngreso;
	}
	public void setNumeroResguardoIngreso(String numeroResguardoIngreso) {
		this.numeroResguardoIngreso = numeroResguardoIngreso;
	}
	public String getTipoEntradaIngreso() {
		return tipoEntradaIngreso;
	}
	public void setTipoEntradaIngreso(String tipoEntradaIngreso) {
		this.tipoEntradaIngreso = tipoEntradaIngreso;
	}
	public String getIngresoSellado() {
		return ingresoSellado;
	}
	public void setIngresoSellado(String ingresoSellado) {
		this.ingresoSellado = ingresoSellado;
	}
	public String getIngresoCajuela() {
		return ingresoCajuela;
	}
	public void setIngresoCajuela(String ingresoCajuela) {
		this.ingresoCajuela = ingresoCajuela;
	}
	public String getNumeroEconomicoGrua() {
		return numeroEconomicoGrua;
	}
	public void setNumeroEconomicoGrua(String numeroEconomicoGrua) {
		this.numeroEconomicoGrua = numeroEconomicoGrua;
	}
	public String getConcecionariaGrua() {
		return concecionariaGrua;
	}
	public void setConcecionariaGrua(String concecionariaGrua) {
		this.concecionariaGrua = concecionariaGrua;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getInventarioIngreso() {
		return inventarioIngreso;
	}
	public void setInventarioIngreso(String inventarioIngreso) {
		this.inventarioIngreso = inventarioIngreso;
	}
	public String getDocumentoIngreso() {
		return documentoIngreso;
	}
	public void setDocumentoIngreso(String documentoIngreso) {
		this.documentoIngreso = documentoIngreso;
	}
}
