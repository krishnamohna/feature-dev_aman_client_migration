package com.cardio.physician.ui.views.dashboard.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.cardio.physician.R;
import com.cardio.physician.databinding.ItemConnectionBinding;
import com.cardio.physician.domain.connection.ConnectionModel;
import java.util.*;
import kotlin.collections.ArrayList;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u00012\u00020\u0003:\u0001.Bd\u00128\u0010\u0004\u001a4\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005\u0012#\u0010\r\u001a\u001f\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0010\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000e\u00a2\u0006\u0002\u0010\u0011J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\nH\u0016J\u001c\u0010!\u001a\u00020\f2\n\u0010\"\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u001c\u0010#\u001a\u00060\u0002R\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\nH\u0016J\u0014\u0010\'\u001a\u00020\f2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00140)J\u0016\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,R!\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R!\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R.\u0010\r\u001a\u001f\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0010\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bRC\u0010\u0004\u001a4\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006/"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/fragment/ConnectionsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/cardio/physician/ui/views/dashboard/fragment/ConnectionsAdapter$ConnectionsViewHolder;", "Landroid/widget/Filterable;", "recyclerViewListener", "Lkotlin/Function2;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "", "position", "", "emptyListView", "Lkotlin/Function1;", "", "showHideView", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "connectionsList", "Ljava/util/ArrayList;", "Lcom/cardio/physician/domain/connection/ConnectionModel;", "Lkotlin/collections/ArrayList;", "getConnectionsList", "()Ljava/util/ArrayList;", "connectionsListFiltered", "getConnectionsListFiltered", "getEmptyListView", "()Lkotlin/jvm/functions/Function1;", "getRecyclerViewListener", "()Lkotlin/jvm/functions/Function2;", "getFilter", "Landroid/widget/Filter;", "getItemCount", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateConnectionList", "list", "", "updateList", "startDate", "", "endDate", "ConnectionsViewHolder", "app_qaDebug"})
public final class ConnectionsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.cardio.physician.ui.views.dashboard.fragment.ConnectionsAdapter.ConnectionsViewHolder> implements android.widget.Filterable {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function2<android.view.View, java.lang.Integer, kotlin.Unit> recyclerViewListener = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> emptyListView = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<com.cardio.physician.domain.connection.ConnectionModel> connectionsList = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<com.cardio.physician.domain.connection.ConnectionModel> connectionsListFiltered = null;
    
    public ConnectionsAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super android.view.View, ? super java.lang.Integer, kotlin.Unit> recyclerViewListener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> emptyListView) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function2<android.view.View, java.lang.Integer, kotlin.Unit> getRecyclerViewListener() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getEmptyListView() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.cardio.physician.domain.connection.ConnectionModel> getConnectionsList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.cardio.physician.domain.connection.ConnectionModel> getConnectionsListFiltered() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.ui.views.dashboard.fragment.ConnectionsAdapter.ConnectionsViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.fragment.ConnectionsAdapter.ConnectionsViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void updateConnectionList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.connection.ConnectionModel> list) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.widget.Filter getFilter() {
        return null;
    }
    
    public final void updateList(long startDate, long endDate) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/fragment/ConnectionsAdapter$ConnectionsViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/cardio/physician/databinding/ItemConnectionBinding;", "(Lcom/cardio/physician/ui/views/dashboard/fragment/ConnectionsAdapter;Lcom/cardio/physician/databinding/ItemConnectionBinding;)V", "getBinding", "()Lcom/cardio/physician/databinding/ItemConnectionBinding;", "bind", "", "app_qaDebug"})
    public final class ConnectionsViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.cardio.physician.databinding.ItemConnectionBinding binding = null;
        
        public ConnectionsViewHolder(@org.jetbrains.annotations.NotNull()
        com.cardio.physician.databinding.ItemConnectionBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.cardio.physician.databinding.ItemConnectionBinding getBinding() {
            return null;
        }
        
        public final void bind() {
        }
    }
}