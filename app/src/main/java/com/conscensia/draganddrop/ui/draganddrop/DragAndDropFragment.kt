package com.conscensia.draganddrop.ui.draganddrop

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.conscensia.draganddrop.R
import com.conscensia.draganddrop.databinding.FragmentDragAndDropBinding
import com.conscensia.draganddrop.viewmodels.DragAndDropViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DragAndDropFragment : Fragment() {

    companion object {
        private val IMAGE_VIEW_TAG = "IMAGE_VIEW_TAG"
        fun newInstance() = DragAndDropFragment()
    }

    private lateinit var binding: FragmentDragAndDropBinding
    private val viewModel by lazy { getViewModel<DragAndDropViewModel>() }


    /* lifecycle */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDragAndDropBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.apply {
            tag = IMAGE_VIEW_TAG
            setOnLongClickListener(DragAndDropLongClickListener())
        }
        view.findViewById<View>(R.id.container).setOnDragListener(DragAndDropOnDragListener())

        viewModel.pointLiveData.observe(viewLifecycleOwner) { point ->
            binding.imageView.layoutParams = binding.imageView.layoutParams?.let { layoutParams ->
                (layoutParams as ViewGroup.MarginLayoutParams).apply {
                    leftMargin = point.x - layoutParams.width / 2
                    topMargin = point.y - layoutParams.height / 2
                }
            }
        }
    }


    /* private */

    private inner class DragAndDropLongClickListener : View.OnLongClickListener {
        override fun onLongClick(view: View?): Boolean {
            val item = ClipData.Item(view?.tag as? CharSequence)
            val dragData = ClipData(
                view?.tag as CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item
            )
            val myShadow = View.DragShadowBuilder(view)

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(
                    dragData,
                    myShadow,
                    null,
                    0
                )
            } else {
                view.startDrag(
                    dragData,
                    myShadow,
                    null,
                    0
                )
            }
        }
    }

    private inner class DragAndDropOnDragListener : View.OnDragListener {
        override fun onDrag(v: View?, event: DragEvent?): Boolean {
            return when (event?.action) {
                DragEvent.ACTION_DRAG_STARTED -> event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                DragEvent.ACTION_DRAG_LOCATION, DragEvent.ACTION_DRAG_ENDED -> true
                DragEvent.ACTION_DROP -> {
                    viewModel.onDropped(Point(event.x.toInt(), event.y.toInt()))
                    true
                }

                else -> false
            }
        }
    }
}
