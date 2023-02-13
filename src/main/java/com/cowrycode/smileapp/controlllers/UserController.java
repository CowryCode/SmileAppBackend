package com.cowrycode.smileapp.controlllers;


import com.cowrycode.smileapp.controlllers.ChatController.ChatObjectModel;
import com.cowrycode.smileapp.models.*;
import com.cowrycode.smileapp.models.featuresmood.PocketBuddyMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.SmileGramMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.TribeMoodDTO;
import com.cowrycode.smileapp.models.metamodels.LeaderBoard;
import com.cowrycode.smileapp.models.metamodels.UnreadTribeMessagesDTO;
import com.cowrycode.smileapp.services.*;
import com.cowrycode.smileapp.utilities.TextExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/smile")
public class UserController {

    private final MoodService moodService;
    private final MyTribeMessageService myTribeMessageService;
    private final UserProfileService userProfileService;
    private final AuthService authService;

    private final ApiCallService apiCallService;

    Logger logger = LoggerFactory.getLogger(UserController.class);
    public UserController(MoodService moodService, MyTribeMessageService myTribeMessageService,
                          UserProfileService userProfileService, AuthService authService, ApiCallService apiCallService) {
        this.moodService = moodService;
        this.myTribeMessageService = myTribeMessageService;
        this.userProfileService = userProfileService;
        this.authService = authService;
        this.apiCallService = apiCallService;
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/create-user")
    public ResponseEntity<UserProfileDTO> createUser(@RequestBody @Validated UserProfileDTO userProfileDTO , HttpServletRequest request){
        try{
            UserProfileDTO savedProfile = userProfileService.saveUserprofile(userProfileDTO);
            if(savedProfile != null){
                return new ResponseEntity<>(savedProfile, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @GetMapping("/get-user")
    public ResponseEntity<UserProfileDTO> getUser(HttpServletRequest request){
        try{
            UserProfileDTO profile = userProfileService.getprofile(request);
            if(profile != null){
                return new ResponseEntity<>(profile, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PostMapping("/add-device")
    public ResponseEntity<UserProfileDTO> addUserDevice(@RequestBody @Validated TextExchange textExchange , HttpServletRequest request){
        UserProfileDTO updatedProfile = userProfileService.savedDeviceID(authService.getIdentifier(request), textExchange.getValue());
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @PostMapping("/tribemessage")
    public ResponseEntity<MyTribeMessageDTO> saveTribeMessage(@RequestBody @Validated MyTribeMessageDTO myTribeMessageDTO, HttpServletRequest request){
        MyTribeMessageDTO savedMessage = myTribeMessageService.saveTribeMessage(myTribeMessageDTO, authService.getIdentifier(request));
        if(savedMessage != null){
            return new ResponseEntity<>(savedMessage, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PostMapping("/read-message")
    public ResponseEntity<UnreadTribeMessagesDTO> readTribeMessage(@RequestBody @Validated MyTribeMessageDTO myTribeMessageDTO, HttpServletRequest request){
        List<MyTribeMessageDTO> unreadmessages = myTribeMessageService.readTribeMessage(myTribeMessageDTO, authService.getIdentifier(request));
        if(unreadmessages != null){
            return new ResponseEntity<>(new UnreadTribeMessagesDTO(unreadmessages), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @GetMapping("/get-tribemessages")
    public ResponseEntity<UnreadTribeMessagesDTO> getUnreadTribeMessages(HttpServletRequest request){
        List<MyTribeMessageDTO> messages = myTribeMessageService.getTribeMessage(authService.getIdentifier(request), false);
        if(messages != null){
            return new ResponseEntity<>(new UnreadTribeMessagesDTO(messages), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }
    }
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/get-unapproved-tribemessages")
    public ResponseEntity<List<MyTribeMessageDTO>> getUnApprovedTribeMessages(HttpServletRequest request){
        List<MyTribeMessageDTO> messages = myTribeMessageService.getUnapprovedTribeMessage();
        if(messages != null){
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/approved-message")
    public ResponseEntity<Boolean> approvedTribeMessage(HttpServletRequest request){
        Long messagID = authService.getIdentifierLong(request);
        boolean response = myTribeMessageService.approveTribeMessage(messagID);
        if(response){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @GetMapping("/get-read-tribemessages")
    public ResponseEntity<UnreadTribeMessagesDTO> getReadTribeMessages(HttpServletRequest request){
        List<MyTribeMessageDTO> messages = myTribeMessageService.getTribeMessage(authService.getIdentifier(request), true);
        if(messages != null){
            return new ResponseEntity<>(new UnreadTribeMessagesDTO(messages), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PostMapping("/smilegram-mood")
    public ResponseEntity<SmileGramMoodDTO> saveSmilegramMood(@RequestBody @Validated SmileGramMoodDTO smileGramMoodDTO, HttpServletRequest request){
        System.out.println("THe mood saved : " + smileGramMoodDTO.toString());
        SmileGramMoodDTO savedsmilegram = moodService.saveSmileGramMood(smileGramMoodDTO, authService.getIdentifier(request));
        if(savedsmilegram != null){
            return new ResponseEntity<>(savedsmilegram, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @PostMapping("/pocketbuddy-mood")
    public ResponseEntity<PocketBuddyMoodDTO> savePocketbuddyMood(@RequestBody @Validated PocketBuddyMoodDTO pocketBuddyMoodDTO, HttpServletRequest request){
        PocketBuddyMoodDTO savedpocketbuddy = moodService.savePocketBuddyMood(pocketBuddyMoodDTO, authService.getIdentifier(request));
        if(savedpocketbuddy != null){
            return new ResponseEntity<>(savedpocketbuddy, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @PostMapping("/tribe-mood")
    public ResponseEntity<TribeMoodDTO> savePocketbuddyMood(@RequestBody @Validated TribeMoodDTO tribeMoodDTO, HttpServletRequest request){
        TribeMoodDTO savedtribemood = moodService.saveTribemood(tribeMoodDTO, authService.getIdentifier(request));
        if(savedtribemood != null){
            return new ResponseEntity<>(savedtribemood, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @GetMapping("/leaderboard")
    public ResponseEntity<LeaderBoard> getLeaderboard(HttpServletRequest request){
        LeaderBoard board = userProfileService.sortPerformance(authService.getIdentifier(request));
        if(board != null){
            return new ResponseEntity<>(board, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @PostMapping("/empathyrequest")
    public ResponseEntity<Boolean> pushEmpathyRequest(@RequestBody @Validated EmpathyRequestDTO empathyRequestDTO , HttpServletRequest request){
        Boolean req = userProfileService.requestEmpathicMessage(authService.getIdentifier(request), empathyRequestDTO);
        if(req){
            return new ResponseEntity<>(req, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(req, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PostMapping("/sendTribemessage")
    public ResponseEntity<UnrepliedTribeCalls> empathyRequestReply(@RequestBody @Validated EmpathyRequestDTO empathyRequestDTO , HttpServletRequest request){
        List<EmpathyRequestDTO> req = userProfileService.replyEmpathicMessage(authService.getIdentifier(request), empathyRequestDTO);
        if(req != null){
            return new ResponseEntity<>( new UnrepliedTribeCalls(req), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @GetMapping("/get-pending-requests") // For Share Empathy
    public ResponseEntity<UnrepliedTribeCalls> pullrequests(HttpServletRequest request){
        List<EmpathyRequestDTO> req = userProfileService.getTribeRequests(authService.getIdentifier(request));
        if(req != null){
            return new ResponseEntity<>(new UnrepliedTribeCalls(req), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PostMapping("/savequestionnaire")
    public ResponseEntity<QuestionnaireBMIScaleDTO> saveQuestionnaire(@RequestBody @Validated QuestionnaireBMIScaleDTO questionnaireBMIScaleDTO , HttpServletRequest request){
        QuestionnaireBMIScaleDTO req = userProfileService.saveBMIScale(authService.getIdentifier(request), questionnaireBMIScaleDTO);
        if(req != null){
            return new ResponseEntity<>( req, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }
    }

//    @PostMapping("/chat")
//    public ResponseEntity<String> chat(@RequestBody @Validated TextExchange chat , HttpServletRequest request){
//        ChatObjectModel chatObject = userProfileService.sendChat(authService.getIdentifier(request), chat.getValue());
//        if(chatObject != null){
//            return new ResponseEntity<>(chatObject.getChatContent(), HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
//        }
//    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody @Validated TextExchange chat , HttpServletRequest request){
        String response = apiCallService.callChatGPT(chat.getValue());
        if(response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PostMapping("/pushnotification")
    public ResponseEntity<Boolean> getLeaderboard(@RequestBody @Validated TextExchange textExchange , HttpServletRequest request){
        Boolean push = userProfileService.pushNotification(authService.getIdentifier(request), textExchange.getValue1(), textExchange.getValue2());
        if(push){
            return new ResponseEntity<>(push, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(push, HttpStatus.NOT_IMPLEMENTED);
        }

    }
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/get-all-appdownloaded-users")
    public ResponseEntity<List<UserProfileDTO>> getAllAppDownloadedUsers(HttpServletRequest request){
        List<UserProfileDTO> users = userProfileService.getAllAppDownloadedUsers(authService.getIdentifier(request));
        if(users != null){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(users, HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/get-all-app-not-downloaded-users")
    public ResponseEntity<List<UserProfileDTO>> getAllAppNotDownloadedUsers(HttpServletRequest request){
        List<UserProfileDTO> users = userProfileService.getALlUsersNotDownloadedApp(authService.getIdentifier(request));
        if(users != null){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(users, HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserProfileDTO>> getAllUsers(HttpServletRequest request){
        List<UserProfileDTO> users = userProfileService.getALlUsers(authService.getIdentifier(request));
        if(users != null){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(users, HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/get-next-participantID/{opinoID}")
    public ResponseEntity<String> getNextParticipantID(@PathVariable Long opinoID, HttpServletRequest request){
        String pID = userProfileService.getNextParticipantID(opinoID);
        if(pID != null){
            return new ResponseEntity<>(pID, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(pID, HttpStatus.NOT_IMPLEMENTED);
        }

    }

}
