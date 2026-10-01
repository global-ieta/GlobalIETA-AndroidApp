package com.example.ieta.core

import com.example.ieta.core.components.defaultBottomNavItems
import com.example.ieta.core.navigation.Screen
import org.junit.Assert.assertEquals
import org.junit.Test

class BottomNavigationTest {

    @Test
    fun bottomNavItems_matchExpectedScreenRoutes() {
        assertEquals(4, defaultBottomNavItems.size)

        assertEquals(Screen.Home.route, defaultBottomNavItems[0].route)
        assertEquals("Home", defaultBottomNavItems[0].title)

        assertEquals(Screen.Industries.route, defaultBottomNavItems[1].route)
        assertEquals("Verticals", defaultBottomNavItems[1].title)

        assertEquals(Screen.Marketplace.route, defaultBottomNavItems[2].route)
        assertEquals("Market", defaultBottomNavItems[2].title)

        assertEquals(Screen.Profile.route, defaultBottomNavItems[3].route)
        assertEquals("Profile", defaultBottomNavItems[3].title)
    }

    @Test
    fun coreTabRoutes_areCorrect() {
        assertEquals("home", Screen.Home.route)
        assertEquals("industries", Screen.Industries.route)
        assertEquals("marketplace", Screen.Marketplace.route)
        assertEquals("profile", Screen.Profile.route)
        assertEquals("aura", Screen.Aura.route)
    }
}
