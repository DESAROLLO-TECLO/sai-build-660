package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class DatosGeneralesVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// DATOS GENERALES
	// - NUMERO CONTROL INTERNO (INFRAC_NUM_CTRL)
	// - INFRACCION ---> (INFRAC_NUM) ----> (INFRAC_IMPRESA)
	// - INFRACCION IMPRESA (INFRAC_IMPRESA)
	// - ESTADO ---> Default DF
	// - CUENTA CON PLACA (INFRAC_CON_PLACA)
	// - SECTOR (SEC_ID)
	// - UNIDAD TERRITORIAL (UT_ID)
	private String 	numeroControlInterno;
	private String 	numeroInfraccion;
	private String 	numeroInfraccionImpresa;
	private Integer	idEstado;
	private String	cuentaConPlaca;
	private Long	idSector;
	private Long	idUnidadTerritorial;
	// OTROS DATOS 
	// - TIPO INGRESO
	// - MEDIO DE TRANSPORTE 
	// - NUMERO ECONOMICO (GRUA_ID)
	// - RESPONSABLE VEHICULO (R_VEH_ID)
	// - ARRASTRE (INFRAC_TIPO_ARRASTRE)
	// - PLACA EMPLEADO (EMP_PLACA)
	private Long	idTipoIngreso;
	private String 	numeroEconomico;
	private Long 	idResponsableVehiculo;
	private String 	tipoArrastre;
	private String 	placaEmpleado;
	//private Long 	baseFolioInfraccion;
	private String	tipoInfraccion;
	
	public String getTipoInfraccion() 
	{
		return tipoInfraccion;
	}

	public void setTipoInfraccion(String tipoInfraccion) 
	{
		this.tipoInfraccion = tipoInfraccion;
	}
	
//	public void setBaseFolioInfraccion(Long baseFolioInfraccion) 
//	{
//		this.baseFolioInfraccion = baseFolioInfraccion;
//	}
//	
//	public Long getBaseFolioInfraccion() 
//	{
//		return baseFolioInfraccion;
//	}
	
	public String getNumeroControlInterno() 
	{
		return numeroControlInterno;
	}

	public void setNumeroControlInterno(String numeroControlInterno) 
	{
		this.numeroControlInterno = numeroControlInterno;
	}
	
	public String getNumeroInfraccion() 
	{
		return numeroInfraccion;
	}
	
	public void setNumeroInfraccion(String numeroInfraccion) 
	{
		this.numeroInfraccion = numeroInfraccion;
	}
	
	public String getNumeroInfraccionImpresa() 
	{
		return numeroInfraccionImpresa;
	}
	
	public void setNumeroInfraccionImpresa(String numeroInfraccionImpresa) 
	{
		this.numeroInfraccionImpresa = numeroInfraccionImpresa;
	}
	
	public Integer getIdEstado() 
	{
		return idEstado;
	}
	
	public void setIdEstado(Integer idEstado) 
	{
		this.idEstado = idEstado;
	}

	public String getCuentaConPlaca() 
	{
		return cuentaConPlaca;
	}

	public void setCuentaConPlaca(String cuentaConPlaca) 
	{
		this.cuentaConPlaca = cuentaConPlaca;
	}

	public Long getIdSector() 
	{
		return idSector;
	}

	public void setIdSector(Long idSector) 
	{
		this.idSector = idSector;
	}

	public Long getIdUnidadTerritorial() 
	{
		return idUnidadTerritorial;
	}

	public void setIdUnidadTerritorial(Long idUnidadTerritorial) 
	{
		this.idUnidadTerritorial = idUnidadTerritorial;
	}	
	
	public String getNumeroEconomico() 
	{
		return numeroEconomico;
	}

	public void setNumeroEconomico(String numeroEconomico) 
	{
		this.numeroEconomico = numeroEconomico;
	}

	public Long getIdResponsableVehiculo() 
	{
		return idResponsableVehiculo;
	}

	public void setIdResponsableVehiculo(Long idResponsableVehiculo) 
	{
		this.idResponsableVehiculo = idResponsableVehiculo;
	}

	public String getTipoArrastre() 
	{
		return tipoArrastre;
	}

	public void setTipoArrastre(String tipoArrastre) 
	{
		this.tipoArrastre = tipoArrastre;
	}

	public String getPlacaEmpleado() 
	{
		return placaEmpleado;
	}

	public void setPlacaEmpleado(String placaEmpleado) 
	{
		this.placaEmpleado = placaEmpleado;
	}
	
	public Long getIdTipoIngreso() 
	{
		return idTipoIngreso;
	}
	
	public void setIdTipoIngreso(Long idTipoIngreso) 
	{
		this.idTipoIngreso = idTipoIngreso;
	}

	@Override
	public String toString()
	{
		StringBuilder datosGenerales = new StringBuilder("");
		
		datosGenerales.append("\n\t numeroInfraccion: "			+ numeroInfraccion);
		datosGenerales.append("\n\t numeroInfraccionImpresa: "	+ numeroInfraccionImpresa);
		datosGenerales.append("\n\t idEstado: " 				+ idEstado);
		datosGenerales.append("\n\t cuentaConPlaca: " 			+ cuentaConPlaca);
		datosGenerales.append("\n\t idSector: " 				+ idSector);
		datosGenerales.append("\n\t idUnidadTerritorial: "		+ idUnidadTerritorial);
		
		datosGenerales.append("\n\t idTipoIngreso: " 		+ idTipoIngreso);
		datosGenerales.append("\n\t numeroEconomico: " 		+ numeroEconomico);
		datosGenerales.append("\n\t idResponsableVehiculo: "+ idResponsableVehiculo);
		datosGenerales.append("\n\t tipoArrastre: " 		+ tipoArrastre);
		datosGenerales.append("\n\t placaEmpleado: "		+ placaEmpleado);
		
		datosGenerales.append("\n\t tipoInfraccion: "		+ tipoInfraccion);
		
		return datosGenerales.toString();
	}	

}
