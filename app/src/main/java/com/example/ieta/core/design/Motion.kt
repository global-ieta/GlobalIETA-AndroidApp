package com.example.ieta.core.design

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

object GlobalIetaMotion {
    // Centralized Duration Constants (in milliseconds)
    const val Fast: Int = 120
    const val Quick: Int = 180
    const val Standard: Int = 250
    const val Smooth: Int = 350
    const val Feature: Int = 450
    const val Hero: Int = 600
    const val Splash: Int = 1200
    const val GraphLinePulse: Int = 4500
    const val AuraBreathing: Int = 2600

    // Centralized Easing Specs
    val FastOutSlowIn: Easing = FastOutSlowInEasing
    val LinearOutSlowIn: Easing = LinearOutSlowInEasing
    val FastOutLinearIn: Easing = FastOutLinearInEasing

    // Default Tween Specs
    val fastTween: TweenSpec<Float> = tween(durationMillis = Fast, easing = FastOutSlowIn)
    val quickTween: TweenSpec<Float> = tween(durationMillis = Quick, easing = FastOutSlowIn)
    val standardTween: TweenSpec<Float> = tween(durationMillis = Standard, easing = FastOutSlowIn)
    val smoothTween: TweenSpec<Float> = tween(durationMillis = Smooth, easing = FastOutSlowIn)

    // Parameterized Tween Specs
    fun <T> fastTweenSpec(easing: Easing = FastOutSlowIn): TweenSpec<T> =
        tween(durationMillis = Fast, easing = easing)

    fun <T> quickTweenSpec(easing: Easing = FastOutSlowIn): TweenSpec<T> =
        tween(durationMillis = Quick, easing = easing)

    fun <T> standardTweenSpec(easing: Easing = FastOutSlowIn): TweenSpec<T> =
        tween(durationMillis = Standard, easing = easing)

    fun <T> smoothTweenSpec(easing: Easing = FastOutSlowIn): TweenSpec<T> =
        tween(durationMillis = Smooth, easing = easing)

    // Spring Specs
    val cardPressSpring: SpringSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )

    val nodeSelectionSpring: SpringSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessMedium
    )

    fun <T> cardPressSpringSpec(
        dampingRatio: Float = Spring.DampingRatioMediumBouncy,
        stiffness: Float = Spring.StiffnessLow
    ): SpringSpec<T> = spring(dampingRatio = dampingRatio, stiffness = stiffness)

    fun <T> nodeSelectionSpringSpec(
        dampingRatio: Float = Spring.DampingRatioLowBouncy,
        stiffness: Float = Spring.StiffnessMedium
    ): SpringSpec<T> = spring(dampingRatio = dampingRatio, stiffness = stiffness)
}

// Convenient top-level references and aliases
val FastOutSlowInEasing: Easing get() = GlobalIetaMotion.FastOutSlowIn
val LinearOutSlowInEasing: Easing get() = GlobalIetaMotion.LinearOutSlowIn
val FastOutLinearInEasing: Easing get() = GlobalIetaMotion.FastOutLinearIn

fun <T> fastTween(easing: Easing = FastOutSlowInEasing): TweenSpec<T> =
    GlobalIetaMotion.fastTweenSpec(easing)

fun <T> quickTween(easing: Easing = FastOutSlowInEasing): TweenSpec<T> =
    GlobalIetaMotion.quickTweenSpec(easing)

fun <T> standardTween(easing: Easing = FastOutSlowInEasing): TweenSpec<T> =
    GlobalIetaMotion.standardTweenSpec(easing)

fun <T> smoothTween(easing: Easing = FastOutSlowInEasing): TweenSpec<T> =
    GlobalIetaMotion.smoothTweenSpec(easing)

fun <T> cardPressSpring(
    dampingRatio: Float = Spring.DampingRatioMediumBouncy,
    stiffness: Float = Spring.StiffnessLow
): SpringSpec<T> = GlobalIetaMotion.cardPressSpringSpec(dampingRatio, stiffness)

fun <T> nodeSelectionSpring(
    dampingRatio: Float = Spring.DampingRatioLowBouncy,
    stiffness: Float = Spring.StiffnessMedium
): SpringSpec<T> = GlobalIetaMotion.nodeSelectionSpringSpec(dampingRatio, stiffness)
