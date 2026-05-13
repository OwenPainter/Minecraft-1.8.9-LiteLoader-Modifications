# Minecraft-1.8.9-LiteLoader-Modifications
Repository to store client side mods made for Minecraft 1.8.9 LiteLoader

Source code for all mods is provided in the `src/` directory to comply with the GNU GPLv3 license requirements. 
Developers can also decompile the .litemod files if they wish to verify the build integrity.

## Mod List

### 1. Highlightr (v1.0.0)
**Author:** LiamTheToe
**Description:** A Block ESP mod that allows users to highlight specific blocks in the world.
- **Block ESP:** Highlights target blocks with a visual bounding box, even through walls.
- **Customizable:** Users can specify the target block ID and adjustment the scan radius via the in-game config panel.
- **Performance:** Optimized scanning logic to minimize impact on frame rates.

### 2. SettingsAPI (v1.0.0)
**Author:** LiamTheToe
**Description:** A utility mod that provides configuration infrastructure and includes a stealthy combat reach enhancement.
- **Combat Reach:** Discreetly extends the player's attack range by patching the `EntityRenderer` bytecode.
- **Config Panel:** Provides an integrated menu for managing mod settings.
- **Stealth Design:** Designed to be subtle and minimize detection.

### 3. Omniscience (v3.3.0)
**Author:** Originally by [EasyMFnE](https://github.com/EasyMFnE/Omniscience).  
**Modified by:** LiamTheToe (May 2026).
**Description:** A powerful visibility modification that reveals invisible players and entities.
- **True Sight:** Renders invisible players and entities as green, making them visible to the user.
- **Chams:** Features entity "chams" (Colored Models) to visualize players through solid objects.
- **Fine-grained Control:** Toggle visibility for players and other entities independently through the settings menu.

## License
This project is licensed under the **GNU General Public License v3.0**. 

- **Original Omniscience:** Licensed under GPLv3 by EasyMFnE.
- **Modifications and Other Mods:** All modifications and original works by LiamTheToe are also licensed under GPLv3 to ensure compliance and support open-source development.

See the [LICENSE](LICENSE) file for the full license text.
