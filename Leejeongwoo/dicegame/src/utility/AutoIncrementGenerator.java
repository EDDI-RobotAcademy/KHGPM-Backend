package utility;

import java.util.HashMap;
import java.util.Map;

// 플레이어들 자동 시퀀스 느낌?
public class AutoIncrementGenerator {

    private static Map<String, Long> entityNameMappedCurrentId = new HashMap<>();

    public static void setAutoIncrementEntity(String entityName) {
        if (entityNameMappedCurrentId.get(entityName) == null) {
            entityNameMappedCurrentId.put(entityName, 1L);
        } else {
            System.out.println("이미 있는 놈이야");
        }
    }

    public static Long getEntityAutoIncrementValue(String entityName) {
        if (entityNameMappedCurrentId.get(entityName) == null) {
            System.out.println("설정된 자동 증가 설정이 존재하지 않습니다!");
            return 0L;
        }

        Long currentId = entityNameMappedCurrentId.get(entityName);
        entityNameMappedCurrentId.put(entityName, currentId + 1);

        return currentId;
    }
}