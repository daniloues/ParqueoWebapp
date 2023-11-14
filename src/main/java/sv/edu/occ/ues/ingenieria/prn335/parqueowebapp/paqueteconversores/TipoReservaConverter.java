/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.paqueteconversores;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf.*;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.io.Serializable;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoReserva;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.TipoReservaBean;

/**
 *
 * @author pc
 */
@FacesConverter(managed = true, value = "tipoReservaConverter")
@RequestScoped
public class TipoReservaConverter implements Converter<TipoReserva>, Serializable {

    @Inject
    TipoReservaBean tipoRervaBean;

    @Override
    public TipoReserva getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            TipoReserva salida = tipoRervaBean.findById(Integer.valueOf(value));
            return salida;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, TipoReserva value) {
        if (value != null && value.getIdTipoReserva() != null) {
            return value.getIdTipoReserva().toString();
        }
        return null;
    }
}
