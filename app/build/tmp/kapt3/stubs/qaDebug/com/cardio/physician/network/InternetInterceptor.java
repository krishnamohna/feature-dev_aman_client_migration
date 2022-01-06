package com.cardio.physician.network;

import okhttp3.Interceptor;
import okhttp3.Response;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/cardio/physician/network/InternetInterceptor;", "Lokhttp3/Interceptor;", "networkMonitor", "Lcom/cardio/physician/network/NetworkHelper;", "(Lcom/cardio/physician/network/NetworkHelper;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "app_qaDebug"})
@javax.inject.Singleton()
public final class InternetInterceptor implements okhttp3.Interceptor {
    private final com.cardio.physician.network.NetworkHelper networkMonitor = null;
    
    @javax.inject.Inject()
    public InternetInterceptor(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.NetworkHelper networkMonitor) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull()
    okhttp3.Interceptor.Chain chain) {
        return null;
    }
}