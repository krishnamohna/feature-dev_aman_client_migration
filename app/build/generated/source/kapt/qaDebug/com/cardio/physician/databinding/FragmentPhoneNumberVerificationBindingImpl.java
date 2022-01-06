package com.cardio.physician.databinding;
import com.cardio.physician.R;
import com.cardio.physician.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentPhoneNumberVerificationBindingImpl extends FragmentPhoneNumberVerificationBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(16);
        sIncludes.setIncludes(0, 
            new String[] {"guideline_left", "guideline_right"},
            new int[] {2, 3},
            new int[] {com.cardio.physician.R.layout.guideline_left,
                com.cardio.physician.R.layout.guideline_right});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.headerView, 1);
        sViewsWithIds.put(R.id.titlePhoneVerification, 4);
        sViewsWithIds.put(R.id.txtOtpDescription, 5);
        sViewsWithIds.put(R.id.txtPhoneNumber, 6);
        sViewsWithIds.put(R.id.otpEdtText1, 7);
        sViewsWithIds.put(R.id.otpEdtText2, 8);
        sViewsWithIds.put(R.id.otpEdtText3, 9);
        sViewsWithIds.put(R.id.otpEdtText4, 10);
        sViewsWithIds.put(R.id.otpEdtText5, 11);
        sViewsWithIds.put(R.id.otpEdtText6, 12);
        sViewsWithIds.put(R.id.txtOtpTimer, 13);
        sViewsWithIds.put(R.id.txtResendOtp, 14);
        sViewsWithIds.put(R.id.btnConfirm, 15);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @Nullable
    private final com.cardio.physician.databinding.GuidelineLeftBinding mboundView01;
    @Nullable
    private final com.cardio.physician.databinding.GuidelineRightBinding mboundView02;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentPhoneNumberVerificationBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }
    private FragmentPhoneNumberVerificationBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatButton) bindings[15]
            , (bindings[1] != null) ? com.cardio.physician.databinding.ToolbarBinding.bind((android.view.View) bindings[1]) : null
            , (androidx.appcompat.widget.AppCompatEditText) bindings[7]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[8]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[9]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[10]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[11]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[12]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[4]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[5]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[13]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[6]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[14]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView01 = (com.cardio.physician.databinding.GuidelineLeftBinding) bindings[2];
        setContainedBinding(this.mboundView01);
        this.mboundView02 = (com.cardio.physician.databinding.GuidelineRightBinding) bindings[3];
        setContainedBinding(this.mboundView02);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        mboundView01.invalidateAll();
        mboundView02.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView01.hasPendingBindings()) {
            return true;
        }
        if (mboundView02.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        mboundView01.setLifecycleOwner(lifecycleOwner);
        mboundView02.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
        executeBindingsOn(mboundView01);
        executeBindingsOn(mboundView02);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}