package com.mentor4you.service;

import com.mentor4you.model.*;
import com.mentor4you.model.DTO.serchMentorsDTO.SmallDataMentorDTO;
import com.mentor4you.repository.CategoriesRepository;
import com.mentor4you.repository.LanguagesRepository;
import com.mentor4you.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SearchMentorsService {


    @Autowired
    CategoriesRepository categoriesRepository;
    LanguagesRepository languagesRepository;
    MentorRepository mentorRepository;

    public SearchMentorsService(CategoriesRepository categoriesRepository,
                                LanguagesRepository languagesRepository,
                                MentorRepository mentorRepository) {
        this.categoriesRepository = categoriesRepository;
        this.languagesRepository = languagesRepository;
        this.mentorRepository = mentorRepository;
    }

    public List<Categories> findAllCategories() {
        return categoriesRepository.findAllCategory();
    }

    public List<String> findAllCategoriesName() {
        List<String> categoriesNameList = categoriesRepository.findAllCategoryName();
        if (categoriesNameList.isEmpty()) {
            categoriesNameList.add("");
            return (categoriesNameList);
        } else {
            return categoriesNameList;
        }
    }

    public List<Languages> findAllLanguages() {
        return languagesRepository.findAllLanguages();
    }

    public List<String> findAllLanfuagesName() {
        List<String> languagesNameList = languagesRepository.findAllLanguagesName();
        if (languagesNameList.isEmpty()) {
            languagesNameList.add("");
            return (languagesNameList);
        } else {
            return languagesNameList;
        }
    }

    public ResponseEntity<List<SmallDataMentorDTO>> createSmallMentorDTOFiltr(
            List<Mentors> mentorsList, String city, String category, String language, int minRate, int maxRate) {
        boolean isOnline = false;
        if (city.equals("Online")) {
            isOnline = true;
        }

        List<SmallDataMentorDTO> smallMentorDTOList = new ArrayList<>();

        if (mentorsList.size() > 0) {
            //проверяем менторов на соответствие запросу в фильтре
            for (Mentors m : mentorsList) {
                //проверяем онлайн
                if (isOnline) {
                    //не проверяем город, а преподает ли ментор онлайн
                    if (m.isOnline()) {
                        //проверяем язык
                        if (checkMentorLanguage(m, language)) {
                            //язык совпал, проверяем цену
                            if (checkMentorPrice(m, category, minRate, maxRate)) {
                                //цена совпала
                                //создаем объект
                                smallMentorDTOList.add(createDTO(m, category));
                            }
                        }
                    }
                } else {
                    if (!m.isOnline()) {
                        //проверяем город
                        if (checkMentorCity(m, city)) {
                            //город совпал проверяем язык
                            if (checkMentorLanguage(m, language)) {
                                //язык совпал, проверяем цену
                                if (checkMentorPrice(m, category, minRate, maxRate)) {
                                    //цена совпала
                                    //создаем объект
                                    smallMentorDTOList.add(createDTO(m, category));
                                }
                            }
                        }
                    }
                }
            }
        } else {
            return new ResponseEntity<List<SmallDataMentorDTO>>(HttpStatus.NOT_FOUND);
        }

        Collections.sort(smallMentorDTOList, new Comparator<SmallDataMentorDTO>() {
            @Override
            public int compare(SmallDataMentorDTO u1, SmallDataMentorDTO u2) {
                return u2.getRate()-u1.getRate();
            }
        });

        return new ResponseEntity<List<SmallDataMentorDTO>>(smallMentorDTOList, HttpStatus.OK);
    }

    private boolean checkMentorCity(Mentors mentors, String city) {
        boolean cityExist = false;
        for (CityToMentors cityToMentors : mentors.getCityToMentors()) {
            if (cityToMentors.getCity().getName().equals(city)) {
                cityExist = true;
                break;
            }
        }
        return cityExist;
    }

    private boolean checkMentorLanguage(Mentors mentors, String language) {
        boolean languageExist = false;
        for (Languages languageTemp : mentors.getAccounts().getLanguagesList()) {
            if (languageTemp.getName().equals(language)) {
                languageExist = true;
                break;
            }
        }
        return languageExist;
    }

    private boolean checkMentorPrice(Mentors mentors, String category, int minPrice, int maxPrice) {
        boolean priceIsGood = false;
        for (Mentors_to_categories mCategory : mentors.getMentors_to_categories()) {
            if (mCategory.getCategories().getName().equals(category)
                    && mCategory.getRate() >= minPrice
                    && mCategory.getRate() <= maxPrice) {
                priceIsGood = true;
                break;
            }
        }
        return priceIsGood;
    }

    private SmallDataMentorDTO createDTO(Mentors m, String foundCategory) {

        SmallDataMentorDTO smallMentTemp = new SmallDataMentorDTO();

        smallMentTemp.setId(m.getId());
        smallMentTemp.setAvatar(m.getAccounts().getUser().getAvatar());
        smallMentTemp.setFirstName(m.getAccounts().getUser().getFirst_name());
        smallMentTemp.setLastName(m.getAccounts().getUser().getLast_name());
        smallMentTemp.setOnline(m.isOnline());
        smallMentTemp.setFoundCategory(foundCategory);
        smallMentTemp.setRating(m.getRating());

        List<String> categoriesList = new ArrayList<>();

        for (Mentors_to_categories mc : m.getMentors_to_categories()) {
            if (mc.getCategories().getName().equals(foundCategory)) {
                smallMentTemp.setRate(mc.getRate());
                smallMentTemp.setCurrency(mc.getCurrency());
            } else {
                categoriesList.add(mc.getCategories().getName());
            }
        }

        if (categoriesList.size() > 0) {
            smallMentTemp.setCategories(categoriesList);
        } else {
            categoriesList.add("");
            smallMentTemp.setCategories(categoriesList);
        }

        return smallMentTemp;
    }

    public ResponseEntity<List<SmallDataMentorDTO>> createSmallMentorDTO(
            List<Mentors> mentorsList) {

        List<SmallDataMentorDTO> smallMentorDTOList = new ArrayList<>();
        String category = "";
        if (mentorsList.size() > 0) {
            //проверяем менторов на соответствие запросу в фильтре
            for (Mentors m : mentorsList) {
                //проверяем онлайн
                smallMentorDTOList.add(createDTO(m, category));
            }
        } else {
            return new ResponseEntity<List<SmallDataMentorDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<SmallDataMentorDTO>>(smallMentorDTOList, HttpStatus.OK);
    }

    public SearchMentorsDTO createSerchMentorSTOList(){
        SearchMentorsDTO searchMentorsDTO = new SearchMentorsDTO();
        searchMentorsDTO.setCategoriesList(findAllCategoriesName());
        searchMentorsDTO.setLanguagesList(findAllLanfuagesName());
        return searchMentorsDTO;
    }
}

