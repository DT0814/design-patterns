package observed;

public interface Author {
    void release(Article article);
    boolean register(Reader reader);
    boolean remove(Reader reader);
}
