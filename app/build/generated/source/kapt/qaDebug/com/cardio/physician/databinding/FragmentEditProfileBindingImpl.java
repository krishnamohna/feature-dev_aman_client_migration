package com.cardio.physician.databinding;
import com.cardio.physician.R;
import com.cardio.physician.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentEditProfileBindingImpl extends FragmentEditProfileBinding implements com.cardio.physician.generated.callback.OnItemSelected.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(44);
        sIncludes.setIncludes(1, 
            new String[] {"guideline_left", "guideline_right"},
            new int[] {4, 5},
            new int[] {com.cardio.physician.R.layout.guideline_left,
                com.cardio.physician.R.layout.guideline_right});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.headerView, 3);
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
        sViewsWithIds.put(R.id.txtResendVerificationLink, 19);
        sViewsWithIds.put(R.id.phoneNumberValContainer, 20);
        sViewsWithIds.put(R.id.txtPhoneNumber, 21);
        sViewsWithIds.put(R.id.phoneNumberContainer, 22);
        sViewsWithIds.put(R.id.countryCode, 23);
        sViewsWithIds.put(R.id.countryCodeSeprator, 24);
        sViewsWithIds.put(R.id.edtPhoneNumber, 25);
        sViewsWithIds.put(R.id.tvPhoneNoError, 26);
        sViewsWithIds.put(R.id.genderContainer, 27);
        sViewsWithIds.put(R.id.txtTitleGender, 28);
        sViewsWithIds.put(R.id.txtRating, 29);
        sViewsWithIds.put(R.id.tvGender, 30);
        sViewsWithIds.put(R.id.dobContainer, 31);
        sViewsWithIds.put(R.id.dobTxtTitle, 32);
        sViewsWithIds.put(R.id.edtDob, 33);
        sViewsWithIds.put(R.id.tvDobError, 34);
        sViewsWithIds.put(R.id.txtTitleHeight, 35);
        sViewsWithIds.put(R.id.edtHeight, 36);
        sViewsWithIds.put(R.id.tvHeightError, 37);
        sViewsWithIds.put(R.id.barrierHeightWeight, 38);
        sViewsWithIds.put(R.id.txtTitleWeight, 39);
        sViewsWithIds.put(R.id.edtWeight, 40);
        sViewsWithIds.put(R.id.tvWeightEditProfileError, 41);
        sViewsWithIds.put(R.id.barrierHeightWeightError, 42);
        sViewsWithIds.put(R.id.btnSave, 43);
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
    @Nullable
    private final androidx.databinding.adapters.AdapterViewBindingAdapter.OnItemSelected mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentEditProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 44, sIncludes, sViewsWithIds));
    }
    private FragmentEditProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.Barrier) bindings[38]
            , (androidx.constraintlayout.widget.Barrier) bindings[42]
            , (androidx.appcompat.widget.AppCompatButton) bindings[43]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[23]
            , (android.view.View) bindings[24]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[31]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[32]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[33]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[17]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[9]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[36]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[13]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[25]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[40]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[15]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[7]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[27]
            , (bindings[3] != null) ? com.cardio.physician.databinding.ToolbarBinding.bind((android.view.View) bindings[3]) : null
            , (com.google.android.material.imageview.ShapeableImageView) bindings[6]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[11]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[22]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[20]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[2]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[34]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[18]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[10]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[30]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[37]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[14]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[26]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[41]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[16]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[8]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[12]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[21]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[29]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[19]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[28]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[35]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[39]
            );
        this.mboundView0 = (androidx.core.widget.NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView11 = (com.cardio.physician.databinding.GuidelineLeftBinding) bindings[4];
        setContainedBinding(this.mboundView11);
        this.mboundView12 = (com.cardio.physician.databinding.GuidelineRightBinding) bindings[5];
        setContainedBinding(this.mboundView12);
        this.spinnerCategory.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.cardio.physician.generated.callback.OnItemSelected(this, 1);
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
        if (BR.userProfileViewModel == variableId) {
            setUserProfileViewModel((com.cardio.physician.ui.views.profile.editprofile.EditUserProfileViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUserProfileViewModel(@Nullable com.cardio.physician.ui.views.profile.editprofile.EditUserProfileViewModel UserProfileViewModel) {
        this.mUserProfileViewModel = UserProfileViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.userProfileViewModel);
        super.requestRebind();
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
        com.cardio.physician.ui.views.profile.editprofile.EditUserProfileViewModel userProfileViewModel = mUserProfileViewModel;
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            androidx.databinding.adapters.AdapterViewBindingAdapter.setOnItemSelectedListener(this.spinnerCategory, mCallback1, (androidx.databinding.adapters.AdapterViewBindingAdapter.OnNothingSelected)null, (androidx.databinding.InverseBindingListener)null);
        }
        executeBindingsOn(mboundView11);
        executeBindingsOn(mboundView12);
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnItemSelected(int sourceId , android.widget.AdapterView callbackArg_0, android.view.View callbackArg_1, int callbackArg_2, long callbackArg_3) {
        // localize variables for thread safety
        // userProfileViewModel
        com.cardio.physician.ui.views.profile.editprofile.EditUserProfileViewModel userProfileViewModel = mUserProfileViewModel;
        // userProfileViewModel != null
        boolean userProfileViewModelJavaLangObjectNull = false;



        userProfileViewModelJavaLangObjectNull = (userProfileViewModel) != (null);
        if (userProfileViewModelJavaLangObjectNull) {






            userProfileViewModel.selectGender(callbackArg_0, callbackArg_1, callbackArg_2, callbackArg_3);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): userProfileViewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}