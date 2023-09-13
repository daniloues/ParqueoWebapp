/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Area;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoEspacio;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AbstractDataAccess;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.TipoEspacioBean;

/**
 *
 * @author alexo
 */
@Named
@ViewScoped
public class FrmTipoEspacio extends AbstractFrm<TipoEspacio> implements Serializable {

    @Inject
    FacesContext fc;

    @Inject
    TipoEspacioBean teBean;

    @Override
    public AbstractDataAccess<TipoEspacio> getDataAccess() {
        return this.teBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdPorObjeto(TipoEspacio object) {
        if (object != null && object.getIdTipoEspacio() != null) {
            return object.getIdTipoEspacio().toString();
        }
        return null;
    }

    @Override
    public TipoEspacio getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdTipoEspacio().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {

        this.regis = new TipoEspacio();
    }

  

}
