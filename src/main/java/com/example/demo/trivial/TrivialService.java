package com.example.demo.trivial;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrivialService {

    @Autowired
    private TrivialRepository trivialRepository;

    public List<ICardResponse> getCards(int amount) {
        List<TrivialCardEntity> cards = this.trivialRepository.findAll();
        // return the amount of cards requested
        return cards.stream().limit(amount).map(this::trivialCardEntityToDto).collect(Collectors.toList());
    }

    public ICardResponse createCard(TrivialCardDto card) {
        log.info("Creating card: " + card);
        TrivialCardEntity entity = TrivialCardEntity.builder()
        .question(card.getQuestion())
        .rightAnswer(card.getRightAnswer())
        .wrongAnswer1(card.getWrongAnswers()[0])
        .wrongAnswer2(card.getWrongAnswers()[1])
        .wrongAnswer3(card.getWrongAnswers()[2])
        .category(TrivialCardDto.decodeCategory(card.getCategory()))
        .difficulty(TrivialCardDto.decodeDifficulty(card.getDifficulty()))
        .build();
        return this.trivialCardEntityToDto(this.trivialRepository.save(entity));
    }

    private TrivialCardDto trivialCardEntityToDto(TrivialCardEntity card) {
        return TrivialCardDto.builder()
        .id(card.getId())
        .question(card.getQuestion())
        .rightAnswer(card.getRightAnswer())
        .wrongAnswers(new String[] {card.getWrongAnswer1(), card.getWrongAnswer2(), card.getWrongAnswer3()})
        .category(TrivialCardDto.EncodeCategory(card.getCategory()))
        .difficulty(TrivialCardDto.EncodeDifficulty(card.getDifficulty()))
        .build();
    }
    
}
