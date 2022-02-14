package space.pixelsg.moovies.ui.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

import space.pixelsg.moovies.data.source.remote.RemoteConfig;

public class PosterView extends AppCompatImageView {
    public PosterView(@NonNull Context context) {
        super(context);
    }

    public PosterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PosterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(getMeasuredWidth(), (int) (getMeasuredWidth() * 1.5));
    }

    public void loadImage(String url) {
        loadUrl(RemoteConfig.IMAGE_URL + url);
    }

    public void loadImageFull(String posterPath) {
        loadUrl(RemoteConfig.IMAGE_URL_FULL + posterPath);
    }

    public void loadUrl(String url) {
        Glide
                .with(this)
                .load(url)
                .centerCrop()
                .into(this);
    }
}
