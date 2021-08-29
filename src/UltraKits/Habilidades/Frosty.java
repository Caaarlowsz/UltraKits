package UltraKits.Habilidades;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import UltraKits.Main;

public class Frosty implements Listener {
	@EventHandler
	public void onPlayerCamel(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SNOW_BLOCK
				|| e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.ICE)
				&& Main.frosty.contains(p.getName())) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 0));
		}
	}
}
