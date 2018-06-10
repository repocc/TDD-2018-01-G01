package sample;

import java.util.HashMap;
import java.util.Map;

public class UserLogin {

    private Map<Integer, User> users;
    private Map<String, Integer> names;
    private Map<String, Integer> passwords;

    public UserLogin() {
        Parser user_parser = new Parser();
        users = user_parser.parseUsersList();
        names = new HashMap<String, Integer>();
        passwords = new HashMap<String, Integer>();
        for (int id: users.keySet()){
            String name = users.get(id).getName();
            String pass = users.get(id).getPassword();
            names.put(name,id);
            passwords.put(pass, id);
        }
    }

    public Boolean validateUser(String username, String password) {
        if (names.containsKey(username) && passwords.containsKey(password)) {
            return names.get(username).equals(passwords.get(password));
        } else {
            return false;
        }
    }
}
