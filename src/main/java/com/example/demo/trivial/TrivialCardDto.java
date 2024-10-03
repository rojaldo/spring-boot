package com.example.demo.trivial;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrivialCardDto implements ICardResponse {

    private long id;

    private String question;

    @JsonProperty("right_answer")
    private String rightAnswer;

    @JsonProperty("wrong_answers")
    private String[] wrongAnswers;

    private String category;

    private String difficulty;

    public static String EncodeCategory(int categoryString) {
        switch (categoryString) {
            case 0:
                return "general knowledge";
            case 1:
                return "science";
            case 2:
                return "math";
            case 3:
                return "geography";
            case 4:
                return "history";
            case 5:
                return "art";
            case 6:
                return "sports";
            case 7:
                return "music";
            case 8:
                return "movies";
            case 9:
                return "books";
            default:
                return "uknown";
        }
    }

    public static int decodeCategory(String categoryString) {
        switch (categoryString.toLowerCase()) {
            case "general knowledge":
                return 0;
            case "science":
                return 1;
            case "math":
                return 2;
            case "geography":
                return 3;
            case "history":
                return 4;
            case "art":
                return 5;
            case "sports":
                return 6;
            case "music":
                return 7;
            case "movies":
                return 8;
            case "books":
                return 9;
            default:
                return -1;
        }
    
    }

    public static String EncodeDifficulty(int difficultyString) {
        switch (difficultyString) {
            case 0:
                return "easy";
            case 1:
                return "medium";
            case 2:
                return "hard";
            default:
                return "uknown";
        }
    }

    public static int decodeDifficulty(String difficultyString) {
        switch (difficultyString.toLowerCase()) {
            case "easy":
                return 0;
            case "medium":
                return 1;
            case "hard":
                return 2;
            default:
                return -1;
        }
    }

}
