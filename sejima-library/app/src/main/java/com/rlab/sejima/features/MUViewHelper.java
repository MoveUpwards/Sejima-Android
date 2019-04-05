package com.rlab.sejima.features;

import android.util.DisplayMetrics;
import android.util.TypedValue;

interface MUViewHelper {

    default float pixelsToDensity(DisplayMetrics dp, float density){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, density, dp);
    }

    default float densityToPixels(DisplayMetrics dp, float pixels){
        return pixels * dp.densityDpi / DisplayMetrics.DENSITY_DEFAULT;
    }

    /**
     * Normalize alpha value between 0 and 1
     * @param alpha the alpha value to check
     * @return the normalized value of alpha
     */
    default float normalizeAlphaValue(float alpha) {
        return normalizeFloatValue(alpha, 0, 1);
    }

    /**
     * Normalize multiplier value between 0 and 1
     * @param multiplier the alpha value to check
     * @return the normalized value of multiplier
     */
    default float normalizeMultiplierValue(float multiplier) {
        return normalizeFloatValue(multiplier, 0, 1);
    }

    default int normalizeIntValue(int valueToNormalize, int minValue, int maxValue){
        valueToNormalize = Math.max(minValue, valueToNormalize);
        return Math.min(valueToNormalize, maxValue);
    }

    default float normalizeFloatValue(float valueToNormalize, float minValue, float maxValue){
        valueToNormalize = Math.max(minValue, valueToNormalize);
        return Math.min(valueToNormalize, maxValue);
    }
}
