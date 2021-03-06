package org.nd4j.linalg.activations.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.math3.util.Pair;
import org.nd4j.linalg.activations.BaseActivationFunction;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.transforms.Cube;
import org.nd4j.linalg.factory.Nd4j;

/**
 * f(x) = x^3
 */
@EqualsAndHashCode
@Getter
public class ActivationCube extends BaseActivationFunction {

    @Override
    public INDArray getActivation(INDArray in, boolean training) {
        Nd4j.getExecutioner().execAndReturn(new Cube(in));
        return in;
    }

    @Override
    public Pair<INDArray,INDArray> backprop(INDArray in, INDArray epsilon) {
        INDArray dLdz = Nd4j.getExecutioner().execAndReturn(new Cube(in).derivative());
        dLdz.muli(epsilon);
        return new Pair<>(dLdz, null);
    }

    @Override
    public String toString() {
        return "cube";
    }
}
