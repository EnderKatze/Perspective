package de.enderkatze

import de.enderkatze.domain.contracts.*
import de.enderkatze.domain.contracts.manager.DisplayManager
import de.enderkatze.domain.contracts.manager.ItemManager
import de.enderkatze.domain.contracts.manager.MenuManager
import de.enderkatze.domain.contracts.manager.MenuReferenceManager
import de.enderkatze.infra.*
import de.enderkatze.infra.listeners.InventoryClickListener
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Perspective private constructor(
    private val plugin: JavaPlugin,
    private val debugMode: Boolean = false,
){

    private val menuManager: MenuManager = MenuManagerImpl()
    private val itemProcessor: ItemProcessor = TODO()
    private val menuReferenceManager: MenuReferenceManager = MenuReferenceManagerImpl()
    private val displayManager: DisplayManager = DisplayManagerImpl(
        guiBuilder = TODO(),
        menuManager = menuManager,
        itemManager = TODO(),
        itemProcessor = itemProcessor
    )
    private val itemManager: ItemManager = ItemManagerImpl(
        menuReferenceManager = menuReferenceManager,
        menuManager = menuManager
    )

    private val menuProcessor: MenuProcessor = MenuProcessorImpl(menuManager, itemManager)

    private val namespacedKey: NamespacedKey = NamespacedKey(plugin, "perspectiveMenuItem")

    init {
        Bukkit.getPluginManager().registerEvents(
            InventoryClickListener(
                menuManager,
                itemManager,
                itemProcessor,
                namespacedKey,
                displayManager),
            plugin)
    }

    fun register(vararg instances: Any) {
        for(instance in instances) {
            if(!menuProcessor.registerMenu(instance)) {
                throw ClassNotFoundException("Couldn't register menu, this might be due to a missing annotation")
            }
        }
    }

    fun openMenu(name: String, player: Player) {
        displayManager.openMenu(name, player)

    }

    companion object {
        fun builder(plugin: JavaPlugin): PerspectiveBuilder {
            return PerspectiveBuilder(plugin)
        }

        class PerspectiveBuilder (private val plugin: JavaPlugin) {

            private var debugMode: Boolean = false

            fun built(): Perspective {
                return Perspective(plugin, debugMode)
            }

            fun inDebugMode(): PerspectiveBuilder {
                debugMode = true
                return this
            }

        }
    }

}