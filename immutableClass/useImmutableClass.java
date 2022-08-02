package immutableClass;

import java.util.concurrent.ConcurrentHashMap;

public class useImmutableClass {

    private final ConcurrentHashMap<String, ImmutableLogin> mapImmutableLogin =
            new ConcurrentHashMap<String, ImmutableLogin>();

    private  final ConcurrentHashMap<String,MutableLogin> mapMutableLogin
            = new ConcurrentHashMap<String,MutableLogin>();

    public void changeMutableLogin(){
        MutableLogin mutableLogin = mapMutableLogin.get("loginA");
        synchronized (mutableLogin){
            mutableLogin.setPassword("newPassword");
        }
    }

    public void changeImmutableLogin(){
        mapImmutableLogin.compute("loginA", (String key, ImmutableLogin login) -> {
            return new ImmutableLogin(login.getUserName(), "newPassword");
        });
    }

    public void readImmutableLogin(){
        ImmutableLogin immutableLogin = mapImmutableLogin.get("loginA");
    }
}
