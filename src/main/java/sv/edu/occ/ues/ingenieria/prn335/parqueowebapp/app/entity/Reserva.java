/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "reserva", catalog = "parqueo", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
//    @NamedQuery(name = "Reserva.findByIdReserva", query = "SELECT r FROM Reserva r WHERE f.idReserva = :idReserva"),
//    @NamedQuery(name = "Reserva.findByDesde", query = "SELECT r FROM Reserva r WHERE f.desde = :desde"),
//    @NamedQuery(name = "Reserva.findByHasta", query = "SELECT r FROM Reserva r WHERE f.hasta = :hasta"),
//    @NamedQuery(name = "Reserva.findByObservaciones", query = "SELECT r FROM Reserva r WHERE f.observaciones = :observaciones")

})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reserva")
    private Long idReserva;
    @Column(name = "desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date desde;
    @Column(name = "hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hasta;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(mappedBy = "idReserva")
    private List<ReservaHistorial> reservaHistorialList;
    @JoinColumn(name = "id_espacio", referencedColumnName = "id_espacio")
    @ManyToOne
    private Espacio idEspacio;
    @JoinColumn(name = "id_tipo_reserva", referencedColumnName = "id_tipo_reserva")
    @ManyToOne
    private TipoReserva idTipoReserva;

    public Reserva() {
    }

    public Reserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<ReservaHistorial> getReservaHistorialList() {
        return reservaHistorialList;
    }

    public void setReservaHistorialList(List<ReservaHistorial> reservaHistorialList) {
        this.reservaHistorialList = reservaHistorialList;
    }

    public Espacio getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(Espacio idEspacio) {
        this.idEspacio = idEspacio;
    }

    public TipoReserva getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(TipoReserva idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReserva != null ? idReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.idReserva == null && other.idReserva != null) || (this.idReserva != null && !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mapeo1.Reserva[ idReserva=" + idReserva + " ]";
    }

}
