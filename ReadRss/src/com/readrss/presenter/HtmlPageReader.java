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

			feedMessage.setFullContent(fullContent);
			// quick implement for uiid ^;^
			feedMessage.setUiid(uiid);

			// System.out.println(buffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return feedMessage;
	}

	public FeedMessage conversionContent(FeedMessage message, String token) {
		String newContent;

		// just remove token
		newContent = message.getFullContent().replaceAll(token, "");
		message.setFullContent(newContent);

		return message;
	}
}
