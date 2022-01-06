package com.cardio.physician.ui.views.diagnosis.step2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import com.cardio.physician.R;
import com.cardio.physician.domain.diagnosis.MedicineModel;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001cBL\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b\u0012\'\u0010\t\u001a#\u0012\u0013\u0012\u00110\u0002\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\b0\n\u00a2\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u0002J\"\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R/\u0010\t\u001a#\u0012\u0013\u0012\u00110\u0002\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step2/adapter/PredictiveMedicineSearchAdapter;", "Landroid/widget/ArrayAdapter;", "", "mContext", "Landroid/content/Context;", "itemLayout", "", "dataList", "", "onMedicineSearch", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "query", "Lcom/cardio/physician/domain/diagnosis/MedicineModel;", "(Landroid/content/Context;ILjava/util/List;Lkotlin/jvm/functions/Function1;)V", "getCount", "getFilter", "Landroid/widget/Filter;", "getItem", "position", "getStringArrayFromModels", "list", "getView", "Landroid/view/View;", "view", "parent", "Landroid/view/ViewGroup;", "FilterMedicine", "app_qaDebug"})
public final class PredictiveMedicineSearchAdapter extends android.widget.ArrayAdapter<java.lang.String> {
    private final android.content.Context mContext = null;
    private final int itemLayout = 0;
    private java.util.List<java.lang.String> dataList;
    private final kotlin.jvm.functions.Function1<java.lang.String, java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel>> onMedicineSearch = null;
    
    public PredictiveMedicineSearchAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext, int itemLayout, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> dataList, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, ? extends java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel>> onMedicineSearch) {
        super(null, 0);
    }
    
    @java.lang.Override()
    public int getCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getItem(int position) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getView(int position, @org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.widget.Filter getFilter() {
        return null;
    }
    
    private final java.util.List<java.lang.String> getStringArrayFromModels(java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> list) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\u001c\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step2/adapter/PredictiveMedicineSearchAdapter$FilterMedicine;", "Landroid/widget/Filter;", "(Lcom/cardio/physician/ui/views/diagnosis/step2/adapter/PredictiveMedicineSearchAdapter;)V", "lock", "", "performFiltering", "Landroid/widget/Filter$FilterResults;", "prefix", "", "publishResults", "", "p0", "results", "app_qaDebug"})
    public final class FilterMedicine extends android.widget.Filter {
        private final java.lang.Object lock = null;
        
        public FilterMedicine() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        protected android.widget.Filter.FilterResults performFiltering(@org.jetbrains.annotations.Nullable()
        java.lang.CharSequence prefix) {
            return null;
        }
        
        @java.lang.Override()
        protected void publishResults(@org.jetbrains.annotations.Nullable()
        java.lang.CharSequence p0, @org.jetbrains.annotations.Nullable()
        android.widget.Filter.FilterResults results) {
        }
    }
}