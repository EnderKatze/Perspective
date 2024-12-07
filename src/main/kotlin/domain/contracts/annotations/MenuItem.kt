package de.enderkatze.domain.contracts.annotations

import org.bukkit.Material

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class MenuItem(
    val closeOnUse: Boolean = true
)
