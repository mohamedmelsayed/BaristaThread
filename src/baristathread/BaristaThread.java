/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baristathread;

/**
 *
 * @author melsayed
 */
public class BaristaThread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ProcessingThread thread=new ProcessingThread();
        thread.run();
    }
    
}
