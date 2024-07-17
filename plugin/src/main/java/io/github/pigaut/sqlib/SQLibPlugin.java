package io.github.pigaut.sqlib;

import io.github.pigaut.lib.sql.*;
import org.bukkit.*;
import org.bukkit.plugin.java.*;

public class SQLibPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
//        // Creates a sqlite database in the plugin folder
//        Database database = SQLib.createDatabase(getDataFolder(), "database");
//        //Creates a datatable object for this database
//        DataTable playersTable = database.tableOf("players");
//
//        //Asynchronous bukkit task to make requests to the database
//        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
//
//            // SQL: CREATE TABLE IF NOT EXISTS players (id INTEGER PRIMARY KEY, name VARCHAR(30), coins INT);
//            playersTable.createIfNotExists(
//                    "id INT PRIMARY KEY",
//                    "name VARCHAR(30)",
//                    "coins INT");
//
//            // SQL: DELETE FROM players;
//            playersTable.clear();
//
//            // SQL: INSERT INTO players (id, name, coins) VALUES (?, ?, ?);
//            playersTable.insertInto("id", "name", "coins")
//                    .withParameter(0)
//                    .withParameter("Alice")
//                    .withParameter(100)
//                    .executeUpdate();
//
//            // insert multiple rows into the database
//            playersTable.insertInto("id", "name", "coins")
//                    .withParameter(1)
//                    .withParameter("Mark")
//                    .withParameter(200)
//                    .addBatch()
//                    .withParameter(2)
//                    .withParameter("Bob")
//                    .withParameter(300)
//                    .addBatch()
//                    .withParameter(3)
//                    .withParameter("Charlie")
//                    .withParameter(400)
//                    .executeBatch();
//
//            // SQL: UPDATE players SET coins = ? WHERE id = ?;
//            playersTable.set("coins = ? WHERE id = ?")
//                    .withParameter(1000)
//                    .withParameter(2)
//                    .executeUpdate();
//
//            //SQL: SELECT * FROM players WHERE id = ?;
//            playersTable.select("WHERE id = ?")
//                    .withParameter(1) // sets ? to 1
//                    .executeQuery(resultSet -> { // executes the query, takes in a QueryReader which can access the results of this query
//                        String name = resultSet.getString(2);
//                    });
//
//            // SQL: SELECT * FROM players;
//            playersTable.selectAll().executeQuery(resultSet -> {
//                while (resultSet.next()) { //iterate through the result set
//                    int id = resultSet.getInt(1);
//                    String name = resultSet.getString(2);
//                    int coins = resultSet.getInt(3);
//                    System.out.println("id : " + id + " name: " + name + " coins: " + coins);
//                }
//            });
//
//            // SQL: SELECT * FROM players;
//            playersTable.selectAll().executeRowQuery(resultSet -> { //This does the exact thing as above but you don't have to iterate the results yourself
//                int id = resultSet.getInt(1);
//                String name = resultSet.getString(2);
//                int coins = resultSet.getInt(3);
//            });
//
//            // SQL: DELETE FROM players WHERE id = 3;
//            playersTable.delete("WHERE id = 3").executeUpdate();
//
//            // SQL: ALTER TABLE players RENAME TO users;
//            playersTable.rename("users");
//
//            // SQL: ALTER TABLE users ADD monkeys INT;
//            playersTable.addColumn("monkeys INT");
//
//            // SQL: ALTER TABLE users RENAME COLUMN monkeys TO monkes;
//            playersTable.renameColumn("monkeys", "monkes");
//
//            // SQL: DROP TABLE users;
//            playersTable.drop();
//
//        });
    }

    @Override
    public void onDisable() {}

}
