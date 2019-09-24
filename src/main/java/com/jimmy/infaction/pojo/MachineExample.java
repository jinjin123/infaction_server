package com.jimmy.infaction.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MachineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MachineExample() {
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

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersion_infoIsNull() {
            addCriterion("version_info is null");
            return (Criteria) this;
        }

        public Criteria andVersion_infoIsNotNull() {
            addCriterion("version_info is not null");
            return (Criteria) this;
        }

        public Criteria andVersion_infoEqualTo(String value) {
            addCriterion("version_info =", value, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoNotEqualTo(String value) {
            addCriterion("version_info <>", value, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoGreaterThan(String value) {
            addCriterion("version_info >", value, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoGreaterThanOrEqualTo(String value) {
            addCriterion("version_info >=", value, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoLessThan(String value) {
            addCriterion("version_info <", value, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoLessThanOrEqualTo(String value) {
            addCriterion("version_info <=", value, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoLike(String value) {
            addCriterion("version_info like", value, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoNotLike(String value) {
            addCriterion("version_info not like", value, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoIn(List<String> values) {
            addCriterion("version_info in", values, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoNotIn(List<String> values) {
            addCriterion("version_info not in", values, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoBetween(String value1, String value2) {
            addCriterion("version_info between", value1, value2, "version_info");
            return (Criteria) this;
        }

        public Criteria andVersion_infoNotBetween(String value1, String value2) {
            addCriterion("version_info not between", value1, value2, "version_info");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNull() {
            addCriterion("uptime is null");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNotNull() {
            addCriterion("uptime is not null");
            return (Criteria) this;
        }

        public Criteria andUptimeEqualTo(Integer value) {
            addCriterion("uptime =", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotEqualTo(Integer value) {
            addCriterion("uptime <>", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThan(Integer value) {
            addCriterion("uptime >", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("uptime >=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThan(Integer value) {
            addCriterion("uptime <", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThanOrEqualTo(Integer value) {
            addCriterion("uptime <=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeIn(List<Integer> values) {
            addCriterion("uptime in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotIn(List<Integer> values) {
            addCriterion("uptime not in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeBetween(Integer value1, Integer value2) {
            addCriterion("uptime between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotBetween(Integer value1, Integer value2) {
            addCriterion("uptime not between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andCpuIsNull() {
            addCriterion("cpu is null");
            return (Criteria) this;
        }

        public Criteria andCpuIsNotNull() {
            addCriterion("cpu is not null");
            return (Criteria) this;
        }

        public Criteria andCpuEqualTo(Integer value) {
            addCriterion("cpu =", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuNotEqualTo(Integer value) {
            addCriterion("cpu <>", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuGreaterThan(Integer value) {
            addCriterion("cpu >", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuGreaterThanOrEqualTo(Integer value) {
            addCriterion("cpu >=", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuLessThan(Integer value) {
            addCriterion("cpu <", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuLessThanOrEqualTo(Integer value) {
            addCriterion("cpu <=", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuIn(List<Integer> values) {
            addCriterion("cpu in", values, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuNotIn(List<Integer> values) {
            addCriterion("cpu not in", values, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuBetween(Integer value1, Integer value2) {
            addCriterion("cpu between", value1, value2, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuNotBetween(Integer value1, Integer value2) {
            addCriterion("cpu not between", value1, value2, "cpu");
            return (Criteria) this;
        }

        public Criteria andMemoryIsNull() {
            addCriterion("memory is null");
            return (Criteria) this;
        }

        public Criteria andMemoryIsNotNull() {
            addCriterion("memory is not null");
            return (Criteria) this;
        }

        public Criteria andMemoryEqualTo(Integer value) {
            addCriterion("memory =", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryNotEqualTo(Integer value) {
            addCriterion("memory <>", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryGreaterThan(Integer value) {
            addCriterion("memory >", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("memory >=", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryLessThan(Integer value) {
            addCriterion("memory <", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryLessThanOrEqualTo(Integer value) {
            addCriterion("memory <=", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryIn(List<Integer> values) {
            addCriterion("memory in", values, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryNotIn(List<Integer> values) {
            addCriterion("memory not in", values, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryBetween(Integer value1, Integer value2) {
            addCriterion("memory between", value1, value2, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryNotBetween(Integer value1, Integer value2) {
            addCriterion("memory not between", value1, value2, "memory");
            return (Criteria) this;
        }

        public Criteria andDiskIsNull() {
            addCriterion("disk is null");
            return (Criteria) this;
        }

        public Criteria andDiskIsNotNull() {
            addCriterion("disk is not null");
            return (Criteria) this;
        }

        public Criteria andDiskEqualTo(String value) {
            addCriterion("disk =", value, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskNotEqualTo(String value) {
            addCriterion("disk <>", value, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskGreaterThan(String value) {
            addCriterion("disk >", value, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskGreaterThanOrEqualTo(String value) {
            addCriterion("disk >=", value, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskLessThan(String value) {
            addCriterion("disk <", value, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskLessThanOrEqualTo(String value) {
            addCriterion("disk <=", value, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskLike(String value) {
            addCriterion("disk like", value, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskNotLike(String value) {
            addCriterion("disk not like", value, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskIn(List<String> values) {
            addCriterion("disk in", values, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskNotIn(List<String> values) {
            addCriterion("disk not in", values, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskBetween(String value1, String value2) {
            addCriterion("disk between", value1, value2, "disk");
            return (Criteria) this;
        }

        public Criteria andDiskNotBetween(String value1, String value2) {
            addCriterion("disk not between", value1, value2, "disk");
            return (Criteria) this;
        }

        public Criteria andNetcardIsNull() {
            addCriterion("netcard is null");
            return (Criteria) this;
        }

        public Criteria andNetcardIsNotNull() {
            addCriterion("netcard is not null");
            return (Criteria) this;
        }

        public Criteria andNetcardEqualTo(String value) {
            addCriterion("netcard =", value, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardNotEqualTo(String value) {
            addCriterion("netcard <>", value, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardGreaterThan(String value) {
            addCriterion("netcard >", value, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardGreaterThanOrEqualTo(String value) {
            addCriterion("netcard >=", value, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardLessThan(String value) {
            addCriterion("netcard <", value, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardLessThanOrEqualTo(String value) {
            addCriterion("netcard <=", value, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardLike(String value) {
            addCriterion("netcard like", value, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardNotLike(String value) {
            addCriterion("netcard not like", value, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardIn(List<String> values) {
            addCriterion("netcard in", values, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardNotIn(List<String> values) {
            addCriterion("netcard not in", values, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardBetween(String value1, String value2) {
            addCriterion("netcard between", value1, value2, "netcard");
            return (Criteria) this;
        }

        public Criteria andNetcardNotBetween(String value1, String value2) {
            addCriterion("netcard not between", value1, value2, "netcard");
            return (Criteria) this;
        }

        public Criteria andOipIsNull() {
            addCriterion("oip is null");
            return (Criteria) this;
        }

        public Criteria andOipIsNotNull() {
            addCriterion("oip is not null");
            return (Criteria) this;
        }

        public Criteria andOipEqualTo(String value) {
            addCriterion("oip =", value, "oip");
            return (Criteria) this;
        }

        public Criteria andOipNotEqualTo(String value) {
            addCriterion("oip <>", value, "oip");
            return (Criteria) this;
        }

        public Criteria andOipGreaterThan(String value) {
            addCriterion("oip >", value, "oip");
            return (Criteria) this;
        }

        public Criteria andOipGreaterThanOrEqualTo(String value) {
            addCriterion("oip >=", value, "oip");
            return (Criteria) this;
        }

        public Criteria andOipLessThan(String value) {
            addCriterion("oip <", value, "oip");
            return (Criteria) this;
        }

        public Criteria andOipLessThanOrEqualTo(String value) {
            addCriterion("oip <=", value, "oip");
            return (Criteria) this;
        }

        public Criteria andOipLike(String value) {
            addCriterion("oip like", value, "oip");
            return (Criteria) this;
        }

        public Criteria andOipNotLike(String value) {
            addCriterion("oip not like", value, "oip");
            return (Criteria) this;
        }

        public Criteria andOipIn(List<String> values) {
            addCriterion("oip in", values, "oip");
            return (Criteria) this;
        }

        public Criteria andOipNotIn(List<String> values) {
            addCriterion("oip not in", values, "oip");
            return (Criteria) this;
        }

        public Criteria andOipBetween(String value1, String value2) {
            addCriterion("oip between", value1, value2, "oip");
            return (Criteria) this;
        }

        public Criteria andOipNotBetween(String value1, String value2) {
            addCriterion("oip not between", value1, value2, "oip");
            return (Criteria) this;
        }

        public Criteria andIspIsNull() {
            addCriterion("isp is null");
            return (Criteria) this;
        }

        public Criteria andIspIsNotNull() {
            addCriterion("isp is not null");
            return (Criteria) this;
        }

        public Criteria andIspEqualTo(String value) {
            addCriterion("isp =", value, "isp");
            return (Criteria) this;
        }

        public Criteria andIspNotEqualTo(String value) {
            addCriterion("isp <>", value, "isp");
            return (Criteria) this;
        }

        public Criteria andIspGreaterThan(String value) {
            addCriterion("isp >", value, "isp");
            return (Criteria) this;
        }

        public Criteria andIspGreaterThanOrEqualTo(String value) {
            addCriterion("isp >=", value, "isp");
            return (Criteria) this;
        }

        public Criteria andIspLessThan(String value) {
            addCriterion("isp <", value, "isp");
            return (Criteria) this;
        }

        public Criteria andIspLessThanOrEqualTo(String value) {
            addCriterion("isp <=", value, "isp");
            return (Criteria) this;
        }

        public Criteria andIspLike(String value) {
            addCriterion("isp like", value, "isp");
            return (Criteria) this;
        }

        public Criteria andIspNotLike(String value) {
            addCriterion("isp not like", value, "isp");
            return (Criteria) this;
        }

        public Criteria andIspIn(List<String> values) {
            addCriterion("isp in", values, "isp");
            return (Criteria) this;
        }

        public Criteria andIspNotIn(List<String> values) {
            addCriterion("isp not in", values, "isp");
            return (Criteria) this;
        }

        public Criteria andIspBetween(String value1, String value2) {
            addCriterion("isp between", value1, value2, "isp");
            return (Criteria) this;
        }

        public Criteria andIspNotBetween(String value1, String value2) {
            addCriterion("isp not between", value1, value2, "isp");
            return (Criteria) this;
        }

        public Criteria andLonIsNull() {
            addCriterion("lon is null");
            return (Criteria) this;
        }

        public Criteria andLonIsNotNull() {
            addCriterion("lon is not null");
            return (Criteria) this;
        }

        public Criteria andLonEqualTo(Double value) {
            addCriterion("lon =", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotEqualTo(Double value) {
            addCriterion("lon <>", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonGreaterThan(Double value) {
            addCriterion("lon >", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonGreaterThanOrEqualTo(Double value) {
            addCriterion("lon >=", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonLessThan(Double value) {
            addCriterion("lon <", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonLessThanOrEqualTo(Double value) {
            addCriterion("lon <=", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonIn(List<Double> values) {
            addCriterion("lon in", values, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotIn(List<Double> values) {
            addCriterion("lon not in", values, "lon");
            return (Criteria) this;
        }

        public Criteria andLonBetween(Double value1, Double value2) {
            addCriterion("lon between", value1, value2, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotBetween(Double value1, Double value2) {
            addCriterion("lon not between", value1, value2, "lon");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(Double value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(Double value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(Double value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(Double value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(Double value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(Double value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<Double> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<Double> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(Double value1, Double value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(Double value1, Double value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andDownIsNull() {
            addCriterion("down is null");
            return (Criteria) this;
        }

        public Criteria andDownIsNotNull() {
            addCriterion("down is not null");
            return (Criteria) this;
        }

        public Criteria andDownEqualTo(Integer value) {
            addCriterion("down =", value, "down");
            return (Criteria) this;
        }

        public Criteria andDownNotEqualTo(Integer value) {
            addCriterion("down <>", value, "down");
            return (Criteria) this;
        }

        public Criteria andDownGreaterThan(Integer value) {
            addCriterion("down >", value, "down");
            return (Criteria) this;
        }

        public Criteria andDownGreaterThanOrEqualTo(Integer value) {
            addCriterion("down >=", value, "down");
            return (Criteria) this;
        }

        public Criteria andDownLessThan(Integer value) {
            addCriterion("down <", value, "down");
            return (Criteria) this;
        }

        public Criteria andDownLessThanOrEqualTo(Integer value) {
            addCriterion("down <=", value, "down");
            return (Criteria) this;
        }

        public Criteria andDownIn(List<Integer> values) {
            addCriterion("down in", values, "down");
            return (Criteria) this;
        }

        public Criteria andDownNotIn(List<Integer> values) {
            addCriterion("down not in", values, "down");
            return (Criteria) this;
        }

        public Criteria andDownBetween(Integer value1, Integer value2) {
            addCriterion("down between", value1, value2, "down");
            return (Criteria) this;
        }

        public Criteria andDownNotBetween(Integer value1, Integer value2) {
            addCriterion("down not between", value1, value2, "down");
            return (Criteria) this;
        }

        public Criteria andUpIsNull() {
            addCriterion("up is null");
            return (Criteria) this;
        }

        public Criteria andUpIsNotNull() {
            addCriterion("up is not null");
            return (Criteria) this;
        }

        public Criteria andUpEqualTo(Integer value) {
            addCriterion("up =", value, "up");
            return (Criteria) this;
        }

        public Criteria andUpNotEqualTo(Integer value) {
            addCriterion("up <>", value, "up");
            return (Criteria) this;
        }

        public Criteria andUpGreaterThan(Integer value) {
            addCriterion("up >", value, "up");
            return (Criteria) this;
        }

        public Criteria andUpGreaterThanOrEqualTo(Integer value) {
            addCriterion("up >=", value, "up");
            return (Criteria) this;
        }

        public Criteria andUpLessThan(Integer value) {
            addCriterion("up <", value, "up");
            return (Criteria) this;
        }

        public Criteria andUpLessThanOrEqualTo(Integer value) {
            addCriterion("up <=", value, "up");
            return (Criteria) this;
        }

        public Criteria andUpIn(List<Integer> values) {
            addCriterion("up in", values, "up");
            return (Criteria) this;
        }

        public Criteria andUpNotIn(List<Integer> values) {
            addCriterion("up not in", values, "up");
            return (Criteria) this;
        }

        public Criteria andUpBetween(Integer value1, Integer value2) {
            addCriterion("up between", value1, value2, "up");
            return (Criteria) this;
        }

        public Criteria andUpNotBetween(Integer value1, Integer value2) {
            addCriterion("up not between", value1, value2, "up");
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