package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudBancoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudConceptoPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDenominacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEntidadPagosVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * Clase que representa la Administración de Pagos:
 * 	-Denominaciones: Representación de : Store Procedure: ABC_DENOMINACIONES y Trigger: TR_BITAC_DENOMINACION
 *  -Concepto de pago: Representación de : Store Procedure: ABC_CONCEPTO_PAGOS y Trigger: TR_BITAC_CONCEP_PAGO
 *  -Entidades: Representación de : Store Procedure: ABC_ENTIDADES_PAGO y Trigger: TR_BITAC_ENTIDAD_PAGO
 *  -Bancos: Representación de : Store Procedure: ABC_BANCOS y Trigger: TR_BITAC_BANCOS
 */

@Service
public class BitAdminCatalogoPagosImpl implements BitAdminCatalogoPagos {

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararDenominacion(CrudDenominacionVO newCrudDenominacionVO,
			CrudDenominacionVO oldCrudDenominacionVO, String oldEstatus) {
		newCrudDenominacionVO.setDenominacionCodigo(newCrudDenominacionVO.getDenominacionCodigo().toUpperCase());
		newCrudDenominacionVO.setDenominacionNombre(newCrudDenominacionVO.getDenominacionNombre().toUpperCase());
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 21L;
		
		filtro.add(new BitComponente(2L, "getDenominacionCodigo"));
		filtro.add(new BitComponente(3L, "getDenominacionNombre"));
		filtro.add(new BitComponente(4L, "getDenominacionValor"));
		filtro.add(new BitComponente(5L, "getDenominacionTipo"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudDenominacionVO, oldCrudDenominacionVO, CrudDenominacionVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudDenominacionVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(1L);
				bitacoraCamniosVO.setValorOriginal(newCrudDenominacionVO.getDenominacionCodigo().toUpperCase());
				bitacoraCamniosVO.setValorFinal(newCrudDenominacionVO.getDenominacionNombre().toUpperCase());
				bitacoraCamniosVO.setCreadoPor(newCrudDenominacionVO.getModificadoPor() != null ? newCrudDenominacionVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudDenominacionVO.getResultado() != null ? newCrudDenominacionVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudDenominacionVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudDenominacionVO) != null ? m.invoke(oldCrudDenominacionVO).toString() : "";
					String newVal = m.invoke(newCrudDenominacionVO) != null ? m.invoke(newCrudDenominacionVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudDenominacionVO.getModificadoPor() != null ? newCrudDenominacionVO.getModificadoPor() : 0L,
								newCrudDenominacionVO.getDenominacionId() != null ? newCrudDenominacionVO.getDenominacionId().toString() : newCrudDenominacionVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudDenominacionVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(6L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudDenominacionVO.getModificadoPor() != null ? newCrudDenominacionVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudDenominacionVO.getDenominacionId() != null ? newCrudDenominacionVO.getDenominacionId().toString() : newCrudDenominacionVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudDenominacionVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(6L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudDenominacionVO.getModificadoPor() != null ? newCrudDenominacionVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudDenominacionVO.getDenominacionId() != null ? newCrudDenominacionVO.getDenominacionId().toString() : newCrudDenominacionVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> compararConceptodePago(CrudConceptoPagoVO newCrudConceptoPagoVO,
			CrudConceptoPagoVO oldCrudConceptoPagoVO, String oldEstatus) {
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudConceptoPagoVO.setConceptoCodigo(newCrudConceptoPagoVO.getConceptoCodigo().toUpperCase());
		newCrudConceptoPagoVO.setConceptoNombre(newCrudConceptoPagoVO.getConceptoNombre().toUpperCase());
		final long componente = 21L;
		
		filtro.add(new BitComponente(8L, "getConceptoId"));
		filtro.add(new BitComponente(9L, "getConceptoCodigo"));
		filtro.add(new BitComponente(10L, "getConceptoNombre"));
		filtro.add(new BitComponente(11L, "getConceptoValor"));
		filtro.add(new BitComponente(12L, "getConceptoDias"));
		filtro.add(new BitComponente(13L, "getConceptoDescuento"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudConceptoPagoVO, oldCrudConceptoPagoVO, CrudConceptoPagoVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudConceptoPagoVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(7L);
				bitacoraCamniosVO.setValorOriginal(newCrudConceptoPagoVO.getConceptoCodigo());
				bitacoraCamniosVO.setValorFinal(newCrudConceptoPagoVO.getConceptoNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudConceptoPagoVO.getModificadoPor() != null ? newCrudConceptoPagoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudConceptoPagoVO.getResultado() != null ? newCrudConceptoPagoVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudConceptoPagoVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudConceptoPagoVO) != null ? m.invoke(oldCrudConceptoPagoVO).toString() : "";
					String newVal = m.invoke(newCrudConceptoPagoVO) != null ? m.invoke(newCrudConceptoPagoVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudConceptoPagoVO.getModificadoPor() != null ? newCrudConceptoPagoVO.getModificadoPor() : 0L,
						newCrudConceptoPagoVO.getConceptoId() != null ? newCrudConceptoPagoVO.getConceptoId().toString() : newCrudConceptoPagoVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudConceptoPagoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(14L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudConceptoPagoVO.getModificadoPor() != null ? newCrudConceptoPagoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudConceptoPagoVO.getConceptoId() != null ? newCrudConceptoPagoVO.getConceptoId().toString() : newCrudConceptoPagoVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudConceptoPagoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(14L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudConceptoPagoVO.getModificadoPor() != null ? newCrudConceptoPagoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudConceptoPagoVO.getConceptoId() != null ? newCrudConceptoPagoVO.getConceptoId().toString() : newCrudConceptoPagoVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> compararEntidad(CrudEntidadPagosVO newCrudEntidadPagosVO,
			CrudEntidadPagosVO oldCrudEntidadPagosVO, String oldEstatus) {
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudEntidadPagosVO.setEntidadCodigo(newCrudEntidadPagosVO.getEntidadCodigo().toUpperCase());
		newCrudEntidadPagosVO.setEntidadNombre(newCrudEntidadPagosVO.getEntidadNombre().toUpperCase());
		final long componente = 21L;
		
		filtro.add(new BitComponente(16L, "getEntidadCodigo"));
		filtro.add(new BitComponente(17L, "getEntidadNombre"));
		
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudEntidadPagosVO, oldCrudEntidadPagosVO, CrudEntidadPagosVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudEntidadPagosVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(15L);
				bitacoraCamniosVO.setValorOriginal(newCrudEntidadPagosVO.getEntidadCodigo());
				bitacoraCamniosVO.setValorFinal(newCrudEntidadPagosVO.getEntidadNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudEntidadPagosVO.getModificadoPor() != null ? newCrudEntidadPagosVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudEntidadPagosVO.getResultado() != null ? newCrudEntidadPagosVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudEntidadPagosVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudEntidadPagosVO) != null ? m.invoke(oldCrudEntidadPagosVO).toString() : "";
					String newVal = m.invoke(newCrudEntidadPagosVO) != null ? m.invoke(newCrudEntidadPagosVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudEntidadPagosVO.getModificadoPor() != null ? newCrudEntidadPagosVO.getModificadoPor() : 0L,
								newCrudEntidadPagosVO.getEntidadId() != null ? newCrudEntidadPagosVO.getEntidadId().toString() : newCrudEntidadPagosVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudEntidadPagosVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(18L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudEntidadPagosVO.getModificadoPor() != null ? newCrudEntidadPagosVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudEntidadPagosVO.getEntidadId() != null ? newCrudEntidadPagosVO.getEntidadId().toString() : newCrudEntidadPagosVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudEntidadPagosVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(18L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudEntidadPagosVO.getModificadoPor() != null ? newCrudEntidadPagosVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudEntidadPagosVO.getEntidadId() != null ? newCrudEntidadPagosVO.getEntidadId().toString() : newCrudEntidadPagosVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> compararBanco(CrudBancoVO newCrudBancoVO, CrudBancoVO oldCrudBancoVO,
			String oldEstatus) {
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudBancoVO.setBancoCodigo(newCrudBancoVO.getBancoCodigo().toUpperCase());
		newCrudBancoVO.setBancoNombre(newCrudBancoVO.getBancoNombre().toUpperCase());
		final long componente = 21L;
		
		filtro.add(new BitComponente(20L, "getBancoCodigo"));
		filtro.add(new BitComponente(21L, "getBancoNombre"));
		
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudBancoVO, oldCrudBancoVO, CrudBancoVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudBancoVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(19L);
				bitacoraCamniosVO.setValorOriginal(newCrudBancoVO.getBancoCodigo());
				bitacoraCamniosVO.setValorFinal(newCrudBancoVO.getBancoNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudBancoVO.getModificadoPor() != null ? newCrudBancoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudBancoVO.getResultado() != null ? newCrudBancoVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudBancoVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudBancoVO) != null ? m.invoke(oldCrudBancoVO).toString() : "";
					String newVal = m.invoke(newCrudBancoVO) != null ? m.invoke(newCrudBancoVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudBancoVO.getModificadoPor() != null ? newCrudBancoVO.getModificadoPor() : 0L,
						newCrudBancoVO.getBancoId() != null ? newCrudBancoVO.getBancoId().toString() : newCrudBancoVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudBancoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(22L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudBancoVO.getModificadoPor() != null ? newCrudBancoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudBancoVO.getBancoId() != null ? newCrudBancoVO.getBancoId().toString() : newCrudBancoVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudBancoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(22L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudBancoVO.getModificadoPor() != null ? newCrudBancoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudBancoVO.getBancoId() != null ? newCrudBancoVO.getBancoId().toString() : newCrudBancoVO.getResultado());
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
