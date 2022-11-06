package com.mahmoud.zaher.fawrytask.core.pagination

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class ScrollingPagination(private val LayoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (limitReached()) {
            loadMoreDate()
        }
    }

    private fun limitReached(): Boolean {
        val visibleItemCount: Int = LayoutManager.childCount
        val totalItemCount: Int = LayoutManager.itemCount
        val firstVisibleItemPosition: Int = LayoutManager.findFirstVisibleItemPosition()

        return (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0

    }

    abstract fun loadMoreDate()

}