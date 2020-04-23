package mx.com.teclo.saicdmx.pdf.garantia;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

public class GarantiaGeneraReporteVoucher {

	  
	public ByteArrayOutputStream generaReporteVoucher(String voucherComercio,String voucherCliente, String sRutaDinamico) {
	    	
		  	ByteArrayOutputStream reporte = new ByteArrayOutputStream();
	        Map p = new HashMap();
	        String comercio,cliente;
	        String comercionChar="";
	        comercionChar=voucherComercio.contains("AL RESPECTO.")?".":"@";	        
	        int indexPrimerArroba = voucherComercio.indexOf("@");        
	        int indexUltimoArroba = voucherComercio.lastIndexOf(comercionChar);   
	        
	        comercio = voucherComercio.substring(indexPrimerArroba,indexUltimoArroba);        
	        comercio = comercio.replaceAll("logo_cpagos", " ").replaceAll("ver_app", " ");
	        indexPrimerArroba = voucherCliente.indexOf("@");        
	        indexUltimoArroba = voucherCliente.lastIndexOf("@");
	        cliente = voucherCliente.substring(indexPrimerArroba,indexUltimoArroba);        
	        cliente = cliente.replaceAll("logo_cpagos", " ").replaceAll("ver_app", " ");        
	        int sizeMaxLine = sizeMaxLineVoucher(comercio+cliente);
	        comercio = formatVoucher(comercio,sizeMaxLine);
	        cliente = formatVoucher(cliente,sizeMaxLine);
	        p.put("ComercioSeccion1", comercio);
	        p.put("ClienteSeccion1", cliente);
		     
	        try {
	           reporte.write(JasperRunManager.runReportToPdf(sRutaDinamico, p, new JREmptyDataSource()));
	        } catch (Exception e) {
	            String mensaje = "Error: " + e.getMessage();
	            try {
	                reporte.write(mensaje.getBytes());
	            } catch (IOException ioexception) {
	            }
	        } 
	        
	        return reporte;
	    }
	   
	    private int sizeMaxLineVoucher(String voucher){
	    	String arrVoucher[] = voucher.split("@");
	    	int sizeMaxLine = 0;
	    	int sizeLine = 0;
	    	String lineBigger = "";
	    	for(String line:arrVoucher){
				  sizeLine = line.trim().length();
				  if(sizeLine > sizeMaxLine){
					  sizeMaxLine = sizeLine;
					  lineBigger = line;
				  }
			  }
	    	return sizeMaxLine;
	    }
	    
	    private String formatVoucher(String voucher,int sizeMaxLine){
	           String arrVoucher[] = voucher.split("@");
	           StringBuilder voucherFormated = new StringBuilder();
	           
	           int i=0,size = 0, total=0;
	           String replaceFormat = "",lineBreak="";
	           String beginBoldText="<style isBold='true' pdfFontName='Helvetica-Bold'>";
	           String endBoldText="</style>";
	           String inicioTamañoTexto="<style  size='6' textAlignment='left'>";
	           String TamañoTexto="<style  size='7'>";
	           String formatCNB="@cnb",formatCNN="@cnn",formatLNN="@lnn",formatBC="@bc",formatLSN="@lsn",formatBR="@br" ;

	           sizeMaxLine = sizeMaxLine - formatCNB.length();

	           for(String line:arrVoucher){
	               line = "@"+line;
	               if(line.contains(formatCNB)){
	                   size = line.length()-formatCNB.length();
	                   total = (sizeMaxLine-size);
	                   replaceFormat = "";
	                   for(i=0;i<total;i++){
	                       replaceFormat+=" ";
	                   }
	                   voucherFormated.append(lineBreak).append(beginBoldText).append(line.replace(formatCNB, replaceFormat)).append(endBoldText);
	               }else if(line.contains(formatCNN)){
	                   size = line.length()-formatCNN.length();
	                   total = (sizeMaxLine-size);
	                   replaceFormat = " ";
	                   for(i=0;i<total;i++){
	                       replaceFormat+=" ";
	                   }
	                   voucherFormated.append(lineBreak).append(TamañoTexto).append(line.replace(formatCNN, replaceFormat)).append(endBoldText);
	               }else if(line.contains(formatLSN)){
	                   size = line.length()-formatLSN.length();
	                   total = (sizeMaxLine-size);
	                   replaceFormat = " ";
//	                   for(i=0;i<total;i++){
//	                       replaceFormat+=" ";
//	                   }
	                   voucherFormated.append(lineBreak).append(inicioTamañoTexto).append(line.replace(formatLSN, replaceFormat)).append(endBoldText);
	               }else if(line.contains(formatLNN)){
	                   voucherFormated.append(line.replace(formatLNN, lineBreak));
	               }else if(line.contains(formatBR)){
	                   voucherFormated.append(line.replace(formatBR, lineBreak));
	               }else if(line.contains(formatBC)){
	                   voucherFormated.append(line.replace(formatBC, lineBreak));
	               }else{
	                   voucherFormated.append(line.replace("@", lineBreak));
	               }
	           }
	           return voucherFormated.toString();
	     }

	
}
