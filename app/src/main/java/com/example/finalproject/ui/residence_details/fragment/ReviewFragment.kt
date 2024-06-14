package com.example.finalproject.ui.residence_details.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.retrofit.RetrofitClient
import com.example.finalproject.storage.AppReferences
import com.example.finalproject.storage.BaseActivity
import com.example.finalproject.ui.residence_details.adapter.ReviewAdapter
import com.example.finalproject.ui.residence_details.factory.GetReviewsFactory
import com.example.finalproject.ui.residence_details.repository.GetReviewsRepository
import com.example.finalproject.ui.residence_details.viewModel.GetReviewsViewModel
import org.json.JSONException
import org.json.JSONObject

class ReviewFragment : Fragment() {

    private lateinit var baseActivity: BaseActivity

    private lateinit var recyclerView: RecyclerView

    private lateinit var reviewAdapter: ReviewAdapter

    private lateinit var getReviewsViewModel: GetReviewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseActivity = BaseActivity()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycle_review_details)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        reviewAdapter = ReviewAdapter(mutableListOf())
        recyclerView.adapter = reviewAdapter

        initView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        val getReviewsRepository = GetReviewsRepository(RetrofitClient.instance)
        val factory = GetReviewsFactory(getReviewsRepository)
        getReviewsViewModel = ViewModelProvider(this, factory).get(GetReviewsViewModel::class.java)

        val token = AppReferences.getToken(requireContext())
        val residenceId = "6668f9b9b1d92be79eac207c" // Replace with your actual residenceId

        getReviewsViewModel.getReviews(token, residenceId)

        getReviewsViewModel.getReviewsResponseLiveData.observe(viewLifecycleOwner) { response ->
            baseActivity.hideProgressDialog()
            response?.let {
                val status = it.status
                Log.e("status review", status)

                Log.e("reviews", it.reviews.toString())

                reviewAdapter.list = it.reviews.toMutableList()
                reviewAdapter.notifyDataSetChanged()
            }
        }

        getReviewsViewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
            BaseActivity().hideProgressDialog()
            error?.let {
                try {
                    val errorMessage = JSONObject(error).getString("message")
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
