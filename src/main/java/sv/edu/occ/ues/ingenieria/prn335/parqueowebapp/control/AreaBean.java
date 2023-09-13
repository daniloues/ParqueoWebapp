/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Area;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoReserva;

/**
 *
 * @author alexo
 */
public class AreaBean extends AbstractDataAccess<Area> implements Serializable{
    

   @PersistenceContext(unitName = "parqueoPU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public AreaBean() {
        super(Area.class);
    }
    
    public List<Area> findByIdPadre(Integer idPadre, int first, int pageSize) {
        if (idPadre != null && em != null) {
            Query q = em.createNamedQuery("Area.findByIdPadre");
            q.setFirstResult(first);
            q.setMaxResults(pageSize);
            q.setParameter("idPersona", idPadre);
            return q.getResultList();
        }
        return Collections.EMPTY_LIST;
    }
    
}
