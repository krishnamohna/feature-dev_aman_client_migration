package com.cardio.physician.ui.common.utils.validation;

import com.cardio.physician.domain.common.model.validation.ValidationModelV2;
import com.cardio.physician.network.Status;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ,\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eJ,\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/cardio/physician/ui/common/utils/validation/Validater;", "", "validation", "Lcom/cardio/physician/ui/common/utils/validation/Validation;", "(Lcom/cardio/physician/ui/common/utils/validation/Validation;)V", "areAllFieldValidated", "", "validations", "", "Lcom/cardio/physician/domain/common/model/validation/ValidationModelV2;", "validateAlphaDiagnosisFirstStep", "ailmentPosition", "", "firstName", "", "lastName", "age", "validateDiagnosisFirstStep", "app_qaDebug"})
public final class Validater {
    private final com.cardio.physician.ui.common.utils.validation.Validation validation = null;
    
    public Validater(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.utils.validation.Validation validation) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2> validateDiagnosisFirstStep(int ailmentPosition, @org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName, @org.jetbrains.annotations.NotNull()
    java.lang.String age) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2> validateAlphaDiagnosisFirstStep(int ailmentPosition, @org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName, @org.jetbrains.annotations.NotNull()
    java.lang.String age) {
        return null;
    }
    
    public final boolean areAllFieldValidated(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2> validations) {
        return false;
    }
}