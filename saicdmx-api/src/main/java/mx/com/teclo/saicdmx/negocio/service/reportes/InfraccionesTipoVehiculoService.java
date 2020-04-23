package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesporTipoVehiculoVO;

import java.io.ByteArrayOutputStream;

public interface InfraccionesTipoVehiculoService {
	
	/**
	 * @author Fernando Octavio Sanchez Chavez
	 * @param fechaInicio 
	 * @param FechaFin
	 * @return <InfraccionesporTipoVehiculoVO>
	 * **/
	List<InfraccionesporTipoVehiculoVO> consultaInfraccionesTipoVehiculo(String fechaInicio,String fechaFin);

	/**
	 * Descripcion: metodo para generar excel de infracciones por tipo de vehiculo
	 * @author Fernando Octavio Sanchez Chavez
	 * @return ByteArrayOutputStream
	 * **/
	ByteArrayOutputStream InfraccionesTipoVehiculoExcel();
}
