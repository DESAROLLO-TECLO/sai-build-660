package mx.com.teclo.saicdmx.persistencia.comun.assemblerReporte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.InfraccionConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.InfraccionImpVO;

public class ReportesAssembler {
	
	
	public Map<String, Object> obtieneParametrosImpCanceladas (InfraccionImpVO ib){
		
		 Map<String, Object> parametros = new HashMap<>();
		
		 StringBuffer infracNombre = new StringBuffer();
	     infracNombre.append(ib.getInfracIPaterno()).append(" ");
	     infracNombre.append(ib.getInfracIMaterno()).append(" ");
	     infracNombre.append(ib.getInfracINombre()).append(" ");
	     
	     String infrCalle = "";
	     String infrNumext = "";
	     String infrNumInt = "";
	     String infrCol = "";
	     String infrDel = "";
	     String infrEdo = "";
	     String auxComa = "";
	     
	     if( ib.getInfracICalle() != null || !ib.getInfracICalle().equals("")){
	         infrCalle = ib.getInfracICalle();
	    	 
	     }
	     if(ib.getInfracINumExt() != null || !ib.getInfracINumExt().equals(""))
	         infrNumext = " " + ib.getInfracINumExt();
	     if( ib.getInfracINumInt() != null || ib.getInfracINumInt().equals(""))
	         infrNumInt = " Int " + ib.getInfracINumInt();
	     if(ib.getInfracICalle() != null || ib.getInfracICalle().equals("") || ib.getInfracINumExt() != null || ib.getInfracINumExt().equals("") ||  ib.getInfracINumInt() != null || ib.getInfracINumInt().equals(""))
	         auxComa = ", ";
	     if(!ib.getInfracIColonia().equals("") || ib.getInfracIColonia() != null)
	         infrCol = auxComa + ib.getInfracIColonia();
	     if(ib.getInfracICalle() != null || !ib.getInfracICalle().equals("") || ib.getInfracINumExt() != null || !ib.getInfracINumExt().equals("") ||ib.getInfracINumInt() != null ||  !ib.getInfracINumInt().equals("") || ib.getInfracIColonia() != null || !ib.getInfracIColonia().equals("")  )
	         auxComa = ", ";
	     else
	         auxComa = "";
	     if(!ib.getInfracIDelId().equals("") || ib.getInfracIDelId() != null)
	         infrDel = auxComa + ib.getInfracIDelId();
	         if( ib.getInfracICalle() != null || !ib.getInfracICalle().equals("")  || ib.getInfracINumExt() != null ||  !ib.getInfracINumExt().equals("") || ib.getInfracINumInt() != null || !ib.getInfracINumInt().equals("") || ib.getInfracIColonia() != null || !ib.getInfracIColonia().equals("") )            	
	         auxComa = ", ";
	     else
	         auxComa = "";
	     if( ib.getInfracIEdoId() != null || !ib.getInfracIEdoId().equals(""))
	         infrEdo = auxComa + ib.getInfracIEdoId();
	     String infracDomicilio = "";
	     if(infrCalle != "")
	         infracDomicilio = infracDomicilio + infrCalle;
	     if(!ib.getInfracINumExt().equals("") && !infrNumext.equalsIgnoreCase(" "))
	         infracDomicilio = infracDomicilio + infrNumext;
	     if(!ib.getInfracINumInt().equals("") && !infrNumInt.equalsIgnoreCase(" Int "))
	         infracDomicilio = infracDomicilio + infrNumInt;
	     if(!ib.getInfracIColonia().equals("") && !infrCol.equalsIgnoreCase(", "))
	         infracDomicilio = infracDomicilio + infrCol;
	     if(!infrDel.equalsIgnoreCase("") && !infrDel.equalsIgnoreCase(",  "))
	         infracDomicilio = infracDomicilio + infrDel;
	     if(!infrEdo.equalsIgnoreCase("") && !infrEdo.equalsIgnoreCase(",  "))
	         infracDomicilio = infracDomicilio + infrEdo;
	     String resVehRecibio = "  ";
	     String resVehAusente = "  ";
	     String resVehNegoARecibir = "  ";
	     
	     if(ib.getrVehId().equalsIgnoreCase("se nego a recibir"))
	         resVehNegoARecibir = " X ";
	     if(ib.getrVehId().equalsIgnoreCase("Ausente"))
	         resVehAusente = " X ";
	     if(ib.getrVehId().equalsIgnoreCase("Recibio"))
	         resVehRecibio = " X ";

	     String infracDia;
	     String infracMes;
	     String infracAao;
	     String infracHora;
         String sancionDiasMensaje = "Multa en días"
         						   + " de salario mínimo"
         						   + " vigente en el Distrito Federal";
	     
	     infracDia = ib.getInfracMFechaHora().substring(0,2);
	     infracMes = ib.getInfracMFechaHora().substring(3,5);
	     infracAao = ib.getInfracMFechaHora().substring(6,10);
	     infracHora = ib.getInfracMFechaHora().substring(11,16);
         
	     
		 parametros.put("leyendaSalarioMinimo", "");  //CHECAR PARAMETRO, NO ESTABA DEFINIDO
		 parametros.put("infracNum", ib.getInfracNum());
	     parametros.put("infracFecha", ib.getInfracMFechaHora());
	     parametros.put("infracNombre", infracNombre.toString());
	     parametros.put("infracDomicilio", infracDomicilio);
	     parametros.put("infracLicencia", ib.getInfracLicencia());
	     parametros.put("licenciaTipo", ib.getTipoLId());
	     parametros.put("servicioPublico", ib.getInfracLServPublico());
	     parametros.put("licenciaExpedida", ib.getInfracLicEdo());
	     parametros.put("vehiculMarca", ib.getVmarId());
	     parametros.put("vehiculoModelo", ib.getVmodId());
	     parametros.put("vehiculoColor", ib.getVcolorId());
	     parametros.put("vehiculoPlacas", ib.getInfracPlaca());
	     parametros.put("placaExpedida", ib.getInfracPlacaEdo());
	     parametros.put("infracMotivacion", ib.getArtMotivacion());
	     parametros.put("infracEnLaCalle", ib.getInfracMEnLaCalle());
	     parametros.put("infracEntreLaCalle", ib.getInfracMEntreCalle());
	     parametros.put("infracYLaCalle", ib.getInfracMYLaCalle());
	     parametros.put("infracMDelegacion", ib.getInfracMDelId());
	     parametros.put("infracMColonia", ib.getInfracMColonia());
	     parametros.put("artNum", ib.getArtId());
	     parametros.put("artFraccion", ib.getArtFraccionNuevo());
	     parametros.put("artParrafo", ib.getArtParrafoNuevo());
	     parametros.put("artInciso", ib.getArtIncisoNuevo());
	     parametros.put("sancionNum", ib.getSancionArtId());
	     parametros.put("sancionDias",sancionDiasMensaje +ib.getArtDias());
	     parametros.put("polSecAgrp", ib.getEmpAgrup());
	     parametros.put("polPlaca", ib.getEmpPlaca());
	     parametros.put("polNombre", ib.getEmpId());
	     parametros.put("resVehRecibio", resVehRecibio);
	     parametros.put("resVehAusente", resVehAusente);
	     parametros.put("resVehNegoARecibir", resVehNegoARecibir);
	     parametros.put("infracNumCtrl", ib.getInfracNumCtrl());	     
	     parametros.put("infracdia",infracDia);
	     parametros.put("infracmes",infracMes);
	     parametros.put("infracaao",infracAao);
	     parametros.put("infrachora",infracHora);

		return parametros;
	}
	
	
	public List<InfraccionImpVO> getInfraccionVO (List<InfraccionConsultaVO> consultaVO){
		

		List<InfraccionImpVO> listaInfraccionVO = new ArrayList<InfraccionImpVO>();
		
		 for (InfraccionConsultaVO infraccionConsultaVO : consultaVO) {

		 InfraccionImpVO infraccionVO = new InfraccionImpVO();	 
		 infraccionVO.setInfracMFechaHora(infraccionConsultaVO.getFecha_infraccion() == null ? "" : infraccionConsultaVO.getFecha_infraccion());
	     infraccionVO.setInfracNum(infraccionConsultaVO.getInfraccion()== null ? "" :infraccionConsultaVO.getInfraccion());
	     infraccionVO.setUtId(infraccionConsultaVO.getUnidad_territorial()== null ? "" :infraccionConsultaVO.getUnidad_territorial());
	     infraccionVO.setInfracPlacaEdo(infraccionConsultaVO.getPlaca_expedida_en()== null ? "" :infraccionConsultaVO.getPlaca_expedida_en());
	     infraccionVO.setInfracConPlaca(infraccionConsultaVO.getCon_placa()== null ? "" :infraccionConsultaVO.getCon_placa());
	     infraccionVO.setInfracICalle(infraccionConsultaVO.getCalle()== null ? "" :infraccionConsultaVO.getCalle());
	     infraccionVO.setInfracIColonia(infraccionConsultaVO.getColonia()== null ? "" :infraccionConsultaVO.getColonia());
	     infraccionVO.setInfracIDelId(infraccionConsultaVO.getDelegacion()== null ? "" :infraccionConsultaVO.getDelegacion());
	     infraccionVO.setInfracIEdoId(infraccionConsultaVO.getEdo_infractor() == null ? "" :infraccionConsultaVO.getEdo_infractor());
	     infraccionVO.setInfracIMaterno(infraccionConsultaVO.getApe_materno()== null ? "" :infraccionConsultaVO.getApe_materno());
	     infraccionVO.setInfracINombre(infraccionConsultaVO.getNombre()== null ? "" :infraccionConsultaVO.getNombre());
	     infraccionVO.setInfracINumExt(infraccionConsultaVO.getNum_exterior() == null ? "" :infraccionConsultaVO.getNum_exterior());
	     infraccionVO.setInfracINumInt(infraccionConsultaVO.getNum_interior()== null ? "" :infraccionConsultaVO.getNum_interior());
	     infraccionVO.setInfracIPaterno(infraccionConsultaVO.getApe_paterno()== null ? "" :infraccionConsultaVO.getApe_paterno());
	     infraccionVO.setInfracLicencia(infraccionConsultaVO.getLicencia()== null ? "" :infraccionConsultaVO.getLicencia());
	     infraccionVO.setInfracImpresa(infraccionConsultaVO.getInfraccion_impresa()== null ? "" :infraccionConsultaVO.getInfraccion_impresa());
	     infraccionVO.setInfracLicEdo(infraccionConsultaVO.getLic_expedida_en()== null ? "" :infraccionConsultaVO.getLic_expedida_en());
	     infraccionVO.setSecId(infraccionConsultaVO.getSector()== null ? "" :infraccionConsultaVO.getSector());
	     infraccionVO.setTipoLId(infraccionConsultaVO.getTipo_licencia()== null ? "" :infraccionConsultaVO.getTipo_licencia());
	     infraccionVO.setVmarId(infraccionConsultaVO.getMarca()== null ? "" :infraccionConsultaVO.getMarca());
	     infraccionVO.setVmodId(infraccionConsultaVO.getModelo()== null ? "" :infraccionConsultaVO.getModelo());
	     infraccionVO.setVcolorId(infraccionConsultaVO.getColor()== null ? "" :infraccionConsultaVO.getColor());
	     infraccionVO.setVtipoId(infraccionConsultaVO.getVehiculo_tipo()== null ? "" :infraccionConsultaVO.getVehiculo_tipo());
	     infraccionVO.setvOrigen(infraccionConsultaVO.getOrigen() == null ? "" :infraccionConsultaVO.getOrigen());
	     infraccionVO.setInfracLServPublico(infraccionConsultaVO.getServicio_publico()== null ? "" :infraccionConsultaVO.getServicio_publico());
	     infraccionVO.setVtarjetaCirculacion(infraccionConsultaVO.getTarjeta_circulacion() == null ? "" :infraccionConsultaVO.getTarjeta_circulacion());
	     infraccionVO.setInfracNumCtrl(infraccionConsultaVO.getNci()== null ? "" :infraccionConsultaVO.getNci());
	     infraccionVO.setInfracMEnLaCalle(infraccionConsultaVO.getEn_la_calle()== null ? "" : infraccionConsultaVO.getEn_la_calle());
	     infraccionVO.setInfracMEntreCalle(infraccionConsultaVO.getEntre_la_calle()== null ? "" :infraccionConsultaVO.getEntre_la_calle());
	     infraccionVO.setInfracMYLaCalle(infraccionConsultaVO.getY_la_calle()== null ? "" :infraccionConsultaVO.getY_la_calle());
	     infraccionVO.setInfracMColonia(infraccionConsultaVO.getColonia_infraccion()== null ? "" :infraccionConsultaVO.getColonia_infraccion());
	     infraccionVO.setInfracMDelId(infraccionConsultaVO.getDelegacion_infraccion()== null ? "" :infraccionConsultaVO.getDelegacion_infraccion());
	     infraccionVO.setInfracPlaca(infraccionConsultaVO.getPlaca() == null ? "" :infraccionConsultaVO.getPlaca());
	     infraccionVO.setSancionArtId(infraccionConsultaVO.getArt_sancion()== null ? "" :infraccionConsultaVO.getArt_sancion());
	     infraccionVO.setEmpId(infraccionConsultaVO.getEmpleado()== null ? "" :infraccionConsultaVO.getEmpleado());
	     infraccionVO.setGruaId(infraccionConsultaVO.getNumero_grua()== null ? "" :infraccionConsultaVO.getNumero_grua());
	     infraccionVO.setDepId(infraccionConsultaVO.getDeposito()== null ? "" :infraccionConsultaVO.getDeposito());
	     infraccionVO.setInfracNumArrastre(infraccionConsultaVO.getNumero_arrastre()== null ? "" : infraccionConsultaVO.getNumero_arrastre());
	     infraccionVO.setInfracArrastre(infraccionConsultaVO.getArrastre()== null ? "" :infraccionConsultaVO.getArrastre());
	     infraccionVO.setInfracTipoArrastre(infraccionConsultaVO.getTipo_arrastre()== null ? "" :infraccionConsultaVO.getTipo_arrastre());
	     infraccionVO.setrVehId(infraccionConsultaVO.getEmp_responsable()== null ? "" :infraccionConsultaVO.getEmp_responsable());
	     infraccionVO.setArtId(infraccionConsultaVO.getArticulo()== null ? "" :infraccionConsultaVO.getArticulo());
	     infraccionVO.setArtFraccionNuevo(infraccionConsultaVO.getFraccion()== null ? "" :infraccionConsultaVO.getFraccion());
	     infraccionVO.setArtParrafoNuevo(infraccionConsultaVO.getParrafo()== null ? "" :infraccionConsultaVO.getParrafo());
	     infraccionVO.setArtIncisoNuevo(infraccionConsultaVO.getInciso()== null ? "" :infraccionConsultaVO.getInciso());
	     infraccionVO.setArtDias(infraccionConsultaVO.getDias_sal_min()== null ? "" :infraccionConsultaVO.getDias_sal_min());
	     infraccionVO.setInfracLeyTransporte(infraccionConsultaVO.getLey_transporte()== null ? "" :infraccionConsultaVO.getLey_transporte());
	     infraccionVO.setArtMotivacion(infraccionConsultaVO.getMotivacion()== null ? "" :infraccionConsultaVO.getMotivacion());
	     infraccionVO.setEmpAgrup(infraccionConsultaVO.getEmp_agrupamiento()== null ? "" :infraccionConsultaVO.getEmp_agrupamiento());
	     infraccionVO.setEmpPlaca(infraccionConsultaVO.getEmp_placa()== null ? "" :infraccionConsultaVO.getEmp_placa());		 
	     listaInfraccionVO.add(infraccionVO);
	     
		 }
		
		 return listaInfraccionVO;
	}
	
	
	
	
	
}
