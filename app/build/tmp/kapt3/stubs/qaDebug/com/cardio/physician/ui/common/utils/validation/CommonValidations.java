package com.cardio.physician.ui.common.utils.validation;

import android.content.Context;
import com.cardio.physician.domain.common.model.validation.ValidationModelV2;
import com.cardio.physician.network.Status;
import com.cardio.physician.ui.common.utils.ENUM;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u00a8\u0006\u000f"}, d2 = {"Lcom/cardio/physician/ui/common/utils/validation/CommonValidations;", "", "()V", "emptyValidation", "Lcom/cardio/physician/domain/common/model/validation/ValidationModelV2;", "value", "", "fieldtype", "Lcom/cardio/physician/ui/common/utils/validation/FieldType;", "errorMsg", "", "context", "Landroid/content/Context;", "minThreeCharIfEnteredValidation", "minThreeCharValidation", "app_qaDebug"})
public final class CommonValidations {
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.common.utils.validation.CommonValidations INSTANCE = null;
    
    private CommonValidations() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.common.model.validation.ValidationModelV2 emptyValidation(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.utils.validation.FieldType fieldtype, int errorMsg, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.common.model.validation.ValidationModelV2 minThreeCharValidation(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.utils.validation.FieldType fieldtype, int errorMsg, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.common.model.validation.ValidationModelV2 minThreeCharIfEnteredValidation(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.utils.validation.FieldType fieldtype, int errorMsg, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}