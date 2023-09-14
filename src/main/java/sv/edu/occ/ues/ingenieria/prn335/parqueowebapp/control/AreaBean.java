/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control;

import jakarta.ejb.Local;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
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
@Stateless
@LocalBean
public class AreaBean extends AbstractDataAccess<Area> implements Serializable {

    @PersistenceContext(unitName = "parqueoPU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public AreaBean() {
        super(Area.class);
    }

//    public List<Area> findByIdPadre(final Integer idPadre, int primero, int tamanio) {
//        if (idPadre != null && em != null) {
//            Query q = em.createNamedQuery("Area.findByIdPadre");
//            q.setFirstResult(primero);
//            q.setMaxResults(tamanio);
//            q.setParameter("idPadre", idPadre);
//            return q.getResultList();
//        }
//        return Collections.EMPTY_LIST;
//    }
    public List<Area> findByIdPadre(final Integer idPadre, int primero, int tamanio) {
        if (idPadre != null && primero >= 0 && tamanio > 0) {
            if (em != null) {
                Query q = em.createNamedQuery("Area.findByIdPadre");
                q.setParameter("idAreaPadre", idPadre);
                q.setFirstResult(primero);
                q.setMaxResults(tamanio);
                return q.getResultList();

            }  

        }
        return Collections.EMPTY_LIST;
    }

    public int contarByIdPadre(final Integer idPadre) {
        if (idPadre != null && em != null) {
            Query q = em.createNamedQuery("Area.coundByIdPadre");
            q.setParameter("idAreaPadre", idPadre);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }

    public List<Area> findRaices(int primero, int tamanio) {
        if (em != null) {
            Query q = em.createNamedQuery("Area.findRaices");
            q.setFirstResult(primero);
            q.setMaxResults(tamanio);
            return q.getResultList();
        }
        return Collections.EMPTY_LIST;
    }

    //NO SE SI ME FALTA UN METODO MAS O ALGUNA PROPIEDAD QUE EL INGE TIENE EN SU AreaBean QUE YO NO TENGO
    //TAMBIEN PUEDA SER QUE EL METODO QUE TENGO LE FALTEN MAS COSAS
}
