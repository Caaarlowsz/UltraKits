package UltraKits.Comandos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import UltraKits.Main;

public class MuteP implements Listener {
	@EventHandler
	public void onPlayerMutado(final AsyncPlayerChatEvent e) {
		final Player player = e.getPlayer();
		if (Main.mute.contains(player.getName())) {
			e.setCancelled(true);
			player.sendMessage(Main.plugin.getConfig().getString("Silenciado").replaceAll("&", "§"));
		}
	}
}
