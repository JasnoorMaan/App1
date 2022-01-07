package com.example.animsplashkotlin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View.inflate
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import com.example.animsplashkotlin.databinding.ActivityHomeBinding.inflate
import com.example.animsplashkotlin.databinding.ActivityloginactivityBinding
import com.example.animsplashkotlin.databinding.ActivityloginactivityBinding.inflate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException

class loginactivity : AppCompatActivity() {

    private lateinit var binding: ActivityloginactivityBinding

    private lateinit var actionBar: ActionBar

    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityloginactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Login"

        progressDialog = ProgressDialog( this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding noAccountTv.setOnClickListener {
            startActivity(Intent(this,registerpage::class.java))
        }

        binding.loginBtn.setOnClickListener{
            validateData()
        }

    }

    private fun validateData() {
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEt.error="Invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            binding.passwordEt.error = "Please enter password"
    }
    else {
        firebaseLogin()
    }

}

    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"Logged in as $email", Toast.LENGTH_SHORT.show
                    startActivity(Intent( this,ProfileActivity::class.java))
                finish(0))
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this,"Login failed due to ${e.message}", Toast.LENGTH_SHORT)
            }
    }


    private fun checkUser() {
        val firebaseUser = FirebaseAuth.currentuser
        if (firebaseUser != null) {
            startActivity(Intent(this, activity_home::class.java))
            finish()
        }
    }
}