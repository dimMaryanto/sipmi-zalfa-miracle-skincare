/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.entity.Kategori;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryKategori extends BaseRepository<Kategori, Integer> {

    public String TABLE_NAME = "m_kategori";
    public String COLUMN_KODE = "kode";
    public String COLUMN_NAME = "nama";

}
