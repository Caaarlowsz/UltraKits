package UltraKits.Habilidades;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

import UltraKits.Main;

public class Launcher implements Listener {
	public Main plugin;

	public Launcher(final Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerHitFishingrodThrower(final PlayerFishEvent event) {
		final Player player = event.getPlayer();
		if (Main.launcher.contains(player.getName()) && event.getCaught() instanceof Player) {
			final Player caught = (Player) event.getCaught();
			if (player.getItemInHand().getType() == Material.FISHING_ROD) {
				caught.setVelocity(new Vector(0, 2, 0));
			}
		}
	}
}
