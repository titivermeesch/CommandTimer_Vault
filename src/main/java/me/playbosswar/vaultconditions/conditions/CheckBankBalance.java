package me.playbosswar.vaultconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.vaultconditions.CommandTimerVaultConditions;
import net.milkbowl.vault.economy.EconomyResponse;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CheckBankBalance implements ConditionRule {
    @Override
    public String getName() {
        return "CHECK_BANK_BALANCE";
    }

    @Override
    public String getDescription() {
        return "Check the balance of a bank";
    }

    @Override
    public boolean evaluate(Facts facts) {
        String name = facts.get("bank_name");
        double value = facts.get("value");

        return CommandTimerVaultConditions.getEcon().bankHas(name, value).type == EconomyResponse.ResponseType.SUCCESS;
    }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("bank_name", "Bank Name", String.class, ""));
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
