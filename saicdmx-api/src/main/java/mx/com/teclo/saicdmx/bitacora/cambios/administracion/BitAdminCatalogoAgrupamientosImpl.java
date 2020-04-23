package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudAgrupamientoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDelegacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEstadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudRegionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudSectorVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoAlarmaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoEmpleadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudUnidadeTerritorialVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * Clase que representa la Administración de Agrupammientos y los SP para cada metodo:
 * 	
 * 	-Abc_Agrupamientos 
 *	-ABC_ESTADOS
 *	-ABC_DELEGACIONES
 *	-ABC_SECTORES
 *	-ABC_REGIONES
 *	-ABC_SECTORES
 *	-ABC_UNIDADES
 * 
 */

@Service
public class BitAdminCatalogoAgrupamientosImpl implements BitAdminCatalogoAgrupamientos {

	//Representación de SP: Abc_Agrupamientos
	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararTipoAgrupamiento(CrudAgrupamientoVO newCrudAgrupamientoVO,
			CrudAgrupamientoVO oldCrudAgrupamientoVO, String oldEstatus) {
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		newCrudAgrupamientoVO.setAgrupacionCodigo(newCrudAgrupamientoVO.getAgrupacionCodigo().toUpperCase());
		newCrudAgrupamientoVO.setAgrupacionNombre(newCrudAgrupamientoVO.getAgrupacionNombre().toUpperCase());
		
		final long componente = 17L;
		
		filtro.add(new BitComponente(30L, "getAgrupacionCodigo"));
		filtro.add(new BitComponente(31L, "getAgrupacionNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudAgrupamientoVO, oldCrudAgrupamientoVO, CrudAgrupamientoVO.class, BitComponente.convertToList(filtro));

		try{
			if(oldEstatus.equals("")){
				//Dentro del SP: Abc_Agrupamientos
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(29L);
				bitacoraCambiosVO.setValorOriginal(newCrudAgrupamientoVO.getAgrupacionCodigo());
				bitacoraCambiosVO.setValorFinal(newCrudAgrupamientoVO.getAgrupacionNombre());
				bitacoraCambiosVO.setCreadoPor(newCrudAgrupamientoVO.getModificadoPor() != null ? newCrudAgrupamientoVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudAgrupamientoVO.getResultado() != null ? newCrudAgrupamientoVO.getResultado() : "");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}else{
				//Dentro del trigger de la tabla Agruoamientos: TR_BITAC_AGRUPAMIENTOS
				if(!newCrudAgrupamientoVO.getOperacion().equals("E")){
					for(Method m : listaCambios){
						String oldVal = m.invoke(oldCrudAgrupamientoVO) != null ? m.invoke(oldCrudAgrupamientoVO).toString() : "";
						String newVal = m.invoke(newCrudAgrupamientoVO) != null ? m.invoke(newCrudAgrupamientoVO).toString() : "";
						
						listBitacoraCambiosVO.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
							componente,
							BitComponente.findByParametro(filtro, m.getName()).getComponente(),
							oldVal,
							newVal,
							newCrudAgrupamientoVO.getModificadoPor() != null ? newCrudAgrupamientoVO.getModificadoPor() : 0L,
							newCrudAgrupamientoVO.getAgrupacionId() != null ? newCrudAgrupamientoVO.getAgrupacionId().toString() : newCrudAgrupamientoVO.getResultado(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
					}
				}
				if(!oldEstatus.equals("A") && !newCrudAgrupamientoVO.getOperacion().equals("E")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(32L);
					bitacoraCambiosVO.setValorOriginal(oldEstatus);
					bitacoraCambiosVO.setValorFinal("A");
					bitacoraCambiosVO.setCreadoPor(newCrudAgrupamientoVO.getModificadoPor() != null ? newCrudAgrupamientoVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudAgrupamientoVO.getAgrupacionId() != null ? newCrudAgrupamientoVO.getAgrupacionId().toString() : newCrudAgrupamientoVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
				if(newCrudAgrupamientoVO.getOperacion().equals("E")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(32L);
					bitacoraCambiosVO.setValorOriginal(oldEstatus);
					bitacoraCambiosVO.setValorFinal("E");
					bitacoraCambiosVO.setCreadoPor(newCrudAgrupamientoVO.getModificadoPor() != null ? newCrudAgrupamientoVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudAgrupamientoVO.getAgrupacionId() != null ? newCrudAgrupamientoVO.getAgrupacionId().toString() : newCrudAgrupamientoVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}
	
	//Representación de SP: ABC_DELEGACIONES
	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararDelegaciones(CrudDelegacionVO newCrudDelegacionVO,
			CrudDelegacionVO oldCrudDelegacionVO, String oldEstatus) {
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<>();
		List<BitComponente> filtro =new ArrayList<BitComponente>();
		
		newCrudDelegacionVO.setDelCod(newCrudDelegacionVO.getDelCod().toUpperCase());
		newCrudDelegacionVO.setDelNombre(newCrudDelegacionVO.getDelNombre().toUpperCase());
		
		final long componente = 17L;
		
		filtro.add(new BitComponente(12L, "getEdoId"));
		filtro.add(new BitComponente(13L, "getDelId"));
		filtro.add(new BitComponente(14L, "getRegId"));
		filtro.add(new BitComponente(15L, "getDelCod"));
		filtro.add(new BitComponente(16L, "getDelNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudDelegacionVO, oldCrudDelegacionVO, CrudDelegacionVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("")){
				//Dentro de SP: ABC_DELEGACIONES
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(11L);
				bitacoraCambiosVO.setValorOriginal(newCrudDelegacionVO.getEdoId() + newCrudDelegacionVO.getDelCod().toUpperCase());
				bitacoraCambiosVO.setValorFinal(newCrudDelegacionVO.getDelNombre().toUpperCase());
				bitacoraCambiosVO.setCreadoPor(newCrudDelegacionVO.getModificadoPor() != null ? newCrudDelegacionVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudDelegacionVO.getResultado() != null ? newCrudDelegacionVO.getResultado() : "");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}else{
				//Representa el trigger dentro de la tabla Delegaciones: TR_BITAC_DELEGACIONES
				if(!newCrudDelegacionVO.getOperacion().equals("E")){
					for(Method m : listaCambios){
						String oldVal = m.invoke(oldCrudDelegacionVO) != null ? m.invoke(oldCrudDelegacionVO).toString() : "";
						String newVal = m.invoke(newCrudDelegacionVO) != null ? m.invoke(newCrudDelegacionVO).toString() : "";
						
						listBitacoraCambiosVO.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
							componente,
							BitComponente.findByParametro(filtro, m.getName()).getComponente(),
							oldCrudDelegacionVO.getEdoId() + oldVal,
							newVal,
							newCrudDelegacionVO.getModificadoPor() != null ? newCrudDelegacionVO.getModificadoPor() : 0L,
							newCrudDelegacionVO.getDelId() != null ? newCrudDelegacionVO.getDelId().toString() : newCrudDelegacionVO.getResultado(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
					}
				}
				if(!oldEstatus.equals("A") && !newCrudDelegacionVO.getOperacion().equals("E")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(17L);
					bitacoraCambiosVO.setValorOriginal(oldCrudDelegacionVO.getEdoId() + oldEstatus);
					bitacoraCambiosVO.setValorFinal("A");
					bitacoraCambiosVO.setCreadoPor(newCrudDelegacionVO.getModificadoPor() != null ? newCrudDelegacionVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudDelegacionVO.getDelId() != null ? newCrudDelegacionVO.getDelId().toString() : newCrudDelegacionVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
				if(newCrudDelegacionVO.getOperacion().equals("E")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(17L);
					bitacoraCambiosVO.setValorOriginal(oldCrudDelegacionVO.getEdoId() + oldEstatus);
					bitacoraCambiosVO.setValorFinal("E");
					bitacoraCambiosVO.setCreadoPor(newCrudDelegacionVO.getModificadoPor() != null ? newCrudDelegacionVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudDelegacionVO.getDelId() != null ? newCrudDelegacionVO.getDelId().toString() : newCrudDelegacionVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}

	//Representación de SP: ABC_ESTADOS
	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararEstado(CrudEstadoVO newCrudEstadoVO, CrudEstadoVO oldCrudEstadoVO,
			String oldEstatus) {
		
		newCrudEstadoVO.setEdoCod(newCrudEstadoVO.getEdoCod().toUpperCase());
		newCrudEstadoVO.setEdoNombre(newCrudEstadoVO.getEdoNombre().toUpperCase());
		
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		final long componente = 17L;
		
		filtro.add(new BitComponente(2L, "getEdoCod"));
		filtro.add(new BitComponente(3L, "getEdoNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudEstadoVO, oldCrudEstadoVO, CrudEstadoVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("")){
				//Repreesenat el SP: ABC_ESTADOS
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(1L);
				bitacoraCambiosVO.setValorOriginal(newCrudEstadoVO.getEdoCod());
				bitacoraCambiosVO.setValorFinal(newCrudEstadoVO.getEdoNombre());
				bitacoraCambiosVO.setCreadoPor(newCrudEstadoVO.getModificadoPor() != null ? newCrudEstadoVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudEstadoVO.getResultado() != null ? newCrudEstadoVO.getResultado() : "");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}else{
				//Representa El trigger dentro de la tabla Estados: TR_BITAC_ESTADOS
				if(!newCrudEstadoVO.getOperacion().equals("E")){
					for(Method m : listaCambios){
						String oldVal = m.invoke(oldCrudEstadoVO) != null ? m.invoke(oldCrudEstadoVO).toString() : "";
						String newVal = m.invoke(newCrudEstadoVO) != null ? m.invoke(newCrudEstadoVO).toString() : "";
						
						listBitacoraCambiosVO.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
							componente,
							BitComponente.findByParametro(filtro, m.getName()).getComponente(),
							oldVal,
							newVal,
							newCrudEstadoVO.getModificadoPor() != null ? newCrudEstadoVO.getModificadoPor() : 0L,
							newCrudEstadoVO.getEdoId() != null ? newCrudEstadoVO.getEdoId().toString() : newCrudEstadoVO.getResultado(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
						
					}
				}
				if(!oldEstatus.equals("A") && !newCrudEstadoVO.getOperacion().equals("E")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(4L);
					bitacoraCambiosVO.setValorOriginal(oldEstatus);
					bitacoraCambiosVO.setValorFinal("A");
					bitacoraCambiosVO.setCreadoPor(newCrudEstadoVO.getModificadoPor() != null ? newCrudEstadoVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudEstadoVO.getEdoId() != null ? newCrudEstadoVO.getEdoId().toString() : newCrudEstadoVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
				if(newCrudEstadoVO.getOperacion().equals("E")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(4L);
					bitacoraCambiosVO.setValorOriginal(oldEstatus);
					bitacoraCambiosVO.setValorFinal("E");
					bitacoraCambiosVO.setCreadoPor(newCrudEstadoVO.getModificadoPor() != null ? newCrudEstadoVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudEstadoVO.getEdoId() != null ? newCrudEstadoVO.getEdoId().toString() : newCrudEstadoVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}

	//Represntación de SP: ABC_REGIONES
	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> comaprarRegione(CrudRegionVO newCrudRegionVO, CrudRegionVO oldCrudRegionVO,
			String oldEstatus) {
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		newCrudRegionVO.setRegionCodigo(newCrudRegionVO.getRegionCodigo().toUpperCase());
		newCrudRegionVO.setRegionNombre(newCrudRegionVO.getRegionNombre().toUpperCase());
		
		final long componente = 17L;
		
		filtro.add(new BitComponente(6L, "getEstadoId"));
		filtro.add(new BitComponente(7L, "getRegionId"));
		filtro.add(new BitComponente(8L, "getRegionCodigo"));
		filtro.add(new BitComponente(9L, "getRegionNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudRegionVO, oldCrudRegionVO, CrudRegionVO.class, BitComponente.convertToList(filtro));
		try{
			if(oldEstatus.equals("")){
				//Representa el SP: ABC_REGIONES
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(5L);
				bitacoraCambiosVO.setValorOriginal(newCrudRegionVO.getRegionCodigo().toUpperCase());
				bitacoraCambiosVO.setValorFinal(newCrudRegionVO.getRegionNombre().toUpperCase());
				bitacoraCambiosVO.setCreadoPor(newCrudRegionVO.getModificadoPor());
				bitacoraCambiosVO.setIdRegistro(newCrudRegionVO.getRegionId().toString());
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}else if (!newCrudRegionVO.getOperacion().equals("E")){
				//Representa el trigger de a tabla Regiones: TR_BITAC_REGIONES
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudRegionVO) != null ? m.invoke(oldCrudRegionVO).toString() : "";
					String newVal = m.invoke(newCrudRegionVO) != null ? m.invoke(newCrudRegionVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						BitComponente.findByParametro(filtro, m.getName()).getComponente() != 6 ? newCrudRegionVO.getEstadoId() + oldVal : oldVal,
						newVal,
						newCrudRegionVO.getModificadoPor() != null ? newCrudRegionVO.getModificadoPor() : 0L,
						newCrudRegionVO.getRegionId() != null ? newCrudRegionVO.getRegionId().toString() : newCrudRegionVO.getResultado(), 
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
					
				}
				if(!oldEstatus.equals("A")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(10L);
					bitacoraCambiosVO.setValorOriginal(newCrudRegionVO.getEstadoId() + oldEstatus);
					bitacoraCambiosVO.setValorFinal("A");
					bitacoraCambiosVO.setCreadoPor(newCrudRegionVO.getModificadoPor());
					bitacoraCambiosVO.setIdRegistro(newCrudRegionVO.getRegionId() != null ? newCrudRegionVO.getRegionId().toString() : newCrudRegionVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
			}
			else if(newCrudRegionVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(10L);
				bitacoraCambiosVO.setValorOriginal(newCrudRegionVO.getEstadoId() + oldEstatus);
				bitacoraCambiosVO.setValorFinal("E");
				bitacoraCambiosVO.setCreadoPor(newCrudRegionVO.getModificadoPor());
				bitacoraCambiosVO.setIdRegistro(newCrudRegionVO.getRegionId() != null ? newCrudRegionVO.getRegionId().toString() : newCrudRegionVO.getResultado());
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}

	//Representación de SP: ABC_SECTORES
	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararSector(CrudSectorVO newCrudSectorVO, CrudSectorVO oldCrudSectorVO, 
			SectorDTO sectorSinDel, SectorDTO sectorConDel){
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		newCrudSectorVO.setSectorCodigo(newCrudSectorVO.getSectorCodigo().toUpperCase());
		newCrudSectorVO.setSectorNombre(newCrudSectorVO.getSectorNombre().toUpperCase());
		
		final long componente = 17L;
		
		filtro.add(new BitComponente(24L, "getSectorCodigo"));
		filtro.add(new BitComponente(25L, "getSectorNombre"));
		filtro.add(new BitComponente(26L, "getEstadoId"));
		filtro.add(new BitComponente(27L, "getDelegacionId"));
		filtro.add(new BitComponente(26L, "getEstadoId"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudSectorVO, oldCrudSectorVO, CrudSectorVO.class, BitComponente.convertToList(filtro));
		
		try{
			switch(newCrudSectorVO.getOperacion()){
			case "A":
				if(sectorSinDel==null){
					//Representa la parte del SP: ABC_SECTORES
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(23L);
					bitacoraCambiosVO.setValorOriginal(newCrudSectorVO.getSectorCodigo());
					bitacoraCambiosVO.setValorFinal(newCrudSectorVO.getSectorNombre());
					bitacoraCambiosVO.setCreadoPor(newCrudSectorVO.getModificadoPor());
					bitacoraCambiosVO.setIdRegistro(newCrudSectorVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}else if(sectorConDel!=null){
					//Representa el trigger de la tabla Sectores: TR_BITAC_SECTORES
					if(!ComparaUtils.comparaCadenas(newCrudSectorVO.getSectorNombre(), sectorConDel.getSecCod())){
						BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
						bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
						bitacoraCambiosVO.setComponenteId(componente);
						bitacoraCambiosVO.setConceptoId(25L);
						bitacoraCambiosVO.setValorOriginal(sectorConDel.getSecNombre());
						bitacoraCambiosVO.setValorFinal(newCrudSectorVO.getSectorNombre());
						bitacoraCambiosVO.setCreadoPor(newCrudSectorVO.getModificadoPor());
						bitacoraCambiosVO.setIdRegistro(newCrudSectorVO.getSectorId() != null ? newCrudSectorVO.getSectorId().toString() : newCrudSectorVO.getResultado());
						bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
						listBitacoraCambiosVO.add(bitacoraCambiosVO);
					}
					if(!sectorConDel.getSecStatus().equals("A")){
						BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
						bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
						bitacoraCambiosVO.setComponenteId(componente);
						bitacoraCambiosVO.setConceptoId(28L);
						bitacoraCambiosVO.setValorOriginal(sectorConDel.getSecStatus());
						bitacoraCambiosVO.setValorFinal("A");
						bitacoraCambiosVO.setCreadoPor(newCrudSectorVO.getModificadoPor());
						bitacoraCambiosVO.setIdRegistro(newCrudSectorVO.getSectorId() != null ? newCrudSectorVO.getSectorId().toString() : newCrudSectorVO.getResultado());
						bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
						listBitacoraCambiosVO.add(bitacoraCambiosVO);
					}
				}
				break;
			case "M":
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudSectorVO) != null ? m.invoke(oldCrudSectorVO).toString() : "";
					String newVal = m.invoke(newCrudSectorVO) != null ? m.invoke(newCrudSectorVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudSectorVO.getModificadoPor() != null ? newCrudSectorVO.getModificadoPor() : 0L,
						newCrudSectorVO.getSectorId() != null ? newCrudSectorVO.getSectorId().toString() : newCrudSectorVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
				if(!sectorSinDel.getSecStatus().equals("A")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(28L);
					bitacoraCambiosVO.setValorOriginal(sectorSinDel.getSecStatus());
					bitacoraCambiosVO.setValorFinal("A");
					bitacoraCambiosVO.setCreadoPor(newCrudSectorVO.getModificadoPor());
					bitacoraCambiosVO.setIdRegistro(newCrudSectorVO.getSectorId() != null ? newCrudSectorVO.getSectorId().toString() : newCrudSectorVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
				break;
			case "E":
				if(!sectorConDel.getSecStatus().equals("E")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(28L);
					bitacoraCambiosVO.setValorOriginal(sectorConDel.getSecStatus());
					bitacoraCambiosVO.setValorFinal("E");
					bitacoraCambiosVO.setCreadoPor(newCrudSectorVO.getModificadoPor());
					bitacoraCambiosVO.setIdRegistro(newCrudSectorVO.getSectorId() != null ? newCrudSectorVO.getSectorId().toString() : newCrudSectorVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
					break;
				}
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
		
	}

	//Representación de SP: ABC_UNIDADES
	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararUnidadTeritorial(CrudUnidadeTerritorialVO newCrudUnidadeTerritorialVO,
			CrudUnidadeTerritorialVO oldCrudUnidadeTerritorialVO) {
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		newCrudUnidadeTerritorialVO.setUtCod(newCrudUnidadeTerritorialVO.getUtCod().toUpperCase());
		newCrudUnidadeTerritorialVO.setUtNombre(newCrudUnidadeTerritorialVO.getUtNombre().toUpperCase());
		
		final long componente = 17L;
		
		filtro.add(new BitComponente(20L, "getUtCod"));
		filtro.add(new BitComponente(21L, "getUtNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudUnidadeTerritorialVO, oldCrudUnidadeTerritorialVO, CrudUnidadeTerritorialVO.class, BitComponente.convertToList(filtro));
		
		try{
			//Representa la parte del SP: ABC_UNIDADES
			if(oldCrudUnidadeTerritorialVO.getUtCod()==null && !newCrudUnidadeTerritorialVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(18L);
				bitacoraCambiosVO.setValorOriginal(newCrudUnidadeTerritorialVO.getUtCod());
				bitacoraCambiosVO.setValorFinal(newCrudUnidadeTerritorialVO.getUtNombre());
				bitacoraCambiosVO.setCreadoPor(newCrudUnidadeTerritorialVO.getModificadoPor());
				bitacoraCambiosVO.setIdRegistro(newCrudUnidadeTerritorialVO.getResultado());
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}
			if(!newCrudUnidadeTerritorialVO.getOperacion().equals("E")){
			//representa lo del trigger en la tabla Unidad_territorial: TR_BITAC_UT
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudUnidadeTerritorialVO) != null ? m.invoke(oldCrudUnidadeTerritorialVO).toString() : "";
					String newVal = m.invoke(newCrudUnidadeTerritorialVO) != null ? m.invoke(newCrudUnidadeTerritorialVO).toString() : ""; 
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudUnidadeTerritorialVO.getModificadoPor() != null ? newCrudUnidadeTerritorialVO.getModificadoPor() : 0L,
						newCrudUnidadeTerritorialVO.getUtId().getUtId() != null ? newCrudUnidadeTerritorialVO.getUtId().getUtId().toString() : newCrudUnidadeTerritorialVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
				if(!ComparaUtils.comparaCadenas(newCrudUnidadeTerritorialVO.getUtId().getSecId(), oldCrudUnidadeTerritorialVO.getUtId().getSecId())){
					BitacoraCambiosVO bitacoraCambiosVO2 = new BitacoraCambiosVO();
					bitacoraCambiosVO2.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO2.setComponenteId(componente);
					bitacoraCambiosVO2.setConceptoId(19L);
					bitacoraCambiosVO2.setValorOriginal(oldCrudUnidadeTerritorialVO.getUtId().getSecId().toString());
					bitacoraCambiosVO2.setValorFinal(newCrudUnidadeTerritorialVO.getUtId().getSecId().toString());
					bitacoraCambiosVO2.setCreadoPor(newCrudUnidadeTerritorialVO.getModificadoPor());
					bitacoraCambiosVO2.setIdRegistro(newCrudUnidadeTerritorialVO.getUtId().getUtId() != null ? newCrudUnidadeTerritorialVO.getUtId().getUtId().toString() : newCrudUnidadeTerritorialVO.getResultado());
					bitacoraCambiosVO2.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO2);
				}
				if(!oldCrudUnidadeTerritorialVO.getUtStatus().equals("A")){
					BitacoraCambiosVO bitacoraCambiosVO2 = new BitacoraCambiosVO();
					bitacoraCambiosVO2.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO2.setComponenteId(componente);
					bitacoraCambiosVO2.setConceptoId(22L);
					bitacoraCambiosVO2.setValorOriginal(oldCrudUnidadeTerritorialVO.getUtStatus());
					bitacoraCambiosVO2.setValorFinal("A");
					bitacoraCambiosVO2.setCreadoPor(newCrudUnidadeTerritorialVO.getModificadoPor());
					bitacoraCambiosVO2.setIdRegistro(newCrudUnidadeTerritorialVO.getUtId().getUtId() != null ? newCrudUnidadeTerritorialVO.getUtId().getUtId().toString() : newCrudUnidadeTerritorialVO.getResultado());
					bitacoraCambiosVO2.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO2);
				}
			}else if(newCrudUnidadeTerritorialVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(22L);
				bitacoraCambiosVO.setValorOriginal(oldCrudUnidadeTerritorialVO.getUtStatus());
				bitacoraCambiosVO.setValorFinal("E");
				bitacoraCambiosVO.setCreadoPor(newCrudUnidadeTerritorialVO.getModificadoPor());
				bitacoraCambiosVO.setIdRegistro(newCrudUnidadeTerritorialVO.getUtId().getUtId() != null ? newCrudUnidadeTerritorialVO.getUtId().getUtId().toString() : newCrudUnidadeTerritorialVO.getResultado());
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
