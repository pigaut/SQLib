package io.github.pigaut.sql;

import org.bukkit.*;
import org.bukkit.plugin.java.*;

public class SQLibPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
//        // Creates a database with a sqlite datasource. 'database.db' will be created in the plugin folder
//        Database sqliteDatabase = SQLib.createDatabase(getDataFolder(), "database");
//
//        // Creates a database with a mysql datasource
//        Database mySqlDatabase = SQLib.createDatabase("monke", "localhost", "3306", "username", "password");
//
//        //Creates a datatable for this database (The table may not exist in the database yet)
//        DataTable playersTable = sqliteDatabase.tableOf("players");
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
//                    .addBatch()
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
//
//                    // sets ? to 1
//                    .withParameter(1)
//
//                    // executes the query, takes in a QueryReader as parameter which can access the ResultSet
//                    .executeQuery(resultSet -> {
//                        if (resultSet.next()) {
//                            System.out.println("The name of the row with id 1 is: " + resultSet.getString(2));
//                        }
//                    });
//
//            // SQL: SELECT * FROM players;
//            playersTable.selectAll()
//                    .executeQuery(resultSet -> {
//                        while (resultSet.next()) { //iterate through the result set
//                            int id = resultSet.getInt(1);
//                            String name = resultSet.getString(2);
//                            int coins = resultSet.getInt(3);
//
//                            System.out.println("id : " + id + " name: " + name + " coins: " + coins);
//                        }
//                    });
//
//            // Prints out the id of the first row if it exists
//            playersTable.selectAll().executeQuery(results -> {
//                if (results.next()) {
//                    System.out.println("The first row is: " + results.getInt(1));
//                }
//            });
//
//            // Prints out the id of the first row if it exists
//            playersTable.selectAll().fetchRow(results -> {
//                System.out.println("The first row is: " + results.getInt(1));
//            });
//
//            // Loops through all the rows and prints out the id
//            playersTable.selectAll().executeQuery(results -> {
//                while (results.next()) {
//                    System.out.println("Row found with id: " + results.getInt(1));
//                }
//            });
//
//            // SQL: DELETE FROM players WHERE id = 3;
//            playersTable.delete("WHERE id = 3").executeUpdate();
//
//            // Loops through all the rows and prints out the id
//            playersTable.selectAll().fetchAllRows(results -> {
//                System.out.println("Row found with id: " + results.getInt(1));
//            });
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
