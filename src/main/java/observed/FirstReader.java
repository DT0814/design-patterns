package observed;

public class FirstReader implements Reader {
    @Override
    public void update(Article article) {
        System.out.println(article);
    }
}
