package pl.odum.workflowodum.word;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.repository.DocRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
@AllArgsConstructor
public class PdfMerge {
    private final static String createPdfScript = "/home/mcs/IdeaProjects/odum-docs/scripts/convertToPdf.sh";
    private final static String mergePdfsScript = "/home/mcs/IdeaProjects/odum-docs/scripts/mergePdfs.sh";
    private final DocRepository docRepository;

    @Transactional
    public void mergeToPdf(Client client, OutputStream os) throws IOException {
        List<Doc> docs = docRepository.findAllForClientMeetings(client);

        String source = docs.get(0).getSourcePath()+"/pdf";
        Files.createDirectories(Paths.get(source));

        docs.forEach(d->{
            try {
                createPdfFromDoc(d, source);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        StringBuilder sourceFiles = new StringBuilder();

        docs.forEach(d->{
            sourceFiles.append(d.fullPath()).append(" ");
        });


        String fileName = "merged";
        String toPdf = source+"/"+fileName;
        System.out.println(toPdf);

        String files = sourceFiles.toString().trim();
        System.out.println(files);

        String cmd = mergePdfsScript+" "+files+" "+toPdf;
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec(cmd);
        try {
            long start = System.currentTimeMillis();
            exec.waitFor();
            long stop = System.currentTimeMillis();
            System.out.println((stop-start)+"ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        File file = new File(toPdf + ".pdf");
        FileInputStream fileInputStream = new FileInputStream(toPdf+".pdf");
        os.write(fileInputStream.readAllBytes());

//        Files.delete(Paths.get(source));
    }


    public void createPdfFromDoc(Doc doc, String source) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String cmd = createPdfScript+" "+source+" "+doc.fullPath();
        Process exec = runtime.exec(cmd);

        try {
            exec.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
