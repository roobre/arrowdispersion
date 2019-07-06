package es.roobre.arrowdispersion;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Random;

public final class ArrowDispersion extends JavaPlugin implements Listener {
    private static final double DISPERSION = 0.07;

    private Random rng = new Random();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBowFired(EntityShootBowEvent shot) {
        Entity arrow = shot.getProjectile();
        if (arrow.getType() != EntityType.ARROW) {
            return;
        }

        Vector velocity = arrow.getVelocity();
        velocity.setX(velocity.getX() * rand());
        velocity.setY(velocity.getY() * rand());
        velocity.setZ(velocity.getZ() * rand());
        arrow.setVelocity(velocity);
    }

    private double rand() {
        return 1.0 + (DISPERSION * (rng.nextDouble() * 2.0 - 1.0));
    }
}
