package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.DownloadLog;
import pl.odum.workflowodum.model.User;

import java.util.List;

@Repository
public interface DownloadLogRepository extends JpaRepository<DownloadLog,Long> {

    @Query(value = "select d from DownloadLog d where d.doc=?1 and d.user=?2")
    DownloadLog findForDocAndUser(Doc doc, User user);
    List<DownloadLog>findAllByDoc(Doc doc);
}
