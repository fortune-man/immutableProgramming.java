package immutableClass;

import java.util.function.Function;

public class Login {
    private volatile ImmutableLogin state;

    public Login(ImmutableLogin state) {
        super();
        this.state = state;
    }

    public synchronized void change(Function<ImmutableLogin, ImmutableLogin> update){
        state = update.apply(state);
    }

    public ImmutableLogin getState() {
        return state;
    }
}
