package UltraKits;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import UltraKits.Habilidades.Neji;
import me.confuser.barapi.BarAPI;

public class KitItems implements Listener {
	public static ItemStack grandpa;

	static {
		KitItems.grandpa = new ItemStack(Material.STICK);
	}

	public static void kitPvP(final Player p) {
		darKit(p, "PvP", 35, null, Main.hg);
	}

	public static void kitArcher(final Player p) {
		final ItemStack arco = new ItemStack(Material.BOW);
		arco.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		arco.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		arco.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		darKit(p, "Archer", 33, new ItemStack[] { arco, new ItemStack(Material.ARROW) }, Main.arqueiro);
	}

	public static void kitUrgal(final Player p) {
		darKit(p, "Urgal", 34, new ItemStack[] { new ItemStack(Material.POTION, 3, (short) 8201) }, Main.urgal);
	}

	public static void kitShooter(final Player p) {
		darKit(p, "Shooter", 34, new ItemStack[] { new ItemStack(Material.NETHER_STAR) }, Main.whister);
	}

	public static void kitpyro(final Player p) {
		darKit(p, "Pyro", 34, new ItemStack[] { new ItemStack(Material.FIREWORK_CHARGE, 16) }, Main.pyro);
	}

	public static void kitTrocador(final Player p) {
		darKit(p, "Trocador", 35, null, Main.Trocador);
	}

	public static void kitSoldado(final Player p) {
		darKit(p, "Soldado", 35, null, Main.soldado);
	}

	public static void kitViper(final Player p) {
		darKit(p, "Viper", 35, null, Main.viper);
	}

	public static void kitNinja(final Player p) {
		darKit(p, "Ninja", 35, null, Main.ninja);
	}

	public static void kitAnchor(final Player p) {
		darKit(p, "Anchor", 35, null, Main.anchor);
	}

	public static void kitSwitcher(final Player p) {
		darKit(p, "Switcher", 34, new ItemStack[] { new ItemStack(Material.SNOW_BALL, 16) }, Main.switcher);
	}

	public static void kitDarkmage(final Player p) {
		darKit(p, "Darkmage", 35, null, Main.darkmage);
	}

	public static void kitThor(final Player p) {
		darKit(p, "Thor", 34, new ItemStack[] { new ItemStack(Material.STONE_AXE) }, Main.thor);
	}

	public static void kitSpecialist(final Player p) {
		darKit(p, "Specialist", 34, new ItemStack[] { new ItemStack(Material.ENCHANTED_BOOK) }, Main.specialist);
	}

	public static void kitlauncher(final Player p) {
		darKit(p, "Launcher", 34, new ItemStack[] { new ItemStack(Material.FISHING_ROD) }, Main.launcher);
	}

	public static void kitmilkman(final Player p) {
		darKit(p, "Milkman", 34, new ItemStack[] { new ItemStack(Material.MILK_BUCKET, 6) }, Main.milkman);
	}

	public static void kitskeleton(final Player p) {
		darKit(p, "Skeleton", 34, new ItemStack[] { new ItemStack(Material.BONE) }, Main.skeleton);
	}

	public static void kitFisherman(final Player p) {
		darKit(p, "Fisherman", 34, new ItemStack[] { new ItemStack(Material.FISHING_ROD) }, Main.fisherman);
	}

	public static void kitPhantom(final Player p) {
		darKit(p, "Phantom", 34, new ItemStack[] { new ItemStack(Material.FEATHER) }, Main.phantom);
	}

	public static void kitGladiator(final Player p) {
		darKit(p, "Gladiator", 34, new ItemStack[] { new ItemStack(Material.IRON_FENCE) }, Main.gladiator);
	}

	public static void kitFlash(final Player p) {
		darKit(p, "Flash", 34, new ItemStack[] { new ItemStack(Material.REDSTONE_TORCH_ON) }, Main.flash);
	}

	public static void kitGrappler(final Player p) {
		final ItemStack hook = new ItemStack(Material.LEASH);
		final ItemMeta im = hook.getItemMeta();
		im.setDisplayName("Grappling Hook");
		hook.setItemMeta(im);
		darKit(p, "Grappler", 34, new ItemStack[] { hook }, Main.grappler);
	}

	public static void kitEndermage(final Player p) {
		final ItemStack portal = new ItemStack(Material.PORTAL);
		final ItemMeta im = portal.getItemMeta();
		im.setDisplayName(ChatColor.BLUE + "Endermage");
		portal.setItemMeta(im);
		darKit(p, "Endermage", 34, new ItemStack[] { portal }, Main.endermage);
	}

	public static void kitMonk(final Player p) {
		final ItemStack monk = new ItemStack(Material.BLAZE_ROD);
		final ItemMeta im = monk.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Monk");
		monk.setItemMeta(im);
		darKit(p, "Monk", 34, new ItemStack[] { monk }, Main.monk);
	}

	public static void kitCamel(final Player p) {
		darKit(p, "Camel", 35, null, Main.camel);
	}

	public static void kitFrosty(final Player p) {
		darKit(p, "Frosty", 35, null, Main.frosty);
	}

	public static void kitwither(final Player p) {
		darKit(p, "Wither", 35, null, Main.wither);
	}

	public static void kitPoseidon(final Player p) {
		darKit(p, "Poseidon", 35, null, Main.poseidon);
	}

	public static void kitstomper(final Player p) {
		darKit(p, "Stomper", 35, null, Main.stomper);
	}

	public static void kitreaper(final Player p) {
		final ItemStack reaper = new ItemStack(Material.WOOD_HOE);
		final ItemMeta im = reaper.getItemMeta();
		im.setDisplayName(ChatColor.YELLOW + "Reaper");
		reaper.setItemMeta(im);
		darKit(p, "Reaper", 34, new ItemStack[] { reaper }, Main.reaper);
	}

	public static void kitturtle(final Player p) {
		darKit(p, "Turtle", 35, null, Main.turtle);
	}

	public static void kitJumper(final Player p) {
		final ItemStack jumper = new ItemStack(Material.PUMPKIN);
		final ItemMeta im = jumper.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Jumper");
		jumper.setItemMeta(im);
		darKit(p, "Jumper", 34, new ItemStack[] { jumper }, Main.jumper);
	}

	public static void kitSnail(final Player p) {
		darKit(p, "Snail", 35, null, Main.snail);
	}

	public static void kitFireman(final Player p) {
		darKit(p, "Fireman", 35, null, Main.fireman);
	}

	public static void kitKangaroo(final Player p) {
		final ItemStack kang = new ItemStack(Material.FIREWORK);
		final ItemMeta im = kang.getItemMeta();
		im.setDisplayName(ChatColor.AQUA + "Kangaroo");
		kang.setItemMeta(im);
		darKit(p, "Kangaroo", 34, new ItemStack[] { kang }, Main.kangaroo);
	}

	public static void kitViking(final Player p) {
		darKit(p, "Viking", 34,
				new ItemStack[] { new ItemStack(Material.getMaterial(Main.plugin.getConfig().getInt("Viking.axe"))) },
				Main.viking);
	}

	public static void kitMadman(final Player p) {
		darKit(p, "Madman", 35, null, Main.madman);
	}

	public static void kitGrandpa(final Player p) {
		final ItemStack g = new ItemStack(Material.STICK);
		g.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
		darKit(p, "Grandpa", 34, new ItemStack[] { g }, Main.grandpa);
	}

	public static void kitGhost(final Player p) {
		final ItemStack pocao = new ItemStack(Material.POTION);
		pocao.setDurability((short) 8238);
		darKit(p, "Ghost", 34, new ItemStack[] { pocao }, Main.ghost);
	}

	public static void kitBarbarian(final Player p) {
		darKit(p, "Barbarian", 35, null, Main.barbarian);
	}

	public static void kitTeleporter(final Player p) {
		darKit(p, "Teleporter", 34, new ItemStack[] { new ItemStack(Material.ENDER_PEARL) }, Main.Teleporter);
	}

	public static void kitSpiderman(final Player p) {
		darKit(p, "SpiderMan", 34, new ItemStack[] { new ItemStack(Material.STRING) }, Main.spiderman);
	}

	public static void kitBerserker(final Player p) {
		darKit(p, "Berserker", 35, null, Main.berserker);
	}

	public static void kitIndio(final Player p) {
		final ItemStack d = new ItemStack(Material.PUMPKIN_SEEDS);
		final ItemMeta meta = d.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Dardos Venenosos");
		d.setItemMeta(meta);
		darKit(p, "Indio", 34, new ItemStack[] { d }, Main.indio);
	}

	public static void kitRyu(final Player p) {
		darKit(p, "Ryu", 34, new ItemStack[] { new ItemStack(Material.BEACON) }, Main.ryu);
	}

	public static void kitNeji(final Player p) {
		Neji.chakra.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		final ItemMeta meta = Neji.chakra.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW + "HAKKESHOU KAITEN");
		Neji.chakra.setItemMeta(meta);
		darKit(p, "Neji", 34, new ItemStack[] { Neji.chakra }, Main.neji);
	}

	public static void kitLobisomem(final Player p) {
		darKit(p, "Lobisomem", 34, new ItemStack[] { new ItemStack(Material.MONSTER_EGG) }, Main.lobisomem);
	}

	public static void kitGranadier(final Player p) {
		darKit(p, "Granadier", 34, new ItemStack[] { new ItemStack(Material.SNOW_BALL, 7) }, Main.granadier);
	}

	public static void kitRhino(final Player p) {
		darKit(p, "Rhino", 35, null, Main.rhino);
	}

	public static void kitAlien(final Player p) {
		darKit(p, "Alien", 34, new ItemStack[] { new ItemStack(Material.ENDER_PORTAL_FRAME) }, Main.alien);
	}

	public static void kitHulk(final Player p) {
		darKit(p, "Hulk", 35, new ItemStack[] { new ItemStack(Material.SADDLE) }, Main.hulk);
	}

	public static void kitCritical(final Player p) {
		darKit(p, "Critical", 35, null, Main.critical);
	}

	public static void kitVitality(final Player p) {
		darKit(p, "Vitality", 35, null, Main.vitality);
	}

	public static void kitQuickDropper(final Player p) {
		darKit(p, "QuickDropper", 35, null, Main.quickdropper);
	}

	public static void barKit(final Player p, final String kit) {
		BarAPI.setMessage(p, Main.plugin.getConfig().getString("BarKit").replaceAll("&", "§").replaceAll("@kit", kit),
				Main.plugin.getConfig().getInt("BarKitTime"));
	}

	public static void darKit(final Player p, final String kit, final int sopas, final ItemStack[] outros,
			final ArrayList<String> habilidade) {
		for (final PotionEffect efeito : p.getActivePotionEffects()) {
			p.removePotionEffect(efeito.getType());
		}
		p.getInventory().clear();
		p.getEquipment().setHelmet(new ItemStack(
				Material.getMaterial(Main.plugin.getConfig().getInt(String.valueOf(kit) + ".helmet.type"))));
		p.getEquipment().setChestplate(new ItemStack(
				Material.getMaterial(Main.plugin.getConfig().getInt(String.valueOf(kit) + ".chestplate.type"))));
		p.getEquipment().setLeggings(new ItemStack(
				Material.getMaterial(Main.plugin.getConfig().getInt(String.valueOf(kit) + ".leggings.type"))));
		p.getEquipment().setBoots(new ItemStack(
				Material.getMaterial(Main.plugin.getConfig().getInt(String.valueOf(kit) + ".boots.type"))));
		p.getInventory().setItem(0, new ItemStack(
				Material.getMaterial(Main.plugin.getConfig().getInt(String.valueOf(kit) + ".sword.type"))));
		if (!Main.plugin.getConfig().getStringList(String.valueOf(kit) + ".helmet.enchants").isEmpty()) {
			for (final String s : Main.plugin.getConfig().getStringList(String.valueOf(kit) + ".helmet.enchants")) {
				final String[] e = s.split(" ");
				p.getEquipment().getHelmet().addUnsafeEnchantment(Enchantment.getById((int) Integer.valueOf(e[0])),
						(int) Integer.valueOf(e[1]));
			}
		}
		if (!Main.plugin.getConfig().getStringList(String.valueOf(kit) + ".chestplate.enchants").isEmpty()) {
			for (final String s : Main.plugin.getConfig().getStringList(String.valueOf(kit) + ".chestplate.enchants")) {
				final String[] e = s.split(" ");
				p.getEquipment().getChestplate().addUnsafeEnchantment(Enchantment.getById((int) Integer.valueOf(e[0])),
						(int) Integer.valueOf(e[1]));
			}
		}
		if (!Main.plugin.getConfig().getStringList(String.valueOf(kit) + ".leggings.enchants").isEmpty()) {
			for (final String s : Main.plugin.getConfig().getStringList(String.valueOf(kit) + ".leggings.enchants")) {
				final String[] e = s.split(" ");
				p.getEquipment().getLeggings().addUnsafeEnchantment(Enchantment.getById((int) Integer.valueOf(e[0])),
						(int) Integer.valueOf(e[1]));
			}
		}
		if (!Main.plugin.getConfig().getStringList(String.valueOf(kit) + ".boots.enchants").isEmpty()) {
			for (final String s : Main.plugin.getConfig().getStringList(String.valueOf(kit) + ".boots.enchants")) {
				final String[] e = s.split(" ");
				p.getEquipment().getBoots().addUnsafeEnchantment(Enchantment.getById((int) Integer.valueOf(e[0])),
						(int) Integer.valueOf(e[1]));
			}
		}
		if (!Main.plugin.getConfig().getStringList(String.valueOf(kit) + ".sword.enchants").isEmpty()) {
			for (final String s : Main.plugin.getConfig().getStringList(String.valueOf(kit) + ".sword.enchants")) {
				final String[] e = s.split(" ");
				p.getInventory().getItem(0).addUnsafeEnchantment(Enchantment.getById((int) Integer.valueOf(e[0])),
						(int) Integer.valueOf(e[1]));
			}
		}
		if (outros != null) {
			p.getInventory().addItem(outros);
		}
		for (int i = 0; i < sopas; ++i) {
			p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
		}
		Main.kits.add(p.getName());
		habilidade.add(p.getName());
		barKit(p, kit);
		p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
	}
}
