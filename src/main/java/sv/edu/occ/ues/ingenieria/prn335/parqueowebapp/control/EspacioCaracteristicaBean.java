/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.EspacioCaracteristica;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class EspacioCaracteristicaBean extends AbstractDataAccess<EspacioCaracteristica>implements Serializable{

    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public EspacioCaracteristicaBean() {
        super(EspacioCaracteristica.class);
    }
    
    public List<EspacioCaracteristica> findEspacioCaracteristicasByEspacio(int espacioId) {
        if (espacioId > 0 && em != null) {
            Query query = em.createNamedQuery("EspacioCaracteristica.findByEspacio");
            query.setParameter("idEspacio", espacioId);
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    public int countByEspacio(int espacioId) {
        if (espacioId > 0 && em != null) {
            Query query = em.createNamedQuery("EspacioCaracteristica.countByEspacio");
            query.setParameter("idEspacio", espacioId);
            return ((Long) query.getSingleResult()).intValue();
        }
        return 0;
    }
    
}
