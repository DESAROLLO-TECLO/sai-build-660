package mx.com.teclo.saicdmx.persistencia.hibernate.dao.salidas;



import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.SalidasDTO;


public interface SalidaDAO extends BaseDao<SalidasDTO>{

	SalidasDTO guardarRegistroToSalida(SalidasDTO salidaDTO);

	SalidasDTO getInfraccionByIdSalida(String idSalidas);

	SalidasDTO buscaTrasladoUpdate(String infracNum, Long depId);



}
