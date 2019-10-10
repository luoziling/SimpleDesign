package com.wzb.pojo;

import java.util.ArrayList;
import java.util.List;

public class DocInfo2Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DocInfo2Example() {
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

        public Criteria andDocnameIsNull() {
            addCriterion("docname is null");
            return (Criteria) this;
        }

        public Criteria andDocnameIsNotNull() {
            addCriterion("docname is not null");
            return (Criteria) this;
        }

        public Criteria andDocnameEqualTo(String value) {
            addCriterion("docname =", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameNotEqualTo(String value) {
            addCriterion("docname <>", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameGreaterThan(String value) {
            addCriterion("docname >", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameGreaterThanOrEqualTo(String value) {
            addCriterion("docname >=", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameLessThan(String value) {
            addCriterion("docname <", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameLessThanOrEqualTo(String value) {
            addCriterion("docname <=", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameLike(String value) {
            addCriterion("docname like", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameNotLike(String value) {
            addCriterion("docname not like", value, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameIn(List<String> values) {
            addCriterion("docname in", values, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameNotIn(List<String> values) {
            addCriterion("docname not in", values, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameBetween(String value1, String value2) {
            addCriterion("docname between", value1, value2, "docname");
            return (Criteria) this;
        }

        public Criteria andDocnameNotBetween(String value1, String value2) {
            addCriterion("docname not between", value1, value2, "docname");
            return (Criteria) this;
        }

        public Criteria andDocidIsNull() {
            addCriterion("docid is null");
            return (Criteria) this;
        }

        public Criteria andDocidIsNotNull() {
            addCriterion("docid is not null");
            return (Criteria) this;
        }

        public Criteria andDocidEqualTo(String value) {
            addCriterion("docid =", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotEqualTo(String value) {
            addCriterion("docid <>", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidGreaterThan(String value) {
            addCriterion("docid >", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidGreaterThanOrEqualTo(String value) {
            addCriterion("docid >=", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidLessThan(String value) {
            addCriterion("docid <", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidLessThanOrEqualTo(String value) {
            addCriterion("docid <=", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidLike(String value) {
            addCriterion("docid like", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotLike(String value) {
            addCriterion("docid not like", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidIn(List<String> values) {
            addCriterion("docid in", values, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotIn(List<String> values) {
            addCriterion("docid not in", values, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidBetween(String value1, String value2) {
            addCriterion("docid between", value1, value2, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotBetween(String value1, String value2) {
            addCriterion("docid not between", value1, value2, "docid");
            return (Criteria) this;
        }

        public Criteria andDepartmentsIsNull() {
            addCriterion("departments is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentsIsNotNull() {
            addCriterion("departments is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentsEqualTo(String value) {
            addCriterion("departments =", value, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsNotEqualTo(String value) {
            addCriterion("departments <>", value, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsGreaterThan(String value) {
            addCriterion("departments >", value, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsGreaterThanOrEqualTo(String value) {
            addCriterion("departments >=", value, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsLessThan(String value) {
            addCriterion("departments <", value, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsLessThanOrEqualTo(String value) {
            addCriterion("departments <=", value, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsLike(String value) {
            addCriterion("departments like", value, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsNotLike(String value) {
            addCriterion("departments not like", value, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsIn(List<String> values) {
            addCriterion("departments in", values, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsNotIn(List<String> values) {
            addCriterion("departments not in", values, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsBetween(String value1, String value2) {
            addCriterion("departments between", value1, value2, "departments");
            return (Criteria) this;
        }

        public Criteria andDepartmentsNotBetween(String value1, String value2) {
            addCriterion("departments not between", value1, value2, "departments");
            return (Criteria) this;
        }

        public Criteria andOccIsNull() {
            addCriterion("occ is null");
            return (Criteria) this;
        }

        public Criteria andOccIsNotNull() {
            addCriterion("occ is not null");
            return (Criteria) this;
        }

        public Criteria andOccEqualTo(String value) {
            addCriterion("occ =", value, "occ");
            return (Criteria) this;
        }

        public Criteria andOccNotEqualTo(String value) {
            addCriterion("occ <>", value, "occ");
            return (Criteria) this;
        }

        public Criteria andOccGreaterThan(String value) {
            addCriterion("occ >", value, "occ");
            return (Criteria) this;
        }

        public Criteria andOccGreaterThanOrEqualTo(String value) {
            addCriterion("occ >=", value, "occ");
            return (Criteria) this;
        }

        public Criteria andOccLessThan(String value) {
            addCriterion("occ <", value, "occ");
            return (Criteria) this;
        }

        public Criteria andOccLessThanOrEqualTo(String value) {
            addCriterion("occ <=", value, "occ");
            return (Criteria) this;
        }

        public Criteria andOccLike(String value) {
            addCriterion("occ like", value, "occ");
            return (Criteria) this;
        }

        public Criteria andOccNotLike(String value) {
            addCriterion("occ not like", value, "occ");
            return (Criteria) this;
        }

        public Criteria andOccIn(List<String> values) {
            addCriterion("occ in", values, "occ");
            return (Criteria) this;
        }

        public Criteria andOccNotIn(List<String> values) {
            addCriterion("occ not in", values, "occ");
            return (Criteria) this;
        }

        public Criteria andOccBetween(String value1, String value2) {
            addCriterion("occ between", value1, value2, "occ");
            return (Criteria) this;
        }

        public Criteria andOccNotBetween(String value1, String value2) {
            addCriterion("occ not between", value1, value2, "occ");
            return (Criteria) this;
        }

        public Criteria andNapmIsNull() {
            addCriterion("nApm is null");
            return (Criteria) this;
        }

        public Criteria andNapmIsNotNull() {
            addCriterion("nApm is not null");
            return (Criteria) this;
        }

        public Criteria andNapmEqualTo(String value) {
            addCriterion("nApm =", value, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmNotEqualTo(String value) {
            addCriterion("nApm <>", value, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmGreaterThan(String value) {
            addCriterion("nApm >", value, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmGreaterThanOrEqualTo(String value) {
            addCriterion("nApm >=", value, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmLessThan(String value) {
            addCriterion("nApm <", value, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmLessThanOrEqualTo(String value) {
            addCriterion("nApm <=", value, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmLike(String value) {
            addCriterion("nApm like", value, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmNotLike(String value) {
            addCriterion("nApm not like", value, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmIn(List<String> values) {
            addCriterion("nApm in", values, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmNotIn(List<String> values) {
            addCriterion("nApm not in", values, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmBetween(String value1, String value2) {
            addCriterion("nApm between", value1, value2, "napm");
            return (Criteria) this;
        }

        public Criteria andNapmNotBetween(String value1, String value2) {
            addCriterion("nApm not between", value1, value2, "napm");
            return (Criteria) this;
        }

        public Criteria andNaccIsNull() {
            addCriterion("nAcc is null");
            return (Criteria) this;
        }

        public Criteria andNaccIsNotNull() {
            addCriterion("nAcc is not null");
            return (Criteria) this;
        }

        public Criteria andNaccEqualTo(String value) {
            addCriterion("nAcc =", value, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccNotEqualTo(String value) {
            addCriterion("nAcc <>", value, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccGreaterThan(String value) {
            addCriterion("nAcc >", value, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccGreaterThanOrEqualTo(String value) {
            addCriterion("nAcc >=", value, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccLessThan(String value) {
            addCriterion("nAcc <", value, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccLessThanOrEqualTo(String value) {
            addCriterion("nAcc <=", value, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccLike(String value) {
            addCriterion("nAcc like", value, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccNotLike(String value) {
            addCriterion("nAcc not like", value, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccIn(List<String> values) {
            addCriterion("nAcc in", values, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccNotIn(List<String> values) {
            addCriterion("nAcc not in", values, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccBetween(String value1, String value2) {
            addCriterion("nAcc between", value1, value2, "nacc");
            return (Criteria) this;
        }

        public Criteria andNaccNotBetween(String value1, String value2) {
            addCriterion("nAcc not between", value1, value2, "nacc");
            return (Criteria) this;
        }

        public Criteria andErateIsNull() {
            addCriterion("eRate is null");
            return (Criteria) this;
        }

        public Criteria andErateIsNotNull() {
            addCriterion("eRate is not null");
            return (Criteria) this;
        }

        public Criteria andErateEqualTo(String value) {
            addCriterion("eRate =", value, "erate");
            return (Criteria) this;
        }

        public Criteria andErateNotEqualTo(String value) {
            addCriterion("eRate <>", value, "erate");
            return (Criteria) this;
        }

        public Criteria andErateGreaterThan(String value) {
            addCriterion("eRate >", value, "erate");
            return (Criteria) this;
        }

        public Criteria andErateGreaterThanOrEqualTo(String value) {
            addCriterion("eRate >=", value, "erate");
            return (Criteria) this;
        }

        public Criteria andErateLessThan(String value) {
            addCriterion("eRate <", value, "erate");
            return (Criteria) this;
        }

        public Criteria andErateLessThanOrEqualTo(String value) {
            addCriterion("eRate <=", value, "erate");
            return (Criteria) this;
        }

        public Criteria andErateLike(String value) {
            addCriterion("eRate like", value, "erate");
            return (Criteria) this;
        }

        public Criteria andErateNotLike(String value) {
            addCriterion("eRate not like", value, "erate");
            return (Criteria) this;
        }

        public Criteria andErateIn(List<String> values) {
            addCriterion("eRate in", values, "erate");
            return (Criteria) this;
        }

        public Criteria andErateNotIn(List<String> values) {
            addCriterion("eRate not in", values, "erate");
            return (Criteria) this;
        }

        public Criteria andErateBetween(String value1, String value2) {
            addCriterion("eRate between", value1, value2, "erate");
            return (Criteria) this;
        }

        public Criteria andErateNotBetween(String value1, String value2) {
            addCriterion("eRate not between", value1, value2, "erate");
            return (Criteria) this;
        }

        public Criteria andTuweipriceIsNull() {
            addCriterion("tuweiPrice is null");
            return (Criteria) this;
        }

        public Criteria andTuweipriceIsNotNull() {
            addCriterion("tuweiPrice is not null");
            return (Criteria) this;
        }

        public Criteria andTuweipriceEqualTo(String value) {
            addCriterion("tuweiPrice =", value, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceNotEqualTo(String value) {
            addCriterion("tuweiPrice <>", value, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceGreaterThan(String value) {
            addCriterion("tuweiPrice >", value, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceGreaterThanOrEqualTo(String value) {
            addCriterion("tuweiPrice >=", value, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceLessThan(String value) {
            addCriterion("tuweiPrice <", value, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceLessThanOrEqualTo(String value) {
            addCriterion("tuweiPrice <=", value, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceLike(String value) {
            addCriterion("tuweiPrice like", value, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceNotLike(String value) {
            addCriterion("tuweiPrice not like", value, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceIn(List<String> values) {
            addCriterion("tuweiPrice in", values, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceNotIn(List<String> values) {
            addCriterion("tuweiPrice not in", values, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceBetween(String value1, String value2) {
            addCriterion("tuweiPrice between", value1, value2, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andTuweipriceNotBetween(String value1, String value2) {
            addCriterion("tuweiPrice not between", value1, value2, "tuweiprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceIsNull() {
            addCriterion("shihuaPrice is null");
            return (Criteria) this;
        }

        public Criteria andShihuapriceIsNotNull() {
            addCriterion("shihuaPrice is not null");
            return (Criteria) this;
        }

        public Criteria andShihuapriceEqualTo(String value) {
            addCriterion("shihuaPrice =", value, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceNotEqualTo(String value) {
            addCriterion("shihuaPrice <>", value, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceGreaterThan(String value) {
            addCriterion("shihuaPrice >", value, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceGreaterThanOrEqualTo(String value) {
            addCriterion("shihuaPrice >=", value, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceLessThan(String value) {
            addCriterion("shihuaPrice <", value, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceLessThanOrEqualTo(String value) {
            addCriterion("shihuaPrice <=", value, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceLike(String value) {
            addCriterion("shihuaPrice like", value, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceNotLike(String value) {
            addCriterion("shihuaPrice not like", value, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceIn(List<String> values) {
            addCriterion("shihuaPrice in", values, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceNotIn(List<String> values) {
            addCriterion("shihuaPrice not in", values, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceBetween(String value1, String value2) {
            addCriterion("shihuaPrice between", value1, value2, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andShihuapriceNotBetween(String value1, String value2) {
            addCriterion("shihuaPrice not between", value1, value2, "shihuaprice");
            return (Criteria) this;
        }

        public Criteria andNcommentIsNull() {
            addCriterion("nComment is null");
            return (Criteria) this;
        }

        public Criteria andNcommentIsNotNull() {
            addCriterion("nComment is not null");
            return (Criteria) this;
        }

        public Criteria andNcommentEqualTo(String value) {
            addCriterion("nComment =", value, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentNotEqualTo(String value) {
            addCriterion("nComment <>", value, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentGreaterThan(String value) {
            addCriterion("nComment >", value, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentGreaterThanOrEqualTo(String value) {
            addCriterion("nComment >=", value, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentLessThan(String value) {
            addCriterion("nComment <", value, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentLessThanOrEqualTo(String value) {
            addCriterion("nComment <=", value, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentLike(String value) {
            addCriterion("nComment like", value, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentNotLike(String value) {
            addCriterion("nComment not like", value, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentIn(List<String> values) {
            addCriterion("nComment in", values, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentNotIn(List<String> values) {
            addCriterion("nComment not in", values, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentBetween(String value1, String value2) {
            addCriterion("nComment between", value1, value2, "ncomment");
            return (Criteria) this;
        }

        public Criteria andNcommentNotBetween(String value1, String value2) {
            addCriterion("nComment not between", value1, value2, "ncomment");
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