package com.cardio.physician.generated.callback;
public final class OnItemSelected implements androidx.databinding.adapters.AdapterViewBindingAdapter.OnItemSelected {
    final Listener mListener;
    final int mSourceId;
    public OnItemSelected(Listener listener, int sourceId) {
        mListener = listener;
        mSourceId = sourceId;
    }
    @Override
    public void onItemSelected(android.widget.AdapterView callbackArg_0, android.view.View callbackArg_1, int callbackArg_2, long callbackArg_3) {
        mListener._internalCallbackOnItemSelected(mSourceId , callbackArg_0, callbackArg_1, callbackArg_2, callbackArg_3);
    }
    public interface Listener {
        void _internalCallbackOnItemSelected(int sourceId , android.widget.AdapterView callbackArg_0, android.view.View callbackArg_1, int callbackArg_2, long callbackArg_3);
    }
}