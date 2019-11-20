package observed;

import com.google.common.collect.Lists;

import java.util.List;

public class HistoryAuthor implements Author {
    List<Reader> readers = Lists.newArrayList();
    private Article article;

    @Override
    public void release(Article article) {
        readers.forEach((reader) -> reader.update(article));
    }

    @Override
    public boolean register(Reader reader) {
        return readers.add(reader);
    }

    @Override
    public boolean remove(Reader reader) {
        return readers.remove(reader);
    }
}
