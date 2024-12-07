package de.enderkatze.domain.contracts.annotations

import org.bukkit.Material

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Menu(
    val title: String,
    val rows: Int)