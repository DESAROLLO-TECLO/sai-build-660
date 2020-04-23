package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;
import java.util.Date;

public class VSSPInfracConsGralFVO implements Serializable {

	private static final long serialVersionUID = 3555308119480100363L;

	private String deposito;
	private String fecha;
	private String fechaEmision;
	private String impresa;
	private String infraccionFecha;
	private String infraccionNumero;
	private String monto;
	private String motivacion;
	private String caja;
	private String numeroControlInterno;
	private String documento;
	private String pago;
	private String pagada;
	private String sancion;
	private String ubicacion;
	private String color;
	private String marca;
	private String modelo;
	private String placa;
	private String articulo;
	private String fraccion;
	private String parrafo;
	private String inciso;
	private String nci;
	private String infracSecId;
	private String infracSector;
	private String infracUniterrId;
	private String infracUniterr;
	private String responsableVehId;
	private String tienePlaca;
	private String vehiculoPlaca;
	private String placaExpedidaId;
	private String placaExpedida;
	private String infractorApePaterno;
	private String infractorApeMaterno;
	private String infractorNombre;
	private String infractorCalle;
	private String infractorNumExt;
	private String infractorNumInt;
	private String infractorColonia;
	private String infractorEdoId;
	private String infractorEdo;
	private String infractorDelegId;
	private String infractorDelegacion;
	private String infractorLicencia;
	private String licenciaTipo;
	private String licExpedidaId;
	private String licenciaExpedida;
	private String tarjetaCirculacion;
	private String infracCalle;
	private String infracEntreCalle;
	private String infracYCalle;
	private String infracColonia;
	private String infracDelegacion;
	private String infracArticulo;
	private String infracFraccion;
	private String infracParrafo;
	private String infracInciso;
	private String vehiculoMarcaId;
	private String vehiculoMarca;
	private String vehiculoModeloId;
	private String vehiculoModelo;
	private String vehiculoColorId;
	private String vehiculoColor;
	private String vehiculoTipo;
	private String vehiculoOrigen;
	private String gruaId;
	private String grua;
	private String oficialNombre;
	private String oficialPlaca;
	private String oficialSectorId;
	private String oficialSector;
	private String oficialAgrupamientoId;
	private String oficialAgrupamiento;
	private String infracObservacion;
	private String infracLServPublico;
	private String infracLeyTransp;
	private String articuloId;
	private String empId;
	private String infractorRfc;
	private String infracArrastre;
	private String infracTipoArrastre;
	private String infracConDireccion;
	private String infracFrenteAlNum;
	private String infracObserveQueId;
	private String infracObserveQueDesc;
	private String garantiaTipoId;
	private String garantiaTipoNombre;
	private String garantiaFolio;
	private String garantiaProcesoId;
	private String tipoVehiculoId;
	private String motivoCambio;
	private String autorizaId;
	private String creadoPor;
	private Integer modificadoPor; 
	private Date fechaAutoriza;
	private String infracDelegacionId;
	private String licenciaTipoId;
	private String idDeposito;
	private Boolean tieneExpediente;
	private String origenPlaca;
	private String nbTipoPersona;
	
	

	public String getIdDeposito() {
		return idDeposito;
	}

	public void setIdDeposito(String idDeposito) {
		this.idDeposito = idDeposito;
	}

	public String getLicenciaTipoId() {
		return licenciaTipoId;
	}

	public void setLicenciaTipoId(String licenciaTipoId) {
		this.licenciaTipoId = licenciaTipoId;
	}

	public String getInfracDelegacionId() {
		return infracDelegacionId;
	}

	public void setInfracDelegacionId(String infracDelegacionId) {
		this.infracDelegacionId = infracDelegacionId;
	}

	public Date getFechaAutoriza() {
		return fechaAutoriza;
	}

	public void setFechaAutoriza(Date fechaAutoriza) {
		this.fechaAutoriza = fechaAutoriza;
	}

	public String getMotivoCambio() {
		return motivoCambio;
	}

	public void setMotivoCambio(String motivoCambio) {
		this.motivoCambio = motivoCambio;
	}

	public String getAutorizaId() {
		return autorizaId;
	}

	public void setAutorizaId(String autorizaId) {
		this.autorizaId = autorizaId;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	
	public Integer getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Integer modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	
	public String getTipoVehiculoId() {
		return tipoVehiculoId;
	}

	public void setTipoVehiculoId(String tipoVehiculoId) {
		this.tipoVehiculoId = tipoVehiculoId;
	}

	/**
	 * @return the deposito
	 */
	public String getDeposito() {
		return deposito;
	}

	/**
	 * @param deposito
	 *            the deposito to set
	 */
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision
	 *            the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the impresa
	 */
	public String getImpresa() {
		return impresa;
	}

	/**
	 * @param impresa
	 *            the impresa to set
	 */
	public void setImpresa(String impresa) {
		this.impresa = impresa;
	}

	/**
	 * @return the infraccionFecha
	 */
	public String getInfraccionFecha() {
		return infraccionFecha;
	}

	/**
	 * @param infraccionFecha
	 *            the infraccionFecha to set
	 */
	public void setInfraccionFecha(String infraccionFecha) {
		this.infraccionFecha = infraccionFecha;
	}

	/**
	 * @return the infraccionNumero
	 */
	public String getInfraccionNumero() {
		return infraccionNumero;
	}

	/**
	 * @param infraccionNumero
	 *            the infraccionNumero to set
	 */
	public void setInfraccionNumero(String infraccionNumero) {
		this.infraccionNumero = infraccionNumero;
	}

	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * @return the motivacion
	 */
	public String getMotivacion() {
		return motivacion;
	}

	/**
	 * @param motivacion
	 *            the motivacion to set
	 */
	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;
	}

	/**
	 * @return the caja
	 */
	public String getCaja() {
		return caja;
	}

	/**
	 * @param caja
	 *            the caja to set
	 */
	public void setCaja(String caja) {
		this.caja = caja;
	}

	/**
	 * @return the numeroControlInterno
	 */
	public String getNumeroControlInterno() {
		return numeroControlInterno;
	}

	/**
	 * @param numeroControlInterno
	 *            the numeroControlInterno to set
	 */
	public void setNumeroControlInterno(String numeroControlInterno) {
		this.numeroControlInterno = numeroControlInterno;
	}

	/**
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @param documento
	 *            the documento to set
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * @return the pago
	 */
	public String getPago() {
		return pago;
	}

	/**
	 * @param pago
	 *            the pago to set
	 */
	public void setPago(String pago) {
		this.pago = pago;
	}

	/**
	 * @return the pagada
	 */
	public String getPagada() {
		return pagada;
	}

	/**
	 * @param pagada
	 *            the pagada to set
	 */
	public void setPagada(String pagada) {
		this.pagada = pagada;
	}

	/**
	 * @return the sancion
	 */
	public String getSancion() {
		return sancion;
	}

	/**
	 * @param sancion
	 *            the sancion to set
	 */
	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	/**
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion
	 *            the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca
	 *            the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo
	 *            the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa
	 *            the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return the articulo
	 */
	public String getArticulo() {
		return articulo;
	}

	/**
	 * @param articulo
	 *            the articulo to set
	 */
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	/**
	 * @return the fraccion
	 */
	public String getFraccion() {
		return fraccion;
	}

	/**
	 * @param fraccion
	 *            the fraccion to set
	 */
	public void setFraccion(String fraccion) {
		this.fraccion = fraccion;
	}

	/**
	 * @return the parrafo
	 */
	public String getParrafo() {
		return parrafo;
	}

	/**
	 * @param parrafo
	 *            the parrafo to set
	 */
	public void setParrafo(String parrafo) {
		this.parrafo = parrafo;
	}

	/**
	 * @return the inciso
	 */
	public String getInciso() {
		return inciso;
	}

	/**
	 * @param inciso
	 *            the inciso to set
	 */
	public void setInciso(String inciso) {
		this.inciso = inciso;
	}

	/**
	 * @return the nci
	 */
	public String getNci() {
		return nci;
	}

	/**
	 * @param nci
	 *            the nci to set
	 */
	public void setNci(String nci) {
		this.nci = nci;
	}

	/**
	 * @return the infracSecId
	 */
	public String getInfracSecId() {
		return infracSecId;
	}

	/**
	 * @param infracSecId
	 *            the infracSecId to set
	 */
	public void setInfracSecId(String infracSecId) {
		this.infracSecId = infracSecId;
	}

	/**
	 * @return the infracSector
	 */
	public String getInfracSector() {
		return infracSector;
	}

	/**
	 * @param infracSector
	 *            the infracSector to set
	 */
	public void setInfracSector(String infracSector) {
		this.infracSector = infracSector;
	}

	/**
	 * @return the infracUniterrId
	 */
	public String getInfracUniterrId() {
		return infracUniterrId;
	}

	/**
	 * @param infracUniterrId
	 *            the infracUniterrId to set
	 */
	public void setInfracUniterrId(String infracUniterrId) {
		this.infracUniterrId = infracUniterrId;
	}

	/**
	 * @return the infracUniterr
	 */
	public String getInfracUniterr() {
		return infracUniterr;
	}

	/**
	 * @param infracUniterr
	 *            the infracUniterr to set
	 */
	public void setInfracUniterr(String infracUniterr) {
		this.infracUniterr = infracUniterr;
	}

	public String getResponsableVehId() {
		return responsableVehId;
	}

	public void setResponsableVehId(String responsableVehId) {
		this.responsableVehId = responsableVehId;
	}

	/**
	 * @return the tienePlaca
	 */
	public String getTienePlaca() {
		return tienePlaca;
	}

	/**
	 * @param tienePlaca
	 *            the tienePlaca to set
	 */
	public void setTienePlaca(String tienePlaca) {
		this.tienePlaca = tienePlaca;
	}

	/**
	 * @return the vehiculoPlaca
	 */
	public String getVehiculoPlaca() {
		return vehiculoPlaca;
	}

	/**
	 * @param vehiculoPlaca
	 *            the vehiculoPlaca to set
	 */
	public void setVehiculoPlaca(String vehiculoPlaca) {
		this.vehiculoPlaca = vehiculoPlaca;
	}

	/**
	 * @return the placaExpedidaId
	 */
	public String getPlacaExpedidaId() {
		return placaExpedidaId;
	}

	/**
	 * @param placaExpedidaId
	 *            the placaExpedidaId to set
	 */
	public void setPlacaExpedidaId(String placaExpedidaId) {
		this.placaExpedidaId = placaExpedidaId;
	}

	/**
	 * @return the placaExpedida
	 */
	public String getPlacaExpedida() {
		return placaExpedida;
	}

	/**
	 * @param placaExpedida
	 *            the placaExpedida to set
	 */
	public void setPlacaExpedida(String placaExpedida) {
		this.placaExpedida = placaExpedida;
	}

	/**
	 * @return the infractorApePaterno
	 */
	public String getInfractorApePaterno() {
		return infractorApePaterno;
	}

	/**
	 * @param infractorApePaterno
	 *            the infractorApePaterno to set
	 */
	public void setInfractorApePaterno(String infractorApePaterno) {
		this.infractorApePaterno = infractorApePaterno;
	}

	/**
	 * @return the infractorApeMaterno
	 */
	public String getInfractorApeMaterno() {
		return infractorApeMaterno;
	}

	/**
	 * @param infractorApeMaterno
	 *            the infractorApeMaterno to set
	 */
	public void setInfractorApeMaterno(String infractorApeMaterno) {
		this.infractorApeMaterno = infractorApeMaterno;
	}

	/**
	 * @return the infractorNombre
	 */
	public String getInfractorNombre() {
		return infractorNombre;
	}

	/**
	 * @param infractorNombre
	 *            the infractorNombre to set
	 */
	public void setInfractorNombre(String infractorNombre) {
		this.infractorNombre = infractorNombre;
	}

	/**
	 * @return the infractorCalle
	 */
	public String getInfractorCalle() {
		return infractorCalle;
	}

	/**
	 * @param infractorCalle
	 *            the infractorCalle to set
	 */
	public void setInfractorCalle(String infractorCalle) {
		this.infractorCalle = infractorCalle;
	}

	/**
	 * @return the infractorNumExt
	 */
	public String getInfractorNumExt() {
		return infractorNumExt;
	}

	/**
	 * @param infractorNumExt
	 *            the infractorNumExt to set
	 */
	public void setInfractorNumExt(String infractorNumExt) {
		this.infractorNumExt = infractorNumExt;
	}

	/**
	 * @return the infractorNumInt
	 */
	public String getInfractorNumInt() {
		return infractorNumInt;
	}

	/**
	 * @param infractorNumInt
	 *            the infractorNumInt to set
	 */
	public void setInfractorNumInt(String infractorNumInt) {
		this.infractorNumInt = infractorNumInt;
	}

	/**
	 * @return the infractorColonia
	 */
	public String getInfractorColonia() {
		return infractorColonia;
	}

	/**
	 * @param infractorColonia
	 *            the infractorColonia to set
	 */
	public void setInfractorColonia(String infractorColonia) {
		this.infractorColonia = infractorColonia;
	}

	/**
	 * @return the infractorEdoId
	 */
	public String getInfractorEdoId() {
		return infractorEdoId;
	}

	/**
	 * @param infractorEdoId
	 *            the infractorEdoId to set
	 */
	public void setInfractorEdoId(String infractorEdoId) {
		this.infractorEdoId = infractorEdoId;
	}

	/**
	 * @return the infractorEdo
	 */
	public String getInfractorEdo() {
		return infractorEdo;
	}

	/**
	 * @param infractorEdo
	 *            the infractorEdo to set
	 */
	public void setInfractorEdo(String infractorEdo) {
		this.infractorEdo = infractorEdo;
	}

	/**
	 * @return the infractorDelegId
	 */
	public String getInfractorDelegId() {
		return infractorDelegId;
	}

	/**
	 * @param infractorDelegId
	 *            the infractorDelegId to set
	 */
	public void setInfractorDelegId(String infractorDelegId) {
		this.infractorDelegId = infractorDelegId;
	}

	/**
	 * @return the infractorDelegacion
	 */
	public String getInfractorDelegacion() {
		return infractorDelegacion;
	}

	/**
	 * @param infractorDelegacion
	 *            the infractorDelegacion to set
	 */
	public void setInfractorDelegacion(String infractorDelegacion) {
		this.infractorDelegacion = infractorDelegacion;
	}

	/**
	 * @return the infractorLicencia
	 */
	public String getInfractorLicencia() {
		return infractorLicencia;
	}

	/**
	 * @param infractorLicencia
	 *            the infractorLicencia to set
	 */
	public void setInfractorLicencia(String infractorLicencia) {
		this.infractorLicencia = infractorLicencia;
	}

	/**
	 * @return the licenciaTipo
	 */
	public String getLicenciaTipo() {
		return licenciaTipo;
	}

	/**
	 * @param licenciaTipo
	 *            the licenciaTipo to set
	 */
	public void setLicenciaTipo(String licenciaTipo) {
		this.licenciaTipo = licenciaTipo;
	}

	/**
	 * @return the licExpedidaId
	 */
	public String getLicExpedidaId() {
		return licExpedidaId;
	}

	/**
	 * @param licExpedidaId
	 *            the licExpedidaId to set
	 */
	public void setLicExpedidaId(String licExpedidaId) {
		this.licExpedidaId = licExpedidaId;
	}

	/**
	 * @return the licenciaExpedida
	 */
	public String getLicenciaExpedida() {
		return licenciaExpedida;
	}

	/**
	 * @param licenciaExpedida
	 *            the licenciaExpedida to set
	 */
	public void setLicenciaExpedida(String licenciaExpedida) {
		this.licenciaExpedida = licenciaExpedida;
	}

	/**
	 * @return the tarjetaCirculacion
	 */
	public String getTarjetaCirculacion() {
		return tarjetaCirculacion;
	}

	/**
	 * @param tarjetaCirculacion
	 *            the tarjetaCirculacion to set
	 */
	public void setTarjetaCirculacion(String tarjetaCirculacion) {
		this.tarjetaCirculacion = tarjetaCirculacion;
	}

	/**
	 * @return the infracCalle
	 */
	public String getInfracCalle() {
		return infracCalle;
	}

	/**
	 * @param infracCalle
	 *            the infracCalle to set
	 */
	public void setInfracCalle(String infracCalle) {
		this.infracCalle = infracCalle;
	}

	/**
	 * @return the infracEntreCalle
	 */
	public String getInfracEntreCalle() {
		return infracEntreCalle;
	}

	/**
	 * @param infracEntreCalle
	 *            the infracEntreCalle to set
	 */
	public void setInfracEntreCalle(String infracEntreCalle) {
		this.infracEntreCalle = infracEntreCalle;
	}

	/**
	 * @return the infracYCalle
	 */
	public String getInfracYCalle() {
		return infracYCalle;
	}

	/**
	 * @param infracYCalle
	 *            the infracYCalle to set
	 */
	public void setInfracYCalle(String infracYCalle) {
		this.infracYCalle = infracYCalle;
	}

	/**
	 * @return the infracColonia
	 */
	public String getInfracColonia() {
		return infracColonia;
	}

	/**
	 * @param infracColonia
	 *            the infracColonia to set
	 */
	public void setInfracColonia(String infracColonia) {
		this.infracColonia = infracColonia;
	}

	/**
	 * @return the infracDelegacion
	 */
	public String getInfracDelegacion() {
		return infracDelegacion;
	}

	/**
	 * @param infracDelegacion
	 *            the infracDelegacion to set
	 */
	public void setInfracDelegacion(String infracDelegacion) {
		this.infracDelegacion = infracDelegacion;
	}

	/**
	 * @return the infracArticulo
	 */
	public String getInfracArticulo() {
		return infracArticulo;
	}

	/**
	 * @param infracArticulo
	 *            the infracArticulo to set
	 */
	public void setInfracArticulo(String infracArticulo) {
		this.infracArticulo = infracArticulo;
	}

	/**
	 * @return the infracFraccion
	 */
	public String getInfracFraccion() {
		return infracFraccion;
	}

	/**
	 * @param infracFraccion
	 *            the infracFraccion to set
	 */
	public void setInfracFraccion(String infracFraccion) {
		this.infracFraccion = infracFraccion;
	}

	/**
	 * @return the infracParrafo
	 */
	public String getInfracParrafo() {
		return infracParrafo;
	}

	/**
	 * @param infracParrafo
	 *            the infracParrafo to set
	 */
	public void setInfracParrafo(String infracParrafo) {
		this.infracParrafo = infracParrafo;
	}

	/**
	 * @return the infracInciso
	 */
	public String getInfracInciso() {
		return infracInciso;
	}

	/**
	 * @param infracInciso
	 *            the infracInciso to set
	 */
	public void setInfracInciso(String infracInciso) {
		this.infracInciso = infracInciso;
	}

	/**
	 * @return the vehiculoMarcaId
	 */
	public String getVehiculoMarcaId() {
		return vehiculoMarcaId;
	}

	/**
	 * @param vehiculoMarcaId
	 *            the vehiculoMarcaId to set
	 */
	public void setVehiculoMarcaId(String vehiculoMarcaId) {
		this.vehiculoMarcaId = vehiculoMarcaId;
	}

	/**
	 * @return the vehiculoMarca
	 */
	public String getVehiculoMarca() {
		return vehiculoMarca;
	}

	/**
	 * @param vehiculoMarca
	 *            the vehiculoMarca to set
	 */
	public void setVehiculoMarca(String vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}

	/**
	 * @return the vehiculoModeloId
	 */
	public String getVehiculoModeloId() {
		return vehiculoModeloId;
	}

	/**
	 * @param vehiculoModeloId
	 *            the vehiculoModeloId to set
	 */
	public void setVehiculoModeloId(String vehiculoModeloId) {
		this.vehiculoModeloId = vehiculoModeloId;
	}

	/**
	 * @return the vehiculoModelo
	 */
	public String getVehiculoModelo() {
		return vehiculoModelo;
	}

	/**
	 * @param vehiculoModelo
	 *            the vehiculoModelo to set
	 */
	public void setVehiculoModelo(String vehiculoModelo) {
		this.vehiculoModelo = vehiculoModelo;
	}

	/**
	 * @return the vehiculoColorId
	 */
	public String getVehiculoColorId() {
		return vehiculoColorId;
	}

	/**
	 * @param vehiculoColorId
	 *            the vehiculoColorId to set
	 */
	public void setVehiculoColorId(String vehiculoColorId) {
		this.vehiculoColorId = vehiculoColorId;
	}

	/**
	 * @return the vehiculoColor
	 */
	public String getVehiculoColor() {
		return vehiculoColor;
	}

	/**
	 * @param vehiculoColor
	 *            the vehiculoColor to set
	 */
	public void setVehiculoColor(String vehiculoColor) {
		this.vehiculoColor = vehiculoColor;
	}

	/**
	 * @return the vehiculoTipo
	 */
	public String getVehiculoTipo() {
		return vehiculoTipo;
	}

	/**
	 * @param vehiculoTipo
	 *            the vehiculoTipo to set
	 */
	public void setVehiculoTipo(String vehiculoTipo) {
		this.vehiculoTipo = vehiculoTipo;
	}

	/**
	 * @return the vehiculoOrigen
	 */
	public String getVehiculoOrigen() {
		return vehiculoOrigen;
	}

	/**
	 * @param vehiculoOrigen
	 *            the vehiculoOrigen to set
	 */
	public void setVehiculoOrigen(String vehiculoOrigen) {
		this.vehiculoOrigen = vehiculoOrigen;
	}

	/**
	 * @return the gruaId
	 */
	public String getGruaId() {
		return gruaId;
	}

	/**
	 * @param gruaId
	 *            the gruaId to set
	 */
	public void setGruaId(String gruaId) {
		this.gruaId = gruaId;
	}

	/**
	 * @return the grua
	 */
	public String getGrua() {
		return grua;
	}

	/**
	 * @param grua
	 *            the grua to set
	 */
	public void setGrua(String grua) {
		this.grua = grua;
	}

	/**
	 * @return the oficialNombre
	 */
	public String getOficialNombre() {
		return oficialNombre;
	}

	/**
	 * @param oficialNombre
	 *            the oficialNombre to set
	 */
	public void setOficialNombre(String oficialNombre) {
		this.oficialNombre = oficialNombre;
	}

	/**
	 * @return the oficialPlaca
	 */
	public String getOficialPlaca() {
		return oficialPlaca;
	}

	/**
	 * @param oficialPlaca
	 *            the oficialPlaca to set
	 */
	public void setOficialPlaca(String oficialPlaca) {
		this.oficialPlaca = oficialPlaca;
	}

	/**
	 * @return the oficialSectorId
	 */
	public String getOficialSectorId() {
		return oficialSectorId;
	}

	/**
	 * @param oficialSectorId
	 *            the oficialSectorId to set
	 */
	public void setOficialSectorId(String oficialSectorId) {
		this.oficialSectorId = oficialSectorId;
	}

	/**
	 * @return the oficialSector
	 */
	public String getOficialSector() {
		return oficialSector;
	}

	/**
	 * @param oficialSector
	 *            the oficialSector to set
	 */
	public void setOficialSector(String oficialSector) {
		this.oficialSector = oficialSector;
	}

	/**
	 * @return the oficialAgrupamientoId
	 */
	public String getOficialAgrupamientoId() {
		return oficialAgrupamientoId;
	}

	/**
	 * @param oficialAgrupamientoId
	 *            the oficialAgrupamientoId to set
	 */
	public void setOficialAgrupamientoId(String oficialAgrupamientoId) {
		this.oficialAgrupamientoId = oficialAgrupamientoId;
	}

	/**
	 * @return the oficialAgrupamiento
	 */
	public String getOficialAgrupamiento() {
		return oficialAgrupamiento;
	}

	/**
	 * @param oficialAgrupamiento
	 *            the oficialAgrupamiento to set
	 */
	public void setOficialAgrupamiento(String oficialAgrupamiento) {
		this.oficialAgrupamiento = oficialAgrupamiento;
	}

	/**
	 * @return the infracObservacion
	 */
	public String getInfracObservacion() {
		return infracObservacion;
	}

	/**
	 * @param infracObservacion
	 *            the infracObservacion to set
	 */
	public void setInfracObservacion(String infracObservacion) {
		this.infracObservacion = infracObservacion;
	}

	/**
	 * @return the infracLServPublico
	 */
	public String getInfracLServPublico() {
		return infracLServPublico;
	}

	/**
	 * @param infracLServPublico
	 *            the infracLServPublico to set
	 */
	public void setInfracLServPublico(String infracLServPublico) {
		this.infracLServPublico = infracLServPublico;
	}

	/**
	 * @return the infracLeyTransp
	 */
	public String getInfracLeyTransp() {
		return infracLeyTransp;
	}

	/**
	 * @param infracLeyTransp
	 *            the infracLeyTransp to set
	 */
	public void setInfracLeyTransp(String infracLeyTransp) {
		this.infracLeyTransp = infracLeyTransp;
	}

	/**
	 * @return the articuloId
	 */
	public String getArticuloId() {
		return articuloId;
	}

	/**
	 * @param articuloId
	 *            the articuloId to set
	 */
	public void setArticuloId(String articuloId) {
		this.articuloId = articuloId;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the infractorRfc
	 */
	public String getInfractorRfc() {
		return infractorRfc;
	}

	/**
	 * @param infractorRfc
	 *            the infractorRfc to set
	 */
	public void setInfractorRfc(String infractorRfc) {
		this.infractorRfc = infractorRfc;
	}

	/**
	 * @return the infracArrastre
	 */
	public String getInfracArrastre() {
		return infracArrastre;
	}

	/**
	 * @param infracArrastre
	 *            the infracArrastre to set
	 */
	public void setInfracArrastre(String infracArrastre) {
		this.infracArrastre = infracArrastre;
	}

	/**
	 * @return the infracTipoArrastre
	 */
	public String getInfracTipoArrastre() {
		return infracTipoArrastre;
	}

	/**
	 * @param infracTipoArrastre
	 *            the infracTipoArrastre to set
	 */
	public void setInfracTipoArrastre(String infracTipoArrastre) {
		this.infracTipoArrastre = infracTipoArrastre;
	}

	/**
	 * @return the infracConDireccion
	 */
	public String getInfracConDireccion() {
		return infracConDireccion;
	}

	/**
	 * @param infracConDireccion
	 *            the infracConDireccion to set
	 */
	public void setInfracConDireccion(String infracConDireccion) {
		this.infracConDireccion = infracConDireccion;
	}

	/**
	 * @return the infracFrenteAlNum
	 */
	public String getInfracFrenteAlNum() {
		return infracFrenteAlNum;
	}

	/**
	 * @param infracFrenteAlNum
	 *            the infracFrenteAlNum to set
	 */
	public void setInfracFrenteAlNum(String infracFrenteAlNum) {
		this.infracFrenteAlNum = infracFrenteAlNum;
	}

	/**
	 * @return the infracObserveQueId
	 */
	public String getInfracObserveQueId() {
		return infracObserveQueId;
	}

	/**
	 * @param infracObserveQueId
	 *            the infracObserveQueId to set
	 */
	public void setInfracObserveQueId(String infracObserveQueId) {
		this.infracObserveQueId = infracObserveQueId;
	}

	/**
	 * @return the infracObserveQueDesc
	 */
	public String getInfracObserveQueDesc() {
		return infracObserveQueDesc;
	}

	/**
	 * @param infracObserveQueDesc
	 *            the infracObserveQueDesc to set
	 */
	public void setInfracObserveQueDesc(String infracObserveQueDesc) {
		this.infracObserveQueDesc = infracObserveQueDesc;
	}

	/**
	 * @return the garantiaTipoId
	 */
	public String getGarantiaTipoId() {
		return garantiaTipoId;
	}

	/**
	 * @param garantiaTipoId
	 *            the garantiaTipoId to set
	 */
	public void setGarantiaTipoId(String garantiaTipoId) {
		this.garantiaTipoId = garantiaTipoId;
	}

	/**
	 * @return the garantiaTipoNombre
	 */
	public String getGarantiaTipoNombre() {
		return garantiaTipoNombre;
	}

	/**
	 * @param garantiaTipoNombre
	 *            the garantiaTipoNombre to set
	 */
	public void setGarantiaTipoNombre(String garantiaTipoNombre) {
		this.garantiaTipoNombre = garantiaTipoNombre;
	}

	public String getGarantiaFolio() {
		return garantiaFolio;
	}

	public void setGarantiaFolio(String garantiaFolio) {
		this.garantiaFolio = garantiaFolio;
	}
	
	public String getGarantiaProcesoId() {
		return garantiaProcesoId;
	}
	
	public void setGarantiaProcesoId(String garantiaProcesoId) {
		this.garantiaProcesoId = garantiaProcesoId;
	}

	public Boolean getTieneExpediente() {
		return tieneExpediente;
	}

	public void setTieneExpediente(Boolean tieneExpediente) {
		this.tieneExpediente = tieneExpediente;
	}

	public String getOrigenPlaca() {
		return origenPlaca;
	}

	public void setOrigenPlaca(String origenPlaca) {
		this.origenPlaca = origenPlaca;
	}

	public String getNbTipoPersona() {
		return nbTipoPersona;
	}

	public void setNbTipoPersona(String nbTipoPersona) {
		this.nbTipoPersona = nbTipoPersona;
	}


	
	
}
