import entity.*;
import mappers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Main {
    enum Menu{
        MAIN(0), PARENT(1),ENTRANT(2),
        SUBJECT(3),FACULTY(4),
        INSTITUTION(5),SPECIALISATION(6),
        GRAD_DOC(7),EXAMS(8),
        ENTER_EXAMS(9),ENTRANT_INFO(10),
        PARENT_INFO(11),GRAD_DOC_INFO(12),EXAM_INFO(13),ENTER_EXAM_INFO(14);
        private int code;
        Menu(int code){
            this.code = code;
        }
        public int getCode(){return code;}

    }

    static String[] mainMenu = {"Выход","Абитуриент","Родители", "Факультеты", "Направления", "Профильные предметы", "Учебные заведения", "Документы о выпуске", "Результаты вступительных экзаменов"};
    static String[] parentMenu = { "Назад","Добавить родителя","Удалить родителя","Просмотреть всех родителей","Изменить данные родителя","Поиск родителя" };
    static String[] entrMenu = {"Назад","Добавить абитуриента","Удалить абитуриента","Просмотреть всех абитуриентов","Изменить данные абитуриента","Поиск абитуриента"};
    static String[] subjMenu = { "Назад","Добавить предмет","Удалить предмет","Просмотреть все предметы","Изменить предмет", "Поиск предмета"};
    static String[] facMenu = { "Назад","Добавить факультет","Удалить факультет","Просмотреть все факультеты","Изменить факультет", "Поиск факультета"};
    static String[] instMenu = { "Назад","Добавить учебное заведение","Удалить учебное заведение","Просмотреть все учебные заведения","Изменить учебное заведение", "Поиск учебного заведения"};
    static String[] specMenu = { "Назад","Добавить специальность","Удалить специальность","Просмотреть все специальности","Изменить специальность","Поиск специальности"};
    static String[] gradMenu = { "Назад","Добавить документ","Удалить документ","Просмотреть все документы","Просмотреть результаты","Изменить документ","Поиск документа" };
    static String[] examMenu = { "Назад","Добавить результат","Удалить результат","Просмотреть все результаты","Изменить результат", "Поиск результатов экзаменов"};
    static String[] enterExamMenu = { "Назад","Добавить результат","Удалить результат","Просмотреть все результаты","Изменить результат", "Поиск результатов входных экзаменов"};
    static String[] infoEnterMenu= {"Назад","Фамилия","Имя","Отчество","Пол","Национальность","Дата рождения","Паспорт","Адрес","Факультет","Направление","Учебное заведение","Год выпуска","Группа вступительного экзамена"};
    static String[] infoParentMenu = { "Назад","Фамилия","Имя","Отчество", "Ребенок", "Адрес" };
    static String[] infoGradMenu = { "Назад","Абитуриент","Тип документа","Номер документа", "Средний балл" };
    static String[] infoExamMenu = { "Назад","Абитуриент","Предмет", "Балл" };
    static String[] infoEnterExamMenu = { "Назад","Абитуриент","Предмет","Аудитория", "Дата", "Балл" };
    static String[][] menuArr = {mainMenu,parentMenu,entrMenu,subjMenu,facMenu,instMenu,specMenu,gradMenu,examMenu,enterExamMenu,infoEnterMenu,infoParentMenu,infoGradMenu,infoExamMenu, infoEnterExamMenu};

    public static void chooseMenu(Menu m) {
        for (int i = 0; i < menuArr[m.getCode()].length; i++)
        {
            System.out.println(i+"."+menuArr[m.getCode()][i]);
        }
    }
    public static String input() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static void menu() throws IOException, InterruptedException {
        EntrantMapper entrantMapper = new EntrantMapper();
        FacultiesMapper facultiesMapper = new FacultiesMapper();
        ExamScoresMapper examScoresMapper = new ExamScoresMapper();
        GradDocsMapper gradDocsMapper = new GradDocsMapper();
        InstitutionsMapper institutionsMapper = new InstitutionsMapper();
        ParentsMapper parentsMapper = new ParentsMapper();
        SchoolScoresMapper schoolScoresMapper = new SchoolScoresMapper();
        SpecializationsMapper specializationsMapper = new SpecializationsMapper();
        SubjectsMapper subjectsMapper = new SubjectsMapper();
        EnterExamsResultsMapper enterExamsResultsMapper = new EnterExamsResultsMapper();


        System.out.println("Приемная комиссия:");
        boolean isMenu = true;
        while (isMenu) {

            chooseMenu(Menu.MAIN);
            int key = 0;

            key = Integer.parseInt(input());
            switch (key) {
                case 0: {
                    isMenu = false;
                    break;
                }
                case 1: {
                    boolean isEntrMenu = true;
                    while (isEntrMenu) {
                        chooseMenu(Menu.ENTRANT);
                        int entrKey = Integer.parseInt(input());
                        switch (entrKey) {
                            case 0: {
                                isEntrMenu = false;
                                break;
                            }
                            case 1: {
                                Entrant entrant = new Entrant();
                                System.out.print("Введите фамилию: ");
                                entrant.setSurname(input());
                                System.out.print("Введите имя: ");
                                entrant.setName(input());
                                System.out.print("Введите отчество: ");
                                entrant.setPatronymic(input());
                                System.out.print("Введите пол: ");
                                entrant.setSex(input());
                                System.out.print("Введите национальность: ");
                                entrant.setNationality(input());
                                System.out.print("Введите дату рождения (гггг-мм-дд): ");
                                entrant.setBirthday(Date.valueOf(input()));
                                System.out.print("Введите адрес: ");
                                entrant.setAddress(input());

                                var faculties = facultiesMapper.findAll();
                                for (int i = 0; i < faculties.size(); i++) {
                                    System.out.println((i + 1) + ". " + faculties.get(i).getName());
                                }
                                System.out.print("Введите факультет: ");
                                var faculty = faculties.get(Integer.parseInt(input())-1);
                                entrant.setFacultiesByFaculty(faculty);


                                var insts = institutionsMapper.findAll();
                                for (int i = 0; i < insts.size(); i++) {
                                    System.out.println((i + 1) + ". " + insts.get(i).getName());
                                }
                                System.out.print("Введите учебное заведение: ");
                                entrant.setInstitutionsByInstitution(insts.get(Integer.parseInt(input())-1));


                                var specs = faculty.getSpecializationsById();
                                for (int i = 0; i < specs.size(); i++) {
                                    System.out.println((i + 1) + ". " + specs.get(i).getName());
                                }
                                System.out.print("Введите направление: ");
                                entrant.setSpecializationsBySpecialization(specs.get(Integer.parseInt(input())-1));

                                System.out.print("Введите год выпуска (гггг): ");
                                entrant.setGradYear(input());
                                System.out.print("Введите паспорт (1234567890): ");
                                entrant.setPassport(input());

                                System.out.print("Добавить группу входного экзамена? (0 - нет, 1 - да)");
                                if (input() == "1") {
                                    System.out.print("Введите группу входного экзамена: ");
                                    entrant.setEnterExamGroup(Integer.parseInt(input()));
                                }
                                entrantMapper.save(entrant);
                                break;
                            }
                            case 2: {

                                var entrants = entrantMapper.findAll();
                                for (int i = 0; i < entrants.size(); i++) {
                                    System.out.println((i + 1) + ". " + entrants.get(i));
                                }
                                System.out.print("Введите абитуриента для удаления (0 для отмены): ");
                                int id = Integer.parseInt(input());
                                if (id == 0)
                                    break;
                                entrantMapper.delete(entrants.get(id - 1));
                                break;
                            }
                            case 3: {

                                var entrants = entrantMapper.findAll();
                                for (int i = 0; i < entrants.size(); i++) {
                                    System.out.println((i + 1) + ". " + entrants.get(i));
                                }
                                break;
                            }
                            case 4: {
                                var isEdit = true;
                                var entrants = entrantMapper.findAll();
                                for (int i = 0; i < entrants.size(); i++) {
                                    System.out.println((i + 1) + ". " + entrants.get(i));
                                }
                                System.out.print("Введите абитуриента для изменения (0 для отмены): ");
                                int id = Integer.parseInt(input());
                                if (id == 0)
                                    break;
                                Entrant entrant = entrants.get(id-1);
                                while (isEdit) {
                                    chooseMenu(Menu.ENTRANT_INFO);
                                    System.out.print("Введите данные для изменения: ");
                                    int editKey = Integer.parseInt(input());
                                    switch (editKey) {
                                        case 0:
                                            isEdit = false;
                                            break;
                                        case 1: {
                                            System.out.print("Введите фамилию: ");
                                            entrant.setSurname(input());
                                            break;
                                        }
                                        case 2: {
                                            System.out.print("Введите имя: ");
                                            entrant.setName(input());
                                            break;
                                        }
                                        case 3: {
                                            System.out.print("Введите отчество: ");
                                            entrant.setSex(input());
                                            break;
                                        }
                                        case 4: {
                                            System.out.print("Введите пол: ");
                                            entrant.setSex(input());
                                            break;
                                        }
                                        case 5: {
                                            System.out.print("Введите национальность: ");
                                            entrant.setNationality(input());
                                            break;
                                        }
                                        case 6: {
                                            System.out.print("Введите дату рождения(гггг-мм-дд): ");
                                            entrant.setBirthday(Date.valueOf(input()));
                                            break;
                                        }
                                        case 7: {
                                            System.out.print("Введите паспорт(1234567890): ");
                                            entrant.setPassport(input());
                                            break;
                                        }
                                        case 8: {
                                            System.out.print("Введите адрес: ");
                                            entrant.setAddress(input());
                                            break;
                                        }
                                        case 9: {

                                            var faculties = facultiesMapper.findAll();
                                            for (int i = 0; i < faculties.size(); i++) {
                                                System.out.println((i + 1) + ". " + faculties.get(i).getName());
                                            }
                                            System.out.print("Введите факультет: ");
                                            var faculty = faculties.get(Integer.parseInt(input()) - 1);
                                            entrant.setFacultiesByFaculty(faculty);
                                            break;
                                        }
                                        case 10: {
                                            var specs = specializationsMapper.findAll();
                                            for (int i = 0; i < specs.size(); i++) {
                                                System.out.println((i + 1) + ". " + specs.get(i).getName());
                                            }
                                            System.out.print("Введите факультет: ");
                                            var spec = specs.get(Integer.parseInt(input()) - 1);
                                            entrant.setSpecializationsBySpecialization(spec);
                                            break;
                                        }
                                        case 11: {
                                            var insts = institutionsMapper.findAll();
                                            for (int i = 0; i < insts.size(); i++) {
                                                System.out.println((i + 1) + ". " + insts.get(i).getName());
                                            }
                                            System.out.print("Введите факультет: ");
                                            var inst = insts.get(Integer.parseInt(input()) - 1);
                                            entrant.setInstitutionsByInstitution(inst);
                                            break;
                                        }
                                        case 12: {
                                            System.out.print("Введите фамилию: ");
                                            entrant.setGradYear(input());
                                            break;
                                        }
                                        case 13: {
                                            System.out.print("Введите фамилию: ");
                                            entrant.setEnterExamGroup(Integer.parseInt(input()));
                                            break;
                                        }
                                    }
                                }
                                entrantMapper.edit(entrant);
                                break;
                            }
                            case 5: {
                                boolean isFind = true;
                                while (isFind) {
                                    chooseMenu(Menu.ENTRANT_INFO);
                                    System.out.println("Выберите данные для поиска: ");
                                    int infoKey = Integer.parseInt(input());
                                    switch (infoKey) {
                                        case 0:
                                            isFind = false;
                                            break;
                                        case 1: {
                                            System.out.println("Введите запрос: ");
                                            var entrants = entrantMapper.findBySurname(input());
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Введите запрос: ");
                                            var entrants = entrantMapper.findByName(input());
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("Введите запрос: ");
                                            var entrants = entrantMapper.findByPatronymic(input());
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 4: {
                                            System.out.println("Введите запрос: ");
                                            var entrants = entrantMapper.findBySex(input());

                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 5: {
                                            System.out.println("Введите запрос: ");
                                            var entrants = entrantMapper.findByNationality(input());
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 6: {
                                            System.out.println("Введите запрос: ");
                                            var entrants = entrantMapper.findByBirthday(input());
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 7: {
                                            System.out.println("Введите запрос: ");
                                            var entrants = entrantMapper.findByPassport(input());
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 8: {
                                            System.out.println("Введите запрос: ");
                                            var entrants = entrantMapper.findByAddress(input());
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 9: {

                                            var faculties = facultiesMapper.findAll();
                                            for (int i = 0; i < faculties.size(); i++) {
                                                System.out.println((i + 1) + ". " + faculties.get(i).getName());
                                            }
                                            System.out.print("Введите факультет: ");
                                            var faculty = faculties.get(Integer.parseInt(input()) - 1);
                                            var entrants = entrantMapper.findByFaculty(faculty);
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 10: {
                                            var specs = specializationsMapper.findAll();
                                            for (int i = 0; i < specs.size(); i++) {
                                                System.out.println((i + 1) + ". " + specs.get(i).getName());
                                            }
                                            System.out.print("Введите направление: ");
                                            var spec = specs.get(Integer.parseInt(input()) - 1);
                                            var entrants = entrantMapper.findBySpecialization(spec);
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 11: {
                                            var insts = institutionsMapper.findAll();
                                            for (int i = 0; i < insts.size(); i++) {
                                                System.out.println((i + 1) + ". " + insts.get(i).getName());
                                            }
                                            System.out.print("Введите учебное заведение: ");
                                            var inst = insts.get(Integer.parseInt(input()) - 1);
                                            var entrants = entrantMapper.findByInstitutions(inst);
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 12: {
                                            System.out.println("Введите запрос: ");
                                            var entrants = entrantMapper.findByGradYear(input());
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                        case 13: {
                                            System.out.println("Введите запрос: ");
                                            var entrants = entrantMapper.findByEnterExamGroup(input());
                                            for (Entrant entrant :
                                                    entrants) {
                                                System.out.println(entrant);
                                            }
                                            break;
                                        }
                                    }

                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    boolean isParentMenu = true;
                    while (isParentMenu) {
                        chooseMenu(Menu.PARENT);
                        int parentKey = Integer.parseInt(input());
                        switch (parentKey) {
                            case 0: {
                                isParentMenu = false;
                                break;
                            }
                            case 1: {
                                Parents parent = new Parents();
                                System.out.print("Введите фамилию: ");
                                parent.setSurname(input());
                                System.out.print("Введите имя: ");
                                parent.setName(input());
                                System.out.print("Введите отчество: ");
                                parent.setPatronymic(input());
                                System.out.print("Введите адрес: ");
                                parent.setAddress(input());
                                var entrants = entrantMapper.findAll();
                                for (int i = 0; i < entrants.size(); i++) {
                                    System.out.println((i + 1) + ". " + entrants.get(i).getName());
                                }
                                System.out.print("Введите абитуриента: ");
                                var entrant = entrants.get(Integer.parseInt(input())-1);
                                parent.setEntrantByChild(entrant);
                                parentsMapper.save(parent);
                                break;
                            }
                            case 2: {
                                var parents = parentsMapper.findAll();
                                for (int i = 0; i < parents.size(); i++) {
                                    System.out.println((i + 1) + ". " + parents.get(i).getName());
                                }
                                System.out.print("Введите родителя: ");
                                var parent = parents.get(Integer.parseInt(input())-1);
                                parentsMapper.delete(parent);
                                break;
                            }
                            case 3: {
                                var parents = parentsMapper.findAll();
                                for (int i = 0; i < parents.size(); i++) {
                                    System.out.println((i + 1) + ". " + parents.get(i));
                                }
                                break;
                            }
                            case 4: {
                                var isEdit = true;

                                var parents = parentsMapper.findAll();
                                for (int i = 0; i < parents.size(); i++) {
                                    System.out.println((i + 1) + ". " + parents.get(i));
                                }
                                System.out.print("Введите родителя для изменения (0 для отмены): ");
                                int id = Integer.parseInt(input());
                                if (id == 0)
                                    break;
                                Parents parent = parents.get(id-1);
                                while (isEdit) {
                                    chooseMenu(Menu.ENTRANT_INFO);
                                    System.out.print("Введите данные для изменения: ");
                                    int editKey = Integer.parseInt(input());
                                    switch (editKey) {
                                        case 0:
                                            isEdit = false;
                                            break;
                                        case 1: {
                                            System.out.print("Введите фамилию: ");
                                            parent.setSurname(input());
                                            break;
                                        }
                                        case 2: {
                                            System.out.print("Введите имя: ");
                                            parent.setName(input());
                                            break;
                                        }
                                        case 3: {
                                            System.out.print("Введите отчество: ");
                                            parent.setPatronymic(input());
                                            break;
                                        }
                                        case 4: {
                                            System.out.print("Введите адрес: ");
                                            parent.setAddress(input());
                                            break;
                                        }
                                        case 5: {
                                            var entrs = entrantMapper.findAll();
                                            for (int i = 0; i < entrs.size(); i++) {
                                                System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                            }
                                            System.out.print("Введите абитуриента: ");
                                            var entr = entrs.get(Integer.parseInt(input()) - 1);
                                            parent.setEntrantByChild(entr);
                                            break;
                                        }
                                    }
                                }
                                parentsMapper.edit(parent);
                                break;
                            }
                            case 5: {
                                boolean isFind = true;
                                while (isFind) {
                                    chooseMenu(Menu.PARENT_INFO);
                                    System.out.println("Выберите данные для поиска: ");
                                    int infoKey = Integer.parseInt(input());
                                    switch (infoKey) {
                                        case 0:
                                            isFind = false;
                                            break;
                                        case 1: {
                                            System.out.println("Введите запрос: ");
                                            var parents = parentsMapper.findBySurname(input());
                                            for (Parents parent :
                                                    parents) {
                                                System.out.println(parent);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Введите запрос: ");

                                            var parents = parentsMapper.findByName(input());
                                            for (Parents parent :
                                                    parents) {
                                                System.out.println(parent);
                                            }
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("Введите запрос: ");

                                            var parents = parentsMapper.findByPatronymic(input());
                                            for (Parents parent :
                                                    parents) {
                                                System.out.println(parent);
                                            }
                                            break;
                                        }
                                        case 5: {
                                            System.out.println("Введите запрос: ");

                                            var parents = parentsMapper.findByAddress(input());
                                            for (Parents parent :
                                                    parents) {
                                                System.out.println(parent);
                                            }
                                            break;
                                        }
                                        case 4: {
                                            var entrants = entrantMapper.findAll();
                                            for (int i = 0; i < entrants.size(); i++) {
                                                System.out.println((i + 1) + ". " + entrants.get(i));
                                            }
                                            System.out.println("Выберите ребенка: ");
                                            Entrant entrant = entrants.get(Integer.parseInt(input()) - 1);

                                            var parents = parentsMapper.findByChild(entrant);
                                            for (Parents parent :
                                                    parents) {
                                                System.out.println(parent);
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    boolean isFacMenu = true;
                    while (isFacMenu) {
                        chooseMenu(Menu.FACULTY);
                        int facKey = Integer.parseInt(input());
                        switch (facKey) {
                            case 0: {
                                isFacMenu = false;
                                break;
                            }
                            case 1: {
                                Faculties faculties = new Faculties();
                                System.out.print("Введите название: ");
                                faculties.setName(input());
                                facultiesMapper.save(faculties);
                                break;
                            }
                            case 2: {
                                var faculties = facultiesMapper.findAll();
                                for (int i = 0; i < faculties.size(); i++) {
                                    System.out.println((i + 1) + ". " + faculties.get(i).getName());
                                }
                                System.out.print("Введите факультет: ");
                                var faculty = faculties.get(Integer.parseInt(input())-1);
                                facultiesMapper.delete(faculty);
                                break;
                            }
                            case 3: {
                                var faculties = facultiesMapper.findAll();
                                for (int i = 0; i < faculties.size(); i++) {
                                    System.out.println((i + 1) + ". " + faculties.get(i));
                                }
                                break;
                            }
                            case 4: {
                                var isEdit = true;

                                var faculties = facultiesMapper.findAll();
                                for (int i = 0; i < faculties.size(); i++) {
                                    System.out.println((i + 1) + ". " + faculties.get(i));
                                }
                                System.out.print("Введите факультет для изменения (0 для отмены): ");
                                int id = Integer.parseInt(input());
                                if (id == 0)
                                    break;
                                Faculties faculty = faculties.get(id-1);
                                while (isEdit) {
                                    System.out.print("Введите название: ");
                                    faculty.setName(input());
                                    break;
                                }
                                facultiesMapper.edit(faculty);
                                break;
                            }
                            case 5: {
                                System.out.println("Введите запрос: ");

                                var faculties = facultiesMapper.findByName(input());
                                for (Faculties faculty :
                                        faculties) {
                                    System.out.println(faculty);
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    boolean isSpecMenu = true;
                    while (isSpecMenu) {
                        chooseMenu(Menu.SPECIALISATION);
                        int specKey = Integer.parseInt(input());
                        switch (specKey) {
                            case 0: {
                                isSpecMenu = false;
                                break;
                            }
                            case 1: {
                                Specializations spec = new Specializations();
                                System.out.print("Введите название: ");
                                spec.setName(input());

                                specializationsMapper.save(spec);
                                break;
                            }
                            case 2: {
                                var specs = specializationsMapper.findAll();
                                for (int i = 0; i < specs.size(); i++) {
                                    System.out.println((i + 1) + ". " + specs.get(i).getName());
                                }
                                System.out.print("Введите направление: ");
                                var spec = specs.get(Integer.parseInt(input())-1);
                                specializationsMapper.delete(spec);
                                break;
                            }
                            case 3: {
                                var specializationsList = specializationsMapper.findAll();
                                for (int i = 0; i < specializationsList.size(); i++) {
                                    System.out.println((i + 1) + ". " + specializationsList.get(i));
                                }
                                break;
                            }
                            case 4: {
                                var isEdit = true;

                                var specializations = specializationsMapper.findAll();
                                for (int i = 0; i < specializations.size(); i++) {
                                    System.out.println((i + 1) + ". " + specializations.get(i));
                                }
                                System.out.print("Введите родителя для изменения (0 для отмены): ");
                                int id = Integer.parseInt(input());
                                if (id == 0)
                                    break;
                                Specializations specialization = specializations.get(id-1);

                                System.out.print("Введите имя: ");
                                specialization.setName(input());

                                specializationsMapper.edit(specialization);
                                break;
                            }
                            case 5: {
                                System.out.println("Введите запрос: ");

                                var specializations = specializationsMapper.findByName(input());
                                for (Specializations specialization :
                                        specializations) {
                                    System.out.println(specialization);
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case 5: {
                    boolean isSubjMenu = true;
                    while (isSubjMenu) {
                        chooseMenu(Menu.SUBJECT);
                        int subjKey = Integer.parseInt(input());
                        switch (subjKey) {
                            case 0: {
                                isSubjMenu = false;
                                break;
                            }
                            case 1: {
                                Subjects subjects = new Subjects();
                                System.out.print("Введите название: ");
                                subjects.setName(input());
                                subjectsMapper.save(subjects);
                                break;
                            }
                            case 2: {
                                var subjects = subjectsMapper.findAll();
                                for (int i = 0; i < subjects.size(); i++) {
                                    System.out.println((i + 1) + ". " + subjects.get(i).getName());
                                }
                                System.out.print("Введите предмет: ");
                                var subj = subjects.get(Integer.parseInt(input())-1);
                                subjectsMapper.delete(subj);
                                break;
                            }
                            case 3: {
                                var subjects = subjectsMapper.findAll();
                                for (int i = 0; i < subjects.size(); i++) {
                                    System.out.println((i + 1) + ". " + subjects.get(i));
                                }
                                break;
                            }
                            case 4: {

                                var subjects = subjectsMapper.findAll();
                                for (int i = 0; i < subjects.size(); i++) {
                                    System.out.println((i + 1) + ". " + subjects.get(i));
                                }
                                System.out.print("Введите предмет для изменения (0 для отмены): ");
                                int id = Integer.parseInt(input());
                                if (id == 0)
                                    break;
                                Subjects subject = subjects.get(id-1);

                                System.out.print("Введите имя: ");
                                subject.setName(input());

                                subjectsMapper.edit(subject);
                                break;
                            }
                            case 5: {
                                System.out.println("Введите запрос: ");

                                var subjects = subjectsMapper.findByName(input());
                                for (Subjects subj :
                                        subjects) {
                                    System.out.println(subj);
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case 6: {
                    boolean isInstMenu = true;
                    while (isInstMenu) {
                        chooseMenu(Menu.INSTITUTION);
                        int instKey = Integer.parseInt(input());
                        switch (instKey) {
                            case 0: {
                                isInstMenu = false;
                                break;
                            }
                            case 1: {
                                Institutions institutions = new Institutions();
                                System.out.print("Введите название: ");
                                institutions.setName(input());
                                System.out.print("Введите тип: ");
                                institutions.setType(input());
                                institutionsMapper.save(institutions);
                                break;
                            }
                            case 2: {
                                var institutions = institutionsMapper.findAll();
                                for (int i = 0; i < institutions.size(); i++) {
                                    System.out.println((i + 1) + ". " + institutions.get(i).getName());
                                }
                                System.out.print("Введите учебное заведение: ");
                                var inst = institutions.get(Integer.parseInt(input())-1);
                                institutionsMapper.delete(inst);
                                break;
                            }
                            case 3: {
                                var institutions = institutionsMapper.findAll();
                                for (int i = 0; i < institutions.size(); i++) {
                                    System.out.println((i + 1) + ". " + institutions.get(i));
                                }
                                break;
                            }
                            case 4: {

                                var institutions = institutionsMapper.findAll();
                                for (int i = 0; i < institutions.size(); i++) {
                                    System.out.println((i + 1) + ". " + institutions.get(i));
                                }
                                System.out.print("Введите учебное заведение для изменения (0 для отмены): ");
                                int id = Integer.parseInt(input());
                                if (id == 0)
                                    break;
                                Institutions inst = institutions.get(id-1);
                                System.out.println("1. Название\n2. Тип");
                                int editKey = Integer.parseInt(input());
                                switch (editKey){
                                    case 1:
                                        System.out.print("Введите имя: ");
                                        inst.setName(input());
                                        break;
                                    case 2:
                                        System.out.println("1. Школа\n2. Колледж");
                                        String type = input();
                                        inst.setType(type);
                                        break;

                                }
                                institutionsMapper.edit(inst);
                                break;
                            }
                            case 5: {
                                System.out.println("1. Название\n2. Тип");
                                int findKey = Integer.parseInt(input());
                                switch (findKey){
                                    case 1:
                                        System.out.println("Введите запрос: ");

                                        var institutions = institutionsMapper.findByName(input());
                                        for (Institutions inst :
                                                institutions) {
                                            System.out.println(inst);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("1. Школа\n2. Колледж");
                                        String type = input();
                                        institutions = institutionsMapper.findByType(type);
                                        for (Institutions inst :
                                                institutions) {
                                            System.out.println(inst);
                                        }
                                        break;
                                }

                                break;
                            }
                        }
                    }
                    break;
                }
                case 7: {
                    boolean isGradMenu = true;
                    while (isGradMenu) {
                        chooseMenu(Menu.GRAD_DOC);
                        int gradKey = Integer.parseInt(input());
                        switch (gradKey) {
                            case 0: {
                                isGradMenu = false;
                                break;
                            }
                            case 1: {
                                GradDocs gradDocs = new GradDocs();
                                var entrs = entrantMapper.findAll();
                                for (int i = 0; i < entrs.size(); i++) {
                                    System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                }
                                System.out.print("Введите абитуриента: ");
                                var entr = entrs.get(Integer.parseInt(input()) - 1);
                                gradDocs.setEntrantByIdEntr(entr);
                                System.out.println("1. Аттестат\n2. Диплом");
                                int type = Integer.parseInt(input());
                                gradDocs.setType(type);
                                System.out.print("Введите номер: ");
                                gradDocs.setNumber(input());
                                System.out.print("Введите средний балл: ");
                                gradDocs.setAvScore(Double.parseDouble(input()));
                                gradDocsMapper.save(gradDocs);
                                break;
                            }
                            case 2: {
                                var gradDocs = gradDocsMapper.findAll();
                                for (int i = 0; i < gradDocs.size(); i++) {
                                    System.out.println((i + 1) + ". " + gradDocs.get(i));
                                }
                                System.out.print("Введите учебное заведение: ");
                                var grad = gradDocs.get(Integer.parseInt(input())-1);
                                gradDocsMapper.delete(grad);
                                break;
                            }
                            case 3: {
                                var gradDocs = gradDocsMapper.findAll();
                                for (int i = 0; i < gradDocs.size(); i++) {
                                    System.out.println((i + 1) + ". " + gradDocs.get(i));
                                }
                                break;
                            }
                            case 4:{
                                boolean isResults = true;
                                while (isResults){
                                    System.out.println("0. Назад\n1. Результаты ЕГЭ\n2. Итоговые оценки\n");
                                    int resKey = Integer.parseInt(input());
                                    switch (resKey){
                                        case 0:
                                            isResults = false;
                                            break;
                                        case 1:{
                                            boolean isExamMenu = true;
                                            while (isExamMenu) {
                                                chooseMenu(Menu.EXAMS);
                                                int examKey = Integer.parseInt(input());
                                                switch (examKey) {
                                                    case 0: {
                                                        isExamMenu = false;
                                                        break;
                                                    }
                                                    case 1: {
                                                        ExamScores examsResults = new ExamScores();
                                                        var entrs = entrantMapper.findAll();
                                                        for (int i = 0; i < entrs.size(); i++) {
                                                            System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                                        }
                                                        System.out.print("Введите абитуриента: ");
                                                        var entr = entrs.get(Integer.parseInt(input()) - 1);
                                                        examsResults.setEntrantByIdEntr(entr);
                                                        var subjs = subjectsMapper.findAll();
                                                        for (int i = 0; i < subjs.size(); i++) {
                                                            System.out.println((i + 1) + ". " + subjs.get(i).getName());
                                                        }
                                                        System.out.print("Введите предмет: ");
                                                        var subj = subjs.get(Integer.parseInt(input()) - 1);
                                                        examsResults.setSubjectsByIdSubj(subj);
                                                        System.out.print("Введите балл: ");
                                                        examsResults.setScore(Integer.parseInt(input()));
                                                        examScoresMapper.save(examsResults);
                                                        break;
                                                    }
                                                    case 2: {
                                                        var examsResults = examScoresMapper.findAll();
                                                        for (int i = 0; i < examsResults.size(); i++) {
                                                            System.out.println((i + 1) + ". " + examsResults.get(i));
                                                        }
                                                        System.out.print("Введите результат для удаления: ");
                                                        var enterExamsResult = examsResults.get(Integer.parseInt(input())-1);
                                                        examScoresMapper.delete(enterExamsResult);
                                                        break;
                                                    }
                                                    case 3: {
                                                        var examsResults = examScoresMapper.findAll();
                                                        for (int i = 0; i < examsResults.size(); i++) {
                                                            System.out.println((i + 1) + ". " + examsResults.get(i));
                                                        }
                                                        break;
                                                    }
                                                    case 4: {

                                                        var examsResults = examScoresMapper.findAll();
                                                        for (int i = 0; i < examsResults.size(); i++) {
                                                            System.out.println((i + 1) + ". " + examsResults.get(i));
                                                        }
                                                        System.out.print("Введите результат для изменения (0 для отмены): ");
                                                        int id = Integer.parseInt(input());
                                                        if (id == 0)
                                                            break;
                                                        ExamScores examsResult = examsResults.get(id-1);
                                                        chooseMenu(Menu.EXAM_INFO);
                                                        int editKey = Integer.parseInt(input());
                                                        switch (editKey){
                                                            case 1:
                                                                var entrs = entrantMapper.findAll();
                                                                for (int i = 0; i < entrs.size(); i++) {
                                                                    System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                                                }
                                                                System.out.print("Введите абитуриента: ");
                                                                var entr = entrs.get(Integer.parseInt(input()) - 1);
                                                                examsResult.setEntrantByIdEntr(entr);
                                                                break;
                                                            case 2:
                                                                var subjs = subjectsMapper.findAll();
                                                                for (int i = 0; i < subjs.size(); i++) {
                                                                    System.out.println((i + 1) + ". " + subjs.get(i).getName());
                                                                }
                                                                System.out.print("Введите предмет: ");
                                                                var subj = subjs.get(Integer.parseInt(input()) - 1);
                                                                examsResult.setSubjectsByIdSubj(subj);
                                                                break;
                                                            case 3:
                                                                System.out.print("Введите балл: ");
                                                                examsResult.setScore(Integer.parseInt(input()));
                                                        }
                                                        examScoresMapper.edit(examsResult);
                                                        break;
                                                    }
                                                    case 5: {
                                                        chooseMenu(Menu.EXAM_INFO);
                                                        int findKey = Integer.parseInt(input());
                                                        switch (findKey) {
                                                            case 1:
                                                                var entrs = entrantMapper.findAll();
                                                                for (int i = 0; i < entrs.size(); i++) {
                                                                    System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                                                }
                                                                System.out.print("Введите абитуриента: ");
                                                                var entr = entrs.get(Integer.parseInt(input()) - 1);
                                                                var examsResults = examScoresMapper.findByEntr(entr);
                                                                for (ExamScores examsResult :
                                                                        examsResults) {
                                                                    System.out.println(examsResult);
                                                                }
                                                                break;
                                                            case 2:
                                                                var subjs = subjectsMapper.findAll();
                                                                for (int i = 0; i < subjs.size(); i++) {
                                                                    System.out.println((i + 1) + ". " + subjs.get(i).getName());
                                                                }
                                                                System.out.print("Введите предмет: ");
                                                                var subj = subjs.get(Integer.parseInt(input()) - 1);
                                                                examsResults = examScoresMapper.findByEntr(subj);
                                                                for (ExamScores examsResult :
                                                                        examsResults) {
                                                                    System.out.println(examsResult);
                                                                }
                                                                break;
                                                            case 3:
                                                                System.out.print("Введите запрос: ");
                                                                examsResults = examScoresMapper.findByScore(input());
                                                                for (ExamScores examsResult :
                                                                        examsResults) {
                                                                    System.out.println(examsResult);
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                        case 2:{
                                            boolean isSchoolMenu = true;
                                            while (isSchoolMenu) {
                                                chooseMenu(Menu.EXAMS);
                                                int examKey = Integer.parseInt(input());
                                                switch (examKey) {
                                                    case 0: {
                                                        isSchoolMenu = false;
                                                        break;
                                                    }
                                                    case 1: {
                                                        SchoolScores schoolScores = new SchoolScores();
                                                        var entrs = entrantMapper.findAll();
                                                        for (int i = 0; i < entrs.size(); i++) {
                                                            System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                                        }
                                                        System.out.print("Введите абитуриента: ");
                                                        var entr = entrs.get(Integer.parseInt(input()) - 1);
                                                        schoolScores.setEntrantByIdEntr(entr);
                                                        var subjs = subjectsMapper.findAll();
                                                        for (int i = 0; i < subjs.size(); i++) {
                                                            System.out.println((i + 1) + ". " + subjs.get(i).getName());
                                                        }
                                                        System.out.print("Введите предмет: ");
                                                        var subj = subjs.get(Integer.parseInt(input()) - 1);
                                                        schoolScores.setSubjectsByIdSubj(subj);
                                                        System.out.print("Введите балл: ");
                                                        schoolScores.setScore(Integer.parseInt(input()));
                                                        schoolScoresMapper.save(schoolScores);
                                                        break;
                                                    }
                                                    case 2: {
                                                        var schoolScores = schoolScoresMapper.findAll();
                                                        for (int i = 0; i < schoolScores.size(); i++) {
                                                            System.out.println((i + 1) + ". " + schoolScores.get(i));
                                                        }
                                                        System.out.print("Введите результат для удаления: ");
                                                        var enterExamsResult = schoolScores.get(Integer.parseInt(input())-1);
                                                        schoolScoresMapper.delete(enterExamsResult);
                                                        break;
                                                    }
                                                    case 3: {
                                                        var schoolScores = schoolScoresMapper.findAll();
                                                        for (int i = 0; i < schoolScores.size(); i++) {
                                                            System.out.println((i + 1) + ". " + schoolScores.get(i));
                                                        }
                                                        break;
                                                    }
                                                    case 4: {

                                                        var schoolScores = schoolScoresMapper.findAll();
                                                        for (int i = 0; i < schoolScores.size(); i++) {
                                                            System.out.println((i + 1) + ". " + schoolScores.get(i));
                                                        }
                                                        System.out.print("Введите результат для изменения (0 для отмены): ");
                                                        int id = Integer.parseInt(input());
                                                        if (id == 0)
                                                            break;
                                                        SchoolScores examsResult = schoolScores.get(id-1);
                                                        chooseMenu(Menu.EXAM_INFO);
                                                        int editKey = Integer.parseInt(input());
                                                        switch (editKey){
                                                            case 1:
                                                                var entrs = entrantMapper.findAll();
                                                                for (int i = 0; i < entrs.size(); i++) {
                                                                    System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                                                }
                                                                System.out.print("Введите абитуриента: ");
                                                                var entr = entrs.get(Integer.parseInt(input()) - 1);
                                                                examsResult.setEntrantByIdEntr(entr);
                                                                break;
                                                            case 2:
                                                                var subjs = subjectsMapper.findAll();
                                                                for (int i = 0; i < subjs.size(); i++) {
                                                                    System.out.println((i + 1) + ". " + subjs.get(i).getName());
                                                                }
                                                                System.out.print("Введите предмет: ");
                                                                var subj = subjs.get(Integer.parseInt(input()) - 1);
                                                                examsResult.setSubjectsByIdSubj(subj);
                                                                break;
                                                            case 3:
                                                                System.out.print("Введите балл: ");
                                                                examsResult.setScore(Integer.parseInt(input()));
                                                        }
                                                        schoolScoresMapper.edit(examsResult);
                                                        break;
                                                    }
                                                    case 5: {
                                                        chooseMenu(Menu.EXAM_INFO);
                                                        int findKey = Integer.parseInt(input());
                                                        switch (findKey) {
                                                            case 1:
                                                                var entrs = entrantMapper.findAll();
                                                                for (int i = 0; i < entrs.size(); i++) {
                                                                    System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                                                }
                                                                System.out.print("Введите абитуриента: ");
                                                                var entr = entrs.get(Integer.parseInt(input()) - 1);
                                                                var schoolScores = schoolScoresMapper.findByEntr(entr);
                                                                for (SchoolScores examsResult :
                                                                        schoolScores) {
                                                                    System.out.println(examsResult);
                                                                }
                                                                break;
                                                            case 2:
                                                                var subjs = subjectsMapper.findAll();
                                                                for (int i = 0; i < subjs.size(); i++) {
                                                                    System.out.println((i + 1) + ". " + subjs.get(i).getName());
                                                                }
                                                                System.out.print("Введите предмет: ");
                                                                var subj = subjs.get(Integer.parseInt(input()) - 1);
                                                                schoolScores = schoolScoresMapper.findByEntr(subj);
                                                                for (SchoolScores examsResult :
                                                                        schoolScores) {
                                                                    System.out.println(examsResult);
                                                                }
                                                                break;
                                                            case 3:
                                                                System.out.print("Введите запрос: ");
                                                                schoolScores = schoolScoresMapper.findByScore(input());
                                                                for (SchoolScores examsResult :
                                                                        schoolScores) {
                                                                    System.out.println(examsResult);
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                    }

                                }
                                break;
                            }
                            case 5: {

                                var gradDocs = gradDocsMapper.findAll();
                                for (int i = 0; i < gradDocs.size(); i++) {
                                    System.out.println((i + 1) + ". " + gradDocs.get(i));
                                }
                                System.out.print("Введите документ для изменения (0 для отмены): ");
                                int id = Integer.parseInt(input());
                                if (id == 0)
                                    break;
                                GradDocs grad = gradDocs.get(id-1);
                                chooseMenu(Menu.GRAD_DOC_INFO);
                                int editKey = Integer.parseInt(input());
                                switch (editKey){
                                    case 1:
                                        var entrs = entrantMapper.findAll();
                                        for (int i = 0; i < entrs.size(); i++) {
                                            System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                        }
                                        System.out.print("Введите абитуриента: ");
                                        var entr = entrs.get(Integer.parseInt(input()) - 1);
                                        grad.setEntrantByIdEntr(entr);
                                        break;
                                    case 2:
                                        System.out.println("1. Аттестат\n2. Диплом");
                                        int type = Integer.parseInt(input());
                                        grad.setType(type);
                                        break;
                                    case 3:
                                        System.out.print("Введите номер: ");
                                        grad.setNumber(input());
                                    case 4:
                                        System.out.print("Введите средний балл: ");
                                        grad.setAvScore(Double.parseDouble(input()));
                                }
                                gradDocsMapper.edit(grad);
                                break;
                            }
                            case 6: {
                                chooseMenu(Menu.GRAD_DOC_INFO);
                                int findKey = Integer.parseInt(input());
                                switch (findKey) {
                                    case 1:
                                        var entrs = entrantMapper.findAll();
                                        for (int i = 0; i < entrs.size(); i++) {
                                            System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                        }
                                        System.out.print("Введите абитуриента: ");
                                        var entr = entrs.get(Integer.parseInt(input()) - 1);
                                        var gradDocs = gradDocsMapper.findByEntrant(entr);
                                        for (GradDocs grad :
                                                gradDocs) {
                                            System.out.println(grad);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("1. Аттестат\n2. Диплом");
                                        int type = Integer.parseInt(input());
                                        gradDocs = gradDocsMapper.findByType(type);
                                        for (GradDocs grad :
                                                gradDocs) {
                                            System.out.println(grad);
                                        }
                                        break;
                                    case 3:
                                        System.out.print("Введите запрос: ");
                                        gradDocs = gradDocsMapper.findByNumber(input());
                                        for (GradDocs grad :
                                                gradDocs) {
                                            System.out.println(grad);
                                        }
                                        break;
                                    case 4:
                                        System.out.print("Введите запрос: ");
                                        gradDocs = gradDocsMapper.findByAvScore(input());
                                        for (GradDocs grad :
                                                gradDocs) {
                                            System.out.println(grad);
                                        }
                                        break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case 8: {
                    boolean isEnterMenu = true;
                    while (isEnterMenu) {
                        chooseMenu(Menu.ENTER_EXAMS);
                        int enterKey = Integer.parseInt(input());
                        switch (enterKey) {
                            case 0: {
                                isEnterMenu = false;
                                break;
                            }
                            case 1: {
                                EnterExamsResults enterExamsResults = new EnterExamsResults();
                                var entrs = entrantMapper.findAll();
                                for (int i = 0; i < entrs.size(); i++) {
                                    System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                }
                                System.out.print("Введите абитуриента: ");
                                var entr = entrs.get(Integer.parseInt(input()) - 1);
                                enterExamsResults.setEntrantByIdEntr(entr);
                                var subjs = subjectsMapper.findAll();
                                for (int i = 0; i < subjs.size(); i++) {
                                    System.out.println((i + 1) + ". " + subjs.get(i).getName());
                                }
                                System.out.print("Введите предмет: ");
                                var subj = subjs.get(Integer.parseInt(input()) - 1);
                                enterExamsResults.setSubjectsByIdSubj(subj);
                                System.out.print("Введите аудиторию: ");
                                enterExamsResults.setClassroom(Integer.parseInt(input()));
                                System.out.print("Введите дату(гггг-мм-дд): ");
                                enterExamsResults.setDate(Timestamp.valueOf(input()));
                                System.out.print("Введите балл: ");
                                enterExamsResults.setScore(Integer.parseInt(input()));
                                enterExamsResultsMapper.save(enterExamsResults);
                                break;
                            }
                            case 2: {
                                var enterExamsResults = enterExamsResultsMapper.findAll();
                                for (int i = 0; i < enterExamsResults.size(); i++) {
                                    System.out.println((i + 1) + ". " + enterExamsResults.get(i));
                                }
                                System.out.print("Введите результат для удаления: ");
                                var enterExamsResult = enterExamsResults.get(Integer.parseInt(input())-1);
                                enterExamsResultsMapper.delete(enterExamsResult);
                                break;
                            }
                            case 3: {
                                var enterExamsResults = enterExamsResultsMapper.findAll();
                                for (int i = 0; i < enterExamsResults.size(); i++) {
                                    System.out.println((i + 1) + ". " + enterExamsResults.get(i));
                                }
                                break;
                            }
                            case 4: {

                                var enterExamsResults = enterExamsResultsMapper.findAll();
                                for (int i = 0; i < enterExamsResults.size(); i++) {
                                    System.out.println((i + 1) + ". " + enterExamsResults.get(i));
                                }
                                System.out.print("Введите результат для изменения (0 для отмены): ");
                                int id = Integer.parseInt(input());
                                if (id == 0)
                                    break;
                                EnterExamsResults enterExamsResult = enterExamsResults.get(id-1);
                                chooseMenu(Menu.ENTER_EXAM_INFO);
                                int editKey = Integer.parseInt(input());
                                switch (editKey){
                                    case 1:
                                        var entrs = entrantMapper.findAll();
                                        for (int i = 0; i < entrs.size(); i++) {
                                            System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                        }
                                        System.out.print("Введите абитуриента: ");
                                        var entr = entrs.get(Integer.parseInt(input()) - 1);
                                        enterExamsResult.setEntrantByIdEntr(entr);
                                        break;
                                    case 2:
                                        var subjs = subjectsMapper.findAll();
                                        for (int i = 0; i < subjs.size(); i++) {
                                            System.out.println((i + 1) + ". " + subjs.get(i).getName());
                                        }
                                        System.out.print("Введите предмет: ");
                                        var subj = subjs.get(Integer.parseInt(input()) - 1);
                                        enterExamsResult.setSubjectsByIdSubj(subj);
                                        break;
                                    case 3:
                                        System.out.print("Введите аудиторию: ");
                                        enterExamsResult.setClassroom(Integer.parseInt(input()));
                                    case 4:
                                        System.out.print("Введите дату: ");
                                        enterExamsResult.setDate(Timestamp.valueOf(input()));
                                    case 5:
                                        System.out.print("Введите балл: ");
                                        enterExamsResult.setScore(Integer.parseInt(input()));
                                }
                                enterExamsResultsMapper.edit(enterExamsResult);
                                break;
                            }
                            case 5: {
                                chooseMenu(Menu.ENTER_EXAM_INFO);
                                int findKey = Integer.parseInt(input());
                                switch (findKey) {
                                    case 1:
                                        var entrs = entrantMapper.findAll();
                                        for (int i = 0; i < entrs.size(); i++) {
                                            System.out.println((i + 1) + ". " + entrs.get(i).getName());
                                        }
                                        System.out.print("Введите абитуриента: ");
                                        var entr = entrs.get(Integer.parseInt(input()) - 1);
                                        var enterExamsResults = enterExamsResultsMapper.findByEntr(entr);
                                        for (EnterExamsResults enterExamsResult :
                                                enterExamsResults) {
                                            System.out.println(enterExamsResult);
                                        }
                                        break;
                                    case 2:
                                        var subjs = subjectsMapper.findAll();
                                        for (int i = 0; i < subjs.size(); i++) {
                                            System.out.println((i + 1) + ". " + subjs.get(i).getName());
                                        }
                                        System.out.print("Введите предмет: ");
                                        var subj = subjs.get(Integer.parseInt(input()) - 1);
                                        enterExamsResults = enterExamsResultsMapper.findBySubj(subj);
                                        for (EnterExamsResults enterExamsResult :
                                                enterExamsResults) {
                                            System.out.println(enterExamsResult);
                                        }
                                        break;
                                    case 3:
                                        System.out.print("Введите запрос: ");
                                        enterExamsResults = enterExamsResultsMapper.findByClassroom(input());
                                        for (EnterExamsResults enterExamsResult :
                                                enterExamsResults) {
                                            System.out.println(enterExamsResult);
                                        }
                                        break;
                                    case 4:
                                        System.out.print("Введите запрос: ");
                                        enterExamsResults = enterExamsResultsMapper.findByDate(input());
                                        for (EnterExamsResults enterExamsResult :
                                                enterExamsResults) {
                                            System.out.println(enterExamsResult);
                                        }
                                        break;
                                    case 5:
                                        System.out.print("Введите запрос: ");
                                        enterExamsResults = enterExamsResultsMapper.findByScore(input());
                                        for (EnterExamsResults enterExamsResult :
                                                enterExamsResults) {
                                            System.out.println(enterExamsResult);
                                        }
                                        break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        try {
            menu();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}