package by.itstep;

import by.itstep.models.FitnessTrainer;
import by.itstep.models.Gym;
import by.itstep.models.SportSection;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SportSection americanFootball = SportSection
                .builder()
                .nameSection("Американский футбол")
                .maxCountPeople(30)
                .typeGym(Gym.STUDIO_A)
                .build();

        SportSection swimming = SportSection
                .builder()
                .nameSection("Плавание")
                .maxCountPeople(15)
                .typeGym(Gym.SWIMMING_POOL)
                .build();

        SportSection soccer = SportSection
                .builder()
                .nameSection("Плавание")
                .maxCountPeople(15)
                .typeGym(Gym.STUDIO_B)
                .build();

        FitnessTrainer tr1 = FitnessTrainer
                .builder()
                .fio("Хорт")
                .age(25)
                .sportSection(americanFootball)
                .workExperience(1)
                .education("Тренер по американскому футболу")
                .build();

        FitnessTrainer tr2 = FitnessTrainer
                .builder()
                .fio("Стоянов")
                .age(31)
                .sportSection(swimming)
                .workExperience(5)
                .education("Тренер по плаванию")
                .build();

        FitnessTrainer tr3 = FitnessTrainer
                .builder()
                .fio("Баран")
                .age(35)
                .sportSection(soccer)
                .workExperience(4)
                .education("Тренер по футболу")
                .build();

        FitnessTrainer tr4 = FitnessTrainer
                .builder()
                .fio("Латушко")
                .age(32)
                .sportSection(americanFootball)
                .workExperience(4)
                .education("Тренер по футболу")
                .build();

        ArrayList<FitnessTrainer> fitnessTrainers = new ArrayList<>();
        fitnessTrainers.add(tr1);
        fitnessTrainers.add(tr2);
        fitnessTrainers.add(tr3);
        fitnessTrainers.add(tr4);

        fitnessTrainers.forEach(
                el -> System.out.println(el.getFio() + ";" + el.getAge() + ";" + el.getSportSection().getTypeGym())
        );

        long countTrainers = fitnessTrainers.stream()
                .filter(el -> el.getAge() > 30)
                .filter(el -> (el.getSportSection().getTypeGym()==Gym.STUDIO_A || el.getSportSection().getTypeGym()==Gym.SWIMMING_POOL))
                .count();

        System.out.println("Кол-во тренеров больше 30 лет преподающие в Зале А или Бассейне: " + countTrainers);
    }
}