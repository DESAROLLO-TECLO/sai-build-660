package mx.com.teclo.saicdmx.negocio.service.controlsuministros;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros.BitSPSuministroAreasNew;
import mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros.BitSPSuministroAreasUPD;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.AltaOficialMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.BuscarAuxiliarAreaOpeMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.BuscarAuxiliarPorIdMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.BuscarAuxiliarPorPlacaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.EliminarOficialMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.ModificarOficialMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialEliminarVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialModificacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialNuevoVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service("auxiliaresService")
public class AuxiliaresServiceImpl implements AuxiliaresService {
	
	@Autowired
	private BuscarAuxiliarPorIdMyBatisDAO auxiliarPorIdDAO;
	
	@Autowired
	private BuscarAuxiliarPorPlacaMyBatisDAO auxiliarPorPlacaDAO;

	@Autowired
	private ModificarOficialMyBatisDAO modificarOficialDAO;

	@Autowired
	private BuscarAuxiliarAreaOpeMyBatisDAO auxiliarAreaDAO;
	
	@Autowired
	private AltaOficialMyBatisDAO altaOficialDAO;
	
	@Autowired
	private EliminarOficialMyBatisDAO bajaOficialDAO;
	
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;

	@Autowired
	private BitSPSuministroAreasUPD  bitSPSuministroAreasUPD;

	@Autowired
	private BitSPSuministroAreasNew  bitSPSuministroAreasNew;

	
	@Override
	public DelegadoAuxiliarVO buscarAuxiliarPorId(Long id_registro) {
		return auxiliarPorIdDAO.buscarAuxiliarPorId(id_registro);
	}
	
	@Transactional
	@Override
	public DelegadoAuxiliarVO buscarAuxiliarPorPlaca(String oficial_placa) {
		return auxiliarPorPlacaDAO.buscarAuxiliarPorPlaca(oficial_placa);
	}
	
	@Override
	public OficialModificacionVO cambiarAuxiliar(OficialModificacionVO valores) {
		BitacoraCambiosVO bitCambios = null;
		try{
			bitCambios = bitSPSuministroAreasUPD.cambiosBitacoraCambioAuxiliar(valores);
		}catch(Exception e){ bitCambios = null; System.err.println(e.getMessage());}
		
		modificarOficialDAO.cambiarAuxiliar(valores);
		
		/*Bitacora de cambios*/
		if(valores != null){
			if(!valores.getResultado().equals("-1") || !valores.getResultado().equals("-2")){
				if(bitCambios!=null)
					bitacoraCambiosService.guardarBitacoraCambios(bitCambios);
			}
		}
		return valores;
	}
	
	@Override
	public DelegadoAuxiliarVO buscarAuxiliarAreaRegion(Long reg_id, Long upc_id) {
		return auxiliarAreaDAO.buscarAuxiliarAreaRegion(reg_id,upc_id);
	}
	

	@Override
	public OficialNuevoVO altaAuxiliar(OficialNuevoVO valoresAux) {
		altaOficialDAO.altaAuxiliar(valoresAux);
		/*Bitacora de cambios*/
		if(valoresAux != null){
			if(!valoresAux.getResultado().equals("-1") ||  !valoresAux.getResultado().equals("-2")){
				try{
				bitacoraCambiosService.guardarBitacoraCambios(bitSPSuministroAreasNew.cambiosBitacoraAltaAuxiliar(valoresAux));
				}catch(Exception e){System.err.println(e.getMessage());}
			}
		}
		return valoresAux;
	}
	
	
	@Override
	public OficialEliminarVO altaBitacoraCambios(OficialEliminarVO valoresAuxBaja) {
		/*bitacoraCambiosService.guardarBitacoraCambiosParametros(
		 * ParametrosBitacoraEnum.BITACORA_CAMBIOS.getParametro(), 3L, 5L, valoresAuxBaja.getOficial_nombre(), "", valoresAuxBaja.getIdUser(), valoresAuxBaja.getId_registro(), ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		 * bitacoraCambiosService.guardarBitacoraCambiosParametros(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 3L, 5L, valoresAuxBaja.getOficial_nombre(), 
				"", valoresAuxBaja.getIdUser(), valoresAuxBaja.getId_registro().toString(), ParametrosBitacoraEnum.ORIGEN_W.getParametro());*/
		bajaOficialDAO.altaBitacoraCambios(valoresAuxBaja);
		return valoresAuxBaja;
	}
	
	@Override
	public OficialEliminarVO eliminarAuxiliar(OficialEliminarVO valoresAuxBaja) {
		bajaOficialDAO.eliminarAuxiliar(valoresAuxBaja);
		
		///////////////// Cambio realizado desde iBatis ...
		if(valoresAuxBaja != null){
			if(valoresAuxBaja.getId_registro() != null){
				bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					3L,
					5L,
					valoresAuxBaja.getOficial_nombre(),
					"",
					valoresAuxBaja.getIdUser() != null ? valoresAuxBaja.getIdUser() : 0L,
					valoresAuxBaja.getId_registro().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
				);
			}
		}
		/////////////////
		
		
		return valoresAuxBaja;
	}

	
}
