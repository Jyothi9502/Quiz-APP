
package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizSession;
import com.example.quizapp.model.Submission;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.repository.QuizSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private QuizSessionRepository sessionRepo;

    public QuizSession startNewSession(Long userId) {
        QuizSession session = new QuizSession();
        session.setUserId(userId);
        session.setCorrectAnswers(0);
        session.setTotalQuestions(0);
        return sessionRepo.save(session);
    }

    public Question getRandomQuestion() {
        List<Question> questions = questionRepo.findAll();
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }

    public QuizSession submitAnswer(Submission submission) {
        Optional<QuizSession> sessionOpt = sessionRepo.findById(submission.getSessionId());
        if (sessionOpt.isEmpty()) {
            throw new RuntimeException("Invalid Session ID");
        }
        QuizSession session = sessionOpt.get();
        Question question = questionRepo.findById(submission.getQuestionId())
                                        .orElseThrow(() -> new RuntimeException("Invalid Question ID"));

        session.setTotalQuestions(session.getTotalQuestions() + 1);
        if (question.getCorrectAnswer().equalsIgnoreCase(submission.getSelectedOption())) {
            session.setCorrectAnswers(session.getCorrectAnswers() + 1);
        }
        return sessionRepo.save(session);
    }

    public QuizSession getSummary(Long sessionId) {
        return sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }
}
