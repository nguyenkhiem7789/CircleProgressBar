package com.nguyen.circleprogressview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CircleProgressView: View {

    companion object {
        const val DEFAULT_BACKGROUND_COLOR = Color.LTGRAY

        const val DEFAULT_PRIMARY_COLOR = Color.RED

        const val DEFAULT_TEXT_COLOR = Color.RED

        const val DEFAULT_STROKE_WIDTH = 10

        const val DEFAULT_TEXT_SIZE = 50
    }

    private var bgColor = DEFAULT_BACKGROUND_COLOR

    private var primaryColor = DEFAULT_PRIMARY_COLOR

    private var textColor = DEFAULT_TEXT_COLOR

    private var strokeWidth = DEFAULT_STROKE_WIDTH

    private var textSize = DEFAULT_TEXT_SIZE

    private val primaryPaint: Paint by lazy {
        Paint()
    }

    private val backgroundPaint: Paint by lazy {
        Paint()
    }

    private val centerTextPaint: Paint by lazy {
        Paint()
    }

    private val textBound: Rect by lazy {
        Rect()
    }

    private var rect: RectF? = null

    private var progress: Int = 0

    constructor(context: Context) : this(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView)
        bgColor = typeArray.getColor(R.styleable.CircleProgressView_pro_background_color, DEFAULT_BACKGROUND_COLOR)
        primaryColor = typeArray.getColor(R.styleable.CircleProgressView_pro_primary_color, DEFAULT_PRIMARY_COLOR)
        textColor = typeArray.getColor(R.styleable.CircleProgressView_pro_text_color, DEFAULT_TEXT_COLOR)
        strokeWidth = typeArray.getDimensionPixelSize(R.styleable.CircleProgressView_pro_stroke_width, DEFAULT_STROKE_WIDTH)
        textSize = typeArray.getDimensionPixelSize(R.styleable.CircleProgressView_pro_text_size, DEFAULT_TEXT_SIZE)
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        primaryPaint.isAntiAlias = true
        primaryPaint.color = primaryColor
        primaryPaint.strokeWidth = strokeWidth.toFloat()
        primaryPaint.style = Paint.Style.STROKE

        backgroundPaint.isAntiAlias = true
        backgroundPaint.color = bgColor
        backgroundPaint.strokeWidth = strokeWidth.toFloat()
        backgroundPaint.style = Paint.Style.STROKE

        centerTextPaint.isAntiAlias = true
        centerTextPaint.color = textColor
        centerTextPaint.textSize = textSize.toFloat()
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = 0
        var height = 0

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if(widthMode == MeasureSpec.EXACTLY) {
            width = widthSize
        } else if(widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(widthSize, heightSize)
        } else {
            width = 0
        }

        if(heightMode == MeasureSpec.EXACTLY) {
            height = heightSize
        } else if(heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(widthSize, heightSize)
        } else {
            height = 0
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if(rect == null) {
            rect = RectF(strokeWidth.toFloat(),
                strokeWidth.toFloat(), width.toFloat() - strokeWidth, height.toFloat() - strokeWidth)
        }
        canvas.drawArc(rect, 270.0f, 360.0f, false, backgroundPaint)
        canvas.drawArc(rect, 270.0f, (360.0f / 100.0f) * progress, false, primaryPaint)
        val text = progress.toString().plus("%")
        centerTextPaint.getTextBounds(text, 0, text.length, textBound)
        canvas.drawText(text, (width/2).toFloat() - textBound.exactCenterX(),
            (height/2).toFloat() - textBound.exactCenterY(), centerTextPaint)
    }
}