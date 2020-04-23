package mx.com.teclo.saicdmx.keyloader.certificados;

 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

 
import lombok.Getter;

/**
 * Descripci�n: Implementaci�n PublicKeyLoader.
 *
 * Historial de Modificaciones:
 * Descripci�n del Cambio	: Creaci�n
 * @author 					: fjmb 
 * @version 				: 1.0
 * Fecha					: 12/Septiembre/2016.
 * 
 */
public class PublicKeyLoader implements KeyLoader {

    @Getter
    X509Certificate key;


    public PublicKeyLoader(String certificateLocation) {
        try {
            this.setX509Certificate(new FileInputStream(certificateLocation));
        } catch (FileNotFoundException fnfe) {
            throw new KeyException("La ubicación del archivo de la llave pública es incorrecta", fnfe.getCause());
        }
    }



    public PublicKeyLoader(InputStream crtInputStream) {
        this.setX509Certificate(crtInputStream);
    }



    public void setX509Certificate(InputStream crtInputStream) {
        try {
            this.key = (X509Certificate)
                    CertificateFactory.getInstance("X.509").generateCertificate(crtInputStream);
        } catch (CertificateException e) {
            throw new KeyException("Error al obtener el certificado x.509. La codificación puede ser incorrecta.", e.getCause());
        }
    }

    public static RSAPublicKeySpec getPublicKeySpec(X509Certificate cert) {
    	 PublicKey localPublicKey = cert.getPublicKey();
		 RSAPublicKey rsaPublicKey = (RSAPublicKey) localPublicKey;
		 
		return new RSAPublicKeySpec(rsaPublicKey.getModulus(), rsaPublicKey.getPublicExponent());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getKey() {
		// TODO Auto-generated method stub
		return (T) key;
	}

}
