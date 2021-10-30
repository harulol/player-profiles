package dev.hawu.plugins.playerprofiles
package commands

import dev.hawu.plugins.api.*
import dev.hawu.plugins.api.commands.*
import dev.hawu.plugins.playerprofiles.I18nImpl.*

class ReloadCommand extends AbstractCommandClass("reload") :

    allowPlayers()
    allowConsole()

    setPermission("ppf.reload")

    override def run(sender: CommandSource, args: CommandArgument): Unit =
        I18nImpl.reload()
        sender.sendMessage("reloaded".tl())

    override def tab(sender: CommandSource, args: CommandArgument): java.util.List[String] = null
