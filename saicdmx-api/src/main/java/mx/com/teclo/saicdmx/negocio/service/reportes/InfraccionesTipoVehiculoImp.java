package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes.InfraccionesporTipoVehiculoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesporTipoVehiculoVO;

@Service
public class InfraccionesTipoVehiculoImp implements InfraccionesTipoVehiculoService{
	@Autowired
	private InfraccionesporTipoVehiculoMyBatisDAO infraccionTipoVehiculo;
	@Autowired
	private InfraccionesTipoVehiculoExcel excelTipoVehiculo;
	
	private ByteArrayOutputStream  reporteExcel;
	

	@Override
	public List<InfraccionesporTipoVehiculoVO> consultaInfraccionesTipoVehiculo(String fechaInicio, String fechaFin) {
		List <InfraccionesporTipoVehiculoVO> infracciones = infraccionTipoVehiculo.consultaInfraccionesTipoVehiculo(fechaInicio, fechaFin);
		if(!infracciones.isEmpty()){
			this.reporteExcel = excelTipoVehiculo.generarExcelInfraccionesTipoElemento(infracciones,
					                                                                   "Infracciones por Tipo de Vehiculo Infomex", 
					                                                                   fechaInicio,
					                                                                   fechaFin);
		}
		return infracciones;
	}


	@Override
	public ByteArrayOutputStream InfraccionesTipoVehiculoExcel() {
		return this.reporteExcel;
	}






}
