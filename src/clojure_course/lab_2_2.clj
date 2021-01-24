(ns clojure-course.lab-2-2
  (:require [clojure-course.lab-2-1 :refer :all]))

(defn lazy-partial-integral-sums
  [f]
  (map first (iterate
               (fn [[sum i]] [(+ sum (trapezoidal-rule-i f (inc i))) (inc i)])
               [0 0])))

(def memoized-lazy-partial-integral-sums (memoize lazy-partial-integral-sums))

(defn integral-partial-sums
  [f]
  (fn [x] (let [n (calc-n x)]
            (nth (memoized-lazy-partial-integral-sums f) n))))