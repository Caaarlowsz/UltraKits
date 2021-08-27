package UltraKits.Inventarios;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class MenuInvListener implements Listener
{
    @EventHandler
    public void onPlayerCLickInventry(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase(new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Seus Kits").toString()) && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() != 0) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.STONE_SWORD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit pvp");
                return;
            }
            if (e.getCurrentItem().getType() == Material.FIREWORK_CHARGE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit pyro");
                return;
            }
            if (e.getCurrentItem().getType() == Material.FIRE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit berserker");
                return;
            }
            if (e.getCurrentItem().getType() == Material.IRON_CHESTPLATE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit soldado");
                return;
            }
            if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit shooter");
                return;
            }
            if (e.getCurrentItem().getType() == Material.COMPASS) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit ninja");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SAPLING) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit rhino");
                return;
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit trocador");
                return;
            }
            if (e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit urgal");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BOW) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit arqueiro");
                return;
            }
            if (e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit vitality");
                return;
            }
            if (e.getCurrentItem().getType() == Material.COAL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit darkmage");
                return;
            }
            if (e.getCurrentItem().getType() == Material.WATER_BUCKET) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit poseidon");
                return;
            }
            if (e.getCurrentItem().getType() == Material.LAVA_BUCKET) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit fireman");
                return;
            }
            if (e.getCurrentItem().getType() == Material.REDSTONE_TORCH_ON) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit flash");
                return;
            }
            if (e.getCurrentItem().getType() == Material.ANVIL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit anchor");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SPONGE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit launcher");
                return;
            }
            if (e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit turtle");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BONE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit skeleton");
                return;
            }
            if (e.getCurrentItem().getType() == Material.MILK_BUCKET) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit milkman");
                return;
            }
            if (e.getCurrentItem().getType() == Material.FISHING_ROD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit fisherman");
                return;
            }
            if (e.getCurrentItem().getType() == Material.LEASH) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit grappler");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SAND) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit camel");
                return;
            }
            if (e.getCurrentItem().getType() == Material.STONE_AXE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit thor");
                return;
            }
            if (e.getCurrentItem().getType() == Material.EXP_BOTTLE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit specialist");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SNOW_BLOCK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit frosty");
                return;
            }
            if (e.getCurrentItem().getType() == Material.FEATHER) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit phantom");
                return;
            }
            if (e.getCurrentItem().getType() == Material.FIREWORK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit Kangaroo");
                return;
            }
            if (e.getCurrentItem().getType() == Material.PORTAL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit endermage");
                return;
            }
            if (e.getCurrentItem().getType() == Material.IRON_BOOTS) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit stomper");
                return;
            }
            if (e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit turtle");
                return;
            }
            if (e.getCurrentItem().getType() == Material.WOOD_HOE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit reaper");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SPIDER_EYE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit viper");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SOUL_SAND) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit snail");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit monk");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SNOW_BALL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit switcher");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit wither");
                return;
            }
            if (e.getCurrentItem().getType() == Material.TNT) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit granadier");
                return;
            }
            if (e.getCurrentItem().getType() == Material.IRON_FENCE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit gladiator");
                return;
            }
            if (e.getCurrentItem().getType() == Material.TRAP_DOOR) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit jumper");
                return;
            }
            if (e.getCurrentItem().getType() == Material.JACK_O_LANTERN) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit madman");
                return;
            }
            if (e.getCurrentItem().getType() == Material.GOLD_AXE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit viking");
                return;
            }
            if (e.getCurrentItem().getType() == Material.STICK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit grandpa");
                return;
            }
            if (e.getCurrentItem().getType() == Material.IRON_HOE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit ghost");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BOWL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit quickdropper");
                return;
            }
            if (e.getCurrentItem().getType() == Material.ENDER_PEARL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit teleporter");
                return;
            }
            if (e.getCurrentItem().getType() == Material.WEB) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit spiderman");
                return;
            }
            if (e.getCurrentItem().getType() == Material.IRON_ORE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit barbarian");
                return;
            }
            if (e.getCurrentItem().getType() == Material.REDSTONE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit berserker");
                return;
            }
            if (e.getCurrentItem().getType() == Material.PUMPKIN_SEEDS) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit indio");
                return;
            }
            if (e.getCurrentItem().getType() == Material.ICE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit freezer");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BEACON) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit ryu");
                return;
            }
            if (e.getCurrentItem().getType() == Material.EYE_OF_ENDER) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit neji");
                return;
            }
            if (e.getCurrentItem().getType() == Material.MONSTER_EGG) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit lobisomem");
                return;
            }
            if (e.getCurrentItem().getType() == Material.ENDER_PORTAL_FRAME) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit alien");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SADDLE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit hulk");
                return;
            }
            if (e.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kit critical");
            }
        }
    }
}
