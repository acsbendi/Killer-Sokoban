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
    private static final String connUserName = "server";
    private static final String connPassword = "server";

    private Connection connection; /** Connection to the database in use */

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
            PreparedStatement preparedStatement = connection.prepareStatement(incrementWinsSQL);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs a new DataBaseManager, creating a connection to the database
     */
    public DataBaseManager(){
        try {
            connection = DriverManager.getConnection(connectionStr,connUserName,connPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            PreparedStatement preparedStatement = connection.prepareStatement(insertNewUserSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, passwordHash);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.executeUpdate();
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
            PreparedStatement preparedStatement = connection.prepareStatement(checkSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, passwordHash);
            ResultSet rs = preparedStatement.executeQuery();

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
    public void Win(String username){
        IncrementStat(username, "Wins");
    }

    /**
     * Increments the number of the total losses of a specified player.
     * @param username The name of the player.
     */
    public void Lose(String username){
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
            PreparedStatement preparedStatement = connection.prepareStatement(findResultSQL);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            for (int i = 1; i < 3; i++) {
                result.add(rs.getInt(i));
            }
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(findBestPlayersSQL);

            while(rs.next())
                result.add(rs.getString(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
