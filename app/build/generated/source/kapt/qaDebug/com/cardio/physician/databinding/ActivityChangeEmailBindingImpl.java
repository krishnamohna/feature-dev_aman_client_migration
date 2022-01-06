package com.cardio.physician.databinding;
import com.cardio.physician.R;
import com.cardio.physician.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityChangeEmailBindingImpl extends ActivityChangeEmailBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(25);
        sIncludes.setIncludes(1, 
            new String[] {"guideline_left", "guideline_right"},
            new int[] {3, 4},
            new int[] {com.cardio.physician.R.layout.guideline_left,
                com.cardio.physician.R.layout.guideline_right});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.headerView, 2);
        sViewsWithIds.put(R.id.txtEditEmailMsg, 5);
        sViewsWithIds.put(R.id.oldPasswordValContainer, 6);
        sViewsWithIds.put(R.id.txtOldPassword, 7);
        sViewsWithIds.put(R.id.oldPasswordContainer, 8);
        sViewsWithIds.put(R.id.edtOldPassword, 9);
        sViewsWithIds.put(R.id.imgOldPassword, 10);
        sViewsWithIds.put(R.id.tvOldPasswordError, 11);
        sViewsWithIds.put(R.id.newPasswordValContainer, 12);
        sViewsWithIds.put(R.id.txtNewPassword, 13);
        sViewsWithIds.put(R.id.newPasswordContainer, 14);
        sViewsWithIds.put(R.id.edtNewPassword, 15);
        sViewsWithIds.put(R.id.imgNewPassword, 16);
        sViewsWithIds.put(R.id.tvNewPasswordError, 17);
        sViewsWithIds.put(R.id.confirmPasswordValContainer, 18);
        sViewsWithIds.put(R.id.txtConfirmPassword, 19);
        sViewsWithIds.put(R.id.confirmPasswordContainer, 20);
        sViewsWithIds.put(R.id.edtConfirmPassword, 21);
        sViewsWithIds.put(R.id.imgConfirmPassword, 22);
        sViewsWithIds.put(R.id.tvConfirmPasswordError, 23);
        sViewsWithIds.put(R.id.btnChangePassword, 24);
    }
    // views
    @NonNull
    private final androidx.core.widget.NestedScrollView mboundView0;
    @Nullable
    private final com.cardio.physician.databinding.GuidelineLeftBinding mboundView1;
    @Nullable
    private final com.cardio.physician.databinding.GuidelineRightBinding mboundView11;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityChangeEmailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds));
    }
    private ActivityChangeEmailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatButton) bindings[24]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[20]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[18]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[21]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[15]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[9]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[1]
            , (bindings[2] != null) ? com.cardio.physician.databinding.ToolbarBinding.bind((android.view.View) bindings[2]) : null
            , (androidx.appcompat.widget.AppCompatImageView) bindings[22]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[16]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[10]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[14]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[12]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[8]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[6]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[23]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[17]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[11]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[19]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[5]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[13]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[7]
            );
        this.frameLayout.setTag(null);
        this.mboundView0 = (androidx.core.widget.NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (com.cardio.physician.databinding.GuidelineLeftBinding) bindings[3];
        setContainedBinding(this.mboundView1);
        this.mboundView11 = (com.cardio.physician.databinding.GuidelineRightBinding) bindings[4];
        setContainedBinding(this.mboundView11);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        mboundView1.invalidateAll();
        mboundView11.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView1.hasPendingBindings()) {
            return true;
        }
        if (mboundView11.hasPendingBindings()) {
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
        mboundView1.setLifecycleOwner(lifecycleOwner);
        mboundView11.setLifecycleOwner(lifecycleOwner);
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
        executeBindingsOn(mboundView1);
        executeBindingsOn(mboundView11);
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