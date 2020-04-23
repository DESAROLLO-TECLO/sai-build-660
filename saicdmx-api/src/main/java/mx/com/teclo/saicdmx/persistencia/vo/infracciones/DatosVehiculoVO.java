package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class DatosVehiculoVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// DATOS DEL VEHICULO
		// - TIPO DE VEHICULO (VTIPO_ID)
		// - ORIGEN (VORIGEN)
		// - MARCA (VMAR_ID)
		// - MODELO (VMOD_ID)
		// - COLOR (VCOLOR_ID)
		// - SERVICIO PUBLICO (INFRAC_L_SERV_PUBLICO)
	    // - VEHICULO TIENE PLACA 
	    // - VEHICULO  PLACA 
	    // - VEHICULO  NO SERIE 
	    // - VEHICULO  INVENTARIO
	private Long 	idTipoVehiculo;
	private String 	origenVehiculo;
	private Long	idMarca;
	private	Long	idModelo;
	private Long	idColor;
	private String	servicioPublico;
	private String vehiculoTienePlaca;
	private String vehiculoPlaca;
	private String vehiculoNumSerie;
	private String[] inventarioDelVehiculo;
	private String codigoVehiculoTipo;
	
	public Long getIdTipoVehiculo() 
	{
		return idTipoVehiculo;
	}
	
	public void setIdTipoVehiculo(Long idTipoVehiculo) 
	{
		this.idTipoVehiculo = idTipoVehiculo;
	}
	
	public String getOrigenVehiculo() 
	{
		return origenVehiculo;
	}
	
	public void setOrigenVehiculo(String origenVehiculo) 
	{
		this.origenVehiculo = origenVehiculo;
	}
	
	public Long getIdMarca() 
	{
		return idMarca;
	}
	
	public void setIdMarca(Long idMarca) 
	{
		this.idMarca = idMarca;
	}
	
	public Long getIdModelo() 
	{
		return idModelo;
	}
	
	public void setIdModelo(Long idModelo) 
	{
		this.idModelo = idModelo;
	}
	
	public Long getIdColor() 
	{
		return idColor;
	}
	
	public void setIdColor(Long idColor) 
	{
		this.idColor = idColor;
	}
	
	public String getServicioPublico() 
	{
		return servicioPublico;
	}
	
	public void setServicioPublico(String servicioPublico) 
	{
		this.servicioPublico = servicioPublico;
	}

	public String getVehiculoTienePlaca() {
		return vehiculoTienePlaca;
	}

	public void setVehiculoTienePlaca(String vehiculoTienePlaca) {
		this.vehiculoTienePlaca = vehiculoTienePlaca;
	}

	public String getVehiculoPlaca() {
		return vehiculoPlaca;
	}

	public void setVehiculoPlaca(String vehiculoPlaca) {
		this.vehiculoPlaca = vehiculoPlaca;
	}

	public String getVehiculoNumSerie() {
		return vehiculoNumSerie;
	}

	public void setVehiculoNumSerie(String vehiculoNumSerie) {
		this.vehiculoNumSerie = vehiculoNumSerie;
	}

	public String[] getInventarioDelVehiculo() {
		return inventarioDelVehiculo;
	}

	public void setInventarioDelVehiculo(String[] inventarioDelVehiculo) {
		this.inventarioDelVehiculo = inventarioDelVehiculo;
	}

	public String getCodigoVehiculoTipo() {
		return codigoVehiculoTipo;
	}

	public void setCodigoVehiculoTipo(String codigoVehiculoTipo) {
		this.codigoVehiculoTipo = codigoVehiculoTipo;
	}

	@Override
	public String toString()
	{
		StringBuilder datosVehiculo = new StringBuilder("");
		
		datosVehiculo.append("\n\t idTipoVehiculo: "+ idTipoVehiculo);
		datosVehiculo.append("\n\t origenVehiculo: "+ origenVehiculo);
		datosVehiculo.append("\n\t idMarca: " 		+ idMarca);
		datosVehiculo.append("\n\t idModelo: " 		+ idModelo);
		datosVehiculo.append("\n\t idColor: " 		+ idColor);
		datosVehiculo.append("\n\t servicioPublico: "+ servicioPublico);
		datosVehiculo.append("\n\t vehiculoTienePlaca: "+ vehiculoTienePlaca);
		datosVehiculo.append("\n\t vehiculoPlaca: "+ vehiculoPlaca);
		datosVehiculo.append("\n\t vehiculoNumSerie: "+ vehiculoNumSerie);
		datosVehiculo.append("\n\t inventarioDelVehiculo: "+ inventarioDelVehiculo);
		datosVehiculo.append("\n\t codigoVehiculoTipo: "+ codigoVehiculoTipo);
		
		return datosVehiculo.toString();
	}
	
}
