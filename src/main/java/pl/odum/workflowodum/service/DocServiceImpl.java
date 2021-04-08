package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.*;
import pl.odum.workflowodum.repository.DocRepository;
import pl.odum.workflowodum.repository.PermitRepository;
import pl.odum.workflowodum.utils.DirectoryCreator;
import pl.odum.workflowodum.word.WordMerge;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocServiceImpl implements DocService {
    private final static String RESPONSE_CONTENT_TYPE = "application/octet-stream";
    private final static String HEADER_KEY = "Content-Disposition";
    private final static String HEADER_VALUE = "attachment; filename=";
    private final DocRepository docRepository;
    private final MeetingService meetingService;
    private final DirectoryCreator directoryCreator;
    private final PermitRepository permitRepository;
    private final DownloadLogService downloadLogService;

    @Override
    public List<Doc> getFiles() {
        return docRepository.findAll();
    }

    @Override
    public Doc findByUuid(String id) {
        return docRepository.findByUuid(id);
    }

    @Override
    @Transactional
    public void saveFile(MultipartFile file,Client client,Permit permit,Long userId) throws IOException {
        Doc doc = new Doc();
        doc.setUuid(UUID.randomUUID().toString());
        doc.setDocName(file.getOriginalFilename());
        doc.setDocType(file.getContentType());
        doc.setDateOfAdding(LocalDate.now());
        doc.setTimeOfAdding(LocalTime.now());
        doc.setClient(client);
        doc.setPermit(permit);
        doc.setToRemove(false);
        doc.setUserAddingId(userId);
        doc.setSourcePath(client.getHomePath()+"/"+permit.getType());
        directoryCreator.createDirectoryPermitForClient(doc.getSourcePath());
        File destination=doc.getFile();
        file.transferTo(destination);
        docRepository.save(doc);
    }

    @Transactional
    public void addNoteToMeeting(MultipartFile file, Meeting meeting) throws IOException {

        Doc doc = new Doc();
        doc.setUuid(UUID.randomUUID().toString());
        doc.setDocName(file.getOriginalFilename());
        doc.setDocType(file.getContentType());
        doc.setDateOfAdding(LocalDate.now());
        doc.setTimeOfAdding(LocalTime.now());
        doc.setClient(meeting.getClient());
        doc.setUserAddingId(meeting.getUser().getId());
        doc.setPermit(permitRepository.findByType("meetings"));
        doc.setSourcePath(meeting.getClient().getHomePath() + "/meetings");


        Files.createDirectories(Paths.get(doc.getSourcePath()));


        file.transferTo(doc.getFile());

        List<Doc> docs = meeting.getDoc();
        docs.add(doc);


        docRepository.save(doc);
        meetingService.save(meeting);
    }

    @Override
    @Transactional
    public void addNotesToMeeting(List<MultipartFile> files, Meeting meeting) {
        //todo: usun powiadomienie w momencie zapisu pliku
        files.forEach(file -> {
            try {
                addNoteToMeeting(file, meeting);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void saveFilesFromMultiPart(List<MultipartFile> files,Client client,Permit permit,Long userId) {
        files.forEach(file -> {
            try {
                saveFile(file,client,permit,userId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void download(Doc doc, HttpServletResponse response, User user) {
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.setHeader(HEADER_KEY, HEADER_VALUE + doc.getDocName());
        File file =doc.getFile();
        try (ServletOutputStream os = response.getOutputStream()) {
            os.write(FileUtils.readFileToByteArray(file));
            downloadLogService.addOrEdit(doc,user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SneakyThrows
    public void prepareDocToRemoving(String uuid) {
        Doc doc = docRepository.findByUuid(uuid);
        doc.setToRemove(true);
        doc.setDateOfRemoving(LocalDate.now().plusDays(7));
        docRepository.save(doc);
    }

    @Transactional
    @Override
    public void removeDocs() {
        List<Doc> docsToRemove = docRepository.findAllByDateOfRemovingBeforeAndDateOfRemovingIsNotNull(LocalDate.now());
        docsToRemove.forEach(doc -> {
            try {
                Files.delete(Path.of(doc.getSourcePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            docRepository.deleteByUuid(doc.getUuid());
        });
    }


    //Download merged documents
    @SneakyThrows
    @Override
    public void downloadMergedClientsDocx(Client client, HttpServletResponse response) {
        List<Doc> docs = findAllByClient(client);
        List<Doc> collect = docs.stream().filter(doc -> doc.getSourcePath().endsWith("/meetings")).collect(Collectors.toList());
        mergeDocs(collect,response);
    }

    private List<Doc> findAllByClient(Client client) {
        List<Meeting> meetings = meetingService.findAllByClient(client);
        List<Doc> docs = new ArrayList<>();
        meetings.forEach(m -> docs.addAll(m.getDoc().stream().filter(Objects::nonNull).collect(Collectors.toList())));
        Collections.sort(docs);
        return docs;
    }


    private void mergeDocs(List<Doc>docs,HttpServletResponse response){
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.setHeader(HEADER_KEY, HEADER_VALUE +"merged.docx");
        WordMerge wordMerge=new WordMerge();
        try{
            wordMerge.doIt(docs,response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
