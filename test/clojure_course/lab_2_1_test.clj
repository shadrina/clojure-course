(ns clojure-course.lab-2-1-test
  (:require [clojure.test :refer :all]
            [clojure-course.lab-2-1 :refer :all]))

(def eps 0.001)

(defn almost-equal [a b]
  (< (- (max a b) (min a b)) eps))

(defn const [_] 1)
(defn line [x] x)
(defn sqr [x] (* x x))

(deftest const-test
  (testing "testing integral with const"
    (is (almost-equal 1.0 ((integral const) 1)))
    (is (almost-equal 2.0 ((integral const) 2)))))

(deftest line-test
  (testing "testing integral with line"
    (is (almost-equal 0.5 ((integral line) 1)))
    (is (almost-equal 2.0 ((integral line) 2)))
    (is (almost-equal 96.605 ((integral line) 13.9)))))

(deftest sqr-test
  (testing "testing integral with sqr"
    (is (almost-equal 0.333 ((integral sqr) 1)))
    (is (almost-equal 576 ((integral sqr) 12)))))