package space.pixelsg.moovies.utils;

public class Data<T> {
    public State state;
    public T data;
    public Throwable throwable;

    private Data(State state, T data, Throwable throwable) {
        this.state = state;
        this.data = data;
        this.throwable = throwable;
    }

    public static <T> Data<T> success(T data) {
        return new Data<>(
                State.SUCCESS,
                data,
                null
        );
    }

    public static <T> Data<T> error(Throwable throwable) {
        return new Data<>(
                State.ERROR,
                null,
                throwable
        );
    }

    public static <T> Data<T> loading() {
        return new Data<>(
                State.LOADING,
                null,
                null
        );
    }

    public enum State {
        LOADING,
        SUCCESS,
        ERROR
    }
}
