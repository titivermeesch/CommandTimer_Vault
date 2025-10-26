package me.playbosswar.vaultconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.vaultconditions.CommandTimerVaultConditions;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerHasPermission implements ConditionRule {
    private final Plugin plugin;

    public PlayerHasPermission(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "HAS_PERMISSION";
    }

    @Override
    public String getDescription() {
        return "Check if player has permission (supports offline players)";
    }

    @Override
    public boolean evaluate(Facts facts) {
        OfflinePlayer p = facts.get("player");
        String permission = facts.get("permission");
        Boolean alwaysWhenOp = facts.get("always_when_op");

        if (p == null) {
            return false;
        }

        if (permission == null || permission.isEmpty()) {
            return false;
        }

        if (alwaysWhenOp && p.isOp()) {
            return true;
        }
    
        return CommandTimerVaultConditions.getPerms().playerHas(null, p, permission);
    }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("permission", "Permission", String.class, ""));
        values.add(new NeededValue<>("always_when_op", "Always trigger when OP", Boolean.class, true));
        return values;
    }

    @Override
    public void execute(Facts facts) {
    }

    @Override
    public int compareTo(@NotNull Rule o) {
        return 0;
    }
}

