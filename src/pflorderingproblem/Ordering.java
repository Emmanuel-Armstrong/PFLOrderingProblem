///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pflorderingproblem;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Emmanuel
// */
//public class Ordering extends Thread{
//    Package in;
//    Customer customer;
//    OrderingListener listener;
//
//    public Ordering(Package in, Customer customer, OrderingListener listener) {
//        this.in = in;
//        this.customer = customer;
//        this.listener = listener;
//    }
//  
//    public void run(){
////        try {
////            Thread.sleep((long) in.getProductionDays() * 1000);
////        } catch (InterruptedException ex) {
////            Logger.getLogger(Ordering.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        listener.packageOrdered(in, customer);
//        
//    }    
//    
//}
