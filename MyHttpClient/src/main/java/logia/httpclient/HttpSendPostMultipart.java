package logia.httpclient;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import logia.httpclient.response.listener.HttpResponseListener;

/**
 * The Class HttpSendPostMultipart.
 * 
 * @author Paul Mai
 */
public class HttpSendPostMultipart extends HttpUtility {

	/** The file part. */
	private Map<String, File> filePart;

	/**
	 * Instantiates a new http send post multipart.
	 *
	 * @param __host the http host
	 * @param __requestURL the request url
	 * @param __headers the headers
	 * @param __paramsText the params text
	 * @param __paramsFile the params file
	 * @param __listener the __listener
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KeyManagementException the key management exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyStoreException the key store exception
	 */
	public HttpSendPostMultipart(HttpHost __host, String __requestURL,
	        Map<String, String> __headers, Map<String, String> __paramsText,
	        Map<String, File> __paramsFile, HttpResponseListener<?> __listener) throws IOException,
	        KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		this(__host, __requestURL, __headers, __paramsText, __paramsFile, __listener, -1);
	}

	/**
	 * Instantiates a new http send post multipart.
	 *
	 * @param __host the host
	 * @param __requestURL the request URL
	 * @param __headers the headers
	 * @param __paramsText the params text
	 * @param __paramsFile the params file
	 * @param __listener the listener
	 * @param __timeout the timeout in milisecond
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KeyManagementException the key management exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyStoreException the key store exception
	 */
	public HttpSendPostMultipart(HttpHost __host, String __requestURL,
	        Map<String, String> __headers, Map<String, String> __paramsText,
	        Map<String, File> __paramsFile, HttpResponseListener<?> __listener, int __timeout)
	        throws IOException, KeyManagementException, NoSuchAlgorithmException,
	        KeyStoreException {
		super(__host, __requestURL, __headers, __paramsText, __listener, __timeout);
		this.filePart = __paramsFile;
		this.httpRequest = new HttpPost(this.requestURL);
		this.setHeaders();
		this.setParameters();
	}

	/**
	 * Instantiates a new http send post multipart.
	 *
	 * @param __requestURL the request url
	 * @param __headers the headers
	 * @param __paramsText the params text
	 * @param __paramsFile the params file
	 * @param __listener the __listener
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KeyManagementException the key management exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyStoreException the key store exception
	 */
	public HttpSendPostMultipart(String __requestURL, Map<String, String> __headers,
	        Map<String, String> __paramsText, Map<String, File> __paramsFile,
	        HttpResponseListener<?> __listener) throws IOException, KeyManagementException,
	        NoSuchAlgorithmException, KeyStoreException {
		this(__requestURL, __headers, __paramsText, __paramsFile, __listener, -1);
	}

	/**
	 * Instantiates a new http send post multipart.
	 *
	 * @param __requestURL the request URL
	 * @param __headers the headers
	 * @param __paramsText the params text
	 * @param __paramsFile the params file
	 * @param __listener the listener
	 * @param __timeout the timeout in milisecond
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KeyManagementException the key management exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyStoreException the key store exception
	 */
	public HttpSendPostMultipart(String __requestURL, Map<String, String> __headers,
	        Map<String, String> __paramsText, Map<String, File> __paramsFile,
	        HttpResponseListener<?> __listener, int __timeout) throws IOException,
	        KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		super(__requestURL, __headers, __paramsText, __listener, __timeout);
		this.filePart = __paramsFile;
		this.httpRequest = new HttpPost(this.requestURL);
		this.setHeaders();
		this.setParameters();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logia.utility.httpclient.HttpUtility#setParameters()
	 */
	@Override
	protected void setParameters() {
		if (this.params == null) {
			return;
		}
		MultipartEntityBuilder _multipartBuilder = MultipartEntityBuilder.create();
		for (Entry<String, String> _stringParam : this.params.entrySet()) {
			_multipartBuilder.addPart(_stringParam.getKey(),
			        new StringBody(_stringParam.getValue(), ContentType.TEXT_PLAIN));
		}
		for (Entry<String, File> _fileParam : this.filePart.entrySet()) {
			_multipartBuilder.addPart(_fileParam.getKey(), new FileBody(_fileParam.getValue(),
			        ContentType.DEFAULT_BINARY, _fileParam.getValue().getName()));
		}
		((HttpEntityEnclosingRequest) this.httpRequest).setEntity(_multipartBuilder.build());
	}

}
