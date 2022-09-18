package com.makeup.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.makeup.R
import com.makeup.databinding.FragmentItemsBinding
import com.makeup.model.repository.Repository
import com.makeup.view.adapter.MainAdapter
import com.makeup.viewmodel.MainViewModel
import com.makeup.viewmodel.MainViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentItemsBinding? = null

    private val binding get() = _binding!!

    private val mainAdapter by lazy{MainAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    /*
    In simple terms, the onViewCreated() is called once the view/screen is created,
    it runs after the onCreateView() an it is safer to use as method is called after the
    view has been created

    In it is the Repository which connects the data gotten from the API
    into the ViewModel

     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        try {
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            viewModel.getItems()

            /* This observes the respinse and pass it into the MainAdapter for the
            data to be displayed in the RecyclerView
             */
            viewModel.itemsResponse.observe(viewLifecycleOwner) { response ->
                if(response.isSuccessful){
                    response.body()?.let { mainAdapter.setData(it) }
                    binding.progressBar.visibility = View.GONE
                }

                else{
                    Toast.makeText(requireContext(), "Data could not be gotten", Toast.LENGTH_SHORT).show()
                }

            }

        }catch (e: Exception){
            Toast.makeText(requireContext(), "Error! Please reload the app", Toast.LENGTH_SHORT).show()
        }


    }

    /*
    setupRecyclerView() binds the recyclerView's adapter to the MainAdapter
    and also defines the layout of the RecyclerView to be in a grid form
     */
    private fun setupRecyclerView(){
        binding.recyclerView.adapter = mainAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
        // return inflater.inflate(R.layout.fragment_items, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}