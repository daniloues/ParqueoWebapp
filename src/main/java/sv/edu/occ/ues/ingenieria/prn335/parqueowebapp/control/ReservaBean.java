/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Espacio;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Reserva;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class ReservaBean extends AbstractDataAccess<Reserva> implements Serializable {

    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ReservaBean() {
        super(Reserva.class);
    }

    public List<Espacio> listarEspaciosDisponibles(Long idTipoReserva, LocalDateTime desde) {
        return em.createQuery("SELECT e FROM Espacio e WHERE e.idArea.idArea IN :idsArea AND e.activo = true AND e.idEspacio NOT IN (SELECT r.idEspacio.idEspacio FROM Reserva r WHERE r.desde >= :desde AND r.hasta <= :hasta)", Espacio.class)
                .setParameter("idsArea", Arrays.asList(1, 2, 3, 4, 5))
                .setParameter("desde", desde)
                .setParameter("hasta", desde.plusMinutes(30))
                .getResultList();
    }

}
