package mx.com.teclo.saicdmx.persistencia.mybatis.dao.parteinformativo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionModificacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionNuevaVO;

@Mapper
public interface ParteInformativoBoletaSancionMyBatisDAO {

	String GET_V_BOLETAS = "SELECT " +
							"a.id_reg as ID, a.no_consecutivo as NOCONSECUTIVO, "
							+ "fecha_sello as FECHA, "
			                + "emp_placa as OFICIALPLACA, emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno as OFICIALNOMBRE "
			                + "FROM PARTE_INFO_BOLETA_SANCION a, empleados b, "
			                + "(SELECT distinct(a.id_reg) "
			                + "FROM PARTE_INFO_BOLETA_SANCION a, PARTE_INFO_BOLETA_INFRACS b "
			                + "WHERE a.id_reg = b.pi_id AND "
			                + "( CASE " 
							+ "WHEN (#{tipoBusqueda} = 1 AND #{valor} IS NOT NULL) AND a.no_consecutivo = #{valor} "
							+ "THEN 1 " 
							+ "WHEN (#{tipoBusqueda} = 2 AND #{valor} IS NOT NULL) AND b.infrac_num = #{valor} "
							+ "THEN 1 " 
							+ "WHEN #{tipoBusqueda} = 0 "  
				            + "THEN 1 "  
							+ "END) = 1) c "
			                + "WHERE a.id_reg = c.id_reg "
			                + "AND a.oficial_id = b.emp_id ORDER BY fecha_sello DESC";
	
	String GET_V_BOLETA_FOR_MOD = "SELECT "
		  +"a.id_reg as ID, a.no_consecutivo as NOCONSECUTIVO, fecha_sello as FECHA, " 
		  +"sit_con_acta as SITUACIONACTA, sit_extravio as SITUACIONEXTRAVIO, sit_mal_elaborada as SITUACIONELABORADA, "
	      +"sit_otro as SITUACIONOTRO, sit_otro_desc as SITUACIONOTRODESC, "
	      +"emp_placa as OFICIALPLACA, emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno as OFICIALNOMBRE, "
	      +"sector as OFICIALAOPER, agrp_nombre as OFICIALAGRUPAMIENTO, sec_nombre as OFICIALSECTOR "
	      +"FROM PARTE_INFO_BOLETA_SANCION a, empleados b, "
	      +"(SELECT distinct(a.id_reg) "
	      +"FROM PARTE_INFO_BOLETA_SANCION a, PARTE_INFO_BOLETA_INFRACS b " 
	      +"WHERE a.id_reg = b.pi_id AND id_reg = #{valor} "
	      +") c, agrupamientos d, sectores e "
	      +"WHERE a.id_reg = c.id_reg "
	      +"AND a.oficial_id = b.emp_id "
	      +"AND b.agrp_id = d.agrp_id AND b.sec_id = e.sec_id";
	
	String SP_PARTE_INFORMATIVO_BOLETA_MODIF = "BEGIN sp_parte_info_boleta_modif ("
			+ "#{id}, "
			+ "#{noConsecutivo}, "
			+ "#{fecha}, "
			+ "#{oficialPlaca}, "
			+ "#{oficialAoper}, "
			+ "#{situacionSelect}, "
			+ "#{situacionOtroDesc}, "
			+ "#{modificadoPor}, "
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	String SP_PARTE_INFO_BOLETA_NUEVO = "BEGIN sp_parte_info_boleta_nuevo ("
			+ "#{noConsecutivo}, "
			+ "#{fecha}, "
			+ "#{oficialPlaca}, "
			+ "#{oficialAoper}, "
			+ "#{situacionSelect}, "
			+ "#{situacionOtroDesc}, "
			+ "#{creadoPor}, "
			+ "#{id, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	/***
	 * @author Jesus Gutierrez
	 * @param tipoBusqueda
	 * @param valor
	 * @return
	 * @throws
	 */
	@Select(GET_V_BOLETAS)
	public List<ParteInformativoBoletaSancionConsultaVO> buscarPIBoletas(@Param("tipoBusqueda") Integer tipoBusqueda, @Param("valor") String valor);

	/***
	 * @author Jesus Gutierrez
	 * @param valor
	 * @return
	 * @throws
	 */
	@Select(GET_V_BOLETA_FOR_MOD)
	public ParteInformativoBoletaSancionModificacionVO biuscarBoletaPorId(@Param("valor") Long valor);
	
	@Select(value = SP_PARTE_INFORMATIVO_BOLETA_MODIF)
	@Options(statementType = StatementType.CALLABLE)
	public ParteInformativoBoletaSancionModificacionVO modificacionBoleta(ParteInformativoBoletaSancionModificacionVO parteInformativoBoletaSancionModificacionVO) throws PersistenceException;
	
	@Select(value= SP_PARTE_INFO_BOLETA_NUEVO)
	@Options(statementType = StatementType.CALLABLE)
	public ParteInformativoBoletaSancionModificacionVO crearBoleta(ParteInformativoBoletaSancionNuevaVO parteInformativoBoletaSancionNuevaVO) throws PersistenceException;
}
