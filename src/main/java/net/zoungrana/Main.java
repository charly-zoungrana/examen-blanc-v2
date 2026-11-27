package net.zoungrana;

import net.zoungrana.activation.ActivationAdapter;
import net.zoungrana.activation.Sigmoid;
import net.zoungrana.activation.Softmax;
import net.zoungrana.model.HiddenLayer;
import net.zoungrana.model.InputLayer;
import net.zoungrana.model.NeuralNetwork;
import net.zoungrana.model.Neuron;
import net.zoungrana.model.OutputLayer;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
        demonstrateActivations();
    }

    private static void demonstrateActivations() {
        Neuron neuron = new Neuron(2.0);
        double identity = neuron.compute();
        neuron.setActivation(new Sigmoid());
        double sigmoid = neuron.compute();
        neuron.setActivation(new ActivationAdapter(value -> value * value));
        double adapted = neuron.compute();

        OutputLayer output = new OutputLayer(List.of(new Neuron(1001), new Neuron(1000)), new Softmax());
        double[] probabilities = output.forward(new double[0]);
        double sum = Arrays.stream(probabilities).sum();

        System.out.println("\nActivations sur le score 2.0");
        System.out.printf(Locale.ROOT, "Identity = %.1f | Sigmoid = %.6f | Adaptateur = %.1f%n",
                identity, sigmoid, adapted);
        System.out.printf(Locale.ROOT, "Softmax([1001, 1000]) = [%.6f, %.6f] | somme = %.6f%n",
                probabilities[0], probabilities[1], sum);

        if (identity != 2.0 || Math.abs(sigmoid - 0.880797) > 1e-6 || adapted != 4.0
                || Math.abs(probabilities[0] - 0.731059) > 1e-6 || Math.abs(sum - 1.0) > 1e-12) {
            throw new IllegalStateException("Resultat inattendu pour les activations.");
        }
    }
}
