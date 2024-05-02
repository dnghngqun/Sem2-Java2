package session9.model;

import session9.entity.Users;

import java.sql.*;

public class LoginDaoImpl implements LoginDAO{
    private static final Connection conn;

    static {
        try {
            conn = MySQLConnectionDB.getMyConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    PreparedStatement pstm = null;
    Statement stm = null;
    public LoginDaoImpl() throws SQLException{}

    @Override
    public String checkLoginStatement(Users users) throws SQLException {
        //Step1: query, step2: Create Statement, step3: ResultSet, Step4:While

        stm = conn.createStatement();
        //select * from users where username = 'admin' and password = 'admin'
        String query = "Select * from users where username like '"+ users.getUsername() + "' and password like '" + users.getPassword() + "'";

        ResultSet rs = stm.executeQuery(query);

        if(rs.next()) {
            String id = rs.getString("user_id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            return id + " " + username + " " + password;
        }

        return "Not in the database";
    }

    @Override
    public String checkLoginPreparedStatement(Users users) throws SQLException {

        String query = "Select * from users where username like ? and password like ?";
        pstm = conn.prepareStatement(query);

        pstm.setString(1, users.getUsername());
        pstm.setString(2, users.getPassword());
        ResultSet rs = pstm.executeQuery();
        if(rs.next()) {
            String id = rs.getString("user_id");
            String username = rs.getString("username");
            String password = rs.getString("password");

            return id + " " + username + " " + password;
        }

        return "Not in the database";
    }

//    public static void main(String[] args) throws SQLException {
//        Users users = new Users(1, "admin", "admin");
//
//        LoginDaoImpl loginDao = new LoginDaoImpl();
//        System.out.println(loginDao.checkLoginPreparedStatement(users));
//        System.out.println(loginDao.checkLoginStatement(users));
//
//    }
}
