/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.boundary.rest;

import jakarta.annotation.Resource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 *
 * @author alexo
 */
@Path("reporte")
public class ReporteResource implements Serializable {

    @Resource(lookup = "jdbc/pgdb")
    DataSource poolDeconexiones;

    @GET
    @Path("{reporte}")
    public Response generarReporte(@PathParam("reporte") final String nombreReporte) {
        String pathReporte = null;

        HashMap parametros = new HashMap();

        switch (nombreReporte) {
            case "tipo_espacio":
                //vinvulo es de mi carpeta simbolica y por eso esta puesto en el pathReporte
                pathReporte = "/reportes/PRN335_2023/HolaMundo.jasper";
                /// el value cambia el nombre de la firma 

                parametros.put("firmadoPor", "Alexo");
                break;
            case "area":
                //vinvulo es de mi carpeta simbolica y por eso esta puesto en el pathReporte
                pathReporte = "/reportes/PRN335_2023/Area.jasper";
                URL pathSubreporte = ReporteResource.class.getResource(pathReporte);
                String pathSubreporteString = pathSubreporte.getPath();

                parametros.put("PAHT_SUBREPORTES", pathSubreporteString.substring(0, pathSubreporteString.lastIndexOf("/")) + "/");
                break;
            default:
                return Response.status(Response.Status.NOT_FOUND)
                        .header("report-not-found", nombreReporte)
                        .entity(Entity.text("no se encuentra el reporte"))
                        .build();
        }

        if (pathReporte != null) {
            try {
                //llenar el reporte
                InputStream fuenteReporte = ReporteResource.class.getResourceAsStream(pathReporte);

                JasperPrint impresor = JasperFillManager.
                        fillReport(
                                fuenteReporte,
                                parametros,
                                poolDeconexiones.getConnection());
                SimplePdfExporterConfiguration configuracion = new SimplePdfExporterConfiguration();
                configuracion.setMetadataAuthor("Universidad de El Salvador ");
                configuracion.setMetadataCreator("Sistema de Parqueo UES");
                configuracion.setMetadataTitle("Reporte - " + nombreReporte);

                //exportar a un stream 
                JRPdfExporter exportador = new JRPdfExporter();
                exportador.setConfiguration(configuracion);
                exportador.setExporterInput(new SimpleExporterInput(impresor));
                StreamingOutput stream = new StreamingOutput() {
                    @Override
                    public void write(OutputStream out) throws IOException, WebApplicationException {
                        exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                        try {
                            exportador.exportReport();
                        } catch (Exception ex) {
                            Logger.getLogger(ReporteResource.class.getName()).log(Level.SEVERE, "No puedo llenar el reporte", ex);

                        }

                    }
                };

                //obtener stream para rest
                // devolver stream para rest en el Response
                return Response.ok(stream, "application/pdf").build();

            } catch (Exception ex) {
                Logger.getLogger(ReporteResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.serverError().build();
    }

}
