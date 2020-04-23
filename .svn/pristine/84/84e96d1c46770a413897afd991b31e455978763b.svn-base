package mx.com.teclo.saicdmx.persistencia.hibernate.dao.semoviarchivoslicencia;

import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviArchivosLicenciaDTO;


public interface SemoviArchivosLicenciaDAO extends BaseDao<SemoviArchivosLicenciaDTO> {
	
	/**
	 * @author UnitisDes0416
	 * @param nombreArchivo String
	 * @param fechaCreacion Date
	 * @return SemoviArchivosLicenciaDTO
	 */
	SemoviArchivosLicenciaDTO buscaArchivoNombreFechaCreacion(String nombreArchivo);
	
	/**
	 * @author UnitisDes0416
	 * @param nombreArchivo String
	 * @param fechaArchivo Date
	 * @return SemoviArchivosLicenciaDTO
	 */
	SemoviArchivosLicenciaDTO buscaArchivoNombreFechaArchivo(String nombreArchivo, Date fechaArchivo);
	/**
	 * @author javier07
	 * @param tipo
	 * @param fechaInicio
	 * @param fechaFin
	 * @return <Object[]>
	 */
	List<SemoviArchivosLicenciaDTO> buscarArchivosLicencia(Long tipo,Date fechaInicio,Date fechaFin);
	
	/**
	 * @author UnitisDes0416
	 * @param fechaArchivo Date
	 * @param codigoArchivo Integer
	 * @return List<SemoviArchivosLicenciaDTO>
	 */
	List<SemoviArchivosLicenciaDTO> buscaArchivoPorFecha(Date fechaArchivo, Integer codigoArchivo);
	
}
