package mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarCatalogo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaCPVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaUTVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarComboTipoArchivoVO;



@Mapper
public interface RadarCatalogosMyBatisDAO {

	String GET_COMBO_CAT_ESTADOS_UT = 
			"SELECT EDO_ID as codigoString," + "EDO_NOMBRE as descripcion "
				+ "FROM rada_cat_estados where EDO_ID = 9 order by EDO_NOMBRE ";

	
	String GET_COMBO_CAT_ESTADOS = 
			"SELECT EDO_ID as codigoString," + "EDO_NOMBRE as descripcion "
				+ "FROM rada_cat_estados " + "order by EDO_NOMBRE ";

	
	String GET_COMBO_CAT_DELEGACION_ESTADO = 
			 "SELECT EDO_ID||'|'||DEL_ID as codigoString,"
				+ " DEL_NOMBRE as descripcion " 
			    + " FROM rada_cat_delegaciones "
				+ " ORDER by DEL_NOMBRE ";
	
	String GET_COMBO_CAT_SECTORES = 
			"SELECT sec_id as codigoString," 
			+ "sec_nombre as descripcion "
			+ "FROM sectores " 
			+ "order by sec_nombre ";
	
	String GET_COMBO_CAT_DELEGACION = 
			"SELECT DEL_ID as codigoString," 
					+ " DEL_NOMBRE as descripcion "
					+ " FROM delegaciones " 
					+ " WHERE edo_id="
					+ " #{estadoId}   ORDER by DEL_NOMBRE";
	
	String GET_COMBO_RADA_CAT_DELEGACION = "SELECT DEL_ID as codigoString,"
			+ " DEL_NOMBRE as descripcion "
			+ " FROM rada_cat_delegaciones "
			+ " WHERE edo_id="
			+ " #{estadoId}  ORDER by DEL_NOMBRE ";

	String CRUD_CAT_UT = "begin SP_RADAR_CAT_UT_M( "
			+ "#{accion}, "
			+ "#{catUTId}, "
			+ "#{catUTNumero}, "
			+ "#{catUTarchivoTipo}, "
			+ "#{catUTtipoRadar}, " 
            + "#{catUTCalle}, "
			+ "#{catUTEntreCalle}, "
			+ "#{catUTYCalle}, "
			+ "#{catUTSectorId}, "
			+ "#{catUTDelegacionId}, "
			+ "#{catUTCodigo}, "
			+ "#{catUTColonia}, "
			+ "#{catUTSentido}, "
			+ "#{catUTEstatus}, "
			+ "#{catUTModificadoPor}, "
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, " 
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT});"
			+ "end;";
	
	String CRUD_CAT_CP = 
			"begin SP_RADAR_CAT_CR( "
			+ "#{accion}, "
			+ "#{catCPId}, "
			+ "#{catCPNumero}, "
			+ "#{catCPAsentamiento}, "
			+ "#{catCPAsentamientoId}, "
			+ "#{catCPAsentamientoTipo}, "
			+ "#{catCPAsentamientoTipoClave}, "
			+ "#{catCPMunicipioId}, "
			+ "#{catCPEstadoId}, "
			+ "#{catCPCiudad}, "
			+ "#{catCPCiudadId}, "
			+ "#{catCPZona}, "
			+ "#{catCPAsentamientoCR}, "
			+ "#{catCPEstatus}, "
			+ "#{catCPModificadoPor}, "
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}  ); "
			+ "end;";
	
	String GET_CONSULTA_UNIDAD_TERRITORIAL = 	
		    "SELECT  A.ID AS catUTId," 
            +" A.UT AS catUTNumero, "  
		    +" A.CALLE AS catUTCalle, "  
		    +" A.ENTRECALLE AS catUTEntreCalle, "  
			+" A.YCALLE AS catUTYCalle, "
			+" A.SECTOR AS catUTSector, "
			+" A.TMM_SECTORID AS catUTSectorId, " 
			+" A.DELEGACION AS catUTDelegacion, " 
			+" A.TMM_DELEGACIONID AS catUTDelegacionId, "   
			+" (select nombre from RADAR_CAT_ARCHIVO_TIPO where ARCHIVO_TIPO_ID = A.ARCHIVO_TIPO_ID) catUTarchivoTipo, " 
		    +" (select nombre from FOTOMULTA_CAT_TIPO_RADAR where CAT_TIPO_RADAR_ID = A.TIPO_RADAR_ID) catUTtipoRadar, "
			+"  ( CASE "
			+"    WHEN (A.ESTATUS = 1) " 
			+"    THEN 'ACTIVO' "
			+"    ELSE 'INACTIVO' "
			+"	   END ) AS catUTEstatus "  
			+"  FROM RADAR_CAT_UT A "     
			+"  WHERE (CASE "
			+"         WHEN ( #{tipoCatalogo} = 'Unidad Territorial') AND A.UT = #{valor} "
			+"           THEN 1 "
		    +"         WHEN ( #{tipoCatalogo} = 'Sector') AND A.TMM_SECTORID = #{valor} "
		    +"           THEN 1 "
		    +"         WHEN ( #{tipoCatalogo} = 'Delegacion') AND A.TMM_DELEGACIONID = #{valor} "
		    +"           THEN 1 "
		    +"         WHEN ( #{tipoCatalogo} = 'Todos' ) "
		    +"           THEN 1 "
		    +"  END) = 1 "
		    +"  AND (CASE "
			+"       WHEN ( #{archivoTipoUT} = 0 AND #{tipoRadarUT} = 0) AND A.ARCHIVO_TIPO_ID IN (SELECT ARCHIVO_TIPO_ID FROM RADAR_CAT_ARCHIVO_TIPO WHERE ACTIVO_CAT_UT = 1) "
		    +"           THEN 1   "
		    +"       WHEN ( #{archivoTipoUT} <> 0 AND #{archivoTipoUT} <> 3  AND #{tipoRadarUT} = 0) AND A.ARCHIVO_TIPO_ID = #{archivoTipoUT} "
		    +"           THEN 1  "
		    +"       WHEN ( #{archivoTipoUT} = 3 AND #{tipoRadarUT} = 0) AND A.ARCHIVO_TIPO_ID = #{archivoTipoUT} AND A.TIPO_RADAR_ID IN (SELECT CAT_TIPO_RADAR_ID FROM FOTOMULTA_CAT_TIPO_RADAR WHERE ACTIVO = 1) "
		    +"           THEN 1  "
		    +"       WHEN ( #{archivoTipoUT} = 3 AND #{tipoRadarUT} <> 0) AND A.ARCHIVO_TIPO_ID = #{archivoTipoUT} AND A.TIPO_RADAR_ID = #{tipoRadarUT} "
		    +"           THEN 1 "
		    +"  END) = 1 "
		    +"  AND (CASE "
		    +"     WHEN ( #{estatusUT} <> 2) AND A.ESTATUS = #{estatusUT} "
		    +"         THEN 1  "
		    +"     WHEN ( #{estatusUT} = 2) AND A.ESTATUS <> 2 "
		    +"         THEN 1  "
		    +"  END) = 1 "
		    +"  order by A.UT asc,A.ARCHIVO_TIPO_ID desc, A.TIPO_RADAR_ID asc,ESTATUS asc , A.FECHA_CREACION desc ";
			
	String GET_UNIDAD_TERRITORIAL = 	
		    "SELECT  A.ID AS catUTId," 
            +" A.UT AS catUTNumero, "  
		    +" A.CALLE AS catUTCalle, "  
		    +" A.ENTRECALLE AS catUTEntreCalle, "  
			+" A.YCALLE AS catUTYCalle, "
			+" A.SECTOR AS catUTSector, "
			+" A.TMM_SECTORID AS catUTSectorId, " 
			+" A.DELEGACION AS catUTDelegacion, " 
			+" A.TMM_DELEGACIONID AS catUTDelegacionId, "  
			+" A.CODIGO AS catUTcodigo, "
			+" A.COLONIA AS catUTcolonia, "   
			+" A.SENTIDO AS catUTsentido, "
			+" (select nombre from RADAR_CAT_ARCHIVO_TIPO where ARCHIVO_TIPO_ID = A.ARCHIVO_TIPO_ID) catUTarchivoTipo, " 
		    +" (select nombre from FOTOMULTA_CAT_TIPO_RADAR where CAT_TIPO_RADAR_ID = A.TIPO_RADAR_ID) catUTtipoRadar, "
			+"  ( CASE "
			+"    WHEN (A.ESTATUS = 1) " 
			+"    THEN 'ACTIVO' "
			+"    ELSE 'INACTIVO' "
			+"	   END ) AS catUTEstatus "  
			+" FROM RADAR_CAT_UT A "            
			+"	  WHERE A.ID = #{valor} ";	
	
	String GET_CONSULTA_CODIGO_POSTAL = 
			"SELECT ID AS catCPId, "
			+" CP AS catCPNumero, "
            +" D_ASENTA AS catCPAsentamiento, "
            +" D_TIPO_ASENTA AS catCPAsentamientoTipo, "
            +" C_TIPO_ASENTA AS catCPAsentamientoTipoClave, "
            +" CR AS catCPAsentamientoCR, "
            +" D_ASENTA_CPCONS AS catCPAsentamientoId, "
            +" D_MUNICIPIO AS catCPMunicipio, "
            +" C_MUNICIPIO AS catCPMunicipioId, " 
	       +" D_ZONA AS catCPZona, "
	       +" D_ESTADO AS catCPEstado, "
	       +" C_ESTADO AS catCPEstadoId, "
	       +" C_AVE_CIUDAD AS catCPCiudadId , "
	       +" NVL(D_CIUDAD,' ')  AS catCPCiudad, "
				
	       +" (CASE "
	       +"   WHEN (ESTATUS = 1) " 
	       +"     THEN 'ACTIVO'  "
	       +"   ELSE 'INACTIVO'  "
	       +"  END)  AS catCPEstatus "
					
	       +" FROM RADAR_CAT_CTRO_REPARTO " 
	        
	       +" WHERE	(CASE  "
	       +"   WHEN ( #{tipoBusqueda} = 'Codigo Postal') AND CP = #{valor} " 
	       +"         THEN 1 "
	       +"   WHEN ( #{tipoBusqueda} = 'Estado') AND C_ESTADO = #{valor} " 
	       +"         THEN 1  "
	       +"   WHEN ( #{tipoBusqueda} = 'DelegacionEstado') AND C_ESTADO = #{valor} AND C_MUNICIPIO = #{valorExtra} "
	       +"         THEN 1  "
	       +"   WHEN ( #{tipoBusqueda} = 'Todos') "
	       +"         THEN 1  "
		   +"	 END) = 1 ";
	
	String GET_COMBO_SECTORES_POR_DELEGACION = "SELECT sec_id as codigoString," + "sec_nombre as descripcion "
				+ "FROM sectores " + "WHERE edo_id= #{edoDfId}  AND del_id="
				+ "#{idDelegacion} "+ 
				" order by sec_nombre ";
	String GET_COMBO_ZONAS_CP = "SELECT ZONA_NOMBRE as codigoString," + "ZONA_NOMBRE as descripcion "
				+ "FROM rada_cat_zonas " + "order by ZONA_NOMBRE ";
				
	String BUSCAR_UNIDAD_TERRITORIAL_POR_UT = 
			"SELECT ID AS catUTId, "
			+ "UT AS catUTNumero, "
			+ "CALLE AS catUTCalle, "
			+ "ENTRECALLE AS catUTEntreCalle,"
			+ "YCALLE AS catUTYCalle, "
			+ "SECTOR AS catUTSector, "
			+ "TMM_SECTORID AS catUTSectorId,"
		    + "DELEGACION AS catUTDelegacion, "
		    + "TMM_DELEGACIONID AS catUTDelegacionId, "
		    + "ARCHIVO_TIPO_ID as catUTarchivoTipo, "
		    + "TIPO_RADAR_ID as catUTtipoRadar, "	
		    + "ESTATUS AS catUTEstatus, "
		    + "CODIGO AS catUTcodigo, "
		    + "COLONIA AS catUTcolonia, "
		    + "SENTIDO AS catUTsentido " 
			+ "FROM RADAR_CAT_UT WHERE ID = #{numeroUT} ";
	
	String BUSCAR_UNIDAD_TERRITORIAL_POR_CP = "SELECT ID AS catCPId, "
			+ "CP AS catCPNumero, "
			+ "D_ASENTA AS catCPAsentamiento, "
			+ "D_TIPO_ASENTA AS catCPAsentamientoTipo, "
			+ "C_TIPO_ASENTA AS catCPAsentamientoTipoClave, "
			+ "CR AS catCPAsentamientoCR, "
			+ "D_ASENTA_CPCONS AS catCPAsentamientoId, "
			+ "D_MUNICIPIO AS catCPMunicipio, "
			+ "C_MUNICIPIO AS catCPMunicipioId, "
			+ "D_ZONA AS catCPZona, "
			+ "D_ESTADO AS catCPEstado, "
			+ "C_ESTADO AS catCPEstadoId, "
			+ "NVL(D_CIUDAD,' ') AS catCPCiudad, "
			+ "NVL(TO_CHAR(C_AVE_CIUDAD),' ') AS catCPCiudadId, "
			+ "ESTATUS AS catCPEstatus "
			+ "FROM RADAR_CAT_CTRO_REPARTO WHERE ID = #{numeroCP}";
	
	String GET_COMBO_ARCHIVO_TIPO = "select ARCHIVO_TIPO_ID as codigoString , NOMBRE as descripcion from RADAR_CAT_ARCHIVO_TIPO";
	
	// UT
	String GET_COMBO_ARCHIVO_TIPO_UT = "select ARCHIVO_TIPO_ID as codigoString , NOMBRE as descripcion from RADAR_CAT_ARCHIVO_TIPO where ACTIVO_CAT_UT = 1 ORDER BY NOMBRE ASC";
	
	String GET_COMBO_TIPO_RADAR = "select CAT_TIPO_RADAR_ID as codigoString , NOMBRE as descripcion  from FOTOMULTA_CAT_TIPO_RADAR ORDER BY NOMBRE ASC";
	
	String UPDATEALL =  "UPDATE RADAR_CAT_UT "   
			+"SET ESTATUS=   CASE "  
	        +"WHEN ((SELECT COUNT(*) FROM  RADAR_CAT_UT WHERE UT= #{catUTNumero} AND ARCHIVO_TIPO_ID = #{catUTarchivoTipo} AND TIPO_RADAR_ID = #{catUTtipoRadar} AND ESTATUS = #{catUTEstatus} ) > 0) "
	        +"THEN '0' " 
	        +"WHEN ((SELECT COUNT(*) FROM  RADAR_CAT_UT WHERE UT= #{catUTNumero} AND ARCHIVO_TIPO_ID = #{catUTarchivoTipo} AND TIPO_RADAR_ID = #{catUTtipoRadar} AND ESTATUS = #{catUTEstatus} ) = 0) "
	        +"THEN  '0' "                 
	        +"END " 
	        +"WHERE UT= #{catUTNumero}  AND ARCHIVO_TIPO_ID = #{catUTarchivoTipo} AND TIPO_RADAR_ID = #{catUTtipoRadar} AND ESTATUS = #{catUTEstatus} ";
	/*
	String COMPROBACION = "SELECT COUNT(*) "
			+ "FROM  RADAR_CAT_UT WHERE UT= #{catUTNumero} "
			+ "AND ARCHIVO_TIPO_ID = #{catUTarchivoTipo} "
			+ "AND TIPO_RADAR_ID = #{catUTtipoRadar} "
			+ "AND ESTATUS = #{catUTEstatus} ";  */
	
	String COMPROBACION = "SELECT ID FROM  RADAR_CAT_UT "
			+ "WHERE  UT= #{catUTNumero} "
			+ "AND ARCHIVO_TIPO_ID = #{catUTarchivoTipo} "
			+ "AND TIPO_RADAR_ID = #{catUTtipoRadar} "
			+ "AND ESTATUS > 0 ";
	
	String CONSULTAOPCIONALALL = "SELECT ID AS catCPId, "
			+" CP AS catCPNumero, "
		    +" D_ASENTA AS catCPAsentamiento, "
		    +" D_TIPO_ASENTA AS catCPAsentamientoTipo, "
		    +" C_TIPO_ASENTA AS catCPAsentamientoTipoClave, "
		    +" CR AS catCPAsentamientoCR, "
		    +" D_ASENTA_CPCONS AS catCPAsentamientoId, "
		    +" D_MUNICIPIO AS catCPMunicipio, "
		    +" C_MUNICIPIO AS catCPMunicipioId, " 
		   +" D_ZONA AS catCPZona, "
		   +" D_ESTADO AS catCPEstado, "
		   +" C_ESTADO AS catCPEstadoId, "
		   +" C_AVE_CIUDAD AS catCPCiudadId , "
		   +" NVL(D_CIUDAD,' ')  AS catCPCiudad, "
				
		   +" (CASE "
		   +"   WHEN (ESTATUS = 1) " 
		   +"     THEN 'ACTIVO'  "
		   +"   ELSE 'INACTIVO'  "
		   +"  END)  AS catCPEstatus "
					
		   +" FROM RADAR_CAT_CTRO_REPARTO " 
		   +" WHERE ROWNUM < 10000  ORDER BY ID DESC";
	
	String COMPROBACIONCP = "select count (*) From  RADAR_CAT_CTRO_REPARTO";
	
	String UPDATE_ESTATUS = "update RADAR_CAT_UT set estatus = #{status} "
			  + " where id = #{idUT}";
	
	String GET_ARCHIVO_ID = "SELECT ID FROM RADAR_CAT_CTRO_REPARTO where rownum = 1 order by id desc";
	
	String ESTATUS_CENTRO_REPARTO = "SELECT estatus FROM RADAR_CAT_CTRO_REPARTO where ID = #{archivoId}";
	
	String GET_ARCHIVO_ID_UT = "SELECT UT FROM RADAR_CAT_UT where rownum = 1 order by id desc";
	
	String ESTATUS_UNIDAD_TERRITORIAL = "SELECT estatus FROM RADAR_CAT_UT where ID = #{archivoId}";
	
	String NOMBRE_ESTADO = "select EDO_NOMBRE from RADA_CAT_ESTADOS where EDO_ID = #{edoId}";
	
	String NOMBRE_DELEGACION = "SELECT DEL_NOMBRE FROM rada_cat_delegaciones WHERE del_id = #{delId} AND edo_id = #{edoId}";
	
	@Select (GET_COMBO_CAT_ESTADOS_UT)
	public  List<FilterValuesVO> getComboCatEstadosUT();

	@Select (GET_COMBO_CAT_ESTADOS)
	public  List<FilterValuesVO> getComboCatEstados();


	public List<RadarComboTipoArchivoVO> filtroCatalogos();

	@Select (GET_COMBO_CAT_DELEGACION_ESTADO)
	public List<FilterValuesVO> getComboCatDelegacionEstado();

	@Select (GET_COMBO_CAT_SECTORES)
	public List<FilterValuesVO> getComboCatSectores();

	@Select (GET_COMBO_CAT_DELEGACION)
	public List<FilterValuesVO> getComboCatDelegacion(
			@Param("estadoId") String estadoId
			);
	
	@Select (GET_COMBO_RADA_CAT_DELEGACION)
	public List<FilterValuesVO> getComboRadaCatDelegacion(@Param("estadoId") String estadoId);
    
	@Select (GET_CONSULTA_UNIDAD_TERRITORIAL)
	public List<RadarCatConsultaUTVO> getConsultaUnidadTerritorial(
			@Param("tipoCatalogo") String tipoCatalogo, 
			@Param("valor") String valor,
			@Param("archivoTipoUT") Long archivoTipoUT,
			@Param("tipoRadarUT") Long tipoRadarUT,
			@Param("estatusUT") Long estatusUT);
	
	@Select (GET_UNIDAD_TERRITORIAL)
	public RadarCatConsultaUTVO getUnidadTerritorial(
			@Param("tipoCatalogo") String tipoCatalogo, 
			@Param("valor") String valor,
			@Param("archivoTipoUT") Long archivoTipoUT,
			@Param("tipoRadarUT") Long tipoRadarUT,
			@Param("estatusUT") Long estatusUT);

	@Select (GET_CONSULTA_CODIGO_POSTAL)
	public List<RadarCatConsultaCPVO> getConsultaCodigoPostal(
			@Param("tipoBusqueda") String tipoBusqueda, 
			@Param("valor") String valor,
			@Param("valorExtra") String valorExtra);

	@Select (GET_COMBO_SECTORES_POR_DELEGACION)
	public List<FilterValuesVO> getComboSectorPorDelegacion(
			@Param("edoDfId")String edoDfId, 
			@Param("idDelegacion")String idDelegacion);

	@Select (GET_COMBO_ZONAS_CP)
	public List<FilterValuesVO> getComboZonasCP();

	@Select (CRUD_CAT_UT)
	@Options(statementType = StatementType.CALLABLE)
	public RadarCatConsultaUTVO crudCatUT(RadarCatConsultaUTVO convertVO) throws PersistenceException;

	@Select (CRUD_CAT_CP)
	@Options(statementType = StatementType.CALLABLE)
	public RadarCatConsultaCPVO crudCatCR(RadarCatConsultaCPVO convertVO);

	@Select (BUSCAR_UNIDAD_TERRITORIAL_POR_UT)
	public RadarCatConsultaUTVO buscarUnidadTerritorialPorUT(
			@Param("numeroUT") String numeroUT);

	@Select (BUSCAR_UNIDAD_TERRITORIAL_POR_CP)
	public RadarCatConsultaCPVO buscarUnidadTerritorialPorCP(
			@Param("numeroCP") String valorId);

	@Select (GET_COMBO_ARCHIVO_TIPO)
	public List<FilterValuesVO> getComboArchivoTipo();

	@Select (GET_COMBO_ARCHIVO_TIPO_UT)
	public List<FilterValuesVO> getComboArchivoTipoUT();
	
	@Select (GET_COMBO_TIPO_RADAR)
	public List<FilterValuesVO> getComboTipoRadar();

	
	@Select (UPDATEALL)
	@Options(statementType = StatementType.CALLABLE)
	public void updataAll(RadarCatConsultaUTVO convertVO);
	
	@Select (COMPROBACION)
	@Options(statementType = StatementType.CALLABLE)
	//public boolean comprobacionUT(RadarCatConsultaUTVO convertVO);
	public String comprobacionUT(RadarCatConsultaUTVO convertVO);
	
	@Select (CONSULTAOPCIONALALL)
	public List<RadarCatConsultaCPVO>  consultaOpcionalAll();
	
	@Select (COMPROBACIONCP)
	public int consultaOpcionalCp();
	
	@Select (UPDATE_ESTATUS)
	public String cambiarEstatus (@Param("status")int status, 
			@Param("idUT")int idUT);
	
	@Select(GET_ARCHIVO_ID)
	public String getArchivoId();
	
	@Select(ESTATUS_CENTRO_REPARTO)
	public Integer estatusCentroReparto(@Param("archivoId") int archivoId);
	
	@Select(GET_ARCHIVO_ID_UT)
	public String getArchivoIdUT();
	
	@Select(ESTATUS_UNIDAD_TERRITORIAL)
	public Integer estatusUnidadTerritorial(@Param("archivoId") int archivoId);
	
	@Select(NOMBRE_ESTADO)
	public String getNombreEstado(@Param("edoId") int edoId);
	
	@Select(NOMBRE_DELEGACION)
	public String getNombreDelegacion(@Param("delId") int delId, 
			@Param("edoId") int edoId);
	
}
