(ns clojure-course.lab-3-1-test
  (:require [clojure.test :refer :all]
            [clojure-course.lab-3-1 :refer :all]))

(defn long-running-pred [x]
  (Thread/sleep 100)
  (even? x))

(deftest correctness-test
  (testing "testing pfilter correctness"
    (let [l1 (list 1 2 3 4 5 6 7 8)
          l2 (list 3 7 1 5 8 4 2 6)
          empty-l (list)]
      (is (= (filter even? l1) (pfilter even? l1)))
      (is (= (filter even? l2) (pfilter even? l2)))
      (is (= (filter even? empty-l) (pfilter even? empty-l)))
      (is (= (filter long-running-pred l1) (pfilter long-running-pred l1)))
      (is (= (filter long-running-pred l2) (pfilter long-running-pred l2)))
      (is (= (filter long-running-pred empty-l) (pfilter long-running-pred empty-l))))))

(deftest time-test-with-regular-pred
  (testing "testing filter vs pfilter with regular predicate"
    (let [l1 (list 1 2 3 4 5 6 7 8)
          l2 (list 3 7 1 5 8 4 2 6)]
      (println "load classes:")
      (time (println (pfilter even? (range 0 100))))
      (println)
      (println "with filter:")
      (time (println (filter even? l1)))
      (time (println (filter even? l2)))
      (println)
      (println "with pfilter:")
      (time (println (pfilter even? l1)))
      (time (println (pfilter even? l2))))))

(deftest time-test-with-long-running-pred
  (testing "testing filter vs pfilter with long running predicate"
    (let [l1 (list 1 2 3 4 5 6 7 8)
          l2 (list 3 7 1 5 8 4 2 6)]
      (println "load classes:")
      (time (println (pfilter long-running-pred (range 0 10))))
      (println)
      (println "with filter:")
      (time (println (filter long-running-pred l1)))
      (time (println (filter long-running-pred l2)))
      (time (println (filter long-running-pred (range 0 100))))
      (println)
      (println "with pfilter:")
      (time (println (pfilter long-running-pred l1)))
      (time (println (pfilter long-running-pred l2)))
      (time (println (pfilter long-running-pred (range 0 100)))))))