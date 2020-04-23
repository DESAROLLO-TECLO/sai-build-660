package mx.com.teclo.saicdmx.keyloader.certificados;

 
import java.io.InputStream;
import java.security.GeneralSecurityException;

 
/**
 * Descripci�n: Implementaci�n KeyLoaderFactory.
 *
 * Historial de Modificaciones:
 * Descripci�n del Cambio	: Creaci�n
 * @author 					: fjmb 
 * @version 				: 1.0
 * Fecha					: 12/Septiembre/2016.
 * 
 */
public class KeyLoaderFactory {


    public final static KeyLoader createInstance(KeyLoaderEnumeration keyLoaderEnumeration, String keyLocation, String ... keyPassword) throws GeneralSecurityException   {
        KeyLoader keyLoader = null;

        if(keyLoaderEnumeration == KeyLoaderEnumeration.PRIVATE_KEY_LOADER) {
            keyLoader = new PrivateKeyLoader(keyLocation, keyPassword == null ? null : keyPassword[0]);
        } else if (keyLoaderEnumeration == KeyLoaderEnumeration.PUBLIC_KEY_LOADER){
            keyLoader = new PublicKeyLoader(keyLocation);
        }

        return keyLoader;
    }


    public final static KeyLoader createInstance(KeyLoaderEnumeration keyLoaderEnumeration, InputStream keyInputStream, String ... keyPassword) throws GeneralSecurityException  {
        KeyLoader keyLoader = null;

        if(keyLoaderEnumeration == KeyLoaderEnumeration.PRIVATE_KEY_LOADER) {
            keyLoader = new PrivateKeyLoader(keyInputStream, keyPassword == null ? null : keyPassword[0]);
        } else if (keyLoaderEnumeration == KeyLoaderEnumeration.PUBLIC_KEY_LOADER){
            keyLoader = new PublicKeyLoader(keyInputStream);
        }

        return keyLoader;
    }
    
    
}
