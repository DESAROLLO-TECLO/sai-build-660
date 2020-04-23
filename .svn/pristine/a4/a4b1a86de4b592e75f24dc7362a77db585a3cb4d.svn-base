package mx.com.teclo.saicdmx.bitacora.cambios.ingresos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.ingresos.IngresosVO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ProcesoDeSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarSalidaVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrBitacIngresosImpl implements BitTrBitacIngresos {

	@SuppressWarnings("finally")
	@Override
	public List<BitacoraCambiosVO> guardarCambiosModifica(ProcesoDeSalidaVO original, ProcesoDeSalidaVO salidaVO)  throws ParseException{
		
		//Concepto a aplicar para la bitacora
				final long componente = 11L;
				List<BitComponente> filtro = new ArrayList<BitComponente>();
				
				//Filtro a aplicar a la bitacora
				filtro.add(new BitComponente(12L, "getFechaIngreso"));
				filtro.add(new BitComponente(14L, "getStatusIngr"));
				
				List<BitacoraCambiosVO> lista = new ArrayList<BitacoraCambiosVO>();
				List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(salidaVO, original, ProcesoDeSalidaVO.class, BitComponente.convertToList(filtro));
				
				try{
					for (Method m : listaCambios)
					{
						String oldVal = m.invoke(original) != null ? m.invoke(original).toString() : "";
						String newVal = m.invoke(salidaVO) != null ? m.invoke(salidaVO).toString() : ""; 
						
						lista.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							componente,
							BitComponente.findByParametro(filtro, m.getName()).getComponente(),
							oldVal,
							newVal,
							salidaVO.getUsuarioId() != null ? salidaVO.getUsuarioId() : 0L,
							salidaVO.getInfracNum(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
					}
				}catch(InvocationTargetException | IllegalAccessException e){
					e.printStackTrace();
				}finally{
					return lista;
				}		
	}
	
	@SuppressWarnings("finally")
	public List<BitacoraCambiosVO> guardarNuevoSalidas(GuardarSalidaVO original, GuardarSalidaVO newVO)  throws ParseException{
		
		//Concepto a aplicar para la bitacora
				final long componente = 11L;
				List<BitComponente> filtro = new ArrayList<BitComponente>();
				
				//Filtro a aplicar a la bitacora
				filtro.add(new BitComponente(14L, "getMovEstatus"));
				filtro.add(new BitComponente(26L, "getIdAutoriza"));
				
				List<BitacoraCambiosVO> lista = new ArrayList<BitacoraCambiosVO>();
				List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newVO, original, GuardarSalidaVO.class, BitComponente.convertToList(filtro));
				
				try{
					for (Method m : listaCambios)
					{
						String oldVal = m.invoke(original) != null ? m.invoke(original).toString() : "";
						String newVal = m.invoke(newVO) != null ? m.invoke(newVO).toString() : ""; 
						
						lista.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							componente,
							BitComponente.findByParametro(filtro, m.getName()).getComponente(),
							oldVal,
							newVal,
							newVO.getCreadoPor() != null ? newVO.getCreadoPor() : 0L,
							newVO.getNuminfrac(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
					}
				}catch(InvocationTargetException | IllegalAccessException e){
					e.printStackTrace();
				}finally{
					return lista;
				}		
	}
	
	@Override
	public List<BitacoraCambiosVO> generarBitacoraIngresos(IngresosVO oldIngresos, IngresosVO newIngresos)  throws ParseException{
		//Concepto a aplicar para la bitacora
		final long componente = 11L;
		
		// String , Long , Long , String , String , Long , String , String , String
		
		List<BitacoraCambiosVO> listBitCambios = new ArrayList<BitacoraCambiosVO>();
			if(!ComparaUtils.comparaCadenas(oldIngresos.getDepId(), newIngresos.getDepId())){
				listBitCambios.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					2L,
					oldIngresos.getDepId() != null ? oldIngresos.getDepId().toString() : null,
					newIngresos.getDepId() != null ? newIngresos.getDepId().toString() : null,
					newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
					newIngresos.getInfracNum(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
				));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getIngrResguardo(), newIngresos.getIngrResguardo())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						3L,
						oldIngresos.getIngrResguardo(),
						newIngresos.getIngrResguardo(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
						newIngresos.getInfracNum(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getCausaId(), newIngresos.getCausaId())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						4L,
						oldIngresos.getCausaId() != null ? oldIngresos.getCausaId().toString() : null,
						newIngresos.getCausaId() !=null ? newIngresos.getCausaId().toString() : null,
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getvTipoCod(), newIngresos.getvTipoCod())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						5L,
						oldIngresos.getvTipoCod(),
						newIngresos.getvTipoCod(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.gettIngrId(), newIngresos.gettIngrId())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						6L,
						oldIngresos.gettIngrId() != null ? oldIngresos.gettIngrId().toString() : null,
						newIngresos.gettIngrId() != null ? newIngresos.gettIngrId().toString() : null,
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getIngrAsn(), newIngresos.getIngrAsn())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						7L,
						oldIngresos.getIngrAsn(),
						newIngresos.getIngrAsn(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getIngrNumCtrl(), newIngresos.getIngrNumCtrl())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						8L,
						oldIngresos.getIngrNumCtrl(),
						newIngresos.getIngrNumCtrl(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getSellado(), newIngresos.getSellado())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						9L,
						oldIngresos.getSellado(),
						newIngresos.getSellado(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getCajuela(), newIngresos.getCajuela())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						10L,
						oldIngresos.getCajuela(),
						newIngresos.getCajuela(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getIngrFecha(), newIngresos.getIngrFecha())){
				DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
				DateFormat DateTimeStampNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				String fechaOld = null, fechaNew = null; 
				if(oldIngresos.getIngrFecha() != null){
					Date dateOld = DateTimeStampNew.parse(oldIngresos.getIngrFecha());
					fechaOld = formatter.format(dateOld);
				}
				if(newIngresos.getIngrFecha() != null){
					Date dateNew = DateTimeStampNew.parse(newIngresos.getIngrFecha());
					fechaNew = formatter.format(dateNew);
				}

				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						11L,
						fechaOld,
						fechaNew,
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getIngrSalida(), newIngresos.getIngrSalida())){
				DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
				DateFormat DateTimeStampNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				String fechaOld = null, fechaNew = null; 
				if(oldIngresos.getIngrSalida() != null){
					Date dateOld = DateTimeStampNew.parse(oldIngresos.getIngrSalida());
					fechaOld = formatter.format(dateOld);
				}
				if(newIngresos.getIngrSalida() != null){
					Date dateNew = DateTimeStampNew.parse(newIngresos.getIngrSalida());
					fechaNew = formatter.format(dateNew);
				}

				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						12L,
						fechaOld,
						fechaNew,
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getIngrSerie(), newIngresos.getIngrSerie())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						13L,
						oldIngresos.getIngrSerie(),
						newIngresos.getIngrSerie(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getIngrStatus(), newIngresos.getIngrStatus())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						14L,
						oldIngresos.getIngrStatus(),
						newIngresos.getIngrStatus(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getVehtipo(), newIngresos.getVehtipo())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						15L,
						oldIngresos.getVehtipo(),
						newIngresos.getVehtipo(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getIngrObserva(), newIngresos.getIngrObserva())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						16L,
						oldIngresos.getIngrObserva(),
						newIngresos.getIngrObserva(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getTipoGrua(), newIngresos.getTipoGrua())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						17L,
						oldIngresos.getTipoGrua() != null ? oldIngresos.getTipoGrua().toString() : null,
						newIngresos.getTipoGrua() != null ? newIngresos.getTipoGrua().toString() : null,
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getPrograma(), newIngresos.getPrograma())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						18L,
						oldIngresos.getPrograma() != null ? oldIngresos.getPrograma().toString() : null,
						newIngresos.getPrograma() != null ? newIngresos.getPrograma().toString() : null,
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getTipoEntrada(), newIngresos.getTipoEntrada())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						19L,
						oldIngresos.getTipoEntrada() != null ? oldIngresos.getTipoEntrada().toString() : null,
						newIngresos.getTipoEntrada() != null ? newIngresos.getTipoEntrada().toString() : null,
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getGruaCod(), newIngresos.getGruaCod())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						20L,
						oldIngresos.getGruaCod(),
						newIngresos.getGruaCod(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getInfracDocto(), newIngresos.getInfracDocto())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						21L,
						oldIngresos.getInfracDocto(),
						newIngresos.getInfracDocto(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getEstatusCancelacion(), newIngresos.getEstatusCancelacion())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						22L,
						oldIngresos.getEstatusCancelacion(),
						newIngresos.getEstatusCancelacion(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getAutorizacionCancelacion(), newIngresos.getAutorizacionCancelacion())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						23L,
						oldIngresos.getAutorizacionCancelacion(),
						newIngresos.getAutorizacionCancelacion(),
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getFechaCancelacion(), newIngresos.getFechaCancelacion())){
				DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
				DateFormat DateTimeStampNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				String fechaOld = null, fechaNew = null; 
				if(oldIngresos.getFechaCancelacion() != null){
					Date dateOld = DateTimeStampNew.parse(oldIngresos.getFechaCancelacion());
					fechaOld = formatter.format(dateOld);
				}
				if(newIngresos.getFechaCancelacion() != null){
					Date dateNew = DateTimeStampNew.parse(newIngresos.getFechaCancelacion());
					fechaNew = formatter.format(dateNew);
				}
				
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						24L,
						fechaOld,
						fechaNew,
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getUsuarioCancela(), newIngresos.getUsuarioCancela())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						25L,
						oldIngresos.getUsuarioCancela() != null ? oldIngresos.getUsuarioCancela().toString() : null,
						newIngresos.getUsuarioCancela() != null ? newIngresos.getUsuarioCancela().toString() : null,
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(!ComparaUtils.comparaCadenas(oldIngresos.getUsuarioAutoriza(), newIngresos.getUsuarioAutoriza())){
				listBitCambios.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						26L,
						oldIngresos.getUsuarioAutoriza() != null ? oldIngresos.getUsuarioAutoriza().toString() : null,
						newIngresos.getUsuarioAutoriza() != null ? newIngresos.getUsuarioAutoriza().toString() : null,
						newIngresos.getModificadoPor() != null ? newIngresos.getModificadoPor() : 0L,
								newIngresos.getInfracNum(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
							
	return listBitCambios;
	}
}
