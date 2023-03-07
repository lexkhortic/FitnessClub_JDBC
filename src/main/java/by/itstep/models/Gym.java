package by.itstep.models;

public enum Gym {
    STUDIO_A("Зал А"),
    STUDIO_B("Зал Б"),
    STUDIO_C("Зал В"),
    SWIMMING_POOL("Бассейн");

    final String typeGym;

    Gym(String typeGym) {
        this.typeGym = typeGym;
    }
}
