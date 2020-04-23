package mx.com.teclo.saicdmx.pdf.garantia;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

public class GarantiaGeneraReporteVoucherHH {
	
    public  ByteArrayOutputStream generaReporteVoucherHH(ArrayList arrVoucher, String logoSSP, String logoPlaca, String sRutaHH) {
        ByteArrayOutputStream reporte = new ByteArrayOutputStream();
        Map p = new HashMap();
        p.put("NBanco", arrVoucher.get(1));
        p.put("CreditoDebito", arrVoucher.get(2));
        p.put("Venta", arrVoucher.get(3));
        p.put("Descripcion", arrVoucher.get(4));
        p.put("NCDescripcion", arrVoucher.get(5));
        p.put("Direccion", arrVoucher.get(6));
        p.put("CDireccion", arrVoucher.get(7));
        p.put("Clave", arrVoucher.get(8));
        p.put("NoTarjeta", arrVoucher.get(10));
        p.put("Vence", arrVoucher.get(11));
        p.put("TipoTarjeta", arrVoucher.get(13));
        p.put("Importe", arrVoucher.get(15));
        p.put("Operacion", arrVoucher.get(17));
        p.put("Referencia", arrVoucher.get(18));
        p.put("Aprobacion", arrVoucher.get(19));
        p.put("Fecha", arrVoucher.get(21));
        p.put("Linea", arrVoucher.get(27));
        p.put("NombreUsuario", arrVoucher.get(28));
        String SeccionLeyenda = "";
        for (int y = 1; y < arrVoucher.size(); y++) {
            if (y >= 29) {
                SeccionLeyenda = SeccionLeyenda + arrVoucher.get(y).toString() + "\n";
            }
        }
        p.put("SeccionLeyenda", SeccionLeyenda);
        p.put("PathLogoSSP",logoSSP );
        p.put("PathLogoPlaca",logoPlaca);
        try {
           reporte.write(JasperRunManager.runReportToPdf(sRutaHH, p, new JREmptyDataSource()));
        } catch (Exception e) {
            String mensaje = "Error: " + e.getMessage();
            try {
                reporte.write(mensaje.getBytes());
            } catch (IOException ioexception) {
            }
        } finally {
           
        }
        return reporte;
    }

}
