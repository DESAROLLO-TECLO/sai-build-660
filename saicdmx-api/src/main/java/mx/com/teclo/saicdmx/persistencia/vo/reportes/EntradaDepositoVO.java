package mx.com.teclo.saicdmx.persistencia.vo.reportes;

public class EntradaDepositoVO {
	private int DEP_ID;
	private String DEPOSITO;
	private int CAUSA_ID;
	private String CAUSA_INGRESO;
	private int T_INGR_ID;
	private String TIPO_INGRESO;
	private String FECHA_INGRESO;
	private int TOTAL_INGRESOS;
	
	public int getDEP_ID() {
		return DEP_ID;
	}
	public void setDEP_ID(int dEP_ID) {
		DEP_ID = dEP_ID;
	}
	public String getDEPOSITO() {
		return DEPOSITO;
	}
	public void setDEPOSITO(String dEPOSITO) {
		DEPOSITO = dEPOSITO;
	}
	public int getCAUSA_ID() {
		return CAUSA_ID;
	}
	public void setCAUSA_ID(int cAUSA_ID) {
		CAUSA_ID = cAUSA_ID;
	}
	public String getCAUSA_INGRESO() {
		return CAUSA_INGRESO;
	}
	public void setCAUSA_INGRESO(String cAUSA_INGRESO) {
		CAUSA_INGRESO = cAUSA_INGRESO;
	}
	public int getT_INGR_ID() {
		return T_INGR_ID;
	}
	public void setT_INGR_ID(int t_INGR_ID) {
		T_INGR_ID = t_INGR_ID;
	}
	public String getTIPO_INGRESO() {
		return TIPO_INGRESO;
	}
	public void setTIPO_INGRESO(String tIPO_INGRESO) {
		TIPO_INGRESO = tIPO_INGRESO;
	}
	public String getFECHA_INGRESO() {
		return FECHA_INGRESO;
	}
	public void setFECHA_INGRESO(String fECHA_INGRESO) {
		FECHA_INGRESO = fECHA_INGRESO;
	}
	public int getTOTAL_INGRESOS() {
		return TOTAL_INGRESOS;
	}
	public void setTOTAL_INGRESOS(int tOTAL_INGRESOS) {
		TOTAL_INGRESOS = tOTAL_INGRESOS;
	}
	
	
	

}
