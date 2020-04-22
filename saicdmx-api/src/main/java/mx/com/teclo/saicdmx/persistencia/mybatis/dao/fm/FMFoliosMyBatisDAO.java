package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FMFoliosMyBatisDAO {

	String REASIGNA_FOLIOS_REUTILIZADOS =
			"INSERT INTO TAI008D_FM_FOLIOS_INFRACCIONES( "
			+ "SELECT (SELECT MAX(ID_FOLIO_INFRACCION) FROM TAI008D_FM_FOLIOS_INFRACCIONES) +rownum,"
			+ "t011.infrac_num, 1, 0, null, .RADAR_ARCHIVO_ID , RA.ARCHIVO_TIPO, RAD.PLACA, RAD.TDSKUID, RAD.INFRAC_NUM "
			+ "FROM    RADAR_ARCHIVO_DETALLE RAD "
			+ "          JOIN RADAR_ARCHIVO RA ON RA.ID = RAD.RADAR_ARCHIVO_ID "
			+ "WHERE   RAD.RADAR_ARCHIVO_ID = #{radarArchivoId} "
			+ "AND     RAD.INFRAC_NUM IS NOT NULL)";
}
