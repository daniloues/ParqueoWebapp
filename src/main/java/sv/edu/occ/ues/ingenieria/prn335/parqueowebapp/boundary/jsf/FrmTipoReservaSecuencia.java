/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.Area;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoReservaSecuencia;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AbstractDataAccess;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.TipoReservaSecuenciaBean;

/**
 *
 * @author alexo
 */
@Named
@ViewScoped
public class FrmTipoReservaSecuencia extends AbstractFrm<TipoReservaSecuencia> implements Serializable {

    @Inject
    TipoReservaSecuenciaBean trsBean;

    @Inject
    FacesContext fc;
    
    @Inject
    FrmReservaHistorial frmReservaHistorial;

    TreeNode raiz;
    TreeNode nodoSeleccionado;

    @Override
    public AbstractDataAccess<TipoReservaSecuencia> getDataAccess() {
        return this.trsBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdPorObjeto(TipoReservaSecuencia object) {
        if (object != null && object.getIdTipoReservaSecuencia() != null) {
            return object.getIdTipoReservaSecuencia().toString();
        }
        return null;
    }

    @Override
    public TipoReservaSecuencia getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdTipoReservaSecuencia().toString().equals(id))
                    .collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        TipoReservaSecuencia padre = this.registro;
        this.registro = new TipoReservaSecuencia();
        this.registro.setIdTipoReservaSecuenciaPadre(padre);
    }

    @PostConstruct
    @Override
    public void inicializar() {
        super.inicializar();
        this.raiz = new DefaultTreeNode("Areas", null);
        List<TipoReservaSecuencia> lista = trsBean.findByIdPadre(null, 0, 100000000);
        if (lista != null && !lista.isEmpty()) {

            for (TipoReservaSecuencia next : lista) {
                if (next.getIdTipoReservaSecuencia() == null) {
                    this.generarArbol(raiz, next);
                }

            }
        }
    }

    @Override
    public List<TipoReservaSecuencia> cargarDatos(int primero, int tamanio) {
        Long idPadre = null;
        if (this.registro != null) {
            idPadre = registro.getIdTipoReservaSecuencia();
        }
        List<TipoReservaSecuencia> lista = trsBean.findByIdPadre(idPadre, 0, 10000000);
        return lista;

    }

    public void generarArbol(TreeNode padre, TipoReservaSecuencia actual) {
        DefaultTreeNode nuevoPadre = new DefaultTreeNode(actual, padre);
        List<TipoReservaSecuencia> hijos = this.trsBean.findByIdPadre(actual.getIdTipoReservaSecuencia(), 0, 10000000);
        for (TipoReservaSecuencia hijo : hijos) {
            this.generarRamas(nuevoPadre, hijo);
        }
    }

//    
    public void generarRamas(TreeNode raiz, TipoReservaSecuencia actual) {
        DefaultTreeNode nuevoPadre = new DefaultTreeNode(actual, raiz);
        List<TipoReservaSecuencia> hijos = this.trsBean.findByIdPadre(actual.getIdTipoReservaSecuencia(), 0, 100000000);
        for (TipoReservaSecuencia hijo : hijos) {
            generarRamas(nuevoPadre, hijo);
        }
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

    public void seleccionarNodoListener(NodeSelectEvent nse) {
        this.registro = (TipoReservaSecuencia) nse.getTreeNode().getData();
        this.seleccionarRegistro();
        if (this.registro != null && this.registro.getIdTipoReservaSecuencia()!= null && this.frmReservaHistorial != null) {
            this.frmReservaHistorial.setIdTipoReservaSecuencia(this.registro.getIdTipoReservaSecuencia());
        }
    }
    
    public FrmReservaHistorial getFrmReservaHistorial() {
        return frmReservaHistorial;
    }

    
}
