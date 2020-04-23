package mx.com.teclo.saicdmx.persistencia.vo.reportes;

public class ColumnasVO {

	private Long idColumna;
	private TablasVO tabla;
	private String nbAlias;
	private String nbReal;

	public Long getIdColumna() {
		return idColumna;
	}

	public void setIdColumna(Long idColumna) {
		this.idColumna = idColumna;
	}

	public TablasVO getTabla() {
		return tabla;
	}

	public void setTabla(TablasVO tabla) {
		this.tabla = tabla;
	}

	public String getNbAlias() {
		return nbAlias;
	}

	public void setNbAlias(String nbAlias) {
		this.nbAlias = nbAlias;
	}

	public String getNbReal() {
		return nbReal;
	}

	public void setNbReal(String nbReal) {
		this.nbReal = nbReal;
	}

}
