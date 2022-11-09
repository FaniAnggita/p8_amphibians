/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.amphibians.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.amphibians.databinding.FragmentAmphibianDetailBinding

/**
 * Fragmen ini menunjukkan informasi rinci tentang Amfibi tertentu
 */
class AmphibianDetailFragment : Fragment() {

    /*
    Mengembalikan delegasi properti untuk mengakses ViewModel kelas AmphibianViewModel
     */
    private val viewModel: AmphibianViewModel by activityViewModels()

    /*
    Fragmen Dipanggil untuk memiliki fragmen instantiate tampilan antarmuka pengguna.
    Ini akan dipanggil antara onCreate (Bundle) dan onViewCreated (View, Bundle).
    Tampilan default dapat dikembalikan dengan memanggil fragmen (int) di konstruktor. Jika tidak, metode ini mengembalikan null.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*
        Menetapkan pemilik siklus hidup yang harus digunakan untuk mengamati perubahan data langsung dalam pengikatan ini.
        Jika data langsung ada di salah satu ekspresi pengikatan dan tidak ada pemilik siklus hidup yang disetel,
        data langsung tidak akan diamati dan pembaruannya tidak akan disebarkan ke UI.
        */
        val binding = FragmentAmphibianDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        /*
        Mengembalikan tampilan Terluar dalam file tata letak yang terkait dengan pengikatan.
        Jika pengikatan ini untuk file tata letak gabungan, ini akan mengembalikan root pertama di tag gabungan.
        */
        return binding.root
    }
}
