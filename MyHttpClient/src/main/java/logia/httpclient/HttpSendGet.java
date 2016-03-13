package logia.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;

import logia.httpclient.response.listener.HttpResponseListener;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;

/**
 * The Class HttpSendGet.
 * 
 * @author Paul Mai
 */
public class HttpSendGet extends HttpUtility {

	/** The request params. */
	private StringBuffer requestParams = new StringBuffer();

	/**
	 * Instantiates a new http send GET method.
	 *
	 * @param __host the host
	 * @param __requestURL the request url
	 * @param __headers the headers
	 * @param __parameters the params
	 * @param __listener the __listener
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KeyManagementException the key management exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyStoreException the key store exception
	 */
	public HttpSendGet(HttpHost __host, String __requestURL, Map<String, String> __headers, Map<String, String> __parameters,
	        HttpResponseListener<?> __listener) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		super(__host, __requestURL, __headers, __parameters, __listener);
		this.setParameters();
		this.httpRequest = new HttpGet(this.requestURL);
		this.setHeaders();
		if (this.requestParams.length() > 0) {
			this.requestURL = this.requestURL + "?" + this.requestParams.toString();
		}

	}

	/**
	 * Instantiates a new http send GET method.
	 *
	 * @param __requestURL the __request url
	 * @param __headers the __headers
	 * @param __parameters the __parameters
	 * @param __listener the __listener
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KeyManagementException the key management exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyStoreException the key store exception
	 */
	public HttpSendGet(String __requestURL, Map<String, String> __headers, Map<String, String> __parameters, HttpResponseListener<?> __listener)
	        throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		super(__requestURL, __headers, __parameters, __listener);
		this.setParameters();
		if (this.requestParams.length() > 0) {
			this.requestURL = this.requestURL + "?" + this.requestParams.toString();
		}
		this.httpRequest = new HttpGet(this.requestURL);
		this.setHeaders();
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
		for (Entry<String, String> _param : this.params.entrySet()) {
			String _key = _param.getKey();
			String _value = _param.getValue();
			try {
				this.requestParams.append(URLEncoder.encode(_key, "UTF-8"));
				this.requestParams.append("=").append(URLEncoder.encode(_value, "UTF-8"));
			}
			catch (UnsupportedEncodingException e) {
				this.requestParams.append("=").append(_value);
			}
			this.requestParams.append("&");
		}
	}
}
