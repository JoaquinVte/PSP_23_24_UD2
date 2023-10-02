package actividades;

import com.sun.security.jgss.GSSUtil;

public class Actividad3 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Hilo("h1"));
        Thread t2 = new Thread(new Hilo("h2"));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            System.out.println("Hilo principal ha terminado.");
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido");
        }

    }



    public static class Hilo implements Runnable {
        private String nombre;

        public Hilo(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            int i = 0;
            while (i<3){
                int time = (int)(Math.random()*491 + 10);
                System.out.println("Soy el hilo " + nombre);

                try {
                    System.out.println("Soy el hilo " + nombre + " y me duermo " + time +"ms");
                    Thread.sleep(time*10);
                } catch (InterruptedException e) {
                    System.out.println("Soy el hilo " + nombre + " y me han interrumpido.");
                }
                i++;
            }
        }
    }
}
