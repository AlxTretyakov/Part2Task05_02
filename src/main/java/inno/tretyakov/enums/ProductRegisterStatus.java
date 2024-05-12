package inno.tretyakov.enums;

public enum ProductRegisterStatus {
    CLOSED("0"),
    OPEN("1"),
    RESERVED("2"),
    DELETED("3");
    final String s;

    ProductRegisterStatus(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
