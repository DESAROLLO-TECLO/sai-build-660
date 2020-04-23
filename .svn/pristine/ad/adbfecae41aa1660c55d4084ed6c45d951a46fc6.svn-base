package mx.com.teclo.saicdmx.negocio.service.infracciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultadetecciones.InfraccionesEstadisticasMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.DepositosEstadisticasVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.EntradaSalidaDepositosGraficaVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesEstadisticasVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesEstadisticasporDispositivoVO;

@Service
public class InfraccionesEstadisticasServiceImpl implements InfraccionesEstadisticasService{

	@Autowired
	private InfraccionesEstadisticasMyBatisDAO infraccionesEstadisticasMyBatisDAO;

	@Override
	public List<InfraccionesEstadisticasVO> infraccionesEstadisticas(String fechaInicio, String fechaFin) {
		// TODO Auto-generated method stub
		
		List<InfraccionesEstadisticasVO> listPreValidAndValid=new ArrayList<InfraccionesEstadisticasVO>();
		listPreValidAndValid = infraccionesEstadisticasMyBatisDAO.infraccionesCreadasPagadas(fechaInicio, fechaFin);
		
		return listPreValidAndValid;
	}
	
	@Override
	public List<InfraccionesEstadisticasVO> infraccionesEstadisticasAños(String fechaInicio, String fechaFin) {
		// TODO Auto-generated method stub
		
		List<InfraccionesEstadisticasVO> listPreValidAndValid=new ArrayList<InfraccionesEstadisticasVO>();
		listPreValidAndValid = infraccionesEstadisticasMyBatisDAO.infraccionesCreadasPagadasAños(fechaInicio, fechaFin);
		
		return listPreValidAndValid;
	}
	
	@Override
	public List<InfraccionesEstadisticasVO> infraccionesCreadasPagadasEstatico() {
		// TODO Auto-generated method stub
		return infraccionesEstadisticasMyBatisDAO.infraccionesCreadasPagadasEstatico();
	}
		
	@Override
	public List<InfraccionesEstadisticasporDispositivoVO> infraccionesporDispositivo(String fechaInicio,
			String fechaFin) {
		// TODO Auto-generated method stub
		return infraccionesEstadisticasMyBatisDAO.infraccionesporDispositivo(fechaInicio, fechaFin);
	}
	
	@Override
	public List<InfraccionesEstadisticasporDispositivoVO> infraccionesporDispositivoEstatico() {
		// TODO Auto-generated method stub
		return infraccionesEstadisticasMyBatisDAO.infraccionesporDispositivoEstatico();
	}
	
	@Override
	public List<DepositosEstadisticasVO> depositosEstadisticas(){
		return infraccionesEstadisticasMyBatisDAO.depositosEstadisticas();
	}
	
	@Override
	public List<EntradaSalidaDepositosGraficaVO> entradaSalidaDepositosGrafica(String fechaInicio,String fechaFin){
		return infraccionesEstadisticasMyBatisDAO.entradaSalidaDepositosGrafica(fechaInicio, fechaFin);
	}
	
	@Override
	public List<EntradaSalidaDepositosGraficaVO> entradaSalidaDepositosGraficaFechas(String fechaInicio,String fechaFin){
		return infraccionesEstadisticasMyBatisDAO.entradaSalidaDepositosGraficaFechas(fechaInicio,fechaFin);
	}
	
}
