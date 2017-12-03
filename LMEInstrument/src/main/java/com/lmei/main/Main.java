package com.lmei.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lmei.model.LMEInstrument;
import com.lmei.model.PrimeInstrument;
import com.lmei.presenter.LMEInstrumentPresenter;
import com.lmei.presenter.PrimeInstrumentPresenter;
import com.lmei.rule.LMERule;
import com.lmei.rule.RuleForField;
import com.lmei.view.LMEView;
import com.lmei.view.PrimeView;

public class Main {
	/** logger. */
	private static final Logger LOG = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		LMEView lmeView = new LMEView();
		LMEInstrumentPresenter lmeInstrumentPresenter = new LMEInstrumentPresenter(lmeView);

		PrimeView primeView = new PrimeView();
		PrimeInstrumentPresenter primeInstrumentPresenter = new PrimeInstrumentPresenter(primeView);

		LOG.debug("hello world!");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.println("Enter your instrument source: 1 = LME, 2 = Prime. 0 = Quit program");
				String instrumentSource = br.readLine();
				if (instrumentSource.equals("1")) {
					// For testing, we create sample data for LME instrument and LME rule
					// 1. LME instrument
					LMEInstrument lmeInstrument = new LMEInstrument();
					lmeInstrument.setId(1);
					lmeInstrument.setLastTradingDate("15-03-2018");
					lmeInstrument.setDeliveryDate("17-03-2018");
					lmeInstrument.setMarket("PB");
					lmeInstrument.setLabel("Lead 13 March 2018");
					// 2. LME Rule
					LMERule lmeRule = new LMERule();
					lmeRule.ruleForLastTradingDateField(RuleForField.USED);
					lmeRule.ruleForDeliveryDateField(RuleForField.USED);
					lmeRule.ruleForMarketField(RuleForField.USED);
					lmeRule.ruleForLabelField(RuleForField.USED);
					lmeRule.ruleForTradableField(RuleForField.USED);

					// set instrument to presenter
					lmeInstrumentPresenter.setInstrumentsQueue(lmeInstrument);
					// set rule to presenter
					lmeInstrumentPresenter.setRule(lmeRule);
				} else if (instrumentSource.equals("2")) {

				} else if (instrumentSource.equals("0")) {
					// to quit program
					LMEInstrument lmePoison = new LMEInstrument();
					PrimeInstrument primePoison = new PrimeInstrument();

					lmePoison.setId(-1);
					primePoison.setId(-1);

					lmeInstrumentPresenter.setInstrumentsQueue(lmePoison);
					lmeInstrumentPresenter.addInstrumentToPresenterQueue(lmePoison);
					primeInstrumentPresenter.setInstrumentsQueue(primePoison);
					primeInstrumentPresenter.addInstrumentToPresenterQueue(primePoison);

					LOG.debug("Quit program.....");
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
