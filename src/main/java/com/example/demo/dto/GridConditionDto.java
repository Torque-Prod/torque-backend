package com.example.demo.dto;


import com.example.demo.utills.TableConstant;
import com.papertrl.common.utils.CommonConstants;
import static com.papertrl.common.utils.CommonConstants.SQL_AND_CONDITION;
import java.io.Serializable;
import java.util.HashMap;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class GridConditionDto implements Serializable {

    private HashMap<String, String> queryConditions;
    private HashMap<Integer, String> querySortOrders;

    public GridConditionDto() {

        queryConditions = new HashMap<>();
        queryConditions.put(TableConstant.STARTS_WITH, CommonConstants.SQL_LIKE_CLAUSE + TableConstant.TABLE_CONDITION_VALUE_ATTR);
        queryConditions.put(TableConstant.CONTAINS, CommonConstants.SQL_LIKE_CLAUSE + TableConstant.TABLE_CONDITION_VALUE_ATTR);
        queryConditions.put(TableConstant.NOT_CONTAINS, CommonConstants.SQL_NOT_LIKE_CLAUSE + TableConstant.TABLE_CONDITION_VALUE_ATTR);
        queryConditions.put(TableConstant.ENDS_WITH, CommonConstants.SQL_LIKE_CLAUSE + TableConstant.TABLE_CONDITION_VALUE_ATTR);
        queryConditions.put(TableConstant.EQUALS, CommonConstants.SQL_EQUAL + TableConstant.TABLE_CONDITION_VALUE_ATTR);
        queryConditions.put(TableConstant.NOT_EQUALS, CommonConstants.SQL_NOT_EQUAL + TableConstant.TABLE_CONDITION_VALUE_ATTR);
        queryConditions.put(TableConstant.PROPERTY_IN, CommonConstants.SQL_IN + TableConstant.TABLE_CONDITION_VALUE_ATTR);
        queryConditions.put(TableConstant.IS, CommonConstants.SQL_IS + TableConstant.TABLE_CONDITION_VALUE_ATTR);
        queryConditions.put(TableConstant.TABLE_CONDITION_BETWEEN, CommonConstants.SQL_BETWEEN_CLAUSE + TableConstant.TABLE_CONDITION_FROM_ATTR
                + SQL_AND_CONDITION
                + TableConstant.TABLE_CONDITION_TO_ATTR);

        querySortOrders = new HashMap<>();
        querySortOrders.put(1, TableConstant.ORDER_ASC);
        querySortOrders.put(-1, TableConstant.ORDER_DESC);
    }

}
