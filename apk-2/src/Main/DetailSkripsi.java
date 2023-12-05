/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saintek1
 */
@Entity
@Table(name = "detail_skripsi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailSkripsi.findAll", query = "SELECT d FROM DetailSkripsi d"),
    @NamedQuery(name = "DetailSkripsi.findByIdPegawai", query = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idPegawai = :idPegawai"),
    @NamedQuery(name = "DetailSkripsi.findByIdAnggota", query = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idAnggota = :idAnggota"),
    @NamedQuery(name = "DetailSkripsi.findByIdPinjam", query = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idPinjam = :idPinjam"),
    @NamedQuery(name = "DetailSkripsi.findByIdSkripsi", query = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idSkripsi = :idSkripsi"),
    @NamedQuery(name = "DetailSkripsi.findByJumlahSkripsi", query = "SELECT d FROM DetailSkripsi d WHERE d.jumlahSkripsi = :jumlahSkripsi")})
public class DetailSkripsi implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetailSkripsiPK detailSkripsiPK;
    @Column(name = "jumlah_skripsi")
    private String jumlahSkripsi;
    @JoinColumns({
        @JoinColumn(name = "id_pegawai", referencedColumnName = "id_pegawai", insertable = false, updatable = false),
        @JoinColumn(name = "id_anggota", referencedColumnName = "id_anggota", insertable = false, updatable = false),
        @JoinColumn(name = "id_pinjam", referencedColumnName = "id_pinjam", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Peminjaman peminjaman;
    @JoinColumn(name = "id_skripsi", referencedColumnName = "id_skripsi", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Skripsi skripsi;

    public DetailSkripsi() {
    }

    public DetailSkripsi(DetailSkripsiPK detailSkripsiPK) {
        this.detailSkripsiPK = detailSkripsiPK;
    }

    public DetailSkripsi(String idPegawai, String idAnggota, String idPinjam, String idSkripsi) {
        this.detailSkripsiPK = new DetailSkripsiPK(idPegawai, idAnggota, idPinjam, idSkripsi);
    }

    public DetailSkripsiPK getDetailSkripsiPK() {
        return detailSkripsiPK;
    }

    public void setDetailSkripsiPK(DetailSkripsiPK detailSkripsiPK) {
        this.detailSkripsiPK = detailSkripsiPK;
    }

    public String getJumlahSkripsi() {
        return jumlahSkripsi;
    }

    public void setJumlahSkripsi(String jumlahSkripsi) {
        this.jumlahSkripsi = jumlahSkripsi;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public Skripsi getSkripsi() {
        return skripsi;
    }

    public void setSkripsi(Skripsi skripsi) {
        this.skripsi = skripsi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailSkripsiPK != null ? detailSkripsiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailSkripsi)) {
            return false;
        }
        DetailSkripsi other = (DetailSkripsi) object;
        if ((this.detailSkripsiPK == null && other.detailSkripsiPK != null) || (this.detailSkripsiPK != null && !this.detailSkripsiPK.equals(other.detailSkripsiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.DetailSkripsi[ detailSkripsiPK=" + detailSkripsiPK + " ]";
    }
    
}
