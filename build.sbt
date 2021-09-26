name := "PlayerProfiles"
version := "0.1"
scalaVersion := "3.1.0-RC1"
idePackagePrefix := Some("dev.hawu.plugins.playerprofiles")

resolvers ++= Seq(
    Resolver.mavenLocal,
    "jitpack" at "https://jitpack.io"
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
    case PathList("dev", "hawu", "plugins", "api", "reflect", xs@_*) => MergeStrategy.discard
    case PathList("dev", "hawu", "plugins", "api", "nbt", xs@_*) => MergeStrategy.discard
    case PathList("dev", "hawu", "plugins", "api", "events", xs@_*) => MergeStrategy.discard
    case PathList(ps@_*) if ps.last.endsWith(".txt") || ps.last.endsWith(".properties") => MergeStrategy.discard
    case x =>
        val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
        oldStrategy(x)
}

libraryDependencies ++= Seq(
    "dev.hawu" % "hikari-library" % "master-SNAPSHOT" % Compile,
)
