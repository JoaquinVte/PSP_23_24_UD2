package ejemplos;

public class LanzaHilos {

    public static void main(String[] args) {

        Thread h1 = new Thread(new Hilos("H1"));
        Thread h2 = new Thread(new Hilos("H2"));

        h1.setPriority(Thread.MAX_PRIORITY);
        h2.setPriority(Thread.MIN_PRIORITY);
        h1.setDaemon(true);
        h2.setDaemon(true);

        h1.start();
        h2.start();

        System.out.println("Hilo principal terminado.");

    }
   static class Hilos implements Runnable {

        private final String nombre;

        Hilos(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            System.out.printf("Hola, soy el hilo: %s.\n", this.nombre);
            System.out.printf("Hilo %s terminado.\n", this.nombre);
        }

    }

}