import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import logia.httpclient.HttpSendGet;
import logia.httpclient.response.impl.DefaultHttpResponseListener;

public final class Test {

	public static void main(String[] args) throws IOException, UnsupportedOperationException,
	        TimeoutException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		DefaultHttpResponseListener _listener = new DefaultHttpResponseListener();
		HttpSendGet _request = new HttpSendGet(
		        "http://localhost:8080/voohooomain/phone/sns/testtimeout", null, null, _listener,
		        0);
		_request.execute();
		System.out.println(_listener.getResponse().getResponseContent());

	}

}
