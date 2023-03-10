package dice;

public enum SpecialDicePattern {

    PATTERN_NOTHING("아무것도 아님", 0),
    PATTERN_BUDDY_FUCKER("모두 2점씩 감점", 1),
    PATTERN_STEAL("3점씩 훔치기", 3),
    PATTERN_DEATH("죽었음", 4),
    PATTERN_DONATE("2점씩 나눠주기", 5);

    final private String name;
    final private int value;

    SpecialDicePattern(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}