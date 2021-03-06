package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import UltraKits.Main;

public class Phantom implements Listener {
	public static HashMap<String, Long> cooldown;

	static {
		Phantom.cooldown = new HashMap<String, Long>();
	}

	@EventHandler
	public void voar(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (e.getAction().name().contains("RIGHT") && p.getItemInHand().getType() == Material.FEATHER) {
			e.setCancelled(true);
			p.updateInventory();
			if (!Phantom.cooldown.containsKey(p.getName())
					|| Phantom.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				if (Main.areaPvP(p)) {
					p.setAllowFlight(true);
					p.setFlying(true);
					p.sendMessage(ChatColor.BLUE + "Agora voce pode voar por 5 segundos!");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							p.setFlying(false);
							p.setAllowFlight(false);
							p.sendMessage(ChatColor.RED + "Hora de cortar as asas desse passarinho!");
						}
					}, 100L);
					Phantom.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L));
				} else {
					p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
				}
			} else {
				p.sendMessage(ChatColor.RED + "Faltam "
						+ TimeUnit.MILLISECONDS
								.toSeconds(Phantom.cooldown.get(p.getName()) - System.currentTimeMillis())
						+ " segundos para poder usar novamente.");
			}
		}
	}
}
