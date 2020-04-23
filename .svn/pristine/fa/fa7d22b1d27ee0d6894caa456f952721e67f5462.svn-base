package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudRegionDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudZonaVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * Clase que representa la Administración de Agentes:
 * 	-Depositos: Representación de : Store Procedure: ABC_DEPOSITOS y Trigger: TR_BITAC_DEPOSITOS
 *  -Regiones Depositos: Representación de : Store Procedure: ABC_REGIONES_DEPOSITOS y Trigger: TR_BITAC_REG_DEPOS
 *  -Zonas: Representación de : Store Procedure: ABC_ZONAS y Trigger: TR_BITAC_ZONAS  
 */

@Service
public class BitAdminCatalogoDepositosImpl implements BitAdminCatalogoDepositos {

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararDepositos(CrudDepositoVO newCrudDepositoVO,
			CrudDepositoVO oldCrudDepositoVO, String oldEstatus) {
//		-Depositos: Representación de : Store Procedure: ABC_DEPOSITOS y Trigger: TR_BITAC_DEPOSITOS
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 18L;
		newCrudDepositoVO.setDepositoCodigo(newCrudDepositoVO.getDepositoCodigo().toUpperCase());
		newCrudDepositoVO.setDepositoNombre(newCrudDepositoVO.getDepositoNombre().toUpperCase());
		
		filtro.add(new BitComponente(11L, "getDepositoCodigo"));
		filtro.add(new BitComponente(12L, "getDepositoNombre"));
		filtro.add(new BitComponente(13L, "getZonaId"));
		filtro.add(new BitComponente(14L, "getDepositoDireccion"));
		filtro.add(new BitComponente(15L, "getDepositoColonia"));
		filtro.add(new BitComponente(16L, "getDepositoTelefono"));
		filtro.add(new BitComponente(17L, "getEstadoId"));
		filtro.add(new BitComponente(18L, "getDelegacionId"));
		filtro.add(new BitComponente(19L, "getRegionId"));
		filtro.add(new BitComponente(20L, "getDepositoSuperficie"));
		filtro.add(new BitComponente(21L, "getDepositoCapacidad"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(oldCrudDepositoVO, newCrudDepositoVO, CrudDepositoVO.class, BitComponente.convertToList(filtro));
		
		try{
			switch(newCrudDepositoVO.getOperacion()){
				case "A":
					if(oldEstatus.equals("")){
						BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
						bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
						bitacoraCambiosVO.setComponenteId(componente);
						bitacoraCambiosVO.setConceptoId(10L);
						bitacoraCambiosVO.setValorOriginal(newCrudDepositoVO.getDepositoCodigo());
						bitacoraCambiosVO.setValorFinal(newCrudDepositoVO.getDepositoNombre());
						bitacoraCambiosVO.setCreadoPor(newCrudDepositoVO.getModificadoPor() != null ? newCrudDepositoVO.getModificadoPor() : 0L);
						bitacoraCambiosVO.setIdRegistro(newCrudDepositoVO.getResultado() != null ? newCrudDepositoVO.getResultado() : "");
						bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
						listBitacoraCambiosVO.add(bitacoraCambiosVO);
					}
					else if(!oldEstatus.equals("")){
						for(Method m : listaCambios){
							String oldVal = m.invoke(oldCrudDepositoVO) != null ? m.invoke(oldCrudDepositoVO).toString() : "";
							String newVal = m.invoke(newCrudDepositoVO) != null ? m.invoke(newCrudDepositoVO).toString() : "";
							
							listBitacoraCambiosVO.add(new BitacoraCambiosVO(
								ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
								componente,
								BitComponente.findByParametro(filtro, m.getName()).getComponente(),
								oldVal,
								newVal,
								newCrudDepositoVO.getModificadoPor() != null ? newCrudDepositoVO.getModificadoPor() : 0L,
								newCrudDepositoVO.getDepositoId() != null ? newCrudDepositoVO.getDepositoId().toString() : newCrudDepositoVO.getResultado(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));
						}
						if(!oldEstatus.equals("A")){
							BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
							bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
							bitacoraCambiosVO.setComponenteId(componente);
							bitacoraCambiosVO.setConceptoId(22L);
							bitacoraCambiosVO.setValorOriginal(oldEstatus);
							bitacoraCambiosVO.setValorFinal("A");
							bitacoraCambiosVO.setCreadoPor(newCrudDepositoVO.getModificadoPor() != null ? newCrudDepositoVO.getModificadoPor() : 0L);
							bitacoraCambiosVO.setIdRegistro(newCrudDepositoVO.getResultado() != null ? newCrudDepositoVO.getResultado() : "");
							bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
							listBitacoraCambiosVO.add(bitacoraCambiosVO);
						}
					}
					break;
				case "M":
					for(Method m : listaCambios){
						String oldVal = m.invoke(oldCrudDepositoVO) != null ? m.invoke(oldCrudDepositoVO).toString() : "";
						String newVal = m.invoke(newCrudDepositoVO) != null ? m.invoke(newCrudDepositoVO).toString() : "";
						
						listBitacoraCambiosVO.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
							componente,
							BitComponente.findByParametro(filtro, m.getName()).getComponente(),
							oldVal,
							newVal,
							newCrudDepositoVO.getModificadoPor() != null ? newCrudDepositoVO.getModificadoPor() : 0L,
							newCrudDepositoVO.getDepositoId() != null ? newCrudDepositoVO.getDepositoId().toString() : newCrudDepositoVO.getResultado(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
					}
					if(!oldEstatus.equals("A")){
						BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
						bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
						bitacoraCambiosVO.setComponenteId(componente);
						bitacoraCambiosVO.setConceptoId(22L);
						bitacoraCambiosVO.setValorOriginal(oldEstatus);
						bitacoraCambiosVO.setValorFinal("A");
						bitacoraCambiosVO.setCreadoPor(newCrudDepositoVO.getModificadoPor() != null ? newCrudDepositoVO.getModificadoPor() : 0L);
						bitacoraCambiosVO.setIdRegistro(newCrudDepositoVO.getResultado() != null ? newCrudDepositoVO.getResultado() : "");
						bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
						listBitacoraCambiosVO.add(bitacoraCambiosVO);
					}
					break;
				case "E":
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(22L);
					bitacoraCambiosVO.setValorOriginal(oldEstatus);
					bitacoraCambiosVO.setValorFinal("E");
					bitacoraCambiosVO.setCreadoPor(newCrudDepositoVO.getModificadoPor() != null ? newCrudDepositoVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudDepositoVO.getResultado() != null ? newCrudDepositoVO.getResultado() : "");
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
					break;
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararRegionDep(CrudRegionDepositoVO newCrudRegionDepositoVO,
			CrudRegionDepositoVO oldCrudRegionDepositoVO, String oldEstatus) {
//		-Regiones Depositos: Representación de : Store Procedure: ABC_REGIONES_DEPOSITOS y Trigger: TR_BITAC_REG_DEPOS
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		newCrudRegionDepositoVO.setRegionCodigo(newCrudRegionDepositoVO.getRegionCodigo().toUpperCase());
		newCrudRegionDepositoVO.setRegionNombre(newCrudRegionDepositoVO.getRegionNombre().toUpperCase());
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 18;
		
//		filtro.add(new BitComponente(6L, "getEstadoDTO"));
		filtro.add(new BitComponente(7L, "getRegionCodigo"));
		filtro.add(new BitComponente(8L, "getRegionNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudRegionDepositoVO, oldCrudRegionDepositoVO, CrudRegionDepositoVO.class, BitComponente.convertToList(filtro));
	
		try{
			switch(newCrudRegionDepositoVO.getOperacion()){
				case "A":
					if(oldEstatus.equals("")){
						BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
						bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
						bitacoraCambiosVO.setComponenteId(componente);
						bitacoraCambiosVO.setConceptoId(5L);
						bitacoraCambiosVO.setValorOriginal(newCrudRegionDepositoVO.getRegionCodigo());
						bitacoraCambiosVO.setValorFinal(newCrudRegionDepositoVO.getRegionNombre());
						bitacoraCambiosVO.setCreadoPor(newCrudRegionDepositoVO.getModificadoPor() != null ? newCrudRegionDepositoVO.getModificadoPor() : 0L);
						bitacoraCambiosVO.setIdRegistro(newCrudRegionDepositoVO.getResultado() != null ? newCrudRegionDepositoVO.getResultado() : "");
						bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
						listBitacoraCambiosVO.add(bitacoraCambiosVO);
					}else if(!oldEstatus.equals("")){
						for(Method m : listaCambios){
							String oldVal = m.invoke(oldCrudRegionDepositoVO) != null ? m.invoke(oldCrudRegionDepositoVO).toString() : "";
							String newVal = m.invoke(newCrudRegionDepositoVO) != null ? m.invoke(newCrudRegionDepositoVO).toString() : "";
							listBitacoraCambiosVO.add(new BitacoraCambiosVO(
								ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
								componente,
								BitComponente.findByParametro(filtro, m.getName()).getComponente(),
								oldVal,
								newVal,
								newCrudRegionDepositoVO.getModificadoPor() != null ? newCrudRegionDepositoVO.getModificadoPor() : 0L,
								newCrudRegionDepositoVO.getRegionId() != null ? newCrudRegionDepositoVO.getRegionId().toString() : newCrudRegionDepositoVO.getResultado(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));
						}
						if(!oldEstatus.equals("A")){
							BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
							bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
							bitacoraCambiosVO.setComponenteId(componente);
							bitacoraCambiosVO.setConceptoId(9L);
							bitacoraCambiosVO.setValorOriginal(oldEstatus);
							bitacoraCambiosVO.setValorFinal("A");
							bitacoraCambiosVO.setCreadoPor(newCrudRegionDepositoVO.getModificadoPor() != null ? newCrudRegionDepositoVO.getModificadoPor() : 0L);
							bitacoraCambiosVO.setIdRegistro(newCrudRegionDepositoVO.getRegionId() != null ? newCrudRegionDepositoVO.getRegionId().toString() : newCrudRegionDepositoVO.getResultado());
							bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
							listBitacoraCambiosVO.add(bitacoraCambiosVO);
						}
					}
					break;
				case "M":
					for(Method m : listaCambios){
						String oldVal = m.invoke(oldCrudRegionDepositoVO) != null ? m.invoke(oldCrudRegionDepositoVO).toString() : "";
						String newVal = m.invoke(newCrudRegionDepositoVO) != null ? m.invoke(newCrudRegionDepositoVO).toString() : "";
						listBitacoraCambiosVO.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
							componente,
							BitComponente.findByParametro(filtro, m.getName()).getComponente(),
							oldVal,
							newVal,
							newCrudRegionDepositoVO.getModificadoPor() != null ? newCrudRegionDepositoVO.getModificadoPor() : 0L,
							newCrudRegionDepositoVO.getRegionId() != null ? newCrudRegionDepositoVO.getRegionId().toString() : newCrudRegionDepositoVO.getResultado(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
					}
					if(ComparaUtils.comparaCadenas(newCrudRegionDepositoVO.getEstadoDTO().getEdoId(), oldCrudRegionDepositoVO.getEstadoDTO().getEdoId())){
						BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
						bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
						bitacoraCambiosVO.setComponenteId(componente);
						bitacoraCambiosVO.setConceptoId(6L);
						bitacoraCambiosVO.setValorOriginal(oldCrudRegionDepositoVO.getEstadoDTO().getEdoId().toString());
						bitacoraCambiosVO.setValorFinal(newCrudRegionDepositoVO.getEstadoDTO().getEdoId().toString());
						bitacoraCambiosVO.setCreadoPor(newCrudRegionDepositoVO.getModificadoPor() != null ? newCrudRegionDepositoVO.getModificadoPor() : 0L);
						bitacoraCambiosVO.setIdRegistro(newCrudRegionDepositoVO.getRegionId() != null ? newCrudRegionDepositoVO.getRegionId().toString() : newCrudRegionDepositoVO.getResultado());
						bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
						listBitacoraCambiosVO.add(bitacoraCambiosVO);
					}
					if(!oldEstatus.equals("A")){
						BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
						bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
						bitacoraCambiosVO.setComponenteId(componente);
						bitacoraCambiosVO.setConceptoId(9L);
						bitacoraCambiosVO.setValorOriginal(oldEstatus);
						bitacoraCambiosVO.setValorFinal("A");
						bitacoraCambiosVO.setCreadoPor(newCrudRegionDepositoVO.getModificadoPor() != null ? newCrudRegionDepositoVO.getModificadoPor() : 0L);
						bitacoraCambiosVO.setIdRegistro(newCrudRegionDepositoVO.getRegionId() != null ? newCrudRegionDepositoVO.getRegionId().toString() : newCrudRegionDepositoVO.getResultado());
						bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
						listBitacoraCambiosVO.add(bitacoraCambiosVO);
					}
					break;
				case "E":
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(9L);
					bitacoraCambiosVO.setValorOriginal(oldEstatus);
					bitacoraCambiosVO.setValorFinal("E");
					bitacoraCambiosVO.setCreadoPor(newCrudRegionDepositoVO.getModificadoPor() != null ? newCrudRegionDepositoVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudRegionDepositoVO.getRegionId() != null ? newCrudRegionDepositoVO.getRegionId().toString() : newCrudRegionDepositoVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
					break;
						
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararZonas(CrudZonaVO newCrudZonaVO, CrudZonaVO oldCrudZonaVO,
			String oldEstatus) {
//		-Zonas: Representación de : Store Procedure: ABC_ZONAS y Trigger: TR_BITAC_ZONAS
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		newCrudZonaVO.setZonaCodigo(newCrudZonaVO.getZonaCodigo().toUpperCase());
		newCrudZonaVO.setZonaNombre(newCrudZonaVO.getZonaNombre().toUpperCase());
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 18L; 
		
		filtro.add(new BitComponente(2L, "getZonaCodigo"));
		filtro.add(new BitComponente(3L, "getZonaNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(oldCrudZonaVO, newCrudZonaVO, CrudZonaVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && !newCrudZonaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(1L);
				bitacoraCambiosVO.setValorOriginal(newCrudZonaVO.getZonaCodigo());
				bitacoraCambiosVO.setValorFinal(newCrudZonaVO.getZonaNombre());
				bitacoraCambiosVO.setCreadoPor(newCrudZonaVO.getModificadoPor() != null ? newCrudZonaVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudZonaVO.getResultado() != null ? newCrudZonaVO.getResultado() : "");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}
			if(!oldEstatus.equals("") && !newCrudZonaVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudZonaVO) != null ? m.invoke(oldCrudZonaVO).toString() : "";
					String newVal = m.invoke(newCrudZonaVO) != null ? m.invoke(newCrudZonaVO).toString() : "";
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudZonaVO.getModificadoPor() != null ? newCrudZonaVO.getModificadoPor() : 0L,
						newCrudZonaVO.getZonaId() != null ? newCrudZonaVO.getZonaId().toString() : newCrudZonaVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudZonaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(4L);
				bitacoraCambiosVO.setValorOriginal(oldEstatus);
				bitacoraCambiosVO.setValorFinal("A");
				bitacoraCambiosVO.setCreadoPor(newCrudZonaVO.getModificadoPor() != null ? newCrudZonaVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudZonaVO.getResultado() != null ? newCrudZonaVO.getResultado() : "");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}
			if(newCrudZonaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(4L);
				bitacoraCambiosVO.setValorOriginal(oldEstatus);
				bitacoraCambiosVO.setValorFinal("E");
				bitacoraCambiosVO.setCreadoPor(newCrudZonaVO.getModificadoPor() != null ? newCrudZonaVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudZonaVO.getResultado() != null ? newCrudZonaVO.getResultado() : "");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}

}
