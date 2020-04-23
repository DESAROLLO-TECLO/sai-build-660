package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class DireccionVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// DIRECCION
		// - CALLE (INFRAC_I_CALLE)
		// - COLONIA(INFRAC_I_COLONIA)
		// - NUMERO EXTERIOR (INFRAC_I_NUM_EXT)
		// - NUMERO INTERIOR (INFRAC_I_NUM_INT)
		// - ID ESTADO (INFRAC_I_EDO_ID)
		// - ID MUNICIPIO (INFRAC_I_DEL_ID)
	private String 	calle;
	private String 	colonia;
	private String 	numeroExterior;
	private String 	numeroInterior;
	private Integer idEstado;
	private Long 	idMunicipio;
	
	public String getCalle() 
	{
		return calle;
	}
	public void setCalle(String calle) 
	{
		this.calle = calle;
	}
	
	public String getColonia() 
	{
		return colonia;
	}
	
	public void setColonia(String colonia) 
	{
		this.colonia = colonia;
	}
	
	public String getNumeroExterior() 
	{
		return numeroExterior;
	}
	
	public void setNumeroExterior(String numeroExterior) 
	{
		this.numeroExterior = numeroExterior;
	}
	
	public String getNumeroInterior() 
	{
		return numeroInterior;
	}
	
	public void setNumeroInterior(String numeroInterior) 
	{
		this.numeroInterior = numeroInterior;
	}
	
	public Integer getIdEstado() 
	{
		return idEstado;
	}
	
	public void setIdEstado(Integer idEstado) 
	{
		this.idEstado = idEstado;
	}
	
	public Long getIdMunicipio() 
	{
		return idMunicipio;
	}
	
	public void setIdMunicipio(Long idMunicipio) 
	{
		this.idMunicipio = idMunicipio;
	}
	
	@Override
	public String toString()
	{
		StringBuilder direccion = new StringBuilder("");
		
		direccion.append("\n\t calle: " 		+ calle);
		direccion.append("\n\t colonia: "		+ colonia);
		direccion.append("\n\t numeroExterior: " + numeroExterior);
		direccion.append("\n\t numeroInterior: " + numeroInterior);
		direccion.append("\n\t idEstado: " 		+ idEstado);
		direccion.append("\n\t idMunicipio: " 	+ idMunicipio);
		
		return direccion.toString();
	}
}
