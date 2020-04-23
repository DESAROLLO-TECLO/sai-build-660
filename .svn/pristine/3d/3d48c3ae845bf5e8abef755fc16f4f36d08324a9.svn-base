package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionDTO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ConsultaTransaccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosTransaccionVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

import org.hibernate.Transaction;

@Repository("transaccionesDAO")
public class TransaccionesDAOImpl extends BaseDaoHibernate<TransaccionDTO> implements TransaccionesDAO{
	
	private RutinasTiempoImpl rutinasTiempo= new RutinasTiempoImpl();
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ConsultaTransaccionesVO> getAllTransacciones(String cajaId){
		StringBuilder consultaTrnsacciones = new StringBuilder("");
		
		consultaTrnsacciones.append("SELECT  Fechavalidacion.FECHA_MAYOR as fechaValidacion,tr.TRAN_ID as tranId,tr.TRAN_IMPORTE as tranImporte,tr.INFRAC_NUM as infracNum,tr.BAN_NOMBRE as banNombre, ");
		consultaTrnsacciones.append(" tr.TRAN_TARJETA as tranTarjeta,tr.TRAN_NOMBRE as tranNombre,tr.TRAN_RESPUESTA as tranRespuesta,tr.TRAN_NUM_AUTORIZA as tranNumAutoriza, ");
		consultaTrnsacciones.append(" TO_CHAR(tr.TRAN_FECHA,'dd/mm/yyyy') as tranFecha, "); 
		consultaTrnsacciones.append(" tr.TRAN_REFERENCIA as tranReferencia,tr.TRAN_LINEA_BANCO as voucher,tr.TRAN_ORDEN as numOperacion ");
		consultaTrnsacciones.append(" FROM    Transacciones tr Left JOIN (  SELECT  tran_id,MAX(FECHA_VALIDACION) AS FECHA_MAYOR,Tipo_Operacion ");
		consultaTrnsacciones.append(" FROM    Transacciones_Validacion GROUP BY TRAN_ID,Tipo_Operacion ) Fechavalidacion ");
		consultaTrnsacciones.append(" ON   Tr.Tran_Id =Fechavalidacion.Tran_Id AND Fechavalidacion.Tipo_Operacion='VENTA' ");
		consultaTrnsacciones.append(" WHERE tr.CAJA_ID = :parameterCajaId and tr.Tran_Respuesta='A' ");
		consultaTrnsacciones.append(" AND Tr.Tran_Status='A' ORDER BY tr.TRAN_FECHA DESC");
		
		SQLQuery query =getCurrentSession().createSQLQuery(consultaTrnsacciones.toString());
		query.setParameter("parameterCajaId", cajaId);
		query.addScalar("tranId", LongType.INSTANCE);
		query.addScalar("numOperacion", StringType.INSTANCE);
		query.addScalar("tranReferencia", StringType.INSTANCE);
		query.addScalar("infracNum", StringType.INSTANCE);
		query.addScalar("tranImporte", DoubleType.INSTANCE);
		query.addScalar("banNombre", StringType.INSTANCE);
		query.addScalar("tranTarjeta", StringType.INSTANCE);
		query.addScalar("tranNombre", StringType.INSTANCE);
		query.addScalar("tranRespuesta", StringType.INSTANCE);
		query.addScalar("tranNumAutoriza", StringType.INSTANCE);
		query.addScalar("tranFecha", StringType.INSTANCE);
		query.addScalar("fechaValidacion", StringType.INSTANCE);
		query.addScalar("voucher", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConsultaTransaccionesVO.class));
		List<ConsultaTransaccionesVO> listaTransacciones = (List<ConsultaTransaccionesVO>)query.list();
		return   listaTransacciones;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ConsultaTransaccionesVO> getTransaccionesFechas(String fechaInicio, String fechaFin, String cajaId){
		StringBuilder consultaTrnsacciones = new StringBuilder("");
		
		consultaTrnsacciones.append("SELECT  Fechavalidacion.FECHA_MAYOR as fechaValidacion,tr.TRAN_ID as tranId,tr.TRAN_IMPORTE as tranImporte,tr.INFRAC_NUM as infracNum,tr.BAN_NOMBRE as banNombre, ");
		consultaTrnsacciones.append(" tr.TRAN_TARJETA as tranTarjeta,tr.TRAN_NOMBRE as tranNombre,tr.TRAN_RESPUESTA as tranRespuesta,tr.TRAN_NUM_AUTORIZA as tranNumAutoriza, ");
		consultaTrnsacciones.append(" TO_CHAR(tr.TRAN_FECHA,'dd/mm/yyyy') as tranFecha, "); 
		consultaTrnsacciones.append(" tr.TRAN_REFERENCIA as tranReferencia,tr.TRAN_LINEA_BANCO as voucher,tr.TRAN_ORDEN as numOperacion ");
		consultaTrnsacciones.append(" FROM    Transacciones tr Left JOIN (  SELECT  tran_id,MAX(FECHA_VALIDACION) AS FECHA_MAYOR,Tipo_Operacion ");
		consultaTrnsacciones.append(" FROM    Transacciones_Validacion GROUP BY TRAN_ID,Tipo_Operacion ) Fechavalidacion ");
		consultaTrnsacciones.append(" ON   Tr.Tran_Id =Fechavalidacion.Tran_Id AND Fechavalidacion.Tipo_Operacion='VENTA' ");
		consultaTrnsacciones.append(" WHERE tr.CAJA_ID = :parameterCajaId and tr.Tran_Respuesta='A' ");
		consultaTrnsacciones.append(" AND TRUNC(tr.TRAN_FECHA) BETWEEN (TO_DATE( :parameterFechaInicio , 'dd/mm/yyyy')) AND (TO_DATE( :parameterFechaFin , 'dd/mm/yyyy'))");
		consultaTrnsacciones.append(" AND Tr.Tran_Status='A'  ORDER BY tr.TRAN_FECHA DESC");
		
		SQLQuery query =getCurrentSession().createSQLQuery(consultaTrnsacciones.toString());
		query.setParameter("parameterCajaId", cajaId);
		query.setParameter("parameterFechaInicio", fechaInicio);
		query.setParameter("parameterFechaFin", fechaFin);
		query.addScalar("tranId", LongType.INSTANCE);
		query.addScalar("numOperacion", StringType.INSTANCE);
		query.addScalar("tranReferencia", StringType.INSTANCE);
		query.addScalar("infracNum", StringType.INSTANCE);
		query.addScalar("tranImporte", DoubleType.INSTANCE);
		query.addScalar("banNombre", StringType.INSTANCE);
		query.addScalar("tranTarjeta", StringType.INSTANCE);
		query.addScalar("tranNombre", StringType.INSTANCE);
		query.addScalar("tranRespuesta", StringType.INSTANCE);
		query.addScalar("tranNumAutoriza", StringType.INSTANCE);
		query.addScalar("tranFecha", StringType.INSTANCE);
		query.addScalar("fechaValidacion", StringType.INSTANCE);
		query.addScalar("voucher", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConsultaTransaccionesVO.class));
		List<ConsultaTransaccionesVO> listaTransacciones = (List<ConsultaTransaccionesVO>)query.list();
		return   listaTransacciones;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ConsultaTransaccionesVO> getTransaccionesFechaInicio(String fechaInicio,String cajaId){
		StringBuilder consultaTrnsacciones = new StringBuilder("");
		
		consultaTrnsacciones.append("SELECT  Fechavalidacion.FECHA_MAYOR as fechaValidacion,tr.TRAN_ID as tranId,tr.TRAN_IMPORTE as tranImporte,tr.INFRAC_NUM as infracNum,tr.BAN_NOMBRE as banNombre, ");
		consultaTrnsacciones.append(" tr.TRAN_TARJETA as tranTarjeta,tr.TRAN_NOMBRE as tranNombre,tr.TRAN_RESPUESTA as tranRespuesta,tr.TRAN_NUM_AUTORIZA as tranNumAutoriza, ");
		consultaTrnsacciones.append(" TO_CHAR(tr.TRAN_FECHA,'dd/mm/yyyy') as tranFecha, "); 
		consultaTrnsacciones.append(" tr.TRAN_REFERENCIA as tranReferencia,tr.TRAN_LINEA_BANCO as voucher,tr.TRAN_ORDEN as numOperacion ");
		consultaTrnsacciones.append(" FROM    Transacciones tr Left JOIN (  SELECT  tran_id,MAX(FECHA_VALIDACION) AS FECHA_MAYOR,Tipo_Operacion ");
		consultaTrnsacciones.append(" FROM    Transacciones_Validacion GROUP BY TRAN_ID,Tipo_Operacion ) Fechavalidacion ");
		consultaTrnsacciones.append(" ON   Tr.Tran_Id =Fechavalidacion.Tran_Id AND Fechavalidacion.Tipo_Operacion='VENTA' ");
		consultaTrnsacciones.append(" WHERE tr.CAJA_ID = :parameterCajaId and tr.Tran_Respuesta='A' ");
		consultaTrnsacciones.append(" AND TRUNC(tr.TRAN_FECHA) >= TO_DATE(:parameterFechaInicio, 'DD/MM/RR')");
		consultaTrnsacciones.append(" AND Tr.Tran_Status='A'  ORDER BY tr.TRAN_FECHA DESC");
		
		SQLQuery query =getCurrentSession().createSQLQuery(consultaTrnsacciones.toString());
		query.setParameter("parameterCajaId", cajaId);
		query.setParameter("parameterFechaInicio", fechaInicio);
		query.addScalar("tranId", LongType.INSTANCE);
		query.addScalar("numOperacion", StringType.INSTANCE);
		query.addScalar("tranReferencia", StringType.INSTANCE);
		query.addScalar("infracNum", StringType.INSTANCE);
		query.addScalar("tranImporte", DoubleType.INSTANCE);
		query.addScalar("banNombre", StringType.INSTANCE);
		query.addScalar("tranTarjeta", StringType.INSTANCE);
		query.addScalar("tranNombre", StringType.INSTANCE);
		query.addScalar("tranRespuesta", StringType.INSTANCE);
		query.addScalar("tranNumAutoriza", StringType.INSTANCE);
		query.addScalar("tranFecha", StringType.INSTANCE);
		query.addScalar("fechaValidacion", StringType.INSTANCE);
		query.addScalar("voucher", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConsultaTransaccionesVO.class));
		List<ConsultaTransaccionesVO> listaTransacciones = (List<ConsultaTransaccionesVO>)query.list();
		return   listaTransacciones;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ConsultaTransaccionesVO> getTransaccionesFechaFin(String fechaFin,String cajaId){
		StringBuilder consultaTrnsacciones = new StringBuilder("");
			
		consultaTrnsacciones.append("SELECT  Fechavalidacion.FECHA_MAYOR as fechaValidacion,tr.TRAN_ID as tranId,tr.TRAN_IMPORTE as tranImporte,tr.INFRAC_NUM as infracNum,tr.BAN_NOMBRE as banNombre, ");
		consultaTrnsacciones.append(" tr.TRAN_TARJETA as tranTarjeta,tr.TRAN_NOMBRE as tranNombre,tr.TRAN_RESPUESTA as tranRespuesta,tr.TRAN_NUM_AUTORIZA as tranNumAutoriza, ");
		consultaTrnsacciones.append(" TO_CHAR(tr.TRAN_FECHA,'dd/mm/yyyy') as tranFecha, "); 
		consultaTrnsacciones.append(" tr.TRAN_REFERENCIA as tranReferencia,tr.TRAN_LINEA_BANCO as voucher,tr.TRAN_ORDEN as numOperacion ");
		consultaTrnsacciones.append(" FROM    Transacciones tr Left JOIN (  SELECT  tran_id,MAX(FECHA_VALIDACION) AS FECHA_MAYOR,Tipo_Operacion ");
		consultaTrnsacciones.append(" FROM    Transacciones_Validacion GROUP BY TRAN_ID,Tipo_Operacion ) Fechavalidacion ");
		consultaTrnsacciones.append(" ON   Tr.Tran_Id =Fechavalidacion.Tran_Id AND Fechavalidacion.Tipo_Operacion='VENTA' ");
		consultaTrnsacciones.append(" WHERE tr.CAJA_ID = :parameterCajaId and tr.Tran_Respuesta='A' ");
		consultaTrnsacciones.append(" AND TRUNC(tr.TRAN_FECHA) <= TO_DATE(:parameterFechaFin, 'DD/MM/RR')");
		consultaTrnsacciones.append(" AND Tr.Tran_Status='A'  ORDER BY tr.TRAN_FECHA DESC");
		
		SQLQuery query =getCurrentSession().createSQLQuery(consultaTrnsacciones.toString());
		query.setParameter("parameterCajaId", cajaId);
		query.setParameter("parameterFechaFin", fechaFin);
		query.addScalar("tranId", LongType.INSTANCE);
		query.addScalar("numOperacion", StringType.INSTANCE);
		query.addScalar("tranReferencia", StringType.INSTANCE);
		query.addScalar("infracNum", StringType.INSTANCE);
		query.addScalar("tranImporte", DoubleType.INSTANCE);
		query.addScalar("banNombre", StringType.INSTANCE);
		query.addScalar("tranTarjeta", StringType.INSTANCE);
		query.addScalar("tranNombre", StringType.INSTANCE);
		query.addScalar("tranRespuesta", StringType.INSTANCE);
		query.addScalar("tranNumAutoriza", StringType.INSTANCE);
		query.addScalar("tranFecha", StringType.INSTANCE);
		query.addScalar("fechaValidacion", StringType.INSTANCE);
		query.addScalar("voucher", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConsultaTransaccionesVO.class));
		List<ConsultaTransaccionesVO> listaTransacciones = (List<ConsultaTransaccionesVO>)query.list();
		return   listaTransacciones;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<DatosTransaccionVO> getTransaccionNumInfrac(String infracNum,String cajaId){
		StringBuilder consultaTrnsacciones = new StringBuilder("");
		
		consultaTrnsacciones.append("SELECT  Fechavalidacion.FECHA_MAYOR as fechaValidacion,tr.TRAN_ID as tranID,tr.TRAN_IMPORTE as tranImporte,tr.INFRAC_NUM as infracNum,tr.BAN_NOMBRE as banNombre, ");
		consultaTrnsacciones.append(" tr.TRAN_TARJETA as tranTarjeta,tr.TRAN_NOMBRE as tranNombre,tr.TRAN_RESPUESTA as tranRespuesta,tr.TRAN_NUM_AUTORIZA as tranNumAutorizacion, ");
		consultaTrnsacciones.append(" TO_CHAR(tr.TRAN_FECHA,'dd/mm/yyyy') AS tranFecha, "); 
		consultaTrnsacciones.append(" tr.TRAN_REFERENCIA as tranReferencia,tr.TRAN_LINEA_BANCO as tranLineaBanco,tr.TRAN_ORDEN as tranOrden ");
		consultaTrnsacciones.append(" FROM    Transacciones tr Left JOIN (SELECT  tran_id,MAX(FECHA_VALIDACION) AS FECHA_MAYOR,Tipo_Operacion ");
		consultaTrnsacciones.append(" FROM    Transacciones_Validacion GROUP BY TRAN_ID,Tipo_Operacion ) Fechavalidacion ");
		consultaTrnsacciones.append(" ON   Tr.Tran_Id =Fechavalidacion.Tran_Id AND Fechavalidacion.Tipo_Operacion='VENTA' ");
		consultaTrnsacciones.append(" WHERE tr.CAJA_ID = :parameterCajaId and tr.Tran_Respuesta='A' ");
		consultaTrnsacciones.append(" AND Tr.Tran_Status='A' AND tr.INFRAC_NUM = :parameterInfracNum ORDER BY tr.TRAN_FECHA DESC");
		
		SQLQuery query =getCurrentSession().createSQLQuery(consultaTrnsacciones.toString());
		query.setParameter("parameterCajaId", cajaId);
		query.setParameter("parameterInfracNum", infracNum);
		query.addScalar("tranID", LongType.INSTANCE);
		query.addScalar("tranOrden", StringType.INSTANCE);
		query.addScalar("tranReferencia", StringType.INSTANCE);
		query.addScalar("infracNum", StringType.INSTANCE);
		query.addScalar("tranImporte", DoubleType.INSTANCE);
		query.addScalar("banNombre", StringType.INSTANCE);
		query.addScalar("tranTarjeta", StringType.INSTANCE);
		query.addScalar("tranNombre", StringType.INSTANCE);
		query.addScalar("tranRespuesta", StringType.INSTANCE);
		query.addScalar("tranNumAutorizacion", StringType.INSTANCE);
		query.addScalar("tranFecha", StringType.INSTANCE);
		query.addScalar("fechaValidacion", StringType.INSTANCE);
		query.addScalar("tranLineaBanco", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(DatosTransaccionVO.class));
		List<DatosTransaccionVO> listaTransacciones=(List<DatosTransaccionVO>)query.list();
		return   listaTransacciones;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<DatosTransaccionVO> getTransaccionesNumReferencia(String numReferencia,String cajaId){
		StringBuilder consultaTrnsacciones = new StringBuilder("");
		
		consultaTrnsacciones.append("SELECT  Fechavalidacion.FECHA_MAYOR as fechaValidacion,tr.TRAN_ID as tranID,tr.TRAN_IMPORTE as tranImporte,tr.INFRAC_NUM as infracNum,tr.BAN_NOMBRE as banNombre, ");
		consultaTrnsacciones.append(" tr.TRAN_TARJETA as tranTarjeta,tr.TRAN_NOMBRE as tranNombre,tr.TRAN_RESPUESTA as tranRespuesta,tr.TRAN_NUM_AUTORIZA as tranNumAutorizacion, ");
		consultaTrnsacciones.append(" TO_CHAR(tr.TRAN_FECHA,'dd/mm/yyyy') as tranFecha, "); 
		consultaTrnsacciones.append(" tr.TRAN_REFERENCIA as tranReferencia,tr.TRAN_LINEA_BANCO as tranLineaBanco,tr.TRAN_ORDEN as tranOrden ");
		consultaTrnsacciones.append(" FROM    Transacciones tr Left JOIN (SELECT  tran_id,MAX(FECHA_VALIDACION) AS FECHA_MAYOR,Tipo_Operacion ");
		consultaTrnsacciones.append(" FROM    Transacciones_Validacion GROUP BY TRAN_ID,Tipo_Operacion ) Fechavalidacion ");
		consultaTrnsacciones.append(" ON   Tr.Tran_Id =Fechavalidacion.Tran_Id AND Fechavalidacion.Tipo_Operacion='VENTA' ");
		consultaTrnsacciones.append(" WHERE tr.CAJA_ID = :parameterCajaId and tr.Tran_Respuesta='A' ");
		consultaTrnsacciones.append(" AND Tr.Tran_Status='A' AND tr.TRAN_REFERENCIA = :parameterNumReferencia ORDER BY tr.TRAN_FECHA DESC");
		
		SQLQuery query =getCurrentSession().createSQLQuery(consultaTrnsacciones.toString());
		query.setParameter("parameterCajaId", cajaId);
		query.setParameter("parameterNumReferencia", numReferencia);
		query.addScalar("tranID", LongType.INSTANCE);
		query.addScalar("tranOrden", StringType.INSTANCE);
		query.addScalar("tranReferencia", StringType.INSTANCE);
		query.addScalar("infracNum", StringType.INSTANCE);
		query.addScalar("tranImporte", DoubleType.INSTANCE);
		query.addScalar("banNombre", StringType.INSTANCE);
		query.addScalar("tranTarjeta", StringType.INSTANCE);
		query.addScalar("tranNombre", StringType.INSTANCE);
		query.addScalar("tranRespuesta", StringType.INSTANCE);
		query.addScalar("tranNumAutorizacion", StringType.INSTANCE);
		query.addScalar("tranFecha", StringType.INSTANCE);
		query.addScalar("fechaValidacion", StringType.INSTANCE);
		query.addScalar("tranLineaBanco", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(DatosTransaccionVO.class));
		List<DatosTransaccionVO> listaTransacciones=(List<DatosTransaccionVO>)query.list();
		return   listaTransacciones;
	}
	
	@Override
	@Transactional
	public TransaccionDTO getTransaccionByTranID(Long tranId,CajaDTO cajaDTO){
		TransaccionDTO transaccionDTO = new TransaccionDTO();
		Criteria c =getCurrentSession().createCriteria(TransaccionDTO.class);
		c.add(Restrictions.eq("transaccionId.tranId",tranId));
		c.add(Restrictions.eq("transaccionId.cajaDTO.cajaId",cajaDTO.getCajaId()));
		transaccionDTO=(TransaccionDTO) c.list().get(0);
		return transaccionDTO;
	}
	
	@Override
	@Transactional
	public void updateTransaccionDTO(TransaccionDTO transaccionDTO){
		getCurrentSession().update(transaccionDTO);
		getCurrentSession().flush();
	}
	//CONSULTA TRANSACCIONES PARA CANCELACION
	
	@SuppressWarnings("unchecked")
	public List<TransaccionDTO> getTransaccionALLForCancelacion(){
		Criteria c =getCurrentSession().createCriteria(TransaccionDTO.class);
		c.add(Restrictions.between("tranFecha", rutinasTiempo.getFechaActual(), rutinasTiempo.getFechaActual()));
		c.add(Restrictions.eq("tranStatus","A"));
		c.add(Restrictions.eq("tranRespuesta","A"));
		c.addOrder(Property.forName("tranFecha").desc());
		return (List<TransaccionDTO>) c.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<TransaccionDTO> getTransaccionForCancelacionByNumOpera(String numOperacion){
		Criteria c =getCurrentSession().createCriteria(TransaccionDTO.class);
		c.add(Restrictions.eq("tranOrden", numOperacion));
		c.add(Restrictions.eq("tranStatus","A"));
		c.add(Restrictions.eq("tranRespuesta","A"));
		c.add(Restrictions.between("tranFecha",rutinasTiempo.getFechaActual(), rutinasTiempo.getFechaActual()));
		return (List<TransaccionDTO>) c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<TransaccionDTO> getTransaccionForCancelacionByNumInfrac(String numInfraccion){
		Criteria c =getCurrentSession().createCriteria(TransaccionDTO.class);
		c.add(Restrictions.eq("infracNum", numInfraccion));
		c.add(Restrictions.eq("tranStatus","A"));
		c.add(Restrictions.eq("tranRespuesta","A"));
		c.add(Restrictions.between("tranFecha", rutinasTiempo.getFechaActual(), rutinasTiempo.getFechaActual()));
		return (List<TransaccionDTO>) c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<TransaccionDTO> getTransaccionForCancelacionByNumRefe(String numReferencia){
		Criteria c =getCurrentSession().createCriteria(TransaccionDTO.class);
		c.add(Restrictions.eq("tranReferencia", numReferencia));
		c.add(Restrictions.eq("tranStatus","A"));
		c.add(Restrictions.eq("tranRespuesta","A"));
		c.add(Restrictions.between("tranFecha", rutinasTiempo.getFechaActual(), rutinasTiempo.getFechaActual()));
		return (List<TransaccionDTO>) c.list();
	}
	
}
