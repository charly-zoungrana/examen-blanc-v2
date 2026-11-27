package net.zoungrana;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Neuron first = new Neuron(0);
        Neuron second = new Neuron(0);
        Neuron hidden = new Neuron(0.5);
        Neuron output = new Neuron(0.125);

        hidden.connectFrom(first, 0.5);
        hidden.connectFrom(second, 0.25);
        output.connectFrom(hidden, 0.5);

        NeuralNetwork network = new NeuralNetwork(
                new InputLayer(List.of(first, second)),
                List.of(new HiddenLayer(List.of(hidden))),
                new OutputLayer(List.of(output)));

        double[] input = {2, 4};
        double[] result = network.forward(input);
        System.out.println("Entree : " + Arrays.toString(input));
        System.out.println("Couche cachee : 2 * 0.5 + 4 * 0.25 + 0.5 = 2.5");
        System.out.println("Sortie attendue : [1.375]");
        System.out.println("Sortie calculee : " + Arrays.toString(result));
        if (result.length != 1 || result[0] != 1.375) {
            throw new IllegalStateException("Propagation incorrecte.");
        }
    }
}
