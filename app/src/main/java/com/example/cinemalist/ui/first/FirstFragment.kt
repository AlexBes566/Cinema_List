package com.example.cinemalist.ui.first

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemalist.databinding.FragmentFirstBinding
import com.example.cinemalist.ui.adapters.MovieAdapter
import com.example.cinemalist.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val mBinding get() = _binding!!

    private val viewModel by viewModels<FirstViewModel>()


    lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(layoutInflater, container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.movieLiveData.observe(viewLifecycleOwner) { response ->
           when (response) {
                is Resource.Success -> {
                    cardViewListLoading.visibility = View.INVISIBLE
                    response.data?.let {
                        movieAdapter.differ.submitList(it.items)
                    }
                }
                is Resource.Error -> {
                    cardViewListLoading.visibility = View.INVISIBLE
                    response.data?.let {
                        Log.e("checkData", "FirstFragment: error ${it}")
                    }

                }
                is Resource.Loading -> {
                    cardViewListLoading.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initAdapter() {
        movieAdapter = MovieAdapter()
        recycler_view.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
