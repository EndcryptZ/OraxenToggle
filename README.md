Simple plugin that toggles Auto-Download of resource-pack from Oraxen plugin **(https://github.com/oraxen/oraxen)**

Make sure to set `send_pack` in `settings.yml` of Oraxen to `false`

```
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

Commands:
- /autorp - Toggles auto-download (Player Command)
- /oraxentogglereload - Reloads `messages.yml`
