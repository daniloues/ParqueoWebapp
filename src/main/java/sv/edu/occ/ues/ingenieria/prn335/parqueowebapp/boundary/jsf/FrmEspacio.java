/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf;

import jakarta.enterprise.context.Dependent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Area;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Espacio;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AbstractDataAccess;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.EspacioBean;

/**
 *
 * @author alexo
 */
@Named
@Dependent
public class FrmEspacio extends AbstractFrm<Espacio> implements Serializable {

    @Inject
    FacesContext fc;
    @Inject
    EspacioBean eBean;
    Integer idArea;

    @Override
    public AbstractDataAccess<Espacio> getDataAccess() {
        return eBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return fc;
    }

    @Override
    public String getIdPorObjeto(Espacio object) {
        if (object != null && object.getIdEspacio() != null) {
            return object.getIdEspacio().toString();
        }
        return null;
    }

    @Override
    public Espacio getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdEspacio().toString().equals(id)).collect(Collectors.toList()).get(0);

        }
        return null;

    }

    @Override
    public void instanciarRegistro() {
        this.regis = new Espacio();
        if (this.idArea != null) {
            this.regis.setIdArea(new Area(idArea));

        }
        this.regis.setActivo(true);

    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    @Override
    public List<Espacio> cargarDatos(int primero, int tamanio) {
        if (this.idArea != null) {
            return this.eBean.findByIdArea(idArea, primero , tamanio);

        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public int contar() {
        if (this.idArea != null) {
            return this.eBean.contarByIdArea(idArea);
        }
        return 0;
    }

}
