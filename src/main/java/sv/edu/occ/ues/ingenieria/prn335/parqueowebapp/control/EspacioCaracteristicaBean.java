/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.EspacioCaracteristica;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class EspacioCaracteristicaBean extends AbstractDataAccess<EspacioCaracteristica>implements Serializable{

    @PersistenceContext(unitName = "parqueoPU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public EspacioCaracteristicaBean() {
        super(EspacioCaracteristica.class);
    }
    
}
