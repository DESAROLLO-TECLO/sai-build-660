package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.util.Date;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionesValidacionDTO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

@Repository("transaccionesValidacionDAO")
public class TransaccionesValidacionDAOImpl extends BaseDaoHibernate<TransaccionesValidacionDTO> implements TransaccionesValidacionDAO{

	private RutinasTiempoImpl rutinasTiempo= new RutinasTiempoImpl();
	
	@Override
	@Transactional
	public String fechaValidacion(Long tranId, String tipoOperacion,Long cajaId) {
		Date fechaValidacionDate =  (Date) getCurrentSession().createCriteria(TransaccionesValidacionDTO.class)
		        .setProjection(Projections.max("fechaValidacion"))
		        .add(Restrictions.eq("tranId", tranId))
		        .add(Restrictions.eq("cajaId", cajaId))
		        .add(Restrictions.eq("tipoOperacion", tipoOperacion))
		        .uniqueResult();
		String fechaValidacion=fechaValidacionDate==null ? "" : rutinasTiempo.getFecha_ddMMYYYY_hhmmss(fechaValidacionDate);
			
		return fechaValidacion;
	}
	
	@Override
	@Transactional
	public void saveTransaccionValidacionDTO(TransaccionesValidacionDTO tranValidacionDTO){
		getCurrentSession().save(tranValidacionDTO);
		getCurrentSession().flush();
	}
}
