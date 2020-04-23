package mx.com.teclo.saicdmx.api.rest.catalogo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import mx.com.teclo.saicdmx.negocio.service.atencionCiudadana.AtencionCiudadanaService;
import mx.com.teclo.saicdmx.negocio.service.catalogos.CatalogoService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.fm.FMCrearLotesService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticuloDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticulosInfraccionesFinanzasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AgrupamientosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AreaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AreaOperativaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.BancoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatConDireccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatObserveQueDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoTramiteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatalogoWebDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatalogoWebOpcionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CategoriaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CausaIngresoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CausalesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ComponentesInventarioDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ConceptoPagoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ConceptosSoporteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ConcesionariaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DelegacionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DenominacionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EntidadPagoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EstadoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EstatusInfraccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EventoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GarantiasCatDocumentosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GruaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GruaStatusDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ProgramaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionDepositoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionalesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ResponsableVehiculoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoAlarmaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoEmpleadoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoIngresoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoLicenciaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoRadarDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoSuministroDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TurnoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.UnidadTerritorialDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoColorDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoMarcaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoModeloDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoSubTipoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoTipoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ZonaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ZonaServicioDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.AgrupamientosVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.AreaOperativaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.AreaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.BancoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatConDireccionVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatObserveQueVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatTipoTramiteVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatalogoWebOpcionVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatalogosWebVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CategoriaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CausaIngresoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CausalesVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.ComponentesInventarioVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.ConceptoPagoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.ConcesionariaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.DelegacionVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.DenominacionVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.EntidadPagoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.EstadoSinDelegacionesVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.EstadoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.EstatusInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.EventoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.GarantiasCatDocumentosVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.GruaStatusVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.GruaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.ProgramaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.RegionDepositoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.RegionVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.RegionalesVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.ResponsableVehiculoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.SectorSinUnidadTerritorialVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.SectorVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoAlarmaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoBusquedaAtcVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoEmpleadoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoIngresoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoRadarVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoSuministroVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TurnoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.UnidadTerritorialVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoColorVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoMarcaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoModeloVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoSubTipoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoTipoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.ZonaServicioVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.ZonaVO;
import mx.com.teclo.saicdmx.persistencia.vo.articulos.ArticuloVO;
import mx.com.teclo.saicdmx.persistencia.vo.articulos.ArticulosInfraccionesFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.EstatusSeguimientoTramiteACVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.ConceptosSoporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudAgrupamientoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudAreaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudArticuloVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudArticulosHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudBancoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCategoriaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCausaIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCausalesVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudComponenteVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudConceptoPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudConcesionariaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDelegacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDenominacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEntidadPagosVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEstadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEstatusInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEventoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudGruaStatusVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudGruaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudProgramaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudRegionDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudRegionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudSectorVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoAlarmaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoEmpleadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTurnoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudUnidadeTerritorialVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoColorVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoMarcaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoModeloVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoResponsableVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoSubTipoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoTipoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudZonaServicioVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudZonaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.RespuestaBusquedaDelegacionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.RespuestaBusquedaDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.RespuestaBusquedaSectoresVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.ComboValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialRecibeVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMMarcaDispositivoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMOrigenPlacaFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoArchivoFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoFotocivicaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTiposDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListCausaIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListInventarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListMediosTraspVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListOperativoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListaNoGruasVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.OpcionConInfraccionVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class CatalogoRestController {

	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private FMCrearLotesService crearLotesService;
	
	@Autowired
	private AtencionCiudadanaService atencionCiudadanaService;	
	
	@RequestMapping(value = "/ingresos/tipos", method = RequestMethod.GET)
	public ResponseEntity<List<TipoIngresoVO>> buscarTiposIngreso() {
		List<TipoIngresoDTO> listaTipoIngresoDTO = catalogoService.buscarTipoIngreso();
		if (listaTipoIngresoDTO.isEmpty()) {
			return new ResponseEntity<List<TipoIngresoVO>>(HttpStatus.NOT_FOUND);
		}
		List<TipoIngresoVO> listaTipoIngresoVO = ResponseConverter.converterLista(new ArrayList<>(), listaTipoIngresoDTO, TipoIngresoVO.class);
		return new ResponseEntity<List<TipoIngresoVO>>(listaTipoIngresoVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/estadosTodos", method = RequestMethod.GET)
	public ResponseEntity<List<EstadoVO>> buscarTodosLosEstados() {
		List<EstadoDTO> estados = catalogoService.buscarEstadosTodos();
		if (estados.isEmpty()) {
			return new ResponseEntity<List<EstadoVO>>(HttpStatus.NOT_FOUND);
		}
		List<EstadoVO> estadosVO = ResponseConverter.converterLista(new ArrayList<>(), estados, EstadoVO.class);
		return new ResponseEntity<List<EstadoVO>>(estadosVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/estados", method = RequestMethod.GET)
	public ResponseEntity<List<EstadoSinDelegacionesVO>> buscarEstadoPorCodigo(@RequestParam(value = "codigo") String codigo)
			throws NotFoundException {
		List<EstadoDTO> estadosDTO = null;
		estadosDTO = catalogoService.buscarEstadoPorCodigo(codigo);
		if (estadosDTO == null) {
			return new ResponseEntity<List<EstadoSinDelegacionesVO>>(HttpStatus.NOT_FOUND);
		}
		List<EstadoSinDelegacionesVO> estadosVO = new ArrayList<EstadoSinDelegacionesVO>();
		estadosVO = ResponseConverter.converterLista(new ArrayList<>(), estadosDTO,
				EstadoSinDelegacionesVO.class);
		return new ResponseEntity<List<EstadoSinDelegacionesVO>>(estadosVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/sector", method = RequestMethod.GET)
	public ResponseEntity<List<SectorSinUnidadTerritorialVO>> buscarSectoresPorEstado(
			@RequestParam(value = "estadoId") Long estadoId) throws NotFoundException {
		List<SectorDTO> sectoresDTO = new ArrayList<SectorDTO>();
		sectoresDTO = catalogoService.buscarSectoresPorEstado(estadoId, "A");
		if (sectoresDTO == null) {
			return new ResponseEntity<List<SectorSinUnidadTerritorialVO>>(HttpStatus.NOT_FOUND);
		}
		List<SectorSinUnidadTerritorialVO> sectoresVO = new ArrayList<SectorSinUnidadTerritorialVO>();
		sectoresVO = ResponseConverter.converterLista(new ArrayList<>(), sectoresDTO,
				SectorSinUnidadTerritorialVO.class);
		return new ResponseEntity<List<SectorSinUnidadTerritorialVO>>(sectoresVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/delegaciones", method = RequestMethod.GET)
	public ResponseEntity<List<DelegacionVO>> buscarDelegacionesPorEstado(
			@RequestParam(value = "estadoId") Long estadoId) throws NotFoundException {
		List<DelegacionDTO> delegacionDTO = new ArrayList<DelegacionDTO>();
		delegacionDTO = catalogoService.buscarDelegacionPorEstado(estadoId);
		if (delegacionDTO == null) {
			return new ResponseEntity<List<DelegacionVO>>(HttpStatus.NOT_FOUND);
		}
		List<DelegacionVO> delegacionVO = new ArrayList<DelegacionVO>();
		delegacionVO = ResponseConverter.converterLista(new ArrayList<>(), delegacionDTO, DelegacionVO.class);
		return new ResponseEntity<List<DelegacionVO>>(delegacionVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/unidadTerritorial", method = RequestMethod.GET)
	public ResponseEntity<List<UnidadTerritorialVO>> buscarUnidadTerritorialPorSector(
			@RequestParam(value = "sectorId") Long sectorId) throws NotFoundException {
		List<UnidadTerritorialDTO> unidadesDTO = new ArrayList<UnidadTerritorialDTO>();
		unidadesDTO = catalogoService.buscarUnidadesTerritorialesPorSector(sectorId);
		if (unidadesDTO == null) {
			return new ResponseEntity<List<UnidadTerritorialVO>>(HttpStatus.NOT_FOUND);
		}
		List<UnidadTerritorialVO> unidadesVO = new ArrayList<UnidadTerritorialVO>();
		unidadesVO = ResponseConverter.converterLista(new ArrayList<>(), unidadesDTO, UnidadTerritorialVO.class);
		return new ResponseEntity<List<UnidadTerritorialVO>>(unidadesVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/catalogoResponsableVehiculo", method = RequestMethod.GET)
	public ResponseEntity<List<ResponsableVehiculoVO>> buscarResponsableVehiculoConEstatusActivo()
			throws NotFoundException {
		List<ResponsableVehiculoDTO> catalogoResponsableVehiculoDTO = new ArrayList<ResponsableVehiculoDTO>();
		catalogoResponsableVehiculoDTO = catalogoService.buscarResponsableVehiculoConEstatusActivo();
		if (catalogoResponsableVehiculoDTO == null) {
			return new ResponseEntity<List<ResponsableVehiculoVO>>(HttpStatus.NOT_FOUND);
		}
		List<ResponsableVehiculoVO> catalogoResponsableVehiculoVO = new ArrayList<ResponsableVehiculoVO>();
		catalogoResponsableVehiculoVO = ResponseConverter.converterLista(new ArrayList<>(),
				catalogoResponsableVehiculoDTO, ResponsableVehiculoVO.class);
		return new ResponseEntity<List<ResponsableVehiculoVO>>(catalogoResponsableVehiculoVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/grua", method = RequestMethod.GET)
	public ResponseEntity<GruaVO> buscarGruaActivaPorCodigo(@RequestParam(value = "codigo") String Codigo)
			throws NotFoundException {
		GruaDTO gruaDTO = new GruaDTO();
		gruaDTO = catalogoService.buscarGrua(Codigo);
		if (gruaDTO == null) {
			return new ResponseEntity<GruaVO>(HttpStatus.NOT_FOUND);
		}
		GruaVO gruaVO = new GruaVO();
		ResponseConverter.copiarPropriedades(gruaVO, gruaDTO);
		return new ResponseEntity<GruaVO>(gruaVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/tipoArrastreCatalogo", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> buscartipoArrastreCatalogo() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.filtroInfraccionTipoArrastre(), HttpStatus.OK);
	}

	@RequestMapping(value = "/tipoUnidadCatalogo", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> buscarFiltroInfraccionTipoUnidad() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.filtroInfraccionTipoUnidad(), HttpStatus.OK);
	}

	@RequestMapping(value = "/conDireccion", method = RequestMethod.GET)
	public ResponseEntity<List<CatConDireccionVO>> buscarTodasCatConDirecciones() throws NotFoundException {
		List<CatConDireccionDTO> direccionesDTO = catalogoService.todasCatConDireccion();
		List<CatConDireccionVO> direccionesVO = new ArrayList<CatConDireccionVO>();
		direccionesVO = ResponseConverter.converterLista(new ArrayList<>(), direccionesDTO, CatConDireccionVO.class);
		return new ResponseEntity<List<CatConDireccionVO>>(direccionesVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/observeQue", method = RequestMethod.GET)
	public ResponseEntity<List<CatObserveQueVO>> buscartTodasObserveQue() throws NotFoundException {
		List<CatObserveQueDTO> catalogoObserveQueDTO = catalogoService.todasObserveQue();
		List<CatObserveQueVO> catalogoObserveQueVO = new ArrayList<CatObserveQueVO>();
		catalogoObserveQueVO = ResponseConverter.converterLista(new ArrayList<>(), catalogoObserveQueDTO,
				CatObserveQueVO.class);
		return new ResponseEntity<List<CatObserveQueVO>>(catalogoObserveQueVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/catalogoTipoVehiculo", method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoTipoVO>> buscarTipoVehiculsActivos() throws NotFoundException {
		List<VehiculoTipoDTO> tipoVehiculsActivosDTO = new ArrayList<VehiculoTipoDTO>();
		tipoVehiculsActivosDTO = catalogoService.buscarTodosVehiculoTipoActivo();
		if (tipoVehiculsActivosDTO == null) {
			return new ResponseEntity<List<VehiculoTipoVO>>(HttpStatus.NOT_FOUND);
		}
		List<VehiculoTipoVO> tipoVehiculsActivosVO = new ArrayList<VehiculoTipoVO>();
		tipoVehiculsActivosVO = ResponseConverter.converterLista(new ArrayList<>(), tipoVehiculsActivosDTO,
				VehiculoTipoVO.class);
		return new ResponseEntity<List<VehiculoTipoVO>>(tipoVehiculsActivosVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/marcasVehiculos", method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoMarcaVO>> buscarMarcasVehiculosActivas() throws NotFoundException {
		List<VehiculoMarcaDTO> vehiculoMarcaDTO = new ArrayList<VehiculoMarcaDTO>();
		vehiculoMarcaDTO = catalogoService.buscarMarcaVehiculo();
		if (vehiculoMarcaDTO == null) {
			return new ResponseEntity<List<VehiculoMarcaVO>>(HttpStatus.NOT_FOUND);
		}
		List<VehiculoMarcaVO> vehiculoMarcaVO = new ArrayList<VehiculoMarcaVO>();
		vehiculoMarcaVO = ResponseConverter.converterLista(new ArrayList<>(), vehiculoMarcaDTO, VehiculoMarcaVO.class);
		return new ResponseEntity<List<VehiculoMarcaVO>>(vehiculoMarcaVO, HttpStatus.OK);
	}

	@RequestMapping(value="/colorVehiculo",method=RequestMethod.GET)
	public ResponseEntity<List<VehiculoColorVO>>buscarColorVehiculo()throws NotFoundException{
		List<VehiculoColorDTO> vehiculoColorDTO = new ArrayList<VehiculoColorDTO>();
		vehiculoColorDTO = catalogoService.buscarColorVehiculo();
		if (vehiculoColorDTO == null){
			return new ResponseEntity<List<VehiculoColorVO>>(HttpStatus.NOT_FOUND);
		}
		List<VehiculoColorVO> vehiculoColorVO = new ArrayList<VehiculoColorVO>();
		vehiculoColorVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				vehiculoColorDTO, VehiculoColorVO.class);
		return new ResponseEntity<List<VehiculoColorVO>>(vehiculoColorVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/modelosVehiculos", method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoModeloVO>> buscarModeloVehiculoActivoPorMarca(@RequestParam(value = "marca") Long marcaId) throws NotFoundException {
		List<VehiculoModeloDTO> vehiculoModeloDTO = new ArrayList<VehiculoModeloDTO>();
		vehiculoModeloDTO = catalogoService.buscarModeloVehiculoActivoPorMarca(marcaId);
		if (vehiculoModeloDTO == null){
			return new ResponseEntity<List<VehiculoModeloVO>>(HttpStatus.NOT_FOUND);
		}
		List<VehiculoModeloVO> vehiculoModeloVO = new ArrayList<VehiculoModeloVO>();
		vehiculoModeloVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				vehiculoModeloDTO, VehiculoModeloVO.class);
		return new ResponseEntity<List<VehiculoModeloVO>>(vehiculoModeloVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/modelosVehiculos/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoModeloVO>> buscarModeloVehiculoActivoPorMarcaTodos(@PathVariable("id")  Long marcaId) throws NotFoundException {
		List<VehiculoModeloDTO> vehiculoModeloDTO = new ArrayList<VehiculoModeloDTO>();
		vehiculoModeloDTO = catalogoService.buscarModeloVehiculoActivoPorMarcaTodos(marcaId);
		if (vehiculoModeloDTO == null){
			return new ResponseEntity<List<VehiculoModeloVO>>(HttpStatus.NOT_FOUND);
		}
		List<VehiculoModeloVO> vehiculoModeloVO = new ArrayList<VehiculoModeloVO>();
		vehiculoModeloVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				vehiculoModeloDTO, VehiculoModeloVO.class);
		return new ResponseEntity<List<VehiculoModeloVO>>(vehiculoModeloVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipoLicencia", method = RequestMethod.GET)
	public ResponseEntity<List<TipoLicenciaVO>> buscarTiposLicencias() throws NotFoundException {
		List<TipoLicenciaDTO> tipoLicenciaDTO = new ArrayList<TipoLicenciaDTO>();
		tipoLicenciaDTO = catalogoService.buscarTipoLicencia();
		if (tipoLicenciaDTO == null){
			return new ResponseEntity<List<TipoLicenciaVO>>(HttpStatus.NOT_FOUND);
		}
		List<TipoLicenciaVO> tipoLicenciaVO = new ArrayList<TipoLicenciaVO>();
		tipoLicenciaVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				tipoLicenciaDTO, TipoLicenciaVO.class);
		return new ResponseEntity<List<TipoLicenciaVO>>(tipoLicenciaVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/garantiasCatDocumentos", method = RequestMethod.GET)
	public ResponseEntity<List<GarantiasCatDocumentosVO>> buscarTodasGarantiasCatDocumentosDTO() throws NotFoundException {
		List<GarantiasCatDocumentosDTO> garantiasCatDocumentosDTO = new ArrayList<GarantiasCatDocumentosDTO>();
		garantiasCatDocumentosDTO = catalogoService.buscarTodasGarantiasCatDocumentosDTO();
		if (garantiasCatDocumentosDTO == null){
			return new ResponseEntity<List<GarantiasCatDocumentosVO>>(HttpStatus.NOT_FOUND);
		}
		List<GarantiasCatDocumentosVO> garantiasCatDocumentosVO = new ArrayList<GarantiasCatDocumentosVO>();
		garantiasCatDocumentosVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				garantiasCatDocumentosDTO, GarantiasCatDocumentosVO.class);
		return new ResponseEntity<List<GarantiasCatDocumentosVO>>(garantiasCatDocumentosVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipoSuministro", method = RequestMethod.GET)
	public ResponseEntity<List<TipoSuministroVO>> buscarTipoSuministro() throws NotFoundException {
		List<TipoSuministroDTO> tipoSuministroDTO = new ArrayList<TipoSuministroDTO>();
		tipoSuministroDTO = catalogoService.buscarTipoSuministro();
		if (tipoSuministroDTO == null){
			return new ResponseEntity<List<TipoSuministroVO>>(HttpStatus.NOT_FOUND);
		}
		List<TipoSuministroVO> tipoSuministroVO = new ArrayList<TipoSuministroVO>();
		tipoSuministroVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				tipoSuministroDTO, TipoSuministroVO.class);
		return new ResponseEntity<List<TipoSuministroVO>>(tipoSuministroVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/regionales", method = RequestMethod.GET)
	public ResponseEntity<List<RegionalesVO>> buscarRegionales() throws NotFoundException {
		List<RegionalesDTO> regionalesDTO = new ArrayList<RegionalesDTO>();
		regionalesDTO = catalogoService.buscarRegionales();
		if (regionalesDTO == null){
			return new ResponseEntity<List<RegionalesVO>>(HttpStatus.NOT_FOUND);
		}
		List<RegionalesVO> regionalesVO = new ArrayList<RegionalesVO>();
		regionalesVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				regionalesDTO, RegionalesVO.class);
		return new ResponseEntity<List<RegionalesVO>>(regionalesVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/areaOperativa", method = RequestMethod.GET)
	public ResponseEntity<List<AreaOperativaVO>> buscarAreaOperativa(
		@RequestParam(value = "reg_id") Long reg_id) throws NotFoundException {
		List<AreaOperativaDTO> areaOperativaDTO = new ArrayList<AreaOperativaDTO>();
		areaOperativaDTO = catalogoService.buscarAreaOperativa(reg_id);
		if (areaOperativaDTO == null) {
			return new ResponseEntity<List<AreaOperativaVO>>(HttpStatus.NOT_FOUND);
		}
		List<AreaOperativaVO> areaOperativaVO = new ArrayList<AreaOperativaVO>();
		areaOperativaVO = ResponseConverter.converterLista(new ArrayList<>(), areaOperativaDTO,
				AreaOperativaVO.class);
		return new ResponseEntity<List<AreaOperativaVO>>(areaOperativaVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/oficiales", method = RequestMethod.GET)
	public ResponseEntity<List<OficialRecibeVO>> buscarOficiales(
		@RequestParam(value = "reg_id" ) Long reg_id, @RequestParam(value = "upc_id" ) Long upc_id) throws NotFoundException {
		List<OficialRecibeVO> oficiales = null;
		oficiales = catalogoService.buscarOficiales(reg_id, upc_id);
		if (oficiales.isEmpty()) {
			throw new NotFoundException("No se encontraron Oficiales");
		}	
		return new ResponseEntity<List<OficialRecibeVO>>(oficiales, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/auxiliares", method = RequestMethod.GET)
	public ResponseEntity<List<DelegadoAuxiliarVO>> buscarAuxiliar(
		@RequestParam(value = "reg_id" ) Long reg_id, @RequestParam(value = "upc_id" ) Long upc_id) throws NotFoundException {
		List<DelegadoAuxiliarVO> auxiliares = null;
		auxiliares = catalogoService.buscarAuxiliar(reg_id, upc_id);
		if (auxiliares.isEmpty()) {
			throw new NotFoundException("No se encontraron Auxiliares");
		}	
		return new ResponseEntity<List<DelegadoAuxiliarVO>>(auxiliares, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/concesionarias", method = RequestMethod.GET)
	public ResponseEntity<List<ConcesionariaVO>> buscarConcesionarias() throws NotFoundException {
		List<ConcesionariaDTO> concesionarias = catalogoService.buscarConcesionarias();
		if (concesionarias.isEmpty()) {
			throw new NotFoundException("No se encontraron Concesionarias");
		}
		List<ConcesionariaVO> concesionariasVO = ResponseConverter.converterLista(new ArrayList<>(), concesionarias, ConcesionariaVO.class);
		return new ResponseEntity<List<ConcesionariaVO>>(concesionariasVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/concesionarias", method = RequestMethod.POST)
	public ResponseEntity<CrudConcesionariaVO> callCrudConcesionaria(@RequestBody CrudConcesionariaVO concesionariaVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudConcesionaria(concesionariaVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/concesionarias/{id}").buildAndExpand(concesionariaVO.getConcesionariaId()).toUri());
		return new ResponseEntity<CrudConcesionariaVO>(concesionariaVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/gruaStatus", method = RequestMethod.GET)
	public ResponseEntity<List<GruaStatusVO>> buscarGruaStatus() throws NotFoundException {
		List<GruaStatusDTO> gruaStatus = catalogoService.buscarGruaStatus();
		if (gruaStatus.isEmpty()) {
			throw new NotFoundException("No se encontraron Grua Status");
		}
		List<GruaStatusVO> gruaStatusVO = new ArrayList<GruaStatusVO>();
		gruaStatusVO = ResponseConverter.converterLista(new ArrayList<>(), gruaStatus, GruaStatusVO.class);
		return new ResponseEntity<List<GruaStatusVO>>(gruaStatusVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gruaStatus", method = RequestMethod.POST)
	public ResponseEntity<CrudGruaStatusVO> callCrudGruaStatus(@RequestBody CrudGruaStatusVO gruaStatusVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudGruaStatus(gruaStatusVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/gruaStatus/{id}").buildAndExpand(gruaStatusVO.getGruaStatusId()).toUri());
		return new ResponseEntity<CrudGruaStatusVO>(gruaStatusVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/gruas/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<GruaVO>> buscarGruasPorConcesionaria(@PathVariable("id")  Long concesionariaId) throws NotFoundException {
		List<GruaVO> gruas = catalogoService.buscarGruasPorConcesionaria(concesionariaId);
		if (gruas.isEmpty()) {
			throw new NotFoundException("No se encontraron Gruas");
		}
		return new ResponseEntity<List<GruaVO>>(gruas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gruas", method = RequestMethod.POST)
	public ResponseEntity<CrudGruaVO> callCrudGrua(@RequestBody CrudGruaVO crudGruaVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudGrua(crudGruaVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/gruas/{id}").buildAndExpand(crudGruaVO.getGruaId()).toUri());
		return new ResponseEntity<CrudGruaVO>(crudGruaVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/turnos", method = RequestMethod.GET)
	public ResponseEntity<List<TurnoVO>> buscarTurnos() throws NotFoundException {
		List<TurnoDTO> turnosDTO = catalogoService.buscarTurnos();
		if (turnosDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Turnos");
		}
		List<TurnoVO> turnosVO = ResponseConverter.converterLista(new ArrayList<>(), turnosDTO, TurnoVO.class);		
		return new ResponseEntity<List<TurnoVO>>(turnosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/turnos", method = RequestMethod.POST)
	public ResponseEntity<CrudTurnoVO> callCrudTurno(@RequestBody CrudTurnoVO turnoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudTurno(turnoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/turnos/{id}").buildAndExpand(turnoVO.getTurnoId()).toUri());
		return new ResponseEntity<CrudTurnoVO>(turnoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/zonasServicio", method = RequestMethod.GET)
	public ResponseEntity<List<ZonaServicioVO>> buscarZonasServicio() throws NotFoundException {
		List<ZonaServicioDTO> zonasServicioDTO = catalogoService.buscarZonasServicio();
		if (zonasServicioDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron ZonaS Servicio");
		}
		List<ZonaServicioVO> zonasServicioVO = new ArrayList<ZonaServicioVO>();
		zonasServicioVO = ResponseConverter.converterLista(new ArrayList<>(), zonasServicioDTO, ZonaServicioVO.class);
		return new ResponseEntity<List<ZonaServicioVO>>(zonasServicioVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zonasServicio", method = RequestMethod.POST)
	public ResponseEntity<CrudZonaServicioVO> callCrudZonaServicio(@RequestBody CrudZonaServicioVO zonaServicioVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudZonaServicio(zonaServicioVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/zonasServicio/{id}").buildAndExpand(zonaServicioVO.getZonaId()).toUri());
		return new ResponseEntity<CrudZonaServicioVO>(zonaServicioVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/tiposEmpleado", method = RequestMethod.GET)
	public ResponseEntity<List<TipoEmpleadoVO>> buscarTiposEmpleados() throws NotFoundException {
		List<TipoEmpleadoDTO> tiposEmpleadoDTO = catalogoService.buscarTiposEmpleados();
		if (tiposEmpleadoDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Tipos Empleado");
		}
		List<TipoEmpleadoVO> tiposEmpleadoVO = ResponseConverter.converterLista(new ArrayList<>(), tiposEmpleadoDTO, TipoEmpleadoVO.class);
		return new ResponseEntity<List<TipoEmpleadoVO>>(tiposEmpleadoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tiposEmpleado", method = RequestMethod.POST)
	public ResponseEntity<CrudTipoEmpleadoVO> callCrudTipoEmpleado(@RequestBody CrudTipoEmpleadoVO crudTipoEmpleadoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudTipoEmpleado(crudTipoEmpleadoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/tiposEmpleado/{id}").buildAndExpand(crudTipoEmpleadoVO.getEmpTipId()).toUri());
		return new ResponseEntity<CrudTipoEmpleadoVO>(crudTipoEmpleadoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/tiposAlarma", method = RequestMethod.GET)
	public ResponseEntity<List<TipoAlarmaVO>> buscarTiposAlarma() throws NotFoundException {
		List<TipoAlarmaDTO> tiposAlarmaDTO = catalogoService.buscarTiposAlarma();
		if (tiposAlarmaDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Tipos Alarma");
		}
		List<TipoAlarmaVO> tiposAlarmaVO = ResponseConverter.converterLista(new ArrayList<>(), tiposAlarmaDTO, TipoAlarmaVO.class);
		return new ResponseEntity<List<TipoAlarmaVO>>(tiposAlarmaVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tiposAlarma", method = RequestMethod.POST)
	public ResponseEntity<CrudTipoAlarmaVO> callCrudTipoAlarma(@RequestBody CrudTipoAlarmaVO crudTipoAlarmaVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudTipoAlarma(crudTipoAlarmaVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/tiposAlarma/{id}").buildAndExpand(crudTipoAlarmaVO.getAlarmaId()).toUri());
		return new ResponseEntity<CrudTipoAlarmaVO>(crudTipoAlarmaVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/estados", method = RequestMethod.POST)
	public ResponseEntity<CrudEstadoVO> callCrudEstado(@RequestBody CrudEstadoVO crudEstadoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudEstado(crudEstadoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/estados/{id}").buildAndExpand(crudEstadoVO.getEdoId()).toUri());
		return new ResponseEntity<CrudEstadoVO>(crudEstadoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/regiones/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<RegionVO>> buscarRegionesPorEstado(@PathVariable("id")  Long estadoId) throws NotFoundException {
		List<RegionVO> regionesVO = catalogoService.buscarRegionesPorEstado(estadoId);
		if (regionesVO.isEmpty()) {
			throw new NotFoundException("No se encontraron Regiones");
		}
		return new ResponseEntity<List<RegionVO>>(regionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/regiones", method = RequestMethod.POST)
	public ResponseEntity<CrudRegionVO> callCrudRegion(@RequestBody CrudRegionVO crudRegionVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudRegion(crudRegionVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/regiones/{id}").buildAndExpand(crudRegionVO.getRegionId()).toUri());
		return new ResponseEntity<CrudRegionVO>(crudRegionVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/delegacionesTodos/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<RespuestaBusquedaDelegacionesVO>> buscarDelegacionesPorEstadoTodos(@PathVariable("id")  Long estadoId) throws NotFoundException {
		List<RespuestaBusquedaDelegacionesVO> delegacionesVO = catalogoService.buscarDelegacionesPorEstado(estadoId);
		if (delegacionesVO.isEmpty()) {
			return new ResponseEntity<List<RespuestaBusquedaDelegacionesVO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<RespuestaBusquedaDelegacionesVO>>(delegacionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delegaciones", method = RequestMethod.POST)
	public ResponseEntity<CrudDelegacionVO> callCrudDelegacion(@RequestBody CrudDelegacionVO crudDelegacionVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudDelegacion(crudDelegacionVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/delegaciones/{id}").buildAndExpand(crudDelegacionVO.getDelId()).toUri());
		return new ResponseEntity<CrudDelegacionVO>(crudDelegacionVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/unidadTerritorialPorSectorTodos/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<UnidadTerritorialVO>> buscarUnidadTerritorialPorSectorTodos(@PathVariable("id")  Long sectorId) throws NotFoundException {
		List<UnidadTerritorialDTO> unidadesDTO = new ArrayList<UnidadTerritorialDTO>();
		unidadesDTO = catalogoService.buscarUnidadesTerritorialesPorSector(sectorId);
		if (unidadesDTO == null) {
			return new ResponseEntity<List<UnidadTerritorialVO>>(HttpStatus.NOT_FOUND);
		}
		List<UnidadTerritorialVO> unidadesVO = ResponseConverter.converterLista(new ArrayList<>(), unidadesDTO, UnidadTerritorialVO.class);
		return new ResponseEntity<List<UnidadTerritorialVO>>(unidadesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/unidadTerritoriales", method = RequestMethod.POST)
	public ResponseEntity<CrudUnidadeTerritorialVO> callCrudUnidadeTerritorial(@RequestBody CrudUnidadeTerritorialVO crudUnidadeTerritorialVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudUnidadTerritorial(crudUnidadeTerritorialVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/unidadTerritoriales/{id}").buildAndExpand(crudUnidadeTerritorialVO.getUtId().getUtId()).toUri());
		return new ResponseEntity<CrudUnidadeTerritorialVO>(crudUnidadeTerritorialVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/sectorTodosPorDelegacion/{delId}/{edoId}", method = RequestMethod.GET)
	public ResponseEntity<List<RespuestaBusquedaSectoresVO>> buscarSectoresPorDelegacion(@PathVariable("delId")  Long delegacionId, 
			@PathVariable("edoId")  Long edoId) throws NotFoundException {
		List<RespuestaBusquedaSectoresVO> sectoresVO = catalogoService.buscarSectoresPorDelegacion(delegacionId, edoId);
		if (sectoresVO == null) {
			return new ResponseEntity<List<RespuestaBusquedaSectoresVO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<RespuestaBusquedaSectoresVO>>(sectoresVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sectorTodos", method = RequestMethod.GET)
	public ResponseEntity<List<SectorVO>> buscarSectores() throws NotFoundException {
		List<SectorDTO> sectoresDTO = catalogoService.buscarSectores();
		if (sectoresDTO == null) {
			return new ResponseEntity<List<SectorVO>>(HttpStatus.NOT_FOUND);
		}
		List<SectorVO> sectoresVO = ResponseConverter.converterLista(new ArrayList<>(), sectoresDTO, SectorVO.class);
		return new ResponseEntity<List<SectorVO>>(sectoresVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sectores", method = RequestMethod.POST)
	public ResponseEntity<CrudSectorVO> callCrudSector(@RequestBody CrudSectorVO crudSectorVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudSector(crudSectorVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/sectores/{id}").buildAndExpand(crudSectorVO.getSectorId()).toUri());
		return new ResponseEntity<CrudSectorVO>(crudSectorVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/agrupamientos", method = RequestMethod.GET)
	public ResponseEntity<List<AgrupamientosVO>> buscarAgrupamientos() throws NotFoundException {
		List<AgrupamientosDTO> agrupamientosDTO = catalogoService.buscarAgrupamientos();
		if (agrupamientosDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Agrupamientos");
		}
		List<AgrupamientosVO> agrupamientosVO = ResponseConverter.converterLista(new ArrayList<>(), agrupamientosDTO, AgrupamientosVO.class);
		return new ResponseEntity<List<AgrupamientosVO>>(agrupamientosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/agrupamientos", method = RequestMethod.POST)
	public ResponseEntity<CrudAgrupamientoVO> callCrudAgrupamiento(@RequestBody CrudAgrupamientoVO crudAgrupamientoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudAgrupamiento(crudAgrupamientoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/agrupamientos/{id}").buildAndExpand(crudAgrupamientoVO.getAgrupacionId()).toUri());
		return new ResponseEntity<CrudAgrupamientoVO>(crudAgrupamientoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/zonas", method = RequestMethod.GET)
	public ResponseEntity<List<ZonaVO>> buscarZonas() throws NotFoundException {
		List<ZonaDTO> zonasDTO = catalogoService.buscarZonas();
		if (zonasDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Zonas");
		}
		List<ZonaVO> zonasVO = ResponseConverter.converterLista(new ArrayList<>(), zonasDTO, ZonaVO.class);
		return new ResponseEntity<List<ZonaVO>>(zonasVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zonas", method = RequestMethod.POST)
	public ResponseEntity<CrudZonaVO> callCrudZona(@RequestBody CrudZonaVO crudZonaVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudZonas(crudZonaVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/zonas/{id}").buildAndExpand(crudZonaVO.getZonaId()).toUri());
		return new ResponseEntity<CrudZonaVO>(crudZonaVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/regionesDeposito/{id}", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<List<RegionDepositoVO>> buscarRegionesDepositoPorEstado(@PathVariable("id")  Long estadoId) throws NotFoundException {
		List<RegionDepositoDTO> regionesDTO = catalogoService.buscarRegionDepositoPorEstado(estadoId);
		if (regionesDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Regiones");
		}
		List<RegionDepositoVO> regionesVO = ResponseConverter.converterLista(new ArrayList<>(), regionesDTO, RegionDepositoVO.class);
		return new ResponseEntity<List<RegionDepositoVO>>(regionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/regionesDepositoDF", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<List<RegionDepositoVO>> buscarRegionesDepositoDF() throws NotFoundException {
		List<RegionDepositoDTO> regionesDTO = catalogoService.buscarRegionDepositoPorEstado(new Long(9));
		if (regionesDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Regiones");
		}
		List<RegionDepositoVO> regionesVO = ResponseConverter.converterLista(new ArrayList<>(), regionesDTO, RegionDepositoVO.class);
		return new ResponseEntity<List<RegionDepositoVO>>(regionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/regionesDeposito", method = RequestMethod.POST)
	public ResponseEntity<CrudRegionDepositoVO> callCrudRegionDeposito(@RequestBody CrudRegionDepositoVO crudRegionDepositoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudRegionDeposito(crudRegionDepositoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/regionesDeposito/{id}").buildAndExpand(crudRegionDepositoVO.getRegionId()).toUri());
		return new ResponseEntity<CrudRegionDepositoVO>(crudRegionDepositoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/depositos/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<RespuestaBusquedaDepositoVO>> buscarDepositosPorRegion(@PathVariable("id")  Long regionId) throws NotFoundException {
		List<RespuestaBusquedaDepositoVO> depositos = catalogoService.buscarDepositosPorRegion(regionId);
		if (depositos.isEmpty()) {
			throw new NotFoundException("No se encontraron Depositos");
		}		
		return new ResponseEntity<List<RespuestaBusquedaDepositoVO>>(depositos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/depositos", method = RequestMethod.POST)
	public ResponseEntity<CrudDepositoVO> callCrudDeposito(@RequestBody CrudDepositoVO crudDepositoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudDeposito(crudDepositoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/depositos/{id}").buildAndExpand(crudDepositoVO.getDepositoId()).toUri());
		return new ResponseEntity<CrudDepositoVO>(crudDepositoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/marcasVehiculosTodos", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<List<VehiculoMarcaVO>> buscarMarcasVehiculosTodos() throws NotFoundException {
		List<VehiculoMarcaDTO> vehiculoMarcaDTO = new ArrayList<VehiculoMarcaDTO>();
		vehiculoMarcaDTO = catalogoService.buscarMarcaVehiculoTodos();
		if (vehiculoMarcaDTO == null) {
			return new ResponseEntity<List<VehiculoMarcaVO>>(HttpStatus.NOT_FOUND);
		}
		List<VehiculoMarcaVO> vehiculoMarcaVO = new ArrayList<VehiculoMarcaVO>();
		vehiculoMarcaVO = ResponseConverter.converterLista(new ArrayList<>(), vehiculoMarcaDTO, VehiculoMarcaVO.class);
		return new ResponseEntity<List<VehiculoMarcaVO>>(vehiculoMarcaVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/marcasVehiculos", method = RequestMethod.POST)
	public ResponseEntity<CrudVehiculoMarcaVO> callCrudVehiculoMarca(@RequestBody CrudVehiculoMarcaVO crudVehiculoMarcaVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudVehiculoMarca(crudVehiculoMarcaVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/marcasVehiculos/{id}").buildAndExpand(crudVehiculoMarcaVO.getvMarId()).toUri());
		return new ResponseEntity<CrudVehiculoMarcaVO>(crudVehiculoMarcaVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/modelosVehiculosTodos", method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoModeloVO>> buscarModeloVehiculo() throws NotFoundException {
		List<VehiculoModeloDTO> vehiculoModeloDTO = new ArrayList<VehiculoModeloDTO>();
		vehiculoModeloDTO = catalogoService.buscarModeloVehiculo();
		if (vehiculoModeloDTO == null){
			return new ResponseEntity<List<VehiculoModeloVO>>(HttpStatus.NOT_FOUND);
		}
		List<VehiculoModeloVO> vehiculoModeloVO = new ArrayList<VehiculoModeloVO>();
		vehiculoModeloVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				vehiculoModeloDTO, VehiculoModeloVO.class);
		return new ResponseEntity<List<VehiculoModeloVO>>(vehiculoModeloVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/modelosVehiculos", method = RequestMethod.POST)
	public ResponseEntity<CrudVehiculoModeloVO> callCrudVehiculoModelo(@RequestBody CrudVehiculoModeloVO crudVehiculoModeloVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudVehiculoModelo(crudVehiculoModeloVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/modelosVehiculos/{id}").buildAndExpand(crudVehiculoModeloVO.getvModId()).toUri());
		return new ResponseEntity<CrudVehiculoModeloVO>(crudVehiculoModeloVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/subTiposVehiculo", method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoSubTipoVO>> buscarSubTiposVehiculo() throws NotFoundException {
		List<VehiculoSubTipoDTO> vehiculosSubTipoDTO = catalogoService.buscarVehiculoSubTipo();
		if (vehiculosSubTipoDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Sub tipos");
		}
		List<VehiculoSubTipoVO> subTiposVO = ResponseConverter.converterLista(new ArrayList<>(), vehiculosSubTipoDTO, VehiculoSubTipoVO.class);
		return new ResponseEntity<List<VehiculoSubTipoVO>>(subTiposVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/subTiposVehiculo", method = RequestMethod.POST)
	public ResponseEntity<CrudVehiculoSubTipoVO> callCrudVehiculoSubTipo(@RequestBody CrudVehiculoSubTipoVO crudVehiculoSubTipoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudVehiculoSubTipo(crudVehiculoSubTipoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/subTiposVehiculo/{id}").buildAndExpand(crudVehiculoSubTipoVO.getvSubTipoId()).toUri());
		return new ResponseEntity<CrudVehiculoSubTipoVO>(crudVehiculoSubTipoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/tiposVehiculoTodos", method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoTipoVO>> buscarTipoVehiculos() throws NotFoundException {
		List<VehiculoTipoDTO> tipoVehiculsActivosDTO = new ArrayList<VehiculoTipoDTO>();
		tipoVehiculsActivosDTO = catalogoService.buscarTipoVehiculo();
		if (tipoVehiculsActivosDTO == null) {
			return new ResponseEntity<List<VehiculoTipoVO>>(HttpStatus.NOT_FOUND);
		}
		List<VehiculoTipoVO> tipoVehiculsActivosVO = new ArrayList<VehiculoTipoVO>();
		tipoVehiculsActivosVO = ResponseConverter.converterLista(new ArrayList<>(), tipoVehiculsActivosDTO,
				VehiculoTipoVO.class);
		return new ResponseEntity<List<VehiculoTipoVO>>(tipoVehiculsActivosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tiposVehiculoTodosPorSupTipo/{id}", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<List<VehiculoTipoVO>> buscarTipoVehiculosPorSubTipo(@PathVariable("id")  Long subTipoId) throws NotFoundException {
		List<VehiculoTipoDTO> tipoVehiculsActivosDTO = new ArrayList<VehiculoTipoDTO>();
		tipoVehiculsActivosDTO = catalogoService.buscarVehiculoTipoPorSubTipo(subTipoId);
		if (tipoVehiculsActivosDTO == null) {
			return new ResponseEntity<List<VehiculoTipoVO>>(HttpStatus.NOT_FOUND);
		}
		List<VehiculoTipoVO> tipoVehiculsActivosVO = new ArrayList<VehiculoTipoVO>();
		tipoVehiculsActivosVO = ResponseConverter.converterLista(new ArrayList<>(), tipoVehiculsActivosDTO,
				VehiculoTipoVO.class);
		return new ResponseEntity<List<VehiculoTipoVO>>(tipoVehiculsActivosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tiposVehiculo", method = RequestMethod.POST)
	public ResponseEntity<CrudVehiculoTipoVO> callCrudVehiculoTipo(@RequestBody CrudVehiculoTipoVO crudVehiculoTipoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudVehiculoTipo(crudVehiculoTipoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/tiposVehiculo/{id}").buildAndExpand(crudVehiculoTipoVO.getvTipoId()).toUri());
		return new ResponseEntity<CrudVehiculoTipoVO>(crudVehiculoTipoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/colorVehiculoTodos",method=RequestMethod.GET)
	public ResponseEntity<List<VehiculoColorVO>>buscarColorVehiculoTodos()throws NotFoundException{
		List<VehiculoColorDTO> vehiculoColorDTO = new ArrayList<VehiculoColorDTO>();
		vehiculoColorDTO = catalogoService.buscarColorVehiculoTodos();
		if (vehiculoColorDTO == null){
			return new ResponseEntity<List<VehiculoColorVO>>(HttpStatus.NOT_FOUND);
		}
		List<VehiculoColorVO> vehiculoColorVO = new ArrayList<VehiculoColorVO>();
		vehiculoColorVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				vehiculoColorDTO, VehiculoColorVO.class);
		return new ResponseEntity<List<VehiculoColorVO>>(vehiculoColorVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/colorVehiculoCrud", method = RequestMethod.POST)
	public ResponseEntity<CrudVehiculoColorVO> callCrudVehiculoColor(@RequestBody CrudVehiculoColorVO crudVehiculoColorVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudVehiculoColor(crudVehiculoColorVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/colorVehiculo/{id}").buildAndExpand(crudVehiculoColorVO.getvColorId()).toUri());
		return new ResponseEntity<CrudVehiculoColorVO>(crudVehiculoColorVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/catalogoResponsableVehiculoTodos", method = RequestMethod.GET)
	public ResponseEntity<List<ResponsableVehiculoVO>> buscarResponsableVehiculo() throws NotFoundException {
		List<ResponsableVehiculoDTO> catalogoResponsableVehiculoDTO = new ArrayList<ResponsableVehiculoDTO>();
		catalogoResponsableVehiculoDTO = catalogoService.buscarResponsableVehiculo();
		if (catalogoResponsableVehiculoDTO == null) {
			return new ResponseEntity<List<ResponsableVehiculoVO>>(HttpStatus.NOT_FOUND);
		}
		List<ResponsableVehiculoVO> catalogoResponsableVehiculoVO = new ArrayList<ResponsableVehiculoVO>();
		catalogoResponsableVehiculoVO = ResponseConverter.converterLista(new ArrayList<>(),
				catalogoResponsableVehiculoDTO, ResponsableVehiculoVO.class);
		return new ResponseEntity<List<ResponsableVehiculoVO>>(catalogoResponsableVehiculoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catalogoResponsableVehiculo", method = RequestMethod.POST)
	public ResponseEntity<CrudVehiculoResponsableVO> callCrudVehiculoResponsable(@RequestBody CrudVehiculoResponsableVO crudVehiculoResponsableVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudVehiculoResponsable(crudVehiculoResponsableVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/catalogoResponsableVehiculo/{id}").buildAndExpand(crudVehiculoResponsableVO.getrVehId()).toUri());
		return new ResponseEntity<CrudVehiculoResponsableVO>(crudVehiculoResponsableVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/tipoLicenciaTodos", method = RequestMethod.GET)
	public ResponseEntity<List<TipoLicenciaVO>> buscarTiposLicenciasTodos() throws NotFoundException {
		List<TipoLicenciaDTO> tipoLicenciaDTO = new ArrayList<TipoLicenciaDTO>();
		tipoLicenciaDTO = catalogoService.buscarTipoLicenciaTodos();
		if (tipoLicenciaDTO == null){
			return new ResponseEntity<List<TipoLicenciaVO>>(HttpStatus.NOT_FOUND);
		}
		List<TipoLicenciaVO> tipoLicenciaVO = new ArrayList<TipoLicenciaVO>();
		tipoLicenciaVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				tipoLicenciaDTO, TipoLicenciaVO.class);
		return new ResponseEntity<List<TipoLicenciaVO>>(tipoLicenciaVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipoLicencia", method = RequestMethod.POST)
	public ResponseEntity<CrudTipoLicenciaVO> callCrudTipoLicencia(@RequestBody CrudTipoLicenciaVO crudTipoLicenciaVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudTipoLicencia(crudTipoLicenciaVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/tipoLicencia/{id}").buildAndExpand(crudTipoLicenciaVO.getTipoLId()).toUri());
		return new ResponseEntity<CrudTipoLicenciaVO>(crudTipoLicenciaVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/eventos", method = RequestMethod.GET)
	public ResponseEntity<List<EventoVO>> buscarEventos() throws NotFoundException {
		List<EventoDTO> eventosDTO = catalogoService.buscarEventos();
		if (eventosDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Eventos");
		}
		List<EventoVO> eventosVO = ResponseConverter.converterLista(new ArrayList<>(), eventosDTO, EventoVO.class);
		return new ResponseEntity<List<EventoVO>>(eventosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eventos", method = RequestMethod.POST)
	public ResponseEntity<CrudEventoVO> callCrudEvento(@RequestBody CrudEventoVO crudEventoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudEvento(crudEventoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/eventos/{id}").buildAndExpand(crudEventoVO.getEventoId()).toUri());
		return new ResponseEntity<CrudEventoVO>(crudEventoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/estatusInfraccion", method = RequestMethod.GET)
	public ResponseEntity<List<EstatusInfraccionVO>> buscarEstatusInfraccion() throws NotFoundException {
		List<EstatusInfraccionDTO> estatusDTO = catalogoService.buscarEstatusInfraccion();
		if (estatusDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Estatus Infraccion");
		}
		List<EstatusInfraccionVO> estatusVO = ResponseConverter.converterLista(new ArrayList<>(), estatusDTO, EstatusInfraccionVO.class);
		return new ResponseEntity<List<EstatusInfraccionVO>>(estatusVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/estatusInfraccion", method = RequestMethod.POST)
	public ResponseEntity<CrudEstatusInfraccionVO> callCrudEstatusInfraccion(@RequestBody CrudEstatusInfraccionVO crudEstatusInfraccionVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudEstatusInfraccion(crudEstatusInfraccionVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/estatusInfraccion/{id}").buildAndExpand(crudEstatusInfraccionVO.getEstatusId()).toUri());
		return new ResponseEntity<CrudEstatusInfraccionVO>(crudEstatusInfraccionVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/denominaciones", method = RequestMethod.GET)
	public ResponseEntity<List<DenominacionVO>> buscarDenominaciones() throws NotFoundException {
		List<DenominacionDTO> denominacionesDTO = catalogoService.buscarDenominaciones();
		if (denominacionesDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Denominaciones");
		}
		List<DenominacionVO> denominacionVO = ResponseConverter.converterLista(new ArrayList<>(), denominacionesDTO, DenominacionVO.class);
		return new ResponseEntity<List<DenominacionVO>>(denominacionVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/denominaciones", method = RequestMethod.POST)
	public ResponseEntity<CrudDenominacionVO> callCrudDenominacion(@RequestBody CrudDenominacionVO crudDenominacionVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudDenominacion(crudDenominacionVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/denominaciones/{id}").buildAndExpand(crudDenominacionVO.getDenominacionId()).toUri());
		return new ResponseEntity<CrudDenominacionVO>(crudDenominacionVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/conceptos", method = RequestMethod.GET)
	public ResponseEntity<List<ConceptoPagoVO>> buscarConceptos() throws NotFoundException {
		List<ConceptoPagoDTO> conceptosDTO = catalogoService.buscarConceptosPago();
		if (conceptosDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Conceptos pagos");
		}
		List<ConceptoPagoVO> conceptosVO = ResponseConverter.converterLista(new ArrayList<>(), conceptosDTO, ConceptoPagoVO.class);
		return new ResponseEntity<List<ConceptoPagoVO>>(conceptosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/conceptos", method = RequestMethod.POST)
	public ResponseEntity<CrudConceptoPagoVO> callCrudConceptoPago(@RequestBody CrudConceptoPagoVO crudConceptoPagoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudConceptoPago(crudConceptoPagoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/conceptos/{id}").buildAndExpand(crudConceptoPagoVO.getConceptoId()).toUri());
		return new ResponseEntity<CrudConceptoPagoVO>(crudConceptoPagoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/entidades", method = RequestMethod.GET)
	public ResponseEntity<List<EntidadPagoVO>> buscarEntidadesPagos() throws NotFoundException {
		List<EntidadPagoDTO> entidadPagoDTO = catalogoService.buscarEntidadesPago();
		if (entidadPagoDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Entidad pago");
		}
		List<EntidadPagoVO> entidadPagoVO = ResponseConverter.converterLista(new ArrayList<>(), entidadPagoDTO, EntidadPagoVO.class);
		return new ResponseEntity<List<EntidadPagoVO>>(entidadPagoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/entidades", method = RequestMethod.POST)
	public ResponseEntity<CrudEntidadPagosVO> callCrudEntidadPagos(@RequestBody CrudEntidadPagosVO crudEntidadPagosVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudEntidadPago(crudEntidadPagosVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/entidades/{id}").buildAndExpand(crudEntidadPagosVO.getEntidadId()).toUri());
		return new ResponseEntity<CrudEntidadPagosVO>(crudEntidadPagosVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/bancos", method = RequestMethod.GET)
	public ResponseEntity<List<BancoVO>> buscarBancos() throws NotFoundException {
		List<BancoDTO> bancosDTO = catalogoService.buscarBancos();
		if (bancosDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Bancos");
		}
		List<BancoVO> bancosVO = ResponseConverter.converterLista(new ArrayList<>(), bancosDTO, BancoVO.class);
		return new ResponseEntity<List<BancoVO>>(bancosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bancos", method = RequestMethod.POST)
	public ResponseEntity<CrudBancoVO> callCrudBanco(@RequestBody CrudBancoVO crudBancoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudBanco(crudBancoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/bancos/{id}").buildAndExpand(crudBancoVO.getBancoId()).toUri());
		return new ResponseEntity<CrudBancoVO>(crudBancoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/ingresos/tipos", method = RequestMethod.POST)
	public ResponseEntity<CrudTipoIngresoVO> callCrudTipoIngreso(@RequestBody CrudTipoIngresoVO crudTipoIngresoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudTipoIngreso(crudTipoIngresoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/ingresos/tipos/{id}").buildAndExpand(crudTipoIngresoVO.gettIngrId()).toUri());
		return new ResponseEntity<CrudTipoIngresoVO>(crudTipoIngresoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/causasIngreso", method = RequestMethod.GET)
	public ResponseEntity<List<CausaIngresoVO>> buscarCausasIngreso() throws NotFoundException {
		List<CausaIngresoDTO> causaIngresoDTO = catalogoService.buscarCausaIngresoTodos();
		if (causaIngresoDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Causas Ingresos");
		}
		List<CausaIngresoVO> causasIngresoVO = ResponseConverter.converterLista(new ArrayList<>(), causaIngresoDTO, CausaIngresoVO.class);
		return new ResponseEntity<List<CausaIngresoVO>>(causasIngresoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/causasIngreso", method = RequestMethod.POST)
	public ResponseEntity<CrudCausaIngresoVO> callCrudCausaIngreso(@RequestBody CrudCausaIngresoVO crudCausaIngresoVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudCausaIngreso(crudCausaIngresoVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/causasIngreso/{id}").buildAndExpand(crudCausaIngresoVO.getIdCausaIngreso()).toUri());
		return new ResponseEntity<CrudCausaIngresoVO>(crudCausaIngresoVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/areas", method = RequestMethod.GET)
	public ResponseEntity<List<AreaVO>> buscarAreas() throws NotFoundException {
		List<AreaDTO> areaDTO = catalogoService.buscarAreas();
		if (areaDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Areas");
		}
		List<AreaVO> areasVO = ResponseConverter.converterLista(new ArrayList<>(), areaDTO, AreaVO.class);
		return new ResponseEntity<List<AreaVO>>(areasVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/areas", method = RequestMethod.POST)
	public ResponseEntity<CrudAreaVO> callCrudArea(@RequestBody CrudAreaVO crudAreaVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudArea(crudAreaVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/areas/{id}").buildAndExpand(crudAreaVO.getAreaId()).toUri());
		return new ResponseEntity<CrudAreaVO>(crudAreaVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/componentes", method = RequestMethod.GET)
	public ResponseEntity<List<ComponentesInventarioVO>> buscarComponentesInventario() throws NotFoundException {
		List<ComponentesInventarioDTO> componentesDTO = catalogoService.buscarComponentesInventarioTodos();
		if (componentesDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Componentes");
		}
		List<ComponentesInventarioVO> componentesVO = ResponseConverter.converterLista(new ArrayList<>(), componentesDTO, ComponentesInventarioVO.class);
		return new ResponseEntity<List<ComponentesInventarioVO>>(componentesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/componentes", method = RequestMethod.POST)
	public ResponseEntity<CrudComponenteVO> callCrudComponente(@RequestBody CrudComponenteVO crudComponenteVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudComponente(crudComponenteVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/componentes/{id}").buildAndExpand(crudComponenteVO.getIdComponente()).toUri());
		return new ResponseEntity<CrudComponenteVO>(crudComponenteVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/articulosTodos", method = RequestMethod.GET)
	public ResponseEntity<List<ArticuloVO>> buscarArticulos() throws NotFoundException {
		List<ArticuloDTO> articulosDTO = catalogoService.buscarArticulos();
		if (articulosDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Articulos");
		}
		List<ArticuloVO> articulosVO = ResponseConverter.converterLista(new ArrayList<>(), articulosDTO, ArticuloVO.class);
		return new ResponseEntity<List<ArticuloVO>>(articulosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/articulos", method = RequestMethod.POST)
	public ResponseEntity<CrudArticuloVO> callCrudArticulo(@RequestBody CrudArticuloVO crudArticuloVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudArticulo(crudArticuloVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/articulos/{id}").buildAndExpand(crudArticuloVO.getArtId()).toUri());
		return new ResponseEntity<CrudArticuloVO>(crudArticuloVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/programas", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramaVO>> buscarProgramas() throws NotFoundException {
		List<ProgramaDTO> programasDTO = catalogoService.buscarProgramas();
		if (programasDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Programas");
		}
		List<ProgramaVO> programasVO = ResponseConverter.converterLista(new ArrayList<>(), programasDTO, ProgramaVO.class);
		return new ResponseEntity<List<ProgramaVO>>(programasVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/programasActivos", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramaVO>> buscarProgramasActivos() throws NotFoundException {
		List<ProgramaDTO> programasDTO = catalogoService.buscarProgramasActivos();
		if (programasDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Programas");
		}
		List<ProgramaVO> programasVO = ResponseConverter.converterLista(new ArrayList<>(), programasDTO, ProgramaVO.class);
		return new ResponseEntity<List<ProgramaVO>>(programasVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/programas", method = RequestMethod.POST)
	public ResponseEntity<CrudProgramaVO> callCrudPrograma(@RequestBody CrudProgramaVO crudProgramaVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudPrograma(crudProgramaVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/programas/{id}").buildAndExpand(crudProgramaVO.getProgramaId()).toUri());
		return new ResponseEntity<CrudProgramaVO>(crudProgramaVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/categorias", method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaVO>> buscarCategorias() throws NotFoundException {
		List<CategoriaDTO> categoriasDTO = catalogoService.buscarCategorias();
		if (categoriasDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Categorias");
		}
		List<CategoriaVO> categoriasVO = ResponseConverter.converterLista(new ArrayList<>(), categoriasDTO, CategoriaVO.class);
		return new ResponseEntity<List<CategoriaVO>>(categoriasVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/categoriasActivas", method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaVO>> buscarCategoriasActivas() throws NotFoundException {
		List<CategoriaDTO> categoriasDTO = catalogoService.buscarCategoriasActivas();
		if (categoriasDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Categorias");
		}
		List<CategoriaVO> categoriasVO = ResponseConverter.converterLista(new ArrayList<>(), categoriasDTO, CategoriaVO.class);
		return new ResponseEntity<List<CategoriaVO>>(categoriasVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/categorias", method = RequestMethod.POST)
	public ResponseEntity<CrudCategoriaVO> callCrudCategoria(@RequestBody CrudCategoriaVO crudCategoriaVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudCategoria(crudCategoriaVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/categorias/{id}").buildAndExpand(crudCategoriaVO.getCategoriaId()).toUri());
		return new ResponseEntity<CrudCategoriaVO>(crudCategoriaVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/causales", method = RequestMethod.GET)
	public ResponseEntity<List<CausalesVO>> buscarCausales() throws NotFoundException {
		List<CausalesDTO> causalesDTO = catalogoService.buscarCausales();
		if (causalesDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Causales");
		}
		List<CausalesVO> causalesVO = ResponseConverter.converterLista(new ArrayList<>(), causalesDTO, CausalesVO.class);
		return new ResponseEntity<List<CausalesVO>>(causalesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/causalesActivos", method = RequestMethod.GET)
	public ResponseEntity<List<CausalesVO>> buscarCausalesActivos() throws NotFoundException {
		List<CausalesDTO> causalesDTO = catalogoService.buscarCausalesActivos();
		if (causalesDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Causales");
		}
		List<CausalesVO> causalesVO = ResponseConverter.converterLista(new ArrayList<>(), causalesDTO, CausalesVO.class);
		return new ResponseEntity<List<CausalesVO>>(causalesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/causales", method = RequestMethod.POST)
	public ResponseEntity<CrudCausalesVO> callCrudCausales(@RequestBody CrudCausalesVO crudCausalesVO, UriComponentsBuilder ucBuilder) throws NotFoundException {
		catalogoService.callCrudCausales(crudCausalesVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/causales/{id}").buildAndExpand(crudCausalesVO.getCausalId()).toUri());
		return new ResponseEntity<CrudCausalesVO>(crudCausalesVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/tipoCajasHistorico", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('TIPO_CAJAS_HISTORICO')")
	public ResponseEntity<List<FilterValuesVO>> tipoCajas() throws NotFoundException {
		List<FilterValuesVO> filterValuesVO = catalogoService.tipoCaja();
		return new ResponseEntity<List<FilterValuesVO>>(filterValuesVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipoResultadoSinCajaHistorico", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('TIPO_RESULTADO_SIN_CAJA_HISTORICO')")
	public ResponseEntity<List<FilterValuesVO>> buscaTipoResultadoSinCajaHistorico() throws NotFoundException {
		List<FilterValuesVO> filterValuesVO = catalogoService.buscaTipoResultadoSinCajaHistorico();
		return new ResponseEntity<List<FilterValuesVO>>(filterValuesVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipoBusquedaSinCajaActual", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('TIPO_BUSQUEDA_SIN_CAJA_ACTUAL')")
	public ResponseEntity<List<FilterValuesVO>> buscaTipoBusquedaSinCajaActual() throws NotFoundException {
		List<FilterValuesVO> filterValuesVO = catalogoService.buscaTipoBusquedaSinCajaActual();
		return new ResponseEntity<List<FilterValuesVO>>(filterValuesVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipoBusquedaCaja", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('TIPO_BUSQUEDA_CAJA')")
	public ResponseEntity<List<FilterValuesVO>> filtroBusquedaDeCaja() throws NotFoundException {
		List<FilterValuesVO> filterValuesVO = catalogoService.filtroBusquedaDeCaja();
		return new ResponseEntity<List<FilterValuesVO>>(filterValuesVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fotomulta/radares", method = RequestMethod.GET)
	public ResponseEntity<List<TipoRadarVO>> buscarRadares(@RequestParam(value = "option" ) Boolean todos) throws NotFoundException {
		List<TipoRadarDTO> radarDTO = catalogoService.buscarRadares(todos);
		if (radarDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron radares");
		}
		List<TipoRadarVO> radarVO = ResponseConverter.converterLista(new ArrayList<>(), radarDTO, TipoRadarVO.class);
		return new ResponseEntity<List<TipoRadarVO>>(radarVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fotomulta/estatus", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> buscareEstatus() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.filtroEstatusDetecciones(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fotomulta/procesados", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> buscarProcesados() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.filtroProcesadosDetecciones(), HttpStatus.OK);
	}
		
	@RequestMapping(value = "/catalogosWeb", method = RequestMethod.GET)
	public ResponseEntity<List<CatalogosWebVO>> buscarCatalogosWeb() throws NotFoundException {
		List<CatalogoWebDTO> catalogosDTO = catalogoService.buscarCatalogosWeb();
		if (catalogosDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Catalogos");
		}
		List<CatalogosWebVO> catalogosVO = ResponseConverter.converterLista(new ArrayList<>(), catalogosDTO, CatalogosWebVO.class);
		return new ResponseEntity<List<CatalogosWebVO>>(catalogosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catalogosWebOpciones/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<CatalogoWebOpcionVO>> buscarCatalogosWebOpcionesPorCatalogo(@PathVariable("id")  Long catalogoId) throws NotFoundException {
		List<CatalogoWebOpcionDTO> opcionesDTO = catalogoService.buscarCatalogosWebOpciones(catalogoId);
		if (opcionesDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron Opciones");
		}
		List<CatalogoWebOpcionVO> opcionesVO = ResponseConverter.converterLista(new ArrayList<>(), opcionesDTO, CatalogoWebOpcionVO.class);
		return new ResponseEntity<List<CatalogoWebOpcionVO>>(opcionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fotomulta/aniossalariominimo", method = RequestMethod.GET)
	public ResponseEntity<List<ComboValuesVO>> buscarAniosSalarioMinimo() throws NotFoundException {
		return new ResponseEntity<List<ComboValuesVO>>(catalogoService.obtenerAnioSalarioMinimo(), HttpStatus.OK);
	}
	@RequestMapping(value = "/fotomulta/tiposfechaconsultalotes", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> buscarTiposFechaConsultaLotes() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.filtroTiposFechaForConsultaLotesFM(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/estatusProcesoLotes", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> buscarEstatusProcesoLotes() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.filtroEstatusProceso(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/articulosInfraccionesFinanzas/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ArticulosInfraccionesFinanzasVO>> buscarArticulosInfraccionesFinanzasPorArticulo(@PathVariable("id")  Long articuloId) throws NotFoundException {
		List<ArticulosInfraccionesFinanzasDTO> articulos = catalogoService.buscarArticulosInfraccionesFinanzasPorArticulo(articuloId);		
		List<ArticulosInfraccionesFinanzasVO> articulosVO = ResponseConverter.converterLista(new ArrayList<>(), articulos, ArticulosInfraccionesFinanzasVO.class);
		return new ResponseEntity<List<ArticulosInfraccionesFinanzasVO>>(articulosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/articuloInfraccionesFinanzas/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArticulosInfraccionesFinanzasVO> buscarArticulosInfraccionesFinanzasPorId(@PathVariable("id")  Long id) throws NotFoundException {
		ArticulosInfraccionesFinanzasDTO articulo = catalogoService.buscarArticulosInfraccionesFinanzasPorId(id);	
		ArticulosInfraccionesFinanzasVO articulosInfraccionesFinanzasVO = ResponseConverter.copiarPropiedadesFull(articulo, ArticulosInfraccionesFinanzasVO.class);	
		return new ResponseEntity<ArticulosInfraccionesFinanzasVO>(articulosInfraccionesFinanzasVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/articuloInfraccionesFinanzas", method = RequestMethod.POST)
	public ResponseEntity<CrudArticulosHistoricoVO> callCrudArticulosHistoricos(@RequestBody CrudArticulosHistoricoVO articuloVO, UriComponentsBuilder ucBuilder) {		
		catalogoService.callCrudArticulosHistoricos(articuloVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/articuloInfraccionesFinanzas/{id}").buildAndExpand(articuloVO.getArtInfrFinCons()).toUri());
		return new ResponseEntity<CrudArticulosHistoricoVO>(articuloVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/articulos/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArticuloVO> buscarArticulosPorId(@PathVariable("id")  Long articuloId) throws NotFoundException {
		ArticuloDTO articulo = catalogoService.buscarArticulosPorId(articuloId);		
		ArticuloVO articuloVO = ResponseConverter.copiarPropiedadesFull(articulo, ArticuloVO.class);
		return new ResponseEntity<ArticuloVO>(articuloVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tiposReporteWeb", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> buscarTipoReportesWeb() throws NotFoundException {
		Long perfilId = usuarioFirmadoService.getUsuarioFirmadoVO().getPerfilId();
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.buscaTipoReportesWeb(perfilId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/opcionesConInfraccion", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_DOCUMENTOS')")
	public ResponseEntity<List<OpcionConInfraccionVO>> OpcionesConInfaccion() throws NotFoundException {
		List<OpcionConInfraccionVO> opcionValues = catalogoService.OpcionesConInfaccion();
		return new ResponseEntity<List<OpcionConInfraccionVO>>(opcionValues, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listaOperativa", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_DOCUMENTOS')")
	public ResponseEntity<List<ListOperativoVO>> listaOperativo() throws NotFoundException {
		List<ListOperativoVO> listOper = catalogoService.listaOperativo();
		return new ResponseEntity<List<ListOperativoVO>>(listOper, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listaMediosTransp", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_DOCUMENTOS')")
	public ResponseEntity<List<ListMediosTraspVO>> listaMediosTrasp() throws NotFoundException {
		List<ListMediosTraspVO> listMedios = catalogoService.listaMediosTrasp();
		return new ResponseEntity<List<ListMediosTraspVO>>(listMedios, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listaNoGruas", method = RequestMethod.GET)
	public ResponseEntity<List<ListaNoGruasVO>> listaNoGruas() throws NotFoundException {
		List<ListaNoGruasVO> listMedios = catalogoService.listaNoGruas();
		return new ResponseEntity<List<ListaNoGruasVO>>(listMedios, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listaCausaIngreso", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_DOCUMENTOS')")
	public ResponseEntity<List<ListCausaIngresoVO>> listaCausaIngreso() throws NotFoundException {
		List<ListCausaIngresoVO> listCausa = catalogoService.listaCausaIngreso();
		return new ResponseEntity<List<ListCausaIngresoVO>>(listCausa, HttpStatus.OK);
	}
	
	/** 
	 * @author dagoberto
	 * @return ListCausaIngresoVO
	 * @throws NotFoundException
	 * metodo que se usa para remision a deposito - con infraccion, haciendo referencia a 
	 * mov entre dep
	 * */	
	@RequestMapping(value = "/listaCausaIngresoByTraslado", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_DOCUMENTOS')")
	public ResponseEntity<List<ListCausaIngresoVO>> listaCausaIngresoByTraslado() throws NotFoundException {
		List<ListCausaIngresoVO> listCausa = catalogoService.listaCausaIngresoByTraslado();
		return new ResponseEntity<List<ListCausaIngresoVO>>(listCausa, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listaCausaIngresoSin", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_DOCUMENTOS')")
	public ResponseEntity<List<ListCausaIngresoVO>> listaCausaIngresoSin() throws NotFoundException {
		List<ListCausaIngresoVO> listCausa = catalogoService.listaCausaIngresoSin();
		return new ResponseEntity<List<ListCausaIngresoVO>>(listCausa, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listaDeposito", method = RequestMethod.GET)
	public ResponseEntity<List<ListDepositoVO>> listaDeposito(@RequestParam(value = "dep_id" ) int dep_id) throws NotFoundException {
		List<ListDepositoVO> deposito = null;
		Long emp_id = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		deposito = catalogoService.listaDeposito(emp_id, dep_id);
		if (deposito.isEmpty()) {
			throw new NotFoundException("No se encontraron depositos");
		}	
		return new ResponseEntity<List<ListDepositoVO>>(deposito, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/listaDepositoOrigen", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_DOCUMENTOS')")
	public ResponseEntity<List<ListDepositoVO>> listaDepositoOrigen() throws NotFoundException {
		List<ListDepositoVO> listCausa = catalogoService.listaDepositoOrigen();
		return new ResponseEntity<List<ListDepositoVO>>(listCausa, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listaInventario", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_DOCUMENTOS')")
	public ResponseEntity<List<ListInventarioVO>> listaInventario() throws NotFoundException {
		List<ListInventarioVO> listCausa = catalogoService.listaInventario();
		return new ResponseEntity<List<ListInventarioVO>>(listCausa, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/opcionesConsultaRemi", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_DOCUMENTOS')")
	public ResponseEntity<List<OpcionConInfraccionVO>> OpcionesConsultaRemision() throws NotFoundException {
		List<OpcionConInfraccionVO> opcionValues = catalogoService.OpcionesConsultaRemision();
		return new ResponseEntity<List<OpcionConInfraccionVO>>(opcionValues, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filtroLiberacionVehiculo", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> filtroLiberacionVehiculo() throws NotFoundException {
		List<FilterValuesVO> opcionValues = catalogoService.filtroLiberacionVehiculo();
		return new ResponseEntity<List<FilterValuesVO>>(opcionValues, HttpStatus.OK);
	}
		
	@RequestMapping(value = "/catalogos/concesionaria/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExceConcesionarial() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelConcesionaria();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/gruaStatus/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelGruaStatus() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelGruaStatus();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/gruas/reporte/{id}", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelGrua(@PathVariable("id")  Long concesionariaId) throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelGrua(concesionariaId);    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/turnos/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelTurno() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelTurno();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/zonasServicio/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelZonasServicio() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelZonasServicio();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/tipoEmpleado/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelTipoEmpleado() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelTipoEmpleado();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/tipoAlarma/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelTipoAlarma() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelTipoAlarma();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/estados/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelEstado() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelEstado();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/regiones/reporte/{id}", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelRegion(@PathVariable("id")  Long estadoId) throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelRegion(estadoId);    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/delegaciones/reporte/{id}", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelDelegacion(@PathVariable("id")  Long estadoId) throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelDelegacion(estadoId);    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/unidadeTerritorial/reporte/{id}", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelUT(@PathVariable("id")  Long sectorId) throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelUT(sectorId);    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/sectores/reporte/{delId}/{edoId}", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelSector(@PathVariable("delId")  Long delegacionId, @PathVariable("edoId")  Long edoId) throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelSector(delegacionId, edoId);    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/zonasDeposito/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelZonaDeposito() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelZonaDeposito();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/agrupamientos/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelAgrupamientos() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelAgrupamientos();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/regionDeposito/reporte/{id}", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelRegionDeposito(@PathVariable("id")  Long estadoId) throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelRegionDeposito(estadoId);    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/deposito/reporte/{id}", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelDeposito(@PathVariable("id")  Long regionId) throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelDeposito(regionId);    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/vehiculoMarca/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelVehiculoMarca() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelVehiculoMarca();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/vehiculoModelo/reporte/{id}", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelVehiculoModelo(@PathVariable("id")  Long marcaId) throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelVehiculoModelo(marcaId);    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/vehiculoSubTipo/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelVehiculoSubTipo() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelVehiculoSubTipo();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/vehiculoTipo/reporte/{id}", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelVehiculoTipo(@PathVariable("id")  Long subTipoId) throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelVehiculoTipo(subTipoId);    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/vehiculoColor/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelVehiculoColor() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelVehiculoColor();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/vehiculoResponsable/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelVehiculoResponsable() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelVehiculoResponsable();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/vehiculoTipoLicencia/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelVehiculoTipoLicencia() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelVehiculoTipoLicencia();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/eventos/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelInfraccionEvento() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelInfraccionEvento();    	
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/infraccionStatus/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelInfraccionStatus() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelInfraccionStatus();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/denominaciones/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelDenominacion() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelDenominacion();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/conceptoPago/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelConceptoPago() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelConceptoPago();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/entidad/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelEntidad() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelEntidad();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/bancos/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelBanco() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelBanco();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/tipoIngreso/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelTipoIngreso() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelTipoIngreso();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/causaIngreso/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelCausaIngreso() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelCausaIngreso();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/areaIngreso/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelAreaIngreso() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelAreaIngreso();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/componente/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelComponente() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelComponente();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/programas/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelPrograma() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelPrograma();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/categorias/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelCategorias() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelCategorias();   
    	return crearResponseReporte(outputStream);
    }
    
    @RequestMapping(value = "/catalogos/causales/reporte", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelCausales() throws IOException  {    	    	
    	ByteArrayOutputStream outputStream =  catalogoService.generarReporteExcelCausales();   
    	return crearResponseReporte(outputStream);
    }
    
    private ResponseEntity<byte[]> crearResponseReporte(ByteArrayOutputStream outputStream) {
    	final byte[] bytes = outputStream.toByteArray();
    	String filename = "ReporteExcel.xls";
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        
        return response;      
    }

	@RequestMapping(value = "/filtroComponentesSoporte", method = RequestMethod.GET)
	public ResponseEntity<List<ComboValuesVO>> filtroComponentesSoporte() throws NotFoundException {
		List<ComboValuesVO> componentes = catalogoService.filtroComponentesSoporte(1);
		return new ResponseEntity<List<ComboValuesVO>>(componentes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filtroConceptosSoporte", method = RequestMethod.GET)
	public ResponseEntity<List<ConceptosSoporteVO>> filtroConceptosSoporte(@RequestParam("componente") Long componenteId) throws NotFoundException {
		List<ConceptosSoporteDTO> listconceptosDTO = catalogoService.filtroConceptosSoporte(componenteId, 1);
		List<ConceptosSoporteVO> listconceptosVO = ResponseConverter.converterLista(new ArrayList<>(), listconceptosDTO, ConceptosSoporteVO.class);
		return new ResponseEntity<List<ConceptosSoporteVO>>(listconceptosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filtroEmbargos", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> filtroEmbargos() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.filtroEmbargos(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filtroOrigenPlaca", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> filtroOrigenPlaca(@RequestParam("tipoFiltro") Boolean tipo) throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.filtroOrigenPlaca(tipo), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registrosPorPagina", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> registrosPorPagina() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.registrosPorPagina(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fm/tiposDetecciones", method = RequestMethod.GET)
	public ResponseEntity<List<FMTiposDeteccionesVO>> fmTiposDetecciones(
			@RequestParam(value = "option" ) Boolean todos
		) throws NotFoundException {
		List<FMTiposDeteccionesVO> tiposDeteccionesVO = catalogoService.fmTiposDetecciones(todos);
		return new ResponseEntity<List<FMTiposDeteccionesVO>>(tiposDeteccionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fm/tiposRadaresByMarca", method = RequestMethod.GET)
	public ResponseEntity<List<FMMarcaDispositivoVO>> fmTiposRadaresByMarca(
			@RequestParam(value = "option" ) Boolean todos,
			@RequestParam(value = "idMarca" ) Integer idMarca
		) throws NotFoundException {
		List<FMMarcaDispositivoVO> tiposDeteccionesVO = catalogoService.fmTiposRadaresByMarca(todos, idMarca);
		return new ResponseEntity<List<FMMarcaDispositivoVO>>(tiposDeteccionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fm/periodoFecha", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> fmPeriodoFecha(
			@RequestParam(value = "option" ) Boolean option
		) throws NotFoundException {
		List<FilterValuesVO> tiposDeteccionesVO = catalogoService.fmPeriodoFecha(option);
		return new ResponseEntity<List<FilterValuesVO>>(tiposDeteccionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaTipoDescuento", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> buscaTipoDescuento() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.buscaTipoDescuento(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fc/tiposDetecciones", method = RequestMethod.GET)
	public ResponseEntity<List<FMTipoFotocivicaVO>> fcTiposDetecciones(
			@RequestParam(value = "option" ) Integer todos
		) throws NotFoundException {
		List<FMTipoFotocivicaVO> tiposDeteccionesVO = catalogoService.fcTiposDetecciones(todos);
		return new ResponseEntity<List<FMTipoFotocivicaVO>>(tiposDeteccionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fc/tipoArchivo", method = RequestMethod.GET)
	public ResponseEntity<List<FMTipoArchivoFCVO>> fcTipoArchivo(
			@RequestParam(value = "origenPlaca") Integer origenPlaca
		) throws NotFoundException {
		List<FMTipoArchivoFCVO> tiposPersonaVO = catalogoService.buscaTipoArchivo(origenPlaca);
		return new ResponseEntity<List<FMTipoArchivoFCVO>>(tiposPersonaVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fc/tiposDeteccionesOpcion", method = RequestMethod.GET)
	public ResponseEntity<List<FMTipoFotocivicaVO>> fcTiposDeteccionesActivo(
			@RequestParam(value = "opcion" ) Integer opcion
		) throws NotFoundException {
		List<FMTipoFotocivicaVO> tiposDeteccionesVO = new ArrayList<FMTipoFotocivicaVO>();
		FMTipoFotocivicaVO fMTipoFotocivicaVO = new FMTipoFotocivicaVO();
		fMTipoFotocivicaVO.setIdTipoFotocivica(0);
		fMTipoFotocivicaVO.setNbTipoFotocivica("Todos");
		tiposDeteccionesVO.add(fMTipoFotocivicaVO);
		tiposDeteccionesVO.addAll(catalogoService.fcTiposDeteccionesOpcion(opcion));
		return new ResponseEntity<List<FMTipoFotocivicaVO>>(tiposDeteccionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fc/tipoArchivoTodo", method = RequestMethod.GET)
	public ResponseEntity<List<FMTipoArchivoFCVO>> fcTipoArchivoTodo(
			@RequestParam(value = "opcion") Integer opcion
			) throws NotFoundException {
		List<FMTipoArchivoFCVO> tiposPersonaVO = catalogoService.buscaTipoArchivoTodo(opcion);
		return new ResponseEntity<List<FMTipoArchivoFCVO>>(tiposPersonaVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filtroOrigenPlacaFC", method = RequestMethod.GET)
	public ResponseEntity<List<FMOrigenPlacaFCVO>> filtroOrigenPlacaFC(
			@RequestParam("tipoFiltro") Integer todos
		) throws NotFoundException {
		return new ResponseEntity<List<FMOrigenPlacaFCVO>>(catalogoService.filtroOrigenPlacaFC(todos), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getValoresParametrosPorNbConfig", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> getValoresParametrosPorNbConfig(
			@RequestParam("nbConfig") String nbConfig){
		Map<String, String> parametros = crearLotesService.getParametrosLPPorNBConfig(nbConfig);
		return new ResponseEntity<Map<String,String>>(parametros, HttpStatus.OK);
	}

	/**
	 * RETORNA LA LISTA DE LOS TIPOS DE FILTRO PARA BUSQUEDA DE TRAMITES
	 * @author F. Campero
	 * @return List<TipoBusquedaAtcVO>
	 */
	@RequestMapping(value="/tramitesTipoBusquedaAC", method = RequestMethod.GET)
	public ResponseEntity<List<TipoBusquedaAtcVO>> getTiposDeBusquedaTramites(){
		List<TipoBusquedaAtcVO> listTipoBusquedas = catalogoService.buscarTiposBusquedaTramitesAtencionCiudadana(); 
		return new ResponseEntity<List<TipoBusquedaAtcVO>>(listTipoBusquedas, HttpStatus.OK);
	}	
	@RequestMapping(value = "/filtroTiposBusquedaDetFC", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> filtroTiposBusquedaDetFC(
			@RequestParam("tipoProcesable") Integer tipoProcesable
		) throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.filtroTiposBusquedaDetFC(tipoProcesable), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ac/filtroTipoTramite", method = RequestMethod.GET)
	public ResponseEntity<List<CatTipoTramiteVO>> filtroTipoTramite(
		@RequestParam(value = "opcion" ) Integer opcion
		) throws NotFoundException {
		
		List<CatTipoTramiteDTO> tipoTramiteDTO = new ArrayList<CatTipoTramiteDTO>();
		tipoTramiteDTO = atencionCiudadanaService.getCatalogoTramite();
		List<CatTipoTramiteVO> tramiteVO = ResponseConverter.converterLista(new ArrayList<>(), tipoTramiteDTO,
				CatTipoTramiteVO.class);
		return new ResponseEntity<List<CatTipoTramiteVO>>(tramiteVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ac/filtroEstatusTramite", method = RequestMethod.GET)
	public ResponseEntity<List<EstatusSeguimientoTramiteACVO>> filtroEstatusTramite(
		@RequestParam(value = "opcion" ) Integer opcion
		) throws NotFoundException {
		return new ResponseEntity<List<EstatusSeguimientoTramiteACVO>>(catalogoService.buscaEstatusTramite(opcion), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ac/filtroParametrosBusquedaAC", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> filtroEstatusTramite(
		) throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(catalogoService.parametrosBusquedaAC(), HttpStatus.OK);
	}
}