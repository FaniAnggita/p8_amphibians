
package com.example.amphibians

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.amphibians.network.Amphibian
import com.example.amphibians.ui.AmphibianApiStatus
import com.example.amphibians.ui.AmphibianListAdapter

/**
 * Update data yang ditampilkan dalam [RecyclerView]
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Amphibian>?) {
    val adapter = recyclerView.adapter as AmphibianListAdapter
    adapter.submitList(data)
}

/**
 * Adaptor pengikatan ini menampilkan [AmphibianApiStatus] dari permintaan jaringan dalam tampilan gambar.
 * Ketika permintaan sedang loading, ini akan menampilkan loading_animation sebuah.  Jika permintaan memiliki kesalahan, itu
 * menampilkan gambar yang rusak untuk mencerminkan kesalahan koneksi.  Ketika permintaan selesai, itu
 * menyembunyikan tampilan gambar.
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: AmphibianApiStatus?) {
    when(status) {
        // Menampilkan drawable 'loading_animation' jika gambar sedang dimuat.
        AmphibianApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        // Menampilkan image jika berhasil dibuat
        AmphibianApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }

        // Menampilkan drawable 'ic_connection_error' jika gambar sedang dimuat.
        AmphibianApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}
