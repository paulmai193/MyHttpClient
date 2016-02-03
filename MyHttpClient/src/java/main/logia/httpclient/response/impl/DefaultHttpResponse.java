package logia.httpclient.response.impl;

import java.io.IOException;
import java.io.InputStream;

import logia.httpclient.HttpUtility;
import logia.httpclient.response.listener.HttpResponseListener;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;

/**
 * The Class DefaultHttpResponse.
 *
 * @author Paul Mai
 */

public class DefaultHttpResponse implements HttpResponseListener<DefaultResponseObject> {

	private DefaultResponseObject response;

	/*
	 * (non-Javadoc)
	 * 
	 * @see logia.httpclient.response.HttpResponseListener#onResponse(logia.httpclient.HttpUtility)
	 */
	@Override
	public void onResponse(HttpUtility __httpUtility) throws UnsupportedOperationException, IOException {
		this.response.setResponseCode(__httpUtility.getHttpResponse().getStatusLine().getStatusCode());
		try (InputStream _inputStream = __httpUtility.getHttpResponse().getEntity().getContent();) {
			this.response.setResponseContent(IOUtils.toString(_inputStream, Charsets.UTF_8));
		}
		catch (UnsupportedOperationException | IOException e) {
			throw e;
		}
	}

	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public DefaultResponseObject getResponse() {
		return this.response;
	}

}
