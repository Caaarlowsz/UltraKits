package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import UltraKits.Main;

public class Ninja implements Listener {
	public static HashMap<String, Long> cooldown;
	public static HashMap<String, String> tp;

	static {
		Ninja.cooldown = new HashMap<String, Long>();
		Ninja.tp = new HashMap<String, String>();
	}

	@EventHandler
	public void marcar(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player p = (Player) e.getEntity();
			final Player d = (Player) e.getDamager();
			if (Main.ninja.contains(d.getName())) {
				Ninja.tp.put(d.getName(), p.getName());
			}
		}
	}

	@EventHandler
	public void teleportar(final PlayerToggleSneakEvent e) {
		final Player p = e.getPlayer();
		if (Main.ninja.contains(p.getName()) && p.isSneaking()) {
			if (Ninja.tp.containsKey(p.getName())) {
				if (!Ninja.cooldown.containsKey(p.getName())
						|| Ninja.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
					final String r = Ninja.tp.get(p.getName());
					final Player req = Bukkit.getPlayer(r);
					if (req != null) {
						if (!Main.inGladiator(p)) {
							if (!Main.inGladiator(req)) {
								if (Main.areaPvP(p)) {
									if (Main.areaPvP(req)) {
										p.teleport((Entity) req);
										p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
										Ninja.cooldown.put(p.getName(),
												System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(12L));
									} else {
										p.sendMessage(ChatColor.RED + "Este player nao est\u00e1 em uma area com PVP.");
									}
								} else {
									p.sendMessage(
											ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
								}
							} else {
								p.sendMessage(ChatColor.RED + "Este player est\u00e1 lutando no Gladiator.");
							}
						} else {
							p.sendMessage(ChatColor.RED + "Voce est\u00e1 lutando no Gladiator.");
						}
					} else {
						p.sendMessage(ChatColor.RED + "Este player nao est\u00e1 online.");
						Ninja.tp.remove(p.getName());
					}
				} else {
					p.sendMessage(ChatColor.RED + "Faltam "
							+ TimeUnit.MILLISECONDS
									.toSeconds(Ninja.cooldown.get(p.getName()) - System.currentTimeMillis())
							+ " segundos para poder usar novamente.");
				}
			} else {
				p.sendMessage(ChatColor.RED + "Ningu\u00e9m para se teleportar.");
			}
		}
	}

	public static void resetTarget(final Player p) {
		for (final Map.Entry<String, String> e : Ninja.tp.entrySet()) {
			final String s = e.getValue();
			if (p.getName() == s) {
				Ninja.tp.remove(e.getKey());
			}
		}
	}
}
