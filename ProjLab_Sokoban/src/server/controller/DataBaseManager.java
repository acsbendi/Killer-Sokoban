package server.controller;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class responsible for the management of the database containing all necessary user data.
 * The database in use has only one table, called Users, which has the following heading:
 *
 * -----ID------Name---PasswordHash----Wins-----Losses---
 * ---integer--String---integer---integer---integer--
 *
 * Note that only a hash of passwords is stored for security reasons.
 */
public class DataBaseManager {
    private static final String connectionStr = "jdbc:mysql://localhost:3306/server";
    //TODO read confidential data from config file
    private static final String connUserName = "server";
    private static final String connPassword = "server";

    /** Connection to the database in use */
    private Connection connection;

    /**
     * Increments one of the stats of a specified user.
     * @param username The user whose stat should be incremented.
     * @param stat The stat to be incremented, can be Wins or Losses
     */
    private void IncrementStat(String username, String stat){
        String incrementWinsSQL = "UPDATE Users "
                + "SET "+ stat  + " = " + stat + " + 1 "
                + "WHERE Name = ?";
        try {
            OpenConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(incrementWinsSQL);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();

            CloseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs a new DataBaseManager,.
     */
    DataBaseManager(){

    }

    /**
     * Registers a new user into the database, with the specified name and password hash.
     * @param username The name of the new user.
     * @param passwordHash The hashed password of the new user.
     */
    public boolean Register(String username, long passwordHash) {

        String insertNewUserSQL = "INSERT INTO Users "
                + "(Name, PasswordHash, Wins, Losses) VALUES "
                + "(?,?,?,?)";
        try {
            OpenConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(insertNewUserSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, passwordHash);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.executeUpdate();

            CloseConnection();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Checks whether a user with the specified name and password (hash) exists in the database.
     * @param username The name of the user to be checked.
     * @param passwordHash The hashed password of the user. to be checked.
     * @return True, if the given user exists, false, if not.
     */
    public boolean Check(String username, long passwordHash ){
        String checkSQL = "SELECT * "
                + "FROM Users "
                + "WHERE Name = ? AND PasswordHash = ?";

        try {
            OpenConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(checkSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, passwordHash);
            ResultSet rs = preparedStatement.executeQuery();

            CloseConnection();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Increments the number of the total wins of a specified player.
     * @param username The name of the player.
     */
    void Win(String username){
        IncrementStat(username, "Wins");
    }

    /**
     * Increments the number of the total losses of a specified player.
     * @param username The name of the player.
     */
    void Lose(String username){
        IncrementStat(username, "Losses");
    }

    /**
     * Queries the numbers of the total wins and losses of a specified user.
     * @param username The name of the user.
     * @return A list containing the number of the wins (1st element)
     * and losses (2nd element).
     */
    ArrayList<Integer> GetResultOf(String username){
        String findResultSQL = "SELECT Wins, Losses "
                + "FROM Users "
                + "WHERE NAME = ?";

        ArrayList<Integer> result = new ArrayList<>(2);
        try {
            OpenConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(findResultSQL);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            for (int i = 1; i < 3; i++) {
                result.add(rs.getInt(i));
            }

            CloseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Queries the 5 users with the most wins currently in the database.
     * @return The list of the top 5 players, in descending order.
     */
    ArrayList<String> GetBestPlayers(){
        String findBestPlayersSQL = "SELECT Name "
                + "FROM Users "
                + "ORDER BY Wins DESC LIMIT 5";

        ArrayList<String> result = new ArrayList<>(5);
        try {
            OpenConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(findBestPlayersSQL);

            while(rs.next())
                result.add(rs.getString(1));

            CloseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Opens the connection to the database.
     * This method should be called before running any query so we only use the connection whenever needed.
     * @throws SQLException Thrown when the connection was not successfully opened.
     */
    private void OpenConnection() throws SQLException {
        connection = DriverManager.getConnection(connectionStr,connUserName,connPassword);
    }

    /**
     * Closes the connection to the database.
     * This method should be called after running any query so we only use the connection whenever needed.
     * @throws SQLException Thrown when the connection was not successfully closed.
     */
    private void CloseConnection() throws SQLException {
        connection.close();
    }
}
