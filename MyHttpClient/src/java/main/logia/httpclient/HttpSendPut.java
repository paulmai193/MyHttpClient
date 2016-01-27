package logia.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import logia.httpclient.response.HttpResponseListener;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;

/**
 * The Class HttpSendPut.
 * 
 * @author Paul Mai
 */
public class HttpSendPut extends HttpUtility {

	/**
	 * Instantiates a new http send put.
	 *
	 * @param __host the host
	 * @param __requestURL the request url
	 * @param __headers the headers
	 * @param __parameters the params
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public HttpSendPut(HttpHost __host, String __requestURL, Map<String, String> __headers, Map<String, String> __parameters,
			HttpResponseListener<?> __listener) throws IOException {
		super(__host, __requestURL, __headers, __parameters, __listener);
		this.httpRequest = new HttpPut(this.requestURL);
		this.setHeaders();
		this.setParameters();
	}

	/**
	 * Instantiates a new http send put.
	 *
	 * @param __requestURL the request url
	 * @param __headers the headers
	 * @param __parameters the params
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public HttpSendPut(String __requestURL, Map<String, String> __headers, Map<String, String> __parameters, HttpResponseListener<?> __listener)
			throws IOException {
		super(__requestURL, __headers, __parameters, __listener);
		this.httpRequest = new HttpPut(this.requestURL);
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
		List<NameValuePair> _urlParameters = new ArrayList<NameValuePair>();
		for (Entry<String, String> _param : this.params.entrySet()) {
			_urlParameters.add(new BasicNameValuePair(_param.getKey(), _param.getValue()));
		}
		try {
			((HttpEntityEnclosingRequest) this.httpRequest).setEntity(new UrlEncodedFormEntity(_urlParameters));
		}
		catch (UnsupportedEncodingException e) {
			HttpUtility.LOGGER.error(e);
		}
	}

}
