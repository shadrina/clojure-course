(ns clojure-course.lab-2-2-test
  (:require [clojure.test :refer :all]
            [clojure-course.core-test :refer :all]
            [clojure-course.lab-2-1 :refer :all]
            [clojure-course.lab-2-2 :refer :all]))

(deftest const-test
  (testing "testing integral with const"
    (is (almost-equal 1.0 ((integral-partial-sums const) 1)))
    (is (almost-equal 2.0 ((integral-partial-sums const) 2)))))

(deftest line-test
  (testing "testing integral with line"
    (is (almost-equal 0.5 ((integral-partial-sums line) 1)))
    (is (almost-equal 2.0 ((integral-partial-sums line) 2)))
    (is (almost-equal 96.605 ((integral-partial-sums line) 13.9)))))

(deftest sqr-test
  (testing "testing integral with sqr"
    (is (almost-equal 0.333 ((integral-partial-sums sqr) 1)))
    (is (almost-equal 576 ((integral-partial-sums sqr) 12)))))

(deftest time-test
  (testing "testing time optimization"
    (println "load classes:")
    (println (time ((integral sqr) 10)))
    (println)
    (println "sqr with integral:")
    (time ((integral sqr) 1))
    (time ((integral sqr) 2))
    (time ((integral sqr) 19.2))
    (println)
    (println "sqr with partial sum integral:")
    (time ((integral-partial-sums sqr) 1))
    (time ((integral-partial-sums sqr) 1))
    (time ((integral-partial-sums sqr) 2))
    (time ((integral-partial-sums sqr) 2))
    (time ((integral-partial-sums sqr) 19.2))
    (time ((integral-partial-sums sqr) 19.2))))