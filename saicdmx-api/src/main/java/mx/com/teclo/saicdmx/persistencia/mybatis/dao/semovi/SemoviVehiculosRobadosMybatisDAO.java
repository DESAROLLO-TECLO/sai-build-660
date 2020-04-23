package mx.com.teclo.saicdmx.persistencia.mybatis.dao.semovi;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculoRobadoHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculosRobadosVO;




@Mapper
public interface 
SemoviVehiculosRobadosMybatisDAO {

	@Select("SELECT ROBADO.ID_ROBO as idRobo, "
			+" ROBADO.TURNO_DGANT as turnoDgant, "
			 +" ROBADO.EXPEDIENTE AS expediente, "
			 +" TO_CHAR(ROBADO.FECHA_ROBO, 'dd/MM/yyyy') as fechaRobo , "
			 +" ESTATUS AS ESTATUS , "
			 +" (SELECT VEHICULO_MARCA.VMAR_NOMBRE FROM VEHICULO_MARCA WHERE  VEHICULO_MARCA.VMAR_ID = ROBADO.MAR_ID) AS marca, "
			 +" ROBADO.NUM_MOTOR as numMotor, "
			 +" ROBADO.NUM_SERIE as numSerie, "
			 +" (SELECT AAO_VEHICULO.AAO FROM AAO_VEHICULO WHERE AAO_VEHICULO.AAO_VEH_ID = ROBADO.MOD_ANIO_ID) AS anio, "
			 +" (SELECT VEHICULO_MODELO.VMOD_NOMBRE FROM VEHICULO_MODELO WHERE VEHICULO_MODELO.VMOD_ID = ROBADO.MOD_ID and VEHICULO_MODELO.VMAR_ID = ROBADO.MAR_ID) AS modelo, "
			 +" ROBADO.PLACA_VEHICULO  as placaVehiculo, " 
			 +"   (SELECT  VEHICULO_COLOR.VCOLOR_NOMBRE FROM VEHICULO_COLOR WHERE VEHICULO_COLOR.VCOLOR_ID = ROBADO.COLOR_ID) AS color  "
			 +"     from VEHICULO_ROBADO ROBADO, VEHICULO_ROBADO_ESTATUS EST    "       
			 +"			 WHERE  "
			 +"	 		 	ROBADO.EXPEDIENTE =  #{valor} 	"		
			 +"			 	AND ROBADO.ID_ESTATUS = EST.ID_ESTATUS " 
			 +"			 	AND ROBADO.ACTIVO = 1 "
			 +"			 order by ROBADO.ID_ROBO desc  "
			
//			" SELECT ROBADO.ID_ROBO as idRobo, ROBADO.TURNO_DGANT as turnoDgant,  ROBADO.PLACA_VEHICULO  as placaVehiculo, ROBADO.NUM_SERIE as numSerie, ROBADO.NUM_MOTOR as numMotor, ESTATUS AS ESTATUS, "
//			+ " TO_CHAR(ROBADO.FECHA_ROBO, 'dd/MM/yyyy') as fechaRobo "
//			+ " from VEHICULO_ROBADO ROBADO, VEHICULO_ROBADO_ESTATUS EST "          
//			+ " WHERE "
//			+ " 	ROBADO.EXPEDIENTE = "				
//			+ " 	AND ROBADO.ID_ESTATUS = EST.ID_ESTATUS "
//			+ " 	AND ROBADO.ACTIVO = 1 "
//			+ " order by ROBADO.ID_ROBO desc "	
			)		
		List<VehiculosRobadosVO> buscarVehiculosRobados(
			@Param("valor") String valor);
	
	
	@Select(" SELECT ROBADO.ID_ROBO as idRobo, "
			+" ROBADO.TURNO_DGANT as turnoDgant, "
			 +" ROBADO.EXPEDIENTE AS expediente, "
			 +" TO_CHAR(ROBADO.FECHA_ROBO, 'dd/MM/yyyy') as fechaRobo , "
			 +" ESTATUS AS ESTATUS , "
			 +" (SELECT VEHICULO_MARCA.VMAR_NOMBRE FROM VEHICULO_MARCA WHERE  VEHICULO_MARCA.VMAR_ID = ROBADO.MAR_ID) AS marca, "
			 +" ROBADO.NUM_MOTOR as numMotor, "
			 +" ROBADO.NUM_SERIE as numSerie, "
			 +" (SELECT AAO_VEHICULO.AAO FROM AAO_VEHICULO WHERE AAO_VEHICULO.AAO_VEH_ID = ROBADO.MOD_ANIO_ID) AS anio, "
			 +" (SELECT VEHICULO_MODELO.VMOD_NOMBRE FROM VEHICULO_MODELO WHERE VEHICULO_MODELO.VMOD_ID = ROBADO.MOD_ID and VEHICULO_MODELO.VMAR_ID = ROBADO.MAR_ID) AS modelo, "
			 +" ROBADO.PLACA_VEHICULO  as placaVehiculo, " 
			 +"   (SELECT  VEHICULO_COLOR.VCOLOR_NOMBRE FROM VEHICULO_COLOR WHERE VEHICULO_COLOR.VCOLOR_ID = ROBADO.COLOR_ID) AS color  "
			 +"     from VEHICULO_ROBADO ROBADO, VEHICULO_ROBADO_ESTATUS EST "          
			+ " WHERE "
			+ " (CASE "
			+ "       WHEN ( #{valorBusqueda} = 'placa') AND ROBADO.PLACA_VEHICULO = #{valor} " 
			+ "         THEN 1 "
			+ "       WHEN ( #{valorBusqueda} = 'numSerie') AND ROBADO.NUM_SERIE = #{valor} " 
			+ "         THEN 1 "
			+ "       WHEN ( #{valorBusqueda} = 'numMotor') AND ROBADO.NUM_MOTOR = #{valor} "
			+ "         THEN 1 "
			+ "       WHEN ( #{valorBusqueda} = 'turno' ) AND ROBADO.TURNO_DGANT = #{valor} " 
			+ "         THEN 1 "
			+ "		  WHEN ( #{valorBusqueda} = 'exp' ) AND  ROBADO.EXPEDIENTE = #{valor}  "
			+ "         THEN 1 "
			+ "      END) = 1 "
			+ " AND ROBADO.ID_ESTATUS = EST.ID_ESTATUS "
			+ " AND ROBADO.ACTIVO = 1 "
			+ " order by ROBADO.ID_ROBO desc"
	
			)		
		List<VehiculosRobadosVO> buscarVehiculosRobadosConsulta(
			@Param("valorBusqueda")String opcion, 
			@Param("valor") String valor);

	
	
	@Select(" select VR.ID_ROBO AS idRobo, VR.ID_ESTATUS AS idEstatus, ESTATUS, "
			+ " TO_CHAR(VR.FECHA_MODIFICACION, 'dd/MM/yyyy HH24:MI:SS') as fechaModificacion "
			+ " from VEHICULO_ROBADO_HISTORICO VR, VEHICULO_ROBADO_ESTATUS VE  "
			+ " WHERE ID_ROBO = #{idRobo} AND VR.ID_ESTATUS = VE.ID_ESTATUS order by VR.FECHA_MODIFICACION desc ")
	List<VehiculoRobadoHistoricoVO> buscarHistoricoVeh(@Param("idRobo") Long idRobo);
	
	
	@Select( "select ROBADO.ID_ROBO as idRobo ,ROBADO.TURNO_DGANT as turnoDgant,ROBADO.EXPEDIENTE,TO_CHAR(ROBADO.FECHA_ROBO, 'dd/MM/yyyy') as fechaRobo,ROBADO.PLACA_VEHICULO as placaVehiculo,ROBADO.NUM_MOTOR as numMotor,ROBADO.NUM_SERIE as numSerie,"
			+ " ROBADO.MAR_ID as idMar,ROBADO.MOD_ID as idMod,ROBADO.MOD_ANIO_ID as idAnio,ROBADO.COLOR_ID as idColor ,ROBADO.ID_ESTATUS as idEstatus,"
			+ " ROBADO.PLACA_VEHICULO , VMAR_NOMBRE as marca, VCOLOR_NOMBRE as color, VMOD_NOMBRE as modelo, AAO as ANIO, ESTATUS AS ESTATUS  "
			+ " from VEHICULO_ROBADO ROBADO, VEHICULO_MARCA MARCA ,"
			+ " VEHICULO_MODELO TIPO_MODELO,VEHICULO_COLOR COLOR, AAO_VEHICULO ANIO , VEHICULO_ROBADO_ESTATUS EST "
			+ " WHERE  ROBADO.MAR_ID = MARCA.VMAR_ID "
			+ " AND ROBADO.MOD_ID = TIPO_MODELO.VMOD_ID "
			+ " and TIPO_MODELO.VMAR_ID = MARCA.VMAR_ID "
			+ " AND ROBADO.COLOR_ID = COLOR.VCOLOR_ID "
			+ " AND ROBADO.MOD_ANIO_ID = ANIO.AAO_VEH_ID  "
			+ " AND ROBADO.ID_ESTATUS = EST.ID_ESTATUS"
			+ " AND ROBADO.ID_ROBO = #{idRobo}" 
			+ " AND ROBADO.ACTIVO = 1  " )
	VehiculosRobadosVO detalleDatosVehiculo(@Param("idRobo") Long idRobo);


	@Select("select AAO_VEH_ID as codigo, AAO as descripcion from AAO_VEHICULO order by AAO desc")
	List<FilterValuesVO> buscarModelo();
	
	
	 @Select("select VMOD_ID AS codigo, VMOD_NOMBRE descripcion "
	 		+ " from VEHICULO_MODELO where VMOD_STATUS='A' and VMAR_ID = #{idMarca}  ORDER BY VMOD_NOMBRE")
	 List<FilterValuesVO> buscarTipo(@Param("idMarca") Long idMarca);


	@Select("select nvl( max(id_robo)+1,1) from VEHICULO_ROBADO ")
	Long ultimoIdRobo();

	@Select("SELECT nvl( max(id_expediente)+1,1) from TAI011D_AUTO_EXPEDIENTES")
	Long ultimoExpediente();
	 
	@Insert("insert into VEHICULO_ROBADO_HISTORICO values(#{nextRobo},#{status},sysdate,#{idUsuario})")
	int insertaHistoricoRoboVehiculo(@Param("nextRobo") Long nextRobo, @Param("status") Long status, @Param("idUsuario") Long idUsuario);

	@Select("select  TAI011D_AUTO_EXPEDIENTES.ID_EXPEDIENTE from TAI011D_AUTO_EXPEDIENTES where CD_EXPEDIENTE = #{cdExpediente}")
	Long existExpediente(@Param("cdExpediente") String expediente);


	@Insert("INSERT INTO  TAI011D_AUTO_EXPEDIENTES (ID_EXPEDIENTE,CD_EXPEDIENTE, ID_CREADOPOR, FH_CREACION) "
			+" VALUES( #{idExp}, #{expediente}  , #{emp}, sysdate ) ")
	boolean crearExpediente(@Param("idExp") Long idExpediente, 
			@Param("expediente")String expediente, @Param("emp")Long emp);


	@Select("select  id_estatus as codigo, estatus as descripcion from VEHICULO_ROBADO_ESTATUS where id_estatus > 1 ")
	List<FilterValuesVO> getEstatusVehiculo();

	@Update(" UPDATE VEHICULO_ROBADO SET "
            +"    VEHICULO_ROBADO.ACTIVO = 0, "
            +"    VEHICULO_ROBADO.ULTIMA_MODIFICACION = SYSDATE, "
            +"    VEHICULO_ROBADO.MODIFICADO_POR = #{modificadoPor} "                
            +"   WHERE VEHICULO_ROBADO.ID_ROBO = #{idRobo} ")
	void cancelarRob(
			@Param("idRobo") Long idRobo, 
			@Param("modificadoPor") Long id);

	@Select(" select   VEHICULO_ROBADO.ID_ROBO   from VEHICULO_ROBADO "
			+ " where VEHICULO_ROBADO.EXPEDIENTE = #{exp} "
			+ " and VEHICULO_ROBADO.TURNO_DGANT = #{turno} "
			+ " and VEHICULO_ROBADO.ACTIVO = 1 ")
	Long verificaReporteRobo(
			@Param("exp")String exp, 
			@Param("turno") String turno);

	@Update(" UPDATE TAI011D_AUTO_EXPEDIENTES "
			+ "	SET CD_EXPEDIENTE = #{expNew}  WHERE CD_EXPEDIENTE = #{expOld} ")
	void updateExpedienteActivos(
			@Param("expNew") String expNew, 
			@Param("expOld") String expOld);

	@Update(" update VEHICULO_ROBADO "
			+ " set  expediente = #{expNew} "
			+ " where expediente =  #{expOld} "
			+ " and activo = 1")
	void updateExpedientExpRob(
			@Param("expNew") String expNew, 
			@Param("expOld") String expOld);

	@Select("select  TAI011D_AUTO_EXPEDIENTES.CD_EXPEDIENTE as expediente  from TAI011D_AUTO_EXPEDIENTES where ID_EXPEDIENTE  = #{idExp} ")
	List<VehiculosRobadosVO> existExpedienteName(@Param("idExp") Long idExp);
	
}