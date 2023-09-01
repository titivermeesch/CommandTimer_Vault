package me.playbosswar.vaultconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.com.conditionsengine.ConditionCompare;
import me.playbosswar.com.conditionsengine.conditions.ConditionHelpers;
import me.playbosswar.vaultconditions.CommandTimerVaultConditions;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CheckPlayerBalance implements ConditionRule {
    @Override
    public String getName() {
        return "CHECK_PLAYER_BALANCE";
    }

    @Override
    public String getDescription() {
        return "Check the balance of a player";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");
        double value = facts.get("value");
        ConditionCompare conditionCompare = facts.get("balance_compare");
        double balance = CommandTimerVaultConditions.getEcon().getBalance(p);
//        CommandTimerVaultConditions.getEcon().isBankMember()
//        CommandTimerVaultConditions.getEcon().isBankOwner()

        return ConditionHelpers.calculateConditionCompare(conditionCompare, value, balance);
    }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("balance_compare", "Balance Compare", ConditionCompare.class, ConditionCompare.GREATER_OR_EQUAL_THAN));
        values.add(new NeededValue<>("value", "Value", Double.class, 0.0));
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
