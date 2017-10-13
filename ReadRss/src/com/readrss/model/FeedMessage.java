/**
 * 
 */
package com.readrss.model;

/**
 * 
 * 
 * @author Thang Le
 *
 */
public class FeedMessage {

	String uiid;
	String url;
	String language;
	String copyright;
	String pubDate;
	// whole content of html page
	String fullContent;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the uiid
	 */
	public String getUiid() {
		return uiid;
	}

	/**
	 * @param uiid
	 *            the uiid to set
	 */
	public void setUiid(String uiid) {
		this.uiid = uiid;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright
	 *            the copyright to set
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	/**
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate
	 *            the pubDate to set
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the fullContent
	 */
	public String getFullContent() {
		return fullContent;
	}

	/**
	 * @param fullContent
	 *            the fullContent to set
	 */
	public void setFullContent(String fullContent) {
		this.fullContent = fullContent;
	}

}
