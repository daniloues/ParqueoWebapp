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
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.ReservaHistorial;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class ReservaHistorialBean extends AbstractDataAccess<ReservaHistorial> implements Serializable {

    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ReservaHistorialBean() {
        super(ReservaHistorial.class);
    }

    public List<ReservaHistorial> findByIdTipoReservaSecuencia(final Long idTipoReservaSecuencia, int primero, int tamanio) {
        if (idTipoReservaSecuencia != null && primero >= 0 && tamanio > 0) {
            if (em != null) {
                Query q = em.createNamedQuery("ReservaHistorial.findByIdTipoReservaSecuencia");
                q.setParameter("idArea", idTipoReservaSecuencia);
                q.setFirstResult(primero);
                q.setMaxResults(tamanio);
                return q.getResultList();
            }
        }
        return Collections.EMPTY_LIST;
    }

    public int contarByIdTipoReservaSecuencia(final Long idTipoReservaSecuencia) {
        if (idTipoReservaSecuencia != null && em != null) {
            Query q = em.createNamedQuery("Espacio.countByIdTipoReservaSecuencia");
            q.setParameter("idArea", idTipoReservaSecuencia);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }

}
