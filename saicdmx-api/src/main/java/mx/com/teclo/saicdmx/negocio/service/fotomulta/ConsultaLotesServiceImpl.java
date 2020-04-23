package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultalotes.FotomultaLotesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ConsultaLotesVO;

@Service
public class ConsultaLotesServiceImpl implements ConsultaLotesService{

	@Autowired 
	private FotomultaLotesMyBatisDAO fotomultaLotesMyBatisDAO;
	
	@Override
	public List<ConsultaLotesVO> consultaLotes(String fechaInicio, String fechaFin, Long tipoRadar, Integer estatusProceso, Integer tipoFecha, Integer archivoTipo) {
		List<ConsultaLotesVO> listLotes = fotomultaLotesMyBatisDAO.consultaLotesPorFechas(fechaInicio, fechaFin, tipoRadar, estatusProceso, tipoFecha,archivoTipo );
		return listLotes;
	}

}
