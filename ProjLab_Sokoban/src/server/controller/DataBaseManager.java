package server.controller;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class responsible for the management of the database containing all necessary user data.
 * The database in use has only one table, called Users, which has the following heading:
 *
 * ---Name---Password----Wins-----Losses---
 * --String---integer---integer---integer--
 */
public class DataBaseManager {
    private static final String connectionStr = "jdbc:mysql://localhost:3306/test";
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
                + "SET ? = ? + 1 "
                + "WHERE Name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(incrementWinsSQL);
            preparedStatement.setString(1, stat);
            preparedStatement.setString(2, stat);
            preparedStatement.setString(3, username);
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
     * Registers a new user into the database, with the specified name and password.
     * @param username The name of the new user.
     * @param password The password of the new user.
     */
    public void Register(String username, String password) {
        String insertNewUserSQL = "INSERT INTO Users "
                + "(Name, Password, Wins, Losses) VALUES "
                + "(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertNewUserSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether a user with the specified name and password exists in the database.
     * @param username The name of the user to be checked.
     * @param password The password of the user to be checked.
     * @return True, if the given user exists, false, if not.
     */
    public boolean Check(String username, String password){
        String checkSQL = "SELECT * "
                + "FROM Users "
                + "WHERE Name = ? AND Password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(checkSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
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
        String findResultSQL = "SELECT (Win, Lose) "
                + "FROM Users "
                + "WHERE NAME = ?";

        ArrayList<Integer> result = new ArrayList<>(2);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findResultSQL);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            for (int i = 1; rs.next(); i++) {
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
                + "ORDER BY Win DESC LIMIT 5";

        ArrayList<String> result = new ArrayList<>(5);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(findBestPlayersSQL);

            for (int i = 1; rs.next(); i++) {
                result.add(rs.getString(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
