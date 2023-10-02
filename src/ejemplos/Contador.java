package ejemplos;

public class Contador {

    private int value;

    public Contador() {
        this.value = 0;
    }

    public synchronized void incrementa(){
        value = value + 1;
    }
    public synchronized void decrementa(){
        value = value - 1;
    }

    @Override
    public String toString() {
        return "Contador{" +
                "value=" + value +
                '}';
    }
}
