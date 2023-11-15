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
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoReservaSecuencia;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class TipoReservaSecuenciaBean extends AbstractDataAccess<TipoReservaSecuencia> implements Serializable {

    @PersistenceContext(name = "ParquePU")
    EntityManager em;

    public TipoReservaSecuenciaBean() {
        super(TipoReservaSecuencia.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public List<TipoReservaSecuencia> findByIdPadre(final Long idTipoReservaSecuenciaPadre, int first, int pageSize) {
        if (first >= 0 && pageSize > 0 && em != null) {
            Query q;

            if (idTipoReservaSecuenciaPadre == null) {
                q = em.createNamedQuery("TipoReservaSecuencia.findRaices");
            } else {

                q = em.createNamedQuery("TipoReservaSecuencia.findByIdTipoReservaSecuenciaPadre");
                q.setParameter("IdTipoReservaSecuenciaPadre", idTipoReservaSecuenciaPadre);
            }

            q.setFirstResult(first);
            q.setMaxResults(pageSize);
            return q.getResultList();
        }
        return Collections.EMPTY_LIST;
    }

    public int countByIdPadre(Integer idTipoReservaSecuenciaPadre) {
        if (idTipoReservaSecuenciaPadre != null) {
            if (em != null) {
                Query q = em.createNamedQuery("Area.countByIdArea");
                q.setParameter("IdTipoReservaSecuenciaPadre", idTipoReservaSecuenciaPadre);
                return ((Long) q.getSingleResult()).intValue();
            }
        }
        return 0;
    }
}
