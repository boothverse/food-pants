package org.boothverse.foodpants.business.dao.util;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.boothverse.foodpants.business.dao.JDBCDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils extends JDBCDAO {
    /**
     * Runs a SQL file
     *
     * @param filepath
     */
    public void executeScript(String filepath) {
        try (Connection conn = getDBConnection(); Reader reader = new BufferedReader(new FileReader(filepath))) {
            ScriptRunner sr = new ScriptRunner(conn);
            sr.runScript(reader);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
