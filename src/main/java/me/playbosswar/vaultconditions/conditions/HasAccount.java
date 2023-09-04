package me.playbosswar.vaultconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.vaultconditions.CommandTimerVaultConditions;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HasAccount implements ConditionRule {
    @Override
    public String getName() {
        return "HAS_ACCOUNT";
    }

    @Override
    public String getDescription() {
        return "Check if player has an account";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");

        if (p == null) {
            return false;
        }

        return CommandTimerVaultConditions.getEcon().hasAccount(p);
    }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        return new ArrayList<>();
    }

    @Override
    public void execute(Facts facts) {
    }

    @Override
    public int compareTo(@NotNull Rule o) {
        return 0;
    }
}
