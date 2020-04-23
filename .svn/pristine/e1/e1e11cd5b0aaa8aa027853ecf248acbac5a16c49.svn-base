package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEstatusInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEventoVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * Clase que representa la Administración de Infracciones:
 * 	-Eventos: Representación de : Store Procedure: ABC_EVENTO y Trigger: TR_BITAC_EVENTOS
 *  -Estatus infraccion: Representación de : Store Procedure: ABC_ESTATUS_INFRACCION y Trigger: TR_BITAC_ESTAT_INFRAC
 * 
 */

@Service
public class BitAdminCatalogoInfraccionesImpl implements BitAdminCatalogoInfracciones {

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> comparaeEventos(CrudEventoVO newCrudEventoVO, CrudEventoVO oldCrudEventoVO,
			String oldEstatus) {
//		-Eventos: Representación de : Store Procedure: ABC_EVENTO y Trigger: TR_BITAC_EVENTOS
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudEventoVO.setEventoCodigo(newCrudEventoVO.getEventoCodigo().toUpperCase());
		newCrudEventoVO.setEventoNombre(newCrudEventoVO.getEventoNombre().toUpperCase());
		final long componente = 20L;
		
		filtro.add(new BitComponente(2L, "getEventoCodigo"));
		filtro.add(new BitComponente(3L, "getEventoNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudEventoVO, oldCrudEventoVO, CrudEventoVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudEventoVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(1L);
				bitacoraCamniosVO.setValorOriginal(newCrudEventoVO.getEventoCodigo());
				bitacoraCamniosVO.setValorFinal(newCrudEventoVO.getEventoNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudEventoVO.getModificadoPor() != null ? newCrudEventoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudEventoVO.getResultado() != null ? newCrudEventoVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudEventoVO.getOperacion().equals("E") && newCrudEventoVO.getEventoId() == oldCrudEventoVO.getEventoId()){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudEventoVO) != null ? m.invoke(oldCrudEventoVO).toString() : "";
					String newVal = m.invoke(newCrudEventoVO) != null ? m.invoke(newCrudEventoVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudEventoVO.getModificadoPor() != null ? newCrudEventoVO.getModificadoPor() : 0L,
						newCrudEventoVO.getEventoId() != null ? newCrudEventoVO.getEventoId().toString() : newCrudEventoVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudEventoVO.getOperacion().equals("E") && newCrudEventoVO.getEventoId() == oldCrudEventoVO.getEventoId()){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(4L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudEventoVO.getModificadoPor() != null ? newCrudEventoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudEventoVO.getEventoId() != null ? newCrudEventoVO.getEventoId().toString() : newCrudEventoVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudEventoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(4L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudEventoVO.getModificadoPor() != null ? newCrudEventoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudEventoVO.getEventoId() != null ? newCrudEventoVO.getEventoId().toString() : newCrudEventoVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararEstatus(CrudEstatusInfraccionVO newCrudEstatusInfraccionVO,
			CrudEstatusInfraccionVO oldCrudEstatusInfraccionVO, String oldEstatus) {
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudEstatusInfraccionVO.setEstatusCodigo(newCrudEstatusInfraccionVO.getEstatusCodigo().toUpperCase());
		newCrudEstatusInfraccionVO.setEstatusNombre(newCrudEstatusInfraccionVO.getEstatusNombre().toUpperCase());
		final long componente = 20L;
		
		filtro.add(new BitComponente(6L, "getEstatusCodigo"));
		filtro.add(new BitComponente(7L, "getEstatusNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudEstatusInfraccionVO, oldCrudEstatusInfraccionVO, CrudEstatusInfraccionVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudEstatusInfraccionVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(5L);
				bitacoraCamniosVO.setValorOriginal(newCrudEstatusInfraccionVO.getEstatusCodigo());
				bitacoraCamniosVO.setValorFinal(newCrudEstatusInfraccionVO.getEstatusNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudEstatusInfraccionVO.getModificadoPor() != null ? newCrudEstatusInfraccionVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudEstatusInfraccionVO.getResultado() != null ? newCrudEstatusInfraccionVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudEstatusInfraccionVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudEstatusInfraccionVO) != null ? m.invoke(oldCrudEstatusInfraccionVO).toString() : "";
					String newVal = m.invoke(newCrudEstatusInfraccionVO) != null ? m.invoke(newCrudEstatusInfraccionVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudEstatusInfraccionVO.getModificadoPor() != null ? newCrudEstatusInfraccionVO.getModificadoPor() : 0L,
						newCrudEstatusInfraccionVO.getEstatusId() != null ? newCrudEstatusInfraccionVO.getEstatusId().toString() : newCrudEstatusInfraccionVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudEstatusInfraccionVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(8L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudEstatusInfraccionVO.getModificadoPor() != null ? newCrudEstatusInfraccionVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudEstatusInfraccionVO.getEstatusId() != null ? newCrudEstatusInfraccionVO.getEstatusId().toString() : newCrudEstatusInfraccionVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudEstatusInfraccionVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(8L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudEstatusInfraccionVO.getModificadoPor() != null ? newCrudEstatusInfraccionVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudEstatusInfraccionVO.getEstatusId() != null ? newCrudEstatusInfraccionVO.getEstatusId().toString() : newCrudEstatusInfraccionVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}
	

}
