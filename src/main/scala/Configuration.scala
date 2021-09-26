package dev.hawu.plugins.playerprofiles

import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

object Configuration:

    private var plugin: Option[JavaPlugin] = None

    extension[T](any: T)
        def ?:(other: T): T =
            return if any == null then other else any

    def init(pl: JavaPlugin): Unit =
        plugin = Some(pl)
