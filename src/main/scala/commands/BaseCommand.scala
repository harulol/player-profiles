package dev.hawu.plugins.playerprofiles
package commands

import dev.hawu.plugins.api.*
import dev.hawu.plugins.api.commands.*
import dev.hawu.plugins.playerprofiles.commands.*
import org.bukkit.command.*
import org.bukkit.plugin.java.*

import java.util
import scala.jdk.CollectionConverters.*

class BaseCommand(private val plugin: JavaPlugin) extends AbstractCommandClass("ppf") :

    allowPlayers()
    allowConsole()
    register(plugin)

    bind(new RegisterCommand())
    bind(new ReloadCommand())

    override def run(sender: CommandSource, args: CommandArgument): Unit = {}

    override def tab(sender: CommandSource, args: CommandArgument): util.List[String] =
        if args.size() == 1 then
            Seq("register", "reload").asJava
        else null
