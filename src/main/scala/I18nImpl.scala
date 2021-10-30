package dev.hawu.plugins.playerprofiles

import dev.hawu.plugins.api.*
import dev.hawu.plugins.api.commands.*
import dev.hawu.plugins.playerprofiles.RandomUtils.*
import org.bukkit.command.*
import org.bukkit.configuration.file.*
import org.bukkit.plugin.java.*

import java.io.*

object I18nImpl:

    private var i18n: Option[I18n] = None

    def init(pl: JavaPlugin): Unit =
        i18n = Some(I18n(pl))

    def reload(): Unit =
        i18n.get.reload()

    extension (key: String)
        def tl(params: (String, Any)*): String =
            i18n.get.tl(key, params.map(_.toPair): _*)

    extension (sender: CommandSender)
        def tl(key: String, params: (String, Any)*): Unit =
            sender.sendMessage(key.tl(params: _*))
