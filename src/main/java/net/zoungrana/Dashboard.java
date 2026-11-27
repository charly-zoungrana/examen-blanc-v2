package net.zoungrana;

import net.zoungrana.model.NetworkObserver;
import net.zoungrana.model.NetworkState;

final class Dashboard implements NetworkObserver {
    private int transitions;

    @Override
    public void onStateChanged(NetworkState previous, NetworkState current) {
        transitions++;
        System.out.printf("Tableau de bord [%d] : %s -> %s%n", transitions, previous, current);
    }

    int transitions() {
        return transitions;
    }
}
