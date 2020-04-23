package mx.com.teclo.saicdmx.negocio.service.controlsuministros;


import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialEliminarVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialModificacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialNuevoVO;

public interface AuxiliaresService {

		public DelegadoAuxiliarVO buscarAuxiliarPorId (Long id_registro);

		public DelegadoAuxiliarVO buscarAuxiliarPorPlaca(String oficial_placa);
		
		public DelegadoAuxiliarVO buscarAuxiliarAreaRegion(Long reg_id, Long upc_id);

		public OficialModificacionVO cambiarAuxiliar(OficialModificacionVO valores);
		
		public OficialNuevoVO altaAuxiliar(OficialNuevoVO valoresAux);

		public OficialEliminarVO altaBitacoraCambios(OficialEliminarVO valoresAuxBaja);
		
		public OficialEliminarVO eliminarAuxiliar(OficialEliminarVO valoresAuxBaja);

	
		
		
}
