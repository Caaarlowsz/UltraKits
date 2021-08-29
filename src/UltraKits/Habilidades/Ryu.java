package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import UltraKits.Main;

public class Ryu implements Listener {
	public static HashMap<String, Long> cooldown;

	static {
		Ryu.cooldown = new HashMap<String, Long>();
	}

	@EventHandler
	public void hadouken(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.ryu.contains(p.getName()) && p.getItemInHand().getType() == Material.BEACON) {
			if (!Ryu.cooldown.containsKey(p.getName()) || Ryu.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				if (Main.areaPvP(p)) {
					final Location location = p.getEyeLocation();
					final BlockIterator blocksToAdd = new BlockIterator(location, 0.0, 40);
					while (blocksToAdd.hasNext()) {
						final Location blockToAdd = blocksToAdd.next().getLocation();
						p.getWorld().playEffect(blockToAdd, Effect.STEP_SOUND, (Object) Material.BEACON, 20);
						p.playSound(blockToAdd, Sound.IRONGOLEM_THROW, 3.0f, 3.0f);
					}
					final Snowball h = (Snowball) p.launchProjectile(Snowball.class);
					final Vector velo1 = p.getLocation().getDirection().normalize().multiply(10);
					h.setVelocity(velo1);
					h.setMetadata("hadouken", (MetadataValue) new FixedMetadataValue(Main.plugin, (Object) true));
					Ryu.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L));
					p.sendMessage(ChatColor.AQUA + "HADOUKEN!");
				} else {
					p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
				}
			} else {
				p.sendMessage(ChatColor.RED + "Faltam "
						+ TimeUnit.MILLISECONDS.toSeconds(Ryu.cooldown.get(p.getName()) - System.currentTimeMillis())
						+ " segundos para poder usar novamente.");
			}
		}
	}

	@EventHandler
	public void dano(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Snowball) {
			final Snowball s = (Snowball) e.getDamager();
			if (s.hasMetadata("hadouken")) {
				e.setDamage(e.getDamage() + 8.0);
			}
		}
	}
}
