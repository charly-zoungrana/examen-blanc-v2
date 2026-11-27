package net.zoungrana.model;

import java.util.ArrayList;
import java.util.List;

public final class Neuron {
    private final double bias;
    private final List<Connection> incoming = new ArrayList<>();
    private double output;

    public Neuron(double bias) {
        this.bias = bias;
    }

    public void connectFrom(Neuron source, double weight) {
        incoming.add(new Connection(source, this, weight));
    }

    List<Connection> incoming() {
        return List.copyOf(incoming);
    }

    void acceptInput(double value) {
        output = value;
    }

    public double compute() {
        double sum = bias;
        for (Connection connection : incoming) {
            sum += connection.source().output * connection.weight();
        }
        output = sum;
        return output;
    }
}
