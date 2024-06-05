
module sampleGradle2{
	requires java.se;
	requires javafx.fxml;
	requires javafx.media;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	
	opens controller to javafx.fxml;
	exports service;
}
