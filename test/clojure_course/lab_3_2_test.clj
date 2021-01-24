(ns clojure-course.lab-3-2-test
  (:require [clojure.test :refer :all]
            [clojure-course.lab-3-2 :refer :all]
            [clojure-course.lab-3-1-test :refer :all]))

(def naturals (iterate inc 1))

(deftest correctness-test-lazy
  (testing "testing pfilter-lazy correctness"
    (let [l1 (list 1 2 3 4 5 6 7 8)
          l2 (list 3 7 1 5 8 4 2 6)
          empty-l (list)]
      (is (= (filter even? l1) (pfilter-lazy even? l1)))
      (is (= (filter even? l2) (pfilter-lazy even? l2)))
      (is (= (filter even? empty-l) (pfilter-lazy even? empty-l)))
      (is (= (filter long-running-pred l1) (pfilter-lazy long-running-pred l1)))
      (is (= (filter long-running-pred l2) (pfilter-lazy long-running-pred l2)))
      (is (= (filter long-running-pred empty-l) (pfilter-lazy long-running-pred empty-l))))))
