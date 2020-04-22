package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultadetecciones;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.DepositosEstadisticasVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.EntradaSalidaDepositosGraficaVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesEstadisticasVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesEstadisticasporDispositivoVO;

@Mapper
public interface InfraccionesEstadisticasMyBatisDAO {
	
	String INFRACCIONES_CREADAS_VS_PAGADAS = "SELECT to_char(INFRAC.FECHA_CREACION, 'YYYY-MM') as mes, "+
			"count(infrac_creadas.infrac_num) as infrac_creadas, "+
			"count(infrac_pagadas.infrac_num) as infrac_pagadas "+
			"FROM INFRACCIONES INFRAC "+
			"left join infracciones infrac_creadas "+
			"on INFRAC.infrac_num = infrac_creadas.infrac_num "+
			"and infrac_creadas.FECHA_CREACION BETWEEN TO_DATE( #{fechaInicio}, 'dd/mm/yyyy') AND TO_DATE( #{fechaFin}, 'dd/mm/yyyy') "+
			"left join infracciones infrac_pagadas "+
			"on INFRAC.infrac_num = infrac_pagadas.infrac_num "+
			"and infrac_pagadas.FECHA_CREACION BETWEEN TO_DATE( #{fechaInicio}, 'dd/mm/yyyy') AND TO_DATE( #{fechaFin}, 'dd/mm/yyyy') "+
			"and infrac_pagadas.INFRAC_PAGADA = 'S' "+
			"WHERE INFRAC.FECHA_CREACION BETWEEN TO_DATE( #{fechaInicio}, 'dd/mm/yyyy') AND TO_DATE( #{fechaFin}, 'dd/mm/yyyy') "+
			"GROUP BY to_char(INFRAC.FECHA_CREACION, 'YYYY-MM') "+
			"ORDER BY mes ASC";
	
	String INFRACCIONES_CREADAS_VS_PAGADAS_AÑOS = "SELECT to_char(INFRAC.FECHA_CREACION, 'YYYY') as mes, "+
			"count(infrac_creadas.infrac_num) as infrac_creadas, "+
			"count(infrac_pagadas.infrac_num) as infrac_pagadas "+
			"FROM INFRACCIONES INFRAC "+
			"left join infracciones infrac_creadas "+
			"on INFRAC.infrac_num = infrac_creadas.infrac_num "+
			"and infrac_creadas.FECHA_CREACION BETWEEN TO_DATE( #{fechaInicio}, 'dd/mm/yyyy') AND TO_DATE( #{fechaFin}, 'dd/mm/yyyy') "+
			"left join infracciones infrac_pagadas "+
			"on INFRAC.infrac_num = infrac_pagadas.infrac_num "+
			"and infrac_pagadas.FECHA_CREACION BETWEEN TO_DATE( #{fechaInicio}, 'dd/mm/yyyy') AND TO_DATE( #{fechaFin}, 'dd/mm/yyyy') "+
			"and infrac_pagadas.INFRAC_PAGADA = 'S' "+
			"WHERE INFRAC.FECHA_CREACION BETWEEN TO_DATE( #{fechaInicio}, 'dd/mm/yyyy') AND TO_DATE( #{fechaFin}, 'dd/mm/yyyy') "+
			"GROUP BY to_char(INFRAC.FECHA_CREACION, 'YYYY') "+
			"ORDER BY mes ASC";
	
	String INFRACCIONES_CREADAS_VS_PAGADAS_ESTATICO = "SELECT to_char(INFRAC.FECHA_CREACION, 'YYYY-MM') as mes, "+
			"count(infrac_creadas.infrac_num) as infrac_creadas, "+
			"count(infrac_pagadas.infrac_num) as infrac_pagadas "+
			"FROM INFRACCIONES INFRAC "+
			"left join infracciones infrac_creadas "+
			"on INFRAC.infrac_num = infrac_creadas.infrac_num "+
			"and infrac_creadas.FECHA_CREACION BETWEEN ADD_MONTHS(sysdate,-11) AND SYSDATE "+
			"left join infracciones infrac_pagadas "+
			"on INFRAC.infrac_num = infrac_pagadas.infrac_num "+
			"and infrac_pagadas.FECHA_CREACION BETWEEN ADD_MONTHS(sysdate,-11) AND SYSDATE "+
			"and infrac_pagadas.INFRAC_PAGADA = 'S' "+
			"WHERE INFRAC.FECHA_CREACION BETWEEN ADD_MONTHS(sysdate,-11) AND SYSDATE "+
			"GROUP BY to_char(INFRAC.FECHA_CREACION, 'YYYY-MM') "+
			"ORDER BY mes ASC";
		
	String INFRACCIONES_POR_DISPOSITIVO = 
			"SELECT label, COUNT(*) AS value FROM( "+
					"SELECT CASE "+
						"WHEN INFRAC_NUM LIKE '01%' THEN 'GRUA' "+
						"WHEN INFRAC_NUM LIKE '02%' THEN 'ACTA_ADMIN' "+
						"WHEN INFRAC_NUM LIKE '04%' THEN 'HANDHELD' "+
						"WHEN INFRAC_NUM LIKE '05%' THEN 'PARQUIMETRO' "+
						"WHEN INFRAC_NUM LIKE '03%' THEN 'RADAR-SSP' "+
						"WHEN INFRAC_NUM LIKE '07%' THEN 'CARRIL-CONF' "+
						"WHEN INFRAC_NUM LIKE '08%' THEN 'FOTO-MULTA' "+
						"END AS label, FECHA_CREACION FROM ( "+
					"SELECT INFRAC_NUM, FECHA_CREACION FROM INFRACCIONES "+
					"UNION ALL "+
					"SELECT INFRAC_NUM, FECHA_CREACION FROM INFRACCIONES_RADAR)) "+
						"WHERE FECHA_CREACION BETWEEN TO_DATE( #{fechaInicio}, 'dd/MM/yyyy') AND TO_DATE( #{fechaFin}, 'dd/MM/yyyy') "+
						"AND label IS NOT NULL "+
					"GROUP BY label";
	
	String INFRACCIONES_POR_DISPOSITIVO_ESTATICO = 
			"SELECT label, COUNT(*) AS value FROM( "+
					"SELECT CASE "+
						"WHEN INFRAC_NUM LIKE '01%' THEN 'GRUA' "+
						"WHEN INFRAC_NUM LIKE '02%' THEN 'ACTA_ADMIN' "+
						"WHEN INFRAC_NUM LIKE '04%' THEN 'HANDHELD' "+
						"WHEN INFRAC_NUM LIKE '05%' THEN 'PARQUIMETRO' "+
						"WHEN INFRAC_NUM LIKE '03%' THEN 'RADAR-SSP' "+
						"WHEN INFRAC_NUM LIKE '07%' THEN 'CARRIL-CONF' "+
						"WHEN INFRAC_NUM LIKE '08%' THEN 'FOTO-MULTA' "+
						"END AS label, FECHA_CREACION FROM ( "+
					"SELECT INFRAC_NUM, FECHA_CREACION FROM INFRACCIONES "+
					"UNION ALL "+
					"SELECT INFRAC_NUM, FECHA_CREACION FROM INFRACCIONES_RADAR)) "+
						"WHERE FECHA_CREACION BETWEEN trunc(add_months(sysdate, -11), 'mm') AND SYSDATE "+
						"AND label IS NOT NULL "+
					"GROUP BY label";
	
	String VEHICULOS_DEPOSITOS = 
			"SELECT COUNT(*) AS y, DEP_NOMBRE AS label, NVL(DEP_CAPACIDAD, 0) AS capacidad FROM DEPOSITOS DEP "+
			"INNER JOIN INGRESOS ING ON DEP.DEP_ID=ING.DEP_ID "+
			"WHERE DEP.DEP_ID IN (SELECT DEP.DEP_ID FROM DEPOSITOS) "+
			"AND ING.INGR_STATUS='A' GROUP BY DEP.DEP_NOMBRE, DEP.DEP_CAPACIDAD ORDER BY DEP_NOMBRE ASC";

	
	String ENTRADA_SALIDA_DEPOSITOS_DINAMICO= 
			"SELECT TO_CHAR(to_date(ing.INGR_FECHA,'dd/mm/yyyy'), 'MONTH','NLS_DATE_LANGUAGE=Spanish') || ' ' || Extract(YEAR FROM to_date(ing.INGR_FECHA)) as mes, "+
			"count(ing.INGR_FECHA) as cantidadIngreso, "+
			"count(ing.INGR_SALIDA) as cantidadSalida "+
			"from DEPOSITOS dep "+
			"left join  INGRESOS ing "+
			"ON DEP.DEP_ID=ING.DEP_ID "+
			"WHERE DEP.DEP_ID IN (SELECT DEP.DEP_ID FROM DEPOSITOS) AND ING.INGR_STATUS='A'  "+
			"AND TRUNC(ing.INGR_FECHA) BETWEEN  tO_DATE(#{fechaInicio},'dd/mm/yyyy') AND TO_DATE(#{fechaFin},'dd,mm,yyyy')"
			+ "GROUP BY TO_CHAR(to_date(ing.INGR_FECHA,'dd/mm/yyyy'), 'MONTH', 'NLS_DATE_LANGUAGE=Spanish') || ' ' || Extract(YEAR FROM to_date(ing.INGR_FECHA)) "
			+ "order by TO_CHAR(to_date(ing.INGR_FECHA,'dd/mm/yyyy'), 'MONTH', 'NLS_DATE_LANGUAGE=Spanish') || ' ' || Extract(YEAR FROM to_date(ing.INGR_FECHA)) asc";
	
	String ENTRADA_SALIDA_DEPOSITOS_DINAMICO_AÑOS= 
			"SELECT Extract(YEAR FROM ing.INGR_FECHA)as mes, "+
			"count(ing.INGR_FECHA) as cantidadIngreso, "+
			"count(ing.INGR_SALIDA) as cantidadSalida "+
			"from DEPOSITOS dep "+
			"left join  INGRESOS ing "+
			"ON DEP.DEP_ID=ING.DEP_ID "+
			"WHERE DEP.DEP_ID IN (SELECT DEP.DEP_ID FROM DEPOSITOS) AND ING.INGR_STATUS='A'  "+
			"AND TRUNC(ing.INGR_FECHA) BETWEEN  tO_DATE(#{fechaInicio},'dd/mm/yyyy') AND TO_DATE(#{fechaFin},'dd,mm,yyyy')"
			+ "GROUP BY TO_CHAR(ing.INGR_FECHA, 'YEAR'), Extract(YEAR FROM ing.INGR_FECHA) "
			+ "order by Extract(YEAR FROM ing.INGR_FECHA) asc";

	
	@Select(value = INFRACCIONES_CREADAS_VS_PAGADAS)
	 List<InfraccionesEstadisticasVO> infraccionesCreadasPagadas(
			@Param("fechaInicio") String fechaInicio,
			@Param ("fechaFin") String fechaFin) throws PersistenceException;
	
	@Select(value = INFRACCIONES_CREADAS_VS_PAGADAS_AÑOS)
	List<InfraccionesEstadisticasVO> infraccionesCreadasPagadasAños(
			@Param("fechaInicio") String fechaInicio,
			@Param ("fechaFin") String fechaFin) throws PersistenceException;
	
	@Select(value = INFRACCIONES_CREADAS_VS_PAGADAS_ESTATICO)
	 List<InfraccionesEstadisticasVO> infraccionesCreadasPagadasEstatico() throws PersistenceException;
	
	@Select(value = INFRACCIONES_POR_DISPOSITIVO)
		List<InfraccionesEstadisticasporDispositivoVO> infraccionesporDispositivo(
				@Param("fechaInicio") String fechaInicio,
				@Param ("fechaFin") String fechaFin) throws PersistenceException;
	
	@Select(value = INFRACCIONES_POR_DISPOSITIVO_ESTATICO)
	List<InfraccionesEstadisticasporDispositivoVO> infraccionesporDispositivoEstatico() throws PersistenceException;
	
	@Select(value=VEHICULOS_DEPOSITOS)
	List<DepositosEstadisticasVO> depositosEstadisticas() throws PersistenceException;
	
	@Select(value=ENTRADA_SALIDA_DEPOSITOS_DINAMICO_AÑOS)
	List<EntradaSalidaDepositosGraficaVO> entradaSalidaDepositosGrafica(
			@Param("fechaInicio") String fechaInicio,
			@Param ("fechaFin") String fechaFin) throws PersistenceException;
	
	@Select(value=ENTRADA_SALIDA_DEPOSITOS_DINAMICO)
	List<EntradaSalidaDepositosGraficaVO> entradaSalidaDepositosGraficaFechas(
			@Param("fechaInicio") String fechaInicio,
			@Param ("fechaFin") String fechaFin) throws PersistenceException;

}
