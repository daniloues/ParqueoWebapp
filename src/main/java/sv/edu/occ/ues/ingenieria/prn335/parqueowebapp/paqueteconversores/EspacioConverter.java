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
@FacesConverter("espacioConverter")
public class EspacioConverter implements Converter<Espacio> {

    @Inject
    EspacioBean espacioBean;

    @Override
    public Espacio getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                Long id = Long.parseLong(value);
                return espacioBean.findById(id);
            } catch (NumberFormatException e) {
                // Log the exception or handle it as needed
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Espacio value) {
        if (value != null && value.getIdEspacio() != null) {
            return value.getIdEspacio().toString();
        }
        return null;
    }
}
