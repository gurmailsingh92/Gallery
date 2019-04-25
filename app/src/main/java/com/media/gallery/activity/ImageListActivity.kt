package com.media.gallery.activity

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dev.activity.BaseActivity
import com.dev.data.domain.entity.ImageModelEntity
import com.media.gallery.Constants
import com.media.gallery.EndlessRecyclerOnScrollListenerGrid
import com.media.gallery.R
import com.media.gallery.Utils
import com.media.gallery.controllers.ImageAdapter
import com.media.gallery.fragment.ImageDialogFragment
import com.media.gallery.interfaces.ImageClickListener
import com.media.gallery.viewmodel.ImageViewModel
import kotlinx.android.synthetic.main.activity_image_list.*
import java.util.*


class ImageListActivity : BaseActivity(), ImageClickListener {

    private lateinit var viewModel: ImageViewModel

    private lateinit var adapter: ImageAdapter
    private lateinit var layoutManager: GridLayoutManager

    private var gridValue: Int = Constants.GRID_SPAN_DEFAULT
    private var query: String = ""

    private var endlessScrollListener: EndlessRecyclerOnScrollListenerGrid? = null
    private var isApiRunning: Boolean = false
    private var isLastItemFound: Boolean = false
    private val imageList by lazy {
        arrayListOf<ImageModelEntity>()
    }

    private val imagesObserver: Observer<Triple<Boolean, List<ImageModelEntity>?, Error?>> =
        Observer {
            isApiRunning = false
            loader.visibility = View.GONE
            loadMoreLoader.visibility = View.GONE

            if (it.second?.isNotEmpty() == true) {
                query = (etQuery.text.toString().trim())
                ivPlaceholder.visibility = View.GONE
            }
            if (it.first) {
                it.second?.forEach { imageModel ->
                    imageList.add(imageModel)
                }
                adapter.submitList(imageList)
            } else {
                if (imageList.isEmpty()) {
                    if (Utils.isOnline(this@ImageListActivity)) {
                        Toast.makeText(this@ImageListActivity, it.third?.message, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this@ImageListActivity,
                            getString(R.string.no_internet_connectivity),
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                } else {
                    endlessScrollListener?.decreasePagingCount()
                    if (!Utils.isOnline(this@ImageListActivity)) {
                        Toast.makeText(this@ImageListActivity, getString(R.string.get_more_results), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }


    override fun initViewModel() {
        viewModel = ViewModelProvider.NewInstanceFactory().create(ImageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)
        setUpToolbar()
        setController()
        setListeners()
    }


    private fun setListeners() {
        etQuery.setOnEditorActionListener(TextView.OnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })
        endlessScrollListener = object : EndlessRecyclerOnScrollListenerGrid(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                Log.d("Request", "given with ${current_page}")

                getImagesApiCall(current_page, visibleThreshold)

            }
        }
        rvImageList.addOnScrollListener(endlessScrollListener!!)

        ivVoiceSearch.setOnClickListener {
            launchVoiceIntent()

        }

    }

    private fun launchVoiceIntent() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            getString(R.string.speech_prompt)
        )

        if (packageManager?.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            try {
                startActivityForResult(intent, Constants.REQ_CODE_SPEECH_INPUT)
            } catch (a: ActivityNotFoundException) {
                a.printStackTrace()
            }

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQ_CODE_SPEECH_INPUT) {
            if (resultCode == Activity.RESULT_OK && null != data) {

                val result = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                val newQuery = result?.getOrNull(0)
                if (newQuery?.isNotEmpty() == true) {
                    if (!query.trim().equals(newQuery.trim(), true)) {
                        etQuery.setText("")
                        etQuery.append(newQuery.trim())
                        Utils.hideSoftKeyBoard(this@ImageListActivity)
                        loader.visibility = View.VISIBLE
//                        query = newQuery
                        reset()
                        viewModel.getImagesList(newQuery, Constants.PAGE_LIMIT, 0).observe(this, imagesObserver)
                    }
                }

            }
        }
    }


    fun getImagesApiCall(vararg pagingParams: Int) {

        val page_no: Int
        val items_to_fetch: Int
        if (TextUtils.isEmpty(query)) {
            return
        }
//            ivSearchEndsHere.setVisibility(View.GONE)
        if (isApiRunning) {
            //only decrease if calling from load more
            if (pagingParams.size == 2) {
                endlessScrollListener?.decreasePagingCount()
            }
            return
        }

        if (isLastItemFound) {
            return
        }

        isApiRunning = true
        if (pagingParams.size == 2) {
            page_no = pagingParams[0]
            items_to_fetch = pagingParams[1]

        } else {
            page_no = Constants.PAGE_START_DEFAULT
            items_to_fetch = Constants.PAGE_LIMIT
        }

        loadMoreLoader.visibility = View.VISIBLE
        Log.d("Request", "sent with ${page_no}")
        Utils.hideSoftKeyBoard(this@ImageListActivity)
        viewModel.getImagesList(query, items_to_fetch, page_no).observe(this, imagesObserver)

    }


    private fun performSearch() {
        val newQuery = StringBuilder(etQuery.text?.toString() ?: "")
        if (newQuery.isNotEmpty() && !newQuery.toString().trim().equals(query, true)) {
            loader.visibility = View.VISIBLE
//            query = String(newQuery)
            reset()
            viewModel.getImagesList(newQuery.toString().trim(), Constants.PAGE_LIMIT, 0).observe(this, imagesObserver)
        }
        Utils.hideSoftKeyBoard(this@ImageListActivity)
    }

    private fun reset() {
        imageList.clear()
        isLastItemFound = false
        isApiRunning = false
        endlessScrollListener?.reset()
        adapter = ImageAdapter(this)
        rvImageList.adapter = adapter
//        adapter.submitList(imageList)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setController() {
        adapter = ImageAdapter(this)
        layoutManager = GridLayoutManager(this, gridValue)
        rvImageList.adapter = adapter
        rvImageList.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        if (menu != null) {
            menu.findItem(R.id.menu_grid_2).isChecked = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Utils.hideSoftKeyBoard(this)
        when (item.itemId) {

            R.id.menu_grid_2 -> {
                item.isChecked = true
                gridValue = 2
            }
            R.id.menu_grid_3 -> {
                item.isChecked = true
                gridValue = 3
            }
            R.id.menu_grid_4 -> {
                item.isChecked = true
                gridValue = 4
            }
        }
        updateSpan()
        return true
    }

    private fun updateSpan() {
        (rvImageList.layoutManager as GridLayoutManager).spanCount = gridValue
        adapter.submitList(imageList)
    }

    override fun onImageClick(imageUrl: String, id: String) {
//        ImageDialogFragment.newInstance(imageUrl).show(supportFragmentManager, "image_dialog")
    }


}
