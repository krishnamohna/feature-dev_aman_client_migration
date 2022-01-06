package com.cardio.physician.databinding;
import com.cardio.physician.R;
import com.cardio.physician.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentForgotPasswordBindingImpl extends FragmentForgotPasswordBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(14);
        sIncludes.setIncludes(0, 
            new String[] {"guideline_left", "guideline_right"},
            new int[] {2, 3},
            new int[] {com.cardio.physician.R.layout.guideline_left,
                com.cardio.physician.R.layout.guideline_right});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.headerView, 1);
        sViewsWithIds.put(R.id.titleforgotPassword, 4);
        sViewsWithIds.put(R.id.forgotPasswordInstruction, 5);
        sViewsWithIds.put(R.id.emailValContainer, 6);
        sViewsWithIds.put(R.id.tvEmailAddress, 7);
        sViewsWithIds.put(R.id.edtEmailId, 8);
        sViewsWithIds.put(R.id.tvEmailError, 9);
        sViewsWithIds.put(R.id.lay2, 10);
        sViewsWithIds.put(R.id.tvConfirmPassword, 11);
        sViewsWithIds.put(R.id.edtConfirmPassword, 12);
        sViewsWithIds.put(R.id.btnForgotPassword, 13);
    }
    // views
    @Nullable
    private final com.cardio.physician.databinding.GuidelineLeftBinding mboundView0;
    @Nullable
    private final com.cardio.physician.databinding.GuidelineRightBinding mboundView01;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentForgotPasswordBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentForgotPasswordBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatButton) bindings[13]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[12]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[8]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[6]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[5]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (bindings[1] != null) ? com.cardio.physician.databinding.ToolbarBinding.bind((android.view.View) bindings[1]) : null
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[10]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[4]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[11]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[7]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[9]
            );
        this.frameLayout.setTag(null);
        this.mboundView0 = (com.cardio.physician.databinding.GuidelineLeftBinding) bindings[2];
        setContainedBinding(this.mboundView0);
        this.mboundView01 = (com.cardio.physician.databinding.GuidelineRightBinding) bindings[3];
        setContainedBinding(this.mboundView01);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        mboundView0.invalidateAll();
        mboundView01.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView0.hasPendingBindings()) {
            return true;
        }
        if (mboundView01.hasPendingBindings()) {
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
        mboundView0.setLifecycleOwner(lifecycleOwner);
        mboundView01.setLifecycleOwner(lifecycleOwner);
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
        executeBindingsOn(mboundView0);
        executeBindingsOn(mboundView01);
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