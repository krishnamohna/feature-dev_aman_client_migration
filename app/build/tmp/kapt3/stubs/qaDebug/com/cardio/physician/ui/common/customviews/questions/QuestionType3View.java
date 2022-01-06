package com.cardio.physician.ui.common.customviews.questions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import com.cardio.physician.R;
import com.cardio.physician.databinding.CompoundQuestionType1LayoutBinding;
import com.cardio.physician.domain.questionare.model.QuestionModel;
import com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\u0007H\u0002J\b\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0005H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u0005H\u0016J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0007H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/cardio/physician/ui/common/customviews/questions/QuestionType3View;", "Lcom/cardio/physician/ui/common/customviews/questions/base/BaseQuestionView;", "context", "Landroid/content/Context;", "question", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "isEnabled", "", "(Landroid/content/Context;Lcom/cardio/physician/domain/questionare/model/QuestionModel;Z)V", "binding", "Lcom/cardio/physician/databinding/CompoundQuestionType1LayoutBinding;", "hasGivenSecondaryAns", "isQuestionAnswered", "isQuestionValid", "questionModel", "setListeners", "", "setSelection", "setSelectionOfSecondaryQuestion", "setViewEnabled", "showQuestion", "showSecondaryOptions", "show", "app_qaDebug"})
public final class QuestionType3View extends com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView {
    private com.cardio.physician.databinding.CompoundQuestionType1LayoutBinding binding;
    
    public QuestionType3View(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel question) {
        super(null, null);
    }
    
    public QuestionType3View(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel question, boolean isEnabled) {
        super(null, null);
    }
    
    private final void setListeners() {
    }
    
    private final void showSecondaryOptions(boolean show) {
    }
    
    @java.lang.Override()
    public void showQuestion(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel questionModel) {
    }
    
    private final void setSelection() {
    }
    
    private final void setSelectionOfSecondaryQuestion() {
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
    
    private final boolean hasGivenSecondaryAns() {
        return false;
    }
}