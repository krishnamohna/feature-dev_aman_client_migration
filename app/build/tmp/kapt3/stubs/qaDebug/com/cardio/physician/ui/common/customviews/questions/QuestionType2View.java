package com.cardio.physician.ui.common.customviews.questions;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.LayoutInflater;
import com.cardio.physician.databinding.CompoundQuestionType2LayoutBinding;
import com.cardio.physician.domain.questionare.model.QuestionModel;
import com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView;
import com.cardio.physician.ui.common.utils.QuestionId;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u0007H\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\b\u0010\u0010\u001a\u00020\fH\u0002J\b\u0010\u0011\u001a\u00020\fH\u0002J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/cardio/physician/ui/common/customviews/questions/QuestionType2View;", "Lcom/cardio/physician/ui/common/customviews/questions/base/BaseQuestionView;", "context", "Landroid/content/Context;", "question", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "isEnabled", "", "(Landroid/content/Context;Lcom/cardio/physician/domain/questionare/model/QuestionModel;Z)V", "binding", "Lcom/cardio/physician/databinding/CompoundQuestionType2LayoutBinding;", "clearEdtField", "", "isQuestionAnswered", "isQuestionValid", "questionModel", "setListeners", "setSelection", "setViewEnabled", "showQuestion", "app_qaDebug"})
public final class QuestionType2View extends com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView {
    private final com.cardio.physician.databinding.CompoundQuestionType2LayoutBinding binding = null;
    
    public QuestionType2View(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel question) {
        super(null, null);
    }
    
    public QuestionType2View(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel question, boolean isEnabled) {
        super(null, null);
    }
    
    private final void setListeners() {
    }
    
    private final void clearEdtField() {
    }
    
    @java.lang.Override()
    public void showQuestion(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel questionModel) {
    }
    
    private final void setSelection() {
    }
    
    @java.lang.Override()
    public boolean isQuestionValid(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel questionModel) {
        return false;
    }
    
    @java.lang.Override()
    public void setViewEnabled(boolean isEnabled) {
    }
    
    @java.lang.Override()
    public boolean isQuestionAnswered() {
        return false;
    }
}