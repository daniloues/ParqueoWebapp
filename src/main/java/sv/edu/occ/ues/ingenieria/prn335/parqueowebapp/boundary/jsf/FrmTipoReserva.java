/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf;

import jakarta.annotation.PostConstruct;
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
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoReserva;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.TipoReservaBean;

/**
 *
 * @author alexo
 */
@Named
@ViewScoped
public class FrmTipoReserva implements Serializable {

    @Inject
    TipoReservaBean trBean;

    LazyDataModel<TipoReserva> modelo;

    EstadosCRUD estado = EstadosCRUD.NINGUNO;

    TipoReserva regis = null;

    public FrmTipoReserva() {

    }

    @PostConstruct
    public void inicializar() {
        inicializarRegistros();

    }

    public void inicializarRegistros() {

        this.modelo = new LazyDataModel<TipoReserva>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                return trBean.count();
            }

            @Override
            public List<TipoReserva> load(int primero, int tamanio, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                return trBean.FindRange(primero, tamanio);
            }

            @Override
            public String getRowKey(TipoReserva object) {
                if (object != null && object.getIdTipoReserva() != null) {
                    return object.getIdTipoReserva().toString();
                }
                return null;
            }

            @Override
            public TipoReserva getRowData(String rowKey) {
                if (rowKey != null) {
                    return this.getWrappedData().stream().filter(r -> r.getIdTipoReserva().toString().equals(rowKey))
                            .collect(Collectors.toList()).get(0);

                }
                return null;
            }

        };

    }

    public void seleccionarRegistro() {
        this.estado = EstadosCRUD.MODIFICAR;

    }

    public void btnNuevoHandler(ActionEvent ae) {
        this.regis = new TipoReserva();
        this.estado = EstadosCRUD.NUEVO;

    }

    public void btnCancelarHandler(ActionEvent ae) {
        this.regis = null;
        this.estado = EstadosCRUD.NINGUNO;
    }

    public void btnModificarHandler(ActionEvent ae) {
        TipoReserva modify = null;
        try {
            modify = this.trBean.modify(regis);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        if (modify != null) {
            //TODO:notificar que se modifico
            this.estado = EstadosCRUD.NINGUNO;
            this.regis = null;
            return;

        }
        //TODO:notificar que no se cambio
    }

    public void btnEliminarHandler(ActionEvent ae) {
        try {
            this.trBean.delete(regis);
            this.estado = EstadosCRUD.NINGUNO;
            return;
            //TODO: enviar mensaje de exito
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        //TODO:notificar que no se elimino
    }

    public void btnGuardarHandler(ActionEvent ae) {
        try {
            this.trBean.create(regis);
            this.estado = EstadosCRUD.NINGUNO;
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        this.regis = null;

    }

    public TipoReserva getRegis() {
        return regis;
    }

    public void setRegis(TipoReserva regis) {
        this.regis = regis;
    }

    public LazyDataModel<TipoReserva> getModelo() {
        return this.modelo;
    }

    public EstadosCRUD getEstado() {
        return estado;
    }

}
