package util;

import java.util.List;

import util.ConditionFilterItem.DataType;

public class ConditionFilter {

	private List<ConditionFilterItem> conditions;

	public List<ConditionFilterItem> getConditions() {
		return conditions;
	}

	public void setConditions(List<ConditionFilterItem> conditions) {
		this.conditions = conditions;
	}

	public String getSqlConditionString() {
		StringBuffer sql = new StringBuffer();
		if (conditions == null || conditions.size() == 0) {
			return sql.toString();
		}
		sql.append(" 1=1 ");
		for (ConditionFilterItem item : conditions) {

			if (item.getValue() != null && !"".equals(item.getValue())) {
				if (item.getDataType() == DataType.STRING) {

				} else if (item.getDataType() == DataType.DATE) {

				} else if (item.getDataType() == DataType.NUMBER) {
					switch (item.getCompareType()) {
					case LESS:
						sql.append(" AND " + item.getIdentifier() + " < "
								+ item.getValue());
						break;
					case LESS_EQ:
						sql.append(" AND " + item.getIdentifier() + " <= "
								+ item.getValue());
						break;
					case EQUAL:
						sql.append(" AND " + item.getIdentifier() + " = "
								+ item.getValue());
						break;
					case LARGER_EQ:
						sql.append(" AND " + item.getIdentifier() + " >= "
								+ item.getValue());
						break;
					case LARGER:
						sql.append(" AND " + item.getIdentifier() + " > "
								+ item.getValue());
						break;
					default:
						break;
					}
				}
			}
		}

		return sql.toString();
	}

}
