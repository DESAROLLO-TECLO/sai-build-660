package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

public class SectorSinUnidadTerritorialVO 
{
	private Long secId;	
	private String secCod;	
	private String secNombre;	

	/**
	 * @return the secId
	 */
	public Long getSecId() 
	{
		return secId;
	}

	/**
	 * @param secId the secId to set
	 */
	public void setSecId(Long secId) 
	{
		this.secId = secId;
	}

	/**
	 * @return the secCod
	 */
	public String getSecCod() 
	{
		return secCod;
	}

	/**
	 * @param secCod the secCod to set
	 */
	public void setSecCod(String secCod) 
	{
		this.secCod = secCod;
	}

	/**
	 * @return the secNombre
	 */
	public String getSecNombre() 
	{
		return secNombre;
	}

	/**
	 * @param secNombre the secNombre to set
	 */
	public void setSecNombre(String secNombre) 
	{
		this.secNombre = secNombre;
	}

	
	@Override
	public String toString()
	{
		StringBuilder sectorVO = new StringBuilder("");
		sectorVO.append("\n\t secId: " + secId);
		sectorVO.append("\n\t secCod: " + secCod);
		sectorVO.append("\n\t secNombre: " + secNombre);
	
		return sectorVO.toString();
	}
	
}
