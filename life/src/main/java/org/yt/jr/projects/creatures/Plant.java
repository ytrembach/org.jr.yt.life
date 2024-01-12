package org.yt.jr.projects.creatures;

import org.yt.jr.projects.creatures.actions.reproduce.CloneAction;
import org.yt.jr.projects.utils.Config;
import org.yt.jr.projects.utils.logs.LogLevels;
import org.yt.jr.projects.utils.logs.LogSources;
import org.yt.jr.projects.utils.logs.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class Plant extends Creature {
    public Plant(int health) {
        super(CreaturesTypes.PLANTS, health);
    }

    @Override
    public void tryToReproduce() {
        if (isReadyToReproduce(false)) {
            double probability = Config.CONFIG.getReproduceProbability(type);
            if (ThreadLocalRandom.current().nextDouble() < probability) {
                new CloneAction(this).cloneChild();
                Logger.Log(LogSources.CREATURE, LogLevels.DEBUG, String.format("%s cloned", this));
            }
        }
    }
}
