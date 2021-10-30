ThisBuild / version := "1.1-SNAPSHOT"
ThisBuild / scalaVersion := "3.1.0"

lazy val root = (project in file("."))
    .settings(
        name := "PlayerProfiles",
        idePackagePrefix := Some("dev.hawu.plugins.playerprofiles"),
        resolvers += Resolver.mavenLocal,
        libraryDependencies ++= Seq(
            "org.bukkit" % "bukkit" % "1.8-R0.1-SNAPSHOT",
            "dev.hawu.plugins" % "hikari-library" % "1.1.5-SNAPSHOT",
        ),
    )
