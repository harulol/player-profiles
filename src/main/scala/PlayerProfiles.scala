package dev.hawu.plugins.playerprofiles

import dev.hawu.plugins.api.*
import dev.hawu.plugins.playerprofiles.commands.*
import org.bukkit.plugin.java.*

class PlayerProfiles extends JavaPlugin :

    override def onEnable(): Unit =
        Constants.setPlugin(this)
        I18n.init(this)
        Configuration.init(this)
        BaseCommand()
