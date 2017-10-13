/**
 * 
 */
package com.readrss.view;

import com.readrss.presenter.RssPresenter.View;

/**
 * @author Thang Le
 *
 */
public class RssView implements View {

	/**
	 * 
	 */
	public RssView() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readrss.presenter.HtmlReaderPresenter.View#viewOriginalFeedMessage(
	 * java.lang.String)
	 */
	@Override
	public void viewOriginalFeedMessage(String msg) {
		System.out.println(msg);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readrss.presenter.HtmlReaderPresenter.View#viewConversionFeedMessage(
	 * java.lang.String)
	 */
	@Override
	public void viewConversionFeedMessage(String msg) {
		System.out.println(msg);

	}

	@Override
	public void viewNoticeMessage(String msg) {
		System.out.println(msg);
		
	}

}
