package mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesConPuntosLicenciaVO;

@Mapper
public interface InfraccionesPuntosLicenciaMyBatisDAO {
	
	
	String QUERY_INFRACC_PUNTOS_LICENCIA = "SELECT  TO_CHAR(INFRAC.INFRAC_M_FECHA_HORA, 'dd/MM/yyyy') AS fechaCorta, "+
			"        PKG_ENCRIPCION.DESENCRIPTA(TRIM (INFRAC.INFRAC_LICENCIA)) AS licencia, "+
			"	     TIPOL.TIPO_L_NOMBRE AS tipoLicencia, "+
			"	     ESTADOS.EDO_NOMBRE AS licenciaExpedidaEn, "+
			"	     PKG_ENCRIPCION.DESENCRIPTA(TRIM (INFRAC.INFRAC_I_PATERNO)) AS apellidoPaterno, "+
			"	     PKG_ENCRIPCION.DESENCRIPTA(TRIM (INFRAC.INFRAC_I_MATERNO)) AS apellidoMaterno, "+
			"	     PKG_ENCRIPCION.DESENCRIPTA(TRIM (INFRAC.INFRAC_I_NOMBRE)) AS nombre, "+
			"	     INFRAC.INFRAC_NUM AS infraccion, "+
			"	     TO_CHAR(INFRAC.INFRAC_M_FECHA_HORA, 'dd/MM/yyyy HH:mm:ss') AS fechaLarga, "+
			"	     ART.ART_NUMERO AS articulo, "+
			"	     ART.ART_FRACCION AS fraccion, "+
			"	     ART.ART_INCISO AS inciso, "+
			"	     ART.ART_PARRAFO AS parrafo, "+
			"	     ART.ART_MOTIVACION AS motivacion, "+
			"	     ART_INFRAC_FINAN.PUNTOS AS puntos "+
			"FROM    INFRACCIONES INFRAC "+
			"	       INNER JOIN ARTICULOS ART "+
			"	         ON ART.ART_ID = INFRAC.ART_ID "+
			"	       INNER JOIN ARTICULOS_INFRAC_FINANZAS ART_INFRAC_FINAN "+ 
			"	         ON ART.ART_ID = ART_INFRAC_FINAN.ART_ID "+
			"	       INNER JOIN TIPO_LICENCIA TIPOL "+
			"	         ON INFRAC.TIPO_L_ID = TIPOL.TIPO_L_ID "+
			"	       INNER JOIN ESTADOS ESTADOS "+
			"	         ON INFRAC.INFRAC_LIC_EDO = ESTADOS.EDO_ID "+
			"WHERE   ART_INFRAC_FINAN.cons = FN_INFRAC_FIN_CONS(ART.ART_ID, TRUNC(INFRAC.INFRAC_M_FECHA_HORA)) "+
			"AND     INFRAC.ESTATUS_CANCELACION IS NULL "+
			"AND     INFRAC.INFRAC_IMPRESA IS NULL "+
			"AND     SUBSTR (INFRAC.INFRAC_NUM, 1, 2) IN ('01','04') "+
			"AND     INFRAC.INFRAC_LICENCIA IS NOT NULL "+
			"AND     PKG_ENCRIPCION.DESENCRIPTA(TRIM(INFRAC.INFRAC_LICENCIA)) IS NOT NULL "+
			"AND     LENGTH(PKG_ENCRIPCION.DESENCRIPTA(TRIM (INFRAC.INFRAC_LICENCIA))) > 5 "+
			"AND     TRUNC(INFRAC.INFRAC_M_FECHA_HORA) = #{fechaArchivo} "+
			"AND     INFRAC.INFRAC_LIC_EDO = 9 "+
			"AND     ISNUMERIC(SUBSTR(PKG_ENCRIPCION.DESENCRIPTA (TRIM (INFRAC.INFRAC_LICENCIA)), 2,LENGTH(PKG_ENCRIPCION.DESENCRIPTA (TRIM (INFRAC.INFRAC_LICENCIA)))-1)) = 1 "+
			"GROUP BY  TO_CHAR(INFRAC.INFRAC_M_FECHA_HORA, 'dd/MM/YYYY'), "+
			"          PKG_ENCRIPCION.DESENCRIPTA(TRIM (INFRAC.INFRAC_LICENCIA)), "+
			"          TIPOL.TIPO_L_NOMBRE, "+
			"          ESTADOS.EDO_NOMBRE, "+
			"          PKG_ENCRIPCION.DESENCRIPTA(TRIM (INFRAC.INFRAC_I_PATERNO)), "+
			"          PKG_ENCRIPCION.DESENCRIPTA(TRIM (INFRAC.INFRAC_I_MATERNO)), "+
			"          PKG_ENCRIPCION.DESENCRIPTA(TRIM (INFRAC.INFRAC_I_NOMBRE)), "+
			"          INFRAC.INFRAC_NUM, "+
			"          INFRAC.INFRAC_M_FECHA_HORA, "+
			"          ART.ART_NUMERO, "+
			"          ART.ART_FRACCION, "+
			"          ART.ART_INCISO, "+
			"          ART.ART_PARRAFO, "+
			"          ART.ART_MOTIVACION, "+  
			"          ART_INFRAC_FINAN.PUNTOS "+
			"ORDER BY  INFRAC.INFRAC_M_FECHA_HORA";
	@Select(QUERY_INFRACC_PUNTOS_LICENCIA)
	List<InfraccionesConPuntosLicenciaVO> buscaInfraccionesConPuntosInfraccion(@Param("fechaArchivo") Date fechaArchivo);


}
