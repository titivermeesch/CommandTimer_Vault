package me.playbosswar.vaultconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.vaultconditions.CommandTimerVaultConditions;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class IsBankMember implements ConditionRule {
    @Override
    public String getName() {
        return "IS_BANK_MEMBER";
    }

    @Override
    public String getDescription() {
        return "Check if player is a bank member";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");
        String bankName=  facts.get("bank_name");

        if (p == null) {
            return false;
        }

        return CommandTimerVaultConditions.getEcon().isBankMember(bankName, p).type == EconomyResponse.ResponseType.SUCCESS;
    }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("bank_name", "Bank Name", String.class, ""));
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
