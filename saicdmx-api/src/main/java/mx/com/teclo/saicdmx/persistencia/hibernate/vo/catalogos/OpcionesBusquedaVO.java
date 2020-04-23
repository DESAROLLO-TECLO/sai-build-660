package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class OpcionesBusquedaVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombreOpcion;
	private String valorOpcion;
	
	public String getNombreOpcion() 
	{
		return nombreOpcion;
	}
	
	public void setNombreOpcion(String nombreOpcion) 
	{
		this.nombreOpcion = nombreOpcion;
	}
	
	public String getValorOpcion() 
	{
		return valorOpcion;
	}
	
	public void setValorOpcion(String valorOpcion) 
	{
		this.valorOpcion = valorOpcion;
	}

	@Override
	public String toString() 
	{
		return "OpcionesBusquedaVO [nombreOpcion= " + nombreOpcion + ", valorOpcion= " + valorOpcion + "]";
	}	
}
