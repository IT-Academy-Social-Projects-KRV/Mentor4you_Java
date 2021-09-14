package com.mentor4you.controller;

import com.mentor4you.exception.ErrorObject;
import com.mentor4you.exception.MenteeNotFoundException;
import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.Accounts;
import com.mentor4you.model.DTO.MenteeResponseDTO;
import com.mentor4you.model.DTO.MenteeUpdateRequest;
import com.mentor4you.model.Mentees;
import com.mentor4you.model.Mentors;
import com.mentor4you.model.User;
import com.mentor4you.service.MenteeService;
import com.mentor4you.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@RestController
@RequestMapping("/api/mentees")
public class MenteesController {



        @Autowired
        MenteeService menteesService;

        public MenteesController(MenteeService menteesService) {
                this.menteesService = menteesService;
        }

        //select mentees by id
        @GetMapping("/{id}")
        Optional<Mentees> getMenteeById(@PathVariable(value = "id") Integer id)
        {
            return menteesService.getMenteeById(id);
        }

        @Operation(summary = "info about mentees")
        @GetMapping
        List<Mentees> getAllMentee(){
                return menteesService.getFullInfoAllMentees();
        }

        @ExceptionHandler
        public ResponseEntity<ErrorObject> handleException(MenteeNotFoundException ex) {
                ErrorObject eObject = new ErrorObject();
                eObject.setStatus(HttpStatus.NOT_FOUND.value());
                eObject.setMessage(ex.getMessage());
                eObject.setTimestamp(System.currentTimeMillis());
                return new ResponseEntity<>(eObject, HttpStatus.NOT_FOUND);
        }
/*

        //select mentee by id
        @Operation(summary = "select mentee by id")
        @GetMapping("/getMenteeDTO/{id}")
        ResponseEntity<MenteeResponseDTO> getOneMenteeById
        (@PathVariable(value = "id") Integer id){

                User user = userRepository.findOneById(id);
                if(user.getRole().name()=="MENTEE"){
                        Map<String, String> socialMap = new HashMap<>();


                        Accounts accounts = accountRepository.findAccountById(id);
                        //Social_networks socialNetworks = socialNetworksRepository.getById(id);
                        if(user!=null){

                                List<Links_to_accounts> listLinkToAkk = linksToAccountsRepository.findAllByAccounts(id);

                                if(listLinkToAkk.size()>0){
                                        for (Links_to_accounts lA:listLinkToAkk) {
                                                String nameSocNetwork = lA.getSocial_networks().getName();
                                                String userUrl = lA.getUrl();
                                                socialMap.put(nameSocNetwork,userUrl);
                                        }

                                }       else{socialMap.put("","");}

                                MenteeResponseDTO mDTO = new MenteeResponseDTO();
                                mDTO.setFirstName(user.getFirst_name());
                                mDTO.setLastName(user.getLast_name());
                                mDTO.setEmail(user.getEmail());
                                mDTO.setPhoneNumber(accounts.getPhoneNumber());
                                mDTO.setSocialMap(socialMap);
                                return new ResponseEntity<MenteeResponseDTO>(mDTO, HttpStatus.FOUND);
                        }

                        return  null;
                }
                throw new MenteeNotFoundException("Mentees with id = "+ id +" not found");
        }

        @Operation(summary = "update mentee by id")
        @PostMapping("/updateMenteeById")
        public String updateMenteeById(@RequestBody MenteeUpdateRequest request){

                int id = request.getId();
                User userToUpdate = userRepository.findOneById(id);
                if(userToUpdate!=null){
                        if(userToUpdate.getRole().name()=="MENTEE"){
                                Accounts accountsToUpdate = accountRepository.findAccountById(id);

                                userToUpdate.setFirst_name(request.getFirstName());
                                userToUpdate.setLast_name(request.getLastName());

                                //update email using method from emailService
                                //if emails are equals do nothing
                                if(userToUpdate.getEmail().equals(request.getEmail())){}
                                else{
                                        String reportUpdate = emailService.updateEmail(request.getEmail(), id);
                                }
                                accountsToUpdate.setPhoneNumber(request.getPhoneNumber());

                                Map<String, String> socialMap = request.getSocialMap();

                                for (Map.Entry<String, String> entry : socialMap.entrySet()) {
                                        Links_to_accounts linksToAccounts = new Links_to_accounts();

                                        int idSocNet = socialNetworksRepository.findByName(entry.getKey()).getId();

                                        System.out.println(entry.getKey() + ":" + entry.getValue());
                                }



                        }
                        else {
                                throw new MenteeNotFoundException("Mentees with id = "+ id +" not found");
                        }

                }
                else {throw new MenteeNotFoundException("Mentees with id = "+ id +" not found");
                }









        }

*/




}
