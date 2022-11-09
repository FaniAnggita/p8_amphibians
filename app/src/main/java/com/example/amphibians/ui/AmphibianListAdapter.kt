package com.example.amphibians.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.amphibians.databinding.ListViewItemBinding
import com.example.amphibians.network.Amphibian

/**
 * Kelas ini mengimplementasikan [RecyclerView] [ListAdapter] yang menggunakan data mengikat untuk menyajikan [List]
 * data, termasuk komputasi diffs antara daftar.
 */
class AmphibianListAdapter(val clickListener: AmphibianListener) :
    ListAdapter<Amphibian, AmphibianListAdapter.AmphibianViewHolder>(DiffCallback) {

    class AmphibianViewHolder(
        // Melakukan binding terhadap navigasi
        var binding: ListViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root){
        /*
        Mengevaluasi binding tertunda, memperbarui setiap pandangan yang memiliki ekspresi terikat variabel dimodifikasi.
        Ini harus dijalankan di utas UI.
         */
        fun bind(clickListener: AmphibianListener, amphibian: Amphibian) {
            binding.amphibian = amphibian
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Amphibian>() {
        //Dipanggil untuk memeriksa apakah dua objek mewakili item yang sama.
        override fun areItemsTheSame(oldItem: Amphibian, newItem: Amphibian): Boolean {
            return oldItem.name == newItem.name
        }

        /*
        Dipanggil untuk memeriksa apakah dua item memiliki data yang sama.
        Informasi ini digunakan untuk mendeteksi jika isi item telah berubah.
         */
        override fun areContentsTheSame(oldItem: Amphibian, newItem: Amphibian): Boolean {
            return oldItem.type == newItem.type && oldItem.description == newItem.description
        }

    }

    /*
    Dipanggil saat RecyclerView membutuhkan tampilan Recycler baru. ViewHolder tipe Viewholder untuk mewakili item.
    ViewHolder baru ini harus dibangun dengan tampilan baru yang dapat mewakili item dari jenis yang diberikan.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmphibianViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AmphibianViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    /*
    Dipanggil oleh RecyclerView untuk menampilkan data pada posisi yang ditentukan. Metode ini harus memperbarui konten tampilan pendaur ulang. ViewHolder.
    itemView untuk mencerminkan item pada posisi yang diberikan.
     */
    override fun onBindViewHolder(holder: AmphibianViewHolder, position: Int) {
        val amphibian = getItem(position)
        holder.bind(clickListener, amphibian)
    }
}

class AmphibianListener(val clickListener: (amphibian: Amphibian) -> Unit) {
    fun onClick(amphibian: Amphibian) = clickListener(amphibian)
}
