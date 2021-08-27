package UltraKits.Inventarios;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import UltraKits.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Warps implements Listener
{
    public static void guiKits(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 27, ChatColor.BLACK + "Warps");
        final ItemStack vidro = new ItemStack(Material.THIN_GLASS);
        final ItemMeta metav = vidro.getItemMeta();
        metav.setDisplayName(ChatColor.AQUA + Main.plugin.getConfig().getString("ServerName"));
        vidro.setItemMeta(metav);
        inv.setItem(0, vidro);
        inv.setItem(1, vidro);
        inv.setItem(2, vidro);
        inv.setItem(3, vidro);
        inv.setItem(4, vidro);
        inv.setItem(5, vidro);
        inv.setItem(6, vidro);
        inv.setItem(7, vidro);
        inv.setItem(8, vidro);
        inv.setItem(9, vidro);
        inv.setItem(18, vidro);
        inv.setItem(19, vidro);
        inv.setItem(20, vidro);
        inv.setItem(21, vidro);
        inv.setItem(22, vidro);
        inv.setItem(23, vidro);
        inv.setItem(24, vidro);
        inv.setItem(25, vidro);
        inv.setItem(26, vidro);
        if (p.hasPermission("warp.evento")) {
            final ItemStack pyro = new ItemStack(Material.CAKE);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.GOLD + "Evento");
            final ArrayList<String> descpyro = new ArrayList<String>();
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("warp.lava")) {
            final ItemStack pyro = new ItemStack(Material.LAVA_BUCKET);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.GRAY + "Arena " + ChatColor.GOLD + "Lava Challenge");
            final ArrayList<String> descpyro = new ArrayList<String>();
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("warp.fps")) {
            final ItemStack pyro = new ItemStack(Material.GLASS);
            final ItemMeta metafish = pyro.getItemMeta();
            metafish.setDisplayName(ChatColor.GRAY + "Arena " + ChatColor.GOLD + "FPS");
            pyro.setItemMeta(metafish);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("warp.hg")) {
            final ItemStack pyro = new ItemStack(Material.MUSHROOM_SOUP);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.GRAY + "Arena " + ChatColor.GOLD + "HG");
            final ArrayList<String> descpyro = new ArrayList<String>();
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("warp.1v1")) {
            final ItemStack pyro = new ItemStack(Material.BLAZE_ROD);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.GRAY + "Arena " + ChatColor.GOLD + "1v1");
            final ArrayList<String> descpyro = new ArrayList<String>();
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        ItemStack[] arrayOfItemStack;
        for (int descpyro2 = (arrayOfItemStack = inv.getContents()).length, metapyro2 = 0; metapyro2 < descpyro2; ++metapyro2) {
            final ItemStack item = arrayOfItemStack[metapyro2];
            if (item == null) {
                inv.setItem(inv.firstEmpty(), vidro);
            }
        }
        p.openInventory(inv);
        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 40.0f, 1.0f);
    }
}
