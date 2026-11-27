package net.zoungrana.model;

@FunctionalInterface
public interface NetworkObserver {
    void onStateChanged(NetworkState previous, NetworkState current);
}
