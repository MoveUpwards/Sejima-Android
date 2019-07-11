package com.rlab.sejima.features;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

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
        valueToNormalize = Math.min(valueToNormalize, maxValue);
        return valueToNormalize > minValue ? valueToNormalize : minValue;
    }

    default float normalizeFloatValue(float valueToNormalize, float minValue, float maxValue){
        valueToNormalize = Math.max(minValue, valueToNormalize);
        valueToNormalize = Math.min(valueToNormalize, maxValue);
        return valueToNormalize > minValue ? valueToNormalize : minValue;
    }

    /**
     * Check if the given resource id exists in resources
     * @param resources the resources which contains the given id
     * @param resId the resource id to check
     * @return true if the resource exists, false otherwise
     */
    default boolean checkResource(Resources resources, int resId) {
        if (resources != null){
            try {
                return resources.getResourceName(resId) != null;
            } catch (Resources.NotFoundException ignore) {
                return false;
            }
        }
        return false;
    }


    /**
     *Apply given radius to the view
     * @param cornerRadius the corner radius to apply
     * @param view the view to be round-cornered
     * @param backgroundColor the background color of the view
     */
    default void applyRoundCornerToView(float cornerRadius, int backgroundColor, View view){
        GradientDrawable borderDrawable = new GradientDrawable();
        borderDrawable.setCornerRadius(cornerRadius);
        borderDrawable.setColor(backgroundColor);
        view.setBackground(borderDrawable);
    }
}
