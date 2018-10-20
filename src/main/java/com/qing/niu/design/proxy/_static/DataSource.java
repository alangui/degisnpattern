package com.qing.niu.design.proxy._static;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/15 23:27
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class DataSource {

    private static LinkedList<Connection> connectionList = new LinkedList<Connection>();

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection createNewConnection() throws SQLException{
        return DriverManager.getConnection("url","username","password");
    }

    private DataSource(){
        if (connectionList == null || connectionList.size() == 0){
            for (int i = 0; i < 10; i++){
                try {
                    connectionList.add(createNewConnection());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection getConnection() throws Exception{
        if (connectionList.size() > 0){
            return new ConnectionProxy(connectionList.remove());
        }
        return null;
    }

    public void recoveryConnection(Connection connection){
        connectionList.add(connection);
    }

    public static DataSource getInstance(){
        return DataSourceInstance.dataSource;
    }

    private static class DataSourceInstance{
        private static DataSource dataSource = new DataSource();
    }
}
