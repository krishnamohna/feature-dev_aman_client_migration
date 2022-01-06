package com.cardio.physician.ui.common.utils;

import android.net.Uri;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import java.util.regex.Pattern;

@kotlin.Suppress(names = {"unused"})
@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000T\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001a\u0010\u000e\u001a\u00020\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001\u001a\u0006\u0010\u0011\u001a\u00020\u0001\u001a\u001a\u0010\u0012\u001a\u00020\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001\u001a(\u0010\u0013\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0017\u001a\u0016\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u000e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e\u001a\u000e\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001\u001a\u000e\u0010 \u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e\u001a\u000e\u0010!\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e\u001a\u000e\u0010\"\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e\u001a\u000e\u0010#\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e\u001a\u000e\u0010$\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e\u001a\u000e\u0010%\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e\u001a\u000e\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020)\u001a\u0018\u0010*\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010+\u001a\u00020,\u001a\u000e\u0010-\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e\u001a\n\u0010.\u001a\u00020/*\u00020\u0001\u001a\u0014\u00100\u001a\u00020\u0001*\u00020\u00012\b\u00101\u001a\u0004\u0018\u00010\u0001\u001a\n\u00102\u001a\u00020\u0001*\u00020\u0001\u001a\n\u00103\u001a\u00020\'*\u00020)\u001a\u0012\u00104\u001a\u00020\'*\u00020)2\u0006\u00105\u001a\u00020,\u001a\u001e\u00106\u001a\u00020\'*\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\'09\u001a\n\u0010:\u001a\u00020\u0014*\u00020\u0001\u001a\n\u0010;\u001a\u00020\u0014*\u00020\u0001\u001a\n\u0010<\u001a\u00020\u0014*\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"MOBILE_NUMBER", "", "MOBILE_NUMBER_PATTERN", "Ljava/util/regex/Pattern;", "NUMERIC_PATTERN", "NUMERIC_VALUE", "PASSWORD", "PASSWORD_PATTERN", "USERID", "USERID_PATTERN", "USERNAME", "USERNAME_PATTERN", "WEBURL_PATTERN", "WEBURL_REGX", "getDisplayName", "firstName", "lastname", "getPatientUid", "getSearchName", "isEqual", "", "T", "first", "", "second", "isMatched", "password", "confirmPassword", "isNumericValue", "target", "", "isTextEmpty", "isValidEmail", "isValidMobileNumber", "isValidPassword", "isValidUserId", "isValidUserName", "isValidWebUrl", "preventSpaceOnEditText", "", "editText", "Landroid/widget/EditText;", "setMaxLength", "length", "", "validPhoneLength", "convertIntoUri", "Landroid/net/Uri;", "convertMetricWeightToPound", "weightUnit", "formatStringToAddThousandsCharacter", "ignoreFirstWhiteSpace", "limitLength", "maxLength", "setDoubleClickListener", "Landroid/view/View;", "onSafeClick", "Lkotlin/Function1;", "validPasswordLength", "validUserIdLength", "validUserNameLength", "app_qaDebug"})
public final class AppUtilsKt {
    private static final java.lang.String USERNAME = "^[a-zA-Z]+[a-zA-Z ]{2,34}";
    private static final java.lang.String USERID = "^(?=.*[a-zA-Z])[A-Z0-9a-z!@$%*#?&_^+.=-]{2,25}";
    private static final java.lang.String MOBILE_NUMBER = "^[0-9]{8,12}";
    private static final java.lang.String NUMERIC_VALUE = "^[0-9]{1,12}";
    private static final java.lang.String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Z0-9a-z!@$%*#?&_^+.=-]{8,20}$";
    private static final java.lang.String WEBURL_REGX = "((https|http)://)((\\w|-)+)(([.]|[/])((\\w|-)+))+";
    private static final java.util.regex.Pattern USERNAME_PATTERN = null;
    private static final java.util.regex.Pattern USERID_PATTERN = null;
    private static final java.util.regex.Pattern MOBILE_NUMBER_PATTERN = null;
    private static final java.util.regex.Pattern NUMERIC_PATTERN = null;
    private static final java.util.regex.Pattern WEBURL_PATTERN = null;
    private static final java.util.regex.Pattern PASSWORD_PATTERN = null;
    
    public static final boolean isValidUserName(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence target) {
        return false;
    }
    
    public static final boolean validUserNameLength(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$validUserNameLength) {
        return false;
    }
    
    public static final boolean isValidUserId(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence target) {
        return false;
    }
    
    public static final boolean validUserIdLength(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$validUserIdLength) {
        return false;
    }
    
    public static final boolean isValidPassword(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence target) {
        return false;
    }
    
    public static final boolean isMatched(@org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String confirmPassword) {
        return false;
    }
    
    public static final boolean isValidEmail(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence target) {
        return false;
    }
    
    public static final boolean validPhoneLength(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence target) {
        return false;
    }
    
    public static final boolean isValidMobileNumber(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence target) {
        return false;
    }
    
    public static final boolean isNumericValue(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence target) {
        return false;
    }
    
    public static final boolean validPasswordLength(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$validPasswordLength) {
        return false;
    }
    
    public static final boolean isValidWebUrl(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence target) {
        return false;
    }
    
    public static final void setMaxLength(@org.jetbrains.annotations.Nullable()
    android.widget.EditText editText, int length) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String isTextEmpty(@org.jetbrains.annotations.NotNull()
    java.lang.String target) {
        return null;
    }
    
    public static final <T extends java.lang.Object>boolean isEqual(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> first, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> second) {
        return false;
    }
    
    public static final void preventSpaceOnEditText(@org.jetbrains.annotations.NotNull()
    android.widget.EditText editText) {
    }
    
    public static final void ignoreFirstWhiteSpace(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$ignoreFirstWhiteSpace) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatStringToAddThousandsCharacter(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$formatStringToAddThousandsCharacter) {
        return null;
    }
    
    public static final void limitLength(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$limitLength, int maxLength) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final android.net.Uri convertIntoUri(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$convertIntoUri) {
        return null;
    }
    
    public static final void setDoubleClickListener(@org.jetbrains.annotations.NotNull()
    android.view.View $this$setDoubleClickListener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.view.View, kotlin.Unit> onSafeClick) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String convertMetricWeightToPound(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$convertMetricWeightToPound, @org.jetbrains.annotations.Nullable()
    java.lang.String weightUnit) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getSearchName(@org.jetbrains.annotations.Nullable()
    java.lang.String firstName, @org.jetbrains.annotations.Nullable()
    java.lang.String lastname) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getDisplayName(@org.jetbrains.annotations.Nullable()
    java.lang.String firstName, @org.jetbrains.annotations.Nullable()
    java.lang.String lastname) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getPatientUid() {
        return null;
    }
}