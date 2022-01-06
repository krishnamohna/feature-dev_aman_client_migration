package com.cardio.physician.ui.common.utils.inputfilter;

import android.text.InputFilter;
import android.text.Spanned;
import java.lang.String;
import java.util.regex.Pattern;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J:\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/cardio/physician/ui/common/utils/inputfilter/DecimalDigitsInputFilter;", "Landroid/text/InputFilter;", "digitsBeforeZero", "", "digitsAfterZero", "(II)V", "mPattern", "Ljava/util/regex/Pattern;", "filter", "", "source", "start", "end", "dest", "Landroid/text/Spanned;", "dstart", "dend", "handleDotAfterLimit", "", "app_qaDebug"})
public final class DecimalDigitsInputFilter implements android.text.InputFilter {
    private final int digitsBeforeZero = 0;
    private final int digitsAfterZero = 0;
    private java.util.regex.Pattern mPattern;
    
    public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.CharSequence filter(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence source, int start, int end, @org.jetbrains.annotations.NotNull()
    android.text.Spanned dest, int dstart, int dend) {
        return null;
    }
    
    private final boolean handleDotAfterLimit(android.text.Spanned dest, java.lang.CharSequence source) {
        return false;
    }
}