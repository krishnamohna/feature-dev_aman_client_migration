package com.cardio.physician.ui.common.customviews.questions.base;

import android.content.Context;
import android.widget.FrameLayout;
import com.cardio.physician.domain.questionare.model.QuestionModel;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\r\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0006\u0010\u0012\u001a\u00020\u000bJ\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001c\u0010\u0014\u001a\u00020\u000b2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tH\u0016J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\nH&J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H&R*\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0018"}, d2 = {"Lcom/cardio/physician/ui/common/customviews/questions/base/BaseQuestionView;", "Landroid/widget/FrameLayout;", "Lcom/cardio/physician/ui/common/customviews/questions/base/QuestionView;", "context", "Landroid/content/Context;", "question", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "(Landroid/content/Context;Lcom/cardio/physician/domain/questionare/model/QuestionModel;)V", "callback", "Lkotlin/Function1;", "", "", "getCallback", "()Lkotlin/jvm/functions/Function1;", "setCallback", "(Lkotlin/jvm/functions/Function1;)V", "getQuestion", "()Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "answerChanged", "isQuestionValid", "registerOnAnswerChange", "setViewEnabled", "isEnabled", "showQuestion", "app_qaDebug"})
public abstract class BaseQuestionView extends android.widget.FrameLayout implements com.cardio.physician.ui.common.customviews.questions.base.QuestionView {
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.questionare.model.QuestionModel question = null;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> callback;
    
    public BaseQuestionView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel question) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.questionare.model.QuestionModel getQuestion() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getCallback() {
        return null;
    }
    
    public final void setCallback(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> p0) {
    }
    
    public abstract void showQuestion(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel question);
    
    public abstract boolean isQuestionValid(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.model.QuestionModel question);
    
    public abstract void setViewEnabled(boolean isEnabled);
    
    public final void answerChanged() {
    }
    
    @java.lang.Override()
    public void registerOnAnswerChange(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> callback) {
    }
}