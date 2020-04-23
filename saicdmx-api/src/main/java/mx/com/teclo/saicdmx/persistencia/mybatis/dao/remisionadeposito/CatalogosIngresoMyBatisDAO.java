package mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.GruaVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListCausaIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListInventarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListMediosTraspVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListOperativoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ListaNoGruasVO;

@Mapper
public interface CatalogosIngresoMyBatisDAO {

	String GET_OPERATIVO = " select oper_cod, oper_nombre "
						 + " from operativo where OPER_STATUS='A' order by oper_nombre ";		
	
	String GET_TRANSPORTE = " select t_ingr_cod, t_ingr_nombre "
						  + " from tipo_ingreso where T_INGR_STATUS='A' order by t_ingr_nombre";
	
	String GET_NO_GRUAS = "SELECT GRUA_ID, GRUA_COD FROM GRUAS WHERE GRUA_STATUS='A' "
							+ "AND (GRUA_ID <> 165 AND GRUA_ID <> 166 AND GRUA_ID <> 167 AND GRUA_ID <> 169) "
							+ "ORDER BY GRUA_COD";
	
	String GET_CAUSA_INGRESO_CON_I = " select causa_cod, causa_nombre "
							 + " from causa_ingreso where CAUSA_STATUS='A' and causa_id = '01' order by causa_nombre";
	
	String GET_CAUSA_INGRESO_SIN_I = " select causa_cod, causa_nombre "
			 				 + " from causa_ingreso where CAUSA_STATUS='A' and causa_id != '01' order by causa_nombre";
	
//			select causa_cod, causa_nombre from causa_ingreso where CAUSA_STATUS='A' and causa_id != '01' : 1=1
//			order by causa_nombre;
	
	String GET_DEPOSITO = " select d.dep_cod, d.dep_nombre  from  depositos d, depositos_empleados de "
						+ " where d.DEP_STATUS='A' and de.DEP_EMP_STATUS='A' and d.dep_id = de.dep_id and"
						+ " de.emp_id = #{emp_id} and d.DEP_ID = #{dep_id} order by d.dep_nombre";
	
	
	String GET_DEPOSITO_ORIGEN = " select dep_cod, dep_nombre "
							   + " from depositos "
							   + " where DEP_STATUS='A' order by dep_nombre";

	
	String GET_INVENTARIO = " select comp_id, comp_cod, comp_nombre from componentes_inventario where comp_status ='A'";
	
	
	@Select(GET_OPERATIVO)
	public List<ListOperativoVO> listaOperativo();
	
	@Select(GET_TRANSPORTE)
	public List<ListMediosTraspVO> listaMediosTrasp();
	
	@Select(GET_CAUSA_INGRESO_CON_I)
	public List<ListCausaIngresoVO> listaCausaIngreso();
	
	@Select(GET_CAUSA_INGRESO_SIN_I)
	public List<ListCausaIngresoVO> listaCausaIngresoSin();
	
	@Select(GET_DEPOSITO)
	public List<ListDepositoVO> listaDeposito(@Param("emp_id") Long emp_id, @Param("dep_id") int dep_id);
	
	@Select(GET_DEPOSITO_ORIGEN)
	public List<ListDepositoVO> listaDepositoOrigen();
	
	@Select(GET_INVENTARIO)
	public List<ListInventarioVO> listaInventario();

	@Select(GET_NO_GRUAS)
	public List<ListaNoGruasVO> listaNoGruas();

	/**
	 * @author dagoberto
	 * @return causa_cod, causa_nombre con id 09
	 * * modulo remision a deposito - con infraccion
	 * */
	@Select("select causa_cod, causa_nombre "
			 + " from causa_ingreso where CAUSA_STATUS='A' and causa_id = '09' order by causa_nombre " )
	public List<ListCausaIngresoVO> listaCausaIngresoByTraslado();
	
	@Select("select GRUA_ID as gruaId, GRUA_COD as gruaCod, GRUA_SMS as gruaSms, CONSE_ID as conseId, GSTAT_ID as gStatId, "
			+ "GRUA_STATUS as gruaStatus, GRUA_COBRAR_ARRASTRE as gruaCobrarArrastre "
			+ "from GRUAS where GRUA_COD = #{gruaCod}")
	public GruaVO buscaGruaPorCod(@Param("gruaCod") String gruaCod);
}
