package com.burakkodaloglu.benimprojem.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat.animate
import androidx.navigation.fragment.findNavController
import com.burakkodaloglu.benimprojem.R
import com.burakkodaloglu.benimprojem.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        CoroutineScope(Dispatchers.Main).launch {

            binding.logo.apply {
                alpha = 0f
                animate().alpha(1f).duration = 2000
            }

            delay(2000)

            auth.currentUser?.let {
                findNavController().navigate(R.id.splashToMainGraph)
            } ?: this@SplashFragment.run {
                findNavController().navigate(R.id.splashToSignIn)
            }
        }
    }
}