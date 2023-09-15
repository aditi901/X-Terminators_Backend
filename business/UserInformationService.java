package com.bts.business;

import com.bts.dao.UserDAO;

class UserInformationService {
    private UserDAO userDAO;

    public UserInformationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void displayUserInfo(String userId) {
        User user = userDAO.getUserByEmail(userId);

        if (user != null) {
            System.out.println("User Information:");
          //  System.out.println("User ID: " + user.getUserId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("User Type: " + user.getRoleId());
        } else {
            System.out.println("User not found.");
        }
    }
}