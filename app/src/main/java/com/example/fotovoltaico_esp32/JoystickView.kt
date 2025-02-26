package com.example.fotovoltaico_esp32

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt

class JoystickView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var centerX = 0f
    private var centerY = 0f
    private var baseRadius = 0f
    private var handleRadius = 0f
    private var handleX = 0f
    private var handleY = 0f
    private var listener: ((Float, Float) -> Unit)? = null

    init {
        isClickable = true
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        centerX = width / 2f
        centerY = height / 2f
        baseRadius = min(centerX, centerY) * 0.8f
        handleRadius = baseRadius * 0.2f
        resetHandle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        // Dibujar círculo base
        paint.color = Color.LTGRAY
        canvas.drawCircle(centerX, centerY, baseRadius, paint)

        // Dibujar el mango
        paint.color = Color.BLUE
        canvas.drawCircle(handleX, handleY, handleRadius, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val dx = event.x - centerX
                val dy = event.y - centerY
                val distance = sqrt(dx * dx + dy * dy)

                if (distance < baseRadius) {
                    handleX = event.x
                    handleY = event.y
                } else {
                    val angle = atan2(dy, dx)
                    handleX = centerX + cos(angle) * baseRadius
                    handleY = centerY + sin(angle) * baseRadius
                }

                // Calcular posición normalizada (-1 a 1)
                val xPercent = (handleX - centerX) / baseRadius
                val yPercent = (handleY - centerY) / baseRadius

                // Notificar cambios
                listener?.invoke(xPercent, yPercent)
                invalidate()
            }

            //MotionEvent.ACTION_UP -> resetHandle()
        }
        return true
    }

    private fun resetHandle() {
        handleX = centerX
        handleY = centerY
        listener?.invoke(0f, 0f)
        invalidate()
    }

    fun setOnJoystickMoveListener(listener: (Float, Float) -> Unit) {
        this.listener = listener
    }
}
