package com.example.ieta.feature

import com.example.ieta.core.navigation.Screen
import com.example.ieta.feature.home.components.ecosystemNodes
import com.example.ieta.feature.home.homeProducts
import com.example.ieta.feature.menu.menuGroups
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class EcosystemHomeTest {

    @Test
    fun ecosystemNodes_containsAllSevenCoreNodes() {
        assertEquals(7, ecosystemNodes.size)

        val nodeIds = ecosystemNodes.map { it.id }
        assertTrue(nodeIds.contains("aura"))
        assertTrue(nodeIds.contains("education"))
        assertTrue(nodeIds.contains("business"))
        assertTrue(nodeIds.contains("legal"))
        assertTrue(nodeIds.contains("gaming"))
        assertTrue(nodeIds.contains("equine"))
        assertTrue(nodeIds.contains("marketplace"))
    }

    @Test
    fun homeProducts_containsExpectedProductsWithStatusAndRoutes() {
        assertTrue(homeProducts.size >= 10)

        val auraProduct = homeProducts.find { it.id == "aura" }
        assertNotNull(auraProduct)
        assertEquals("AVAILABLE", auraProduct?.status)
        assertEquals(Screen.Aura.route, auraProduct?.route)

        val arinProduct = homeProducts.find { it.id == "arin" }
        assertNotNull(arinProduct)
        assertEquals(Screen.Arin.route, arinProduct?.route)
    }

    @Test
    fun menuGroups_containsStructuredCategoriesAndItems() {
        assertEquals(4, menuGroups.size)

        val ecosystemGroup = menuGroups.find { it.categoryCode == "CAT-ECO-01" }
        assertNotNull(ecosystemGroup)
        assertEquals(10, ecosystemGroup?.items?.size)

        val firstItem = ecosystemGroup?.items?.first()
        assertEquals("AURA AI CORE", firstItem?.title)
        assertEquals(Screen.Aura.route, firstItem?.route)
    }
}
