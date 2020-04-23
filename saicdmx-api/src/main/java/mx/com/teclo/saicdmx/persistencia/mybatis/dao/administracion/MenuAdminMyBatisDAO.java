package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.MenuAdminVO;

@Mapper
public interface MenuAdminMyBatisDAO {

	String CONSULTA_MENUS_INACTIVOS = 
			"select menu_id, (select menu_texto from menu_dinamico " 
                        +"where menu_id = m.menu_superior) || '-' || menu_texto menu " 
                        +"from menu_dinamico m where menu_id not in (SELECT menu_id "
                        +"FROM V_ADMIN_SERVICIO_ACTIVO "
                        +"where perfil_id = #{valor}) " 
                        +"and menu_superior != 0 and st_activo = 1 and id_aplicacion = 1 order by menu_orden"; //valido = st_activo
	
	
	String CONSULTA_MENUS_ACTIVOS = "SELECT MENU_ID,MENU "
			+ "FROM V_ADMIN_SERVICIO_ACTIVO "
             + "WHERE perfil_id = #{valor} order by menu_orden";
	
	
	@Select(CONSULTA_MENUS_INACTIVOS)
	List<MenuAdminVO> obtieneListaMenusInactivos (@Param("valor") Long id);
	
	@Select(CONSULTA_MENUS_ACTIVOS)
	List<MenuAdminVO> obtieneListaMenusActivos (@Param("valor") Long id);
	
	
}
