package dev.hawu.plugins.playerprofiles

import dev.hawu.plugins.api.*
import dev.hawu.plugins.api.collections.tuples.*
import org.bukkit.command.*

object RandomUtils:

    extension[T] (any: T)
        def ?:(other: T): T = if any != null then any else other

    extension (pair: (?, ?))
        def toPair: Pair[?, ?] = Pair(pair._1, pair._2)

    extension (sender: CommandSender)
        def <<(s: String): Unit =
            sender.sendMessage(Strings.color(s))
