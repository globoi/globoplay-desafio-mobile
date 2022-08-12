package com.example.globoplay.views

import android.accounts.AuthenticatorDescription
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.RoomDatabase
import androidx.viewpager2.widget.ViewPager2
import com.example.globoplay.R
import com.example.globoplay.database.AppDatabase
import com.example.globoplay.database.daos.MyListDao
import com.example.globoplay.database.models.MyList
import com.example.globoplay.databinding.ActivityMovieDetailBinding
import com.example.globoplay.viewmodel.ListaViewModel
import com.example.globoplay.viewmodel.MovieViewModel
import com.example.globoplay.views.adapter.MovieAdapter
import com.example.globoplay.views.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class MediaDetail() : AppCompatActivity() {

    private lateinit var binding:ActivityMovieDetailBinding
    private val listaViewModel: ListaViewModel by viewModel()
    private lateinit var tabLayout: TabLayout
    private lateinit var resume:String
    private lateinit var viewPager:ViewPager2
    private lateinit var myListDao: MyListDao
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.database = AppDatabase.getInstance(this)
        this.myListDao = this.database.myListDao()
        getData()
    }

    override fun onStart() {
        super.onStart()
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager
        viewPager.adapter = PagerAdapter(this,resume)

        TabLayoutMediator(tabLayout,viewPager){ tab, index ->
            tab.text = when(index){
                0 -> {"Assista Também"}
                1-> {"Detalhes"}
                else->{throw Resources.NotFoundException("Position failed")}
            }

        }.attach()
    }

    private fun getData(){
        val nome = intent.getStringExtra("title")
        val description = intent.getStringExtra("description").toString()
        val poster = intent.getStringExtra("poster")
        val releaseDate = intent.getStringExtra("releaseDate")
        val voteAverage = intent.getStringExtra("voteAverage")

        resume = "Nome: $nome\nData de Lançamento: $releaseDate\nNota IMDB: $voteAverage\n"

        Picasso.get().load("https://image.tmdb.org/t/p/original$poster")
           .transform(BlurTransformation(this,40))
           .into(binding.mediaBackground)

        Picasso.get().load("https://image.tmdb.org/t/p/w500$poster")
          .into(binding.mediaPoster)

        binding.mediaTitle.text = nome.toString()
        binding.mediaDescription.text = description

        this.binding.btnMylist.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                if (nome != null) {
                    saveMedia(nome,description,poster,releaseDate,voteAverage)
                }
            }
        }

        this.binding.btnMylistRemove.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                if (nome != null) {
                    deleteMedia(nome)
                }
            }
        }
    }

    private suspend fun saveMedia(title:String,description: String?, poster:String?,releaseDate:String?,voteAverage:String?):Boolean{
        listaViewModel.save(MyList(title,description,poster,releaseDate,voteAverage))
        return true
    }

    private suspend fun deleteMedia(title: String){
        listaViewModel.delete(title)
    }
}



