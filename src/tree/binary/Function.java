package tree.binary;

public interface Function<S, T> {
    S apply(T elem);
}
