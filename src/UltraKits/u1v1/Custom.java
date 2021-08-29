package UltraKits.u1v1;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import UltraKits.Main;

public class Custom implements Listener {
	static Plugin pl;
	public static Inventory inv;
	static ItemStack cancel;
	static ItemStack done;
	static ItemStack armDima;
	static ItemStack armFerro;
	static ItemStack armOuro;
	static ItemStack armChain;
	static ItemStack armCouro;
	static ItemStack swdDima;
	static ItemStack swdFerro;
	static ItemStack swdOuro;
	static ItemStack swdPedra;
	static ItemStack swdMadeira;
	static ItemStack resoup;
	static ItemStack nosoup;
	static ArrayList<ItemStack> customItem;
	static HashMap<String, String> armadura;
	static HashMap<String, String> espada;
	static HashMap<String, Boolean> sopas;

	static {
		Custom.pl = Main.plugin;
		Custom.inv = Bukkit.createInventory((InventoryHolder) null, 27,
				Main.plugin.getConfig().getString("Custom.invTitle").replaceAll("&", "§"));
		Custom.cancel = new ItemStack(Material.WOOL);
		Custom.done = new ItemStack(Material.WOOL);
		Custom.armDima = new ItemStack(Material.DIAMOND_CHESTPLATE);
		Custom.armFerro = new ItemStack(Material.IRON_CHESTPLATE);
		Custom.armOuro = new ItemStack(Material.GOLD_CHESTPLATE);
		Custom.armChain = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		Custom.armCouro = new ItemStack(Material.LEATHER_CHESTPLATE);
		Custom.swdDima = new ItemStack(Material.DIAMOND_SWORD);
		Custom.swdFerro = new ItemStack(Material.IRON_SWORD);
		Custom.swdOuro = new ItemStack(Material.GOLD_SWORD);
		Custom.swdPedra = new ItemStack(Material.STONE_SWORD);
		Custom.swdMadeira = new ItemStack(Material.WOOD_SWORD);
		Custom.resoup = new ItemStack(Material.MUSHROOM_SOUP);
		Custom.nosoup = new ItemStack(Material.BOWL);
		Custom.customItem = new ArrayList<ItemStack>();
		Custom.armadura = new HashMap<String, String>();
		Custom.espada = new HashMap<String, String>();
		Custom.sopas = new HashMap<String, Boolean>();
	}

	public static void loadCustomItems() {
		Custom.cancel.setDurability((short) 14);
		Custom.done.setDurability((short) 5);
		final ItemMeta im1 = Custom.cancel.getItemMeta();
		im1.setDisplayName(ChatColor.RED + "Cancelar");
		Custom.cancel.setItemMeta(im1);
		final ItemMeta im2 = Custom.done.getItemMeta();
		im2.setDisplayName(ChatColor.GREEN + "Pronto");
		Custom.done.setItemMeta(im2);
		final ItemMeta meta1 = Custom.armDima.getItemMeta();
		meta1.setDisplayName(ChatColor.GOLD + "Armaduras");
		final ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.GREEN + "\u27a8 Diamante");
		lore1.add(ChatColor.RED + "- Ferro");
		lore1.add(ChatColor.RED + "- Ouro");
		lore1.add(ChatColor.RED + "- Chain");
		lore1.add(ChatColor.RED + "- Couro");
		meta1.setLore(lore1);
		Custom.armDima.setItemMeta(meta1);
		final ItemMeta meta2 = Custom.armFerro.getItemMeta();
		meta2.setDisplayName(ChatColor.GOLD + "Armadura");
		final ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add(ChatColor.RED + "- Diamante");
		lore2.add(ChatColor.GREEN + "\u27a8 Ferro");
		lore2.add(ChatColor.RED + "- Ouro");
		lore2.add(ChatColor.RED + "- Chain");
		lore2.add(ChatColor.RED + "- Couro");
		meta2.setLore(lore2);
		Custom.armFerro.setItemMeta(meta2);
		final ItemMeta meta3 = Custom.armOuro.getItemMeta();
		meta3.setDisplayName(ChatColor.GOLD + "Armadura");
		final ArrayList<String> lore3 = new ArrayList<String>();
		lore3.add(ChatColor.RED + "- Diamante");
		lore3.add(ChatColor.RED + "- Ferro");
		lore3.add(ChatColor.GREEN + "\u27a8 Ouro");
		lore3.add(ChatColor.RED + "- Chain");
		lore3.add(ChatColor.RED + "- Couro");
		meta3.setLore(lore3);
		Custom.armOuro.setItemMeta(meta3);
		final ItemMeta meta4 = Custom.armChain.getItemMeta();
		meta4.setDisplayName(ChatColor.GOLD + "Armadura");
		final ArrayList<String> lore4 = new ArrayList<String>();
		lore4.add(ChatColor.RED + "- Diamante");
		lore4.add(ChatColor.RED + "- Ferro");
		lore4.add(ChatColor.RED + "- Ouro");
		lore4.add(ChatColor.GREEN + "\u27a8 Chain");
		lore4.add(ChatColor.RED + "- Couro");
		meta4.setLore(lore4);
		Custom.armChain.setItemMeta(meta4);
		final ItemMeta meta5 = Custom.armCouro.getItemMeta();
		meta5.setDisplayName(ChatColor.GOLD + "Armadura");
		final ArrayList<String> lore5 = new ArrayList<String>();
		lore5.add(ChatColor.RED + "- Diamante");
		lore5.add(ChatColor.RED + "- Ferro");
		lore5.add(ChatColor.RED + "- Ouro");
		lore5.add(ChatColor.RED + "- Chain");
		lore5.add(ChatColor.GREEN + "\u27a8 Couro");
		meta5.setLore(lore5);
		Custom.armCouro.setItemMeta(meta5);
		final ItemMeta meta6 = Custom.swdDima.getItemMeta();
		meta6.setDisplayName(ChatColor.GOLD + "Espada");
		final ArrayList<String> lore6 = new ArrayList<String>();
		lore6.add(ChatColor.GREEN + "\u27a8 Diamante");
		lore6.add(ChatColor.RED + "- Ferro");
		lore6.add(ChatColor.RED + "- Ouro");
		lore6.add(ChatColor.RED + "- Pedra");
		lore6.add(ChatColor.RED + "- Madeira");
		meta6.setLore(lore6);
		Custom.swdDima.setItemMeta(meta6);
		final ItemMeta meta7 = Custom.swdFerro.getItemMeta();
		meta7.setDisplayName(ChatColor.GOLD + "Espada");
		final ArrayList<String> lore7 = new ArrayList<String>();
		lore7.add(ChatColor.RED + "- Diamante");
		lore7.add(ChatColor.GREEN + "\u27a8 Ferro");
		lore7.add(ChatColor.RED + "- Ouro");
		lore7.add(ChatColor.RED + "- Pedra");
		lore7.add(ChatColor.RED + "- Madeira");
		meta7.setLore(lore7);
		Custom.swdFerro.setItemMeta(meta7);
		final ItemMeta meta8 = Custom.swdOuro.getItemMeta();
		meta7.setDisplayName(ChatColor.GOLD + "Espada");
		final ArrayList<String> lore8 = new ArrayList<String>();
		lore8.add(ChatColor.RED + "- Diamante");
		lore8.add(ChatColor.RED + "- Ferro");
		lore8.add(ChatColor.GREEN + "\u27a8 Ouro");
		lore8.add(ChatColor.RED + "- Pedra");
		lore8.add(ChatColor.RED + "- Madeira");
		meta8.setLore(lore8);
		Custom.swdOuro.setItemMeta(meta8);
		final ItemMeta meta9 = Custom.swdPedra.getItemMeta();
		meta9.setDisplayName(ChatColor.GOLD + "Espada");
		final ArrayList<String> lore9 = new ArrayList<String>();
		lore9.add(ChatColor.RED + "- Diamante");
		lore9.add(ChatColor.RED + "- Ferro");
		lore9.add(ChatColor.RED + "- Ouro");
		lore9.add(ChatColor.GREEN + "\u27a8 Pedra");
		lore9.add(ChatColor.RED + "- Madeira");
		meta9.setLore(lore9);
		Custom.swdPedra.setItemMeta(meta9);
		final ItemMeta meta10 = Custom.swdMadeira.getItemMeta();
		meta10.setDisplayName(ChatColor.GOLD + "Espada");
		final ArrayList<String> lore10 = new ArrayList<String>();
		lore10.add(ChatColor.RED + "- Diamante");
		lore10.add(ChatColor.RED + "- Ferro");
		lore10.add(ChatColor.RED + "- Ouro");
		lore10.add(ChatColor.RED + "- Pedra");
		lore10.add(ChatColor.GREEN + "\u27a8 Madeira");
		meta10.setLore(lore10);
		Custom.swdMadeira.setItemMeta(meta10);
		final ItemMeta im3 = Custom.resoup.getItemMeta();
		im3.setDisplayName(ChatColor.GOLD + "Sopas");
		Custom.resoup.setItemMeta(im3);
		final ItemMeta im4 = Custom.nosoup.getItemMeta();
		im4.setDisplayName(ChatColor.GOLD + "Sem Sopas");
		Custom.nosoup.setItemMeta(im4);
	}

	@EventHandler
	public void criarCustom(final InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getInventory().getName().equals(Custom.inv.getName())) {
			if (e.getCurrentItem().equals((Object) Desafiar.ic)) {
				e.setCancelled(true);
				return;
			}
			if (e.getCurrentItem().equals((Object) Custom.cancel)) {
				e.setCancelled(true);
				p.closeInventory();
			}
			if (e.getCurrentItem().equals((Object) Custom.done)) {
				if (Desafiar.desafiando.containsKey(p.getName())) {
					if (Custom.inv.contains(Custom.swdDima)) {
						Custom.espada.put(p.getName(), "Diamante");
					} else if (Custom.inv.contains(Custom.swdFerro)) {
						Custom.espada.put(p.getName(), "Ferro");
					} else if (Custom.inv.contains(Custom.swdOuro)) {
						Custom.espada.put(p.getName(), "Ouro");
					} else if (Custom.inv.contains(Custom.swdPedra)) {
						Custom.espada.put(p.getName(), "Pedra");
					} else if (Custom.inv.contains(Custom.swdMadeira)) {
						Custom.espada.put(p.getName(), "Madeira");
					}
					if (Custom.inv.contains(Custom.armDima)) {
						Custom.armadura.put(p.getName(), "Diamante");
					} else if (Custom.inv.contains(Custom.armFerro)) {
						Custom.armadura.put(p.getName(), "Ferro");
					} else if (Custom.inv.contains(Custom.armOuro)) {
						Custom.armadura.put(p.getName(), "Ouro");
					} else if (Custom.inv.contains(Custom.armChain)) {
						Custom.armadura.put(p.getName(), "Chain");
					} else if (Custom.inv.contains(Custom.armCouro)) {
						Custom.armadura.put(p.getName(), "Couro");
					}
					if (Custom.inv.contains(Custom.resoup)) {
						Custom.sopas.put(p.getName(), true);
					} else if (Custom.inv.contains(Custom.nosoup)) {
						Custom.sopas.put(p.getName(), false);
					}
					Desafiar.customizado.put(p.getName(), String.valueOf(Custom.armadura.get(p.getName())) + ";"
							+ Custom.espada.get(p.getName()) + ";" + Custom.sopas.get(p.getName()));
					final String pname = Desafiar.desafiando.get(p.getName());
					final Player req = Bukkit.getPlayer(pname);
					if (req != null) {
						Desafiar.requests.put(p.getName(), req.getName());
						for (final String b : Custom.pl.getConfig().getStringList("CustomDesafiou")) {
							if (Custom.sopas.get(p.getName())) {
								p.sendMessage(b.replaceAll("@armadura", Custom.armadura.get(p.getName()))
										.replaceAll("@espada", Custom.espada.get(p.getName())).replaceAll("&", "§")
										.replaceAll("@desafiado", req.getName()).replaceAll("@sopa", "Sim"));
							} else {
								p.sendMessage(b.replaceAll("@armadura", Custom.armadura.get(p.getName()))
										.replaceAll("@espada", Custom.espada.get(p.getName())).replaceAll("&", "§")
										.replaceAll("@desafiado", req.getName()).replaceAll("@sopa", "Nao"));
							}
						}
						for (final String s : Custom.pl.getConfig().getStringList("CustomConvite")) {
							if (Custom.sopas.get(p.getName())) {
								req.sendMessage(s.replaceAll("@armadura", Custom.armadura.get(p.getName()))
										.replaceAll("@espada", Custom.espada.get(p.getName())).replaceAll("&", "§")
										.replaceAll("@desafiante", p.getName()).replaceAll("@sopa", "Sim"));
							} else {
								req.sendMessage(s.replaceAll("@armadura", Custom.armadura.get(p.getName()))
										.replaceAll("@espada", Custom.espada.get(p.getName())).replaceAll("&", "§")
										.replaceAll("@desafiante", p.getName()).replaceAll("@sopa", "Nao"));
							}
						}
					}
					e.setCancelled(true);
					p.closeInventory();
				} else {
					e.setCancelled(true);
					p.closeInventory();
					p.sendMessage(Main.plugin.getConfig().getString("SemDesafiante").replaceAll("&", "§"));
				}
			}
			if (e.getCurrentItem().equals((Object) Custom.armDima)) {
				e.setCancelled(true);
				Custom.inv.setItem(13, Custom.armFerro);
			} else if (e.getCurrentItem().equals((Object) Custom.armFerro)) {
				e.setCancelled(true);
				Custom.inv.setItem(13, Custom.armOuro);
			} else if (e.getCurrentItem().equals((Object) Custom.armOuro)) {
				e.setCancelled(true);
				Custom.inv.setItem(13, Custom.armChain);
			} else if (e.getCurrentItem().equals((Object) Custom.armChain)) {
				e.setCancelled(true);
				Custom.inv.setItem(13, Custom.armCouro);
			} else if (e.getCurrentItem().equals((Object) Custom.armCouro)) {
				e.setCancelled(true);
				Custom.inv.setItem(13, Custom.armDima);
			}
			if (e.getCurrentItem().equals((Object) Custom.swdDima)) {
				e.setCancelled(true);
				Custom.inv.setItem(11, Custom.swdFerro);
			} else if (e.getCurrentItem().equals((Object) Custom.swdFerro)) {
				e.setCancelled(true);
				Custom.inv.setItem(11, Custom.swdOuro);
			} else if (e.getCurrentItem().equals((Object) Custom.swdOuro)) {
				e.setCancelled(true);
				Custom.inv.setItem(11, Custom.swdPedra);
			} else if (e.getCurrentItem().equals((Object) Custom.swdPedra)) {
				e.setCancelled(true);
				Custom.inv.setItem(11, Custom.swdMadeira);
			} else if (e.getCurrentItem().equals((Object) Custom.swdMadeira)) {
				e.setCancelled(true);
				Custom.inv.setItem(11, Custom.swdDima);
			}
			if (e.getCurrentItem().equals((Object) Custom.resoup)) {
				e.setCancelled(true);
				Custom.inv.setItem(15, Custom.nosoup);
			} else if (e.getCurrentItem().equals((Object) Custom.nosoup)) {
				e.setCancelled(true);
				Custom.inv.setItem(15, Custom.resoup);
			}
		}
	}
}
