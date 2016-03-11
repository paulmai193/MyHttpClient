package logia.httpclient.response.impl;

import logia.utility.json.annotaion.JsonKey;

@logia.utility.json.annotaion.JsonObject
public class DefaultResponseObject {

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

	/**
	 * @param __responseCode the responseCode to set
	 */
	public void setResponseCode(int __responseCode) {
		this.responseCode = __responseCode;
	}

	/**
	 * @param __responseContent the responseContent to set
	 */
	public void setResponseContent(String __responseContent) {
		this.responseContent = __responseContent;
	}
}
