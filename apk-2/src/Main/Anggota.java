/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author saintek1
 */
@Entity
@Table(name = "anggota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anggota.findAll", query = "SELECT a FROM Anggota a"),
    @NamedQuery(name = "Anggota.findByIdAnggota", query = "SELECT a FROM Anggota a WHERE a.idAnggota = :idAnggota"),
    @NamedQuery(name = "Anggota.findByNamaAnggota", query = "SELECT a FROM Anggota a WHERE a.namaAnggota = :namaAnggota")})
public class Anggota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_anggota")
    private String idAnggota;
    @Column(name = "nama_anggota")
    private String namaAnggota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anggota")
    private Collection<Peminjaman> peminjamanCollection;

    public Anggota() {
    }

    public Anggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    @XmlTransient
    public Collection<Peminjaman> getPeminjamanCollection() {
        return peminjamanCollection;
    }

    public void setPeminjamanCollection(Collection<Peminjaman> peminjamanCollection) {
        this.peminjamanCollection = peminjamanCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnggota != null ? idAnggota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anggota)) {
            return false;
        }
        Anggota other = (Anggota) object;
        if ((this.idAnggota == null && other.idAnggota != null) || (this.idAnggota != null && !this.idAnggota.equals(other.idAnggota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.Anggota[ idAnggota=" + idAnggota + " ]";
    }
    
}
