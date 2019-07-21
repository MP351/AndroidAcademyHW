package com.example.androidacademyhw

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class NewsItemListDecorator(val div: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = div
        outRect.bottom = div
        if((parent.layoutManager as GridLayoutManager).spanCount == 1) {
            portraitMarginSet(outRect)
        } else {
            landMarginSet(outRect, view)
        }
    }

    private fun landMarginSet(outRect: Rect, view: View) {
        when ((view.layoutParams as GridLayoutManager.LayoutParams).spanIndex % 2) {
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