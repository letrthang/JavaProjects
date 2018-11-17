package com.lecompany.ui.utils.converters;

import com.vaadin.flow.templatemodel.ModelEncoder;
import com.lecompany.ui.dataproviders.DataProviderUtil;
import com.lecompany.ui.utils.FormattingUtils;

public class CurrencyFormatter implements ModelEncoder<Integer, String> {

	@Override
	public String encode(Integer modelValue) {
		return DataProviderUtil.convertIfNotNull(modelValue, FormattingUtils::formatAsCurrency);
	}

	@Override
	public Integer decode(String presentationValue) {
		throw new UnsupportedOperationException();
	}
}
