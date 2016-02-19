package main;

import services.MainService;
import util.Format;

public class Main {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        MainService frame = MainService.getInstance();
        frame.initialize();
        frame.setSize(Format.screenWidth(), Format.screenHeight());
        frame.setUndecorated(true);
        frame.show();
    }
}
