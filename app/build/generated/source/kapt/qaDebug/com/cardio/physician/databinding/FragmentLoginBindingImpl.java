package com.cardio.physician.databinding;
import com.cardio.physician.R;
import com.cardio.physician.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentLoginBindingImpl extends FragmentLoginBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(27);
        sIncludes.setIncludes(1, 
            new String[] {"guideline_left", "guideline_right"},
            new int[] {2, 3},
            new int[] {com.cardio.physician.R.layout.guideline_left,
                com.cardio.physician.R.layout.guideline_right});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id._imgProfilePic, 4);
        sViewsWithIds.put(R.id.emailValContainer, 5);
        sViewsWithIds.put(R.id.tvEmailAddress, 6);
        sViewsWithIds.put(R.id.phoneNumberContainer, 7);
        sViewsWithIds.put(R.id.countryCodeSeprator, 8);
        sViewsWithIds.put(R.id.edtUserName, 9);
        sViewsWithIds.put(R.id.countryCode, 10);
        sViewsWithIds.put(R.id.tvEmailError, 11);
        sViewsWithIds.put(R.id.passwordValContainer, 12);
        sViewsWithIds.put(R.id.tvPassword, 13);
        sViewsWithIds.put(R.id.passwordContainer, 14);
        sViewsWithIds.put(R.id.edtPassword, 15);
        sViewsWithIds.put(R.id.imgShowPassword, 16);
        sViewsWithIds.put(R.id.tvPasswordError, 17);
        sViewsWithIds.put(R.id.forgotPassword, 18);
        sViewsWithIds.put(R.id.btnLogin, 19);
        sViewsWithIds.put(R.id.txtSocialMedia, 20);
        sViewsWithIds.put(R.id.btnGoogleContainer, 21);
        sViewsWithIds.put(R.id.iconGoogle, 22);
        sViewsWithIds.put(R.id.btnGoogleSignIn, 23);
        sViewsWithIds.put(R.id.txtTitleSignup, 24);
        sViewsWithIds.put(R.id.txtSignup, 25);
        sViewsWithIds.put(R.id.iconSignupForward, 26);
    }
    // views
    @NonNull
    private final androidx.core.widget.NestedScrollView mboundView0;
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView1;
    @Nullable
    private final com.cardio.physician.databinding.GuidelineLeftBinding mboundView11;
    @Nullable
    private final com.cardio.physician.databinding.GuidelineRightBinding mboundView12;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentLoginBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds));
    }
    private FragmentLoginBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatImageView) bindings[4]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[21]
            , (androidx.appcompat.widget.AppCompatButton) bindings[23]
            , (androidx.appcompat.widget.AppCompatButton) bindings[19]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[10]
            , (android.view.View) bindings[8]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[15]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[9]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[18]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[22]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[26]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[16]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[14]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[12]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[7]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[6]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[11]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[13]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[17]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[25]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[20]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[24]
            );
        this.mboundView0 = (androidx.core.widget.NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView11 = (com.cardio.physician.databinding.GuidelineLeftBinding) bindings[2];
        setContainedBinding(this.mboundView11);
        this.mboundView12 = (com.cardio.physician.databinding.GuidelineRightBinding) bindings[3];
        setContainedBinding(this.mboundView12);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        mboundView11.invalidateAll();
        mboundView12.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView11.hasPendingBindings()) {
            return true;
        }
        if (mboundView12.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.mViewModel == variableId) {
            setMViewModel((com.cardio.physician.ui.views.auth.login.LoginViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setMViewModel(@Nullable com.cardio.physician.ui.views.auth.login.LoginViewModel MViewModel) {
        this.mMViewModel = MViewModel;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        mboundView11.setLifecycleOwner(lifecycleOwner);
        mboundView12.setLifecycleOwner(lifecycleOwner);
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
        executeBindingsOn(mboundView11);
        executeBindingsOn(mboundView12);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): mViewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}