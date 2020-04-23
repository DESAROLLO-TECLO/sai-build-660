package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class DatosPlacaVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// DATOS PLACA
	// - NUMERO PLACA (INFRAC_PLACA)
	// - ID ESTADO DE EXPEDICION (INFRAC_PLACA_EDO)
	// - NUMERO TARJETA CIRCULACION (VTARJETA_CIRCULACION)
	private String 	placaVehiculo;
	private Integer	idEstadoExpedicionPlaca;
	private String 	numeroTarjetaCirculacion;
	
	public String getPlacaVehiculo() 
	{
		return placaVehiculo;
	}

	public void setPlacaVehiculo(String placaVehiculo) 
	{
		this.placaVehiculo = placaVehiculo;
	}

	public Integer getIdEstadoExpedicionPlaca() 
	{
		return idEstadoExpedicionPlaca;
	}

	public void setIdEstadoExpedicionPlaca(Integer idEstadoExpedicionPlaca) 
	{
		this.idEstadoExpedicionPlaca = idEstadoExpedicionPlaca;
	}

	public String getNumeroTarjetaCirculacion() 
	{
		return numeroTarjetaCirculacion;
	}

	public void setNumeroTarjetaCirculacion(String numeroTarjetaCirculacion) 
	{
		this.numeroTarjetaCirculacion = numeroTarjetaCirculacion;
	}

	@Override
	public String toString()
	{
		StringBuilder datosVehiculo = new StringBuilder("");
		
		datosVehiculo.append("\n\t placaVehiculo: "				+ placaVehiculo);
		datosVehiculo.append("\n\t idEstadoExpedicionPlaca: "	+ idEstadoExpedicionPlaca);
		datosVehiculo.append("\n\t numeroTarjetaCirculacion: " 	+ numeroTarjetaCirculacion);
		
		return datosVehiculo.toString();
	}
}
