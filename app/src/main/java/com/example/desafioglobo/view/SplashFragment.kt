package com.example.desafioglobo.view

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.desafioglobo.R
import com.example.desafioglobo.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.splash_animation)
        binding.mainText.startAnimation(animation)

        val paint = binding.mainText.paint
        val width = paint.measureText(binding.mainText.toString())
        val textShader: Shader = LinearGradient(0f, 0f, width, binding.mainText.textSize, intArrayOf(
            Color.parseColor("#fb0130"),
            Color.parseColor("#ff9001"),
        ), null, Shader.TileMode.REPEAT)

        binding.mainText.paint.shader = textShader

        Handler(Looper.getMainLooper()).postDelayed({
            openHome()
        }, 3000)
    }

    private fun openHome() {
        Navigation.findNavController(requireView()).navigate(R.id.homeFragment)
    }
}