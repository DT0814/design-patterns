package observed;

public class SecondReader implements Reader {
    @Override
    public void update(Article article) {
        System.out.println(article);
    }
}
