package com.example.globoplay.views.lista

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globoplay.R
import com.example.globoplay.database.models.MyList
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.globoplay.databinding.FragmentListaBinding
import com.example.globoplay.domain.PopularMovie
import com.example.globoplay.viewmodel.ListaViewModel
import com.example.globoplay.views.MediaDetail
import com.example.globoplay.views.adapter.ClickItemMediaDetails
import com.example.globoplay.views.adapter.ClickItemMovieDetails
import com.example.globoplay.views.adapter.MediaAdapter
import com.example.globoplay.views.adapter.MovieAdapter
import com.squareup.picasso.Picasso

class ListaFragment : Fragment(), ClickItemMediaDetails {

    private var _binding: FragmentListaBinding? = null
    private val listaViewModel: ListaViewModel by viewModel()
    private lateinit var recyclerViewMedias: RecyclerView
    private lateinit var adapterList: MediaAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListaBinding.inflate(inflater, container, false)
        initMediaFavsList()
        return binding.root
    }

    private fun initMediaFavsList() {
        listaViewModel.mediaList.observe(viewLifecycleOwner){ medias ->
                val layoutManager = GridLayoutManager(context,4, RecyclerView.VERTICAL,false)

                recyclerViewMedias = binding.recyclerViewMedias
                recyclerViewMedias.layoutManager = layoutManager
                recyclerViewMedias.setHasFixedSize(true)
                adapterList = MediaAdapter(medias,this)
                recyclerViewMedias.adapter = adapterList
            }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onItemCLickListener(media: MyList) {
        val mediaDetails = Intent(context, MediaDetail::class.java)
        mediaDetails.putExtra("title",media.mediaName)
        mediaDetails.putExtra("description",media.description)
        mediaDetails.putExtra("poster", media.posterImage)
        mediaDetails.putExtra("voteAverage",media.voteAverage)
        mediaDetails.putExtra("releaseDate",media.releaseDate)
        startActivity(mediaDetails)
    }


}
