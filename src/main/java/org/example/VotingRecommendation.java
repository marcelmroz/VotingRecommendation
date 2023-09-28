package org.example;

import java.util.*;

public class VotingRecommendation {

    static class PartyResult {
        String partyName;
        int percentage;

        public PartyResult(String partyName, int percentage) {
            this.partyName = partyName;
            this.percentage = percentage;
        }
    }

    private static final Map<String, List<PartyResult>> electionData = new HashMap<>();

    static {
        List<PartyResult> constituency1 = Arrays.asList(
                new PartyResult("PartiaA", 45),
                new PartyResult("PartiaB", 30),
                new PartyResult("PartiaC", 15),
                new PartyResult("PartiaD", 10));

        List<PartyResult> constituency2 = Arrays.asList(
                new PartyResult("PartiaE", 40),
                new PartyResult("PartiaA", 35),
                new PartyResult("PartiaC", 25));

        electionData.put("Okręg1", constituency1);
        electionData.put("Okręg2", constituency2);
    }

    private static boolean hasAChance(int percentage) {
        final int THRESHOLD = 10;
        return percentage > THRESHOLD;
    }

    public static String whoToVoteFor(String constituency, String unwantedParty) {
        List<PartyResult> results = electionData.get(constituency);
        results.sort((a, b) -> b.percentage - a.percentage);  // Sort in descending order

        for (PartyResult result : results) {
            if (!result.partyName.equals(unwantedParty) && hasAChance(result.percentage)) {
                return result.partyName;
            }
        }
        return "Nie znaleziono odpowiedniej partii";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("W jakim okręgu głosujesz? Dostępne opcje: " + String.join(", ", electionData.keySet()));
        String chosenConstituency = scanner.nextLine();

        System.out.println("Na którą partię NIE chcesz głosować?");
        String unwantedParty = scanner.nextLine();

        String recommendation = whoToVoteFor(chosenConstituency, unwantedParty);
        System.out.println("Rekomendacja dla Ciebie: Głosuj na " + recommendation + " w okręgu " + chosenConstituency);
    }
}
