/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author alexo
 */
@Named
@ViewScoped
public class FrmNotificarEspacio implements  Serializable{
 
    public String getEspaciosLibres(){
        //implementar logica para descubrir el espacio libre
        return String.valueOf(System.currentTimeMillis());
    }
    
}
