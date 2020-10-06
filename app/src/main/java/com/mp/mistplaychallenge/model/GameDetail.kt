package com.mp.mistplaychallenge.model

/**
 *  GameDetail - data class containing game detail info
 *  Data classes act like data holder classes and helps with avoiding to write extra code
 *
 *  @param title - holds the game title
 *  @param img - holds the URL where the game's image is stored
 */

data class GameDetail(
    var title: String?,
    var img: String?
)