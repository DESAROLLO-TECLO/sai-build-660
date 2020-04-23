package mx.com.teclo.saicdmx.persistencia.mybatis.dao.parteinformativo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorDocsVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsVO;

@Mapper
public interface ParteInformativoInfracsMyBatisDAO {
	
	String GET_INFRACCIONES_POR_DOCUMENTOS = "SELECT INFRAC_NUM AS INFRACNUM, INFRAC_PLACA AS INFRACPLACA FROM PARTE_INFO_C_DOCS_INFRACS "
											+ "WHERE PI_ID = #{documentoId} ORDER BY INFRAC_NUM";
	
	String SP_PARTE_INFO_INFRACS = "BEGIN SP_PARTE_INFO_INFRACS ("
			+ "#{tipo}, "
			+ "#{piId}, "
			+ "#{infracNum}, "
			+ "#{infracPlaca}, "
			+ "#{modificadoPor}, "
			+ "#{p_resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	String BIT_VALIDA_INFRAC_PARTE_INFO = "select count(*)  from PARTE_INFO_C_DOCS_INFRACS where pi_id = #{idPi} and infrac_num = #{infracNum} and infrac_placa = #{placa}";
	
	String BIT_VALIDA_INFRAC_PARTE_INFO_BOL_SANCION= "select count(*) from PARTE_INFO_BOLETA_INFRACS where pi_id = #{idPi} and INFRAC_NUM = #{infracNum} ";
	
	String BIT_INSERTA_INFRAC_PARTE_INFO = "Insert into PARTE_INFO_C_DOCS_INFRACS (PI_ID, INFRAC_NUM, INFRAC_PLACA, CREADO_POR, MODIFICADO_POR) Values (#{p_pi_id}, #{p_infraccion}, #{p_placa}, #{p_emp_id}, #{p_emp_id})";
	
	String BIT_ELIMINA_INFRAC_PARTE_INFO = "delete PARTE_INFO_C_DOCS_INFRACS where pi_id = #{idPi} and infrac_num = #{infracNum} and infrac_placa = #{placa}";
	
	/***
	 * 
	 * @param parteInformativoCDocsInfracsVO
	 * @return
	 * @throws PersistenceException
	 */
	@Select(value = SP_PARTE_INFO_INFRACS)
	@Options(statementType = StatementType.CALLABLE)	
	public ParteInformativoInfracsVO addOrDeleteInfracciones(ParteInformativoInfracsVO parteInformativoCDocsInfracsVO) throws PersistenceException;

	@Select(value = GET_INFRACCIONES_POR_DOCUMENTOS)
	public List<ParteInformativoInfracsPorDocsVO> infraccionesPorDocumento(@Param("documentoId") Long valor);
	
	/***
	 * 
	 * @param 
	 * @return
	 * @throws PersistenceException
	 */
	@Select(value = BIT_VALIDA_INFRAC_PARTE_INFO)
	@Options(statementType = StatementType.CALLABLE)
	public Integer validaInfracParteInfo(@Param("idPi") Long idPi, @Param("infracNum") String infracNum, @Param("placa") String placa) throws PersistenceException;
	
	/***
	 * 
	 * @param 
	 * @return
	 * @throws PersistenceException
	 */
	@Select(value = BIT_INSERTA_INFRAC_PARTE_INFO)
	public Integer insertaInfracParteInfo(@Param("p_pi_id") long piId, @Param("p_infraccion") String infracNum, @Param("p_placa") String placa, @Param("p_emp_id") long empId) throws PersistenceException;
	
	/***
	 * 
	 * @param 
	 * @return
	 * @throws PersistenceException
	 */
	@Select(value = BIT_ELIMINA_INFRAC_PARTE_INFO)
	public Integer eliminaInfracParteInfo(@Param("idPi") Long idPi, @Param("infracNum") String infracNum, @Param("placa") String placa) throws PersistenceException;


	/**
	 * @author Javier Flores
	 * @param idPi
	 * @param infracNum
	 * @return
	 * @throws PersistenceException
	 */
	@Select(value = BIT_VALIDA_INFRAC_PARTE_INFO_BOL_SANCION)
	@Options(statementType = StatementType.CALLABLE)
	public Integer validaInfracParteInfoBoletaSancion(@Param("idPi") Long idPi, @Param("infracNum") String infracNum) throws PersistenceException;


}
