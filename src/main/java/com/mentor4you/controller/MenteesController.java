package com.mentor4you.controller;

import com.mentor4you.exception.ErrorObject;
import com.mentor4you.exception.MenteeNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.model.DTO.MenteeResponseDTO;
import com.mentor4you.model.DTO.MenteeUpdateRequest;
import com.mentor4you.repository.ContactsToAccountsRepository;
import com.mentor4you.repository.TypeContactsRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import com.mentor4you.service.ContactsToAccountsService;
import com.mentor4you.service.EmailService;
import com.mentor4you.service.MenteeService;
import com.mentor4you.service.TypeContactsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/api/mentees")
public class MenteesController {


    @Autowired
    MenteeService menteesService;
    UserRepository userRepository;
    ContactsToAccountsRepository contactsToAccountsRepository;
    EmailService emailService;
    TypeContactsRepository typeContactsRepository;
    ContactsToAccountsService contactsToAccountsService;
    TypeContactsService typeContactsService;
    JwtProvider jwtProvider;

    public MenteesController(MenteeService menteesService,
                             UserRepository userRepository,
                             ContactsToAccountsRepository contactsToAccountsRepository,
                             EmailService emailService,
                             TypeContactsRepository typeContactsRepository,
                             ContactsToAccountsService contactsToAccountsService,
                             TypeContactsService typeContactsService,
                             JwtProvider jwtProvider) {
        this.menteesService = menteesService;
        this.userRepository = userRepository;
        this.contactsToAccountsRepository = contactsToAccountsRepository;
        this.emailService = emailService;
        this.typeContactsRepository = typeContactsRepository;
        this.contactsToAccountsService = contactsToAccountsService;
        this.typeContactsService = typeContactsService;
        this.jwtProvider = jwtProvider;
    }

    //select mentees by id
    @GetMapping("/{id}")
    Optional<Mentees> getMenteeById(@PathVariable(value = "id") Integer id) {
        return menteesService.getMenteeById(id);
    }

    @Operation(summary = "info about mentees")
    @GetMapping
    List<Mentees> getAllMentee() {
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

    //select mentee by email
    @Operation(summary = "select mentee by email")
    @GetMapping("/getMenteeDTO/")
    ResponseEntity<MenteeResponseDTO> getOneMenteeByEmail
    (HttpServletRequest req4) {

        String token = jwtProvider.getTokenFromRequest(req4);
        String emailMy = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail(emailMy);
        int id = user.getId();
        if (user.getRole().name() == Role.MENTEE.name()) {

            Map<String, String> socialMap = new HashMap<>();

            //Social_networks socialNetworks = socialNetworksRepository.getById(id);
            if (user != null) {

                List<ContactsToAccounts> listConToAkk = contactsToAccountsRepository.findAllByAccounts(id);

                if (listConToAkk.size() > 0) {
                    for (ContactsToAccounts lA : listConToAkk) {
                        String typContact = lA.getTypeContacts().getName();
                        String contData = lA.getContactData();
                        socialMap.put(typContact, contData);
                    }
                } else {
                    socialMap.put("", "");
                }

                MenteeResponseDTO mDTO = new MenteeResponseDTO();
                mDTO.setFirstName(user.getFirst_name());
                mDTO.setLastName(user.getLast_name());
                mDTO.setSocialMap(socialMap);
                return new ResponseEntity<MenteeResponseDTO>(mDTO, HttpStatus.OK);
            }
            return null;
        }
        throw new MenteeNotFoundException("Mentees with id = " + id + " not found");
    }

    @Operation(summary = "update mentee by email")
    @PostMapping("/updateMenteeByEmail")
    public ResponseEntity<String> updateMenteeByEmail(@RequestBody MenteeUpdateRequest request,

                                                      //HttpRequestHandler req3,
                                                      HttpServletRequest req4) {


        //HttpRequestHandler req32 = req3;
        HttpServletRequest req42 = req4;

        String token = req4.getHeader("Authorization");

        String emailLast = request.getEmailLast();
        User userToUpdate = userRepository.findUserByEmail(emailLast);
        int id = userToUpdate.getId();

        if (userToUpdate != null) {
            if (userToUpdate.getRole().name() == Role.MENTEE.name()) {

                userToUpdate.setFirst_name(request.getFirstName());
                userToUpdate.setLast_name(request.getLastName());

                //update email using method from emailService
                //if emails are equals do nothing
                if (userToUpdate.getEmail().equals(request.getEmailNew())) {
                } else {
                    //TODO create new token with new email
                    String reportUpdate = emailService.updateEmail(request.getEmailNew(), id);
                }
                userRepository.save(userToUpdate);

/**
 * Данные с веба могут быть:
 *          такие как в базе
 *          измененные
 *          более полные (новая соц сеть)
 *          другие
 *
 *          Если с фронта пришло что-то типа "LinkedIn":"" значит
 *          нужно удалить эту запись из базы если она была но пока записываем так как есть
 *
 *          Если с фронта пришло что-то типа "LinkedIn":"" и в базе нет
 *          этого typeContact у юзера значит не добавляем
 */

                //создадим мапу для удобства поиска
                Map<String, ContactsToAccounts> socialMapBD = new HashMap<>();

                //находим все записи в табличке ContactsToAccount для одного юзера по id
                List<ContactsToAccounts> listContToAcc = contactsToAccountsRepository.findAllByAccounts(id);

                //Пришло с веба
                //Map<TypeCont, ContData>
                Map<String, String> socialMapWeb = request.getSocialMap();

                //если в базе записи с контактными данными есть то перегоняем в мапу
                if (listContToAcc.size() > 0) {
                    //заполняем мапу
                    for (ContactsToAccounts tp : listContToAcc) {
                        socialMapBD.put(tp.getTypeContacts().getName(), tp);
                    }
                    //теперь есть две мапы, одна с сервера по Id юзера, вторая с веба
                    //нужно их сравнить и применить изменения
                    //идем по мапе с веба(она новая) и применяем изменения
                    Iterator<Map.Entry<String, String>> iter = socialMapWeb.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<String, String> entry = iter.next();
                        /**
                         * если значение пустое например "LinkedIn":"" удаляем пару из карты
                         * но, может быть так что пользователь удалил специально свою ссылку на ресурс
                         * и нужно удалить из базы
                         */
                        //TODO add possibility delete row from table
                        //TODO if "LinkedIn":"" и такого типа у юзера нету то не нужно добавлять, а если есть то обновить

                        if ("".equalsIgnoreCase(entry.getValue())) {
                            //если записи с таким TypeContact у юзера были удаляем их из базы
                            if (socialMapBD.containsKey(entry.getKey())) {
                                int conToAccId = (socialMapBD.get(entry.getKey())).getId();
                                contactsToAccountsRepository.deleteRowById(conToAccId);
                                iter.remove();
                            } else {
                                iter.remove();
                            }
                        }
                        // данные не пустые например "LinkedIn":"hppts://blabla"
                        else {
                            //проверяем была ли у юзера такая соцСеть
                            if (socialMapBD.containsKey(entry.getKey())) {
                                String contactDataFromDB = (socialMapBD.get(entry.getKey()).getContactData());
                                String contactDataFromWEB = entry.getValue();

                                //Может быть так что данные пришли без изменений
                                //данные одинаковые ничего не делаем
                                if (!contactDataFromWEB.equals(contactDataFromDB)) {
                                    //если тип "Соц сеть" одинаковый, но данные разные нужно обновить, а не добавлять
                                    ContactsToAccounts contactsToUpdate = socialMapBD.get(entry.getKey());
                                    contactsToUpdate.setContactData(contactDataFromWEB);
                                    contactsToAccountsRepository.save(contactsToUpdate);
                                }
                            }
                            // если пришли сюда значит юзер добавляет новую социальную сеть
                            // новая для себя или для всего сайта
                            //мы не верим юзеру и новую соцсеть не добавляем
                            else if (typeContactsService.isTypeContactsExist(entry.getKey())) {
                                contactsToAccountsService.createNewContactData(id, entry.getKey(), entry.getValue());
                            } else {
                                //TODO может возникнуть ситуация если юзер хочет сохранить ссылку на соцсеть которой нет
                                throw new MenteeNotFoundException("Mentee wonts to add a network, witch is not in Mentor4you = " + entry.getKey());
                            }
                        }
                    }
                }
                // юзер пустой значит все записи с веба добавляем в базу
                // кроме пустых "LinkedIn":""
                else {
                    for (Map.Entry<String, String> entry : socialMapWeb.entrySet()) {
                        //TODO может возникнуть ситуация если юзер хочет сохранить ссылку на соцсеть которой нет
                        //если пустые   "LinkedIn":""
                        if (entry.getValue().equals("")) {
                            //do nothing
                        } else {
                            if (typeContactsService.isTypeContactsExist(entry.getKey())) {
                                contactsToAccountsService.createNewContactData(id, entry.getKey(), entry.getValue());
                            } else {
                                throw new MenteeNotFoundException("Mentee wonts to add a network, witch is not in Mentor4you = " + entry.getKey());
                            }
                        }
                    }
                }
            } else {
                throw new MenteeNotFoundException("Mentees with id = " + id + " not found");
            }
        } else {
            throw new MenteeNotFoundException("Mentees with id = " + id + " not found");
        }
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }
}






