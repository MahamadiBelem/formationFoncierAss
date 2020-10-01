package bf.agriculture.dgfomr.domain.enumeration;

/**
 * The RegimeFormateur enumeration.
 */
public enum RegimeFormateur {
    Vacataire,
    Permanent;

    private String value;

    RegimeFormateur() {}

    RegimeFormateur(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
