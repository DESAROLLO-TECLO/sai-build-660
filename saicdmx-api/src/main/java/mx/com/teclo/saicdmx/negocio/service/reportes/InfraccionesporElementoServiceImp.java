package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes.InfraccionesporElementoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesporElementoVO;

@Service
public class InfraccionesporElementoServiceImp implements InfraccionesporElementoService {
	
	@Autowired 
	private InfraccionesporElementoMyBatisDAO consultaMyBatis;
	@Autowired
	private ExcelInfraccionesElemeto generaReporteExcel;
	
	private ByteArrayOutputStream  reporte;

	@Override
	public ByteArrayOutputStream descargaExcelInfraccionesporElemento() {
		// TODO Auto-generated method stub
		return this.reporte;
	}

	@Override
	public List<InfraccionesporElementoVO> consultaInfraccionesporElemento(InfraccionesporElementoVO parametrosBusquedaVO) {
		List<InfraccionesporElementoVO> infraccionesporElemento = consultaMyBatis.consultaIfraccionesElemento(parametrosBusquedaVO.getFechaInicio(),
																											  parametrosBusquedaVO.getFechaFin(),
																											  parametrosBusquedaVO.getIdPlacasOficiales());
		if(!infraccionesporElemento.isEmpty()){
			this.reporte = generaReporteExcel.generarInfraccionesElementoExcel(infraccionesporElemento,"Infracciones por Elemento",
																			   parametrosBusquedaVO.getFechaInicio(),
																			   parametrosBusquedaVO.getFechaFin(),
																			   parametrosBusquedaVO.getPlacasOficiales());
		}
		return infraccionesporElemento;
	}

}
