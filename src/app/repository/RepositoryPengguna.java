/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.entity.Pengguna;
import java.sql.SQLException;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryPengguna extends BaseRepository<Pengguna, Integer> {

    public String TABLE_NAME = "m_user";
    public String COLUMN_ID = "id";
    public String COLUMN_NAME = "nama";
    public String COLUMN_USERNAME = "username";
    public String COLUMN_PASSWORD = "password";
    public String COLUMN_JABATAN = "jabatan";
    public String COLUMN_STATUS = "status";

    public Pengguna findByUsernameAndPasswordAndStatus(String username, String password, Boolean status) throws SQLException;
    
    public Boolean exists(String username)throws SQLException;

}
