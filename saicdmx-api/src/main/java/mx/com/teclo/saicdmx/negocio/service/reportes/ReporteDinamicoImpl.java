package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes.PerfilesReportesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes.TipoReporteDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.PerfilesReportesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ReportesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoReportesDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes.ReporteDinamicoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.TipoReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesDiariasVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteInfraccionesGralVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReportesTaqLiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.RptInfraccionesEmpleado;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.TotalInfraccionesporArticuloVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@Service
public class ReporteDinamicoImpl implements ReporteDinamicoIService {
	
	
	@Autowired
	private ReporteDinamicoMyBatisDAO reporteDinamicoMyBatis;
	
	@Autowired
    private GeneraExcelService GeneraRptExcel;
	
	@Autowired
	private GeneraExcelInfraccionGral GeneraRptExcelGral;
	
	@Autowired
	private GenerarExcelInfraccArticulos RptExcelArticulos;
	
	@Autowired
	private GeneraExcelInfraccionesDelegaciones RptExcelDeglaciones;
	
	@Autowired
	private GeneraExcelTotalInfraccionesporArticulo generaExcelTotalArticulo;
    
	@Autowired
    private GeneraExcelInfraccionEmpleado infraccionEmpleadoExcel;
	
	private ByteArrayOutputStream  reporte;
	
	@Autowired
	PerfilesReportesDAO perfilesReporteDAO;
	
	@Autowired
	private TipoReporteDAO tipoReporte;
	
	

	@Override
	@Transactional
	public ReporteDinamicoVO obtenerListaReportes(Long idPerfil) throws NotFoundException{
		List<PerfilesReportesDTO> listaReportePerfilDTO = perfilesReporteDAO.ontenerReportesPorPerfil(idPerfil);
		if(listaReportePerfilDTO.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		List<TipoReportesDTO> listaTipos = tipoReporte.getAllReports();
		if(listaTipos.isEmpty())
			throw new NotFoundException("No se encontraron tipos de reportes");
		
		ReporteDinamicoVO objectReturn = new ReporteDinamicoVO();
		List<TipoReporteVO> listaTiposVO = new ArrayList<>();
		TipoReporteVO tipoVO = null;
		for(TipoReportesDTO tipoRep : listaTipos){
			tipoVO = new TipoReporteVO();
			ResponseConverter.copiarPropriedades(tipoVO, tipoRep);
			listaTiposVO.add(tipoVO);
		}
		objectReturn.setTipoReporte(listaTiposVO);
		
		List<ReportesTaqLiteVO> listReporteVO = new ArrayList<>();
		ReportesTaqLiteVO vo = null;
		for(PerfilesReportesDTO perfilRep : listaReportePerfilDTO){
			ReportesDTO dto = perfilRep.getReporte();
			vo = new ReportesTaqLiteVO(dto.getIdReporte(),dto.getNbReporte(),dto.getUrl(),dto.getTipoReporte().getIdTipoReporte(),dto.getTxUrlDinamic());
			listReporteVO.add(vo);
		}
		objectReturn.setReportes(listReporteVO);
		return objectReturn;
	}


	@Override
	public List<CatalogoDinamicoVO> catalogoNombreDepositos() {
		List<CatalogoDinamicoVO> catalogos = reporteDinamicoMyBatis.CatalogoNombreDepositos();
		return catalogos;
	}

	
	/*Infracciones diarias reporte */
	@Override
	public List<InfraccionesDiariasVO> InfraccionesDiarias(String fechaInicio) {
		List<InfraccionesDiariasVO> totales = reporteDinamicoMyBatis.ConsultaInfraccionesDiarias(fechaInicio);
		if(!totales.isEmpty()){
			 this.reporte = GeneraRptExcel.generarReporteExcel(totales,"Infracciones Diarias (Total por Depósito) ", fechaInicio,"");
		   }
		return totales;
	}
	
    /*infracciones Diarias detalle ,muestra deposito motivo y total */ 
	@Override
	public List<InfraccionesDiariasVO> InfraccionesDiariasDetalle(String fechaInicio) {
		//List<InfraccionesDiariasVO> infracciones = new ArrayList<InfraccionesDiariasVO>();
		List<InfraccionesDiariasVO> infracciones = reporteDinamicoMyBatis.ConsultaInfraccionesDiariasDetalle(fechaInicio);
		
		if(!infracciones.isEmpty()){
			 this.reporte = GeneraRptExcel.generarReporteExcel(infracciones,"Infracciones Diarias (Por Medio de Ingreso)", fechaInicio,"medio");
		   }
		return infracciones;
	}

/*Infracciones Gral Reporte*/
	@Override
	public List<ReporteInfraccionesGralVO> consultaInfraccionesGral(String fechaInicio, String fechaFin) {
		  List<ReporteInfraccionesGralVO> Infracciones = reporteDinamicoMyBatis.consultaDeteccionesGral(fechaInicio, fechaFin);
		 
		  if(!Infracciones.isEmpty()){
			  this.reporte = GeneraRptExcelGral.generarReporteExcel(Infracciones, "Infracciones General", fechaInicio, fechaFin,"","");
		  }
		return Infracciones;
	}
	
	/*Metodo para Crear REporte Excel infracciones Diarias  */ 
	@Override
	public ByteArrayOutputStream descargaExcelDeteccMarcado() {
		return this.reporte;
	}

/*Infracciones por articulo */
	@Override
	public List<CatalogoDinamicoVO> catalogoArticulosActivos() {
		List<CatalogoDinamicoVO> ArticulosActivos = reporteDinamicoMyBatis.CatalogoArticulos();
		return ArticulosActivos;
	}

	@Override
	public List<ReporteInfraccionesGralVO> consultaInfraccionesArticulo(ReporteInfraccionesGralVO ParametrosBusqueda) {
		String id="";
		for(int x = 0; x<ParametrosBusqueda.getId().size();x++){
			if(x + 1 == ParametrosBusqueda.getId().size()){
				id=id+ParametrosBusqueda.getId().get(x);
			}else{
				id=id+ParametrosBusqueda.getId().get(x)+",";
			}
		}
		List<ReporteInfraccionesGralVO> infracciones = reporteDinamicoMyBatis.consultaDeteccionesporArticulo(ParametrosBusqueda.getFechaInicio(),
																											 ParametrosBusqueda.getFechaFin(),
																											 id);
		if(!infracciones.isEmpty()){
			this.reporte = RptExcelArticulos.generarReporteExcel(infracciones, "Infracciones por Artículos",
					                                              ParametrosBusqueda.getFechaInicio(),
					                                              ParametrosBusqueda.getFechaFin(),
					                                              ParametrosBusqueda.getArticulo());
		}
		return infracciones;
	}

/*Reporte Infracciones por Delegaciones  */
	@Override
	public List<CatalogoDinamicoVO> catalogoDelegaciones() {
		List<CatalogoDinamicoVO> Delegaciones = reporteDinamicoMyBatis.catalogoDelegaciones();
		return Delegaciones;
	}


@Override
public List<ReporteInfraccionesGralVO> consultaInfraccionesDelegaciones(ReporteInfraccionesGralVO datos) {
	String id="";
	for(int x = 0; x<datos.getId().size();x++){
		if(x + 1 == datos.getId().size()){
			id=id+datos.getId().get(x);
		}else{
			id=id+datos.getId().get(x)+",";
		}
	}
	List<ReporteInfraccionesGralVO> infracciones = reporteDinamicoMyBatis.conusltaInfraccionesDelegacion(datos.getFechaInicio(),datos.getFechaFin(),id);
	if(!infracciones.isEmpty()){
		this.reporte = RptExcelDeglaciones.generarReporteExcel(infracciones, "Infracciones por Delegaciones",
				                                              datos.getFechaInicio(),
				                                              datos.getFechaFin(),
				                                              datos.getArticulo());
	}
	return infracciones;
}

/////Reporte infracciones por empleados 
@Override
public List<CatalogoDinamicoVO> consultaEmpleados(String PlacasOficial) {
		List<CatalogoDinamicoVO> empleados = reporteDinamicoMyBatis.consultaEmpleados(PlacasOficial);
		return empleados;
}

@Override
public List<RptInfraccionesEmpleado> consultaInfraccionesEmpleados(RptInfraccionesEmpleado ParametroBusqueda) {
	List<RptInfraccionesEmpleado> infraccionesEmpleado = reporteDinamicoMyBatis.consultaInfraccionesEmpleado(ParametroBusqueda.getFechaInicio(),
			                                                                                                 ParametroBusqueda.getFechaFin(),
			                                                                                                 ParametroBusqueda.getPlacasOficial());
	if(!infraccionesEmpleado.isEmpty()){
		this.reporte = infraccionEmpleadoExcel.generarReporteExcel(infraccionesEmpleado, "Infracciones por Empleado",
				                                                   ParametroBusqueda.getFechaInicio(),ParametroBusqueda.getFechaFin(),
				                                                   ParametroBusqueda.getPlacaBuscadas());
	}
	return infraccionesEmpleado;
}


@Override
public ByteArrayOutputStream descargaExcelInfraccionesEmpleados() {
	// TODO Auto-generated method stub
	return this.reporte;
}


@Override
public List<TotalInfraccionesporArticuloVO> consultaInfraccionesArticuloDetalle(TotalInfraccionesporArticuloVO ParametrosBusqueda) {
	String id="";
	for(int x = 0; x<ParametrosBusqueda.getId().size();x++){
		if(x + 1 == ParametrosBusqueda.getId().size()){
			id=id+ParametrosBusqueda.getId().get(x);
		}else{
			id=id+ParametrosBusqueda.getId().get(x)+",";
		}
	}
	List<TotalInfraccionesporArticuloVO> infracciones = reporteDinamicoMyBatis.consultaDeteccionesporArticuloDetalle(ParametrosBusqueda.getFechaInicio(),
																										             ParametrosBusqueda.getFechaFin(),
																										             id);
	if(!infracciones.isEmpty()){
		this.reporte = generaExcelTotalArticulo.generarReporteExcelTotalInfracc(infracciones, "Total Infracciones por Artículos",
				                                                                 ParametrosBusqueda.getFechaInicio(),
				                                                                 ParametrosBusqueda.getFechaFin(),
				                                                                 ParametrosBusqueda.getArticulo());
	}
	return infracciones;
}


@Override
public ByteArrayOutputStream TotalInfraccionesporArticuloExcel() {
	return this.reporte;
}







	

}
