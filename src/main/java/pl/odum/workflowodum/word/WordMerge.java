package pl.odum.workflowodum.word;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class WordMerge {


    public void doIt(OutputStream os) throws Exception {
        List<OPCPackage> sources=new ArrayList<>();
        OPCPackage src3Package=OPCPackage.open("/home/maciej/Pobrane/merged (10).docx");
        sources.add(src3Package);
        OPCPackage src1Package = OPCPackage.open("/home/maciej/Pobrane/file-sample_1MB.docx");
        sources.add(src1Package);
        OPCPackage src2Package = OPCPackage.open("/home/maciej/Pobrane/file-sample_100kB.docx");
        sources.add(src2Package);

        XWPFDocument mainDoc = new XWPFDocument(src3Package);
        CTBody mainBody = mainDoc.getDocument().getBody();

        for (int i = 1; i < sources.size(); i++) {
            XWPFDocument src = new XWPFDocument(sources.get(i));
            CTBody srcBody = src.getDocument().getBody();
            appendBody(mainBody, srcBody);
        }

        mainDoc.write(os);
    }

    private static void appendBody(CTBody src, CTBody append) throws Exception {
        XmlOptions optionsOuter = new XmlOptions();
        optionsOuter.setSaveOuter();
        String appendString = append.xmlText(optionsOuter);
        String srcString = src.xmlText();
        String prefix = srcString.substring(0, srcString.indexOf(">") + 1);
        String mainPart = srcString.substring(srcString.indexOf(">") + 1, srcString.lastIndexOf("<"));
        String sufix = srcString.substring(srcString.lastIndexOf("<"));
        String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));
        CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + addPart + sufix);
        src.set(makeBody);
    }
}
