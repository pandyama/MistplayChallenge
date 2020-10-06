package com.mp.mistplaychallenge

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.mp.mistplaychallenge.adapter.ParentAdapter
import com.mp.mistplaychallenge.model.GameCategory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /**
     *  @param pref_name is the name of the sharedPreference used for maintaining the current theme state
     *  @param linearLayoutManager is the layout manager variable for the Parent recycler view
    * */
    private val PREF_NAME = "theme"
    private lateinit var linearLayoutManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        verticalView.layoutManager = linearLayoutManager
        fetchGames()
    }

    /**
     *  @param sharedPref - holds the current sharedPreference by name referenced by PREF_NAME variable
     *  fabOnclick
     *      - whenever user clicks the Floating Action Button, the theme is switched from Light to Dark mode and vice versa
     *      - sharedPreference is used to maintain the current theme state by holding an integer locally on user's phone
     */

    fun fabOnclick(v: View){
        val sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        if(sharedPref.getInt("day",0) == -1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPref.edit().putInt("day",1).commit()
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPref.edit().putInt("day",-1).commit()
        }
    }

    /**
     *  @param gameList - contains list of all games along with their game category as a string variable
     *  @param gson - GsonBuilder variable
     *  @param gameCategory - holds the gson converted List object that holds GameCategory object from GameCategory data class in model package
     *
     * fetchGames
     *       - converts the gameList variable into a List of GameCategory data object
     *       - calls ParentAdapter class and passes the gameCategory List object to it which will then create the Vertical recycler view
     */

    private fun fetchGames(){
        val gameList = """[
             {
              "list_title": "Mistplay Favorites",
              "games": [
                {
                  "title": "RAID: Shadow Legends",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/raid-shadow-legends/RAID_Portrait.jpg"
                },
                {
                  "title": "YAHTZEE® With Buddies Dice Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/yahtzeewithbuddies/Yahtzee_Portrait.jpg"
                },
                {
                  "title": "Solitaire Grand Harvest - Free Tripeaks Solitaire",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/solitaire-grand-harvest/SolitaireGrandHarvest_Portrait.jpg"
                },
                {
                  "title": "Bingo Blitz™- Bingo Games",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/bingo-blitz/9726_GenericBanners_pack2_290x420.jpg"
                },
                {
                  "title": "Slotomania™ Free Slots: Casino Slot Machine Games",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/slotomania/Slotomania_Portrait.jpg"
                },
                {
                  "title": "Caesars Casino: Free Slots Games",
                  "img": "https://assets.mistplay.com/images/timeplay/games/caesars/CaesarsSlots_Portrait.jpg"
                },
                {
                  "title": "Bingo Pop - Live Multiplayer Bingo Games for Free",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/bingo-pop/BingoPop_Portrait.jpg"
                },
                {
                  "title": "Big Farm: Mobile Harvest – Free Farming Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/big-farm-mobile-harvest/BigFarmMobileHarvest_Portrait.jpg"
                },
                {
                  "title": "Word Life - Connect crosswords puzzle",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/word-life/WorldLife-Portrait.jpg"
                },
                {
                  "title": "Wheel of Fortune: Free Play",
                  "img": "https://assets.mistplay.com/images/timeplay/games/wheel-of-fortune/WOF-portrait.jpg"
                }
              ]
            },
            {
              "list_title": "Fantasy",
              "games": [
                {
                  "title": "Monster Legends",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/monster-legends/Portrait_290x420.png"
                },
                {
                  "title": "Park of Monster",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/parkofmonster/POM-Portrait.png"
                },
                {
                  "title": "Wizard of Oz Free Slots Casino",
                  "img": "https://assets.mistplay.com/images/timeplay/games/wizard-of-oz-slots/WoZ_Portrait.jpg"
                },
                {
                  "title": "Game of Thrones Slots Casino - Free Slot Machines",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/got-slots/GoTSlots_Portrait.jpg"
                },
                {
                  "title": "Dragon Mania Legends",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/dragon-mania/DragonMania_Portrait.jpg"
                },
                {
                  "title": "Lords Mobile: Kingdom Wars",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/Lords-Mobile/LordsMobile-Portrait.jpg"
                },
                {
                  "title": "The Wizard of Oz Magic Match 3 Puzzles & Games",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/wizzard-of-oz/WizardOfOz_Portrait.jpg"
                },
                {
                  "title": "Romancing SaGa Re;univerSe",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/romancing-saga/RomancingSaga_Portrait.jpg"
                },
                {
                  "title": "RAID: Shadow Legends",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/raid-shadow-legends/RAID_Portrait.jpg"
                }
              ]
            },
            {
              "list_title": "Slots",
              "games": [
                {
                  "title": "Slotomania™ Free Slots: Casino Slot Machine Games",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/slotomania/Slotomania_Portrait.jpg"
                },
                {
                  "title": "Caesars Casino: Free Slots Games",
                  "img": "https://assets.mistplay.com/images/timeplay/games/caesars/CaesarsSlots_Portrait.jpg"
                },
                {
                  "title": "Huuuge Casino Slots - Slot Machines 777",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/huuuge-casino-slots/HuuugeCasino_Portrait.jpg"
                },
                {
                  "title": "Neverland Casino Slots 2020 - Social Slots Games",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/neverland-casino/NeverlandCasino-Portrait.jpg"
                },
                {
                  "title": "Stars Slots Casino - Vegas Slot Machines",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/huuuge-stars-slots/Stars_slots_portrait.jpg"
                },
                {
                  "title": "House of Fun™ : Free Slots & Casino Slots Machines",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/houseoffun/HoF_Portrait.jpg"
                },
                {
                  "title": "Double Win Casino Slots - Real Vegas Night Slots",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/Double-Win-Casino-Slots/doublewin0310_portrait.jpg"
                },
                {
                  "title": "Vegas Slots - DoubleDown Casino",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/doubledown-casino/VegasSlots_Portrait.jpg"
                },
                {
                  "title": "Game of Thrones Slots Casino - Free Slot Machines",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/got-slots/GoTSlots_Portrait.jpg"
                },
                {
                  "title": "Wizard of Oz Free Slots Casino",
                  "img": "https://assets.mistplay.com/images/timeplay/games/wizard-of-oz-slots/WoZ_Portrait.jpg"
                }
              ]
            },
            {
              "list_title": "Match 3",
              "games": [
                {
                  "title": "Best Fiends - Free Puzzle Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/best-fiends/unnamed.jpg"
                },
                {
                  "title": "Vineyard Valley: Match & Blast Puzzle Design Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/vineyard-valley/VineyardValley_Portrait.jpg"
                },
                {
                  "title": "Toy Blast",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/toy-blast/mistplay-360x520-01.jpg"
                },
                {
                  "title": "Genies & Gems - Jewel & Gem Matching Adventure",
                  "img": "https://s3.amazonaws.com/mistplay-static/images/timeplay/games/genies-gems/GeniesGems_Portrait.jpg"
                },
                {
                  "title": "Toon Blast",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/toon-blast/mistplay-itempool-360x520.png"
                },
                {
                  "title": "Tile Master - Classic Triple Match & Puzzle Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/tilemaster/tilemaster_portrait.png"
                },
                {
                  "title": "Delicious B&B: Match 3 game & Interactive story",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/delicious-bb/DeliciousBB_Portrait.jpg"
                },
                {
                  "title": "Gummy Candy Blast - Free Match 3 Puzzle Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/gummycandyblast/gummycandy_portrait.png"
                },
                {
                  "title": "Matchington Mansion",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/matchingtonmansion/matchingtonmansion_portrait.png"
                },
                {
                  "title": "Puppy Blast™- pets puzzle adventure",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/puppyblast/puppyblast_portrait.png"
                }
              ]
            },
            {
              "list_title": "Board Games",
              "games": [
                {
                  "title": "Bingo Blitz™- Bingo Games",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/bingo-blitz/9726_GenericBanners_pack2_290x420.jpg"
                },
                {
                  "title": "Board Kings™ ",
                  "img": "https://s3.amazonaws.com/mistplay-static/images/timeplay/games/board-kings/Board+Kings_Portrait.jpg"
                },
                {
                  "title": "Scrabble® GO - New Word Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/scrabble/ScrabbleGo_Portrait_June2020.jpg"
                },
                {
                  "title": "Words With Friends 2 – Free Multiplayer Word Games",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/wordswithfriends2.1/WordsWithFriends_Portrait.jpg"
                },
                {
                  "title": "Tile Master - Classic Triple Match & Puzzle Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/tilemaster/tilemaster_portrait.png"
                },
                {
                  "title": "Dice With Buddies™ Free - The Fun Social Dice Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/dice-with-buddies/Dice_Portrait.jpg"
                },
                {
                  "title": "Ultimate Cribbage - Classic Board Card Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/ultimate_cribbage/Portrait_Image.png"
                },
                {
                  "title": "CROSS-STITCH: COLORING BOOK",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/cross-stitch/360X520.jpg"
                },
                {
                  "title": "YAHTZEE® With Buddies Dice Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/yahtzeewithbuddies/Yahtzee_Portrait.jpg"
                }
              ]
            },
            {
              "list_title": "Other Games",
              "games": [
                {
                  "title": "Big Farm: Mobile Harvest – Free Farming Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/big-farm-mobile-harvest/BigFarmMobileHarvest_Portrait.jpg"
                },
                {
                  "title": "State of Survival: Survive the Zombie Apocalypse",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/stateofsurvival/SOS0531_portrait.png"
                },
                {
                  "title": "Guns of Glory: Build an Epic Army for the Kingdom",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/guns-of-glory/GOG0531_portrait.png"
                },
                {
                  "title": "Genies & Gems - Jewel & Gem Matching Adventure",
                  "img": "https://s3.amazonaws.com/mistplay-static/images/timeplay/games/genies-gems/GeniesGems_Portrait.jpg"
                },
                {
                  "title": "Who Wants to Be a Millionaire? Trivia & Quiz Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/millionaire/mil_static_mistplay-360x520.png"
                },
                {
                  "title": "Party in my Dorm: College Life Roleplay Chat Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/party-in-my-dorm/Pimd+-+Portrait.jpg"
                },
                {
                  "title": "Stars Slots Casino - Vegas Slot Machines",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/huuuge-stars-slots/Stars_slots_portrait.jpg"
                },
                {
                  "title": "June's Journey - Hidden Objects",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/junes-journey-hidden-object/JunesJourney-Portrait.jpg"
                },
                {
                  "title": "Double Win Casino Slots - Real Vegas Night Slots",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/Double-Win-Casino-Slots/doublewin0310_portrait.jpg"
                },
                {
                  "title": "Nonogram - Jigsaw Puzzle Game",
                  "img": "https://mistplay-static.s3.amazonaws.com/images/timeplay/games/nonogram/nonogram_portrait.png"
                }
              ]
            }
            ]"""
        val gson = GsonBuilder().create()
        val gameCategory = gson.fromJson(gameList, Array<GameCategory>::class.java).toList()

        verticalView.adapter = ParentAdapter(gameCategory, applicationContext)
    }
}
