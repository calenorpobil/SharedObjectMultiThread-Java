/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.objetoscompartidosmultihilo;

/**
 *
 * @author Carlos
 */
public class ObjetosCompartidosMultihilo {

    public static void main(String[] args) {
        Contador cont = new Contador(100);
        HiloA a = new HiloA("a", cont);
        HiloB b = new HiloB("b", cont);
        a.start();
        b.start();
        a.setPriority(10);
        b.setPriority(0);
    }
}

class Contador {

    Contador(int c) {
        this.c = c;
    }
    private int c = 0; //atributo contador 

    public void incrementa() {
        c = c + 1;
    }

    public void decrementa() {
        c = c - 1;
    }

    public int getValor() {
        return c;
    }
} // CONTADOR

class HiloB extends Thread {

    private Contador contador;

    public HiloB(String n, Contador c) {
        setName(n);
        contador = c;
    }

    public void run() {
        synchronized (contador) {
            while (true) {
                contador.incrementa();
                try {
                    System.out.println(getName()
                            + " contador vale " + contador.getValor());
                    sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }
} // FIN HILOB

class HiloA extends Thread {

    private Contador contador;

    public HiloA(String n, Contador c) {
        setName(n);
        contador = c;
    }

    public void run() {
        synchronized (contador) {
            while (true) {
                contador.decrementa(); //decrementa el contador
                try {
                    System.out.println(getName()
                            + " contador vale " + contador.getValor());
                    sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }

} // FIN HILOA
