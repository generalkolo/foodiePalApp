package com.miu.cs473.foodiepal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.cs473.foodiepal.adapter.ViewPagerAdapter
import com.miu.cs473.foodiepal.frragments.AboutMeFragment
import com.miu.cs473.foodiepal.frragments.BlogFragment
import com.miu.cs473.foodiepal.frragments.ContactFragment
import com.miu.cs473.foodiepal.frragments.MealPlannerFragment
import com.miu.cs473.foodiepal.frragments.RecipeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Create a list of Fragments
        val fragments = listOf(
            RecipeFragment(),
            MealPlannerFragment(),
            BlogFragment(),
            ContactFragment(),
            AboutMeFragment()
        )

        // Set up the ViewPager and TabLayout
        val pagerAdapter = ViewPagerAdapter(this, fragments)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Recipes"
                1 -> "Meal\nPlanner"
                2 -> "Blog"
                3 -> "Contct"
                4 -> "About Me"
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }.attach()

        tabLayout.tabMode = TabLayout.MODE_FIXED

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_recipes -> viewPager.currentItem = 0
                R.id.nav_meal_planner -> viewPager.currentItem = 1
                R.id.nav_blog -> viewPager.currentItem = 2
            }
            true
        }
    }
}