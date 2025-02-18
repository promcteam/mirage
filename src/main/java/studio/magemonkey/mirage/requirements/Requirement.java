package studio.magemonkey.mirage.requirements;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class Requirement {
    private final String fullString;

    public static Requirement make(String string) {
        if (string.startsWith(EnchantmentRequirement.NAME)) {
            return new EnchantmentRequirement(string);
        } else if (string.startsWith(FabledSkillRequirement.NAME)) {
            if (!Bukkit.getPluginManager().isPluginEnabled("Fabled")) {
                throw new IllegalStateException("Fabled is not enabled");
            }
            return new FabledSkillRequirement(string);
        } else if (string.startsWith(FabledClassRequirement.NAME)) {
            if (!Bukkit.getPluginManager().isPluginEnabled("Fabled")) {
                throw new IllegalStateException("Fabled is not enabled");
            }
            return new FabledClassRequirement(string);
        } else if (string.startsWith(JobsJobRequirement.NAME)) {
            if (!Bukkit.getPluginManager().isPluginEnabled("Jobs")) {
                throw new IllegalStateException("Jobs is not enabled");
            }
            return new JobsJobRequirement(string);
        } else if (string.startsWith(McMMOSkillRequirement.NAME)) {
            if (!Bukkit.getPluginManager().isPluginEnabled("mcMMO")) {
                throw new IllegalStateException("mcMMO is not enabled");
            }
            return new McMMOSkillRequirement(string);
        } else if (string.startsWith(FusionProfessionRequirement.NAME) || string.startsWith("RPGCRAFTING_profession")) {
            if (!Bukkit.getPluginManager().isPluginEnabled("Fusion")) {
                throw new IllegalStateException("Fusion is not enabled");
            }
            return new FusionProfessionRequirement(string);
        } else {
            throw new IllegalArgumentException("Unknown name");
        }
    }

    public Requirement(String fullString) {
        if (!fullString.split(":")[0].equalsIgnoreCase(this.getName())) {
            throw new IllegalArgumentException();
        }
        this.fullString = fullString;
    }

    @NotNull
    public abstract String getName();

    public abstract boolean meets(@NotNull Player player);

    @Override
    public String toString() {return fullString;}
}
