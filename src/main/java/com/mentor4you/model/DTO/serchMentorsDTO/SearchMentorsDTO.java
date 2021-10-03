package com.mentor4you.model.DTO.serchMentorsDTO;

import com.mentor4you.model.Categories;
import com.mentor4you.model.Languages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchMentorsDTO {

    private List<String> categoriesList;
    private List<String> languagesList;
    private List<String> cityList;

    public SearchMentorsDTO() {
    }

    public SearchMentorsDTO(List<String> categoriesList, List<String> languagesList, List<String> cityList) {
        this.categoriesList = categoriesList;
        this.languagesList = languagesList;
        this.cityList = cityList;
    }


    public List<String> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public List<String> getLanguagesList() {
        return languagesList;
    }

    public void setLanguagesList(List<String> languagesList) {
        this.languagesList = languagesList;
    }

    public List<String> getCityList() {
        return cityList;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }




}
