package mx.com.teclo.saicdmx.persistencia.hibernate.dao.estadistica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDTO;
import mx.com.teclo.saicdmx.persistencia.vo.estadistica.DatosVO;

@Repository
public class MapaDAOImpl extends BaseDaoHibernate<InfraccionDTO> implements  MapaDAO{
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map tipoInfraccionPorFecha(String[] tipoInfraccion, String fechai, String fechaf){
		
		String sqlInfraccionDTO = "";
		String sqlInfraccionRadarDTO = "";
		String sqlInfraccionDigitalización = "";
		
		String filtroInfraccionDTO = "";
		String filtroInfraccionRadarDTO = "";
		
		
		Map <String, List<DatosVO>> agrupado =  new HashMap<String, List<DatosVO>>();;
		for (String radar : tipoInfraccion) {
			String getTablaDTO = obtenerTabla(radar);
			
			if(getTablaDTO.equals("infracciones")){
				filtroInfraccionDTO = obtenerFiltroRadares(radar, filtroInfraccionDTO);
				sqlInfraccionDTO = "select dele.DEL_COD as codigo, substr(ir.INFRAC_NUM,1,2) as codigoradar, count(1) as infracciones from " + getTablaDTO + " ir join DELEGACIONES dele on dele.del_id = ir.INFRAC_M_DEL_ID where dele.edo_id = 9 and (ir.infrac_num like " + filtroInfraccionDTO + ") and trunc(ir.INFRAC_M_FECHA_HORA) between trunc(to_date('"+fechai+"','dd/MM/yyyy')) and trunc(to_date('"+fechaf+"','dd/MM/yyyy')) and ir.estatus_cancelacion is null GROUP BY dele.DEL_COD, substr(ir.INFRAC_NUM,1,2)";
			}else if(getTablaDTO.equals("infracciones_radar")){
				filtroInfraccionRadarDTO = obtenerFiltroRadares(radar, filtroInfraccionRadarDTO);
				sqlInfraccionRadarDTO = "select dele.DEL_COD as codigo, substr(ir.INFRAC_NUM,1,2) as codigoradar, count(1) as infracciones from " + getTablaDTO + " ir join DELEGACIONES dele on dele.del_id = ir.INFRAC_M_DEL_ID and dele.EDO_ID = ir.INFRAC_M_EDO_ID where dele.EDO_ID  = 9 and (ir.infrac_num like " + filtroInfraccionRadarDTO + ") and trunc(ir.INFRAC_M_FECHA_HORA) between trunc(to_date('"+fechai+"','dd/MM/yyyy')) and trunc(to_date('"+fechaf+"','dd/MM/yyyy')) and ir.ESTATUS_CANCELACION is null GROUP BY dele.DEL_COD, substr(ir.INFRAC_NUM,1,2)";
			}
			else{
				sqlInfraccionDigitalización = "select dele.DEL_COD as codigo, count(1) as infracciones from infracciones_digitalizacion ir join DELEGACIONES dele on dele.del_id = ir.INFRAC_M_DEL_ID where dele.edo_id = 9 and trunc(ir.INFRAC_M_FECHA_HORA) between trunc(to_date('"+fechai+"','dd/MM/yyyy')) and trunc(to_date('"+fechaf+"','dd/MM/yyyy')) and ir.estatus_cancelacion is null GROUP BY dele.DEL_COD";
			}
			
		}
		
		String[] sentencias = {sqlInfraccionDTO, sqlInfraccionRadarDTO, sqlInfraccionDigitalización};
		int countsentencias = 1;
		for(String sql : sentencias){
			if(!sql.equals("")){
				SQLQuery query = getCurrentSession().createSQLQuery(sql);  
				List rows = query.list();
				Iterator rowsIt = rows.iterator();
					
				while (rowsIt.hasNext()) {
					Object object[] = (Object[])rowsIt.next(); 
					
					String key = "";
					
					if(countsentencias == 3){
						key = "10";
					}else{
						key = object[1].toString();
					}
					
				    if(agrupado.containsKey(key)){
				        List<DatosVO> list = agrupado.get(key);
				        if(key.equals("10")){
				           list.add(new DatosVO(object[0].toString(), Integer.parseInt(object[1].toString())));
				        }
				        else{
				        	list.add(new DatosVO(object[0].toString(), Integer.parseInt(object[2].toString())));
				        }
				        
				    }else{
				        List<DatosVO> list = new ArrayList<DatosVO>();
				        if(key.equals("10")){
				          list.add(new DatosVO(object[0].toString(), Integer.parseInt( object[1].toString())));
				        }
				        else{
				        	list.add(new DatosVO(object[0].toString(), Integer.parseInt( object[2].toString())));
				        }
				        agrupado.put(key, list);
				    }
				}
			}
			
			countsentencias ++;
		}
		
		return agrupado;	
	}
	
	private String obtenerTabla(String codigoradar){
		String tabla = "";
		
		if(codigoradar.equals("01") || codigoradar.equals("04") || codigoradar.equals("05") || codigoradar.equals("06")){
			tabla = "infracciones";
		}else if(codigoradar.equals("03") || codigoradar.equals("07") || codigoradar.equals("08") || codigoradar.equals("09")){
			tabla = "infracciones_radar";
		}
		
		return tabla;
	}
	
	private String obtenerFiltroRadares(String radar, String clause){		
		if(clause.equals("")){
			clause = "'" + radar + "%'";
		}else{
			clause += " OR ir.infrac_num like '" + radar + "%'";
		}
		
		return clause;
	}
	
}