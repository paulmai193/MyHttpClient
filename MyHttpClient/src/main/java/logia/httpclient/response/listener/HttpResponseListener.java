package logia.httpclient.response.listener;

import java.io.IOException;

import logia.httpclient.HttpUtility;

/**
 * The listener interface for receiving httpResponse events. The class that is interested in processing a httpResponse event implements this
 * interface, and the object created with that class is registered with a component using the component's
 * <code>addHttpResponseListener<code> method. When
 * the httpResponse event occurs, that object's appropriate
 * method is invoked.
 *
 * @see HttpResponseEvent
 * @author Paul Mai
 * @param <T>
 */
public interface HttpResponseListener<T> {

	/**
	 * On response.
	 *
	 * @param __httpUtility the HTTP utility
	 * @throws UnsupportedOperationException the unsupported operation exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void onResponse(HttpUtility __httpUtility) throws UnsupportedOperationException, IOException;

}
