package com.jimmy.infaction.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BrowserExample() {
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

        public Criteria andOrigin_urlIsNull() {
            addCriterion("origin_url is null");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlIsNotNull() {
            addCriterion("origin_url is not null");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlEqualTo(String value) {
            addCriterion("origin_url =", value, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlNotEqualTo(String value) {
            addCriterion("origin_url <>", value, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlGreaterThan(String value) {
            addCriterion("origin_url >", value, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlGreaterThanOrEqualTo(String value) {
            addCriterion("origin_url >=", value, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlLessThan(String value) {
            addCriterion("origin_url <", value, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlLessThanOrEqualTo(String value) {
            addCriterion("origin_url <=", value, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlLike(String value) {
            addCriterion("origin_url like", value, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlNotLike(String value) {
            addCriterion("origin_url not like", value, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlIn(List<String> values) {
            addCriterion("origin_url in", values, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlNotIn(List<String> values) {
            addCriterion("origin_url not in", values, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlBetween(String value1, String value2) {
            addCriterion("origin_url between", value1, value2, "origin_url");
            return (Criteria) this;
        }

        public Criteria andOrigin_urlNotBetween(String value1, String value2) {
            addCriterion("origin_url not between", value1, value2, "origin_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlIsNull() {
            addCriterion("action_url is null");
            return (Criteria) this;
        }

        public Criteria andAction_urlIsNotNull() {
            addCriterion("action_url is not null");
            return (Criteria) this;
        }

        public Criteria andAction_urlEqualTo(String value) {
            addCriterion("action_url =", value, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlNotEqualTo(String value) {
            addCriterion("action_url <>", value, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlGreaterThan(String value) {
            addCriterion("action_url >", value, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlGreaterThanOrEqualTo(String value) {
            addCriterion("action_url >=", value, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlLessThan(String value) {
            addCriterion("action_url <", value, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlLessThanOrEqualTo(String value) {
            addCriterion("action_url <=", value, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlLike(String value) {
            addCriterion("action_url like", value, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlNotLike(String value) {
            addCriterion("action_url not like", value, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlIn(List<String> values) {
            addCriterion("action_url in", values, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlNotIn(List<String> values) {
            addCriterion("action_url not in", values, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlBetween(String value1, String value2) {
            addCriterion("action_url between", value1, value2, "action_url");
            return (Criteria) this;
        }

        public Criteria andAction_urlNotBetween(String value1, String value2) {
            addCriterion("action_url not between", value1, value2, "action_url");
            return (Criteria) this;
        }

        public Criteria andUserIsNull() {
            addCriterion("user is null");
            return (Criteria) this;
        }

        public Criteria andUserIsNotNull() {
            addCriterion("user is not null");
            return (Criteria) this;
        }

        public Criteria andUserEqualTo(String value) {
            addCriterion("user =", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotEqualTo(String value) {
            addCriterion("user <>", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThan(String value) {
            addCriterion("user >", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThanOrEqualTo(String value) {
            addCriterion("user >=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThan(String value) {
            addCriterion("user <", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThanOrEqualTo(String value) {
            addCriterion("user <=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLike(String value) {
            addCriterion("user like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotLike(String value) {
            addCriterion("user not like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserIn(List<String> values) {
            addCriterion("user in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotIn(List<String> values) {
            addCriterion("user not in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserBetween(String value1, String value2) {
            addCriterion("user between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotBetween(String value1, String value2) {
            addCriterion("user not between", value1, value2, "user");
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

        public Criteria andHostidIsNull() {
            addCriterion("hostid is null");
            return (Criteria) this;
        }

        public Criteria andHostidIsNotNull() {
            addCriterion("hostid is not null");
            return (Criteria) this;
        }

        public Criteria andHostidEqualTo(String value) {
            addCriterion("hostid =", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidNotEqualTo(String value) {
            addCriterion("hostid <>", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidGreaterThan(String value) {
            addCriterion("hostid >", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidGreaterThanOrEqualTo(String value) {
            addCriterion("hostid >=", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidLessThan(String value) {
            addCriterion("hostid <", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidLessThanOrEqualTo(String value) {
            addCriterion("hostid <=", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidLike(String value) {
            addCriterion("hostid like", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidNotLike(String value) {
            addCriterion("hostid not like", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidIn(List<String> values) {
            addCriterion("hostid in", values, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidNotIn(List<String> values) {
            addCriterion("hostid not in", values, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidBetween(String value1, String value2) {
            addCriterion("hostid between", value1, value2, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidNotBetween(String value1, String value2) {
            addCriterion("hostid not between", value1, value2, "hostid");
            return (Criteria) this;
        }

        public Criteria andBrwtypeIsNull() {
            addCriterion("brwtype is null");
            return (Criteria) this;
        }

        public Criteria andBrwtypeIsNotNull() {
            addCriterion("brwtype is not null");
            return (Criteria) this;
        }

        public Criteria andBrwtypeEqualTo(String value) {
            addCriterion("brwtype =", value, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeNotEqualTo(String value) {
            addCriterion("brwtype <>", value, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeGreaterThan(String value) {
            addCriterion("brwtype >", value, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeGreaterThanOrEqualTo(String value) {
            addCriterion("brwtype >=", value, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeLessThan(String value) {
            addCriterion("brwtype <", value, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeLessThanOrEqualTo(String value) {
            addCriterion("brwtype <=", value, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeLike(String value) {
            addCriterion("brwtype like", value, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeNotLike(String value) {
            addCriterion("brwtype not like", value, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeIn(List<String> values) {
            addCriterion("brwtype in", values, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeNotIn(List<String> values) {
            addCriterion("brwtype not in", values, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeBetween(String value1, String value2) {
            addCriterion("brwtype between", value1, value2, "brwtype");
            return (Criteria) this;
        }

        public Criteria andBrwtypeNotBetween(String value1, String value2) {
            addCriterion("brwtype not between", value1, value2, "brwtype");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
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