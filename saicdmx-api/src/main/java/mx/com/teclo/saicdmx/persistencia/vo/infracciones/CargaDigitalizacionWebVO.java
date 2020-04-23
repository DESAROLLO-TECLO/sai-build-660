package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

public class CargaDigitalizacionWebVO {
	
	private Long empleadoId;
	private Integer FoliosRepetidos;
	private Integer FoliosLiberados;
	private Integer FoliosProcesados;
	private Integer ResultOut;
	private String MessageOut;
	private CargaDigitalizacionWebSPVO cargaDigitalizacionWebSPVO;
	
	public CargaDigitalizacionWebVO(){
		
	}
	
	public CargaDigitalizacionWebVO(Long empleadoId, Integer foliosRepetidos, Integer foliosLiberados,
			Integer foliosProcesados, Integer resultOut, String messageOut) {
		super();
		this.empleadoId = empleadoId;
		FoliosRepetidos = foliosRepetidos;
		FoliosLiberados = foliosLiberados;
		FoliosProcesados = foliosProcesados;
		ResultOut = resultOut;
		MessageOut = messageOut;
	}
	public Long getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
	}
	public Integer getFoliosRepetidos() {
		return FoliosRepetidos;
	}
	public void setFoliosRepetidos(Integer foliosRepetidos) {
		FoliosRepetidos = foliosRepetidos;
	}
	public Integer getFoliosLiberados() {
		return FoliosLiberados;
	}
	public void setFoliosLiberados(Integer foliosLiberados) {
		FoliosLiberados = foliosLiberados;
	}
	public Integer getFoliosProcesados() {
		return FoliosProcesados;
	}
	public void setFoliosProcesados(Integer foliosProcesados) {
		FoliosProcesados = foliosProcesados;
	}
	public Integer getResultOut() {
		return ResultOut;
	}
	public void setResultOut(Integer resultOut) {
		ResultOut = resultOut;
	}
	public String getMessageOut() {
		return MessageOut;
	}
	public void setMessageOut(String messageOut) {
		MessageOut = messageOut;
	}
	public CargaDigitalizacionWebSPVO getCargaDigitalizacionWebSPVO() {
		return cargaDigitalizacionWebSPVO;
	}
	public void setCargaDigitalizacionWebSPVO(CargaDigitalizacionWebSPVO cargaDigitalizacionWebSPVO) {
		this.cargaDigitalizacionWebSPVO = cargaDigitalizacionWebSPVO;
	}
	public void generaParametrosParaSP(){
		this.cargaDigitalizacionWebSPVO = 
				new CargaDigitalizacionWebSPVO(
						empleadoId,
						FoliosRepetidos,
						FoliosLiberados,
						FoliosProcesados,
						ResultOut,
						MessageOut);
	}
}
