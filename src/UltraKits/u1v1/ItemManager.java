package UltraKits.u1v1;

import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;
import org.bukkit.inventory.*;
import UltraKits.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import java.util.*;

public class ItemManager
{
    static FileConfiguration kits;
    static Plugin pl;
    static HashMap<ItemStack, String> id;
    static HashMap<ItemStack, Integer> items;
    
    static {
        ItemManager.kits = Main.kit;
        ItemManager.pl = Main.plugin;
        ItemManager.id = new HashMap<ItemStack, String>();
        ItemManager.items = new HashMap<ItemStack, Integer>();
    }
    
    public static void loadItems() {
        if (ItemManager.pl.getConfig().getBoolean("EnableCustom")) {
            final Material m = Material.getMaterial(ItemManager.pl.getConfig().getInt("Custom.id"));
            if (m != null) {
                final ItemStack c = new ItemStack(m);
                final ItemMeta i = c.getItemMeta();
                i.setDisplayName(ItemManager.pl.getConfig().getString("Custom.title").replaceAll("&", "§"));
                final List<String> lore = (List<String>)ItemManager.pl.getConfig().getStringList("Custom.lore");
                if (lore != null && !lore.isEmpty()) {
                    final ArrayList<String> loreA = new ArrayList<String>();
                    for (int a = 0; a < lore.size(); ++a) {
                        loreA.add(lore.get(a).replaceAll("&", "§"));
                    }
                    i.setLore((List)loreA);
                }
                c.setItemMeta(i);
                Custom.customItem.clear();
                Custom.customItem.add(c);
                ItemManager.items.put(c, ItemManager.pl.getConfig().getInt("Custom.slot") - 1);
            }
            else {
                Bukkit.getConsoleSender().sendMessage("§b[Mega1V1] §cErro na configuracao do material do custom.");
            }
        }
        final Material material = Material.getMaterial(ItemManager.pl.getConfig().getInt("Item.id"));
        if (material != null) {
            Desafiar.item = new ItemStack(material);
            final ItemMeta im = Desafiar.item.getItemMeta();
            im.setDisplayName(ItemManager.pl.getConfig().getString("Item.title").replaceAll("&", "§"));
            final List<String> lore2 = (List<String>)ItemManager.pl.getConfig().getStringList("Item.lore");
            if (lore2 != null && !lore2.isEmpty()) {
                final ArrayList<String> loreA2 = new ArrayList<String>();
                for (int j = 0; j < lore2.size(); ++j) {
                    loreA2.add(lore2.get(j).replaceAll("&", "§"));
                }
                im.setLore((List)loreA2);
                Desafiar.item.setItemMeta(im);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage("§b[Mega1V1] §cErro na configuracao do item.");
        }
        final List<String> kit = (List<String>)ItemManager.kits.getStringList("Kits");
        if (kit != null && !kit.isEmpty()) {
            for (final String k : kit) {
                if (ItemManager.kits.getConfigurationSection(k) == null) {
                    Bukkit.getConsoleSender().sendMessage("§b[Mega1V1] §cO Kit §f'" + k + "' §cnao foi encontrado.");
                    return;
                }
                final Material mat = Material.getMaterial(ItemManager.kits.getInt(String.valueOf(k) + ".id"));
                if (mat != null) {
                    final ItemStack item = new ItemStack(mat);
                    final ItemMeta im2 = item.getItemMeta();
                    if (ItemManager.kits.getString(String.valueOf(k) + ".title") != null) {
                        im2.setDisplayName(ItemManager.kits.getString(String.valueOf(k) + ".title").replaceAll("&", "§"));
                    }
                    if (ItemManager.kits.getStringList(String.valueOf(k) + ".lore") != null) {
                        final List<String> l = (List<String>)ItemManager.kits.getStringList(String.valueOf(k) + ".lore");
                        final ArrayList<String> lore3 = new ArrayList<String>();
                        for (int i2 = 0; i2 < l.size(); ++i2) {
                            lore3.add(l.get(i2).replaceAll("&", "§"));
                        }
                        im2.setLore((List)lore3);
                    }
                    item.setItemMeta(im2);
                    ItemManager.items.put(item, ItemManager.kits.getInt(String.valueOf(k) + ".slot") - 1);
                    ItemManager.id.put(item, k);
                }
                else {
                    Bukkit.getConsoleSender().sendMessage("§b[Mega1V1] §cErros de configuracao no Kit:§f '" + k + "'");
                }
            }
        }
    }
}
