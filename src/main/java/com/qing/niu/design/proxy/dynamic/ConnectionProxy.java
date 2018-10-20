package com.qing.niu.design.proxy.dynamic;

import com.qing.niu.design.proxy._static.DataSource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/15 23:47
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class ConnectionProxy implements InvocationHandler{

    private Connection connection;

    public ConnectionProxy(Connection connection){
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Connection.class.isAssignableFrom(proxy.getClass()) && method.getName().equals("close")){
            DataSource.getInstance().recoveryConnection(connection);
            return null;
        }else {
            return method.invoke(connection,args);
        }
    }

    public Connection getConnnectionProxy(){
        return (Connection) Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{Connection.class},this);
    }
}
