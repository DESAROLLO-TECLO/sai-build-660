package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatConDireccionVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatObserveQueVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.DelegacionDescAndIdVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.EstadoSinDelegacionesVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.EstadoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.GarantiasCatDocumentosVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.ResponsableVehiculoDescAndIdVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.SectorSinUnidadTerritorialVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoColorDescAndIdVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoColorVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoMarcaDescAndIdVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoTipoDescAndIdVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;

public class InfraccionAllDataVO {
	
	private List<EstadoSinDelegacionesVO> edoSinDelegacionCatalgo;
	
	private List<ResponsableVehiculoDescAndIdVO> responsableVehiculoCatalogo;
	
	private List<FilterValuesVO> tipoArrastreCatalogo;
	
	private List<FilterValuesVO> tipoUnidadCatalogo;
	
	private List<CatObserveQueVO> observeQueCatalogo;
	
	private List<CatConDireccionVO> conDireccionCatalogo;
	
	private List<DelegacionDescAndIdVO> delegacionPorEstadoCatalogo;
	
	private List<VehiculoTipoDescAndIdVO> vehiculoTipoCatalogo;
	
	private List<VehiculoMarcaDescAndIdVO> vehiculoMarcaCatalogo;

	private List<VehiculoColorDescAndIdVO> vehiculoColorCatalogo;
	
	private List<EstadoVO> estadoCatalogo;
	
	private List<GarantiasCatDocumentosVO> garantiasCatDocumentosCatalogo;
	
	private List<TipoLicenciaVO> tipoLicenciaCatalogo;
	
	private List<SectorSinUnidadTerritorialVO> sectorSinUTCatalogo;
	
	public List<EstadoSinDelegacionesVO> getEdoSinDelegacionCatalgo() {
		return edoSinDelegacionCatalgo;
	}

	public void setEdoSinDelegacionCatalgo(List<EstadoSinDelegacionesVO> edoSinDelegacionCatalgo) {
		this.edoSinDelegacionCatalgo = edoSinDelegacionCatalgo;
	}

	public List<ResponsableVehiculoDescAndIdVO> getResponsableVehiculoCatalogo() {
		return responsableVehiculoCatalogo;
	}

	public void setResponsableVehiculoCatalogo(List<ResponsableVehiculoDescAndIdVO> responsableVehiculoCatalogo) {
		this.responsableVehiculoCatalogo = responsableVehiculoCatalogo;
	}

	public List<FilterValuesVO> getTipoArrastreCatalogo() {
		return tipoArrastreCatalogo;
	}

	public void setTipoArrastreCatalogo(List<FilterValuesVO> tipoArrastreCatalogo) {
		this.tipoArrastreCatalogo = tipoArrastreCatalogo;
	}

	public List<FilterValuesVO> getTipoUnidadCatalogo() {
		return tipoUnidadCatalogo;
	}

	public void setTipoUnidadCatalogo(List<FilterValuesVO> tipoUnidadCatalogo) {
		this.tipoUnidadCatalogo = tipoUnidadCatalogo;
	}

	public List<CatObserveQueVO> getObserveQueCatalogo() {
		return observeQueCatalogo;
	}

	public void setObserveQueCatalogo(List<CatObserveQueVO> observeQueCatalogo) {
		this.observeQueCatalogo = observeQueCatalogo;
	}

	public List<CatConDireccionVO> getConDireccionCatalogo() {
		return conDireccionCatalogo;
	}

	public void setConDireccionCatalogo(List<CatConDireccionVO> conDireccionCatalogo) {
		this.conDireccionCatalogo = conDireccionCatalogo;
	}

	public List<DelegacionDescAndIdVO> getDelegacionPorEstadoCatalogo() {
		return delegacionPorEstadoCatalogo;
	}

	public void setDelegacionPorEstadoCatalogo(List<DelegacionDescAndIdVO> delegacionPorEstadoCatalogo) {
		this.delegacionPorEstadoCatalogo = delegacionPorEstadoCatalogo;
	}

	public List<VehiculoTipoDescAndIdVO> getVehiculoTipoCatalogo() {
		return vehiculoTipoCatalogo;
	}

	public void setVehiculoTipoCatalogo(List<VehiculoTipoDescAndIdVO> vehiculoTipoCatalogo) {
		this.vehiculoTipoCatalogo = vehiculoTipoCatalogo;
	}

	public List<VehiculoMarcaDescAndIdVO> getVehiculoMarcaCatalogo() {
		return vehiculoMarcaCatalogo;
	}

	public void setVehiculoMarcaCatalogo(List<VehiculoMarcaDescAndIdVO> vehiculoMarcaCatalogo) {
		this.vehiculoMarcaCatalogo = vehiculoMarcaCatalogo;
	}

	public List<VehiculoColorDescAndIdVO> getVehiculoColorCatalogo() {
		return vehiculoColorCatalogo;
	}

	public void setVehiculoColorCatalogo(List<VehiculoColorDescAndIdVO> vehiculoColorCatalogo) {
		this.vehiculoColorCatalogo = vehiculoColorCatalogo;
	}

	public List<EstadoVO> getEstadoCatalogo() {
		return estadoCatalogo;
	}

	public void setEstadoCatalogo(List<EstadoVO> estadoCatalogo) {
		this.estadoCatalogo = estadoCatalogo;
	}

	public List<GarantiasCatDocumentosVO> getGarantiasCatDocumentosCatalogo() {
		return garantiasCatDocumentosCatalogo;
	}

	public void setGarantiasCatDocumentosCatalogo(List<GarantiasCatDocumentosVO> garantiasCatDocumentosCatalogo) {
		this.garantiasCatDocumentosCatalogo = garantiasCatDocumentosCatalogo;
	}

	public List<TipoLicenciaVO> getTipoLicenciaCatalogo() {
		return tipoLicenciaCatalogo;
	}

	public void setTipoLicenciaCatalogo(List<TipoLicenciaVO> tipoLicenciaCatalogo) {
		this.tipoLicenciaCatalogo = tipoLicenciaCatalogo;
	}

	public List<SectorSinUnidadTerritorialVO> getSectorSinUTCatalogo() {
		return sectorSinUTCatalogo;
	}

	public void setSectorSinUTCatalogo(List<SectorSinUnidadTerritorialVO> sectorSinUTCatalogo) {
		this.sectorSinUTCatalogo = sectorSinUTCatalogo;
	}
	
}
