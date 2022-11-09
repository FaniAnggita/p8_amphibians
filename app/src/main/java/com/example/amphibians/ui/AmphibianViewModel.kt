
package com.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel : ViewModel() {

    // TODO: Create properties to represent MutableLiveData and LiveData for the API status
    //Membuat Live Data yang bisa berubah tanpa nilai yang ditugaskan untuk itu.
    private val _status = MutableLiveData<AmphibianApiStatus>()

    /*
    Live Data adalah kelas pemegang data yang dapat diamati dalam siklus hidup tertentu.
    Ini berarti bahwa pengamat dapat ditambahkan dalam pasangan dengan LifecycleOwner.
     */
    val status : LiveData<AmphibianApiStatus> = _status

    // TODO: Create properties to represent MutableLiveData and LiveData for a list of amphibian objects
    private val _amphibians = MutableLiveData<List<Amphibian>>()
    val amphibians : LiveData<List<Amphibian>> = _amphibians
    // TODO: Create properties to represent MutableLiveData and LiveData for a single amphibian object.
    //  This will be used to display the details of an amphibian when a list item is clicked
    private val _amphibian = MutableLiveData<Amphibian>()
    val amphibian : LiveData<Amphibian> = _amphibian
    // TODO: Create a function that gets a list of amphibians from the api service and sets the
    //  status via a Coroutine
    fun getAmphibianList(){
        /*
        Lingkup Coroutine terkait dengan ViewModel ini. Lingkup ini akan dibatalkan ketika ViewModel akan dihapus, yaitu ViewModel.
         */
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try {
                _amphibians.value = AmphibianApi.retrofitService.getAmphibians()
                _status.value = AmphibianApiStatus.DONE
            }
            catch(e: Exception) {
                _amphibians.value = listOf()
                _status.value = AmphibianApiStatus.ERROR
            }
        }

    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        // TODO: Set the amphibian object
        _amphibian.value = amphibian
    }
}
