package pl.odum.workflowodum.word;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class WordMerge {

    private final OutputStream result;
    private final List<InputStream> inputs;
    private XWPFDocument first;

    public WordMerge(OutputStream result) {
        this.result = result;
        inputs = new ArrayList<>();
    }

    public void add(InputStream stream) throws IOException, InvalidFormatException {
        inputs.add(stream);
        OPCPackage srcPackage = OPCPackage.open(stream);
        XWPFDocument src1Document = new XWPFDocument(srcPackage);
        if(inputs.size() == 1){
            first = src1Document;
        } else {
            CTBody srcBody = src1Document.getDocument().getBody();
            first.getDocument().addNewBody().set(srcBody);
        }
    }

    public void doMerge() throws Exception{
        first.write(result);
    }
}
