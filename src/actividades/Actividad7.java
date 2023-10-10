package actividades;

import java.util.ArrayList;
import java.util.List;

public class Actividad7 {
    private static final int CANTIDAD = 100000000;
    private static final int HILOS = 10;

    public static void main(String[] args) {
        Actividad7 a7 = new Actividad7();
        a7.go();
    }

    private void go() {
        Contadores contadores = new Contadores();
        List<Thread> hilos = new ArrayList<>();

        for (int i=0;i<HILOS;i++)
            hilos.add(new Thread(new Hilo(contadores)));

        for(Thread t : hilos)
            t.start();

        try{
            for(Thread t : hilos)
                t.join();
        }catch (Exception e){}

        System.out.println(contadores.getContador1());
        System.out.println(contadores.getContador2());

    }


    class Hilo implements Runnable {
        private Contadores contadores;

        public Hilo(Contadores contadores) {
            this.contadores = contadores;
        }

        @Override
        public void run() {
            for(int i=0;i<CANTIDAD/HILOS;i++)
                contadores.incrementar1();

            for(int i=0;i<CANTIDAD/HILOS;i++)
                contadores.incrementar2();
        }
    }

    class Contadores {
        private long cont1 = 0;
        private long cont2 = 0;
        private final Object lock1 = new Object();
        private final Object lock2 = new Object();
        public void incrementar1() {
            synchronized (lock1) {
                cont1++;
            }

        }



        public long getContador1() {

            synchronized (lock1) {

                return cont1;

            }

        }



        public void incrementar2() {

            synchronized (lock2) {

                cont2++;

            }

        }



        public long getContador2() {

            synchronized (lock2) {

                return cont2;

            }

        }

    }
}
