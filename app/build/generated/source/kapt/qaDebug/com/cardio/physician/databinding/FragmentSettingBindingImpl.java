package com.cardio.physician.databinding;
import com.cardio.physician.R;
import com.cardio.physician.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSettingBindingImpl extends FragmentSettingBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(29);
        sIncludes.setIncludes(1, 
            new String[] {"guideline_left", "guideline_right"},
            new int[] {3, 4},
            new int[] {com.cardio.physician.R.layout.guideline_left,
                com.cardio.physician.R.layout.guideline_right});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.headerView, 2);
        sViewsWithIds.put(R.id.enablePasscodeContainer, 5);
        sViewsWithIds.put(R.id.imgEnablePasscode, 6);
        sViewsWithIds.put(R.id.txtEnablePasscodeTitle, 7);
        sViewsWithIds.put(R.id.switchNotification, 8);
        sViewsWithIds.put(R.id.changePasswordContainer, 9);
        sViewsWithIds.put(R.id.imgChangePassword, 10);
        sViewsWithIds.put(R.id.imgForwardChangePin, 11);
        sViewsWithIds.put(R.id.txtChangePasswordTitle, 12);
        sViewsWithIds.put(R.id.aboutUsContainer, 13);
        sViewsWithIds.put(R.id.imgAbout, 14);
        sViewsWithIds.put(R.id.txtAbout, 15);
        sViewsWithIds.put(R.id.imgForwardChangePassword, 16);
        sViewsWithIds.put(R.id.faqContainer, 17);
        sViewsWithIds.put(R.id.imgFaq, 18);
        sViewsWithIds.put(R.id.txtFaqTitle, 19);
        sViewsWithIds.put(R.id.imgFaqNext, 20);
        sViewsWithIds.put(R.id.termsAndConditionContainer, 21);
        sViewsWithIds.put(R.id.imgTermsAndCondition, 22);
        sViewsWithIds.put(R.id.txtTermsAndConditionTitle, 23);
        sViewsWithIds.put(R.id.imgTermsAndConditionForward, 24);
        sViewsWithIds.put(R.id.logoutContainer, 25);
        sViewsWithIds.put(R.id.imgLogout, 26);
        sViewsWithIds.put(R.id.txtLogout, 27);
        sViewsWithIds.put(R.id.appVersion, 28);
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

    public FragmentSettingBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 29, sIncludes, sViewsWithIds));
    }
    private FragmentSettingBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[13]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[28]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[9]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[17]
            , (bindings[2] != null) ? com.cardio.physician.databinding.ToolbarBinding.bind((android.view.View) bindings[2]) : null
            , (androidx.appcompat.widget.AppCompatImageView) bindings[14]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[10]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[6]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[18]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[20]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[16]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[11]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[26]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[22]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[24]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[25]
            , (androidx.appcompat.widget.SwitchCompat) bindings[8]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[21]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[15]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[12]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[7]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[19]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[27]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[23]
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
                mDirtyFlags = 0x1L;
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
            return variableSet;
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
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}