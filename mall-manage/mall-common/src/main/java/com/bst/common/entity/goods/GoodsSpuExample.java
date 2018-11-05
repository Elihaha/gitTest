package com.bst.common.entity.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsSpuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsSpuExample() {
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

        public Criteria andSpuNoIsNull() {
            addCriterion("spu_no is null");
            return (Criteria) this;
        }

        public Criteria andSpuNoIsNotNull() {
            addCriterion("spu_no is not null");
            return (Criteria) this;
        }

        public Criteria andSpuNoEqualTo(String value) {
            addCriterion("spu_no =", value, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoNotEqualTo(String value) {
            addCriterion("spu_no <>", value, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoGreaterThan(String value) {
            addCriterion("spu_no >", value, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoGreaterThanOrEqualTo(String value) {
            addCriterion("spu_no >=", value, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoLessThan(String value) {
            addCriterion("spu_no <", value, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoLessThanOrEqualTo(String value) {
            addCriterion("spu_no <=", value, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoLike(String value) {
            addCriterion("spu_no like", value, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoNotLike(String value) {
            addCriterion("spu_no not like", value, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoIn(List<String> values) {
            addCriterion("spu_no in", values, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoNotIn(List<String> values) {
            addCriterion("spu_no not in", values, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoBetween(String value1, String value2) {
            addCriterion("spu_no between", value1, value2, "spuNo");
            return (Criteria) this;
        }

        public Criteria andSpuNoNotBetween(String value1, String value2) {
            addCriterion("spu_no not between", value1, value2, "spuNo");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Long value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Long value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Long value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Long value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Long value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Long> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Long> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Long value1, Long value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Long value1, Long value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andLowPriceIsNull() {
            addCriterion("low_price is null");
            return (Criteria) this;
        }

        public Criteria andLowPriceIsNotNull() {
            addCriterion("low_price is not null");
            return (Criteria) this;
        }

        public Criteria andLowPriceEqualTo(BigDecimal value) {
            addCriterion("low_price =", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceNotEqualTo(BigDecimal value) {
            addCriterion("low_price <>", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceGreaterThan(BigDecimal value) {
            addCriterion("low_price >", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("low_price >=", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceLessThan(BigDecimal value) {
            addCriterion("low_price <", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("low_price <=", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceIn(List<BigDecimal> values) {
            addCriterion("low_price in", values, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceNotIn(List<BigDecimal> values) {
            addCriterion("low_price not in", values, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("low_price between", value1, value2, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("low_price not between", value1, value2, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceIsNull() {
            addCriterion("high_price is null");
            return (Criteria) this;
        }

        public Criteria andHighPriceIsNotNull() {
            addCriterion("high_price is not null");
            return (Criteria) this;
        }

        public Criteria andHighPriceEqualTo(BigDecimal value) {
            addCriterion("high_price =", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceNotEqualTo(BigDecimal value) {
            addCriterion("high_price <>", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceGreaterThan(BigDecimal value) {
            addCriterion("high_price >", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("high_price >=", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceLessThan(BigDecimal value) {
            addCriterion("high_price <", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("high_price <=", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceIn(List<BigDecimal> values) {
            addCriterion("high_price in", values, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceNotIn(List<BigDecimal> values) {
            addCriterion("high_price not in", values, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("high_price between", value1, value2, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("high_price not between", value1, value2, "highPrice");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Long value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Long value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Long value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Long value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Long> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Long> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Long value1, Long value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNull() {
            addCriterion("brand_id is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Long value) {
            addCriterion("brand_id =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Long value) {
            addCriterion("brand_id <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Long value) {
            addCriterion("brand_id >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Long value) {
            addCriterion("brand_id >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Long value) {
            addCriterion("brand_id <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Long value) {
            addCriterion("brand_id <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Long> values) {
            addCriterion("brand_id in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Long> values) {
            addCriterion("brand_id not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Long value1, Long value2) {
            addCriterion("brand_id between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Long value1, Long value2) {
            addCriterion("brand_id not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Byte value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Byte value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Byte value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Byte value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Byte value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Byte value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Byte> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Byte> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Byte value1, Byte value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Byte value1, Byte value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayIsNull() {
            addCriterion("total_putaway is null");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayIsNotNull() {
            addCriterion("total_putaway is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayEqualTo(Integer value) {
            addCriterion("total_putaway =", value, "totalPutaway");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayNotEqualTo(Integer value) {
            addCriterion("total_putaway <>", value, "totalPutaway");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayGreaterThan(Integer value) {
            addCriterion("total_putaway >", value, "totalPutaway");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_putaway >=", value, "totalPutaway");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayLessThan(Integer value) {
            addCriterion("total_putaway <", value, "totalPutaway");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayLessThanOrEqualTo(Integer value) {
            addCriterion("total_putaway <=", value, "totalPutaway");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayIn(List<Integer> values) {
            addCriterion("total_putaway in", values, "totalPutaway");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayNotIn(List<Integer> values) {
            addCriterion("total_putaway not in", values, "totalPutaway");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayBetween(Integer value1, Integer value2) {
            addCriterion("total_putaway between", value1, value2, "totalPutaway");
            return (Criteria) this;
        }

        public Criteria andTotalPutawayNotBetween(Integer value1, Integer value2) {
            addCriterion("total_putaway not between", value1, value2, "totalPutaway");
            return (Criteria) this;
        }

        public Criteria andTotalStockIsNull() {
            addCriterion("total_stock is null");
            return (Criteria) this;
        }

        public Criteria andTotalStockIsNotNull() {
            addCriterion("total_stock is not null");
            return (Criteria) this;
        }

        public Criteria andTotalStockEqualTo(Integer value) {
            addCriterion("total_stock =", value, "totalStock");
            return (Criteria) this;
        }

        public Criteria andTotalStockNotEqualTo(Integer value) {
            addCriterion("total_stock <>", value, "totalStock");
            return (Criteria) this;
        }

        public Criteria andTotalStockGreaterThan(Integer value) {
            addCriterion("total_stock >", value, "totalStock");
            return (Criteria) this;
        }

        public Criteria andTotalStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_stock >=", value, "totalStock");
            return (Criteria) this;
        }

        public Criteria andTotalStockLessThan(Integer value) {
            addCriterion("total_stock <", value, "totalStock");
            return (Criteria) this;
        }

        public Criteria andTotalStockLessThanOrEqualTo(Integer value) {
            addCriterion("total_stock <=", value, "totalStock");
            return (Criteria) this;
        }

        public Criteria andTotalStockIn(List<Integer> values) {
            addCriterion("total_stock in", values, "totalStock");
            return (Criteria) this;
        }

        public Criteria andTotalStockNotIn(List<Integer> values) {
            addCriterion("total_stock not in", values, "totalStock");
            return (Criteria) this;
        }

        public Criteria andTotalStockBetween(Integer value1, Integer value2) {
            addCriterion("total_stock between", value1, value2, "totalStock");
            return (Criteria) this;
        }

        public Criteria andTotalStockNotBetween(Integer value1, Integer value2) {
            addCriterion("total_stock not between", value1, value2, "totalStock");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateIsNull() {
            addCriterion("gmt_update is null");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateIsNotNull() {
            addCriterion("gmt_update is not null");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateEqualTo(Date value) {
            addCriterion("gmt_update =", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateNotEqualTo(Date value) {
            addCriterion("gmt_update <>", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateGreaterThan(Date value) {
            addCriterion("gmt_update >", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_update >=", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateLessThan(Date value) {
            addCriterion("gmt_update <", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_update <=", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateIn(List<Date> values) {
            addCriterion("gmt_update in", values, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateNotIn(List<Date> values) {
            addCriterion("gmt_update not in", values, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateBetween(Date value1, Date value2) {
            addCriterion("gmt_update between", value1, value2, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_update not between", value1, value2, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeIsNull() {
            addCriterion("putaway_time is null");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeIsNotNull() {
            addCriterion("putaway_time is not null");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeEqualTo(Date value) {
            addCriterion("putaway_time =", value, "putawayTime");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeNotEqualTo(Date value) {
            addCriterion("putaway_time <>", value, "putawayTime");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeGreaterThan(Date value) {
            addCriterion("putaway_time >", value, "putawayTime");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("putaway_time >=", value, "putawayTime");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeLessThan(Date value) {
            addCriterion("putaway_time <", value, "putawayTime");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeLessThanOrEqualTo(Date value) {
            addCriterion("putaway_time <=", value, "putawayTime");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeIn(List<Date> values) {
            addCriterion("putaway_time in", values, "putawayTime");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeNotIn(List<Date> values) {
            addCriterion("putaway_time not in", values, "putawayTime");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeBetween(Date value1, Date value2) {
            addCriterion("putaway_time between", value1, value2, "putawayTime");
            return (Criteria) this;
        }

        public Criteria andPutawayTimeNotBetween(Date value1, Date value2) {
            addCriterion("putaway_time not between", value1, value2, "putawayTime");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeIsNull() {
            addCriterion("remove_time is null");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeIsNotNull() {
            addCriterion("remove_time is not null");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeEqualTo(Date value) {
            addCriterion("remove_time =", value, "removeTime");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeNotEqualTo(Date value) {
            addCriterion("remove_time <>", value, "removeTime");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeGreaterThan(Date value) {
            addCriterion("remove_time >", value, "removeTime");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("remove_time >=", value, "removeTime");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeLessThan(Date value) {
            addCriterion("remove_time <", value, "removeTime");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeLessThanOrEqualTo(Date value) {
            addCriterion("remove_time <=", value, "removeTime");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeIn(List<Date> values) {
            addCriterion("remove_time in", values, "removeTime");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeNotIn(List<Date> values) {
            addCriterion("remove_time not in", values, "removeTime");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeBetween(Date value1, Date value2) {
            addCriterion("remove_time between", value1, value2, "removeTime");
            return (Criteria) this;
        }

        public Criteria andRemoveTimeNotBetween(Date value1, Date value2) {
            addCriterion("remove_time not between", value1, value2, "removeTime");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusIsNull() {
            addCriterion("goods_status is null");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusIsNotNull() {
            addCriterion("goods_status is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusEqualTo(Byte value) {
            addCriterion("goods_status =", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotEqualTo(Byte value) {
            addCriterion("goods_status <>", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusGreaterThan(Byte value) {
            addCriterion("goods_status >", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("goods_status >=", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusLessThan(Byte value) {
            addCriterion("goods_status <", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusLessThanOrEqualTo(Byte value) {
            addCriterion("goods_status <=", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusIn(List<Byte> values) {
            addCriterion("goods_status in", values, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotIn(List<Byte> values) {
            addCriterion("goods_status not in", values, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusBetween(Byte value1, Byte value2) {
            addCriterion("goods_status between", value1, value2, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("goods_status not between", value1, value2, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andIsSellIsNull() {
            addCriterion("is_sell is null");
            return (Criteria) this;
        }

        public Criteria andIsSellIsNotNull() {
            addCriterion("is_sell is not null");
            return (Criteria) this;
        }

        public Criteria andIsSellEqualTo(Boolean value) {
            addCriterion("is_sell =", value, "isSell");
            return (Criteria) this;
        }

        public Criteria andIsSellNotEqualTo(Boolean value) {
            addCriterion("is_sell <>", value, "isSell");
            return (Criteria) this;
        }

        public Criteria andIsSellGreaterThan(Boolean value) {
            addCriterion("is_sell >", value, "isSell");
            return (Criteria) this;
        }

        public Criteria andIsSellGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_sell >=", value, "isSell");
            return (Criteria) this;
        }

        public Criteria andIsSellLessThan(Boolean value) {
            addCriterion("is_sell <", value, "isSell");
            return (Criteria) this;
        }

        public Criteria andIsSellLessThanOrEqualTo(Boolean value) {
            addCriterion("is_sell <=", value, "isSell");
            return (Criteria) this;
        }

        public Criteria andIsSellIn(List<Boolean> values) {
            addCriterion("is_sell in", values, "isSell");
            return (Criteria) this;
        }

        public Criteria andIsSellNotIn(List<Boolean> values) {
            addCriterion("is_sell not in", values, "isSell");
            return (Criteria) this;
        }

        public Criteria andIsSellBetween(Boolean value1, Boolean value2) {
            addCriterion("is_sell between", value1, value2, "isSell");
            return (Criteria) this;
        }

        public Criteria andIsSellNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_sell not between", value1, value2, "isSell");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountIsNull() {
            addCriterion("soldout_count is null");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountIsNotNull() {
            addCriterion("soldout_count is not null");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountEqualTo(Integer value) {
            addCriterion("soldout_count =", value, "soldoutCount");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountNotEqualTo(Integer value) {
            addCriterion("soldout_count <>", value, "soldoutCount");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountGreaterThan(Integer value) {
            addCriterion("soldout_count >", value, "soldoutCount");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("soldout_count >=", value, "soldoutCount");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountLessThan(Integer value) {
            addCriterion("soldout_count <", value, "soldoutCount");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountLessThanOrEqualTo(Integer value) {
            addCriterion("soldout_count <=", value, "soldoutCount");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountIn(List<Integer> values) {
            addCriterion("soldout_count in", values, "soldoutCount");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountNotIn(List<Integer> values) {
            addCriterion("soldout_count not in", values, "soldoutCount");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountBetween(Integer value1, Integer value2) {
            addCriterion("soldout_count between", value1, value2, "soldoutCount");
            return (Criteria) this;
        }

        public Criteria andSoldoutCountNotBetween(Integer value1, Integer value2) {
            addCriterion("soldout_count not between", value1, value2, "soldoutCount");
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