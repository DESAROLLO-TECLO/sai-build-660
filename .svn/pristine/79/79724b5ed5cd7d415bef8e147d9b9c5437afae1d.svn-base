package mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.exceptions.PersistenceException;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialEliminarVO;

@Mapper
public interface EliminarOficialMyBatisDAO {

	String INSERT_OFICIAL_BITACORA_CAMBIOS = " Insert into BITACORA_CAMBIOS "
			+ " (COMPONENTE_ID, CONCEPTO_ID, ID_REGISTRO, VALOR_ORIGINAL, VALOR_FINAL, MODIFICADO_POR)"
			+ " Values (3, 5, #{id_registro}, #{oficial_nombre} , '', #{idUser})";
		
	String DELETE_OFICIAL = " DELETE REGIONALES_UPC_DELEGADOS WHERE ID_REGISTRO =  #{id_registro} ";

	
		@Insert(INSERT_OFICIAL_BITACORA_CAMBIOS)
		public Integer altaBitacoraCambios(OficialEliminarVO valoresAuxBaja) throws PersistenceException;
		
		@Delete(DELETE_OFICIAL)
		public Integer eliminarAuxiliar(OficialEliminarVO valoresAuxBaja) throws PersistenceException;
		
}
