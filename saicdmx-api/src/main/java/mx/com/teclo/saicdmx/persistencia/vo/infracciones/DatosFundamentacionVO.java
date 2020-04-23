package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class DatosFundamentacionVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// DATOS FUNDAMENTACION / MOTIVACION / SANCION
		// - ID ARTICULO INFRIGIDO (ART_ID)
		// - FRACCION PARRAFO INCISO (INFO ASOCIADA AL IDENTIFICADOR DEL ARTICULO)
		// - INFRACCION LEY DE TRANSPORTE (INFRAC_LEY_TRANSPORTE)	
		// - MOTIVACION (ART_MOTIVACION)
		// - OBSERVACION (INFRAC_OBSERVACION)
		// - MULTA EN DIAS 
	private Long 	idArticuloInfrigido;
	private String 	infraccionLeyTransporte;
	private String 	motivacion;
	private String 	observaciones;
	
	public Long getIdArticuloInfrigido() 
	{
		return idArticuloInfrigido;
	}
	
	public void setIdArticuloInfrigido(Long idArticuloInfrigido) 
	{
		this.idArticuloInfrigido = idArticuloInfrigido;
	}
	
	public String getInfraccionLeyTransporte() 
	{
		return infraccionLeyTransporte;
	}
	
	public void setInfraccionLeyTransporte(String infraccionLeyTransporte) 
	{
		this.infraccionLeyTransporte = infraccionLeyTransporte;
	}
	
	public String getMotivacion() 
	{
		return motivacion;
	}
	
	public void setMotivacion(String motivacion) 
	{
		this.motivacion = motivacion;
	}
	
	public String getObservaciones() 
	{
		return observaciones;
	}
	
	public void setObservaciones(String observaciones) 
	{
		this.observaciones = observaciones;
	}
	
	@Override
	public String toString()
	{
		StringBuilder datosFundamentacion = new StringBuilder("");
		
		datosFundamentacion.append("\n\t idArticuloInfrigido: "		+ idArticuloInfrigido);
		datosFundamentacion.append("\n\t infraccionLeyTransporte: "	+ infraccionLeyTransporte);
		datosFundamentacion.append("\n\t motivacion: " 				+ motivacion);
		datosFundamentacion.append("\n\t observaciones: " 			+ observaciones);
		
		return datosFundamentacion.toString();
	}	
}
