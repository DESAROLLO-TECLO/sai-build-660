package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;


import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionesCanceladasDTO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

@Repository
public class TransaccionesCanceladasDAOImpl extends BaseDaoHibernate<TransaccionesCanceladasDTO> implements TransaccionesCanceladasDAO{

	private RutinasTiempoImpl rutinasTiempoImpl=new RutinasTiempoImpl();
	
	
	public List<TransaccionesCanceladasDTO> consultaAllTransaccionesCanceladas(){
		Criteria t=getCurrentSession().createCriteria(TransaccionesCanceladasDTO.class);
		 t.addOrder(Property.forName("tranFecha").desc());
		 List<TransaccionesCanceladasDTO> list = (List<TransaccionesCanceladasDTO>) t.list();
		 return list;
	}
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasInfraccion(String numInfraccion){
		Criteria t=getCurrentSession().createCriteria(TransaccionesCanceladasDTO.class);
		t.add(Restrictions.eq("infracNum", numInfraccion));
		List<TransaccionesCanceladasDTO> list = (List<TransaccionesCanceladasDTO>) t.list();
		return list;
	}
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasNumOperacion(String numOperacion){
		Criteria t=getCurrentSession().createCriteria(TransaccionesCanceladasDTO.class);
		t.add(Restrictions.eq("numOperacion", numOperacion));
		List<TransaccionesCanceladasDTO> list = (List<TransaccionesCanceladasDTO>) t.list();
		return list;
	}
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasReferencia(String numReferencia){
		Criteria t=getCurrentSession().createCriteria(TransaccionesCanceladasDTO.class);
		t.add(Restrictions.eq("tranReferencia", numReferencia));
		List<TransaccionesCanceladasDTO> list = (List<TransaccionesCanceladasDTO>) t.list();
		return list;
	}
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasFechaInico(String fechaInicio){
		Date fechaIni=rutinasTiempoImpl.convertirStringDate(fechaInicio,"dd/MM/yyyy");
		Criteria t=getCurrentSession().createCriteria(TransaccionesCanceladasDTO.class);
		t.add(Restrictions.ge("tranFecha", fechaIni));
		t.addOrder(Property.forName("tranFecha").desc());
		List<TransaccionesCanceladasDTO> list = (List<TransaccionesCanceladasDTO>) t.list();
		return list;
	}
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasFechasFin(String fechaFin){
		Date fechaFinal=rutinasTiempoImpl.convertirStringDate(fechaFin,"dd/MM/yyyy");
		Date fechFin=rutinasTiempoImpl.incrementarNumeroDias(fechaFinal,1);
		Criteria t=getCurrentSession().createCriteria(TransaccionesCanceladasDTO.class);
		t.add(Restrictions.lt("tranFecha", fechFin));
		t.addOrder(Property.forName("tranFecha").desc());
		List<TransaccionesCanceladasDTO> list = (List<TransaccionesCanceladasDTO>) t.list();
		return list;
	}
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasFechas(String fechaInicio,String fechaFin){
		Date fechaIni=rutinasTiempoImpl.convertirStringDate(fechaInicio,"dd/MM/yyyy");
		Date fechaFinal=rutinasTiempoImpl.convertirStringDate(fechaFin,"dd/MM/yyyy");
		Date fechFin=rutinasTiempoImpl.incrementarNumeroDias(fechaFinal,1);
		Criteria t=getCurrentSession().createCriteria(TransaccionesCanceladasDTO.class);
		t.add(Restrictions.ge("tranFecha", fechaIni));
		t.add(Restrictions.lt("tranFecha", fechFin));
		t.addOrder(Property.forName("tranFecha").desc());
		List<TransaccionesCanceladasDTO> list = (List<TransaccionesCanceladasDTO>) t.list();
		return list;
	}
	
	public TransaccionesCanceladasDTO consultaTransaccionCanceladaByTranId(Long tranId,String numOperacion){
		Criteria t=getCurrentSession().createCriteria(TransaccionesCanceladasDTO.class);
		t.add(Restrictions.eq("tranId",tranId));
		t.add(Restrictions.eq("numOperacion",numOperacion));
		
		return (TransaccionesCanceladasDTO) t.uniqueResult();
	}
	
	@Transactional
	public void guardarTransaccionCancelada(TransaccionesCanceladasDTO transacionCancelada){
		 getCurrentSession().saveOrUpdate(transacionCancelada);
		 getCurrentSession().flush();
	}
}
