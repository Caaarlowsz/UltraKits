package UltraKits;

import org.bukkit.event.*;
import org.bukkit.*;
import java.io.*;
import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;

public class SettingsManager implements Listener
{
    static SettingsManager instance;
    Plugin p;
    static FileConfiguration config;
    static File cfile;
    static FileConfiguration data;
    static File dfile;
    static FileConfiguration duel;
    static File filed;
    
    static {
        SettingsManager.instance = new SettingsManager();
    }
    
    public static SettingsManager getInstance() {
        return SettingsManager.instance;
    }
    
    public void setup(final Plugin p) {
        SettingsManager.cfile = new File(p.getDataFolder(), "config.yml");
        SettingsManager.config = p.getConfig();
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        SettingsManager.dfile = new File(p.getDataFolder(), "data.yml");
        if (!SettingsManager.dfile.exists()) {
            try {
                SettingsManager.dfile.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel criar a pasta data.yml");
            }
        }
        SettingsManager.data = (FileConfiguration)YamlConfiguration.loadConfiguration(SettingsManager.dfile);
        SettingsManager.filed = new File(p.getDataFolder(), "config.yml");
        if (!SettingsManager.filed.exists()) {
            try {
                SettingsManager.filed.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel criar a pasta config.yml!");
            }
        }
        SettingsManager.config = (FileConfiguration)YamlConfiguration.loadConfiguration(SettingsManager.filed);
    }
    
    public FileConfiguration getDuel() {
        return SettingsManager.config;
    }
    
    public void saveDuel() {
        try {
            SettingsManager.config.save(SettingsManager.filed);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel salvar config.yml!");
        }
    }
    
    public void reloadDuel() {
        SettingsManager.duel = (FileConfiguration)YamlConfiguration.loadConfiguration(SettingsManager.filed);
    }
    
    public FileConfiguration getData() {
        return SettingsManager.data;
    }
    
    public void saveData() {
        try {
            SettingsManager.data.save(SettingsManager.dfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel salvar data.yml!");
        }
    }
    
    public void reloadData() {
        SettingsManager.data = (FileConfiguration)YamlConfiguration.loadConfiguration(SettingsManager.dfile);
    }
    
    public FileConfiguration getConfig() {
        return SettingsManager.config;
    }
    
    public void saveConfig() {
        try {
            SettingsManager.config.save(SettingsManager.cfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel salvar config.yml!");
        }
    }
    
    public void reloadConfig() {
        SettingsManager.config = (FileConfiguration)YamlConfiguration.loadConfiguration(SettingsManager.cfile);
    }
    
    public PluginDescriptionFile getDesc() {
        return this.p.getDescription();
    }
}
