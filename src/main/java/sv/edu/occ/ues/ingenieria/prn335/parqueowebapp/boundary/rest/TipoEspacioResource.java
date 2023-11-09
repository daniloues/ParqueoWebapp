/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoEspacio;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.TipoEspacioBean;

/**
 *
 * @author alexo
 */
@Path("tipo_espacio")
public class TipoEspacioResource {

    @Inject
    TipoEspacioBean teBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<TipoEspacio> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "page_size")
            @DefaultValue(value = "50") int pageSize) {

        return teBean.FindRange(first, pageSize);

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response findById(
            @PathParam(value = "id")
            final Integer idTipoEspacio) {
        if (idTipoEspacio != null) {

            TipoEspacio encontrado = teBean.findById(idTipoEspacio);
            if (encontrado != null) {
                return Response.status(Response.Status.OK)
                        .entity(encontrado)
                        .build();
            }

            return Response.status(Response.Status.NOT_FOUND)
                    .header("not-found", "id")
                    .build();
        }

        return Response.status(422).
                header("missing-parameter", "id")
                .build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(TipoEspacio registro,
            @Context UriInfo info
    ) {
        if (registro != null && registro.getIdTipoEspacio() != null && registro.getNombre() != null) {

            try {
                teBean.create(registro);
                URI requestUri = info.getRequestUri();

                return Response.status(Response.Status.CREATED)
                        .header("location",
                                requestUri.toString() + "/"
                                + registro.getIdTipoEspacio())
                        .build();

            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }

            return Response.status(500)
                    .header("create-exception", registro.toString())
                    .build();
        }

        return Response.status(422).
                header("missing-parameter", "id")
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer idTipoEspacio) {
        if (idTipoEspacio != null) {
            TipoEspacio registro = teBean.findById(idTipoEspacio);

            if (registro != null) {
                try {
                    teBean.delete(registro);
                    return Response.status(Response.Status.NO_CONTENT).build();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    return Response.status(500).header("delete-exception", registro.toString()).build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .header("not-found", "id")
                        .build();
            }
        }

        return Response.status(422)
                .header("missing-parameter", "id")
                .build();
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Integer idTipoEspacio, TipoEspacio registroActualizado) {
        if (idTipoEspacio != null && registroActualizado != null) {
            TipoEspacio registroExistente = teBean.findById(idTipoEspacio);

            if (registroExistente != null) {
                try {
                    registroExistente.setNombre(registroActualizado.getNombre());
                    TipoEspacio registroModificado = teBean.modify(registroExistente);
                    return Response.status(Response.Status.OK)
                            .entity(registroModificado)
                            .build();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    return Response.status(500)
                            .header("update-exception", registroActualizado.toString())
                            .build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .header("not-found", "id")
                        .build();
            }
        }

        return Response.status(422)
                .header("missing-parameter", "id o datos de actualización")
                .build();
    }

    @GET
    @Path("/count")
    @Produces({MediaType.APPLICATION_JSON})
    public Response count() {
        try {
            int total = teBean.count();
            return Response.status(Response.Status.OK)
                    .entity(total)
                    .build();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return Response.status(500)
                    .header("count-exception", ex.getMessage())
                    .build();
        }
    }

}
