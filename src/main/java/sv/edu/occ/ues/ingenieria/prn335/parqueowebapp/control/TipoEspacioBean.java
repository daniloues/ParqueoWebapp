/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoEspacio;

/**
 *
 * @author alexo
 */
@Stateless
@Local
public class TipoEspacioBean extends AbstractDataAccess<TipoEspacio> implements Serializable {
    
    @PersistenceContext(unitName = "parqueoPU")
    EntityManager em;
    
//    public void create(TipoEspacio registro) throws IllegalArgumentException, IllegalStateException {
//        
//        if (registro != null) {
//            try {
//                if (em != null) {
//                    em.persist(registro);
//                    return;                    
//                }
//                
//            } catch (Exception ex) {
//                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
//                
//            }
//            throw new IllegalStateException();
//        }
//        throw new IllegalArgumentException();
//    }
    
    
  
    
    
//    public TipoEspacio modify(TipoEspacio registro){
//        if (registro !=null) {
//            if (em !=null) {
//                try {
//                    return em.merge(registro);
//                } catch (Exception ex) {
//                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
//                }
//            }
//            throw new IllegalStateException();
//        }
//        
//    return null;
//    
//    }
    
//    public void delete(TipoEspacio registro) throws IllegalArgumentException, IllegalStateException {
//    if (registro != null) {
//        try {
//            if (em != null) {
//                TipoEspacio entityToRemove = em.find(TipoEspacio.class, registro.getIdTipoEspacio());
//                if (entityToRemove != null) {
//                    em.remove(entityToRemove);
//                    return;
//                }
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
//        }
//        throw new IllegalStateException();
//    }
//    throw new IllegalArgumentException();
//}

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    public TipoEspacioBean(){
    super(TipoEspacio.class);
    }


    
}
