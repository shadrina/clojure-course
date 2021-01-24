(ns clojure-course.lab-3-2
  (:require [clojure-course.lab-3-1 :refer :all]))

(def batch-size-default 3)

(defn my-partition-with-fixed-batch
  [batch-size coll]
  (when (not (empty? coll))
    (cons
      (take batch-size coll)
      (lazy-seq (my-partition-with-fixed-batch batch-size (drop batch-size coll))))))

(defn pfilter-lazy
  ([pred coll] (pfilter-lazy pred coll batch-size-default))
  ([pred coll batch-size]
   (->> (my-partition-with-fixed-batch batch-size coll)
        (my-partition-with-fixed-batch threads)
        (mapcat (fn [block]
               (->> block
                    (my-pmap #(doall (filter pred %))))))
        (apply concat))))