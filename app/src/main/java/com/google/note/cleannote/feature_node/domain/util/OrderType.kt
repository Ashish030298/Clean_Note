package com.google.note.cleannote.feature_node.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
