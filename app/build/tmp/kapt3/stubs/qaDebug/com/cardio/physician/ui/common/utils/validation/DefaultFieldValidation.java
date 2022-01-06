package com.cardio.physician.ui.common.utils.validation;

import android.content.Context;
import com.cardio.physician.R;
import com.cardio.physician.domain.common.model.validation.ValidationModelV2;
import com.cardio.physician.network.Status;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J \u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0016H\u0016J\u0010\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0016H\u0016J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0016H\u0016J\u0010\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u0016H\u0016J\u0010\u0010&\u001a\u00020\u00112\u0006\u0010\'\u001a\u00020\u0016H\u0016J\u0010\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u0016H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006*"}, d2 = {"Lcom/cardio/physician/ui/common/utils/validation/DefaultFieldValidation;", "Lcom/cardio/physician/ui/common/utils/validation/Validation;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "validations", "", "Lcom/cardio/physician/domain/common/model/validation/ValidationModelV2;", "getValidations", "()Ljava/util/List;", "setValidations", "(Ljava/util/List;)V", "build", "", "init", "", "queueValidationRequest", "error", "Lcom/cardio/physician/network/Status;", "msg", "", "fieldType", "Lcom/cardio/physician/ui/common/utils/validation/FieldType;", "validateAge", "age", "validateAilment", "ailmentPosition", "", "validateBottomBp", "bottomBp", "validateFirstName", "firstName", "validateHeartRate", "heartRate", "validateLastname", "lastName", "validateTopBp", "value", "validateWeight", "weight", "app_qaDebug"})
public final class DefaultFieldValidation implements com.cardio.physician.ui.common.utils.validation.Validation {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2> validations;
    
    public DefaultFieldValidation(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2> getValidations() {
        return null;
    }
    
    public final void setValidations(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2> p0) {
    }
    
    private final void queueValidationRequest(com.cardio.physician.network.Status error, java.lang.String msg, com.cardio.physician.ui.common.utils.validation.FieldType fieldType) {
    }
    
    @java.lang.Override()
    public void init() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2> build() {
        return null;
    }
    
    @java.lang.Override()
    public void validateAilment(int ailmentPosition) {
    }
    
    @java.lang.Override()
    public void validateFirstName(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName) {
    }
    
    @java.lang.Override()
    public void validateLastname(@org.jetbrains.annotations.NotNull()
    java.lang.String lastName) {
    }
    
    @java.lang.Override()
    public void validateAge(@org.jetbrains.annotations.NotNull()
    java.lang.String age) {
    }
    
    @java.lang.Override()
    public void validateWeight(@org.jetbrains.annotations.NotNull()
    java.lang.String weight) {
    }
    
    @java.lang.Override()
    public void validateHeartRate(@org.jetbrains.annotations.NotNull()
    java.lang.String heartRate) {
    }
    
    @java.lang.Override()
    public void validateTopBp(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void validateBottomBp(@org.jetbrains.annotations.NotNull()
    java.lang.String bottomBp) {
    }
}