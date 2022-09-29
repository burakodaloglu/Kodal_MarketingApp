package com.burakkodaloglu.benimprojem.presentation.signup

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.burakkodaloglu.benimprojem.R
import com.burakkodaloglu.benimprojem.data.model.User
import com.burakkodaloglu.benimprojem.data.source.local.DataBaseHelper
import com.burakkodaloglu.benimprojem.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FragmentSignUp : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private lateinit var auth: FirebaseAuth

    //Sayfa açıldığın ilk çalışan kısım (Activity gibi düşün) Ama burada genelde yüklü bir veri çekilecekse sadece onun kodu olabilir. Başka herhangi bir logic gerek yok.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //View'ın çizildiği oluşturulduğu scope. Burada logic yazmazsan daha tatlı olur.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    //View artık oluşturuldu. Bu scope içerisinde tasarımla alakalı işlemleri gerçekleştirebilirsin.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.btnSignUp.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener { authResult ->
                    authResult.user?.let {
                        Toast.makeText(requireContext(), "Kayıt başarıyla oluşturuldu!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.signUpToSignIn)
                    }
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                if (TextUtils.isEmpty(email)) {
                    binding.etEmail.error = "Lütfen E-posta adresinizi oluşturunuz"
                }
                if (TextUtils.isEmpty(password)) {
                    binding.etPassword.error = "Lütfen Şifrenizi oluşturunuz"
                }
            }

            binding.etEmail.text.clear()
            binding.etPassword.text.clear()
        }

        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.signUpToSignIn)
        }
    }
}