/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vin.dao;

import com.example.vin.common.Tool;
import com.example.vin.database.ConnectionDB;
import com.example.vin.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinhKudo
 */
@Repository
public class UserDao {

    public ArrayList<User> listUser(String username, String email) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "Select * from user where 1 = 1 ";
        if (!Tool.checkNull(username)) {
            sql += " AND USERNAME like ? ";
        }
        if (!Tool.checkNull(email)) {
            sql += " AND EMAIL like ? ";
        }
        System.out.println("sql: " + sql);
        try {
            Connection conn = ConnectionDB.getConnectionDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(username)) {
                pstm.setString(i++, "%" + username + "%");
            }
            if (!Tool.checkNull(email)) {
                pstm.setString(i++, "%" + email + "%");
            }
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("USERNAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setAddress(rs.getString("ADDRESS"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public User add(User user) {
        User u = new User();
        String sql = "INSERT INTO user (USERNAME,EMAIL,ADDRESS) ";
        sql += "                 VALUES(   ?    ,  ?  ,    ?  ) ";
        System.out.println("sql: " + sql);
        try {
            Connection conn = ConnectionDB.getConnectionDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, user.getUsername());
            pstm.setString(i++, user.getEmail());
            pstm.setString(i++, user.getAddress());
            if (pstm.executeUpdate() == 1) {
                u = user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public User dataUser(int id) {
        User user = null;
        String sql = "Select * from user where ID = ? ";
        System.out.println("sql: " + sql);
        try {
            Connection conn = ConnectionDB.getConnectionDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("USERNAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setAddress(rs.getString("ADDRESS"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public User edit(User user) {
        User u = dataUser(user.getId());
        String sql = "UPDATE user SET USERNAME = ? , EMAIL = ? , ADDRESS = ? WHERE ID = ? ";
        System.out.println("sql: " + sql);
        try {
            Connection conn = ConnectionDB.getConnectionDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, user.getUsername());
            pstm.setString(i++, user.getEmail());
            pstm.setString(i++, user.getAddress());
            pstm.setInt(i++, user.getId());
            if (pstm.executeUpdate() == 1) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public User delete(int id) {
        User u = dataUser(id);
        String sql = "DELETE FROM user WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Connection conn = ConnectionDB.getConnectionDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, id);
            if (pstm.executeUpdate() == 1) {
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new User();
    }
}
