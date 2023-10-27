/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.ws;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control.ManejadorSesionWS;

/**
 *
 * @author alexo
 */
@Named
@ApplicationScoped
@ServerEndpoint(value = "/notificador_espacio")

public class NotificadorEspacioEndpoint implements Serializable {

    @Inject
    ManejadorSesionWS msWS;

    @OnOpen
    public void conecto(Session s) {
        msWS.agregar(s);

        System.out.println("conecto");

    }

    @OnClose
    public void cerrar(Session s) {
        msWS.eliminar(s);
    }

    public void propagarMensaje(String mensaje) throws IOException {
        for (Session sesion : msWS.getSesiones()) {
            if (sesion.isOpen()) {
                sesion.getBasicRemote().sendText(mensaje);
            }
        }
    }

}
