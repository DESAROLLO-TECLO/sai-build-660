package mx.com.teclo.saicdmx.persistencia.vo.salidas;

public class EvidenciasVO {
	private boolean existe = false;
	private byte[] Img;
	private String localPath;

	public boolean isExiste() {
		return existe;
	}
	public void setExiste(boolean existe) {
		this.existe = existe;
	}
	public byte[] getImg() {
		return Img;
	}
	public void setImg(byte[] img) {
		Img = img;
	}
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	
	
	
}
