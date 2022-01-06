package com.cardio.physician.ui.common.utils.validation;

import com.cardio.physician.domain.common.model.validation.ValidationModelV2;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\tH&J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\tH&J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\tH&J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\tH&J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\tH&J\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\tH&\u00a8\u0006\u0018"}, d2 = {"Lcom/cardio/physician/ui/common/utils/validation/Validation;", "", "build", "", "Lcom/cardio/physician/domain/common/model/validation/ValidationModelV2;", "init", "", "validateAge", "age", "", "validateAilment", "ailmentPosition", "", "validateBottomBp", "bottomBp", "validateFirstName", "firstName", "validateHeartRate", "heartRate", "validateLastname", "lastName", "validateTopBp", "validateWeight", "weight", "app_qaDebug"})
public abstract interface Validation {
    
    public abstract void validateFirstName(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2> build();
    
    public abstract void validateLastname(@org.jetbrains.annotations.NotNull()
    java.lang.String lastName);
    
    public abstract void validateAge(@org.jetbrains.annotations.NotNull()
    java.lang.String age);
    
    public abstract void validateWeight(@org.jetbrains.annotations.NotNull()
    java.lang.String weight);
    
    public abstract void validateHeartRate(@org.jetbrains.annotations.NotNull()
    java.lang.String heartRate);
    
    public abstract void validateTopBp(@org.jetbrains.annotations.NotNull()
    java.lang.String heartRate);
    
    public abstract void validateBottomBp(@org.jetbrains.annotations.NotNull()
    java.lang.String bottomBp);
    
    public abstract void init();
    
    public abstract void validateAilment(int ailmentPosition);
}