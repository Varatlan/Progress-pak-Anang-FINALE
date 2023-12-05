/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     04/12/2023 09.17.49                          */
/*==============================================================*/


drop index ANGGOTA_PK;

drop table ANGGOTA;

drop index BUKU_PK;

drop table BUKU;

drop index PUNYA_FK;

drop index MENGIRIM_FK;

drop index DETAIL_BUKU_PK;

drop table DETAIL_BUKU;

drop index MEMILIKI_FK;

drop index DIKIRIM_FK;

drop index DETAIL_SKRIPSI_PK;

drop table DETAIL_SKRIPSI;

drop index PEGAWAI_PK;

drop table PEGAWAI;

drop index MELAKUKAN_FK;

drop index MENERIMA_FK;

drop index PEMINJAMAN_PK;

drop table PEMINJAMAN;

drop index SKRIPSI_PK;

drop table SKRIPSI;

/*==============================================================*/
/* Table: ANGGOTA                                               */
/*==============================================================*/
create table ANGGOTA (
   ID_ANGGOTA           VARCHAR(255)         not null,
   NAMA_ANGGOTA         VARCHAR(255)         null,
   constraint PK_ANGGOTA primary key (ID_ANGGOTA)
);

/*==============================================================*/
/* Index: ANGGOTA_PK                                            */
/*==============================================================*/
create unique index ANGGOTA_PK on ANGGOTA (
ID_ANGGOTA
);

/*==============================================================*/
/* Table: BUKU                                                  */
/*==============================================================*/
create table BUKU (
   ISBN                 VARCHAR(255)         not null,
   JUDUL                VARCHAR(255)         null,
   SUBJUDUL             VARCHAR(255)         null,
   PENGARANG            VARCHAR(255)         null,
   TAHUN                VARCHAR(255)         null,
   HALAMAN              VARCHAR(255)         null,
   PENERBIT             VARCHAR(255)         null,
   constraint PK_BUKU primary key (ISBN)
);

/*==============================================================*/
/* Index: BUKU_PK                                               */
/*==============================================================*/
create unique index BUKU_PK on BUKU (
ISBN
);

/*==============================================================*/
/* Table: DETAIL_BUKU                                           */
/*==============================================================*/
create table DETAIL_BUKU (
   ID_PEGAWAI           VARCHAR(255)         not null,
   ID_ANGGOTA           VARCHAR(255)         not null,
   ID_PINJAM            VARCHAR(255)         not null,
   ISBN                 VARCHAR(255)         not null,
   JUMLAH__BUKU         VARCHAR(255)         null,
   constraint PK_DETAIL_BUKU primary key (ID_PEGAWAI, ID_ANGGOTA, ID_PINJAM, ISBN)
);

/*==============================================================*/
/* Index: DETAIL_BUKU_PK                                        */
/*==============================================================*/
create unique index DETAIL_BUKU_PK on DETAIL_BUKU (
ID_PEGAWAI,
ID_ANGGOTA,
ID_PINJAM,
ISBN
);

/*==============================================================*/
/* Index: MENGIRIM_FK                                           */
/*==============================================================*/
create  index MENGIRIM_FK on DETAIL_BUKU (
ID_PEGAWAI,
ID_ANGGOTA,
ID_PINJAM
);

/*==============================================================*/
/* Index: PUNYA_FK                                              */
/*==============================================================*/
create  index PUNYA_FK on DETAIL_BUKU (
ISBN
);

/*==============================================================*/
/* Table: DETAIL_SKRIPSI                                        */
/*==============================================================*/
create table DETAIL_SKRIPSI (
   ID_PEGAWAI           VARCHAR(255)         not null,
   ID_ANGGOTA           VARCHAR(255)         not null,
   ID_PINJAM            VARCHAR(255)         not null,
   ID_SKRIPSI           VARCHAR(255)         not null,
   JUMLAH_SKRIPSI       VARCHAR(255)         null,
   constraint PK_DETAIL_SKRIPSI primary key (ID_PEGAWAI, ID_ANGGOTA, ID_PINJAM, ID_SKRIPSI)
);

/*==============================================================*/
/* Index: DETAIL_SKRIPSI_PK                                     */
/*==============================================================*/
create unique index DETAIL_SKRIPSI_PK on DETAIL_SKRIPSI (
ID_PEGAWAI,
ID_ANGGOTA,
ID_PINJAM,
ID_SKRIPSI
);

/*==============================================================*/
/* Index: DIKIRIM_FK                                            */
/*==============================================================*/
create  index DIKIRIM_FK on DETAIL_SKRIPSI (
ID_PEGAWAI,
ID_ANGGOTA,
ID_PINJAM
);

/*==============================================================*/
/* Index: MEMILIKI_FK                                           */
/*==============================================================*/
create  index MEMILIKI_FK on DETAIL_SKRIPSI (
ID_SKRIPSI
);

/*==============================================================*/
/* Table: PEGAWAI                                               */
/*==============================================================*/
create table PEGAWAI (
   ID_PEGAWAI           VARCHAR(255)         not null,
   NAMA_PEGAWAI         VARCHAR(255)         null,
   constraint PK_PEGAWAI primary key (ID_PEGAWAI)
);

/*==============================================================*/
/* Index: PEGAWAI_PK                                            */
/*==============================================================*/
create unique index PEGAWAI_PK on PEGAWAI (
ID_PEGAWAI
);

/*==============================================================*/
/* Table: PEMINJAMAN                                            */
/*==============================================================*/
create table PEMINJAMAN (
   ID_PEGAWAI           VARCHAR(255)         not null,
   ID_ANGGOTA           VARCHAR(255)         not null,
   ID_PINJAM            VARCHAR(255)         not null,
   TGL_PINJAM           VARCHAR(255)         null,
   constraint PK_PEMINJAMAN primary key (ID_PEGAWAI, ID_ANGGOTA, ID_PINJAM)
);

/*==============================================================*/
/* Index: PEMINJAMAN_PK                                         */
/*==============================================================*/
create unique index PEMINJAMAN_PK on PEMINJAMAN (
ID_PEGAWAI,
ID_ANGGOTA,
ID_PINJAM
);

/*==============================================================*/
/* Index: MENERIMA_FK                                           */
/*==============================================================*/
create  index MENERIMA_FK on PEMINJAMAN (
ID_PEGAWAI
);

/*==============================================================*/
/* Index: MELAKUKAN_FK                                          */
/*==============================================================*/
create  index MELAKUKAN_FK on PEMINJAMAN (
ID_ANGGOTA
);

/*==============================================================*/
/* Table: SKRIPSI                                               */
/*==============================================================*/
create table SKRIPSI (
   ID_SKRIPSI           VARCHAR(255)         not null,
   JUDUL                VARCHAR(255)         null,
   PENGARANG            VARCHAR(255)         null,
   TAHUN                VARCHAR(255)         null,
   HALAMAN              VARCHAR(255)         null,
   constraint PK_SKRIPSI primary key (ID_SKRIPSI)
);

/*==============================================================*/
/* Index: SKRIPSI_PK                                            */
/*==============================================================*/
create unique index SKRIPSI_PK on SKRIPSI (
ID_SKRIPSI
);

alter table DETAIL_BUKU
   add constraint FK_DETAIL_B_MENGIRIM_PEMINJAM foreign key (ID_PEGAWAI, ID_ANGGOTA, ID_PINJAM)
      references PEMINJAMAN (ID_PEGAWAI, ID_ANGGOTA, ID_PINJAM)
      on delete restrict on update restrict;

alter table DETAIL_BUKU
   add constraint FK_DETAIL_B_PUNYA_BUKU foreign key (ISBN)
      references BUKU (ISBN)
      on delete restrict on update restrict;

alter table DETAIL_SKRIPSI
   add constraint FK_DETAIL_S_DIKIRIM_PEMINJAM foreign key (ID_PEGAWAI, ID_ANGGOTA, ID_PINJAM)
      references PEMINJAMAN (ID_PEGAWAI, ID_ANGGOTA, ID_PINJAM)
      on delete restrict on update restrict;

alter table DETAIL_SKRIPSI
   add constraint FK_DETAIL_S_MEMILIKI_SKRIPSI foreign key (ID_SKRIPSI)
      references SKRIPSI (ID_SKRIPSI)
      on delete restrict on update restrict;

alter table PEMINJAMAN
   add constraint FK_PEMINJAM_MELAKUKAN_ANGGOTA foreign key (ID_ANGGOTA)
      references ANGGOTA (ID_ANGGOTA)
      on delete restrict on update restrict;

alter table PEMINJAMAN
   add constraint FK_PEMINJAM_MENERIMA_PEGAWAI foreign key (ID_PEGAWAI)
      references PEGAWAI (ID_PEGAWAI)
      on delete restrict on update restrict;

