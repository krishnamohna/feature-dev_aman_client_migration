package com.countrypicker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.countrypicker.bean.CountryData;
import com.countrypicker.custom.CountryPickerView;
import com.countrypicker.listener.CountrySelectListener;

public class CountrySelectActivity extends AppCompatActivity {

    public static final String RESULT_COUNTRY_DATA = "result_country_data";
    public static final String EXTRA_SELECTED_COUNTRY = "extra_selected_country";

    CountryPickerView countryPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_select);

        countryPickerView = findViewById(R.id.countryPickerView);

        if (getIntent() != null && getIntent().getSerializableExtra(EXTRA_SELECTED_COUNTRY) != null)
            countryPickerView.setSelectedCountry((CountryData) getIntent().getSerializableExtra(EXTRA_SELECTED_COUNTRY));

        countryPickerView.setCountrySelectListener(new CountrySelectListener() {
            @Override
            public void onCountrySelect(CountryData countryData) {
                Intent intent = new Intent();
                intent.putExtra(RESULT_COUNTRY_DATA, countryData);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
