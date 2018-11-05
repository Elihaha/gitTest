package com.bst.common.entity.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderChildExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderChildExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMainIdIsNull() {
            addCriterion("main_id is null");
            return (Criteria) this;
        }

        public Criteria andMainIdIsNotNull() {
            addCriterion("main_id is not null");
            return (Criteria) this;
        }

        public Criteria andMainIdEqualTo(Long value) {
            addCriterion("main_id =", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotEqualTo(Long value) {
            addCriterion("main_id <>", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdGreaterThan(Long value) {
            addCriterion("main_id >", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("main_id >=", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdLessThan(Long value) {
            addCriterion("main_id <", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdLessThanOrEqualTo(Long value) {
            addCriterion("main_id <=", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdIn(List<Long> values) {
            addCriterion("main_id in", values, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotIn(List<Long> values) {
            addCriterion("main_id not in", values, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdBetween(Long value1, Long value2) {
            addCriterion("main_id between", value1, value2, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotBetween(Long value1, Long value2) {
            addCriterion("main_id not between", value1, value2, "mainId");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andExplainIsNull() {
            addCriterion("explain is null");
            return (Criteria) this;
        }

        public Criteria andExplainIsNotNull() {
            addCriterion("explain is not null");
            return (Criteria) this;
        }

        public Criteria andExplainEqualTo(String value) {
            addCriterion("explain =", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainNotEqualTo(String value) {
            addCriterion("explain <>", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainGreaterThan(String value) {
            addCriterion("explain >", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainGreaterThanOrEqualTo(String value) {
            addCriterion("explain >=", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainLessThan(String value) {
            addCriterion("explain <", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainLessThanOrEqualTo(String value) {
            addCriterion("explain <=", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainLike(String value) {
            addCriterion("explain like", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainNotLike(String value) {
            addCriterion("explain not like", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainIn(List<String> values) {
            addCriterion("explain in", values, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainNotIn(List<String> values) {
            addCriterion("explain not in", values, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainBetween(String value1, String value2) {
            addCriterion("explain between", value1, value2, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainNotBetween(String value1, String value2) {
            addCriterion("explain not between", value1, value2, "explain");
            return (Criteria) this;
        }

        public Criteria andGoodsCountIsNull() {
            addCriterion("goods_count is null");
            return (Criteria) this;
        }

        public Criteria andGoodsCountIsNotNull() {
            addCriterion("goods_count is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsCountEqualTo(Integer value) {
            addCriterion("goods_count =", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountNotEqualTo(Integer value) {
            addCriterion("goods_count <>", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountGreaterThan(Integer value) {
            addCriterion("goods_count >", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_count >=", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountLessThan(Integer value) {
            addCriterion("goods_count <", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountLessThanOrEqualTo(Integer value) {
            addCriterion("goods_count <=", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountIn(List<Integer> values) {
            addCriterion("goods_count in", values, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountNotIn(List<Integer> values) {
            addCriterion("goods_count not in", values, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountBetween(Integer value1, Integer value2) {
            addCriterion("goods_count between", value1, value2, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_count not between", value1, value2, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Integer value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Integer value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Integer value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Integer value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Integer value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Integer> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Integer> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Integer value1, Integer value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIsReturnIsNull() {
            addCriterion("is_return is null");
            return (Criteria) this;
        }

        public Criteria andIsReturnIsNotNull() {
            addCriterion("is_return is not null");
            return (Criteria) this;
        }

        public Criteria andIsReturnEqualTo(Boolean value) {
            addCriterion("is_return =", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnNotEqualTo(Boolean value) {
            addCriterion("is_return <>", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnGreaterThan(Boolean value) {
            addCriterion("is_return >", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_return >=", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnLessThan(Boolean value) {
            addCriterion("is_return <", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnLessThanOrEqualTo(Boolean value) {
            addCriterion("is_return <=", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnIn(List<Boolean> values) {
            addCriterion("is_return in", values, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnNotIn(List<Boolean> values) {
            addCriterion("is_return not in", values, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnBetween(Boolean value1, Boolean value2) {
            addCriterion("is_return between", value1, value2, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_return not between", value1, value2, "isReturn");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeIsNull() {
            addCriterion("sales_return_time is null");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeIsNotNull() {
            addCriterion("sales_return_time is not null");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeEqualTo(Date value) {
            addCriterion("sales_return_time =", value, "salesReturnTime");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeNotEqualTo(Date value) {
            addCriterion("sales_return_time <>", value, "salesReturnTime");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeGreaterThan(Date value) {
            addCriterion("sales_return_time >", value, "salesReturnTime");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sales_return_time >=", value, "salesReturnTime");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeLessThan(Date value) {
            addCriterion("sales_return_time <", value, "salesReturnTime");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeLessThanOrEqualTo(Date value) {
            addCriterion("sales_return_time <=", value, "salesReturnTime");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeIn(List<Date> values) {
            addCriterion("sales_return_time in", values, "salesReturnTime");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeNotIn(List<Date> values) {
            addCriterion("sales_return_time not in", values, "salesReturnTime");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeBetween(Date value1, Date value2) {
            addCriterion("sales_return_time between", value1, value2, "salesReturnTime");
            return (Criteria) this;
        }

        public Criteria andSalesReturnTimeNotBetween(Date value1, Date value2) {
            addCriterion("sales_return_time not between", value1, value2, "salesReturnTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}