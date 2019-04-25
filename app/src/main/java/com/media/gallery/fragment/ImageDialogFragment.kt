package com.media.gallery.fragment

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.media.gallery.R
import kotlinx.android.synthetic.main.fragment_dialog_image.*

class ImageDialogFragment : DialogFragment() {

    companion object {
        const val KEY_IMAGE_URL = "image_url"
        fun newInstance(imageUrl: String): ImageDialogFragment {
            val f = ImageDialogFragment()
            val b = Bundle()
            b.putString(KEY_IMAGE_URL, imageUrl)
            f.arguments = b
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        ivImage.setImageUri(
            arguments?.getString(KEY_IMAGE_URL) ?: "",
            ColorDrawable(ContextCompat.getColor(context!!, R.color.color_grey))
        )
    }

}