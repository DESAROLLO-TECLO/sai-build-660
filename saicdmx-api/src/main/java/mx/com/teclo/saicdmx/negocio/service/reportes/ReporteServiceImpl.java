package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes.ReporteMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteVO;
import mx.com.teclo.saicdmx.util.documento.PeticionReporteService;

/**
 * 
 * @author javier07
 *
 */
@Service
public class ReporteServiceImpl implements ReporteService {

	@Value("${app.config.codigo}")
	private String cdApp;

	@Autowired
	private ReporteMyBatisDAO reporteMyBatisDAO;

	@Autowired
	@Qualifier("reporteExcel")
	private PeticionReporteService reporteExcel;
		
	@Override
	public List<ReporteVO> obtenerListaLinks(Long empledoId) {
		return reporteMyBatisDAO.getListaLinks(empledoId);
	}

}
