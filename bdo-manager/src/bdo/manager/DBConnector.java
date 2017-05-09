package bdo.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;

public class DBConnector {
	
	Connection con;
	
	String conStr = "jdbc:mysql://localhost:3306/bdo-manager?user=root&password=";
	
	public DBConnector() throws SQLException {
		con = DriverManager.getConnection(conStr);
	}
	
	public Integer addMember(String firstname, String lastname, Integer level, String cl) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("INSERT INTO `member` (`firstname`, `lastname`, `level`, `cl`) VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, firstname);
                stmt.setString(2, lastname);
                stmt.setInt(3, level);
                stmt.setString(4, cl);
		int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating graph failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating Member failed, no ID obtained.");
                }
            }
	}
        
        public ResultSet viewMember() throws SQLException {
            ResultSet res = null;
            PreparedStatement stmt = con.prepareStatement("SELECT firstname,lastname, level, cl FROM `member`");
            res = stmt.executeQuery();
            return res;
        }
        
        public void removeMember() throws SQLException {
            /*
            * TODO delete sql statement aufbauen für ausgewählte zeile
            * dazu popup fenster mit anfrage willst du wirklich
            */
            //PreparedStatement stmt = con.prepareStatement("DELETE FROM `member` WHERE ");
            //res = stmt.executeQuery();
        }
	/*
	public Integer addMeasurement(Integer graphId, Integer p, Integer q, Integer sampleSetSize, Integer numberOfEdges) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("INSERT INTO `measurement` (`graph`, `p`, `q`, `sampleSetSize`, `numberOfEdges`) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, graphId);
		stmt.setInt(2, p);
		stmt.setInt(3, q);
		stmt.setInt(4, sampleSetSize);
		stmt.setInt(5, numberOfEdges);
		int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating measurement failed, no rows affected.");
        }

        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating measurement failed, no ID obtained.");
            }
        }
	}
	
	public Integer addMeasurementData(Integer measurementId, Integer iteration, Integer independentSetSize, Integer counter1, Integer counter2, Integer counter3) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("INSERT INTO `measurementData` (`measurement`, `iteration`, `M1`, `M2`, `M3`, `independentSetSize`) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, measurementId);
		stmt.setInt(2, iteration);
		stmt.setInt(3, counter1);
		stmt.setInt(4, counter2);
		stmt.setInt(5, counter3);
		stmt.setInt(6, independentSetSize);
		int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating measurementData failed, no rows affected.");
        }

        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating measurementData failed, no ID obtained.");
            }
        }
	}
	
	
	
	public void close() throws SQLException {
		this.con.close();
	}
        */

    private static class JDBCTutorialUtilities {

        private static void printSQLException(SQLException e) {
            throw new UnsupportedOperationException("Test."); //To change body of generated methods, choose Tools | Templates.
        }

        public JDBCTutorialUtilities() {
        }
    }
}
