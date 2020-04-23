package mx.com.teclo.saicdmx.persistencia.vo.admireporte;

import java.util.ArrayList;

public class ConfigParamVO {
	
	private String nbParametro;
	private String nbEtiquetaParam;
	private TipoParametroVO tipoParametroVO;
	private ArrayList<PropiedadesVO> propiedades;
	private ComponenteVO componenteVO;
	
	//constructor de una clase
	public ConfigParamVO(String nbParametro){
		this.nbParametro = nbParametro;
	}
	
	public ConfigParamVO(){
		super();
	}
	
	public String getNbParametro() {
		return nbParametro;
	}
	public void setNbParametro(String nbParametro) {
		this.nbParametro = nbParametro;
	}
	public String getNbEtiquetaParam() {
		return nbEtiquetaParam;
	}
	public void setNbEtiquetaParam(String nbEtiquetaParam) {
		this.nbEtiquetaParam = nbEtiquetaParam;
	}
	public TipoParametroVO getTipoParametroVO() {
		return tipoParametroVO;
	}
	public void setTipoParametroVO(TipoParametroVO tipoParametroVO) {
		this.tipoParametroVO = tipoParametroVO;
	}
	public ArrayList<PropiedadesVO> getPropiedades() {
		return propiedades;
	}
	public void setPropiedades(ArrayList<PropiedadesVO> propiedades) {
		this.propiedades = propiedades;
	}
	
	
	
	
	public ComponenteVO getComponenteVO() {
		return componenteVO;
	}
	public void setComponenteVO(ComponenteVO componenteVO) {
		this.componenteVO = componenteVO;
	}
	
		

}
