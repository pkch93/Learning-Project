package edu.pkch.template;

import static edu.pkch.template.CalculateType.MINUS;

public class MinusCalculator extends AbstractCalculator {
    public MinusCalculator(CalculateValidator calculateValidator) {
        super(calculateValidator);
    }

    @Override
    public boolean isSupport(CalculateType type) {
        return type == MINUS;
    }

    @Override
    protected int operate(int x, int y) {
        return x - y;
    }
}
