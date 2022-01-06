package com.cardio.physician.ui.views.diagnosis.step2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.cardio.physician.R;
import com.cardio.physician.databinding.ItemMedicineAddedBinding;
import com.cardio.physician.domain.diagnosis.MedicineModel;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001fB\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0013J\u001c\u0010\u0016\u001a\u00020\f2\n\u0010\u0017\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u000fH\u0016J\u001c\u0010\u0019\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000fH\u0016J\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u000fH\u0002R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006 "}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step2/adapter/MedRecylarAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/cardio/physician/ui/views/diagnosis/step2/adapter/MedRecylarAdapter$MedViewHolder;", "()V", "listMeds", "", "Lcom/cardio/physician/domain/diagnosis/MedicineModel;", "getListMeds", "()Ljava/util/List;", "setListMeds", "(Ljava/util/List;)V", "addMed", "", "it", "getItemCount", "", "isMedExist", "", "medName", "", "isMedSearchExist", "searchedMed", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "removeItem", "adapterPosition", "MedViewHolder", "app_qaDebug"})
public final class MedRecylarAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.cardio.physician.ui.views.diagnosis.step2.adapter.MedRecylarAdapter.MedViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> listMeds;
    
    public MedRecylarAdapter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> getListMeds() {
        return null;
    }
    
    public final void setListMeds(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.ui.views.diagnosis.step2.adapter.MedRecylarAdapter.MedViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.diagnosis.step2.adapter.MedRecylarAdapter.MedViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void addMed(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.MedicineModel it) {
    }
    
    public final boolean isMedSearchExist(@org.jetbrains.annotations.NotNull()
    java.lang.String searchedMed) {
        return false;
    }
    
    public final boolean isMedExist(@org.jetbrains.annotations.NotNull()
    java.lang.String medName) {
        return false;
    }
    
    private final void removeItem(int adapterPosition) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step2/adapter/MedRecylarAdapter$MedViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/cardio/physician/ui/views/diagnosis/step2/adapter/MedRecylarAdapter;Landroid/view/View;)V", "binding", "Lcom/cardio/physician/databinding/ItemMedicineAddedBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ItemMedicineAddedBinding;", "setBinding", "(Lcom/cardio/physician/databinding/ItemMedicineAddedBinding;)V", "getItemView", "()Landroid/view/View;", "bind", "", "model", "Lcom/cardio/physician/domain/diagnosis/MedicineModel;", "app_qaDebug"})
    public final class MedViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.view.View itemView = null;
        @org.jetbrains.annotations.NotNull()
        private com.cardio.physician.databinding.ItemMedicineAddedBinding binding;
        
        public MedViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.view.View getItemView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.cardio.physician.databinding.ItemMedicineAddedBinding getBinding() {
            return null;
        }
        
        public final void setBinding(@org.jetbrains.annotations.NotNull()
        com.cardio.physician.databinding.ItemMedicineAddedBinding p0) {
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.cardio.physician.domain.diagnosis.MedicineModel model) {
        }
    }
}