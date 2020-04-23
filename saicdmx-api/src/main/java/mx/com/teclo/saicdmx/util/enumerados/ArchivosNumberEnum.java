package mx.com.teclo.saicdmx.util.enumerados;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author UnitisDes0416
 *
 */
public enum ArchivosNumberEnum {

	PROCESO_COMPLETO(1),
	PROCESO_FORANEO(2),
	RADAR_SSP(1), 
	CARRIL_CONFINADO(2), 
	FOTOMULTA(3), 
	RADAR_CONCESIONADO(4),
	RADAR_SSP_FORANEO(5),
	FOTOMULTA_FORANEO(6),
	CARRIL_CONFINADO_FORANEO(7), 
	RADAR_CONCESIONADO_FORANEO(8),
	EMPLEADO_ID_DEFAULT(-2),
	REGISTRO_ACTIVO(1),
	REGISTRO_INACTIVO(0),
	REGISTRO_DUPLICADO(1),
	REGISTRO_NODUPLICADO(0),
	//REG_METROPOLITANO_ARTICULO_ID(1204),
	REG_METROPOLITANO_ARTICULO_ID(1493),
	REG_DISTRITO_FEDERAL_ARTICULO_ID(1493),
	ARCHIVO_LISTO_PARA_COMPLEMENTAR(1),
	EN_PROCESO(1),
	DETECCIONES_CANCELADAS(0),
	ACTIVO(1),
	INACTIVO(0),
	FM_RADAR_VELOCIDAD_Jenoptik(1),
	FM_RADAR_VELOCIDAD_RedFlex(2),
	FM_CARRIL_CONFINADO_Jenoptik(3),
	FM_FOTOMULTA_Jenoptik(4),
	FM_FOTOMULTA_Bosch(5),
	FM_FOTOMULTA_Laser(6),
	FM_RADAR_VELOCIDAD_CONSESIONADO_Redflex(7),	
	FM_ORIGEN_CDM(0),
	FM_ORIGEN_FORANEO(1),
	DATO_OBLIGATORIO(1),
	DATO_REQUERIDOO(2),
	DATO_OPCIONAL(3),
	ESTATUS_PROCESO_CANCELADO(33);
	
	
	private Integer constante;
	private static Map<Integer, ArchivosNumberEnum> dictionary;
	
	static {
		  dictionary = new HashMap<Integer, ArchivosNumberEnum>();
		  for(ArchivosNumberEnum state : values()) {
		      dictionary.put(state.constante, state);
		  }
		}
	
	ArchivosNumberEnum(Integer constante){
		this.constante = constante;
	}
	
	/**
	 * @return the constante
	 */
	public Integer getConstante() {
		return constante;
	}
	
	/**
	 * @return the constante
	 */
	public Long getLongConstante() {
		return Long.parseLong(constante.toString());
	}
	
	/**
	 * @param constante
	 *            the constante to set
	 */
	public void setLongConstante(Integer constante) {
		this.constante = constante;
	}
	
	public static ArchivosNumberEnum fromInteger(Integer fromValue) {
		ArchivosNumberEnum var = dictionary.get(fromValue);
		  if(var == null) {
		      return null;
		  }
		  return var;
		}
}
