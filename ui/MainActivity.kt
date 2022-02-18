package zfani.assaf.fido.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import zfani.assaf.fido.databinding.ActivityMainBinding
import zfani.assaf.fido.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}