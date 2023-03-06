package player;

public enum SpecialDicePattern {
    PATTERN_DEATH("죽었음", 4),
    PATTERN_STEAL("3점씩 훔치기", 3),
    //영어 단어 기록;? donate : 기부하다.
    PATTERN_DONATE("2점씩 나눠주기", 5),
    PATTERN_BUDDY_FUCKER("다같이 2점씩 까지기", 1),
    PATTERN_NOTHING("아무것도 없음", 0);

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