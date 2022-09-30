package bookSeat;

public class BookSystem {
	private String[][] seat;
	private String[] arr= {"A", "B", "C"};
	
	public BookSystem() {
		seat=new String[3][10];
		for(int i=0; i<seat.length; i++) {
			for(int j=0; j<seat[i].length; j++)
				seat[i][j]="□";
		}
	}
	
	public String showSeats() {
		String seatResult="";
		for(int i=0; i<seat.length; i++) {
			seatResult+=arr[i];
			for(int j=0; j<seat[i].length; j++)
				seatResult+=seat[i][j];
			seatResult+="\n";
		}
		seatResult+="\tScreen";
		return seatResult;
	}
	public String BookSeat(String aaa, int num) {
		int ver=0;
		int number=num-1;
		switch(aaa) {
			case "A":
				ver=0;
				break;
			case "B":
				ver=1;
				break;
			case "C":
				ver=2;
				break;
			default:
				ver=3;
		}
		if(ver>2 || (number>9 || number<0))
			return "잘못 입력했습니다. 제대로 입력해주세요.";
		if(seat[ver][number].equals("□")) {
			seat[ver][number]="■";
			return aaa+"행"+num+"열 예약완료";
		}else 
			return "이미 예약된 좌석입니다.";
	}
	public String cancelSeat(String aaa, int num) {
		int ver=0;
		int number=num-1;
		switch(aaa) {
			case "A":
				ver=0;
				break;
			case "B":
				ver=1;
				break;
			case "C":
				ver=2;
				break;
			default:
				ver=3;
		}
		if(ver>2 || (number>9 || number<0))
			return "잘못 입력했습니다. 제대로 입력해주세요.";
		if(seat[ver][number].equals("■")) {
			seat[ver][number]="□";
			return aaa+"행"+num+"열 예약 취소 완료";
		}else 
			return "예약된 좌석이 아닙니다.";
	}
	public String[][] getSeat(){
		return seat;
	}
}
