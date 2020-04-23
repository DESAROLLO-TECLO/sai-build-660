package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class DatosPoliciaVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// DATOS POLICIA
		// - ID EMPLEADO (EMP_ID)
		// - NOMBRE COMPLETO (EMP_APE_PATERNO + EMP_APE_MATERNO + EMP_NOMBRE)
		// - ID AGRUPAMIENTO (AGRP_ID)
		// - ID SECTOR (SEC_ID)
		// - PLACA EMPLEADO (EMP_PLACA)
	private Long	idEmpleado;
	private String 	nombreCompleto;
	private Long 	idAgrupamiento;
	private Long	idSector;
	private String 	placaEmpleado;
	private String	nombreAgrupamiento;
	private String	nombreSector;

	public Long getIdEmpleado() 
	{
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) 
	{
		this.idEmpleado = idEmpleado;
	}

	public String getNombreCompleto() 
	{
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) 
	{
		this.nombreCompleto = nombreCompleto;
	}

	public Long getIdSector() 
	{
		return idSector;
	}

	public void setIdSector(Long idSector) 
	{
		this.idSector = idSector;
	}

	public String getPlacaEmpleado() 
	{
		return placaEmpleado;
	}

	public void setPlacaEmpleado(String placaEmpleado) 
	{
		this.placaEmpleado = placaEmpleado;
	}

	public Long getIdAgrupamiento() 
	{
		return idAgrupamiento;
	}
	
	public void setIdAgrupamiento(Long idAgrupamiento) 
	{
		this.idAgrupamiento = idAgrupamiento;
	}
	
	public String getNombreAgrupamiento() 
	{
		return nombreAgrupamiento;
	}
	
	public void setNombreAgrupamiento(String nombreAgrupamiento) 
	{
		this.nombreAgrupamiento = nombreAgrupamiento;
	}
	
	public String getNombreSector() 
	{
		return nombreSector;
	}
	
	public void setNombreSector(String nombreSector) 
	{
		this.nombreSector = nombreSector;
	}
	
	@Override
	public String toString()
	{
		StringBuilder datosPolicia = new StringBuilder("");
		
		datosPolicia.append("\n\t idEmpleado: "		+ idEmpleado);
		datosPolicia.append("\n\t nombreCompleto: "	+ nombreCompleto);
		datosPolicia.append("\n\t idAgrupamiento: "	+ idAgrupamiento);
		datosPolicia.append("\n\t idSector: " 		+ idSector);
		datosPolicia.append("\n\t placaEmpleado: " 	+ placaEmpleado);
		datosPolicia.append("\n\t nombreAgrupamiento: " + nombreAgrupamiento);
		datosPolicia.append("\n\t nombreSector: " 	+ nombreSector);
		
		return datosPolicia.toString();
	}	
	
}
