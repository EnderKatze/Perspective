package de.enderkatze.domain.contracts.data

import java.util.UUID

// Holds all data about the Menu that stays constant
data class MenuData(
    val title: String,
    val rows: Int,
    val itemIDs: List<String>
)