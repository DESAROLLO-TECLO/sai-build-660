package mx.com.teclo.saicdmx.negocio.service.fm;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMCancelacionDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDetalleDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesFechasVO;

@Service
public class CancelacionDeteccionesFMImpl implements ICancelacionDeteccionesFMService {
	
	@Autowired
	private FMCancelacionDeteccionesMyBatisDAO  CancelacionDetecciones;
	@Autowired
    private FMReporteDetalleDetecciones fmreporteDetalleDetecciones;
	
	private ByteArrayOutputStream  reporte;

	@Override
	public List<FMDeteccionesFechasVO> filtroTiposFecha() {
		List<FMDeteccionesFechasVO> FechasDetecciones= null;	
		String fechaFin = obtenerFechaFin();
		try{
			FechasDetecciones = CancelacionDetecciones.consultaFechasDetecciones(fechaFin);
			for (FMDeteccionesFechasVO fotomultaCatComboFechasVO : FechasDetecciones) {
				int mes = Integer.parseInt(fotomultaCatComboFechasVO.getMes());
				String year = fotomultaCatComboFechasVO.getAnio();
				String fechaFormat = getMonth(mes).substring(0, 1).toUpperCase() + getMonth(mes).substring(1);
				fotomultaCatComboFechasVO.setFechaFormat(year + "/" + fechaFormat);
				fotomultaCatComboFechasVO.setFechaValue(fotomultaCatComboFechasVO.getMes() + "/" + year);
			}
			
		}catch(Exception e){
			//.out.println(e);
			FechasDetecciones.get(0).setFechaValue("Error en datos de la base de datos");
		}
		
		return FechasDetecciones;
	}


	@Override
	public List<FMConsultaDeteccionesVO> DeteccionesParaCancelar(String fechaInicio, String fechaFin, int tipoDeteccion,int tipoRadar, int origenPlaca) {
		List<FMConsultaDeteccionesVO> DeteccionesCancelar = null;
		int TotalActuales=0,i=0,cabeza=0,d=0,con=0,tipoD=0;
		
		if(fechaInicio.equals("")){
			fechaInicio ="01/01/";
			fechaInicio = fechaInicio + CancelacionDetecciones.FechaMasViejaDeteccion(fechaFin); //FechaMasViejaDeteccion
		}
		
		List<FMConsultaDeteccionesVO> responce = new ArrayList<FMConsultaDeteccionesVO>();
		String Radares[] = { "Carril Confinado", "Fotomultas", "Radares de velocidad 03", "Radares de velocidad 09" };
		
		DeteccionesCancelar = CancelacionDetecciones.consultaDeteccionesCancelar(fechaInicio, fechaFin,tipoDeteccion, tipoRadar, origenPlaca);
		
		
		for(int x= 0 ;x<DeteccionesCancelar.size();x++){
			//if(DeteccionesCancelar.get(x).get)[]
			TotalActuales+=Integer.parseInt(DeteccionesCancelar.get(x).getTOTAL());
		}
		
		
		if (tipoDeteccion > 0) {
			for (i = 0; i < DeteccionesCancelar.size(); i++) {
				if(DeteccionesCancelar.get(i).getTOTAL().equals("0") && DeteccionesCancelar.get(i).getTOTAL().equals("0")){
				  
				}else{
					FMConsultaDeteccionesVO filter = new FMConsultaDeteccionesVO();
					if(tipoD==0){
						filter.setTIPO(DeteccionesCancelar.get(i).getTIPO());
					       d=1;
					}else{
						filter.setTIPO(null);
					}
					
					filter.setRADAR(DeteccionesCancelar.get(i).getRADAR());
					filter.setTOTAL(DeteccionesCancelar.get(i).getTOTAL());
					filter.setHistorico("0");
					filter.setTotalActuales(0);
					filter.setTotalHistoricos(0);
					filter.setMARCA(DeteccionesCancelar.get(i).getMARCA());
					filter.setID(DeteccionesCancelar.get(i).getID());
					responce.add(con, filter);
					con+=1;
				}
				
			}
			if(TotalActuales!= 0){
				FMConsultaDeteccionesVO filterEspecidico = new FMConsultaDeteccionesVO();
			    filterEspecidico.setTIPO(null);
			    filterEspecidico.setRADAR(null);
			    filterEspecidico.setTOTAL(null);
			    filterEspecidico.setHistorico(null);
			    filterEspecidico.setTotalActuales(TotalActuales);
			    filterEspecidico.setTotalHistoricos(0);
			    filterEspecidico.setMARCA("todos");
			    responce.add(responce.size(), filterEspecidico);
			}
			
		} else {
			d=0;
			for (String TipoDeteccion : Radares){
				 for(i=0;i<DeteccionesCancelar.size();i++){
					 if(DeteccionesCancelar.get(i).getTOTAL().equals("0")){
						 
						}else{
							
					 if(TipoDeteccion.equals(DeteccionesCancelar.get(i).getTIPO()) && cabeza == 0){ 
						    FMConsultaDeteccionesVO filter = new FMConsultaDeteccionesVO();
						    filter.setTIPO(DeteccionesCancelar.get(i).getTIPO());
							filter.setRADAR(DeteccionesCancelar.get(i).getRADAR());
							filter.setTOTAL(DeteccionesCancelar.get(i).getTOTAL());
							filter.setHistorico("0");
							filter.setTotalActuales(0);
							filter.setTotalHistoricos(0);
							filter.setMARCA(DeteccionesCancelar.get(i).getMARCA());
							filter.setID(DeteccionesCancelar.get(i).getID());
							responce.add(d,filter);
							cabeza=1;
							d+=1;
					 }else if(TipoDeteccion.equals(DeteccionesCancelar.get(i).getTIPO()) && cabeza == 1){
						 FMConsultaDeteccionesVO filtersnDeteccion = new FMConsultaDeteccionesVO();
						 filtersnDeteccion .setTIPO(null);
						 filtersnDeteccion .setRADAR(DeteccionesCancelar.get(i).getRADAR());
						 filtersnDeteccion .setTOTAL(DeteccionesCancelar.get(i).getTOTAL());
						 filtersnDeteccion .setHistorico("0");
						 filtersnDeteccion .setTotalActuales(0);
						 filtersnDeteccion .setTotalHistoricos(0);
						 filtersnDeteccion.setMARCA(DeteccionesCancelar.get(i).getMARCA());
						 filtersnDeteccion.setID(DeteccionesCancelar.get(i).getID());
						 responce.add(d,filtersnDeteccion);
							//.out.print("Se creo renglon sin tipodedeteccion");
							d+=1;
					 }else{
		    			cabeza=0;
		    			continue;
		    		 }
				   }//fin para omitir 0
				 }
				 
			}//fin de arreglo
			
			if(TotalActuales== 0){
				
			}else{
			FMConsultaDeteccionesVO filter2 = new FMConsultaDeteccionesVO();
			filter2.setTIPO(null);
			filter2.setRADAR(null);
			filter2.setTOTAL(null);
			filter2.setHistorico(null);
			filter2.setTotalActuales(TotalActuales);
			filter2.setTotalHistoricos(0);
			filter2.setMARCA("todos");
			responce.add(d,filter2);
			
			}
		}
		return responce;
	}

	private String obtenerFechaFin(){
		 Calendar calendar = Calendar.getInstance();
	     calendar.setTime(new Date()); 
	     DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	     
	     int diaActual = calendar.get(Calendar.DATE);	      
	     if(diaActual>15){
	         calendar.add(Calendar.DAY_OF_YEAR, -diaActual);
	     }else{	             
	         calendar.add(Calendar.DAY_OF_YEAR, -diaActual);
	         calendar.add(Calendar.MONTH, -1);  
	      }
	     
	     return formatoFecha.format(calendar.getTime());
	}


	public String getMonth(int mes) {
		//int Mes = Integer.parseInt(numCadena);parseInt(mes);
		String month[] = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
		return month[mes-1];
	}

	@Override
	public List<FMConsultaDetalleDeteccionesVO> DeteccionesDetalle(String fechaInicio, String fechaFin, int tipoDeteccion,int tipoRadar, int origenPlaca) {
		List<FMConsultaDetalleDeteccionesVO> DeteccionesCancelarDetalle  = null;
		
		if(fechaInicio.equals("")){
			fechaInicio ="01/01/";
			fechaInicio = fechaInicio + CancelacionDetecciones.FechaMasViejaDeteccion(fechaFin); //FechaMasViejaDeteccion
		}
		DeteccionesCancelarDetalle = CancelacionDetecciones.consultaDetallesDetecciones(fechaInicio, fechaFin, tipoDeteccion, tipoRadar, origenPlaca);
		 for(int x=0 ;x<DeteccionesCancelarDetalle.size();x++){
			 if(DeteccionesCancelarDetalle.get(x).getOrigenPlaca()=="0"){
				 DeteccionesCancelarDetalle.get(x).setOrigenPlaca("CDMX");
			 }else{
				 DeteccionesCancelarDetalle.get(x).setOrigenPlaca("FORANEA"); 
			 }
		 }
		 
		 if(!DeteccionesCancelarDetalle.isEmpty()){
			 this.reporte = fmreporteDetalleDetecciones.generarReporteExcel(DeteccionesCancelarDetalle,"Detecciones a Cancelar", fechaInicio, fechaFin);
		   }
		 	
		// TODO Auto-generated method stub
		return DeteccionesCancelarDetalle;
	}

	@Override
	public Long DeteccionesCancelacionFM(String fechaCancelacion, long usuario, String fechaInicio, String fechaFin,int tipoDeteccion, int tipoRadar, int origenPlaca,String motivo) {
		if(fechaInicio.equals("")){
			fechaInicio ="01/01/";
			fechaInicio = fechaInicio + CancelacionDetecciones.FechaMasViejaDeteccion(fechaFin); //FechaMasViejaDeteccion
		}
		
		long resultadoDetecciones = CancelacionDetecciones.DeteccionesCancelarFM(fechaCancelacion, usuario, fechaInicio, fechaFin, tipoDeteccion, tipoRadar, origenPlaca,motivo);
		
		return resultadoDetecciones;
	}
	

	@Override
	public ByteArrayOutputStream descargaExcelDeteccMarcado() {
		return this.reporte;
	}

	

	
}
