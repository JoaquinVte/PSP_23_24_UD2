package ejemplos;

public class TransferenciaCorregido {

    // Independientemente como asignemos las cuentas,
    // siempre se bloqueara primero la que tenga un numero inferior,
    // con lo que eviatamos el interbloqueo entre los hilos
    public static void main(String[] args) {

        Cuenta c1,c2;
        c1 = new Cuenta();
        c2 = new Cuenta();

        Thread t1 = new Thread(new Hilo(c1,c2));
        Thread t2 = new Thread(new Hilo(c2,c1));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("C1: " + c1.saldo);
        System.out.println("C2: " + c2.saldo);

    }



    static class Hilo implements Runnable {

        private Cuenta c1;
        private Cuenta c2;

        public Hilo(Cuenta c1,Cuenta c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        @Override
        public void run() {
            for(int i=0;i<1000000;i++){
                transaccion(c1,c2,1);
            }
        }

        private void transaccion(Cuenta c1, Cuenta c2, int i) {

            if(c1.numCuenta>c2.numCuenta){
                synchronized (c2) {
                    synchronized (c1) {
                        c1.sacar(1);
                        c2.incrementa(1);
                    }
                }
            }else {
                synchronized (c1) {
                    synchronized (c2) {
                        c1.sacar(1);
                        c2.incrementa(1);
                    }
                }
            }
        }
    }

    static class Cuenta {

        static int actual = 0;
        private int saldo;
        private int numCuenta;

        public Cuenta() {
            saldo = 1000000;
            numCuenta = actual++;
        }

        public int getNumCuenta() {
            return numCuenta;
        }

        public void incrementa(int cantidad){
            saldo+=cantidad;
        }

        public void sacar(int cantidad){
            saldo-=cantidad;
        }
    }
}
