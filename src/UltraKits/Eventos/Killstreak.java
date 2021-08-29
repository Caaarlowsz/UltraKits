package UltraKits.Eventos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import UltraKits.Main;

public class Killstreak implements Listener {
	@EventHandler
	public void onDeathEvent(final PlayerDeathEvent event) {
		final Player p = event.getEntity();
		final Player killer = event.getEntity().getKiller();
		if (p instanceof Player && killer instanceof Player) {
			if (!Main.killstreaks.containsKey(killer.getName())) {
				Main.killstreaks.put(killer.getName(), 0);
				Main.killstreaks.put(killer.getName(), Main.killstreaks.get(killer.getName()) + 1);
			} else if (Main.killstreaks.containsKey(killer.getName())) {
				Main.killstreaks.put(killer.getName(), Main.killstreaks.get(killer.getName()) + 1);
			}
			if (Main.killstreaks.containsKey(p.getName())) {
				if (Main.killstreaks.get(p.getName()) >= 5) {
					Bukkit.broadcastMessage(
							ChatColor.RED + killer.getName() + " acabou com o killstreak de " + p.getName());
				}
				Main.killstreaks.remove(p.getName());
			}
			if (Main.killstreaks.get(killer.getName()) == 5) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 5.");
			} else if (Main.killstreaks.get(killer.getName()) == 10) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 10.");
			} else if (Main.killstreaks.get(killer.getName()) == 25) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 25.");
			} else if (Main.killstreaks.get(killer.getName()) == 50) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 50.");
			} else if (Main.killstreaks.get(killer.getName()) == 75) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 75.");
			} else if (Main.killstreaks.get(killer.getName()) == 100) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 100.");
			} else if (Main.killstreaks.get(killer.getName()) == 200) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 200.");
			} else if (Main.killstreaks.get(killer.getName()) == 300) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 300.");
			} else if (Main.killstreaks.get(killer.getName()) == 400) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 400.");
			} else if (Main.killstreaks.get(killer.getName()) == 500) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 500.");
			} else if (Main.killstreaks.get(killer.getName()) == 600) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 600.");
			} else if (Main.killstreaks.get(killer.getName()) == 700) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 700.");
			} else if (Main.killstreaks.get(killer.getName()) == 800) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 800.");
			} else if (Main.killstreaks.get(killer.getName()) == 900) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 900.");
			} else if (Main.killstreaks.get(killer.getName()) == 1000) {
				Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + " esta com um killstreak de 1000.");
			}
		}
	}

	@EventHandler
	public void OnPlayerLeave(final PlayerQuitEvent event) {
		final Player p = event.getPlayer();
		Main.killstreaks.remove(p.getName());
	}
}
