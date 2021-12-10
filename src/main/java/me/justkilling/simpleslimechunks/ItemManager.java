package me.justkilling.simpleslimechunks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
    public static ItemStack slimechunk;
    public static void init(){
        createSlimechunk();
    }
    private static void createSlimechunk(){
        ItemStack item = new ItemStack(Material.PAPER);
        String itemname = "&l&nClick&r to get the closest &a&lslime &rchunk!";
        itemname = ChatColor.translateAlternateColorCodes('&', itemname);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemname));
        itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemmeta);
        slimechunk = item;
    }
}
