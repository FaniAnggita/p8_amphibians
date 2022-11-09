package com.example.amphibians.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// TODO: Create a property for the base URL provided in the codelab
private const val BASE_URL =
    "https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2/"
// TODO: Build the Moshi object with Kotlin adapter factory that Retrofit will be using to parse JSON
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// TODO: Build a Retrofit object with the Moshi converter
/*
Retrofit mengadaptasi antarmuka Java ke panggilan HTTP dengan menggunakan anotasi
pada metode yang dideklarasikan untuk menentukan bagaimana permintaan dibuat.
Buat instance menggunakan builder dan lewati antarmuka Anda untuk membuat untuk menghasilkan implementasi.
 */
private val retrofit = Retrofit.Builder()
     // untuk serialisasi dan deserialisasi objek
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AmphibianApiService {
    // TODO: Declare a suspended function to get the list of amphibians
    // Membuat method GET untuk request file json.
    @GET("android-basics-kotlin-unit-4-pathway-2-project-api.json")
    suspend fun getAmphibians(): List<Amphibian>
}

// TODO: Create an object that provides a lazy-initialized retrofit service
object AmphibianApi {
    val retrofitService : AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java) }
}
