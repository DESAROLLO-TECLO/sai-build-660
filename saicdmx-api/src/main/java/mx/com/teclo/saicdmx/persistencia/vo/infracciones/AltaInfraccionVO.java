package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class AltaInfraccionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8304739020215977888L;
	
	private String infracImpresa; 	
	private Integer secId;
	private String infracConPlaca;
	private Integer infracPlacaEdo;
	private String infracPlaca;
	private String infracIPaterno;
	private String infracIMaterno;
	private String infracINombre;
	private String infracICalle;
	private String infracINumExt;
	private String infracINumInt;
	private String infracIColonia;
	private Integer infracIedoId;
	private Integer infracIDelId;
	private String infracLicencia;
	private Integer tipoLId;
	private String infracLServPublico ;
	private Integer infracLicEdo ;
	private Integer vMarId;	
	private Integer vModId;
	private Integer vColorId;
	private String vOrigen;
	private String vTarjetaCirculacion;
	private Integer vTipoId;
	private String artMotivacion;	
	private String infracMEnLaCalle;
	private String infracMEntreCalle;	
	private String infracMYLaCalle;
	private String infracMColonia ;	
	private Integer infracMEdoId;
	private Integer infracMDelId;
	private Integer artId;
	private String infracLeyTransporte;
	private Integer sancionArtId;
	private String infracArrastre;
	private String infracNumArrastre;
	private String infracTipoArrastre;	
	private Integer gruaId;
	private Integer depId; //penndiente	
	private Integer empleadoId;//pendiente
	private Integer emp_Sector;//pendiente  	
	private Integer empAgrup;//pendiente
	private Integer rVehId;	
	private String oper;
	private String infracNumCtrl;	
	private Integer utId;
	private String fecha;
	private Integer modificadoPor;//pendiente
	private String resultado;
	private String mensaje;
	private String empleadoCod;
	private String empAgrupamiento;
	private String empPat;
	private String empMaterno;
	private String empNombre;
	private String infracCaptura;
	private Integer infracApoyoGrua;
	private String infracRfc;
	private String fechaEmision;
	private String infracObservacion;
	private String motivoCambio;
	private String infracConDireccion;
	private String infracFrenteAlNumero;
	private Integer observeQue;
	private Integer garantiaTipoId;
	private String garantiaFolio;
	private AltaInfraccionSPVO altaInfraccionSPVO; 
	
	public String getInfracImpresa() {
		return infracImpresa;
	}
	public void setInfracImpresa(String infracImpresa) {
		this.infracImpresa = infracImpresa;
	}
	public Integer getSecId() {
		return secId;
	}
	public void setSecId(Integer secId) {
		this.secId = secId;
	}
	public String getInfracConPlaca() {
		return infracConPlaca;
	}
	public void setInfracConPlaca(String infracConPlaca) {
		this.infracConPlaca = infracConPlaca;
	}
	public Integer getInfracPlacaEdo() {
		return infracPlacaEdo;
	}
	public void setInfracPlacaEdo(Integer infracPlacaEdo) {
		this.infracPlacaEdo = infracPlacaEdo;
	}
	public String getInfracPlaca() {
		return infracPlaca;
	}
	public void setInfracPlaca(String infracPlaca) {
		this.infracPlaca = infracPlaca;
	}
	public String getInfracIPaterno() {
		return infracIPaterno;
	}
	public void setInfracIPaterno(String infracIPaterno) {
		this.infracIPaterno = infracIPaterno;
	}
	public String getInfracIMaterno() {
		return infracIMaterno;
	}
	public void setInfracIMaterno(String infracIMaterno) {
		this.infracIMaterno = infracIMaterno;
	}
	public String getInfracINombre() {
		return infracINombre;
	}
	public void setInfracINombre(String infracINombre) {
		this.infracINombre = infracINombre;
	}
	public String getInfracICalle() {
		return infracICalle;
	}
	public void setInfracICalle(String infracICalle) {
		this.infracICalle = infracICalle;
	}
	public String getInfracINumExt() {
		return infracINumExt;
	}
	public void setInfracINumExt(String infracINumExt) {
		this.infracINumExt = infracINumExt;
	}
	public String getInfracINumInt() {
		return infracINumInt;
	}
	public void setInfracINumInt(String infracINumInt) {
		this.infracINumInt = infracINumInt;
	}
	public String getInfracIColonia() {
		return infracIColonia;
	}
	public void setInfracIColonia(String infracIColonia) {
		this.infracIColonia = infracIColonia;
	}
	public Integer getInfracIedoId() {
		return infracIedoId;
	}
	public void setInfracIedoId(Integer infracIedoId) {
		this.infracIedoId = infracIedoId;
	}
	public Integer getInfracIDelId() {
		return infracIDelId;
	}
	public void setInfracIDelId(Integer infracIDelId) {
		this.infracIDelId = infracIDelId;
	}
	public String getInfracLicencia() {
		return infracLicencia;
	}
	public void setInfracLicencia(String infracLicencia) {
		this.infracLicencia = infracLicencia;
	}
	public Integer getTipoLId() {
		return tipoLId;
	}
	public void setTipoLId(Integer tipoLId) {
		this.tipoLId = tipoLId;
	}
	public String getInfracLServPublico() {
		return infracLServPublico;
	}
	public void setInfracLServPublico(String infracLServPublico) {
		this.infracLServPublico = infracLServPublico;
	}
	public Integer getInfracLicEdo() {
		return infracLicEdo;
	}
	public void setInfracLicEdo(Integer infracLicEdo) {
		this.infracLicEdo = infracLicEdo;
	}
	public Integer getvMarId() {
		return vMarId;
	}
	public void setvMarId(Integer vMarId) {
		this.vMarId = vMarId;
	}
	public Integer getvModId() {
		return vModId;
	}
	public void setvModId(Integer vModId) {
		this.vModId = vModId;
	}
	public Integer getvColorId() {
		return vColorId;
	}
	public void setvColorId(Integer vColorId) {
		this.vColorId = vColorId;
	}
	public String getvOrigen() {
		return vOrigen;
	}
	public void setvOrigen(String vOrigen) {
		this.vOrigen = vOrigen;
	}
	public String getvTarjetaCirculacion() {
		return vTarjetaCirculacion;
	}
	public void setvTarjetaCirculacion(String vTarjetaCirculacion) {
		this.vTarjetaCirculacion = vTarjetaCirculacion;
	}
	public Integer getvTipoId() {
		return vTipoId;
	}
	public void setvTipoId(Integer vTipoId) {
		this.vTipoId = vTipoId;
	}
	public String getArtMotivacion() {
		return artMotivacion;
	}
	public void setArtMotivacion(String artMotivacion) {
		this.artMotivacion = artMotivacion;
	}
	public String getInfracMEnLaCalle() {
		return infracMEnLaCalle;
	}
	public void setInfracMEnLaCalle(String infracMEnLaCalle) {
		this.infracMEnLaCalle = infracMEnLaCalle;
	}
	public String getInfracMEntreCalle() {
		return infracMEntreCalle;
	}
	public void setInfracMEntreCalle(String infracMEntreCalle) {
		this.infracMEntreCalle = infracMEntreCalle;
	}
	public String getInfracMYLaCalle() {
		return infracMYLaCalle;
	}
	public void setInfracMYLaCalle(String infracMYLaCalle) {
		this.infracMYLaCalle = infracMYLaCalle;
	}
	public String getInfracMColonia() {
		return infracMColonia;
	}
	public void setInfracMColonia(String infracMColonia) {
		this.infracMColonia = infracMColonia;
	}
	public Integer getInfracMEdoId() {
		return infracMEdoId;
	}
	public void setInfracMEdoId(Integer infracMEdoId) {
		this.infracMEdoId = infracMEdoId;
	}
	public Integer getInfracMDelId() {
		return infracMDelId;
	}
	public void setInfracMDelId(Integer infracMDelId) {
		this.infracMDelId = infracMDelId;
	}
	public Integer getArtId() {
		return artId;
	}
	public void setArtId(Integer artId) {
		this.artId = artId;
	}
	public String getInfracLeyTransporte() {
		return infracLeyTransporte;
	}
	public void setInfracLeyTransporte(String infracLeyTransporte) {
		this.infracLeyTransporte = infracLeyTransporte;
	}
	public Integer getSancionArtId() {
		return sancionArtId;
	}
	public void setSancionArtId(Integer sancionArtId) {
		this.sancionArtId = sancionArtId;
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
	public Integer getGruaId() {
		return gruaId;
	}
	public void setGruaId(Integer gruaId) {
		this.gruaId = gruaId;
	}
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	public Integer getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(Integer empleadoId) {
		this.empleadoId = empleadoId;
	}
	public Integer getEmp_Sector() {
		return emp_Sector;
	}
	public void setEmp_Sector(Integer emp_Sector) {
		this.emp_Sector = emp_Sector;
	}
	public Integer getEmpAgrup() {
		return empAgrup;
	}
	public void setEmpAgrup(Integer empAgrup) {
		this.empAgrup = empAgrup;
	}
	public Integer getrVehId() {
		return rVehId;
	}
	public void setrVehId(Integer rVehId) {
		this.rVehId = rVehId;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getInfracNumCtrl() {
		return infracNumCtrl;
	}
	public void setInfracNumCtrl(String infracNumCtrl) {
		this.infracNumCtrl = infracNumCtrl;
	}
	public Integer getUtId() {
		return utId;
	}
	public void setUtId(Integer utId) {
		this.utId = utId;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Integer modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getEmpleadoCod() {
		return empleadoCod;
	}
	public void setEmpleadoCod(String empleadoCod) {
		this.empleadoCod = empleadoCod;
	}
	public String getEmpAgrupamiento() {
		return empAgrupamiento;
	}
	public void setEmpAgrupamiento(String empAgrupamiento) {
		this.empAgrupamiento = empAgrupamiento;
	}
	public String getEmpPat() {
		return empPat;
	}
	public void setEmpPat(String empPat) {
		this.empPat = empPat;
	}
	public String getEmpMaterno() {
		return empMaterno;
	}
	public void setEmpMaterno(String empMaterno) {
		this.empMaterno = empMaterno;
	}
	public String getEmpNombre() {
		return empNombre;
	}
	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}
	public String getInfracCaptura() {
		return infracCaptura;
	}
	public void setInfracCaptura(String infracCaptura) {
		this.infracCaptura = infracCaptura;
	}
	public Integer getInfracApoyoGrua() {
		return infracApoyoGrua;
	}
	public void setInfracApoyoGrua(Integer infracApoyoGrua) {
		this.infracApoyoGrua = infracApoyoGrua;
	}
	public String getInfracRfc() {
		return infracRfc;
	}
	public void setInfracRfc(String infracRfc) {
		this.infracRfc = infracRfc;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getInfracObservacion() {
		return infracObservacion;
	}
	public void setInfracObservacion(String infracObservacion) {
		this.infracObservacion = infracObservacion;
	}
	public String getMotivoCambio() {
		return motivoCambio;
	}
	public void setMotivoCambio(String motivoCambio) {
		this.motivoCambio = motivoCambio;
	}
	public String getInfracConDireccion() {
		return infracConDireccion;
	}
	public void setInfracConDireccion(String infracConDireccion) {
		this.infracConDireccion = infracConDireccion;
	}
	public String getInfracFrenteAlNumero() {
		return infracFrenteAlNumero;
	}
	public void setInfracFrenteAlNumero(String infracFrenteAlNumero) {
		this.infracFrenteAlNumero = infracFrenteAlNumero;
	}
	public Integer getObserveQue() {
		return observeQue;
	}
	public void setObserveQue(Integer observeQue) {
		this.observeQue = observeQue;
	}
	public Integer getGarantiaTipoId() {
		return garantiaTipoId;
	}
	public void setGarantiaTipoId(Integer garantiaTipoId) {
		this.garantiaTipoId = garantiaTipoId;
	}
	public AltaInfraccionSPVO getAltaInfraccionSPVO() {
		return altaInfraccionSPVO;
	}
	public void setAltaInfraccionSPVO(AltaInfraccionSPVO altaInfraccionSPVO) {
		this.altaInfraccionSPVO = altaInfraccionSPVO;
	}
	public String getGarantiaFolio() {
		return garantiaFolio;
	}
	public void setGarantiaFolio(String garantiaFolio) {
		this.garantiaFolio = garantiaFolio;
	}
	public void generaParametrosParaSP(){
		this.altaInfraccionSPVO = 
				new AltaInfraccionSPVO(
						infracImpresa, 	
						secId,
						infracConPlaca,
						infracPlacaEdo,
						infracPlaca,
						infracIPaterno,
						infracIMaterno,
						infracINombre,
						infracICalle,
						infracINumExt,
						infracINumInt,
						infracIColonia,
						infracIedoId,
						infracIDelId,
						infracLicencia,
						tipoLId,
						infracLServPublico ,
						infracLicEdo ,
						vMarId,	
						vModId,
						vColorId,
						vOrigen,
						vTarjetaCirculacion,
						vTipoId,
						artMotivacion,	
						infracMEnLaCalle,
						infracMEntreCalle,	
						infracMYLaCalle,
						infracMColonia ,	
						infracMEdoId,
						infracMDelId,
						artId,
						infracLeyTransporte,
						sancionArtId,
						infracArrastre,
						infracNumArrastre,
						infracTipoArrastre,	
						gruaId,
						depId, 	
						empleadoId,
						emp_Sector,  	
						empAgrup,
						rVehId,	
						oper,
						infracNumCtrl,	
						utId,
						fecha,
						modificadoPor,
						resultado,
						mensaje,
						empleadoCod,
						empAgrupamiento,
						empPat,
						empMaterno,
						empNombre,
						infracCaptura,
						infracApoyoGrua,
						infracRfc,
						fechaEmision,
						infracObservacion,
						motivoCambio,
						infracConDireccion,
						infracFrenteAlNumero,
						observeQue,
						garantiaTipoId,
						garantiaFolio
					);
	}
	
}
