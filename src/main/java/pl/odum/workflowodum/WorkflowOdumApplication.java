package pl.odum.workflowodum;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import pl.odum.workflowodum.service.DocService;
import pl.odum.workflowodum.service.NotificationService;

@SpringBootApplication
@AllArgsConstructor
public class WorkflowOdumApplication {

    private final NotificationService notificationService;
    private final DocService docService;


    public static void main(String[] args) {
        SpringApplication.run(WorkflowOdumApplication.class, args);
    }


    @Scheduled(cron = "0 0 0 * * *")
    void executeTasks(){
        notificationService.save();
        docService.removeDocs();
    }
}
