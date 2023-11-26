package co.marcin.darkrise.riseresources.rewards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a reward of a certain resource, or a cost if its amount is negative,
 * applied when mining a block.
 */
public abstract class Reward {

    public static Reward make(String string) {
        if (string.startsWith(VanillaExpReward.NAME)) {
            return new VanillaExpReward(string);
        } else if (string.startsWith(VaultMoneyReward.NAME)) {
            return new VaultMoneyReward(string);
        } else if (string.startsWith(JobsMoneyReward.NAME)) {
            if (!Bukkit.getPluginManager().isPluginEnabled("Jobs")) {
                throw new IllegalStateException("Jobs is not enabled");
            }
            return new JobsMoneyReward(string);
        } else if (string.startsWith(JobsExpReward.NAME)) {
            if (!Bukkit.getPluginManager().isPluginEnabled("Jobs")) {
                throw new IllegalStateException("Jobs is not enabled");
            }
            return new JobsExpReward(string);
        } else if (string.startsWith(JobsPointsReward.NAME)) {
            if (!Bukkit.getPluginManager().isPluginEnabled("Jobs")) {
                throw new IllegalStateException("Jobs is not enabled");
            }
            return new JobsPointsReward(string);
        } else if (string.startsWith(SkillAPISkillReward.NAME)) {
            if (!Bukkit.getPluginManager().isPluginEnabled("ProSkillAPI")) {
                throw new IllegalStateException("ProSkillAPI is not enabled");
            }
            return new SkillAPISkillReward(string);
        } else {
            throw new IllegalArgumentException("Unknown name");
        }
    }

    public Reward(String fullString) {
        if (!fullString.startsWith(this.getName())) {throw new IllegalArgumentException();}
    }

    @NotNull
    public abstract String getName();

    public abstract void apply(@NotNull Player player);

    public String[] getMessageArgs() {return new String[0];}
}
