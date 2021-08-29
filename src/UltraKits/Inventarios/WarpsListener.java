package UltraKits.Inventarios;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WarpsListener implements Listener {
	@EventHandler
	public void onPlayerCLickInventry(final InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.BLACK + "Warps") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.GLASS) {
				p.closeInventory();
				p.chat("/fps");
				return;
			}
			if (e.getCurrentItem().getType() == Material.CAKE) {
				p.closeInventory();
				p.chat("/evento");
				return;
			}
			if (e.getCurrentItem().getType() == Material.GRASS) {
				p.closeInventory();
				p.chat("/sky");
				return;
			}
			if (e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
				p.closeInventory();
				p.chat("/earlyhg");
				return;
			}
			if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
				p.closeInventory();
				p.chat("/1v1");
				return;
			}
			if (e.getCurrentItem().getType() == Material.LAVA_BUCKET) {
				p.closeInventory();
				p.chat("/lc");
			}
		}
	}
}
