package actividades;

import java.util.ArrayList;
import java.util.List;

public class Actividad5 {

    public static void main(String[] args) {
        List<Thread> hilos = new ArrayList<>();
        NumeroOculto numeroOculto = new NumeroOculto();

        for(int i=1;i<=10;i++)
            hilos.add(new Thread(new Hilo("h"+i,numeroOculto)));

        for(Thread t : hilos)
            t.start();

        try {
            for (Thread t : hilos)
                t.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static class Hilo implements Runnable {

        private NumeroOculto numeroOculto;
        private String nombre;

        public Hilo(String nombre,NumeroOculto numeroOculto) {
            this.nombre=nombre;
            this.numeroOculto = numeroOculto;
        }

        @Override
        public void run() {
            int value;
            int resultado;

            do{
                value = (int)(Math.random()*101);
                resultado = numeroOculto.propuestaNumero(value);
            } while (resultado==0);

            if(resultado==1)
                System.out.println("Soy " + nombre + ", y he ganado!!!" );
            else
                System.out.println("Soy " + nombre + ", y he perdido");
        }
    }


    public static class NumeroOculto {
        int value;
        boolean adivinado;
        public NumeroOculto() {
            value = (int)(Math.random()*101);
            adivinado = false;
        }
        public int propuestaNumero(int num){

            if(adivinado)
                return -1;

            if(num==value){
                adivinado=true;
                return 1;
            }else{
                return 0;
            }

        }

        public int getValue() {
            return value;
        }
    }
}
