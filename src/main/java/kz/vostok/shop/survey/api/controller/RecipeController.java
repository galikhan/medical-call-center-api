package kz.vostok.shop.survey.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import kz.vostok.shop.survey.api.record.Survey;
import kz.vostok.shop.survey.api.repository.DictionaryRepository;
import kz.vostok.shop.survey.api.repository.ParticipantAnswerRepository;
import kz.vostok.shop.survey.api.repository.ParticipantRepository;
import kz.vostok.shop.survey.api.repository.QuestionRepository;
import kz.vostok.shop.survey.api.repository.SurveyRepository;
import kz.vostok.shop.survey.api.repository.VitaminConfigParamRepository;
import kz.vostok.shop.survey.api.repository.VitaminConfigRepository;
import kz.vostok.shop.survey.api.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@Controller("/api/v1/recipe")
@Secured(SecurityRule.IS_ANONYMOUS)
public class RecipeController {

    private EmailService emailService;
    private ParticipantRepository participantRepository;
    private ParticipantAnswerRepository participantAnswerRepository;
    private VitaminConfigRepository vitaminConfigRepository;
    private VitaminConfigParamRepository vitaminConfigParamRepository;
    private SurveyRepository surveyRepository;
    private DictionaryRepository dictionaryRepository;
    private QuestionRepository questionRepository;

    private Logger log = LoggerFactory.getLogger(RecipeController.class);

    public RecipeController(EmailService emailService,
                            ParticipantRepository participantRepository,
                            ParticipantAnswerRepository participantAnswerRepository,
                            VitaminConfigRepository vitaminConfigRepository,
                            VitaminConfigParamRepository vitaminConfigParamRepository,
                            SurveyRepository surveyRepository,
                            DictionaryRepository dictionaryRepository,
                            QuestionRepository questionRepository) {
        this.emailService = emailService;
        this.participantRepository = participantRepository;
        this.participantAnswerRepository = participantAnswerRepository;
        this.vitaminConfigRepository = vitaminConfigRepository;
        this.vitaminConfigParamRepository = vitaminConfigParamRepository;
        this.surveyRepository = surveyRepository;
        this.dictionaryRepository = dictionaryRepository;
        this.questionRepository = questionRepository;
    }

    @Get("/send")
    public void send() {
        this.emailService.sendWelcomeEmail("galixan@mail.ru", "galikhan.khamitov@gmail.com");
    }

    @Get("/participant/{participant}")
    public Object sendRecipeEmail(Long participant) {

        Set<Long> vitaminConfigSet = new HashSet<>();
        var answers = this.participantAnswerRepository.findByParticipant(participant);
        var optionalSurvey = this.surveyRepository.findActive();
        Map<Long, Integer> configMeetAnswerCount = new HashMap<>();

        if(optionalSurvey.isPresent()) {

            var survey = Survey.to(optionalSurvey.get());
            log.info("survey {}", survey);
            var allVitaminConfigIds = vitaminConfigRepository
                    .findBySurveyId(survey.id())
                    .stream().map(item -> item.id())
                    .collect(Collectors.toUnmodifiableList());

            log.info("allVitaminConfigIds {}", allVitaminConfigIds);

            answers.stream().forEach(answer -> {

                var question = this.questionRepository.findById(answer.question());
                var dictOptional = this.dictionaryRepository.findRecordById(question.category());
                var categoryCode = dictOptional.isPresent()? dictOptional.get().getCode_() : null;
                if("name".equals(categoryCode) || "email".equals(categoryCode) || "city".equals(categoryCode)) {
                    log.info("non filter param {}", categoryCode);
                } else {
                    var selectedVitaminConfigIds = vitaminConfigParamRepository.findConfigsThatMeetsAnswer(allVitaminConfigIds, dictOptional, answer, question);
//                    log.info("ids - of answer {}, answers - {}, {}", answer.value(), answer.answers(), selectedVitaminConfigIds);
                    selectedVitaminConfigIds.stream().forEach(id -> {
                        if(configMeetAnswerCount.containsKey(id)) {
                            configMeetAnswerCount.put(id, configMeetAnswerCount.get(id) + 1);
                        } else {
                            configMeetAnswerCount.put(id, 1);
                        }
                    });
                    vitaminConfigSet.addAll(selectedVitaminConfigIds);
                }
            });
        }

        Long maxKey = configMeetAnswerCount.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        if(maxKey != null) {
            return vitaminConfigRepository.findById(maxKey);
        }
        //        this.emailService.sendWelcomeEmail("galixan@mail.ru", "galikhan.khamitov@gmail.com");
        return null;
    }

}
