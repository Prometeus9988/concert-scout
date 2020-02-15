package logic.fxmlview;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class SingleListPage {

	@FXML
	protected VBox menuBar;
	@FXML
	protected HBox scrPane;
	@FXML
	protected VBox secRoot;
	@FXML
	private VBox col1;
	@FXML
	private VBox col2;
	@FXML
	private VBox col3;
	@FXML
	private VBox col4;
	@FXML
	private VBox col5;
	
	protected List<VBox> initList() {
		List<VBox> columns=new ArrayList<>();
		columns.add(this.col1);
		columns.add(this.col2);
		columns.add(this.col3);
		columns.add(this.col4);
		columns.add(this.col5);
		return columns;
	}

}
