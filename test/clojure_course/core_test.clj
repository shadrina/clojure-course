(ns clojure-course.core-test
  (:require [clojure.test :refer :all]
            [clojure-course.core :refer :all]))

(def eps 0.001)

(defn almost-equal [a b]
  (< (- (max a b) (min a b)) eps))

(defn const [_] 1)
(defn line [x] x)
(defn sqr [x] (* x x))