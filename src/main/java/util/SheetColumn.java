package util;

public class SheetColumn {
	public enum DataType{
		DATE,
		NUMBER,
		INT,
		STRING
	}
	
	private String columnName;
	private DataType type;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public DataType getType() {
		return type;
	}
	public void setType(DataType type) {
		this.type = type;
	}
	
	

}
