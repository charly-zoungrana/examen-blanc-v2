package net.zoungrana;

import java.util.ArrayList;
import java.util.List;

public final class NeuralNetwork {
    private final List<Layer> layers;

    public NeuralNetwork(InputLayer input, List<HiddenLayer> hidden, OutputLayer output) {
        List<Layer> ordered = new ArrayList<>();
        ordered.add(input);
        ordered.addAll(hidden);
        ordered.add(output);
        layers = List.copyOf(ordered);
    }

    double[] forward(double[] input) {
        double[] values = input;
        for (Layer layer : layers) {
            values = layer.forward(values);
        }
        return values;
    }
}
