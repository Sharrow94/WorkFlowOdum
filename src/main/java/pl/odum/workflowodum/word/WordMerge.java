package pl.odum.workflowodum.word;

import com.aspose.words.Document;
import com.aspose.words.ImportFormatMode;
import pl.odum.workflowodum.model.Doc;
import java.io.OutputStream;
import java.util.Set;

public class WordMerge {


    public void WordMerge(Set<Doc> docs, OutputStream os) throws Exception {

        Document merged = new Document();
        docs.forEach(doc -> {
            Document toMerge = null;
            try {
                toMerge = new Document(doc.fullPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            merged.appendDocument(toMerge, ImportFormatMode.KEEP_SOURCE_FORMATTING);
        });

        merged.save(os, 20);
    }
}
