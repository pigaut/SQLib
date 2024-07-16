package com.github.pigaut.sqlib;

import com.github.pigaut.lib.sql.*;
import com.github.pigaut.lib.sql.database.statement.*;
import com.github.pigaut.lib.sql.database.table.*;
import org.bukkit.*;
import org.bukkit.plugin.java.*;

public class SQLibPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Database database = SQLib.createDatabase(this, "database.db");

        DataTable players = database.tableOf("players");
        DataTable users = new GenericDataTable(database, "users");

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            players.createIfNotExists(
                    "id INTEGER PRIMARY KEY",
                    "name VARCHAR(30)",
                    "coins INT");

            players.clear();

            players.insertInto("id", "name", "coins")
                    .withParameter(1)
                    .withParameter("Alice")
                    .withParameter(100)
                    .addBatch()
                    .withParameter(2)
                    .withParameter("Bob")
                    .withParameter(200)
                    .executeBatch();

            players.insertInto("id", "name", "coins")
                    .withParameter(3)
                    .withParameter("Charlie")
                    .withParameter(300)
                    .executeUpdate();

            // Update a row in 'players' table
            players.set("coins = 150 WHERE id = 2").executeUpdate();

            // Select and print all data from 'players' table
            System.out.println("Printing all players:");
            players.selectAll().executeRowQuery(result -> {
                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Coins: " + result.getInt("coins"));
                System.out.println("------------------------");
            });

            // Delete a row from 'players' table
            players.delete("WHERE id = 3").executeUpdate();

            // Select and print all data from 'players' table after deletion
            System.out.println("Printing players after deletion:");
            players.selectAll().executeRowQuery(result -> {

                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Coins: " + result.getInt("coins"));
                System.out.println("------------------------");
            });

            // Rename the 'players' table to 'users'
            players.rename("users");

            // Clear all data from 'players' table before disabling
            players.clear();

            players.selectAll().executeQuery((RowQueryReader) results -> {
                System.out.println("ID: " + results.getInt("id"));
                System.out.println("Name: " + results.getString("name"));
                System.out.println("Coins: " + results.getInt("coins"));
                System.out.println("------------------------");
            });

            users.drop();

        });

    }

    @Override
    public void onDisable() {}

}
