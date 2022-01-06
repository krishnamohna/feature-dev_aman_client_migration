package com.cardio.physician.ui.common.utils;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import com.cardio.physician.domain.fitness.model.DateModel;
import com.google.android.gms.fitness.data.DataPoint;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u000e\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b\u001a&\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u001a\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e\u001a\u0006\u0010\u0014\u001a\u00020\u0001\u001a\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0013\u001a\u00020\u0016\u001a\u000e\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\b\u001a.\u0010\u0019\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u001a\u001c\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00162\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f\u001a\u0010\u0010!\u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001\u001a\u0006\u0010\"\u001a\u00020#\u001a\u0006\u0010$\u001a\u00020#\u001a\u0018\u0010%\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\'\u001a\u00020\u000e\u001a\u0010\u0010(\u001a\u0004\u0018\u00010\u00012\u0006\u0010)\u001a\u00020\u0016\u001a\u0012\u0010*\u001a\u0004\u0018\u00010\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001c\u0010+\u001a\u00020\u000e*\u00020\u00012\u0006\u0010,\u001a\u00020\u00012\b\b\u0002\u0010-\u001a\u00020.\u001a\n\u0010/\u001a\u00020\u0001*\u000200\u001a\n\u00101\u001a\u00020\u0016*\u00020\u0001\u001a\n\u00102\u001a\u00020\u0001*\u000200\u00a8\u00063"}, d2 = {"formatDate", "", "inputFormat", "inputDate", "formatDateToGraph", "dateInput", "formatTimeStampToTimePassed", "timeStamp", "", "getBirthDatePicker", "Landroid/app/DatePickerDialog;", "context", "Landroid/content/Context;", "mDate", "Ljava/util/Date;", "callback", "Landroid/app/DatePickerDialog$OnDateSetListener;", "getCalendar", "Ljava/util/Calendar;", "date", "getCurrentDate", "getDate", "", "getDateFromTimeMills", "timeMills", "getDatePicker", "minDate", "getDatesOfLastDays", "", "days", "listDates", "", "Lcom/cardio/physician/domain/fitness/model/DateModel;", "getDaysDifference", "getDefaultDateFormatter", "Ljava/text/SimpleDateFormat;", "getDefaultTimeFormatter", "getDiffYears", "first", "last", "getMonthNumber", "month", "getStringFromDate", "datePickerStringToDate", "dateFormat", "timeZone", "Ljava/util/TimeZone;", "getEndTimeString", "Lcom/google/android/gms/fitness/data/DataPoint;", "getNoYearsFromDate", "getStartTimeString", "app_qaDebug"})
public final class DateUtilsKt {
    
    @org.jetbrains.annotations.Nullable()
    public static final android.app.DatePickerDialog getDatePicker(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.util.Date mDate, @org.jetbrains.annotations.NotNull()
    java.util.Date minDate, @org.jetbrains.annotations.Nullable()
    android.app.DatePickerDialog.OnDateSetListener callback) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final android.app.DatePickerDialog getBirthDatePicker(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.util.Date mDate, @org.jetbrains.annotations.Nullable()
    android.app.DatePickerDialog.OnDateSetListener callback) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getMonthNumber(int month) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getDate(int date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.Date datePickerStringToDate(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$datePickerStringToDate, @org.jetbrains.annotations.NotNull()
    java.lang.String dateFormat, @org.jetbrains.annotations.NotNull()
    java.util.TimeZone timeZone) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getStringFromDate(@org.jetbrains.annotations.Nullable()
    java.util.Date mDate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getDateFromTimeMills(long timeMills) {
        return null;
    }
    
    public static final int getNoYearsFromDate(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$getNoYearsFromDate) {
        return 0;
    }
    
    public static final int getDiffYears(@org.jetbrains.annotations.Nullable()
    java.util.Date first, @org.jetbrains.annotations.NotNull()
    java.util.Date last) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.Calendar getCalendar(@org.jetbrains.annotations.Nullable()
    java.util.Date date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getCurrentDate() {
        return null;
    }
    
    public static final int getDaysDifference(@org.jetbrains.annotations.Nullable()
    java.lang.String date) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.text.SimpleDateFormat getDefaultDateFormatter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.text.SimpleDateFormat getDefaultTimeFormatter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getStartTimeString(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.fitness.data.DataPoint $this$getStartTimeString) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getEndTimeString(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.fitness.data.DataPoint $this$getEndTimeString) {
        return null;
    }
    
    public static final void getDatesOfLastDays(int days, @org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.fitness.model.DateModel> listDates) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String formatDate(@org.jetbrains.annotations.NotNull()
    java.lang.String inputFormat, @org.jetbrains.annotations.NotNull()
    java.lang.String inputDate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDateToGraph(@org.jetbrains.annotations.NotNull()
    java.lang.String dateInput) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatTimeStampToTimePassed(long timeStamp) {
        return null;
    }
}