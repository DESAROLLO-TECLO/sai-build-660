package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParametrosTablasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReportesTaqVO;

public interface ParametrosService {

	/**
	 * Descripción: Método para construir el query 
	 * que se ejecutará para ontener los datos 
	 * que van a poblar los componentes seleccionables
	 * @author Jorge Luis
	 * @param ParametrosTablasDTO, List<ParametrosColumnDTO>
	 * @return String
	 */
	public String makeQueryForCollecction(ParametrosTablasDTO parametrosTabla, List<ParametrosColumnDTO> paramsColumnas);
	
	
	/**
	 * Descripci�n: M�todo para obtener el 
	 * reporte por su identificador unicos
	 * @author Jorge Luis
	 * @param Long
	 * @return ReportesTaqVO
	 */
	public ReportesTaqVO getReporteById(Long idReporte) throws SQLException, NamingException;
	
	/**
	 * Descripción: Método para obtener los 
	 * registros del catalogo
	 * @author Jorge Luis
	 * @param Long
	 * @return ReportesTaqVO
	 */
	public Map<Object, Object> getCatCollection(ParametrosVO parametroVO) throws SQLException, NamingException;
	
}
