package eu.parent.android.app.common.presentation.extensions

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by mahmoud on 9/7/17.
 */
fun RecyclerView.verticalLayoutManager() = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

fun RecyclerView.horizontalLayoutManager() = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

fun RecyclerView.verticalDivider() = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)