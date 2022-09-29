package com.burakkodaloglu.benimprojem.presentation.signin

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.burakkodaloglu.benimprojem.R
import com.burakkodaloglu.benimprojem.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FragmentSignIn : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.btnSignIn.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener { authResult ->
                    authResult.user?.let {
                        Toast.makeText(requireContext(), "Giriş başarılı!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.signInToMainGraph)
                    }
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }   else {

                if (TextUtils.isEmpty(email)) {
                    binding.etEmail.error = "Lütfen E-posta adresinizi giriniz"
                }

                if (TextUtils.isEmpty(password)) {
                    binding.etPassword.error = "Lütfen Şifrenizi giriniz"
                }
            }
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.signInToSignUp)
        }
    }
}