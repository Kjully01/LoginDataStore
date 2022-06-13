package br.com.logindatastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.preferences.core.booleanPreferencesKey
import br.com.logindatastore.databinding.ActivityHomeBinding
import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listener()
    }

    private fun listener() {
        binding.btnSignOut.setOnClickListener {
            userLogOut()
        }
    }

    private fun userLogOut() {
        runBlocking {
            DataStoreManager.setDataStore(preferencesKey = booleanPreferencesKey("HAS_LOGIN"), false)
            val intent = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}