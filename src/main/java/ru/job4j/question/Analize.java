package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int added = 0;
        int deleted = 0;
        Map<Integer, String> previousMap = previous.stream().collect(Collectors.toMap(User::getId, User::getName));
        Map<Integer, String> currentMap = current.stream().collect(Collectors.toMap(User::getId, User::getName));

        if (previousMap.hashCode() != currentMap.hashCode()) {

            for (Integer key : previousMap.keySet()) {
                if (!currentMap.containsKey(key)) {
                    deleted++;
                }
                if (currentMap.containsKey(key) && !currentMap.get(key).equals(previousMap.get(key))) {
                    changed++;
                }
            }
            for (Integer e : currentMap.keySet()) {
                if (!previousMap.containsKey(e)) {
                    added++;
                }
            }
        }
        return new Info(added, changed, deleted);

    }
}
