package pl.odum.workflowodum;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.service.MeetingService;
import pl.odum.workflowodum.service.UserService;

@Component
@AllArgsConstructor
public class CurrentUser {
    private final UserService userService;
    private final MeetingService meetingService;

    public boolean isCurrentUsersMeeting(Long meetingId, Authentication auth){
        Meeting meeting = meetingService.findById(meetingId);
        User user = userService.findByUserName(auth.getName());
        return meeting.getUser().getId().equals(user.getId());
    }
}
