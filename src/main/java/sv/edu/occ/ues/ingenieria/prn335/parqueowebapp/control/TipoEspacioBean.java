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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoEspacio;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class TipoEspacioBean extends AbstractDataAccess<TipoEspacio> implements Serializable {

    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public TipoEspacioBean() {
        super(TipoEspacio.class);
    }

    public List<TipoEspacio> findRangeSlow(int first, int pageSize) {

        try {
            Thread.sleep(2000);

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return this.FindRange(first, pageSize);
    }

}
