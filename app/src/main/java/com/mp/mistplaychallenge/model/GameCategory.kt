package com.mp.mistplaychallenge.model

/**
 *  GameCategory - data class that containing Game category and list of games
 *  Data classes act like data holder classes and helps with avoiding to write extra code
 *
 *  @param list_title - holds the Game category title
 *  @param games - List of data type GameDetail containing details of each game in its respective category
 */

data class GameCategory(
    var list_title: String?,
    var games: List<GameDetail>?
)