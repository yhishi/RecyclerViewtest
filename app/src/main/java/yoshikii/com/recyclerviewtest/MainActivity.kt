package yoshikii.com.recyclerviewtest

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import yoshikii.com.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = MyAdapter(this, users.map { it.name }.toMutableList())
        binding.recyclerView.adapter = adapter
    }
}

internal data class User(
    val id: Int,
    val name: String
)

private val users = listOf(
    User(1, "yoshikii"),
    User(3, "tanaka"),
    User(4,"sato")
)