package mx.com.teclo.saicdmx.persistencia.mybatis.dao.parteinformativo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsNuevoVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsVO;

@Mapper
public interface ParteInformativoCDocsMyBatisDAO {
	
	String GET_V_DOCUMENTOS = "SELECT " 
			+ "a.id_pi as idPi,a.no_consecutivo as NOCONSECUTIVO,a.fecha as FECHA, "
			+ "emp_placa as OFICIALPLACA, "
			+ "emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno as OFICIALNOMBRE " 
			+ "FROM parte_informativo_c_docs a, empleados b, " 
			+ "( SELECT distinct(a.id_pi) FROM parte_informativo_c_docs a, parte_info_c_docs_infracs b "
				+ "WHERE a.id_pi = b.pi_id AND "
				+ "( CASE " 
					+	"WHEN (#{tipoBusqueda} = 1 AND #{valor} IS NOT NULL) AND a.no_consecutivo = #{valor} "
					+	  "THEN 1 " 
					+	"WHEN (#{tipoBusqueda} = 2 AND #{valor} IS NOT NULL) AND b.infrac_num = #{valor} "
					+	  "THEN 1 " 
					+	"WHEN (#{tipoBusqueda} = 3 AND #{valor} IS NOT NULL) AND b.infrac_placa = #{valor} "
					+	  "THEN 1 "
					+	"WHEN (#{tipoBusqueda} = 4 AND #{valor} IS NOT NULL) AND id_pi = #{valor} "
					+	  "THEN 1 "
					+	"WHEN #{tipoBusqueda} = 0 "  
		            +   "THEN 1 "  
					+	"END) = 1) c " 
			+	"WHERE a.id_pi = c.id_pi AND a.oficial_id = b.emp_id ORDER BY a.fecha DESC";
	
	String GET_V_DOCUMENTO = "SELECT " 
			+ "a.id_pi as idPi, a.no_consecutivo as noConsecutivo, TO_CHAR(a.fecha, 'DD/MM/YYYY HH24:MI') as fecha, TO_CHAR(a.fecha_entrega, 'DD/MM/YYYY HH24:MI') as fechaEntrega, a.oficial_id as oficialId, a.oficial_aoper as oficialAoper, "
			+ "b.emp_placa as oficialPlaca, "
			+ "emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno as OFICIALNOMBRE, " 
			+ "a.doc_ife as docIfe, a.doc_tarj_circ as docTarjCirc, a.doc_licencia as docLicencia, a.doc_verific as docVerific, a.doc_ife_nombre as docIfeNombre, "
			+ "a.doc_tarj_circ_nombre as docTarjCircNombre, a.doc_licencia_nombre as docLicenciaNombre, a.doc_verific_nombre as docVerificNombre, a.observacion, a.fecha_entrega as fechaEntrega, a.doc_otros as docOtros, a.doc_otros_nombre as docOtrosNombre "
			+ "FROM parte_informativo_c_docs a, empleados b, " 
			+ "( SELECT distinct(a.id_pi) FROM parte_informativo_c_docs a, parte_info_c_docs_infracs b "
				+ "WHERE a.id_pi = b.pi_id AND "
				+ "a.id_pi = #{idPi}"
				+ ") c " 
			+	"WHERE a.id_pi = c.id_pi AND a.oficial_id = b.emp_id";
	
	String GET_V_DOCUMENTO_FOR_MOD= "SELECT " 
			+ "a.id_pi as idPi,a.no_consecutivo as NOCONSECUTIVO,a.fecha as FECHA, "
			+ "a.oficial_id as OFICIALID, a.oficial_aoper as OFICIALAOPER, "
			+ "a.doc_ife as DOCIFE, "
			+ "a.doc_tarj_circ as DOCTARJCIRC, "
			+ "a.doc_licencia as DOCLICENCIA, "
			+ "a.doc_verific as DOCVERIFIC, "
			+ "a.doc_otros as DOCOTROS," 
			+ "a.doc_ife_nombre as DOCIFENOMBRE, a.doc_tarj_circ_nombre as DOCTARJCIRCNOMBRE, "
			+ "a.doc_licencia_nombre as DOCLICENCIANOMBRE, "
			+ "a.doc_verific_nombre as DOCVERIFICNOMBRE, a.doc_otros_nombre as DOCOTROSNOMBRE, "
			+ "observacion as OBSERVACION, a.fecha_entrega as FECHAENTREGA, " 
			+ "emp_placa as OFICIALPLACA, emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno as OFICIALNOMBRE, "
			+ "agrp_nombre as OFICIALAGRUPAMIENTO, sec_nombre as OFICIALSECTOR "
			+ "FROM parte_informativo_c_docs a, empleados b, " 
			+ "( SELECT distinct(a.id_pi) FROM parte_informativo_c_docs a, parte_info_c_docs_infracs b "
			+ "WHERE a.id_pi = b.pi_id AND id_pi = #{valor} ) c, agrupamientos d, sectores e "
			+ "WHERE a.id_pi = c.id_pi AND a.oficial_id = b.emp_id "
			+ "AND b.agrp_id = d.agrp_id AND b.sec_id = e.sec_id";
	
	String SP_PARTE_INFORMATIVO_MODIF = "BEGIN sp_parte_informativo_modif ("
			+ "#{idPi}, "
			+ "#{noConsecutivo}, "
			+ "#{fecha}, "
			+ "#{fechaEntrega}, "
			+ "#{oficialPlaca}, "
			+ "#{oficialAoper}, "
			+ "#{docIfe}, "
			+ "#{docTarjCirc}, "
			+ "#{docLicencia}, "
			+ "#{docVerific}, "
			+ "#{docOtros}, "
			+ "#{docIfeNombre}, "
			+ "#{docTarjCircNombre}, "
			+ "#{docLicenciaNombre}, "
			+ "#{docVerificNombre}, "
			+ "#{docOtrosNombre}, "
			+ "#{observacion}, "
			+ "#{modificadoPor}, "
			+ "#{p_resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	String SP_PARTE_INFORMATIVO_NUEVO = "BEGIN sp_parte_informativo_nuevo ("
			+ "#{noConsecutivo}, "
			+ "#{fecha}, "
			+ "#{oficialPlaca}, "
			+ "#{oficialAoper}, "
			+ "#{docIfe}, "
			+ "#{docTarjCirc}, "
			+ "#{docLicencia}, "
			+ "#{docVerific}, "
			+ "#{docOtros}, "
			+ "#{docIfeNombre}, "
			+ "#{docTarjCircNombre}, "
			+ "#{docLicenciaNombre}, "
			+ "#{docVerificNombre}, "
			+ "#{docOtrosNombre}, "
			+ "#{observacion}, "
			+ "#{creadoPor}, "
			+ "#{id, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{p_resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	
	/***
	 * 
	 * @param tipoBusqueda Los valores son 0=TODOS, 1=no_consecutivo, 2=infrac_num, 3=infrac_placa, 4=id del documento
	 * @param valor
	 * @return Listado de documentos
	 */
	@Select(GET_V_DOCUMENTOS)
	public List<ParteInformativoCDocsConsultaVO> buscarPIDocumentos(@Param("tipoBusqueda") Integer tipoBusqueda, @Param("valor") String valor);

	/***
	 * 
	 * @param idPi
	 * @return documento unico
	 */
	@Select(GET_V_DOCUMENTO)
	public ParteInformativoCDocsVO buscarPIDocumento(@Param("idPi") Long idPi);
	
	/***
	 * 
	 * @param tipoBusqueda Los valores son 0=TODOS, 1=no_consecutivo, 2=infrac_num, 3=infrac_placa, 4=id del documento
	 * @param valor
	 * @return Objeto de documentos
	 */
	@Select(GET_V_DOCUMENTO_FOR_MOD)
	public ParteInformativoCDocsVO buscarDocumentoPorId(@Param("valor") long valor);
	
	/***
	 * 
	 * @param parteInformativoCDocsVO
	 * @return
	 * @throws PersistenceException
	 */
	@Select(value = SP_PARTE_INFORMATIVO_MODIF)
	@Options(statementType = StatementType.CALLABLE)	
	public ParteInformativoCDocsVO modificacionDocumento(ParteInformativoCDocsVO parteInformativoCDocsVO) throws PersistenceException;

	/***
	 * 
	 * @param parteInformativoCDocsNuevoVO
	 * @return
	 * @throws PersistenceException
	 */
	@Select(value = SP_PARTE_INFORMATIVO_NUEVO)
	@Options(statementType = StatementType.CALLABLE)
	public ParteInformativoCDocsNuevoVO crearDocumento(ParteInformativoCDocsNuevoVO parteInformativoCDocsNuevoVO) throws PersistenceException;
	
}
