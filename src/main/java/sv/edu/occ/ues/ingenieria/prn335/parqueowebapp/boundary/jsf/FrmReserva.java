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
import java.util.stream.Collectors;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Area;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Espacio;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Reserva;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AbstractDataAccess;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AreaBean;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.EspacioBean;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.ReservaBean;

/**
 *
 * @author alexo
 */
@Named
@ViewScoped
public class FrmReserva extends AbstractFrm<Reserva> implements Serializable {

    @Inject
    FacesContext fc;
    @Inject
    ReservaBean rBean;

    @Inject
    EspacioBean eBean;
    @Inject
    AreaBean aBean;

    @Override
    public AbstractDataAccess<Reserva> getDataAccess() {
        return rBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return fc;
    }

    @Override
    public String getIdPorObjeto(Reserva object) {
        if (object != null && object.getIdReserva() != null) {
            return object.getIdReserva().toString();
        }
        return null;
    }

    @Override
    public Reserva getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdReserva().toString().equals(id)).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new Reserva();

    }

    public String generarPathArea(Long id) {

        String txtSalida = "Espacio: ";

        Espacio espacioTabla = eBean.findById(id);
        txtSalida = txtSalida + espacioTabla.getNombre();

        Area espacioArea = aBean.findById(espacioTabla.getIdArea().getIdArea());
        txtSalida = txtSalida + ", Area: ";
        txtSalida = txtSalida + espacioArea.getNombre();

        boolean signal = true;
        do {
            if (espacioArea.getIdAreaPadre() != null) {
                espacioArea = aBean.findById(espacioArea.getIdAreaPadre().getIdArea());
                txtSalida = txtSalida + "/";
                txtSalida = txtSalida + espacioArea.getNombre();
            } else {
                signal = false;
            }

        } while (signal);

        return txtSalida;
    }
}
