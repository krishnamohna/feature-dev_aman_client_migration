package com.cardio.physician.databinding;
import com.cardio.physician.R;
import com.cardio.physician.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSignUpBindingImpl extends FragmentSignUpBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(48);
        sIncludes.setIncludes(1, 
            new String[] {"guideline_left", "guideline_right"},
            new int[] {3, 4},
            new int[] {com.cardio.physician.R.layout.guideline_left,
                com.cardio.physician.R.layout.guideline_right});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.headerView, 2);
        sViewsWithIds.put(R.id.titleSignup, 5);
        sViewsWithIds.put(R.id.imgProfilePic, 6);
        sViewsWithIds.put(R.id.firstNameValContainer, 7);
        sViewsWithIds.put(R.id.txtFirstName, 8);
        sViewsWithIds.put(R.id.edtFirstName, 9);
        sViewsWithIds.put(R.id.tvFirstNameError, 10);
        sViewsWithIds.put(R.id.lastNameValContainer, 11);
        sViewsWithIds.put(R.id.txtLastName, 12);
        sViewsWithIds.put(R.id.edtLastName, 13);
        sViewsWithIds.put(R.id.tvLastName, 14);
        sViewsWithIds.put(R.id.emailValContainer, 15);
        sViewsWithIds.put(R.id.txtEmailAddress, 16);
        sViewsWithIds.put(R.id.edtEmailId, 17);
        sViewsWithIds.put(R.id.tvEmailError, 18);
        sViewsWithIds.put(R.id.phoneNumberValContainer, 19);
        sViewsWithIds.put(R.id.txtPhoneNumber, 20);
        sViewsWithIds.put(R.id.phoneNumberContainer, 21);
        sViewsWithIds.put(R.id.countryCode, 22);
        sViewsWithIds.put(R.id.countryCodeSeprator, 23);
        sViewsWithIds.put(R.id.edtPhoneNumber, 24);
        sViewsWithIds.put(R.id.tvPhoneNoError, 25);
        sViewsWithIds.put(R.id.passwordValContainer, 26);
        sViewsWithIds.put(R.id.txtPassword, 27);
        sViewsWithIds.put(R.id.passwordContainer, 28);
        sViewsWithIds.put(R.id.edtPassword, 29);
        sViewsWithIds.put(R.id.imgShowPassword, 30);
        sViewsWithIds.put(R.id.ivPasswordnfo, 31);
        sViewsWithIds.put(R.id.tvPasswordError, 32);
        sViewsWithIds.put(R.id.confirmPasswordValContainer, 33);
        sViewsWithIds.put(R.id.txtConfirmPassword, 34);
        sViewsWithIds.put(R.id.confirmPasswordContainer, 35);
        sViewsWithIds.put(R.id.edtConfirmPassword, 36);
        sViewsWithIds.put(R.id.imgConfirmShowPassword, 37);
        sViewsWithIds.put(R.id.tvConfirmPasswordError, 38);
        sViewsWithIds.put(R.id.chkBoxAcceptPolicy, 39);
        sViewsWithIds.put(R.id.tvAgree, 40);
        sViewsWithIds.put(R.id.tvTermCondition, 41);
        sViewsWithIds.put(R.id.tvAnd, 42);
        sViewsWithIds.put(R.id.tvPrivacyPolicy, 43);
        sViewsWithIds.put(R.id.btnSignup, 44);
        sViewsWithIds.put(R.id.txtTitleSignup, 45);
        sViewsWithIds.put(R.id.txtSignin, 46);
        sViewsWithIds.put(R.id.iconSignupForward, 47);
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

    public FragmentSignUpBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 48, sIncludes, sViewsWithIds));
    }
    private FragmentSignUpBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatButton) bindings[44]
            , (androidx.appcompat.widget.AppCompatCheckBox) bindings[39]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[35]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[33]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[22]
            , (android.view.View) bindings[23]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[36]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[17]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[9]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[13]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[29]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[24]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[15]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[7]
            , (bindings[2] != null) ? com.cardio.physician.databinding.ToolbarBinding.bind((android.view.View) bindings[2]) : null
            , (androidx.appcompat.widget.AppCompatImageView) bindings[47]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[37]
            , (com.google.android.material.imageview.ShapeableImageView) bindings[6]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[30]
            , (android.widget.ImageView) bindings[31]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[11]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[28]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[26]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[21]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[19]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[5]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[40]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[42]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[38]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[18]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[10]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[14]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[32]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[25]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[43]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[41]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[34]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[16]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[8]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[12]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[27]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[20]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[46]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[45]
            );
        this.mboundView0 = (androidx.core.widget.NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView11 = (com.cardio.physician.databinding.GuidelineLeftBinding) bindings[3];
        setContainedBinding(this.mboundView11);
        this.mboundView12 = (com.cardio.physician.databinding.GuidelineRightBinding) bindings[4];
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
            setMViewModel((com.cardio.physician.ui.views.auth.signup.SignUpViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setMViewModel(@Nullable com.cardio.physician.ui.views.auth.signup.SignUpViewModel MViewModel) {
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