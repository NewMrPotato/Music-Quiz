package com.rather.quiz.Controllers;

import com.rather.quiz.Quiz.Question;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainController {

    private final String path = "PATH";

    //---------------------------------------------------------------------------------------------------------
    private Scene scene;
    private Stage stage;
    @FXML
    private AnchorPane main_AnchorPane;
    //---------------------------------------------------------------------------------------------------------
    @FXML
    private ImageView AI_ImageView;
    @FXML
    private Label question_Label;
    @FXML
    private Button changeImage_Button;
    //---------------------------------------------------------------------------------------------------------
    @FXML
    private Button answer0_Button;
    @FXML
    private Button answer1_Button;
    @FXML
    private Button answer2_Button;
    @FXML
    private Button answer3_Button;
    @FXML
    private Button answer4_Button;
    @FXML
    private Button answer5_Button;
    @FXML
    private Button answer6_Button;
    @FXML
    private Button answer7_Button;
    //---------------------------------------------------------------------------------------------------------
    private int numberOfQuestion = 0;
    private int numberOfAttempt = 0;
    private int result = 0;
    //---------------------------------------------------------------------------------------------------------

    private VBox resultsMain_VBox;
    private VBox results_VBox;
    private Label congratulation_Label;
    private Label results_Label;
    private Button restartQuiz_Button;

    private Popup results_Popup;
    private double anchorXPopupResults;
    private double anchorYPopupResults;

    private final int HeightPopupResults = 350;
    private final int WidthPopupResults = 500;

    //---------------------------------------------------------------------------------------------------------

    private static Question[] questions = {
            new Question(
                    "Which singer is in the picture?",
                    new String[]{"BTS", "Arash", "Eminem", "Adele", "BlackPink", "Sia", "Skillet", "Akcent"},0
            ),
            new Question(
                    "Which song is depicted?",
                    new String[]{"Bones", "I'm Alone", "Heat Waves", "A Day in the Life", "Sail to the Moon", "Be My Baby", "Insanity", "Blinding Lights"},7
            ),
            new Question(
                    "What genre of music is commonly associated with the image above?",
                    new String[]{"Jazz", "Reggae", "Pop", "Country", "Hip-hop", "Rock", "Classical", "Blues"},4
            ),
            new Question(
                    "What type of instrument is shown in the image above?",
                    new String[]{"Viola", "Violin", "Cello", "Double bass", "Harp", "Flute", "Clarinet", "Oboe"},6
            ),
            new Question(
                    "What genre of music is commonly associated with the image above?",
                    new String[]{"Heavy metal", "Opera", "R&B", "Electronic dance music (EDM)", "Folk", "Reggaeton", "Soul", "Punk rock"},1
            ),
            new Question(
                    "Which song is depicted?",
                    new String[]{"Feed Good", "Breaking Me", "Wasted", "Frozen", "Bad Boys", "Starboy", "Without Me", "The Monster"},4
            ),
            new Question(
                    "What type of instrument is shown in the image above?",
                    new String[]{"Harp", "Double bass", "Clarinet", "Oboe", "Viola", "Flute", "Cello", "Violin"},6
            ),
            new Question(
                    "Which singer is in the picture?",
                    new String[]{"Mozart", "Imagine Dragons", "Tom Jones", "Crazy frog", "Katy Perry", "Oliver Tree", "Ice Mc", "Sade"},1
            ),
            new Question(
                    "What genre of music is commonly associated with the image above?",
                    new String[]{"Jazz", "Reggae", "Pop", "Country", "Hip-hop", "Rock", "Classical", "Blues"},3
            ),
            new Question(
                    "What type of instrument is shown in the image above?",
                    new String[]{"Cello", "Double bass", "Clarinet", "Violin", "Viola", "Flute", "Oboe", "Harp"},7
            )
    };

    private void createFinalPopup(){
        //stage = Application.getPrimaryStage();
        stage = (Stage) question_Label.getScene().getWindow();
        Window window = question_Label.getScene().getWindow();

        // Определить координаты верхнего левого угла главного окна
        double mainX = stage.getX();
        double mainY = stage.getY();

        // Определить размер главного окна
        double mainWidth = stage.getWidth();
        double mainHeight = stage.getHeight();

        // Определить размер экрана
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        results_Popup.xProperty().add(stage.xProperty().add(mainWidth));
        results_Popup.yProperty().add(stage.yProperty());

        anchorXPopupResults = stage.getX() + (mainWidth - HeightPopupResults*(2.5));
        anchorYPopupResults = stage.getY() + (mainHeight - WidthPopupResults*(1.5));

        // Добавляем слушателя позиции главного окна
        window.xProperty().addListener((observable, oldValue, newValue) -> {
            double x = stage.getX() + (mainWidth - HeightPopupResults*(2.5));
            double y = stage.getY() + (mainHeight - WidthPopupResults*(1.5));
            results_Popup.setX(x);
            results_Popup.setY(y);
        });

        results_Label.setText(
                "The percentage of correctness is " + (result*100/30) + "%\n\n" +
                result + " out of 30 points"
        );

        results_Popup.show(stage, anchorXPopupResults, anchorYPopupResults);

        main_AnchorPane.setDisable(true);

        restartQuiz_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberOfQuestion = 0;
                result = 0;
                results_Popup.hide();
                main_AnchorPane.setDisable(false);
                setQuestion();
            }
        });
    }

    private void handlerQuizAnswer(int numButton){
        if(numberOfQuestion<10){
            if (numButton==questions[numberOfQuestion].getIndexOfRightAnswer())
                switch (numberOfAttempt) {
                    case 0: {
                        result = result + 3;
                        break;
                    }
                    case 1: {
                        result = result + 2;
                        break;
                    }
                    case 2: {
                        result = result + 1;
                        break;
                    }
                }
            numberOfQuestion++;
        } else {
            createFinalPopup();
        }
    }

    private void setQuestion(){
        if(numberOfQuestion<10) {
            numberOfAttempt = 0;

            question_Label.setText("\t" + questions[numberOfQuestion].getQuestion());
            answer0_Button.setText(questions[numberOfQuestion].getAnswers()[0]);
            answer1_Button.setText(questions[numberOfQuestion].getAnswers()[1]);
            answer2_Button.setText(questions[numberOfQuestion].getAnswers()[2]);
            answer3_Button.setText(questions[numberOfQuestion].getAnswers()[3]);
            answer4_Button.setText(questions[numberOfQuestion].getAnswers()[4]);
            answer5_Button.setText(questions[numberOfQuestion].getAnswers()[5]);
            answer6_Button.setText(questions[numberOfQuestion].getAnswers()[6]);
            answer7_Button.setText(questions[numberOfQuestion].getAnswers()[7]);

            changeImage_Button.setDisable(false);

            // You need to add your local path
            switch (numberOfQuestion) {
                case 0 -> {
                    AI_ImageView.setImage(new Image(path + "questionImages/1.1.png"));
                }
                case 1 -> {
                    AI_ImageView.setImage(new Image(path + "questionImages/2.1.png"));
                }
                case 2 -> {
                    AI_ImageView.setImage(new Image(path + "questionImages/3.1.png"));
                }
                case 3 -> {
                    AI_ImageView.setImage(new Image(path + "questionImages/4.1.png"));
                }
                case 4 -> {
                    AI_ImageView.setImage(new Image(path + "questionImages/5.1.png"));
                }
                case 5 -> {
                    AI_ImageView.setImage(new Image(path + "questionImages/6.1.png"));
                }
                case 6 -> {
                    AI_ImageView.setImage(new Image(path + "questionImages/7.1.png"));
                }
                case 7 -> {
                    AI_ImageView.setImage(new Image(path + "questionImages/8.1.png"));
                }
                case 8 -> {
                    AI_ImageView.setImage(new Image(path + "questionImages/9.1.png"));
                }
                case 9 -> {
                    AI_ImageView.setImage(new Image(path + "questionImages/10.1.png"));
                }
            }
        } else {
            createFinalPopup();
        }
    }

    private void setActionsButtons(){
        answer0_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handlerQuizAnswer(0);
                setQuestion();
            }
        });
        answer1_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handlerQuizAnswer(1);
                setQuestion();
            }
        });
        answer2_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handlerQuizAnswer(2);
                setQuestion();
            }
        });
        answer3_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handlerQuizAnswer(3);
                setQuestion();
            }
        });
        answer4_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handlerQuizAnswer(4);
                setQuestion();
            }
        });
        answer5_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handlerQuizAnswer(5);
                setQuestion();
            }
        });
        answer6_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handlerQuizAnswer(6);
                setQuestion();
            }
        });
        answer7_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handlerQuizAnswer(7);
                setQuestion();
            }
        });
    }


    @FXML
    public void initialize() {
        //---------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------
        // Настройка компонентов всплывающего окна результатов.
        results_Label = new Label();
        results_Label.setStyle("-fx-background-color: #382F85; -fx-border-color: #ffffff;");
        results_Label.setPrefSize(WidthPopupResults-60, HeightPopupResults-120);
        results_Label.setAlignment(Pos.CENTER);
        results_Label.setTextFill(Paint.valueOf("#ffffff"));
        results_Label.setFont(new Font("Agency FB", 18));

        congratulation_Label = new Label("Congratulation!");
        congratulation_Label.setStyle("-fx-background-color: #A66500; -fx-border-color: #ffffff;");
        congratulation_Label.setTextFill(Paint.valueOf("#ffffff"));
        congratulation_Label.setAlignment(Pos.CENTER);
        congratulation_Label.setFont(new Font("Agency FB", 20));
        congratulation_Label.setPrefSize(WidthPopupResults-60, HeightPopupResults-300);

        results_VBox = new VBox();
        results_VBox.setPrefSize(WidthPopupResults-60, HeightPopupResults-60);
        results_VBox.setAlignment(Pos.CENTER);
        results_VBox.getChildren().add(congratulation_Label);
        results_VBox.getChildren().add(results_Label);

        restartQuiz_Button = new Button("Restart");
        restartQuiz_Button.setTextFill(Paint.valueOf("#ffffff"));
        restartQuiz_Button.setFont(new Font("Agency FB", 20));
        restartQuiz_Button.setStyle("-fx-background-color: #FF9C00; -fx-border-color: #ffffff; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10;");

        resultsMain_VBox = new VBox();
        resultsMain_VBox.setStyle("-fx-border-color: #ffffff; -fx-background-color: #228751;");
        resultsMain_VBox.setSpacing(20);
        resultsMain_VBox.setPrefSize(WidthPopupResults, HeightPopupResults);
        resultsMain_VBox.setAlignment(Pos.CENTER);
        resultsMain_VBox.setPadding(new Insets(30));
        resultsMain_VBox.getChildren().add(results_VBox);
        resultsMain_VBox.getChildren().add(restartQuiz_Button);

        results_Popup = new Popup();
        results_Popup.getContent().add(resultsMain_VBox);
        results_Popup.setHeight(HeightPopupResults);
        results_Popup.setWidth(WidthPopupResults);
        //---------------------------------------------------------------------------------------------------------

        changeImage_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeImageAIInterface aiInterface = new changeImageAIInterface() {
                    public void setImageAI(String firstRef, String secondRef) {
                        if (numberOfAttempt==1) {
                            AI_ImageView.setImage(new Image(firstRef));
                        }else{
                            AI_ImageView.setImage(new Image(secondRef));
                            changeImage_Button.setDisable(true);
                        }
                    };
                };

                if(numberOfAttempt<3) {
                    numberOfAttempt++;

                    // You need to add your local path
                    switch (numberOfQuestion) {
                        case 0 -> {
                            aiInterface.setImageAI(path + "questionImages/1.2.png", path + "questionImages/1.3.png");
                        }
                        case 1 -> {
                            aiInterface.setImageAI(path + "questionImages/2.2.png", path + "questionImages/2.3.png");
                        }
                        case 2 -> {
                            aiInterface.setImageAI(path + "questionImages/3.2.png", path + "questionImages/3.3.png");
                        }
                        case 3 -> {
                            aiInterface.setImageAI(path + "questionImages/4.2.png", path + "questionImages/4.3.png");
                        }
                        case 4 -> {
                            aiInterface.setImageAI(path + "questionImages/5.2.png", path + "questionImages/5.3.png");
                        }
                        case 5 -> {
                            aiInterface.setImageAI(path + "questionImages/6.2.png", path + "questionImages/6.3.png");
                        }
                        case 6 -> {
                            aiInterface.setImageAI(path + "questionImages/7.2.png", path + "questionImages/7.3.png");
                        }
                        case 7 -> {
                            aiInterface.setImageAI(path + "questionImages/8.2.png", path + "questionImages/8.3.png");
                        }
                        case 8 -> {
                            aiInterface.setImageAI(path + "questionImages/9.3.png", path + "questionImages/9.2.png");
                        }
                        case 9 -> {
                            aiInterface.setImageAI(path + "questionImages/10.2.png", path + "questionImages/10.3.png");
                        }
                    }
                }
            }
        });

        //---------------------------------------------------------------------------------------------------------

        setActionsButtons();
        setQuestion();
    }

    interface changeImageAIInterface {
        void setImageAI(String firstRef, String secondRef);
    }

}