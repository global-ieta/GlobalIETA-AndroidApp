package com.example.ieta.core.navigation

sealed class Screen(val route: String, val title: String) {
    object Splash : Screen("splash", "Global IETA Core")
    object Home : Screen("home", "Connected Worlds")
    object Aura : Screen("aura", "AURA Quantum Core")
    object Arin : Screen("arin", "ARIN Spatial Engine")
    object Aero : Screen("aero", "AERO Mobility")
    object Campus : Screen("campus", "IETA CAMPUS")
    object Connector : Screen("connector", "IETA CONNECTOR")
    object RideOS : Screen("rideos", "RIDEOS Core")
    object Workspace : Screen("workspace", "IETA WORKSPACE")
    object Marketplace : Screen("marketplace", "Global Asset Exchange")
    object Gaming : Screen("gaming", "Gaming & Interactive")
    object Billing : Screen("billing", "Billing & Subscriptions")
    object Industries : Screen("industries", "Ecosystem Verticals")
    object Company : Screen("company", "About Global IETA")
    object Resources : Screen("resources", "Developer Documentation")
    object EarlyAccess : Screen("early-access", "Early Access Protocol")
    object SignIn : Screen("signin", "System Authentication")
    object Profile : Screen("profile", "Architect Profile")
    object Settings : Screen("settings", "System Settings")
    object Support : Screen("support", "Support Matrix")
    object About : Screen("about", "System Specs")
    object Privacy : Screen("privacy", "Privacy & Data Guardrails")
    object Terms : Screen("terms", "Terms of Service")
    object Search : Screen("search", "Global Search Engine")
    object Notifications : Screen("notifications", "System Logs & Notifications")
    object Menu : Screen("menu", "Primary Command Index")

    companion object {
        val allScreens = listOf(
            Splash, Home, Aura, Arin, Aero, Campus, Connector, RideOS, Workspace,
            Marketplace, Gaming, Billing, Industries, Company, Resources,
            EarlyAccess, SignIn, Profile, Settings, Support, About, Privacy,
            Terms, Search, Notifications, Menu
        )
    }
}
