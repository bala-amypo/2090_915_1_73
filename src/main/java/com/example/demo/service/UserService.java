@Service
public class UserService {

    public String registerUser(String username, String password, String role) {
        return "User registered successfully";
    }

    public String authenticate(String username, String password) {
        return "JWT_TOKEN_SAMPLE";
    }
}
