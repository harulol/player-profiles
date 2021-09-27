package dev.hawu.plugins.playerprofiles
package commands

import dev.hawu.plugins.api.*
import dev.hawu.plugins.api.commands.*
import org.bukkit.command.*

import java.util
import scala.jdk.CollectionConverters.*

class BaseCommand extends AbstractCommandClass("ppf") :

    allowPlayers()
    allowConsole()
    register()

    bind(new RegisterCommand)
    bind(new ReloadCommand)

    override def run(sender: CommandSource, args: CommandArgument): Unit = {}

    override def tab(sender: CommandSource, args: CommandArgument): util.List[String] =
        if args.size() == 1 then
            return Seq("register", "reload").asJava
        else return null
