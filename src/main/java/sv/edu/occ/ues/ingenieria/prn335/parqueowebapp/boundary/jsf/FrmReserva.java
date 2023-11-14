/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Area;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Espacio;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Reserva;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoReserva;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AbstractDataAccess;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AreaBean;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.EspacioBean;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.ReservaBean;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.TipoReservaBean;

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
    @Inject
    TipoReservaBean trBean;
    List<TipoReserva> listaTipoReserva;
    TreeNode raiz;
    TreeNode nodoSeleccionado;

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
        listaTipoReserva = trBean.FindRange(0, 1000000000);
    }

    public void generarArbol(TreeNode padre, Area actual) {
        DefaultTreeNode nuevoPadre = new DefaultTreeNode(actual, padre);

        List<Area> hijos = this.aBean.findByIdPadre(actual.getIdArea(), 0, 100000000);
        for (Area hijo : hijos) {
            generarArbol(nuevoPadre, hijo);
        }

    }

    public List<TipoReserva> getListaTipoReserva() {
        return listaTipoReserva;
    }

    public void setListaTipoReserva(List<TipoReserva> listaTipoReserva) {
        this.listaTipoReserva = listaTipoReserva;
    }

    public TreeNode getRaiz() {
        return raiz;
    }

    public void setRaiz(TreeNode raiz) {
        this.raiz = raiz;
    }

    public TreeNode getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    public void setNodoSeleccionado(TreeNode nodoSeleccionado) {
        this.nodoSeleccionado = nodoSeleccionado;
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

    public void cambiarFechaDesde(AjaxBehaviorEvent event) {
        this.registro.setDesde((Date) ((UIOutput) event.getSource()).getValue());
        System.out.println(registro.getDesde());

    }

    public void validate(FacesContext context, UIComponent component, Object value) {
        //
    }

}
