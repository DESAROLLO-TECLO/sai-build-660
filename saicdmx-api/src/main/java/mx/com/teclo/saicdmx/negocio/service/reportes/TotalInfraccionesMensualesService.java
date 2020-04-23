package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.TotalInfraccionesMensualesVO;

public interface TotalInfraccionesMensualesService {
	
	/**
	 *Metodo traer el total de infracciones  
	 *parametros String fechainicio , fechaFin
	 *return */
	public List<TotalInfraccionesMensualesVO> consultaInfracciones(String fechaInicio,String fechaFin);
	
	/*Metodo para crear el excel con los resultados de la consulta*/
	ByteArrayOutputStream descargaExcelInfracMensuales();

}
