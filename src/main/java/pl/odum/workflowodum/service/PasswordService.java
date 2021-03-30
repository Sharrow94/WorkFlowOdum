package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.User;

public interface PasswordService {
    boolean checkOldPassword(User user, String oldPassword);
    boolean isPassword (String newPassword, String confirmPassword);
}
