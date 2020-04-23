package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudConcesionariaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudGruaStatusVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudGruaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTurnoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudZonaServicioVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * Clase que representa la Administración de Agentes:
 * 	-Concesionaria: Representación de : Store Procedure: ABC_CONCESIONARIAS y Trigger: TR_BITAC_CONCESIONARIA
 *  -Gruas: Representación de : Store Procedure: ABC_GRUAS y Trigger: TR_BITAC_GRUAS
 *  -Gruas estatus: Representación de : Store Procedure: ABC_GRUAS_STATUS y Trigger: TR_BITAC_GRUAS_STATUS
 * 	-Trunos: Representación de : Store Procedure: ABC_TURNOS y Trigger: TR_BITAC_TURNOS
 * 	-Zonas Servicio: Representación de : Store Procedure: ABC_ZONA_SERVICIO y Trigger: TR_BITAC_ZONA_SERVICIO
 */

@Service
public class BitAdminCatalogoGruasImpl implements BitAdminCatalogoGruas {

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararConcesionarias(CrudConcesionariaVO newCrudConcesionariaVO,
			CrudConcesionariaVO oldCrudConcesionariaVO, String oldEstatus) {
//		-Concesionaria: Representación de : Store Procedure: ABC_CONCESIONARIAS y Trigger: TR_BITAC_CONCESIONARIA
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudConcesionariaVO.setConcesionariaCodigo(newCrudConcesionariaVO.getConcesionariaCodigo().toUpperCase());
		newCrudConcesionariaVO.setConcesionariaNombre(newCrudConcesionariaVO.getConcesionariaNombre().toUpperCase());
		final long componente = 15L;
		
		filtro.add(new BitComponente(2L, "getConcesionariaCodigo"));
		filtro.add(new BitComponente(3L, "getConcesionariaPrefijo"));
		filtro.add(new BitComponente(5L, "getConcesionariaNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudConcesionariaVO, oldCrudConcesionariaVO, CrudConcesionariaVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudConcesionariaVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(1L);
				bitacoraCamniosVO.setValorOriginal(newCrudConcesionariaVO.getConcesionariaPrefijo());
				bitacoraCamniosVO.setValorFinal(newCrudConcesionariaVO.getConcesionariaNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudConcesionariaVO.getModificadoPor() != null ? newCrudConcesionariaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudConcesionariaVO.getResultado() != null ? newCrudConcesionariaVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudConcesionariaVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudConcesionariaVO) != null ? m.invoke(oldCrudConcesionariaVO).toString() : "";
					String newVal = m.invoke(newCrudConcesionariaVO) != null ? m.invoke(newCrudConcesionariaVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudConcesionariaVO.getModificadoPor() != null ? newCrudConcesionariaVO.getModificadoPor() : 0L,
						newCrudConcesionariaVO.getConcesionariaId() != null ? newCrudConcesionariaVO.getConcesionariaId().toString() : newCrudConcesionariaVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudConcesionariaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(6L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudConcesionariaVO.getModificadoPor() != null ? newCrudConcesionariaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudConcesionariaVO.getConcesionariaId() != null ? newCrudConcesionariaVO.getConcesionariaId().toString() : newCrudConcesionariaVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudConcesionariaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(6L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudConcesionariaVO.getModificadoPor() != null ? newCrudConcesionariaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudConcesionariaVO.getConcesionariaId() != null ? newCrudConcesionariaVO.getConcesionariaId().toString() : newCrudConcesionariaVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> compararGruas(CrudGruaVO newCrudGruaVO, CrudGruaVO oldCrudGruaVO,
			String oldEstatus) {
//		-Gruas: Representación de : Store Procedure: ABC_GRUAS y Trigger: TR_BITAC_GRUAS
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudGruaVO.setGruaCod(newCrudGruaVO.getGruaCod().toUpperCase());
		final long componente = 15L;
		
		filtro.add(new BitComponente(18L, "getGruaCod"));
//		filtro.add(new BitComponente(19L, "gteGrue_sms"));
		filtro.add(new BitComponente(20L, "getConseId"));
		filtro.add(new BitComponente(21L, "getgStatId"));
//		filtro.add(new BitComponente(22L, "getEstatus"));
//		filtro.add(new BitComponente(23L, "getTip_g_id"));
//		filtro.add(new BitComponente(24L, "getGtrua_cobra_arrastre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudGruaVO, oldCrudGruaVO, CrudGruaVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudGruaVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(17L);
				bitacoraCamniosVO.setValorOriginal("");
				bitacoraCamniosVO.setValorFinal(newCrudGruaVO.getGruaCod());
				bitacoraCamniosVO.setCreadoPor(newCrudGruaVO.getModificadoPor() != null ? newCrudGruaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudGruaVO.getResultado() != null ? newCrudGruaVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudGruaVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudGruaVO) != null ? m.invoke(oldCrudGruaVO).toString() : "";
					String newVal = m.invoke(newCrudGruaVO) != null ? m.invoke(newCrudGruaVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudGruaVO.getModificadoPor() != null ? newCrudGruaVO.getModificadoPor() : 0L,
						newCrudGruaVO.getGruaId() != null ? newCrudGruaVO.getGruaId().toString() : newCrudGruaVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
				if(oldEstatus.equals("E")){
					BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
					bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCamniosVO.setComponenteId(componente);
					bitacoraCamniosVO.setConceptoId(22L);
					bitacoraCamniosVO.setValorOriginal(oldEstatus);
					bitacoraCamniosVO.setValorFinal("A");
					bitacoraCamniosVO.setCreadoPor(newCrudGruaVO.getModificadoPor() != null ? newCrudGruaVO.getModificadoPor() : 0L);
					bitacoraCamniosVO.setIdRegistro(newCrudGruaVO.getGruaId() != null ? newCrudGruaVO.getGruaId().toString() : newCrudGruaVO.getResultado());
					bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCamniosVO);
				}
			}
			if(newCrudGruaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(22L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudGruaVO.getModificadoPor() != null ? newCrudGruaVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudGruaVO.getGruaId() != null ? newCrudGruaVO.getGruaId().toString() : newCrudGruaVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> comparaEstatusGruas(CrudGruaStatusVO newCrudGruaStatusVO,
			CrudGruaStatusVO oldCrudGruaStatusVO, String oldEstatus) {
//		-Gruas estatus: Representación de : Store Procedure: ABC_GRUAS_STATUS y Trigger: TR_BITAC_GRUAS_STATUS
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudGruaStatusVO.setGruaStatusCod(newCrudGruaStatusVO.getGruaStatusCod().toUpperCase());
		newCrudGruaStatusVO.setGruaStatusNombre(newCrudGruaStatusVO.getGruaStatusNombre().toUpperCase());
		final long componente = 15L;
		
		filtro.add(new BitComponente(8L, "getGruaStatusCod"));
		filtro.add(new BitComponente(9L, "getGruaStatusNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudGruaStatusVO, oldCrudGruaStatusVO, CrudGruaStatusVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudGruaStatusVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(7L);
				bitacoraCamniosVO.setValorOriginal(newCrudGruaStatusVO.getGruaStatusCod());
				bitacoraCamniosVO.setValorFinal(newCrudGruaStatusVO.getGruaStatusNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudGruaStatusVO.getModificadoPor() != null ? newCrudGruaStatusVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudGruaStatusVO.getResultado() != null ? newCrudGruaStatusVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudGruaStatusVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudGruaStatusVO) != null ? m.invoke(oldCrudGruaStatusVO).toString() : "";
					String newVal = m.invoke(newCrudGruaStatusVO) != null ? m.invoke(newCrudGruaStatusVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudGruaStatusVO.getModificadoPor() != null ? newCrudGruaStatusVO.getModificadoPor() : 0L,
						newCrudGruaStatusVO.getGruaStatusId() != null ? newCrudGruaStatusVO.getGruaStatusId().toString() : newCrudGruaStatusVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudGruaStatusVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(10L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudGruaStatusVO.getModificadoPor() != null ? newCrudGruaStatusVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudGruaStatusVO.getGruaStatusId() != null ? newCrudGruaStatusVO.getGruaStatusId().toString() : newCrudGruaStatusVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudGruaStatusVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(10L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudGruaStatusVO.getModificadoPor() != null ? newCrudGruaStatusVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudGruaStatusVO.getGruaStatusId() != null ? newCrudGruaStatusVO.getGruaStatusId().toString() : newCrudGruaStatusVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> comparaTurnos(CrudTurnoVO newCrudTurnoVO, CrudTurnoVO oldCrudTurnoVO,
			String oldEstatus) {
//		-Trunos: Representación de : Store Procedure: ABC_TURNOS y Trigger: TR_BITAC_TURNOS
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudTurnoVO.setTurnoCod(newCrudTurnoVO.getTurnoCod().toUpperCase());
		newCrudTurnoVO.setTurnoNombre(newCrudTurnoVO.getTurnoNombre().toUpperCase());
		
		final long componente = 15L;
		
		filtro.add(new BitComponente(12L, "getTurnoCod"));
		filtro.add(new BitComponente(13L, "getTurnoNombre"));
		filtro.add(new BitComponente(14L, "getFechaInicio"));
		filtro.add(new BitComponente(15L, "getFechaFin"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudTurnoVO, oldCrudTurnoVO, CrudTurnoVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudTurnoVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(11L);
				bitacoraCamniosVO.setValorOriginal(newCrudTurnoVO.getTurnoCod());
				bitacoraCamniosVO.setValorFinal(newCrudTurnoVO.getTurnoNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudTurnoVO.getModificadoPor() != null ? newCrudTurnoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudTurnoVO.getResultado() != null ? newCrudTurnoVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudTurnoVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudTurnoVO) != null ? m.invoke(oldCrudTurnoVO).toString() : "";
					String newVal = m.invoke(newCrudTurnoVO) != null ? m.invoke(newCrudTurnoVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudTurnoVO.getModificadoPor() != null ? newCrudTurnoVO.getModificadoPor() : 0L,
								newCrudTurnoVO.getTurnoId() != null ? newCrudTurnoVO.getTurnoId().toString() : newCrudTurnoVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudTurnoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(16L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudTurnoVO.getModificadoPor() != null ? newCrudTurnoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudTurnoVO.getTurnoId() != null ? newCrudTurnoVO.getTurnoId().toString() : newCrudTurnoVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudTurnoVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(16L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudTurnoVO.getModificadoPor() != null ? newCrudTurnoVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudTurnoVO.getTurnoId() != null ? newCrudTurnoVO.getTurnoId().toString() : newCrudTurnoVO.getResultado());
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
	public ArrayList<BitacoraCambiosVO> comparaCrudZonaServicio(CrudZonaServicioVO newCrudZonaServicioVO,
			CrudZonaServicioVO oldCrudZonaServicioVO, String oldEstatus) {
//		-Zonas Servicio: Representación de : Store Procedure: ABC_ZONA_SERVICIO y Trigger: TR_BITAC_ZONA_SERVICIO
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudZonaServicioVO.setZonaCod(newCrudZonaServicioVO.getZonaCod().toUpperCase());
		newCrudZonaServicioVO.setZonaNombre(newCrudZonaServicioVO.getZonaNombre().toUpperCase());
		
		final long componente = 15L;
		
		filtro.add(new BitComponente(26L, "getZonaCod"));
		filtro.add(new BitComponente(27L, "getZonaNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudZonaServicioVO, oldCrudZonaServicioVO, CrudZonaServicioVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && newCrudZonaServicioVO.getOperacion().equals("A")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(25L);
				bitacoraCamniosVO.setValorOriginal(newCrudZonaServicioVO.getZonaCod());
				bitacoraCamniosVO.setValorFinal(newCrudZonaServicioVO.getZonaNombre());
				bitacoraCamniosVO.setCreadoPor(newCrudZonaServicioVO.getModificadoPor() != null ? newCrudZonaServicioVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudZonaServicioVO.getResultado() != null ? newCrudZonaServicioVO.getResultado() : "");
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(!oldEstatus.equals("") && !newCrudZonaServicioVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudZonaServicioVO) != null ? m.invoke(oldCrudZonaServicioVO).toString() : "";
					String newVal = m.invoke(newCrudZonaServicioVO) != null ? m.invoke(newCrudZonaServicioVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudZonaServicioVO.getModificadoPor() != null ? newCrudZonaServicioVO.getModificadoPor() : 0L,
						newCrudZonaServicioVO.getZonaId() != null ? newCrudZonaServicioVO.getZonaId().toString() : newCrudZonaServicioVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
			}
			if(oldEstatus.equals("E") && !newCrudZonaServicioVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(28L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("A");
				bitacoraCamniosVO.setCreadoPor(newCrudZonaServicioVO.getModificadoPor() != null ? newCrudZonaServicioVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudZonaServicioVO.getZonaId() != null ? newCrudZonaServicioVO.getZonaId().toString() : newCrudZonaServicioVO.getResultado());
				bitacoraCamniosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCamniosVO);
			}
			if(newCrudZonaServicioVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCamniosVO = new BitacoraCambiosVO();
				bitacoraCamniosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCamniosVO.setComponenteId(componente);
				bitacoraCamniosVO.setConceptoId(28L);
				bitacoraCamniosVO.setValorOriginal(oldEstatus);
				bitacoraCamniosVO.setValorFinal("E");
				bitacoraCamniosVO.setCreadoPor(newCrudZonaServicioVO.getModificadoPor() != null ? newCrudZonaServicioVO.getModificadoPor() : 0L);
				bitacoraCamniosVO.setIdRegistro(newCrudZonaServicioVO.getZonaId() != null ? newCrudZonaServicioVO.getZonaId().toString() : newCrudZonaServicioVO.getResultado());
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
