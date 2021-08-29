package UltraKits.u1v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import UltraKits.Main;
import UltraKits.ServerScoreboard;
import UltraKits.Habilidades.Ninja;
import me.confuser.barapi.BarAPI;

public class Commands implements CommandExecutor {
	public static HashMap<String, ItemStack[]> inv;
	public static HashMap<String, ItemStack[]> arm;
	public static ArrayList<String> em;
	public static FileConfiguration data;
	public static Plugin pl;

	static {
		Commands.inv = new HashMap<String, ItemStack[]>();
		Commands.arm = new HashMap<String, ItemStack[]>();
		Commands.em = new ArrayList<String>();
		Commands.data = Main.data;
		Commands.pl = Main.plugin;
	}

	public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("[Mega1V1] Comando apenas para players in-game.");
			return true;
		}
		final Player p = (Player) sender;
		if (cmd.equalsIgnoreCase("1v1")) {
			if (args.length == 0) {
				if (!p.hasPermission("1v1.jogar")) {
					this.sendConfigMessage(p, "SemPermissao");
					return true;
				}
				if (!Commands.em.contains(p.getName())) {
					if (Main.data.getConfigurationSection("Spawn") == null) {
						p.sendMessage(Commands.pl.getConfig().getString("ErroConfig").replaceAll("&", "§"));
						return true;
					}
					final Location loc1 = new Location(Bukkit.getWorld(Main.data.getString("Spawn.world")),
							Main.data.getDouble("Spawn.x"), Main.data.getDouble("Spawn.y"),
							Main.data.getDouble("Spawn.z"), (float) Main.data.getLong("Spawn.yaw"),
							(float) Main.data.getLong("Spawn.pitch"));
					p.teleport(loc1);
					Commands.arm.put(p.getName(), p.getEquipment().getArmorContents());
					Commands.inv.put(p.getName(), p.getInventory().getContents());
					Main.resetKit(p);
					limparInv(p);
					p.getInventory().addItem(new ItemStack[] { Desafiar.item });
					if (Main.plugin.getConfig().getBoolean("EnableScoreboard2")) {
						p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
						SB.criarScoreboard(p);
						p.setHealth(20.0);
					}
					Ninja.resetTarget(p);
					this.sendConfigMessage(p, "Entrou");
					Commands.em.add(p.getName());
				} else {
					Commands.em.remove(p.getName());
					if (Main.plugin.getConfig().getBoolean("EnableScoreboard2")) {
						p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
					}
					if (Main.plugin.getConfig().getBoolean("EnableScoreboard")) {
						ServerScoreboard.criarScoreboard(p);
					}
					if (Main.data.getConfigurationSection("Saida") != null) {
						final Location loc1 = new Location(Bukkit.getWorld(Main.data.getString("Saida.world")),
								Main.data.getDouble("Saida.x"), Main.data.getDouble("Saida.y"),
								Main.data.getDouble("Saida.z"), (float) Main.data.getLong("Saida.yaw"),
								(float) Main.data.getLong("Saida.pitch"));
						p.teleport(loc1);
					} else {
						p.teleport(p.getWorld().getSpawnLocation());
					}
					limparInv(p);
					if (Commands.inv.containsKey(p.getName())) {
						p.getInventory().setContents((ItemStack[]) Commands.inv.get(p.getName()));
						Commands.inv.remove(p.getName());
					}
					if (Commands.arm.containsKey(p.getName())) {
						p.getEquipment().setArmorContents((ItemStack[]) Commands.arm.get(p.getName()));
						Commands.arm.remove(p.getName());
					}
					this.sendConfigMessage(p, "ItemsRestaurados");
					this.sendConfigMessage(p, "Saiu");
					p.setHealth(20.0);
					BarAPI.setMessage(p, ChatColor.RED + "Voce saiu do 1v1!", 5);
				}
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("set") && !p.hasPermission("1v1.set")) {
					p.sendMessage(Main.plugin.getConfig().getString("SemPermissao").replaceAll("&", "§"));
					return true;
				}
				if (args[0].equalsIgnoreCase("reload")) {
					if (!p.hasPermission("1v1.set")) {
						p.sendMessage(Main.plugin.getConfig().getString("SemPermissao").replaceAll("&", "§"));
						return true;
					}
					this.reloadPlugin();
					p.sendMessage(Main.plugin.getConfig().getString("ConfigReload").replaceAll("&", "§"));
				}
				if (!args[0].equalsIgnoreCase("reload") && !args[0].equalsIgnoreCase("set")
						&& p.hasPermission("1v1.set")) {
					for (final String s : Main.plugin.getConfig().getStringList("UsoAdmin")) {
						p.sendMessage(s.replaceAll("&", "§"));
					}
				}
			} else if (args.length > 1) {
				if (!p.hasPermission("1v1.set")) {
					p.sendMessage(Main.plugin.getConfig().getString("SemPermissao").replaceAll("&", "§"));
					return true;
				}
				if (args[0].equalsIgnoreCase("set")) {
					if (args[1].equalsIgnoreCase("spawn")) {
						Main.data.set("Spawn.world", (Object) p.getWorld().getName());
						Main.data.set("Spawn.x", (Object) p.getLocation().getX());
						Main.data.set("Spawn.y", (Object) p.getLocation().getY());
						Main.data.set("Spawn.z", (Object) p.getLocation().getZ());
						Main.data.set("Spawn.yaw", (Object) p.getLocation().getYaw());
						Main.data.set("Spawn.pitch", (Object) p.getLocation().getPitch());
						try {
							Main.data.save(Main.d);
						} catch (IOException e) {
							e.printStackTrace();
						}
						p.sendMessage(Main.plugin.getConfig().getString("SpawnSetado").replaceAll("&", "§"));
					}
					if (args[1].equalsIgnoreCase("pos1")) {
						Main.data.set("Pos1.world", (Object) p.getWorld().getName());
						Main.data.set("Pos1.x", (Object) p.getLocation().getX());
						Main.data.set("Pos1.y", (Object) p.getLocation().getY());
						Main.data.set("Pos1.z", (Object) p.getLocation().getZ());
						Main.data.set("Pos1.yaw", (Object) p.getLocation().getYaw());
						Main.data.set("Pos1.pitch", (Object) p.getLocation().getPitch());
						try {
							Main.data.save(Main.d);
						} catch (IOException e) {
							e.printStackTrace();
						}
						p.sendMessage(Main.plugin.getConfig().getString("Pos1Setada").replaceAll("&", "§"));
					}
					if (args[1].equalsIgnoreCase("pos2")) {
						Main.data.set("Pos2.world", (Object) p.getWorld().getName());
						Main.data.set("Pos2.x", (Object) p.getLocation().getX());
						Main.data.set("Pos2.y", (Object) p.getLocation().getY());
						Main.data.set("Pos2.z", (Object) p.getLocation().getZ());
						Main.data.set("Pos2.yaw", (Object) p.getLocation().getYaw());
						Main.data.set("Pos2.pitch", (Object) p.getLocation().getPitch());
						try {
							Main.data.save(Main.d);
						} catch (IOException e) {
							e.printStackTrace();
						}
						p.sendMessage(Main.plugin.getConfig().getString("Pos2Setada").replaceAll("&", "§"));
					}
					if (args[1].equalsIgnoreCase("saida")) {
						Main.data.set("Saida.world", (Object) p.getWorld().getName());
						Main.data.set("Saida.x", (Object) p.getLocation().getX());
						Main.data.set("Saida.y", (Object) p.getLocation().getY());
						Main.data.set("Saida.z", (Object) p.getLocation().getZ());
						Main.data.set("Saida.yaw", (Object) p.getLocation().getYaw());
						Main.data.set("Saida.pitch", (Object) p.getLocation().getPitch());
						try {
							Main.data.save(Main.d);
						} catch (IOException e) {
							e.printStackTrace();
						}
						this.sendConfigMessage(p, "SaidaSetada");
					}
				}
			}
		}
		return false;
	}

	public void sendConfigMessage(final Player p, final String path) {
		p.sendMessage(Main.plugin.getConfig().getString(path).replaceAll("&", "§"));
	}

	public static void limparInv(final Player p) {
		p.getInventory().clear();
		p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR),
				new ItemStack(Material.AIR), new ItemStack(Material.AIR) });
	}

	public void reloadPlugin() {
		try {
			Main.data.save(Main.d);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.data = (FileConfiguration) YamlConfiguration.loadConfiguration(Main.d);
		try {
			Main.kit.save(Main.k);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.kit = (FileConfiguration) YamlConfiguration.loadConfiguration(Main.k);
		try {
			Main.stats.save(Main.s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.stats = (FileConfiguration) YamlConfiguration.loadConfiguration(Main.s);
		ItemManager.loadItems();
	}
}
