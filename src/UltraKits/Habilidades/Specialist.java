package UltraKits.Habilidades;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import UltraKits.Main;

public class Specialist implements Listener {
	@EventHandler
	public void onIasnteract(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (Main.specialist.contains(p.getName())
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK
						|| event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
				&& p.getItemInHand().getType() == Material.ENCHANTED_BOOK) {
			p.openEnchanting((Location) null, true);
		}
	}

	@EventHandler
	public void onVampire(final EntityDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player killed = (Player) event.getEntity();
			if (killed.getKiller() instanceof Player) {
				final Player player = event.getEntity().getKiller();
				if (Main.specialist.contains(player.getName())) {
					player.getInventory().addItem(new ItemStack[] { new ItemStack(384, 1) });
					player.sendMessage(ChatColor.GREEN + "Voce ganhou um pote de EXP por matar " + killed.getName());
				}
			}
		}
	}
}
