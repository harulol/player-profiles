<div align="right">

![GitHub](https://img.shields.io/github/license/harulol/player-profiles?style=plastic) ![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/harulol/player-profiles?include_prereleases&style=plastic&label=latest)
</div>

<div align="center">

# Player Profiles

Descriptive profiles and identifications for players.
</div>

### Commands

Arguments in `<>` are required, while those that are in `[]` are optional.

- `/ppf register <playerName> [duration]`:
  Issues an ID for `playerName` that expires in `duration` after registration.
- `/ppf reload`: Reloads the config file and messages file.

### Permissions

| Permission | Description | Default | Children |
|---|---|:---:|:---:|
| ppf.register | Allows access to the `register` sub-command. | OP | None |
| ppf.reload | Allows access to the `reload` sub-command. | OP | None |

### Screenshots

<details>
<summary>ID Template</summary>

With `/pfp register uwuBennett` (since it defaults to 1 month):

![ID](./github-assets/id-template.jpg)

With `/pfp register uwuBennett 2w`:

![ID](./github-assets/id-template-2-weeks.jpg)
</details>
