package me.justkilling.simpleslimechunks.Commands;

import me.justkilling.simpleslimechunks.ItemManager;
import me.justkilling.simpleslimechunks.SimpleSlimechunks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class getslimechunk implements CommandExecutor {
    String itemname = "";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;


            p.getInventory().addItem(ItemManager.slimechunk);
            p.sendMessage("Successfully gave " +  itemname +"!");
        }
        return true;
    }

}
