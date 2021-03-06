package br.com.logindatastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.logindatastore.databinding.ActivityLoginBinding
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listener()
    }

    private fun listener() {
        binding.apply {
            btnLogin.setOnClickListener {
                verifyUser()
            }

            btnCreateAccount.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun verifyUser() {
        val name = binding.etLogin.text.toString()
        val password = binding.etPassword.text.toString()

        runBlocking {
            if (DataStoreManager.readDataStore(stringPreferencesKey("NAME")) == name
                && DataStoreManager.readDataStore(stringPreferencesKey("PASSWORD")) == password
            ) {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
                DataStoreManager.setDataStore(preferencesKey = booleanPreferencesKey("HAS_LOGIN"), true)
            } else {
                Toast.makeText(this@LoginActivity, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}