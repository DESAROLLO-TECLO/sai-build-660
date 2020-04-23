package mx.com.teclo.saicdmx.persistencia.hibernate.dao.salidas;

import java.sql.Blob;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.ImgSalidasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.SalidasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarTrasladoVO;


public interface SalidaImgDAO extends BaseDao<ImgSalidasDTO> {

	void insertarImgSalidas(GuardarSalidaVO convertVO, Blob imgBlob, Long idMovimiento);

	List<ImgSalidasDTO> getExpediente(String idSalida, String tipo, String nombreComp);

	void insertarImgSalidasIngreso(GuardarTrasladoVO convertVO, Blob imgBlob, Long idMovimiento);

}
