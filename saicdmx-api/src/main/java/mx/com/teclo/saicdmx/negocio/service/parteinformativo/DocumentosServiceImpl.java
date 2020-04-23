package mx.com.teclo.saicdmx.negocio.service.parteinformativo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo.BitSpParteInfoInfracs;
import mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo.BitSpParteInformativoNuevo;
import mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo.BitTrBitacPInfDocs;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.parteinformativo.ParteInformativoCDocsMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.parteinformativo.ParteInformativoInfracsMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsNuevoVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorDocsVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class DocumentosServiceImpl implements DocumentosService {

	@Autowired
	private ParteInformativoCDocsMyBatisDAO parteInformativoCDocsDAO;
	
	@Autowired
	private ParteInformativoInfracsMyBatisDAO parteInformativoInfracsMyBatisDAO;
	
	@Autowired
	private BitSpParteInformativoNuevo bitSpParteInformativoNuevo;
	
	@Autowired
	private BitTrBitacPInfDocs bitTrBitacPInfDocs;
	
	@Autowired
	private BitSpParteInfoInfracs bitSpParteInfoInfracs;
	
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;

	@Override
	public List<ParteInformativoCDocsConsultaVO> buscarPIDocumentos(Integer tipoBusqueda, String valor) {
		List<ParteInformativoCDocsConsultaVO> listaDocumentosDTO = new ArrayList<ParteInformativoCDocsConsultaVO>();
		listaDocumentosDTO = parteInformativoCDocsDAO.buscarPIDocumentos(tipoBusqueda, valor);
		return listaDocumentosDTO;
	}

	@Override
	public ParteInformativoCDocsVO buscarDocumentoPorId(long valor) {
		return parteInformativoCDocsDAO.buscarDocumentoPorId(valor);
	}

	@Override
	public ParteInformativoCDocsVO modificacionDocumento(ParteInformativoCDocsVO parteInformativoCDocsVO, List<ParteInformativoInfracsPorDocsVO> addInfracciones, List<ParteInformativoInfracsPorDocsVO> deleteInfracciones) throws ParseException{
		Calendar currentTime = Calendar.getInstance();
		Integer hour = currentTime.get(Calendar.HOUR_OF_DAY);;
		Integer minutes = currentTime.get(Calendar.MINUTE);
		String strMinutes = minutes.toString();
		String strHours = hour.toString();
		
		if(minutes < 10){
			strMinutes = "0"+minutes;
		}
		
		if(hour < 10){
			strHours = "0"+hour;
		}

		String fecha = parteInformativoCDocsVO.getFecha();
		String fechaEntrega = parteInformativoCDocsVO.getFechaEntrega();
		
		//FechaEntrega ingresada
		if(fechaEntrega == null){
			parteInformativoCDocsVO.setFechaEntrega("");
		}else{
			if(!fechaEntrega.matches("([0-9]{2})/([0-9]{2})/([0-9]{4}) ([0-9]{2}):([0-9]{2})")){
				fechaEntrega = fechaEntrega + " " + strHours + ":" + strMinutes;
				parteInformativoCDocsVO.setFechaEntrega(fechaEntrega);
			}
		}
		
		//FechaRecepcion modificada
		if (fecha.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")){
			fecha = fecha + " " + strHours + ":" + strMinutes;
			parteInformativoCDocsVO.setFecha(fecha);
		}
		
		
		
		//Guardado Padre
		if(parteInformativoCDocsVO != null){
			
			ParteInformativoCDocsVO original = parteInformativoCDocsDAO.buscarPIDocumento(parteInformativoCDocsVO.getIdPi());
			
			parteInformativoCDocsDAO.modificacionDocumento(parteInformativoCDocsVO);
			
			bitacoraCambiosService.guardarListaBitacoraCambios(bitTrBitacPInfDocs.guardarCambiosModifica(original, parteInformativoCDocsVO));
			
			/*List<Method> listaCambios = compararObjetos(parteInformativoCDocsVO, original, ParteInformativoCDocsVO.class);
			
			try{
				for (Method m : listaCambios)
				{
					if(obtenerComponenteBitacora(m.getName()) != 0)
					{
						String oldVal = m.invoke(original) != null ? m.invoke(original).toString() : "";
						String newVal = m.invoke(parteInformativoCDocsVO) != null ? m.invoke(parteInformativoCDocsVO).toString() : ""; 
						bitacoraCambiosService.guardarBitacoraCambiosParametros(
								ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
								6L,
								(long)obtenerComponenteBitacora(m.getName()),
								oldVal,
								newVal,
								parteInformativoCDocsVO.getModificadoPor() != null ? parteInformativoCDocsVO.getModificadoPor() : 0L,
								parteInformativoCDocsVO.getIdPi().toString(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							);
					}
				}
			}catch(InvocationTargetException | IllegalAccessException e){
				e.printStackTrace();
			}*/
		}
		
		//############################### al modificar se utiliza triggers
		/*if(!parteInformativoCDocsVO.getResultado().equals("-1")){
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				6L,
				3L,
				parteInformativoCDocsVO.getNoConsecutivo(),
				"",
				parteInformativoCDocsVO.getModificadoPor() != null ? parteInformativoCDocsVO.getModificadoPor() : 0L,
				parteInformativoCDocsVO.getIdPi().toString() ,
				"",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			);*/
			//###############################
		
		
		//Guardado Hijo
			//Deletes
			for(ParteInformativoInfracsPorDocsVO deleteObject : deleteInfracciones){
				ParteInformativoInfracsVO toDelete = new ParteInformativoInfracsVO();
				toDelete.setTipo("5");
				toDelete.setPiId(parteInformativoCDocsVO.getIdPi());
				toDelete.setInfracNum(deleteObject.getInfracNum());
				toDelete.setInfracPlaca(deleteObject.getInfracPlaca());
				toDelete.setModificadoPor(parteInformativoCDocsVO.getModificadoPor());
				
				//ANTIGUA BITACORA_CAMBIOS(Realiza el Agregado/Eliminacion junto con el bitacoreo)
				//parteInformativoInfracsMyBatisDAO.addOrDeleteInfracciones(toDelete);
				
				Integer existe = parteInformativoInfracsMyBatisDAO.validaInfracParteInfo(toDelete.getPiId(), toDelete.getInfracNum(), toDelete.getInfracPlaca());
				
				//agrega un registro a la bitacora por cada infraccion eliminada del documento//
				if(existe != 0){
					
					//Elimina los que existan
					parteInformativoInfracsMyBatisDAO.eliminaInfracParteInfo(toDelete.getPiId(), toDelete.getInfracNum(), toDelete.getInfracPlaca());
					
					bitacoraCambiosService.guardarBitacoraCambios(bitSpParteInfoInfracs.guardarCambiosRemoverInfrac(toDelete));
				}
				/*
				if(!toDelete.getInfracNum().equals("0")){
					
					bitacoraCambiosService.guardarBitacoraCambiosParametros(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						6L,
						5L,
						toDelete.getPiId().toString(),
						toDelete.getInfracNum(),
						toDelete.getModificadoPor() != null ? toDelete.getModificadoPor() : 0L,
						toDelete.getPiId().toString(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					);
				}
				*/
				
			}
			
			//Adds
			for(ParteInformativoInfracsPorDocsVO addObject : addInfracciones){
				ParteInformativoInfracsVO toAdd = new ParteInformativoInfracsVO();
				toAdd.setTipo("2");
				toAdd.setPiId(parteInformativoCDocsVO.getIdPi());
				toAdd.setInfracNum(addObject.getInfracNum());
				toAdd.setInfracPlaca(addObject.getInfracPlaca());
				toAdd.setModificadoPor(parteInformativoCDocsVO.getModificadoPor());
				
				//ANTIGUA BITACORA_CAMBIOS(Realiza el Agregado/Eliminacion junto con el bitacoreo)
				//parteInformativoInfracsMyBatisDAO.addOrDeleteInfracciones(toAdd);
				
				//Valida si existe
				Integer existe = parteInformativoInfracsMyBatisDAO.validaInfracParteInfo(toAdd.getPiId(), toAdd.getInfracNum(), toAdd.getInfracPlaca());
				
				//agrega un registro a la bitacora por cada infraccion insertado del documento//
				if(existe == 0){
					
					//Elimina los que existan
					parteInformativoInfracsMyBatisDAO.insertaInfracParteInfo(toAdd.getPiId(), toAdd.getInfracNum(), toAdd.getInfracPlaca(), toAdd.getModificadoPor());
					
					//agrega un registro a la bitacora por cada infraccion eliminada del documento//
					bitacoraCambiosService.guardarBitacoraCambios(bitSpParteInfoInfracs.guardarCambiosAdicionInfrac(toAdd));
				}
				
				
				/*if(!toAdd.getResultado().equals("-1")){
					bitacoraCambiosService.guardarBitacoraCambiosParametros(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						6L,
						3L,
						toAdd.getPiId().toString(),
						toAdd.getInfracNum(),
						toAdd.getModificadoPor() != null ? toAdd.getModificadoPor() : 0L,
						toAdd.getPiId().toString(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					);
				}*/
				////
			}
		
		return parteInformativoCDocsVO;
		//String hour = fecha.split(" ")[1].split(":")[0];
		//String minutes = fecha.split(" ")[1].split(":")[1];
	}

	@Override
	public ParteInformativoCDocsNuevoVO crearDocumento(ParteInformativoCDocsNuevoVO parteInformativoCDocsNuevoVO, List<ParteInformativoInfracsPorDocsVO> addInfracciones) {
//		Calendar currentTime = Calendar.getInstance();
//		Integer hour = currentTime.get(Calendar.HOUR_OF_DAY);;
//		Integer minutes = currentTime.get(Calendar.MINUTE);
//		String strMinutes = minutes.toString();
//		String strHours = hour.toString();
//		
//		if(minutes < 10){
//			strMinutes = "0"+minutes;
//		}
//		
//		if(hour < 10){
//			strHours = "0"+hour;
//		}
//		
//		String fecha = parteInformativoCDocsNuevoVO.getFecha();
//		fecha = fecha + " " + strHours + ":" + strMinutes;
//		parteInformativoCDocsNuevoVO.setFecha(fecha);
		
		//Guardado Padre
		parteInformativoCDocsDAO.crearDocumento(parteInformativoCDocsNuevoVO);
		
		bitacoraCambiosService.guardarListaBitacoraCambios(bitSpParteInformativoNuevo.guardarCambiosCrear(parteInformativoCDocsNuevoVO));
		/*
		if(parteInformativoCDocsNuevoVO != null){
			if(!parteInformativoCDocsNuevoVO.getResultado().equals("-1")){
				bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					6L,
					1L,
					parteInformativoCDocsNuevoVO.getNoConsecutivo(),
					"",
					parteInformativoCDocsNuevoVO.getCreadoPor() != null ? parteInformativoCDocsNuevoVO.getCreadoPor() : 0L,
							parteInformativoCDocsNuevoVO.getId(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
				);
			}
		}
		*/
		
		
		if(!parteInformativoCDocsNuevoVO.getId().equals("-1")){
			//Adds
			for(ParteInformativoInfracsPorDocsVO addObject : addInfracciones){
				ParteInformativoInfracsVO toAdd = new ParteInformativoInfracsVO();
				toAdd.setTipo("1");
				toAdd.setPiId(Long.parseLong(parteInformativoCDocsNuevoVO.getId()));
				toAdd.setInfracNum(addObject.getInfracNum());
				toAdd.setInfracPlaca(addObject.getInfracPlaca());
				toAdd.setModificadoPor(parteInformativoCDocsNuevoVO.getCreadoPor()); //El store identifica en que campo insertar al usuario
				parteInformativoInfracsMyBatisDAO.addOrDeleteInfracciones(toAdd);

				//agregar un registro a la bitacora por cada infraccion agregada al documento//
				bitacoraCambiosService.guardarBitacoraCambios(bitSpParteInfoInfracs.guardarCambiosAdicionInfrac(toAdd));
				//if(!toAdd.getResultado().equals("-1")){
				/*bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					6L,
					3L,
					toAdd.getPiId().toString(),
					toAdd.getInfracNum(),
					toAdd.getModificadoPor() != null ? toAdd.getModificadoPor() : 0L,
					toAdd.getPiId().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
				);*/
				//}
				////
			}
		}
		
		return parteInformativoCDocsNuevoVO;
	}
	
	@SuppressWarnings("finally")
	public List<Method> compararObjetos(Object obj1, Object obj2, Class persistentClass, String[] filtro){
		return null;
	}
	
	@SuppressWarnings("finally")
	public List<Method> compararObjetos(Object obj1, Object obj2, Class persistentClass){
		if (obj1.getClass() == persistentClass && obj2.getClass() == persistentClass){
			
			//persistentClass picdVO2 = (persistentClass) obj2;
			
			List<Method> listapicdVO = new ArrayList<Method>(); 
			
			try{
				for (Method method : persistentClass.getMethods()){
					if(method.getName().startsWith("get")){
						Method met1 = obj1.getClass().getMethod(method.getName());
						Method met2 = obj2.getClass().getMethod(method.getName());
						
						Object r1 = met1.invoke(obj1);
						Object r2 = met2.invoke(obj2);
						
						if(r1 != null){
							if(!r1.equals(r2 != null ? r2 : ""))
								listapicdVO.add(met2);	
						}else{
							if(r2 != null)
								if(!r2.equals(r1 != null ? r1 : ""))
									listapicdVO.add(met2);
						}
					}
				}
				//obj1 = Class.forName(persistentClass.getName()).cast(obj1);
			}
			catch(NoSuchMethodException nsme)
			{
				nsme.printStackTrace();
			}finally{
				return listapicdVO;
			}
		}
		return null;
	}
	
	public int obtenerComponenteBitacora(String nombreParametro)
	{
		String[] param = nombreParametro.split("get");
		if("NoConsecutivo".equals(param[1])) return 7;
		if("Fecha".equals(param[1])) return 8;
		if("OficialPlaca".equals(param[1])) return 9;
		if("OficialAoper".equals(param[1])) return 10;
		if("DocIfe".equals(param[1])) return 11;
		if("DocTarjCirc".equals(param[1])) return 12;
		if("DocLicencia".equals(param[1])) return 13;
		if("DocVerific".equals(param[1])) return 14;
		if("DocIfeNombre".equals(param[1])) return 15;
		if("DocTarjCircNombre".equals(param[1])) return 16;
		if("DocLicenciaNombre".equals(param[1])) return 17;
		if("DocVerificNombre".equals(param[1])) return 18;
		if("Observacion".equals(param[1])) return 19;
		if("FechaEntrega".equals(param[1])) return 20;
		if("DocOtros".equals(param[1])) return 21;
		if("DocOtrosNombre".equals(param[1])) return 22;
		return 0;
	}
}
