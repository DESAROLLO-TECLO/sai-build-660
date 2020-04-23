package mx.com.teclo.saicdmx.persistencia.mybatis.dao.placas;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.placas.PlacasVO;

@Mapper
public interface ConsultaPlacasMyBatis {

	//String CONSULTA_PLACAS = "SELECT id as placaId, PKG_ENCRIPCION.DESENCRIPTA(placa) as placaCodigo, activo as placaStatus, cd_creadopor as creadoPor, fh_creacion as fechaCreacion, cd_modificadopor as modificadoPor, fh_modificacion as ultimaModificacion,observaciones as observaciones "
		//	+ " FROM TAI006_FM_VALIDA_MATRICULA";
//	String CONSULTA_PLACAS = "SELECT id as placaId, PKG_ENCRIPCION.DESENCRIPTA(placa) as placaCodigo, activo as placaStatus,"+
//		" (select EMPP.EMP_PLACA from EMPLEADOS EMPP where EMPP.EMP_ID=cd_creadopor) as creadoPor, fh_creacion as fechaCreacion,"+
//			" (select EMPP.EMP_PLACA from EMPLEADOS EMPP where EMPP.EMP_ID=cd_modificadopor) as modificadoPor, fh_modificacion as ultimaModificacion,observaciones as observaciones "
//			+ " FROM TAI006_FM_VALIDA_MATRICULA";
	String CONSULTA_PLACAS = "SELECT id as placaId, PKG_ENCRIPCION.DESENCRIPTA(placa) as placaCodigo, activo as placaStatus, cd_creadopor as creadoPor, fh_creacion as fechaCreacion, cd_modificadopor as modificadoPor, fh_modificacion as ultimaModificacion,observaciones as observaciones, "
				+ " (select EMPP.EMP_PLACA from EMPLEADOS EMPP where EMPP.EMP_ID=cd_creadopor) as creadoPorS,"+
			" (select EMPP.EMP_PLACA from EMPLEADOS EMPP where EMPP.EMP_ID=cd_modificadopor) as modificadoPorS "+
				" FROM TAI006_FM_VALIDA_MATRICULA";

	String UPDATE_ESTATUS = "update TAI006_FM_VALIDA_MATRICULA set activo = #{status} , CD_MODIFICADOPOR= #{empId} , FH_MODIFICACION=sysdate " + " where id = #{placaId}";

	String CONSULTA_POR_PLACA = CONSULTA_PLACAS + " WHERE UPPER(PKG_ENCRIPCION.DESENCRIPTA(placa))  = #{valor}";
	String CONSULTA_POR_FECHA_CREACION = CONSULTA_PLACAS
			+ " WHERE TRUNC (fh_creacion) BETWEEN TO_DATE(#{fechaInicio},'DD/MM/YYYY') AND TO_DATE(#{fechaFin},'DD/MM/YYYY') ";
	String CONSULTA_POR_FECHA_MODIFICACION = CONSULTA_PLACAS
			+ " WHERE TRUNC (fh_modificacion) BETWEEN TO_DATE(#{fechaInicio},'DD/MM/YYYY') AND TO_DATE(#{fechaFin},'DD/MM/YYYY') ";
	String CONSULTA_POR_Placas_ID = CONSULTA_PLACAS + " WHERE id = #{valor}";
	// String CONSULTA_TODOS = CONSULTA_PLACAS +" WHERE id as placaId = #{valor}
	// " ;
	String CONSULTA_POR_FECHA_HOY_CREACION = CONSULTA_PLACAS + " WHERE TRUNC (fh_creacion) =  TO_DATE(#{fechaInicio},'DD/MM/YYYY')";
	String CONSULTA_POR_FECHA_AYER_CREACION = CONSULTA_PLACAS + " WHERE TRUNC (fh_creacion) = TO_DATE(#{fechaInicio},'DD/MM/YYYY')";
	String CONSULTA_POR_FECHA_HOY_MODIFICACION = CONSULTA_PLACAS + " WHERE TRUNC (fh_modificacion) = TO_DATE(#{fechaInicio},'DD/MM/YYYY')";
	String CONSULTA_POR_FECHA_AYER_MODIFICACION = CONSULTA_PLACAS + " WHERE TRUNC (fh_modificacion) = TO_DATE(#{fechaInicio},'DD/MM/YYYY')";

	@Select(CONSULTA_POR_FECHA_HOY_CREACION)
	List<PlacasVO> obtieneListaPlacasHoyC(@Param("fechaInicio") String fecha);

	@Select(CONSULTA_POR_FECHA_AYER_CREACION)
	List<PlacasVO> obtieneListaPlacasAyerC(@Param("fechaInicio") String fecha);

	@Select(CONSULTA_POR_FECHA_HOY_MODIFICACION)
	List<PlacasVO> obtieneListaPlacasHoyM(@Param("fechaInicio") String fecha);

	@Select(CONSULTA_POR_FECHA_AYER_MODIFICACION)
	List<PlacasVO> obtieneListaPlacasAyerM(@Param("fechaInicio") String fecha);

	@Select(CONSULTA_POR_PLACA)
	List<PlacasVO> obtieneListaPlacas(@Param("valor") String valor);

	@Select(CONSULTA_POR_FECHA_CREACION)
	List<PlacasVO> obtieneListaPlacasCreacionFH(@Param("fechaInicio") String fechaInicio,
			@Param("fechaFin") String fechaFin);

	@Select(CONSULTA_POR_FECHA_MODIFICACION)
	List<PlacasVO> obtieneListaPLacasModificacionFH(@Param("fechaInicio") String fechaInicio,
			@Param("fechaFin") String fechaFin);

	@Select(CONSULTA_POR_Placas_ID)
	List<PlacasVO> obtieneListaPlacasId(@Param("valor") String valor);

	@Select(CONSULTA_PLACAS)
	List<PlacasVO> obtieneListaPlacasTodos();

	@Select(UPDATE_ESTATUS)
	public String cambiarEstatus(@Param("status") int status, @Param("placaId") int placaId , @Param("empId") int empId);

}
