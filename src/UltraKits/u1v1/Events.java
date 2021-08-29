package UltraKits.u1v1;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import UltraKits.Main;
import me.confuser.barapi.BarAPI;

public class Events implements Listener {
	static ArrayList<String> move;
	static ArrayList<String> respawn;

	static {
		Events.move = new ArrayList<String>();
		Events.respawn = new ArrayList<String>();
	}

	@EventHandler
	public void antiMove(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if ((e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ())
				&& Events.move.contains(p.getName())) {
			p.teleport(e.getFrom());
		}
	}

	@EventHandler
	public void onQuit(final PlayerQuitEvent e) {
		final Player p = e.getPlayer();
		if (Commands.arm.containsKey(p.getName())) {
			p.getEquipment().setArmorContents((ItemStack[]) Commands.arm.get(p.getName()));
			Commands.arm.remove(p.getName());
		}
		if (Commands.inv.containsKey(p.getName())) {
			p.getInventory().setContents((ItemStack[]) Commands.inv.get(p.getName()));
			Commands.inv.remove(p.getName());
		}
		if (Desafiar.cooldown.containsKey(p.getName())) {
			final BukkitRunnable task = Desafiar.cooldown.get(p.getName());
			task.cancel();
			Desafiar.cooldown.remove(p.getName());
		}
		if (Desafiar.tasks.containsKey(p.getName())) {
			final BukkitRunnable task = Desafiar.tasks.get(p.getName());
			task.cancel();
			Desafiar.tasks.remove(p.getName());
		}
		if (Events.move.contains(p.getName())) {
			Events.move.remove(p.getName());
		}
		if (Commands.em.contains(p.getName())) {
			Commands.em.remove(p.getName());
			if (Main.data.getConfigurationSection("Saida") != null) {
				p.teleport(new Location(Bukkit.getWorld(Main.data.getString("Saida.world")),
						Main.data.getDouble("Saida.x"), Main.data.getDouble("Saida.y"), Main.data.getDouble("Saida.z"),
						(float) Main.data.getDouble("Saida.yaw"), (float) Main.data.getDouble("Saida.pitch")));
			} else {
				p.teleport(p.getWorld().getSpawnLocation());
			}
		}
		if (Desafiar.lutando.containsKey(p.getName())) {
			final Player req = Bukkit.getPlayer((String) Desafiar.lutando.get(p.getName()));
			if (req != null) {
				Desafiar.showPlayer(req);
				if (Desafiar.lutando.containsKey(req.getName())) {
					Desafiar.lutando.remove(req.getName());
				}
				if (Events.move.contains(req.getName())) {
					Events.move.remove(req.getName());
				}
				if (Desafiar.cooldown.containsKey(req.getName())) {
					final BukkitRunnable task2 = Desafiar.cooldown.get(req.getName());
					task2.cancel();
					Desafiar.cooldown.remove(req.getName());
				}
				if (Desafiar.tasks.containsKey(req.getName())) {
					final BukkitRunnable task2 = Desafiar.tasks.get(req.getName());
					task2.cancel();
					Desafiar.tasks.remove(req.getName());
				}
				for (final String n : Commands.em) {
					final Player i = Bukkit.getPlayer(n);
					if (i != null) {
						for (final String s : Main.plugin.getConfig().getStringList("Vitoria")) {
							if (req.getInventory().contains(Material.MUSHROOM_SOUP)) {
								i.sendMessage(s.replaceAll("&", "§").replaceAll("@perdedor", p.getName())
										.replaceAll("@vencedor", req.getName())
										.replaceAll("@sopas",
												new StringBuilder().append(this.getAmount(req, Material.MUSHROOM_SOUP))
														.toString())
										.replaceAll("@coracoes", new StringBuilder()
												.append((int) ((Damageable) req).getHealth() / 2).toString()));
							} else {
								i.sendMessage(s.replaceAll("&", "§").replaceAll("@perdedor", p.getName())
										.replaceAll("@vencedor", req.getName()).replaceAll("@sopas", "0")
										.replaceAll("@coracoes", new StringBuilder()
												.append(((Damageable) req).getHealth() / 2.0).toString()));
							}
						}
					}
				}
				if (Main.data.getConfigurationSection("Spawn") != null) {
					Commands.limparInv(req);
					req.getInventory().addItem(new ItemStack[] { Desafiar.item });
					req.teleport(new Location(Bukkit.getWorld(Main.data.getString("Spawn.world")),
							Main.data.getDouble("Spawn.x"), Main.data.getDouble("Spawn.y"),
							Main.data.getDouble("Spawn.z"), (float) Main.data.getLong("Spawn.yaw"),
							(float) Main.data.getLong("Spawn.pitch")));
					req.setHealth(20.0);
					BarAPI.removeBar(req);
				} else {
					req.teleport(req.getWorld().getSpawnLocation());
				}
			}
			Desafiar.lutando.remove(p.getName());
		}
	}

	@EventHandler
	public void acabar(final PlayerDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			final Player p = e.getEntity();
			final Player killer = e.getEntity().getKiller();
			if (Desafiar.tasks.containsKey(p.getName())) {
				final BukkitRunnable run = Desafiar.tasks.get(p.getName());
				run.cancel();
				Desafiar.tasks.remove(p.getName());
			}
			if (Desafiar.tasks.containsKey(killer.getName())) {
				final BukkitRunnable run = Desafiar.tasks.get(killer.getName());
				run.cancel();
				Desafiar.tasks.remove(killer.getName());
			}
			if (Desafiar.lutando.containsKey(p.getName())) {
				e.getDrops().clear();
				final String name = Desafiar.lutando.get(p.getName());
				if (name == killer.getName()) {
					Desafiar.lutando.remove(p.getName());
					if (Desafiar.lutando.containsKey(killer.getName())) {
						Desafiar.lutando.remove(killer.getName());
					}
					Desafiar.showPlayer(p);
					Desafiar.showPlayer(killer);
					for (final String n : Commands.em) {
						final Player i = Bukkit.getPlayer(n);
						if (i != null) {
							for (final String s : Main.plugin.getConfig().getStringList("Vitoria")) {
								if (killer.getInventory().contains(Material.MUSHROOM_SOUP)) {
									i.sendMessage(s.replaceAll("&", "§").replaceAll("@perdedor", p.getName())
											.replaceAll("@vencedor", killer.getName())
											.replaceAll("@sopas", new StringBuilder()
													.append(this.getAmount(killer, Material.MUSHROOM_SOUP)).toString())
											.replaceAll("@coracoes", new StringBuilder()
													.append((int) ((Damageable) killer).getHealth() / 2).toString()));
								} else {
									i.sendMessage(s.replaceAll("&", "§").replaceAll("@perdedor", p.getName())
											.replaceAll("@vencedor", killer.getName()).replaceAll("@sopas", "0")
											.replaceAll("@coracoes", new StringBuilder()
													.append((int) ((Damageable) killer).getHealth() / 2).toString()));
								}
							}
						}
					}
					if (Main.data.getConfigurationSection("Spawn") != null) {
						Commands.limparInv(killer);
						killer.getInventory().addItem(new ItemStack[] { Desafiar.item });
						killer.teleport(new Location(Bukkit.getWorld(Main.data.getString("Spawn.world")),
								Main.data.getDouble("Spawn.x"), Main.data.getDouble("Spawn.y"),
								Main.data.getDouble("Spawn.z"), (float) Main.data.getLong("Spawn.yaw"),
								(float) Main.data.getLong("Spawn.pitch")));
						killer.setHealth(20.0);
						BarAPI.removeBar(killer);
					} else {
						killer.teleport(killer.getWorld().getSpawnLocation());
					}
				}
			}
		}
	}

	@EventHandler
	public void aoTentarSair(final PlayerCommandPreprocessEvent e) {
		if (Commands.em.contains(e.getPlayer().getName()) && !e.getMessage().equalsIgnoreCase("/1v1")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "Apenas o comando /1v1 pode ser usado!");
		}
	}

	@EventHandler
	public void onRespawn(final PlayerRespawnEvent e) {
		final Player p = e.getPlayer();
		if (Commands.em.contains(p.getName())) {
			if (Main.data.getConfigurationSection("Spawn") != null) {
				Commands.limparInv(p);
				p.getInventory().addItem(new ItemStack[] { Desafiar.item });
				new BukkitRunnable() {
					public void run() {
						p.teleport(new Location(Bukkit.getWorld(Main.data.getString("Spawn.world")),
								Main.data.getDouble("Spawn.x"), Main.data.getDouble("Spawn.y"),
								Main.data.getDouble("Spawn.z"), (float) Main.data.getLong("Spawn.yaw"),
								(float) Main.data.getLong("Spawn.pitch")));
						p.setHealth(20.0);
						BarAPI.removeBar(p);
					}
				}.runTaskLater(Main.plugin, 10L);
			} else {
				new BukkitRunnable() {
					public void run() {
						p.teleport(p.getWorld().getSpawnLocation());
					}
				}.runTaskLater(Main.plugin, 10L);
			}
		}
	}

	@EventHandler
	public void noDropItem(final PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().equals((Object) Desafiar.item)) {
			e.setCancelled(true);
		}
	}

	public int getAmount(final Player player, final Material id) {
		int amout = 0;
		ItemStack[] contents;
		for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
			final ItemStack item = contents[i];
			if (item != null && item.getType() == id && item.getAmount() > 0) {
				amout += item.getAmount();
			}
		}
		return amout;
	}

	@EventHandler
	public void onDropBowl(final PlayerDropItemEvent e) {
		if (Commands.em.contains(e.getPlayer().getName()) && (e.getItemDrop().getItemStack().getType() == Material.BOWL
				|| e.getItemDrop().getItemStack().getType() == Material.MUSHROOM_SOUP)) {
			e.getItemDrop().remove();
		}
	}
}
