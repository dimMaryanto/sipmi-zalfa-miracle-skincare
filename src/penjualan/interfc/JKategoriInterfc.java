package penjualan.interfc;

import penjualan.entity.JKategori;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lani
 */
@Deprecated
public interface JKategoriInterfc {

    List getAll() throws SQLException;

    JKategori insert(JKategori o) throws SQLException;

    void update(JKategori o) throws SQLException;

    void delete(String id_kategori) throws SQLException;
}
