package mx.com.teclo.saicdmx.persistencia.vo.reportes;

import java.util.List;

public class TotalInfraccionesporArticuloVO {
	/*,,,INFRAC_PARRAFO,INFRAC_INCISO*/

	private String INFRAC_FECHA_CORTA;
	private String INFRAC_ARTICULO;
	private String INFRAC_FRACCION;
	private String INFRAC_PARRAFO;
	private String INFRAC_INCISO;
	private long   total;
	
	/*parametros de busqueda*/
	private String fechaInicio;
	private String fechaFin;
	private List<String> id;
	private String articulo;
	
	/**
	 * @return the iNFRAC_FECHA_CORTA
	 */
	public String getINFRAC_FECHA_CORTA() {
		return INFRAC_FECHA_CORTA;
	}
	/**
	 * @param iNFRAC_FECHA_CORTA the iNFRAC_FECHA_CORTA to set
	 */
	public void setINFRAC_FECHA_CORTA(String iNFRAC_FECHA_CORTA) {
		INFRAC_FECHA_CORTA = iNFRAC_FECHA_CORTA;
	}
	/**
	 * @return the iNFRAC_ARTICULO
	 */
	public String getINFRAC_ARTICULO() {
		return INFRAC_ARTICULO;
	}
	/**
	 * @param iNFRAC_ARTICULO the iNFRAC_ARTICULO to set
	 */
	public void setINFRAC_ARTICULO(String iNFRAC_ARTICULO) {
		INFRAC_ARTICULO = iNFRAC_ARTICULO;
	}
	/**
	 * @return the iNFRAC_FRACCION
	 */
	public String getINFRAC_FRACCION() {
		return INFRAC_FRACCION;
	}
	/**
	 * @param iNFRAC_FRACCION the iNFRAC_FRACCION to set
	 */
	public void setINFRAC_FRACCION(String iNFRAC_FRACCION) {
		INFRAC_FRACCION = iNFRAC_FRACCION;
	}
	/**
	 * @return the iNFRAC_PARRAFO
	 */
	public String getINFRAC_PARRAFO() {
		return INFRAC_PARRAFO;
	}
	/**
	 * @param iNFRAC_PARRAFO the iNFRAC_PARRAFO to set
	 */
	public void setINFRAC_PARRAFO(String iNFRAC_PARRAFO) {
		INFRAC_PARRAFO = iNFRAC_PARRAFO;
	}
	/**
	 * @return the iNFRAC_INCISO
	 */
	public String getINFRAC_INCISO() {
		return INFRAC_INCISO;
	}
	/**
	 * @param iNFRAC_INCISO the iNFRAC_INCISO to set
	 */
	public void setINFRAC_INCISO(String iNFRAC_INCISO) {
		INFRAC_INCISO = iNFRAC_INCISO;
	}
	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the id
	 */
	public List<String> getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(List<String> id) {
		this.id = id;
	}
	/**
	 * @return the articulo
	 */
	public String getArticulo() {
		return articulo;
	}
	/**
	 * @param articulo the articulo to set
	 */
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	
}
