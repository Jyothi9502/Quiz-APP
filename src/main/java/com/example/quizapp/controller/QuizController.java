
package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizSession;
import com.example.quizapp.model.Submission;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/start")
    public QuizSession startQuiz(@RequestParam Long userId) {
        return quizService.startNewSession(userId);
    }

    @GetMapping("/question")
    public Question getRandomQuestion() {
        return quizService.getRandomQuestion();
    }

    @PostMapping("/submit")
    public QuizSession submitAnswer(@RequestBody Submission submission) {
        return quizService.submitAnswer(submission);
    }

    @GetMapping("/summary")
    public QuizSession getQuizSummary(@RequestParam Long sessionId) {
        return quizService.getSummary(sessionId);
    }
}
