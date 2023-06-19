package com.example.productsonline

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import org.json.JSONArray
import org.json.JSONObject
import java.io.File


class ProductsListFragment : Fragment() {
    private lateinit var communicator: Communicator
    private lateinit var productsListAdapter: ProductsListAdapter
    private lateinit var productsList: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = context as Communicator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsList = view.findViewById(R.id.productList)
        productsListAdapter = ProductsListAdapter {
            communicator.onProductClicked(it)
        }

        productsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = productsListAdapter
        }

       //Second lab start
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints)
            .build()


        val workManager = WorkManager.getInstance(requireContext())
        workManager.enqueue(workRequest)
        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(viewLifecycleOwner) {
            when (it.state) {
                WorkInfo.State.SUCCEEDED -> {
                    val file = File(context?.filesDir, "data.txt")
                    val data = file.readBytes()
                    val dataList = convertBytesToObjectList(data)
                    productsListAdapter.submitList(dataList)

                }

                WorkInfo.State.FAILED -> {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }

                WorkInfo.State.RUNNING -> {
                }

                WorkInfo.State.BLOCKED -> {
                }

                WorkInfo.State.CANCELLED -> {
                }

                WorkInfo.State.ENQUEUED -> {
                }
            }
        }
        //Second lab end

    }

    private fun convertBytesToObjectList(data: ByteArray?): List<Product> {
        val jsonString = data?.let { String(it, Charsets.UTF_8) } ?: ""
        val jsonObjectParent = JSONObject(jsonString)
        val productsArray = jsonObjectParent.getJSONArray("products")

        val dataList = mutableListOf<Product>()

        for (i in 0 until productsArray.length()) {
            val jsonObject = productsArray.getJSONObject(i)
            val product = Product(
                jsonObject.getInt("id"),
                jsonObject.getString("title"),
                jsonObject.getString("description"),
                jsonObject.getDouble("price"),
                jsonObject.getDouble("rating"),
                jsonObject.getString("brand"),
                jsonObject.getString("thumbnail"),
                )

            dataList.add(product)
        }

        return dataList
    }


}