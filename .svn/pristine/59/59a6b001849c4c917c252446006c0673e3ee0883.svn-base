package mx.com.teclo.saicdmx.negocio.service.parteinformativo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo.BitSpParteInfoBoletaNuevo;
import mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo.BitSpParteInfoInfracs;
import mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo.BitTrBitacPInfDocs;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.parteinformativo.ParteInformativoBoletaSancionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.parteinformativo.ParteInformativoInfracsMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionModificacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionNuevaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorBolsVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class ParteInformativoBoletaSancionServiceImpl implements ParteInformativoBoletaSancionService{

	@Autowired
	private ParteInformativoBoletaSancionMyBatisDAO parteInformativoBoletaSancionMyBatisDAO;
	
	@Autowired
	private ParteInformativoInfracsMyBatisDAO ParteInformativoInfracsMyBatisDAO;
	
	@Autowired
	private BitacoraCambiosService  bitacoraCambiosService;

	@Autowired
	private BitSpParteInfoInfracs bitSpParteInfoInfracs;
	
	@Autowired
	private BitTrBitacPInfDocs bitTrBitacPInfDocs; 
	
	@Autowired
	private ParteInformativoBoletaSancionService parteInformativoBoletaSancionService;

	@Autowired
	private BitSpParteInfoBoletaNuevo bitSpParteInfoBoletaNuevo;
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<ParteInformativoBoletaSancionConsultaVO> buscarPIBoletas(Integer tipoBusqueda, String valor) {
		List<ParteInformativoBoletaSancionConsultaVO> listaBoletasDTO = new ArrayList<ParteInformativoBoletaSancionConsultaVO>();
		listaBoletasDTO = parteInformativoBoletaSancionMyBatisDAO.buscarPIBoletas(tipoBusqueda, valor);
		return listaBoletasDTO;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override	
	public ParteInformativoBoletaSancionModificacionVO buscarBoletaPorId(Long valor) {
		return parteInformativoBoletaSancionMyBatisDAO.biuscarBoletaPorId(valor);
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public ParteInformativoBoletaSancionModificacionVO modificacionBoleta(ParteInformativoBoletaSancionModificacionVO boletaVO,
																			List<ParteInformativoInfracsPorBolsVO> addInfracciones,
																			List<ParteInformativoInfracsPorBolsVO> deleteInfracciones) {
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
		
		ParteInformativoBoletaSancionModificacionVO originalBoletaVO = parteInformativoBoletaSancionService.buscarBoletaPorId(boletaVO.getId());

		String fechaSello = boletaVO.getFecha();
	     
		
		/********** Valores originales antes de ser modificados ***********/
	    String fechaHora = originalBoletaVO.getFecha().substring(11, 16);
	    String fechaAnio= originalBoletaVO.getFecha().substring(0, 4);
	    String fechaMes=  originalBoletaVO.getFecha().substring(5, 7);
	    String fechaDia= originalBoletaVO.getFecha().substring(8, 10);
	    String fechaFormatOriginal = fechaDia+"/"+fechaMes+"/"+fechaAnio+" "+fechaHora;
	    
		String originalNoConsecutivo = originalBoletaVO.getNoConsecutivo();
		String originalFechaSello = fechaFormatOriginal;
		String originalAreaOperativa = originalBoletaVO.getOficialAoper();
		String originalOficialPlaca= originalBoletaVO.getOficialPlaca();
		String originalSituacion= evaluaSituacionBoleta(originalBoletaVO);
		
		
		if(originalBoletaVO.getSituacionOtroDesc()==null){
			originalBoletaVO.setSituacionOtroDesc("");
				if(boletaVO.getSituacionOtroDesc()==null){
					boletaVO.setSituacionOtroDesc("");;
				}
	     }
		
		//FechaSello modificada
		if (fechaSello.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")){
			fechaSello = fechaSello + " " + strHours + ":" + strMinutes;
			boletaVO.setFecha(fechaSello);
		}
		
		//Guardado de Padre
		parteInformativoBoletaSancionMyBatisDAO.modificacionBoleta(boletaVO);
		
		//######################### Evalua si hubo algun cambio y lo guarda en bitacora 
		if(!boletaVO.getResultado().equals("-1")){	
			
			if(!originalNoConsecutivo.equals(boletaVO.getNoConsecutivo())){
				BitacoraCambiosVO cambiosVO = bitTrBitacPInfDocs.guardarCambiosNoConsecutivo(boletaVO,originalNoConsecutivo);
				bitacoraCambiosService.guardarBitacoraCambios(cambiosVO);
			}
			if(!originalFechaSello.equals(boletaVO.getFecha())){
				BitacoraCambiosVO cambiosVO = bitTrBitacPInfDocs.guardarCambiosFechaSello(boletaVO,originalFechaSello);
				bitacoraCambiosService.guardarBitacoraCambios(cambiosVO);
			}
			if(!originalOficialPlaca.equals(boletaVO.getOficialPlaca())){
				BitacoraCambiosVO cambiosVO = bitTrBitacPInfDocs.guardarCambiosPlacaOficial(boletaVO,originalOficialPlaca);
				bitacoraCambiosService.guardarBitacoraCambios(cambiosVO);
			}
			if(!originalAreaOperativa.equals(boletaVO.getOficialAoper())){
				BitacoraCambiosVO cambiosVO = bitTrBitacPInfDocs.guardarCambiosAreaOperativa(boletaVO,originalAreaOperativa);
				bitacoraCambiosService.guardarBitacoraCambios(cambiosVO);
			}
			
			if(!originalSituacion.equals(boletaVO.getSituacionSelect()) ||
			   !originalBoletaVO.getSituacionOtroDesc().equals(boletaVO.getSituacionOtroDesc())){
				BitacoraCambiosVO cambiosVO = bitTrBitacPInfDocs.guardarCambioSituacion(boletaVO,originalSituacion);
				bitacoraCambiosService.guardarBitacoraCambios(cambiosVO);
			}

			//######################### Verfica si hay nuevas infracciones que se eliminaron
			for(ParteInformativoInfracsPorBolsVO deleteObject : deleteInfracciones){
				ParteInformativoInfracsVO toDelete = new ParteInformativoInfracsVO();
				toDelete.setTipo("6");
				toDelete.setPiId(boletaVO.getId());
				toDelete.setInfracNum(deleteObject.getInfracNum());
				toDelete.setInfracPlaca("");
				toDelete.setModificadoPor(boletaVO.getModificadoPor());
				ParteInformativoInfracsMyBatisDAO.addOrDeleteInfracciones(toDelete);
		    if(!toDelete.getInfracNum().equals("0")){		
				BitacoraCambiosVO cambiosVO = bitTrBitacPInfDocs.guardarCambiosDeleteInfraccs(boletaVO,toDelete.getInfracNum());
				bitacoraCambiosService.guardarBitacoraCambios(cambiosVO);
			}
		    
			//######################### Verfica si hay nuevas infracciones que se agregaron
			for(ParteInformativoInfracsPorBolsVO addObject : addInfracciones){
				ParteInformativoInfracsVO toAdd = new ParteInformativoInfracsVO();
				toAdd.setTipo("4");
				toAdd.setPiId(boletaVO.getId());
				toAdd.setInfracNum(addObject.getInfracNum());
				toAdd.setInfracPlaca("");
				toAdd.setModificadoPor(boletaVO.getModificadoPor());
				Integer result=ParteInformativoInfracsMyBatisDAO.validaInfracParteInfoBoletaSancion(boletaVO.getId(), addObject.getInfracNum());

				if(result==0){					
					ParteInformativoInfracsMyBatisDAO.addOrDeleteInfracciones(toAdd);					
					BitacoraCambiosVO cambiosVO = bitTrBitacPInfDocs.guardarCambiosAddInfraccs(boletaVO,toAdd.getInfracNum());
					bitacoraCambiosService.guardarBitacoraCambios(cambiosVO);	
				}	
			}	
		}
		
		}
		return boletaVO;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public ParteInformativoBoletaSancionNuevaVO crearBoleta(ParteInformativoBoletaSancionNuevaVO boletaVO,
			List<ParteInformativoInfracsPorBolsVO> addInfracciones) {
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
//		String fecha = boletaVO.getFecha();
//		fecha = fecha + " " + strHours + ":" + strMinutes;
//		boletaVO.setFecha(fecha);
		
		//Guardado del padre
		this.parteInformativoBoletaSancionMyBatisDAO.crearBoleta(boletaVO);
		
		if(boletaVO.getResultado().equals("-1")) {
			return boletaVO;
		}
		
		//###################################  Bitacora de cambios ... 
		if(!boletaVO.getResultado().equals("-1")){
			try{
				bitacoraCambiosService.guardarBitacoraCambios(bitSpParteInfoBoletaNuevo.cambiosBitacoraCrearBoleta(boletaVO));
			}catch(Exception e){System.err.println(e.getMessage());}
		}
		//###################################
		
		
		if(!boletaVO.getId().equals("-1")){
			
			for(ParteInformativoInfracsPorBolsVO addObject : addInfracciones){
				ParteInformativoInfracsVO toAdd = new ParteInformativoInfracsVO();
				toAdd.setTipo("3");
				toAdd.setPiId(Long.parseLong(boletaVO.getId()));
				toAdd.setInfracNum(addObject.getInfracNum());
				toAdd.setInfracPlaca("");
				toAdd.setModificadoPor(boletaVO.getCreadoPor());
				
				
				ParteInformativoInfracsMyBatisDAO.addOrDeleteInfracciones(toAdd);
				
				//###################################
				if(!toAdd.getResultado().equals("-1")){
					bitacoraCambiosService.guardarBitacoraCambios(bitSpParteInfoInfracs.guardarNuevaInfraccionBoletaSancion(toAdd));
				}
				//###################################
				
			}
		}
		
		return boletaVO;
	}
	
	public String evaluaSituacionBoleta(ParteInformativoBoletaSancionModificacionVO boletaVO){
		String resultSituacion = "";
		if(boletaVO.getSituacionActa().equals("1")){
			resultSituacion = "1";		
		}else{
			if(boletaVO.getSituacionExtravio().equals("1")){
				resultSituacion = "2";		
			}else{
				if(boletaVO.getSituacionElaborada().equals("1")){
					resultSituacion = "3";	
				}else{
					resultSituacion = "4";	
				}
			}
		}
		return resultSituacion;
	}

}
