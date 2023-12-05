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
@Table(name = "detail_buku")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailBuku.findAll", query = "SELECT d FROM DetailBuku d"),
    @NamedQuery(name = "DetailBuku.findByIdPegawai", query = "SELECT d FROM DetailBuku d WHERE d.detailBukuPK.idPegawai = :idPegawai"),
    @NamedQuery(name = "DetailBuku.findByIdAnggota", query = "SELECT d FROM DetailBuku d WHERE d.detailBukuPK.idAnggota = :idAnggota"),
    @NamedQuery(name = "DetailBuku.findByIdPinjam", query = "SELECT d FROM DetailBuku d WHERE d.detailBukuPK.idPinjam = :idPinjam"),
    @NamedQuery(name = "DetailBuku.findByIsbn", query = "SELECT d FROM DetailBuku d WHERE d.detailBukuPK.isbn = :isbn"),
    @NamedQuery(name = "DetailBuku.findByJumlahBuku", query = "SELECT d FROM DetailBuku d WHERE d.jumlahBuku = :jumlahBuku")})
public class DetailBuku implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetailBukuPK detailBukuPK;
    @Column(name = "jumlah__buku")
    private String jumlahBuku;
    @JoinColumn(name = "isbn", referencedColumnName = "isbn", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Buku buku;
    @JoinColumns({
        @JoinColumn(name = "id_pegawai", referencedColumnName = "id_pegawai", insertable = false, updatable = false),
        @JoinColumn(name = "id_anggota", referencedColumnName = "id_anggota", insertable = false, updatable = false),
        @JoinColumn(name = "id_pinjam", referencedColumnName = "id_pinjam", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Peminjaman peminjaman;

    public DetailBuku() {
    }

    public DetailBuku(DetailBukuPK detailBukuPK) {
        this.detailBukuPK = detailBukuPK;
    }

    public DetailBuku(String idPegawai, String idAnggota, String idPinjam, String isbn) {
        this.detailBukuPK = new DetailBukuPK(idPegawai, idAnggota, idPinjam, isbn);
    }

    public DetailBukuPK getDetailBukuPK() {
        return detailBukuPK;
    }

    public void setDetailBukuPK(DetailBukuPK detailBukuPK) {
        this.detailBukuPK = detailBukuPK;
    }

    public String getJumlahBuku() {
        return jumlahBuku;
    }

    public void setJumlahBuku(String jumlahBuku) {
        this.jumlahBuku = jumlahBuku;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailBukuPK != null ? detailBukuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailBuku)) {
            return false;
        }
        DetailBuku other = (DetailBuku) object;
        if ((this.detailBukuPK == null && other.detailBukuPK != null) || (this.detailBukuPK != null && !this.detailBukuPK.equals(other.detailBukuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.DetailBuku[ detailBukuPK=" + detailBukuPK + " ]";
    }
    
}
