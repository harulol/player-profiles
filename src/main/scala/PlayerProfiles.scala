package dev.hawu.plugins.playerprofiles

import dev.hawu.plugins.api.*
import dev.hawu.plugins.playerprofiles.*
import dev.hawu.plugins.playerprofiles.RandomUtils.*
import dev.hawu.plugins.playerprofiles.commands.*
import org.bukkit.*
import org.bukkit.plugin.java.*

class PlayerProfiles extends JavaPlugin :

    private val LIBRARY_LINK = "https://github.com/harulol/hikari-library"

    override def onEnable(): Unit =
        if Bukkit.getPluginManager.getPlugin("HikariLibrary") != null then
            val console = Bukkit.getConsoleSender
            console << "&cThis project was built against a framework. A copy can be retrieved from:"
            console << s"&c- $LIBRARY_LINK"
            Bukkit.getPluginManager.disablePlugin(this)
            ()

        I18nImpl.init(this)
        Configuration.init(this)
        BaseCommand(this)
