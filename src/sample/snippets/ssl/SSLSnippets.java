package sample.snippets.ssl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SSLSnippets {

	public static void main(String[] args) {
		try {
			downloadSSLCertificate("www.github.com", 443);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		}		
	}

	private static void downloadSSLCertificate(String host, int port) throws UnknownHostException, IOException, CertificateEncodingException {		
		SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(host, port);
		sslSocket.setEnabledProtocols(new String[] {"TLSv1","TLSv1.1","TLSv1.2"});
		sslSocket.startHandshake();
		Certificate certs[] = sslSocket.getSession().getPeerCertificates();
		System.out.println("number of peer certificates reveiced: " + certs.length);
		int i = 0;
		for (Certificate cert : certs) {
			String certFileName = "temp/cert_" + host + "_" + cert.getType() + "_" + ++i + ".crt";
			File file = new File(certFileName);
			file.delete();
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(cert.getEncoded());
			fileOutputStream.close();
			System.out.println("certificate downloaded - " + certFileName);
		}		
	}

}
