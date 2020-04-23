package mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaLoteLCMVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.InfraccionLoteLCMVO;

@Mapper
public interface ConsultaLCMMyBatisDAO {
	
	String GET_LOTE_FOLIOS = "SELECT i.LINEA_CAPTURA, i.IMPORTE, i.NUMERO_DIAS, i.SALARIO_MINIMO, "
						   + "TO_CHAR(i.FECHA_CREACION, 'DD/MM/YYYY HH24:mm') AS FECHA_CREACION, i.DESCUENTO, "
						   + "i.TOTAL, i.RECARGOS, i.INFRAC_NUM, e.EMP_NOMBRE,e.EMP_APE_PATERNO,  e.EMP_APE_MATERNO, "
						   + "e.EMP_PLACA, TO_CHAR(i.FECHA_REASIGNACION, 'DD/MM/YYYY') AS FECHA_REASIGNACION, "
						   + "TO_CHAR(i.VIGENCIA, 'DD/MM/YYYY') AS VIGENCIA, i.ESTATUS_RESULTADO "
						   + "FROM INFRACCIONES_LC_REASIGNA i "
						   + "LEFT JOIN EMPLEADOS e ON i.CREADO_POR = e.EMP_ID "
						   + "LEFT JOIN INFRACCIONES_RADAR r ON i.INFRAC_NUM = r.INFRAC_NUM "
						   + "WHERE i.TIPO_PROCESO = 1 and i.REASIGNA_LOTE_ID = ${lote} "
						   + "ORDER BY i.FECHA_REASIGNACION DESC";
	
	String SQL_CONSULTA_LOTE_MASIVO = "SELECT l.ID, l.NOMBRE, TO_CHAR(l.FECHA_REASIGNACION, 'dd/MM/yyyy') AS FECHA_REASIGNACION, "
									+ "l.CANTIDAD_PROCESADOS, "
									+ "l.CANTIDAD_CANCELADOS, "
									+ "(SELECT COUNT(*) FROM INFRACCIONES_LC_REASIGNA r WHERE r.TIPO_PROCESO != 0 AND r.REASIGNA_LOTE_ID = l.ID) AS TOTAL, "
									+ "TO_CHAR(l.FECHA_EMISION, 'dd/MM/yyyy') AS FECHA_EMISION "
									+ "FROM INFRACCIONES_LC_REASIGNA_LOTE l ";
	
	String GET_WITH_FECHA_INICIO_FIN = SQL_CONSULTA_LOTE_MASIVO 
									 + "where TRUNC(fecha_reasignacion)  "
									 + "between TO_DATE (#{fechaInicio},'dd/mm/yyyy') "
									 + "and TO_DATE(#{fechaFin},'dd/mm/yyyy')"
									 + "and ROWNUM <=40000 "
									 + "ORDER BY ID DESC";
	
	String GET_WITH_FECHA_INICIO = SQL_CONSULTA_LOTE_MASIVO
								 + "where TRUNC(fecha_reasignacion)  "
								 + "between TO_DATE (#{fechaInicio},'dd/mm/yyyy') "
								 + "and SYSDATE and ROWNUM <=40000 ORDER BY ID DESC";
	
	String GET_WITH_FECHA_FIN = SQL_CONSULTA_LOTE_MASIVO
							  + "where TRUNC(fecha_reasignacion)  "
							  + "between TO_DATE ('01/01/1500','dd/mm/yyyy') "
							  + "and TO_DATE (#{fechaFin},'dd/mm/yyyy') "
							  + "and ROWNUM <=40000"
							  + "ORDER BY ID DESC";
	
	String GET_WITHOUT_FECHA = SQL_CONSULTA_LOTE_MASIVO
							 + "and ROWNUM <=40000 ORDER BY ID DESC";
	
	String GET_WITH_PARAMETROS = SQL_CONSULTA_LOTE_MASIVO
							+"WHERE "
							+ "	(CASE "
							+ "		WHEN(${cbTipoFecha} = 1)"
							+ "			THEN 1 "
							+ "		WHEN(${cbTipoFecha} = 2 AND #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NOT NULL) "
							+ "								AND TRUNC (l.FECHA_REASIGNACION, 'dd') "
							+ "									BETWEEN TO_DATE( #{fechaInicio} ,'dd/mm/yyyy') "
							+ "										AND TO_DATE( #{fechaFin},'dd/mm/yyyy') "
							+ "			THEN 1 "
							+ "		WHEN(${cbTipoFecha} = 3 AND #{fechaInicio}  IS NOT NULL AND #{fechaFin} IS NOT NULL) "
							+ "								AND TRUNC (l.FECHA_EMISION, 'dd') "
							+ "									BETWEEN TO_DATE( #{fechaInicio} ,'dd/mm/yyyy') "
							+ "										AND TO_DATE( #{fechaFin},'dd/mm/yyyy') "
							+ "			THEN 1 "
							+ "		END "
							+ "	) = 1 "
							+ "	AND "
							+ "	(CASE "
							+ "		WHEN(${cbCampoBusqueda} = 1)"
							+ "			THEN 1 "
							+ "		WHEN(${cbCampoBusqueda} = 2 AND #{idLote} IS NOT NULL) AND l.ID = #{idLote} "
							+ "			THEN 1 "
							+ "		WHEN(${cbCampoBusqueda} = 3 AND #{nameLote} IS NOT NULL) AND LOWER(l.NOMBRE) LIKE '%' || #{nameLote} || '%' "
							+ "			THEN 1 "
							+ "     END "
							+ "	) = 1 "
							+ "	AND "
							+ "	(CASE "
							+ "		WHEN(${cbEstatusLotes} = 1)"
							+ "			THEN 1 "
							+ "		WHEN(${cbEstatusLotes} = 2) AND l.REASIGNADO = 1 "
							+ "			THEN 1 "
							+ "		WHEN(${cbEstatusLotes} = 3) AND l.CANCELADO = 1 "
							+ "			THEN 1 "
							+ "		WHEN(${cbEstatusLotes} = 4) AND l.PROCESANDO = 1 "
							+ "			THEN 1 "
							+ "		END "
							+ "	) = 1 "
							+ "and ROWNUM <=40000 "
							+ "ORDER BY l.ID DESC";
	
	String GET_DATOS_LOTE = "SELECT ID, NOMBRE, "
						+ "TO_CHAR(FECHA_EMISION, 'dd/MM/yyyy') AS FECHA_EMISION "
						+ "FROM INFRACCIONES_LC_REASIGNA_LOTE "
						+ "WHERE ID = ${lote}";
	
	@Select(value = GET_WITH_FECHA_INICIO_FIN)
	public List<InfraccionLoteLCMVO> obtenerPorFechasInicioFin(@Param("fechaInicio")String fechaInicio, 
															   @Param("fechaFin")String fechaFin);
	
	@Select(value = GET_WITH_FECHA_INICIO)
	public List<InfraccionLoteLCMVO> obtenerPorFechaInicio(@Param("fechaInicio")String fechaInicio);
	
	@Select(value = GET_WITH_FECHA_FIN)
	public List<InfraccionLoteLCMVO> obtenerPorFechaFin(@Param("fechaFin")String fechaFin);
	
	@Select(value = GET_WITHOUT_FECHA)
	public List<InfraccionLoteLCMVO> obtenerSinFecha();
	
	@Select(value = GET_LOTE_FOLIOS)
	public List<ConsultaLoteLCMVO> obtenerLoteFolios(@Param("lote")String lote);
	
	@Select(value = GET_WITH_PARAMETROS)
	public List<InfraccionLoteLCMVO> obtenerLoteFoliosConParametros(
															@Param("fechaInicio")String fechaInicio, 
															@Param("fechaFin")String fechaFin, 
															@Param("cbCampoBusqueda")Integer cbCampoBusqueda, 
															@Param("idLote")Integer idLote, 
															@Param("nameLote")String nameLote, 
															@Param("cbTipoFecha")Integer cbTipoFecha, 
															@Param("cbEstatusLotes")Integer cbEstatusLotes);
	

	@Select(value = GET_DATOS_LOTE)
	public InfraccionLoteLCMVO obtenerDatosLote(@Param("lote")String lote);
}
