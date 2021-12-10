package me.justkilling.simpleslimechunks.Events;

import me.justkilling.simpleslimechunks.SimpleSlimechunks;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class ClickEvent implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(e.getItem() != null){
            if(e.getItem().getEnchantmentLevel(Enchantment.DURABILITY) == 10 && e.getItem().getType() == Material.PAPER){


                Chunk nearestSlimeChunk = getNearestSlimeChunk(getSlimeChunksAround(player, 5), player);
                String message = ChatColor.translateAlternateColorCodes('&',SimpleSlimechunks.getInstance().getConfig().getString("chunkmessage"));
                double chunkX = nearestSlimeChunk.getBlock(8,64,8).getLocation().getX();
                double chunkZ = nearestSlimeChunk.getBlock(8,64,8).getLocation().getZ();
                player.sendMessage(message.replace("%coordinates%",  "X:" + chunkX + " Z:" + chunkZ));
                spawnChunkparticles(nearestSlimeChunk, player);


            }

        }

    }

    private List<Chunk> getSlimeChunksAround(Player p, int range) {
        Location loc = p .getLocation();
        World world = loc.getWorld();
        int baseX = loc.getChunk().getX();
        int baseZ = loc.getChunk().getZ();

        List<Chunk> chunkList = new ArrayList<>();
        try {
            for (int x = -range; x <= range; x++) {
                for (int z = -range; z <= range; z++) {
                    Chunk chunk = world.getChunkAt(baseX + x, baseZ + z);
                    if (chunk.isSlimeChunk()){

                        chunkList.add(chunk);
                    }

                }
            }
        } catch (Exception ex) {
            Bukkit.getServer().getConsoleSender().sendMessage(ex.getMessage());
        }

        return chunkList;
    }

    private Chunk getNearestSlimeChunk(List<Chunk> chunkList, Player player) {
        Chunk nearestChunk = chunkList.get(0);
        double oldDist = player.getLocation().distanceSquared(nearestChunk.getBlock(8,64,8).getLocation());
        for (Chunk chunk : chunkList){
            double dist = player.getLocation().distanceSquared(chunk.getBlock(8,64,8).getLocation());
            if(dist<oldDist){
                oldDist = dist;
                player.sendMessage(oldDist + " " + dist + " Chunk:" + String.valueOf(chunk));
                nearestChunk = chunk;
            }
        }
        return nearestChunk;
    }
    int runnableTimer = 0;
    private void spawnChunkparticles(Chunk chunk, Player p){
        int minX = chunk.getX()*16;
        int minZ = chunk.getZ()*16;
        int minY = p.getLocation().getBlockY();
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(0, 255, 0), 5);

         new BukkitRunnable(){
            @Override
            public void run(){
                for(int x = minX; x < minX+17; x++) {
                    for(int y = minY; y < minY+1; y++) {
                        for(int z = minZ; z < minZ+17; z++) {
                            p.spawnParticle(Particle.REDSTONE, minX, y, z, 1, dustOptions);
                            p.spawnParticle(Particle.REDSTONE, x , y, minZ, 1, dustOptions);
                            p.spawnParticle(Particle.REDSTONE, minX+16, y, z, 1, dustOptions);
                            p.spawnParticle(Particle.REDSTONE, x , y, minZ+17, 1, dustOptions);

                        }
                    }
                }
                runnableTimer++;
                if(runnableTimer >= SimpleSlimechunks.getInstance().getConfig().getInt("particleduration")) this.cancel();
            }

        }.runTaskTimerAsynchronously(SimpleSlimechunks.getInstance(),0,20);



    }

}
