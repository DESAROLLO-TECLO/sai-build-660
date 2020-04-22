package mx.com.teclo.saicdmx.util.comun;

/**
 * Interface para constantes 
 * @author uniti_000
 *
 */
public interface IConstantesSIIDF {
	
		
	/***************************************************************
	 * TIPOS DE BUSQUEDA PARA INGRESO A DEPOSITO CON INFRACCION
	 ***************************************************************/
	public static final String PLACA = "PLACA";
	public static final String INFRACCION = "INFRACCION";
	public static final String PREIMPRESA = "PREIMPRESA";
	public static final String NCI = "NCI";
	
	/***********************************
	  * TIPOS DE INGRESO (tieneIngreso)	 
	***********************************/		
	public static final String INFRACCION_SIN_INGRESO = "NI";
	public static final String INFRACCION_CON_INGRESO = "CI";
	public static final String INFRACCION_CON_INGRESO_PLACA = "CIP";
			
	/***********************************
	  * BITACORA CONCEPTOS
	***********************************/
    public static final Long BITACORA_COMPONENTES_INFRACCION = new Long(5);    
    
    public static final Long BITACORA_CONCEPTOS_ALTA_INFRACCION = new Long(1);
    public static final Long BITACORA_CONCEPTOS_NUEVO_MARCADO_INFRACCION = new Long(16);    
    public static final Long BITACORA_CONCEPTOS_ALTA_INGRESO_SIN_INFRACCION = new Long(1);    
    public static final Long BITACORA_CONCEPTOS_LIBERA_INFRACCIONES_PREIMPRESAS = new Long(48);
    public static final Long BITACORA_CONCEPTO_INFRACCION = new Long(18);

}
