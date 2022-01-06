package com.cardio.physician.ui.common.utils.extentions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.cardio.physician.R;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a0\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011\u001a&\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u0011\u001a2\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00162\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u000fH\u0007\"\"\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0018"}, d2 = {"factory", "Lcom/bumptech/glide/request/transition/DrawableCrossFadeFactory;", "kotlin.jvm.PlatformType", "getFactory", "()Lcom/bumptech/glide/request/transition/DrawableCrossFadeFactory;", "setFactory", "(Lcom/bumptech/glide/request/transition/DrawableCrossFadeFactory;)V", "getProgressDrawable", "Landroidx/swiperefreshlayout/widget/CircularProgressDrawable;", "context", "Landroid/content/Context;", "loadImage", "", "Landroid/widget/ImageView;", "image", "", "circleCrop", "", "cache", "imageUrl", "Landroid/net/Uri;", "showProgress", "", "errorImage", "app_qaDebug"})
public final class ImageExtensionsKt {
    private static com.bumptech.glide.request.transition.DrawableCrossFadeFactory factory;
    
    /**
     * ImageView Extensions
     */
    public static final void loadImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView $this$loadImage, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @androidx.annotation.DrawableRes()
    int image, boolean circleCrop, boolean cache) {
    }
    
    public static final void loadImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView $this$loadImage, @org.jetbrains.annotations.NotNull()
    android.net.Uri imageUrl, boolean circleCrop, boolean showProgress) {
    }
    
    public static final com.bumptech.glide.request.transition.DrawableCrossFadeFactory getFactory() {
        return null;
    }
    
    public static final void setFactory(com.bumptech.glide.request.transition.DrawableCrossFadeFactory p0) {
    }
    
    @android.annotation.SuppressLint(value = {"CheckResult"})
    public static final void loadImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView $this$loadImage, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, boolean circleCrop, boolean showProgress, int errorImage) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.swiperefreshlayout.widget.CircularProgressDrawable getProgressDrawable(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}