package mx.com.teclo.saicdmx.negocio.service.certificados;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.Principal;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

import mx.com.teclo.saicdmx.persistencia.vo.certificados.CertificadoVO;

public class ReadCertificateDataService {

	private InputStream inStream;
	private X509Certificate certificate;
	private CertificateFactory certificateFactory;
	private CertificadoVO detailsCert;

	public ReadCertificateDataService(InputStream inStream, X509Certificate certificate) {
		this.inStream = inStream;
		this.certificate = certificate;
	}

	public ReadCertificateDataService(InputStream inStream) {
		this.inStream = inStream;
	}

	public CertificadoVO getCertificateData() {

		detailsCert = new CertificadoVO();

		try {
			certificateFactory = CertificateFactory.getInstance("X.509");
			certificate = (X509Certificate) certificateFactory.generateCertificate(inStream);

			detailsCert.setEmisorDN(certificate.getSubjectDN().toString());
			detailsCert.setPropietarioDN(certificate.getIssuerDN().toString());
			detailsCert.setCertValidoDesde(certificate.getNotBefore());
			detailsCert.setCertValidoHasta(certificate.getNotAfter());
			detailsCert.setCertEncoder(convertToPem(certificate));
			detailsCert.setNumSerie(certificate.getSerialNumber().toString());

			Principal principal = certificate.getIssuerDN();
			Properties prop = new Properties();
			prop.load(new StringReader(principal.getName().replaceAll(",", "\n")));
			String issuerCN = prop.getProperty("CN");
			detailsCert.setCertEmitidoPor(issuerCN);
			principal = certificate.getSubjectDN();
			prop.load(new StringReader(principal.getName().replaceAll(",", "\n")));
			String subjectCN = prop.getProperty("CN");
			detailsCert.setCertEmitidoPara(subjectCN);
			String subjectRFC = prop.getProperty("OID.2.5.4.45");
			detailsCert.setPropietarioRfc(subjectRFC);

		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return detailsCert;

	}

	protected static String convertToPem(X509Certificate cert) throws CertificateEncodingException {
		Base64 encoder = new Base64(64);
		String cert_begin = "-----BEGIN CERTIFICATE-----\n";
		String end_cert = "-----END CERTIFICATE-----";

		byte[] derCert = cert.getEncoded();
		String pemCertPre = new String(encoder.encode(derCert));
		String pemCert = cert_begin + pemCertPre + end_cert;
		return pemCert;
	}

	public X509Certificate getCertificate() {

		try {
			certificate = (X509Certificate) certificateFactory.generateCertificate(inStream);
		} catch (CertificateException e) {
			e.printStackTrace();
		}

		return certificate;
	}
	
}