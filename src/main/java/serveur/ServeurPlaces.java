/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import dao.AdherentUtile;
import dao.DocumentUtile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rachad
 * 
 * Ce Thread sera lancer au demarage du systeme pour assurer les
 * communicartions avec les kindles sans interompre les taches de gestion 
 * qui seront relisees par l'admin
 */
public class ServeurPlaces extends Thread {
   
    
  public void run(){
     
  }
      
   

} 
