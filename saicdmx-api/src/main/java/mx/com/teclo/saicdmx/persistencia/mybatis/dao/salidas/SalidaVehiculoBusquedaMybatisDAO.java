package mx.com.teclo.saicdmx.persistencia.mybatis.dao.salidas;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaTrasladoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaVehiculoSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarTrasladoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.InfoPlacaEmpVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.SalidaVehiculoBusqVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ValidarInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.busquedaCatSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.resultCatVO;

@Mapper
public interface SalidaVehiculoBusquedaMybatisDAO {


	String GET_VEHICULO_BUSQUEDA =  "SELECT NUM_CTRL as infracNumCtrl,NUM_INFRAC as infracNum,NUM_PLACA as placaNum,MARCA as marcaV,NOM_MARCA as nomMarca, " 
	+" MODELO as modeloV ,NOM_MODELO as nomModelo ,COLOR as colorV,NOM_COLOR as nomColor,TO_CHAR(FECHA_INGRESO,'dd/mm/yyyy')  as ingresoFecha, "
	+" DEPOSITO as numDeposito,NOM_DEP as nomDep,NUM_RESGUARDO as numResguardo,NUM_DOCTO as numDocto,SALIDA_TIPO_ID as tipoSalida "
	+" FROM V_SALIDAS_VEH_BUSQ ";
	
	String GET_DEPID = "select DEP_ID from DEPOSITOS_EMPLEADOS where EMP_ID = #{empId} ";
	
	String GET_NUMINFRAC = GET_VEHICULO_BUSQUEDA + "WHERE NUM_INFRAC = #{valorBusq} "
			+ "AND deposito = #{depId} ";
	
	String GET_NUM_PLACA = GET_VEHICULO_BUSQUEDA + "WHERE NUM_PLACA = #{valorBusq} "
			+ "AND deposito = #{depId} ";
	
	String GET_NUM_INFRAC_IMPRESA = GET_VEHICULO_BUSQUEDA + "WHERE NUM_INFRAC_IMPRESA = #{valorBusq} "
			+ "AND deposito = #{depId} ";
	
	String GET_NUM_DOCTO = GET_VEHICULO_BUSQUEDA + "WHERE NUM_DOCTO = #{valorBusq} "
			+ "AND deposito = #{depId} ";
	
	String GET_NUM_RESGUARDO = GET_VEHICULO_BUSQUEDA + "WHERE NUM_RESGUARDO = #{valorBusq} "
			+ "AND deposito = #{depId} ";
	
	String GET_NUM_CTRL = GET_VEHICULO_BUSQUEDA + "WHERE NUM_CTRL = #{valorBusq} "
			+ "AND deposito = #{depId} ";
	
	
	
	@Select(GET_NUMINFRAC)
	List<SalidaVehiculoBusqVO> busquedaVehiculoInfrac(
			@Param("valorBusq")String valorBusq,
			@Param("depId")String depId
			);
	@Select(GET_NUM_PLACA)
	List<SalidaVehiculoBusqVO> busquedaVehiculoNumPlaca(
			@Param("valorBusq")String valorBusq,
			@Param("depId")String depId
			);
	@Select(GET_NUM_INFRAC_IMPRESA)
	List<SalidaVehiculoBusqVO> busquedaVehiculoInfracImpr(
			@Param("valorBusq")String valorBusq,
			@Param("depId")String depId
			);
	@Select(GET_NUM_DOCTO)
	List<SalidaVehiculoBusqVO> busquedaVehiculoDoct(
			@Param("valorBusq")String valorBusq,
			@Param("depId")String depId
			);
	@Select(GET_NUM_RESGUARDO)
	List<SalidaVehiculoBusqVO> busquedaVehiculoResguardo(
			@Param("valorBusq")String valorBusq,
			@Param("depId")String depId
			);
	@Select(GET_NUM_CTRL)
	List<SalidaVehiculoBusqVO> busquedaVehiculoNumCtrl(
			@Param("valorBusq")String valorBusq,
			@Param("depId")String depId
			);
	
	@Select(GET_DEPID)
	String  busquedaDepId(
			@Param("empId")Long empId
			);
	
	@Select("SELECT case when count(*)>0 then 1 else 0 end as result "
			+ "FROM INGRESOS_MOV_HIST "
			+ "WHERE INFRAC_NUM_MOVS = #{infracNum} "
			+ "AND ESTATUS_MOVIMIENTO=1 ")	    
	boolean  busquedaTransaladoPr(
			@Param("infracNum")String infracNum
			);
	
	@Select("SELECT NUM_CTRL as infracNumCtrl ,NUM_INFRAC as infracNum,NUM_PLACA as placaNum ,NUM_SERIE as numSerie, "
    +"DEPOSITO as numDeposito,TO_CHAR(FECHA_INGRESO,'dd/mm/yyyy')  as ingresoFecha, " 
    +"NUM_RESGUARDO as numResguardo,NOM_DEP as nomDep,  NOM_MARCA as nomMarca, NOM_MODELO as nomModelo ,NOM_COLOR as nomColor "
    +"FROM V_SALIDAS_VEH_BUSQ "
    +" WHERE NUM_INFRAC = #{numInfrac}" )
	SalidaVehiculoBusqVO infoInfraccion(
			@Param("numInfrac")String numInfrac);
	
	@Select("SELECT ID_DEST as codigoString, DESTINO as descripcion "
                + "FROM CAT_DEST_ADJUDICACION "
                + "WHERE ESTATUS='A' "
                + "ORDER BY ID_DEST,DESTINO ")
	List<FilterValuesVO> comboAdjunDestino();
	
	@Select("SELECT ID_FASE as codigo ,FASE as descripcion "
            + "FROM CAT_FASES_COMPACTACION "
            + "WHERE ESTATUS='A' "
            + "ORDER BY ID_FASE,FASE ")
	List<FilterValuesVO> comboFaseCompacta();
	
	@Select("SELECT  INFRAC_PAGADA AS idInfracPagada, DECODE(INFRAC_PAGADA,'S','INFRACCIÓN PAGADA','N','INFRACCIÓN NO PAGADA') pagoInfrac "
            +"FROM INFRACCIONES "
            +"WHERE INFRAC_NUM = #{numInfrac} ")
	ValidarInfraccionVO validarPlaca(
			@Param("numInfrac")String infracNum);
	
	@Select("SELECT DEP_ID as codigo, DEP_NOMBRE as descripcion "
             +" FROM DEPOSITOS  "
             +" WHERE  "
             +" (CASE   "
             +"     WHEN ( #{depOrigen} IS NOT NULL) AND  "
             +"       DEP_STATUS = 'A'  "
             +"       AND DEP_ID != #{depOrigen}  "
             +"    THEN 1  "
             +"     WHEN ( #{depOrigen} IS NULL ) AND  "
             +"       DEP_STATUS = 'A'  "
             +"     THEN 1  "
             +" END)= 1 "
             +" ORDER BY DEP_ID,DEP_NOMBRE ")
             List<FilterValuesVO> comboDepDestino(
			@Param("depOrigen")String depOrigen);
	
//	String GET_PLACA_INFO = "SELECT EMP_ID as empId, EMP_PLACA as placaEmp, NOMBRE_COMP as nomComp, EMP_APE_PATERNO as apellidoPaterno, EMP_APE_MATERNO as apellidoMaterno, "
//		     +" EMP_NOMBRE as NombreEmp,  AGRP_NOMBRE as nomAgrupamiento, SEC_NOMBRE as nomSector, PERFIL_WEB as perfil  "
//		     +" FROM V_INFRACCION_USUARIOS "
//		     +" WHERE EMP_PLACA = #{placaOficial} ";
	
	String GET_PLACA_INFO = "select emp_id as empId, emp_placa as placaEmp, concat(EMP_APE_PATERNO,concat(' ', concat(EMP_APE_MATERNO, concat(' ', EMP_NOMBRE)))) "
	 +" as NombreEmp ,EMP_APE_PATERNO as apellidoPaterno ,EMP_APE_MATERNO as apellidoMaterno ,EMP_NOMBRE as NombreEmp,a.agrp_id, agrp_nombre as  nomAgrupamiento, s.sec_id, sec_nombre as nomSector , EMP_COD "
	 +" ,NVL(pu.PERFIL_ID,0) PERFIL_ID, NVL(PERFIL_NOMBRE,'SIN PERFIL WEB') as perfil "
	 +" from EMPLEADOS, agrupamientos a, sectores s,  PERFIL_USUARIO pu, PERFILES p " 
	 +" where EMP_STATUS='A' and (EMP_TIP_ID = 1 or EMP_TIP_ID = 7 or EMP_TIP_ID = 10) and  "
     +" a.agrp_id = EMPLEADOS.agrp_id and s.sec_id = EMPLEADOS.sec_id and EMP_COD = #{placaOficial} "
     +" and pu.USU_ID(+) = EMP_ID "
     +" and p.PERFIL_ID(+) = pu.PERFIL_ID ";
	
	@Select(GET_PLACA_INFO)
	InfoPlacaEmpVO validarInfoPlaca(@Param("placaOficial") String placaOficial);
	
	@Select(" begin SP_SALIDA_VEHICULO("
			+ "#{salidaTpo}, "
			+ "#{numinfrac}, "
			+ "#{idDep}, "
			+ "#{idAutoriza}, "
			+ "#{numSerie}, "
			+ "#{docSalida}, "
			+ "#{idAdjudica}, "
			+ "#{idFase}, "
			+ "#{depDestino}, "
			+ "#{movEstatus}, "
			+ "#{numResguardo}, "
            + "#{ingrStatus}, "
            + "#{creadoPor}, "
            + "#{numCtrl}, "
            + "#{numPlaca}, "
//            + "#{imagen, jdbcType=byte[]}"
            + "#{resultadoPrincipal,jdbcType=VARCHAR, javaType=java.lang.String, mode=OUT}, "
            + "#{mensaje,jdbcType=VARCHAR, javaType=java.lang.String, mode=OUT} ); end; " )


            
	@Options(statementType = StatementType.CALLABLE)
	GuardarSalidaVO guardarVO(GuardarSalidaVO convertVO);
	
	String QUERY_GRAL_CONSULTA_VEH = 
			 " SELECT ID_SALIDA as idSalida, NUM_CTRL as numCtrl,INFRAC_NUM_SALIDA as numInfracSalida,NUM_PLACA_VEHICULO as numPlacaV, MARCA as marcaV , "
            + " MODELO as modeloV, TPO_SALIDA as tipoSalida , TO_CHAR(FCH_SALIDA,'DD/MM/YYYY HH:mm:ss') as fechaSalida, "
            + " TO_CHAR(FCH_CAPTURA_INGRESOMOV,'DD/MM/YYYY HH:mm:ss') as fechaCreacionIngMov,DEP_DESTINO as depDestino,DEP_ORIGEN as depOrigen, "
            + " NOM_TPO_SALIDA as nomTpoSalida, NOM_MARCA as nomMarca, NOM_MODELO as nomModelo, NOM_DEPOSITO_DEST as nomDepDest, NOM_DEPOSITO_ORIG as nomDepOrig, "
            + " FOLIO_DOC_SALIDA as folioDocSalida , NOM_FASE as nomFase, NOM_ADJUDICA as nomAdjudicacion, TO_CHAR(FCH_ULTIMA_MODIFICACION, 'dd/MM/yyyy HH:mm:ss') as fchUltimaModificacion, estatus " 
            + " FROM V_SALIDAS_VEH_CONSULTA ";
	
	@Select( QUERY_GRAL_CONSULTA_VEH + " WHERE #{tipoBusq} = #{valorCombo} ")
	List<ConsultaVehiculoSalidaVO> busquedaGeneralVehiculo(
			@Param("tipoBusq") String tipoBusq, 
			@Param("valorCombo") String valorCombo);
	
	
	@Select( QUERY_GRAL_CONSULTA_VEH  
			+" WHERE "	
            +"   (CASE  "
            +"        WHEN ( #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NOT NULL ) AND "
            +"              TO_CHAR(FCH_SALIDA,'DD/MM/YYYY') BETWEEN "
            +"              TO_DATE ( #{fechaInicio} ,'dd/mm/yyyy') "
            +"              AND TO_DATE( #{fechaFin} ,'dd/mm/yyyy') "
            +"				AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep}) "
            +"          THEN 1 "
            +"        WHEN ( #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NULL ) AND "
            +"             TO_CHAR(FCH_SALIDA, 'DD/MM/YYYY') >= TO_DATE( #{fechaInicio} ) "
            +"				AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep}) "
            +"          THEN 1 "
            +"        WHEN ( #{fechaInicio} IS NULL AND #{fechaFin} IS NOT NULL ) AND "
            +"             TO_CHAR(FCH_SALIDA, 'DD/MM/YYYY') <= TO_DATE( #{fechaFin} ) "
            +"				AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep}) "
            +"          THEN 1 "
            +"        WHEN ( #{fechaInicio} IS NULL AND #{fechaFin} IS  NULL ) "
            +"				AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep}) "
            +"          THEN 1  "
            +"    END) = 1 "
            +" ORDER BY  FCH_SALIDA DESC" )				
	List<ConsultaVehiculoSalidaVO> busquedaTodosVehiculo(
			@Param("fechaInicio") String fechaInicio, 
			@Param("fechaFin") String fechaFin,
			@Param("idDep") Long idDep);
	
	@Select( QUERY_GRAL_CONSULTA_VEH 
			+"  WHERE "
            +"    (CASE "
            +"        WHEN ( #{fechaInicio} IS NULL AND #{fechaFin} IS NULL ) AND " 
            +"              TPO_SALIDA = #{tipoBusqSalida} "
            +"				AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep}) "
            +"          THEN 1 "
            +"         WHEN ( #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NULL ) AND " 
            +"               TPO_SALIDA = #{tipoBusqSalida}  AND   "
            +"               TO_CHAR(FCH_SALIDA, 'DD/MM/YYYY') >= TO_DATE( #{fechaInicio} ) "
            +"				AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep}) "
            +"          THEN 1 "
            +"          WHEN ( #{fechaInicio} IS NULL AND #{fechaFin} IS NOT NULL ) AND " 
            +"               TPO_SALIDA = #{tipoBusqSalida}  AND   "
            +"               TO_CHAR(FCH_SALIDA, 'DD/MM/YYYY') <= TO_DATE( #{fechaFin} ) "
            +"				AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep}) "
            +"          THEN 1  "
            +"          WHEN ( #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NOT NULL ) AND " 
            +"              TPO_SALIDA = #{tipoBusqSalida} AND "
            +"              TO_CHAR(FCH_SALIDA,'DD/MM/YYYY') BETWEEN  "
            +"               TO_DATE ( #{fechaInicio} ,'dd/mm/yyyy') "
            +"              AND TO_DATE( #{fechaFin} ,'dd/mm/yyyy') "
            +"				AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep}) "
            +"          THEN 1 " 
            +"    END) = 1 "
            +" AND  " 
            +"    (CASE " 
            +"        WHEN ( #{tipoBusqSalida} = 1 ) AND "  
            +"              ID_ADJUDICA = #{tipoBusqEspecific} " 
            +"          THEN 1 "
            +"         WHEN ( #{tipoBusqSalida} = 2 ) AND  "
            +"               ID_FASE =  #{tipoBusqEspecific}    "
            +"          THEN 1 "
            +"			WHEN ( #{tipoBusqSalida} = 3 ) AND "
            +"				INFRAC_NUM_SALIDA = #{tipoBusqEspecific} " 
            +"			THEN 1 "  
            +"			WHEN ( #{tipoBusqSalida} = 4 ) AND "
            +"				FOLIO_DOC_SALIDA = #{tipoBusqEspecific} "
            +"			THEN 1 " 
            +"          WHEN ( #{tipoBusqSalida} = 5 ) AND  "
            +"               ID_DEP_DESTINO = #{tipoBusqEspecific}  "
            +"          THEN 1  "
            +"          WHEN ( #{tipoBusqEspecific} IS NULL) "
            +"         THEN 1  "
            +"    END) = 1 " 
            +" ORDER BY  FCH_SALIDA DESC "
			)
		List<ConsultaVehiculoSalidaVO> busquedaEspecialVehiculo(
			@Param("tipoBusqSalida") String tipoBusqSalida, 
			@Param("fechaInicio") String fechaInicio, 
			@Param("fechaFin") String fechaFin,
			@Param("tipoBusqEspecific") String tipoBusqEspecific, 
			@Param("idDep")  Long idDep);
	
	@Select( QUERY_GRAL_CONSULTA_VEH + " WHERE NUM_PLACA_VEHICULO = #{valorCombo} "
			+ "				AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep})  ")
	List<ConsultaVehiculoSalidaVO> busquedaNumPlacaVehiculo(
			@Param("valorCombo") String valorCombo, 
			@Param("idDep")  Long idDep);
	
	@Select( QUERY_GRAL_CONSULTA_VEH + " WHERE INFRAC_NUM_SALIDA = #{valorCombo} "
			+ "				AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep})  ")
	List<ConsultaVehiculoSalidaVO> busquedaInfracNumSalidaVehiculo(
			@Param("valorCombo") String valorCombo,
			@Param("idDep")  Long idDep);
	
	@Select( QUERY_GRAL_CONSULTA_VEH + " WHERE INFRAC_IMPRESA = #{valorCombo} "
			+ " AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep})  ")
	List<ConsultaVehiculoSalidaVO> busquedaInfraccImpresaVehiculo(
			@Param("valorCombo") String valorCombo,
			@Param("idDep")  Long idDep);
	
	@Select( QUERY_GRAL_CONSULTA_VEH + " WHERE NUM_CTRL = #{valorCombo} "
			+ " AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep})  ")
	List<ConsultaVehiculoSalidaVO> busquedaNumCtrlVehiculo(
			@Param("valorCombo") String valorCombo,
			@Param("idDep")  Long idDep);
	
	@Select( QUERY_GRAL_CONSULTA_VEH + " WHERE FOLIO_DOC_SALIDA = #{valorCombo} "
			+ " AND  (ID_DEP_DESTINO = #{idDep} OR DEP_SALIDA = #{idDep})  ")
	List<ConsultaVehiculoSalidaVO> busquedaFolioDocSalidaVehiculo(
			@Param("valorCombo") String valorCombo,
			@Param("idDep")  Long idDep);
	
		
	@Select("SELECT DEP_ID as codigo ,DEP_NOMBRE as descripcion "
            + "FROM DEPOSITOS "
            + "WHERE DEP_STATUS='A' "
            )
     List<FilterValuesVO> depositoTraslados();
	
//	String QUERY_CONSULTA_TRASLADO_GRAL = " SELECT INFRAC_NUM_CTRL AS infracNumCtrl , INFRAC_NUM_MOVS AS infracNumMovs,INFRAC_PLACA AS infracPlaca, MARCA AS marcaV, "
//			+" MODELO AS modeloV, DEP_ORIGEN AS depOrigen, DEP_DESTINO AS depDestino, TO_CHAR(FCH_SALIDA_MOV,'DD/MM/YYYY' ) AS fechaSalidaMov, "
//			+" NOM_MARCA AS nomMarcaV ,NOM_MODELO AS nomModeloV ,NOM_DEPOSITO_ORIG AS nomDepOrigen ,NOM_DEPOSITO_DEST AS nomDepDestino, NUM_DOCTO AS numDocto, INFRAC_IMPRESA AS infracImpresa "
//			+" FROM V_SALIDAS_VEH_TRANS_DEPOSITO "; 
	
	String QUERY_CONSULTA_TRASLADO_GRAL = "SELECT INFRAC_NUM_CTRL as infracNumCtrl, INFRAC_NUM_MOVS as infracNumMovs, EMP_PLACA as empPlaca, INFRAC_PLACA as infracPlaca, MARCA as marcaV, "
			+ " MODELO as modeloV, DEP_ORIGEN as depOrigen, DEP_DESTINO as depDestino, TO_CHAR(FCH_SALIDA_MOV,'dd/mm/yyyy hh:mi:ss') as fechaSalidaMov, "
			+ " NUM_DOCTO as numDocto, NOM_DEPOSITO_DEST as nomDepDestino, NOM_MARCA as nomMarcaV ,NOM_MODELO as nomModeloV ,NOM_COLOR as nomColor, INFRAC_IMPRESA as infracImpresa, TO_CHAR(FECHA_TIEMPO,'dd/mm/yyyy hh:mi:ss') as fechaTiempo "
			+ " FROM V_REMISION_INFRAC_SALIDA ";
	
	@Select(QUERY_CONSULTA_TRASLADO_GRAL)	
	List<ConsultaTrasladoVO> busquedaTraslados();
	
	@Select(QUERY_CONSULTA_TRASLADO_GRAL + " WHERE DEP_ORIGEN = #{depOrig}" )	
	List<ConsultaTrasladoVO> busquedaTrasladosDepOrg(
			@Param("depOrig") String depOrig);
	
	@Select(QUERY_CONSULTA_TRASLADO_GRAL + " WHERE DEP_DESTINO = #{depDesti}" )	
	List<ConsultaTrasladoVO> busquedaTrasladosDepDesti(
			@Param("depDesti") String depDesti);
	
	@Select(QUERY_CONSULTA_TRASLADO_GRAL + " WHERE INFRAC_PLACA = #{datoBusq}  AND DEP_DESTINO = #{depId} " )	
	List<ConsultaTrasladoVO> busquedaTrsldNumPlaca(
			@Param("datoBusq") String datoBusq, 
			@Param("depId") String depId );
	
	@Select(QUERY_CONSULTA_TRASLADO_GRAL + " WHERE INFRAC_NUM_MOVS = #{datoBusq} AND DEP_DESTINO = #{depId} " )	
	List<ConsultaTrasladoVO> busquedaTrsldInfracNumMov(
			@Param("datoBusq") String datoBusq,
			@Param("depId") String depId );
	
	@Select(QUERY_CONSULTA_TRASLADO_GRAL + " WHERE INFRAC_IMPRESA = #{datoBusq} AND DEP_DESTINO = #{depId} " )	
	List<ConsultaTrasladoVO> busquedaTrsldInfraccImpresa(
			@Param("datoBusq") String datoBusq, 
			@Param("depId") String depId );
	
	@Select(QUERY_CONSULTA_TRASLADO_GRAL + " WHERE INFRAC_NUM_CTRL = #{datoBusq} AND DEP_DESTINO = #{depId}" )	
	List<ConsultaTrasladoVO> busquedaTrsldNumCtrl(
			@Param("datoBusq") String datoBusq,
			@Param("depId") String depId);
	
	@Select(QUERY_CONSULTA_TRASLADO_GRAL + " WHERE NUM_DOCTO = #{datoBusq}  AND DEP_DESTINO = #{depId} " )	
	List<ConsultaTrasladoVO> busquedaTrsldDocto(
			@Param("datoBusq")String datoBusq,
			@Param("depId") String depId);
	
	@Select("SELECT  DEP_DESTINO FROM V_REMISION_INFRAC_SALIDA "
		  + " WHERE "
          +" (CASE " 
          +"    WHEN ( #{tipoBusqueda} = 'INFRAC_PLACA' ) AND " 
          +"          INFRAC_PLACA =  #{datoBusq}  "
          +"    THEN 1  "
          +"    WHEN ( #{tipoBusqueda} = 'INFRAC_NUM_MOVS' ) AND "
          +"           INFRAC_NUM_MOVS =  #{datoBusq} "
          +"    THEN 1 "
          +"    WHEN ( #{tipoBusqueda} = 'INFRAC_IMPRESA' ) AND "
          +"          INFRAC_IMPRESA =  #{datoBusq} "
          +"    THEN 1 "
          +"    WHEN ( #{tipoBusqueda} = 'INFRAC_NUM_CTRL' ) AND "
          +"          INFRAC_NUM_CTRL =  #{datoBusq}  "
          +"    THEN 1 "
          +"    WHEN ( #{tipoBusqueda} = 'NUM_DOCTO' ) AND "
          +"           NUM_DOCTO =  #{datoBusq}  "
          +"   THEN 1 "
          +" END)= 1 " )
	String busquedaDepDestino(
			@Param("tipoBusqueda")String tipoBusqueda,
			@Param("datoBusq") String datoBusq);
	
	  
    
	@Select(" SELECT ID_MOVIMIENTO as idMovimiento, INFRAC_NUM_CTRL as infracNumCtrl, INFRAC_NUM_MOVS as infracNumMovs,INFRAC_IMPRESA as infracImpresa,DEP_ORIGEN as depOrigen, DEP_DESTINO as depDestino, "
        +" INFRAC_PLACA as infracPlaca, EMP_PLACA as empPlaca, NUM_SERIE as ingrSerie, NOM_EMP_AUTORIZA as nomEmpAutoriza, ESTATUS_MOVIMIENTO as estatusMov, "
        +" EMP_USUARIO_AUTORIZA_MOV as idEmpAutoriza, NOM_DEPOSITO_ORIG as nomDepOrigen, TO_CHAR(FCH_SALIDA_MOV,'DD-MM-YYYY') as fechaSalidaMov, TO_CHAR(FECHA_TIEMPO,'dd/mm/yyyy hh:mi:ss') as fechaTiempo "
        +" , NOM_DEPOSITO_DEST as nomDepDestino , NOM_MARCA as nomMarcaV , NOM_MODELO as nomModeloV, NOM_COLOR as nomColor "
        + "FROM V_REMISION_INFRAC_SALIDA "
        +" WHERE INFRAC_NUM_MOVS = #{numInfraccion} " )
	ConsultaTrasladoVO buscarInfoTrasladoVeh(
			@Param("numInfraccion") String numInfraccion);
	
	@Select(" begin SP_INGRESO_TRASLADO("
			+ "#{depId}, "
			+ "#{infracNum}, "
			+ "#{creadoPor}, "
			+ "#{estatusMov}, "
			+ "#{empAutoriza}, "
			+ "#{observaciones}, "
			+ "#{idMov}, "
			+ "#{resultadoPrincipal,jdbcType=VARCHAR, javaType=java.lang.String, mode=OUT}, "
			+ "#{mensaje,jdbcType=VARCHAR, javaType=java.lang.String, mode=OUT} ); end; " )
		
	@Options(statementType = StatementType.CALLABLE)
	void guardarVOInfoTrasladoVeh(GuardarTrasladoVO convertVO);
	
	
	@Update("UPDATE INGRESOS "
            +"   SET INGR_SALIDA = SYSDATE, "
            +"       INGR_STATUS = #{ingrStatus}, "
            +"       MODIFICADO_POR = #{creadoPor}, "
           +"        ULTIMA_MODIFICACION = SYSDATE, "
             +"      USUARIO_AUTORIZA = #{idAutoriza} "
            +" WHERE INFRAC_NUM = #{numinfrac} " )
	void actualizarTablaIngresos(GuardarSalidaVO convertVO);
	
	@Select(" SELECT ID_TIPO_SALIDA as codigoString, DESCRIPCION as descripcion" 
    +" FROM CAT_TIPO_SALIDA_VEH "
    +" WHERE ESTATUS = 'A' ")
	List<FilterValuesVO> comboTipoSalida();
	
	@Select(" select ID_MOVIMIENTO from  MOV_DEP_VEHICULO where INFRAC_NUM_SALIDA = #{numInfrac}")
	Long getIdSalida(
			@Param("numInfrac")String infraccion);
	
	@Select("SELECT INFRAC_NUM_SALIDA FROM MOV_DEP_VEHICULO WHERE ID_MOVIMIENTO =  #{idSalidas}")
	String getNumInfraccionBySalida(
			@Param("idSalidas")Long idSalidas);
	
	String QUERY_CAT_FASE_COMPACTA = " SELECT  ID_FASE as idCat, FASE as nomTipoCat, " 
			+" DESCRIPCION as descripcion, TO_CHAR(TRUNC(FCH_COMPACTACION))  as fchCompatacion, " 
			+" CASE   NVL(ESTATUS, 'I') WHEN 'A' THEN 'Activo' "
			+" WHEN 'I' THEN 'Inactivo' "
			+" WHEN null THEN 'Inactivo' END AS estatus "
			+" from CAT_FASES_COMPACTACION "; 
	
	@Select( QUERY_CAT_FASE_COMPACTA + " WHERE " 
			+ " (CASE " 
			+" 		WHEN ( #{tipoBusqueda} = 'todo') "
			+" 		THEN 1 "
			+" 		WHEN ( #{tipoBusqueda} = 'fase') AND FASE = #{valorCombo} " 
			+" 		THEN 1 "
			+" 	END) = 1 "
			+" order by ID_FASE asc ")
	List<resultCatVO> getCatCompacta(busquedaCatSalidaVO convertVO);
	
	@Select(QUERY_CAT_FASE_COMPACTA + " WHERE ID_FASE = #{idCat} ")
	resultCatVO getCatCompactaByIdCat(
			@Param("idCat") Long idCat);
	
	
	
	String QUERY_CAT_ADJUDICACION = " SELECT ID_DEST as idCat, DESTINO as nomTipoCat, DESCRIPCION as descripcion, "
			+" CASE " 
			+" 	NVL(ESTATUS, 'I') WHEN 'A' THEN 'Activo' "
			+" 		WHEN 'I' THEN 'Inactivo' "
			+" 		WHEN null THEN 'Inactivo' END AS estatus "
			+" from CAT_DEST_ADJUDICACION ";
	
	@Select(QUERY_CAT_ADJUDICACION + " WHERE " 
			+" (CASE "
			+" 	 WHEN ( #{tipoBusqueda} = 'todo') "
			+" 	 THEN 1 "
			+" 	 WHEN ( #{tipoBusqueda} = 'destino') AND DESTINO = #{valorCombo} "
			+" 	 THEN 1 "
			+" END) = 1 "
			+" order by ID_DEST asc "	)
	List<resultCatVO> getCatAdjudica(busquedaCatSalidaVO convertVO);
	
	@Select(QUERY_CAT_ADJUDICACION + " WHERE ID_DEST = #{idCat} ")
	resultCatVO getCatAdjudicaByIdCat(
			@Param("idCat")Long idCat);
	
	@Update("UPDATE CAT_FASES_COMPACTACION SET  "
			+ " FASE = #{nomTipoCat}, DESCRIPCION = #{descripcion}, FCH_COMPACTACION = "
			+" TO_DATE( #{fchCompatacion}, 'dd/MM/yyyy'), ESTATUS = #{estatus} WHERE ID_FASE = #{idCat}" )
	void updateCatCompactaByIdCat(resultCatVO convertVO);
	
	@Update("UPDATE CAT_DEST_ADJUDICACION SET  "
			+ " DESTINO = #{nomTipoCat}, DESCRIPCION = #{descripcion}, ESTATUS = #{estatus} WHERE ID_DEST = #{idCat}")
	void updateCatAdjudicaByIdCat(resultCatVO convertVO);
	
	@Insert("INSERT INTO CAT_FASES_COMPACTACION VALUES( "
			+ "(SELECT NVL(MAX(ID_FASE + 1), 1) from CAT_FASES_COMPACTACION) ,"
			+ "#{nomTipoCat} , #{descripcion}, TO_DATE( #{fchCompatacion}, 'dd/MM/yyyy') , #{estatus} )")
	void insertCatCompactaByIdCat(resultCatVO convertVO);
	
	@Insert("INSERT INTO CAT_DEST_ADJUDICACION VALUES( (SELECT NVL(MAX(ID_FASE + 1), 1) from CAT_DEST_ADJUDICACION), #{nomTipoCat},  #{descripcion} , #{estatus} )" )
	void insertCatAdjudicaByIdCat(resultCatVO convertVO);
	
	String GET_RUTA_LOCAL_EXPEDIENTE = "SELECT PATH || '/' || NOMBRE_ARCHIVO AS PATH FROM V_IMAGENES_MOVIMIENTOS WHERE NOMBRE_ARCHIVO LIKE '%'||#{infracNum}|| '_' ||#{tipo}||'_' ||#{idMov}||'%'";
	
	@Select(value = GET_RUTA_LOCAL_EXPEDIENTE)
	public String obtenerRutaLocalExpedienteMovimiento(@Param("infracNum") String infracNum, @Param("tipo") String tipo, @Param("idMov") String idMov);
	
	@Select(" Select * "
			+" from (select ID_MOVIMIENTO from MOV_DEP_VEHICULO  WHERE INFRAC_NUM = #{numInfrac} ORDER BY ID_MOVIMIENTO DESC ) "
			+" where rownum = 1 ")
	Long getIdMovVeh(@Param("numInfrac")String numinfrac);
	
	@Select("SELECT INGR_STATUS FROM INGRESOS WHERE INFRAC_NUM = #{infracNum} ")
	String getIngrEstatusOld(@Param("infracNum") String infracNum);
	
	@Select("SELECT INGR_STATUS AS movEstatus FROM INGRESOS WHERE INFRAC_NUM = #{infracNum}")
	GuardarSalidaVO getOriginalIngreso(@Param("infracNum")String numinfrac);
	

}
