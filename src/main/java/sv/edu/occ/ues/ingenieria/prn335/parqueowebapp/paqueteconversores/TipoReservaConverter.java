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
@FacesConverter("tipoReservaConverter")
public class TipoReservaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            return component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object obj) {
        if (obj != null && !"".equals(obj)) {
            String id;
            try {
                id = this.getId(getClazz(ctx, component), obj);
                if (id == null) {
                    id = "";
                }
                id = id.trim();
                component.getAttributes().put(id, getClazz(ctx, component).cast(obj));
                return id;
            } catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace(); // seu log aqui
            }
        }
        return null;
    }

    private Class<?> getClazz(FacesContext facesContext, UIComponent component) {
        return component.getValueExpression("value").getType(facesContext.getELContext());
    }

    public String getId(Class<?> clazz, Object obj) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        List<Class<?>> hierarquiaDeClasses = this.getHierarquiaDeClasses(clazz);

        for (Class<?> classeDaHierarquia : hierarquiaDeClasses) {
            for (Field field : classeDaHierarquia.getDeclaredFields()) {
                if ((field.getAnnotation(Id.class)) != null || field.getAnnotation(EmbeddedId.class) != null) {
                    Field privateField = classeDaHierarquia.getDeclaredField(field.getName());
                    privateField.setAccessible(true);
                    if (privateField.get(clazz.cast(obj)) != null) {
                        return (String) field.getType().cast(privateField.get(clazz.cast(obj))).toString();
                    }
                }
            }
        }
        return null;
    }

    public List<Class<?>> getHierarquiaDeClasses(Class<?> clazz) {

        List<Class<?>> hierarquiaDeClasses = new ArrayList<>();
        Class<?> classeNaHierarquia = clazz;
        while (classeNaHierarquia != Object.class) {
            hierarquiaDeClasses.add(classeNaHierarquia);
            classeNaHierarquia = classeNaHierarquia.getSuperclass();

        }
        return hierarquiaDeClasses;
    }
}
