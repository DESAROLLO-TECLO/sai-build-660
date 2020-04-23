package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.prepago.services.BusinessException;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes.VistaInfraccionesAnualesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.VistaDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes.ReporteInfraccionesAnualesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.infraccionesAnualesVO;

@Service
public class InfraccionesAnualesServiceImp implements InfraccionesAnualesService {

	@Autowired
	private VistaInfraccionesAnualesDAO vistaInfraccionesDAO;
	@Autowired
	private ReporteInfraccionesAnualesMyBatisDAO infraccionesAnualesMyBatis;
	@Autowired 
	private GenerarExcelInfraccionesAnuales generaExcel;
	
	private ByteArrayOutputStream  reporte;
	
	@Override
	public List<VistaDTO> consultaAniosInfraccion() throws BusinessException {
		List<VistaDTO> ListaAnios = vistaInfraccionesDAO.getInfo();
		return ListaAnios;
	}

	@Override
	public List<infraccionesAnualesVO> consultaInfraccionesAnuales(Long anio) throws BusinessException {
		List<infraccionesAnualesVO> infracciones = infraccionesAnualesMyBatis.obtenerInfraccionesAnuales(anio);
		
		if(!infracciones.isEmpty()){
			this.reporte = generaExcel.generarReporteExcel(infracciones,"Infracciónes Anuales (Total Mensual)",anio);
		}
		return infracciones;
	}

	@Override
	public ByteArrayOutputStream descargaExcelInfraccionesAnuales() {
		// TODO Auto-generated method stub
		return this.reporte;
	}

	/*Metodos infracciones TOtal Anuales */
	@Override
	public List<infraccionesAnualesVO> consultaInfrraccionesTotalAnual(Long anio) throws BusinessException {
		List<infraccionesAnualesVO> infraccionesTotalAnual = infraccionesAnualesMyBatis.consultaInfraccionesAnualesTotal(anio);		
		if(!infraccionesTotalAnual.isEmpty()){
			this.reporte = generaExcel.generarReporteExcel(infraccionesTotalAnual, "Infracciónes Anules (Total Anual) ",anio);
		}
		return infraccionesTotalAnual;
	}

	
}
