package UltraKits.Comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import UltraKits.Main;
import UltraKits.u1v1.Commands;

public class CombatLog implements Listener {
	public static ArrayList<String> cl;

	static {
		CombatLog.cl = new ArrayList<String>();
	}

	@EventHandler
	public void entrarCombate(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player p = (Player) e.getEntity();
			final Player damager = (Player) e.getDamager();
			if (Main.plugin.getConfig().getBoolean("CombatLog") && !Commands.em.contains(p.getName())) {
				if (!CombatLog.cl.contains(p.getName())) {
					CombatLog.cl.add(p.getName());
					p.sendMessage(
							ChatColor.RED + "Voce entrou em combate com: " + ChatColor.DARK_RED + damager.getName()
									+ ChatColor.RED + ". Nao podera fugir durante os proximos 10 segundos.");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							if (CombatLog.cl.contains(p.getName())) {
								CombatLog.cl.remove(p.getName());
								if (p != null) {
									p.sendMessage(ChatColor.GREEN + "Voce nao esta mais em combate.");
								}
							}
						}
					}, 200L);
				}
				if (!CombatLog.cl.contains(damager.getName())) {
					CombatLog.cl.add(damager.getName());
					damager.sendMessage(ChatColor.RED + "Voce entrou em combate com: " + ChatColor.DARK_RED
							+ p.getName() + ChatColor.RED + ". Nao podera fugir durante os proximos 10 segundos.");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							if (CombatLog.cl.contains(damager.getName())) {
								CombatLog.cl.remove(damager.getName());
								if (damager != null) {
									damager.sendMessage(ChatColor.GREEN + "Voce nao esta mais em combate.");
								}
							}
						}
					}, 200L);
				}
			}
		}
	}

	@EventHandler
	public void antiFuga(final PlayerCommandPreprocessEvent e) {
		if (CombatLog.cl.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "Voce est\u00e1 em combate. Aguarde para digitar comandos.");
		}
	}

	@EventHandler
	public void reset(final PlayerDeathEvent e) {
		final Player p = e.getEntity();
		if (CombatLog.cl.contains(p.getName())) {
			CombatLog.cl.remove(p.getName());
		}
	}
}
