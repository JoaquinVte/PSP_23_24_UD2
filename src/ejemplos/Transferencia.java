package ejemplos;

public class Transferencia {


    class Hilo implements Runnable {

        private Cuenta c1;
        private Cuenta c2;

        public Hilo(Cuenta c1,Cuenta c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        @Override
        public void run() {
            for(int i=0;i<1000000;i++){
                c1.sacar(1);
                c2.incrementa(1);
            }
        }
    }

    class Cuenta {
        private int saldo;

        public Cuenta() {
            saldo = 1000000;
        }

        public void incrementa(int cantidad){
            saldo+=cantidad;
        }

        public void sacar(int cantidad){
            saldo-=cantidad;
        }
    }
}
