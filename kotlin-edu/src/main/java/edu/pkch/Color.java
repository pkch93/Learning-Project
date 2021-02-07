package edu.pkch;

public enum Color {
    RED("#ff0000") {
        public void print() {
            System.out.println(getRgb());
        }
    },
    GREEN("#00ff00") {
        public void print() {
            System.out.println(getRgb());
        }
    },
    BLUE("#0000ff") {
        public void print() {
            System.out.println(getRgb());
        }
    };

    private final String rgb;

    Color(String rgb) {
        this.rgb = rgb;
    }

    public String getRgb() {
        return rgb;
    }

    public abstract void print();
}
