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
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
    TipoReservaBean trBean;

    @Override
    public TipoReserva getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return trBean.findById(Integer.valueOf(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, TipoReserva value) {
        if (esTipoReservaValido(value)) {
            return obtenerIdComoCadena(value);
        }
        return null;
    }

    private boolean esTipoReservaValido(TipoReserva tipoReserva) {
        return tipoReserva != null && tipoReserva.getIdTipoReserva() != null;
    }

    private String obtenerIdComoCadena(TipoReserva tipoReserva) {
        return tipoReserva.getIdTipoReserva().toString();
    }
}
