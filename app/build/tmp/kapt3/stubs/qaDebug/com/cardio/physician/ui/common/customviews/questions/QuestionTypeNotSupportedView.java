package com.cardio.physician.ui.common.customviews.questions;

import android.content.Context;
import android.view.LayoutInflater;
import com.cardio.physician.databinding.CompoundQuestionTypeNotSupportedLayoutBinding;
import com.cardio.physician.domain.questionare.model.QuestionModel;
import com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0005H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/cardio/physician/ui/common/customviews/questions/QuestionTypeNotSupportedView;", "Lcom/cardio/physician/ui/common/customviews/questions/base/BaseQuestionView;", "context", "Landroid/content/Context;", "question", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "isEnabled", "", "(Landroid/content/Context;Lcom/cardio/physician/domain/questionare/model/QuestionModel;Z)V", "binding", "Lcom/cardio/physician/databinding/CompoundQuestionTypeNotSupportedLayoutBinding;", "isQuestionAnswered", "isQuestionValid", "setViewEnabled", "", "showQuestion", "questionModel", "app_qaDebug"})
public final class QuestionTypeNotSupportedView extends com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView {
    private com.cardio.physician.databinding.CompoundQuestionTypeNotSupportedLayoutBinding binding;
    
    public QuestionTypeNotSupportedView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel question) {
        super(null, null);
    }
    
    public QuestionTypeNotSupportedView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel question, boolean isEnabled) {
        super(null, null);
    }
    
    @java.lang.Override()
    public void showQuestion(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel questionModel) {
    }
    
    @java.lang.Override()
    public boolean isQuestionValid(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel question) {
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