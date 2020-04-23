package mx.com.teclo.saicdmx.persistencia.hibernate.dao.bitacora;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bitacora.BitacoraCambiosDTO;

@Repository
public 	class 		BitacoraCambiosDAOImpl
		extends 	BaseDaoHibernate<BitacoraCambiosDTO>
		implements 	BitacoraCambiosDAO
{
	private static final Logger logger = Logger.getLogger(BitacoraCambiosDAOImpl.class);

	@Override
	public BitacoraCambiosDTO buscarBitacoraCambios(Long idBitacoraCambios) 
	{
		logger.info("ENTRANDO A buscarBitacoraCambios --OK");
		BitacoraCambiosDTO bitacoraCambiosDTO = findById(idBitacoraCambios);		
		logger.info("SALIENDO DE buscarBitacoraCambios --OK");
		return bitacoraCambiosDTO;
	}

	@Override
	public Long guardarBitacoraCambios(BitacoraCambiosDTO bitacoraCambiosDTO) 
	{
		logger.info("ENTRANDO A guardarBitacoraCambios --OK");
		logger.info("DATOS A GUARDAR EN LA BITACORA: " + bitacoraCambiosDTO.toString());
		Long idBitacora = (Long) this.save(bitacoraCambiosDTO);
		logger.info("SALIENDO DE guardarBitacoraCambios --OK");
		
		return idBitacora;
	}

}
