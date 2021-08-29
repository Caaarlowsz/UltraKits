package UltraKits.Habilidades;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import UltraKits.Main;

public class Viking implements Listener {
	@EventHandler
	public void main(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player p = (Player) e.getEntity();
			final Player d = (Player) e.getDamager();
			if (Main.viking.contains(d.getName()) && (d.getItemInHand().getType() == Material.WOOD_AXE
					|| d.getItemInHand().getType() == Material.STONE_AXE
					|| p.getItemInHand().getType() == Material.GOLD_AXE
					|| p.getItemInHand().getType() == Material.IRON_AXE
					|| p.getItemInHand().getType() == Material.DIAMOND_AXE)) {
				e.setDamage(e.getDamage() * 2.0);
			}
		}
	}

	@EventHandler
	public void madman(final EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		final Player d = (Player) e.getDamager();
		if (!Main.madman.contains(d.getName())) {
			return;
		}
		final Random rand = new Random();
		final int percent = rand.nextInt(100);
		if (percent <= 33) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 30, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 30, 0));
		}
	}
}
