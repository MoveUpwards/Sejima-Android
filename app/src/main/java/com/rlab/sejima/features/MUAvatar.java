package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import com.rlab.sejima.R;

/*
    Created by Antoine RICHE on 26/03/2019.
    credits: https://github.com/hdodenhof/CircleImageView
 */

public class MUAvatar extends android.support.v7.widget.AppCompatImageView {

    /**
     * Static fields to determinate the border type
     */
    public static final Integer SQUARE_BORDER = 0;
    public static final Integer ROUND_BORDER = 1;
    public static final Integer DEFAULT_BORDER_WIDTH_IN_DP = 3;


    // Drawing tools variables
    /**
     * RectF representing the image area
     */
    private final RectF mDrawableRect = new RectF();
    /**
     * RectF representing the border area
     */
    private final RectF mBorderRect = new RectF();
    private final Matrix mShaderMatrix = new Matrix();
    /**
     * Paint used to draw the image
     */
    private final Paint mBitmapPaint = new Paint();
    /**
     * Paint used to draw the image's border
     */
    private final Paint mBorderPaint = new Paint();
    /**
     * Paint used to draw the circular background
     */
    private final Paint mBkgPaint = new Paint();

    /**
     * The Bitmap representation of image
     */
    private Bitmap mBitmap;
    /**
     * The Bitmap shader attached to Bitmap
     */
    private BitmapShader mBitmapShader;
    /**
     * The Bitmap width
     */
    private int mBitmapWidth;
    /**
     * The Bitmap height
     */
    private int mBitmapHeight;
    /**
     * Indicates if the view is ready to be drawn
     */
    private boolean mReady;
    /**
     * Indicates if the view is being rendered
     */
    private boolean mSetupPending;


    // Custom attributes
    /**
     * The border color
     */
    private int mBorderColor = Color.BLACK;
    /**
     * The background color if the image is not large enough
     */
    private int mBkgColor = Color.TRANSPARENT;
    /**
     * The border width
     */
    private float mBorderWidth;
    /**
     * The drawable representation of the image
     */
    private Drawable mImage;
    /**
     * The placeholder image as drawable
     */
    private Drawable mPlaceholderImage;
    /**
     * Interface handling user interaction
     */
    private MUAvatarClickListener mClickListener;
    /**
     * The current type of border
     */
    private int mBorderType = SQUARE_BORDER;
    /**
     * The current corner radius (=1000) in case of circle border
     */
    private float mCornerRadius;
    /**
     * The scale used to convert px in dp
     */
    private float mScale;

    /**
     * Default constructor
     * @param context the view context
     */
    public MUAvatar(Context context) {
        super(context);
        init(context);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUAvatar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUAvatar);
        mBorderWidth = attributes.getDimension(R.styleable.MUAvatar_border_width, mBorderWidth);
        mBorderColor = attributes.getColor(R.styleable.MUAvatar_border_color, mBorderColor);
        mBkgColor = attributes.getColor(R.styleable.MUAvatar_bkg_color, mBkgColor);
        mCornerRadius = attributes.getDimension(R.styleable.MUAvatar_corner_radius, mCornerRadius);
        mBorderType = attributes.getInt(R.styleable.MUAvatar_border_type, mBorderType);
        mImage = attributes.getDrawable(R.styleable.MUAvatar_android_src);

        init(context);
        attributes.recycle();
    }

    private void init(Context context) {
        mScale = (float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;

        setImageDrawable(mImage);
        setScaleType(ScaleType.CENTER_CROP);
        setBorderColor(mBorderColor);
        setCornerRadius(mCornerRadius);
        setBorderType(mBorderType);
        setBorderWidth(mBorderWidth != 0 ? mBorderWidth : DEFAULT_BORDER_WIDTH_IN_DP);

        mReady = true;

        if (mSetupPending) {
            setup();
            mSetupPending = false;
        }

        AppCompatImageView root = this;
        setOnTouchListener((v, event) -> {
            if(mClickListener != null
                    && event.getAction() == MotionEvent.ACTION_DOWN
                    && isInCircle(event.getX(), event.getY())){
                mClickListener.clickOnImage(root);
                this.performClick();
            }
            return false;
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null) {
            return;
        }

        // Deal with the background color
        if (mBkgColor != Color.TRANSPARENT) {
            canvas.drawRoundRect(mDrawableRect, mCornerRadius, mCornerRadius, mBkgPaint);
        }

        // Deal with the drawable image
        canvas.drawRoundRect(mDrawableRect, mCornerRadius, mCornerRadius, mBitmapPaint);

        // Deal with the border color
        if (mBorderWidth > 0) {
            canvas.drawRoundRect(mDrawableRect, mCornerRadius, mCornerRadius, mBorderPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        setup();
    }

    @Override
    public void setPaddingRelative(int start, int top, int end, int bottom) {
        super.setPaddingRelative(start, top, end, bottom);
        setup();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        initializeBitmap();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        initializeBitmap();
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        initializeBitmap();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        initializeBitmap();
    }

    /**
     * Retrieve the drawable source image as Bitmap
     * @param drawable the source image
     * @return the image as a Bitmap
     */
    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap = Bitmap.createBitmap(
                    drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                    Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initializeBitmap() {
        mImage = getDrawable();
        mImage = mImage != null ? mImage : mPlaceholderImage;
        mBitmap = getBitmapFromDrawable(mImage);
        setup();
    }

    /**
     * Get the image attached to the view
     * @return the image as Drawable
     */
    public Drawable getImage() {
        return getDrawable();
    }

    /**
     * Attach the image to the view
     * @param image the image as Drawable
     */
    public void setImage(Drawable image) {
        mImage = image != null ? image : mPlaceholderImage;
        setImageDrawable(mImage);
    }

    private void setup() {
        if (!mReady) {
            mSetupPending = true;
            return;
        }

        if (getWidth() == 0 && getHeight() == 0) {
            return;
        }

        if (mBitmap == null) {
            invalidate();
            return;
        }

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mBkgPaint.setStyle(Paint.Style.FILL);
        mBkgPaint.setAntiAlias(true);
        mBkgPaint.setColor(mBkgColor);

        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();

        mBorderRect.set(calculateBounds());
        mDrawableRect.set(mBorderRect);

        updateShaderMatrix();
        invalidate();
    }

    /**
     * Return the coordinates of the border area according to current view as RectF
     */
    private RectF calculateBounds() {
        int availableWidth  = getWidth() - getPaddingLeft() - getPaddingRight();
        int availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        int sideLength = Math.min(availableWidth, availableHeight);
        sideLength -= 1 * mBorderWidth;
        float left = getPaddingLeft() + (availableWidth - sideLength) / 2f;
        float top = getPaddingTop() + (availableHeight - sideLength) / 2f;

        return new RectF(left, top, left + sideLength, top + sideLength);
    }

    private void updateShaderMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);

        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
            scale = mDrawableRect.height() / (float) mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mDrawableRect.left, (int) (dy + 0.5f) + mDrawableRect.top);

        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

    /**
     * Get the background color
     * @return the background color as RGBA integer
     */
    public int getBkgColor() {
        return mBkgColor;
    }

    /**
     * Set the background color
     * @param bkgColor the background color as RGBA integer
     */
    public void setBkgColor(@ColorInt int bkgColor) {
        if (bkgColor == mBkgColor) {
            return;
        }

        mBkgColor = bkgColor;
        mBkgPaint.setColor(bkgColor);
        invalidate();
    }

    /**
     * Get the border color
     * @return the border color as RGBA integer
     */
    public int getBorderColor() {
        return mBorderColor;
    }

    /**
     * Set and apply borderColor
     * @param borderColor the color as RGBA integer
     */
    public void setBorderColor(@ColorInt int borderColor) {
        if (borderColor == mBorderColor) {
            return;
        }

        mBorderColor = borderColor;
        mBorderPaint.setColor(mBorderColor);
        invalidate();
    }

    /**
     * Get the current listener
     * @return the current interface listener
     */
    public MUAvatarClickListener getClickListener() {
        return mClickListener;
    }

    /**
     * Set the interface listener to handle click events
     * @param clickListener the interface listener
     */
    public void setClickListener(MUAvatarClickListener clickListener) {
        mClickListener = clickListener;
    }

    /**
     * Get the placeholder drawable image
     * @return the placeholder drawable image
     */
    public Drawable getPlaceholderImage() {
        return mPlaceholderImage;
    }

    /**
     * Set the placeholder drawable image
     * @param placeholderImage the placeholder drawable image
     */
    public void setPlaceholderImage(Drawable placeholderImage) {
        mPlaceholderImage = placeholderImage;
    }

    /**
     * Get the border width
     * @return the border width in dp
     */
    public float getBorderWidth() {
        return mBorderWidth;
    }

    /**
     * Set the border width
     * @param borderWidth the border width in pixels
     */
    public void setBorderWidth(float borderWidth) {
        mBorderWidth = borderWidth * mScale;
        invalidate();
    }

    /**
     * Get the border type
     * @return the border type as integer
     */
    public int getBorderType() {
        return mBorderType;
    }

    public void setBorderType(int borderType) {
        mBorderType = borderType == ROUND_BORDER ? ROUND_BORDER : SQUARE_BORDER;
        mCornerRadius = mBorderType == SQUARE_BORDER ?
                mCornerRadius : 1000;
        invalidate();
    }

    /**
     * Get the corner radius
     * @return the corner radius as float
     */
    public float getCornerRadius() {
        return mCornerRadius;
    }

    /**
     * Set the corner radius
     * @return the corner radius as float
     */
    public void setCornerRadius(float cornerRadius) {
        mCornerRadius = mBorderType == SQUARE_BORDER ?
                cornerRadius : 1000;
        invalidate();
    }

    /**
     * Determinates if the touch event is in the circle zone
     * @param x the x coordinate of touch
     * @param y the y coordinate of touch
     * @return true if the touch occurred in the circle, false otherwise
     */
    private boolean isInCircle(float x, float y) {
        double distance = Math.sqrt(
                Math.pow(mDrawableRect.centerX() - x, 2) + Math.pow(mDrawableRect.centerY() - y, 2)
        );
        return distance <= (mDrawableRect.width() / 2);
    }

    /**
     * Interface to handle user's interactions.
     */
    public interface MUAvatarClickListener {
        void clickOnImage(AppCompatImageView imageView);
    }
}
