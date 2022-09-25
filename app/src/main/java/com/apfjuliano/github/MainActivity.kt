package com.apfjuliano.github

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

private const val PREF_EMAIL = "email"
private const val PREF_SIGN_IN_UP = "sign_in_up"
private const val PREF = "GITHUB_PREFERENCES" //Name of stored preferences

class MainActivity : AppCompatActivity() {
    var signIn = true

    fun onRadioButtonClicked(view: View) {
        showToast("Clicou")
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rdb_sign_in ->
                    if (checked) {
                        signIn = true
                        showToast("Sign in")
                    }
                R.id.rdb_sign_up ->
                    if (checked) {
                        signIn = false
                        showToast("Sign up")
                    }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtEmail = findViewById<EditText>(R.id.edt_email)
        val edtPassword = findViewById<EditText>(R.id.edt_password)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val preferences = getSharedPreferences(PREF, Context.MODE_PRIVATE)

        edtEmail.setText(preferences.getString(PREF_EMAIL, ""))
        signIn = preferences.getBoolean(PREF_SIGN_IN_UP, true)

        btnLogin.setOnClickListener {
            val editPreferences = preferences.edit()
            editPreferences.putString(PREF_EMAIL, edtEmail.text.toString())
            editPreferences.putBoolean(PREF_SIGN_IN_UP, signIn)
            editPreferences.apply()
        }
    }

    private fun showToast(eventName: String) {
        Toast.makeText( this, eventName, Toast.LENGTH_SHORT).show()
    }
}

