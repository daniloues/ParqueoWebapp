/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Area;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoReserva;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AbstractDataAccess;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.TipoReservaBean;

/**
 *
 * @author alexo
 */
@Named
@ViewScoped
public class FrmTipoReserva extends AbstractFrm<TipoReserva> implements Serializable {

    @Inject
    TipoReservaBean trBean;
    @Inject
    FrmTipoReservaSecuencia trsBean;
    @Inject
    FacesContext fc;

    @Override
    public AbstractDataAccess<TipoReserva> getDataAccess() {
        return this.trBean;

    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdPorObjeto(TipoReserva object) {
        if (object != null && object.getIdTipoReserva() != null) {
            return object.getIdTipoReserva().toString();
        }
        return null;
    }

    @Override
    public TipoReserva getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdTipoReserva().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;

    }

    @Override
    public void instanciarRegistro() {
        this.registro = new TipoReserva();

    }

    public FrmTipoReservaSecuencia getTrsBean() {
        return trsBean;
    }

    public void setTrsBean(FrmTipoReservaSecuencia trsBean) {
        this.trsBean = trsBean;
    }

}
