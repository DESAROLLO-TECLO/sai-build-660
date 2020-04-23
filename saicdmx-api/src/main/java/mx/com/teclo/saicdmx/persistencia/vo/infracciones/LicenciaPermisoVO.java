package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class LicenciaPermisoVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// LICENCIA / PERMISO
		// - NUMERO LICENCIA (INFRAC_LICENCIA)
		// - RFC (INFRAC_I_RFC)
		// - TIPO DE LICENCIA (TIPO_L_ID)
		// - ID ESTADO DE EXPEDICION (INFRAC_LIC_EDO)
	private String 	numeroLicenciaInfractor;
	private String 	rfc;
	private Long 	idTipoLicencia;
	private Integer	idEstadoExpedicion;
		
	public String getNumeroLicenciaInfractor() 
	{
		return numeroLicenciaInfractor;
	}
	
	public void setNumeroLicenciaInfractor(String numeroLicenciaInfractor) 
	{
		this.numeroLicenciaInfractor = numeroLicenciaInfractor;
	}
	
	public String getRfc() 
	{
		return rfc;
	}
	
	public void setRfc(String rfc) 
	{
		this.rfc = rfc;
	}
	
	public Long getIdTipoLicencia() 
	{
		return idTipoLicencia;
	}
	
	public void setIdTipoLicencia(Long idTipoLicencia) 
	{
		this.idTipoLicencia = idTipoLicencia;
	}
	
	public Integer getIdEstadoExpedicion() 
	{
		return idEstadoExpedicion;
	}
	
	public void setIdEstadoExpedicion(Integer idEstadoExpedicion) 
	{
		this.idEstadoExpedicion = idEstadoExpedicion;
	}
	
		
	@Override
	public String toString()
	{
		StringBuilder licenciaPermiso = new StringBuilder("");
		
		licenciaPermiso.append("\n\t numeroLicenciaInfractor: " + numeroLicenciaInfractor);
		licenciaPermiso.append("\n\t rfc: " + rfc);
		licenciaPermiso.append("\n\t idTipoLicencia: " + idTipoLicencia);
		licenciaPermiso.append("\n\t idEstadoExpedicion: " + idEstadoExpedicion);
				
		return licenciaPermiso.toString();
	}

}
