package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import UltraKits.Main;
import UltraKits.u1v1.Commands;

public class Monk implements Listener {
	public Main plugin;
	public static HashMap<String, Long> coolmonk;

	static {
		Monk.coolmonk = new HashMap<String, Long>();
	}

	public Monk(final Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onHitWithBlazeRod(final EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		final Player t = (Player) e.getDamager();
		if (Main.monk.contains(p.getName())) {
			if (t.getItemInHand().getType() == Material.BLAZE_ROD) {
				if (Monk.coolmonk.containsKey(t.getName())) {
					e.setCancelled(true);
					t.sendMessage(ChatColor.RED + "Sua habilidade esta no cooldown!");
					return;
				}
				if (Main.areaPvP(t)) {
					if (Main.areaPvP(p)) {
						if (!Commands.em.contains(p.getName()) && !Commands.em.contains(t.getName())) {
							Monk.coolmonk.put(t.getName(), System.currentTimeMillis());
							e.setCancelled(true);
							p.sendMessage(ChatColor.RED + "Um Monk desarrumou seu inventario!");
							p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, (Object) null);
							t.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, (Object) null);
							final int random = new Random().nextInt(p.getInventory().getSize() - 10 + 1 + 10);
							final ItemStack selected = p.getInventory().getItem(random);
							final ItemStack held = p.getItemInHand();
							p.setItemInHand(selected);
							p.getInventory().setItem(random, held);
							Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
									(Runnable) new Runnable() {
										@Override
										public void run() {
											Monk.coolmonk.remove(t.getName());
										}
									}, 200L);
						}
					} else {
						t.sendMessage(ChatColor.RED + "Este player deve estar em uma \u00e1rea de PVP.");
					}
				} else {
					t.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
				}
			}
		}
	}
}
