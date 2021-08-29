package UltraKits.u1v1;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import UltraKits.Main;

public class Desafiar implements Listener {
	static Plugin pl;
	static FileConfiguration kits;
	static FileConfiguration data;
	static HashMap<String, String> desafiando;
	static HashMap<String, String> kit;
	static HashMap<String, String> customizado;
	static HashMap<String, String> lutando;
	static HashMap<String, String> requests;
	static HashMap<String, BukkitRunnable> cooldown;
	static HashMap<String, BukkitRunnable> tasks;
	static ItemStack item;
	static ItemStack ic;
	static Inventory gui;

	static {
		Desafiar.pl = Main.plugin;
		Desafiar.kits = Main.kit;
		Desafiar.data = Main.data;
		Desafiar.desafiando = new HashMap<String, String>();
		Desafiar.kit = new HashMap<String, String>();
		Desafiar.customizado = new HashMap<String, String>();
		Desafiar.lutando = new HashMap<String, String>();
		Desafiar.requests = new HashMap<String, String>();
		Desafiar.cooldown = new HashMap<String, BukkitRunnable>();
		Desafiar.tasks = new HashMap<String, BukkitRunnable>();
		Desafiar.gui = Bukkit.createInventory((InventoryHolder) null, Main.plugin.getConfig().getInt("1v1Slots"),
				Main.plugin.getConfig().getString("1v1Title").replaceAll("&", "§"));
	}

	@EventHandler
	public void desafiarPlayer(final PlayerInteractEntityEvent e) {
		final Player p = e.getPlayer();
		if (p.getItemInHand().equals((Object) Desafiar.item) && e.getRightClicked() instanceof Player) {
			final Player req = (Player) e.getRightClicked();
			if (Desafiar.requests.containsKey(req.getName())) {
				if (Desafiar.requests.get(req.getName()) == p.getName()) {
					Desafiar.requests.remove(req.getName());
					p.setFoodLevel(20);
					p.setHealth(20.0);
					req.setFoodLevel(20);
					req.setHealth(20.0);
					Commands.limparInv(p);
					Commands.limparInv(req);
					hidePlayer(p, req);
					hidePlayer(req, p);
					if (Main.data.getConfigurationSection("Pos1") != null
							&& Main.data.getConfigurationSection("Pos2") != null) {
						final World w = Bukkit.getWorld(Main.data.getString("Pos1.world"));
						final double x = Main.data.getDouble("Pos1.x");
						final double y = Main.data.getDouble("Pos1.y");
						final double z = Main.data.getDouble("Pos1.z");
						p.teleport(new Location(w, x, y, z, (float) Main.data.getLong("Pos1.yaw"),
								(float) Main.data.getLong("Pos1.pitch")));
						final World w2 = Bukkit.getWorld(Main.data.getString("Pos2.world"));
						final double x2 = Main.data.getDouble("Pos2.x");
						final double y2 = Main.data.getDouble("Pos2.y");
						final double z2 = Main.data.getDouble("Pos2.z");
						req.teleport(new Location(w2, x2, y2, z2, (float) Main.data.getLong("Pos2.yaw"),
								(float) Main.data.getLong("Pos2.pitch")));
						Desafiar.lutando.put(p.getName(), req.getName());
						Desafiar.lutando.put(req.getName(), p.getName());
						Desafiar.requests.remove(req.getName());
						if (!Desafiar.customizado.containsKey(req.getName())) {
							if (Desafiar.kit.containsKey(req.getName())) {
								final String id = Desafiar.kit.get(req.getName());
								p.getInventory().clear();
								req.getInventory().clear();
								if (Main.kit.getConfigurationSection(id) != null) {
									p.getEquipment().setHelmet(new ItemStack(Material.getMaterial(
											Main.kit.getString(String.valueOf(id) + ".helmet.type").toUpperCase())));
									req.getEquipment().setHelmet(new ItemStack(Material.getMaterial(
											Main.kit.getString(String.valueOf(id) + ".helmet.type").toUpperCase())));
									if (Main.kit.getStringList(String.valueOf(id) + ".helmet.enchants") != null) {
										for (final String en : Main.kit
												.getStringList(String.valueOf(id) + ".helmet.enchants")) {
											final String[] enchant = en.split(" ");
											p.getEquipment().getHelmet().addUnsafeEnchantment(
													Enchantment.getByName(enchant[0].toUpperCase()),
													(int) Integer.valueOf(enchant[1]));
											req.getEquipment().getHelmet().addUnsafeEnchantment(
													Enchantment.getByName(enchant[0].toUpperCase()),
													(int) Integer.valueOf(enchant[1]));
										}
									}
									p.getEquipment().setChestplate(new ItemStack(Material.getMaterial(Main.kit
											.getString(String.valueOf(id) + ".chestplate.type").toUpperCase())));
									req.getEquipment().setChestplate(new ItemStack(Material.getMaterial(Main.kit
											.getString(String.valueOf(id) + ".chestplate.type").toUpperCase())));
									if (Main.kit.getStringList(String.valueOf(id) + ".chestplate.enchants") != null) {
										for (final String en : Main.kit
												.getStringList(String.valueOf(id) + ".chestplate.enchants")) {
											final String[] enchant = en.split(" ");
											p.getEquipment().getChestplate().addUnsafeEnchantment(
													Enchantment.getByName(enchant[0].toUpperCase()),
													(int) Integer.valueOf(enchant[1]));
											req.getEquipment().getChestplate().addUnsafeEnchantment(
													Enchantment.getByName(enchant[0].toUpperCase()),
													(int) Integer.valueOf(enchant[1]));
										}
									}
									p.getEquipment().setLeggings(new ItemStack(Material.getMaterial(
											Main.kit.getString(String.valueOf(id) + ".leggings.type").toUpperCase())));
									req.getEquipment().setLeggings(new ItemStack(Material.getMaterial(
											Main.kit.getString(String.valueOf(id) + ".leggings.type").toUpperCase())));
									if (Main.kit.getStringList(String.valueOf(id) + ".leggings.enchants") != null) {
										for (final String en : Main.kit
												.getStringList(String.valueOf(id) + ".leggings.enchants")) {
											final String[] enchant = en.split(" ");
											p.getEquipment().getLeggings().addUnsafeEnchantment(
													Enchantment.getByName(enchant[0].toUpperCase()),
													(int) Integer.valueOf(enchant[1]));
											req.getEquipment().getLeggings().addUnsafeEnchantment(
													Enchantment.getByName(enchant[0].toUpperCase()),
													(int) Integer.valueOf(enchant[1]));
										}
									}
									p.getEquipment().setBoots(new ItemStack(Material.getMaterial(
											Main.kit.getString(String.valueOf(id) + ".boots.type").toUpperCase())));
									req.getEquipment().setBoots(new ItemStack(Material.getMaterial(
											Main.kit.getString(String.valueOf(id) + ".boots.type").toUpperCase())));
									if (Main.kit.getStringList(String.valueOf(id) + ".boots.enchants") != null) {
										for (final String en : Main.kit
												.getStringList(String.valueOf(id) + ".boots.enchants")) {
											final String[] enchant = en.split(" ");
											p.getEquipment().getBoots().addUnsafeEnchantment(
													Enchantment.getByName(enchant[0].toUpperCase()),
													(int) Integer.valueOf(enchant[1]));
											req.getEquipment().getBoots().addUnsafeEnchantment(
													Enchantment.getByName(enchant[0].toUpperCase()),
													(int) Integer.valueOf(enchant[1]));
										}
									}
									p.getInventory().setItemInHand(new ItemStack(Material.getMaterial(
											Main.kit.getString(String.valueOf(id) + ".sword.type").toUpperCase())));
									req.getInventory().setItemInHand(new ItemStack(Material.getMaterial(
											Main.kit.getString(String.valueOf(id) + ".sword.type").toUpperCase())));
									if (Main.kit.getStringList(String.valueOf(id) + ".sword.enchants") != null) {
										for (final String en : Main.kit
												.getStringList(String.valueOf(id) + ".sword.enchants")) {
											final String[] enchant = en.split(" ");
											p.getInventory().getItemInHand().addUnsafeEnchantment(
													Enchantment.getByName(enchant[0].toUpperCase()),
													(int) Integer.valueOf(enchant[1]));
											req.getInventory().getItemInHand().addUnsafeEnchantment(
													Enchantment.getByName(enchant[0].toUpperCase()),
													(int) Integer.valueOf(enchant[1]));
										}
									}
									if (Main.kit.getStringList(String.valueOf(id) + ".potions") != null) {
										for (final String pt : Main.kit
												.getStringList(String.valueOf(id) + ".potions")) {
											final String[] potion = pt.split(" ");
											p.addPotionEffect(new PotionEffect(
													PotionEffectType.getByName(potion[0].toUpperCase()),
													Integer.valueOf(potion[2]) * 20, Integer.valueOf(potion[1]) - 1));
											req.addPotionEffect(new PotionEffect(
													PotionEffectType.getByName(potion[0].toUpperCase()),
													Integer.valueOf(potion[2]) * 20, Integer.valueOf(potion[1]) - 1));
										}
									}
									for (int sopas = Main.kit.getInt(String.valueOf(id) + ".sopas"),
											i = 0; i < sopas; ++i) {
										p.getInventory()
												.addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
										req.getInventory()
												.addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
									}
									p.updateInventory();
									req.updateInventory();
								}
							}
						} else {
							final String cs = Desafiar.customizado.get(req.getName());
							final String[] c = cs.split(";");
							if (c[0].equalsIgnoreCase("Diamante")) {
								p.getEquipment()
										.setArmorContents(new ItemStack[] { new ItemStack(Material.DIAMOND_BOOTS),
												new ItemStack(Material.DIAMOND_LEGGINGS),
												new ItemStack(Material.DIAMOND_CHESTPLATE),
												new ItemStack(Material.DIAMOND_HELMET) });
								req.getEquipment()
										.setArmorContents(new ItemStack[] { new ItemStack(Material.DIAMOND_BOOTS),
												new ItemStack(Material.DIAMOND_LEGGINGS),
												new ItemStack(Material.DIAMOND_CHESTPLATE),
												new ItemStack(Material.DIAMOND_HELMET) });
							} else if (c[0].equalsIgnoreCase("Ferro")) {
								p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.IRON_BOOTS),
										new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.IRON_CHESTPLATE),
										new ItemStack(Material.IRON_HELMET) });
								req.getEquipment().setArmorContents(new ItemStack[] {
										new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.IRON_LEGGINGS),
										new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_HELMET) });
							} else if (c[0].equalsIgnoreCase("Ouro")) {
								p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.GOLD_BOOTS),
										new ItemStack(Material.GOLD_LEGGINGS), new ItemStack(Material.GOLD_CHESTPLATE),
										new ItemStack(Material.GOLD_HELMET) });
								req.getEquipment().setArmorContents(new ItemStack[] {
										new ItemStack(Material.GOLD_BOOTS), new ItemStack(Material.GOLD_LEGGINGS),
										new ItemStack(Material.GOLD_CHESTPLATE), new ItemStack(Material.GOLD_HELMET) });
							} else if (c[0].equalsIgnoreCase("Chain")) {
								p.getEquipment()
										.setArmorContents(new ItemStack[] { new ItemStack(Material.CHAINMAIL_BOOTS),
												new ItemStack(Material.CHAINMAIL_LEGGINGS),
												new ItemStack(Material.CHAINMAIL_CHESTPLATE),
												new ItemStack(Material.CHAINMAIL_HELMET) });
								req.getEquipment()
										.setArmorContents(new ItemStack[] { new ItemStack(Material.CHAINMAIL_BOOTS),
												new ItemStack(Material.CHAINMAIL_LEGGINGS),
												new ItemStack(Material.CHAINMAIL_CHESTPLATE),
												new ItemStack(Material.CHAINMAIL_HELMET) });
							} else if (c[0].equalsIgnoreCase("Couro")) {
								p.getEquipment()
										.setArmorContents(new ItemStack[] { new ItemStack(Material.LEATHER_BOOTS),
												new ItemStack(Material.LEATHER_LEGGINGS),
												new ItemStack(Material.LEATHER_CHESTPLATE),
												new ItemStack(Material.LEATHER_HELMET) });
								req.getEquipment()
										.setArmorContents(new ItemStack[] { new ItemStack(Material.LEATHER_BOOTS),
												new ItemStack(Material.LEATHER_LEGGINGS),
												new ItemStack(Material.LEATHER_CHESTPLATE),
												new ItemStack(Material.LEATHER_HELMET) });
							}
							if (c[1].equalsIgnoreCase("Diamante")) {
								p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD) });
								req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD) });
							} else if (c[1].equalsIgnoreCase("Ferro")) {
								p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_SWORD) });
								req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_SWORD) });
							} else if (c[1].equalsIgnoreCase("Ouro")) {
								p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_SWORD) });
								req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_SWORD) });
							} else if (c[1].equalsIgnoreCase("Pedra")) {
								p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
								req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
							} else if (c[1].equalsIgnoreCase("Madeira")) {
								p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.WOOD_SWORD) });
								req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.WOOD_SWORD) });
							}
							if (c[2].equalsIgnoreCase("true")) {
								for (int i = 0; i < 35; ++i) {
									p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
									req.getInventory()
											.addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
								}
							}
						}
						Events.move.add(p.getName());
						Events.move.add(req.getName());
						new BukkitRunnable() {
							int tempo = Main.plugin.getConfig().getInt("Cooldown1v1") + 1;

							public void run() {
								if (this.tempo > 1) {
									--this.tempo;
									Desafiar.cooldown.put(p.getName(), this);
									Desafiar.cooldown.put(req.getName(), this);
									p.sendMessage(Main.plugin.getConfig().getString("Iniciando").replaceAll("&", "§")
											.replaceAll("@tempo", new StringBuilder().append(this.tempo).toString()));
									req.sendMessage(Main.plugin.getConfig().getString("Iniciando").replaceAll("&", "§")
											.replaceAll("@tempo", new StringBuilder().append(this.tempo).toString()));
									p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
									req.playSound(req.getLocation(), Sound.CLICK, 1.0f, 1.0f);
								} else {
									if (Events.move.contains(p.getName())) {
										Events.move.remove(p.getName());
									}
									if (Events.move.contains(req.getName())) {
										Events.move.remove(req.getName());
									}
									p.sendMessage(Main.plugin.getConfig().getString("Valendo").replaceAll("&", "§"));
									p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0f, 1.0f);
									req.sendMessage(Main.plugin.getConfig().getString("Valendo").replaceAll("&", "§"));
									req.playSound(req.getLocation(), Sound.NOTE_PIANO, 1.0f, 1.0f);
									this.cancel();
								}
							}
						}.runTaskTimer(Desafiar.pl, 0L, 20L);
					}
				}
			} else {
				for (final Map.Entry<ItemStack, Integer> entry : ItemManager.items.entrySet()) {
					Desafiar.gui.setItem((int) entry.getValue(), (ItemStack) entry.getKey());
				}
				Desafiar.desafiando.put(p.getName(), req.getName());
				p.openInventory(Desafiar.gui);
			}
		}
	}

	@EventHandler
	public void selecionarKit(final InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getInventory().getName().equals(Desafiar.gui.getName())) {
			if (ItemManager.id.containsKey(e.getCurrentItem())) {
				if (Desafiar.desafiando.containsKey(p.getName())) {
					final String id = ItemManager.id.get(e.getCurrentItem());
					final Player req = Bukkit.getPlayer((String) Desafiar.desafiando.get(p.getName()));
					if (req != null) {
						Desafiar.requests.put(p.getName(), req.getName());
						Desafiar.kit.put(p.getName(), id);
						for (final String msg : Main.plugin.getConfig().getStringList("Desafiou")) {
							if (Main.kit.getString(String.valueOf(id) + ".aliase") != null) {
								p.sendMessage(msg.replaceAll("&", "§")
										.replaceAll("@kit", Main.kit.getString(String.valueOf(id) + ".aliase"))
										.replaceAll("@desafiado", req.getName()));
							} else {
								p.sendMessage(msg.replaceAll("&", "§").replaceAll("@kit", id).replaceAll("@desafiado",
										req.getName()));
							}
						}
						for (final String cv : Main.plugin.getConfig().getStringList("Convite")) {
							if (Main.kit.getString(String.valueOf(id) + ".aliase") != null) {
								req.sendMessage(cv.replaceAll("&", "§")
										.replaceAll("@kit", Main.kit.getString(String.valueOf(id) + ".aliase"))
										.replaceAll("@desafiante", p.getName()));
							} else {
								req.sendMessage(cv.replaceAll("&", "§").replaceAll("@kit", id).replaceAll("@desafiante",
										p.getName()));
							}
						}
					} else {
						p.sendMessage(Main.plugin.getConfig().getString("NaoOnline").replaceAll("&", "§"));
					}
				}
				e.setCancelled(true);
				p.closeInventory();
			} else if (!Custom.customItem.isEmpty()) {
				for (final ItemStack i : Custom.customItem) {
					if (e.getCurrentItem().equals((Object) i)) {
						e.setCancelled(true);
						p.closeInventory();
						Custom.inv.setItem(0, Custom.cancel);
						Custom.inv.setItem(9, Custom.cancel);
						Custom.inv.setItem(18, Custom.cancel);
						Custom.inv.setItem(8, Custom.done);
						Custom.inv.setItem(17, Custom.done);
						Custom.inv.setItem(26, Custom.done);
						Desafiar.ic = new ItemStack(
								Material.getMaterial(Main.plugin.getConfig().getInt("Custom.ItemRedor.id")));
						final ItemMeta metaIC = Desafiar.ic.getItemMeta();
						metaIC.setDisplayName(
								Main.plugin.getConfig().getString("Custom.ItemRedor.title").replaceAll("&", "§"));
						Desafiar.ic.setItemMeta(metaIC);
						Custom.inv.setItem(1, Desafiar.ic);
						Custom.inv.setItem(2, Desafiar.ic);
						Custom.inv.setItem(3, Desafiar.ic);
						Custom.inv.setItem(4, Desafiar.ic);
						Custom.inv.setItem(5, Desafiar.ic);
						Custom.inv.setItem(6, Desafiar.ic);
						Custom.inv.setItem(7, Desafiar.ic);
						Custom.inv.setItem(10, Desafiar.ic);
						Custom.inv.setItem(12, Desafiar.ic);
						Custom.inv.setItem(14, Desafiar.ic);
						Custom.inv.setItem(16, Desafiar.ic);
						Custom.inv.setItem(19, Desafiar.ic);
						Custom.inv.setItem(20, Desafiar.ic);
						Custom.inv.setItem(21, Desafiar.ic);
						Custom.inv.setItem(22, Desafiar.ic);
						Custom.inv.setItem(23, Desafiar.ic);
						Custom.inv.setItem(24, Desafiar.ic);
						Custom.inv.setItem(25, Desafiar.ic);
						Custom.inv.setItem(13, Custom.armFerro);
						Custom.inv.setItem(11, Custom.swdDima);
						Custom.inv.setItem(15, Custom.resoup);
						Custom.espada.put(p.getName(), "diamond");
						Custom.armadura.put(p.getName(), "iron");
						Custom.sopas.put(p.getName(), true);
						p.openInventory(Custom.inv);
					}
				}
			}
		}
	}

	public static void hidePlayer(final Player p, final Player p2) {
		new BukkitRunnable() {
			public void run() {
				if (!Desafiar.tasks.containsKey(p.getName())) {
					Desafiar.tasks.put(p.getName(), this);
				}
				Player[] onlinePlayers;
				for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
					final Player n = onlinePlayers[i];
					if (n != p2) {
						n.hidePlayer(p);
						p.hidePlayer(n);
					}
				}
			}
		}.runTaskTimer(Desafiar.pl, 0L, 20L);
	}

	public static void showPlayer(final Player p) {
		Player[] onlinePlayers;
		for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
			final Player s = onlinePlayers[i];
			s.showPlayer(p);
			p.showPlayer(s);
		}
	}
}
