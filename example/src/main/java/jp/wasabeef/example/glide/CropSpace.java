package jp.wasabeef.example.glide;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;

import androidx.annotation.NonNull;
import jp.wasabeef.glide.transformations.BitmapTransformation;

class CropSpace extends BitmapTransformation {
  private static final int VERSION = 1;
  private static final String ID =
    "jp.wasabeef.glide.transformations.CropSpace." + VERSION;
  private int size;
  private int left, top, right, bottom;

  @Override
  protected Bitmap transform(@NonNull @NotNull Context context, @NonNull @NotNull BitmapPool pool, @NonNull @NotNull Bitmap toTransform, int outWidth, int outHeight) {
    this.size = Math.max(outWidth, outHeight);
    int width = toTransform.getWidth();
    int height = toTransform.getHeight();
    Bitmap result = pool.get(width, height, Bitmap.Config.ARGB_8888);
    return Bitmap.createBitmap(toTransform, width/3, 0, width / 3, height / 2);
  }

  @Override
  public void updateDiskCacheKey(@NonNull @NotNull MessageDigest messageDigest) {
    messageDigest.update((ID + size).getBytes(CHARSET));
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof  CropSpace && ((CropSpace) o).size == size;
  }

  @Override
  public int hashCode() {
    return ID.hashCode() + size * 10;
  }
}
