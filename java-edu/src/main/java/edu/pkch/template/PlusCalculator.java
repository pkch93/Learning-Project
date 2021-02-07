package edu.pkch.template;

import static edu.pkch.template.CalculateType.PLUS;

public class PlusCalculator extends AbstractCalculator {
    public PlusCalculator(CalculateValidator calculateValidator) {
        super(calculateValidator);
    }

    @Override
    public boolean isSupport(CalculateType type) {
        return type == PLUS;
    }

    @Override
    protected int operate(int x, int y) {
        return x + y;
    }
}
