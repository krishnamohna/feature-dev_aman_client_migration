package com.cardio.physician.ui.common.customviews.questions.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u001c\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/cardio/physician/ui/common/customviews/questions/base/QuestionView;", "", "isQuestionAnswered", "", "registerOnAnswerChange", "", "callback", "Lkotlin/Function1;", "app_qaDebug"})
public abstract interface QuestionView {
    
    public abstract boolean isQuestionAnswered();
    
    public abstract void registerOnAnswerChange(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> callback);
}