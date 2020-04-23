package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.RegionalesUPCVO;
/**
 * 
 * @author javier07
 *
 */
@Mapper
public interface RegionalUsuarioMyBtisDAO {

	
	String OBTIENE_REGIONES_UPC="select c.reg_id || '-' || b.upc_id area_id, reg_nombre || '-' || upc_nombre area "
             + " from regionales_upc b, regionales c "
             + " where b.reg_id = c.reg_id and b.reg_id != 100 "
             + " order by reg_nombre, upc_nombre";
	
	/**
	 * @author javier07
	 * @param valor
	 * @return
	 */
	 @Select(OBTIENE_REGIONES_UPC)
     List<RegionalesUPCVO> obtieneRegiones_UPC(); 	 
}
