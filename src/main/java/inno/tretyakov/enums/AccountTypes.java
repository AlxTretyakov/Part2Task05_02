package inno.tretyakov.enums;

public enum AccountTypes {
    CL("Клиентский"),
    VB("Внутрибанковский");
    final String s;
    AccountTypes(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
