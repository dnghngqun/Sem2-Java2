package session9.controller;

import session9.entity.Users;
import session9.model.LoginDaoImpl;

import java.sql.SQLException;

public class LoginController {
    //call instance of model step 2
    LoginDaoImpl loginDao = new LoginDaoImpl();

    public LoginController() throws SQLException {
    }

    public String loginStatementController(Users users) throws SQLException {
        //step 2
        String message = loginDao.checkLoginStatement(users);
//        return loginDao.checkLoginStatement(users);
        //step 3
        return message;
    }

    public String loginPreparedStatementController(Users users) throws SQLException {
        String message = loginDao.checkLoginPreparedStatement(users);
        return message;
    }

}
