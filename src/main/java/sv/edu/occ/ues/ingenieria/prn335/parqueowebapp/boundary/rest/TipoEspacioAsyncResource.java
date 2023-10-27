/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.rest;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoEspacio;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.TipoEspacioBean;

/**
 *
 * @author alexo
 */
@Path("tipo_espacio_async")
public class TipoEspacioAsyncResource implements Serializable {

    @Inject
    TipoEspacioBean teBean;


//    @Resource
//    ManagedExecutorService mes;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public CompletableFuture<List<TipoEspacio>> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "page_size")
            @DefaultValue(value = "50") int pageSize) {
        return CompletableFuture.supplyAsync(() -> teBean.findRangeSlow(first, pageSize));

        //return teBean.FindRange(first, pageSize);
    }
}
