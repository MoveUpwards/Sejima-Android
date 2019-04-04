package com.rlab.sejima.features;

import android.util.DisplayMetrics;
import android.util.TypedValue;

public interface MUViewHelper {

    default float pixelsToDensity(DisplayMetrics dp, float density){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, density, dp);
    }

    default float densityToPixels(DisplayMetrics dp, float pixels){
        return pixels * dp.densityDpi / DisplayMetrics.DENSITY_DEFAULT;
    }
}
