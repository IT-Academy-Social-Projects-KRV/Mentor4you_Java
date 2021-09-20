package com.mentor4you.service;

import com.mentor4you.exception.MenteeNotFoundException;
import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.model.DTO.MenteeResponseDTO;
import com.mentor4you.repository.*;
import com.mentor4you.model.DTO.MentorGeneralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Transactional
public class MentorService {

    @Autowired
    MentorsToCategory mentorsToCategory;
    AccountRepository accountRepository;
    MentorRepository mentorRepository;
    UserRepository userRepository;
    EmailService emailService;
    ContactsToAccountsRepository contactsToAccountsRepository;
    TypeContactsService typeContactsService;
    ContactsToAccountsService contactsToAccountsService;

    public MentorService(MentorsToCategory mentorsToCategory, AccountRepository accountRepository, MentorRepository mentorRepository, UserRepository userRepository, EmailService emailService, ContactsToAccountsRepository contactsToAccountsRepository, TypeContactsService typeContactsService, ContactsToAccountsService contactsToAccountsService) {
        this.mentorsToCategory = mentorsToCategory;
        this.accountRepository = accountRepository;
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.contactsToAccountsRepository = contactsToAccountsRepository;
        this.typeContactsService = typeContactsService;
        this.contactsToAccountsService = contactsToAccountsService;
    }

    //select all mentor
    public List<Mentors> getFullInfoAllMentors(){
        int theMentors = accountRepository.findByRole(Role.MENTOR).size();
        if(theMentors!=0){
            return mentorRepository.findAll();
        }
        throw new MentorNotFoundException("Mentors not found");

    }

    //    select mentor by id
    public Optional<Mentors> getMentorById(int id){

        Optional<Mentors> theMentor = mentorRepository.findById(id).stream().filter(e->e.getId()==id).findFirst();
        if(theMentor.isPresent()) {
            return theMentor;
        }

        throw new MentorNotFoundException("Mentor with id = "+ id +" not found");

    }
    // не фінальна версія
    public ResponseEntity<MentorGeneralDTO> getByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        MenteeResponseDTO mDTO = new MenteeResponseDTO();
            try
            {
                if(user ==null||user.getRole()!=Role.MENTOR)return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;

                int id = user.getId();
                mDTO.setFirstName(user.getFirst_name());
                mDTO.setLastName(user.getLast_name());
                mDTO.setEmail(user.getEmail());


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

                        mDTO.setFirstName(user.getFirst_name());
                        mDTO.setLastName(user.getLast_name());
                        mDTO.setSocialMap(socialMap);

                    }
                Mentors m = mentorRepository.getById(user.getId());

                MentorGeneralDTO dto =
                        new MentorGeneralDTO(
                                m.getDescription(),
                                m.isShowable_status(),
                                m.isOnline(),
                                m.isOfflineIn(),
                                m.isOfflineOut(),
                                m.getEducations(),
                                m.getCertificats(),
                                m.getMentors_to_categories(),
                                mDTO
                        );
                return new ResponseEntity<MentorGeneralDTO>(dto, HttpStatus.OK);
            }
            catch (EntityNotFoundException e)
            {
                return new ResponseEntity<MentorGeneralDTO>(HttpStatus.NOT_FOUND);
            }

    }


    public ResponseEntity<String> updateGeneralDataMentors(String email , MentorGeneralDTO up)
    {
        User user = userRepository.findUserByEmail(email);
        if (user == null || user.getRole() != Role.MENTOR)
            return new ResponseEntity<String>("update was Unsuccessful", HttpStatus.NOT_FOUND);
        ////////////////////////////////////////////////////////////
        String emailNew = up.getAccountInfo().getEmail();
        User userToUpdate = user;
        int id = userToUpdate.getId();


            if (userToUpdate != null)
            {

                userToUpdate.setFirst_name(up.getAccountInfo().getFirstName());
                userToUpdate.setLast_name(up.getAccountInfo().getLastName());

                //update email using method from emailService
                //if emails are equals do nothing
                if (!userToUpdate.getEmail().equals(emailNew)) {
                    //TODO create new token with new email
                    String reportUpdate = emailService.updateEmail(emailNew, id);
                }
                userRepository.save(userToUpdate);
                Map<String, ContactsToAccounts> socialMapBD = new HashMap<>();

                //находим все записи в табличке ContactsToAccount для одного юзера по id
                List<ContactsToAccounts> listContToAcc = contactsToAccountsRepository.findAllByAccounts(id);

                //Пришло с веба. Map<TypeCont, ContData>
                Map<String, String> socialMapWeb = up.getAccountInfo().getSocialMap();

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

                        if ("".equalsIgnoreCase(entry.getValue())) {
                            //если записи с таким TypeContact у юзера были, удаляем их из базы
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
                            // мы не верим юзеру и новую соцсеть не добавляем
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



     //////////////////////////////////////////////////////////
        try {
                    Mentors mentor = mentorRepository.getById(user.getId());
                    mentor.setCertificats(up.getCertificats());
                    mentor.setEducations(up.getEducations());
                    mentor.setDescription(up.getDescription());
                    mentor.setOnline(up.isOnline());
                    mentor.setOfflineIn(up.isOfflineIn());
                    mentor.setOfflineOut(up.isOfflineOut());
                    // remove(mentor);
                    for (Mentors_to_categories n : up.getCategories()) {
                        n.setMentors(mentor);
                    }
                    mentor.setMentors_to_categories(up.getCategories());

                    mentorRepository.save(mentor);
            //update email using method from emailService
            //if emails are equals do nothing
            if (!userToUpdate.getEmail().equals(emailNew)) {
                //TODO create new token with new email
                String reportUpdate = emailService.updateEmail(emailNew, id);
            }
            userRepository.save(userToUpdate);
                    return new ResponseEntity<String>("update was successful", HttpStatus.OK);
                } catch (EntityNotFoundException e) {
                }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


  public void remove(Mentors m){
        if(m.getMentors_to_categories()!=null)
        mentorsToCategory.removeByMentors(m);
    }
}

