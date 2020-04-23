package mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana;

import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.empleados.EmpleadosSinPsswdVO;

public class AtenCiudaCamposRequeridosVO {
	
	private Boolean idACTramite;
	private Boolean idTipoTramite;
	private Boolean fhAlta;
	private Boolean nbCiudadano;
	private Boolean nbCPaterno;
	private Boolean nbMaterno;
	private Boolean nbEmpresa;
	private Boolean nuCTelefono;
	private Boolean txCCorreo;
	
	private Boolean txCCalle;
	private Boolean txCColonia;
	private Boolean nuCInt;
	private Boolean nuCExt;
	
	private Boolean idCDelegacion;
	private Boolean nuCCP;
	private Boolean idCEDO;
	private Boolean cdCPlaca;
	private Boolean idMarca;
	private Boolean idModelo;
	private Boolean idColor;
	
	private Boolean idTipoVehiculo;
	private Boolean txHechos;
	private Boolean txCC;
	
	private Boolean idTipoDoc;
    private Boolean nbMarcaOtro;
    private Boolean nbModeloOtro;
    private Boolean nbDocOtro;
    
	private Boolean txMotivoCambio;
	private Boolean cdTipoPersona;
	
	private Boolean folioInfrac;
	private Boolean medioSolicitud;
	
	
	public Boolean getIdACTramite() {
		return idACTramite;
	}
	public void setIdACTramite(Boolean idACTramite) {
		this.idACTramite = idACTramite;
	}
	public Boolean getIdTipoTramite() {
		return idTipoTramite;
	}
	public void setIdTipoTramite(Boolean idTipoTramite) {
		this.idTipoTramite = idTipoTramite;
	}
	public Boolean getFhAlta() {
		return fhAlta;
	}
	public void setFhAlta(Boolean fhAlta) {
		this.fhAlta = fhAlta;
	}
	public Boolean getNbCiudadano() {
		return nbCiudadano;
	}
	public void setNbCiudadano(Boolean nbCiudadano) {
		this.nbCiudadano = nbCiudadano;
	}
	public Boolean getNbCPaterno() {
		return nbCPaterno;
	}
	public void setNbCPaterno(Boolean nbCPaterno) {
		this.nbCPaterno = nbCPaterno;
	}
	public Boolean getNbMaterno() {
		return nbMaterno;
	}
	public void setNbMaterno(Boolean nbMaterno) {
		this.nbMaterno = nbMaterno;
	}
	public Boolean getNbEmpresa() {
		return nbEmpresa;
	}
	public void setNbEmpresa(Boolean nbEmpresa) {
		this.nbEmpresa = nbEmpresa;
	}
	public Boolean getNuCTelefono() {
		return nuCTelefono;
	}
	public void setNuCTelefono(Boolean nuCTelefono) {
		this.nuCTelefono = nuCTelefono;
	}
	public Boolean getTxCCorreo() {
		return txCCorreo;
	}
	public void setTxCCorreo(Boolean txCCorreo) {
		this.txCCorreo = txCCorreo;
	}
	public Boolean getTxCCalle() {
		return txCCalle;
	}
	public void setTxCCalle(Boolean txCCalle) {
		this.txCCalle = txCCalle;
	}
	public Boolean getTxCColonia() {
		return txCColonia;
	}
	public void setTxCColonia(Boolean txCColonia) {
		this.txCColonia = txCColonia;
	}
	public Boolean getNuCInt() {
		return nuCInt;
	}
	public void setNuCInt(Boolean nuCInt) {
		this.nuCInt = nuCInt;
	}
	public Boolean getNuCExt() {
		return nuCExt;
	}
	public void setNuCExt(Boolean nuCExt) {
		this.nuCExt = nuCExt;
	}
	public Boolean getIdCDelegacion() {
		return idCDelegacion;
	}
	public void setIdCDelegacion(Boolean idCDelegacion) {
		this.idCDelegacion = idCDelegacion;
	}
	public Boolean getNuCCP() {
		return nuCCP;
	}
	public void setNuCCP(Boolean nuCCP) {
		this.nuCCP = nuCCP;
	}
	public Boolean getIdCEDO() {
		return idCEDO;
	}
	public void setIdCEDO(Boolean idCEDO) {
		this.idCEDO = idCEDO;
	}
	public Boolean getCdCPlaca() {
		return cdCPlaca;
	}
	public void setCdCPlaca(Boolean cdCPlaca) {
		this.cdCPlaca = cdCPlaca;
	}
	public Boolean getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Boolean idMarca) {
		this.idMarca = idMarca;
	}
	public Boolean getIdModelo() {
		return idModelo;
	}
	public void setIdModelo(Boolean idModelo) {
		this.idModelo = idModelo;
	}
	public Boolean getIdColor() {
		return idColor;
	}
	public void setIdColor(Boolean idColor) {
		this.idColor = idColor;
	}
	public Boolean getIdTipoVehiculo() {
		return idTipoVehiculo;
	}
	public void setIdTipoVehiculo(Boolean idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}
	public Boolean getTxHechos() {
		return txHechos;
	}
	public void setTxHechos(Boolean txHechos) {
		this.txHechos = txHechos;
	}
	public Boolean getTxCC() {
		return txCC;
	}
	public void setTxCC(Boolean txCC) {
		this.txCC = txCC;
	}
	public Boolean getIdTipoDoc() {
		return idTipoDoc;
	}
	public void setIdTipoDoc(Boolean idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}
	public Boolean getNbMarcaOtro() {
		return nbMarcaOtro;
	}
	public void setNbMarcaOtro(Boolean nbMarcaOtro) {
		this.nbMarcaOtro = nbMarcaOtro;
	}
	public Boolean getNbModeloOtro() {
		return nbModeloOtro;
	}
	public void setNbModeloOtro(Boolean nbModeloOtro) {
		this.nbModeloOtro = nbModeloOtro;
	}
	public Boolean getNbDocOtro() {
		return nbDocOtro;
	}
	public void setNbDocOtro(Boolean nbDocOtro) {
		this.nbDocOtro = nbDocOtro;
	}
	public Boolean getTxMotivoCambio() {
		return txMotivoCambio;
	}
	public void setTxMotivoCambio(Boolean txMotivoCambio) {
		this.txMotivoCambio = txMotivoCambio;
	}
	public Boolean getCdTipoPersona() {
		return cdTipoPersona;
	}
	public void setCdTipoPersona(Boolean cdTipoPersona) {
		this.cdTipoPersona = cdTipoPersona;
	}
	public Boolean getFolioInfrac() {
		return folioInfrac;
	}
	public void setFolioInfrac(Boolean folioInfrac) {
		this.folioInfrac = folioInfrac;
	}
	public Boolean getMedioSolicitud() {
		return medioSolicitud;
	}
	public void setMedioSolicitud(Boolean medioSolicitud) {
		this.medioSolicitud = medioSolicitud;
	}
	
	
	
	
	

}
