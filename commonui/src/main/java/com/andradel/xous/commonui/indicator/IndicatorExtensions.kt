package com.andradel.xous.commonui.indicator

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3

fun <VH : RecyclerView.ViewHolder> CircleIndicator3.setViewPagerAndAdapter(
    vp: ViewPager2,
    adapter: RecyclerView.Adapter<VH>
) {
    setViewPager(vp)
    adapter.registerAdapterDataObserver(adapterDataObserver)
}