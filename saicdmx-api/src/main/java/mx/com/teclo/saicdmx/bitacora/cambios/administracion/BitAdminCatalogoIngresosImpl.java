package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudAreaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCausaIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudComponenteVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoIngresoVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * Clase que representa la Administración de Ingresos:
 * 	-Areas: Representación de : Store Procedure: ABC_AREAS y Trigger: TR_BITAC_AREAS
 *  -Causa ingreso: Representación de : Store Procedure: ABC_CAUSA_INGRESO y Trigger: TR_BITAC_ESTAT_INFRAC
 *  -Comppnentes: Representación de : Store Procedure: ABC_COMPONENTES_INVENTARIO y Trigger: TR_BITAC_COMP_INVENTARIO
 *  -Tipo ingreso: Representación de : Store Procedure: ABC_TIPO_INGRESO y Trigger: TR_BITAC_TIPO_INGRESO 
 */

@Service
public class BitAdminCatalogoIngresosImpl implements BitAdminCatalogoIngresos {

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararAreas(CrudAreaVO newCrudAreaVO, CrudAreaVO oldCrudAreaVO,
			String oldEstatus) {
//		-Areas: Representación de : Store Procedure: ABC_AREAS y Trigger: TR_BITAC_AREAS
		newCrudAreaVO.setAreaCodigo(newCrudAreaVO.getAreaCodigo().toUpperCase());
		newCrudAreaVO.setAreaNombre(newCrudAreaVO.getAreaNombre().toUpperCase());
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 22L;
		
		filtro.add(new BitComponente(10L, "getAreaCodigo"));
		filtro.add(new BitComponente(11L, "getAreaNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudAreaVO, oldCrudAreaVO, CrudAreaVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudAreaVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(9L);
				bitacoraCamniosVO.setValorOriginal(newCrudAreaVO.getAreaCodigo());
				bitacoraCamniosVO.setValorFinal(newCrudAreaVO.getAreaNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudAreaVO.getModificadoPor() != null ? newCrudAreaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudAreaVO.getResultado() != null ? newCrudAreaVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudAreaVO.getOperacion().equals("E") && newCrudAreaVO.getAreaId() == oldCrudAreaVO.getAreaId()){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudAreaVO) != null ? m.invoke(oldCrudAreaVO).toString() : "";
					String newVal = m.invoke(newCrudAreaVO) != null ? m.invoke(newCrudAreaVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudAreaVO.getModificadoPor() != null ? newCrudAreaVO.getModificadoPor() : 0L,
						newCrudAreaVO.getAreaId() != null ? newCrudAreaVO.getAreaId().toString() : newCrudAreaVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudAreaVO.getOperacion().equals("E") && newCrudAreaVO.getAreaId() == oldCrudAreaVO.getAreaId()){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(12L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudAreaVO.getModificadoPor() != null ? newCrudAreaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudAreaVO.getAreaId() != null ? newCrudAreaVO.getAreaId().toString() : newCrudAreaVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudAreaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(12L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudAreaVO.getModificadoPor() != null ? newCrudAreaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudAreaVO.getAreaId() != null ? newCrudAreaVO.getAreaId().toString() : newCrudAreaVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> compararCausa(CrudCausaIngresoVO newCrudCausaIngresoVO,
			CrudCausaIngresoVO oldCrudCausaIngresoVO, String oldEstatus) {
		newCrudCausaIngresoVO.setCodigoCausaIngreso(newCrudCausaIngresoVO.getCodigoCausaIngreso().toUpperCase());
		newCrudCausaIngresoVO.setNombreCausaIngreso(newCrudCausaIngresoVO.getNombreCausaIngreso().toUpperCase());
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 22L;
		
		filtro.add(new BitComponente(6L, "getCodigoCausaIngreso"));
		filtro.add(new BitComponente(7L, "getNombreCausaIngreso"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudCausaIngresoVO, oldCrudCausaIngresoVO, CrudCausaIngresoVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudCausaIngresoVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(5L);
				bitacoraCamniosVO.setValorOriginal(newCrudCausaIngresoVO.getCodigoCausaIngreso());
				bitacoraCamniosVO.setValorFinal(newCrudCausaIngresoVO.getNombreCausaIngreso());
				bitacoraCamniosVO.setCreadoPor(newCrudCausaIngresoVO.getModificadoPor() != null ? newCrudCausaIngresoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudCausaIngresoVO.getResultado() != null ? newCrudCausaIngresoVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudCausaIngresoVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudCausaIngresoVO) != null ? m.invoke(oldCrudCausaIngresoVO).toString() : "";
					String newVal = m.invoke(newCrudCausaIngresoVO) != null ? m.invoke(newCrudCausaIngresoVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudCausaIngresoVO.getModificadoPor() != null ? newCrudCausaIngresoVO.getModificadoPor() : 0L,
						newCrudCausaIngresoVO.getIdCausaIngreso() != null ? newCrudCausaIngresoVO.getIdCausaIngreso().toString() : newCrudCausaIngresoVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudCausaIngresoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(8L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudCausaIngresoVO.getModificadoPor() != null ? newCrudCausaIngresoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudCausaIngresoVO.getIdCausaIngreso() != null ? newCrudCausaIngresoVO.getIdCausaIngreso().toString() : newCrudCausaIngresoVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudCausaIngresoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(8L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudCausaIngresoVO.getModificadoPor() != null ? newCrudCausaIngresoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudCausaIngresoVO.getIdCausaIngreso() != null ? newCrudCausaIngresoVO.getIdCausaIngreso().toString() : newCrudCausaIngresoVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> compararComponente(CrudComponenteVO newCrudComponenteVO,
			CrudComponenteVO oldCrudComponenteVO, String oldEstatus) {
//		-Comppnentes: Representación de : Store Procedure: ABC_COMPONENTES_INVENTARIO y Trigger: TR_BITAC_COMP_INVENTARIO
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudComponenteVO.setCodigoComponente(newCrudComponenteVO.getCodigoComponente().toUpperCase());
		newCrudComponenteVO.setNombreComponente(newCrudComponenteVO.getNombreComponente().toUpperCase());
		final long componente = 22L;
		
		filtro.add(new BitComponente(14L, "getCodigoComponente"));
		filtro.add(new BitComponente(15L, "getNombreComponente"));
		filtro.add(new BitComponente(16L, "getOrdenComponente"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudComponenteVO, oldCrudComponenteVO, CrudComponenteVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudComponenteVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(13L);
				bitacoraCamniosVO.setValorOriginal(newCrudComponenteVO.getCodigoComponente());
				bitacoraCamniosVO.setValorFinal(newCrudComponenteVO.getNombreComponente());
				bitacoraCamniosVO.setCreadoPor(newCrudComponenteVO.getModificadoPor() != null ? newCrudComponenteVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudComponenteVO.getResultado() != null ? newCrudComponenteVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudComponenteVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudComponenteVO) != null ? m.invoke(oldCrudComponenteVO).toString() : "";
					String newVal = m.invoke(newCrudComponenteVO) != null ? m.invoke(newCrudComponenteVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudComponenteVO.getModificadoPor() != null ? newCrudComponenteVO.getModificadoPor() : 0L,
								newCrudComponenteVO.getIdComponente() != null ? newCrudComponenteVO.getIdComponente().toString() : newCrudComponenteVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudComponenteVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(17L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudComponenteVO.getModificadoPor() != null ? newCrudComponenteVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudComponenteVO.getIdComponente() != null ? newCrudComponenteVO.getIdComponente().toString() : newCrudComponenteVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudComponenteVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(17L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudComponenteVO.getModificadoPor() != null ? newCrudComponenteVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudComponenteVO.getIdComponente() != null ? newCrudComponenteVO.getIdComponente().toString() : newCrudComponenteVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> compararTipo(CrudTipoIngresoVO newCrudTipoIngresoVO,
			CrudTipoIngresoVO oldCrudTipoIngresoVO, String oldEstatus) {
//		-Tipo ingreso: Representación de : Store Procedure: ABC_TIPO_INGRESO y Trigger: TR_BITAC_TIPO_INGRESO
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudTipoIngresoVO.settIngrCod(newCrudTipoIngresoVO.gettIngrCod().toUpperCase());
		newCrudTipoIngresoVO.settIngrNombre(newCrudTipoIngresoVO.gettIngrNombre().toUpperCase());
		final long componente = 22L;
		
		filtro.add(new BitComponente(2L, "gettIngrCod"));
		filtro.add(new BitComponente(3L, "gettIngrNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudTipoIngresoVO, oldCrudTipoIngresoVO, CrudTipoIngresoVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudTipoIngresoVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(1L);
				bitacoraCamniosVO.setValorOriginal(newCrudTipoIngresoVO.gettIngrCod());
				bitacoraCamniosVO.setValorFinal(newCrudTipoIngresoVO.gettIngrNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudTipoIngresoVO.getModificadoPor() != null ? newCrudTipoIngresoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudTipoIngresoVO.getResultado() != null ? newCrudTipoIngresoVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudTipoIngresoVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudTipoIngresoVO) != null ? m.invoke(oldCrudTipoIngresoVO).toString() : "";
					String newVal = m.invoke(newCrudTipoIngresoVO) != null ? m.invoke(newCrudTipoIngresoVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudTipoIngresoVO.getModificadoPor() != null ? newCrudTipoIngresoVO.getModificadoPor() : 0L,
								newCrudTipoIngresoVO.gettIngrId() != null ? newCrudTipoIngresoVO.gettIngrId().toString() : newCrudTipoIngresoVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudTipoIngresoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(4L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudTipoIngresoVO.getModificadoPor() != null ? newCrudTipoIngresoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudTipoIngresoVO.gettIngrId() != null ? newCrudTipoIngresoVO.gettIngrId().toString() : newCrudTipoIngresoVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudTipoIngresoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(4L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudTipoIngresoVO.getModificadoPor() != null ? newCrudTipoIngresoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudTipoIngresoVO.gettIngrId() != null ? newCrudTipoIngresoVO.gettIngrId().toString() : newCrudTipoIngresoVO.getResultado());
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
