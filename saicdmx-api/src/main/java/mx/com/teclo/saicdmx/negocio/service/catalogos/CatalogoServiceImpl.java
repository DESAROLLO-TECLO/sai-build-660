package mx.com.teclo.saicdmx.negocio.service.catalogos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitAdminCatalogoAgentes;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitAdminCatalogoAgrupamientos;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitAdminCatalogoArticulo;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitAdminCatalogoDepositos;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitAdminCatalogoGruas;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitAdminCatalogoInfracciones;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitAdminCatalogoIngresos;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitAdminCatalogoPagos;
import mx.com.teclo.saicdmx.bitacora.cambios.administracion.BitAdminCatalogoVehiculos;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.articulos.ArticuloDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.articulos.ArticulosInfraccionesFinanzasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.AgrupamientosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.AreaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.AreaOperativaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.BancoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CatConDireccionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CatObserveQueDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CatalogoWebDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CatalogoWebOpcionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CategoriaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CausaIngresoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CausalDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ComponentesInventarioDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ComponentesSoporteDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ConceptoPagoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ConceptosSoporteDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ConcesionariaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.DelegacionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.DenominacionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.DepositoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.DepositosDAOImpl;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.EntidadPagoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.EstadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.EstatusInfraccionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.EventoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.GarantiasCatDocumentosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.GruaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.GruaStatusDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.OperativoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ProgramaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.RadarCatArchivoTipoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.RegionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.RegionDepositoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.RegionalesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ResponsableVehiculoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.SectorDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.TipoAlarmaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.TipoBusquedaAtcDAOImpl;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.TipoEmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.TipoFechasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.TipoIngresoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.TipoLicenciaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.TipoRadarDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.TipoSuministroDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.TurnoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.UnidadTerritorialDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.VInfraccionUsuariosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.VehiculoColorDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.VehiculoMarcaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.VehiculoModeloDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.VehiculoSubTipoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.VehiculoTipoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ZonaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ZonaServicioDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias.GarantiasDocumentoDAO;
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
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ComponentesSoporteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ConceptoPagoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ConceptosSoporteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ConcesionariaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DelId;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DelegacionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DenominacionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosDTO;
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
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoBusquedaAtcDTO;
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
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoModeloId;
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
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoEmpleadoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.UTIdVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoMarcaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoModeloIdVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoSubTipoVO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.atencionCiudadana.TramitesACMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.AgrupamientosMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.AreaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.ArticuloMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.BancoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.CategoriaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.CausaIngresoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.CausalesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.ComponenteMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.ConceptoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.ConcesionariaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.DelegacionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.DenominacionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.DepositoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.EntidadPagosMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.EstadoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.EstatusInfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.EventoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.GruaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.GruaStatusMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.ParametrosBDDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.ProgramaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.RegionDepositoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.RegionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.SectorMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.TipoAlarmaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.TipoEmpleadoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.TipoIngresoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.TipoLicenciaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.TurnoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.UnidadeTerritorialMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.VehiculoColorMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.VehiculoMarcaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.VehiculoModeloMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.VehiculoResponsableMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.VehiculoSubTipoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.VehiculoTipoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.ZonaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.ZonaServicioMybatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.CatalogoOficialRecibeMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.DelegadoAuxiliarMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarabitacoraproceso.RadarBitacoraProcesoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito.CatalogosIngresoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportesweb.ReportesWebMybatisDAO;
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
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListCausaIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListInventarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListMediosTraspVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListOperativoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListaNoGruasVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.OpcionConInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportesweb.ReportesWebVO;
import mx.com.teclo.saicdmx.util.comun.Infracciones;
import mx.com.teclo.saicdmx.util.comun.Remisiones;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.EstatusProcesoLote;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclo.saicdmx.util.enumerados.RadarTipoArchivo;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class CatalogoServiceImpl implements CatalogoService {

	private static final Logger logger = Logger.getLogger(CatalogoServiceImpl.class);

	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;
	@Autowired
	private BitAdminCatalogoAgrupamientos bitAdminCatalogoAgrupamientos;
	@Autowired
	private BitAdminCatalogoArticulo bitAdminCatalogoArticulos;
	@Autowired
	private BitAdminCatalogoAgentes bitAdminCatalogoAgentes;
	@Autowired
	private BitAdminCatalogoDepositos bitAdminCatalogoDepositos;
	@Autowired
	private BitAdminCatalogoGruas bitAdminCatalogoGruas;
	@Autowired
	private BitAdminCatalogoInfracciones bitAdminCatalogoInfracciones;
	@Autowired
	private BitAdminCatalogoIngresos bitAdminCatalogoIngresos;
	@Autowired
	private BitAdminCatalogoPagos bitAdminCatalogoPagos;
	@Autowired
	private BitAdminCatalogoVehiculos bitAdminCatalogoVehiculos;
	@Autowired
	private GruaDAO gruaDao;
	@Autowired
	private ResponsableVehiculoDAO responsableVehiculoDao;
	@Autowired
	private VInfraccionUsuariosDAO vInfraccionUsuarioDAO;
	@Autowired
	private TipoIngresoDAO tIngrDao;
	@Autowired
	private CatConDireccionDAO catConDireccionDAO;
	@Autowired
	private CatObserveQueDAO catObserveQueDAO;
	@Autowired
	private RadarCatArchivoTipoDAO radarCatArchivoTipoDAO;
	@Autowired
	private DepositosDAOImpl depositosDAO;
	@Autowired
	private ConcesionariaDAO concesionariaDAO;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private GruaStatusDAO gruaStatusDAO;
	@Autowired
	private TurnoDAO turnoDAO;
	@Autowired
	private ZonaServicioDAO zonaServicioDAO;
	@Autowired
	private ConcesionariaMyBatisDAO concesionariaMyBatisDAO;
	@Autowired
	private GruaMyBatisDAO gruaMyBatisDAO;
	@Autowired
	private GruaStatusMyBatisDAO gruaStatusMyBatisDAO;
	@Autowired
	private TurnoMyBatisDAO turnoMyBatisDAO;
	@Autowired
	private ZonaServicioMybatisDAO zonaServicioMybatisDAO;
	@Autowired
	private TipoEmpleadoMyBatisDAO tipoEmpleadoMyBatisDAO;
	@Autowired
	private TipoEmpleadoDAO tipoEmpleadoDAO;
	@Autowired
	private TipoAlarmaMyBatisDAO tipoAlarmaMyBatisDAO;
	@Autowired
	private TipoAlarmaDAO tipoAlarmaDAO;
	@Autowired
	private EstadoMyBatisDAO estadoMyBatisDAO;
	@Autowired
	private RegionDAO regionDAO;
	@Autowired
	private RegionMyBatisDAO regionMyBatisDAO;
	@Autowired
	private DelegacionMyBatisDAO delegacionMyBatisDAO;
	@Autowired
	private UnidadeTerritorialMyBatisDAO unidadeTerritorialMyBatisDAO;
	@Autowired
	private SectorMyBatisDAO sectorMyBatisDAO;
	@Autowired
	private AgrupamientosDAO agrupamientosDAO;
	@Autowired
	private AgrupamientosMyBatisDAO agrupamientosMyBatisDAO;
	@Autowired
	private ZonaDAO zonaDAO;
	@Autowired
	private ZonaMyBatisDAO zonaMyBatisDAO;
	@Autowired
	private RegionDepositoDAO regionDepositoDAO;
	@Autowired
	private RegionDepositoMyBatisDAO regionDepositoMyBatisDAO;
	@Autowired
	private DepositoDAO depositoDAO;
	@Autowired
	private DepositoMyBatisDAO depositoMyBatisDAO;
	@Autowired
	private VehiculoMarcaMyBatisDAO vehiculoMarcaMyBatisDAO;
	@Autowired
	private VehiculoModeloMyBatisDAO vehiculoModeloMyBatisDAO;
	@Autowired
	private VehiculoSubTipoMyBatisDAO vehiculoSubTipoMyBatisDAO;
	@Autowired
	private VehiculoSubTipoDAO vehiculoSubTipoDAO;
	@Autowired
	private VehiculoTipoMyBatisDAO vehiculoTipoMyBatisDAO;
	@Autowired
	private VehiculoColorMyBatisDAO vehiculoColorMyBatisDAO;
	@Autowired
	private VehiculoResponsableMyBatisDAO vehiculoResponsableMyBatisDAO;
	@Autowired
	private TipoLicenciaMyBatisDAO tipoLicenciaMyBatisDAO;
	@Autowired
	private EventoDAO eventoDAO;
	@Autowired
	private EventoMyBatisDAO eventoMyBatisDAO;
	@Autowired
	private EstatusInfraccionDAO estatusInfraccionDAO;
	@Autowired
	private EstatusInfraccionMyBatisDAO estatusInfraccionMyBatisDAO;
	@Autowired
	private DenominacionDAO denominacionDAO;
	@Autowired
	private DenominacionMyBatisDAO denominacionMyBatisDAO;
	@Autowired
	private ConceptoPagoDAO conceptoPagoDAO;
	@Autowired
	private ConceptoMyBatisDAO conceptoMyBatisDAO;
	@Autowired
	private EntidadPagoDAO entidadPagoDAO;
	@Autowired
	private EntidadPagosMyBatisDAO entidadPagosMyBatisDAO;
	@Autowired
	private BancoDAO bancoDAO;
	@Autowired
	private BancoMyBatisDAO bancoMyBatisDAO;
	@Autowired
	private TipoIngresoMyBatisDAO tipoIngresoMyBatisDAO;
	@Autowired
	private CausaIngresoMyBatisDAO causaIngresoMyBatisDAO;
	@Autowired
	private AreaDAO areaDAO;
	@Autowired
	private AreaMyBatisDAO areaMyBatisDAO;
	@Autowired
	private ComponenteMyBatisDAO componenteMyBatisDAO;
	@Autowired
	private ArticuloDAO articuloDAO;
	@Autowired
	private ArticuloMyBatisDAO articuloMyBatisDAO;
	@Autowired
	private ProgramaDAO programaDAO;
	@Autowired
	private ProgramaMyBatisDAO programaMyBatisDAO;
	@Autowired
	private CategoriaDAO categoriaDAO;
	@Autowired
	private CategoriaMyBatisDAO categoriaMyBatisDAO;
	@Autowired
	private CausalDAO causalDAO;
	@Autowired
	private CausalesMyBatisDAO causalesMyBatisDAO;
	@Autowired
	private ReportesWebMybatisDAO reportesWebMyBatisDAO;
	@Autowired
	private RadarBitacoraProcesoMyBatisDAO radarBitacoraProcesoMyBatisDAO;
	@Autowired
	private TipoBusquedaAtcDAOImpl tipoBusquedaAtcDAOImpl;
	@Autowired
	private TramitesACMyBatisDAO tramitesACMyBatisDAO; 
	///////////////////////////////////////////////////
	// ESTADO / DELEGACION
	///////////////////////////////////////////////////
	@Autowired
	private EstadoDAO estadoDAO;

	@Autowired
	private DelegacionDAO delegacionDAO;

	///////////////////////////////////////////////////
	// SECTOR / UNIDAD TERRITORIAL
	///////////////////////////////////////////////////
	@Autowired
	private SectorDAO sectorDAO;

	@Autowired
	private UnidadTerritorialDAO unidadTerritorialDAO;

	///////////////////////////////////////////////////
	// TIPO LICENCIA
	///////////////////////////////////////////////////
	@Autowired
	private TipoLicenciaDAO tipoLicenciaDAO;

	///////////////////////////////////////////////////
	// VEHICULO TIPO / MARCA / MODELO / COLOR
	///////////////////////////////////////////////////
	@Autowired
	private VehiculoTipoDAO vehiculoTipoDAO;
	@Autowired
	private VehiculoMarcaDAO vehiculoMarcaDAO;
	@Autowired
	private VehiculoModeloDAO vehiculoModeloDAO;
	@Autowired
	private VehiculoColorDAO vehiculoColorDAO;
	@Autowired
	private CausaIngresoDAO causaIngresoDAO;

	///////////////////////////////////////////////////
	// ARTICULO
	///////////////////////////////////////////////////

	///////////////////////////////////////////////////
	// ACCESORIOS DE VEHICULO
	///////////////////////////////////////////////////
	@Autowired
	private ComponentesInventarioDAO componentesInventarioDAO;

	@Autowired
	private OperativoDAO operativoDAO;

	@Autowired
	private GarantiasCatDocumentosDAO garantiasCatDocumentosDAO;

	///////////////////////////////////////////////////
	// TIPO Suministro / Regionales / Oficiales / Auxiliares
	///////////////////////////////////////////////////
	@Autowired
	private TipoSuministroDAO tipoSuministroDAO;

	@Autowired
	private RegionalesDAO regionalesDAO;

	@Autowired
	private AreaOperativaDAO areaOperativaDAO;

	@Autowired
	private CatalogoOficialRecibeMyBatisDAO oficialesDAO;

	@Autowired
	private DelegadoAuxiliarMyBatisDAO auxiliaresDAO;

	@Autowired
	private CatalogosIngresoMyBatisDAO catalogoIngresoDAO;

	@Autowired
	private TipoRadarDAO tipoRadarDAO;

	@Autowired
	private ComponentesSoporteDAO componentesSoporteDAO;

	@Autowired
	private ConceptosSoporteDAO conceptosSoporteDAO;

	@Autowired
	private ArticulosInfraccionesFinanzasDAO articulosInfraccionesFinanzasDAO;

	@Autowired
	private CatalogoWebOpcionDAO catalogoWebOpcionDAO;

	@Autowired
	private CatalogoWebDAO catalogoWebDAO;
	
	@Autowired
	private FMDeteccionesMyBatisDAO deteccionesMyBatisDAO;

	@Autowired
	private ParametrosBDDAO parametrosBDDAO;
	
	@Autowired
	private TipoFechasDAO tipoFechasDAO;
	
	@Autowired
	private GarantiasDocumentoDAO garantiasDocumentoDAO;
	
	
	@Transactional(readOnly = true)
	@Override
	public List<ResponsableVehiculoDTO> buscarResponsableVehiculo() {
		return responsableVehiculoDao.findAll();
	}

	@Transactional
	@Override
	public ResponsableVehiculoDTO buscarResponsableVehiculo(long idResponsableVehiculo) {
		return responsableVehiculoDao.buscarResponsableVehiculo(idResponsableVehiculo);
	}

	@Transactional
	@Override
	public List<ResponsableVehiculoDTO> buscarResponsableVehiculoConEstatusActivo() {
		return responsableVehiculoDao.buscarResponsableVehiculoConEstatusActivo();
	}

	@Transactional
	@Override
	public List<TipoIngresoDTO> buscarTipoIngreso() {
		return tIngrDao.findAll();
	}

	@Transactional
	@Override
	public TipoIngresoDTO buscarTipoIngresoPorId(Long idTipoIngreso) {
		return tIngrDao.buscarPorId(idTipoIngreso);
	}

	@Transactional
	@Override
	public List<TipoIngresoDTO> buscarTipoIngresoDiferentseAlId(Long idTipoIngreso) {
		return tIngrDao.buscarDiferentesAlId(idTipoIngreso);
	}

	@Transactional
	@Override
	public GruaDTO buscarGrua(String gCod) {
		return gruaDao.buscarGrua("A", gCod);
	}

	@Transactional
	@Override
	public VInfraccionUsuariosDTO buscarEmpleado(String placa) {
		return vInfraccionUsuarioDAO.buscarOficial(placa);
	}

	@Transactional
	@Override
	public List<GarantiasCatDocumentosDTO> buscarTodasGarantiasCatDocumentosDTO() {
		return garantiasCatDocumentosDAO.findAll();
	}
	///////////////////////////////////////////////////
	// ESTADO / DELEGACION
	///////////////////////////////////////////////////

	@Override
	@Transactional
	public List<DelegacionDTO> buscarDelegacion(int edo) {
		List<DelegacionDTO> delegs = new ArrayList<DelegacionDTO>();
		EstadoDTO estado = estadoDAO.findOne(edo);
		// delegs.addAll(estado.getDelegaciones());
		return delegs;
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadoDTO> buscarEstadosTodos() {
//		logger.info("ENTRANDO A getEstados() -- OK");
		return estadoDAO.findAll();
	}

	@Override
	@Transactional
	public EstadoDTO buscarEstado(int id) {
		return estadoDAO.findOne(id);
	}

	@Override
	@Transactional
	public DelegacionDTO buscarDelegacion(long id, EstadoDTO edo) {
		DelId dId = new DelId();
		dId.setDelId(id);
		EstadoDTO estadoDTO = estadoDAO.buscarEstado(edo.getEdoId());
		dId.setEdoId(estadoDTO.getEdoId());
		return delegacionDAO.findOne(dId);
	}

	@Override
	@Transactional
	public List<DelegacionDTO> buscarDelegacionPorEstado(Long estadoId) {
		return delegacionDAO.buscarDelegacionPorEstado(estadoId);
	}

	@Override
	@Transactional
	public List<EstadoDTO> buscarEstadoPorCodigo(String codigo) {

		List<EstadoDTO> estadosDTO = estadoDAO.buscarEstadoPorCodigo(codigo);
		if (estadosDTO == null)
			return null;
		return estadosDTO;
	}

	///////////////////////////////////////////////////
	// SECTOR / UNIDAD TERRITORIAL
	///////////////////////////////////////////////////
	@Override
	@Transactional
	public List<SectorDTO> buscarSectoresPorEstado(Long estadoId, String status) {
		return sectorDAO.buscarSectores(estadoId, status);
	}

	@Override
	@Transactional
	public List<UnidadTerritorialDTO> buscarUnidadesTerritorialesPorSector(Long idSector) {
		return unidadTerritorialDAO.buscarUnidadesTerritorialesPorSector(idSector);
	}

	@Override
	@Transactional
	public SectorDTO buscarSector(long id) {
		return sectorDAO.buscarSector(id);
	}

	@Override
	@Transactional
	public List<CatConDireccionDTO> todasCatConDireccion() {
		return catConDireccionDAO.findAll();
	}

	@Override
	@Transactional
	public List<CatObserveQueDTO> todasObserveQue() {
		return catObserveQueDAO.findAll();
	}

	///////////////////////////////////////////////////
	// TIPO LICENCIA
	///////////////////////////////////////////////////
	@Override
	@Transactional
	public List<TipoLicenciaDTO> buscarTipoLicencia() {
		return tipoLicenciaDAO.buscarListaTipoLicencia(Infracciones.ESTATUS_ACTIVO);
	}

	@Override
	@Transactional
	public TipoLicenciaDTO buscarTipoLicencia(long idTipoLicencia) {
		return tipoLicenciaDAO.buscarTipoLicencia(idTipoLicencia);
	}

	@Override
	@Transactional
	public TipoLicenciaDTO buscarTipoLicencia(String tipoLicenciaNombre) {
		return tipoLicenciaDAO.buscarTipoLicencia(tipoLicenciaNombre);
	}

	///////////////////////////////////////////////////
	// VEHICULO TIPO / MARCA / MODELO / COLOR
	///////////////////////////////////////////////////
	@Override
	@Transactional
	public List<VehiculoTipoDTO> buscarTipoVehiculo() {
		return vehiculoTipoDAO.findAll();
	}

	@Override
	@Transactional
	public List<VehiculoTipoDTO> buscarTodosVehiculoTipoActivo() {
		return vehiculoTipoDAO.buscarTodosVehiculoTipoActivo();
	}

	@Override
	@Transactional
	public List<VehiculoMarcaDTO> buscarMarcaVehiculo() {
		return vehiculoMarcaDAO.buscarVehiculoMarca("A");
	}

	@Override
	@Transactional
	public List<VehiculoModeloDTO> buscarModeloVehiculoActivoPorMarca(Long marcaId) {
		return vehiculoModeloDAO.buscarVehiculoModelo(marcaId, "A");
	}

	@Override
	@Transactional
	public List<VehiculoColorDTO> buscarColorVehiculo() {
		return vehiculoColorDAO.buscarColor("A");
	}

	@Override
	@Transactional
	public VehiculoTipoDTO buscarTipoVehiculo(long id) {
		return vehiculoTipoDAO.findOne(id);
	}

	@Override
	@Transactional
	public VehiculoColorDTO buscarColorVehiculo(long id) {
		return vehiculoColorDAO.findOne(id);
	}

	@Override
	@Transactional
	public VehiculoModeloDTO buscarModeloVehiculo(Long vMarId, Long vModId) {
		VehiculoModeloId modId = new VehiculoModeloId();

		modId.setvMarId(vMarId);
		modId.setvModId(vModId);

		VehiculoModeloDTO vehModeloDTO = vehiculoModeloDAO.findOne(modId);
		return vehModeloDTO;
	}

	@Override
	@Transactional
	public VehiculoMarcaDTO buscarMarcaVehiculo(long idVehiculoMarca) {
		return vehiculoMarcaDAO.buscarVehiculoMarca(idVehiculoMarca);
	}

	@Override
	public List<OpcionesBusquedaVO> buscarOpcionesBusqueda() {
		List<OpcionesBusquedaVO> listaOpcionesBusqueda = new ArrayList<OpcionesBusquedaVO>();
		OpcionesBusquedaVO opcionesBusquedaVO = new OpcionesBusquedaVO();
		String nombreOpcion = Infracciones.LABEL_PLACA_PERMISO;
		String valorOpcion = Infracciones.PLACA_PERMISO;
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		opcionesBusquedaVO = new OpcionesBusquedaVO();
		nombreOpcion = Infracciones.LABEL_BOLETA_PREIMPRESA;
		valorOpcion = Infracciones.BOLETA_PREIMPRESA;
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		opcionesBusquedaVO = new OpcionesBusquedaVO();
		nombreOpcion = Infracciones.LABEL_NUMERO_INFRACCION;
		valorOpcion = Infracciones.INFRACCION;
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		opcionesBusquedaVO = new OpcionesBusquedaVO();
		nombreOpcion = Infracciones.LABEL_LICENCIA_DE_CONDUCIR;
		valorOpcion = Infracciones.LICENCIA_DE_CONDUCIR;
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		return listaOpcionesBusqueda;
	}

	@Override
	public List<OpcionesBusquedaVO> buscarOpcionesBusquedaIngresoPorInfraccion() {
		List<OpcionesBusquedaVO> listaOpcionesBusqueda = new ArrayList<OpcionesBusquedaVO>();
		OpcionesBusquedaVO opcionesBusquedaVO = new OpcionesBusquedaVO();
		String nombreOpcion = " No. de Placa";
		String valorOpcion = "PLACA";
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		opcionesBusquedaVO = new OpcionesBusquedaVO();
		nombreOpcion = "Infraccion";
		valorOpcion = "INFRACCION";
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		opcionesBusquedaVO = new OpcionesBusquedaVO();
		nombreOpcion = "Infraccion Impresa";
		valorOpcion = "PREIMPRESA";
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		opcionesBusquedaVO = new OpcionesBusquedaVO();
		nombreOpcion = "NCI";
		valorOpcion = "NCI";
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		return listaOpcionesBusqueda;
	}

	// TIPO DE ARRASTRE
	@Override
	public List<TipoArrastreVO> buscarTipoArrastre() {
		List<TipoArrastreVO> listaTipoArrastre = new ArrayList<TipoArrastreVO>();

		TipoArrastreVO tipoArrastreVO = new TipoArrastreVO();
		tipoArrastreVO.setNombreTipoArrastre(Infracciones.LABEL_SIN_ARRASTRE);
		tipoArrastreVO.setValorTipoArrastre(Infracciones.INFRACCION_SIN_ARRASTRE);
		listaTipoArrastre.add(tipoArrastreVO);

		tipoArrastreVO = new TipoArrastreVO();
		tipoArrastreVO.setNombreTipoArrastre(Infracciones.LABEL_ARRASTRE_POR_INFRACCION);
		tipoArrastreVO.setValorTipoArrastre(Infracciones.ARRASTRE_POR_INFRACCION);
		listaTipoArrastre.add(tipoArrastreVO);

		tipoArrastreVO = new TipoArrastreVO();
		tipoArrastreVO.setNombreTipoArrastre(Infracciones.LABEL_ARRASTRE_POR_ABANDONO);
		tipoArrastreVO.setValorTipoArrastre(Infracciones.ARRASTRE_POR_ABANDONO);
		listaTipoArrastre.add(tipoArrastreVO);

		return listaTipoArrastre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.teclo.siidf.negocio.catalogos.facade.CatalogoFacade#
	 * buscarCausaIngreso()
	 */
	@Override
	@Transactional
	public List<CausaIngresoDTO> buscarCausaIngreso() {
		return causaIngresoDAO.findAll();
	}

	@Override
	@Transactional
	public List<CausaIngresoDTO> buscarCausaIngresoTodos() {
		return causaIngresoDAO.buscarTodos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.teclo.siidf.negocio.catalogos.facade.CatalogoFacade#
	 * buscarOpcionesTipoDeSellado()
	 */
	@Override
	public List<OpcionesCheckVO> buscarOpcionesTipoDeSellado() {
		List<OpcionesCheckVO> listaOpciones = new ArrayList<OpcionesCheckVO>();
		OpcionesCheckVO OpcionesCheckVO = new OpcionesCheckVO();

		OpcionesCheckVO.setNombreOpcion(Remisiones.LABEL_VEHICULO_SELLADO_EN_ARRASTRE);
		OpcionesCheckVO.setValorOpcion(Remisiones.ID_VEHICULO_SELLADO_EN_ARRASTRE);
		listaOpciones.add(OpcionesCheckVO);

		OpcionesCheckVO = new OpcionesCheckVO();
		OpcionesCheckVO.setNombreOpcion(Remisiones.LABEL_VEHICULO_SELLADO_EN_DEPOSITO);
		OpcionesCheckVO.setValorOpcion(Remisiones.ID_VEHICULO_SELLADO_EN_DEPOSITO);
		listaOpciones.add(OpcionesCheckVO);

		return listaOpciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.teclo.siidf.negocio.catalogos.facade.CatalogoFacade#
	 * buscarOpcionesEstatusCajuela()
	 */
	@Override
	public List<OpcionesCheckVO> buscarOpcionesEstatusCajuela() {
		List<OpcionesCheckVO> listaOpciones = new ArrayList<OpcionesCheckVO>();
		OpcionesCheckVO OpcionesCheckVO = new OpcionesCheckVO();

		OpcionesCheckVO.setNombreOpcion(Remisiones.LABEL_CAJUELA_ABIERTA_SI);
		OpcionesCheckVO.setValorOpcion(Remisiones.ID_CAJUELA_ABIERTA_SI);
		listaOpciones.add(OpcionesCheckVO);

		OpcionesCheckVO = new OpcionesCheckVO();
		OpcionesCheckVO.setNombreOpcion(Remisiones.LABEL_CAJUELA_ABIERTA_NO);
		OpcionesCheckVO.setValorOpcion(Remisiones.ID_CAJUELA_ABIERTA_NO);
		listaOpciones.add(OpcionesCheckVO);

		return listaOpciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.teclo.siidf.negocio.catalogos.facade.CatalogoFacade#
	 * buscarOpcionesEstatusPlacaVehiculo()
	 */
	@Override
	public List<OpcionesCheckVO> buscarOpcionesEstatusPlacaVehiculo() {
		List<OpcionesCheckVO> listaOpciones = new ArrayList<OpcionesCheckVO>();
		OpcionesCheckVO opcionesCheckVO = new OpcionesCheckVO();

		opcionesCheckVO.setNombreOpcion(Remisiones.LABEL_VEHICULO_TIENE_PLACA_SI);
		opcionesCheckVO.setValorOpcion(Remisiones.ID_VEHICULO_TIENE_PLACA_SI);
		listaOpciones.add(opcionesCheckVO);

		opcionesCheckVO = new OpcionesCheckVO();
		opcionesCheckVO.setNombreOpcion(Remisiones.LABEL_VEHICULO_TIENE_PLACA_NO);
		opcionesCheckVO.setValorOpcion(Remisiones.ID_VEHICULO_TIENE_PLACA_NO);
		listaOpciones.add(opcionesCheckVO);

		return listaOpciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.teclo.siidf.negocio.catalogos.facade.CatalogoFacade#
	 * buscarOpcionesOrigenVehiculo()
	 */
	@Override
	public List<OpcionesCheckVO> buscarOpcionesOrigenVehiculo() {
		List<OpcionesCheckVO> listaOpciones = new ArrayList<OpcionesCheckVO>();
		OpcionesCheckVO opcionesCheckVO = new OpcionesCheckVO();

		opcionesCheckVO.setNombreOpcion(Remisiones.LABEL_VEHICULO_ORIGEN_NACIONAL);
		opcionesCheckVO.setValorOpcion(Remisiones.ID_VEHICULO_ORIGEN_NACIONAL);
		listaOpciones.add(opcionesCheckVO);

		opcionesCheckVO = new OpcionesCheckVO();
		opcionesCheckVO.setNombreOpcion(Remisiones.LABEL_VEHICULO_ORIGEN_EXTRANJERO);
		opcionesCheckVO.setValorOpcion(Remisiones.ID_VEHICULO_ORIGEN_EXTRANJERO);
		listaOpciones.add(opcionesCheckVO);

		return listaOpciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.teclo.siidf.negocio.catalogos.facade.CatalogoFacade#
	 * buscarOpcionesOrigenVehiculo()
	 */
	@Override
	public List<OpcionesCheckVO> buscarOpcionesViolacionLeyTransporte() {
		List<OpcionesCheckVO> listaOpciones = new ArrayList<OpcionesCheckVO>();
		OpcionesCheckVO opcionesCheckVO = new OpcionesCheckVO();

		opcionesCheckVO.setNombreOpcion(Remisiones.LABEL_VIOLACION_LEY_TRANSPORTE_SI);
		opcionesCheckVO.setValorOpcion(Remisiones.ID_VIOLACION_LEY_TRANSPORTE_SI);
		listaOpciones.add(opcionesCheckVO);

		opcionesCheckVO = new OpcionesCheckVO();
		opcionesCheckVO.setNombreOpcion(Remisiones.LABEL_VIOLACION_LEY_TRANSPORTE_NO);
		opcionesCheckVO.setValorOpcion(Remisiones.ID_VIOLACION_LEY_TRANSPORTE_NO);
		listaOpciones.add(opcionesCheckVO);

		return listaOpciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.teclo.siidf.negocio.catalogos.facade.CatalogoFacade#
	 * buscarComponentesInventario()
	 */
	@Override
	@Transactional
	public List<ComponentesInventarioDTO> buscarComponentesInventario() {
		return componentesInventarioDAO.buscarComponentesInventario("A");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.teclo.siidf.negocio.catalogos.facade.CatalogoFacade#
	 * buscarCausaIngresoDiferentesAlId()
	 */
	@Override
	@Transactional
	public List<CausaIngresoDTO> buscarCausaIngresoDiferentesAlId(Long... idCausaIngreso) {
		return causaIngresoDAO.buscarCausaIngresoDiferentesAlId(idCausaIngreso);
	}

	@Override
	public List<OpcionesBusquedaVO> buscarOpcionesConsultaRemision() {
		List<OpcionesBusquedaVO> listaOpcionesBusqueda = new ArrayList<OpcionesBusquedaVO>();

		OpcionesBusquedaVO opcionesBusquedaVO = new OpcionesBusquedaVO();

		// PLACA VEHICULO
		String nombreOpcion = Remisiones.LABEL_PLACA;
		String valorOpcion = Remisiones.VALUE_PLACA;
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		// NUMERO DE INFRACCION
		opcionesBusquedaVO = new OpcionesBusquedaVO();
		nombreOpcion = Remisiones.LABEL_NUMERO_INFRACCION;
		valorOpcion = Remisiones.VALUE_NUMERO_INFRACCION;
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		// NUMERO DE INFRACCION IMPRESA
		opcionesBusquedaVO = new OpcionesBusquedaVO();
		nombreOpcion = Remisiones.LABEL_IMPRESA;
		valorOpcion = Remisiones.VALUE_IMPRESA;
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		// NUMERO DE CONTROL INTERNO
		opcionesBusquedaVO = new OpcionesBusquedaVO();
		nombreOpcion = Remisiones.LABEL_NUMERO_CONTROL_INTERNO;
		valorOpcion = Remisiones.VALUE_NUMERO_CONTROL_INTERNO;
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		// DOCUMENTO
		opcionesBusquedaVO = new OpcionesBusquedaVO();
		nombreOpcion = Remisiones.LABEL_DOCUMENTO;
		valorOpcion = Remisiones.VALUE_DOCUMENTO;
		opcionesBusquedaVO.setNombreOpcion(nombreOpcion);
		opcionesBusquedaVO.setValorOpcion(valorOpcion);
		listaOpcionesBusqueda.add(opcionesBusquedaVO);

		return listaOpcionesBusqueda;
	}

	@Override
	@Transactional
	public List<OperativoDTO> buscarOperativos() {
		return operativoDAO.buscarOperativos();
	}

	@Override
	public List<FilterValuesVO> filtroPIDocumentos() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();

		for (int i = 0; i < 4; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);

			if (i == 0) {
				filter.setDescripcion("Todos");
			} else if (i == 1) {
				filter.setDescripcion("Consecutivo");
			} else if (i == 2) {
				filter.setDescripcion("Infracción");
			} else if (i == 3) {
				filter.setDescripcion("Placa");
			}

			filterValues.add(filter);
		}
		return filterValues;
	}

	@Override
	public List<FilterValuesVO> filtroPIBoletas() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();

		for (int i = 0; i < 3; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);

			if (i == 0) {
				filter.setDescripcion("Todos");
			} else if (i == 1) {
				filter.setDescripcion("Consecutivo");
			} else if (i == 2) {
				filter.setDescripcion("Infracción");
			}

			filterValues.add(filter);
		}
		return filterValues;
	}

	public List<FilterValuesVO> opcionesSituacionesBoletas() {
		List<FilterValuesVO> optionValues = new ArrayList<FilterValuesVO>();

		for (int i = 0; i < 5; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigoString(String.valueOf(i));

			if (i == 0) {
				filter.setDescripcion("Seleccione");
			} else if (i == 1) {
				filter.setDescripcion("Con Acta (Robo)");
			} else if (i == 2) {
				filter.setDescripcion("Extraviada con Arresto");
			} else if (i == 3) {
				filter.setDescripcion("Mal Elaborada con Arresto");
			} else if (i == 4) {
				filter.setDescripcion("Otro");
			}

			optionValues.add(filter);
		}
		return optionValues;
	}

	@Override
	public List<FilterValuesVO> filtroInfraccionTipoArrastre() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO

		filter = new FilterValuesVO(0, "N", "Sin Arrastre");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "I", "Arrastre por Infracción");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "A", "Arrastre por Abandono");
		filterValues.add(filter);

		return filterValues;
	}

	@Override
	public List<FilterValuesVO> filtroInfraccionTipoUnidad() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO

		filter = new FilterValuesVO(0, "G", "Grua|Patrulla|Moto");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "P", "PIE");
		filterValues.add(filter);

		return filterValues;
	}

	///////////////////////////////////////////////////
	// TIPO DE SUMINISTRO / REGIONALES / AUXILIARES
	///////////////////////////////////////////////////
	@Override
	@Transactional
	public List<TipoSuministroDTO> buscarTipoSuministro() {
		return tipoSuministroDAO.findAll();
	}

	@Override
	@Transactional
	public List<RegionalesDTO> buscarRegionales() {
		return regionalesDAO.buscarRegionales();
	}

	@Override
	@Transactional
	public List<AreaOperativaDTO> buscarAreaOperativa(Long reg_id) {
		return areaOperativaDAO.buscarAreaOperativa(reg_id);
	}

	@Override
	@Transactional
	public List<OficialRecibeVO> buscarOficiales(Long reg_id, Long upc_id) {
		return oficialesDAO.buscarOficiales(reg_id, upc_id);
	}

	@Override
	@Transactional
	public List<DelegadoAuxiliarVO> buscarAuxiliar(Long reg_id, Long upc_id) {
		return auxiliaresDAO.buscarAuxiliar(upc_id, reg_id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public List<RadarCatArchivoTipoDTO> buscaCatArchivoTipoActivos() {
		return radarCatArchivoTipoDAO.buscaCatArchivoTipoActivos();
	}

	/**
	 * {@inheritDoc}
	 */
	 public List<ComboValuesVO> obtenerAnioSalarioMinimo() {
	
//	 RutinasTiempoImpl rutinasTiempo = new RutinasTiempoImpl();
//	 Date fecha = new Date();
	
	 //return rutinasTiempo.obtenerAnioactualAniosAnterires(fecha, 2);
	 return radarBitacoraProcesoMyBatisDAO.getAnioSalarioMinimo();
	
	 }

	///////////////////////////////////////////////////
	// CATALOGO DE OPCIONES DE BUSQUEDA CON INFRACCION
	///////////////////////////////////////////////////

	@Override
	public List<OpcionConInfraccionVO> OpcionesConInfaccion() {
		List<OpcionConInfraccionVO> opcionValues = new ArrayList<OpcionConInfraccionVO>();

		for (int i = 0; i < 4; i++) {
			OpcionConInfraccionVO filter = new OpcionConInfraccionVO();
			filter.setId(i);

			if (i == 0) {
				filter.setValor("PLACA");
				filter.setDescripcion("No. de Placa");
			} else if (i == 1) {
				filter.setValor("INFRACCION");
				filter.setDescripcion("No. de Infracción");
			} else if (i == 2) {
				filter.setValor("PREIMPRESA");
				filter.setDescripcion("Infracción impresa");
			} else if (i == 3) {
				filter.setValor("NCI");
				filter.setDescripcion("NCI");
			}

			opcionValues.add(filter);
		}
		return opcionValues;
	}

	///////////////////////////////////////
	// DEPOSITOS///////////////////////////
	///////////////////////////////////////

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public DepositosEmpleadosDTO depositoEmpleadoPorEmpId(Long id) {
		return depositosDAO.depositoEmpleadoPorEmpId(id);
	}

	@Transactional
	@Override
	public List<ConcesionariaDTO> buscarConcesionarias() {
		return concesionariaDAO.buscarConcesionariasOrdenadas();// findAll();
	}

	@Override
	@Transactional
	public void callCrudConcesionaria(CrudConcesionariaVO concesionariaVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		concesionariaVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudConcesionariaVO newCrudConcesionariaVO = concesionariaVO;
		CrudConcesionariaVO oldCrudConcesionariaVO = new CrudConcesionariaVO();
		ConcesionariaDTO oldConcesionariaDTO = new ConcesionariaDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldConcesionariaDTO = concesionariaDAO.buscarconcesionariaPorCod(concesionariaVO.getConcesionariaCodigo().toUpperCase());
		if(oldConcesionariaDTO!=null){
			oldEstatus = oldConcesionariaDTO.getConcesionariaStatus().toString();
			ResponseConverter.copiarPropriedades(oldCrudConcesionariaVO, oldConcesionariaDTO);
		}
		concesionariaMyBatisDAO.callCrudConcesionaria(concesionariaVO);
		
		if(concesionariaVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoGruas.compararConcesionarias(newCrudConcesionariaVO, oldCrudConcesionariaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!concesionariaVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoGruas.compararConcesionarias(newCrudConcesionariaVO, oldCrudConcesionariaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Transactional
	@Override
	public List<GruaStatusDTO> buscarGruaStatus() {
		return gruaStatusDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudGruaStatus(CrudGruaStatusVO gruaStatusVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		gruaStatusVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudGruaStatusVO newCrudGruaStatusVO = gruaStatusVO;
		CrudGruaStatusVO oldCrudGruaStatusVO = new CrudGruaStatusVO();
		GruaStatusDTO oldGruaStatusDTO = new GruaStatusDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCamiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus = "";
		
		oldGruaStatusDTO = gruaStatusDAO.buscarEstatusGruaPorCod(newCrudGruaStatusVO.getGruaStatusCod().toUpperCase());
		if(oldGruaStatusDTO!=null){
			oldEstatus = oldGruaStatusDTO.getStatus();
			ResponseConverter.copiarPropriedades(oldCrudGruaStatusVO, oldGruaStatusDTO);
		}
		
		gruaStatusMyBatisDAO.callCrudGruaStatus(gruaStatusVO);
		
		if(gruaStatusVO.getResultado()==null){
			bitacoraCamiosVO = bitAdminCatalogoGruas.comparaEstatusGruas(newCrudGruaStatusVO, oldCrudGruaStatusVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCamiosVO);
		}else if(!gruaStatusVO.getResultado().equals("-1")){
			bitacoraCamiosVO = bitAdminCatalogoGruas.comparaEstatusGruas(newCrudGruaStatusVO, oldCrudGruaStatusVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCamiosVO);
		}
	}

	@Transactional
	@Override
	public List<GruaDTO> buscarGruas() {
		return gruaDao.findAll();
	}

	@Override
	@Transactional
	public void callCrudGrua(CrudGruaVO crudGruaVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudGruaVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudGruaVO newCrudGruaVO = crudGruaVO;
		CrudGruaVO oldCrudGruaVO = new CrudGruaVO();
		GruaVO oldGruaVO = new GruaVO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldGruaVO = catalogoIngresoDAO.buscaGruaPorCod(newCrudGruaVO.getGruaCod().toUpperCase());
		
		if(oldGruaVO!=null){
			oldEstatus = oldGruaVO.getGruaStatus();
			ResponseConverter.copiarPropriedades(oldCrudGruaVO, oldGruaVO);
		}
		
		gruaMyBatisDAO.callCrudGrua(crudGruaVO);
		
		if(crudGruaVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoGruas.compararGruas(newCrudGruaVO, oldCrudGruaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudGruaVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoGruas.compararGruas(newCrudGruaVO, oldCrudGruaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Transactional
	@Override
	public List<TurnoDTO> buscarTurnos() {
		return turnoDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudTurno(CrudTurnoVO turnoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		turnoVO.setModificadoPor(usuarioFirmadoVO.getId());
		if(turnoVO.getFechaInicio() == null || turnoVO.getHoraInicio() == null){
			turnoVO.setFechaInicio(null);
		}else{
			String fechaInicio = turnoVO.getFechaInicio() + " " + turnoVO.getHoraInicio();
			turnoVO.setFechaInicio(fechaInicio.trim());
		}
		if(turnoVO.getFechaFin()  == null || turnoVO.getHoraFin() == null){
			turnoVO.setFechaFin(null);
		}else{
			String fechaFin = turnoVO.getFechaFin() + " " + turnoVO.getHoraFin();
			turnoVO.setFechaFin(fechaFin.trim());
		}
		
		CrudTurnoVO newCrudTurnoVO = turnoVO;
		CrudTurnoVO oldCrudTurnoVO = new CrudTurnoVO();
		TurnoDTO oldTurnoDTO = new TurnoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus = "";
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatoHora = new SimpleDateFormat("HH:mm");
		
		oldTurnoDTO = turnoDAO.buscarturnoPorCod(turnoVO.getTurnoCod());
		if(oldTurnoDTO!=null){
			oldEstatus = oldTurnoDTO.getTurnoStatus();
			ResponseConverter.copiarPropriedades(oldCrudTurnoVO, oldTurnoDTO);
			oldCrudTurnoVO.setFechaInicio(formatoFecha.format(oldTurnoDTO.getTurnoInicio()) + " " + formatoHora.format(oldTurnoDTO.getTurnoInicio()));
			oldCrudTurnoVO.setFechaFin(formatoFecha.format(oldTurnoDTO.getTurnoFin()) + " " + formatoHora.format(oldTurnoDTO.getTurnoFin()));
			
		}
		
		turnoMyBatisDAO.callCrudTurno(turnoVO);
		
		if(turnoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoGruas.comparaTurnos(newCrudTurnoVO, oldCrudTurnoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!turnoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoGruas.comparaTurnos(newCrudTurnoVO, oldCrudTurnoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Transactional
	@Override
	public List<ZonaServicioDTO> buscarZonasServicio() {
//		return zonaServicioDAO.findAll();
		return zonaServicioDAO.busquedaOrdenada();
	}

	///////////////////////////////////////
	// Remision a despositos ////////////
	///////////////////////////////////////

	@Override
	public List<ListOperativoVO> listaOperativo() {
		return catalogoIngresoDAO.listaOperativo();
	}

	@Override
	public List<ListMediosTraspVO> listaMediosTrasp() {
		return catalogoIngresoDAO.listaMediosTrasp();
	}

	@Override
	public List<ListCausaIngresoVO> listaCausaIngreso() {
		return catalogoIngresoDAO.listaCausaIngreso();
	}

	@Override
	public List<ListCausaIngresoVO> listaCausaIngresoSin() {
		return catalogoIngresoDAO.listaCausaIngresoSin();
	}

	@Override
	public List<ListDepositoVO> listaDeposito(Long emp_id, int dep_id) {
		return catalogoIngresoDAO.listaDeposito(emp_id, dep_id);
	}

	@Override
	public List<ListDepositoVO> listaDepositoOrigen() {
		return catalogoIngresoDAO.listaDepositoOrigen();
	}

	@Override
	public List<ListInventarioVO> listaInventario() {
		return catalogoIngresoDAO.listaInventario();
	}

	@Override
	public List<OpcionConInfraccionVO> OpcionesConsultaRemision() {
		List<OpcionConInfraccionVO> opcionValues = new ArrayList<OpcionConInfraccionVO>();

		for (int i = 0; i < 6; i++) {
			OpcionConInfraccionVO filter = new OpcionConInfraccionVO();
			filter.setId(i);

			if (i == 0) {
				filter.setValor("PLACA");
				filter.setDescripcion("No. de Placa");
			} else if (i == 1) {
				filter.setValor("INFRACCION");
				filter.setDescripcion("No. de Infracción");
			} else if (i == 2) {
				filter.setValor("PREIMPRESA");
				filter.setDescripcion("Infracción impresa");
			} else if (i == 3) {
				filter.setValor("NCI");
				filter.setDescripcion("NCI");
			} else if (i == 4) {
				filter.setValor("CALLE_HOY");
				filter.setDescripcion("Calle (Hoy)");
			} else if (i == 5) {
				filter.setValor("CALLE_AYER");
				filter.setDescripcion("Calle (Ayer)");
			}
//			else if (i == 3) {
//				filter.setValor("DOCUMENTO");
//				filter.setDescripcion("No. Documento");
//			} 

			opcionValues.add(filter);
		}
		return opcionValues;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<FilterValuesVO> filterTiposFecha() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();

		for (int i = 1; i < 3; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);

			if (i == 1) {
				filter.setDescripcion("Fecha Infracción");
			} else if (i == 2) {
				filter.setDescripcion("Fecha Validación");
			}

			filterValues.add(filter);
		}
		return filterValues;
	}

	@Override
	@Transactional
	public void callCrudZonaServicio(CrudZonaServicioVO zonaServicioVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		zonaServicioVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudZonaServicioVO newCrudZonaServicioVO = zonaServicioVO;
		CrudZonaServicioVO oldCrudZonaServicioVO = new CrudZonaServicioVO();
		ZonaServicioDTO oldZonaServicioDTO = new ZonaServicioDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus = "";
		oldZonaServicioDTO = zonaServicioDAO.buscarZonaServicioPorCod(zonaServicioVO.getZonaCod().toUpperCase());
		if(oldZonaServicioDTO!=null){
			oldEstatus = oldZonaServicioDTO.getZonaStatus();
			ResponseConverter.copiarPropriedades(oldCrudZonaServicioVO, oldZonaServicioDTO);
		}
		
		zonaServicioMybatisDAO.callCrudZonaServicio(zonaServicioVO);
		
		if(zonaServicioVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoGruas.comparaCrudZonaServicio(newCrudZonaServicioVO, oldCrudZonaServicioVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!zonaServicioVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoGruas.comparaCrudZonaServicio(newCrudZonaServicioVO, oldCrudZonaServicioVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<TipoEmpleadoDTO> buscarTiposEmpleados() {
		return tipoEmpleadoDAO.buscarTiposEmpleado();
	}

	@Override
	@Transactional
	public void callCrudTipoEmpleado(CrudTipoEmpleadoVO crudTipoEmpleadoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudTipoEmpleadoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudTipoEmpleadoVO newCrudTipoEmpleadoVO = crudTipoEmpleadoVO;
		CrudTipoEmpleadoVO oldCrudTipoEmpleadoVO = new CrudTipoEmpleadoVO();
		TipoEmpleadoDTO oldTipoEmpleadoDTO = new TipoEmpleadoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO=new ArrayList<>();
		String oldEstatus="";
		
		oldTipoEmpleadoDTO = tipoEmpleadoDAO.buscarTipoEmpleadoPorCod(crudTipoEmpleadoVO.getEmpTipCodigo());
		if(oldTipoEmpleadoDTO!=null){
			oldEstatus = oldTipoEmpleadoDTO.getEmpTipEstatus();
			ResponseConverter.copiarPropriedades(oldCrudTipoEmpleadoVO, oldTipoEmpleadoDTO);
		}
		
		tipoEmpleadoMyBatisDAO.callCrudTipoEmpleado(crudTipoEmpleadoVO);
		
		if(crudTipoEmpleadoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoAgentes.compararTipoEmpleado(newCrudTipoEmpleadoVO, oldCrudTipoEmpleadoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		else if(!crudTipoEmpleadoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoAgentes.compararTipoEmpleado(newCrudTipoEmpleadoVO, oldCrudTipoEmpleadoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<TipoAlarmaDTO> buscarTiposAlarma() {
		return tipoAlarmaDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudTipoAlarma(CrudTipoAlarmaVO crudTipoAlarmaVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudTipoAlarmaVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudTipoAlarmaVO newCrudTipoAlarmaVO = crudTipoAlarmaVO;
		CrudTipoAlarmaVO oldcrudTipoAlarmaVO = new CrudTipoAlarmaVO();
		TipoAlarmaDTO oldCrudTipoAlarmaDTO = new TipoAlarmaDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO=new ArrayList<>();
		String estatusOld="";
		
		oldCrudTipoAlarmaDTO = tipoAlarmaDAO.buscarPorCodAlarma(crudTipoAlarmaVO.getAlarmaCod());
		if(oldCrudTipoAlarmaDTO!=null){
			estatusOld = oldCrudTipoAlarmaDTO.getAlarmaStatus();
			ResponseConverter.copiarPropriedades(oldcrudTipoAlarmaVO, oldCrudTipoAlarmaDTO);
		}
		
		tipoAlarmaMyBatisDAO.callCrudTipoAlarma(crudTipoAlarmaVO);
		
		if(crudTipoAlarmaVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoAgentes.compararTipoAlarma(newCrudTipoAlarmaVO, oldcrudTipoAlarmaVO, estatusOld);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudTipoAlarmaVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoAgentes.compararTipoAlarma(newCrudTipoAlarmaVO, oldcrudTipoAlarmaVO, estatusOld);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public void callCrudEstado(CrudEstadoVO crudEstadoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudEstadoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudEstadoVO newCrudEstadoVO = crudEstadoVO;
		CrudEstadoVO oldCrudEstadoVO = new CrudEstadoVO();
		EstadoDTO oldCrudEstadoDTO = new EstadoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<>();
		String oldEstatus = "";
		
		oldCrudEstadoDTO = estadoDAO.buscarPorCodigo(crudEstadoVO.getEdoCod().toUpperCase());
		if(oldCrudEstadoDTO!=null){
			oldEstatus = oldCrudEstadoDTO.getEdoStatus();
			ResponseConverter.copiarPropriedades(oldCrudEstadoVO, oldCrudEstadoDTO);
		}
		
		estadoMyBatisDAO.callCrudEstado(crudEstadoVO);
		
		if(crudEstadoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.compararEstado(newCrudEstadoVO, oldCrudEstadoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudEstadoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.compararEstado(newCrudEstadoVO, oldCrudEstadoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<RegionDTO> buscarRegiones() {
		return regionDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudRegion(CrudRegionVO crudRegionVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudRegionVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudRegionVO newCrudRegionVO = crudRegionVO;
		CrudRegionVO oldCrudRegionVO = new CrudRegionVO();
		RegionDTO oldRegionDTO = new RegionDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<>();
		String oldEstatus = "";
		
		oldRegionDTO = regionDAO.buscarRegPorCod(crudRegionVO.getRegionCodigo().toUpperCase());
		if(oldRegionDTO!=null){
			oldEstatus = oldRegionDTO.getRegionStatus();
			ResponseConverter.copiarPropriedades(oldCrudRegionVO, oldRegionDTO);
		}
		
		regionMyBatisDAO.callCrudRegion(crudRegionVO);
		
		if(crudRegionVO.getResultado()== null){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.comaprarRegione(newCrudRegionVO, oldCrudRegionVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		else if(!crudRegionVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.comaprarRegione(newCrudRegionVO, oldCrudRegionVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<RespuestaBusquedaDelegacionesVO> buscarDelegacionesPorEstado(Long estadoId) {
		return delegacionMyBatisDAO.buscarDelegacionesPorEstado(estadoId.toString());
	}

	@Override
	@Transactional
	public void callCrudDelegacion(CrudDelegacionVO crudDelegacionVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudDelegacionVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudDelegacionVO newCrudDelegacionVO = crudDelegacionVO;
		CrudDelegacionVO oldCrudDelegacionVO = new CrudDelegacionVO();		
		DelegacionDTO oldDelegacionDTO = new DelegacionDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<>();
		String oldEstatus = "";
		
		oldDelegacionDTO = delegacionDAO.buscarDelegacionPorCod(crudDelegacionVO.getDelCod());
		if(oldDelegacionDTO!=null){
			oldEstatus = oldDelegacionDTO.getDelStatus();
			ResponseConverter.copiarPropriedades(oldCrudDelegacionVO, oldDelegacionDTO);
			oldCrudDelegacionVO.setEdoId(oldDelegacionDTO.getEstadoDTO().getEdoId());
			oldCrudDelegacionVO.setDelId(oldDelegacionDTO.getDelId().getDelId());
		}
		
		delegacionMyBatisDAO.callCrudDelegacion(crudDelegacionVO);
		
		if(crudDelegacionVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.compararDelegaciones(newCrudDelegacionVO, oldCrudDelegacionVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else{
			if(!crudDelegacionVO.getResultado().equals("-1")){
				bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.compararDelegaciones(newCrudDelegacionVO, oldCrudDelegacionVO, oldEstatus);
				bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
			}
		}
	}

	@Override
	@Transactional
	public List<UnidadTerritorialDTO> buscarUnidadesTerritoriales() {
		return unidadTerritorialDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudUnidadTerritorial(CrudUnidadeTerritorialVO crudUnidadeTerritorialVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudUnidadeTerritorialVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudUnidadeTerritorialVO newCrudUnidadeTerritorialVO = crudUnidadeTerritorialVO;
		CrudUnidadeTerritorialVO oldCrudUnidadeTerritorialVO = new CrudUnidadeTerritorialVO();
		UnidadTerritorialDTO oldUnidadTerritorialDTO = new UnidadTerritorialDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<>();
		
		oldUnidadTerritorialDTO = unidadTerritorialDAO.buscarUnidadTerritorialPorCod(crudUnidadeTerritorialVO.getUtCod().toUpperCase(), crudUnidadeTerritorialVO.getUtId().getSecId());
		if(oldUnidadTerritorialDTO!=null){
			ResponseConverter.copiarPropriedades(oldCrudUnidadeTerritorialVO, oldUnidadTerritorialDTO);
			UTIdVO uTIdVO = new UTIdVO();
			uTIdVO.setSecId(oldUnidadTerritorialDTO.getUtId().getSecId());
			uTIdVO.setUtId(oldUnidadTerritorialDTO.getUtId().getUtId());
			oldCrudUnidadeTerritorialVO.setUtId(uTIdVO);
		}
		
		unidadeTerritorialMyBatisDAO.callCrudUnidadeTerritorial(crudUnidadeTerritorialVO);
		
		if(crudUnidadeTerritorialVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.compararUnidadTeritorial(newCrudUnidadeTerritorialVO, oldCrudUnidadeTerritorialVO);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudUnidadeTerritorialVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.compararUnidadTeritorial(newCrudUnidadeTerritorialVO, oldCrudUnidadeTerritorialVO);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<SectorDTO> buscarSectores() {
		return sectorDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudSector(CrudSectorVO crudSectorVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudSectorVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudSectorVO newCrudSectorVO = crudSectorVO;
		CrudSectorVO oldCrudSectorVO = new CrudSectorVO();
		SectorDTO oldSectorDTO = new SectorDTO();
		SectorDTO oldSectorDTO2 = new SectorDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<>();
		
		oldSectorDTO = sectorDAO.buscarSectorPorCod(crudSectorVO.getSectorCodigo().toUpperCase());
		oldSectorDTO2 = sectorDAO.buscarSectorPorCodDel(crudSectorVO.getSectorCodigo().toUpperCase(), crudSectorVO.getEstadoId(), crudSectorVO.getDelegacionId());
		if(oldSectorDTO!=null){
			ResponseConverter.copiarPropriedades(oldCrudSectorVO, oldSectorDTO);
			oldCrudSectorVO.setSectorId(oldSectorDTO.getSecId()); 
			oldCrudSectorVO.setSectorCodigo(oldSectorDTO.getSecCod());
			oldCrudSectorVO.setSectorNombre(oldSectorDTO.getSecNombre());
			oldCrudSectorVO.setEstadoId(oldSectorDTO.getDel().getEstadoDTO().getEdoId());
			oldCrudSectorVO.setDelegacionId(oldSectorDTO.getDel().getDelId().getDelId());
		}
				
		sectorMyBatisDAO.callCrudSector(crudSectorVO);
		
		if(crudSectorVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.compararSector(newCrudSectorVO, oldCrudSectorVO, oldSectorDTO, oldSectorDTO2);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudSectorVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.compararSector(newCrudSectorVO, oldCrudSectorVO, oldSectorDTO, oldSectorDTO2);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		
	}

	@Override
	@Transactional
	public List<AgrupamientosDTO> buscarAgrupamientos() {
//		return agrupamientosDAO.findAll();
		return agrupamientosDAO.buscarAgrupamientosOrdenados();
	}

	@Override
	@Transactional
	public void callCrudAgrupamiento(CrudAgrupamientoVO crudAgrupamientoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudAgrupamientoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudAgrupamientoVO newCrudAgrupamientoVO = crudAgrupamientoVO;
		CrudAgrupamientoVO oldCrudAgrupamientoVO = new CrudAgrupamientoVO();
		AgrupamientosDTO oldAgrupamientoDTO = new AgrupamientosDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO=new ArrayList<>();
		String oldEstatus = "";
		
		oldAgrupamientoDTO = agrupamientosDAO.buscarAgrupamientoPorCod(crudAgrupamientoVO.getAgrupacionCodigo());
		if(oldAgrupamientoDTO!=null){
			oldEstatus = oldAgrupamientoDTO.getAgrupacionStatus();
			ResponseConverter.copiarPropriedades(oldCrudAgrupamientoVO, oldAgrupamientoDTO);
		}
		
		agrupamientosMyBatisDAO.callCrudAgrupamiento(crudAgrupamientoVO);
		
		if(crudAgrupamientoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.compararTipoAgrupamiento(newCrudAgrupamientoVO, oldCrudAgrupamientoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudAgrupamientoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoAgrupamientos.compararTipoAgrupamiento(newCrudAgrupamientoVO, oldCrudAgrupamientoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<ZonaDTO> buscarZonas() {
		return zonaDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudZonas(CrudZonaVO crudZonaVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudZonaVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudZonaVO newCrudZonaVO = crudZonaVO;
		CrudZonaVO oldCrudZonaVO = new CrudZonaVO();
		ZonaDTO oldZonaDTO = new ZonaDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus = "";
		
		oldZonaDTO = zonaDAO.busarZonaPorCodigo(crudZonaVO.getZonaCodigo().toUpperCase());
		if(oldZonaDTO!=null){
			ResponseConverter.copiarPropriedades(oldCrudZonaVO, oldZonaDTO);
			oldEstatus = oldZonaDTO.getZonaStatus();
		}
		
		zonaMyBatisDAO.callCrudZona(crudZonaVO);
		
		if(crudZonaVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoDepositos.compararZonas(newCrudZonaVO, oldCrudZonaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudZonaVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoDepositos.compararZonas(newCrudZonaVO, oldCrudZonaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	public List<RegionDepositoDTO> buscarRegionDepositoPorEstado(Long estadoId) {
		return regionDepositoDAO.buscarRegionesPorEstado(estadoId);
	}

	@Override
	@Transactional
	public void callCrudRegionDeposito(CrudRegionDepositoVO crudRegionDepositoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudRegionDepositoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudRegionDepositoVO newCrudRegionDepositoVO = crudRegionDepositoVO;
		CrudRegionDepositoVO oldCrudRegionDepositoVO = new CrudRegionDepositoVO();
		RegionDepositoDTO oldRegionDepositoDTO = new RegionDepositoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldRegionDepositoDTO = regionDepositoDAO.buscarRegionesPorCodigo(crudRegionDepositoVO.getRegionCodigo().toUpperCase());
		if(oldRegionDepositoDTO!=null){
			ResponseConverter.copiarPropriedades(oldCrudRegionDepositoVO, oldRegionDepositoDTO);
			oldEstatus = oldRegionDepositoDTO.getRegionStatus();
		}
		
		regionDepositoMyBatisDAO.callCrudRegionDeposito(crudRegionDepositoVO);
		
		if(crudRegionDepositoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoDepositos.compararRegionDep(newCrudRegionDepositoVO, oldCrudRegionDepositoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudRegionDepositoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoDepositos.compararRegionDep(newCrudRegionDepositoVO, oldCrudRegionDepositoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	
	}

	@Override
	@Transactional
	public List<RespuestaBusquedaDepositoVO> buscarDepositosPorRegion(Long regionId) {
		return depositoMyBatisDAO.buscarDepositosPorRegion(regionId.toString());
	}

	@Override
	@Transactional
	public void callCrudDeposito(CrudDepositoVO crudDepositoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudDepositoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudDepositoVO newCrudDepositoVO = crudDepositoVO;
		CrudDepositoVO oldCrudDepositoVO = new CrudDepositoVO();
		DepositosDTO oldDepostioDTO = new DepositosDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldDepostioDTO = depositoDAO.buscarDepositoPorCodigo(newCrudDepositoVO.getDepositoCodigo().toUpperCase());
		if(oldDepostioDTO!=null){
			ResponseConverter.copiarPropriedades(oldCrudDepositoVO, oldDepostioDTO);
			oldEstatus = oldDepostioDTO.getDepEstatus();
			oldCrudDepositoVO.setDepositoId(oldDepostioDTO.getDepId());
			oldCrudDepositoVO.setDepositoCodigo(oldDepostioDTO.getDepCod());
			oldCrudDepositoVO.setDepositoNombre(oldDepostioDTO.getDepNombre());
			oldCrudDepositoVO.setZonaId(oldDepostioDTO.getZonaDTO().getZonaId());
			oldCrudDepositoVO.setDepositoDireccion(oldDepostioDTO.getDepDireccion());
			oldCrudDepositoVO.setDepositoColonia(oldDepostioDTO.getDepColonia());
			oldCrudDepositoVO.setDepositoTelefono(oldDepostioDTO.getDepTelefono());
			oldCrudDepositoVO.setEstadoId(oldDepostioDTO.getEstado().getEdoId());
			oldCrudDepositoVO.setDelegacionId(oldDepostioDTO.getDelegacion().getDelId().getDelId());
			oldCrudDepositoVO.setRegionId(oldDepostioDTO.getRegion().getRegionId());
			oldCrudDepositoVO.setDepositoSuperficie(oldDepostioDTO.getDepSuperficie());
			oldCrudDepositoVO.setDepositoCapacidad(oldDepostioDTO.getDepCapacidad());
		}
		
		depositoMyBatisDAO.callCrudDeposito(crudDepositoVO);
		
		if(crudDepositoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoDepositos.compararDepositos(newCrudDepositoVO, oldCrudDepositoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudDepositoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoDepositos.compararDepositos(newCrudDepositoVO, oldCrudDepositoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		
	}

	@Override
	@Transactional
	public List<VehiculoMarcaDTO> buscarMarcaVehiculoTodos() {
//		return vehiculoMarcaDAO.findAll();
		return vehiculoMarcaDAO.busquedaVehiculoMarcaOrdenada();
	}

	@Override
	@Transactional
	public void callCrudVehiculoMarca(CrudVehiculoMarcaVO crudVehiculoMarcaVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudVehiculoMarcaVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudVehiculoMarcaVO newCrudVehiculoMarcaVO = crudVehiculoMarcaVO;
		CrudVehiculoMarcaVO oldCrudVehiculoMarcaVO = new CrudVehiculoMarcaVO();
		VehiculoMarcaDTO oldVehiculoMarcaDTO = new VehiculoMarcaDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldVehiculoMarcaDTO = vehiculoMarcaDAO.buscarVehiculoMarcaPorCod(crudVehiculoMarcaVO.getvMarCod().toUpperCase());
		if(oldVehiculoMarcaDTO!=null){
			oldEstatus = oldVehiculoMarcaDTO.getvMarStatus();
			ResponseConverter.copiarPropriedades(oldCrudVehiculoMarcaVO, oldVehiculoMarcaDTO);
		}
		
		vehiculoMarcaMyBatisDAO.callCrudVehiculoMarca(crudVehiculoMarcaVO);
		
		if(crudVehiculoMarcaVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararMarca(newCrudVehiculoMarcaVO, oldCrudVehiculoMarcaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudVehiculoMarcaVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararMarca(newCrudVehiculoMarcaVO, oldCrudVehiculoMarcaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<VehiculoModeloDTO> buscarModeloVehiculo() {
		return vehiculoModeloDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudVehiculoModelo(CrudVehiculoModeloVO crudVehiculoModeloVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudVehiculoModeloVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudVehiculoModeloVO newCrudVehiculoModeloVO = crudVehiculoModeloVO;
		CrudVehiculoModeloVO oldCrudVehiculoModeloVO = new CrudVehiculoModeloVO();
		VehiculoModeloDTO oldVehiculoModeloDTO = new VehiculoModeloDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		
		oldVehiculoModeloDTO = vehiculoModeloDAO.buscarVehiculoModeloPorCod(crudVehiculoModeloVO.getvModCod().toUpperCase());
		if(oldVehiculoModeloDTO!=null){
			VehiculoMarcaVO vehiculoMarcaVO = new VehiculoMarcaVO();
			ResponseConverter.copiarPropriedades(vehiculoMarcaVO, oldVehiculoModeloDTO.getVehiculoMarca());
			ResponseConverter.copiarPropriedades(oldCrudVehiculoModeloVO, oldVehiculoModeloDTO);
			oldCrudVehiculoModeloVO.setVehiculoMarca(vehiculoMarcaVO);
		}
		
		vehiculoModeloMyBatisDAO.callCrudGrua(crudVehiculoModeloVO);
		
		if(crudVehiculoModeloVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararModelo(newCrudVehiculoModeloVO, oldCrudVehiculoModeloVO);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudVehiculoModeloVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararModelo(newCrudVehiculoModeloVO, oldCrudVehiculoModeloVO);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<VehiculoSubTipoDTO> buscarVehiculoSubTipo() {
		return vehiculoSubTipoDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudVehiculoSubTipo(CrudVehiculoSubTipoVO crudVehiculoSubTipoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudVehiculoSubTipoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudVehiculoSubTipoVO newCrudVehiculoSubTipoVO = crudVehiculoSubTipoVO;
		CrudVehiculoSubTipoVO oldCrudVehiculoSubTipoVO = new CrudVehiculoSubTipoVO();
		VehiculoSubTipoDTO oldVehiculoSubTipoDTO= new VehiculoSubTipoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldVehiculoSubTipoDTO = vehiculoSubTipoDAO.buscarVehiculoSubTipoPorCod(crudVehiculoSubTipoVO.getvSubTipoCodigo().toUpperCase());
		if(oldVehiculoSubTipoDTO!=null){
			oldEstatus = oldVehiculoSubTipoDTO.getvSubTipoStatus();
			ResponseConverter.copiarPropriedades(oldCrudVehiculoSubTipoVO, oldVehiculoSubTipoDTO);
		}
		
		vehiculoSubTipoMyBatisDAO.callCrudVehiculoSubTipo(crudVehiculoSubTipoVO);
		
		if(crudVehiculoSubTipoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararSubtipo(newCrudVehiculoSubTipoVO, oldCrudVehiculoSubTipoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudVehiculoSubTipoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararSubtipo(newCrudVehiculoSubTipoVO, oldCrudVehiculoSubTipoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public void callCrudVehiculoTipo(CrudVehiculoTipoVO crudVehiculoTipoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudVehiculoTipoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudVehiculoTipoVO newCrudVehiculoTipoVO = crudVehiculoTipoVO;
		CrudVehiculoTipoVO oldCrudVehiculoTipoVO = new CrudVehiculoTipoVO();
		VehiculoTipoDTO oldVehiculoTipoDTO = new VehiculoTipoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldVehiculoTipoDTO = vehiculoTipoDAO.buscarVehiculoTipoPorCod(crudVehiculoTipoVO.getVipoCod().toUpperCase());
		if(oldVehiculoTipoDTO!=null){
			oldEstatus = oldVehiculoTipoDTO.getvTipoStatus();
			VehiculoSubTipoVO vehiculoSubTipoVO = new VehiculoSubTipoVO();
			ResponseConverter.copiarPropriedades(vehiculoSubTipoVO, oldVehiculoTipoDTO.getvSubtipo());
			ResponseConverter.copiarPropriedades(oldCrudVehiculoTipoVO, oldVehiculoTipoDTO);
			oldCrudVehiculoTipoVO.setvSubtipo(vehiculoSubTipoVO);
		}
		
		vehiculoTipoMyBatisDAO.callCrudVehiculoTipo(crudVehiculoTipoVO);
		
		if(crudVehiculoTipoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararTipo(newCrudVehiculoTipoVO, oldCrudVehiculoTipoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudVehiculoTipoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararTipo(newCrudVehiculoTipoVO, oldCrudVehiculoTipoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<VehiculoColorDTO> buscarColorVehiculoTodos() {
		return vehiculoColorDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudVehiculoColor(CrudVehiculoColorVO crudVehiculoColorVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudVehiculoColorVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudVehiculoColorVO newCrudVehiculoColorVO = crudVehiculoColorVO;
		CrudVehiculoColorVO oldCrudVehiculoColorVO = new CrudVehiculoColorVO();
		VehiculoColorDTO oldVehiculoColorDTO = new VehiculoColorDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldVehiculoColorDTO = vehiculoColorDAO.buscarVehiculoColorPorCod(crudVehiculoColorVO.getvColorCod().toUpperCase());
		if(oldVehiculoColorDTO!=null){
			oldEstatus = oldVehiculoColorDTO.getvColorStatus();
			ResponseConverter.copiarPropriedades(oldCrudVehiculoColorVO, oldVehiculoColorDTO);
		}
		
		vehiculoColorMyBatisDAO.callCrudVehiculoColor(crudVehiculoColorVO);
		
		if(crudVehiculoColorVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararColor(newCrudVehiculoColorVO, oldCrudVehiculoColorVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudVehiculoColorVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararColor(newCrudVehiculoColorVO, oldCrudVehiculoColorVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public void callCrudVehiculoResponsable(CrudVehiculoResponsableVO crudVehiculoResponsableVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudVehiculoResponsableVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudVehiculoResponsableVO newCrudVehiculoResponsableVO = crudVehiculoResponsableVO;
		CrudVehiculoResponsableVO oldCrudVehiculoResponsableVO = new CrudVehiculoResponsableVO();
		ResponsableVehiculoDTO responsableVehiculoDTO = new ResponsableVehiculoDTO(); 
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		responsableVehiculoDTO = responsableVehiculoDao.buscarResponsableVehiculoPorCod(crudVehiculoResponsableVO.getrVehCod().toUpperCase());
		if(responsableVehiculoDTO!=null){
			oldEstatus = responsableVehiculoDTO.getrVehStatus();
			ResponseConverter.copiarPropriedades(oldCrudVehiculoResponsableVO, responsableVehiculoDTO);
		}
		
		vehiculoResponsableMyBatisDAO.callCrudVehiculoResponsable(crudVehiculoResponsableVO);
		
		if(crudVehiculoResponsableVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararResponsable(newCrudVehiculoResponsableVO, oldCrudVehiculoResponsableVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudVehiculoResponsableVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararResponsable(newCrudVehiculoResponsableVO, oldCrudVehiculoResponsableVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<TipoLicenciaDTO> buscarTipoLicenciaTodos() {
		return tipoLicenciaDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudTipoLicencia(CrudTipoLicenciaVO crudTipoLicenciaVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudTipoLicenciaVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudTipoLicenciaVO newCrudTipoLicenciaVO = crudTipoLicenciaVO;
		CrudTipoLicenciaVO oldCrudTipoLicenciaVO = new CrudTipoLicenciaVO();
		TipoLicenciaDTO oldTipoLicenciaDTO = new TipoLicenciaDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldTipoLicenciaDTO = tipoLicenciaDAO.buscarTipoLicenciaPorCod(crudTipoLicenciaVO.getTipoLCod().toUpperCase());
		if(oldTipoLicenciaDTO!=null){
			oldEstatus = oldTipoLicenciaDTO.getTipoLStatus();
			ResponseConverter.copiarPropriedades(oldCrudTipoLicenciaVO, oldTipoLicenciaDTO);
		}
		
		tipoLicenciaMyBatisDAO.callCrudTipoLicencia(crudTipoLicenciaVO);
		
		if(crudTipoLicenciaVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararTipoLicencia(newCrudTipoLicenciaVO, oldCrudTipoLicenciaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudTipoLicenciaVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoVehiculos.compararTipoLicencia(newCrudTipoLicenciaVO, oldCrudTipoLicenciaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<EventoDTO> buscarEventos() {
		return eventoDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudEvento(CrudEventoVO crudEventoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudEventoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudEventoVO newCrudEventoVO = crudEventoVO;
		CrudEventoVO oldCrudEventoVO = new CrudEventoVO();
		EventoDTO oldEventoDTO = new EventoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldEventoDTO = eventoDAO.buscarEventoPorCod(crudEventoVO.getEventoCodigo().toUpperCase());
		if(oldEventoDTO!=null){
			oldEstatus = oldEventoDTO.getEventoStatus();
			ResponseConverter.copiarPropriedades(oldCrudEventoVO, oldEventoDTO);
		}
		
		eventoMyBatisDAO.callCrudEvento(crudEventoVO);
		
		if(crudEventoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoInfracciones.comparaeEventos(newCrudEventoVO, oldCrudEventoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudEventoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoInfracciones.comparaeEventos(newCrudEventoVO, oldCrudEventoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<EstatusInfraccionDTO> buscarEstatusInfraccion() {
		return estatusInfraccionDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudEstatusInfraccion(CrudEstatusInfraccionVO crudEstatusInfraccionVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudEstatusInfraccionVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudEstatusInfraccionVO newCrudEstatusInfraccionVO = crudEstatusInfraccionVO;
		CrudEstatusInfraccionVO oldCrudEstatusInfraccionVO = new CrudEstatusInfraccionVO();
		EstatusInfraccionDTO oldEstatusInfraccionDTO = new EstatusInfraccionDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldEstatusInfraccionDTO = estatusInfraccionDAO.buscarEstatusInfPorCod(crudEstatusInfraccionVO.getEstatusCodigo().toUpperCase());
		if(oldEstatusInfraccionDTO!=null){
			oldEstatus = oldEstatusInfraccionDTO.getStatus();
			ResponseConverter.copiarPropriedades(oldCrudEstatusInfraccionVO, oldEstatusInfraccionDTO);
		}
		
		estatusInfraccionMyBatisDAO.callCrudEstatusInfraccion(crudEstatusInfraccionVO);
		
		if(crudEstatusInfraccionVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoInfracciones.compararEstatus(newCrudEstatusInfraccionVO, oldCrudEstatusInfraccionVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudEstatusInfraccionVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoInfracciones.compararEstatus(newCrudEstatusInfraccionVO, oldCrudEstatusInfraccionVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<DenominacionDTO> buscarDenominaciones() {
		return denominacionDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudDenominacion(CrudDenominacionVO crudDenominacionVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudDenominacionVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudDenominacionVO newCrudDenominacionVO = crudDenominacionVO;
		CrudDenominacionVO oldCrudDenominacionVO = new CrudDenominacionVO();
		DenominacionDTO oldDenominacionDTO = new DenominacionDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldDenominacionDTO = denominacionDAO.buscarDenominacionPorCod(crudDenominacionVO.getDenominacionCodigo().toUpperCase());
		if(oldDenominacionDTO!=null){
			oldEstatus = oldDenominacionDTO.getDenominacionStatus();
			ResponseConverter.copiarPropriedades(oldCrudDenominacionVO, oldDenominacionDTO);
		}
		
		denominacionMyBatisDAO.callCrudDenominacion(crudDenominacionVO);
		
		if(crudDenominacionVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoPagos.compararDenominacion(newCrudDenominacionVO, oldCrudDenominacionVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudDenominacionVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoPagos.compararDenominacion(newCrudDenominacionVO, oldCrudDenominacionVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		
		
		
	}

	@Override
	@Transactional
	public List<ConceptoPagoDTO> buscarConceptosPago() {
		return conceptoPagoDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudConceptoPago(CrudConceptoPagoVO crudConceptoPagoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudConceptoPagoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudConceptoPagoVO newCrudConceptoPagoVO = crudConceptoPagoVO;
		CrudConceptoPagoVO oldCrudConceptoPagoVO = new CrudConceptoPagoVO();
		ConceptoPagoDTO oldConceptoPagoDTO = new ConceptoPagoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldConceptoPagoDTO = conceptoPagoDAO.buscarConceptoPagoPorCod(crudConceptoPagoVO.getConceptoCodigo().toUpperCase());
		if(oldConceptoPagoDTO!=null){
			oldEstatus = oldConceptoPagoDTO.getConceptoStatus();
			ResponseConverter.copiarPropriedades(oldCrudConceptoPagoVO, oldConceptoPagoDTO);
		}
		
		conceptoMyBatisDAO.callCrudConceptoPago(crudConceptoPagoVO);
		
		if(crudConceptoPagoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoPagos.compararConceptodePago(newCrudConceptoPagoVO, oldCrudConceptoPagoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudConceptoPagoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoPagos.compararConceptodePago(newCrudConceptoPagoVO, oldCrudConceptoPagoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		
	}

	@Override
	@Transactional
	public List<EntidadPagoDTO> buscarEntidadesPago() {
		return entidadPagoDAO.busquedaOrdenanda();
	}

	@Override
	@Transactional
	public void callCrudEntidadPago(CrudEntidadPagosVO crudEntidadPagosVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudEntidadPagosVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudEntidadPagosVO newCrudEntidadPagosVO = crudEntidadPagosVO;
		CrudEntidadPagosVO oldCrudEntidadPagosVO = new CrudEntidadPagosVO();
		EntidadPagoDTO oldEntidadPagoDTO = new EntidadPagoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldEntidadPagoDTO = entidadPagoDAO.buscarEntidadPagoPorCod(crudEntidadPagosVO.getEntidadCodigo().toUpperCase());
		if(oldEntidadPagoDTO!=null){
			oldEstatus = oldEntidadPagoDTO.getEntidadStatus();
			ResponseConverter.copiarPropriedades(oldCrudEntidadPagosVO, oldEntidadPagoDTO);
		}
		
		entidadPagosMyBatisDAO.callCrudEntidadPagos(crudEntidadPagosVO);
		
		if(crudEntidadPagosVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoPagos.compararEntidad(newCrudEntidadPagosVO, oldCrudEntidadPagosVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudEntidadPagosVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoPagos.compararEntidad(newCrudEntidadPagosVO, oldCrudEntidadPagosVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		
	}

	@Override
	@Transactional
	public List<BancoDTO> buscarBancos() {
//		return bancoDAO.findAll();
		return bancoDAO.busquedaOrdenanda();
	}

	@Override
	@Transactional
	public void callCrudBanco(CrudBancoVO crudBancoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudBancoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudBancoVO newCrudBancoVO = crudBancoVO;
		CrudBancoVO oldCrudBancoVO = new CrudBancoVO();
		BancoDTO oldBancoDTO = new BancoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldBancoDTO = bancoDAO.buscarBancoPorCod(crudBancoVO.getBancoCodigo().toUpperCase());
		if(oldBancoDTO!=null){
			oldEstatus = oldBancoDTO.getBancoStatus();
			ResponseConverter.copiarPropriedades(oldCrudBancoVO, oldBancoDTO);
		}
		bancoMyBatisDAO.callCrudBanco(crudBancoVO);
		
		if(crudBancoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoPagos.compararBanco(newCrudBancoVO, oldCrudBancoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudBancoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoPagos.compararBanco(newCrudBancoVO, oldCrudBancoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public void callCrudTipoIngreso(CrudTipoIngresoVO crudTipoIngresoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudTipoIngresoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudTipoIngresoVO newCrudTipoIngresoVO = crudTipoIngresoVO;
		CrudTipoIngresoVO oldCrudTipoIngresoVO = new CrudTipoIngresoVO();
		TipoIngresoDTO oldTipoInfresoDTO = new TipoIngresoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldTipoInfresoDTO = tIngrDao.buscarTipoPorCod(crudTipoIngresoVO.gettIngrCod().toUpperCase());
		if(oldTipoInfresoDTO!=null){
			oldEstatus =  oldTipoInfresoDTO.gettIngrStatus();
			ResponseConverter.copiarPropriedades(oldCrudTipoIngresoVO, oldTipoInfresoDTO);
		}
				
		tipoIngresoMyBatisDAO.callCrudTipoIngreso(crudTipoIngresoVO);
		
		if(crudTipoIngresoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoIngresos.compararTipo(newCrudTipoIngresoVO, oldCrudTipoIngresoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudTipoIngresoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoIngresos.compararTipo(newCrudTipoIngresoVO, oldCrudTipoIngresoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		
	}

	@Override
	@Transactional
	public void callCrudCausaIngreso(CrudCausaIngresoVO crudCausaIngresoVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudCausaIngresoVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudCausaIngresoVO newCrudCausaIngresoVO = crudCausaIngresoVO;
		CrudCausaIngresoVO oldCrudCausaIngresoVO = new CrudCausaIngresoVO();
		CausaIngresoDTO oldCausaIngresoDTO = new CausaIngresoDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldCausaIngresoDTO = causaIngresoDAO.buscarCausaIngreso(crudCausaIngresoVO.getCodigoCausaIngreso().toUpperCase());
		if(oldCausaIngresoDTO!=null){
			oldEstatus = oldCausaIngresoDTO.getStatusCausaIngreso();
			ResponseConverter.copiarPropriedades(oldCrudCausaIngresoVO, oldCausaIngresoDTO);
		}
		
		causaIngresoMyBatisDAO.callCrudCausaIngreso(crudCausaIngresoVO);
		
		if(crudCausaIngresoVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoIngresos.compararCausa(newCrudCausaIngresoVO, oldCrudCausaIngresoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudCausaIngresoVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoIngresos.compararCausa(newCrudCausaIngresoVO, oldCrudCausaIngresoVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		
	}

	@Override
	@Transactional
	public List<AreaDTO> buscarAreas() {
		return areaDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudArea(CrudAreaVO crudAreaVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudAreaVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudAreaVO newCrudAreaVO = crudAreaVO;
		CrudAreaVO oldCrudAreaVO = new CrudAreaVO();
		AreaDTO oldAreaDTO = new AreaDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldAreaDTO = areaDAO.buscarAreaPorCod(crudAreaVO.getAreaCodigo().toUpperCase());
		if(oldAreaDTO!=null){
			oldEstatus = oldAreaDTO.getAreaStatus();
			ResponseConverter.copiarPropriedades(oldCrudAreaVO, oldAreaDTO);
		}
		
		areaMyBatisDAO.callCrudArea(crudAreaVO);
		
		if(crudAreaVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoIngresos.compararAreas(newCrudAreaVO, oldCrudAreaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudAreaVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoIngresos.compararAreas(newCrudAreaVO, oldCrudAreaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		
	}

	@Override
	@Transactional
	public List<ComponentesInventarioDTO> buscarComponentesInventarioTodos() {
		return componentesInventarioDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudComponente(CrudComponenteVO crudComponenteVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudComponenteVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudComponenteVO newCrudComponenteVO = crudComponenteVO;
		CrudComponenteVO oldCrudComponenteVO = new CrudComponenteVO();
		ComponentesInventarioDTO oldComponentesInventarioDTO = new ComponentesInventarioDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus="";
		
		oldComponentesInventarioDTO = componentesInventarioDAO.buscarComponenteInventarioPorCOd(crudComponenteVO.getCodigoComponente().toUpperCase()); 
		if(oldComponentesInventarioDTO!=null){
			oldEstatus = oldComponentesInventarioDTO.getStatusComponente();
			ResponseConverter.copiarPropriedades(oldCrudComponenteVO, oldComponentesInventarioDTO);
			oldCrudComponenteVO.setOrdenComponente(Long.valueOf(oldComponentesInventarioDTO.getOrdenComponente()));
		}
		
		componenteMyBatisDAO.callCrudComponente(crudComponenteVO);
		
		if(crudComponenteVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoIngresos.compararComponente(newCrudComponenteVO, oldCrudComponenteVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudComponenteVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoIngresos.compararComponente(newCrudComponenteVO, oldCrudComponenteVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		
	}

	@Override
	@Transactional
	public List<ArticuloDTO> buscarArticulos() {
//		return articuloDAO.findAll();
		return articuloDAO.buscarArticuloActivos();
	}

	@Override
	@Transactional
	public void callCrudArticulo(CrudArticuloVO crudArticuloVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudArticuloVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudArticuloVO newCrudArticuloVO = crudArticuloVO;
		CrudArticuloVO oldCrudArticuloVO = new CrudArticuloVO();
		ArticuloDTO oldArticuloDTO = new ArticuloDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		
		if(newCrudArticuloVO.getArtId()!=null){
			oldArticuloDTO = articuloDAO.buscarArticulo(newCrudArticuloVO.getArtId());
			if(oldArticuloDTO!=null){
				ResponseConverter.copiarPropriedades(oldCrudArticuloVO, oldArticuloDTO);
				oldCrudArticuloVO.setArtDias(Long.valueOf(oldArticuloDTO.getArtDias()));
				oldCrudArticuloVO.setArtPorcenDesc(oldArticuloDTO.getArtPorcenDesc().longValue());
				oldCrudArticuloVO.setArtEstatus(oldArticuloDTO.getArtStatus());
			}
		}
				
		articuloMyBatisDAO.callCrudArticulo(crudArticuloVO);
		
		if(crudArticuloVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoArticulos.compararArticulo(newCrudArticuloVO, oldCrudArticuloVO);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudArticuloVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoArticulos.compararArticulo(newCrudArticuloVO, oldCrudArticuloVO);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
		
	}

	@Override
	@Transactional
	public List<ProgramaDTO> buscarProgramas() {
		return programaDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudPrograma(CrudProgramaVO crudProgramaVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudProgramaVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudProgramaVO newCrudProgramaVO = crudProgramaVO;
		CrudProgramaVO oldCrudProgramaVO = new CrudProgramaVO();
		ProgramaDTO oldProgramaDTO = new ProgramaDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus = "";
		
		oldProgramaDTO = programaDAO.buscarProgramaPorCod(newCrudProgramaVO.getProgramaCodigo().toUpperCase());
		if(oldProgramaDTO!=null){
			ResponseConverter.copiarPropriedades(oldCrudProgramaVO, oldProgramaDTO);
			oldEstatus = oldProgramaDTO.getProgramaStatus();
		}
		
		programaMyBatisDAO.callCrudPrograma(crudProgramaVO);
		
		if(crudProgramaVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoArticulos.compararPrograma(newCrudProgramaVO, oldCrudProgramaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudProgramaVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoArticulos.compararPrograma(newCrudProgramaVO, oldCrudProgramaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<CategoriaDTO> buscarCategorias() {
		return categoriaDAO.findAll();
	}

	@Override
	@Transactional
	public void callCrudCategoria(CrudCategoriaVO crudCategoriaVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudCategoriaVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudCategoriaVO newCrudCategoriaVO = crudCategoriaVO;
		CrudCategoriaVO oldCrudCategoriaVO = new CrudCategoriaVO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		CategoriaDTO oldCategoriaDTO = new CategoriaDTO(); 
		String oldEstatus ="";
		
		oldCategoriaDTO = categoriaDAO.buscarCategoriaPorCod(newCrudCategoriaVO.getCategoriaCodigo().toUpperCase());
		if(oldCategoriaDTO!=null){
			oldEstatus = oldCategoriaDTO.getCategoriaStatus();
			ResponseConverter.copiarPropriedades(oldCrudCategoriaVO, oldCategoriaDTO);
		}
		
		categoriaMyBatisDAO.callCrudCategoria(crudCategoriaVO);
		
		if(!crudCategoriaVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoArticulos.compararCategoria(newCrudCategoriaVO, oldCrudCategoriaVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	@Override
	@Transactional
	public List<CausalesDTO> buscarCausales() {
		return causalDAO.buscarCausalesActivos();
	}

	@Override
	@Transactional
	public void callCrudCausales(CrudCausalesVO crudCausalesVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		crudCausalesVO.setModificadoPor(usuarioFirmadoVO.getId());
		CrudCausalesVO newCrudCausalesVO = crudCausalesVO;
		CrudCausalesVO oldCrudCausalesVO = new CrudCausalesVO();
		CausalesDTO oldCausalDTO = new CausalesDTO();
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		String oldEstatus = "";
		
		
		oldCausalDTO = causalDAO.buscarCausalPorCod(newCrudCausalesVO.getCausalCodigo().toUpperCase());
		if(oldCausalDTO!=null){
			oldEstatus = oldCausalDTO.getCausalStatus();
			ResponseConverter.copiarPropriedades(oldCrudCausalesVO, oldCausalDTO);
		}
		
		causalesMyBatisDAO.callCrudCausales(crudCausalesVO);
		
		if(crudCausalesVO.getResultado()==null){
			bitacoraCambiosVO = bitAdminCatalogoArticulos.compararCausal(newCrudCausalesVO, oldCrudCausalesVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}else if(!crudCausalesVO.getResultado().equals("-1")){
			bitacoraCambiosVO = bitAdminCatalogoArticulos.compararCausal(newCrudCausalesVO, oldCrudCausalesVO, oldEstatus);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVO);
		}
	}

	// ******************************
	// *********Caja Sin Corte*******

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> tipoCaja() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO

		filter = new FilterValuesVO(0, "20", "Handheld");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "12", "Depósito");
		filterValues.add(filter);

		return filterValues;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> buscaTipoResultadoSinCajaHistorico() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO

		filter = new FilterValuesVO(0, "TOTAL", "Total infracción");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "DETALLE", "Detalle infracción");
		filterValues.add(filter);

		return filterValues;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> buscaTipoBusquedaSinCajaActual() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO

		filter = new FilterValuesVO(0, "TODAS", "Todas las cajas");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "CAJA_COD", "Número de caja");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "EMP_PLACA", "Placa del usuario");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "PERFIL_ID", "Tipo de caja");
		filterValues.add(filter);

		return filterValues;
	}

	// ******************************
	// *********Corte de Caja *******

	@Override
	public List<FilterValuesVO> filtroBusquedaDeCaja() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO

		filter = new FilterValuesVO(0, "buscaPorPlaca", "Placa Usuario");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "buscaPorCaja", "No. de Caja");
		filterValues.add(filter);

		return filterValues;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoRadarDTO> buscarRadares(Boolean todos) {
		List<TipoRadarDTO> list = tipoRadarDAO.findAll();
		if (todos) {
			TipoRadarDTO optiontodos = new TipoRadarDTO();
			optiontodos.setTipoRadarId(new Long(0));
			optiontodos.setNombre("Todos");
			list.add(0, optiontodos);
		}
		return list;
	}

	@Override
	public List<FilterValuesVO> filtroEstatusDetecciones() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();

		for (int i = 0; i < 3; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);

			if (i == 0) {
				filter.setDescripcion("Rechazadas");
			} else if (i == 1) {
				filter.setDescripcion("Autorizadas");
			} else if (i == 2) {
				filter.setCodigo(filter.getCodigo() + 1);
				filter.setDescripcion("Todos");
			}

			filterValues.add(0, filter);
		}
		return filterValues;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> filtroProcesadosDetecciones() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();

		for (int i = 0; i < 3; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);

			if (i == 0) {
				filter.setDescripcion("No");
			} else if (i == 1) {
				filter.setDescripcion("Si");
			} else if (i == 2) {
				filter.setCodigo(filter.getCodigo() + 1);
				filter.setDescripcion("Todos");
			}

			filterValues.add(0, filter);
		}
		return filterValues;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> filtroTiposFechaForConsultaLotesFM() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();

		for (int i = 0; i < 4; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);

			if (i == 0) {
				filter.setDescripcion("Todos");
			} else if (i == 1) {
				filter.setDescripcion("Fecha Emisión");
			} else if (i == 2) {
				filter.setDescripcion("Fecha Complementación");
			} else if (i == 3) {
				filter.setDescripcion("Fecha Liberación");
			}

			filterValues.add(filter);
		}
		return filterValues;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> filtroEstatusProceso() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();

		for (int i = 0; i < 4; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			if (i == 0) {
				filter.setCodigo(EstatusProcesoLote.TODOS.getEstatusProceso());
				filter.setDescripcion("Todos");
			} else if (i == 1) {
				filter.setCodigo(EstatusProcesoLote.CANCELADO.getEstatusProceso());
				filter.setDescripcion("Cancelado");
			} else if (i == 2) {
				filter.setCodigo(EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso());
				filter.setDescripcion("Complementado");
			} else if (i == 3) {
				filter.setCodigo(EstatusProcesoLote.LIBERADO.getEstatusProceso());
				filter.setDescripcion("Liberado");
			}

			filterValues.add(filter);
		}
		return filterValues;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> buscaTipoReportesWeb(Long perfilId) {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		List<ReportesWebVO> list = reportesWebMyBatisDAO.busquedaPorPerfil(perfilId);

		for (ReportesWebVO object : list) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigoString(object.getReporteId().toString());
			filter.setDescripcion(object.getNombreReporte());
			filterValues.add(filter);
		}
		return filterValues;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> filtroLiberacionVehiculo() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		for (int i = 0; i < 5; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			if (i == 0) {
				filter.setCodigoString("PLACA");
				filter.setDescripcion("No. De Placa");
			} else if (i == 1) {
				filter.setCodigoString("INFRACCION");
				filter.setDescripcion("No. De Infracción");
			} else if (i == 2) {
				filter.setCodigoString("PREIMPRESA");
				filter.setDescripcion("Infracción Impresa");
			} else if (i == 3) {
				filter.setCodigoString("DOCUMENTO");
				filter.setDescripcion("No. Documento");
			} else if (i == 4) {
				filter.setCodigoString("NCI");
				filter.setDescripcion("NCI");
			}

			filterValues.add(filter);
		}

		return filterValues;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ComboValuesVO> filtroComponentesSoporte(Integer status) {
		List<ComboValuesVO> combo = new ArrayList<ComboValuesVO>();
		ComboValuesVO seleccione = new ComboValuesVO();
		seleccione.setValor("0");
		seleccione.setDescripcion("SELECCIONE");
		combo.add(seleccione);

		for (ComponentesSoporteDTO value : componentesSoporteDAO.buscarComponentesPorStatus(status)) {
			ComboValuesVO item = new ComboValuesVO();
			item.setValor(value.getComponenteId().toString());
			item.setDescripcion(value.getNombre());
			combo.add(item);
		}

		return combo;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ConceptosSoporteDTO> filtroConceptosSoporte(Long componenteId, Integer valido) {
		List<ConceptosSoporteDTO> lista = new ArrayList<ConceptosSoporteDTO>();
		ConceptosSoporteDTO seleccione = new ConceptosSoporteDTO();
		seleccione.setConceptoId(new Long(0));
		seleccione.setConceptoNombre("SELECCIONE");
		seleccione.setDescripcion("");

		lista = conceptosSoporteDAO.buscarConceptosSoporteByComponente(componenteId, valido);
		lista.add(0, seleccione);
		return lista;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> filtroEmbargos() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		for (int i = 1; i < 4; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);
			if (i == 1) {
				filter.setDescripcion("Placa/Permiso");
			} else if (i == 2) {
				filter.setDescripcion("No. de Infracción");
			} else if (i == 3) {
				filter.setDescripcion("Documento");
			}

			filterValues.add(filter);
		}

		return filterValues;
	}

	@Override
	@Transactional
	public List<ArticulosInfraccionesFinanzasDTO> buscarArticulosInfraccionesFinanzasPorArticulo(Long articuloId) {
		return articulosInfraccionesFinanzasDAO.buscarArticulosInfraccionesFinanzasPorArticulo(articuloId);
	}

	@Override
	@Transactional
	public List<CausalesDTO> buscarCausalesActivos() {
		return causalDAO.buscarCausalesActivos();
	}

	@Override
	@Transactional
	public ArticulosInfraccionesFinanzasDTO buscarArticulosInfraccionesFinanzasPorId(Long id) {
		return articulosInfraccionesFinanzasDAO.buscarArticulosInfraccionesFinanzasPorId(id);
	}

	@Override
	@Transactional
	public ArticuloDTO buscarArticulosPorId(Long articuloId) {
		return articuloDAO.buscarArticulo(articuloId);
	}

	@Override
	public void callCrudArticulosHistoricos(CrudArticulosHistoricoVO articuloVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		articuloVO.setModificadoPor(usuarioFirmadoVO.getId());
		articuloMyBatisDAO.callCrudArticulosHistoricos(articuloVO);
	}

	@Override
	@Transactional
	public GruaStatusDTO buscarGruaStatusPorId(Long gruaStatusId) {
		return gruaStatusDAO.findOne(gruaStatusId);
	}

	@Override
	@Transactional
	public List<RegionVO> buscarRegionesPorEstado(Long estadoId) {
		return regionMyBatisDAO.buscarRegionesPorEstado(estadoId.toString());
	}

	@Override
	@Transactional
	public List<CatalogoWebDTO> buscarCatalogosWeb() {
		return catalogoWebDAO.buscarCatalogosActivos();// findAll();
	}

	@Override
	@Transactional
	public List<CatalogoWebOpcionDTO> buscarCatalogosWebOpciones(Long catalogoId) {
		return catalogoWebOpcionDAO.buscarOpcionesPorCatalogoId(catalogoId);
	}

	@Override
	@Transactional
	public List<GruaVO> buscarGruasPorConcesionaria(Long concesionariaId) {
		return gruaMyBatisDAO.buscarGruasPorConcesionaria(concesionariaId.toString());
	}

	@Override
	public List<RespuestaBusquedaSectoresVO> buscarSectoresPorDelegacion(Long delegacionId, Long estadoId) {
		return sectorMyBatisDAO.buscarSectoresPorDelegacion(delegacionId.toString(), estadoId.toString());
	}

	@Override
	@Transactional
	public List<VehiculoModeloDTO> buscarModeloVehiculoActivoPorMarcaTodos(Long marcaId) {
		return vehiculoModeloDAO.buscarVehiculoModeloTodos(marcaId);
	}

	@Override
	public List<VehiculoTipoDTO> buscarVehiculoTipoPorSubTipo(Long subTipoId) {
		return vehiculoTipoDAO.buscarVehiculoTipoPorSubTipo(subTipoId);
	}

	@Override
	@Transactional
	public List<ProgramaDTO> buscarProgramasActivos() {
		return programaDAO.buscarProgramasActivos();
	}

	@Override
	@Transactional
	public List<CategoriaDTO> buscarCategoriasActivas() {
		return categoriaDAO.buscarCategoriasActivas();
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelConcesionaria() throws IOException {
		List<ConcesionariaDTO> concesionarias = concesionariaDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO CONCESIONARIA");
		titulos.add("PREFIJO CONCESIONARIA");
		titulos.add("NOMBRE CONCESIONARIA");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (ConcesionariaDTO concesionaria : concesionarias) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(concesionaria.getConcesionariaCodigo());
			listaContenido1.add(concesionaria.getConcesionariaPrefijo());
			listaContenido1.add(concesionaria.getConcesionariaNombre());
			listaContenido1.add(concesionaria.getConcesionariaStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelGruaStatus() throws IOException {
		List<GruaStatusDTO> gruas = gruaStatusDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO GRUA");
		titulos.add("NOMBRE GRUA");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (GruaStatusDTO grua : gruas) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(grua.getGruaStatusCod());
			listaContenido1.add(grua.getGruaStatusNombre());
			listaContenido1.add(grua.getStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelGrua(Long concesionariaId) throws IOException {
		List<GruaVO> gruas = gruaMyBatisDAO.buscarGruasPorConcesionaria(concesionariaId.toString());
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO CONCECIONARIA");
		titulos.add("GRÚA ESTATUS");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (GruaVO grua : gruas) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(grua.getGruaCod());
			listaContenido1.add(grua.getgStatus());
			listaContenido1.add(grua.getStatusDesc());
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelTurno() throws IOException {
		List<TurnoDTO> turnos = turnoDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO TURNO");
		titulos.add("NOMBRE TURNO");
		titulos.add("INICIO");
		titulos.add("FIN");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		for (TurnoDTO turno : turnos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(turno.getTurnoCod());
			listaContenido1.add(turno.getTurnoNombre());
			listaContenido1.add(turno.getTurnoInicio() != null ? dateFormat.format(turno.getTurnoInicio()) : "");
			listaContenido1.add(turno.getTurnoFin() != null ? dateFormat.format(turno.getTurnoFin()) : "");
			listaContenido1.add(turno.getTurnoStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelZonasServicio() throws IOException {
		List<ZonaServicioDTO> zonas = zonaServicioDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO ZONA");
		titulos.add("NOMBRE ZONA");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (ZonaServicioDTO zona : zonas) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(zona.getZonaCod());
			listaContenido1.add(zona.getZonaNombre());
			listaContenido1.add(zona.getZonaStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelTipoEmpleado() throws IOException {
		List<TipoEmpleadoDTO> tipos = tipoEmpleadoDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO TIPO EMPLEADO");
		titulos.add("NOMBRE TIPO EMPLEADO");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (TipoEmpleadoDTO tipo : tipos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(tipo.getEmpTipCodigo());
			listaContenido1.add(tipo.getEmpTipNombre());
			listaContenido1.add(tipo.getEmpTipEstatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelTipoAlarma() throws IOException {
		List<TipoAlarmaDTO> tipos = tipoAlarmaDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO TIPO ALARMA");
		titulos.add("NOMBRE TIPO ALARMA");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (TipoAlarmaDTO tipo : tipos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(tipo.getAlarmaCod());
			listaContenido1.add(tipo.getAlarmaNombre());
			listaContenido1.add(tipo.getAlarmaStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelEstado() throws IOException {
		List<EstadoDTO> estados = estadoDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO ESTADO");
		titulos.add("NOMBRE ESTADO");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (EstadoDTO estado : estados) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(estado.getEdoCod());
			listaContenido1.add(estado.getEdoNombre());
			listaContenido1.add(estado.getEdoStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelRegion(Long estadoId) throws IOException {
		List<RegionVO> regiones = regionMyBatisDAO.buscarRegionesPorEstado(estadoId.toString());
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO REGION");
		titulos.add("NOMBRE REGION");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (RegionVO region : regiones) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(region.getRegionCodigo());
			listaContenido1.add(region.getRegionNombre());
			listaContenido1.add(region.getStatusDesc());
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelDelegacion(Long estadoId) throws IOException {
		List<RespuestaBusquedaDelegacionesVO> delegaciones = delegacionMyBatisDAO
				.buscarDelegacionesPorEstado(estadoId.toString());
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO DELEGACIÓN");
		titulos.add("REGION NOMBRE");
		titulos.add("NOMBRE DELEGACIÓN");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (RespuestaBusquedaDelegacionesVO delegacion : delegaciones) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(delegacion.getDelCod());
			listaContenido1.add(delegacion.getRegNombre());
			listaContenido1.add(delegacion.getDelNombre());
			listaContenido1.add(delegacion.getStatusDesc());
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelUT(Long sectorId) throws IOException {
		List<UnidadTerritorialDTO> unidades = unidadTerritorialDAO.buscarUnidadesTerritorialesPorSector(sectorId);
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO UT");
		titulos.add("NOMBRE UT");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (UnidadTerritorialDTO unidade : unidades) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(unidade.getUtCod());
			listaContenido1.add(unidade.getUtNombre());
			listaContenido1.add(unidade.getUtStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelSector(Long delegacionId, Long edoId) throws IOException {
		List<RespuestaBusquedaSectoresVO> sectores = sectorMyBatisDAO
				.buscarSectoresPorDelegacion(delegacionId.toString(), edoId.toString());
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO SECTOR");
		titulos.add("NOMBRE SECTOR");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (RespuestaBusquedaSectoresVO sector : sectores) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(sector.getSectorCodigo());
			listaContenido1.add(sector.getSectorNombre());
			listaContenido1.add(sector.getStatusDesc());
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelAgrupamientos() throws IOException {
		List<AgrupamientosDTO> agrupamientos = agrupamientosDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO AGRUPAMIENTO");
		titulos.add("NOMBRE AGRUPAMIENTO");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (AgrupamientosDTO agrupamiento : agrupamientos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(agrupamiento.getAgrupacionCodigo());
			listaContenido1.add(agrupamiento.getAgrupacionNombre());
			listaContenido1.add(agrupamiento.getAgrupacionStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelZonaDeposito() throws IOException {
		List<ZonaDTO> zonas = zonaDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO ZONA");
		titulos.add("NOMBRE ZONA");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (ZonaDTO zona : zonas) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(zona.getZonaCodigo());
			listaContenido1.add(zona.getZonaNombre());
			listaContenido1.add(zona.getZonaStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelRegionDeposito(Long estadoId) throws IOException {
		List<RegionDepositoDTO> regiones = regionDepositoDAO.buscarRegionesPorEstado(estadoId);
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO REGIÓN");
		titulos.add("NOMBRE REGIÓN");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (RegionDepositoDTO region : regiones) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(region.getRegionCodigo());
			listaContenido1.add(region.getRegionNombre());
			listaContenido1.add(region.getRegionStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelDeposito(Long regionId) throws IOException {
		List<RespuestaBusquedaDepositoVO> depositos = depositoMyBatisDAO.buscarDepositosPorRegion(regionId.toString());
		List<String> titulos = new ArrayList<String>();
		titulos.add("Código Depósito");
		titulos.add("Nombre Depósitos");
		titulos.add("Zona");
		titulos.add("Delegación");
		titulos.add("Dirección");
		titulos.add("Colonia");
		titulos.add("Teléfono");
		titulos.add("Capacidad");
		titulos.add("Superficie");
		titulos.add("Status");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (RespuestaBusquedaDepositoVO deposito : depositos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(deposito.getDepositoCodigo());
			listaContenido1.add(deposito.getDepositoNombre());
			listaContenido1.add(deposito.getZonaNombre());
			listaContenido1.add(deposito.getDelegacionNombre());
			listaContenido1.add(deposito.getDepositoDireccion());
			listaContenido1.add(deposito.getDepositoColonia());
			listaContenido1.add(deposito.getDepositoTelefono());
			listaContenido1.add(String.valueOf(deposito.getDepositoCapacidad()));
			listaContenido1.add(String.valueOf(deposito.getDepositoSuperficie()));
			listaContenido1.add(deposito.getStatusDesc());
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelVehiculoMarca() throws IOException {
		List<VehiculoMarcaDTO> marcas = vehiculoMarcaDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO MARCA");
		titulos.add("NOMBRE MARCA");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (VehiculoMarcaDTO marca : marcas) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(marca.getvMarCod());
			listaContenido1.add(marca.getvMarNombre());
			listaContenido1.add(marca.getvMarStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelVehiculoModelo(Long marcaId) throws IOException {
		List<VehiculoModeloDTO> modelos = vehiculoModeloDAO.buscarVehiculoModeloTodos(marcaId);
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO MODELO");
		titulos.add("NOMBRE MODELO");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (VehiculoModeloDTO modelo : modelos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(modelo.getvModCod());
			listaContenido1.add(modelo.getvModNombre());
			listaContenido1.add(modelo.getvModStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelVehiculoSubTipo() throws IOException {
		List<VehiculoSubTipoDTO> subTipos = vehiculoSubTipoDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO SUBIPO");
		titulos.add("NOMBRE SUBTIPO");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (VehiculoSubTipoDTO subTipo : subTipos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(subTipo.getvSubTipoCodigo());
			listaContenido1.add(subTipo.getvSubTipoNombre());
			listaContenido1.add(subTipo.getvSubTipoStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelVehiculoTipo(Long subTipoId) throws IOException {
		List<VehiculoTipoDTO> tipos = vehiculoTipoDAO.buscarVehiculoTipoPorSubTipo(subTipoId);
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO TIPO");
		titulos.add("NOMBRE TIPO");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (VehiculoTipoDTO tipo : tipos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(tipo.getVipoCod());
			listaContenido1.add(tipo.getvTipoNombre());
			listaContenido1.add(tipo.getvTipoStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelVehiculoColor() throws IOException {
		List<VehiculoColorDTO> colores = vehiculoColorDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO COLOR");
		titulos.add("NOMBRE COLOR");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (VehiculoColorDTO color : colores) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(color.getvColorCod());
			listaContenido1.add(color.getvColorNombre());
			listaContenido1.add(color.getvColorStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelVehiculoResponsable() throws IOException {
		List<ResponsableVehiculoDTO> responsables = responsableVehiculoDao.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO RESPONSABLE");
		titulos.add("NOMBRE RESPONSABLE");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (ResponsableVehiculoDTO responsable : responsables) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(responsable.getrVehCod());
			listaContenido1.add(responsable.getrVehNombre());
			listaContenido1.add(responsable.getrVehStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelVehiculoTipoLicencia() throws IOException {
		List<TipoLicenciaDTO> licencias = tipoLicenciaDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO TIPO LICENCIA");
		titulos.add("NOMBRE TIPO LICENCIA");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (TipoLicenciaDTO licencia : licencias) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(licencia.getTipoLCod());
			listaContenido1.add(licencia.getTipoLNombre());
			listaContenido1.add(licencia.getTipoLStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelInfraccionEvento() throws IOException {
		List<EventoDTO> eventos = eventoDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO EVENTO");
		titulos.add("NOMBRE EVENTO");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (EventoDTO evento : eventos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(evento.getEventoCodigo());
			listaContenido1.add(evento.getEventoNombre());
			listaContenido1.add(evento.getEventoStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelInfraccionStatus() throws IOException {
		List<EstatusInfraccionDTO> estatus = estatusInfraccionDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO");
		titulos.add("NOMBRE");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (EstatusInfraccionDTO e : estatus) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(e.getEstatusCodigo());
			listaContenido1.add(e.getEstatusNombre());
			listaContenido1.add(e.getStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelDenominacion() throws IOException {
		List<DenominacionDTO> denominaciones = denominacionDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO DENOMINACIÓN");
		titulos.add("NOMBRE DENOMINACIÓN");
		titulos.add("VALOR");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (DenominacionDTO denominacion : denominaciones) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(denominacion.getDenominacionCodigo());
			listaContenido1.add(denominacion.getDenominacionNombre());
			listaContenido1.add(String.valueOf(denominacion.getDenominacionValor()));
			listaContenido1.add(denominacion.getDenominacionStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelConceptoPago() throws IOException {
		List<ConceptoPagoDTO> conceptos = conceptoPagoDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO CONCEPTO");
		titulos.add("NOMBRE CONCEPTO");
		titulos.add("VALOR");
		titulos.add("DIAS");
		titulos.add("DESCUENTO");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (ConceptoPagoDTO concepto : conceptos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(concepto.getConceptoCodigo());
			listaContenido1.add(concepto.getConceptoNombre());
			listaContenido1.add(String.valueOf(concepto.getConceptoValor()));
			listaContenido1.add(String.valueOf(concepto.getConceptoDescuento()));
			listaContenido1.add(concepto.getConceptoStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelEntidad() throws IOException {
		List<EntidadPagoDTO> entidades = entidadPagoDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO ENTIDAD");
		titulos.add("NOMBRE ENTIDAD");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (EntidadPagoDTO entidad : entidades) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(entidad.getEntidadCodigo());
			listaContenido1.add(entidad.getEntidadNombre());
			listaContenido1.add(entidad.getEntidadStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelBanco() throws IOException {
		List<BancoDTO> bancos = bancoDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO BANCO");
		titulos.add("NOMBRE BANCO");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (BancoDTO banco : bancos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(banco.getBancoCodigo());
			listaContenido1.add(banco.getBancoNombre());
			listaContenido1.add(banco.getBancoStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelTipoIngreso() throws IOException {
		List<TipoIngresoDTO> tipos = tIngrDao.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO TIPO INGRESO");
		titulos.add("NOMBRE TIO INGRESO");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (TipoIngresoDTO tipo : tipos) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(tipo.gettIngrCod());
			listaContenido1.add(tipo.gettIngrNombre());
			listaContenido1.add(tipo.gettIngrStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelCausaIngreso() throws IOException {
		List<CausaIngresoDTO> causas = causaIngresoDAO.buscarTodos();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO CAUSA");
		titulos.add("NOMBRE CAUSA");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (CausaIngresoDTO causa : causas) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(causa.getCodigoCausaIngreso());
			listaContenido1.add(causa.getNombreCausaIngreso());
			listaContenido1.add(causa.getStatusCausaIngreso().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelAreaIngreso() throws IOException {
		List<AreaDTO> areas = areaDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO AGRUPAMIENTO");
		titulos.add("NOMBRE AGRUPAMIENTO");
		titulos.add("STATUS AGRUPAMIENTO");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (AreaDTO area : areas) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(area.getAreaCodigo());
			listaContenido1.add(area.getAreaNombre());
			listaContenido1.add(area.getAreaStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelComponente() throws IOException {
		List<ComponentesInventarioDTO> componentes = componentesInventarioDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO COMPONENTE");
		titulos.add("NOMBRE COMPONENTE");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (ComponentesInventarioDTO componente : componentes) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(componente.getCodigoComponente());
			listaContenido1.add(componente.getNombreComponente());
			listaContenido1.add(componente.getStatusComponente().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelPrograma() throws IOException {
		List<ProgramaDTO> programas = programaDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO PROGRAMA");
		titulos.add("NOMBRE PROGRAMA");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (ProgramaDTO programa : programas) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(programa.getProgramaCodigo());
			listaContenido1.add(programa.getProgramaNombre());
			listaContenido1.add(programa.getProgramaStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelCategorias() throws IOException {
		List<CategoriaDTO> categorias = categoriaDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO CATEGORIA");
		titulos.add("NOMBRE CATEGORIA");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (CategoriaDTO categoria : categorias) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(categoria.getCategoriaCodigo());
			listaContenido1.add(categoria.getCategoriaDesc());
			listaContenido1.add(categoria.getCategoriaStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	@Override
	@Transactional
	public ByteArrayOutputStream generarReporteExcelCausales() throws IOException {
		List<CausalesDTO> causales = causalDAO.findAll();
		List<String> titulos = new ArrayList<String>();
		titulos.add("CÓDIGO CAUSAL");
		titulos.add("NOMBRE CAUSAL");
		titulos.add("ESTATUS");
		List<String> listaContenido1;
		List<Object> contenido1 = new ArrayList<Object>();
		for (CausalesDTO causal : causales) {
			listaContenido1 = new ArrayList<>();
			listaContenido1.add(causal.getCausalCodigo());
			listaContenido1.add(causal.getCausalNombre());
			listaContenido1.add(causal.getCausalStatus().equals("A") ? "Activo" : "Cancelado");
			contenido1.add(listaContenido1);
		}
		return generarReporte(titulos, contenido1);
	}

	private ByteArrayOutputStream generarReporte(List<String> titulos, List<Object> contenido1) throws IOException {
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<Object> contenido = new ArrayList<Object>();
		propiedadesReporte.setNombreReporte("Reporte");
		propiedadesReporte.setExtencionArchvio(".xls");
		contenido.add(contenido1);
		propiedadesReporte.setTituloExcel("Reporte");
		encabezadoTitulo.add(titulos);
		peticioReporteVO.setContenido(contenido);
		peticioReporteVO.setEncabezado(encabezadoTitulo);
		peticioReporteVO.setPropiedadesReporte(propiedadesReporte);
		return peticionReporteBOImpl.generaReporteExcel(peticioReporteVO);
	}

	public List<ListaNoGruasVO> listaNoGruas() {
		return catalogoIngresoDAO.listaNoGruas();
	}

//	/***
//	 * {@inheritDoc}
//	 */
//	public List<FilterValuesVO> filtroOrigenPlaca(Boolean detcOrfoto) {
//		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
//
//		if (detcOrfoto) {
//			for (int i = 0; i < 2; i++) {
//				FilterValuesVO filter = new FilterValuesVO();
//				if (i == 0) {
//					filter.setCodigo(i + 1);
//					filter.setDescripcion("Foránea");
//				} else if (i == 1) {
//					filter.setCodigo(i - 1);
//					filter.setDescripcion("CDMX");
//				} //else if (i == 2) {
////					filter.setCodigo(i);
////					filter.setDescripcion("Todos");
////				}
//
//				filterValues.add(0, filter);
//			}
//		} else {
//			for (int i = 0; i < 2; i++) {
//				FilterValuesVO filter = new FilterValuesVO();
//				if (i == 0) {
//					filter.setCodigo(1);
//					filter.setDescripcion("Foránea");
//				} else if (i == 1) {
//					filter.setCodigo(0);
//					filter.setDescripcion("CDMX");
//				}
////				else{
////					filter.setCodigo(i);
////					filter.setDescripcion("Todos");
////				}
//				filterValues.add(0, filter);
//			}
//		}
//		return filterValues;
//	}
	
	/***
	 * {@inheritDoc}
	 */
	public List<FilterValuesVO> filtroOrigenPlaca(Boolean detcOrfoto) {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();

		if (detcOrfoto) {
			for (int i = 0; i < 2; i++) {
				FilterValuesVO filter = new FilterValuesVO();
				if (i == 0) {
					filter.setCodigo(i + 1);
					filter.setDescripcion("Foránea");
				} else if (i == 1) {
					filter.setCodigo(i - 1);
					filter.setDescripcion("CDMX");
				} //else if (i == 2) {
//					filter.setCodigo(i);
//					filter.setDescripcion("Todos");
//				}

				filterValues.add(0, filter);
			}
		} else {
			for (int i = 0; i < 2; i++) {
				FilterValuesVO filter = new FilterValuesVO();
				if (i == 0) {
					filter.setCodigo(RadarTipoArchivo.FOTOMULTA_FORANEA.getProcesoId());
					filter.setDescripcion("Foránea");
				} else if (i == 1) {
					filter.setCodigo(RadarTipoArchivo.FOTOMULTA.getProcesoId());
					filter.setDescripcion("CDMX");
				}
//				else{
//					filter.setCodigo(i);
//					filter.setDescripcion("Todos");
//				}
				filterValues.add(0, filter);
			}
		}
		return filterValues;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> registrosPorPagina() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		// 5 10 20 50 100
		for (int i = 0; i < 5; i++) {
			FilterValuesVO filter = new FilterValuesVO();

			if (i == 0) {
				filter.setCodigo(5);
				filter.setDescripcion("5");
			} else if (i == 1) {
				filter.setCodigo(10);
				filter.setDescripcion("10");
			} else if (i == 2) {
				filter.setCodigo(20);
				filter.setDescripcion("20");
			} else if (i == 3) {
				filter.setCodigo(50);
				filter.setDescripcion("50");
			} else if (i == 4) {
				filter.setCodigo(100);
				filter.setDescripcion("100");
			}

			filterValues.add(filter);
		}
		return filterValues;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<ListCausaIngresoVO> listaCausaIngresoByTraslado() {
	
		return catalogoIngresoDAO.listaCausaIngresoByTraslado();
	}
	
	
	/***
	 * Modulo Dispositivos fijos ,trae una lista con los tipos y conuna clave 
	 * José Carmen Castillo Navarrete 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<FMTiposDeteccionesVO> fmTiposDetecciones(Boolean todos) {
		List<FMTiposDeteccionesVO> filterValues = deteccionesMyBatisDAO.buscarTipoDeteccion();
		if (todos){
			FMTiposDeteccionesVO filter = new FMTiposDeteccionesVO();
			filter.setNbDispositivoDeteccion("Todos");
			filter.setIdTipoDeteccion(Long.parseLong("0"));
			filter.setIdArchivoTipoFora(0);
			
			filterValues.add(0, filter);
		}
		return filterValues;
	}
	
	/*
	 * Modulo Dispositivos fijos ,trae una lista tipos de detacciones FMTiposDeteccionesVO
	 * José Carmen Castillo Navarrete 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<FMMarcaDispositivoVO> fmTiposRadaresByMarca(Boolean todos, Integer idMarca) {
		List<FMMarcaDispositivoVO> filterValues = deteccionesMyBatisDAO.ListaTipoRadar(idMarca);
		if (todos){
			FMMarcaDispositivoVO filter = new FMMarcaDispositivoVO();
			filter.setNbMarcaDispositivo("Todos");
			filter.setIdMarcaDispositivoDet(0);
			filterValues.add(0, filter);
		}
		return filterValues;
	}

	@Override
	public List<FMOrigenPlacaVO> buscaCatOrigenPlaca() {
		FMOrigenPlacaVO cdmx = new FMOrigenPlacaVO();
		FMOrigenPlacaVO fora = new FMOrigenPlacaVO();
		List<FMOrigenPlacaVO> lista = new ArrayList<FMOrigenPlacaVO>();
		cdmx.setId(0L); cdmx.setNombre("Ciudad de Mexico");
		fora.setId(1L); fora.setNombre("Foraneo");
		lista.add(cdmx);
		lista.add(fora);
		return lista;
	}
	
	
	/*
	 * Modulo Dispositivos fijos ,trae una lista tipos de detacciones FMTiposDeteccionesVO
	 * José Carmen Castillo Navarrete 
	 */
	@Override
	public List<FilterValuesVO> fmPeriodoFecha(Boolean option) {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		for (int i = 0; i <= 7; i++) {
			FilterValuesVO filter = new FilterValuesVO();

			if (i == 0) {
				filter.setCodigo(i);
				filter.setDescripcion("Hoy");
			} else if (i == 1) {
				filter.setCodigo(i);
				filter.setDescripcion("Ayer");
			} else if (i == 2) {
				filter.setCodigo(i);
				filter.setDescripcion("Esta Semana");
			} else if (i == 3) {
				filter.setCodigo(i);
				filter.setDescripcion("Última Semana");
			} else if (i == 4) {
				filter.setCodigo(i);
				filter.setDescripcion("Últimos 7 Días");
			} else if (i == 5) {
				filter.setCodigo(i);
				filter.setDescripcion("Este Mes");
			} else if (i == 6) {
				filter.setCodigo(i);
				filter.setDescripcion("Último Mes");
			} else if (i == 7) {
				filter.setCodigo(i);
				filter.setDescripcion("Últimos 30 Días");
			}
			
			filterValues.add(filter);
		}
		return filterValues;
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FilterValuesVO> buscaTipoDescuento() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		// 5 10 20 50 100
		for (int i = 0; i < 3; i++) {
			FilterValuesVO filter = new FilterValuesVO();

			if (i == 0) {
				filter.setCodigo(1);
				filter.setDescripcion("Máximo");
			} else if (i == 1) {
				filter.setCodigo(2);
				filter.setDescripcion("Mínimo");
			} else if (i == 2) {
				filter.setCodigo(3);
				filter.setDescripcion("Automático");
			}

			filterValues.add(filter);
		}
		return filterValues;
	}
	
	/***
	 * Modulo Fotocivicas ,trae una lista con los tipos y conuna clave 
	 * José Carmen Castillo Navarrete 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<FMTipoFotocivicaVO> fcTiposDetecciones(Integer todos) {
		List<FMTipoFotocivicaVO> filterValues = deteccionesMyBatisDAO.buscarTipoDeteccionFc(todos);
		return filterValues;
	}
	
	@Override
	public List<FMTipoArchivoFCVO> buscaTipoArchivo(Integer origenPlaca) {
		if(origenPlaca == null){
			origenPlaca = -1;
		}
		List<FMTipoArchivoFCVO> filterValues = deteccionesMyBatisDAO.consultaTipoArchivoFC(origenPlaca);
		return filterValues;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<FMTipoFotocivicaVO> fcTiposDeteccionesOpcion(Integer opcion) {
		List<FMTipoFotocivicaVO> filterValues = deteccionesMyBatisDAO.buscarTipoDeteccionFcOpcion(opcion);
		return filterValues;
	}
	
	@Override
	public List<FMTipoArchivoFCVO> buscaTipoArchivoTodo(Integer opcion) {
		List<FMTipoArchivoFCVO> filterValues = deteccionesMyBatisDAO.consultaTipoArchivoFCTodo(opcion);
		return filterValues;
	}
	
	public List<FMOrigenPlacaFCVO> filtroOrigenPlacaFC(Integer todos) {
		List<FMOrigenPlacaFCVO> filterValues = deteccionesMyBatisDAO.consultaOrigenPlacaFC(todos);
		return filterValues;
	}
	
	public List<FilterValuesVO> filtroTiposBusquedaDetFC(Integer tipoProcesable) {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		
		Map<String, String> parametros = getParametros();
		String tipoBusquedaDet = parametros.get("TIPO_BUSQUEDA_DETECCIONES");
		String[] catBusqueda = tipoBusquedaDet.split("\\|");
		
		for (Integer j = 0; j < catBusqueda.length; j++) {
			String[] tipoBusquedaSel = catBusqueda[j].split(",");
			if(tipoBusquedaSel[1].equals(tipoProcesable.toString())) {
				FilterValuesVO filterValuesVO = new FilterValuesVO();
				filterValuesVO.setCodigo(Integer.valueOf(tipoBusquedaSel[0]));
				filterValuesVO.setDescripcion(tipoBusquedaSel[2]);
				filterValuesVO.setCodigoString(tipoBusquedaSel[3]);
				
				filterValues.add(filterValuesVO);
			}else if(tipoProcesable == 0) {
				FilterValuesVO filterValuesVO = new FilterValuesVO();
				filterValuesVO.setCodigo(Integer.valueOf(tipoBusquedaSel[0]));
				filterValuesVO.setDescripcion(tipoBusquedaSel[2]);
				filterValuesVO.setCodigoString(tipoBusquedaSel[3]);
				
				filterValues.add(filterValuesVO);
			}
			
		}
		return filterValues;
	}
	
	@Transactional (readOnly =  true)
	public Map<String, String> getParametros(){
		List<Map<String, String>> listaParametros = deteccionesMyBatisDAO.buscarQuerys();
		Map<String, String> parametros = new HashMap<String, String>();
		for(Map<String, String> registro : listaParametros) {
			parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		}
		return parametros;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<TipoBusquedaAtcVO> buscarTiposBusquedaTramitesAtencionCiudadana() {
		List<TipoBusquedaAtcDTO> listDTO = tipoBusquedaAtcDAOImpl.buscarTiposBusquedaAtencionCiudadana();
		List<TipoBusquedaAtcVO> listVO = ResponseConverter.converterLista(new ArrayList<>(), listDTO, TipoBusquedaAtcVO.class);
		return listVO;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Map<String, String> getParametrosBD(int op, String valor) {
		List<Map<String, String>> listaParametros = new ArrayList<Map<String, String>>();
		Map<String, String> parametros = new HashMap<String, String>();
		switch(op) {
			case 0:
				listaParametros = parametrosBDDAO.getParametrosTodos();
			break;
			case 1:
				listaParametros = parametrosBDDAO.getParametrosSoloQuerys();
			break;
			case 2:
				listaParametros = parametrosBDDAO.getParametrosSoloParametros();
			break;
			case 3:
			listaParametros = parametrosBDDAO.getParametrosPorNbConfig(valor);
			break;
			case 4:
				listaParametros = parametrosBDDAO.getParametrosPorCdConfig(valor);
			break;
		}
		for(Map<String, String> registro : listaParametros) {
			parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		}
		return parametros;
	}
	@Override
	@Transactional(readOnly = true)
	public List<TipoFechasDTO> tipoFechas() {
		return  tipoFechasDAO.buscarTipoFechas();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<GarantiaDocumentoDTO> catalogoDocGarantias(){
		return garantiasDocumentoDAO.buscarTipoDocumentos();
	}
	
	@Override
	public List<EstatusSeguimientoTramiteACVO> buscaEstatusTramite(Integer todos) {
		List<EstatusSeguimientoTramiteACVO> filterValues = new ArrayList<EstatusSeguimientoTramiteACVO>();
		Map<String, String> parametros = getParametros();
		String consStSegTramite = parametros.get("CONS_ST_SEG_TRAMITE");
		
		consStSegTramite = StringUtils.replace(consStSegTramite, "#{todos}", todos.toString());
		filterValues = tramitesACMyBatisDAO.consStSegTramite(consStSegTramite);
		
		return filterValues;
	}
	
	@Override
	public List<FilterValuesVO> parametrosBusquedaAC() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		// 5 10 20 50 100
		for (int i = 0; i < 3; i++) {
			FilterValuesVO filter = new FilterValuesVO();

			if (i == 0) {
				filter.setCodigo(1);
				filter.setDescripcion("Folio");
			} else if (i == 1) {
				filter.setCodigo(2);
				filter.setDescripcion("Infración");
			} else if (i == 2) {
				filter.setCodigo(3);
				filter.setDescripcion("Placa");
			}

			filterValues.add(filter);
		}
		return filterValues;
	}
}
