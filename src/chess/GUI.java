package chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GUI extends Application {

    ChessBoard3D chessboard = new ChessBoard3D();

    // ChessBoard chessboard = new ChessBoard();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        final int Width = 1920;
        final int Height = 800;
        HBox root = new HBox();
        // root.getChildren().addAll(chessboard);
        root.getChildren().addAll(chessboard.board);
        chessboard.startgame();
        chessboard.listen();
        Scene scene = new Scene(root, Width, Height);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

}
