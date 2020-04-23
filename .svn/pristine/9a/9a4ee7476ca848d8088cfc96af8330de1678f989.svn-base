package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudArticuloVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudArticulosHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCategoriaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCausalesVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudProgramaVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * Clase que representa la Administración de Articulos:
 * 	-Categoria: Representación de : Store Procedure: ABC_CATEGORIAS y Trigger: TR_BITAC_CATEGORIAS
 * 	-Causales: Representación de : Store Procedure: ABC_CAUSALES y Trigger: TR_BITAC_CAUSALES
 *  -Articulos: Representación de : Store Procedure: ABC_ARTICULOS y Trigger: TR_BITAC_ARTICULOS
 *  -Programas: Representación de : Store Procedure: ABC_PROGRAMAS y Trigger: TR_BITAC_PROGRAMAS
 * 
 */

@Service
public class BitAdminCatalogoArticuloImpl implements BitAdminCatalogoArticulo {

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararCategoria(CrudCategoriaVO newCrudCategoriaVO,
			CrudCategoriaVO oldCrudCategoriaVO, String oldEstatus) {
//		-Categoria: Representación de : Store Procedure: ABC_CATEGORIAS y Trigger: TR_BITAC_CATEGORIAS
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		newCrudCategoriaVO.setCategoriaCodigo(newCrudCategoriaVO.getCategoriaCodigo().toUpperCase());
		newCrudCategoriaVO.setCategoriaDesc(newCrudCategoriaVO.getCategoriaDesc().toUpperCase());
		
		final long componente = 23L;
		
		filtro.add(new BitComponente(6L, "getCategoriaCodigo"));
		filtro.add(new BitComponente(7L, "getCategoriaDesc"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudCategoriaVO, oldCrudCategoriaVO, CrudCategoriaVO.class, BitComponente.convertToList(filtro));		
		
		try{
			if(oldEstatus.equals("") && !newCrudCategoriaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(5L);
				bitacoraCambiosVO.setValorOriginal(newCrudCategoriaVO.getCategoriaCodigo());
				bitacoraCambiosVO.setValorFinal(newCrudCategoriaVO.getCategoriaDesc());
				bitacoraCambiosVO.setCreadoPor(newCrudCategoriaVO.getModificadoPor() !=null ? newCrudCategoriaVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudCategoriaVO.getResultado() != null ? newCrudCategoriaVO.getResultado() : "");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}
			if(!oldEstatus.equals("") && !newCrudCategoriaVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudCategoriaVO) != null ? m.invoke(oldCrudCategoriaVO).toString() : "";
					String newVal = m.invoke(newCrudCategoriaVO) != null ? m.invoke(newCrudCategoriaVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudCategoriaVO.getModificadoPor() != null ? newCrudCategoriaVO.getModificadoPor() : 0L,
						newCrudCategoriaVO.getCategoriaId() != null ? newCrudCategoriaVO.getCategoriaId().toString() : newCrudCategoriaVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
				if(!oldEstatus.equals("A")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(8L);
					bitacoraCambiosVO.setValorOriginal(oldEstatus);
					bitacoraCambiosVO.setValorFinal("A");
					bitacoraCambiosVO.setCreadoPor(newCrudCategoriaVO.getModificadoPor() !=null ? newCrudCategoriaVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudCategoriaVO.getResultado() != null ? newCrudCategoriaVO.getResultado() : "");
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
			}
			if(newCrudCategoriaVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(8L);
				bitacoraCambiosVO.setValorOriginal(oldEstatus);
				bitacoraCambiosVO.setValorFinal("E");
				bitacoraCambiosVO.setCreadoPor(newCrudCategoriaVO.getModificadoPor() !=null ? newCrudCategoriaVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudCategoriaVO.getResultado() != null ? newCrudCategoriaVO.getResultado() : "");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararCausal(CrudCausalesVO newCrudCausalesVO,
			CrudCausalesVO oldCrudCausalesVO, String oldEstatus) {
//		-Causales: Representación de : Store Procedure: ABC_CAUSALES y Trigger: TR_BITAC_CAUSALES
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 23L;
		
		newCrudCausalesVO.setCausalCodigo(newCrudCausalesVO.getCausalCodigo().toUpperCase());
		newCrudCausalesVO.setCausalNombre(newCrudCausalesVO.getCausalNombre().toUpperCase());
		
		filtro.add(new BitComponente(2L, "getCausalCodigo"));
		filtro.add(new BitComponente(3L, "getCausalNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudCausalesVO, oldCrudCausalesVO, CrudCausalesVO.class, BitComponente.convertToList(filtro));
		
		try{
			if(oldEstatus.equals("") && !newCrudCausalesVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(1L);
				bitacoraCambiosVO.setValorOriginal(newCrudCausalesVO.getCausalCodigo());
				bitacoraCambiosVO.setValorFinal(newCrudCausalesVO.getCausalNombre());
				bitacoraCambiosVO.setCreadoPor(newCrudCausalesVO.getModificadoPor() != null ? newCrudCausalesVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudCausalesVO.getResultado());
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}
			else if(!oldEstatus.equals("") && !newCrudCausalesVO.getOperacion().equals("E")){
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudCausalesVO) != null ? m.invoke(oldCrudCausalesVO).toString() : "";
					String newVal = m.invoke(newCrudCausalesVO) != null ? m.invoke(newCrudCausalesVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudCausalesVO.getModificadoPor() != null ? newCrudCausalesVO.getModificadoPor() : 0L,
						newCrudCausalesVO.getCausalId() != null ? newCrudCausalesVO.getCausalId().toString() : newCrudCausalesVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
				if(!oldEstatus.equals("A")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(4L);
					bitacoraCambiosVO.setValorOriginal(oldEstatus);
					bitacoraCambiosVO.setValorFinal("A");
					bitacoraCambiosVO.setCreadoPor(newCrudCausalesVO.getModificadoPor() != null ? newCrudCausalesVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudCausalesVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
			}else if(newCrudCausalesVO.getOperacion().equals("E")){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(4L);
				bitacoraCambiosVO.setValorOriginal(oldEstatus);
				bitacoraCambiosVO.setValorFinal("E");
				bitacoraCambiosVO.setCreadoPor(newCrudCausalesVO.getModificadoPor() != null ? newCrudCausalesVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudCausalesVO.getResultado());
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return listBitacoraCambiosVO;
		}
	}


	@SuppressWarnings("finally")
	@Override
	public ArrayList<BitacoraCambiosVO> compararArticulo(CrudArticuloVO newCrudArticuloVO,
			CrudArticuloVO oldCrudArticuloVO) {
//		-Articulos: Representación de : Store Procedure: ABC_ARTICULOS y Trigger: TR_BITAC_ARTICULOS
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		final long componente = 23L;
		
//		filtro.add(new BitComponente(14L, "getCategId"));
		filtro.add(new BitComponente(15L, "getArtMotivacion"));
		filtro.add(new BitComponente(16L, "getProgId"));
		filtro.add(new BitComponente(17L, "getCausalId"));
		filtro.add(new BitComponente(18L, "getArtNumero"));
		filtro.add(new BitComponente(19L, "getArtFraccion"));
		filtro.add(new BitComponente(20L, "getArtParrafo"));
		filtro.add(new BitComponente(21L, "getArtInciso"));
		filtro.add(new BitComponente(22L, "getArtDias"));
		filtro.add(new BitComponente(23L, "getArtDescuento"));
		filtro.add(new BitComponente(24L, "getArtPorcenDesc"));
		filtro.add(new BitComponente(25L, "getArtTipo"));
//		filtro.add(new BitComponente(26L, "getArtEstatus"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudArticuloVO, oldCrudArticuloVO, CrudArticuloVO.class, BitComponente.convertToList(filtro));
		
		try{
			switch(newCrudArticuloVO.getOperacion()){
			case "A":
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(13L);
				bitacoraCambiosVO.setValorOriginal(newCrudArticuloVO.getArtNumero()+" "+newCrudArticuloVO.getFraccion()+" "+newCrudArticuloVO.getArtParrafo());
				bitacoraCambiosVO.setValorFinal("");
				bitacoraCambiosVO.setCreadoPor(newCrudArticuloVO.getModificadoPor() != null ? newCrudArticuloVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudArticuloVO.getResultado());
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO);
				break;
			case "M":
				for(Method m: listaCambios){
					String oldVal = m.invoke(oldCrudArticuloVO) != null ? m.invoke(oldCrudArticuloVO).toString() : "";
					String newVal = m.invoke(newCrudArticuloVO) != null ? m.invoke(newCrudArticuloVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudArticuloVO.getModificadoPor() != null ? newCrudArticuloVO.getModificadoPor() : 0L,
						newCrudArticuloVO.getArtId() != null ? newCrudArticuloVO.getArtId().toString() : newCrudArticuloVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
				if(!newCrudArticuloVO.getArtEstatus().equals("A")){
					BitacoraCambiosVO bitacoraCambiosVO2 = new BitacoraCambiosVO();
					bitacoraCambiosVO2.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO2.setComponenteId(componente);
					bitacoraCambiosVO2.setConceptoId(26L);
					bitacoraCambiosVO2.setValorOriginal(oldCrudArticuloVO.getArtEstatus());
					bitacoraCambiosVO2.setValorFinal("A");
					bitacoraCambiosVO2.setCreadoPor(newCrudArticuloVO.getModificadoPor() != null ? newCrudArticuloVO.getModificadoPor() : 0L);
					bitacoraCambiosVO2.setIdRegistro(newCrudArticuloVO.getResultado());
					bitacoraCambiosVO2.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO2);
				}
				break;
			case "E":
				BitacoraCambiosVO bitacoraCambiosVO2 = new BitacoraCambiosVO();
				bitacoraCambiosVO2.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO2.setComponenteId(componente);
				bitacoraCambiosVO2.setConceptoId(26L);
				bitacoraCambiosVO2.setValorOriginal(oldCrudArticuloVO.getArtEstatus());
				bitacoraCambiosVO2.setValorFinal("E");
				bitacoraCambiosVO2.setCreadoPor(newCrudArticuloVO.getModificadoPor() != null ? newCrudArticuloVO.getModificadoPor() : 0L);
				bitacoraCambiosVO2.setIdRegistro(newCrudArticuloVO.getResultado());
				bitacoraCambiosVO2.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				listBitacoraCambiosVO.add(bitacoraCambiosVO2);
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
	public ArrayList<BitacoraCambiosVO> compararPrograma(CrudProgramaVO newCrudProgramaVO,
			CrudProgramaVO oldCrudProgramaVO, String oldEstatus) {
//		-Programas: Representación de : Store Procedure: ABC_PROGRAMAS y Trigger: TR_BITAC_PROGRAMAS
		ArrayList<BitacoraCambiosVO> listBitacoraCambiosVO = new ArrayList<BitacoraCambiosVO>();
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		newCrudProgramaVO.setProgramaCodigo(newCrudProgramaVO.getProgramaCodigo().toUpperCase());
		newCrudProgramaVO.setProgramaNombre(newCrudProgramaVO.getProgramaNombre().toUpperCase());
		final long componente = 23L;
		
		filtro.add(new BitComponente(10L, "getProgramaCodigo"));
		filtro.add(new BitComponente(11L, "getProgramaNombre"));
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newCrudProgramaVO, oldCrudProgramaVO, CrudProgramaVO.class, BitComponente.convertToList(filtro));
	
		try{
			switch(newCrudProgramaVO.getOperacion()){
			case "A":
				if(oldEstatus.equals("")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(9L);
					bitacoraCambiosVO.setValorOriginal(newCrudProgramaVO.getProgramaCodigo());
					bitacoraCambiosVO.setValorFinal(newCrudProgramaVO.getProgramaNombre());
					bitacoraCambiosVO.setCreadoPor(newCrudProgramaVO.getModificadoPor() != null ? newCrudProgramaVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudProgramaVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}else{
					for(Method m : listaCambios){
						String oldVal = m.invoke(oldCrudProgramaVO) != null ? m.invoke(oldCrudProgramaVO).toString() : "";
						String newVal = m.invoke(newCrudProgramaVO) != null ? m.invoke(newCrudProgramaVO).toString() : "";
						
						listBitacoraCambiosVO.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
							componente,
							BitComponente.findByParametro(filtro, m.getName()).getComponente(),
							oldVal,
							newVal,
							newCrudProgramaVO.getModificadoPor() != null ? newCrudProgramaVO.getModificadoPor() : 0L,
							newCrudProgramaVO.getProgramaId() != null ? newCrudProgramaVO.getProgramaId().toString() : newCrudProgramaVO.getResultado(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
					}
					if(!oldEstatus.equals("A")){
						BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
						bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
						bitacoraCambiosVO.setComponenteId(componente);
						bitacoraCambiosVO.setConceptoId(12L);
						bitacoraCambiosVO.setValorOriginal(oldEstatus);
						bitacoraCambiosVO.setValorFinal("A");
						bitacoraCambiosVO.setCreadoPor(newCrudProgramaVO.getModificadoPor() != null ? newCrudProgramaVO.getModificadoPor() : 0L);
						bitacoraCambiosVO.setIdRegistro(newCrudProgramaVO.getResultado());
						bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
						listBitacoraCambiosVO.add(bitacoraCambiosVO);
					}
				}
				break;
			case "M":
				for(Method m : listaCambios){
					String oldVal = m.invoke(oldCrudProgramaVO) != null ? m.invoke(oldCrudProgramaVO).toString() : "";
					String newVal = m.invoke(newCrudProgramaVO) != null ? m.invoke(newCrudProgramaVO).toString() : "";
					
					listBitacoraCambiosVO.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(),
						componente,
						BitComponente.findByParametro(filtro, m.getName()).getComponente(),
						oldVal,
						newVal,
						newCrudProgramaVO.getModificadoPor() != null ? newCrudProgramaVO.getModificadoPor() : 0L,
						newCrudProgramaVO.getProgramaId() != null ? newCrudProgramaVO.getProgramaId().toString() : newCrudProgramaVO.getResultado(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
				}
				if(!oldEstatus.equals("A")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(componente);
					bitacoraCambiosVO.setConceptoId(12L);
					bitacoraCambiosVO.setValorOriginal(oldEstatus);
					bitacoraCambiosVO.setValorFinal("A");
					bitacoraCambiosVO.setCreadoPor(newCrudProgramaVO.getModificadoPor() != null ? newCrudProgramaVO.getModificadoPor() : 0L);
					bitacoraCambiosVO.setIdRegistro(newCrudProgramaVO.getResultado());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					listBitacoraCambiosVO.add(bitacoraCambiosVO);
				}
				break;
			case "E":
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(componente);
				bitacoraCambiosVO.setConceptoId(12L);
				bitacoraCambiosVO.setValorOriginal(oldEstatus);
				bitacoraCambiosVO.setValorFinal("E");
				bitacoraCambiosVO.setCreadoPor(newCrudProgramaVO.getModificadoPor() != null ? newCrudProgramaVO.getModificadoPor() : 0L);
				bitacoraCambiosVO.setIdRegistro(newCrudProgramaVO.getResultado());
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

}

