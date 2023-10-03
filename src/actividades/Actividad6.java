package actividades;

import java.util.ArrayList;
import java.util.List;

public class Actividad6 {

    private static final int PARTIDAS = 1000;
    private static final int MAX_VALUE = 100;
    private static final int FIN_PARTIDA =-2 ;

    public static void main(String[] args) {
        List<Thread> hilos = new ArrayList<>();
        NumeroOculto numeroOculto = new NumeroOculto(PARTIDAS);

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
        private String nombre;
        private int idPartida;
        private NumeroOculto numeroOculto;
        private int partidasGanadas;

        public Hilo(String nombre, NumeroOculto numeroOculto) {
            this.nombre = nombre;
            this.numeroOculto = numeroOculto;
            this.idPartida = 1;
            this.partidasGanadas=0;
        }

        @Override
        public void run() {

            int value;
            int resultado;

            do{

                value = (int)(Math.random()*Actividad6.MAX_VALUE+1);
                resultado = numeroOculto.propuestaNumero(idPartida,value);
                if(resultado==-1) {
                    idPartida= numeroOculto.getId();
                }else if(resultado==1){
                    partidasGanadas++;
                    idPartida= numeroOculto.getId();
                }

            }while(resultado!=FIN_PARTIDA);
            System.out.println(nombre + " he ganado "+ partidasGanadas + " partidas.");
        }
    }

    public static class NumeroOculto {
        private int value;
        private int id;
        private final int MAX_PARTIDAS;

        public NumeroOculto(int MAX_PARTIDAS) {
            this.MAX_PARTIDAS = MAX_PARTIDAS;
            this.value = (int)(Math.random()*Actividad6.MAX_VALUE+1);
            this.id = 1 ;
        }

        public synchronized int propuestaNumero(int idPartida, int num){

            if(id>MAX_PARTIDAS)
                return FIN_PARTIDA;

            if(idPartida!=id)
                return -1;

            if(num==value){
                id++;
                this.value = (int)(Math.random()*Actividad6.MAX_VALUE+1);
                return 1;
            }else{
                return 0;
            }
        }

        public synchronized int getId() {
            return id;
        }
    }

}
