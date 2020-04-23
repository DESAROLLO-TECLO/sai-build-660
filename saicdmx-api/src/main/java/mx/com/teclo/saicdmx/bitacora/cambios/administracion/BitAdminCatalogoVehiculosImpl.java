package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoColorVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoMarcaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoModeloVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoResponsableVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoSubTipoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoTipoVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * Clase que representa la Administración de Pagos:
 * 	-Marca: Representación de : Store Procedure: ABC_VEHICULO_MARCA y Trigger: TR_BITAC_VEH_MARCA
 *  -Modelo: Representación de : Store Procedure: ABC_VEHICULO_MODELO y Trigger: TR_BITAC_VEH_MODELO
 *  -Subtipo: Representación de : Store Procedure: ABC_VEHICULO_SUBTIPO y Trigger: TR_BITAC_VEHICULO_SUBTIPO
 *  -Tipo: Representación de : Store Procedure: ABC_VEHICULO_TIPO y Trigger: TR_BITAC_VEH_TIPO
 *  -Color: Representación de : Store Procedure: ABC_VEHICULO_COLOR y Trigger: TR_BITAC_VEHICULO_COLOR
 *  -Responsable: Representación de : Store Procedure: ABC_RESPONSABLE_VEHICULO y Trigger: TR_BITAC_RESP_VEHICULO
 *  -Tipo Licencia: Representación de : Store Procedure: ABC_TIPO_LICENCIA y Trigger: TR_BITAC_TIPO_LICENCIA
 */

@Service
public class BitAdminCatalogoVehiculosImpl implements BitAdminCatalogoVehiculos {

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararMarca(CrudVehiculoMarcaVO newCrudVehiculoMarcaVO,
			CrudVehiculoMarcaVO oldCrudVehiculoMarcaVO, String oldEstatus) {
		newCrudVehiculoMarcaVO.setvMarCod(newCrudVehiculoMarcaVO.getvMarCod().toUpperCase());
		newCrudVehiculoMarcaVO.setvMarNombre(newCrudVehiculoMarcaVO.getvMarNombre().toUpperCase());
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 19L;
		
		filtro.add(new BitComponente(2L, "getvMarCod"));
		filtro.add(new BitComponente(3L, "getvMarNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudVehiculoMarcaVO, oldCrudVehiculoMarcaVO, CrudVehiculoMarcaVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudVehiculoMarcaVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(1L);
				bitacoraCamniosVO.setValorOriginal(newCrudVehiculoMarcaVO.getvMarCod());
				bitacoraCamniosVO.setValorFinal(newCrudVehiculoMarcaVO.getvMarNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudVehiculoMarcaVO.getModificadoPor() != null ? newCrudVehiculoMarcaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudVehiculoMarcaVO.getResultado() != null ? newCrudVehiculoMarcaVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudVehiculoMarcaVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudVehiculoMarcaVO) != null ? m.invoke(oldCrudVehiculoMarcaVO).toString() : "";
					String newVal = m.invoke(newCrudVehiculoMarcaVO) != null ? m.invoke(newCrudVehiculoMarcaVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudVehiculoMarcaVO.getModificadoPor() != null ? newCrudVehiculoMarcaVO.getModificadoPor() : 0L,
						newCrudVehiculoMarcaVO.getvMarId() != null ? newCrudVehiculoMarcaVO.getvMarId().toString() : newCrudVehiculoMarcaVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudVehiculoMarcaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(4L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudVehiculoMarcaVO.getModificadoPor() != null ? newCrudVehiculoMarcaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudVehiculoMarcaVO.getvMarId() != null ? newCrudVehiculoMarcaVO.getvMarId().toString() : newCrudVehiculoMarcaVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudVehiculoMarcaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(4L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudVehiculoMarcaVO.getModificadoPor() != null ? newCrudVehiculoMarcaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudVehiculoMarcaVO.getvMarId() != null ? newCrudVehiculoMarcaVO.getvMarId().toString() : newCrudVehiculoMarcaVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> compararModelo(CrudVehiculoModeloVO newCrudVehiculoModeloVO,
			CrudVehiculoModeloVO oldCrudVehiculoModeloVO) {
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		newCrudVehiculoModeloVO.setvModCod(newCrudVehiculoModeloVO.getvModCod().toUpperCase());
		newCrudVehiculoModeloVO.setvModNombre(newCrudVehiculoModeloVO.getvModNombre().toUpperCase());
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 19L;
		
		filtro.add(new BitComponente(7L, "getvModCod"));
		filtro.add(new BitComponente(8L, "getvModNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudVehiculoModeloVO, oldCrudVehiculoModeloVO, CrudVehiculoModeloVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldCrudVehiculoModeloVO.getvModCod()==null && newCrudVehiculoModeloVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(5L);
				bitacoraCamniosVO.setValorOriginal(newCrudVehiculoModeloVO.getResultado() +""+ newCrudVehiculoModeloVO.getvModCod());
				bitacoraCamniosVO.setValorFinal(newCrudVehiculoModeloVO.getvModNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudVehiculoModeloVO.getModificadoPor() != null ? newCrudVehiculoModeloVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudVehiculoModeloVO.getResultado() != null ? newCrudVehiculoModeloVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			else{ 
				if(ComparaUtils.comparaCadenas(newCrudVehiculoModeloVO.getVehiculoMarca().getvMarId(), oldCrudVehiculoModeloVO.getVehiculoMarca().getvMarId()) && !newCrudVehiculoModeloVO.getOperacion().equals("E")){
					for(Method m : listaCambios){
						String oldVal = m.invoke(oldCrudVehiculoModeloVO) != null ? m.invoke(oldCrudVehiculoModeloVO).toString() : "";
						String newVal = m.invoke(newCrudVehiculoModeloVO) != null ? m.invoke(newCrudVehiculoModeloVO).toString() : "";
						
						listBitacoraCambiosVO.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
							componente,
							BitComponente.findByParametro(filtro, m.getName()).getComponente(),
							oldVal,
							newVal,
							newCrudVehiculoModeloVO.getModificadoPor() != null ? newCrudVehiculoModeloVO.getModificadoPor() : 0L,
							newCrudVehiculoModeloVO.getvModId() != null ? newCrudVehiculoModeloVO.getvModId().getvModId().toString() : newCrudVehiculoModeloVO.getResultado(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
					}
				}
				if(ComparaUtils.comparaCadenas(newCrudVehiculoModeloVO.getVehiculoMarca().getvMarId(), oldCrudVehiculoModeloVO.getVehiculoMarca().getvMarId()) && oldCrudVehiculoModeloVO.getvModStatus().equals("E") && !newCrudVehiculoModeloVO.getOperacion().equals("E")){
					BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
					bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCamniosVO.setComponenteId(componente);
					bitacoraCamniosVO.setConceptoId(9L);
					bitacoraCamniosVO.setValorOriginal(oldCrudVehiculoModeloVO.getvModStatus());
					bitacoraCamniosVO.setValorFinal("A");
					bitacoraCamniosVO.setCreadoPor(newCrudVehiculoModeloVO.getModificadoPor() != null ? newCrudVehiculoModeloVO.getModificadoPor() : 0L);
					bitacoraCamniosVO.setIdRegistro(newCrudVehiculoModeloVO.getvModId() != null ? newCrudVehiculoModeloVO.getvModId().getvModId().toString() : newCrudVehiculoModeloVO.getResultado());
					bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCamniosVO);
				}
				if(newCrudVehiculoModeloVO.getOperacion().equals("E")){
					BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
					bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCamniosVO.setComponenteId(componente);
					bitacoraCamniosVO.setConceptoId(9L);
					bitacoraCamniosVO.setValorOriginal(oldCrudVehiculoModeloVO.getvModStatus());
					bitacoraCamniosVO.setValorFinal("E");
					bitacoraCamniosVO.setCreadoPor(newCrudVehiculoModeloVO.getModificadoPor() != null ? newCrudVehiculoModeloVO.getModificadoPor() : 0L);
					bitacoraCamniosVO.setIdRegistro(newCrudVehiculoModeloVO.getvModId() != null ? newCrudVehiculoModeloVO.getvModId().getvModId().toString() : newCrudVehiculoModeloVO.getResultado());
					bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCamniosVO);
				}
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararSubtipo(CrudVehiculoSubTipoVO newCrudVehiculoSubTipoVO,
			CrudVehiculoSubTipoVO oldCrudVehiculoSubTipoVO, String oldEstatus) {
		newCrudVehiculoSubTipoVO.setvSubTipoCodigo(newCrudVehiculoSubTipoVO.getvSubTipoCodigo().toUpperCase());
		newCrudVehiculoSubTipoVO.setvSubTipoNombre(newCrudVehiculoSubTipoVO.getvSubTipoNombre().toUpperCase());
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 19L;
		
		filtro.add(new BitComponente(17L, "getvSubTipoCodigo"));
		filtro.add(new BitComponente(18L, "getvSubTipoNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudVehiculoSubTipoVO, oldCrudVehiculoSubTipoVO, CrudVehiculoSubTipoVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudVehiculoSubTipoVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(16L);
				bitacoraCamniosVO.setValorOriginal(newCrudVehiculoSubTipoVO.getvSubTipoCodigo());
				bitacoraCamniosVO.setValorFinal(newCrudVehiculoSubTipoVO.getvSubTipoNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudVehiculoSubTipoVO.getModificadoPor() != null ? newCrudVehiculoSubTipoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudVehiculoSubTipoVO.getResultado() != null ? newCrudVehiculoSubTipoVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudVehiculoSubTipoVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudVehiculoSubTipoVO) != null ? m.invoke(oldCrudVehiculoSubTipoVO).toString() : "";
					String newVal = m.invoke(newCrudVehiculoSubTipoVO) != null ? m.invoke(newCrudVehiculoSubTipoVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudVehiculoSubTipoVO.getModificadoPor() != null ? newCrudVehiculoSubTipoVO.getModificadoPor() : 0L,
						newCrudVehiculoSubTipoVO.getvSubTipoId() != null ? newCrudVehiculoSubTipoVO.getvSubTipoId().toString() : newCrudVehiculoSubTipoVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudVehiculoSubTipoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(19L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudVehiculoSubTipoVO.getModificadoPor() != null ? newCrudVehiculoSubTipoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudVehiculoSubTipoVO.getvSubTipoId() != null ? newCrudVehiculoSubTipoVO.getvSubTipoId().toString() : newCrudVehiculoSubTipoVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudVehiculoSubTipoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(6L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudVehiculoSubTipoVO.getModificadoPor() != null ? newCrudVehiculoSubTipoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudVehiculoSubTipoVO.getvSubTipoId() != null ? newCrudVehiculoSubTipoVO.getvSubTipoId().toString() : newCrudVehiculoSubTipoVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> compararTipo(CrudVehiculoTipoVO newCrudVehiculoTipoVO,
			CrudVehiculoTipoVO oldCrudVehiculoTipoVO, String oldEstatus) {
		newCrudVehiculoTipoVO.setVipoCod(newCrudVehiculoTipoVO.getVipoCod().toUpperCase());
		newCrudVehiculoTipoVO.setvTipoNombre(newCrudVehiculoTipoVO.getvTipoNombre().toUpperCase());
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 19L;
		
		filtro.add(new BitComponente(11L, "getVipoCod"));
//		filtro.add(new BitComponente(12L, "getvSubtipo"));
		filtro.add(new BitComponente(13L, "getvTipoNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudVehiculoTipoVO, oldCrudVehiculoTipoVO, CrudVehiculoTipoVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudVehiculoTipoVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(10L);
				bitacoraCamniosVO.setValorOriginal(newCrudVehiculoTipoVO.getVipoCod());
				bitacoraCamniosVO.setValorFinal(newCrudVehiculoTipoVO.getvTipoNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudVehiculoTipoVO.getModificadoPor() != null ? newCrudVehiculoTipoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudVehiculoTipoVO.getResultado() != null ? newCrudVehiculoTipoVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudVehiculoTipoVO.getOperacion().equals("E") 
					&& ComparaUtils.comparaCadenas(newCrudVehiculoTipoVO.getvSubtipo().getvSubTipoId(), oldCrudVehiculoTipoVO.getvSubtipo().getvSubTipoId())){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudVehiculoTipoVO) != null ? m.invoke(oldCrudVehiculoTipoVO).toString() : "";
					String newVal = m.invoke(newCrudVehiculoTipoVO) != null ? m.invoke(newCrudVehiculoTipoVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudVehiculoTipoVO.getModificadoPor() != null ? newCrudVehiculoTipoVO.getModificadoPor() : 0L,
						newCrudVehiculoTipoVO.getvTipoId() != null ? newCrudVehiculoTipoVO.getvTipoId().toString() : newCrudVehiculoTipoVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudVehiculoTipoVO.getOperacion().equals("E") && 
					ComparaUtils.comparaCadenas(newCrudVehiculoTipoVO.getvSubtipo().getvSubTipoId(), oldCrudVehiculoTipoVO.getvSubtipo().getvSubTipoId())){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(15L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudVehiculoTipoVO.getModificadoPor() != null ? newCrudVehiculoTipoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudVehiculoTipoVO.getvTipoId() != null ? newCrudVehiculoTipoVO.getvTipoId().toString() : newCrudVehiculoTipoVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudVehiculoTipoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(15L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudVehiculoTipoVO.getModificadoPor() != null ? newCrudVehiculoTipoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudVehiculoTipoVO.getvTipoId() != null ? newCrudVehiculoTipoVO.getvTipoId().toString() : newCrudVehiculoTipoVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> compararColor(CrudVehiculoColorVO newCrudVehiculoColorVO,
			CrudVehiculoColorVO oldCrudVehiculoColorVO, String oldEstatus) {
		newCrudVehiculoColorVO.setvColorCod(newCrudVehiculoColorVO.getvColorCod().toUpperCase());
		newCrudVehiculoColorVO.setvColorNombre(newCrudVehiculoColorVO.getvColorNombre().toUpperCase());
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		long creadoPor = newCrudVehiculoColorVO.getModificadoPor() != null ? newCrudVehiculoColorVO.getModificadoPor() : 0L;
		String idRegistro = newCrudVehiculoColorVO.getvColorId() != null ? newCrudVehiculoColorVO.getvColorId().toString() :
			newCrudVehiculoColorVO.getResultado() != null ? newCrudVehiculoColorVO.getResultado() : "";
		
		final long componente = 19L;
		
		filtro.add(new BitComponente(21L, "getvColorCod"));
		filtro.add(new BitComponente(22L, "getvColorNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudVehiculoColorVO, oldCrudVehiculoColorVO, CrudVehiculoColorVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudVehiculoColorVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(20L);
				bitacoraCamniosVO.setValorOriginal(newCrudVehiculoColorVO.getvColorCod());
				bitacoraCamniosVO.setValorFinal(newCrudVehiculoColorVO.getvColorNombre());
				bitacoraCamniosVO.setCreadoPor(creadoPor);
				bitacoraCamniosVO.setIdRegistro(idRegistro);
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudVehiculoColorVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudVehiculoColorVO) != null ? m.invoke(oldCrudVehiculoColorVO).toString() : "";
					String newVal = m.invoke(newCrudVehiculoColorVO) != null ? m.invoke(newCrudVehiculoColorVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						creadoPor,
						idRegistro,
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudVehiculoColorVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(23L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(creadoPor);
				bitacoraCamniosVO.setIdRegistro(idRegistro);
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudVehiculoColorVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(23L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(creadoPor);
				bitacoraCamniosVO.setIdRegistro(idRegistro);
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
	public ArrayList<BitacoraCambiosVO> compararResponsable(CrudVehiculoResponsableVO newCrudVehiculoResponsableVO,
			CrudVehiculoResponsableVO oldCrudVehiculoResponsableVO, String oldEstatus) {
		newCrudVehiculoResponsableVO.setrVehCod(newCrudVehiculoResponsableVO.getrVehCod().toUpperCase());
		newCrudVehiculoResponsableVO.setrVehNombre(newCrudVehiculoResponsableVO.getrVehNombre().toUpperCase());
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		long creadoPor = newCrudVehiculoResponsableVO.getModificadoPor() != null ? newCrudVehiculoResponsableVO.getModificadoPor() : 0L;
		String idRegistro = newCrudVehiculoResponsableVO.getrVehId() != null ? newCrudVehiculoResponsableVO.getrVehId().toString() :
			newCrudVehiculoResponsableVO.getResultado() != null ? newCrudVehiculoResponsableVO.getResultado() : "";
		
		final long componente = 19L;
		
		filtro.add(new BitComponente(25L, "getrVehCod"));
		filtro.add(new BitComponente(26L, "getrVehNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudVehiculoResponsableVO, oldCrudVehiculoResponsableVO, CrudVehiculoResponsableVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudVehiculoResponsableVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(24L);
				bitacoraCamniosVO.setValorOriginal(newCrudVehiculoResponsableVO.getrVehCod());
				bitacoraCamniosVO.setValorFinal(newCrudVehiculoResponsableVO.getrVehNombre());
				bitacoraCamniosVO.setCreadoPor(creadoPor);
				bitacoraCamniosVO.setIdRegistro(idRegistro);
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudVehiculoResponsableVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudVehiculoResponsableVO) != null ? m.invoke(oldCrudVehiculoResponsableVO).toString() : "";
					String newVal = m.invoke(newCrudVehiculoResponsableVO) != null ? m.invoke(newCrudVehiculoResponsableVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						creadoPor,
						idRegistro,
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudVehiculoResponsableVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(27L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(creadoPor);
				bitacoraCamniosVO.setIdRegistro(idRegistro);
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudVehiculoResponsableVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(27L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(creadoPor);
				bitacoraCamniosVO.setIdRegistro(idRegistro);
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
	public ArrayList<BitacoraCambiosVO> compararTipoLicencia(CrudTipoLicenciaVO newCrudTipoLicenciaVO,
			CrudTipoLicenciaVO oldCrudTipoLicenciaVO, String oldEstatus) {
		newCrudTipoLicenciaVO.setTipoLCod(newCrudTipoLicenciaVO.getTipoLCod().toUpperCase());
		newCrudTipoLicenciaVO.setTipoLNombre(newCrudTipoLicenciaVO.getTipoLNombre().toUpperCase());
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		long creadoPor = newCrudTipoLicenciaVO.getModificadoPor() != null ? newCrudTipoLicenciaVO.getModificadoPor() : 0L;
		String idRegistro = newCrudTipoLicenciaVO.getTipoLId() != null ? newCrudTipoLicenciaVO.getTipoLId().toString() :
			newCrudTipoLicenciaVO.getResultado() != null ? newCrudTipoLicenciaVO.getResultado() : "";
		
		final long componente = 19L;
		
		filtro.add(new BitComponente(29L, "getTipoLCod"));
		filtro.add(new BitComponente(30L, "getTipoLNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudTipoLicenciaVO, oldCrudTipoLicenciaVO, CrudTipoLicenciaVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudTipoLicenciaVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(28L);
				bitacoraCamniosVO.setValorOriginal(newCrudTipoLicenciaVO.getTipoLCod());
				bitacoraCamniosVO.setValorFinal(newCrudTipoLicenciaVO.getTipoLNombre());
				bitacoraCamniosVO.setCreadoPor(creadoPor);
				bitacoraCamniosVO.setIdRegistro(idRegistro);
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudTipoLicenciaVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudTipoLicenciaVO) != null ? m.invoke(oldCrudTipoLicenciaVO).toString() : "";
					String newVal = m.invoke(newCrudTipoLicenciaVO) != null ? m.invoke(newCrudTipoLicenciaVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						creadoPor,
						idRegistro,
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudTipoLicenciaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(31L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(creadoPor);
				bitacoraCamniosVO.setIdRegistro(idRegistro);
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudTipoLicenciaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(31L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(creadoPor);
				bitacoraCamniosVO.setIdRegistro(idRegistro);
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
