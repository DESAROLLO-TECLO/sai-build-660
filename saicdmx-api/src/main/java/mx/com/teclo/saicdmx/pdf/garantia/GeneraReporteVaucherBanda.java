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

public class GeneraReporteVaucherBanda {
	
	
	public  ByteArrayOutputStream generaReporteVoucherBanda(ArrayList arrVoucherComercio,
            ArrayList arrVoucherCliente, String logoSSP, String logoPlaca, String sRuta) {
        
        ByteArrayOutputStream reporte = new ByteArrayOutputStream();
        Map<String, Object> p = new HashMap();
        System.out.println(arrVoucherComercio.toString());
        String ComercioImpresion = "";
        String ComercioSeccion1 = "";
        String ComercioSeccion2 = "";
        String ComercioSeccion3 = "";
        String ComercioSeccion4 = "";
        String ComercioSeccion5 = "";
        String ComercioSeccion6 = "";
        String ComercioSeccion7 = "";
        String ComercioSeccion8 = "";
        String ComercioSeccion9 = "";
        for (int y = 1; y < arrVoucherComercio.size(); y++) {
            System.out.println(arrVoucherComercio.get(y).toString());
            ComercioImpresion = ComercioImpresion + arrVoucherComercio.get(y).toString() + "\n";
            if (y == 1) {
                ComercioSeccion1 = ComercioSeccion1 + arrVoucherComercio.get(y).toString() + "\n";
            }
            if (y >= 2 && y <= 11) {
                ComercioSeccion2 = ComercioSeccion2 + arrVoucherComercio.get(y).toString() + "\n";
            }
            if (y >= 12 && y <= 16) {
                ComercioSeccion3 = ComercioSeccion3 + arrVoucherComercio.get(y).toString() + "\n";
            }
            if (y >= 17 && y <= 18) {
                ComercioSeccion4 = ComercioSeccion4 + arrVoucherComercio.get(y).toString() + "\n";
            }
            if (y >= 19 && y <= 20) {
                ComercioSeccion5 = ComercioSeccion5 + arrVoucherComercio.get(y).toString() + "\n";
            }
            if (y >= 21 && y <= 22) {
                ComercioSeccion6 = ComercioSeccion6 + arrVoucherComercio.get(y).toString() + "\n";
            }
            if (y >= 23 && y <= 32) {
                ComercioSeccion7 = ComercioSeccion7 + arrVoucherComercio.get(y).toString() + "\n";
            }
            if (y >= 33 && y <= 40) {
                ComercioSeccion8 = ComercioSeccion8 + arrVoucherComercio.get(y).toString() + "\n";
            }
            if (y >= 41) {
                ComercioSeccion9 = ComercioSeccion9 + arrVoucherComercio.get(y).toString() + "\n";
            }
        }
        ComercioImpresion = ComercioSeccion1 + ComercioSeccion2 + ComercioSeccion3 + ComercioSeccion4 + ComercioSeccion5 + ComercioSeccion6 + ComercioSeccion7 + ComercioSeccion8 + ComercioSeccion9;
        p.put("ComercioSeccion1", ComercioSeccion1);
        p.put("ComercioSeccion2", ComercioSeccion2);
        p.put("ComercioSeccion3", ComercioSeccion3);
        p.put("ComercioSeccion4", ComercioSeccion4);
        p.put("ComercioSeccion5", ComercioSeccion5);
        p.put("ComercioSeccion6", ComercioSeccion6);
        p.put("ComercioSeccion7", ComercioSeccion7);
        p.put("ComercioSeccion8", ComercioSeccion8);
        p.put("ComercioSeccion9", ComercioSeccion9);

        String ClienteImpresion = "";
        String ClienteSeccion1 = "";
        String ClienteSeccion2 = "";
        String ClienteSeccion3 = "";
        String ClienteSeccion4 = "";
        String ClienteSeccion5 = "";
        String ClienteSeccion6 = "";
        String ClienteSeccion7 = "";
        String ClienteSeccion8 = "";
        String ClienteSeccion9 = "";

        for (int y = 1; y < arrVoucherCliente.size(); y++) {
            System.out.println(arrVoucherCliente.get(y).toString());
            ClienteImpresion = ClienteImpresion + arrVoucherCliente.get(y).toString() + "\n";
            if (y == 1) {
                ClienteSeccion1 = ClienteSeccion1 + arrVoucherCliente.get(y).toString() + "\n";
            }
            if (y >= 2 && y <= 10) {
                ClienteSeccion2 = ClienteSeccion2 + arrVoucherCliente.get(y).toString() + "\n";
            }
            if (y >= 11 && y <= 14) {
                ClienteSeccion3 = ClienteSeccion3 + arrVoucherCliente.get(y).toString() + "\n";
            }
            if (y >= 15 && y <= 16) {
                ClienteSeccion4 = ClienteSeccion4 + arrVoucherCliente.get(y).toString() + "\n";
            }
            if (y >= 17 && y <= 18) {
                ClienteSeccion5 = ClienteSeccion5 + arrVoucherCliente.get(y).toString() + "\n";
            }
            if (y >= 19 && y <= 20) {
                ClienteSeccion6 = ClienteSeccion6 + arrVoucherCliente.get(y).toString() + "\n";
            }
            if (y >= 21 && y <= 30) {
                ClienteSeccion7 = ClienteSeccion7 + arrVoucherCliente.get(y).toString() + "\n";
            }
            if (y >= 31 && y <= 32) {
                ClienteSeccion8 = ClienteSeccion8 + arrVoucherCliente.get(y).toString() + "\n";
            }
            if (y >= 33) {
                ClienteSeccion9 = ClienteSeccion9 + arrVoucherCliente.get(y).toString() + "\n";
            }
        }
        ComercioImpresion = ClienteSeccion1 + ClienteSeccion2 + ClienteSeccion3 + ClienteSeccion4 + ClienteSeccion5 + ClienteSeccion6 + ClienteSeccion7 + ClienteSeccion8 + ClienteSeccion9;
        p.put("ClienteSeccion1", ClienteSeccion1);
        p.put("ClienteSeccion2", ClienteSeccion2);
        p.put("ClienteSeccion3", ClienteSeccion3);
        p.put("ClienteSeccion4", ClienteSeccion4);
        p.put("ClienteSeccion5", ClienteSeccion5);
        p.put("ClienteSeccion6", ClienteSeccion6);
        p.put("ClienteSeccion7", ClienteSeccion7);
        p.put("ClienteSeccion8", ClienteSeccion8);
        p.put("ClienteSeccion9", ClienteSeccion9);
        
        p.put("PathLogoSSP",logoSSP );
        p.put("PathLogoPlaca", logoPlaca);
        try {
            reporte.write(JasperRunManager.runReportToPdf(sRuta, p, new JREmptyDataSource()));
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
