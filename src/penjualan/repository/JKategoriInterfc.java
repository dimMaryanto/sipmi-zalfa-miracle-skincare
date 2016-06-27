package penjualan.repository;

import penjualan.entity.KategoriBarang;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lani
 */
public interface JKategoriInterfc {

    List getAll() throws SQLException;

    KategoriBarang insert(KategoriBarang o) throws SQLException;

    void update(KategoriBarang o) throws SQLException;

    void delete(String id_kategori) throws SQLException;
}
