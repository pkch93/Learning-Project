package edu.pkch.clazz;

public class Calculator {
    public static class Plus implements Operation<Integer> {
        @Override
        public Integer operate(Integer t1, Integer t2) {
            return t1 + t2;
        }
    }

    public static class Product implements Operation<Integer> {
        @Override
        public Integer operate(Integer t1, Integer t2) {
            return t1 * t2;
        }
    }

    class Calculation {
        public String upper() {
            Class<? extends Calculator> clazz = Calculator.this.getClass();
            return clazz.getName();
        }
    }
}
