/**
 * 
 */
package com.readrss.presenter;

import java.util.List;

import com.readrss.dao.FeedMessageDAO;
import com.readrss.model.FeedMessage;

/**
 * @author Thang Le
 *
 */
public class RssPresenter {
	View view;

	public interface View {
		public void viewOriginalFeedMessage(String msg);

		public void viewConversionFeedMessage(String msg);

		public void viewNoticeMessage(String msg);
	}

	HtmlPageReader htmlPageReader = new HtmlPageReader();
	FeedMessageDAO feedMessageDAO = new FeedMessageDAO();

	/**
	 * 
	 */
	public RssPresenter(View view) {
		this.view = view;
	}

	/**
	 * Read and display message to console
	 * 
	 * @param urlLst
	 */
	public void readAndDisplayRss(List<String> urlLst) {
		FeedMessage feedMessage;

		if (urlLst == null || urlLst.size() == 0) {
			return;
		}

		try {
			for (String url : urlLst) {
				feedMessage = htmlPageReader.readHtmlPage(url);
				view.viewOriginalFeedMessage(feedMessage.getFullContent());
				//
				feedMessage = htmlPageReader.conversionContent(feedMessage, "NewsPicks");
				view.viewConversionFeedMessage(feedMessage.getFullContent());
			}
		} catch (Exception e) {
			view.viewNoticeMessage("something went wrong....");
		}
	}

	/**
	 * Read and save message to file.
	 * 
	 * @param urlLst
	 */
	public void readAndStoreRss(List<String> urlLst) {
		FeedMessage feedMessage;

		if (urlLst == null || urlLst.size() == 0) {
			return;
		}

		try {
			for (String url : urlLst) {
				feedMessage = htmlPageReader.readHtmlPage(url);
				feedMessage = htmlPageReader.conversionContent(feedMessage, "NewsPicks");
				feedMessageDAO.saveFeedMessage(feedMessage);
			}
		} catch (Exception e) {
			view.viewNoticeMessage("something went wrong....");
		}
	}
}
