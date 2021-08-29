package UltraKits.Inventarios;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import UltraKits.KitItems;
import UltraKits.Main;

public class MenuInv implements Listener {
	public static void guiKits(final Player p) {
		final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder) p, 54,
				new StringBuilder().append(ChatColor.DARK_GREEN).append(ChatColor.BOLD).append("Seus Kits").toString());
		final ItemStack vidro = new ItemStack(Material.THIN_GLASS);
		final ItemMeta metav = vidro.getItemMeta();
		metav.setDisplayName(ChatColor.AQUA + Main.plugin.getConfig().getString("ServerName"));
		vidro.setItemMeta(metav);
		if (p.hasPermission("kit.pvp") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.STONE_SWORD);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "PvP");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Kit sem habilidade!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.arqueiro") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.BOW);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Arqueiro");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Mate seus inimigos com seu arco e flecha");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.shooter") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.NETHER_STAR);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Shooter");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Jogue efeitos de wither em seu inimigo");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.pyro") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.FIREWORK_CHARGE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Pyro");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Jogue bolas de fogo!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.ninja") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.COMPASS);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Ninja");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Bata em um jogador e se agache");
			descpyro.add(ChatColor.WHITE + "para se teleportar ate ele");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.fisherman") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.FISHING_ROD);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Fisherman");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Use sua vara de pesca para puxar jogadores");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.soldado") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.IRON_CHESTPLATE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Soldado");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Use sua espada para saltar");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.urgal") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.REDSTONE_BLOCK);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Urgal");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Receba 3 pocoes de forca");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.trocador") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.DIAMOND_CHESTPLATE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Trocador");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Aperte shift e mude sua armadura!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.grappler") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.LEASH);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Grappler");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Use sua corda para se mover rapidamente");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.darkmage") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.COAL);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Darkmage");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao bater em jogadores voce tem 33% de chance");
			descpyro.add(ChatColor.WHITE + "de dar a eles o efeito de Cegueira!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.rhino") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.SAPLING);
			pyro.setDurability((short) 5);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Rhino");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Se abaixe, carregue sua for\u00e7a e");
			descpyro.add(ChatColor.WHITE + "mande o inimigo pelos ares!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.poseidon") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.WATER_BUCKET);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Poseidon");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao entrar em contato com a agua ganhe forcas!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.granadier") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.TNT);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Granadier");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Lance granadas contra os inimigos!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.launcher") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.SPONGE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Launcher");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Com sua vara de pesca");
			descpyro.add(ChatColor.WHITE + "Jogue os caras pra cima!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.milkman") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.MILK_BUCKET);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Milkman");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao clicar em seu balde de leite voce tera");
			descpyro.add(ChatColor.WHITE + "Efeitos!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.turtle") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.LEATHER_CHESTPLATE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Turtle");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao cair de qualquer lugar");
			descpyro.add(ChatColor.WHITE + "Segurando o shift voce nao morrera!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.skeleton") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.BONE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Skeleton");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Apos bater em qualquer jogador");
			descpyro.add(ChatColor.WHITE + "voce o matara!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.camel") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.SAND);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Camel");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ganhe velocidade e regeneracao na areia");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.thor") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.STONE_AXE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Thor");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Solte raios com seu machado!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.frosty") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.SNOW_BLOCK);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Frosty");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ganhe velocidade e regeneracao na neve");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.specialist") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.EXP_BOTTLE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Specialist");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Tenha uma mesa de encantamento portatil!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.endermage") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.PORTAL);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Endermage");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Puxe jogadores com seu portal!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.stomper") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.IRON_BOOTS);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Stomper");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Recebe 2 coracoes de dano");
			descpyro.add(ChatColor.WHITE + "e ao cair em cima de jogadores");
			descpyro.add(ChatColor.WHITE + "voce acabara matando-os a menos que estejam no shift!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.kangaroo") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.FIREWORK);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Kangaroo");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Com seu firework use para");
			descpyro.add(ChatColor.WHITE + "Dar pulos duplos!");
			descpyro.add(ChatColor.WHITE + "Um otimo kit para fugir de inimigos");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.reaper") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.WOOD_HOE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Reaper");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Com sua enchada");
			descpyro.add(ChatColor.WHITE + "bate em jogadores e evenene-os!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.fireman") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.LAVA_BUCKET);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Fireman");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Use seu kit para");
			descpyro.add(ChatColor.WHITE + "nao receber dano de fogo!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.anchor") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.ANVIL);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Anchor");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Use seu kit para nao");
			descpyro.add(ChatColor.WHITE + "receber e nem levar knocback!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.flash") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.REDSTONE_TORCH_ON);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Flash");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Use seu kit para");
			descpyro.add(ChatColor.WHITE + "Teleportar-se!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.viper") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.SPIDER_EYE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Viper");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao bater em jogadores voce tem 33% de chance");
			descpyro.add(ChatColor.WHITE + "de dar a eles o efeito de Veneno!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.monk") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.BLAZE_ROD);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Monk");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Coloque items em um local");
			descpyro.add(ChatColor.WHITE + "aleatorio no inventario do inimigo!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.snail") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.SOUL_SAND);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Snail");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao bater em jogadores voce tem 33% de chance");
			descpyro.add(ChatColor.WHITE + "de dar a eles o efeito de Lentidao!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.wither") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.SKULL_ITEM);
			pyro.setDurability((short) 1);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Wither");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao bater em jogadores voce tem 33% de chance");
			descpyro.add(ChatColor.WHITE + "de dar a eles o efeito do Wither!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.gladiator") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.IRON_FENCE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Gladiator");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Crie uma arena e fique frente a frente");
			descpyro.add(ChatColor.WHITE + "contra os seus inimigos!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.switcher") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.SNOW_BALL);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Switcher");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Use suas bolas de neve para trocar");
			descpyro.add(ChatColor.WHITE + "de lugar com seus jogadores!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.jumper") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.TRAP_DOOR);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Jumper");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Use esse kit e jogue");
			descpyro.add(ChatColor.WHITE + "seus inimigos pra cima!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.vitality") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.MUSHROOM_SOUP);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Vitality");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao matar um player seu inventario");
			descpyro.add(ChatColor.WHITE + "ser\u00e1 preenchido com sopas!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.madman") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.JACK_O_LANTERN);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Madman");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao bater em jogadores voce tem 33% de chance");
			descpyro.add(ChatColor.WHITE + "de dar a eles Nausea e Cegueira.");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.viking") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.GOLD_AXE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Viking");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao bater em jogadores com machados");
			descpyro.add(ChatColor.WHITE + "o dano ser\u00e1 2 vezes maior.");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.grandpa") || p.hasPermission("kit.*")) {
			final ItemStack pyro = KitItems.grandpa;
			pyro.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Grandpa");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Aplique Knockback nos seus advers\u00e1rios!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.ghost") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.IRON_HOE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Ghost");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Transforme em um fantasma e");
			descpyro.add(ChatColor.WHITE + "assombre seus inimigos!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.quickdropper") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.BOWL);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "QuickDropper");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ao tomar uma sopa o pote ser\u00e1");
			descpyro.add(ChatColor.WHITE + "dropado automaticamente!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.teleporter") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.ENDER_PEARL);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Teleporter");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Se teleporte com perolas do fim!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.spiderman") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.WEB);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "SpiderMan");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Atire teia em seus inimigos!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.barbarian") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.IRON_ORE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Barbarian");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Mate um player com a espada");
			descpyro.add(ChatColor.WHITE + "e fa\u00e7a um UPGRADE!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.berserker") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.REDSTONE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Berserker");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Mate um player e ganhe for\u00e7a!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.indio") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.PUMPKIN_SEEDS);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Indio");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Atire dardos venenosos em seus inimigos!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.ryu") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.BEACON);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Ryu");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "D\u00ea um HADOUKEN em seus inimigos!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.neji") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.EYE_OF_ENDER);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Neji");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Fa\u00e7a um HAKKESHOU KAITEN (rotacao) e");
			descpyro.add(ChatColor.WHITE + "afaste quem estiver por perto!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.lobisomem") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.MONSTER_EGG);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Lobisomem");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Ganhe efeitos e receba ajuda de");
			descpyro.add(ChatColor.WHITE + "seus amigos Lobos!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.phantom") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.FEATHER);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Phantom");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Use a pena e voe por 5 segundos!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.alien") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.ENDER_PORTAL_FRAME);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Alien");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Use seus poderes de E.T. para ");
			descpyro.add(ChatColor.WHITE + "levitar players e infecta-los!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.hulk") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.SADDLE);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Hulk");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "Pegue e lance seus inimigos!");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		if (p.hasPermission("kit.critical") || p.hasPermission("kit.*")) {
			final ItemStack pyro = new ItemStack(Material.GOLDEN_APPLE);
			pyro.setDurability((short) 1);
			final ItemMeta metapyro = pyro.getItemMeta();
			metapyro.setDisplayName(ChatColor.GREEN + "Critical");
			final ArrayList<String> descpyro = new ArrayList<String>();
			descpyro.add(ChatColor.WHITE + "30% de chance de dar um ");
			descpyro.add(ChatColor.WHITE + "golpe critico ao bater! ");
			metapyro.setLore(descpyro);
			pyro.setItemMeta(metapyro);
			inv.addItem(new ItemStack[] { pyro });
		}
		ItemStack[] arrayOfItemStack;
		for (int descpyro2 = (arrayOfItemStack = inv.getContents()).length,
				metapyro2 = 0; metapyro2 < descpyro2; ++metapyro2) {
			final ItemStack item = arrayOfItemStack[metapyro2];
			if (item == null) {
				inv.setItem(inv.firstEmpty(), vidro);
			}
		}
		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CHEST_OPEN, 40.0f, 1.0f);
	}
}
