package com.countrypicker.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.countrypicker.R;
import com.countrypicker.adapter.CountryAdapter;
import com.countrypicker.bean.CountryData;
import com.countrypicker.listener.CountrySelectListener;
import com.countrypicker.util.JsonHelper;

public class CountryPickerView extends LinearLayout {
    CountryAdapter countryAdapter;
    private boolean showSearch;
    private CountrySelectListener countrySelectListener;
    //private CountryData selectedCountry;

    public CountryPickerView(Context context) {
        this(context, null);
    }

    public CountryPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CountryPickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CountryPickerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CountryPickerView);
        showSearch = a.getBoolean(R.styleable.CountryPickerView_showSearch, true);
        a.recycle();
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.compound_country_select, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EditText editText = findViewById(R.id.editTextSearch);

        countryAdapter = new CountryAdapter(JsonHelper.parseJsonToCountryList(this.getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(countryAdapter);

        if (!showSearch) {
            editText.setVisibility(GONE);
        } else {
            editText.addTextChangedListener(new TextWatcher() {
                //region not used
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                //endregion
                @Override
                public void afterTextChanged(Editable s) {
                    String searchString = s.toString();
                    countryAdapter.setCountryDataList(JsonHelper.parseJsonToCountryList(CountryPickerView.this.getContext(), searchString));
                }
            });
        }

        countryAdapter.setCountrySelectListener(new CountrySelectListener() {
            @Override
            public void onCountrySelect(CountryData countryData) {
                if (countrySelectListener != null) {
                    countryAdapter.setSelectedCountry(countryData);
                    countrySelectListener.onCountrySelect(countryData);
                }
            }
        });
    }

    public void setCountrySelectListener(CountrySelectListener countrySelectListener) {
        this.countrySelectListener = countrySelectListener;
    }

    public CountryData getSelectedCountry() {
        return countryAdapter.getSelectedCountry();
    }

    public void setSelectedCountry(CountryData countryData) {
        countryAdapter.setSelectedCountry(countryData);
    }
}
