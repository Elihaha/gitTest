package com.bst.backcommon.permission.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperatorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperatorExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andStatueIsNull() {
            addCriterion("statue is null");
            return (Criteria) this;
        }

        public Criteria andStatueIsNotNull() {
            addCriterion("statue is not null");
            return (Criteria) this;
        }

        public Criteria andStatueEqualTo(Integer value) {
            addCriterion("statue =", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotEqualTo(Integer value) {
            addCriterion("statue <>", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueGreaterThan(Integer value) {
            addCriterion("statue >", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueGreaterThanOrEqualTo(Integer value) {
            addCriterion("statue >=", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueLessThan(Integer value) {
            addCriterion("statue <", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueLessThanOrEqualTo(Integer value) {
            addCriterion("statue <=", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueIn(List<Integer> values) {
            addCriterion("statue in", values, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotIn(List<Integer> values) {
            addCriterion("statue not in", values, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueBetween(Integer value1, Integer value2) {
            addCriterion("statue between", value1, value2, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotBetween(Integer value1, Integer value2) {
            addCriterion("statue not between", value1, value2, "statue");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
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

        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("salt not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andMacroIdIsNull() {
            addCriterion("macro_id is null");
            return (Criteria) this;
        }

        public Criteria andMacroIdIsNotNull() {
            addCriterion("macro_id is not null");
            return (Criteria) this;
        }

        public Criteria andMacroIdEqualTo(String value) {
            addCriterion("macro_id =", value, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdNotEqualTo(String value) {
            addCriterion("macro_id <>", value, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdGreaterThan(String value) {
            addCriterion("macro_id >", value, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdGreaterThanOrEqualTo(String value) {
            addCriterion("macro_id >=", value, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdLessThan(String value) {
            addCriterion("macro_id <", value, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdLessThanOrEqualTo(String value) {
            addCriterion("macro_id <=", value, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdLike(String value) {
            addCriterion("macro_id like", value, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdNotLike(String value) {
            addCriterion("macro_id not like", value, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdIn(List<String> values) {
            addCriterion("macro_id in", values, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdNotIn(List<String> values) {
            addCriterion("macro_id not in", values, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdBetween(String value1, String value2) {
            addCriterion("macro_id between", value1, value2, "macroId");
            return (Criteria) this;
        }

        public Criteria andMacroIdNotBetween(String value1, String value2) {
            addCriterion("macro_id not between", value1, value2, "macroId");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Integer value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Integer value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Integer value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Integer value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Integer value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Integer> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Integer> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Integer value1, Integer value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Integer value1, Integer value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andUpdatorIsNull() {
            addCriterion("updator is null");
            return (Criteria) this;
        }

        public Criteria andUpdatorIsNotNull() {
            addCriterion("updator is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatorEqualTo(Integer value) {
            addCriterion("updator =", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotEqualTo(Integer value) {
            addCriterion("updator <>", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorGreaterThan(Integer value) {
            addCriterion("updator >", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("updator >=", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLessThan(Integer value) {
            addCriterion("updator <", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLessThanOrEqualTo(Integer value) {
            addCriterion("updator <=", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorIn(List<Integer> values) {
            addCriterion("updator in", values, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotIn(List<Integer> values) {
            addCriterion("updator not in", values, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorBetween(Integer value1, Integer value2) {
            addCriterion("updator between", value1, value2, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotBetween(Integer value1, Integer value2) {
            addCriterion("updator not between", value1, value2, "updator");
            return (Criteria) this;
        }

        public Criteria andHeadUrlIsNull() {
            addCriterion("head_url is null");
            return (Criteria) this;
        }

        public Criteria andHeadUrlIsNotNull() {
            addCriterion("head_url is not null");
            return (Criteria) this;
        }

        public Criteria andHeadUrlEqualTo(String value) {
            addCriterion("head_url =", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotEqualTo(String value) {
            addCriterion("head_url <>", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlGreaterThan(String value) {
            addCriterion("head_url >", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlGreaterThanOrEqualTo(String value) {
            addCriterion("head_url >=", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLessThan(String value) {
            addCriterion("head_url <", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLessThanOrEqualTo(String value) {
            addCriterion("head_url <=", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLike(String value) {
            addCriterion("head_url like", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotLike(String value) {
            addCriterion("head_url not like", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlIn(List<String> values) {
            addCriterion("head_url in", values, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotIn(List<String> values) {
            addCriterion("head_url not in", values, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlBetween(String value1, String value2) {
            addCriterion("head_url between", value1, value2, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotBetween(String value1, String value2) {
            addCriterion("head_url not between", value1, value2, "headUrl");
            return (Criteria) this;
        }

        public Criteria andPointsIsNull() {
            addCriterion("points is null");
            return (Criteria) this;
        }

        public Criteria andPointsIsNotNull() {
            addCriterion("points is not null");
            return (Criteria) this;
        }

        public Criteria andPointsEqualTo(Integer value) {
            addCriterion("points =", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotEqualTo(Integer value) {
            addCriterion("points <>", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThan(Integer value) {
            addCriterion("points >", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThanOrEqualTo(Integer value) {
            addCriterion("points >=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThan(Integer value) {
            addCriterion("points <", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThanOrEqualTo(Integer value) {
            addCriterion("points <=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsIn(List<Integer> values) {
            addCriterion("points in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotIn(List<Integer> values) {
            addCriterion("points not in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsBetween(Integer value1, Integer value2) {
            addCriterion("points between", value1, value2, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotBetween(Integer value1, Integer value2) {
            addCriterion("points not between", value1, value2, "points");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andUserColumnIsNull() {
            addCriterion("user_column is null");
            return (Criteria) this;
        }

        public Criteria andUserColumnIsNotNull() {
            addCriterion("user_column is not null");
            return (Criteria) this;
        }

        public Criteria andUserColumnEqualTo(Integer value) {
            addCriterion("user_column =", value, "userColumn");
            return (Criteria) this;
        }

        public Criteria andUserColumnNotEqualTo(Integer value) {
            addCriterion("user_column <>", value, "userColumn");
            return (Criteria) this;
        }

        public Criteria andUserColumnGreaterThan(Integer value) {
            addCriterion("user_column >", value, "userColumn");
            return (Criteria) this;
        }

        public Criteria andUserColumnGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_column >=", value, "userColumn");
            return (Criteria) this;
        }

        public Criteria andUserColumnLessThan(Integer value) {
            addCriterion("user_column <", value, "userColumn");
            return (Criteria) this;
        }

        public Criteria andUserColumnLessThanOrEqualTo(Integer value) {
            addCriterion("user_column <=", value, "userColumn");
            return (Criteria) this;
        }

        public Criteria andUserColumnIn(List<Integer> values) {
            addCriterion("user_column in", values, "userColumn");
            return (Criteria) this;
        }

        public Criteria andUserColumnNotIn(List<Integer> values) {
            addCriterion("user_column not in", values, "userColumn");
            return (Criteria) this;
        }

        public Criteria andUserColumnBetween(Integer value1, Integer value2) {
            addCriterion("user_column between", value1, value2, "userColumn");
            return (Criteria) this;
        }

        public Criteria andUserColumnNotBetween(Integer value1, Integer value2) {
            addCriterion("user_column not between", value1, value2, "userColumn");
            return (Criteria) this;
        }


        public Criteria andManageFlagIsNull() {
            addCriterion("manage_flag is null");
            return (Criteria) this;
        }

        public Criteria andManageFlagIsNotNull() {
            addCriterion("manage_flag is not null");
            return (Criteria) this;
        }

        public Criteria andManageFlagEqualTo(Integer value) {
            addCriterion("manage_flag =", value, "manageFlag");
            return (Criteria) this;
        }

        public Criteria andManageFlagNotEqualTo(Integer value) {
            addCriterion("manage_flag <>", value, "manageFlag");
            return (Criteria) this;
        }

        public Criteria andManageFlagGreaterThan(Integer value) {
            addCriterion("manage_flag >", value, "manageFlag");
            return (Criteria) this;
        }

        public Criteria andManageFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("manage_flag >=", value, "manageFlag");
            return (Criteria) this;
        }

        public Criteria andManageFlagLessThan(Integer value) {
            addCriterion("manage_flag <", value, "manageFlag");
            return (Criteria) this;
        }

        public Criteria andManageFlagLessThanOrEqualTo(Integer value) {
            addCriterion("manage_flag <=", value, "manageFlag");
            return (Criteria) this;
        }

        public Criteria andManageFlagIn(List<Integer> values) {
            addCriterion("manage_flag in", values, "manageFlag");
            return (Criteria) this;
        }

        public Criteria andManageFlagNotIn(List<Integer> values) {
            addCriterion("manage_flag not in", values, "manageFlag");
            return (Criteria) this;
        }

        public Criteria andManageFlagBetween(Integer value1, Integer value2) {
            addCriterion("manage_flag between", value1, value2, "manageFlag");
            return (Criteria) this;
        }

        public Criteria andManageFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("manage_flag not between", value1, value2, "manageFlag");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andIdentitysIsNull() {
            addCriterion("identitys is null");
            return (Criteria) this;
        }

        public Criteria andIdentitysIsNotNull() {
            addCriterion("identitys is not null");
            return (Criteria) this;
        }

        public Criteria andIdentitysEqualTo(String value) {
            addCriterion("identitys =", value, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysNotEqualTo(String value) {
            addCriterion("identitys <>", value, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysGreaterThan(String value) {
            addCriterion("identitys >", value, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysGreaterThanOrEqualTo(String value) {
            addCriterion("identitys >=", value, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysLessThan(String value) {
            addCriterion("identitys <", value, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysLessThanOrEqualTo(String value) {
            addCriterion("identitys <=", value, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysLike(String value) {
            addCriterion("identitys like", value, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysNotLike(String value) {
            addCriterion("identitys not like", value, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysIn(List<String> values) {
            addCriterion("identitys in", values, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysNotIn(List<String> values) {
            addCriterion("identitys not in", values, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysBetween(String value1, String value2) {
            addCriterion("identitys between", value1, value2, "identitys");
            return (Criteria) this;
        }

        public Criteria andIdentitysNotBetween(String value1, String value2) {
            addCriterion("identitys not between", value1, value2, "identitys");
            return (Criteria) this;
        }

        public Criteria andGradesIsNull() {
            addCriterion("grades is null");
            return (Criteria) this;
        }

        public Criteria andGradesIsNotNull() {
            addCriterion("grades is not null");
            return (Criteria) this;
        }

        public Criteria andGradesEqualTo(String value) {
            addCriterion("grades =", value, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesNotEqualTo(String value) {
            addCriterion("grades <>", value, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesGreaterThan(String value) {
            addCriterion("grades >", value, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesGreaterThanOrEqualTo(String value) {
            addCriterion("grades >=", value, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesLessThan(String value) {
            addCriterion("grades <", value, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesLessThanOrEqualTo(String value) {
            addCriterion("grades <=", value, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesLike(String value) {
            addCriterion("grades like", value, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesNotLike(String value) {
            addCriterion("grades not like", value, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesIn(List<String> values) {
            addCriterion("grades in", values, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesNotIn(List<String> values) {
            addCriterion("grades not in", values, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesBetween(String value1, String value2) {
            addCriterion("grades between", value1, value2, "grades");
            return (Criteria) this;
        }

        public Criteria andGradesNotBetween(String value1, String value2) {
            addCriterion("grades not between", value1, value2, "grades");
            return (Criteria) this;
        }

        public Criteria andCitysIsNull() {
            addCriterion("citys is null");
            return (Criteria) this;
        }

        public Criteria andCitysIsNotNull() {
            addCriterion("citys is not null");
            return (Criteria) this;
        }

        public Criteria andCitysEqualTo(String value) {
            addCriterion("citys =", value, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysNotEqualTo(String value) {
            addCriterion("citys <>", value, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysGreaterThan(String value) {
            addCriterion("citys >", value, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysGreaterThanOrEqualTo(String value) {
            addCriterion("citys >=", value, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysLessThan(String value) {
            addCriterion("citys <", value, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysLessThanOrEqualTo(String value) {
            addCriterion("citys <=", value, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysLike(String value) {
            addCriterion("citys like", value, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysNotLike(String value) {
            addCriterion("citys not like", value, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysIn(List<String> values) {
            addCriterion("citys in", values, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysNotIn(List<String> values) {
            addCriterion("citys not in", values, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysBetween(String value1, String value2) {
            addCriterion("citys between", value1, value2, "citys");
            return (Criteria) this;
        }

        public Criteria andCitysNotBetween(String value1, String value2) {
            addCriterion("citys not between", value1, value2, "citys");
            return (Criteria) this;
        }

        public Criteria andVerifyIsNull() {
            addCriterion("verify is null");
            return (Criteria) this;
        }

        public Criteria andVerifyIsNotNull() {
            addCriterion("verify is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyEqualTo(Integer value) {
            addCriterion("verify =", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyNotEqualTo(Integer value) {
            addCriterion("verify <>", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyGreaterThan(Integer value) {
            addCriterion("verify >", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyGreaterThanOrEqualTo(Integer value) {
            addCriterion("verify >=", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyLessThan(Integer value) {
            addCriterion("verify <", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyLessThanOrEqualTo(Integer value) {
            addCriterion("verify <=", value, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyIn(List<Integer> values) {
            addCriterion("verify in", values, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyNotIn(List<Integer> values) {
            addCriterion("verify not in", values, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyBetween(Integer value1, Integer value2) {
            addCriterion("verify between", value1, value2, "verify");
            return (Criteria) this;
        }

        public Criteria andVerifyNotBetween(Integer value1, Integer value2) {
            addCriterion("verify not between", value1, value2, "verify");
            return (Criteria) this;
        }

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(Integer value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(Integer value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(Integer value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(Integer value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(Integer value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<Integer> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<Integer> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(Integer value1, Integer value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(Integer value1, Integer value2) {
            addCriterion("cid not between", value1, value2, "cid");
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