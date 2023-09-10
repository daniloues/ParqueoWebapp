/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoReserva;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.AbstractDataAccess;

/**
 *
 * @author alexo
 */
public abstract class AbstractFrm<T> implements Serializable {

    public abstract AbstractDataAccess<T> getDataAccess();

    LazyDataModel<T> modelo;

    EstadosCRUD estado = EstadosCRUD.NINGUNO;

    T registro = null;

    public abstract FacesContext getFacesContext();

    @PostConstruct
    public void inicializar() {
        inicializarRegistros();

    }

    public void inicializarRegistros() {

        this.modelo = new LazyDataModel<T>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                int resultado=0;
                AbstractDataAccess<T> trBean = null;
                try {
                    trBean=getDataAccess();
                    resultado = trBean.count();
                } catch (Exception e) {
                    
                }
                        
                
                return resultado;
            }

            @Override
            public List<T> load(int primero, int tamanio, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                
                List<TipoReserva> resultado = trBean.FindRange(primero, tamanio);
                return resultado;
            }

            @Override
            public String getRowKey(T object) {
                if (object != null && object.getIdTipoReserva() != null) {
                    return object.getIdTipoReserva().toString();
                }
                return null;
            }

            @Override
            public T getRowData(String rowKey) {
                if (rowKey != null) {
                    return this.getWrappedData().stream().filter(r -> r.getIdTipoReserva().toString().equals(rowKey))
                            .collect(Collectors.toList()).get(0);

                }
                return null;
            }

        };

    }
}
