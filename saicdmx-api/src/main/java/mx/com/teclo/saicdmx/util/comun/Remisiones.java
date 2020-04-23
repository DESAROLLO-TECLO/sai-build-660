package mx.com.teclo.saicdmx.util.comun;

public interface Remisiones {

	/***************************************************************
	 * TIPOS DE ESTATUS
	 ***************************************************************/
	public static final String ESTATUS_ACTIVO = "A";

	/***************************************************************
	 * TIPOS DE BUSQUEDA PARA INGRESO A DEPOSITO CON INFRACCION
	 ***************************************************************/
	public static final String PLACA = "PLACA";
	public static final String INFRACCION = "INFRACCION";
	public static final String PREIMPRESA = "PREIMPRESA";
	public static final String NCI = "NCI";

	/***************************************************************
	 * OPCIONES DE B�SQUEDA EN CONSULTA DE REMISIONES
	 ***************************************************************/
	public static final String LABEL_PLACA = "NO. DE PLACA";
	public static final String VALUE_PLACA = "PLACA";
	public static final String LABEL_NUMERO_INFRACCION = "NO. DE INFRACCI�N";
	public static final String VALUE_NUMERO_INFRACCION = "INFRACCION";
	public static final String LABEL_IMPRESA = "INFRACCION IMPRESA";
	public static final String VALUE_IMPRESA = "PREIMPRESA";
	public static final String LABEL_NUMERO_CONTROL_INTERNO = "NUMERO DE CONTROL INTERNO";
	public static final String VALUE_NUMERO_CONTROL_INTERNO = "NCI";
	public static final String LABEL_DOCUMENTO = "DOCUMENTO";
	public static final String VALUE_DOCUMENTO = "DOCUMENTO";

	/***************************************************************
	 * TIPOS DE INGRESO (tieneIngreso)
	 ****************************************************************/
	public static final String INFRACCION_SIN_INGRESO = "NI";
	public static final String INFRACCION_CON_INGRESO = "CI";
	public static final String INFRACCION_CON_INGRESO_PLACA = "CIP";

	/***************************************************************
	 * TIPOS DE INGRESO
	 ****************************************************************/
	public static final String TIPO_INGRESO_GRUA = "1";
	public static final String TIPO_INGRESO_A_PIE = "2";
	public static final String TIPO_INGRESO_A_PIE_VALOR_GENERICO = "167";

	/***************************************************************
	 * CAUSAS DE INGRESO
	 ****************************************************************/
	public static final String CAUSA_INGRESO_ESCOLTADO = "6";
	public static final String CAUSA_INGRESO_GRUA = "1";

	/***************************************************************
	 * FLAGS
	 ****************************************************************/
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String INGRESO_CON_ARRASTRE = "S";

	/***************************************************************
	 * OPCIONES CHECK TIPOS DE SELLADO
	 ****************************************************************/
	public static final String LABEL_VEHICULO_SELLADO_EN_ARRASTRE = "Arrastre";
	public static final String ID_VEHICULO_SELLADO_EN_ARRASTRE = "A";
	public static final String LABEL_VEHICULO_SELLADO_EN_DEPOSITO = "Dep�sito";
	public static final String ID_VEHICULO_SELLADO_EN_DEPOSITO = "D";

	/***************************************************************
	 * OPCIONES CHECK TIPOS DE CAJUELA ABIERTA
	 ****************************************************************/
	public static final String LABEL_CAJUELA_ABIERTA_SI = "Si";
	public static final String ID_CAJUELA_ABIERTA_SI = "S";
	public static final String LABEL_CAJUELA_ABIERTA_NO = "No";
	public static final String ID_CAJUELA_ABIERTA_NO = "N";

	/***************************************************************
	 * NUMERO ECONOMICO DE GRUA VALIDADO
	 ****************************************************************/
	public static final int GRUA_NO_ENCONTRADA = 0;
	public static final int GRUA_ENCONTRADA = 1;
	public static final int GRUA_NO_CORRESPONDE = 2;

	/***************************************************************
	 * OPCIONES CHECK TIPOS DE STATUS PLACA VEH�CULO
	 ****************************************************************/
	public static final String LABEL_VEHICULO_TIENE_PLACA_SI = "Si";
	public static final String ID_VEHICULO_TIENE_PLACA_SI = "S";
	public static final String LABEL_VEHICULO_TIENE_PLACA_NO = "No";
	public static final String ID_VEHICULO_TIENE_PLACA_NO = "N";

	/***************************************************************
	 * OPCIONES CHECK TIPOS DE ORIGEN VEH�CULO
	 ****************************************************************/
	public static final String LABEL_VEHICULO_ORIGEN_NACIONAL = "Nacional";
	public static final String ID_VEHICULO_ORIGEN_NACIONAL = "N";
	public static final String LABEL_VEHICULO_ORIGEN_EXTRANJERO = "Extranjero";
	public static final String ID_VEHICULO_ORIGEN_EXTRANJERO = "E";

	/***************************************************************
	 * OPCIONES CHECK VIOLACION LEY DE TRANSPORTE
	 ****************************************************************/
	public static final String LABEL_VIOLACION_LEY_TRANSPORTE_SI = "Si";
	public static final String ID_VIOLACION_LEY_TRANSPORTE_SI = "S";
	public static final String LABEL_VIOLACION_LEY_TRANSPORTE_NO = "No";
	public static final String ID_VIOLACION_LEY_TRANSPORTE_NO = "N";
	
	/***************************************************************
	 * TIPOS DE ESTATUS MOVIMIENTOS
	 ***************************************************************/
    public static final Integer ESTATUS_INGRESADO = 2;

	public static final Long BASE_FOLIO_ALTA_INGRESO_SIN_INFRACCION = new Long(200000000);
	
}
