package ejemplos;

public class Ejemplo2 {

    public static void main(String[] args) {
        Contador c = new Contador();

        Thread t1 = new Thread(new HiloIncrementa(c));
        Thread t2 = new Thread(new HiloDecrementa(c));

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(c);
    }

    public static class HiloDecrementa implements Runnable {
        private Contador value;

        public HiloDecrementa(Contador value) {
            this.value = value;
        }

        @Override
        public void run() {
            for(int i=0;i<10000;i++)
                value.decrementa();
        }
    }
    public static class HiloIncrementa implements Runnable {

        private Contador value;

        public HiloIncrementa(Contador value) {
            this.value = value;
        }

        @Override
        public void run() {
            for(int i=0;i<10000;i++)
                value.incrementa();
        }
    }
}
