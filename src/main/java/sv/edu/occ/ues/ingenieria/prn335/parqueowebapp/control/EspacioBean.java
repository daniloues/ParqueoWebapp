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
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Area;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Espacio;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class EspacioBean extends AbstractDataAccess<Espacio> implements Serializable {

    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;

    public EspacioBean() {
        super(Espacio.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    public List<Espacio> findByIdArea(final Integer idArea, int primero, int tamanio) {
        if (idArea != null && primero >= 0 && tamanio > 0) {
            if (em != null) {
                Query q = em.createNamedQuery("Espacio.findByIdArea");
                q.setParameter("idArea", idArea);
                q.setFirstResult(primero);
                q.setMaxResults(tamanio);
                return q.getResultList();

            }  

        }
        return Collections.EMPTY_LIST;
    }

    public int contarByIdArea(final Integer idArea) {
        if (idArea != null && em != null) {
            Query q = em.createNamedQuery("Espacio.countByIdArea");
            q.setParameter("idArea", idArea);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }
    //AGREGADO PARA PRUEBAS
    public List<Espacio> findByTipoEspacioAndValorCarro(int primero, int tamanio) {
    if (primero >= 0 && tamanio > 0) {
        if (em != null) {
            TypedQuery<Espacio> q = em.createNamedQuery("Espacio.findByTipoEspacioAndValorCarro", Espacio.class);
            q.setFirstResult(primero);
            q.setMaxResults(tamanio);
            return q.getResultList();
        }
    }
    return Collections.emptyList();
}


}
