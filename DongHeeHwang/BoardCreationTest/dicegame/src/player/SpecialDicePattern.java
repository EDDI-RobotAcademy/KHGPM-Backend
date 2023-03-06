package player;

public enum SpecialDicePattern {
    PATTERN_DEATH("죽었음", 4),
    PATTERN_STEAL("3점씩 훔치기", 3),
    PATTERN_DONATE("2점씩 나눠주기", 5),
    PATTERN_BUDDY_FUCKER("고문관입니다(다같이 2점씩 까지기)", 1),
    PATTERN_NOTHING("존 스노우 유노나띵", 0);

    final private String name;
    final private int value;

    private SpecialDicePattern(String name, int value) {
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
