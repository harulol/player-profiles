name := "PlayerProfiles"
version := "0.1"
scalaVersion := "3.1.0-RC1"
idePackagePrefix := Some("dev.hawu.plugins.playerprofiles")

resolvers ++= Seq(
    Resolver.mavenLocal,
)

ThisBuild / assemblyShadeRules := Seq(
    ShadeRule.rename("dev.hawu.plugins.api.**" -> "dev.hawu.plugins.playerprofiles.libs.api.@1").inAll,
)

ThisBuild / assemblyMergeStrategy := {
    case PathList("javax", xs@_*) => MergeStrategy.discard
    case PathList("com", xs@_*) => MergeStrategy.discard
    case PathList("junit", xs@_*) => MergeStrategy.discard
    case PathList("org", xs@_*) => MergeStrategy.discard
    case PathList("scala", xs@_*) => MergeStrategy.discard
    case PathList(ps@_*) if ps.last.endsWith(".txt") || ps.last.endsWith(".properties") => MergeStrategy.first
    case x =>
        val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
        oldStrategy(x)
}

libraryDependencies ++= Seq(
    "dev.hawu.plugins" % "hikari-library-core" % "1.1-SNAPSHOT" % Compile,
    "dev.hawu.plugins" % "hikari-library-commands" % "1.0-SNAPSHOT" % Compile,
    "dev.hawu.plugins" % "hikari-library-collections" % "1.0-SNAPSHOT" % Compile,
    "dev.hawu.plugins" % "hikari-library-inventories" % "2.0-SNAPSHOT" % Compile,
)
