package mx.com.teclo.saicdmx.persistencia.vo.radares;

public class DetalleInfraccionesLiberadasVO {

	private String folioInicial;
	private String folioFinal;
	private Integer total;
	/**
	 * @return the folioInicial
	 */
	public String getFolioInicial() {
		return folioInicial;
	}
	/**
	 * @param folioInicial the folioInicial to set
	 */
	public void setFolioInicial(String folioInicial) {
		this.folioInicial = folioInicial;
	}
	/**
	 * @return the folioFinal
	 */
	public String getFolioFinal() {
		return folioFinal;
	}
	/**
	 * @param folioFinal the folioFinal to set
	 */
	public void setFolioFinal(String folioFinal) {
		this.folioFinal = folioFinal;
	}
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
}
