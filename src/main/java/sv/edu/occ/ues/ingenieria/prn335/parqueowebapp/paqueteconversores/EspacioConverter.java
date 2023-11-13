/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.paqueteconversores;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import java.io.Serializable;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Espacio;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.EspacioBean;

/**
 *
 * @author pc
 */
@FacesConverter(managed = true, value = "espacioConverter")
@RequestScoped
public class EspacioConverter implements Converter<Espacio>, Serializable {

    @Inject
    EspacioBean EspacioBean;

    Espacio Espacio;

    @Override
    public Espacio getAsObject(FacesContext context, UIComponent component, String value) {
         if (value != null && value.trim().length() > 0) {
            try {
                Long id = Long.parseLong(value);
                Espacio = EspacioBean.findById(id);
                return Espacio;
            } catch (Exception e) {
                
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Espacio value) {
        if (value != null) {
            return String.valueOf(((Espacio) value).getIdEspacio());
        } else {
            return null;
        }
    }

}