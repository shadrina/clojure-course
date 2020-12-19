(ns clojure-course.lab-2-1-test
  (:require [clojure.test :refer :all]
            [clojure-course.lab-2-1 :refer :all]))

(def eps 0.01)

(defn almost-equal [a b]
  (< (- (max a b) (min a b)) eps))

(defn const [_] 1)
(defn line [x] x)
(defn sqr [x] (* x x))

(deftest integral-test
  (testing "testing integral")
  (is (= ((integral const) 1) 2))
  (is (= 0 1)))

;(deftest task21-test-simple
;  (testing "task21 test simple function"
;    (let [f (integr (fn [_] 2))]
;      (is (= (f 1) 2.0))
;      (is (= (f 2) 4.0))
;      (is (= (f 5) 10.0)))
;    (let [f (integr (fn [x] x))]
;      (is (= (f 1) 0.5))
;      (is (= (f 2) 2.0))
;      (is (= (f 5) 12.5)))
;    (let [f (integr (fn [x] (* x x))) epsilon 0.001]
;      (is (almost-equal (f 1) (xxs 1) epsilon))
;      (is (almost-equal (f 2) (xxs 2) epsilon))
;      (is (almost-equal (f 5) (xxs 5) epsilon)))))