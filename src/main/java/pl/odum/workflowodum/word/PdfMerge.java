package pl.odum.workflowodum.word;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.repository.DocRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@AllArgsConstructor
public class PdfMerge {
    private final static String CREATE_PDF_SCRIPT = "/home/mcs/IdeaProjects/odum-docs/scripts/convertToPdf.sh";
    private final static String MERGE_PDFS_SCRIPT = "/home/mcs/IdeaProjects/odum-docs/scripts/mergePdfs.sh";
    private final static String MERGED_FILE_NAME = "merged.pdf";
    private final DocRepository docRepository;

    @Transactional
    @Async
    public void mergeToPdf(Client client, OutputStream os, Long userId) throws IOException {
        List<Doc> docs = docRepository.findAllForClientMeetings(client);
        String pdfDir = docs.get(0).getSourcePath() + "/pdf"+userId;

        try {
            Files.createDirectories(Paths.get(pdfDir));

            createPdfFromAllDocs(docs, pdfDir);

//        String sourceFiles = getPdfFileNames(docs);
//        System.out.println(sourceFiles);

            String toPdf = pdfDir + "/" + MERGED_FILE_NAME;

            createMerged(pdfDir, toPdf);

            os.write(FileUtils.readFileToByteArray(new File(toPdf)));

        }catch (NoSuchFileException e){
//            System.out.println("zasady sa po to zeby je lamac");
        }
        finally {
            deleteDirectoryRecursion(Paths.get(pdfDir));
        }
    }

    private void createPdfFromAllDocs(List<Doc> docs, String pdfDir) {
        AtomicInteger counter= new AtomicInteger();
        docs.forEach(d -> {
            try {
                createPdfFromDoc(d, pdfDir, counter.getAndIncrement());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private String getPdfFileNames(List<Doc> docs){
        StringBuilder sb = new StringBuilder();
        docs.forEach(d -> {
            String pdfDir = d.getSourcePath()+"/pdf";
            String sourceFile = pdfDir+"/"+d.getUuid()+".pdf";
            sb.append(sourceFile).append(" ");
        });
        return sb.toString().trim();
    }

    private void createMerged(String source, String toPdf) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String cmd = MERGE_PDFS_SCRIPT + " " + source + "/*.pdf " + toPdf;
        System.out.println(cmd);
        Process exec = runtime.exec(cmd);
        try {
            exec.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createPdfFromDoc(Doc doc, String source, int counter) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String cmd = CREATE_PDF_SCRIPT + " " + source + " " + doc.fullPath();
        System.out.println(cmd);
        Process exec = runtime.exec(cmd);

        try {
            exec.waitFor();
            File file = new File(source + "/" + doc.getUuid() + ".pdf");
            if(file.exists()){
                file.renameTo(new File(source + "/" + counter + ".pdf"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void deleteDirectoryRecursion(Path path) throws IOException {
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteDirectoryRecursion(entry);
                }
            }
        }
        Files.delete(path);
    }

}
