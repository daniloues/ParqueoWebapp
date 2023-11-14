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



}
