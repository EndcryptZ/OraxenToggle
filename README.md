# OraxenToggle

OraxenToggle is a lightweight plugin designed to provide server administrators with control over the **Oraxen resource pack auto-download feature**. With this plugin, you can easily enable or disable the automatic download of the Oraxen resource pack for players, without needing to restart the server or manually alter configuration files.

## Features

- **Toggle Oraxen Resource Pack Download:** Instantly enable or disable the automatic resource pack prompt for players.
- **Permission-Based Access:** Restrict toggling commands to authorized users.
- **Lightweight & Easy to Use:** Minimal setup required; easy integration with any server running Oraxen.
- **Configurable:** Customize command names, permissions, and plugin messages.

## 🔧 Setup

Before using OraxenToggle, make sure to disable automatic pack dispatch in Oraxen to enhance flexibility and user experience.  
Edit your Oraxen `settings.yml` file as follows:

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

> **Note:**  
> Setting `send_pack: false` ensures that OraxenToggle has full control over when the pack is sent.

## Installation

1. Download the latest release of OraxenToggle from the [releases page](https://github.com/EndcryptZ/OraxenToggle/releases).
2. Place the `OraxenToggle.jar` file into your server's `plugins` directory.
3. Ensure you have the [Oraxen plugin](https://github.com/oraxen/oraxen) installed and configured.
4. Restart or reload your Minecraft server.

## Usage

- **Command:**  
  `/oraxentoggle`  
  Toggles the Oraxen resource pack auto-download state (enabled/disabled).

- **Permissions:**  
  `oraxentoggle.toggle` - Allows a user to use the toggle command.

### Example

```
/oraxentoggle
```

The plugin will respond with the new state of the Oraxen resource pack auto-download feature.

## Configuration

A default configuration file will be generated in the `plugins/OraxenToggle` directory after the first run. You can customize:

- Command aliases
- Permission nodes
- Messages shown to players

## Contributing

Contributions, issues, and feature requests are welcome!

1. Fork the repository
2. Create a new branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a pull request

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Credits

Developed by [EndcryptZ](https://github.com/EndcryptZ)

---

*OraxenToggle is an unofficial add-on and is not affiliated with or endorsed by Oraxen.*
