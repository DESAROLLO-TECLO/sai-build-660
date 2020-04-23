package mx.com.teclo.saicdmx.util.enums;


public enum EnumGarantiasTipo {
	PLACA(1), LICENCIA(2), TARJETA(3), PROMESA_PLACA(4), PROMESA_LICENCIA(5), PROMESA_TARJETA(6), GARANTIA_ASOCIADA(7);

	private Integer tipoGarantiaID;

	private EnumGarantiasTipo(Integer tipoGarantiaID) {
		this.tipoGarantiaID = tipoGarantiaID;
	}

	public Integer getTipoGarantiaID() {
		return tipoGarantiaID;
	}

	public void setTipoGarantiaID(Integer tipoGarantiaID) {
		this.tipoGarantiaID = tipoGarantiaID;
	}

	public static EnumGarantiasTipo getTipoGarantia(Integer tipoGarantia) {
		for (EnumGarantiasTipo tipo : EnumGarantiasTipo.values()) {
			if (tipoGarantia.equals(tipo.getTipoGarantiaID())) {
				return tipo;
			}
		}
		return null;
	}
}
