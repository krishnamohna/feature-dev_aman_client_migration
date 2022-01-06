package com.cardio.physician.databinding;
import com.cardio.physician.R;
import com.cardio.physician.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentGetProfileBindingImpl extends FragmentGetProfileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(34);
        sIncludes.setIncludes(1, 
            new String[] {"guideline_left", "guideline_right"},
            new int[] {3, 4},
            new int[] {com.cardio.physician.R.layout.guideline_left,
                com.cardio.physician.R.layout.guideline_right});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.headerView, 2);
        sViewsWithIds.put(R.id.txtUserName, 5);
        sViewsWithIds.put(R.id.txtEmailAddress, 6);
        sViewsWithIds.put(R.id.txtPhoneNumber, 7);
        sViewsWithIds.put(R.id.txtOtherDetail, 8);
        sViewsWithIds.put(R.id.genderContainer, 9);
        sViewsWithIds.put(R.id.imgGender, 10);
        sViewsWithIds.put(R.id.txtTitleGender, 11);
        sViewsWithIds.put(R.id.txtGender, 12);
        sViewsWithIds.put(R.id.weightContainer, 13);
        sViewsWithIds.put(R.id.imgWeight, 14);
        sViewsWithIds.put(R.id.txtTitleWeight, 15);
        sViewsWithIds.put(R.id.txtWeight, 16);
        sViewsWithIds.put(R.id.heightContainer, 17);
        sViewsWithIds.put(R.id.imgHeight, 18);
        sViewsWithIds.put(R.id.txtTitleHeight, 19);
        sViewsWithIds.put(R.id.txtHeight, 20);
        sViewsWithIds.put(R.id.imgProfilePic, 21);
        sViewsWithIds.put(R.id.wearableContainer, 22);
        sViewsWithIds.put(R.id.imgWearable, 23);
        sViewsWithIds.put(R.id.txtWearableTitle, 24);
        sViewsWithIds.put(R.id.txtSelectedWearable, 25);
        sViewsWithIds.put(R.id.imgSelectWearable, 26);
        sViewsWithIds.put(R.id.healthLogsContainer, 27);
        sViewsWithIds.put(R.id.imgHealthLogs, 28);
        sViewsWithIds.put(R.id.txtHealthLogs, 29);
        sViewsWithIds.put(R.id.settingContainer, 30);
        sViewsWithIds.put(R.id.imgSetting, 31);
        sViewsWithIds.put(R.id.txtSettingTitle, 32);
        sViewsWithIds.put(R.id.imgSelectSetting, 33);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
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

    public FragmentGetProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 34, sIncludes, sViewsWithIds));
    }
    private FragmentGetProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[9]
            , (bindings[2] != null) ? com.cardio.physician.databinding.ToolbarBinding.bind((android.view.View) bindings[2]) : null
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[27]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[17]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[10]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[28]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[18]
            , (com.google.android.material.imageview.ShapeableImageView) bindings[21]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[33]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[26]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[31]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[23]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[14]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[30]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[6]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[12]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[29]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[20]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[8]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[7]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[25]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[32]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[11]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[19]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[15]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[5]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[24]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[16]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[22]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[13]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
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