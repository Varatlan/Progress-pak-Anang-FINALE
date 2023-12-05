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
@Table(name = "skripsi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skripsi.findAll", query = "SELECT s FROM Skripsi s"),
    @NamedQuery(name = "Skripsi.findByIdSkripsi", query = "SELECT s FROM Skripsi s WHERE s.idSkripsi = :idSkripsi"),
    @NamedQuery(name = "Skripsi.findByJudul", query = "SELECT s FROM Skripsi s WHERE s.judul = :judul"),
    @NamedQuery(name = "Skripsi.findByPengarang", query = "SELECT s FROM Skripsi s WHERE s.pengarang = :pengarang"),
    @NamedQuery(name = "Skripsi.findByTahun", query = "SELECT s FROM Skripsi s WHERE s.tahun = :tahun"),
    @NamedQuery(name = "Skripsi.findByHalaman", query = "SELECT s FROM Skripsi s WHERE s.halaman = :halaman")})
public class Skripsi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_skripsi")
    private String idSkripsi;
    @Column(name = "judul")
    private String judul;
    @Column(name = "pengarang")
    private String pengarang;
    @Column(name = "tahun")
    private String tahun;
    @Column(name = "halaman")
    private String halaman;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skripsi")
    private Collection<DetailSkripsi> detailSkripsiCollection;

    public Skripsi() {
    }

    public Skripsi(String idSkripsi) {
        this.idSkripsi = idSkripsi;
    }

    public String getIdSkripsi() {
        return idSkripsi;
    }

    public void setIdSkripsi(String idSkripsi) {
        this.idSkripsi = idSkripsi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getHalaman() {
        return halaman;
    }

    public void setHalaman(String halaman) {
        this.halaman = halaman;
    }

    @XmlTransient
    public Collection<DetailSkripsi> getDetailSkripsiCollection() {
        return detailSkripsiCollection;
    }

    public void setDetailSkripsiCollection(Collection<DetailSkripsi> detailSkripsiCollection) {
        this.detailSkripsiCollection = detailSkripsiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSkripsi != null ? idSkripsi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skripsi)) {
            return false;
        }
        Skripsi other = (Skripsi) object;
        if ((this.idSkripsi == null && other.idSkripsi != null) || (this.idSkripsi != null && !this.idSkripsi.equals(other.idSkripsi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.Skripsi[ idSkripsi=" + idSkripsi + " ]";
    }
    
}
