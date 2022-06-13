package br.com.logindatastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.datastore.preferences.core.booleanPreferencesKey
import br.com.logindatastore.databinding.ActivitySplashBinding
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            verifyUserLogged()
            finish()
        }, 3000)
    }

    private fun verifyUserLogged(){
        runBlocking {
            val hasLogin = DataStoreManager.readDataStore(booleanPreferencesKey("HAS_LOGIN")) ?: false
            if(!hasLogin){
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}