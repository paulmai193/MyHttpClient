package logia.httpclient.response.impl;

import java.io.IOException;
import java.io.InputStream;

import logia.httpclient.HttpUtility;
import logia.httpclient.response.HttpResponseListener;
import logia.utility.json.JsonUtil;
import logia.utility.json.annotaion.JsonKey;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;

/**
 * The Class DefaultHttpResponse.
 *
 * @author Paul Mai
 */
@logia.utility.json.annotaion.JsonObject
public class DefaultHttpResponse implements HttpResponseListener<JsonObject> {

	@JsonKey(key = "code")
	private int    responseCode;

	@JsonKey(key = "content")
	private String responseContent;

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return this.responseCode;
	}

	/**
	 * @return the responseContent
	 */
	public String getResponseContent() {
		return this.responseContent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logia.httpclient.response.HttpResponseListener#onResponse(logia.httpclient.HttpUtility)
	 */
	@Override
	public JsonObject onResponse(HttpUtility _httpUtility) throws UnsupportedOperationException, IOException {
		this.responseCode = _httpUtility.getHttpResponse().getStatusLine().getStatusCode();

		try (InputStream _inputStream = _httpUtility.getHttpResponse().getEntity().getContent();) {
			this.responseContent = IOUtils.toString(_inputStream, Charsets.UTF_8);
		}
		catch (UnsupportedOperationException | IOException e) {
			throw e;
		}

		return JsonUtil.toJsonObject(this);
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @param responseContent the responseContent to set
	 */
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

}
