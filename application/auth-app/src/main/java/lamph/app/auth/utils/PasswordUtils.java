package lamph.app.auth.utils;

public interface PasswordUtils {

    String hashPassword(String password);

    boolean matchPassword(String password, String hashedPassword);
}
