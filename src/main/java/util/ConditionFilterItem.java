package util;

public class ConditionFilterItem {

	public enum DataType {
		STRING, DATE, NUMBER;
		
	}

	public enum CompareType {
		LESS, LESS_EQ, EQUAL, LARGER_EQ, LARGER, LIKE
		
	}

	private DataType dataType;
	private CompareType compareType;
	private String identifier; // the identifier in SQL
	private String value;

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public CompareType getCompareType() {
		return compareType;
	}

	public void setCompareType(CompareType compareType) {
		this.compareType = compareType;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
