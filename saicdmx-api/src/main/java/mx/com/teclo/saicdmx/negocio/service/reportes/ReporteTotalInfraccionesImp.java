package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes.ReporteDinamicoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes.ReporteTotalesInfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.rptInfraccionesMensuales;

@Service
public class ReporteTotalInfraccionesImp implements ReporteTotalInfraccionesI {
	
	@Autowired
	private ReporteTotalesInfraccionMyBatisDAO rptTotalInfracciones;
	@Autowired
	private ReporteDinamicoMyBatisDAO reporteDinamicoMyBatis;
	@Autowired
	private ReporteInfraccionesTotalMensual reporteTotal;
	
	private ByteArrayOutputStream  reporte;
	
	@Override
	public List<CatalogoDinamicoVO> obtenerListaDepositos() {
		List<CatalogoDinamicoVO> depositos = reporteDinamicoMyBatis.CatalogoNombreDepositos();
		return depositos;
	}

	@Override
	public List<CatalogoDinamicoVO> obtenerListaAnios() {
		List<CatalogoDinamicoVO> anios = rptTotalInfracciones.listaAniosDisponibles();
		return anios;
	}

	@Override
	public List<rptInfraccionesMensuales> obtenerInfraccionesMensuales(rptInfraccionesMensuales parametrosBusquedaVO) {
	
		List<rptInfraccionesMensuales> infraccionesMensuales = rptTotalInfracciones.totalInfraccionesMensuales(parametrosBusquedaVO.getDeposito().toString(),
				 																							   parametrosBusquedaVO.getAnio().toString(),
				 																							   Integer.toString(parametrosBusquedaVO.getMes()));
		if(!infraccionesMensuales.isEmpty()){
			this.reporte = reporteTotal.generaTotalInfraccionesMensual(infraccionesMensuales, "Total Infracciones Mensuales",
					                                                   parametrosBusquedaVO.getMes(),
					                                                   parametrosBusquedaVO.getAnio(),
					                                                   parametrosBusquedaVO.getDeposito());
		}
		
		
		return infraccionesMensuales;
	}

	@Override
	public ByteArrayOutputStream descargaExcelInfracMensuales() {
		return this.reporte;
	}

}
