package com.example.retorfit_setup

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retorfit_setup.databinding.RetroTodoBinding

class Retro_Adapter : RecyclerView.Adapter<Retro_Adapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding:RetroTodoBinding) : RecyclerView.ViewHolder(binding.root)
    private val diffCallback = object : DiffUtil.ItemCallback<RetroData>(){
        override fun areItemsTheSame(oldItem: RetroData, newItem: RetroData): Boolean {
            return oldItem.id == newItem.id;
        }

        override fun areContentsTheSame(oldItem: RetroData, newItem: RetroData): Boolean {
            return oldItem == newItem;
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var todos : List<RetroData>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun getItemCount(): Int {
          return todos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(RetroTodoBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
         holder.binding.apply {
             val todo = todos[position]

             retroTitle.text = todo.title;
             retroCheckBox.isChecked = todo.completed

         }
    }


}