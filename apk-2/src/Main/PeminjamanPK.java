/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author saintek1
 */
@Embeddable
public class PeminjamanPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_pegawai")
    private String idPegawai;
    @Basic(optional = false)
    @Column(name = "id_anggota")
    private String idAnggota;
    @Basic(optional = false)
    @Column(name = "id_pinjam")
    private String idPinjam;

    public PeminjamanPK() {
    }

    public PeminjamanPK(String idPegawai, String idAnggota, String idPinjam) {
        this.idPegawai = idPegawai;
        this.idAnggota = idAnggota;
        this.idPinjam = idPinjam;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getIdPinjam() {
        return idPinjam;
    }

    public void setIdPinjam(String idPinjam) {
        this.idPinjam = idPinjam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPegawai != null ? idPegawai.hashCode() : 0);
        hash += (idAnggota != null ? idAnggota.hashCode() : 0);
        hash += (idPinjam != null ? idPinjam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeminjamanPK)) {
            return false;
        }
        PeminjamanPK other = (PeminjamanPK) object;
        if ((this.idPegawai == null && other.idPegawai != null) || (this.idPegawai != null && !this.idPegawai.equals(other.idPegawai))) {
            return false;
        }
        if ((this.idAnggota == null && other.idAnggota != null) || (this.idAnggota != null && !this.idAnggota.equals(other.idAnggota))) {
            return false;
        }
        if ((this.idPinjam == null && other.idPinjam != null) || (this.idPinjam != null && !this.idPinjam.equals(other.idPinjam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.PeminjamanPK[ idPegawai=" + idPegawai + ", idAnggota=" + idAnggota + ", idPinjam=" + idPinjam + " ]";
    }
    
}
