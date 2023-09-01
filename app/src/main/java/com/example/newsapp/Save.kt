package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.FragmentSaveBinding
import com.example.newsapp.presentation.adaptor.RecycleAdaptor
import com.example.newsapp.presentation.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Save.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class Save : Fragment() {
    private lateinit var binding: FragmentSaveBinding
    private val viewModel: NewsViewModel by viewModels()

    @Inject
     lateinit var adaptors: RecycleAdaptor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSaveBinding.bind(view)

       adaptors.setOnItemClick {
           val bundle = Bundle().apply {
               putSerializable("selected_article",it)
           }

           findNavController().navigate(
               R.id.action_save_to_info ,
               bundle

           )
       }
        initRecycle()

        viewModel.getSaveNews().observe(viewLifecycleOwner) {
            adaptors.differ.submitList((it))
        }

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val data = adaptors.differ.currentList[position]
                viewModel.deleteNews(data)
                Snackbar.make(view,"Delete Success",Snackbar.LENGTH_LONG).apply {
                    setAction("UNDO") {
                        viewModel.saveNews(data)
                    }
                    show()
                }
            }

        }

        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.listSave)
        }
    }

    fun initRecycle() {
        adaptors = this.adaptors

        binding.listSave.apply {
            adapter = adaptors
            layoutManager = LinearLayoutManager(requireContext())
        }
    }



}