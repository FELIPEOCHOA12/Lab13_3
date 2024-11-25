package com.example.animationsapp

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
//Felipe Hebert Ochoa Patiño
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSimpleValue = findViewById<Button>(R.id.btnSimpleValue)
        val btnVisibility = findViewById<Button>(R.id.btnVisibility)
        val btnSizeChange = findViewById<Button>(R.id.btnSizeChange)
        val btnMultipleValues = findViewById<Button>(R.id.btnMultipleValues)
        val btnRepeatAnimation = findViewById<Button>(R.id.btnRepeatAnimation)
        val btnGestureAnimation = findViewById<Button>(R.id.btnGestureAnimation)

        btnSimpleValue.setOnClickListener {
            animateColorChange(it, Color.RED, Color.BLUE)
        }

        btnVisibility.setOnClickListener {
            it.visibility = if (it.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
        }

        btnSizeChange.setOnClickListener {
            animateSizeChange(it, 200, 100)
        }

        btnMultipleValues.setOnClickListener {
            animateColorAndSize(it)
        }

        btnRepeatAnimation.setOnClickListener {
            repeatColorAnimation(it, Color.GREEN, Color.YELLOW)
        }

        btnGestureAnimation.setOnClickListener {
            // Implement gesture-related animations if required
            animateColorChange(it, Color.MAGENTA, Color.CYAN)
        }
    }

    private fun animateColorChange(view: View, startColor: Int, endColor: Int) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
        colorAnimation.duration = 1000
        colorAnimation.addUpdateListener { animator ->
            view.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()
    }

    private fun animateSizeChange(view: View, startWidth: Int, endWidth: Int) {
        val animator = ValueAnimator.ofInt(startWidth, endWidth)
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            val newWidth = animation.animatedValue as Int
            val params = view.layoutParams
            params.width = newWidth
            view.layoutParams = params
        }
        animator.start()
    }

    private fun animateColorAndSize(view: View) {
        animateColorChange(view, Color.LTGRAY, Color.DKGRAY)
        animateSizeChange(view, 150, 300)
    }

    private fun repeatColorAnimation(view: View, startColor: Int, endColor: Int) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
        colorAnimation.duration = 1000
        colorAnimation.repeatCount = ValueAnimator.INFINITE
        colorAnimation.repeatMode = ValueAnimator.REVERSE
        colorAnimation.addUpdateListener { animator ->
            view.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()
    }
}
