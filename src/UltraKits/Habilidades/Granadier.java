package UltraKits.Habilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import UltraKits.Main;

public class Granadier implements Listener {
	@EventHandler
	public void lancar(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.granadier.contains(p.getName()) && p.getItemInHand().getType() == Material.SNOW_BALL) {
			e.setCancelled(true);
			p.updateInventory();
			p.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.SNOW_BALL, 1) });
			final Snowball granada = (Snowball) p.launchProjectile(Snowball.class);
			granada.setMetadata("granadier", (MetadataValue) new FixedMetadataValue(Main.plugin, (Object) true));
			p.playSound(p.getLocation(), Sound.FUSE, 1.0f, 1.0f);
			p.sendMessage(ChatColor.GOLD + "Granada jogada!");
		}
	}

	@EventHandler
	public void explosao(final ProjectileHitEvent e) {
		if (e.getEntity() instanceof Snowball) {
			final Snowball b = (Snowball) e.getEntity();
			if (b.hasMetadata("granadier")) {
				b.getWorld().createExplosion(b.getLocation(), 3.0f);
			}
		}
	}

	@EventHandler
	public void dano(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player p = (Player) e.getEntity();
			if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION && Main.granadier.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
}
