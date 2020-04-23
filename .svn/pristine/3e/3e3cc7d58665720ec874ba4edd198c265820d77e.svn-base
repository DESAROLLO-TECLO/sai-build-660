package mx.com.teclo.saicdmx.persistencia.hibernate.dao.salidas;


import java.io.Serializable;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos.IngresosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.SalidasDTO;



@Repository("salidaDAO")
public class SalidaDAOImpl extends BaseDaoHibernate<SalidasDTO> implements SalidaDAO {

		
	@Override
	public SalidasDTO guardarRegistroToSalida(SalidasDTO salidaDTO) {
		salidaDTO.setFchSalida(new Date());
		salidaDTO.setModificadoPor(salidaDTO.getCreadoPor());
		salidaDTO.setFechaCreacion(new Date());
	//	salidaDTO.setFchUltimaModificacion(new Date());
		save(salidaDTO);
		return salidaDTO;
		
	}

	@Override
	public  SalidasDTO getInfraccionByIdSalida(String idSalidas) {
		
		Criteria criteria = getCurrentSession().createCriteria(SalidasDTO.class);
		criteria.add(Restrictions.eq("idSalida", idSalidas));
		return (SalidasDTO) criteria.list();
		
	}

	@Override
	public SalidasDTO buscaTrasladoUpdate(String infracNum, Long depId) {
		Criteria criteria =  getCurrentSession().createCriteria(SalidasDTO.class);
		criteria.createAlias("numinfraccion", "inf");
		criteria.add(Restrictions.eq("activo", 1L));
		criteria.add(Restrictions.eq("stMovimiento", "T"));
		criteria.add(Restrictions.eq("depDestino", depId.toString()));
		criteria.add(Restrictions.eq("inf.infracNum", infracNum));
	
		return (SalidasDTO) criteria.uniqueResult();
	}
	

	
//	@Override
//	public boolean guardarRegistro(GuardarSalidaVO convertVO, List<FilesSalidaVO> filesVO) {
//		
//		List<ImgSalidasDTO> imagenesListDTO = new ArrayList<ImgSalidasDTO>();
//		
//		
//		
//
//		for (FilesSalidaVO i : filesVO) {
//			ImgSalidasDTO objeto = new ImgSalidasDTO();
//			if (validarImagenSalida(i.getFiletype())) {
//				objeto.setImagen(arrayBytesToBlob2(i.getBase64()));
//				objeto.setSalidaDTO(salidaDTO);
//				imagenesListDTO.add(objeto);
//				// bandera = salidaDAO.guardarRegistroImg(i.getBase64(),
//				// convertVO);
//			} else {
//				// throw new NotFoundException("No se pudo cargar la imagen");
//			}
//		}
//		
//		salidaDTO.setImagenes(imagenesListDTO);
//
//	
//		return true;
//	}

	

//	private SalidasDTO insertarCamposSalidasVehiculo(SalidasDTO salidasObject) {
//		
//		//flush();
//		return salidasObject;
//	}

//	public SalidasDTO autoincrementaIdSalida(final SalidasDTO imgSDTO) {
//		getSession().doWork(new Work() {
//			@Override
//			public void execute(Connection connection) throws SQLException {
//				PreparedStatement call = connection
//						.prepareStatement("SELECT NVL(MAX(ID_SALIDA)+1 , 1) FROM SALIDA_VEHICULO");
//				ResultSet rs = null;
//				long res = 0L;
//				rs = call.executeQuery();
//
//				while (rs.next()) {
//					res = rs.getLong(1);
//				}
//
//				imgSDTO.setIdSalida(res);
//
//			}
//		});
//		return imgSDTO;
//	}







	// @Override
	// public boolean guardarRegistroImg(String file) {
	//
	// Blob imgBlob = arrayBytesToBlob2(bt);
	// //salidaImgDAO.insertarImgSalidas(salidaDTO2, imgBlob);
	// return true;
	// }
	//
}
