/**
 * 
 */
package com.readrss.presenter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.readrss.model.FeedMessage;

/**
 * @author Thang Le
 *
 */
public class HtmlPageReader {

	/**
	 * 
	 */
	public HtmlPageReader() {

	}

	/**
	 * Read content of a page as byte stream from url
	 * 
	 * @param sURL
	 * @return
	 */
	public FeedMessage readHtmlPage(String sURL) {
		StringBuffer buffer = null;
		URL url = null;
		FeedMessage feedMessage = new FeedMessage();
		String fullContent;

		try {
			url = new URL(sURL);
			InputStream is;
			is = url.openStream();

			int ptr = 0;
			buffer = new StringBuffer();
			while ((ptr = is.read()) != -1) {
				buffer.append((char) ptr);
			}

			fullContent = new String(buffer.toString());
			String uiid = sURL.replaceAll(":", "").replaceAll("/", "");

			// set message content
			feedMessage.setFullContent(fullContent);
			// set uiid. quick implement for uiid, reuse url as Id ^;^
			feedMessage.setUiid(uiid);

			// System.out.println(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return feedMessage;
	}

	/**
	 * Remove "token" in message
	 * 
	 * @param message
	 * @param token
	 * @return
	 */
	public FeedMessage conversionContent(FeedMessage message, String token) {
		String newContent;

		if (message == null || token == null)
			return message;

		// just remove token on the message
		newContent = message.getFullContent().replaceAll(token, "");
		message.setFullContent(newContent);

		return message;
	}
}
