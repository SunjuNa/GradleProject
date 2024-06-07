
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
	
	opens bean.dto to javafx.base; 
	opens bean.dao to javafx.base;
	
}
