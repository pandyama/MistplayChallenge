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
     *  @param games - contains list of all games along with their game category as a string variable
     *  @param gson - GsonBuilder variable
     *  @param gameCategory - holds the gson converted List object that holds GameCategory object from GameCategory data class in model package
     *
     * fetchGames
     *       - converts the gameList variable into a List of GameCategory data object
     *       - calls ParentAdapter class and passes the gameCategory List object to it which will then create the Vertical recycler view
     */

    private fun fetchGames(){
        val games = Games().gameList
        val gson = GsonBuilder().create()
        val gameCategory = gson.fromJson(games, Array<GameCategory>::class.java).toList()

        verticalView.adapter = ParentAdapter(gameCategory, applicationContext)
    }
}
