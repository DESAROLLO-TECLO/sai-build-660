package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoAlarmaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoEmpleadoVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * Clase que representa la Administración de Agentes:
 * 	-Tipo Alarma: Representación de : Store Procedure: ABC_TIPO_ALARMAS
 * 	-Tipo Empleado: Representación de : Store Procedure: ABC_EMPLEADO_TIPO
 * 
 */

@Service
public class BitAdminCatalogoAgentesImpl implements BitAdminCatalogoAgentes {

	//Representación de SP: ABC_TIPO_ALARMAS
	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararTipoAlarma(CrudTipoAlarmaVO newCrudTipoAlarmaVO,
			CrudTipoAlarmaVO oldCrudTipoAlarmaVO, String estatusOld) {
		newCrudTipoAlarmaVO.setAlarmaCod(newCrudTipoAlarmaVO.getAlarmaCod().toUpperCase());
		newCrudTipoAlarmaVO.setAlarmaNombre(newCrudTipoAlarmaVO.getAlarmaNombre().toUpperCase());
		
		final long componente = 16L;
		
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		filtro.add(new BitComponente(6L,"getAlarmaCod"));
		filtro.add(new BitComponente(7L,"getAlarmaNombre"));
		
		ArrayList<BitacoraCambiosVO> listbitacoraCambiosVO = new ArrayList<>();
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudTipoAlarmaVO, oldCrudTipoAlarmaVO, CrudTipoAlarmaVO.class, BitComponente.convertToList(filtro));
		
		try{
			//Incluido en el SP: ABC_TIPO_ALARMAS
			if(estatusOld.equals("")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(5L);
				bitacoraCambiosVO.setValorOriginal(newCrudTipoAlarmaVO.getAlarmaCod());
				bitacoraCambiosVO.setValorFinal(newCrudTipoAlarmaVO.getAlarmaNombre());
				bitacoraCambiosVO.setCreadoPor(newCrudTipoAlarmaVO.getModificadoPor() != null ? newCrudTipoAlarmaVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudTipoAlarmaVO.getResultado() != null ? newCrudTipoAlarmaVO.getResultado() : "0");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listbitacoraCambiosVO.add(bitacoraCambiosVO);
			}else{
				//Incluido en el trigger de la tabla TIPO_ALARMAS: TR_BITAC_TIPO_ALARMAS
				if(!newCrudTipoAlarmaVO.getOperacion().equals("E")){
					for(Method m : listaCambios){
						String oldVal = m.invoke(oldCrudTipoAlarmaVO) != null ? m.invoke(oldCrudTipoAlarmaVO).toString() : "";
						String newVal = m.invoke(newCrudTipoAlarmaVO) != null ? m.invoke(newCrudTipoAlarmaVO).toString() : "";
						
						listbitacoraCambiosVO.add(new BitacoraCambiosVO(
								ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
								componente,
								BitComponente.findByParametro(filtro, m.getName()).getComponente(),
								oldVal,
								newVal,
								newCrudTipoAlarmaVO.getModificadoPor() != null ? newCrudTipoAlarmaVO.getModificadoPor() : 0L,
								newCrudTipoAlarmaVO.getAlarmaId() != null ? newCrudTipoAlarmaVO.getAlarmaId().toString() : newCrudTipoAlarmaVO.getResultado().toString(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));
					}
				}
				if(!estatusOld.equals("A") && !newCrudTipoAlarmaVO.getOperacion().equals("E")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(8L);
					bitacoraCambiosVO.setValorOriginal(estatusOld);
					bitacoraCambiosVO.setValorFinal("A");
					bitacoraCambiosVO.setCreadoPor(newCrudTipoAlarmaVO.getModificadoPor() != null ? newCrudTipoAlarmaVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudTipoAlarmaVO.getAlarmaId() != null ? newCrudTipoAlarmaVO.getAlarmaId().toString() : 
						newCrudTipoAlarmaVO.getResultado() != null ? newCrudTipoAlarmaVO.getResultado().toString() : "0");
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listbitacoraCambiosVO.add(bitacoraCambiosVO);
				}
				if(newCrudTipoAlarmaVO.getOperacion().equals("E")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(8L);
					bitacoraCambiosVO.setValorOriginal(estatusOld);
					bitacoraCambiosVO.setValorFinal("E");
					bitacoraCambiosVO.setCreadoPor(newCrudTipoAlarmaVO.getModificadoPor() != null ? newCrudTipoAlarmaVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudTipoAlarmaVO.getAlarmaId() != null ? newCrudTipoAlarmaVO.getAlarmaId().toString() : 
						newCrudTipoAlarmaVO.getResultado() != null ? newCrudTipoAlarmaVO.getResultado().toString() : "0");
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listbitacoraCambiosVO.add(bitacoraCambiosVO);
				}
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listbitacoraCambiosVO;
		}
		
	}

	//Represetación del SP: ABC_EMPLEADO_TIPO
	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararTipoEmpleado(CrudTipoEmpleadoVO newCrudTipoEmpleadoVO,
			CrudTipoEmpleadoVO oldCrudTipoEmpleadoVO, String oldEstatus) {
		
		newCrudTipoEmpleadoVO.setEmpTipCodigo(newCrudTipoEmpleadoVO.getEmpTipCodigo().toUpperCase());
		newCrudTipoEmpleadoVO.setEmpTipNombre(newCrudTipoEmpleadoVO.getEmpTipNombre().toUpperCase());
		
		final long componente = 16L;
		
		ArrayList<BitacoraCambiosVO> listbitacoraCambiosVO = new ArrayList<>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		filtro.add(new BitComponente(2L, "getEmpTipCodigo"));
		filtro.add(new BitComponente(3L, "getEmpTipNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudTipoEmpleadoVO, oldCrudTipoEmpleadoVO, CrudTipoEmpleadoVO.class, BitComponente.convertToList(filtro));
		
		try{
		//Dentro del SP: ABC_EMPLEADO_TIPO
		if(oldEstatus.equals("")){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(componente);
			bitacoraCambiosVO.setConceptoId(1L);
			bitacoraCambiosVO.setValorOriginal(newCrudTipoEmpleadoVO.getEmpTipCodigo());
			bitacoraCambiosVO.setValorFinal(newCrudTipoEmpleadoVO.getEmpTipNombre());
			bitacoraCambiosVO.setCreadoPor(newCrudTipoEmpleadoVO.getModificadoPor() != null ? newCrudTipoEmpleadoVO.getModificadoPor() : 0L);
			bitacoraCambiosVO.setIdRegistro(newCrudTipoEmpleadoVO.getResultado() != null ? newCrudTipoEmpleadoVO.getResultado() : "0");
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			listbitacoraCambiosVO.add(bitacoraCambiosVO);
		}else{
			//Denttro del Tigger de la tabla Empleado_Tipo: TR_BITAC_EMPLEADO_TIPO
			if(!newCrudTipoEmpleadoVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudTipoEmpleadoVO) != null ? m.invoke(oldCrudTipoEmpleadoVO).toString() : "";
					String newVal = m.invoke(newCrudTipoEmpleadoVO) != null ? m.invoke(newCrudTipoEmpleadoVO).toString() : "";
					
					listbitacoraCambiosVO.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
							componente,
							BitComponente.findByParametro(filtro, m.getName()).getComponente(),
							oldVal,
							newVal,
							newCrudTipoEmpleadoVO.getModificadoPor() != null ? newCrudTipoEmpleadoVO.getModificadoPor() : 0L,
							newCrudTipoEmpleadoVO.getEmpTipId() != null ? newCrudTipoEmpleadoVO.getEmpTipId().toString() : 
								newCrudTipoEmpleadoVO.getResultado() != null ? newCrudTipoEmpleadoVO.toString() : "0",
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));
				}
			}
			if(oldEstatus.equals("E") && !newCrudTipoEmpleadoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(4L);
				bitacoraCambiosVO.setValorOriginal(oldEstatus);
				bitacoraCambiosVO.setValorFinal("A");
				bitacoraCambiosVO.setCreadoPor(newCrudTipoEmpleadoVO.getModificadoPor() != null ? newCrudTipoEmpleadoVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudTipoEmpleadoVO.getEmpTipId() != null ? newCrudTipoEmpleadoVO.getEmpTipId().toString() : 
							newCrudTipoEmpleadoVO.getResultado() != null ? newCrudTipoEmpleadoVO.toString() : "0");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listbitacoraCambiosVO.add(bitacoraCambiosVO);
			}
			if(newCrudTipoEmpleadoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(4L);
				bitacoraCambiosVO.setValorOriginal(oldEstatus);
				bitacoraCambiosVO.setValorFinal("E");
				bitacoraCambiosVO.setCreadoPor(newCrudTipoEmpleadoVO.getModificadoPor() != null ? newCrudTipoEmpleadoVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudTipoEmpleadoVO.getEmpTipId() != null ? newCrudTipoEmpleadoVO.getEmpTipId().toString() : 
							newCrudTipoEmpleadoVO.getResultado() != null ? newCrudTipoEmpleadoVO.toString() : "0");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listbitacoraCambiosVO.add(bitacoraCambiosVO);
			}
		}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listbitacoraCambiosVO;
		}
	}
}
