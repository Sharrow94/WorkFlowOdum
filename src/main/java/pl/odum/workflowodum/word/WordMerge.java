package pl.odum.workflowodum.word;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import pl.odum.workflowodum.model.Doc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


public class WordMerge {


        @Transactional
        public void doIt(List<Doc>docs, OutputStream os) throws Exception{

            String newPath="/home/mcs/IdeaProjects/odum-docs/clients/"+docs.get(0).getClient().getName().toLowerCase()+"/meetings"+System.currentTimeMillis()+"merged.docx";
            try{
                List<OPCPackage> sources = new ArrayList<>();
                Files.createFile(Path.of(newPath));

                Path copied = Paths.get(newPath);
                Path originalPath = Path.of(docs.get(0).fullPath());
                Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);



                OPCPackage mainPackage = OPCPackage.open(newPath);
                sources.add(mainPackage);
                for (int i = 1; i <docs.size() ; i++) {
                    OPCPackage srcPackage = OPCPackage.open(docs.get(i).fullPath());
                    sources.add(srcPackage);
                }
                XWPFDocument mainDoc = new XWPFDocument(mainPackage);
                CTBody mainBody = mainDoc.getDocument().getBody();

                for (int i = 1; i < sources.size(); i++) {
                    XWPFDocument src = new XWPFDocument(sources.get(i));
                    CTBody srcBody = src.getDocument().getBody();
                    appendBody(mainBody, srcBody);
                }

                mainDoc.write(os);

                sources.forEach(source-> {
                    try {
                        source.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }finally {
                if (Files.exists(Path.of(newPath))){
                    Files.delete(Path.of(newPath));
                }
            }
        }

        private void appendBody(CTBody src, CTBody append) throws Exception {
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
