# OraxenToggle

**OraxenToggle** is a lightweight addon for the [Oraxen plugin](https://github.com/oraxen/oraxen) that allows players to toggle the automatic resource pack download when they join the server.

This gives each player the ability to control whether the Oraxen resource pack is sent to them automatically, enhancing flexibility and user experience.


## 🔧 Setup

Before using OraxenToggle, make sure to disable automatic pack dispatch in Oraxen by editing the `settings.yml`:

```yaml
dispatch:
  send_pack: false
  send_on_reload: true
  delay: -1
  mandatory: true
  prompt: "<#fa4943>Accept the pack to enjoy a full <b><gradient:#9055FF:#13E2DA>Oraxen</b><#fa4943> experience"
  join_message:
    enabled: false
    delay: -1
```

Setting `send_pack: false` ensures that OraxenToggle has full control over when the pack is sent.

## 💡 Features

* Lets players individually toggle whether the resource pack is sent on join
* Fully configurable `messages.yml` file
* Simple and efficient with no bloat

## 📜 Commands

| Command               | Description                               | Permission            |
|-----------------------|-------------------------------------------|-----------------------|
| `/autorp`             | Toggle auto-download of the resource pack | `oraxentoggle.use`    |
| `/oraxentogglereload` | Reload the configuration/messages         | `oraxentoggle.reload` |

## 📂 Configuration

`messages.yml` lets you customize the toggle messages shown to players.

## 📌 Requirements

* Minecraft server using [Spigot](https://www.spigotmc.org/) or a fork (Paper, Purpur, etc.)
* [Oraxen Plugin](https://github.com/oraxen/oraxen)

## 🧩 Plugin Compatibility

* ✅ Tested with latest Oraxen versions
* ✅ Supports most Spigot forks

## 🔗 Links

* [OraxenToggle on GitHub](https://github.com/EndcryptZ/OraxenToggle)
* [Oraxen Plugin](https://github.com/oraxen/oraxen)
