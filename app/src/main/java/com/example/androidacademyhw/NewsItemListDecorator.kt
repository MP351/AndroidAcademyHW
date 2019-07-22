package com.example.androidacademyhw

import android.graphics.Rect
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class NewsItemListDecorator(val div: Int): androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        outRect.top = div
        outRect.bottom = div
        if((parent.layoutManager as androidx.recyclerview.widget.GridLayoutManager).spanCount == 1) {
            portraitMarginSet(outRect)
        } else {
            landMarginSet(outRect, view)
        }
    }

    private fun landMarginSet(outRect: Rect, view: View) {
        when ((view.layoutParams as androidx.recyclerview.widget.GridLayoutManager.LayoutParams).spanIndex % 2) {
            0 -> {
                outRect.left = div
                outRect.right = div/2
            }
            else -> {
                outRect.left = div /2
                outRect.right = div
            }
        }
    }

    private fun portraitMarginSet(outRect: Rect) {
        outRect.right = div
        outRect.left = div
    }
}