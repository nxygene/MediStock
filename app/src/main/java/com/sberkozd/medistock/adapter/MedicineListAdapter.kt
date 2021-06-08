package com.sberkozd.medistock.adapter

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sberkozd.medistock.R
import com.sberkozd.medistock.data.Medicine
import com.sberkozd.medistock.databinding.MedicineListBinding
import com.sberkozd.medistock.ui.home.StockUpdateListener


class MedicineListAdapter(var listener: StockUpdateListener, var isAdmin: Boolean) :
    RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>() {

    private val items: MutableList<Medicine> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val binding = MedicineListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MedicineViewHolder(binding)
    }

    fun setItems(items: List<Medicine>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.binding.apply {
            this.mediRecyclerViewImage.load(items[position].image)
            this.mediRecyclerViewName.text = items[position].name
            this.mediRecyclerViewStock.text = items[position].stock.toString()
            if (isAdmin) {
                root.setOnClickListener {
                    val dialog = MaterialAlertDialogBuilder(it.context)
                        .setPositiveButton("Confirm") { dialogInterface, which ->
                            val editText =
                                (dialogInterface as AlertDialog).findViewById<EditText>(R.id.search)
                            listener.onUpdate(items[position], editText!!.text.toString())
                            Toast.makeText(it.context, "Updated!", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("Cancel", null)
                        .setView(R.layout.fragment_custom_dialog).show()

                    dialog.findViewById<EditText>(R.id.search)!!.apply {
                        inputType = InputType.TYPE_CLASS_NUMBER
                        setText(items[position].stock.toString())
                    }
                }
            }
        }

    }


    override fun getItemCount() =
        items.size

    class MedicineViewHolder(val binding: MedicineListBinding) :
        RecyclerView.ViewHolder(binding.root)
}



