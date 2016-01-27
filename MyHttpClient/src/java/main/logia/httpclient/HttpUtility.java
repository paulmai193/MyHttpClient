package logia.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeoutException;

import logia.httpclient.response.HttpResponseListener;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

/**
 * The Class HttpUtility.
 * 
 * @author Paul Mai
 */
public abstract class HttpUtility {

	/** The Constant HEADER_CONTENT_TYPE. */
	public static final String              HEADER_CONTENT_TYPE = "Content-Type";

	/** The Constant HEADER_COOKIE. */
	public static final String              HEADER_COOKIE       = "Cookie";

	/** The Constant HEADER_USER_AGENT. */
	public static final String              HEADER_USER_AGENT   = "User-Agent";

	/** The cookie store. */
	private CookieStore                     cookieStore;

	/** The headers. */
	protected Map<String, String>           headers;

	/** The http client. */
	protected HttpClient                    httpClient;

	/** The http context. */
	protected HttpClientContext             httpContext;

	/** The http host. */
	protected HttpHost                      httpHost;

	/** The http request. */
	protected HttpRequest                   httpRequest;

	/** The http response. */
	protected HttpResponse                  httpResponse;

	/** The logger. */
	protected static final Logger           LOGGER              = Logger.getLogger(HttpUtility.class);

	/** The params. */
	protected Map<String, String>           params;

	/** The request url. */
	protected String                        requestURL;

	/** The response listener. */
	protected final HttpResponseListener<?> responseListener;

	/**
	 * Instantiates a new http utility.
	 *
	 * @param __httpHost the host
	 * @param __requestURL the request url
	 * @param __headers the headers
	 * @param _parameters the params
	 * @param __listener the __listener
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public HttpUtility(HttpHost __httpHost, String __requestURL, Map<String, String> __headers, Map<String, String> _parameters,
			HttpResponseListener<?> __listener) throws IOException {
		this.httpHost = __httpHost;
		this.requestURL = __requestURL;
		this.headers = __headers;
		this.params = _parameters;

		this.httpContext = HttpClientContext.create();
		this.httpClient = HttpClientBuilder.create().build();

		this.responseListener = __listener;
	}

	/**
	 * Instantiates a new http utility.
	 *
	 * @param __requestURL the request url
	 * @param __headers the headers
	 * @param __parameters the params
	 * @param __listener the __listener
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public HttpUtility(String __requestURL, Map<String, String> __headers, Map<String, String> __parameters, HttpResponseListener __listener)
			throws IOException {
		this.requestURL = __requestURL;
		this.headers = __headers;
		this.params = __parameters;

		this.httpContext = HttpClientContext.create();
		this.httpClient = HttpClientBuilder.create().build();

		this.responseListener = __listener;
	}

	/**
	 * Execute the http request.
	 *
	 * @return the response code
	 * @throws UnsupportedOperationException the unsupported operation exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TimeoutException the timeout exception
	 */
	public void execute() throws UnsupportedOperationException, IOException, TimeoutException {
		if (this.httpHost != null) {
			this.httpResponse = this.httpClient.execute(this.httpHost, this.httpRequest, this.httpContext);
		}
		else {
			this.httpResponse = this.httpClient.execute((HttpUriRequest) this.httpRequest, this.httpContext);
		}
		this.cookieStore = this.httpContext.getCookieStore();

		this.responseListener.onResponse(this);
	}

	/**
	 * Gets the http response.
	 *
	 * @return the httpResponse
	 */
	public HttpResponse getHttpResponse() {
		return this.httpResponse;
	}

	/**
	 * Gets the cookies.
	 *
	 * @return the cookies
	 */
	public List<Cookie> getListCookies() {
		return this.cookieStore.getCookies();
	}

	/**
	 * Gets the response content.
	 *
	 * @return the response content
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Deprecated
	public String getResponseContent() throws IOException {
		InputStream _inputStream = null;
		if (this.httpClient != null) {
			_inputStream = this.httpResponse.getEntity().getContent();
		}
		else {
			throw new IOException("Connection is not established.");
		}
		String _responseContent = IOUtils.toString(_inputStream, Charsets.UTF_8);
		return _responseContent;
	}

	/**
	 * Sets the cookie.
	 *
	 * @param __cookie the new cookie
	 */
	public void setCookie(Cookie __cookie) {
		this.cookieStore.addCookie(__cookie);
		this.httpContext.setCookieStore(this.cookieStore);
	}

	/**
	 * Sets the cookies.
	 *
	 * @param __cookies the new cookies
	 */
	public void setCookies(List<Cookie> __cookies) {
		for (Cookie cookie : __cookies) {
			this.setCookie(cookie);
		}
	}

	/**
	 * Sets the http context.
	 *
	 * @param __httpContext the httpContext to set
	 */
	public void setHttpContext(HttpClientContext __httpContext) {
		this.httpContext = __httpContext;
	}

	/**
	 * Sets the headers.
	 */
	protected void setHeaders() {
		if (this.headers == null) {
			return;
		}
		for (Entry<String, String> _header : this.headers.entrySet()) {
			this.httpRequest.setHeader(_header.getKey(), _header.getValue());
		}
	}

	/**
	 * Sets the parameters, encode them using URLEncoder.
	 */
	protected abstract void setParameters();

}
