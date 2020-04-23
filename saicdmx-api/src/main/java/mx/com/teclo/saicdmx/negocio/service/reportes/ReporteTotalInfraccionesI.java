package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.rptInfraccionesMensuales;


public interface ReporteTotalInfraccionesI {
	
	/*Infracciones por mensualidades */
	List<CatalogoDinamicoVO> obtenerListaDepositos();
	List<CatalogoDinamicoVO> obtenerListaAnios();
	List<rptInfraccionesMensuales> obtenerInfraccionesMensuales(rptInfraccionesMensuales parametrosBusquedaVO);
	ByteArrayOutputStream descargaExcelInfracMensuales();

}
