package mx.com.teclo.saicdmx.keyloader.certificados;

 
import com.google.common.io.ByteStreams;
 
import lombok.Getter;

import org.apache.commons.ssl.PKCS8Key;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * Descripci�n: Implementaci�n de PrivateKeyLoader.
 * Historial de Modificaciones: 
 * Descripci�n del Cambio : Creaci�n
 * @author : fjmb
 * @version : 1.0 
 * Fecha : 12/Septiembre/2016.
 * 
 */
public class PrivateKeyLoader implements KeyLoader {

	@Getter
	PrivateKey key;

	public PrivateKeyLoader(String privateKeyLocation, String keyPassword) throws GeneralSecurityException {
		this.setPrivateKey(privateKeyLocation, keyPassword);
	}

	public PrivateKeyLoader(InputStream privateKeyInputStream, String keyPassword) throws GeneralSecurityException {
		this.setPrivateKey(privateKeyInputStream, keyPassword);
	}

	/**
	 * @param privateKeyLocation,  private key located in filesystem
	 * @param keyPassword,    private key password
	 * @throws GeneralSecurityException 
	 * @throws KeyException,  thrown when any security exception occurs
	 */

	public void setPrivateKey(String privateKeyLocation, String keyPassword) throws GeneralSecurityException  {
		InputStream privateKeyInputStream = null;

		try {
			privateKeyInputStream = new FileInputStream(privateKeyLocation);
		} catch (FileNotFoundException fnfe) {
			throw new KeyException("La ubicaci�n del archivo de la llave privada es incorrecta", fnfe.getCause());
		}

		this.setPrivateKey(privateKeyInputStream, keyPassword);
	}

	/**
	 *
	 * @param privateKeyInputStream,  private key's input stream
	 * @param keyPassword,  private key password
	 * @throws GeneralSecurityException 
	 * @throws KeyException, thrown when any security exception occurs
	 */
	public void setPrivateKey(InputStream privateKeyInputStream, String keyPassword) throws GeneralSecurityException {

		byte[] privateKeyByte = this.extractProtectedPrivateKey(privateKeyInputStream, keyPassword);
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);

		try {
			this.key = KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
		} catch (GeneralSecurityException gse) {
			throw new KeyException("Error al obtener la información del certificado debido a su codificación",
					gse.getCause());
		}
	}

	private byte[] extractProtectedPrivateKey(InputStream privateKeyInputStream, String keyPassword) throws GeneralSecurityException {
		byte[] bytes;

		try {
			if (keyPassword == null) {
				bytes = ByteStreams.toByteArray(privateKeyInputStream);
			} else {
				bytes = new PKCS8Key(privateKeyInputStream, keyPassword.toCharArray()).getDecryptedBytes();
			}
//		}
//		catch (GeneralSecurityException e) {
//			throw new KeyException("La contraseña del certificado no es correcta", e.getCause());
		
		} catch (IOException ioe) {
			throw new KeyException(ioe.getMessage(), ioe.getCause());
		}

		return bytes;
	}

	public static RSAPublicKeySpec getPublicKeySpec(PrivateKey priv) {
		RSAPrivateCrtKey rsaCrtKey = (RSAPrivateCrtKey) priv; // May throw a
																// ClassCastException
		return new RSAPublicKeySpec(rsaCrtKey.getModulus(), rsaCrtKey.getPublicExponent());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getKey() {
		// TODO Auto-generated method stub
		return (T) key;
	}
}
