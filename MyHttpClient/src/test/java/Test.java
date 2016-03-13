import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import logia.httpclient.HttpSendGet;
import logia.httpclient.HttpSendPost;
import logia.httpclient.response.impl.DefaultHttpResponseListener;

public final class Test {

	public static void main(String[] args) throws IOException, UnsupportedOperationException, TimeoutException, KeyManagementException,
	        NoSuchAlgorithmException, KeyStoreException {
		DefaultHttpResponseListener _listener = new DefaultHttpResponseListener();
		HttpSendGet _request = new HttpSendGet("http://localhost", null, null, _listener);
		_request.execute();
		System.out.println(_listener.getResponse().getResponseContent());

		_listener = new DefaultHttpResponseListener();
		HttpSendPost _post = new HttpSendPost("http://localhost", null, null, _listener);
		_post.execute();
		System.out.println(_listener.getResponse().getResponseContent());
	}

}
