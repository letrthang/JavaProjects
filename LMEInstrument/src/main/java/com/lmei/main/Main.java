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
import com.lmei.rule.PrimeRule;
import com.lmei.rule.RuleForField;
import com.lmei.view.LMEView;
import com.lmei.view.PrimeView;

public class Main {
	/** logger. */
	private static final Logger LOG = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			LMEView lmeView = new LMEView();
			LMEInstrumentPresenter lmeInstrumentPresenter = new LMEInstrumentPresenter(lmeView);

			PrimeView primeView = new PrimeView();
			PrimeInstrumentPresenter primeInstrumentPresenter = new PrimeInstrumentPresenter(primeView);

			LMEInstrument lmeInstrument = new LMEInstrument();
			LMERule lmeRule = new LMERule();
			
			PrimeInstrument primeInstrument = new PrimeInstrument();
			PrimeRule primeRule = new PrimeRule();

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter your instrument source: 1 = LME, 2 = Prime. 0 = Quit program");

			while (true) {

				String instrumentSource = br.readLine();
				if (instrumentSource.equals("1")) {
					// For testing, we create sample data for LME instrument and LME rule.
					// By right, all instruments and rules should store in a database for reference.

					// 1. LME instrument
					lmeInstrument.setId(1);
					lmeInstrument.setLastTradingDate("15-03-2018");
					lmeInstrument.setDeliveryDate("17-03-2018");
					lmeInstrument.setMarket("PB");
					lmeInstrument.setLabel("Lead 13 March 2018");

					// 2. LME Rule
					lmeRule.ruleForLastTradingDateField(RuleForField.USED);
					lmeRule.ruleForDeliveryDateField(RuleForField.USED);
					lmeRule.ruleForMarketField(RuleForField.USED);
					lmeRule.ruleForLabelField(RuleForField.USED);
					lmeRule.setTradableField(true);

					// set rule to presenter
					lmeInstrumentPresenter.setRule(lmeRule);
					// set instrument to presenter
					lmeInstrumentPresenter.setInstrumentsQueue(lmeInstrument);

				} else if (instrumentSource.equals("2")) {
					// for Prime

					primeInstrument.setId(2);
					primeInstrument.setLastTradingDate("14-03-2018");
					primeInstrument.setDeliveryDate("18-03-2018");
					primeInstrument.setMarket("LME_PB");
					primeInstrument.setLabel("Lead 13 March 2018");
					primeInstrument.setExchangeCode("PB_03_2018");
					primeInstrument.setTradable(false);

					primeRule.ruleForLastTradingDateField(RuleForField.REFERENCED);
					primeRule.ruleForDeliveryDateField(RuleForField.REFERENCED);
					primeRule.ruleForMarketField(RuleForField.USED);
					primeRule.ruleForLabelField(RuleForField.USED);
					primeRule.ruleForTradableField(false);
					primeRule.ruleForExchangeCodeField(RuleForField.UNUSED);
					// this instrument will get some fields from other instrument via an ID
					primeRule.setReferencedInstrumentId(lmeInstrument.getId());

					// set rule to presenter
					primeInstrumentPresenter.setRule(primeRule);
					// set instrument to presenter
					primeInstrumentPresenter.setInstrumentsQueue(primeInstrument);

				} else if (instrumentSource.equals("0")) {
					// to quit program by sending poison object to BlockingQueues to terminate
					// threads
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
