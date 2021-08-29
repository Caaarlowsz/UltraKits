package UltraKits.Habilidades;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import UltraKits.Main;

public class Switcher implements Listener {
	@EventHandler
	public void trocar(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Snowball) {
			final Player p = (Player) e.getEntity();
			final Snowball b = (Snowball) e.getDamager();
			if (b.getShooter() instanceof Player) {
				final Player h = (Player) b.getShooter();
				if (Main.switcher.contains(h.getName())) {
					final Location ploc = p.getLocation();
					final Location hloc = h.getLocation();
					p.teleport(hloc);
					h.teleport(ploc);
				}
			}
		}
	}
}
