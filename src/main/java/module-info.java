
module sampleGradle2{
	requires java.se;
	requires javafx.fxml;
	requires javafx.media;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.graphics;
	
	opens controller to javafx.fxml;
	exports service;
}
