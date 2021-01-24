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
      (is (= (filter long-running-pred empty-l) (pfilter-lazy long-running-pred empty-l)))
      (is (= (take 10 (filter even? naturals)) (take 10 (pfilter-lazy even? naturals)))))))

(deftest time-test-with-regular-pred-lazy
  (testing "testing filter vs pfilter-lazy with regular predicate"
    (let [l1 (list 1 2 3 4 5 6 7 8)
          l2 (list 3 7 1 5 8 4 2 6)]
      (println "load classes:")
      (time (println (pfilter-lazy even? (range 0 100))))
      (println)
      (println "with filter:")
      (time (println (filter even? l1)))
      (time (println (filter even? l2)))
      (time (println (take 10 (filter even? naturals))))
      (println)
      (println "with pfilter:")
      (time (println (pfilter-lazy even? l1)))
      (time (println (pfilter-lazy even? l2)))
      (time (println (take 10 (pfilter-lazy even? naturals)))))))

(deftest time-test-with-long-running-pred-lazy
  (testing "testing filter vs pfilter-lazy with long running predicate"
    (let [l1 (list 1 2 3 4 5 6 7 8)
          l2 (list 3 7 1 5 8 4 2 6)]
      (println "load classes:")
      (time (println (pfilter-lazy long-running-pred (range 0 10))))
      (println)
      (println "with filter:")
      (time (println (filter long-running-pred l1)))
      (time (println (filter long-running-pred l2)))
      (time (println (filter long-running-pred (range 100))))
      (time (println (take 10 (filter long-running-pred naturals))))
      (println)
      (println "with pfilter:")
      (time (println (pfilter-lazy long-running-pred l1)))
      (time (println (pfilter-lazy long-running-pred l2)))
      (time (println (pfilter-lazy long-running-pred (range 100))))
      (time (println (take 10 (pfilter-lazy long-running-pred naturals)))))))
