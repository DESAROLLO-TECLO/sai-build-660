package mx.com.teclo.saicdmx.negocio.service.catalogos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticuloDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticulosInfraccionesFinanzasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AgrupamientosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AreaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AreaOperativaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.BancoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatConDireccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatObserveQueDTO;
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
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosEmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EntidadPagoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EstadoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EstatusInfraccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EventoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GarantiasCatDocumentosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GruaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GruaStatusDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.OperativoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ProgramaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RadarCatArchivoTipoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionDepositoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionalesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ResponsableVehiculoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoAlarmaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoEmpleadoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoFechasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoIngresoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoLicenciaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoRadarDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoSuministroDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TurnoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.UnidadTerritorialDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VInfraccionUsuariosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoColorDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoMarcaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoModeloDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoSubTipoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoTipoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ZonaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ZonaServicioDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDocumentoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.GruaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.OpcionesBusquedaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.OpcionesCheckVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.RegionVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoBusquedaAtcVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.EstatusSeguimientoTramiteACVO;
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
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMOrigenPlacaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoArchivoFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoFotocivicaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTiposDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.TipoArrastreVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadaresCatArchivoTipoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListCausaIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListInventarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListMediosTraspVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListOperativoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListaNoGruasVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.OpcionConInfraccionVO;

public interface CatalogoService {

	public List<TipoIngresoDTO> buscarTipoIngreso();

	public VInfraccionUsuariosDTO buscarEmpleado(String placa);

	public GruaDTO buscarGrua(String codigoGrua);

	public List<ResponsableVehiculoDTO> buscarResponsableVehiculo();

	public ResponsableVehiculoDTO buscarResponsableVehiculo(long idResponsableVehiculo);
	
	public List<FMOrigenPlacaVO> buscaCatOrigenPlaca();

	// ESTADO
	public List<EstadoDTO> buscarEstadosTodos();

	public List<DelegacionDTO> buscarDelegacion(int edo);

	public EstadoDTO buscarEstado(int id);

	public DelegacionDTO buscarDelegacion(long id, EstadoDTO edo);

	// SECTOR
	/***********************************************************************
	 * OBTIENE LOS SECTORES POR IDENTIFICADOR DE SECTOR Y STATUS
	 * 
	 * @param estadoId
	 *            IDENTIFICADOR DE LA ENTIDAD FEDERATIVA
	 * @param status
	 *            STATUS DEL SECTOR
	 * @return List<SectorVO> LISTA DE SectorVO
	 **********************************************************************/
	public List<SectorDTO> buscarSectoresPorEstado(Long estadoId, String status);

	public List<UnidadTerritorialDTO> buscarUnidadesTerritorialesPorSector(Long idSector);

	public SectorDTO buscarSector(long id);

	// LICENCIA
	public List<TipoLicenciaDTO> buscarTipoLicencia();

	public TipoLicenciaDTO buscarTipoLicencia(long id);

	public TipoLicenciaDTO buscarTipoLicencia(String tipoLicenciaNombre);

	// VEHICULO
	public List<VehiculoTipoDTO> buscarTipoVehiculo();

	public List<VehiculoMarcaDTO> buscarMarcaVehiculo();

	/**
	 * RETORNA UNA LISTA DE VEHICULOS ACTIVOS FILTRADOS POR LA MARCA
	 * 
	 * @param marcaId
	 * @param status
	 * @return
	 */
	public List<VehiculoModeloDTO> buscarModeloVehiculoActivoPorMarca(Long marcaId);

	public List<VehiculoColorDTO> buscarColorVehiculo();

	public VehiculoTipoDTO buscarTipoVehiculo(long id);

	public VehiculoColorDTO buscarColorVehiculo(long id);

	public VehiculoModeloDTO buscarModeloVehiculo(Long vMarId, Long vModId);

	public VehiculoMarcaDTO buscarMarcaVehiculo(long id);

	// ARTICULO

	// OPCIONES BUSQUEDA
	public List<OpcionesBusquedaVO> buscarOpcionesBusqueda();

	// TIPO DE ARRASTRE
	public List<TipoArrastreVO> buscarTipoArrastre();

	// OPCIONES BUSQUEDA DE INGRESO A DEPOSTIO POR INFRACCION
	public List<OpcionesBusquedaVO> buscarOpcionesBusquedaIngresoPorInfraccion();

	/**
	 * CARGA EL TIPO DE INGRESO (ACTIVO) CON EL ID INDICADO
	 * 
	 * @param idTipoIngreso
	 * @return TipoIngresoVO
	 */
	public TipoIngresoDTO buscarTipoIngresoPorId(Long idTipoIngreso);

	/**
	 * CARGA EL CATALOGO DE TIPO INGRESO EXCLUYENDO EL ELEMNTO CON EL ID
	 * INDICADO
	 * 
	 * @param idTipoIngreso
	 * @return List<TipoIngresoVO>
	 */
	public List<TipoIngresoDTO> buscarTipoIngresoDiferentseAlId(Long idTipoIngreso);

	/**
	 * CARGA EL CATALOGO PARA LAS CAUSAS DE INGRESO A DEPOSITO
	 * 
	 * @return List<CausaIngresoVO> LISTA DE CAUSAS
	 */
	public List<CausaIngresoDTO> buscarCausaIngreso();

	/**
	 * CARGA EL CATALGO PARA LOS TIPO DE SELLADO DEL VEHICULO EN EL INGRESO EN
	 * ARRASTRE/EN DEPOSITO
	 * 
	 * @return List<OpcionesCheckVO> LISTA TIPOS DE SELLADO
	 */
	public List<OpcionesCheckVO> buscarOpcionesTipoDeSellado();

	/**
	 * CARGA EL CATALGO PARA LOS ESTATUS DE CAJUAELA DEL VEHICULO EN EL INGRESO
	 * ABIERTA/CERRADA
	 * 
	 * @return List<OpcionesCheckVO> lista de estatus de cajuela
	 */
	public List<OpcionesCheckVO> buscarOpcionesEstatusCajuela();

	/**
	 * CARGA EL CATALOGO DE ACCESORIOS DE VEHICULOS PARA EL CHECK LIST
	 * 
	 * @return List<ComponentesInventarioVO> LISTA DE INVENTARIOS
	 */
	public List<ComponentesInventarioDTO> buscarComponentesInventario();

	/**
	 * CARGA EL CATALGO PARA LOS ESTATUS DE PLACA DEL VEHICULO
	 * 
	 * @return List<OpcionesCheckVO> lista de estatus de placa Vehiculo
	 */
	public List<OpcionesCheckVO> buscarOpcionesEstatusPlacaVehiculo();

	/**
	 * CARGA EL CATALGO PARA LOS TIPOS DE ORIGEN DEL VEHICULO
	 * 
	 * @return List<OpcionesCheckVO> lista de tipos de origen Vehiculo
	 */
	public List<OpcionesCheckVO> buscarOpcionesOrigenVehiculo();

	/**
	 * CARGA EL CATALGO PARA LOS ESTATUS DE VIOLACION DE LEY DE TRANSPORTE
	 * 
	 * @return List<OpcionesCheckVO> lista de tipos de origen Vehiculo
	 */
	public List<OpcionesCheckVO> buscarOpcionesViolacionLeyTransporte();

	/**
	 * CARGA EL CATALOGO DE CAUSA INGRESO EXCLUYENDO LOS ELEMNTOS CON EL ID
	 * INDICADO
	 * 
	 * @param idCausaIngreso
	 * @return List<CausaIngresoVO>
	 */
	public List<CausaIngresoDTO> buscarCausaIngresoDiferentesAlId(Long... idCausaIngreso);

	/************************************************************************
	 * OBTIENE EL LISTADO DE OPCIONES, DEL PAR ETIQUETA/VALOR PARA EL FILTRADO
	 * EN LA CONSULTA DE REMISIONES
	 * 
	 * @return List<OpcionesBusquedaVO> LISTA CON NOMBRE/VALOR
	 *************************************************************************/
	public List<OpcionesBusquedaVO> buscarOpcionesConsultaRemision();

	/**
	 * CARGA EL CATALGO PARA LOS Operativos
	 * 
	 * @return List<OperativoVO> lista de tipos de operativos
	 */
	public List<OperativoDTO> buscarOperativos();

	/**
	 * 
	 * Retorna Estado por codigo
	 * 
	 * @param codigo
	 * @return List<EstadoDTO>
	 */
	public List<EstadoDTO> buscarEstadoPorCodigo(String codigo);

	/**
	 * Retorna registros activos del catalogo Responsable_
	 * 
	 * @return
	 */
	public List<ResponsableVehiculoDTO> buscarResponsableVehiculoConEstatusActivo();

	/***
	 * @author Jesus Gutierrez LLENA UN LISTA CON VALORES DE FILTRADO PARA EL
	 *         SUB-MODULO DE CONSULTA DE DOCUMENTOS DENTRO DE PARTE INFORMATIVO
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> filtroPIDocumentos();

	/***
	 * @author Jesus Gutierrez LLENA UN LISTA CON VALORES DE FILTRADO PARA EL
	 *         SUB-MODULO DE CONSULTA DE BOLETAS DENTRO DE PARTE INFORMATIVO
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> filtroPIBoletas();

	/***
	 * @author Jesus Gutierrez LLENA UN LISTA CON OPCIONES PARA EL SUB-MODULO DE
	 *         MODIFICACION DE BOLETAS DENTRO DE PARTE INFORMATIVO
	 * @return List<FilterValuesVO> de opciones para la situacion de la boleta
	 */
	public List<FilterValuesVO> opcionesSituacionesBoletas();

	/**
	 * LLENA UN LISTA CON VALORES DE FILTRADO PARA EL MODULO DE ALTA DE
	 * INFRACCION PARA EL TIPO DE ARRASTRE
	 * 
	 * @return
	 */
	public List<FilterValuesVO> filtroInfraccionTipoArrastre();

	/**
	 * LLENA UN LISTA CON VALORES DE FILTRADO PARA EL MODULO DE ALTA DE
	 * INFRACCION PARA EL TIPO DE UNIDAD
	 * 
	 * @return
	 */
	public List<FilterValuesVO> filtroInfraccionTipoUnidad();

	/**
	 * Retorna un listado de todos los registros de la tabla CAT_CON_DIRECCION
	 * 
	 * @return
	 */
	public List<CatConDireccionDTO> todasCatConDireccion();

	/**
	 * Retorna un listado de todos los registros de la tabla CAT_OBSERVE_QUE
	 * 
	 * @return
	 */
	public List<CatObserveQueDTO> todasObserveQue();

	/**
	 * Retorna una lista de delegaciones filtradas por estado
	 * 
	 * @param estadoId
	 * @return
	 */
	public List<DelegacionDTO> buscarDelegacionPorEstado(Long estadoId);

	/**
	 * Retorna una lista de Tipos de Vehiculos activos
	 * 
	 * @param
	 * @return List<VehiculoTipoDTO>
	 */
	public List<VehiculoTipoDTO> buscarTodosVehiculoTipoActivo();

	/**
	 * Retorna una lista de todos los registros del catalogo
	 * GARANTIAS_CAT_DOCUMENTOS
	 * 
	 * @param
	 * @return List<VehiculoTipoDTO>
	 */
	public List<GarantiasCatDocumentosDTO> buscarTodasGarantiasCatDocumentosDTO();

	/**
	 * Retorna una lista de todos los registros del catalogo Tipos de Suministro
	 */
	public List<TipoSuministroDTO> buscarTipoSuministro();

	/**
	 * Retorna una lista de regionales
	 */
	public List<RegionalesDTO> buscarRegionales();

	/**
	 * Retorna una lista Areas operativas por region
	 */
	public List<AreaOperativaDTO> buscarAreaOperativa(Long reg_id);

	/**
	 * Retorna una lista de ofiales por region y por area operativa
	 */
	public List<OficialRecibeVO> buscarOficiales(Long reg_id, Long upc_id);

	/**
	 * Retorna una lista de ofiales por region y por area operativa
	 */
	public List<DelegadoAuxiliarVO> buscarAuxiliar(Long reg_id, Long upc_id);

	/**
	 * Carga el catalogo para Tipo de Radar
	 * 
	 * @author UnitisDes0416
	 * @return List<RadarCatArchivoTipoDTO>
	 */
	List<RadarCatArchivoTipoDTO> buscaCatArchivoTipoActivos();

	/**
	 * Obtiene el años actual y anterior para el salario minimo
	 * 
	 * @author UnitisDes0416
	 * @return List<ComboValuesVO>
	 */
	public List<ComboValuesVO> obtenerAnioSalarioMinimo();

	/**
	 * RETORNA UN REGISTRO DE DEPOSITOS_EMPLEADO FILTRADO POR EL EMP_ID
	 * 
	 * @author Kevin Ojeda
	 * @param Long
	 *            id
	 * @return DepositosEmpleadosDTO
	 */
	public DepositosEmpleadosDTO depositoEmpleadoPorEmpId(Long id);

	/**
	 * Retorna una lista de opciones de busqueda con infracion en el modulo de
	 * Remision a deposito
	 */
	public List<OpcionConInfraccionVO> OpcionesConInfaccion();

	/**
	  * Retorna lista de operativo en el modulo de
	  * Remision a deposito
	  */
	public List<ListOperativoVO> listaOperativo();
	
	/**
	  * Retorna lista de transporte  en el modulo de
	  * Remision a deposito
	  */
	public List<ListMediosTraspVO> listaMediosTrasp();
	
	public List<ListaNoGruasVO> listaNoGruas();
	
	/**
	  * Retorna lista de valores del modulo Remision a Deposito
	  */
	public List<ListCausaIngresoVO> listaCausaIngreso();
	
	public List<ListCausaIngresoVO> listaCausaIngresoSin();

	/***
	 * Retorna una lista con opciones de tipos de fecha para el modulo de fotomulta
	 * @author Jesus Gutierrez
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> filterTiposFecha();

	public List<ListDepositoVO> listaDeposito(Long emp_id, int dep_id);

	/**
	 * Carga el catalogo Concesionarias
	 * 
	 * @author UnitisDes03
	 * @return List<ConcesionariaDTO>
	 */
	public List<ConcesionariaDTO> buscarConcesionarias();

	public List<ListDepositoVO> listaDepositoOrigen();

	public List<ListInventarioVO> listaInventario();

	public List<OpcionConInfraccionVO> OpcionesConsultaRemision();
	
	/**
	 * Actualiza, inserta o desactiva concesionarias
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudConcesionaria(CrudConcesionariaVO concesionariaVO);

	/**
	 * Carga el catalogo GruaStatus
	 * 
	 * @author UnitisDes03
	 * @return List<GruaStatusDTO>
	 */
	public List<GruaStatusDTO> buscarGruaStatus();

	/**
	 * Actualiza, inserta o desactiva Grua Status
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudGruaStatus(CrudGruaStatusVO gruaStatusVO);

	/**
	 * Actualiza, inserta o desactiva Grua
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudGrua(CrudGruaVO crudGruaVO);

	/**
	 * Carga el catalogo Gruas
	 * 
	 * @author UnitisDes03
	 * @return List<GruaDTO>
	 */
	public List<GruaDTO> buscarGruas();

	/**
	 * Carga el catalogo turnos
	 * 
	 * @author UnitisDes03
	 * @return List<TurnoDTO>
	 */
	public List<TurnoDTO> buscarTurnos();

	/**
	 * Actualiza, inserta o desactiva turno
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudTurno(CrudTurnoVO turnoVO);

	/**
	 * Carga el catalogo Zona Servicio
	 * 
	 * @author UnitisDes03
	 * @return List<ZonaServicioDTO>
	 */
	public List<ZonaServicioDTO> buscarZonasServicio();

	/**
	 * Actualiza, inserta o desactiva Zona Servicio
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudZonaServicio(CrudZonaServicioVO zonaServicioVO);

	/**
	 * Carga el catalogo Tipo Empleado
	 * 
	 * @author UnitisDes03
	 * @return List<TipoEmpleadoDTO>
	 */
	public List<TipoEmpleadoDTO> buscarTiposEmpleados();

	/**
	 * Actualiza, inserta o desactiva Tipo Empleado
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudTipoEmpleado(CrudTipoEmpleadoVO crudTipoEmpleadoVO);

	/**
	 * Carga el catalogo Tipo Alarma
	 * 
	 * @author UnitisDes03
	 * @return List<TipoAlarmaDTO>
	 */
	public List<TipoAlarmaDTO> buscarTiposAlarma();

	/**
	 * Actualiza, inserta o desactiva Tipo Alarma
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudTipoAlarma(CrudTipoAlarmaVO crudTipoAlarmaVO);

	/**
	 * Actualiza, inserta o desactiva Estados
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudEstado(CrudEstadoVO crudEstadoVO);

	/**
	 * Carga el catalogo de Regiones
	 * 
	 * @author UnitisDes03
	 * @return List<RegionDTO>
	 */
	public List<RegionDTO> buscarRegiones();

	/**
	 * Actualiza, inserta o desactiva Region
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudRegion(CrudRegionVO crudRegionVO);

	/**
	 * Carga el catalogo de delegacion
	 * 
	 * @author UnitisDes03
	 * @return List<DelegacionDTO>
	 */
	public List<RespuestaBusquedaDelegacionesVO> buscarDelegacionesPorEstado(Long estadoId);

	/**
	 * Actualiza, inserta o desactiva Delegacion
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudDelegacion(CrudDelegacionVO crudDelegacionVO);

	/**
	 * Carga el catalogo de Unidad territorial
	 * 
	 * @author UnitisDes03
	 * @return List<UnidadTerritorialDTO>
	 */
	public List<UnidadTerritorialDTO> buscarUnidadesTerritoriales();

	/**
	 * Actualiza, inserta o desactiva Unidad territorial
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudUnidadTerritorial(CrudUnidadeTerritorialVO crudUnidadeTerritorialVO);

	/**
	 * Carga el catalogo de Sectores
	 * 
	 * @author UnitisDes03
	 * @return List<SectorDTO>
	 */
	public List<SectorDTO> buscarSectores();

	/**
	 * Actualiza, inserta o desactiva Unidad territorial
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudSector(CrudSectorVO crudSectorVO);

	/**
	 * Carga el catalogo de Sectores
	 * 
	 * @author UnitisDes03
	 * @return List<SectorDTO>
	 */
	public List<AgrupamientosDTO> buscarAgrupamientos();

	/**
	 * Actualiza, inserta o desactiva Agrupamientos
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudAgrupamiento(CrudAgrupamientoVO crudAgrupamientoVO);

	/**
	 * Carga el catalogo de Zonas
	 * 
	 * @author UnitisDes03
	 * @return List<ZonaDTO>
	 */
	public List<ZonaDTO> buscarZonas();

	/**
	 * Actualiza, inserta o desactiva Agrupamientos
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudZonas(CrudZonaVO crudZonaVO);

	/**
	 * Carga el catalogo de REGION DEPOSITO
	 * 
	 * @author UnitisDes03
	 * @return List<RegionDepositoDTO>
	 */
	public List<RegionDepositoDTO> buscarRegionDepositoPorEstado(Long estadoId);

	/**
	 * Actualiza, inserta o desactiva Region Deposito
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudRegionDeposito(CrudRegionDepositoVO crudRegionDepositoVO);

	/**
	 * Carga el catalogo de depositos
	 * 
	 * @author UnitisDes03
	 * @return List<RespuestaBusquedaDepositoVO>
	 */
	public List<RespuestaBusquedaDepositoVO> buscarDepositosPorRegion(Long regionId);

	/**
	 * Actualiza, inserta o desactiva Deposito
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudDeposito(CrudDepositoVO crudDepositoVO);

	/**
	 * Carga el catalogo de marca vehiculos
	 * 
	 * @author UnitisDes03
	 * @return List<VehiculoMarcaDTO>
	 */
	public List<VehiculoMarcaDTO> buscarMarcaVehiculoTodos();

	/**
	 * Actualiza, inserta o desactiva marca vehiculo
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudVehiculoMarca(CrudVehiculoMarcaVO crudVehiculoMarcaVO);

	/**
	 * Carga el catalogo de modelo vehiculos
	 * 
	 * @author UnitisDes03
	 * @return List<VehiculoModeloDTO>
	 */
	public List<VehiculoModeloDTO> buscarModeloVehiculo();

	/**
	 * Actualiza, inserta o desactiva modelo vehiculo
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudVehiculoModelo(CrudVehiculoModeloVO crudVehiculoModeloVO);

	/**
	 * Carga el catalogo de modelo vehiculos
	 * 
	 * @author UnitisDes03
	 * @return List<VehiculoSubTipoDTO>
	 */
	public List<VehiculoSubTipoDTO> buscarVehiculoSubTipo();

	/**
	 * Actualiza, inserta o desactiva vehiculo subtipo
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudVehiculoSubTipo(CrudVehiculoSubTipoVO crudVehiculoSubTipoVO);

	/**
	 * Actualiza, inserta o desactiva vehiculo tipo
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudVehiculoTipo(CrudVehiculoTipoVO crudVehiculoTipoVO);

	/**
	 * Carga el catalogo de vehiculo color
	 * 
	 * @author UnitisDes03
	 * @return List<VehiculoColorDTO>
	 */
	public List<VehiculoColorDTO> buscarColorVehiculoTodos();

	/**
	 * Actualiza, inserta o desactiva vehiculo color
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudVehiculoColor(CrudVehiculoColorVO crudVehiculoColorVO);

	/**
	 * Actualiza, inserta o desactiva vehiculo tipo
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudVehiculoResponsable(CrudVehiculoResponsableVO crudVehiculoResponsableVO);

	/**
	 * Carga el catalogo de tipo licencia
	 * 
	 * @author UnitisDes03
	 * @return List<TipoLicenciaDTO>
	 */
	public List<TipoLicenciaDTO> buscarTipoLicenciaTodos();

	/**
	 * Actualiza, inserta o desactiva tipo licencia
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudTipoLicencia(CrudTipoLicenciaVO crudTipoLicenciaVO);

	/**
	 * Carga el catalogo de eventos
	 * 
	 * @author UnitisDes03
	 * @return List<EventoDTO>
	 */
	public List<EventoDTO> buscarEventos();

	/**
	 * Actualiza, inserta o desactiva eventos
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudEvento(CrudEventoVO crudEventoVO);

	/**
	 * Carga el catalogo de Estatus Infraccion
	 * 
	 * @author UnitisDes03
	 * @return List<EstatusInfraccionDTO>
	 */
	public List<EstatusInfraccionDTO> buscarEstatusInfraccion();

	/**
	 * Actualiza, inserta o desactiva Estatus Infraccion
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudEstatusInfraccion(CrudEstatusInfraccionVO crudEstatusInfraccionVO);

	/**
	 * Carga el catalogo de denominaciones
	 * 
	 * @author UnitisDes03
	 * @return List<DenominacionDTO>
	 */
	public List<DenominacionDTO> buscarDenominaciones();

	/**
	 * Actualiza, inserta o desactiva denominaciones
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudDenominacion(CrudDenominacionVO crudDenominacionVO);

	/**
	 * Carga el catalogo de conceptosPagos
	 * 
	 * @author UnitisDes03
	 * @return List<ConceptoPagoDTO>
	 */
	public List<ConceptoPagoDTO> buscarConceptosPago();

	/**
	 * Actualiza, inserta o desactiva conceptosPagos
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudConceptoPago(CrudConceptoPagoVO crudConceptoPagoVO);

	/**
	 * Carga el catalogo de Entidad Pagos
	 * 
	 * @author UnitisDes03
	 * @return List<EntidadPagoDTO>
	 */
	public List<EntidadPagoDTO> buscarEntidadesPago();

	/**
	 * Actualiza, inserta o desactiva Entidad Pagos
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudEntidadPago(CrudEntidadPagosVO crudEntidadPagosVO);

	/**
	 * Carga el catalogo de Bancos
	 * 
	 * @author UnitisDes03
	 * @return List<BancoDTO>
	 */
	public List<BancoDTO> buscarBancos();

	/**
	 * Actualiza, inserta o desactiva Bancos
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudBanco(CrudBancoVO crudBancoVO);

	/**
	 * Actualiza, inserta o desactiva tipo ingreso
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudTipoIngreso(CrudTipoIngresoVO crudTipoIngresoVO);

	/**
	 * Actualiza, inserta o desactiva causa ingreso
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudCausaIngreso(CrudCausaIngresoVO crudCausaIngresoVO);

	/**
	 * Carga el catalogo de areas
	 * 
	 * @author UnitisDes03
	 * @return List<AreaDTO>
	 */
	public List<AreaDTO> buscarAreas();

	/**
	 * Actualiza, inserta o desactiva areas
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudArea(CrudAreaVO crudAreaVO);

	/**
	 * Carga el catalogo de componentes
	 * 
	 * @author UnitisDes03
	 * @return List<ComponentesInventarioDTO>
	 */
	public List<ComponentesInventarioDTO> buscarComponentesInventarioTodos();

	/**
	 * Actualiza, inserta o desactiva Componentes
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudComponente(CrudComponenteVO crudComponenteVO);

	/**
	 * Carga el catalogo de articulos
	 * 
	 * @author UnitisDes03
	 * @return List<ArticuloDTO>
	 */
	public List<ArticuloDTO> buscarArticulos();

	/**
	 * Actualiza, inserta o desactiva articulos
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudArticulo(CrudArticuloVO crudArticuloVO);

	/**
	 * Carga el catalogo de Programas
	 * 
	 * @author UnitisDes03
	 * @return List<ProgramaDTO>
	 */
	public List<ProgramaDTO> buscarProgramas();

	/**
	 * Actualiza, inserta o desactiva programas
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudPrograma(CrudProgramaVO crudProgramaVO);

	/**
	 * Carga el catalogo de Categoria
	 * 
	 * @author UnitisDes03
	 * @return List<CategoriaDTO>
	 */
	public List<CategoriaDTO> buscarCategorias();

	/**
	 * Actualiza, inserta o desactiva Categoria
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudCategoria(CrudCategoriaVO crudCategoriaVO);

	/**
	 * Carga el catalogo de CausalesVO
	 * 
	 * @author UnitisDes03
	 * @return List<CausalesDTO>
	 */
	public List<CausalesDTO> buscarCausales();
	
	public List<CausalesDTO> buscarCausalesActivos();

	/**
	 * Actualiza, inserta o desactiva CausalesVO
	 * 
	 * @author UnitisDes03
	 */
	public void callCrudCausales(CrudCausalesVO crudCausalesVO);
	
	/***
	 * @author Jesus Gutierrez
	 * @param todos
	 * @return
	 */
	public List<TipoRadarDTO> buscarRadares(Boolean todos);
	
	/***
	 * @author Jesus Gutierrez
	 * @return
	 */
	public List<FilterValuesVO> filtroEstatusDetecciones();
	
	/***
	 * @author Jesus Gutierrez
	 * @return
	 */
	public List<FilterValuesVO> filtroProcesadosDetecciones();

	/**
	 * Retorna una lista de filtros de tipos de caja
	 * @author Kevin Ojeda
	 * @param 
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> tipoCaja();

	/**
	 * Retorna una lista de filtros de tipos de resultado para modulo Corte sin Caja, Historico
	 * @author Kevin Ojeda
	 * @param 
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> buscaTipoResultadoSinCajaHistorico();
	
	/**
	 * Carga grua status por id
	 * @author UnitisDes03
	 * @return GruaStatusDTO
	 * @param gruaStatusId
	 */
	public GruaStatusDTO buscarGruaStatusPorId(Long gruaStatusId);
	
	/**
	 * Carga el catalogo de Regiones
	 * 
	 * @author UnitisDes03
	 * @return List<RegionVO>
	 * @param estadoId
	 */
	public List<RegionVO> buscarRegionesPorEstado(Long estadoId);
	
	/**
	 * Carga los catalogos web
	 * @author UnitisDes03
	 * @return List<CatalogoWebDTO>
	 */
	public List<CatalogoWebDTO> buscarCatalogosWeb();
	
	/**
	 * Carga los catalogos opciones web por catalogo id
	 * @author UnitisDes03
	 * @return List<CatalogoWebDTO>
	 * @param catalogoId
	 */
	public List<CatalogoWebOpcionDTO> buscarCatalogosWebOpciones(Long catalogoId);
	
	
	/**
	 * Carga los catalogos gruas por Concesionaria id
	 * @author UnitisDes03
	 * @return List<GruaVO>
	 * @param concesionariaId
	 */
	public List<GruaVO> buscarGruasPorConcesionaria(Long concesionariaId);
	
	/**
	 * Carga los catalogos sectores por delegacion
	 * @author UnitisDes03
	 * @return List<RespuestaBusquedaSectoresVO>
	 * @param delegacionId
	 */
	public List<RespuestaBusquedaSectoresVO> buscarSectoresPorDelegacion(Long delegacionId, Long estadoId);
	
	public List<VehiculoModeloDTO> buscarModeloVehiculoActivoPorMarcaTodos(Long marcaId);
	
	public List<VehiculoTipoDTO> buscarVehiculoTipoPorSubTipo(Long subTipoId);
	
	/***
	 * Retorna un listado de filtros de tipo fecha para el modulo de consulta de lotes fotomulta
	 * @author Jesus Gutierrez
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> filtroTiposFechaForConsultaLotesFM();
	
	/***
	 * Retorna un listado de filtros de estatus proceso para el modulo de consulta de lotes fotomulta
	 * @author Jesus Gutierrez
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> filtroEstatusProceso();

	/**
	 * Retorna una lista de filtros de tipos de busqueda para modulo Corte sin Caja, Actual
	 * @author Kevin Ojeda
	 * @param 
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> buscaTipoBusquedaSinCajaActual();
	
	public List<ArticulosInfraccionesFinanzasDTO> buscarArticulosInfraccionesFinanzasPorArticulo(Long articuloId);
	
	public ArticulosInfraccionesFinanzasDTO buscarArticulosInfraccionesFinanzasPorId(Long id);
	
	public void callCrudArticulosHistoricos(CrudArticulosHistoricoVO articuloVO);

	
	/***
	 * @author Jesus Gutierrez
	 * @return List<FilterValuesVO>
	 * @param perfilId
	 */
	public List<FilterValuesVO> buscaTipoReportesWeb(Long perfilId);

	public List<ProgramaDTO> buscarProgramasActivos();
	
	public List<CategoriaDTO> buscarCategoriasActivas();
	
	public ArticuloDTO buscarArticulosPorId(Long articuloId);

	/**
	 * @author Kevin Ojeda
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> filtroBusquedaDeCaja();
	
	/***
	 * @author Jesus Gutierrez
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> filtroLiberacionVehiculo();
	
	public ByteArrayOutputStream generarReporteExcelConcesionaria() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelGruaStatus() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelGrua(Long concesionariaId) throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelTurno() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelZonasServicio() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelTipoEmpleado() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelTipoAlarma() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelEstado() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelRegion(Long estadoId) throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelDelegacion(Long estadoId) throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelUT(Long sectorId) throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelSector(Long delegacionId, Long edoId) throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelAgrupamientos() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelZonaDeposito() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelRegionDeposito(Long estadoId) throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelDeposito(Long regionId) throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelVehiculoMarca() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelVehiculoModelo(Long marcaId) throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelVehiculoSubTipo() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelVehiculoTipo(Long subTipoId) throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelVehiculoColor() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelVehiculoResponsable() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelVehiculoTipoLicencia() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelInfraccionEvento() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelInfraccionStatus() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelDenominacion() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelConceptoPago() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelEntidad() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelBanco() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelTipoIngreso() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelCausaIngreso() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelAreaIngreso() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelComponente() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelPrograma() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelCategorias() throws IOException;
	
	public ByteArrayOutputStream generarReporteExcelCausales() throws IOException;
	
	/***
	 * @author Jesus Gutierrez
	 * @param status
	 * @return
	 */
	public List<ComboValuesVO> filtroComponentesSoporte(Integer status);
	
	/***
	 * @author Jesus Gutierrez
	 * @param componenteId
	 * @return
	 */
	public List<ConceptosSoporteDTO> filtroConceptosSoporte(Long componenteId, Integer valido);
	
	/***
	 * @author Jesus Gutierrez
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> filtroEmbargos();
	
	public List<CausaIngresoDTO> buscarCausaIngresoTodos();

	/***
	 * @author Jesus Gutierrez
	 * @return FilterValuesVO
	 * @param detcOrfoto
	 * Obtiene los valores para el filtro que identifica el origen de la deteccion
	 */
	
	public List<FilterValuesVO> filtroOrigenPlaca(Boolean detcOrfoto);
	
	/***
	 * @author Jesus Gutierrez
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> registrosPorPagina();

	public List<ListCausaIngresoVO> listaCausaIngresoByTraslado();
	
	/***
	 * Retorna una lista con tipos de detecciones para el modulo de Dispocitivos Fijos 
	 * @author José Carmen Castillo Navarrete 
	 * @return List<FMTiposDeteccionesVO>
	 */
	public List<FMTiposDeteccionesVO> fmTiposDetecciones(Boolean todos);
	
	/***
	 * Retorna una lista con tipos de detecciones para el modulo de Dispocitivos Fijos 
	 * @author José Carmen Castillo Navarrete
	 * @return List<FMMarcaDispositivoVO>
	 */
	public List<FMMarcaDispositivoVO> fmTiposRadaresByMarca(Boolean todos, Integer idMarca);
	
	/***
	 * Retorna una lista con tipos de detecciones para el modulo de Dispocitivos Fijos 
	 * @author José Carmen Castillo Navarrete
	 * @return List<FilterValuesVO>
	 */
	public List<FilterValuesVO> fmPeriodoFecha(Boolean option);
	
	public List<FilterValuesVO> buscaTipoDescuento();
	
	public List<FMTipoFotocivicaVO> fcTiposDetecciones(Integer todos);
	
	public List<FMTipoArchivoFCVO> buscaTipoArchivo(Integer origenPlaca);
	
	public List<FMTipoFotocivicaVO> fcTiposDeteccionesOpcion(Integer stActivo);
	
	public List<FMTipoArchivoFCVO> buscaTipoArchivoTodo(Integer opcion);

	/***
	 * @author Jose Castillo
	 * @return FMOrigenPlacaFCVO
	 * @param detcOrfoto
	 * Obtiene los valores para el filtro que identifica el origen de la plava vehicular
	 */
	
	public List<FMOrigenPlacaFCVO> filtroOrigenPlacaFC(Integer todos);
	
	public List<FilterValuesVO> filtroTiposBusquedaDetFC(Integer tipoProcesable);
		/***
	 * @author Fernando Campero
	 * @return List<TipoBusquedaAtcVO>
	 * Obtiene los tipos/filtros para busqueda de tramites para atención ciudadana
	 */
	List<TipoBusquedaAtcVO> buscarTiposBusquedaTramitesAtencionCiudadana();
	/**
	 * RETORNA LOS PARAMETROS DE LA TABLA TAI041P_CONFIGURACION, DEPEDIENDO DE LA OPCION:
	 *  
	 * 0 -> Todos ,
	 * 1 -> Solo Querys,
	 * 2 -> Solo parametros, 
	 * 3 -> Buscar por Nombre de configuracion + valor,
	 * 4 -> Por codigo de parametro + valor,
	 * 
	 * @author Fernando Campero
	 * @return Map<String, String>
	 * @param op : es la opcion requerida int
	 * @param valor : es el valor buscado (opción 3 y 4) String 
	 */
	Map<String, String> getParametrosBD(int op, String valor);
	
	public List<TipoFechasDTO> tipoFechas();
	
	public List<GarantiaDocumentoDTO> catalogoDocGarantias();
	
	public List<EstatusSeguimientoTramiteACVO> buscaEstatusTramite(Integer todos);
	
	public List<FilterValuesVO> parametrosBusquedaAC();
}
