package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class DatosInfractorVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// DATOS DEL INFRACTOR
		// - APELLIDO PATERNO (INFRAC_I_PATERNO)
		// - APELLIDO MATERNO (INFRAC_I_MATERNO)
		// - NOMBRE (INFRAC_I_NOMBRE)
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombre;	
	// DIRECCION direccionVO
		// - CALLE (INFRAC_I_CALLE)
		// - COLONIA(INFRAC_I_COLONIA)
		// - NUMERO EXTERIOR (INFRAC_I_NUM_EXT)
		// - NUMERO INTERIOR (INFRAC_I_NUM_INT)
		// - ID ESTADO (INFRAC_I_EDO_ID)
		// - ID MUNICIPIO (INFRAC_I_DEL_ID)
	private DireccionVO direccionVO = new DireccionVO();
	// LICENCIA / PERMISO licenciaPermisoVO
		// - NUMERO LICENCIA (INFRAC_LICENCIA)
		// - RFC (INFRAC_I_RFC)
		// - TIPO DE LICENCIA (TIPO_L_ID)
		// - ID ESTADO DE EXPEDICION (INFRAC_LIC_EDO)
	private LicenciaPermisoVO licenciaPermisoVO = new LicenciaPermisoVO();
	
	public String getApellidoPaterno() 
	{
		return apellidoPaterno;
	}
	
	public void setApellidoPaterno(String apellidoPaterno) 
	{
		this.apellidoPaterno = apellidoPaterno;
	}
	
	public String getApellidoMaterno() 
	{
		return apellidoMaterno;
	}
	
	public void setApellidoMaterno(String apellidoMaterno) 
	{
		this.apellidoMaterno = apellidoMaterno;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public DireccionVO getDireccionVO() 
	{
		return direccionVO;
	}
	
	public void setDireccionVO(DireccionVO direccionVO) 
	{
		this.direccionVO = direccionVO;
	}
	
	public LicenciaPermisoVO getLicenciaPermisoVO() 
	{
		return licenciaPermisoVO;
	}
	
	public void setLicenciaPermisoVO(LicenciaPermisoVO licenciaPermisoVO) 
	{
		this.licenciaPermisoVO = licenciaPermisoVO;
	}
	
	@Override
	public String toString()
	{
		StringBuilder datosInfractor = new StringBuilder("");
		
		datosInfractor.append("\n\t apellidoPaterno: " 	+ apellidoPaterno);
		datosInfractor.append("\n\t apellidoMaterno: "	+ apellidoMaterno);
		datosInfractor.append("\n\t nombre: " 			+ nombre);
		datosInfractor.append("\n\t direccionVO: " 		+ direccionVO.toString());
		datosInfractor.append("\n\t licenciaPermisoVO: " + licenciaPermisoVO.toString());
				
		return datosInfractor.toString();
	}
	
}
