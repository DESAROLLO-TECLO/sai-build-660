package mx.com.teclo.saicdmx.persistencia.hibernate.dao.salidas;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.ImgSalidasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.SalidasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarTrasladoVO;


@Repository("salidaImgDAO")
public class SalidaImgDAOImp extends BaseDaoHibernate<ImgSalidasDTO > implements SalidaImgDAO {

	@Override
	public void insertarImgSalidas(GuardarSalidaVO convertVO, Blob imgBlob, Long movimiento) {
		ImgSalidasDTO imgSalida = new ImgSalidasDTO();
		autoincrementaIdImagenSalida(imgSalida);
		imgSalida.setActivo(1);
		imgSalida.setArchivo(imgBlob);
		imgSalida.setCreadoPor(convertVO.getCreadoPor());
		imgSalida.setFechaCreacion(new Date());
		imgSalida.setInfracNum(convertVO.getNuminfrac());
		imgSalida.setInfracNumCtrl(convertVO.getNumCtrl());
		imgSalida.setModificadoPor(convertVO.getCreadoPor());
		imgSalida.setNombreArchivo(convertVO.getNuminfrac()+"_"+convertVO.getTipoMovimiento()+"_"+movimiento+".jpg");
		imgSalida.setTipo(convertVO.getTipoMovimiento());
		imgSalida.setUltimaModificacion(new Date());
		save(imgSalida);
		flush();
		
	}
	
	@Override
	public List<ImgSalidasDTO> getExpediente(String idSalidasImg, String tipo, String nombComp) {
		Criteria criteria = getCurrentSession().createCriteria(ImgSalidasDTO.class);
		criteria.add(Restrictions.eq("infracNum", idSalidasImg));
		criteria.add(Restrictions.eq("tipo", tipo));
		criteria.add(Restrictions.eq("nombreArchivo", nombComp));
		return criteria.list();
	}

	public ImgSalidasDTO autoincrementaIdImagenSalida(final ImgSalidasDTO imgSDTO){
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement call = connection.prepareStatement("SELECT NVL(MAX(IMG_MOV_ID)+1 , 1) FROM MOV_DEP_IMAGENES");
				ResultSet rs = null;
				long res=0L;
				rs = call.executeQuery();
				
				while(rs.next()){
					res = rs.getLong(1);
				}
				
				imgSDTO.setImgMovId(res);
				
			}
		});
		return imgSDTO;
	}

	@Override
	public void insertarImgSalidasIngreso(GuardarTrasladoVO convertVO, Blob imgBlob, Long idMovimiento) {
		ImgSalidasDTO imgSalida = new ImgSalidasDTO();
		autoincrementaIdImagenSalida(imgSalida);
		imgSalida.setActivo(1);
		imgSalida.setArchivo(imgBlob);
		imgSalida.setCreadoPor(convertVO.getCreadoPor());
		imgSalida.setFechaCreacion(new Date());
		imgSalida.setInfracNum(convertVO.getInfracNum());
		imgSalida.setInfracNumCtrl(convertVO.getNumCtrl());
		imgSalida.setModificadoPor(convertVO.getCreadoPor());
		imgSalida.setNombreArchivo(convertVO.getInfracNum()+"_"+convertVO.getTipoMovimiento()+"_"+idMovimiento+".jpg");
		imgSalida.setTipo(convertVO.getTipoMovimiento());
		imgSalida.setUltimaModificacion(new Date());
		save(imgSalida);
		flush();
		
	}

	



}
