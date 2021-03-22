package com.irzstudio.latihanpraeznote.Adapter

import com.irzstudio.latihanpraeznote.Data.Item

interface OnItemListener {
    fun onClick(item: Item)
    fun onDelete(item: Item)
}


