package com.nunu.coco.network.model

import com.nunu.coco.dataModel.RecentPriceData

data class RecentCoinPriceList (

    val status : String,
    val data : List<RecentPriceData>

)