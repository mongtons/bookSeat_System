package bookSeat;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppMain extends Application{
	String message="";
	BookSystem bs=new BookSystem();
	@Override
	public void start(Stage primaryStage) throws Exception{
		VBox root=new VBox();
		HBox root2=new HBox();
		root.setPrefSize(600, 300);
		root.setPadding(new Insets(10, 10, 10, 10));
		
		root.setAlignment(Pos.CENTER);
		root.setSpacing(20);
		root2.setSpacing(20);
		
		TextField textField1=new TextField();
		textField1.setPrefWidth(50);
		
		TextField textField2=new TextField();
		textField2.setPrefWidth(50);
		
		TextArea textArea=new TextArea();
		textArea.setPrefWidth(200);
		message+=bs.showSeats()+"\n";
		message+="첫번째 텍스트상자에는 좌석 행 입력(ex: A)\n두번째 텍스트상자에는 좌석 열 입력(ex: 2)\n";
		textArea.setText(message);
		
		Label label=new Label();
		label.setPrefWidth(500);
		label.setText("예약 현황");
		label.setFont(new Font(20));
		
		Button btn1=new Button("전체 좌석 조회");
		Button btn2=new Button("좌석 예약");
		Button btn3=new Button("예약된 좌석 목록");
		Button btn4=new Button("예약 취소");
		Button btn5=new Button("초기화");
		
		btn1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				message=bs.showSeats()+"\n";
				message+="첫번째 텍스트상자에는 좌석 행 입력(ex: A)\n두번째 텍스트상자에는 좌석 열 입력(ex: 2)\n";
				textArea.setText(message);
			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String temp1=textField1.getText();
				String temp2=textField2.getText();
				try {
					label.setText(bs.BookSeat(temp1, Integer.valueOf(temp2)));
				}catch(NumberFormatException e) {
					label.setText("두번째 텍스트상자에 숫자를 입력해주세요.");
				}
			}
		});
		btn3.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				ArrayList<String> seatResult=new ArrayList<String>();
				String[][] seat=bs.getSeat();
				for(int i=0; i<bs.getSeat().length; i++) {
					for(int j=0; j<bs.getSeat()[i].length; j++) {
						if(seat[i][j].equals("■")) {
							switch(i) {
								case 0:
									seatResult.add("A행"+(j+1)+"열");
									break;
								case 1:
									seatResult.add("B행"+(j+1)+"열");
									break;
								case 2:
									seatResult.add("C행"+(j+1)+"열");
									break;
							}
						}
					}
				}
				if(seatResult.isEmpty()) {
					message+="현재 예약된 좌석이 없습니다.";
					textArea.setText(message);
				}else {
					message+="현재 예약되어 있는 좌석 수: "+seatResult.size()+"\n";
					message+=seatResult+"\n";
					textArea.setText(message);
				}
			}
		});
		btn4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String temp1=textField1.getText();
				String temp2=textField2.getText();
				try {
					label.setText(bs.cancelSeat(temp1, Integer.valueOf(temp2)));
				}catch(NumberFormatException e) {
					label.setText("두번째 텍스트상자에 숫자를 입력해주세요.");
				}
			}
		});
		btn5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BookSystem newBS=new BookSystem();
				bs=newBS;
				label.setText("시스템이 초기화 되었습니다.");
			}
		});
		
		ObservableList list=root.getChildren();
		list.addAll(textField1, textField2);
		root2.getChildren().addAll(btn1, btn2, btn3, btn4, btn5);
		list.add(root2);
		
		list.add(textArea);
		list.add(label);
		
		Scene scene=new Scene(root);
		
		primaryStage.setTitle("20185231_김지환");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}