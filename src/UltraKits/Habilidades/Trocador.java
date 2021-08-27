package UltraKits.Habilidades;

import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;
import UltraKits.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Trocador implements Listener
{
    private HashMap<String, ItemStack[]> armors;
    public Plugin plugin;
    
    public Trocador(final Main plugin) {
        this.armors = new HashMap<String, ItemStack[]>();
        this.plugin = (Plugin)plugin;
    }
    
    @EventHandler
    public void Tartaruga(final PlayerToggleSneakEvent e) {
        final Player p = e.getPlayer();
        if (Main.Trocador.contains(p.getName())) {
            if (p.isSneaking()) {
                if (this.armors.containsKey(p.getName())) {
                    p.getEquipment().setArmorContents((ItemStack[])this.armors.get(p.getName()));
                    this.armors.remove(p.getName());
                }
            }
            else {
                this.armors.put(p.getName(), p.getEquipment().getArmorContents());
                p.getEquipment().setHelmet(new ItemStack(Material.getMaterial(this.plugin.getConfig().getInt("Trocador.change_helmet"))));
                p.getEquipment().setChestplate(new ItemStack(Material.getMaterial(this.plugin.getConfig().getInt("Trocador.change_chestplate"))));
                p.getEquipment().setLeggings(new ItemStack(Material.getMaterial(this.plugin.getConfig().getInt("Trocador.change_leggings"))));
                p.getEquipment().setBoots(new ItemStack(Material.getMaterial(this.plugin.getConfig().getInt("Trocador.change_boots"))));
            }
        }
    }
}
