package net.zoungrana;

import java.util.List;

public final class InputLayer extends AbstractLayer {
    public InputLayer(List<Neuron> neurons) {
        super(neurons);
    }

    @Override
    public double[] forward(double[] input) {
        if (input.length != neurons().size()) {
            throw new IllegalArgumentException("Dimension incorrecte pour le vecteur d'entrée.");
        }
        for (int i = 0; i < input.length; i++) {
            neurons().get(i).acceptInput(input[i]);
        }
        return input.clone();
    }
}
