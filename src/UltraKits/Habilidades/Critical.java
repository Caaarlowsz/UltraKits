package UltraKits.Habilidades;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import UltraKits.Main;

public class Critical implements Listener {
	@EventHandler
	public void dano(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player p = (Player) e.getEntity();
			final Player d = (Player) e.getDamager();
			if (Main.critical.contains(d.getName())) {
				final Random r = new Random();
				final int c = r.nextInt(100);
				if (c <= 30) {
					e.setDamage(e.getDamage() + 4.0);
					p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, (Object) Material.REDSTONE_BLOCK, 10);
					p.sendMessage(
							ChatColor.RED + "Voce recebeu um golpe critico de " + ChatColor.DARK_RED + d.getName());
					d.sendMessage(
							ChatColor.RED + "Voce aplicou um golpe critico em " + ChatColor.DARK_RED + p.getName());
				}
			}
		}
	}
}
