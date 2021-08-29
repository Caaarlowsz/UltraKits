package UltraKits.Habilidades;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import UltraKits.Main;

public class Berserker implements Listener {
	@EventHandler
	public void forca(final PlayerDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			final Player p = e.getEntity().getKiller();
			if (Main.berserker.contains(p.getName())) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0));
				p.sendMessage(ChatColor.AQUA + "MODO " + ChatColor.DARK_PURPLE + "BERSERKER" + " ON!");
				p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1.0f, 1.0f);
			}
		}
	}
}
