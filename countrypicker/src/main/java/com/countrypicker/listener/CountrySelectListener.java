package com.countrypicker.listener;

import com.countrypicker.bean.CountryData;

@FunctionalInterface
public interface CountrySelectListener {
    void onCountrySelect(CountryData countryData);
}
