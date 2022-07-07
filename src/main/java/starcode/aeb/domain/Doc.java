package starcode.aeb.domain;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doc {
    private ArrayList<String> name = new ArrayList<String>();

    private String lastName = "";
    private String firstName = "";
    private String patronymic = "";
    private String dateBirthday = "";
    private String citizenship = "";
    private String passportSeries = "";
    private String passportNumber = "";
    private String passportWhoGives = "";
    private String passportWhenTakesDay = "";
    private String passportWhenTakesMonth = "";
    private String SNILS = "";
    private String INN = "";
    private String phone = "";
    private String email = "";
    private String passportPostal = "";
    private String passportAddress = "";
    private String factAddressPostal = "";
    private String factAddress = "";
    private String knowledge = "";
    private String plusesAndMinuses = "";
    private String habits = "";
    private String familyStatus = "";
    private String experiencePC = "";
    private String driveLicence = "";
    private String dateDay = "";
    private String dateMonth = "";

    public ArrayList<String> getList() {
        name.add(getLastName());
        name.add(getFirstName());
        name.add(getPatronymic());
        name.add(getDateBirthday());
        name.add(getCitizenship());
        name.add(getPassportSeries());
        name.add(getPassportNumber());
        name.add(getPassportWhoGives());
        name.add(getPassportWhenTakesDay());
        name.add(getPassportWhenTakesMonth());
        name.add(getSNILS());
        name.add(getINN());
        name.add(getPhone());
        return name;
    }
}
