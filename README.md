# OraxenToggle

OraxenToggle is a lightweight plugin designed to provide simple toggling functionality for the [Oraxen](https://github.com.oraxen/oraxen) Minecraft plugin. This tool allows server administrators and players to enable or disable Oraxen features easily without needing to restart the server or manually edit configuration files.

## Features

- **Toggle Oraxen Functionality:** Instantly enable or disable Oraxen features with simple commands.
- **Permission-Based Access:** Restrict toggling commands to authorized users.
- **Lightweight & Easy to Use:** Minimal setup required; easy integration with existing Oraxen setups.
- **Configurable:** Customize command names and permissions via the configuration file.

## Installation

1. Download the latest release of OraxenToggle from the [releases page](https://github.com/EndcryptZ/OraxenToggle/releases).
2. Place the `OraxenToggle.jar` file into your server's `plugins` directory.
3. Ensure you have the [Oraxen plugin](https://github.com/oraxen/oraxen) installed and configured.
4. Restart or reload your Minecraft server.

## Usage

- **Command:**  
  `/oraxentoggle`  
  Toggles the Oraxen plugin state (enabled/disabled).

- **Permissions:**  
  `oraxentoggle.toggle` - Allows a user to use the toggle command.

### Example

```
/oraxentoggle
```

The plugin will respond with the new state of Oraxen (enabled or disabled).

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
