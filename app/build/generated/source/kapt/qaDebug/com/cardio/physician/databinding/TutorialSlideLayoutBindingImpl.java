package com.cardio.physician.databinding;
import com.cardio.physician.R;
import com.cardio.physician.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class TutorialSlideLayoutBindingImpl extends TutorialSlideLayoutBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(8);
        sIncludes.setIncludes(0, 
            new String[] {"guideline_left", "guideline_right"},
            new int[] {1, 2},
            new int[] {com.cardio.physician.R.layout.guideline_left,
                com.cardio.physician.R.layout.guideline_right});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tutorialImageContainer, 3);
        sViewsWithIds.put(R.id.tutorialImage, 4);
        sViewsWithIds.put(R.id.tutorialDetailContainer, 5);
        sViewsWithIds.put(R.id.tutorialTitle, 6);
        sViewsWithIds.put(R.id.tutorialDescription, 7);
    }
    // views
    @Nullable
    private final com.cardio.physician.databinding.GuidelineLeftBinding mboundView0;
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView01;
    @Nullable
    private final com.cardio.physician.databinding.GuidelineRightBinding mboundView02;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public TutorialSlideLayoutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private TutorialSlideLayoutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatTextView) bindings[7]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[4]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[3]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[6]
            );
        this.mboundView0 = (com.cardio.physician.databinding.GuidelineLeftBinding) bindings[1];
        setContainedBinding(this.mboundView0);
        this.mboundView01 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView01.setTag(null);
        this.mboundView02 = (com.cardio.physician.databinding.GuidelineRightBinding) bindings[2];
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
        mboundView0.invalidateAll();
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
        if (mboundView0.hasPendingBindings()) {
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
        mboundView0.setLifecycleOwner(lifecycleOwner);
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
        executeBindingsOn(mboundView0);
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