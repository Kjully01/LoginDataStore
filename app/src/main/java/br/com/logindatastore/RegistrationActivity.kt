package br.com.logindatastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.logindatastore.databinding.ActivityRegistrationBinding
import kotlinx.coroutines.runBlocking

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listener()
    }

    private fun listener() {
        binding.run {
            btnRegistration.setOnClickListener {
                if(etNameLogin.text.isNotEmpty() && etPassword.text.isNotEmpty() && etCPF.text.isNotEmpty() && etAge.text.isNotEmpty()){
                    saveUser()
                    val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@RegistrationActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveUser(){
        runBlocking {
            DataStoreManager.setDataStore(
                preferencesKey = stringPreferencesKey("NAME"),
                value = binding.etNameLogin.text.toString()
            )
            DataStoreManager.setDataStore(
                preferencesKey = stringPreferencesKey("PASSWORD"),
                value = binding.etPassword.text.toString()
            )
            DataStoreManager.setDataStore(
                preferencesKey = stringPreferencesKey("CPF"),
                value = binding.etCPF.text.toString()
            )
            DataStoreManager.setDataStore(
                preferencesKey = intPreferencesKey("AGE"),
                value = binding.etAge.text.toString().toInt()
            )
            Toast.makeText(this@RegistrationActivity, "Salvo com sucesso", Toast.LENGTH_SHORT).show()
        }
    }
}