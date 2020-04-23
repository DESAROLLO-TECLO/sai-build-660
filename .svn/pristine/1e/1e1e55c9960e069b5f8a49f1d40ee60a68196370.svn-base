package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultadetecciones.FotomultaDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultadetecciones.FotomultaDeteccionesReportesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesPorRadarVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesResultadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesSinProcesarVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaInfraccionesLiberacionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaUsuarioClasificacionVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

@Service
public class DeteccionesSinProcesarServiceImpl implements DeteccionesSinProcesarService{

	@Autowired
	private FotomultaDeteccionesMyBatisDAO deteccionesSinProcesarDAO;
	
	@Autowired
	private FotomultaDeteccionesReportesMyBatisDAO deteccionesReportesMyBatisDAO;
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<DeteccionesPorRadarVO> buscarDetecciones(Integer tipoFecha, Integer origenPlaca) {
		List<DeteccionesPorRadarVO> results = new ArrayList<DeteccionesPorRadarVO>();
		List<DeteccionesSinProcesarVO> listdeteccionesActual = null;
		List<DeteccionesSinProcesarVO> listdeteccionesHistorico = null;
		DeteccionesPorRadarVO prevalidadas = new DeteccionesPorRadarVO();
		DeteccionesPorRadarVO sinProcesarActual = new DeteccionesPorRadarVO();
		DeteccionesPorRadarVO sinProcesarHistorico = new DeteccionesPorRadarVO();
		Boolean flag = false;
		
        Calendar tresMeses = Calendar.getInstance();
        tresMeses.add(Calendar.MONTH, - 2);
        int mesInicio = tresMeses.get(Calendar.MONTH)+1;
        int año = tresMeses.get(Calendar.YEAR);
        String fechaInicio="";
        if(mesInicio<10){
        	 fechaInicio="01/0"+mesInicio+"/"+año;
        }else{
        	fechaInicio="01/"+mesInicio+"/"+año;
        }
        
        List<DeteccionesSinProcesarVO> listdeteccionesPrevalidadas = deteccionesSinProcesarDAO.buscarDeteccionesPrevalidadas(origenPlaca);
        
		if(tipoFecha.equals(1)){
			listdeteccionesActual = deteccionesSinProcesarDAO.buscarDeteccionesSinProcesarActual(fechaInicio, origenPlaca);
			listdeteccionesHistorico = deteccionesSinProcesarDAO.buscarDeteccionesSinProcesarHistorico(fechaInicio, origenPlaca);
		}else if(tipoFecha.equals(2)){
			listdeteccionesActual = deteccionesSinProcesarDAO.buscarDeteccionesSinProcesarActualFechaValidacion(fechaInicio, origenPlaca);
			listdeteccionesHistorico = deteccionesSinProcesarDAO.buscarDeteccionesSinProcesarHistoricoFechaValidacion(fechaInicio, origenPlaca);
		}
		
		
		if(!listdeteccionesPrevalidadas.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listdeteccionesPrevalidadas){
				if(deteccion.getCodigo().equals("LR")){
					prevalidadas.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					prevalidadas.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					prevalidadas.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			prevalidadas.setTotal(listdeteccionesPrevalidadas.get(0).getTotalDetecciones());
		}
		
		if(!listdeteccionesActual.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listdeteccionesActual){
				if(deteccion.getCodigo().equals("LR")){
					sinProcesarActual.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					sinProcesarActual.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					sinProcesarActual.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			sinProcesarActual.setTotal(listdeteccionesActual.get(0).getTotalDetecciones());
		}
		
		if(!listdeteccionesHistorico.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listdeteccionesHistorico){
				if(deteccion.getCodigo().equals("LR")){
					sinProcesarHistorico.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					sinProcesarHistorico.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					sinProcesarHistorico.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			sinProcesarHistorico.setTotal(listdeteccionesHistorico.get(0).getTotalDetecciones());
		}
		
		if(flag){
			results.add(prevalidadas);
			results.add(sinProcesarActual);
			results.add(sinProcesarHistorico);
		}
		return results;
	}

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map buscarDeteccionesPorTiposFecha(Integer tipoFecha, String codigoRadar, Integer origenPlaca, Integer tipoDeteccion) {
		Map response = new HashMap();
		List<DeteccionesResultadoVO> list = null;
		
		RutinasTiempoImpl rutinas = new RutinasTiempoImpl();
        Calendar tresMeses = Calendar.getInstance();
        tresMeses.add(Calendar.MONTH, -2);
        Date ahora = new Date();
        int mesInicio = tresMeses.get(Calendar.MONTH) + 1;
        int añoInicio = tresMeses.get(Calendar.YEAR);
        
        String fechaInicio="01/"+mesInicio+"/"+añoInicio;
        String fechaHoy = rutinas.getStringDateFromFormta("dd/MM/yyyy",ahora);
		
		final Integer procesables=0;
	    final Integer noProcesables=1;
		
		if(tipoFecha == 1 && tipoDeteccion.equals(procesables)){
			list = deteccionesSinProcesarDAO.buscarDeteccionesByFechaInfraccionActual(codigoRadar, fechaInicio, fechaHoy, origenPlaca);
		}else if(tipoFecha == 1 && tipoDeteccion.equals(noProcesables)){
			list = deteccionesSinProcesarDAO.buscarDeteccionesByFechaInfraccionHistorica(codigoRadar, fechaInicio, origenPlaca);
		}else if(tipoFecha == 2 && tipoDeteccion.equals(procesables)){
			list = deteccionesSinProcesarDAO.buscarDeteccionesByFechaValidacionActual(codigoRadar, fechaInicio, fechaHoy, origenPlaca);
		}else if(tipoFecha == 2 && tipoDeteccion.equals(noProcesables)){
			list = deteccionesSinProcesarDAO.buscarDeteccionesByFechaValidacionHistorica(codigoRadar, fechaInicio, origenPlaca);
		}
		
		//response = agruparResultados(list);
		response.put("list", list);
		
		return response;
	}
	
	/***
	 * {@inheritDoc}
	 * @throws ParseException 
	 */
	public List<DeteccionesResultadoVO> buscarDeteccionesPorMes(String fechaInicio, Integer origenPlaca, Integer tipoFecha, String codigoRadar) throws ParseException{
		List<DeteccionesResultadoVO> lista = null;

		if(tipoFecha == 1){
			lista = deteccionesSinProcesarDAO.buscarDeteccionesFechaInfraccionPorMes(fechaInicio, origenPlaca, codigoRadar);
		}else if(tipoFecha == 2){
			lista = deteccionesSinProcesarDAO.buscarDeteccionesFechaValidacionPorMes(fechaInicio, origenPlaca, codigoRadar);
		}
		
		return lista;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	public Map agruparResultados(List<DeteccionesResultadoVO> datos){
		//Map mainMap = new HashMap();
		List<DeteccionesResultadoVO> lista = new ArrayList<DeteccionesResultadoVO>();
		Map Mapa = new HashMap();
		
		int i = 0;
		int j = 0;
		for(DeteccionesResultadoVO obj : datos){
			if(i < 3){
				//mainMap.put(i, obj);
				lista.add(obj);
				i++;
			}else{	
				Mapa.put(j, lista);
				lista = new ArrayList<DeteccionesResultadoVO>();
				lista.add(obj);
				i = 0;
				j ++;
			}
			
		}
		
		if(i != 0 && i < 3){
			Mapa.put(i, lista);
		}
		
		return Mapa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DeteccionesPorRadarVO> buscarDeteccionesPorRangoTiempo() {
		List<DeteccionesPorRadarVO> listPreValidAndValid = new ArrayList<DeteccionesPorRadarVO>();
		DeteccionesPorRadarVO prevalidadas = new DeteccionesPorRadarVO();
		DeteccionesPorRadarVO validadas = new DeteccionesPorRadarVO();
		DeteccionesPorRadarVO totalDetecciones = new DeteccionesPorRadarVO();
		DeteccionesPorRadarVO infracciones = new DeteccionesPorRadarVO();
		
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		 Calendar calendar = Calendar.getInstance();	
		 calendar.setTime(new Date());
		 calendar.add(Calendar.MONTH , -12);  // numero de meses a añadir, o restar en caso de días<0	
		 String fecha = sdf.format(calendar.getTime());
		
		
		List<DeteccionesSinProcesarVO> listdeteccionesPrevalidadas = deteccionesReportesMyBatisDAO.buscarDeteccionesPrevalidadasPorRangoTiempo(fecha);
		List<DeteccionesSinProcesarVO> listdeteccionesValidadas = deteccionesReportesMyBatisDAO.buscarDeteccionesValidadasPorRangoTiempo(fecha);
		List<DeteccionesSinProcesarVO> listaTotalClasificacion = deteccionesReportesMyBatisDAO.buscarDeteccionesTotalPorRangoTiempo(fecha);
		List<DeteccionesSinProcesarVO> listaInfracciones = deteccionesReportesMyBatisDAO.buscarDeteccionesInfracciones(fecha);

		Boolean flag = false;
		
		if(!listdeteccionesPrevalidadas.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listdeteccionesPrevalidadas){
				if(deteccion.getCodigo().equals("LR")){
					prevalidadas.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					prevalidadas.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					prevalidadas.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			prevalidadas.setTotal(listdeteccionesPrevalidadas.get(0).getTotalDetecciones());
		}
		
		if(!listdeteccionesValidadas.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listdeteccionesValidadas){
				if(deteccion.getCodigo().equals("LR")){
					validadas.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					validadas.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					validadas.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			validadas.setTotal(listdeteccionesValidadas.get(0).getTotalDetecciones());
		}
		
		
		if(!listaTotalClasificacion.isEmpty()){
			flag = true;

			for(DeteccionesSinProcesarVO deteccion : listaTotalClasificacion){
				if(deteccion.getCodigo().equals("LR")){
					totalDetecciones.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					totalDetecciones.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					totalDetecciones.setLaser(deteccion.getNumeroDeteciones());
				}
			}

			totalDetecciones.setTotal(listaTotalClasificacion.get(0).getTotalDetecciones());
		}
		
		
		
		if(!listaInfracciones.isEmpty()){
			flag = true;	
			for(DeteccionesSinProcesarVO deteccion : listaInfracciones){
				if(deteccion.getCodigo().equals("LR")){
					infracciones.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					infracciones.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					infracciones.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			infracciones.setTotal(listaInfracciones.get(0).getTotalDetecciones());
		}

		if(flag){
			listPreValidAndValid.add(prevalidadas);
			listPreValidAndValid.add(validadas);
			listPreValidAndValid.add(totalDetecciones);
			listPreValidAndValid.add(infracciones);
		}
		return listPreValidAndValid;
	}

	/**
	 *@author javier07
	 */
	@Override
	public List<DeteccionesPorRadarVO> buscarAcepRechaPorRangoTiempo() {
		
		List<DeteccionesPorRadarVO> listaAcepRecha = new ArrayList<DeteccionesPorRadarVO>();
		
		DeteccionesPorRadarVO aceptadasClasificacion = new DeteccionesPorRadarVO();
		DeteccionesPorRadarVO rechazadasClasificacion = new DeteccionesPorRadarVO();

		DeteccionesPorRadarVO aceptadasPrevalidadas = new DeteccionesPorRadarVO();
		DeteccionesPorRadarVO rechazadasPrevalidadas = new DeteccionesPorRadarVO();
		
		DeteccionesPorRadarVO aceptadasValidas = new DeteccionesPorRadarVO();
		DeteccionesPorRadarVO rechazadasValidadas = new DeteccionesPorRadarVO();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH , -12);  // numero de meses a añadir, o restar en caso de días<0	
		String fecha = sdf.format(calendar.getTime());
		
		List<DeteccionesSinProcesarVO> listaClasificacionAceptadas = deteccionesReportesMyBatisDAO.buscarClasificacionAceptadas(fecha);
		List<DeteccionesSinProcesarVO> listaClasificacionRechazadas = deteccionesReportesMyBatisDAO.buscarClasificacionRechazadas(fecha);
		
		List<DeteccionesSinProcesarVO> listaPrevaliAceptadas = deteccionesReportesMyBatisDAO.buscarPrevalidadasAceptadas(fecha);
		List<DeteccionesSinProcesarVO> listaPrevaliRechazadas = deteccionesReportesMyBatisDAO.buscarPrevalidadasRechazadas(fecha);
		
		List<DeteccionesSinProcesarVO> listaValiAceptadas = deteccionesReportesMyBatisDAO.buscarValidadasAceptadas(fecha);
		List<DeteccionesSinProcesarVO> listaValiRechazadas = deteccionesReportesMyBatisDAO.buscarValidadasRechazadas(fecha);
	
		
		
		Boolean flag = false;
		
		if(!listaClasificacionAceptadas.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listaClasificacionAceptadas){
				if(deteccion.getCodigo().equals("LR")){
					aceptadasClasificacion.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					aceptadasClasificacion.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					aceptadasClasificacion.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			aceptadasClasificacion.setTotal(listaClasificacionAceptadas.get(0).getTotalDetecciones());
		}
		
		
		if(!listaClasificacionRechazadas.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listaClasificacionRechazadas){
				if(deteccion.getCodigo().equals("LR")){
					rechazadasClasificacion.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					rechazadasClasificacion.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					rechazadasClasificacion.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			rechazadasClasificacion.setTotal(listaClasificacionRechazadas.get(0).getTotalDetecciones());
		}

		if(!listaPrevaliAceptadas.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listaPrevaliAceptadas){
				if(deteccion.getCodigo().equals("LR")){
					aceptadasPrevalidadas.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					aceptadasPrevalidadas.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					aceptadasPrevalidadas.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			aceptadasPrevalidadas.setTotal(listaPrevaliAceptadas.get(0).getTotalDetecciones());
		}
		
		
		if(!listaPrevaliRechazadas.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listaPrevaliRechazadas){
				if(deteccion.getCodigo().equals("LR")){
					rechazadasPrevalidadas.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					rechazadasPrevalidadas.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					rechazadasPrevalidadas.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			rechazadasPrevalidadas.setTotal(listaPrevaliRechazadas.get(0).getTotalDetecciones());
		}

		if(!listaValiAceptadas.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listaValiAceptadas){
				if(deteccion.getCodigo().equals("LR")){
					aceptadasValidas.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					aceptadasValidas.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					aceptadasValidas.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			aceptadasValidas.setTotal(listaValiAceptadas.get(0).getTotalDetecciones());
		}
		
		
		if(!listaValiRechazadas.isEmpty()){
			flag = true;
			for(DeteccionesSinProcesarVO deteccion : listaValiRechazadas){
				if(deteccion.getCodigo().equals("LR")){
					rechazadasValidadas.setLuzRoja(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MI")){
					rechazadasValidadas.setBosch(deteccion.getNumeroDeteciones());
				}else if(deteccion.getCodigo().equals("MR")){
					rechazadasValidadas.setLaser(deteccion.getNumeroDeteciones());
				}
			}
			rechazadasValidadas.setTotal(listaValiRechazadas.get(0).getTotalDetecciones());
		}
		
		

		if(flag){
			listaAcepRecha.add(aceptadasClasificacion);
			listaAcepRecha.add(rechazadasClasificacion);				
			listaAcepRecha.add(aceptadasPrevalidadas);
			listaAcepRecha.add(rechazadasPrevalidadas);		
			listaAcepRecha.add(aceptadasValidas);
			listaAcepRecha.add(rechazadasValidadas);	
		}
		return listaAcepRecha;
	}

	@Override
	public List<List<FotoMultaInfraccionesLiberacionesVO>> buscarInfraccLiberacionesPorRangoTiempo() {
		
		List<List<FotoMultaInfraccionesLiberacionesVO>> listaDatos = new ArrayList<List<FotoMultaInfraccionesLiberacionesVO>>();		
		List<FotoMultaInfraccionesLiberacionesVO> listaLiberaciones = new ArrayList<FotoMultaInfraccionesLiberacionesVO>();
		List<FotoMultaInfraccionesLiberacionesVO> listaInfracciones = new ArrayList<FotoMultaInfraccionesLiberacionesVO>();

		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		 Calendar calendar = Calendar.getInstance();	
		 calendar.setTime(new Date());
		 calendar.add(Calendar.MONTH , -12);  // numero de meses a añadir, o restar en caso de días<0	
		 String fecha = sdf.format(calendar.getTime());
		 
		 listaInfracciones = deteccionesReportesMyBatisDAO.buscarInfracciones(fecha);
		 listaLiberaciones = deteccionesReportesMyBatisDAO.buscarLiberaciones(fecha);
			
		 if(!listaInfracciones.isEmpty()){
		
			 listaDatos.add(listaInfracciones);
		 }
		 
		if(!listaLiberaciones.isEmpty()){

			 listaDatos.add(listaLiberaciones);
		}
		
		 return listaDatos;
		
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FotoMultaUsuarioClasificacionVO> buscarUsuariosClasificacion() {

		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		 Calendar calendar = Calendar.getInstance();	
		 calendar.setTime(new Date());
		 calendar.add(Calendar.MONTH , -12);  // numero de meses a añadir, o restar en caso de días<0	
		 String fecha = sdf.format(calendar.getTime());

		 List<FotoMultaUsuarioClasificacionVO> lista = null;
		 lista = deteccionesReportesMyBatisDAO.buscarUsuariosClasificacion(fecha);

		 return lista;
	}
	
	
	
	
	
	
}
