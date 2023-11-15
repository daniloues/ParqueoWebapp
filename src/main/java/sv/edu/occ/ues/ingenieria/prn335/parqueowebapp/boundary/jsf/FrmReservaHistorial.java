/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.ReservaHistorial;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoReservaSecuencia;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AbstractDataAccess;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.ReservaHistorialBean;

/**
 *
 * @author pc
 */
@Named
@ViewScoped
public class FrmReservaHistorial extends AbstractFrm<ReservaHistorial> implements Serializable {

    @Inject
    FacesContext fc;
    @Inject
    ReservaHistorialBean rhBean;

    @Inject
    FrmTipoReservaSecuencia frmTipoReservaSecuencia;

    TreeNode raiz;
    TreeNode nodoSeleccionado;
    Long idTipoReservaSecuencia;

    @PostConstruct
    @Override
    public void inicializar() {
        super.inicializar();
        this.raiz = new DefaultTreeNode("Areas", null);
        List<ReservaHistorial> lista = rhBean.findByIdTipoReservaSecuencia(null, 0, 100000000);
        if (lista != null && !lista.isEmpty()) {

            for (ReservaHistorial next : lista) {
                if (next.getIdTipoReservaSecuencia() == null) {
                    this.generarArbol(raiz, next);
                }

            }
        }
    }

    public void generarArbol(TreeNode padre, ReservaHistorial actual) {
        DefaultTreeNode nuevoPadre = new DefaultTreeNode(actual, padre);

        List<ReservaHistorial> hijos = this.rhBean.findByIdTipoReservaSecuencia(actual.getIdReservaHistorial(), 0, 1000000);
        for (ReservaHistorial hijo : hijos) {
            generarArbol(nuevoPadre, hijo);
        }

    }

    @Override
    public AbstractDataAccess<ReservaHistorial> getDataAccess() {
        return rhBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return fc;
    }

    @Override
    public String getIdPorObjeto(ReservaHistorial object) {
        if (object != null && object.getIdReservaHistorial() != null) {
            return object.getIdReservaHistorial().toString();
        }
        return null;
    }

    @Override
    public ReservaHistorial getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdReservaHistorial().toString().equals(id)).collect(Collectors.toList()).get(0);

        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new ReservaHistorial();
        if (this.idTipoReservaSecuencia != null) {
            this.registro.setIdTipoReservaSecuencia(new TipoReservaSecuencia(idTipoReservaSecuencia));

        }
        this.registro.setActivo(true);

    }

    public Long getIdTipoReservaSecuencia() {
        return idTipoReservaSecuencia;
    }

    public void setIdTipoReservaSecuencia(Long idTipoReservaSecuencia) {
        this.idTipoReservaSecuencia = idTipoReservaSecuencia;
    }

    @Override
    public List<ReservaHistorial> cargarDatos(int primero, int tamanio) {
        if (this.idTipoReservaSecuencia != null) {
            return this.rhBean.findByIdTipoReservaSecuencia(idTipoReservaSecuencia, primero, tamanio);
        }
        return Collections.EMPTY_LIST;

    }

    @Override
    public int contar() {
        if (this.idTipoReservaSecuencia != null) {
            return this.rhBean.contarByIdTipoReservaSecuencia(idTipoReservaSecuencia);
        }
        return 0;
    }

}
