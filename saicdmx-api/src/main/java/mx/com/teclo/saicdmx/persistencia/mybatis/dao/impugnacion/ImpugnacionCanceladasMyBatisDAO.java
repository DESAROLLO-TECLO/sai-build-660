package mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VConsultaCanceladas;

@Mapper
public interface ImpugnacionCanceladasMyBatisDAO {
	
String V_CONSULTA_CANCELACIONES = "SELECT infraccion as infracNum, "
			                                       + "vehiculo_placa as vehiculoPlaca,"
			                                       + " infrac_fecha as fecha,"
			                                       + " infrac_calle as infracCalle,"
			                                       + " infrac_articulo as motivacion,"
			                                       + " sancion_dias as sancionDias,"
			                                       + " infrac_pagada as infracPagada,"
			                                       + " deposito as deposito"
			                                        + " from V_CONSULTA_CANCELACIONES ";
			                                        
String GET_V_PLACA_PERMISO = V_CONSULTA_CANCELACIONES + "WHERE VEHICULO_PLACA = #{valor} ";
String GET_V_BOLETA_PREIMPRESA = V_CONSULTA_CANCELACIONES + "WHERE INFRACTOR_LICENCIA = #{valor}";
String GET_V_NUMERO_INFRACCION = V_CONSULTA_CANCELACIONES + "WHERE INFRACCION = #{valor} ";
String GET_V_LICENCIA_CONDUCIR = V_CONSULTA_CANCELACIONES + "WHERE IMPRESA = #{valor} ";
			                                        
@Select(GET_V_PLACA_PERMISO)
public List<VConsultaCanceladas> obtieneCanceladasPlaca(@Param("valor") String valor);

@Select(GET_V_BOLETA_PREIMPRESA)
public List<VConsultaCanceladas> obtieneCanceladasPreimpresa(@Param("valor") String valor);

@Select(GET_V_NUMERO_INFRACCION)
public List<VConsultaCanceladas> obtieneCanceladasInfraccion(@Param("valor") String valor);

@Select(GET_V_LICENCIA_CONDUCIR)
public List<VConsultaCanceladas> obtieneCanceladasLicencia(@Param("valor") String valor);

}
