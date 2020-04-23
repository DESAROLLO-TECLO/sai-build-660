package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes.TotalInfraccionesMensualesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.TotalInfraccionesMensualesVO;

@Service
public class TotalInfraccionesMensualesServiceImp implements TotalInfraccionesMensualesService {

	@Autowired 
	private TotalInfraccionesMensualesMyBatisDAO infraccionesMensualesDAO;
	@Autowired
	private GeneraExcelTotalInfraccionesMensuales generaExcel;
	
	private ByteArrayOutputStream  reporte;
	
	@Override
	public List<TotalInfraccionesMensualesVO> consultaInfracciones(String fechaInicio, String fechaFin) {
		 List<TotalInfraccionesMensualesVO> infraccionesMensuales = infraccionesMensualesDAO.obtenerInfraccionesMensuales(fechaInicio, fechaFin);
		 
		 if(!infraccionesMensuales.isEmpty()){
			 this.reporte = generaExcel.generarReporteExcel(infraccionesMensuales,"Total de Infracciones por Mes", fechaInicio, fechaFin);
		    }
		return infraccionesMensuales;
	}

	@Override
	public ByteArrayOutputStream descargaExcelInfracMensuales() {
		return this.reporte;
	}

}
