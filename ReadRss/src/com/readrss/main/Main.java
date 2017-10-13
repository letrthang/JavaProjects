package com.readrss.main;

import java.util.ArrayList;
import java.util.List;

import com.readrss.presenter.RssPresenter;
import com.readrss.view.RssView;

public class Main {

	private static RssPresenter rssPresenter;
	private static RssView rssView;

	public static void main(String[] args) {
		List<String> urlLst = new ArrayList<>();
		urlLst.add("http://tech.uzabase.com/entry/2017/10/11/130352");
		urlLst.add("http://tech.uzabase.com/entry/2017/09/26/191009-");
		
		rssView = new RssView();
		rssPresenter = new RssPresenter(rssView);
		
		rssPresenter.readAndDisplayRss(urlLst);
		rssPresenter.readAndStoreRss(urlLst);
	}

}
