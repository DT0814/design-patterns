package decorator;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class SplitWordInputStream extends FileInputStream {

    public SplitWordInputStream(File file) throws FileNotFoundException {
        super(file);
    }

    public String readOneWord() throws IOException {
        List<Character> word = Lists.newArrayList();
        int res = super.read();
        while (res != -1 && !Character.isWhitespace(res)) {
            word.add((char) res);
            res = super.read();
        }
        return (word.size() > 0) ? word.stream().map(Object::toString).reduce("", String::concat) : "";
    }

    public String[] readAllWords() throws IOException {
        List<String> words = Lists.newArrayList();
        String res = readOneWord();
        while (!res.isBlank()) {
            words.add(res);
            res = readOneWord();
        }
        return words.toArray(new String[0]);
    }

    @Override
    public int read(byte[] bytes, int off, int len) throws IOException {
        int result = super.read(bytes, off, len);
        for (int i = off; i < off + result; i++) {
            if (Character.isWhitespace(bytes[i])) {
                bytes[i] = (byte) System.lineSeparator().charAt(0);
            }
        }
        return result;
    }

    @Override
    public int read() throws IOException {
        int res = super.read();
        if (res != -1 && !Character.isWhitespace(res)) {
            res = Character.LINE_SEPARATOR;
        }
        return res;
    }
}
