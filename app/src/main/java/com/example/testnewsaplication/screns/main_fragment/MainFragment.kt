package com.example.testnewsaplication.screns.main_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.testnewsaplication.util.MAIN
import com.example.testnewsaplication.R
import com.example.testnewsaplication.databinding.FragmentMainBinding
import com.example.testnewsaplication.model_response.NewsContentResponse
import com.example.testnewsaplication.network_observer.NetworkActive
import com.example.testnewsaplication.rv_adapter.AdapterRv
import com.example.testnewsaplication.rv_adapter.NewsLoaderStateAdapter
import com.example.testnewsaplication.rv_adapter.SetOnClick
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    lateinit var  adapterRv:AdapterRv

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var networkActive: NetworkActive

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initDataBase()
        clearBd()

        networkActive= NetworkActive(requireContext())
        networkActive.observe(viewLifecycleOwner){ isConnected ->
            if (isConnected)
                init()
        }
    }

    private fun init(){
        adapterRv= AdapterRv(object : SetOnClick{
            override fun onClickElement(mappingModel: NewsContentResponse) {
                val bundle=Bundle()
                bundle.putString("newsUrl",mappingModel.url)
                MAIN.navController.navigate(R.id.action_mainFragment_to_viewWebFragment,bundle)
            }
        } )
        binding.recyclerView.adapter=adapterRv.withLoadStateFooter(
            footer = NewsLoaderStateAdapter()
        )
        adapterRv.addLoadStateListener {
            binding.loadingBar.isVisible=it.refresh is LoadState.Loading

        }
        lifecycleScope.launch{
            viewModel.flow.collect {
                adapterRv.submitData(it)

            }
        }
    }

    private fun clearBd(){
        lifecycleScope.launch {
            viewModel.getAllNews()?.forEach(){
                viewModel.delete(it){}
            }
        }
    }

}