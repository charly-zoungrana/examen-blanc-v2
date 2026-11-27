package net.zoungrana.activation;

public final class Identity implements ActivationFunction {
    @Override
    public double apply(double value) {
        return value;
    }
}
