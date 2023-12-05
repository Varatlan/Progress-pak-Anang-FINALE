/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "peminjaman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peminjaman.findAll", query = "SELECT p FROM Peminjaman p"),
    @NamedQuery(name = "Peminjaman.findByIdPegawai", query = "SELECT p FROM Peminjaman p WHERE p.peminjamanPK.idPegawai = :idPegawai"),
    @NamedQuery(name = "Peminjaman.findByIdAnggota", query = "SELECT p FROM Peminjaman p WHERE p.peminjamanPK.idAnggota = :idAnggota"),
    @NamedQuery(name = "Peminjaman.findByIdPinjam", query = "SELECT p FROM Peminjaman p WHERE p.peminjamanPK.idPinjam = :idPinjam"),
    @NamedQuery(name = "Peminjaman.findByTglPinjam", query = "SELECT p FROM Peminjaman p WHERE p.tglPinjam = :tglPinjam")})
public class Peminjaman implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeminjamanPK peminjamanPK;
    @Column(name = "tgl_pinjam")
    private String tglPinjam;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peminjaman")
    private Collection<DetailSkripsi> detailSkripsiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peminjaman")
    private Collection<DetailBuku> detailBukuCollection;
    @JoinColumn(name = "id_anggota", referencedColumnName = "id_anggota", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Anggota anggota;
    @JoinColumn(name = "id_pegawai", referencedColumnName = "id_pegawai", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pegawai pegawai;

    public Peminjaman() {
    }

    public Peminjaman(PeminjamanPK peminjamanPK) {
        this.peminjamanPK = peminjamanPK;
    }

    public Peminjaman(String idPegawai, String idAnggota, String idPinjam) {
        this.peminjamanPK = new PeminjamanPK(idPegawai, idAnggota, idPinjam);
    }

    public PeminjamanPK getPeminjamanPK() {
        return peminjamanPK;
    }

    public void setPeminjamanPK(PeminjamanPK peminjamanPK) {
        this.peminjamanPK = peminjamanPK;
    }

    public String getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(String tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    @XmlTransient
    public Collection<DetailSkripsi> getDetailSkripsiCollection() {
        return detailSkripsiCollection;
    }

    public void setDetailSkripsiCollection(Collection<DetailSkripsi> detailSkripsiCollection) {
        this.detailSkripsiCollection = detailSkripsiCollection;
    }

    @XmlTransient
    public Collection<DetailBuku> getDetailBukuCollection() {
        return detailBukuCollection;
    }

    public void setDetailBukuCollection(Collection<DetailBuku> detailBukuCollection) {
        this.detailBukuCollection = detailBukuCollection;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peminjamanPK != null ? peminjamanPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peminjaman)) {
            return false;
        }
        Peminjaman other = (Peminjaman) object;
        if ((this.peminjamanPK == null && other.peminjamanPK != null) || (this.peminjamanPK != null && !this.peminjamanPK.equals(other.peminjamanPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.Peminjaman[ peminjamanPK=" + peminjamanPK + " ]";
    }
    
}
