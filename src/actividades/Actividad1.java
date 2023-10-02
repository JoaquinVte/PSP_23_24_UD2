package actividades;

public class Actividad1 {
    // Escribe un programa que obtenga el objeto Thread
    // correspondiente al hilo actual de ejecución y
    // muestre información acerca de él.
    // Este programa no tiene que crear ningún hilo,
    // solo mostrar la siguiente información sobre el hilo
    // actualmente en ejecución: nombre, id, prioridad, si es un demonio.

    public static void main(String[] args){

        Thread thread = Thread.currentThread();

        System.out.println(thread.getName());
        System.out.println(thread.getId());
        System.out.println(thread.getPriority());
        System.out.println(thread.isDaemon());

        System.out.println("Max priority:" + Thread.MAX_PRIORITY);
        System.out.println("Min priority:" + Thread.MIN_PRIORITY);
        System.out.println("Normal priority:" + Thread.NORM_PRIORITY);
    }
}
