package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;
import java.util.Date;

public class DatosUbicacionAutomovilVO implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// DATOS UBICACION AUTOMOVIL
		// - CALLE PRINCIPAL (INFRAC_M_EN_LA_CALLE)
		// - CALLE REFERENCIA (INFRAC_M_ENTRE_CALLE)
		// - SEGUNDA CALLE DE REFERENCIA (INFRAC_M_Y_LA_CALLE)
		// - COLONIA (INFRAC_M_COLONIA)
		// - ID DEL ESTADO (INFRAC_M_EDO_ID)
		// - ID DE LA DELEGACION (INFRAC_M_DEL_ID)
		// - FECHA Y HORA (INFRAC_M_FECHA_HORA)
	private String 	enLaCalle;
	private String 	entreLaCalle;
	private String 	yEnLaCalle;
	private String 	colonia;
	private Integer	idEstado;
	private Long 	idDelegacion;
	private Date	fechaHoraInfraccion;
	
	public String getEnLaCalle() 
	{
		return enLaCalle;
	}
	
	public void setEnLaCalle(String enLaCalle) 
	{
		this.enLaCalle = enLaCalle;
	}
	
	public String getEntreLaCalle() 
	{
		return entreLaCalle;
	}
	
	public void setEntreLaCalle(String entreLaCalle) 
	{
		this.entreLaCalle = entreLaCalle;
	}
	
	public String getyEnLaCalle() 
	{
		return yEnLaCalle;
	}
	
	public void setyEnLaCalle(String yEnLaCalle) 
	{
		this.yEnLaCalle = yEnLaCalle;
	}
	
	public String getColonia() 
	{
		return colonia;
	}
	
	public void setColonia(String colonia) 
	{
		this.colonia = colonia;
	}
	
	public Integer getIdEstado() 
	{
		return idEstado;
	}
	
	public void setIdEstado(Integer idEstado) 
	{
		this.idEstado = idEstado;
	}
	
	public Long getIdDelegacion() 
	{
		return idDelegacion;
	}
	
	public void setIdDelegacion(Long idDelegacion) 
	{
		this.idDelegacion = idDelegacion;
	}
	
	public Date getFechaHoraInfraccion() 
	{
		return fechaHoraInfraccion;
	}
	
	public void setFechaHoraInfraccion(Date fechaHoraInfraccion) 
	{
		this.fechaHoraInfraccion = fechaHoraInfraccion;
	}

	@Override
	public String toString()
	{
		StringBuilder licenciaPermiso = new StringBuilder("");
		
		licenciaPermiso.append("\n\t enLaCalle: " 		+ enLaCalle);
		licenciaPermiso.append("\n\t entreLaCalle: " 	+ entreLaCalle);
		licenciaPermiso.append("\n\t yEnLaCalle: " 		+ yEnLaCalle);
		licenciaPermiso.append("\n\t colonia: " 		+ colonia);
		licenciaPermiso.append("\n\t idEstado: " 		+ idEstado);
		licenciaPermiso.append("\n\t idDelegacion: " 	+ idDelegacion);
		licenciaPermiso.append("\n\t fechaHoraInfraccion: " + fechaHoraInfraccion);
		
		return licenciaPermiso.toString();
	}	
}
