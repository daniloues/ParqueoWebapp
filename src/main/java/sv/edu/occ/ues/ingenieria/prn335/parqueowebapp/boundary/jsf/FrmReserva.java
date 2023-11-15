/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.model.SelectItem;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Espacio;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Reserva;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoReserva;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AbstractDataAccess;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AreaBean;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Area;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoEspacio;

import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.EspacioBean;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.ReservaBean;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.TipoEspacioBean;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.TipoReservaBean;

/**
 *
 * @author alexo
 */
@Named
@ViewScoped
public class FrmReserva extends AbstractFrm<Reserva> implements Serializable {

    @Inject
    ReservaBean rbean;
    @Inject
    TipoReservaBean trbean;
    @Inject
    AreaBean aBean;
    @Inject
    EspacioBean eBean;
    @Inject
    TipoEspacioBean teBean;
    @Inject
    FacesContext Fc;
    @Inject
    FrmEspacio frmEspacio;
    List<TipoReserva> listaTipoReserva;

    Area areaE;

    TreeNode raiz;
    TreeNode nodoSeleccionado;
    List<Espacio> espaciosDisponibles;
    List<TipoEspacio> caractaristicasDisponibles;
    List<TipoEspacio> caracteristicasSeleccionadas;
    List<SelectItem> caracteristicasDisponiblesAsItems;

    @Override
    public void instanciarRegistro() {
        this.registro = new Reserva();
        listaTipoReserva = trbean.FindRange(0, 10000000);
    }

    @PostConstruct
    @Override
    public void inicializar() {
        super.inicializar();
        this.raiz = new DefaultTreeNode("Areas", null);
        List<Area> lista = aBean.findByIdPadre(null, 0, 100000000);
        if (lista != null && !lista.isEmpty()) {

            for (Area next : lista) {
                if (next.getIdAreaPadre() == null) {
                    this.generarArbol(raiz, next);
                }

            }
        }
    }

    public void generarArbol(TreeNode padre, Area actual) {
        DefaultTreeNode nuevoPadre = new DefaultTreeNode(actual, padre);

        List<Area> hijos = this.aBean.findByIdPadre(actual.getIdArea(), 0, 1000000000);
        for (Area hijo : hijos) {
            generarArbol(nuevoPadre, hijo);
        }
    }

    public void seleccionarNodoListener(NodeSelectEvent nse) {
        Area area = (Area) nse.getTreeNode().getData();
        this.seleccionarRegistro();
        System.out.println("selecionaste " + (Area) nse.getTreeNode().getData());
        if (this.areaE != null && this.areaE.getIdArea() != null && this.frmEspacio != null) {
            this.frmEspacio.setIdArea(areaE.getIdArea());
        }

        espaciosDisponibles = eBean.findByIdArea(area.getIdArea(), 0, 10000);
        caractaristicasDisponibles = teBean.FindRange(0, 100000);

        // Lista de objetos SelectItem que representan las opciones disponibles
        List<SelectItem> items = new ArrayList<>();

        for (TipoEspacio caracteristica : caractaristicasDisponibles) {
            items.add(new SelectItem(caracteristica, caracteristica.getNombre()));
        }

        setCaracteristicasDisponiblesAsItems(items);

    }

    @Override
    public AbstractDataAccess<Reserva> getDataAccess() {
        return rbean;
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

    public List<Espacio> getEspaciosDisponibles() {

        if (espaciosDisponibles != null) {
            Date desde = this.registro.getDesde();
            Date hasta = this.registro.getHasta();
            boolean activo = false;
            List<Espacio> espacio1 = new ArrayList<>();
            List<Espacio> espacios = espaciosDisponibles;
            for (int i = 0; i < espacios.size(); i++) {
                Espacio eP = espacios.get(i);
                eP.getReservaList();

                List<Reserva> reserva = eP.getReservaList();
                for (int j = 0; j < reserva.size(); j++) {
                    Reserva rs = reserva.get(j);

                    if (rs.getDesde().getTime() >= desde.getTime() && rs.getHasta().getTime() <= hasta.getTime()) {
                        // throw new ValidatorException(new FacesMessage("La fecha ya esta reservada"));
                        activo = false;
                        break;
                    }
                    activo = true;
                }
                if (activo == true) {
                    espacio1.add(eP);
                }
            }
            espaciosDisponibles = espacio1;
        }

        return espaciosDisponibles;
    }

    public void setEspaciosDisponibles(List<Espacio> espaciosDisponibles) {
        this.espaciosDisponibles = espaciosDisponibles;
    }

    public List<TipoEspacio> getCaractaristicasDisponibles() {
        return caractaristicasDisponibles;
    }

    public void setCaractaristicasDisponibles(List<TipoEspacio> caractaristicasDisponibles) {
        this.caractaristicasDisponibles = caractaristicasDisponibles;
    }

    public List<TipoEspacio> getCaracteristicasSeleccionadas() {
        return caracteristicasSeleccionadas;
    }

    public void setCaracteristicasSeleccionadas(List<TipoEspacio> caracteristicasSeleccionadas) {
        this.caracteristicasSeleccionadas = caracteristicasSeleccionadas;
    }

    public List<SelectItem> getCaracteristicasDisponiblesAsItems() {
        return caracteristicasDisponiblesAsItems;
    }

    public void setCaracteristicasDisponiblesAsItems(List<SelectItem> caracteristicasDisponiblesAsItems) {
        this.caracteristicasDisponiblesAsItems = caracteristicasDisponiblesAsItems;
    }

    public String generarPathArea(long idEspacio) {

        Espacio espacio = eBean.findById(idEspacio);

        if (espacio != null) {
            Area areaPadre = espacio.getIdArea().getIdAreaPadre();
            Area area = espacio.getIdArea();
            if (areaPadre != null) {
                return "Espacio: " + espacio.getNombre() + " Areas:" + areaPadre.getNombre() + "/" + area.getNombre();
            } else if (area != null) {
                return "Espacio: " + espacio.getNombre() + " Areas: " + area.getNombre();
            }
        }
        return "";
    }

    public void cambiarFechaDesde(AjaxBehaviorEvent event) {
        this.registro.setDesde((Date) ((UIOutput) event.getSource()).getValue());
        System.out.println(registro.getDesde());
    }

    private boolean espacioDesbloqueado;

    public boolean isEspacioDesbloqueado() {
        return espacioDesbloqueado;
    }

    public void setEspacioDesbloqueado(boolean espacioDesbloqueado) {
        this.espacioDesbloqueado = espacioDesbloqueado;
    }

    public void desbloquearEspacio() {
        espacioDesbloqueado = true;
    }

    // ... (otros métodos)
    public boolean validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // Obtiene la fecha seleccionada
        Date fechaSeleccionada = (Date) value;

        Date fechaAhora = new Date();

        if (this.registro.getDesde().getTime() > fechaAhora.getTime()) {
            if (fechaSeleccionada != null && !this.registro.getDesde().after(fechaSeleccionada)) {
                //System.out.print(fechaSeleccionada + "/" + this.registro.getDesde());
                return true;
            }
        }

        fechaAhora.getTime();
        throw new ValidatorException(new FacesMessage("La fecha debe ser posterior a la fecha actual"));
    }

// Agregar el siguiente atributo y método
    private boolean fechaValida;

    public boolean isFechaValida() {
        return fechaValida;
    }

    @Override
    public FacesContext getFacesContext() {
        return Fc;
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
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdReserva().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    public void refinarBusquedaNodo() {

    }
}
