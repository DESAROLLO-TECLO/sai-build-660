package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.naming.NamingException;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.GestionReportesVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ObjectCollectionVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ObjectGenericVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReportesTaqVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;

/**
 * 
 * @author javier07
 *
 */
public interface ReporteService {

	/**
	 * @author javier07
	 * @param empledoId
	 * @return
	 */
	List<ReporteVO> obtenerListaLinks(Long empledoId);
	
	/**
	 * Descripción: Método para consultar todos los reportes
	 * activos
	 * @author Jorge Luis
	 * @return List<ReportesTaqVO>
	 */
	public List<ReportesTaqVO> obtenerReportesActivos();
	
	
	/**
	 * Descripción: Método para consultar todos los perfiles
	 * que están ligados a sus reportes
	 * @author Jorge Luis
	 * @return List<GestionReportesVO>
	 */
	public GestionReportesVO getGestionReportes();
	
	/**
	 * Descripción: Método para guardar la configuración de permisos
	 * sobre los reportes
	 * @author Jorge Luis
	 * @param GestionReportesVO
	 * @return GestionReportesVO
	 */
	public GestionReportesVO persisteConfigReportePerf(GestionReportesVO voObject);
	
	/**
	 * Descripción: Método para ejecutar la consulta con JDBC,
	 * la consulta dinamica. Se desarrolló esté método 
	 * independiente para que no interfiera con el tiempo máximo de 
	 * transacción de hibernate.
	 * @author Jorge Luis
	 * @param HashMap<Object, Object>, ReportesTaqVO
	 * @return ObjectGenericVO
	 */
	public ObjectGenericVO executeQuery(HashMap<Object, Object> parametros, ReportesTaqVO vo)throws SQLException, NamingException;
	
	/**
	 * Descripción: Método para generar y descargar el 
	 * reporte en excel de los datos consultados, 
	 * las cabeceras y los titulos son dinamicos, 
	 * se adecuan con los datos que tiene el 
	 * reporte configurados en base de datos
	 * 
	 * @author Jorge Luis
	 * @param ObjectGenericVO, ReportesTaqVO
	 * @return ByteArrayOutputStream
	 * @throws IOException
	 */
	public ByteArrayOutputStream generateExcel(ObjectGenericVO objectData,ReportesTaqVO reporteVO) throws IOException, BusinessException, SQLException, NamingException;
	
	/**
	 * Descripción: Método para comprobar si el 
	 * reporte que se va consultar existe.
	 * @author Jorge Luis
	 * @param HashMap<Object, Object>
	 * @return ReportesTaqVO
	 */
	public ReportesTaqVO compruebaSiExisteReporteById(HashMap<Object, Object> params);
	
	/**
	 * Descripción: Método para consultar los parametros 
	 * de un reporte
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametroVO>
	 */
	public List<ParametrosVO> getParametrosByReporte(Long idReporte);
	
	/**
	 * Descripción: Método para filtrar los parametros de búsqueda,
	 * compara los que recibe de la consulta en front VS los que 
	 * recupera de la base de datos
	 * 
	 * @author Jorge Luis
	 * @param List<ParametroVO>, HashMap<Object, Object>
	 * @return List<LinkedHashMap<Object, Object>>
	 */
	public List<LinkedHashMap<Object, Object>> filtroParametrosBusqueda(List<ParametrosVO> parmsVO, HashMap<Object, Object> paramMap);
	
	/**
	 * Descripción: Método para construir el query que tiene asociado el reporte
	 * en base de datos
	 * 
	 * @author Jorge Luis
	 * @param ReportesTaqVO, HashMap<String, String>
	 * @return String
	 */
	public String makeQuery(ReportesTaqVO reporteVO, HashMap<Object, Object> parametros);
	
	/**
	 * Descripción: Esre método se hizo especialmente para los
	 * parametros que soportan multiples valores en la 
	 * calusula IN del QUERY
	 * 
	 * @author Jorge Luis
	 * @param String
	 * @return String
	 */
	public String getFormatMultiplesValores(String str);
	
	/**
	 * Descripción: Método para extraer todos 
	 * los resultados obtenidos de la cosnulta 
	 * con JDBC 
	 * @author Jorge Luis
	 * @param ResultSet
	 * @return List<LinkedHashMap<Object, Object>>
	 */
	public List<LinkedHashMap<Object, Object>> resultSetToArrayMap(ResultSet rs) throws SQLException;
	
	/**
	 * Descripción: Método para filtrar lo 
	 * resultados del ResultMap cuando el 
	 * reporte tiene alguna paginación configurada
	 * @author Jorge Luis
	 * @param String, List<LinkedHashMap<Object, Object>>, List<Object>
	 * @return List<Object>
	 */
	public List<Object> agrupaMapMultipleHoja(String columnPaginacion, List<LinkedHashMap<Object, Object>> listFilerRS,List<Object> valores);
	
	/**
	 * Descripci�n: Método para filtrar solo los parametros que 
	 * si vienen como parametro y eso aparecerá en el reporte
	 * @author Jorge Luis
	 * @param String, List<ObjectCollectionVO>
	 * @return Object
	 */
	public Object filtrarParametrosConValor(String valoresActuales, List<ObjectCollectionVO> listaRecuperadaBD);
	
	
	/**
	 * Descripción: Metodo para ejecutar la subconsulta con jdbc,
	 * la consulta dinamica. Se desarroll� est� m�todo 
	 * independiente para que no interfiera con el tiempo m�ximo de 
	 * transacci�n de hibernate.
	 * @author 
	 * @param HashMap<Object, Object>, ReportesTaqVO
	 * @return ObjectGenericVO
	 */
	public 	List<LinkedHashMap<Object, Object>> executeSubQuery(long idParametro,String valores)throws SQLException, NamingException;
	
	
	/**
	 * Descripci�n: M�todo para construir el query que tiene asociado el reporte
	 * en base de datos
	 * 
	 * @author Jorge Luis
	 * @param String, String, Long, String
	 * @return String
	 */
	public String makeSubQuery(String qwery,String valores,Long idParametro, String txSeparador);
	
	/**
	 * Descripció: Método para separar los 
	 * parametros recibidos de Front
	 * @author Jorge Luis
	 * @param String,String
	 * @return String
	 */
	public String separarValores(String valores,String txSeparador);
	
	/**
	 * Descripció: Método para concatenar objetos de una lista con comas ","
	 * @author Jorge Luis
	 * @param List<Object>
	 * @return String
	 */
	public String concatElements(List<Object> listElements);
	
}
