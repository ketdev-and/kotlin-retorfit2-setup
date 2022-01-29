package com.example.retorfit_setup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retorfit_setup.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    private lateinit var  todoAdapter: Retro_Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerview();

        lifecycleScope.launchWhenCreated{
            binding.retroProgress.isVisible = true
            val response = try {
                RetroInstance.api.getTodo()
            }catch (e: IOException){
                Log.e("INTERNET ERROR", "you might have internet connection failure", )
                binding.retroProgress.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e("SERVER ERROR", "failed to connect to server", )
                binding.retroProgress.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                todoAdapter.todos = response.body()!!
            }else{
                Log.e("SERVER ERROR", "failed to connect to server", )
            }
            binding.retroProgress.isVisible = false
        }
    }

    private fun setupRecyclerview() = binding.retroList.apply {
        todoAdapter = Retro_Adapter();
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}