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
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AbstractDataAccess;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AreaBean;

/**
 *
 * @author alexo
 */
@Named
@ViewScoped
public class FrmArea extends AbstractFrm<Area> implements Serializable {

    @Inject
    FrmEspacio frmEspacio;
    @Inject
    FacesContext fc;
    @Inject
            
    AreaBean aBean;
    TreeNode raiz;
    TreeNode nodoSeleccionado;

    @PostConstruct
    @Override
    public void inicializar() {
        super.inicializar();
        this.raiz = new DefaultTreeNode("Areas", null);
        List<Area> lista = aBean.findRaices(0, 1000000000);
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

    @Override
    public AbstractDataAccess<Area> getDataAccess() {
        return aBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return fc;
    }

    @Override
    public String getIdPorObjeto(Area object) {

        if (object != null && object.getIdArea() != null) {
            return object.getIdArea().toString();
        }
        return null;
    }

    @Override
    public Area getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return modelo.getWrappedData().stream().filter(r -> r.getIdArea().toString().equals(id)).collect(Collectors.toList()).get(0);

        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        Area padre = this.regis;
        this.regis = new Area();
        this.regis.setIdAreaPadre(padre);
    }

    @Override
    public List<Area> cargarDatos(int primero, int tamanio) {
        Integer idPadre = null;
        if (this.regis != null) {
            idPadre = regis.getIdArea();
        }
        List<Area> lista = aBean.findByIdPadre(idPadre, 0, 10000000);
        return lista;
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
        this.regis = (Area) nse.getTreeNode().getData();
        this.seleccionarRegistro();
        if (this.regis != null && this.regis.getIdArea() != null && this.frmEspacio != null) {
            this.frmEspacio.setIdArea(this.regis.getIdArea());
        }
    }

    public FrmEspacio getFrmEspacio() {
        return frmEspacio;
    }
    

}
