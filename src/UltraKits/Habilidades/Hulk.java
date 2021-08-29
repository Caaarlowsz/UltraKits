package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import UltraKits.Main;

public class Hulk implements Listener {
	public static HashMap<String, Long> cooldown;

	static {
		Hulk.cooldown = new HashMap<String, Long>();
	}

	@EventHandler
	public void pegar(final PlayerInteractEntityEvent e) {
		final Player p = e.getPlayer();
		if (e.getRightClicked() instanceof Player) {
			final Player r = (Player) e.getRightClicked();
			if (Main.hulk.contains(p.getName())) {
				if (!Hulk.cooldown.containsKey(p.getName())
						|| Hulk.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
					if (Main.areaPvP(p)) {
						if (Main.areaPvP(r)) {
							if (p.getItemInHand().getType() == Material.SADDLE) {
								e.setCancelled(true);
								p.updateInventory();
								p.setPassenger((Entity) r);
								Hulk.cooldown.put(p.getName(),
										System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L));
								p.sendMessage(ChatColor.GOLD + "Voce pegou o player: " + ChatColor.WHITE + r.getName());
								r.sendMessage(
										ChatColor.GOLD + "Voce foi pego pelo Hulk: " + ChatColor.WHITE + p.getName());
							}
						} else {
							p.sendMessage(ChatColor.RED
									+ "Voce pode usar esta habilidade apenas em players que estejam em areas com PVP.");
						}
					} else {
						p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
					}
				} else {
					p.sendMessage(ChatColor.RED + "Faltam "
							+ TimeUnit.MILLISECONDS
									.toSeconds(Hulk.cooldown.get(p.getName()) - System.currentTimeMillis())
							+ " segundos para poder usar novamente.");
				}
			}
		}
	}

	@EventHandler
	public static void playerInteract(final PlayerInteractEvent event) {
		if (!event.getAction().equals((Object) Action.LEFT_CLICK_AIR)) {
			return;
		}
		final Player player = event.getPlayer();
		if (player.getPassenger() == null || !(player.getPassenger() instanceof Player)) {
			return;
		}
		if (!Main.areaPvP(player) || !Main.areaPvP((Player) player.getPassenger())) {
			player.sendMessage(ChatColor.RED + "Essa habilidade pode ser usada apenas em areas com PVP.");
			return;
		}
		final Player pass = (Player) player.getPassenger();
		player.eject();
		pass.damage(0.0, (Entity) player);
		pass.setVelocity(player.getLocation().getDirection().multiply(2.5));
		pass.setVelocity(new Vector(pass.getVelocity().getX(), 1.0, pass.getVelocity().getZ()));
		pass.sendMessage(ChatColor.RED + "Voc\u00ea foi jogado por " + ChatColor.DARK_RED + player.getName());
	}
}
